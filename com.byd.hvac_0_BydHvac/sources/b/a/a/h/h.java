package b.a.a.h;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public SharedPreferences f251a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public SharedPreferences.Editor f252b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f253c;

    /* JADX INFO: compiled from: caradapter-bug */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static h f254a = new h();
    }

    public static h b() {
        return b.f254a;
    }

    public boolean a(String str) {
        return this.f251a.contains(str);
    }

    public int c(String str, int i) {
        return this.f251a.getInt(str, i);
    }

    public void d(String str, int i) {
        this.f252b.putInt(str, i);
        this.f252b.commit();
    }

    public h() {
        Context contextA = b.a.a.h.b.b().a();
        this.f253c = contextA;
        SharedPreferences sharedPreferences = contextA.getSharedPreferences("hvac", 0);
        this.f251a = sharedPreferences;
        this.f252b = sharedPreferences.edit();
    }
}
