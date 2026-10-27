package p;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class dj0 implements io.reactivex.rxjava3.functions.a {
    public final /* synthetic */ int c;
    public final /* synthetic */ com.spotify.eventsender.disk.a e;

    public /* synthetic */ dj0(com.spotify.eventsender.disk.a aVar, int i) {
        this.c = i;
        this.e = aVar;
    }

    @Override // io.reactivex.rxjava3.functions.a
    public final void run() {
        int i = this.c;
        com.spotify.eventsender.disk.a aVar = this.e;
        switch (i) {
            case 0:
                aVar.f.accept(Boolean.TRUE);
                ((u11) aVar.c).getClass();
                break;
            default:
                ((u11) aVar.c).getClass();
                break;
        }
    }
}
