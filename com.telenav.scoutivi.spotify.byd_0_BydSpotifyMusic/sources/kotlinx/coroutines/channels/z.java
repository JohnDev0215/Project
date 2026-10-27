package kotlinx.coroutines.channels;

import kotlinx.coroutines.channels.g0;

/* JADX INFO: compiled from: Produce.kt */
/* JADX INFO: loaded from: classes.dex */
public class z<E> extends m<E> implements a0<E> {
    public z(k5.g gVar, l<E> lVar) {
        super(gVar, lVar, true);
    }

    @Override // kotlinx.coroutines.channels.a0
    public /* bridge */ /* synthetic */ g0 getChannel() {
        return getChannel();
    }

    @Override // kotlinx.coroutines.a, kotlinx.coroutines.s1, kotlinx.coroutines.l1
    public boolean isActive() {
        return super.isActive();
    }

    @Override // kotlinx.coroutines.a
    protected void onCancelled(Throwable th, boolean z6) {
        if (get_channel().close(th) || z6) {
            return;
        }
        kotlinx.coroutines.g0.a(getContext(), th);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.a
    public void onCompleted(g5.r rVar) {
        g0.a.a(get_channel(), null, 1, null);
    }
}
