package p;

import com.google.common.base.Optional;
import com.spotify.player.esperanto.proto.EsOptional$OptionalBoolean;
import com.spotify.player.esperanto.proto.EsSetOptions$SetOptionsRequest;
import com.spotify.player.esperanto.proto.EsSetShufflingContext$SetShufflingContextRequest;
import com.spotify.player.model.command.SetOptionsCommand;
import com.spotify.player.model.command.SetShufflingContextCommand;
import com.spotify.player.model.command.options.CommandOptions;
import com.spotify.player.model.command.options.LoggingParams;
import io.reactivex.rxjava3.core.Single;

/* JADX INFO: loaded from: classes.dex */
public final class sh0 implements fu1 {
    public final vz a;
    public final yc1 b;

    public sh0(vz vzVar, yc1 yc1Var) {
        v03.j(vzVar, "playerClient");
        v03.j(yc1Var, "loggingParamsFactory");
        this.a = vzVar;
        this.b = yc1Var;
    }

    public final Single a(boolean z, boolean z2) {
        SetOptionsCommand.Builder builderA = SetOptionsCommand.a();
        builderA.b(z);
        builderA.c(z2);
        SetOptionsCommand setOptionsCommandA = builderA.a();
        v03.i(setOptionsCommandA, "setOptionsCommand");
        ug0 ug0VarK = EsSetOptions$SetOptionsRequest.k();
        if (setOptionsCommandA.e().d()) {
            bg0 bg0VarF = EsOptional$OptionalBoolean.f();
            Object objC = setOptionsCommandA.e().c();
            v03.i(objC, "command.repeatingContext().get()");
            bg0VarF.d(((Boolean) objC).booleanValue());
            ug0VarK.g((EsOptional$OptionalBoolean) bg0VarF.build());
        }
        if (setOptionsCommandA.f().d()) {
            bg0 bg0VarF2 = EsOptional$OptionalBoolean.f();
            Object objC2 = setOptionsCommandA.f().c();
            v03.i(objC2, "command.repeatingTrack().get()");
            bg0VarF2.d(((Boolean) objC2).booleanValue());
            ug0VarK.h((EsOptional$OptionalBoolean) bg0VarF2.build());
        }
        if (setOptionsCommandA.g().d()) {
            bg0 bg0VarF3 = EsOptional$OptionalBoolean.f();
            Object objC3 = setOptionsCommandA.g().c();
            v03.i(objC3, "command.shufflingContext().get()");
            bg0VarF3.d(((Boolean) objC3).booleanValue());
            ug0VarK.j((EsOptional$OptionalBoolean) bg0VarF3.build());
        }
        if (setOptionsCommandA.d().d()) {
            Object objC4 = setOptionsCommandA.d().c();
            v03.i(objC4, "command.playbackSpeed().get()");
            ug0VarK.f(((Number) objC4).floatValue());
        }
        if (setOptionsCommandA.c().d()) {
            Object objC5 = setOptionsCommandA.c().c();
            v03.i(objC5, "command.options().get()");
            ug0VarK.e(ci1.n((CommandOptions) objC5));
        }
        Optional optionalB = setOptionsCommandA.b();
        v03.i(optionalB, "command.loggingParams()");
        LoggingParams loggingParamsA = this.b.a(optionalB);
        v03.i(loggingParamsA, "loggingParamsFactory.dec…(command.loggingParams())");
        ug0VarK.d(ux2.p(loggingParamsA));
        com.google.protobuf.i iVarBuild = ug0VarK.build();
        v03.i(iVarBuild, "requestBuilder.build()");
        vz vzVar = this.a;
        vzVar.getClass();
        Single<R> map = vzVar.callSingle("spotify.player.esperanto.proto.ContextPlayer", "SetOptions", (EsSetOptions$SetOptionsRequest) iVarBuild).map(uz.r);
        v03.i(map, "callSingle(\"spotify.play…     }\n                })");
        Single map2 = map.map(oh0.c);
        v03.i(map2, "playerClient.SetOptions(…::commandResultFromProto)");
        return map2;
    }

    public final Single b(boolean z) {
        SetShufflingContextCommand setShufflingContextCommandA = SetShufflingContextCommand.a(z);
        zg0 zg0VarH = EsSetShufflingContext$SetShufflingContextRequest.h();
        zg0VarH.f(setShufflingContextCommandA.d());
        if (setShufflingContextCommandA.c().d()) {
            Object objC = setShufflingContextCommandA.c().c();
            v03.i(objC, "command.options().get()");
            zg0VarH.e(ci1.n((CommandOptions) objC));
        }
        Optional optionalB = setShufflingContextCommandA.b();
        v03.i(optionalB, "command.loggingParams()");
        LoggingParams loggingParamsA = this.b.a(optionalB);
        v03.i(loggingParamsA, "loggingParamsFactory.dec…(command.loggingParams())");
        zg0VarH.d(ux2.p(loggingParamsA));
        com.google.protobuf.i iVarBuild = zg0VarH.build();
        v03.i(iVarBuild, "requestBuilder.build()");
        vz vzVar = this.a;
        vzVar.getClass();
        Single<R> map = vzVar.callSingle("spotify.player.esperanto.proto.ContextPlayer", "SetShufflingContext", (EsSetShufflingContext$SetShufflingContextRequest) iVarBuild).map(uz.v);
        v03.i(map, "callSingle(\"spotify.play…     }\n                })");
        Single map2 = map.map(rh0.c);
        v03.i(map2, "playerClient.SetShufflin…::commandResultFromProto)");
        return map2;
    }
}
