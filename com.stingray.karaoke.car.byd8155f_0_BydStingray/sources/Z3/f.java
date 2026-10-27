package z3;

import android.graphics.Bitmap;
import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class f extends AbstractC2410c {
    public i l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public K3.a f24387m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public K3.i f24388n;
    public c o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Bitmap f24389p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public /* synthetic */ Object f24390q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ i f24391r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f24392s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(i iVar, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f24391r = iVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f24390q = obj;
        this.f24392s |= Integer.MIN_VALUE;
        return i.a(this.f24391r, null, 0, this);
    }
}
