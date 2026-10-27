package b7;

import java.util.Timer;

/* JADX INFO: renamed from: b7.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1005f implements k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f12285a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Timer f12286b;

    public /* synthetic */ C1005f(Timer timer, int i7) {
        this.f12285a = i7;
        this.f12286b = timer;
    }

    @Override // b7.k
    public final void a() {
        switch (this.f12285a) {
            case 0:
                this.f12286b.cancel();
                break;
            default:
                this.f12286b.cancel();
                break;
        }
    }
}
