package org.apache.shiro.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;
import org.apache.shiro.config.Ini;

/* JADX INFO: loaded from: classes.dex */
public class StringUtils {
    public static final char DEFAULT_DELIMITER_CHAR = ',';
    public static final char DEFAULT_QUOTE_CHAR = '\"';
    public static final String EMPTY_STRING = "";

    public static boolean hasText(String str) {
        if (!hasLength(str)) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasLength(String str) {
        return str != null && str.length() > 0;
    }

    public static boolean startsWithIgnoreCase(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        if (str.startsWith(str2)) {
            return true;
        }
        if (str.length() < str2.length()) {
            return false;
        }
        return str.substring(0, str2.length()).toLowerCase().equals(str2.toLowerCase());
    }

    public static String clean(String str, boolean z) {
        if (str == null) {
            return str;
        }
        if (z) {
            str = str.trim();
        }
        if (str.equals("")) {
            return null;
        }
        return str;
    }

    public static String clean(String str) {
        return clean(str, true);
    }

    public static String toString(Object[] objArr) {
        return toDelimitedString(objArr, ",");
    }

    public static String toDelimitedString(Object[] objArr, String str) {
        if (objArr == null || objArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < objArr.length; i++) {
            if (i > 0) {
                sb.append(str);
            }
            sb.append(objArr[i]);
        }
        return sb.toString();
    }

    public static String toDelimitedString(Collection collection, String str) {
        return (collection == null || collection.isEmpty()) ? "" : join(collection.iterator(), str);
    }

    public static String[] tokenizeToStringArray(String str, String str2) {
        return tokenizeToStringArray(str, str2, true, true);
    }

    public static String[] tokenizeToStringArray(String str, String str2, boolean z, boolean z2) {
        if (str == null) {
            return null;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str, str2);
        ArrayList arrayList = new ArrayList();
        while (stringTokenizer.hasMoreTokens()) {
            String strNextToken = stringTokenizer.nextToken();
            if (z) {
                strNextToken = strNextToken.trim();
            }
            if (!z2 || strNextToken.length() > 0) {
                arrayList.add(strNextToken);
            }
        }
        return toStringArray(arrayList);
    }

    public static String[] toStringArray(Collection collection) {
        if (collection == null) {
            return null;
        }
        return (String[]) collection.toArray(new String[collection.size()]);
    }

    public static String[] splitKeyValue(String str) throws ParseException {
        String strClean = clean(str);
        if (strClean == null) {
            return null;
        }
        String[] strArrSplit = strClean.split(" ", 2);
        if (strArrSplit.length != 2) {
            strArrSplit = strClean.split("=", 2);
            if (strArrSplit.length != 2) {
                throw new ParseException("Unable to determine Key/Value pair from line [" + strClean + "].  There is no space from which the split location could be determined.", 0);
            }
        }
        strArrSplit[0] = clean(strArrSplit[0]);
        strArrSplit[1] = clean(strArrSplit[1]);
        if (strArrSplit[1].startsWith("=")) {
            strArrSplit[1] = clean(strArrSplit[1].substring(1));
        }
        if (strArrSplit[0] == null) {
            throw new ParseException("No valid key could be found in line [" + strClean + "] to form a key/value pair.", 0);
        }
        if (strArrSplit[1] != null) {
            return strArrSplit;
        }
        throw new ParseException("No corresponding value could be found in line [" + strClean + "] for key [" + strArrSplit[0] + Ini.SECTION_SUFFIX, 0);
    }

    public static String[] split(String str) {
        return split(str, DEFAULT_DELIMITER_CHAR);
    }

    public static String[] split(String str, char c) {
        return split(str, c, DEFAULT_QUOTE_CHAR);
    }

    public static String[] split(String str, char c, char c2) {
        return split(str, c, c2, c2);
    }

    public static String[] split(String str, char c, char c2, char c3) {
        return split(str, c, c2, c3, false, true);
    }

    public static String[] split(String str, char c, char c2, char c3, boolean z, boolean z2) {
        int i;
        String strClean = clean(str);
        if (strClean == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        boolean z3 = false;
        while (i2 < strClean.length()) {
            char cCharAt = strClean.charAt(i2);
            if (cCharAt == c2) {
                if (z3 && strClean.length() > (i = i2 + 1) && strClean.charAt(i) == c2) {
                    sb.append(strClean.charAt(i));
                    i2 = i;
                } else {
                    z3 = !z3;
                    if (z) {
                        sb.append(cCharAt);
                    }
                }
            } else if (cCharAt == c3) {
                z3 = !z3;
                if (z) {
                    sb.append(cCharAt);
                }
            } else if (cCharAt == c && !z3) {
                String string = sb.toString();
                if (z2) {
                    string = string.trim();
                }
                arrayList.add(string);
                sb = new StringBuilder();
            } else {
                sb.append(cCharAt);
            }
            i2++;
        }
        String string2 = sb.toString();
        if (z2) {
            string2 = string2.trim();
        }
        arrayList.add(string2);
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static String join(Iterator<?> it, String str) {
        if (it == null) {
            return null;
        }
        if (!it.hasNext()) {
            return "";
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return next == null ? "" : next.toString();
        }
        StringBuilder sb = new StringBuilder(256);
        if (next != null) {
            sb.append(next);
        }
        while (it.hasNext()) {
            if (str != null) {
                sb.append(str);
            }
            Object next2 = it.next();
            if (next2 != null) {
                sb.append(next2);
            }
        }
        return sb.toString();
    }

    public static Set<String> splitToSet(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        return asSet(split(str, str2.charAt(0)));
    }

    public static String uppercaseFirstChar(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        sb.append(Character.toUpperCase(str.charAt(0)));
        if (length > 1) {
            sb.append(str.substring(1));
        }
        return sb.toString();
    }

    private static <E> Set<E> asSet(E... eArr) {
        if (eArr == null || eArr.length == 0) {
            return Collections.emptySet();
        }
        if (eArr.length == 1) {
            return Collections.singleton(eArr[0]);
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(((eArr.length * 4) / 3) + 1);
        Collections.addAll(linkedHashSet, eArr);
        return linkedHashSet;
    }
}
