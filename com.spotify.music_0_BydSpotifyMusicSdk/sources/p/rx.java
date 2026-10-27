package p;

import com.spotify.automotive.connectivity.ConnectionStateController$ConnectionState;
import com.spotify.connectivity.connectiontype.ConnectionApis;

/* JADX INFO: loaded from: classes.dex */
public final class rx {
    public final jf0 b;
    public final io.reactivex.rxjava3.disposables.a d;
    public final io.reactivex.rxjava3.subjects.b a = io.reactivex.rxjava3.subjects.b.a();
    public ConnectionStateController$ConnectionState c = ConnectionStateController$ConnectionState.c;

    public rx(ConnectionApis connectionApis, jf0 jf0Var, f61 f61Var) {
        io.reactivex.rxjava3.disposables.a aVar = new io.reactivex.rxjava3.disposables.a();
        this.d = aVar;
        this.b = jf0Var;
        aVar.c(connectionApis.getConnectionTypeObservable().subscribe(new rm(this, 2, f61Var), new ed1(19)));
    }
}
