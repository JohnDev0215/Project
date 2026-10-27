package p;

import com.spotify.music.R;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class od1 implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ pd1 e;

    public /* synthetic */ od1(pd1 pd1Var, int i) {
        this.c = i;
        this.e = pd1Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        pd1 pd1Var = this.e;
        switch (i) {
            case 0:
                pd1Var.a.e(R.string.login_error_general_login, true);
                break;
            default:
                pd1Var.a.c(true, false);
                break;
        }
    }
}
