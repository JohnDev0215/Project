package android.os;

import android.os.Parcelable;
import android.util.proto.ProtoOutputStream;
import java.util.Arrays;

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

    static synchronized int[] parseAndVerifyAdvancedPattern(String str) {
        int i;
        int i2;
        boolean z;
        int i3;
        int i4;
        int i5;
        int i6;
        int length = str.length();
        int i7 = 0;
        boolean z2 = false;
        i = 0;
        boolean z3 = false;
        boolean z4 = false;
        while (i7 < length) {
            if (i > 2045) {
                throw new IllegalArgumentException("Pattern is too large!");
            }
            char cCharAt = str.charAt(i7);
            if (cCharAt == '*') {
                if (!z2) {
                    if (i == 0 || isParsedModifier(sParsedPatternScratch[i - 1])) {
                        throw new IllegalArgumentException("Modifier must follow a token.");
                    }
                    i2 = i + 1;
                    sParsedPatternScratch[i] = -7;
                    z = false;
                    i = i2;
                }
                z = false;
            } else if (cCharAt != '+') {
                if (cCharAt != '.') {
                    if (cCharAt != '{') {
                        if (cCharAt != '}') {
                            switch (cCharAt) {
                                case '[':
                                    if (!z2) {
                                        int i8 = i7 + 1;
                                        if (str.charAt(i8) == '^') {
                                            i6 = i + 1;
                                            sParsedPatternScratch[i] = -2;
                                            i7 = i8;
                                        } else {
                                            i6 = i + 1;
                                            sParsedPatternScratch[i] = -1;
                                        }
                                        i = i6;
                                        i7++;
                                        z2 = true;
                                    } else {
                                        z = true;
                                    }
                                    break;
                                case '\\':
                                    i7++;
                                    if (i7 >= length) {
                                        throw new IllegalArgumentException("Escape found at end of pattern!");
                                    }
                                    cCharAt = str.charAt(i7);
                                    z = true;
                                    break;
                                    break;
                                case ']':
                                    if (!z2) {
                                        z = true;
                                    } else {
                                        int i9 = sParsedPatternScratch[i - 1];
                                        if (i9 == -1 || i9 == -2) {
                                            throw new IllegalArgumentException("You must define characters in a set.");
                                        }
                                        sParsedPatternScratch[i] = -3;
                                        z2 = false;
                                        z = false;
                                        i++;
                                        z4 = false;
                                    }
                                    break;
                                default:
                                    z = true;
                                    break;
                            }
                        } else if (z3) {
                            sParsedPatternScratch[i] = -6;
                            z3 = false;
                            i++;
                            z = false;
                        }
                    } else if (!z2) {
                        if (i == 0 || isParsedModifier(sParsedPatternScratch[i - 1])) {
                            throw new IllegalArgumentException("Modifier must follow a token.");
                        }
                        sParsedPatternScratch[i] = -5;
                        i7++;
                        z3 = true;
                        i++;
                    }
                } else if (!z2) {
                    i2 = i + 1;
                    sParsedPatternScratch[i] = -4;
                    z = false;
                    i = i2;
                }
                z = false;
            } else {
                if (!z2) {
                    if (i == 0 || isParsedModifier(sParsedPatternScratch[i - 1])) {
                        throw new IllegalArgumentException("Modifier must follow a token.");
                    }
                    i2 = i + 1;
                    sParsedPatternScratch[i] = -8;
                    z = false;
                    i = i2;
                }
                z = false;
            }
            if (z2) {
                if (z4) {
                    i3 = i + 1;
                    sParsedPatternScratch[i] = cCharAt;
                    z4 = false;
                } else {
                    int i10 = i7 + 2;
                    if (i10 < length) {
                        int i11 = i7 + 1;
                        if (str.charAt(i11) == '-' && str.charAt(i10) != ']') {
                            sParsedPatternScratch[i] = cCharAt;
                            i++;
                            i7 = i11;
                            z4 = true;
                        }
                    }
                    int i12 = i + 1;
                    sParsedPatternScratch[i] = cCharAt;
                    i3 = i12 + 1;
                    sParsedPatternScratch[i12] = cCharAt;
                }
                i = i3;
            } else if (z3) {
                int iIndexOf = str.indexOf(125, i7);
                if (iIndexOf < 0) {
                    throw new IllegalArgumentException("Range not ended with '}'");
                }
                String strSubstring = str.substring(i7, iIndexOf);
                int iIndexOf2 = strSubstring.indexOf(44);
                if (iIndexOf2 < 0) {
                    try {
                        i4 = Integer.parseInt(strSubstring);
                        i5 = i4;
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Range number format incorrect", e);
                    }
                } else {
                    int i13 = Integer.parseInt(strSubstring.substring(0, iIndexOf2));
                    i5 = iIndexOf2 == strSubstring.length() - 1 ? Integer.MAX_VALUE : Integer.parseInt(strSubstring.substring(iIndexOf2 + 1));
                    i4 = i13;
                }
                if (i4 > i5) {
                    throw new IllegalArgumentException("Range quantifier minimum is greater than maximum");
                }
                int i14 = i + 1;
                sParsedPatternScratch[i] = i4;
                i = i14 + 1;
                sParsedPatternScratch[i14] = i5;
                i7 = iIndexOf;
            } else if (z) {
                sParsedPatternScratch[i] = cCharAt;
                i++;
            }
            i7++;
        }
        if (z2) {
            throw new IllegalArgumentException("Set was not terminated!");
        }
        return Arrays.copyOf(sParsedPatternScratch, i);
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
