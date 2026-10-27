package com.spotify.common.uri;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static final byte[] a = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90};
    public static final Set b = Collections.unmodifiableSet(new HashSet<Character>() { // from class: com.spotify.common.uri.Base62$1
        {
            byte[] bArr = a.a;
            for (int i = 0; i < 62; i++) {
                add(Character.valueOf((char) (bArr[i] & 255)));
            }
        }
    });
    public static final char[] c = "7N42dgm5tFLK9N8MT7fHC7".toCharArray();

    public static boolean a(String str) {
        char c2;
        if (22 != str.length()) {
            return false;
        }
        boolean z = false;
        for (int i = 0; i < 22; i++) {
            char cCharAt = str.charAt(i);
            if (!b.contains(Character.valueOf(cCharAt))) {
                return false;
            }
            if (!z && cCharAt != (c2 = c[i])) {
                if ((c2 >= '0' && c2 <= '9') || (cCharAt >= '0' && cCharAt <= '9')) {
                    if (c2 <= cCharAt) {
                        return false;
                    }
                    z = true;
                } else if (Character.isUpperCase(c2) == Character.isUpperCase(cCharAt)) {
                    if (c2 <= cCharAt) {
                        return false;
                    }
                    z = true;
                } else {
                    if (!Character.isUpperCase(c2)) {
                        return false;
                    }
                    z = true;
                }
            }
        }
        return true;
    }
}
