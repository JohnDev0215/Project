package com.sun.xml.bind.api.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.commons.compress.archivers.tar.TarConstants;

/* JADX INFO: loaded from: classes3.dex */
class NameUtil {
    private static final byte ACTION_BREAK = 2;
    private static final byte ACTION_CHECK_C2 = 1;
    private static final byte ACTION_CHECK_PUNCT = 0;
    private static final byte ACTION_NOBREAK = 3;
    protected static final int DIGIT = 3;
    protected static final int LOWER_LETTER = 1;
    protected static final int OTHER = 4;
    protected static final int OTHER_LETTER = 2;
    protected static final int UPPER_LETTER = 0;
    private static final byte[] actionTable = new byte[25];

    private static boolean xor(boolean z, boolean z2) {
        return (z && z2) || !(z || z2);
    }

    protected boolean isPunct(char c) {
        return c == '-' || c == '.' || c == ':' || c == '_' || c == 183 || c == 903 || c == 1757 || c == 1758;
    }

    NameUtil() {
    }

    protected static boolean isDigit(char c) {
        return (c >= '0' && c <= '9') || Character.isDigit(c);
    }

    protected static boolean isUpper(char c) {
        return (c >= 'A' && c <= 'Z') || Character.isUpperCase(c);
    }

    protected static boolean isLower(char c) {
        return (c >= 'a' && c <= 'z') || Character.isLowerCase(c);
    }

    protected boolean isLetter(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || Character.isLetter(c);
    }

    private String toLowerCase(String str) {
        return str.toLowerCase(Locale.ENGLISH);
    }

    private String toUpperCase(char c) {
        return String.valueOf(c).toUpperCase(Locale.ENGLISH);
    }

    private String toUpperCase(String str) {
        return str.toUpperCase(Locale.ENGLISH);
    }

    public String capitalize(String str) {
        if (!isLower(str.charAt(0))) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str.length());
        sb.append(toUpperCase(str.charAt(0)));
        sb.append(toLowerCase(str.substring(1)));
        return sb.toString();
    }

    private int nextBreak(String str, int i) {
        int length = str.length();
        int iClassify = classify(str.charAt(i));
        int i2 = i + 1;
        while (i2 < length) {
            char cCharAt = str.charAt(i2);
            int iClassify2 = classify(cCharAt);
            byte b = actionTable[(iClassify * 5) + iClassify2];
            if (b != 0) {
                if (b != 1) {
                    if (b == 2) {
                        return i2;
                    }
                } else if (i2 < length - 1 && isLower(str.charAt(i2 + 1))) {
                    return i2;
                }
            } else if (isPunct(cCharAt)) {
                return i2;
            }
            i2++;
            iClassify = iClassify2;
        }
        return -1;
    }

    static {
        for (int i = 0; i < 5; i++) {
            for (int i2 = 0; i2 < 5; i2++) {
                actionTable[(i * 5) + i2] = decideAction(i, i2);
            }
        }
    }

    private static byte decideAction(int i, int i2) {
        if (i == 4 && i2 == 4) {
            return (byte) 0;
        }
        if (!xor(i == 3, i2 == 3)) {
            return (byte) 2;
        }
        if (i == 1 && i2 != 1) {
            return (byte) 2;
        }
        if (!xor(i <= 2, i2 <= 2)) {
            return (byte) 2;
        }
        if (xor(i == 2, i2 == 2)) {
            return (i == 0 && i2 == 0) ? (byte) 1 : (byte) 3;
        }
        return (byte) 2;
    }

    protected int classify(char c) {
        int type = Character.getType(c);
        if (type == 1) {
            return 0;
        }
        if (type == 2) {
            return 1;
        }
        if (type == 3 || type == 4 || type == 5) {
            return 2;
        }
        return type != 9 ? 4 : 3;
    }

    public List<String> toWordList(String str) {
        ArrayList arrayList = new ArrayList();
        int length = str.length();
        int i = 0;
        while (i < length) {
            while (i < length && isPunct(str.charAt(i))) {
                i++;
            }
            if (i >= length) {
                break;
            }
            int iNextBreak = nextBreak(str, i);
            arrayList.add(escape(capitalize(iNextBreak == -1 ? str.substring(i) : str.substring(i, iNextBreak))));
            if (iNextBreak == -1) {
                break;
            }
            i = iNextBreak;
        }
        return arrayList;
    }

    protected String toMixedCaseName(List<String> list, boolean z) {
        StringBuilder sb = new StringBuilder();
        if (!list.isEmpty()) {
            sb.append(z ? list.get(0) : toLowerCase(list.get(0)));
            for (int i = 1; i < list.size(); i++) {
                sb.append(list.get(i));
            }
        }
        return sb.toString();
    }

    protected String toMixedCaseVariableName(String[] strArr, boolean z, boolean z2) {
        if (z2) {
            for (int i = 1; i < strArr.length; i++) {
                strArr[i] = capitalize(strArr[i]);
            }
        }
        StringBuilder sb = new StringBuilder();
        if (strArr.length > 0) {
            sb.append(z ? strArr[0] : toLowerCase(strArr[0]));
            for (int i2 = 1; i2 < strArr.length; i2++) {
                sb.append(strArr[i2]);
            }
        }
        return sb.toString();
    }

    public String toConstantName(String str) {
        return toConstantName(toWordList(str));
    }

    public String toConstantName(List<String> list) {
        StringBuilder sb = new StringBuilder();
        if (!list.isEmpty()) {
            sb.append(toUpperCase(list.get(0)));
            for (int i = 1; i < list.size(); i++) {
                sb.append('_');
                sb.append(toUpperCase(list.get(i)));
            }
        }
        return sb.toString();
    }

    public static void escape(StringBuilder sb, String str, int i) {
        int length = str.length();
        while (i < length) {
            char cCharAt = str.charAt(i);
            if (Character.isJavaIdentifierPart(cCharAt)) {
                sb.append(cCharAt);
            } else {
                sb.append('_');
                if (cCharAt <= 15) {
                    sb.append("000");
                } else if (cCharAt <= 255) {
                    sb.append(TarConstants.VERSION_POSIX);
                } else if (cCharAt <= 4095) {
                    sb.append('0');
                }
                sb.append(Integer.toString(cCharAt, 16));
            }
            i++;
        }
    }

    private static String escape(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isJavaIdentifierPart(str.charAt(i))) {
                StringBuilder sb = new StringBuilder(str.substring(0, i));
                escape(sb, str, i);
                return sb.toString();
            }
        }
        return str;
    }
}
