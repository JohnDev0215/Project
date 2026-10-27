package p;

import com.spotify.base.java.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ot implements io.reactivex.rxjava3.functions.a {
    public final /* synthetic */ int c;

    @Override // io.reactivex.rxjava3.functions.a
    public final void run() {
        switch (this.c) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                break;
            default:
                Logger.e("Requesting updated remote configuration data.", new Object[0]);
                break;
        }
    }
}
