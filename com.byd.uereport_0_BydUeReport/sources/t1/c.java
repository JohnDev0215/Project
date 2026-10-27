package t1;

import java.util.List;
import v1.j;

/* JADX INFO: compiled from: FontCharacter.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<j> f8002a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final char f8003b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final double f8004c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final double f8005d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f8006e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f8007f;

    public c(List<j> list, char c8, double d8, double d9, String str, String str2) {
        this.f8002a = list;
        this.f8003b = c8;
        this.f8004c = d8;
        this.f8005d = d9;
        this.f8006e = str;
        this.f8007f = str2;
    }

    public static int c(char c8, String str, String str2) {
        return ((((0 + c8) * 31) + str.hashCode()) * 31) + str2.hashCode();
    }

    public List<j> a() {
        return this.f8002a;
    }

    public double b() {
        return this.f8005d;
    }

    public int hashCode() {
        return c(this.f8003b, this.f8007f, this.f8006e);
    }
}
