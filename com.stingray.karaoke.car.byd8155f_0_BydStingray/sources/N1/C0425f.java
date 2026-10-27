package N1;

import java.io.Serializable;
import java.util.Iterator;
import s7.AbstractC2410c;

/* JADX INFO: renamed from: N1.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0425f extends AbstractC2410c {
    public Serializable l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Iterator f4522m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f4523n;
    public int o;

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f4523n = obj;
        this.o |= Integer.MIN_VALUE;
        return com.bumptech.glide.c.D(null, null, this);
    }
}
