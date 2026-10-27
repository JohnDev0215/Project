package p;

import com.spotify.ondemandselector.proto.OndemandResponse;

/* JADX INFO: loaded from: classes.dex */
public final class iq1 implements io.reactivex.rxjava3.functions.o {
    public static final iq1 c = new iq1();

    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        OndemandResponse ondemandResponse = (OndemandResponse) obj;
        v51 v51VarG = ondemandResponse.g();
        v03.i(v51VarG, "it.urisList");
        return new gq1(ou.q0(v51VarG), ondemandResponse.f());
    }
}
