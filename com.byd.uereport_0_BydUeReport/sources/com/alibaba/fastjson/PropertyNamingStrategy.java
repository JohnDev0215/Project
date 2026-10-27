package com.alibaba.fastjson;

/* JADX INFO: loaded from: classes.dex */
public enum PropertyNamingStrategy {
    CamelCase,
    PascalCase,
    SnakeCase,
    KebabCase,
    NoChange;

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f2596a;

        static {
            int[] iArr = new int[PropertyNamingStrategy.values().length];
            f2596a = iArr;
            try {
                iArr[PropertyNamingStrategy.SnakeCase.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2596a[PropertyNamingStrategy.KebabCase.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2596a[PropertyNamingStrategy.PascalCase.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2596a[PropertyNamingStrategy.CamelCase.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2596a[PropertyNamingStrategy.NoChange.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public String translate(String str) {
        char cCharAt;
        int i8 = a.f2596a[ordinal()];
        int i9 = 0;
        if (i8 == 1) {
            StringBuilder sb = new StringBuilder();
            while (i9 < str.length()) {
                char cCharAt2 = str.charAt(i9);
                if (cCharAt2 < 'A' || cCharAt2 > 'Z') {
                    sb.append(cCharAt2);
                } else {
                    char c8 = (char) (cCharAt2 + ' ');
                    if (i9 > 0) {
                        sb.append('_');
                    }
                    sb.append(c8);
                }
                i9++;
            }
            return sb.toString();
        }
        if (i8 == 2) {
            StringBuilder sb2 = new StringBuilder();
            while (i9 < str.length()) {
                char cCharAt3 = str.charAt(i9);
                if (cCharAt3 < 'A' || cCharAt3 > 'Z') {
                    sb2.append(cCharAt3);
                } else {
                    char c9 = (char) (cCharAt3 + ' ');
                    if (i9 > 0) {
                        sb2.append('-');
                    }
                    sb2.append(c9);
                }
                i9++;
            }
            return sb2.toString();
        }
        if (i8 != 3) {
            if (i8 != 4 || (cCharAt = str.charAt(0)) < 'A' || cCharAt > 'Z') {
                return str;
            }
            char[] charArray = str.toCharArray();
            charArray[0] = (char) (charArray[0] + ' ');
            return new String(charArray);
        }
        char cCharAt4 = str.charAt(0);
        if (cCharAt4 < 'a' || cCharAt4 > 'z') {
            return str;
        }
        char[] charArray2 = str.toCharArray();
        charArray2[0] = (char) (charArray2[0] - ' ');
        return new String(charArray2);
    }
}
