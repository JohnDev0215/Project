package com.google.gson.internal.sql;

import p.lo2;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static final boolean a;
    public static final lo2 b;
    public static final lo2 c;
    public static final lo2 d;

    static {
        boolean z = true;
        try {
            Class.forName("java.sql.Date");
        } catch (ClassNotFoundException unused) {
            z = false;
        }
        a = z;
        if (z) {
            b = SqlDateTypeAdapter.b;
            c = SqlTimeTypeAdapter.b;
            d = SqlTimestampTypeAdapter.b;
        } else {
            b = null;
            c = null;
            d = null;
        }
    }
}
