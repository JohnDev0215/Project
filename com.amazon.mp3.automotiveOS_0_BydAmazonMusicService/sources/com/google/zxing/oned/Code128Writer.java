package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes7.dex */
public final class Code128Writer extends OneDimensionalCodeWriter {

    private enum CType {
        UNCODABLE,
        ONE_DIGIT,
        TWO_DIGITS,
        FNC_1
    }

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    protected Collection<BarcodeFormat> getSupportedWriteFormats() {
        return Collections.singleton(BarcodeFormat.CODE_128);
    }

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    public boolean[] encode(String str) {
        return encode(str, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x001e  */
    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected boolean[] encode(java.lang.String r4, java.util.Map<com.google.zxing.EncodeHintType, ?> r5) {
        /*
            r3 = this;
            int r0 = check(r4, r5)
            if (r5 == 0) goto L1e
            com.google.zxing.EncodeHintType r1 = com.google.zxing.EncodeHintType.CODE128_COMPACT
            boolean r2 = r5.containsKey(r1)
            if (r2 == 0) goto L1e
            java.lang.Object r5 = r5.get(r1)
            java.lang.String r5 = r5.toString()
            boolean r5 = java.lang.Boolean.parseBoolean(r5)
            if (r5 == 0) goto L1e
            r5 = 1
            goto L1f
        L1e:
            r5 = 0
        L1f:
            if (r5 == 0) goto L2c
            com.google.zxing.oned.Code128Writer$MinimalEncoder r5 = new com.google.zxing.oned.Code128Writer$MinimalEncoder
            r0 = 0
            r5.<init>(r0)
            boolean[] r4 = com.google.zxing.oned.Code128Writer.MinimalEncoder.access$100(r5, r4)
            goto L30
        L2c:
            boolean[] r4 = encodeFast(r4, r0)
        L30:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code128Writer.encode(java.lang.String, java.util.Map):boolean[]");
    }

    private static int check(String str, Map<EncodeHintType, ?> map) {
        String string;
        int length = str.length();
        if (length < 1 || length > 80) {
            throw new IllegalArgumentException("Contents length should be between 1 and 80 characters, but got " + length);
        }
        int i = -1;
        if (map != null) {
            EncodeHintType encodeHintType = EncodeHintType.FORCE_CODE_SET;
            if (map.containsKey(encodeHintType)) {
                string = map.get(encodeHintType).toString();
                string.hashCode();
                switch (string) {
                    case "A":
                        i = 101;
                        break;
                    case "B":
                        i = 100;
                        break;
                    case "C":
                        i = 99;
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported code set hint: " + string);
                }
            }
        }
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = str.charAt(i2);
            switch (cCharAt) {
                case 241:
                case 242:
                case 243:
                case 244:
                    break;
                default:
                    if (cCharAt > 127) {
                        throw new IllegalArgumentException("Bad character in input: ASCII value=" + ((int) cCharAt));
                    }
                    break;
            }
            switch (i) {
                case 99:
                    if (cCharAt < '0' || ((cCharAt > '9' && cCharAt <= 127) || cCharAt == 242 || cCharAt == 243 || cCharAt == 244)) {
                        throw new IllegalArgumentException("Bad character in input for forced code set C: ASCII value=" + ((int) cCharAt));
                    }
                    break;
                    break;
                case 100:
                    if (cCharAt <= ' ') {
                        throw new IllegalArgumentException("Bad character in input for forced code set B: ASCII value=" + ((int) cCharAt));
                    }
                    break;
                    break;
                case 101:
                    if (cCharAt > '_' && cCharAt <= 127) {
                        throw new IllegalArgumentException("Bad character in input for forced code set A: ASCII value=" + ((int) cCharAt));
                    }
                    break;
                    break;
            }
        }
        return i;
    }

    private static boolean[] encodeFast(String str, int i) {
        int length = str.length();
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 1;
        while (i2 < length) {
            int iChooseCode = i == -1 ? chooseCode(str, i2, i4) : i;
            int iCharAt = 100;
            if (iChooseCode == i4) {
                switch (str.charAt(i2)) {
                    case 241:
                        iCharAt = 102;
                        break;
                    case 242:
                        iCharAt = 97;
                        break;
                    case 243:
                        iCharAt = 96;
                        break;
                    case 244:
                        if (i4 == 101) {
                            iCharAt = 101;
                        }
                        break;
                    default:
                        if (i4 == 100) {
                            iCharAt = str.charAt(i2) - ' ';
                        } else if (i4 == 101) {
                            iCharAt = str.charAt(i2) - ' ';
                            if (iCharAt < 0) {
                                iCharAt += 96;
                            }
                        } else {
                            int i6 = i2 + 1;
                            if (i6 == length) {
                                throw new IllegalArgumentException("Bad number of characters for digit only encoding.");
                            }
                            iCharAt = Integer.parseInt(str.substring(i2, i2 + 2));
                            i2 = i6;
                        }
                        break;
                }
                i2++;
            } else {
                iCharAt = i4 == 0 ? iChooseCode != 100 ? iChooseCode != 101 ? 105 : 103 : 104 : iChooseCode;
                i4 = iChooseCode;
            }
            arrayList.add(Code128Reader.CODE_PATTERNS[iCharAt]);
            i3 += iCharAt * i5;
            if (i2 != 0) {
                i5++;
            }
        }
        return produceResult(arrayList, i3);
    }

    static boolean[] produceResult(Collection<int[]> collection, int i) {
        int[][] iArr = Code128Reader.CODE_PATTERNS;
        collection.add(iArr[i % 103]);
        collection.add(iArr[106]);
        int iAppendPattern = 0;
        int i2 = 0;
        for (int[] iArr2 : collection) {
            for (int i3 : iArr2) {
                i2 += i3;
            }
        }
        boolean[] zArr = new boolean[i2];
        Iterator<int[]> it = collection.iterator();
        while (it.hasNext()) {
            iAppendPattern += OneDimensionalCodeWriter.appendPattern(zArr, iAppendPattern, it.next(), true);
        }
        return zArr;
    }

    private static CType findCType(CharSequence charSequence, int i) {
        int length = charSequence.length();
        if (i >= length) {
            return CType.UNCODABLE;
        }
        char cCharAt = charSequence.charAt(i);
        if (cCharAt == 241) {
            return CType.FNC_1;
        }
        if (cCharAt < '0' || cCharAt > '9') {
            return CType.UNCODABLE;
        }
        int i2 = i + 1;
        if (i2 >= length) {
            return CType.ONE_DIGIT;
        }
        char cCharAt2 = charSequence.charAt(i2);
        if (cCharAt2 < '0' || cCharAt2 > '9') {
            return CType.ONE_DIGIT;
        }
        return CType.TWO_DIGITS;
    }

    private static int chooseCode(CharSequence charSequence, int i, int i2) {
        CType cTypeFindCType;
        CType cTypeFindCType2;
        char cCharAt;
        CType cTypeFindCType3 = findCType(charSequence, i);
        CType cType = CType.ONE_DIGIT;
        if (cTypeFindCType3 == cType) {
            return i2 == 101 ? 101 : 100;
        }
        CType cType2 = CType.UNCODABLE;
        if (cTypeFindCType3 == cType2) {
            return (i >= charSequence.length() || ((cCharAt = charSequence.charAt(i)) >= ' ' && (i2 != 101 || (cCharAt >= '`' && (cCharAt < 241 || cCharAt > 244))))) ? 100 : 101;
        }
        if (i2 == 101 && cTypeFindCType3 == CType.FNC_1) {
            return 101;
        }
        if (i2 == 99) {
            return 99;
        }
        if (i2 == 100) {
            CType cType3 = CType.FNC_1;
            if (cTypeFindCType3 == cType3 || (cTypeFindCType = findCType(charSequence, i + 2)) == cType2 || cTypeFindCType == cType) {
                return 100;
            }
            if (cTypeFindCType == cType3) {
                return findCType(charSequence, i + 3) == CType.TWO_DIGITS ? 99 : 100;
            }
            int i3 = i + 4;
            while (true) {
                cTypeFindCType2 = findCType(charSequence, i3);
                if (cTypeFindCType2 != CType.TWO_DIGITS) {
                    break;
                }
                i3 += 2;
            }
            return cTypeFindCType2 == CType.ONE_DIGIT ? 100 : 99;
        }
        if (cTypeFindCType3 == CType.FNC_1) {
            cTypeFindCType3 = findCType(charSequence, i + 1);
        }
        return cTypeFindCType3 == CType.TWO_DIGITS ? 99 : 100;
    }

    private static final class MinimalEncoder {
        private int[][] memoizedCost;
        private Latch[][] minPath;

        private enum Charset {
            A,
            B,
            C,
            NONE
        }

        private enum Latch {
            A,
            B,
            C,
            SHIFT,
            NONE
        }

        private static boolean isDigit(char c) {
            return c >= '0' && c <= '9';
        }

        private MinimalEncoder() {
        }

        /* synthetic */ MinimalEncoder(AnonymousClass1 anonymousClass1) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:30:0x009a  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x00b9  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean[] encode(java.lang.String r17) {
            /*
                Method dump skipped, instruction units count: 276
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code128Writer.MinimalEncoder.encode(java.lang.String):boolean[]");
        }

        private static void addPattern(Collection<int[]> collection, int i, int[] iArr, int[] iArr2, int i2) {
            collection.add(Code128Reader.CODE_PATTERNS[i]);
            if (i2 != 0) {
                iArr2[0] = iArr2[0] + 1;
            }
            iArr[0] = iArr[0] + (i * iArr2[0]);
        }

        private boolean canEncode(CharSequence charSequence, Charset charset, int i) {
            int i2;
            char cCharAt = charSequence.charAt(i);
            int i3 = AnonymousClass1.$SwitchMap$com$google$zxing$oned$Code128Writer$MinimalEncoder$Charset[charset.ordinal()];
            if (i3 == 1) {
                return cCharAt == 241 || cCharAt == 242 || cCharAt == 243 || cCharAt == 244 || " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001fÿ".indexOf(cCharAt) >= 0;
            }
            if (i3 == 2) {
                return cCharAt == 241 || cCharAt == 242 || cCharAt == 243 || cCharAt == 244 || " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u007fÿ".indexOf(cCharAt) >= 0;
            }
            if (i3 != 3) {
                return false;
            }
            return cCharAt == 241 || ((i2 = i + 1) < charSequence.length() && isDigit(cCharAt) && isDigit(charSequence.charAt(i2)));
        }

        private int encode(CharSequence charSequence, Charset charset, int i) {
            int iEncode;
            int iEncode2;
            int i2 = this.memoizedCost[charset.ordinal()][i];
            if (i2 > 0) {
                return i2;
            }
            Latch latch = Latch.NONE;
            int i3 = i + 1;
            boolean z = i3 >= charSequence.length();
            Charset[] charsetArr = {Charset.A, Charset.B};
            int i4 = Integer.MAX_VALUE;
            for (int i5 = 0; i5 <= 1; i5++) {
                if (canEncode(charSequence, charsetArr[i5], i)) {
                    Latch latchValueOf = Latch.NONE;
                    Charset charset2 = charsetArr[i5];
                    if (charset != charset2) {
                        latchValueOf = Latch.valueOf(charset2.toString());
                        iEncode2 = 2;
                    } else {
                        iEncode2 = 1;
                    }
                    if (!z) {
                        iEncode2 += encode(charSequence, charsetArr[i5], i3);
                    }
                    if (iEncode2 < i4) {
                        latch = latchValueOf;
                        i4 = iEncode2;
                    }
                    if (charset == charsetArr[(i5 + 1) % 2]) {
                        Latch latch2 = Latch.SHIFT;
                        int iEncode3 = !z ? encode(charSequence, charset, i3) + 2 : 2;
                        if (iEncode3 < i4) {
                            latch = latch2;
                            i4 = iEncode3;
                        }
                    }
                }
            }
            Charset charset3 = Charset.C;
            if (canEncode(charSequence, charset3, i)) {
                Latch latch3 = Latch.NONE;
                if (charset != charset3) {
                    latch3 = Latch.C;
                    iEncode = 2;
                } else {
                    iEncode = 1;
                }
                int i6 = (charSequence.charAt(i) != 241 ? 2 : 1) + i;
                if (i6 < charSequence.length()) {
                    iEncode += encode(charSequence, charset3, i6);
                }
                if (iEncode < i4) {
                    latch = latch3;
                    i4 = iEncode;
                }
            }
            if (i4 == Integer.MAX_VALUE) {
                throw new IllegalArgumentException("Bad character in input: ASCII value=" + ((int) charSequence.charAt(i)));
            }
            this.memoizedCost[charset.ordinal()][i] = i4;
            this.minPath[charset.ordinal()][i] = latch;
            return i4;
        }
    }

    /* JADX INFO: renamed from: com.google.zxing.oned.Code128Writer$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$oned$Code128Writer$MinimalEncoder$Charset;
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$oned$Code128Writer$MinimalEncoder$Latch;

        static {
            int[] iArr = new int[MinimalEncoder.Charset.values().length];
            $SwitchMap$com$google$zxing$oned$Code128Writer$MinimalEncoder$Charset = iArr;
            try {
                iArr[MinimalEncoder.Charset.A.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$zxing$oned$Code128Writer$MinimalEncoder$Charset[MinimalEncoder.Charset.B.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$zxing$oned$Code128Writer$MinimalEncoder$Charset[MinimalEncoder.Charset.C.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[MinimalEncoder.Latch.values().length];
            $SwitchMap$com$google$zxing$oned$Code128Writer$MinimalEncoder$Latch = iArr2;
            try {
                iArr2[MinimalEncoder.Latch.A.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$zxing$oned$Code128Writer$MinimalEncoder$Latch[MinimalEncoder.Latch.B.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$zxing$oned$Code128Writer$MinimalEncoder$Latch[MinimalEncoder.Latch.C.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$zxing$oned$Code128Writer$MinimalEncoder$Latch[MinimalEncoder.Latch.SHIFT.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }
}
