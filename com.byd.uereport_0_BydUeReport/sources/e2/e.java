package e2;

/* JADX INFO: compiled from: Item.java */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f4545a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4546b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4547c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f4548d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f4549e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f4550f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f4551g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4552h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public e f4553i;

    public e() {
    }

    public boolean a(e eVar) {
        int i8 = this.f4546b;
        if (i8 != 1) {
            if (i8 != 15) {
                if (i8 == 12) {
                    return eVar.f4549e.equals(this.f4549e) && eVar.f4550f.equals(this.f4550f);
                }
                if (i8 != 13) {
                    switch (i8) {
                        case 3:
                        case 4:
                            if (eVar.f4547c != this.f4547c) {
                                break;
                            }
                            break;
                        case 5:
                        case 6:
                            break;
                        case 7:
                        case 8:
                            break;
                        default:
                            if (!eVar.f4549e.equals(this.f4549e) || !eVar.f4550f.equals(this.f4550f) || !eVar.f4551g.equals(this.f4551g)) {
                            }
                            break;
                    }
                    return false;
                }
            }
            return eVar.f4548d == this.f4548d;
        }
        return eVar.f4549e.equals(this.f4549e);
    }

    public void b(int i8) {
        this.f4546b = 3;
        this.f4547c = i8;
        this.f4552h = Integer.MAX_VALUE & (3 + i8);
    }

    public void c(int i8, String str, String str2, String str3) {
        this.f4546b = i8;
        this.f4549e = str;
        this.f4550f = str2;
        this.f4551g = str3;
        if (i8 != 1 && i8 != 7 && i8 != 8) {
            if (i8 == 12) {
                this.f4552h = (i8 + (str.hashCode() * str2.hashCode())) & Integer.MAX_VALUE;
                return;
            } else if (i8 != 13) {
                this.f4552h = (i8 + (str.hashCode() * str2.hashCode() * str3.hashCode())) & Integer.MAX_VALUE;
                return;
            }
        }
        this.f4552h = (i8 + str.hashCode()) & Integer.MAX_VALUE;
    }

    public e(int i8, e eVar) {
        this.f4545a = i8;
        this.f4546b = eVar.f4546b;
        this.f4547c = eVar.f4547c;
        this.f4548d = eVar.f4548d;
        this.f4549e = eVar.f4549e;
        this.f4550f = eVar.f4550f;
        this.f4551g = eVar.f4551g;
        this.f4552h = eVar.f4552h;
    }
}
