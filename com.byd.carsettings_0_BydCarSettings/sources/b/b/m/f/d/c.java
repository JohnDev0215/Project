package b.b.m.f.d;

/* JADX INFO: compiled from: CarDialogInfo.java */
/* JADX INFO: loaded from: classes2.dex */
public class c implements Comparable<c> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2139a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f2140b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f2141c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f2142d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f2143e = false;

    public c(int i, String str, String str2) {
        this.f2139a = i;
        this.f2141c = str;
        this.f2140b = str2;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(c cVar) {
        if (cVar.c() > this.f2139a) {
            return -1;
        }
        return cVar.c() < this.f2139a ? 1 : 0;
    }

    public String b() {
        return this.f2141c;
    }

    public int c() {
        return this.f2139a;
    }

    public String d() {
        return this.f2140b;
    }

    public boolean e() {
        return this.f2143e;
    }

    public void f(boolean z) {
        this.f2143e = z;
    }

    public String toString() {
        return "CarDialogInfo{level=" + this.f2139a + ", method='" + this.f2140b + "', clazzName='" + this.f2141c + "', subClazzName='" + this.f2142d + "', staticMethod='" + this.f2143e + "'}";
    }
}
