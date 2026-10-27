package b.b.g.r;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: compiled from: SharedPreferencesUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public SharedPreferences f1836a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public SharedPreferences.Editor f1837b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f1838c;

    /* JADX INFO: compiled from: SharedPreferencesUtils.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static v f1839a = new v();
    }

    public static v d() {
        return b.f1839a;
    }

    public boolean a(String str) {
        return this.f1836a.contains(str);
    }

    public boolean b(String str, boolean z) {
        return this.f1836a.getBoolean(str, z);
    }

    public double c(String str, double d2) {
        return Double.longBitsToDouble(this.f1836a.getLong(str, Double.doubleToRawLongBits(d2)));
    }

    public int e(String str, int i) {
        return this.f1836a.getInt(str, i);
    }

    public long f(String str, long j) {
        return this.f1836a.getLong(str, j);
    }

    public void g(String str, boolean z) {
        this.f1837b.putBoolean(str, z);
        this.f1837b.commit();
    }

    public void h(String str, double d2) {
        this.f1837b.putLong(str, Double.doubleToRawLongBits(d2));
        this.f1837b.commit();
    }

    public void i(String str, int i) {
        this.f1837b.putInt(str, i);
        this.f1837b.commit();
    }

    public void j(String str, long j) {
        this.f1837b.putLong(str, j);
        this.f1837b.commit();
    }

    public void k(String str) {
        this.f1837b.remove(str);
        this.f1837b.commit();
    }

    public v() {
        Context contextA = b.b.a.a.a.b().a();
        this.f1838c = contextA;
        SharedPreferences sharedPreferences = contextA.getSharedPreferences("carsetting", 0);
        this.f1836a = sharedPreferences;
        this.f1837b = sharedPreferences.edit();
    }
}
