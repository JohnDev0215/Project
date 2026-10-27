package Q5;

import com.stingray.kca.model.KaraokeItem;
import com.stingray.shared.logs.segment.songQueue.SourceType;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q5.c0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0554c0 implements InterfaceC2782k {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2785n f6727m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ KaraokeItem f6728n;

    public /* synthetic */ C0554c0(InterfaceC2785n interfaceC2785n, KaraokeItem karaokeItem, int i7) {
        this.l = i7;
        this.f6727m = interfaceC2785n;
        this.f6728n = karaokeItem;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.l) {
            case 0:
                KaraokeItem karaokeItem = (KaraokeItem) obj;
                A7.m.f(karaokeItem, "it");
                String str = this.f6728n.f13107a;
                this.f6727m.invoke(karaokeItem, str != null ? new F6.b(SourceType.PAGE, str) : null);
                break;
            case 1:
                KaraokeItem karaokeItem2 = (KaraokeItem) obj;
                A7.m.f(karaokeItem2, "item");
                String str2 = this.f6728n.f13107a;
                this.f6727m.invoke(karaokeItem2, str2 != null ? new F6.b(SourceType.SWIMLANE, str2) : null);
                break;
            case 2:
                KaraokeItem karaokeItem3 = (KaraokeItem) obj;
                A7.m.f(karaokeItem3, "item");
                String str3 = this.f6728n.f13107a;
                this.f6727m.invoke(karaokeItem3, str3 != null ? new F6.b(SourceType.PROMO_BANNER, str3) : null);
                break;
            default:
                KaraokeItem karaokeItem4 = (KaraokeItem) obj;
                A7.m.f(karaokeItem4, "item");
                String str4 = this.f6728n.f13107a;
                this.f6727m.invoke(karaokeItem4, str4 != null ? new F6.b(SourceType.SWIMLANE, str4) : null);
                break;
        }
        return m7.q.f19267a;
    }
}
