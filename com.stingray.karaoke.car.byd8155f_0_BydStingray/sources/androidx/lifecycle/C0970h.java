package androidx.lifecycle;

import s7.AbstractC2410c;

/* JADX INFO: renamed from: androidx.lifecycle.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0970h extends AbstractC2410c {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0971i f11990m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f11991n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0970h(C0971i c0971i, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f11990m = c0971i;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.l = obj;
        this.f11991n |= Integer.MIN_VALUE;
        this.f11990m.j(this);
        return m7.q.f19267a;
    }
}
