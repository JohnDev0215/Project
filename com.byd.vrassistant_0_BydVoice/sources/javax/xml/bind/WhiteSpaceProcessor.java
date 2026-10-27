package javax.xml.bind;

/* JADX INFO: loaded from: classes3.dex */
abstract class WhiteSpaceProcessor {
    public static final boolean isWhiteSpace(char c) {
        if (c > ' ') {
            return false;
        }
        return c == '\t' || c == '\n' || c == '\r' || c == ' ';
    }

    protected static final boolean isWhiteSpaceExceptSpace(char c) {
        if (c >= ' ') {
            return false;
        }
        return c == '\t' || c == '\n' || c == '\r';
    }

    WhiteSpaceProcessor() {
    }

    public static String replace(String str) {
        return replace((CharSequence) str).toString();
    }

    public static CharSequence replace(CharSequence charSequence) {
        int length = charSequence.length() - 1;
        while (length >= 0 && !isWhiteSpaceExceptSpace(charSequence.charAt(length))) {
            length--;
        }
        if (length < 0) {
            return charSequence;
        }
        StringBuilder sb = new StringBuilder(charSequence);
        sb.setCharAt(length, ' ');
        for (int i = length - 1; i >= 0; i--) {
            if (isWhiteSpaceExceptSpace(sb.charAt(i))) {
                sb.setCharAt(i, ' ');
            }
        }
        return new String(sb);
    }

    public static CharSequence trim(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        while (i < length && isWhiteSpace(charSequence.charAt(i))) {
            i++;
        }
        int i2 = length - 1;
        int i3 = i2;
        while (i3 > i && isWhiteSpace(charSequence.charAt(i3))) {
            i3--;
        }
        return (i == 0 && i3 == i2) ? charSequence : charSequence.subSequence(i, i3 + 1);
    }

    public static String collapse(String str) {
        return collapse((CharSequence) str).toString();
    }

    public static CharSequence collapse(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        while (i < length && !isWhiteSpace(charSequence.charAt(i))) {
            i++;
        }
        if (i == length) {
            return charSequence;
        }
        StringBuilder sb = new StringBuilder(length);
        if (i != 0) {
            for (int i2 = 0; i2 < i; i2++) {
                sb.append(charSequence.charAt(i2));
            }
            sb.append(' ');
        }
        boolean z = true;
        for (int i3 = i + 1; i3 < length; i3++) {
            char cCharAt = charSequence.charAt(i3);
            boolean zIsWhiteSpace = isWhiteSpace(cCharAt);
            if (!z || !zIsWhiteSpace) {
                if (zIsWhiteSpace) {
                    sb.append(' ');
                } else {
                    sb.append(cCharAt);
                }
                z = zIsWhiteSpace;
            }
        }
        int length2 = sb.length();
        if (length2 > 0) {
            int i4 = length2 - 1;
            if (sb.charAt(i4) == ' ') {
                sb.setLength(i4);
            }
        }
        return sb;
    }

    public static final boolean isWhiteSpace(CharSequence charSequence) {
        for (int length = charSequence.length() - 1; length >= 0; length--) {
            if (!isWhiteSpace(charSequence.charAt(length))) {
                return false;
            }
        }
        return true;
    }
}
