package d.a.a.f.c.a.a;

import android.text.TextUtils;

/* JADX INFO: compiled from: AttrFactory.java */
/* JADX INFO: loaded from: classes.dex */
public class g {
    public static l a(l lVar, String str, int i2, String str2, String str3) {
        if (lVar == null) {
            return null;
        }
        lVar.f4651a = str;
        lVar.f4652b = i2;
        lVar.f4653c = str2;
        lVar.f4654d = str3;
        return lVar;
    }

    public static l b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if ("background".equals(str)) {
            return new a();
        }
        if ("textColor".equals(str)) {
            return new j();
        }
        if ("listSelector".equals(str)) {
            return new h();
        }
        if ("divider".equals(str)) {
            return new e();
        }
        if ("src".equals(str)) {
            return new i();
        }
        if (str.startsWith("drawable")) {
            return new f(str);
        }
        if (str.startsWith("tin")) {
            return new k();
        }
        if (str.startsWith("byd_loading_")) {
            return new d(str);
        }
        if (str.startsWith("byd_clock_")) {
            return new b(str);
        }
        if (str.startsWith("byd_dgclock_")) {
            return new c(str);
        }
        return null;
    }
}
