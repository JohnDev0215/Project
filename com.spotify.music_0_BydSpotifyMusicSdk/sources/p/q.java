package p;

import android.app.Service;
import com.spotify.automotive.playback.ErrorState;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class q implements io.reactivex.rxjava3.functions.a {
    public final /* synthetic */ int c;
    public final /* synthetic */ Object e;

    public /* synthetic */ q(int i, Object obj) {
        this.c = i;
        this.e = obj;
    }

    @Override // io.reactivex.rxjava3.functions.a
    public final void run() {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                ((vg1) obj).g(null);
                break;
            case 1:
                ((Service) obj).stopSelf();
                break;
            case 2:
                ((hk) obj).c();
                break;
            case 3:
                ((mk) obj).b.b.onNext(ErrorState.VOICE_FAILURE);
                break;
            case 4:
                ((u11) ((vs1) obj).a).getClass();
                break;
            default:
                ax1 ax1Var = (ax1) obj;
                ax1Var.c.remove(ax1Var.a);
                break;
        }
    }
}
