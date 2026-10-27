package p;

import com.spotify.connectivity.connectiontype.DeferUntilConnected;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class g80 implements ObservableTransformer {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ g80(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // io.reactivex.rxjava3.core.ObservableTransformer
    public final ObservableSource apply(Observable observable) {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                return ((DeferUntilConnected) obj).lambda$apply$3(observable);
            default:
                return com.spotify.connectivity.sessionstate.DeferUntilConnected.lambda$apply$1((Observable) obj, observable);
        }
    }
}
