package b.b.g.m.d;

/* JADX INFO: compiled from: BydTimeZone.java */
/* JADX INFO: loaded from: classes2.dex */
public class e implements Comparable<e> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f911a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f912b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f913c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f914d;

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(e eVar) {
        if (this.f911a > eVar.e()) {
            return 1;
        }
        return this.f911a < eVar.e() ? -1 : 0;
    }

    public String b() {
        return this.f914d;
    }

    public String c() {
        return this.f912b;
    }

    public String d() {
        return this.f913c;
    }

    public int e() {
        return this.f911a;
    }

    public void f(String str) {
        this.f914d = str;
    }

    public void g(String str) {
        this.f912b = str;
    }

    public void h(String str) {
        this.f913c = str;
    }

    public String toString() {
        return "BydTimeZone{id='" + this.f912b + "', name='" + this.f913c + "', gmt='" + this.f914d + "'}";
    }
}
