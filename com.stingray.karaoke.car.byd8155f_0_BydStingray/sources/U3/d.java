package U3;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f9009a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final char f9010b;
    public final double c;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f9011e;

    public d(ArrayList arrayList, char c, double d, String str, String str2) {
        this.f9009a = arrayList;
        this.f9010b = c;
        this.c = d;
        this.d = str;
        this.f9011e = str2;
    }

    public static int a(char c, String str, String str2) {
        return str2.hashCode() + M5.b.c(str, c * 31, 31);
    }

    public final int hashCode() {
        return a(this.f9010b, this.f9011e, this.d);
    }
}
