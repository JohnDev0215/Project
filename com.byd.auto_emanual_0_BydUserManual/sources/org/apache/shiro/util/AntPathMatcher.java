package org.apache.shiro.util;

/* JADX INFO: loaded from: classes.dex */
public class AntPathMatcher implements PatternMatcher {
    public static final String DEFAULT_PATH_SEPARATOR = "/";
    private String pathSeparator = DEFAULT_PATH_SEPARATOR;

    public void setPathSeparator(String str) {
        if (str == null) {
            str = DEFAULT_PATH_SEPARATOR;
        }
        this.pathSeparator = str;
    }

    public boolean isPattern(String str) {
        if (str == null) {
            return false;
        }
        return (str.indexOf(42) == -1 && str.indexOf(63) == -1) ? false : true;
    }

    @Override // org.apache.shiro.util.PatternMatcher
    public boolean matches(String str, String str2) {
        return match(str, str2);
    }

    public boolean match(String str, String str2) {
        return doMatch(str, str2, true);
    }

    public boolean matchStart(String str, String str2) {
        return doMatch(str, str2, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v0 */
    /* JADX WARN: Type inference failed for: r14v1, types: [int] */
    /* JADX WARN: Type inference failed for: r14v3 */
    /* JADX WARN: Type inference failed for: r15v1 */
    /* JADX WARN: Type inference failed for: r15v2, types: [int] */
    /* JADX WARN: Type inference failed for: r15v4 */
    protected boolean doMatch(String str, String str2, boolean z) {
        int i;
        boolean z2 = false;
        if (str2 == null || str2.startsWith(this.pathSeparator) != str.startsWith(this.pathSeparator)) {
            return false;
        }
        int i2 = 1;
        String[] strArr = StringUtils.tokenizeToStringArray(str, this.pathSeparator, false, true);
        String[] strArr2 = StringUtils.tokenizeToStringArray(str2, this.pathSeparator, false, true);
        int length = strArr.length - 1;
        int length2 = strArr2.length - 1;
        int i3 = 0;
        int i4 = 0;
        while (i3 <= length && i4 <= length2) {
            String str3 = strArr[i3];
            if ("**".equals(str3)) {
                break;
            }
            if (!matchStrings(str3, strArr2[i4])) {
                return false;
            }
            i3++;
            i4++;
        }
        if (i4 > length2) {
            if (i3 > length) {
                if (str.endsWith(this.pathSeparator)) {
                    return str2.endsWith(this.pathSeparator);
                }
                return !str2.endsWith(this.pathSeparator);
            }
            if (!z) {
                return true;
            }
            if (i3 == length && strArr[i3].equals("*") && str2.endsWith(this.pathSeparator)) {
                return true;
            }
            while (i3 <= length) {
                if (!strArr[i3].equals("**")) {
                    return false;
                }
                i3++;
            }
            return true;
        }
        if (i3 > length) {
            return false;
        }
        if (!z && "**".equals(strArr[i3])) {
            return true;
        }
        while (i3 <= length && i4 <= length2) {
            String str4 = strArr[length];
            if (str4.equals("**")) {
                break;
            }
            if (!matchStrings(str4, strArr2[length2])) {
                return false;
            }
            length--;
            length2--;
        }
        if (i4 > length2) {
            while (i3 <= length) {
                if (!strArr[i3].equals("**")) {
                    return false;
                }
                i3++;
            }
            return true;
        }
        while (i3 != length && i4 <= length2) {
            int i5 = i3 + 1;
            int i6 = i5;
            while (true) {
                if (i6 > length) {
                    i6 = -1;
                    break;
                }
                if (strArr[i6].equals("**")) {
                    break;
                }
                i6++;
            }
            if (i6 == i5) {
                i3 = i5;
            } else {
                int i7 = (i6 - i3) - i2;
                int i8 = (length2 - i4) + i2;
                ?? r14 = z2;
                while (true) {
                    if (r14 > i8 - i7) {
                        i = -1;
                        break;
                    }
                    for (?? r15 = z2; r15 < i7; r15++) {
                        if (!matchStrings(strArr[i3 + r15 + 1], strArr2[i4 + r14 + r15])) {
                            break;
                        }
                    }
                    i = i4 + r14;
                    break;
                    z2 = false;
                    r14++;
                }
                if (i == -1) {
                    return false;
                }
                z2 = false;
                i4 = i + i7;
                i3 = i6;
                i2 = 1;
            }
        }
        while (i3 <= length) {
            if (!strArr[i3].equals("**")) {
                return z2;
            }
            i3++;
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x004c, code lost:
    
        if (r9 <= r4) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x004e, code lost:
    
        if (r5 > r2) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0052, code lost:
    
        if (r0[r5] == '*') goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0054, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0055, code lost:
    
        r5 = r5 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0058, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0059, code lost:
    
        r10 = r0[r2];
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x005b, code lost:
    
        if (r10 == '*') goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x005d, code lost:
    
        if (r9 > r4) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x005f, code lost:
    
        if (r10 == '?') goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0063, code lost:
    
        if (r10 == r1[r4]) goto L108;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0065, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0066, code lost:
    
        r2 = r2 - 1;
        r4 = r4 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x006b, code lost:
    
        if (r9 <= r4) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x006d, code lost:
    
        if (r5 > r2) goto L109;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0071, code lost:
    
        if (r0[r5] == '*') goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0073, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0074, code lost:
    
        r5 = r5 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0077, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0078, code lost:
    
        if (r5 == r2) goto L112;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x007a, code lost:
    
        if (r9 > r4) goto L113;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x007c, code lost:
    
        r10 = r5 + 1;
        r11 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0080, code lost:
    
        if (r11 > r2) goto L119;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0084, code lost:
    
        if (r0[r11] != '*') goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0087, code lost:
    
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x008a, code lost:
    
        r11 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x008b, code lost:
    
        if (r11 != r10) goto L111;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x008d, code lost:
    
        r5 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x008f, code lost:
    
        r10 = (r11 - r5) - r3;
        r13 = (r4 - r9) + r3;
        r14 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0098, code lost:
    
        if (r14 > (r13 - r10)) goto L121;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x009a, code lost:
    
        r15 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x009b, code lost:
    
        if (r15 >= r10) goto L122;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x009d, code lost:
    
        r3 = r0[(r5 + r15) + 1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00a3, code lost:
    
        if (r3 == r7) goto L124;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00ab, code lost:
    
        if (r3 == r1[(r9 + r14) + r15]) goto L125;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00ad, code lost:
    
        r14 = r14 + 1;
        r7 = '?';
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x00b3, code lost:
    
        r15 = r15 + 1;
        r7 = '?';
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x00b9, code lost:
    
        r9 = r9 + r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x00bb, code lost:
    
        r9 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x00bc, code lost:
    
        if (r9 != (-1)) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x00be, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x00bf, code lost:
    
        r9 = r9 + r10;
        r5 = r11;
        r3 = 1;
        r7 = '?';
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x00c5, code lost:
    
        if (r5 > r2) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x00c9, code lost:
    
        if (r0[r5] == '*') goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x00cb, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x00cc, code lost:
    
        r5 = r5 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x00cf, code lost:
    
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean matchStrings(java.lang.String r18, java.lang.String r19) {
        /*
            Method dump skipped, instruction units count: 209
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.shiro.util.AntPathMatcher.matchStrings(java.lang.String, java.lang.String):boolean");
    }

    public String extractPathWithinPattern(String str, String str2) {
        int i = 0;
        String[] strArr = StringUtils.tokenizeToStringArray(str, this.pathSeparator, false, true);
        String[] strArr2 = StringUtils.tokenizeToStringArray(str2, this.pathSeparator, false, true);
        StringBuilder sb = new StringBuilder();
        boolean z = false;
        while (i < strArr.length) {
            String str3 = strArr[i];
            if (str3.indexOf(42) > -1 || str3.indexOf(63) > -1) {
                while (i < strArr2.length) {
                    if (z || (i == 0 && !str.startsWith(this.pathSeparator))) {
                        sb.append(this.pathSeparator);
                    }
                    sb.append(strArr2[i]);
                    i++;
                    z = true;
                }
            }
            i++;
        }
        return sb.toString();
    }
}
