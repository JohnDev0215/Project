package Q5;

import com.stingray.kca.model.KaraokeItem;
import com.stingray.shared.logs.segment.songQueue.SourceType;
import i6.C1415d;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: Q5.r2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0615r2 implements InterfaceC2782k {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1415d f7045m;

    public /* synthetic */ C0615r2(C1415d c1415d, int i7) {
        this.l = i7;
        this.f7045m = c1415d;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.l) {
            case 0:
                KaraokeItem karaokeItem = (KaraokeItem) obj;
                A7.m.f(karaokeItem, "item");
                C1415d c1415d = this.f7045m;
                String str = c1415d.f12250s;
                c1415d.n(karaokeItem, str != null ? new F6.b(SourceType.PAGE, str) : null);
                break;
            case 1:
                KaraokeItem karaokeItem2 = (KaraokeItem) obj;
                A7.m.f(karaokeItem2, "item");
                C1415d c1415d2 = this.f7045m;
                String str2 = c1415d2.f12250s;
                c1415d2.n(karaokeItem2, str2 != null ? new F6.b(SourceType.PAGE, str2) : null);
                break;
            case 2:
                A7.m.f((j6.a) obj, "it");
                this.f7045m.i(true);
                break;
            case 3:
                A7.m.f((String) obj, "it");
                this.f7045m.i(true);
                break;
            default:
                A7.m.f((String) obj, "it");
                this.f7045m.i(true);
                break;
        }
        return m7.q.f19267a;
    }
}
