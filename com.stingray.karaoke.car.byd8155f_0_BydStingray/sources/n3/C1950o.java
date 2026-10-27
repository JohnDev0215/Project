package n3;

import n7.C1995j;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: n3.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1950o extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ A7.v f19884m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ A7.v f19885n;
    public final /* synthetic */ C1930E o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ boolean f19886p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ C1995j f19887q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1950o(A7.v vVar, A7.v vVar2, C1930E c1930e, boolean z6, C1995j c1995j) {
        super(1);
        this.f19884m = vVar;
        this.f19885n = vVar2;
        this.o = c1930e;
        this.f19886p = z6;
        this.f19887q = c1995j;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        C1945j c1945j = (C1945j) obj;
        A7.m.f(c1945j, "entry");
        this.f19884m.l = true;
        this.f19885n.l = true;
        this.o.s(c1945j, this.f19886p, this.f19887q);
        return m7.q.f19267a;
    }
}
