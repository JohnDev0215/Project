package e4;

/* JADX INFO: compiled from: CarDialogInfo.java */
/* JADX INFO: loaded from: classes2.dex */
public class c implements Comparable<c> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4622d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f4623e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f4624f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f4625g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f4626h = false;

    public c(int i8, String str, String str2) {
        this.f4622d = i8;
        this.f4624f = str;
        this.f4623e = str2;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(c cVar) {
        if (cVar.c() > this.f4622d) {
            return -1;
        }
        return cVar.c() < this.f4622d ? 1 : 0;
    }

    public String b() {
        return this.f4624f;
    }

    public int c() {
        return this.f4622d;
    }

    public String d() {
        return this.f4623e;
    }

    public String e() {
        return this.f4625g;
    }

    public boolean f() {
        return this.f4626h;
    }

    public void g(boolean z7) {
        this.f4626h = z7;
    }

    public void h(String str) {
        this.f4625g = str;
    }

    public String toString() {
        return "CarDialogInfo{level=" + this.f4622d + ", method='" + this.f4623e + "', clazzName='" + this.f4624f + "', subClazzName='" + this.f4625g + "', staticMethod='" + this.f4626h + "'}";
    }
}
