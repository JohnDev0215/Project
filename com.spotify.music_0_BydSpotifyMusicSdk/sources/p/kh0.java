package p;

import com.google.common.base.Optional;
import com.spotify.player.esperanto.proto.EsPause$PauseRequest;
import com.spotify.player.esperanto.proto.EsPauseresumeOrigin$PauseResumeOrigin;
import com.spotify.player.esperanto.proto.EsResume$ResumeRequest;
import com.spotify.player.esperanto.proto.EsSeekTo$SeekToRequest;
import com.spotify.player.esperanto.proto.EsStop$StopRequest;
import com.spotify.player.model.PauseResumeOrigin;
import com.spotify.player.model.command.PauseCommand;
import com.spotify.player.model.command.ResumeCommand;
import com.spotify.player.model.command.SeekToCommand;
import com.spotify.player.model.command.SkipToNextTrackCommand;
import com.spotify.player.model.command.SkipToPrevTrackCommand;
import com.spotify.player.model.command.StopCommand;
import com.spotify.player.model.command.options.CommandOptions;
import com.spotify.player.model.command.options.LoggingParams;
import io.reactivex.rxjava3.core.Single;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes.dex */
public final class kh0 {
    public final /* synthetic */ int a;
    public final /* synthetic */ nh0 b;

    public /* synthetic */ kh0(nh0 nh0Var, int i) {
        this.a = i;
        this.b = nh0Var;
    }

