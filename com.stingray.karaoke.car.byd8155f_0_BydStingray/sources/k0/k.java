package K0;

import A7.C0053a;
import com.stingray.kca.model.KaraokeItem;
import f6.o;
import m7.q;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class k extends C0053a implements InterfaceC2782k {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ int f3502s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ k(int i7, Object obj, Class cls, String str, String str2, int i10, int i11) {
        super(i7, i10, cls, obj, str, str2);
        this.f3502s = i11;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f3502s) {
            case 0:
                ((V.d) this.l).b((m) obj);
                break;
            case 1:
                KaraokeItem karaokeItem = (KaraokeItem) obj;
                A7.m.f(karaokeItem, "p0");
                ((e6.g) this.l).n(karaokeItem, null);
                break;
            case 2:
                KaraokeItem karaokeItem2 = (KaraokeItem) obj;
                A7.m.f(karaokeItem2, "p0");
                ((f6.g) this.l).n(karaokeItem2, null);
                break;
            default:
                KaraokeItem karaokeItem3 = (KaraokeItem) obj;
                A7.m.f(karaokeItem3, "p0");
                ((o) this.l).n(karaokeItem3, null);
                break;
        }
        return q.f19267a;
    }
}
