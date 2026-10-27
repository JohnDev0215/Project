package p;

import android.content.ContentResolver;
import android.database.ContentObserver;
import com.spotify.connectivity.platformconnectiontype.ConnectivityMonitorImpl;
import com.spotify.connectivity.platformconnectiontype.ConnectivityObserver;
import com.spotify.connectivity.platformconnectiontype.InternetConnectivityObservableImpl;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class vx implements io.reactivex.rxjava3.functions.f {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;

    public /* synthetic */ vx(Object obj, int i, Object obj2) {
        this.a = i;
        this.c = obj;
        this.b = obj2;
    }

    @Override // io.reactivex.rxjava3.functions.f
    public final void cancel() {
        int i = this.a;
        Object obj = this.b;
        Object obj2 = this.c;
        switch (i) {
            case 0:
                ((ConnectivityMonitorImpl) obj2).lambda$getConnectionTypes$1((ConnectivityObserver) obj);
                break;
            case 1:
                ((InternetConnectivityObservableImpl) obj2).lambda$createConnectionType$2((ConnectivityObserver) obj);
                break;
            default:
                ((ContentResolver) obj2).unregisterContentObserver((ContentObserver) obj);
                break;
        }
    }
}
