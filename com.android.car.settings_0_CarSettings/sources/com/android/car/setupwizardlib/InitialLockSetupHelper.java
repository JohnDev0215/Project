package com.android.car.setupwizardlib;

/* JADX INFO: loaded from: classes5.dex */
public class InitialLockSetupHelper {
    public static boolean isValidLockResultCode(int flags) {
        return flags == 0;
    }

    public static byte getByteFromPatternCell(int row, int col) {
        return (byte) ((row * 3) + col + 1);
    }

    public static int getPatternCellRowFromByte(byte cell) {
        return (byte) ((cell - 1) / 3);
    }

    public static int getPatternCellColumnFromByte(byte cell) {
        return (byte) ((cell - 1) % 3);
    }

    public static byte[] charSequenceToByteArray(CharSequence chars) {
        if (chars == null) {
            return null;
        }
        byte[] byteArray = new byte[chars.length()];
        for (int i = 0; i < chars.length(); i++) {
            byteArray[i] = (byte) chars.charAt(i);
        }
        return byteArray;
    }

    public static CharSequence byteArrayToCharSequence(byte[] input) {
        if (input == null) {
            return null;
        }
        StringBuffer charSequence = new StringBuffer();
        for (byte b : input) {
            charSequence.append((char) b);
        }
        return charSequence;
    }
}
