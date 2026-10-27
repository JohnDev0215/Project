package p;

/* JADX INFO: loaded from: classes.dex */
public final class s00 implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ com.spotify.core.corefullsessionservice.a e;

    public /* synthetic */ s00(com.spotify.core.corefullsessionservice.a aVar, int i) {
        this.c = i;
        this.e = aVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        com.spotify.core.corefullsessionservice.a aVar = this.e;
        switch (i) {
            case 0:
                aVar.r = aVar.a();
                break;
            default:
                aVar.c();
                break;
        }
    }
}
