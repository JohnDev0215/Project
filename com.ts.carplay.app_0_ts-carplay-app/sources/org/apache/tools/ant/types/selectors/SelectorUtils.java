package org.apache.tools.ant.types.selectors;

import java.io.File;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.tools.ant.types.Resource;

/* JADX INFO: loaded from: classes.dex */
public final class SelectorUtils {
    private static SelectorUtils instance = new SelectorUtils();

    private SelectorUtils() {
    }

    public static SelectorUtils getInstance() {
        return instance;
    }

    public static boolean matchPatternStart(String pattern, String str) {
        return matchPatternStart(pattern, str, true);
    }

    public static boolean matchPatternStart(String pattern, String str, boolean isCaseSensitive) {
        if (str.startsWith(File.separator) != pattern.startsWith(File.separator)) {
            return false;
        }
        String[] patDirs = tokenizePathAsArray(pattern);
        String[] strDirs = tokenizePathAsArray(str);
        int patIdxStart = 0;
        int patIdxEnd = patDirs.length - 1;
        int strIdxStart = 0;
        int strIdxEnd = strDirs.length - 1;
        while (patIdxStart <= patIdxEnd && strIdxStart <= strIdxEnd) {
            String patDir = patDirs[patIdxStart];
            if (patDir.equals("**")) {
                break;
            }
            if (!match(patDir, strDirs[strIdxStart], isCaseSensitive)) {
                return false;
            }
            patIdxStart++;
            strIdxStart++;
        }
        return strIdxStart > strIdxEnd || patIdxStart <= patIdxEnd;
    }

    public static boolean matchPath(String pattern, String str) {
        return matchPath(pattern, str, true);
    }

    public static boolean matchPath(String pattern, String str, boolean isCaseSensitive) {
        int patIdxTmp;
        int j;
        if (str.startsWith(File.separator) != pattern.startsWith(File.separator)) {
            return false;
        }
        String[] patDirs = tokenizePathAsArray(pattern);
        String[] strDirs = tokenizePathAsArray(str);
        int patIdxStart = 0;
        int patIdxEnd = patDirs.length - 1;
        int strIdxStart = 0;
        int strIdxEnd = strDirs.length - 1;
        while (patIdxStart <= patIdxEnd && strIdxStart <= strIdxEnd) {
            String patDir = patDirs[patIdxStart];
            if (patDir.equals("**")) {
                break;
            }
            if (!match(patDir, strDirs[strIdxStart], isCaseSensitive)) {
                return false;
            }
            patIdxStart++;
            strIdxStart++;
        }
        if (strIdxStart > strIdxEnd) {
            for (int i = patIdxStart; i <= patIdxEnd; i++) {
                if (!patDirs[i].equals("**")) {
                    return false;
                }
            }
            return true;
        }
        if (patIdxStart > patIdxEnd) {
            return false;
        }
        while (patIdxStart <= patIdxEnd && strIdxStart <= strIdxEnd) {
            String patDir2 = patDirs[patIdxEnd];
            if (patDir2.equals("**")) {
                break;
            }
            if (!match(patDir2, strDirs[strIdxEnd], isCaseSensitive)) {
                return false;
            }
            patIdxEnd--;
            strIdxEnd--;
        }
        if (strIdxStart > strIdxEnd) {
            for (int i2 = patIdxStart; i2 <= patIdxEnd; i2++) {
                if (!patDirs[i2].equals("**")) {
                    return false;
                }
            }
            return true;
        }
        while (patIdxStart != patIdxEnd && strIdxStart <= strIdxEnd) {
            int i3 = patIdxStart + 1;
            while (true) {
                if (i3 > patIdxEnd) {
                    patIdxTmp = -1;
                    break;
                }
                if (!patDirs[i3].equals("**")) {
                    i3++;
                } else {
                    int patIdxTmp2 = i3;
                    patIdxTmp = patIdxTmp2;
                    break;
                }
            }
            int patIdxTmp3 = patIdxStart + 1;
            if (patIdxTmp == patIdxTmp3) {
                patIdxStart++;
            } else {
                int patLength = (patIdxTmp - patIdxStart) - 1;
                int strLength = (strIdxEnd - strIdxStart) + 1;
                int i4 = 0;
                while (true) {
                    if (i4 > strLength - patLength) {
                        j = -1;
                        break;
                    }
                    for (int j2 = 0; j2 < patLength; j2++) {
                        String subPat = patDirs[patIdxStart + j2 + 1];
                        String subStr = strDirs[strIdxStart + i4 + j2];
                        if (!match(subPat, subStr, isCaseSensitive)) {
                            break;
                        }
                    }
                    int foundIdx = strIdxStart + i4;
                    j = foundIdx;
                    break;
                    i4++;
                }
                if (j == -1) {
                    return false;
                }
                patIdxStart = patIdxTmp;
                strIdxStart = j + patLength;
            }
        }
        for (int i5 = patIdxStart; i5 <= patIdxEnd; i5++) {
            if (!patDirs[i5].equals("**")) {
                return false;
            }
        }
        return true;
    }

