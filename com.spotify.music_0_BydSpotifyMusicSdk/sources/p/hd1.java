package p;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class hd1 implements io.reactivex.rxjava3.functions.a {
    public final /* synthetic */ int c;
    public final /* synthetic */ com.spotify.automotive.auth.b e;
    public final /* synthetic */ wk2 k;

    public /* synthetic */ hd1(com.spotify.automotive.auth.b bVar, c7 c7Var, int i) {
        this.c = i;
        this.e = bVar;
        this.k = c7Var;
    }

    @Override // io.reactivex.rxjava3.functions.a
    public final void run() {
        int i = this.c;
        wk2 wk2Var = this.k;
        com.spotify.automotive.auth.b bVar = this.e;
        switch (i) {
            case 0:
                bVar.getClass();
                c7 c7Var = (c7) wk2Var;
                c7Var.c("auto_login_at_startup");
                ((b7) bVar.g).b(c7Var.b());
                break;
            default:
                bVar.getClass();
                c7 c7Var2 = (c7) wk2Var;
                c7Var2.c("login_with_credentials");
                ((b7) bVar.g).b(c7Var2.b());
                break;
        }
    }
}
