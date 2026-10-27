package com.ts.appservice.caradapter.e;

/* JADX INFO: compiled from: ObjectConverter.java */
/* JADX INFO: loaded from: classes.dex */
public class e {
    public static int a(Object obj) {
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        if (obj instanceof String) {
            try {
                return Integer.parseInt((String) obj);
            } catch (NumberFormatException unused) {
            }
        }
        return 0;
    }
}
