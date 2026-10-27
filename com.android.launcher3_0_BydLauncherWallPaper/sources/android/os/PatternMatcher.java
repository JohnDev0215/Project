package android.os;

import android.os.Parcelable;
import android.util.proto.ProtoOutputStream;

/* JADX INFO: loaded from: classes2.dex */
public class PatternMatcher implements Parcelable {
    private static final int MAX_PATTERN_STORAGE = 2048;
    private static final int NO_MATCH = -1;
    private static final int PARSED_MODIFIER_ONE_OR_MORE = -8;
    private static final int PARSED_MODIFIER_RANGE_START = -5;
    private static final int PARSED_MODIFIER_RANGE_STOP = -6;
    private static final int PARSED_MODIFIER_ZERO_OR_MORE = -7;
    private static final int PARSED_TOKEN_CHAR_ANY = -4;
    private static final int PARSED_TOKEN_CHAR_SET_INVERSE_START = -2;
    private static final int PARSED_TOKEN_CHAR_SET_START = -1;
    private static final int PARSED_TOKEN_CHAR_SET_STOP = -3;
    public static final int PATTERN_ADVANCED_GLOB = 3;
    public static final int PATTERN_LITERAL = 0;
    public static final int PATTERN_PREFIX = 1;
    public static final int PATTERN_SIMPLE_GLOB = 2;
    private static final String TAG = "PatternMatcher";
    private static final int TOKEN_TYPE_ANY = 1;
    private static final int TOKEN_TYPE_INVERSE_SET = 3;
    private static final int TOKEN_TYPE_LITERAL = 0;
    private static final int TOKEN_TYPE_SET = 2;
    private final int[] mParsedPattern;
    private final String mPattern;
    private final int mType;
    private static final int[] sParsedPatternScratch = new int[2048];
    public static final Parcelable.Creator<PatternMatcher> CREATOR = new Parcelable.Creator<PatternMatcher>() { // from class: android.os.PatternMatcher.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PatternMatcher createFromParcel(Parcel parcel) {
            return new PatternMatcher(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PatternMatcher[] newArray(int i) {
            return new PatternMatcher[i];
        }
    };

    private static boolean isParsedModifier(int i) {
        return i == -8 || i == -7 || i == -6 || i == -5;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PatternMatcher(String str, int i) {
        this.mPattern = str;
        this.mType = i;
        if (i == 3) {
            this.mParsedPattern = parseAndVerifyAdvancedPattern(str);
        } else {
            this.mParsedPattern = null;
        }
    }

    public final String getPath() {
        return this.mPattern;
    }

    public final int getType() {
        return this.mType;
    }

    public boolean match(String str) {
        return matchPattern(str, this.mPattern, this.mParsedPattern, this.mType);
    }

    public String toString() {
        int i = this.mType;
        return "PatternMatcher{" + (i != 0 ? i != 1 ? i != 2 ? i != 3 ? "? " : "ADVANCED: " : "GLOB: " : "PREFIX: " : "LITERAL: ") + this.mPattern + "}";
    }

    public void dumpDebug(ProtoOutputStream protoOutputStream, long j) {
        long jStart = protoOutputStream.start(j);
        protoOutputStream.write(1138166333441L, this.mPattern);
        protoOutputStream.write(1159641169922L, this.mType);
        protoOutputStream.end(jStart);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mPattern);
        parcel.writeInt(this.mType);
        parcel.writeIntArray(this.mParsedPattern);
    }

    public PatternMatcher(Parcel parcel) {
        this.mPattern = parcel.readString();
        this.mType = parcel.readInt();
        this.mParsedPattern = parcel.createIntArray();
    }

    static boolean matchPattern(String str, String str2, int[] iArr, int i) {
        if (str == null) {
            return false;
        }
        if (i == 0) {
            return str2.equals(str);
        }
        if (i == 1) {
            return str.startsWith(str2);
        }
        if (i == 2) {
            return matchGlobPattern(str2, str);
        }
        if (i == 3) {
            return matchAdvancedPattern(iArr, str);
        }
        return false;
    }

    static boolean matchGlobPattern(String str, String str2) {
        int length = str.length();
        if (length <= 0) {
            return str2.length() <= 0;
        }
        int length2 = str2.length();
        char cCharAt = str.charAt(0);
        int i = 0;
        int i2 = 0;
        while (i < length && i2 < length2) {
            i++;
            char cCharAt2 = i < length ? str.charAt(i) : (char) 0;
            boolean z = cCharAt == '\\';
            if (z) {
                i++;
                char c = cCharAt2;
                cCharAt2 = i < length ? str.charAt(i) : (char) 0;
                cCharAt = c;
            }
            if (cCharAt2 == '*') {
                if (z || cCharAt != '.') {
                    while (str2.charAt(i2) == cCharAt && (i2 = i2 + 1) < length2) {
                    }
                    i++;
                    cCharAt = i < length ? str.charAt(i) : (char) 0;
                } else {
                    if (i >= length - 1) {
                        return true;
                    }
                    int i3 = i + 1;
                    char cCharAt3 = str.charAt(i3);
                    if (cCharAt3 == '\\') {
                        i3++;
                        cCharAt3 = i3 < length ? str.charAt(i3) : (char) 0;
                    }
                    while (str2.charAt(i2) != cCharAt3 && (i2 = i2 + 1) < length2) {
                    }
                    if (i2 == length2) {
                        return false;
                    }
                    i = i3 + 1;
                    cCharAt = i < length ? str.charAt(i) : (char) 0;
                    i2++;
                }
            } else {
                if (cCharAt != '.' && str2.charAt(i2) != cCharAt) {
                    return false;
                }
                i2++;
                cCharAt = cCharAt2;
            }
        }
        if (i < length || i2 < length2) {
            return i == length + (-2) && str.charAt(i) == '.' && str.charAt(i + 1) == '*';
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:77:0x012c A[Catch: all -> 0x01c0, TryCatch #0 {, blocks: (B:4:0x0003, B:8:0x0014, B:18:0x002e, B:69:0x0101, B:100:0x01a3, B:70:0x010b, B:72:0x010f, B:74:0x0119, B:76:0x0121, B:77:0x012c, B:79:0x013a, B:81:0x0140, B:83:0x014c, B:90:0x0174, B:91:0x0181, B:92:0x0188, B:84:0x0152, B:88:0x0167, B:94:0x018a, B:95:0x0191, B:96:0x0192, B:97:0x0199, B:99:0x019c, B:22:0x0037, B:25:0x0041, B:26:0x004c, B:27:0x0053, B:28:0x0054, B:30:0x0058, B:31:0x005d, B:32:0x0064, B:35:0x0068, B:37:0x0072, B:39:0x0080, B:38:0x007a, B:41:0x0086, B:44:0x0096, B:46:0x00a2, B:47:0x00ac, B:48:0x00b3, B:50:0x00b6, B:54:0x00c4, B:56:0x00d0, B:57:0x00d6, B:58:0x00dd, B:61:0x00e2, B:63:0x00ee, B:64:0x00f4, B:65:0x00fb, B:101:0x01a6, B:102:0x01ad, B:104:0x01b0, B:107:0x01b8, B:108:0x01bf), top: B:112:0x0003, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static synchronized int[] parseAndVerifyAdvancedPattern(java.lang.String r14) {
        /*
            Method dump skipped, instruction units count: 462
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.os.PatternMatcher.parseAndVerifyAdvancedPattern(java.lang.String):int[]");
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static boolean matchAdvancedPattern(int[] r18, java.lang.String r19) {
        /*
            r9 = r18
            int r10 = r9.length
            int r11 = r19.length()
            r12 = 0
            r0 = r12
            r1 = r0
            r2 = r1
            r13 = r2
        Lc:
            r3 = 1
            if (r0 >= r10) goto L90
            r4 = r9[r0]
            r5 = -4
            r6 = 2
            r14 = -1
            if (r4 == r5) goto L3b
            r1 = -2
            if (r4 == r1) goto L22
            if (r4 == r14) goto L22
            int r1 = r0 + 1
            r15 = r0
            r16 = r2
            r4 = r12
            goto L42
        L22:
            if (r4 != r14) goto L26
            r1 = r6
            goto L27
        L26:
            r1 = 3
        L27:
            int r2 = r0 + 1
        L29:
            int r0 = r0 + r3
            if (r0 >= r10) goto L32
            r4 = r9[r0]
            r5 = -3
            if (r4 == r5) goto L32
            goto L29
        L32:
            int r4 = r0 + (-1)
            int r0 = r0 + 1
            r15 = r2
            r16 = r4
            r4 = r1
            goto L41
        L3b:
            int r0 = r0 + 1
            r15 = r1
            r16 = r2
            r4 = r3
        L41:
            r1 = r0
        L42:
            r0 = 2147483647(0x7fffffff, float:NaN)
            if (r1 < r10) goto L4c
        L47:
            r17 = r1
            r5 = r3
            r6 = r5
            goto L72
        L4c:
            r2 = r9[r1]
            r5 = -8
            if (r2 == r5) goto L6c
            r5 = -7
            if (r2 == r5) goto L65
            r0 = -5
            if (r2 == r0) goto L58
            goto L47
        L58:
            int r1 = r1 + 1
            r0 = r9[r1]
            int r1 = r1 + r3
            r2 = r9[r1]
            int r1 = r1 + r6
            r5 = r0
            r17 = r1
            r6 = r2
            goto L72
        L65:
            int r1 = r1 + 1
            r6 = r0
            r17 = r1
            r5 = r12
            goto L72
        L6c:
            int r1 = r1 + 1
            r6 = r0
            r17 = r1
            r5 = r3
        L72:
            if (r5 <= r6) goto L75
            return r12
        L75:
            r0 = r19
            r1 = r13
            r2 = r11
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r18
            r7 = r15
            r8 = r16
            int r0 = matchChars(r0, r1, r2, r3, r4, r5, r6, r7, r8)
            if (r0 != r14) goto L88
            return r12
        L88:
            int r13 = r13 + r0
            r1 = r15
            r2 = r16
            r0 = r17
            goto Lc
        L90:
            if (r0 < r10) goto L95
            if (r13 < r11) goto L95
            r12 = r3
        L95:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: android.os.PatternMatcher.matchAdvancedPattern(int[], java.lang.String):boolean");
    }

    private static int matchChars(String str, int i, int i2, int i3, int i4, int i5, int[] iArr, int i6, int i7) {
        int i8 = 0;
        while (i8 < i5 && matchChar(str, i + i8, i2, i3, iArr, i6, i7)) {
            i8++;
        }
        if (i8 < i4) {
            return -1;
        }
        return i8;
    }

    private static boolean matchChar(String str, int i, int i2, int i3, int[] iArr, int i4, int i5) {
        if (i >= i2) {
            return false;
        }
        if (i3 == 0) {
            return str.charAt(i) == iArr[i4];
        }
        if (i3 == 1) {
            return true;
        }
        if (i3 == 2) {
            while (i4 < i5) {
                char cCharAt = str.charAt(i);
                if (cCharAt >= iArr[i4] && cCharAt <= iArr[i4 + 1]) {
                    return true;
                }
                i4 += 2;
            }
            return false;
        }
        if (i3 != 3) {
            return false;
        }
        while (i4 < i5) {
            char cCharAt2 = str.charAt(i);
            if (cCharAt2 >= iArr[i4] && cCharAt2 <= iArr[i4 + 1]) {
                return false;
            }
            i4 += 2;
        }
        return true;
    }
}
