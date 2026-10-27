package Q5;

import com.stingray.kca.model.KaraokeItem;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q5.z1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0646z1 extends A7.j implements InterfaceC2785n {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ int f7160t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2785n f7161u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2785n f7162v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ E.D f7163w;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0646z1(InterfaceC2785n interfaceC2785n, InterfaceC2785n interfaceC2785n2, E.D d, int i7) {
        super(2, A7.l.class, "onSelectItem", "SearchResults$onSelectItem(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/lazy/LazyListState;Lcom/stingray/kca/model/KaraokeItem;Lcom/stingray/shared/logs/segment/songQueue/SourceLog;)V", 0);
        this.f7160t = i7;
        switch (i7) {
            case 1:
                this.f7161u = interfaceC2785n;
                this.f7162v = interfaceC2785n2;
                this.f7163w = d;
                super(2, A7.l.class, "onSelectItem", "SearchResults$onSelectItem(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/lazy/LazyListState;Lcom/stingray/kca/model/KaraokeItem;Lcom/stingray/shared/logs/segment/songQueue/SourceLog;)V", 0);
                break;
            default:
                this.f7161u = interfaceC2785n;
                this.f7162v = interfaceC2785n2;
                this.f7163w = d;
                break;
        }
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f7160t) {
            case 0:
                KaraokeItem karaokeItem = (KaraokeItem) obj;
                A7.m.f(karaokeItem, "p0");
                this.f7161u.invoke(karaokeItem, (F6.b) obj2);
                E.D d = this.f7163w;
                this.f7162v.invoke(Integer.valueOf(d.d.f1443b.i()), Integer.valueOf(d.d.c.i()));
                break;
            default:
                KaraokeItem karaokeItem2 = (KaraokeItem) obj;
                A7.m.f(karaokeItem2, "p0");
                this.f7161u.invoke(karaokeItem2, (F6.b) obj2);
                E.D d10 = this.f7163w;
                this.f7162v.invoke(Integer.valueOf(d10.d.f1443b.i()), Integer.valueOf(d10.d.c.i()));
                break;
        }
        return m7.q.f19267a;
    }
}
