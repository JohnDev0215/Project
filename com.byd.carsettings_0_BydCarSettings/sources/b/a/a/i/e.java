package b.a.a.i;

import androidx.appcompat.widget.ActivityChooserView;

/* JADX INFO: compiled from: Item.java */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f202a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f203b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f204c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f205d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f206e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f207f;
    public String g;
    public int h;
    public e i;

    public e() {
    }

    public boolean a(e eVar) {
        int i = this.f203b;
        if (i != 1) {
            if (i != 15) {
                if (i == 12) {
                    return eVar.f206e.equals(this.f206e) && eVar.f207f.equals(this.f207f);
                }
                if (i != 13) {
                    switch (i) {
                        case 3:
                        case 4:
                            if (eVar.f204c != this.f204c) {
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
                            if (!eVar.f206e.equals(this.f206e) || !eVar.f207f.equals(this.f207f) || !eVar.g.equals(this.g)) {
                            }
                            break;
                    }
                    return false;
                }
            }
            return eVar.f205d == this.f205d;
        }
        return eVar.f206e.equals(this.f206e);
    }

    public void b(int i) {
        this.f203b = 3;
        this.f204c = i;
        this.h = Integer.MAX_VALUE & (3 + i);
    }

    public void c(int i, String str, String str2, String str3) {
        this.f203b = i;
        this.f206e = str;
        this.f207f = str2;
        this.g = str3;
        if (i != 1 && i != 7 && i != 8) {
            if (i == 12) {
                this.h = (i + (str.hashCode() * str2.hashCode())) & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
                return;
            } else if (i != 13) {
                this.h = (i + (str.hashCode() * str2.hashCode() * str3.hashCode())) & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
                return;
            }
        }
        this.h = (i + str.hashCode()) & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
    }

    public e(int i, e eVar) {
        this.f202a = i;
        this.f203b = eVar.f203b;
        this.f204c = eVar.f204c;
        this.f205d = eVar.f205d;
        this.f206e = eVar.f206e;
        this.f207f = eVar.f207f;
        this.g = eVar.g;
        this.h = eVar.h;
    }
}
