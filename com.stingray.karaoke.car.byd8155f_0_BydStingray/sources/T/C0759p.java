package T;

import e0.C1182v;

/* JADX INFO: renamed from: T.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0759p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f8286a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f8287b;

    public /* synthetic */ C0759p(Object obj, int i7) {
        this.f8286a = i7;
        this.f8287b = obj;
    }

    public final void a() {
        switch (this.f8286a) {
            case 0:
                C0761q c0761q = (C0761q) this.f8287b;
                c0761q.f8326z--;
                break;
            default:
                C1182v c1182v = (C1182v) this.f8287b;
                c1182v.f13742j--;
                break;
        }
    }

    public final void b() {
        switch (this.f8286a) {
            case 0:
                ((C0761q) this.f8287b).f8326z++;
                break;
            default:
                ((C1182v) this.f8287b).f13742j++;
                break;
        }
    }
}
