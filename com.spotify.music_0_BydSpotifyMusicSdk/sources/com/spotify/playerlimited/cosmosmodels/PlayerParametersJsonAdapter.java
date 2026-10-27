package com.spotify.playerlimited.cosmosmodels;

import com.spotify.player.legacyplayer.LoggingParameters;
import com.spotify.player.model.PlayOrigin;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import kotlin.collections.EmptySet;
import p.ds2;
import p.pt1;
import p.u4;
import p.v03;
import p.wt1;

/* JADX INFO: loaded from: classes.dex */
public final class PlayerParametersJsonAdapter extends f<PlayerParameters> {
    public final JsonReader.b a;
    public final f b;
    public final f c;
    public final f d;
    public final f e;

    public PlayerParametersJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("context", "options", "play_origin", "logging_params");
        v03.i(bVarA, "of(\"context\", \"options\",…rigin\", \"logging_params\")");
        this.a = bVarA;
        EmptySet emptySet = EmptySet.c;
        f fVarF = oVar.f(wt1.class, emptySet, "context");
        v03.i(fVarF, "moshi.adapter(PlayerCont…a, emptySet(), \"context\")");
        this.b = fVarF;
        f fVarF2 = oVar.f(pt1.class, emptySet, "options");
        v03.i(fVarF2, "moshi.adapter(PlayOption…a, emptySet(), \"options\")");
        this.c = fVarF2;
        f fVarF3 = oVar.f(PlayOrigin.class, emptySet, "origin");
        v03.i(fVarF3, "moshi.adapter(PlayOrigin…va, emptySet(), \"origin\")");
        this.d = fVarF3;
        f fVarF4 = oVar.f(LoggingParameters.class, emptySet, "loggingParams");
        v03.i(fVarF4, "moshi.adapter(LoggingPar…tySet(), \"loggingParams\")");
        this.e = fVarF4;
    }

    @Override // com.squareup.moshi.f
    public final PlayerParameters fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        wt1 wt1Var = null;
        pt1 pt1Var = null;
        PlayOrigin playOrigin = null;
        LoggingParameters loggingParameters = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.a);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                wt1Var = (wt1) this.b.fromJson(jsonReader);
            } else if (iM0 == 1) {
                pt1Var = (pt1) this.c.fromJson(jsonReader);
            } else if (iM0 == 2) {
                playOrigin = (PlayOrigin) this.d.fromJson(jsonReader);
            } else if (iM0 == 3 && (loggingParameters = (LoggingParameters) this.e.fromJson(jsonReader)) == null) {
                JsonDataException jsonDataExceptionW = ds2.w("loggingParams", "logging_params", jsonReader);
                v03.i(jsonDataExceptionW, "unexpectedNull(\"loggingP…\"logging_params\", reader)");
                throw jsonDataExceptionW;
            }
        }
        jsonReader.q();
        if (loggingParameters != null) {
            return new PlayerParameters(wt1Var, pt1Var, playOrigin, loggingParameters);
        }
        JsonDataException jsonDataExceptionO = ds2.o("loggingParams", "logging_params", jsonReader);
        v03.i(jsonDataExceptionO, "missingProperty(\"logging…\"logging_params\", reader)");
        throw jsonDataExceptionO;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, PlayerParameters playerParameters) {
        PlayerParameters playerParameters2 = playerParameters;
        v03.j(mVar, "writer");
        if (playerParameters2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("context");
        this.b.toJson(mVar, playerParameters2.a);
        mVar.J0("options");
        this.c.toJson(mVar, playerParameters2.b);
        mVar.J0("play_origin");
        this.d.toJson(mVar, playerParameters2.c);
        mVar.J0("logging_params");
        this.e.toJson(mVar, playerParameters2.d);
        mVar.X();
    }

    public final String toString() {
        return u4.b(38, "GeneratedJsonAdapter(PlayerParameters)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
