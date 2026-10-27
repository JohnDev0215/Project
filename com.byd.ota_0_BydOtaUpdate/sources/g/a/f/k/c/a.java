package g.a.f.k.c;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f5739a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f5740b;

    public final int a(String str) {
        if (str == null) {
            return 1;
        }
        return str.hashCode();
    }

    public String b() {
        return this.f5739a;
    }

    public String c() {
        return this.f5740b;
    }

    public final boolean d(String str, String str2) {
        if (str == str2) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        return str.equals(str2);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return aVar == this || (d(this.f5739a, aVar.f5739a) && d(this.f5740b, aVar.f5740b));
    }

    public int hashCode() {
        return a(this.f5739a) + (a(this.f5740b) * 31);
    }
}
