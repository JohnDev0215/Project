package p;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.spotify.player.esperanto.proto.EsAddToQueueRequest$AddToQueueRequest;
import com.spotify.player.esperanto.proto.EsGetQueueRequest$GetQueueRequest;
import com.spotify.player.esperanto.proto.EsSetQueueRequest$SetQueueRequest;
import com.spotify.player.model.ContextTrack;
import com.spotify.player.model.command.AddToQueueCommand;
import com.spotify.player.model.command.SetQueueCommand;
import com.spotify.player.model.command.options.CommandOptions;
import com.spotify.player.model.command.options.LoggingParams;
import com.spotify.webapi.service.models.Search;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class wh0 {
    public final vz a;
    public final yc1 b;
    public final io.reactivex.rxjava3.internal.operators.flowable.h0 c;

    public wh0(vz vzVar, yc1 yc1Var) {
        v03.j(vzVar, "playerClient");
        v03.j(yc1Var, "loggingParamsFactory");
        this.a = vzVar;
        this.b = yc1Var;
        EsGetQueueRequest$GetQueueRequest esGetQueueRequest$GetQueueRequestF = EsGetQueueRequest$GetQueueRequest.f();
        v03.i(esGetQueueRequest$GetQueueRequestF, "getDefaultInstance()");
        Observable<R> map = vzVar.callStream("spotify.player.esperanto.proto.ContextPlayer", "GetQueue", esGetQueueRequest$GetQueueRequestF).map(uz.l);
        v03.i(map, "callStream(\"spotify.play…     }\n                })");
        Flowable flowable = map.map(uh0.c).toFlowable(BackpressureStrategy.e);
        flowable.getClass();
        io.reactivex.rxjava3.internal.functions.k.c(1, "bufferSize");
        io.reactivex.rxjava3.internal.operators.flowable.l0 l0Var = new io.reactivex.rxjava3.internal.operators.flowable.l0();
        int i = io.reactivex.rxjava3.internal.operators.flowable.n0.n;
        AtomicReference atomicReference = new AtomicReference();
        this.c = new io.reactivex.rxjava3.internal.operators.flowable.h0(new io.reactivex.rxjava3.internal.operators.flowable.n0(new io.reactivex.rxjava3.internal.operators.flowable.m0(atomicReference, l0Var), flowable, atomicReference, l0Var));
    }

    public final Single a(ContextTrack contextTrack) {
        v03.j(contextTrack, Search.Type.TRACK);
        AddToQueueCommand addToQueueCommandA = AddToQueueCommand.a(contextTrack);
        lf0 lf0VarH = EsAddToQueueRequest$AddToQueueRequest.h();
        if (addToQueueCommandA.c().d()) {
            Object objC = addToQueueCommandA.c().c();
            v03.i(objC, "command.options().get()");
            lf0VarH.e(ci1.n((CommandOptions) objC));
        }
        Optional optionalB = addToQueueCommandA.b();
        v03.i(optionalB, "command.loggingParams()");
        LoggingParams loggingParamsA = this.b.a(optionalB);
        v03.i(loggingParamsA, "loggingParamsFactory.dec…(command.loggingParams())");
        lf0VarH.d(ux2.p(loggingParamsA));
        ContextTrack contextTrackD = addToQueueCommandA.d();
        v03.i(contextTrackD, "command.track()");
        lf0VarH.f(b00.b(contextTrackD));
        com.google.protobuf.i iVarBuild = lf0VarH.build();
        v03.i(iVarBuild, "requestBuilder.build()");
        vz vzVar = this.a;
        vzVar.getClass();
        Single<R> map = vzVar.callSingle("spotify.player.esperanto.proto.ContextPlayer", "AddToQueue", (EsAddToQueueRequest$AddToQueueRequest) iVarBuild).map(uz.e);
        v03.i(map, "callSingle(\"spotify.play…     }\n                })");
        Single map2 = map.map(th0.c);
        v03.i(map2, "playerClient.AddToQueue(…::commandResultFromProto)");
        return map2;
    }

    public final Single b(SetQueueCommand setQueueCommand) {
        wg0 wg0VarJ = EsSetQueueRequest$SetQueueRequest.j();
        if (setQueueCommand.d().d()) {
            Object objC = setQueueCommand.d().c();
            v03.i(objC, "command.options().get()");
            wg0VarJ.g(ci1.n((CommandOptions) objC));
        }
        try {
            String strF = setQueueCommand.f();
            v03.i(strF, "command.queueRevision()");
            wg0VarJ.h(Long.parseLong(strF));
            Optional optionalB = setQueueCommand.b();
            v03.i(optionalB, "command.loggingParams()");
            LoggingParams loggingParamsA = this.b.a(optionalB);
            v03.i(loggingParamsA, "loggingParamsFactory.dec…(command.loggingParams())");
            wg0VarJ.f(ux2.p(loggingParamsA));
            ImmutableList immutableListC = setQueueCommand.c();
            v03.i(immutableListC, "command.nextTracks()");
            ArrayList arrayList = new ArrayList(lu.W(immutableListC));
            Iterator<E> it = immutableListC.iterator();
            while (it.hasNext()) {
                arrayList.add(b00.c((ContextTrack) it.next()));
            }
            wg0VarJ.d(arrayList);
            ImmutableList immutableListE = setQueueCommand.e();
            v03.i(immutableListE, "command.prevTracks()");
            ArrayList arrayList2 = new ArrayList(lu.W(immutableListE));
            Iterator<E> it2 = immutableListE.iterator();
            while (it2.hasNext()) {
                arrayList2.add(b00.c((ContextTrack) it2.next()));
            }
            wg0VarJ.e(arrayList2);
            com.google.protobuf.i iVarBuild = wg0VarJ.build();
            v03.i(iVarBuild, "requestBuilder.build()");
            vz vzVar = this.a;
            vzVar.getClass();
            Single<R> map = vzVar.callSingle("spotify.player.esperanto.proto.ContextPlayer", "SetQueue", (EsSetQueueRequest$SetQueueRequest) iVarBuild).map(uz.s);
            v03.i(map, "callSingle(\"spotify.play…     }\n                })");
            Single map2 = map.map(vh0.c);
            v03.i(map2, "playerClient.SetQueue(re…::commandResultFromProto)");
            return map2;
        } catch (NumberFormatException unused) {
            Single singleJust = Single.just(new uu("Invalid revision"));
            v03.i(singleJust, "just(CommandResult.failure(\"Invalid revision\"))");
            return singleJust;
        }
    }
}
