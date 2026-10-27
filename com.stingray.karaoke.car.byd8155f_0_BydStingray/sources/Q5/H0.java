package Q5;

import com.stingray.kca.model.KaraokeItem;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class H0 implements InterfaceC2772a {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f6372m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ KaraokeItem f6373n;

    public /* synthetic */ H0(InterfaceC2782k interfaceC2782k, KaraokeItem karaokeItem, int i7) {
        this.l = i7;
        this.f6372m = interfaceC2782k;
        this.f6373n = karaokeItem;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.l) {
            case 0:
                this.f6372m.b(this.f6373n);
                break;
            case 1:
                this.f6372m.b(this.f6373n);
                break;
            case 2:
                this.f6372m.b(this.f6373n);
                break;
            default:
                this.f6372m.b(this.f6373n);
                break;
        }
        return m7.q.f19267a;
    }
}
