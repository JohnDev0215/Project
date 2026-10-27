package org.codehaus.stax2.ri;

import javax.xml.stream.XMLStreamConstants;

/* JADX INFO: loaded from: classes7.dex */
public final class Stax2Util implements XMLStreamConstants {

    public static final class ByteAggregator {
        private static final byte[] NO_BYTES = new byte[0];
    }

    private static final boolean _isSpace(char c) {
        return c <= ' ';
    }

    private Stax2Util() {
    }

    public static String trimSpaces(String str) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            if (!_isSpace(str.charAt(i))) {
                int i2 = length - 1;
                if (!_isSpace(str.charAt(i2))) {
                    return i == 0 ? str : str.substring(i);
                }
                do {
                    i2--;
                    if (i2 <= i) {
                        break;
                    }
                } while (_isSpace(str.charAt(i2)));
                return str.substring(i, i2 + 1);
            }
            i++;
        }
        return null;
    }
}