    public static boolean match(String pattern, String str) {
        return match(pattern, str, true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x00fe, code lost:
    
        if (r7 == '?') goto L161;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x0100, code lost:
    
        if (r21 == false) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0108, code lost:
    
        if (r7 == r1[(r15 + r2) + r10]) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x010b, code lost:
    
        if (r21 != false) goto L162;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x011b, code lost:
    
        if (java.lang.Character.toUpperCase(r7) == java.lang.Character.toUpperCase(r1[(r15 + r2) + r10])) goto L163;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x011e, code lost:
    
        r2 = r2 + 1;
        r4 = 1;
        r11 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x0129, code lost:
    
        r10 = r10 + 1;
        r4 = 1;
        r11 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x0132, code lost:
    
        r3 = r3 + 1;
        r4 = 1;
        r10 = 42;
        r11 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x013b, code lost:
    
        r2 = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x013c, code lost:
    
        if (r2 <= r13) goto L115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x013e, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x0145, code lost:
    
        if (r0[r2] == '*') goto L119;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x0147, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x0149, code lost:
    
        r2 = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00a6, code lost:
    
        if (r5 <= r14) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00a8, code lost:
    
        r3 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00a9, code lost:
    
        if (r3 <= r13) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00ab, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x00ae, code lost:
    
        if (r0[r3] == '*') goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00b0, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00b1, code lost:
    
        r3 = r3 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x00b4, code lost:
    
        r15 = r5;
        r12 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00b8, code lost:
    
        if (r12 == r13) goto L148;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00ba, code lost:
    
        if (r15 <= r14) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x00be, code lost:
    
        r3 = r12 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x00c1, code lost:
    
        if (r3 <= r13) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x00c3, code lost:
    
        r5 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x00c7, code lost:
    
        if (r0[r3] != r10) goto L110;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x00c9, code lost:
    
        r2 = r3;
        r5 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x00cb, code lost:
    
        r2 = r12 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x00cd, code lost:
    
        if (r5 != r2) goto L147;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x00cf, code lost:
    
        r12 = r12 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x00d2, code lost:
    
        r6 = (r5 - r12) - 1;
        r17 = (r14 - r15) + 1;
        r2 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x00df, code lost:
    
        if (r2 <= (r17 - r6)) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x00e1, code lost:
    
        r10 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x00e4, code lost:
    
        r10 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x00e5, code lost:
    
        if (r10 < r6) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x00e7, code lost:
    
        r18 = r15 + r2;
        r10 = r18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x00ec, code lost:
    
        if (r10 != (-1)) goto L98;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x00ee, code lost:
    
        return r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x00ef, code lost:
    
        r12 = r5;
        r15 = r10 + r6;
        r10 = 42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x00f7, code lost:
    
        r7 = r0[(r12 + r10) + r4];
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean match(java.lang.String r19, java.lang.String r20, boolean r21) {
        /*
            Method dump skipped, instruction units count: 337
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.types.selectors.SelectorUtils.match(java.lang.String, java.lang.String, boolean):boolean");
    }

    public static Vector tokenizePath(String path) {
        return tokenizePath(path, File.separator);
    }

    public static Vector tokenizePath(String path, String separator) {
        Vector ret = new Vector();
        StringTokenizer st = new StringTokenizer(path, separator);
        while (st.hasMoreTokens()) {
            ret.addElement(st.nextToken());
        }
        return ret;
    }

    private static String[] tokenizePathAsArray(String path) {
        char sep = File.separatorChar;
        int start = 0;
        int len = path.length();
        int count = 0;
        for (int pos = 0; pos < len; pos++) {
            if (path.charAt(pos) == sep) {
                if (pos != start) {
                    count++;
                }
                start = pos + 1;
            }
        }
        if (len != start) {
            count++;
        }
        String[] l = new String[count];
        int count2 = 0;
        int start2 = 0;
        for (int pos2 = 0; pos2 < len; pos2++) {
            if (path.charAt(pos2) == sep) {
                if (pos2 != start2) {
                    String tok = path.substring(start2, pos2);
                    l[count2] = tok;
                    count2++;
                }
                start2 = pos2 + 1;
            }
        }
        if (len != start2) {
            String tok2 = path.substring(start2);
            l[count2] = tok2;
        }
        return l;
    }

    public static boolean isOutOfDate(File src, File target, int granularity) {
        if (src.exists()) {
            return !target.exists() || src.lastModified() - ((long) granularity) > target.lastModified();
        }
        return false;
    }

    public static boolean isOutOfDate(Resource src, Resource target, int granularity) {
        if (src.isExists()) {
            return !target.isExists() || src.getLastModified() - ((long) granularity) > target.getLastModified();
        }
        return false;
    }

    public static String removeWhitespace(String input) {
        StringBuffer result = new StringBuffer();
        if (input != null) {
            StringTokenizer st = new StringTokenizer(input);
            while (st.hasMoreTokens()) {
                result.append(st.nextToken());
            }
        }
        return result.toString();
    }

    public static boolean hasWildcards(String input) {
        return (input.indexOf(42) == -1 && input.indexOf(63) == -1) ? false : true;
    }

    public static String rtrimWildcardTokens(String input) {
        Vector v = tokenizePath(input, File.separator);
        StringBuffer sb = new StringBuffer();
        for (int counter = 0; counter < v.size() && !hasWildcards((String) v.elementAt(counter)); counter++) {
            if (counter > 0) {
                sb.append(File.separator);
            }
            sb.append((String) v.elementAt(counter));
        }
        return sb.toString();
    }
}
