package p;

import com.google.common.base.Optional;
import com.spotify.player.esperanto.proto.EsSkipNext$SkipNextRequest;
import com.spotify.player.esperanto.proto.EsSkipPrev$SkipPrevRequest;
import com.spotify.player.model.ContextTrack;
import com.spotify.player.model.command.SkipToNextTrackCommand;
import com.spotify.player.model.command.SkipToPrevTrackCommand;
import com.spotify.player.model.command.options.CommandOptions;
import com.spotify.player.model.command.options.LoggingParams;
import com.spotify.player.model.command.options.SkipToPrevTrackOptions;
import io.reactivex.rxjava3.core.Single;

/* JADX INFO: loaded from: classes.dex */
public final class nh0 implements eu1 {
    public final vz a;
    public final yc1 b;

    public nh0(vz vzVar, yc1 yc1Var) {
        v03.j(vzVar, "playerClient");
        v03.j(yc1Var, "loggingParamsFactory");
        this.a = vzVar;
        this.b = yc1Var;
    }

    public final Single a(zu zuVar) {
        Single singleQ0 = zuVar.q0(new kh0(this, 4), new kh0(this, 6), new kh0(this, 9), new kh0(this, 10), new kh0(this, 12), new kh0(this, 0));
        v03.i(singleQ0, "override fun execute(pla…stop() },\n        )\n    }");
        return singleQ0;
    }

    public final Single b(SkipToNextTrackCommand skipToNextTrackCommand) {
        bh0 bh0VarH = EsSkipNext$SkipNextRequest.h();
        if (skipToNextTrackCommand.d().d()) {
            Object objC = skipToNextTrackCommand.d().c();
            v03.i(objC, "command.options().get()");
            bh0VarH.e(ci1.n((CommandOptions) objC));
        }
        Optional optionalC = skipToNextTrackCommand.c();
        v03.i(optionalC, "command.loggingParams()");
        LoggingParams loggingParamsA = this.b.a(optionalC);
        v03.i(loggingParamsA, "loggingParamsFactory.dec…(command.loggingParams())");
        bh0VarH.d(ux2.p(loggingParamsA));
        if (skipToNextTrackCommand.e().d()) {
            Object objC2 = skipToNextTrackCommand.e().c();
            v03.i(objC2, "command.track().get()");
            bh0VarH.f(b00.b((ContextTrack) objC2));
        }
        com.google.protobuf.i iVarBuild = bh0VarH.build();
        v03.i(iVarBuild, "requestBuilder.build()");
        vz vzVar = this.a;
        vzVar.getClass();
        Single<R> map = vzVar.callSingle("spotify.player.esperanto.proto.ContextPlayer", "SkipNext", (EsSkipNext$SkipNextRequest) iVarBuild).map(uz.w);
        v03.i(map, "callSingle(\"spotify.play…     }\n                })");
        Single map2 = map.map(so.e);
        v03.i(map2, "playerClient.SkipNext(re…::commandResultFromProto)");
        return map2;
    }

    public final Single c(SkipToPrevTrackCommand skipToPrevTrackCommand) {
        ch0 ch0VarI = EsSkipPrev$SkipPrevRequest.i();
        if (skipToPrevTrackCommand.d().d()) {
            if (((SkipToPrevTrackOptions) skipToPrevTrackCommand.d().c()).c() != null) {
                CommandOptions commandOptionsC = ((SkipToPrevTrackOptions) skipToPrevTrackCommand.d().c()).c();
                v03.i(commandOptionsC, "command.options().get().commandOptions()");
                ch0VarI.f(ci1.n(commandOptionsC));
            }
            if (((SkipToPrevTrackOptions) skipToPrevTrackCommand.d().c()).a().d()) {
                Object objC = ((SkipToPrevTrackOptions) skipToPrevTrackCommand.d().c()).a().c();
                v03.i(objC, "command.options().get().allowSeeking().get()");
                ch0VarI.d(((Boolean) objC).booleanValue());
            }
        }
        Optional optionalC = skipToPrevTrackCommand.c();
        v03.i(optionalC, "command.loggingParams()");
        LoggingParams loggingParamsA = this.b.a(optionalC);
        v03.i(loggingParamsA, "loggingParamsFactory.dec…(command.loggingParams())");
        ch0VarI.e(ux2.p(loggingParamsA));
        if (skipToPrevTrackCommand.e().d()) {
            Object objC2 = skipToPrevTrackCommand.e().c();
            v03.i(objC2, "command.track().get()");
            ch0VarI.g(b00.b((ContextTrack) objC2));
        }
        com.google.protobuf.i iVarBuild = ch0VarI.build();
        v03.i(iVarBuild, "requestBuilder.build()");
        vz vzVar = this.a;
        vzVar.getClass();
        Single<R> map = vzVar.callSingle("spotify.player.esperanto.proto.ContextPlayer", "SkipPrev", (EsSkipPrev$SkipPrevRequest) iVarBuild).map(uz.x);
        v03.i(map, "callSingle(\"spotify.play…     }\n                })");
        Single map2 = map.map(lh0.c);
        v03.i(map2, "playerClient.SkipPrev(re…::commandResultFromProto)");
        return map2;
    }
}
