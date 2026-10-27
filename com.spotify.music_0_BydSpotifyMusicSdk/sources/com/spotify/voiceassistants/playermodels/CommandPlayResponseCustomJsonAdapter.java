package com.spotify.voiceassistants.playermodels;

import com.spotify.player.model.Context;
import com.spotify.player.model.PlayOrigin;
import com.spotify.player.model.command.options.PreparePlayOptions;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import kotlin.collections.EmptySet;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.ds2;
import p.ly0;
import p.u4;
import p.v03;
import p.zl2;

/* JADX INFO: loaded from: classes.dex */
public final class CommandPlayResponseCustomJsonAdapter extends f<CommandPlayResponse> {
    private final ContextJsonAdapter contextAdapter;
    private final o moshi;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;
    private final PlayOriginJsonAdapter playOriginAdapter;
    private final PreparePlayOptionsJsonAdapter preparePlayOptionsAdapter;
    private final f<String> stringAdapter;

    public CommandPlayResponseCustomJsonAdapter() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public final o getMoshi() {
        return this.moshi;
    }

    public String toString() {
        return u4.b(41, "GeneratedJsonAdapter(CommandPlayResponse)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ CommandPlayResponseCustomJsonAdapter(o oVar, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            oVar = new o.a().d();
            v03.i(oVar, "Builder().build()");
        }
        this(oVar);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    @ly0
    public CommandPlayResponse fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String strFromJson = null;
        String strFromJson2 = null;
        String strFromJson3 = null;
        Context contextFromJson = null;
        PreparePlayOptions preparePlayOptionsFromJson = null;
        PlayOrigin playOriginFromJson = null;
        while (jsonReader.hasNext()) {
            switch (jsonReader.M0(this.options)) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    break;
                case 0:
                    strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                    break;
                case 1:
                    strFromJson2 = this.nullableStringAdapter.fromJson(jsonReader);
                    break;
                case 2:
                    strFromJson3 = this.stringAdapter.fromJson(jsonReader);
                    if (strFromJson3 == null) {
                        JsonDataException jsonDataExceptionW = ds2.w("endpoint", "endpoint", jsonReader);
                        v03.i(jsonDataExceptionW, "unexpectedNull(\n        … reader\n                )");
                        throw jsonDataExceptionW;
                    }
                    break;
                    break;
                case 3:
                    contextFromJson = this.contextAdapter.nullSafe().fromJson(jsonReader);
                    break;
                case 4:
                    preparePlayOptionsFromJson = this.preparePlayOptionsAdapter.nullSafe().fromJson(jsonReader);
                    break;
                case 5:
                    playOriginFromJson = this.playOriginAdapter.nullSafe().fromJson(jsonReader);
                    break;
            }
        }
        jsonReader.q();
        if (strFromJson3 != null) {
            return new CommandPlayResponse(strFromJson, strFromJson2, strFromJson3, contextFromJson, preparePlayOptionsFromJson, playOriginFromJson);
        }
        JsonDataException jsonDataExceptionO = ds2.o("endpoint", "endpoint", jsonReader);
        v03.i(jsonDataExceptionO, "missingProperty(\"endpoint\", \"endpoint\", reader)");
        throw jsonDataExceptionO;
    }

    @Override // com.squareup.moshi.f
    @zl2
    public void toJson(m mVar, CommandPlayResponse commandPlayResponse) {
        v03.j(mVar, "writer");
        if (commandPlayResponse == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("req_id");
        this.nullableStringAdapter.toJson(mVar, commandPlayResponse.getRequestId());
        mVar.J0("error");
        this.nullableStringAdapter.toJson(mVar, commandPlayResponse.getError());
        mVar.J0("endpoint");
        this.stringAdapter.toJson(mVar, commandPlayResponse.getEndpoint());
        mVar.J0("context");
        this.contextAdapter.toJson(mVar, commandPlayResponse.getContext());
        mVar.J0("options");
        this.preparePlayOptionsAdapter.toJson(mVar, commandPlayResponse.getPlayOptions());
        mVar.J0("play_origin");
        this.playOriginAdapter.toJson(mVar, commandPlayResponse.getPlayOrigin());
        mVar.X();
    }

    public CommandPlayResponseCustomJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        this.moshi = oVar;
        JsonReader.b bVarA = JsonReader.b.a("req_id", "error", "endpoint", "context", "options", "play_origin");
        v03.i(bVarA, "of(\n        \"req_id\", \"e…ons\", \"play_origin\"\n    )");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<String> fVarF = oVar.f(String.class, emptySet, "requestId");
        v03.i(fVarF, "moshi.adapter(\n        S…ySet(), \"requestId\"\n    )");
        this.nullableStringAdapter = fVarF;
        f<String> fVarF2 = oVar.f(String.class, emptySet, "endpoint");
        v03.i(fVarF2, "moshi.adapter(\n        S…\n        \"endpoint\"\n    )");
        this.stringAdapter = fVarF2;
        this.contextAdapter = new ContextJsonAdapter();
        this.preparePlayOptionsAdapter = new PreparePlayOptionsJsonAdapter();
        this.playOriginAdapter = new PlayOriginJsonAdapter();
    }
}
