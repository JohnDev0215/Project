package Q5;

import com.stingray.kca.model.KaraokeItem;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: Q5.u2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0627u2 implements InterfaceC2772a {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f7084m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ KaraokeItem f7085n;

    public /* synthetic */ C0627u2(InterfaceC2782k interfaceC2782k, KaraokeItem karaokeItem, int i7) {
        this.l = i7;
        this.f7084m = interfaceC2782k;
        this.f7085n = karaokeItem;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.l) {
            case 0:
                this.f7084m.b(this.f7085n);
                break;
            default:
                this.f7084m.b(this.f7085n);
                break;
        }
        return m7.q.f19267a;
    }
}
