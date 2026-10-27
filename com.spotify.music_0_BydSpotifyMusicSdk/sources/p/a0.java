package p;

import io.reactivex.rxjava3.core.Notification;
import io.reactivex.rxjava3.core.ObservableEmitter;

/* JADX INFO: loaded from: classes.dex */
public final class a0 implements io.reactivex.rxjava3.functions.a {
    public final /* synthetic */ int c;
    public final Object e;

    public /* synthetic */ a0(int i, Object obj) {
        this.c = i;
        this.e = obj;
    }

    @Override // io.reactivex.rxjava3.functions.a
    public final void run() {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                ((com.spotify.automotive.base.service.a) obj).f.A();
                break;
            case 1:
                ((ObservableEmitter) obj).onComplete();
                break;
            default:
                ((io.reactivex.rxjava3.functions.g) obj).accept(Notification.b);
                break;
        }
    }
}
