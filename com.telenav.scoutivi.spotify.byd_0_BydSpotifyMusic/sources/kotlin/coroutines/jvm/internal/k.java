package kotlin.coroutines.jvm.internal;

import kotlin.jvm.internal.s;

/* JADX INFO: compiled from: ContinuationImpl.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class k extends j implements kotlin.jvm.internal.h<Object> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f7491e;

    public k(int i7, k5.d<Object> dVar) {
        super(dVar);
        this.f7491e = i7;
    }

    @Override // kotlin.jvm.internal.h
    public int getArity() {
        return this.f7491e;
    }

    @Override // kotlin.coroutines.jvm.internal.a
    public String toString() {
        if (getCompletion() != null) {
            return super.toString();
        }
        String strD = s.d(this);
        kotlin.jvm.internal.l.d(strD, "renderLambdaToString(this)");
        return strD;
    }
}
