package kotlinx.coroutines.channels;

/* JADX INFO: compiled from: AbstractChannel.kt */
/* JADX INFO: loaded from: classes.dex */
public final class i0<E> extends h0<E> {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final r5.l<E, g5.r> f7569k;

    /* JADX WARN: Multi-variable type inference failed */
    public i0(E e7, kotlinx.coroutines.i<? super g5.r> iVar, r5.l<? super E, g5.r> lVar) {
        super(e7, iVar);
        this.f7569k = lVar;
    }

    @Override // kotlinx.coroutines.internal.o
    public boolean M() {
        if (!super.M()) {
            return false;
        }
        U();
        return true;
    }

    @Override // kotlinx.coroutines.channels.f0
    public void U() {
        kotlinx.coroutines.internal.v.b(this.f7569k, R(), this.f7567j.getContext());
    }
}
