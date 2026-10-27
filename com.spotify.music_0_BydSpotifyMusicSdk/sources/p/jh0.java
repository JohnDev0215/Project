package p;

import com.google.common.base.Optional;
import com.spotify.player.esperanto.proto.EsContext$Context;
import com.spotify.player.esperanto.proto.EsContextPage$ContextPage;
import com.spotify.player.esperanto.proto.EsContextPlayerOptions$ContextPlayerOptionOverrides;
import com.spotify.player.esperanto.proto.EsOptional$OptionalBoolean;
import com.spotify.player.esperanto.proto.EsOptional$OptionalInt64;
import com.spotify.player.esperanto.proto.EsPlay$PlayRequest;
import com.spotify.player.esperanto.proto.EsPlayOptions$PlayOptions;
import com.spotify.player.esperanto.proto.EsPlayOrigin$PlayOrigin;
import com.spotify.player.esperanto.proto.EsPreparePlay$PreparePlayRequest;
import com.spotify.player.esperanto.proto.EsPreparePlayOptions$PreparePlayOptions;
import com.spotify.player.esperanto.proto.EsRestrictions$Restrictions;
import com.spotify.player.esperanto.proto.EsSkipToTrack$SkipToTrack;
import com.spotify.player.model.AudioStream;
import com.spotify.player.model.Context;
import com.spotify.player.model.ContextPage;
import com.spotify.player.model.ContextTrack;
import com.spotify.player.model.PlayOrigin;
import com.spotify.player.model.Restrictions;
import com.spotify.player.model.Suppressions;
import com.spotify.player.model.command.PlayCommand;
import com.spotify.player.model.command.options.LoggingParams;
import com.spotify.player.model.command.options.PlayOperation;
import com.spotify.player.model.command.options.PlayOptions;
import com.spotify.player.model.command.options.PlayTrigger;
import com.spotify.player.model.command.options.PlayerOptionOverrides;
import com.spotify.player.model.command.options.PrefetchLevel;
import com.spotify.player.model.command.options.PreparePlayOptions;
import com.spotify.player.model.command.options.SkipToTrack;
import io.reactivex.rxjava3.core.Single;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes.dex */
public final class jh0 {
    public final vz a;
    public final yc1 b;

    public jh0(vz vzVar, yc1 yc1Var) {
        v03.j(vzVar, "playerClient");
        v03.j(yc1Var, "loggingParamsFactory");
        this.a = vzVar;
        this.b = yc1Var;
    }

