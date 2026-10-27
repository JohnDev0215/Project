package Q5;

import com.stingray.kca.model.KaraokeItem;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class A1 extends A7.j implements InterfaceC2782k {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ int f6250t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f6251u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2785n f6252v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ E.D f6253w;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public A1(InterfaceC2782k interfaceC2782k, InterfaceC2785n interfaceC2785n, E.D d, int i7) {
        super(1, A7.l.class, "onMore", "SearchResults$onMore(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/lazy/LazyListState;Lcom/stingray/kca/model/KaraokeItem;)V", 0);
        this.f6250t = i7;
        switch (i7) {
            case 1:
                this.f6251u = interfaceC2782k;
                this.f6252v = interfaceC2785n;
                this.f6253w = d;
                super(1, A7.l.class, "onMore", "SearchResults$onMore(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/lazy/LazyListState;Lcom/stingray/kca/model/KaraokeItem;)V", 0);
                break;
            default:
                this.f6251u = interfaceC2782k;
                this.f6252v = interfaceC2785n;
                this.f6253w = d;
                break;
        }
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f6250t) {
            case 0:
                KaraokeItem karaokeItem = (KaraokeItem) obj;
                A7.m.f(karaokeItem, "p0");
                this.f6251u.b(karaokeItem);
                E.D d = this.f6253w;
                this.f6252v.invoke(Integer.valueOf(d.d.f1443b.i()), Integer.valueOf(d.d.c.i()));
                break;
            default:
                KaraokeItem karaokeItem2 = (KaraokeItem) obj;
                A7.m.f(karaokeItem2, "p0");
                this.f6251u.b(karaokeItem2);
                E.D d10 = this.f6253w;
                this.f6252v.invoke(Integer.valueOf(d10.d.f1443b.i()), Integer.valueOf(d10.d.c.i()));
                break;
        }
        return m7.q.f19267a;
    }
}
