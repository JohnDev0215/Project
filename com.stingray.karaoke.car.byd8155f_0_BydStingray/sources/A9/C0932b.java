package a9;

import s7.AbstractC2410c;

/* JADX INFO: renamed from: a9.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0932b extends AbstractC2410c {
    public C0933c l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f11225m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0933c f11226n;
    public int o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0932b(C0933c c0933c, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f11226n = c0933c;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f11225m = obj;
        this.o |= Integer.MIN_VALUE;
        return this.f11226n.d(this);
    }
}
