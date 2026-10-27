package p;

import com.spotify.connectivity.connectiontype.DeferUntilConnected;
import io.reactivex.rxjava3.core.Observable;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class h80 implements io.reactivex.rxjava3.functions.o {
    public final /* synthetic */ int c;
    public final /* synthetic */ Observable e;

    public /* synthetic */ h80(Observable observable, int i) {
        this.c = i;
        this.e = observable;
    }

    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        int i = this.c;
        Observable observable = this.e;
        switch (i) {
            case 0:
                return DeferUntilConnected.lambda$apply$2(observable, (Boolean) obj);
            case 1:
                return observable.startWithItem((Map) obj);
            default:
                return com.spotify.connectivity.sessionstate.DeferUntilConnected.lambda$apply$0(observable, (Boolean) obj);
        }
    }
}
