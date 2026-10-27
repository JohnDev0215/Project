package g4;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* JADX INFO: renamed from: g4.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1298b extends WeakReference {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final q f14852a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f14853b;
    public x c;

    public C1298b(q qVar, r rVar, ReferenceQueue referenceQueue) {
        super(rVar, referenceQueue);
        A4.h.c(qVar, "Argument must not be null");
        this.f14852a = qVar;
        boolean z6 = rVar.l;
        this.c = null;
        this.f14853b = z6;
    }
}
