package spi;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import com.blankj.utilcode.util.Utils;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: SPUtils.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"ApplySharedPref"})
public final class t9 {
    public static final Map<String, t9> b = new HashMap();
    public SharedPreferences a;

    private t9(String str) {
        this.a = Utils.getApp().getSharedPreferences(str, 0);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.HashMap, java.util.Map<java.lang.String, spi.t9>] */
    public static t9 a() {
        ?? r1 = b;
        t9 t9Var = (t9) r1.get("spUtils");
        if (t9Var == null) {
            synchronized (t9.class) {
                t9Var = (t9) r1.get("spUtils");
                if (t9Var == null) {
                    t9Var = new t9("spUtils", 0);
                    r1.put("spUtils", t9Var);
                }
            }
        }
        return t9Var;
    }

    public final void b(long j) {
        this.a.edit().putLong("traffic", j).apply();
    }

    private t9(String str, int i) {
        this.a = Utils.getApp().getSharedPreferences(str, i);
    }
}
