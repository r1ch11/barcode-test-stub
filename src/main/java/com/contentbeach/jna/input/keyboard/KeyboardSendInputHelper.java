package com.contentbeach.jna.input.keyboard;

import com.sun.jna.platform.win32.BaseTSD;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * Use to send text input to a Windows application window.
 *
 * Text input is limited to what is supported in
 * {@link com.contentbeach.jna.input.keyboard.ScanCodeHelper#lookupCode(char)}.
 */
public final class KeyboardSendInputHelper {

    /**
     * Scan-code event.
     */
    private static final int KEYEVENT_SCANCODE = 0x0008;

    /**
     * Key up event.
     */
    private static final int KEYEVENT_UP       = 0x0002;

    /**
     * Flags for the key down event.
     */
    private final WinDef.DWORD dwKeyDownFlags;

    /**
     * Flags for the key up event.
     */
    private final WinDef.DWORD dwKeyUpFlags;

    /**
     * Window handle for target window.
     */
    private final WinDef.HWND hwnd;

    /**
     * The constructor requires a window name and throws
     * {@link com.contentbeach.jna.input.keyboard.NoSuchWindowException}
     * if there is no window open with the given name.
     * @param windowName Name of the target window for which output is to be
     *                   written.
     * @throws NoSuchWindowException if no window is open with the
     * given window name.
     */
    public KeyboardSendInputHelper(final String windowName)
            throws NoSuchWindowException {
        hwnd = focusWindow(windowName);
        if (hwnd == null) {
            throw new NoSuchWindowException("Window Name: " + windowName);
        }
        dwKeyDownFlags = new WinDef.DWORD(KEYEVENT_SCANCODE);
        dwKeyUpFlags = new WinDef.DWORD(KEYEVENT_SCANCODE | KEYEVENT_UP);
    }

    /**
     * Should set focus to the window with the given name.
     * This will not work if the target window is minimized.
     * @param windowName example: "Untitled - Notepad"
     * @return window handle for the window with the given name.
     */
    private WinDef.HWND focusWindow(final String windowName) {
        HWND windowHandle = User32.INSTANCE.FindWindow(null, windowName);
        User32.INSTANCE.SetForegroundWindow(windowHandle);
        User32.INSTANCE.SetFocus(windowHandle);
        return windowHandle;
    }

    /**
     * Helper method to create a single input for a key down or key up event.
     * <p>
     *     This only sets <code>input.ki.dwFlags</code>
     *     and <code>input.ki.wScan</code> since this is
     *     just being used as a DTO for the <code>dwFlags</code>
     *     and <code>wScan</code> values.
     * </p>
     * @param scanCode Scan code that represents either a character or the
     *                 shift key.
     * @param dwFlags Flags for key down or key up.
     * @return A single input representing a key down or key up event for a
     * text character or the shift key.
     */
    private WinUser.INPUT createInput(final int scanCode,
                                      final WinDef.DWORD dwFlags) {
        WinUser.INPUT input = new WinUser.INPUT();
        // Other inputs are not needed.
        // TODO - should a Pair be used instead?
        input.input.ki.dwFlags = dwFlags;
        input.input.ki.wScan = new WinDef.WORD(scanCode);
        return input;
    }

    /**
     * Helper method used to create key down and key up events for a given
     * character.
     * @param scanCode Scan code that corresponds to a given character.
     * @param isUpperCase true if shift events are needed to make the character
     *                    upper-case in the target window.
     * @return the list of inputs with the required key down and key up events
     * to write the character that corresponds to the scanCode to the target
     * window.
     */
    private List<WinUser.INPUT> createInputs(final int scanCode,
                                             final boolean isUpperCase) {
        List<WinUser.INPUT> inputList = new ArrayList<>();
        if (isUpperCase) {
            // press shift key
            inputList.add(createInput(ScanCodeHelper.shiftKey(),
                    this.dwKeyDownFlags));
        }
        // press character key
        inputList.add(createInput(scanCode, this.dwKeyDownFlags));
        // release character key
        inputList.add(createInput(scanCode, this.dwKeyUpFlags));
        if (isUpperCase) {
            // release shift key
            inputList.add(createInput(ScanCodeHelper.shiftKey(),
                    this.dwKeyUpFlags));
        }
        return inputList;
    }

    /**
     * Calls JNA (User32.INSTANCE.SendInput) using the given scanCodes
     * to write a string of characters to the target window.
     * <p>
     *     The given chars are used to determine if shift is needed to make
     *     certain chars upper-case in the target window.
     * </p>
     * @param scanCodes Array of scan codes used to create key down
     *                  and key up events.
     * @param chars Array of chars used to determine if and where shift down
     *              and shift up events are needed to make characters upper-
     *              case in the target window.
     */
    private void writeText(final int[] scanCodes, final char[] chars) {
        Objects.requireNonNull(scanCodes,
                "Scan Codes Array handle cannot be null");
        Objects.requireNonNull(chars, "Chars Array cannot be null");
        if (scanCodes.length != chars.length) {
            throw new IllegalArgumentException(String.format(
                    "Scan Codes Array size (%d) must equal Chars Array size (%d)",
                    scanCodes.length,
                    chars.length));
        }
        List<WinUser.INPUT> inputList = new ArrayList<>();
        for (int i = 0; i < scanCodes.length; i++) {
            inputList.addAll(createInputs(scanCodes[i],
                    Character.isUpperCase(chars[i])));
        }
        
        WinUser.INPUT[] inputArray =
                (WinUser.INPUT[]) new WinUser.INPUT().toArray(
                        inputList.size());        

        for (int i = 0; i < inputList.size(); i++) {
            WinUser.INPUT input = inputList.get(i);
            inputArray[i].type = new WinDef.DWORD(WinUser.INPUT.INPUT_KEYBOARD);
            inputArray[i].input.setType("ki");
            inputArray[i].input.ki.time = new WinDef.DWORD(0);
            inputArray[i].input.ki.wVk = new WinDef.WORD(0);
            inputArray[i].input.ki.dwExtraInfo = new BaseTSD.ULONG_PTR(0);
            inputArray[i].input.ki.dwFlags = input.input.ki.dwFlags;
            inputArray[i].input.ki.wScan = input.input.ki.wScan;
        }

        WinDef.DWORD sentInputs = User32.INSTANCE.SendInput(
                new WinDef.DWORD(inputArray.length),
                inputArray,
                inputArray[0].size()
        );

        System.out.println("Sent Input Count: " + sentInputs.intValue());
    }

    /**
     * Writes the given text to the window.
     * @param text text to write to window.
     */
    public void writeText(final String text) {
        char[] chars = text.toCharArray();
        IntStream intStream = text.chars();
        int[] scanCodes = intStream.map(
                ch -> ScanCodeHelper.lookupCode((char) ch)).toArray();
        writeText(scanCodes, chars);
    }

}