    public final Single a(PlayCommand playCommand) {
        EsPlayOptions$PlayOptions.Trigger trigger;
        EsPlayOptions$PlayOptions.Operation operation;
        EsPreparePlayOptions$PreparePlayOptions.AudioStream audioStream;
        EsPreparePlayOptions$PreparePlayOptions.PrefetchLevel prefetchLevel;
        fg0 fg0VarH = EsPlay$PlayRequest.h();
        mg0 mg0VarH = EsPreparePlay$PreparePlayRequest.h();
        Context contextB = playCommand.b();
        v03.i(contextB, "command.context()");
        nf0 nf0VarK = EsContext$Context.k();
        if (contextB.e().d()) {
            Object objC = contextB.e().c();
            v03.i(objC, "context.pages().get()");
            Iterable<ContextPage> iterable = (Iterable) objC;
            ArrayList arrayList = new ArrayList(lu.W(iterable));
            for (ContextPage contextPage : iterable) {
                v03.j(contextPage, "page");
                pf0 pf0VarJ = EsContextPage$ContextPage.j();
                if (contextPage.e().d()) {
                    Object objC2 = contextPage.e().c();
                    v03.i(objC2, "page.tracks().get()");
                    Iterable iterable2 = (Iterable) objC2;
                    ArrayList arrayList2 = new ArrayList(lu.W(iterable2));
                    Iterator it = iterable2.iterator();
                    while (it.hasNext()) {
                        arrayList2.add(b00.b((ContextTrack) it.next()));
                    }
                    pf0VarJ.d(arrayList2);
                    pf0VarJ.f(false);
                } else {
                    pf0VarJ.f(true);
                }
                pf0VarJ.e(contextPage.b());
                pf0VarJ.h(contextPage.d());
                pf0VarJ.g(contextPage.c());
                com.google.protobuf.i iVarBuild = pf0VarJ.build();
                v03.i(iVarBuild, "newBuilder().apply {\n   …xtPageUrl()\n    }.build()");
                arrayList.add((EsContextPage$ContextPage) iVarBuild);
            }
            nf0VarK.d(arrayList);
            nf0VarK.f(false);
        } else {
            nf0VarK.f(true);
        }
        nf0VarK.e(contextB.d());
        nf0VarK.h(contextB.h());
        nf0VarK.j(contextB.i());
        if (contextB.f().d()) {
            Object objC3 = contextB.f().c();
            v03.i(objC3, "context.restrictions().get()");
            Restrictions restrictions = (Restrictions) objC3;
            sg0 sg0VarZ = EsRestrictions$Restrictions.Z();
            sg0VarZ.h(restrictions.f());
            sg0VarZ.r(restrictions.n());
            sg0VarZ.s(restrictions.o());
            sg0VarZ.k(restrictions.h());
            sg0VarZ.j(restrictions.g());
            sg0VarZ.w(restrictions.s());
            sg0VarZ.v(restrictions.r());
            sg0VarZ.x(restrictions.t());
            sg0VarZ.y(restrictions.u());
            sg0VarZ.z(restrictions.v());
            sg0VarZ.t(restrictions.p());
            sg0VarZ.d(restrictions.b());
            sg0VarZ.g(restrictions.e());
            sg0VarZ.A(restrictions.w());
            sg0VarZ.l(restrictions.i());
            sg0VarZ.f(restrictions.d());
            sg0VarZ.e(restrictions.c());
            sg0VarZ.p(restrictions.m());
            sg0VarZ.o(restrictions.l());
            sg0VarZ.n(restrictions.k());
            sg0VarZ.m(restrictions.j());
            sg0VarZ.B(restrictions.x());
            sg0VarZ.u(restrictions.q());
            com.google.protobuf.i iVarBuild2 = sg0VarZ.build();
            v03.i(iVarBuild2, "newBuilder().apply {\n   …dReasons())\n    }.build()");
            nf0VarK.g((EsRestrictions$Restrictions) iVarBuild2);
        }
        com.google.protobuf.i iVarBuild3 = nf0VarK.build();
        v03.i(iVarBuild3, "newBuilder().apply {\n   …)\n        }\n    }.build()");
        mg0VarH.d((EsContext$Context) iVarBuild3);
        PlayOrigin playOriginF = playCommand.f();
        v03.i(playOriginF, "command.playOrigin()");
        gg0 gg0VarV = EsPlayOrigin$PlayOrigin.v();
        gg0VarV.g(playOriginF.f());
        gg0VarV.h(playOriginF.g());
        gg0VarV.l(playOriginF.k());
        gg0VarV.f(playOriginF.d());
        gg0VarV.j(playOriginF.h());
        gg0VarV.e(playOriginF.c());
        gg0VarV.k(playOriginF.i());
        gg0VarV.d(playOriginF.e());
        com.google.protobuf.i iVarBuild4 = gg0VarV.build();
        v03.i(iVarBuild4, "newBuilder().apply {\n   …eClasses())\n    }.build()");
        mg0VarH.f((EsPlayOrigin$PlayOrigin) iVarBuild4);
        if (playCommand.d().d()) {
            Object objC4 = playCommand.d().c();
            v03.i(objC4, "command.options().get()");
            PreparePlayOptions preparePlayOptions = (PreparePlayOptions) objC4;
            com.spotify.player.esperanto.proto.e eVarR = EsPreparePlayOptions$PreparePlayOptions.r();
            if (preparePlayOptions.g().d()) {
                Object objC5 = preparePlayOptions.g().c();
                v03.i(objC5, "preparePlayOptions.playbackId().get()");
                eVarR.k(vr2.q((String) objC5));
            }
            if (preparePlayOptions.a().d()) {
                Object objC6 = preparePlayOptions.a().c();
                v03.i(objC6, "preparePlayOptions.alwaysPlaySomething().get()");
                eVarR.f(((Boolean) objC6).booleanValue());
            }
            if (preparePlayOptions.l().d()) {
                Object objC7 = preparePlayOptions.l().c();
                v03.i(objC7, "preparePlayOptions.skipTo().get()");
                SkipToTrack skipToTrack = (SkipToTrack) objC7;
                dh0 dh0VarJ = EsSkipToTrack$SkipToTrack.j();
                dh0VarJ.e((String) skipToTrack.d().g(""));
                if (skipToTrack.c().d()) {
                    cg0 cg0VarH = EsOptional$OptionalInt64.h();
                    Object objC8 = skipToTrack.c().c();
                    v03.i(objC8, "skipToTrack.pageIndex().get()");
                    cg0VarH.d(((Number) objC8).longValue());
                    dh0VarJ.d((EsOptional$OptionalInt64) cg0VarH.build());
                }
                dh0VarJ.g((String) skipToTrack.f().g(""));
                dh0VarJ.h((String) skipToTrack.g().g(""));
                if (skipToTrack.e().d()) {
                    cg0 cg0VarH2 = EsOptional$OptionalInt64.h();
                    Object objC9 = skipToTrack.e().c();
                    v03.i(objC9, "skipToTrack.trackIndex().get()");
                    cg0VarH2.d(((Number) objC9).longValue());
                    dh0VarJ.f((EsOptional$OptionalInt64) cg0VarH2.build());
                }
                com.google.protobuf.i iVarBuild5 = dh0VarJ.build();
                v03.i(iVarBuild5, "newBuilder().apply {\n   …)\n        }\n    }.build()");
                eVarR.p((EsSkipToTrack$SkipToTrack) iVarBuild5);
            }
            if (preparePlayOptions.j().d()) {
                cg0 cg0VarH3 = EsOptional$OptionalInt64.h();
                Object objC10 = preparePlayOptions.j().c();
                v03.i(objC10, "preparePlayOptions.seekTo().get()");
                cg0VarH3.d(((Number) objC10).longValue());
                eVarR.n((EsOptional$OptionalInt64) cg0VarH3.build());
            }
            if (preparePlayOptions.e().d()) {
                Object objC11 = preparePlayOptions.e().c();
                v03.i(objC11, "preparePlayOptions.initiallyPaused().get()");
                eVarR.h(((Boolean) objC11).booleanValue());
            }
            if (preparePlayOptions.n().d()) {
                Object objC12 = preparePlayOptions.n().c();
                v03.i(objC12, "preparePlayOptions.systemInitiated().get()");
                eVarR.r(((Boolean) objC12).booleanValue());
            }
            if (preparePlayOptions.h().d()) {
                Object objC13 = preparePlayOptions.h().c();
                v03.i(objC13, "preparePlayOptions.playerOptionsOverride().get()");
                PlayerOptionOverrides playerOptionOverrides = (PlayerOptionOverrides) objC13;
                sf0 sf0VarH = EsContextPlayerOptions$ContextPlayerOptionOverrides.h();
                if (playerOptionOverrides.d().d()) {
                    bg0 bg0VarF = EsOptional$OptionalBoolean.f();
                    Object objC14 = playerOptionOverrides.d().c();
                    v03.i(objC14, "overrides.shufflingContext().get()");
                    bg0VarF.d(((Boolean) objC14).booleanValue());
                    sf0VarH.f((EsOptional$OptionalBoolean) bg0VarF.build());
                }
                if (playerOptionOverrides.b().d()) {
                    bg0 bg0VarF2 = EsOptional$OptionalBoolean.f();
                    Object objC15 = playerOptionOverrides.b().c();
                    v03.i(objC15, "overrides.repeatingContext().get()");
                    bg0VarF2.d(((Boolean) objC15).booleanValue());
                    sf0VarH.d((EsOptional$OptionalBoolean) bg0VarF2.build());
                }
                if (playerOptionOverrides.c().d()) {
                    bg0 bg0VarF3 = EsOptional$OptionalBoolean.f();
                    Object objC16 = playerOptionOverrides.c().c();
                    v03.i(objC16, "overrides.repeatingTrack().get()");
                    bg0VarF3.d(((Boolean) objC16).booleanValue());
                    sf0VarH.e((EsOptional$OptionalBoolean) bg0VarF3.build());
                }
                com.google.protobuf.i iVarBuild6 = sf0VarH.build();
                v03.i(iVarBuild6, "newBuilder().apply {\n   …)\n        }\n    }.build()");
                eVarR.l((EsContextPlayerOptions$ContextPlayerOptionOverrides) iVarBuild6);
            }
            if (preparePlayOptions.m().d()) {
                eVarR.d(((Suppressions) preparePlayOptions.m().c()).b());
            }
            if (preparePlayOptions.i().d()) {
                PrefetchLevel prefetchLevel2 = (PrefetchLevel) preparePlayOptions.i().c();
                int i = prefetchLevel2 == null ? -1 : bx1.a[prefetchLevel2.ordinal()];
                if (i == -1 || i == 1) {
                    prefetchLevel = EsPreparePlayOptions$PreparePlayOptions.PrefetchLevel.NONE;
                } else {
                    if (i != 2) {
                        throw new NoWhenBranchMatchedException();
                    }
                    prefetchLevel = EsPreparePlayOptions$PreparePlayOptions.PrefetchLevel.MEDIA;
                }
                eVarR.m(prefetchLevel);
            }
            if (preparePlayOptions.b().d()) {
                AudioStream audioStream2 = (AudioStream) preparePlayOptions.b().c();
                int i2 = audioStream2 == null ? -1 : bx1.b[audioStream2.ordinal()];
                if (i2 == -1 || i2 == 1) {
                    audioStream = EsPreparePlayOptions$PreparePlayOptions.AudioStream.DEFAULT;
                } else {
                    if (i2 != 2) {
                        throw new NoWhenBranchMatchedException();
                    }
                    audioStream = EsPreparePlayOptions$PreparePlayOptions.AudioStream.ALARM;
                }
                eVarR.g(audioStream);
            }
            if (preparePlayOptions.k().d()) {
                eVarR.o((String) preparePlayOptions.k().c());
            }
            if (preparePlayOptions.f().d()) {
                eVarR.j((String) preparePlayOptions.f().c());
            }
            eVarR.e(preparePlayOptions.d());
            com.google.protobuf.i iVarBuild7 = eVarR.build();
            v03.i(iVarBuild7, "newBuilder().apply {\n   …Override())\n    }.build()");
            mg0VarH.e((EsPreparePlayOptions$PreparePlayOptions) iVarBuild7);
        }
        fg0VarH.f((EsPreparePlay$PreparePlayRequest) mg0VarH.build());
        if (playCommand.e().d()) {
            Object objC17 = playCommand.e().c();
            v03.i(objC17, "command.playOptions().get()");
            PlayOptions playOptions = (PlayOptions) objC17;
            com.spotify.player.esperanto.proto.d dVarG = EsPlayOptions$PlayOptions.g();
            if (playOptions.c().d()) {
                PlayOperation playOperation = (PlayOperation) playOptions.c().c();
                int i3 = playOperation == null ? -1 : qt1.a[playOperation.ordinal()];
                if (i3 == -1 || i3 == 1) {
                    operation = EsPlayOptions$PlayOptions.Operation.REPLACE;
                } else if (i3 == 2) {
                    operation = EsPlayOptions$PlayOptions.Operation.ENQUEUE;
                } else {
                    if (i3 != 3) {
                        throw new NoWhenBranchMatchedException();
                    }
                    operation = EsPlayOptions$PlayOptions.Operation.PUSH;
                }
                dVarG.d(operation);
            }
            if (playOptions.d().d()) {
                PlayTrigger playTrigger = (PlayTrigger) playOptions.d().c();
                int i4 = playTrigger == null ? -1 : qt1.b[playTrigger.ordinal()];
                if (i4 == -1 || i4 == 1) {
                    trigger = EsPlayOptions$PlayOptions.Trigger.IMMEDIATELY;
                } else if (i4 == 2) {
                    trigger = EsPlayOptions$PlayOptions.Trigger.ADVANCED_PAST_TRACK;
                } else {
                    if (i4 != 3) {
                        throw new NoWhenBranchMatchedException();
                    }
                    trigger = EsPlayOptions$PlayOptions.Trigger.ADVANCED_PAST_CONTEXT;
                }
                dVarG.e(trigger);
            }
            com.google.protobuf.i iVarBuild8 = dVarG.build();
            v03.i(iVarBuild8, "newBuilder().apply {\n   …}\n        }\n    }.build()");
            fg0VarH.e((EsPlayOptions$PlayOptions) iVarBuild8);
        }
        Optional optionalC = playCommand.c();
        v03.i(optionalC, "command.loggingParams()");
        LoggingParams loggingParamsA = this.b.a(optionalC);
        v03.i(loggingParamsA, "loggingParamsFactory.dec…(command.loggingParams())");
        fg0VarH.d(ux2.p(loggingParamsA));
        com.google.protobuf.i iVarBuild9 = fg0VarH.build();
        v03.i(iVarBuild9, "newBuilder().apply {\n   …ams()))\n        }.build()");
        vz vzVar = this.a;
        vzVar.getClass();
        Single<R> map = vzVar.callSingle("spotify.player.esperanto.proto.ContextPlayer", "Play", (EsPlay$PlayRequest) iVarBuild9).map(uz.o);
        v03.i(map, "callSingle(\"spotify.play…     }\n                })");
        Single map2 = map.map(zb0.l);
        v03.i(map2, "playerClient.Play(playCo…::commandResultFromProto)");
        return map2;
    }
}
