package kotlinx.coroutines.channels;

/* JADX INFO: compiled from: Broadcast.kt */
/* JADX INFO: loaded from: classes.dex */
final class w<E> extends i<E> {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final k5.d<g5.r> f7591g;

    public w(k5.g gVar, g<E> gVar2, r5.p<? super a0<? super E>, ? super k5.d<? super g5.r>, ? extends Object> pVar) {
        super(gVar, gVar2, false);
        this.f7591g = l5.c.b(pVar, this, this);
    }

    @Override // kotlinx.coroutines.channels.i, kotlinx.coroutines.channels.g
    public c0<E> c() {
        c0<E> c0VarC = f().c();
        start();
        return c0VarC;
    }

    @Override // kotlinx.coroutines.a
    protected void onStart() {
        b6.a.a(this.f7591g, this);
    }
}