    public final Single a(Object obj) {
        EsSeekTo$SeekToRequest.Relative relative;
        int i = this.a;
        nh0 nh0Var = this.b;
        switch (i) {
            case 0:
                au1 au1Var = (au1) obj;
                v03.j(au1Var, "it");
                nh0Var.getClass();
                SeekToCommand seekToCommandA = SeekToCommand.a(au1Var.i);
                com.spotify.player.esperanto.proto.g gVarI = EsSeekTo$SeekToRequest.i();
                if (seekToCommandA.c().d()) {
                    Object objC = seekToCommandA.c().c();
                    v03.i(objC, "command.options().get()");
                    gVarI.e(ci1.n((CommandOptions) objC));
                }
                Optional optionalB = seekToCommandA.b();
                v03.i(optionalB, "command.loggingParams()");
                LoggingParams loggingParamsA = nh0Var.b.a(optionalB);
                v03.i(loggingParamsA, "loggingParamsFactory.dec…(command.loggingParams())");
                gVarI.d(ux2.p(loggingParamsA));
                gVarI.f(seekToCommandA.e());
                if (seekToCommandA.d().d()) {
                    Object objC2 = seekToCommandA.d().c();
                    v03.i(objC2, "command.relative().get()");
                    int iOrdinal = ((SeekToCommand.Relative) objC2).ordinal();
                    if (iOrdinal == 0) {
                        relative = EsSeekTo$SeekToRequest.Relative.BEGINNING;
                    } else if (iOrdinal == 1) {
                        relative = EsSeekTo$SeekToRequest.Relative.END;
                    } else {
                        if (iOrdinal != 2) {
                            throw new NoWhenBranchMatchedException();
                        }
                        relative = EsSeekTo$SeekToRequest.Relative.CURRENT;
                    }
                    gVarI.g(relative);
                }
                com.google.protobuf.i iVarBuild = gVarI.build();
                v03.i(iVarBuild, "requestBuilder.build()");
                vz vzVar = nh0Var.a;
                vzVar.getClass();
                Single<R> map = vzVar.callSingle("spotify.player.esperanto.proto.ContextPlayer", "SeekTo", (EsSeekTo$SeekToRequest) iVarBuild).map(uz.q);
                v03.i(map, "callSingle(\"spotify.play…     }\n                })");
                Single map2 = map.map(ro.e);
                v03.i(map2, "playerClient.SeekTo(requ…::commandResultFromProto)");
                return map2;
            case 1:
                u4.z(obj);
                v03.j(null, "it");
                throw null;
            case 2:
                u4.z(obj);
                v03.j(null, "it");
                throw null;
            case 3:
                u4.z(obj);
                v03.j(null, "it");
                StopCommand stopCommandA = StopCommand.a();
                nh0Var.getClass();
                eh0 eh0VarF = EsStop$StopRequest.f();
                Optional optionalB2 = stopCommandA.b();
                v03.i(optionalB2, "command.loggingParams()");
                LoggingParams loggingParamsA2 = nh0Var.b.a(optionalB2);
                v03.i(loggingParamsA2, "loggingParamsFactory.dec…(command.loggingParams())");
                eh0VarF.d(ux2.p(loggingParamsA2));
                com.google.protobuf.i iVarBuild2 = eh0VarF.build();
                v03.i(iVarBuild2, "requestBuilder.build()");
                vz vzVar2 = nh0Var.a;
                vzVar2.getClass();
                Single<R> map3 = vzVar2.callSingle("spotify.player.esperanto.proto.ContextPlayer", "Stop", (EsStop$StopRequest) iVarBuild2).map(uz.y);
                v03.i(map3, "callSingle(\"spotify.play…     }\n                })");
                Single map4 = map3.map(mh0.c);
                v03.i(map4, "playerClient.Stop(reques…::commandResultFromProto)");
                return map4;
            case 4:
                zt1 zt1Var = (zt1) obj;
                v03.j(zt1Var, "it");
                String str = zt1Var.i;
                v03.i(str, "it.featureIdentifier()");
                nh0Var.getClass();
                ResumeCommand.Builder builderA = ResumeCommand.a();
                CommandOptions.Builder builderA2 = CommandOptions.a();
                builderA2.b(zt1Var.j);
                builderA.b(builderA2.a());
                builderA.c(PauseResumeOrigin.a(str).a());
                ResumeCommand resumeCommandA = builderA.a();
                v03.i(resumeCommandA, "builder()\n              …\n                .build()");
                tg0 tg0VarH = EsResume$ResumeRequest.h();
                if (resumeCommandA.c().d()) {
                    Object objC3 = resumeCommandA.c().c();
                    v03.i(objC3, "command.options().get()");
                    tg0VarH.e(ci1.n((CommandOptions) objC3));
                }
                Optional optionalB3 = resumeCommandA.b();
                v03.i(optionalB3, "command.loggingParams()");
                LoggingParams loggingParamsA3 = nh0Var.b.a(optionalB3);
                v03.i(loggingParamsA3, "loggingParamsFactory.dec…(command.loggingParams())");
                tg0VarH.d(ux2.p(loggingParamsA3));
                if (resumeCommandA.d().d()) {
                    Object objC4 = resumeCommandA.d().c();
                    v03.i(objC4, "command.resumeOrigin().get()");
                    eg0 eg0VarF = EsPauseresumeOrigin$PauseResumeOrigin.f();
                    eg0VarF.d(((PauseResumeOrigin) objC4).b());
                    com.google.protobuf.i iVarBuild3 = eg0VarF.build();
                    v03.i(iVarBuild3, "newBuilder().apply {\n   …dentifier()\n    }.build()");
                    tg0VarH.f((EsPauseresumeOrigin$PauseResumeOrigin) iVarBuild3);
                }
                com.google.protobuf.i iVarBuild4 = tg0VarH.build();
                v03.i(iVarBuild4, "requestBuilder.build()");
                vz vzVar3 = nh0Var.a;
                vzVar3.getClass();
                Single<R> map5 = vzVar3.callSingle("spotify.player.esperanto.proto.ContextPlayer", "Resume", (EsResume$ResumeRequest) iVarBuild4).map(uz.f197p);
                v03.i(map5, "callSingle(\"spotify.play…     }\n                })");
                Single map6 = map5.map(po.e);
                v03.i(map6, "playerClient.Resume(requ…::commandResultFromProto)");
                return map6;
            case 5:
                u4.z(obj);
                v03.j(null, "it");
                throw null;
            case 6:
                yt1 yt1Var = (yt1) obj;
                v03.j(yt1Var, "it");
                String str2 = yt1Var.i;
                v03.i(str2, "it.featureIdentifier()");
                nh0Var.getClass();
                PauseCommand.Builder builderA3 = PauseCommand.a();
                CommandOptions.Builder builderA4 = CommandOptions.a();
                builderA4.b(yt1Var.j);
                builderA3.b(builderA4.a());
                builderA3.c(PauseResumeOrigin.a(str2).a());
                PauseCommand pauseCommandA = builderA3.a();
                v03.i(pauseCommandA, "builder()\n              …\n                .build()");
                dg0 dg0VarH = EsPause$PauseRequest.h();
                if (pauseCommandA.c().d()) {
                    Object objC5 = pauseCommandA.c().c();
                    v03.i(objC5, "command.options().get()");
                    dg0VarH.e(ci1.n((CommandOptions) objC5));
                }
                Optional optionalB4 = pauseCommandA.b();
                v03.i(optionalB4, "command.loggingParams()");
                LoggingParams loggingParamsA4 = nh0Var.b.a(optionalB4);
                v03.i(loggingParamsA4, "loggingParamsFactory.dec…(command.loggingParams())");
                dg0VarH.d(ux2.p(loggingParamsA4));
                if (pauseCommandA.d().d()) {
                    Object objC6 = pauseCommandA.d().c();
                    v03.i(objC6, "command.pauseOrigin().get()");
                    eg0 eg0VarF2 = EsPauseresumeOrigin$PauseResumeOrigin.f();
                    eg0VarF2.d(((PauseResumeOrigin) objC6).b());
                    com.google.protobuf.i iVarBuild5 = eg0VarF2.build();
                    v03.i(iVarBuild5, "newBuilder().apply {\n   …dentifier()\n    }.build()");
                    dg0VarH.f((EsPauseresumeOrigin$PauseResumeOrigin) iVarBuild5);
                }
                com.google.protobuf.i iVarBuild6 = dg0VarH.build();
                v03.i(iVarBuild6, "requestBuilder.build()");
                vz vzVar4 = nh0Var.a;
                vzVar4.getClass();
                Single<R> map7 = vzVar4.callSingle("spotify.player.esperanto.proto.ContextPlayer", "Pause", (EsPause$PauseRequest) iVarBuild6).map(uz.n);
                v03.i(map7, "callSingle(\"spotify.play…     }\n                })");
                Single map8 = map7.map(u11.o);
                v03.i(map8, "playerClient.Pause(reque…::commandResultFromProto)");
                return map8;
            case 7:
                u4.z(obj);
                v03.j(null, "it");
                throw null;
            case 8:
                u4.z(obj);
                v03.j(null, "it");
                throw null;
            case 9:
                v03.j((bu1) obj, "it");
                SkipToNextTrackCommand skipToNextTrackCommandB = SkipToNextTrackCommand.b();
                v03.i(skipToNextTrackCommandB, "create()");
                return nh0Var.b(skipToNextTrackCommandB);
            case 10:
                cu1 cu1Var = (cu1) obj;
                v03.j(cu1Var, "it");
                SkipToNextTrackCommand skipToNextTrackCommand = cu1Var.i;
                v03.i(skipToNextTrackCommand, "it.command()");
                return nh0Var.b(skipToNextTrackCommand);
            case 11:
                u4.z(obj);
                v03.j(null, "it");
                SkipToPrevTrackCommand skipToPrevTrackCommandB = SkipToPrevTrackCommand.b();
                v03.i(skipToPrevTrackCommandB, "create()");
                return nh0Var.c(skipToPrevTrackCommandB);
            default:
                du1 du1Var = (du1) obj;
                v03.j(du1Var, "it");
                SkipToPrevTrackCommand skipToPrevTrackCommand = du1Var.i;
                v03.i(skipToPrevTrackCommand, "it.command()");
                return nh0Var.c(skipToPrevTrackCommand);
        }
    }
}
