package com.google.zxing.datamatrix.encoder;

/* JADX INFO: loaded from: classes2.dex */
final class TextEncoder extends C40Encoder {
    TextEncoder() {
    }

    @Override // com.google.zxing.datamatrix.encoder.C40Encoder
    int encodeChar(char c2, StringBuilder sb) {
        int i;
        int i2;
        char c3;
        if (c2 == ' ') {
            c3 = 3;
        } else {
            if (c2 >= '0' && c2 <= '9') {
                i2 = (c2 - '0') + 4;
            } else {
                if (c2 < 'a' || c2 > 'z') {
                    if (c2 < 0 || c2 > 31) {
                        char c4 = '!';
                        if (c2 < '!' || c2 > '/') {
                            if (c2 >= ':' && c2 <= '@') {
                                sb.append((char) 1);
                                i = (c2 - ':') + 15;
                            } else if (c2 < '[' || c2 > '_') {
                                c4 = '`';
                                if (c2 == '`') {
                                    sb.append((char) 2);
                                } else if (c2 >= 'A' && c2 <= 'Z') {
                                    sb.append((char) 2);
                                    i = (c2 - 'A') + 1;
                                } else {
                                    if (c2 < '{' || c2 > 127) {
                                        if (c2 >= 128) {
                                            sb.append("\u0001\u001e");
                                            return encodeChar((char) (c2 - 128), sb) + 2;
                                        }
                                        HighLevelEncoder.illegalCharacter(c2);
                                        return -1;
                                    }
                                    sb.append((char) 2);
                                    i = (c2 - '{') + 27;
                                }
                            } else {
                                sb.append((char) 1);
                                i = (c2 - '[') + 22;
                            }
                            c2 = (char) i;
                        } else {
                            sb.append((char) 1);
                        }
                        i = c2 - c4;
                        c2 = (char) i;
                    } else {
                        sb.append((char) 0);
                    }
                    sb.append(c2);
                    return 2;
                }
                i2 = (c2 - 'a') + 14;
            }
            c3 = (char) i2;
        }
        sb.append(c3);
        return 1;
    }

    @Override // com.google.zxing.datamatrix.encoder.C40Encoder, com.google.zxing.datamatrix.encoder.Encoder
    public int getEncodingMode() {
        return 2;
    }
}
