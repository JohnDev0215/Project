package t9;

import w9.v;

/* JADX INFO: loaded from: classes.dex */
public final class e extends y9.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f22522a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final w9.a f22523b;

    public e(int i7) {
        this.f22522a = i7;
        switch (i7) {
            case 1:
                this.f22523b = new v();
                break;
            default:
                this.f22523b = new w9.i(1);
                break;
        }
    }

    private final void h(CharSequence charSequence) {
    }

    @Override // y9.a
    public void a(CharSequence charSequence) {
        int i7 = this.f22522a;
    }

    @Override // y9.a
    public boolean b(w9.a aVar) {
        switch (this.f22522a) {
            case 0:
                return true;
            default:
                return super.b(aVar);
        }
    }

    @Override // y9.a
    public final w9.a d() {
        switch (this.f22522a) {
            case 0:
                return (w9.i) this.f22523b;
            default:
                return (v) this.f22523b;
        }
    }

    @Override // y9.a
    public boolean e() {
        switch (this.f22522a) {
            case 0:
                return true;
            default:
                return super.e();
        }
    }

    @Override // y9.a
    public final P2.g g(f fVar) {
        switch (this.f22522a) {
            case 0:
                return P2.g.a(fVar.f22527b);
            default:
                return null;
        }
    }
}
