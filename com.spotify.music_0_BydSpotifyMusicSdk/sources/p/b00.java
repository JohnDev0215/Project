package p;

import com.google.common.collect.ImmutableMap;
import com.spotify.player.esperanto.proto.EsContextTrack$ContextTrack;
import com.spotify.player.esperanto.proto.EsProvidedTrack$ProvidedTrack;
import com.spotify.player.model.ContextTrack;
import com.spotify.webapi.service.models.Search;

/* JADX INFO: loaded from: classes.dex */
public abstract class b00 {
    public static final ContextTrack a(EsProvidedTrack$ProvidedTrack esProvidedTrack$ProvidedTrack) {
        v03.j(esProvidedTrack$ProvidedTrack, "protoTrack");
        EsContextTrack$ContextTrack esContextTrack$ContextTrackG = esProvidedTrack$ProvidedTrack.g();
        v03.i(esContextTrack$ContextTrackG, "protoTrack.contextTrack");
        ContextTrack.Builder builderA = ContextTrack.a(esContextTrack$ContextTrackG.l());
        String strK = esContextTrack$ContextTrackG.k();
        if (strK != null && strK.length() != 0) {
            builderA.d(esContextTrack$ContextTrackG.k());
        }
        if (esContextTrack$ContextTrackG.i() > 0) {
            builderA.b(esContextTrack$ContextTrackG.j());
        }
        ContextTrack contextTrackA = builderA.a();
        v03.i(contextTrackA, "builder(protoTrack.uri).…)\n        }\n    }.build()");
        ContextTrack.Builder builderD = contextTrackA.d();
        builderD.c(esProvidedTrack$ProvidedTrack.i());
        ContextTrack contextTrackA2 = builderD.a();
        v03.i(contextTrackA2, "contextTrackFromProto(pr…k.provider)\n    }.build()");
        return contextTrackA2;
    }

    public static final EsContextTrack$ContextTrack b(ContextTrack contextTrack) {
        v03.j(contextTrack, Search.Type.TRACK);
        xf0 xf0VarM = EsContextTrack$ContextTrack.m();
        String strF = contextTrack.f();
        if (strF != null && strF.length() != 0) {
            xf0VarM.f(contextTrack.f());
        }
        String strE = contextTrack.e();
        if (strE != null && strE.length() != 0) {
            xf0VarM.e(contextTrack.e());
        }
        ImmutableMap immutableMapB = contextTrack.b();
        if (immutableMapB != null && !immutableMapB.isEmpty()) {
            xf0VarM.d(contextTrack.b());
        }
        com.google.protobuf.i iVarBuild = xf0VarM.build();
        v03.i(iVarBuild, "newBuilder().apply {\n   …)\n        }\n    }.build()");
        return (EsContextTrack$ContextTrack) iVarBuild;
    }

    public static final EsProvidedTrack$ProvidedTrack c(ContextTrack contextTrack) {
        v03.j(contextTrack, Search.Type.TRACK);
        og0 og0VarJ = EsProvidedTrack$ProvidedTrack.j();
        og0VarJ.d(b(contextTrack));
        og0VarJ.e(contextTrack.c());
        com.google.protobuf.i iVarBuild = og0VarJ.build();
        v03.i(iVarBuild, "newBuilder().apply {\n   …provider())\n    }.build()");
        return (EsProvidedTrack$ProvidedTrack) iVarBuild;
    }
}
