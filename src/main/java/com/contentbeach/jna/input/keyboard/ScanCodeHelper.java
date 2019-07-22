package com.contentbeach.jna.input.keyboard;

import winkeyboard.ScanCode;

/**
 * Provides methods to retrieve scan codes from
 * {@link winkeyboard.ScanCode} for specific text characters.
 * <p>
 * The {@link #shiftKey()} method is needed to handle upper-case
 * characters.
 * </p>
 */
public final class ScanCodeHelper {

    /**
     * Private to prevent instantiation.
     */
    private ScanCodeHelper() {
        // intentionally empty
    }

    /**
     * Use this to get the left-shift key code which is needed
     * for upper-case characters.
     * @return the left-shift scan code.
     */
    public static int shiftKey() {
        return ScanCode.DIK_LSHIFT;
    }

    /**
     * Use this to find a scan code for the given character.
     * @param ch the character.
     * @return scan code for the given character.
     */
    public static int lookupCode(final char ch) {
        int retval = 0;
        switch (Character.toLowerCase(ch)) {
            case 'a' :
                retval = ScanCode.DIK_A;
                break;
            case 'b' :
                retval = ScanCode.DIK_B;
                break;
            case 'c' :
                retval = ScanCode.DIK_C;
                break;
            case 'd' :
                retval = ScanCode.DIK_D;
                break;
            case 'e' :
                retval = ScanCode.DIK_E;
                break;
            case 'f' :
                retval = ScanCode.DIK_F;
                break;
            case 'g' :
                retval = ScanCode.DIK_G;
                break;
            case 'h' :
                retval = ScanCode.DIK_H;
                break;
            case 'i' :
                retval = ScanCode.DIK_I;
                break;
            case 'j' :
                retval = ScanCode.DIK_J;
                break;
            case 'k' :
                retval = ScanCode.DIK_K;
                break;
            case 'l' :
                retval = ScanCode.DIK_L;
                break;
            case 'm' :
                retval = ScanCode.DIK_M;
                break;
            case 'n' :
                retval = ScanCode.DIK_N;
                break;
            case 'o' :
                retval = ScanCode.DIK_O;
                break;
            case 'p' :
                retval = ScanCode.DIK_P;
                break;
            case 'q' :
                retval = ScanCode.DIK_Q;
                break;
            case 'r' :
                retval = ScanCode.DIK_R;
                break;
            case 's' :
                retval = ScanCode.DIK_S;
                break;
            case 't' :
                retval = ScanCode.DIK_T;
                break;
            case 'u' :
                retval = ScanCode.DIK_U;
                break;
            case 'v' :
                retval = ScanCode.DIK_V;
                break;
            case 'w' :
                retval = ScanCode.DIK_W;
                break;
            case 'x' :
                retval = ScanCode.DIK_X;
                break;
            case 'y' :
                retval = ScanCode.DIK_Y;
                break;
            case 'z' :
                retval = ScanCode.DIK_Z;
                break;
            case '0' :
                retval = ScanCode.DIK_0;
                break;
            case '1' :
                retval = ScanCode.DIK_1;
                break;
            case '2' :
                retval = ScanCode.DIK_2;
                break;
            case '3' :
                retval = ScanCode.DIK_3;
                break;
            case '4' :
                retval = ScanCode.DIK_4;
                break;
            case '5' :
                retval = ScanCode.DIK_5;
                break;
            case '6' :
                retval = ScanCode.DIK_6;
                break;
            case '7' :
                retval = ScanCode.DIK_7;
                break;
            case '8' :
                retval = ScanCode.DIK_8;
                break;
            case '9' :
                retval = ScanCode.DIK_9;
                break;
            case '-' :
                retval = ScanCode.DIK_MINUS;
                break;
            case '=' :
                retval = ScanCode.DIK_EQUALS;
                break;
            case '\t' :
                retval = ScanCode.DIK_TAB;
                break;
            case '[' :
                retval = ScanCode.DIK_LBRACKET;
                break;
            case ']' :
                retval = ScanCode.DIK_RBRACKET;
                break;
            case '\\' :
                retval = ScanCode.DIK_BACKSLASH;
                break;
            case ',' :
                retval = ScanCode.DIK_COMMA;
                break;
            case '.' :
                retval = ScanCode.DIK_PERIOD;
                break;
            case '/' :
                retval = ScanCode.DIK_SLASH;
                break;
            case ' ' :
                retval = ScanCode.DIK_SPACE;
                break;
            case '@' :
                retval = ScanCode.DIK_AT;
                break;
            case ':' :
                retval = ScanCode.DIK_COLON;
                break;
            case ';' :
                retval = ScanCode.DIK_SEMICOLON;
                break;
            case '_' :
                retval = ScanCode.DIK_UNDERLINE;
                break;
            case '\'' :
                retval = ScanCode.DIK_APOSTROPHE;
                break;
            case '+' :
                retval = ScanCode.DIK_ADD;
                break;
            case '*' :
                retval = ScanCode.DIK_MULTIPLY;
                break;
            default :
                throw new IllegalArgumentException(
                        "Unsupported character: " + ch);
        }
        return retval;
    }
}
