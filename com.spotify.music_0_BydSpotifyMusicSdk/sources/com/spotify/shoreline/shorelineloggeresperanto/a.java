package com.spotify.shoreline.shorelineloggeresperanto;

import com.google.protobuf.ByteString;
import com.google.protobuf.i;
import com.spotify.shoreline.esperanto.proto.EsShorelineLogger$SetContextValueRequest;
import com.spotify.shoreline.esperanto.proto.EsShorelineLogger$SetContextValueResponse;
import java.util.ArrayList;
import java.util.Locale;
import p.el;
import p.ln1;
import p.lu;
import p.mt;
import p.ou;
import p.ry0;
import p.u11;
import p.v03;
import p.v51;

/* JADX INFO: loaded from: classes.dex */
public final class a extends ln1 {
    public final u11 g;

    public a(u11 u11Var) {
        this.g = u11Var;
    }

    @Override // p.ln1
    public final EsShorelineLogger$SetContextValueResponse a(EsShorelineLogger$SetContextValueRequest esShorelineLogger$SetContextValueRequest) {
        boolean zI = esShorelineLogger$SetContextValueRequest.i();
        u11 u11Var = this.g;
        if (zI) {
            v51 v51VarG = esShorelineLogger$SetContextValueRequest.f().g();
            v03.i(v51VarG, "request.playbackIds.playbackIdList");
            ou.h0(v51VarG, null, null, null, new ry0() { // from class: com.spotify.shoreline.shorelineloggeresperanto.ShorelineLoggerEsperanto$SetContextValue$1
                {
                    super(1);
                }

                @Override // p.ry0
                public final Object invoke(Object obj) {
                    ByteString byteString = (ByteString) obj;
                    a aVar = this.this$0;
                    v03.i(byteString, "it");
                    aVar.getClass();
                    String strC = el.d.c(byteString.q());
                    v03.i(strC, "base16().encode(this.toByteArray())");
                    String lowerCase = strC.toLowerCase(Locale.ROOT);
                    v03.i(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                    return lowerCase.concat(", ");
                }
            }, 31);
            v51<ByteString> v51VarG2 = esShorelineLogger$SetContextValueRequest.f().g();
            v03.i(v51VarG2, "request.playbackIds.playbackIdList");
            ArrayList arrayList = new ArrayList(lu.W(v51VarG2));
            for (ByteString byteString : v51VarG2) {
                v03.i(byteString, "playbackId");
                String strC = el.d.c(byteString.q());
                v03.i(strC, "base16().encode(this.toByteArray())");
                String lowerCase = strC.toLowerCase(Locale.ROOT);
                v03.i(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                arrayList.add(lowerCase);
            }
            u11Var.getClass();
        } else if (esShorelineLogger$SetContextValueRequest.j()) {
            esShorelineLogger$SetContextValueRequest.g();
            v03.i(esShorelineLogger$SetContextValueRequest.g(), "request.playbackStatus");
            u11Var.getClass();
        } else if (esShorelineLogger$SetContextValueRequest.h()) {
            mt mtVar = mt.r;
            u11Var.getClass();
            v03.j(mtVar, "contextValue");
        }
        i iVarBuild = EsShorelineLogger$SetContextValueResponse.e().build();
        v03.i(iVarBuild, "newBuilder().build()");
        return (EsShorelineLogger$SetContextValueResponse) iVarBuild;
    }
}
