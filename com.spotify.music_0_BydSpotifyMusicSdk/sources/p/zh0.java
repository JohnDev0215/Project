package p;

import com.spotify.player.esperanto.proto.EsGetErrorRequest$GetErrorRequest;
import com.spotify.player.esperanto.proto.EsGetStateRequest$GetStateRequest;
import com.spotify.player.esperanto.proto.EsOptional$OptionalInt64;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

/* JADX INFO: loaded from: classes.dex */
public final class zh0 {
    public final vz a;

    public zh0(vz vzVar) {
        this.a = vzVar;
    }

    public final Observable a() {
        EsGetErrorRequest$GetErrorRequest esGetErrorRequest$GetErrorRequestF = EsGetErrorRequest$GetErrorRequest.f();
        v03.i(esGetErrorRequest$GetErrorRequestF, "getDefaultInstance()");
        vz vzVar = this.a;
        vzVar.getClass();
        Observable<R> map = vzVar.callStream("spotify.player.esperanto.proto.ContextPlayer", "GetError", esGetErrorRequest$GetErrorRequestF).map(uz.k);
        v03.i(map, "callStream(\"spotify.play…     }\n                })");
        Observable map2 = map.map(xh0.c);
        v03.i(map2, "playerClient.GetError(Es…p(::playerErrorFromProto)");
        return map2;
    }

    public final Flowable b() {
        zf0 zf0VarG = EsGetStateRequest$GetStateRequest.g();
        cg0 cg0VarH = EsOptional$OptionalInt64.h();
        long j = 2;
        cg0VarH.d(j);
        zf0VarG.e(cg0VarH);
        cg0 cg0VarH2 = EsOptional$OptionalInt64.h();
        cg0VarH2.d(j);
        zf0VarG.d(cg0VarH2);
        EsGetStateRequest$GetStateRequest esGetStateRequest$GetStateRequest = (EsGetStateRequest$GetStateRequest) zf0VarG.build();
        v03.i(esGetStateRequest$GetStateRequest, "request");
        vz vzVar = this.a;
        vzVar.getClass();
        Observable<R> map = vzVar.callStream("spotify.player.esperanto.proto.ContextPlayer", "GetState", esGetStateRequest$GetStateRequest).map(uz.m);
        v03.i(map, "callStream(\"spotify.play…     }\n                })");
        Flowable flowable = map.map(yh0.c).toFlowable(BackpressureStrategy.e);
        v03.i(flowable, "playerClient.GetState(re…kpressureStrategy.LATEST)");
        return flowable;
    }
}
