package i0;

import s7.AbstractC2410c;

/* JADX INFO: renamed from: i0.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1399c extends AbstractC2410c {
    public ViewOnAttachStateChangeListenerC1400d l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public U8.d f15649m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f15650n;
    public final /* synthetic */ ViewOnAttachStateChangeListenerC1400d o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f15651p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1399c(ViewOnAttachStateChangeListenerC1400d viewOnAttachStateChangeListenerC1400d, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.o = viewOnAttachStateChangeListenerC1400d;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f15650n = obj;
        this.f15651p |= Integer.MIN_VALUE;
        return this.o.a(this);
    }
}
