package com.contentbeach.barcode.scanner.stub;

import com.contentbeach.jna.input.keyboard.KeyboardSendInputHelper;
import com.contentbeach.jna.input.keyboard.NoSuchWindowException;

/**
 * Utilizes the {@link com.contentbeach.jna.input.keyboard.KeyboardSendInputHelper}
 * to end keyboard events to a Windows-based application window.
 * <p><em>Note:</em> this will not work if the target window is minimized.</p>
 */
public final class BarcodeScannerStub {

    /**
     * Private to prevent instantiation.
     */
    private BarcodeScannerStub() {
        // intentionally empty
    }

    /**
     * Main driver for the barcode scanner stub.
     * <p>
     *     An exit status of <code>1</code> is returned if there
     *     is an error running the barcode scanner stub.
     * </p>
     * <p>
     *     Usage:
     * </p>
     * <p>
     *     <ul>
     *         <li><code>args[0]</code> - Window title</li>
     *         <li><code>args[1]</code> - barcode</li>
     *     </ul>
     * </p>
     * @param args must include the window title and barcode.
     */
    public static void main(final String[] args) {

        if (args.length != 2) {
            System.err.println("Windows title and barcode are required.");
        }

        String windowName = args[0];
        System.out.format("Setting window title to \"%s\"%n", windowName);

        String barcode = args[1];
        System.out.format("Setting barcode to \"%s\"%n", barcode);

        KeyboardSendInputHelper helper = null;
        try {
            helper = new KeyboardSendInputHelper(windowName);
        } catch (NoSuchWindowException ex) {
            System.err.println("No window found with name: " + windowName);
            System.exit(1);
        }

        helper.writeText(barcode);
    }
}
