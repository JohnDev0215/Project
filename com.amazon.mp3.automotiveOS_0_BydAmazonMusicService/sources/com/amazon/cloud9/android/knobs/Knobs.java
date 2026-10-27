package com.amazon.cloud9.android.knobs;

import android.os.SystemProperties;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes2.dex */
public class Knobs {
    private static final Map<String, String> overrides = new HashMap();

    private static <E> E firstNonNull(@CheckForNull E e, E e2) {
        return e != null ? e : e2;
    }

    public static String get(String str, String str2) {
        return (String) firstNonNull(overrides.get(str), SystemProperties.get(str, str2));
    }

    public static boolean get(String str, boolean z) {
        return Boolean.parseBoolean(get(str, Boolean.toString(z)));
    }

    public static int get(String str, int i) {
        return Integer.parseInt(get(str, Integer.toString(i)));
    }

    private Knobs() {
    }
}
