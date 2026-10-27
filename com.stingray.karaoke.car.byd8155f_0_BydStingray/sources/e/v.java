package E;

import G.P;
import T.C0732b0;
import T.C0735d;

/* JADX INFO: loaded from: classes.dex */
public final class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1442a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0732b0 f1443b;
    public final C0732b0 c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f1444e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final P f1445f;

    public v(int i7, int i10, int i11) {
        this.f1442a = i11;
        switch (i11) {
            case 1:
                this.f1443b = C0735d.M(i7);
                this.c = C0735d.M(i10);
                this.f1445f = new P(i7, 90, 200);
                break;
            default:
                this.f1443b = C0735d.M(i7);
                this.c = C0735d.M(i10);
                this.f1445f = new P(i7, 30, 100);
                break;
        }
    }

    public final void a(int i7, int i10) {
        switch (this.f1442a) {
            case 0:
                if (i7 >= 0.0f) {
                    this.f1443b.j(i7);
                    this.f1445f.b(i7);
                    this.c.j(i10);
                    return;
                } else {
                    throw new IllegalArgumentException(("Index should be non-negative (" + i7 + ')').toString());
                }
            default:
                if (i7 >= 0.0f) {
                    this.f1443b.j(i7);
                    this.f1445f.b(i7);
                    this.c.j(i10);
                    return;
                } else {
                    throw new IllegalArgumentException(("Index should be non-negative (" + i7 + ')').toString());
                }
        }
    }
}
