package com.spotify.webapi.service.models;

import com.spotify.connectivity.productstate.RxProductState;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.util.List;
import kotlin.collections.EmptySet;
import p.ds2;
import p.u4;
import p.v03;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
public final class EpisodeSimpleJsonAdapter extends f<EpisodeSimple> {
    private final f<Integer> intAdapter;
    private final f<Boolean> nullableBooleanAdapter;
    private final f<List<Image>> nullableListOfImageAdapter;
    private final f<ResumePoint> nullableResumePointAdapter;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public EpisodeSimpleJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("audio_preview_url", "description", "duration_ms", "explicit", "href", "id", "images", "is_playable", "name", "release_date", "resume_point", "uri", RxProductState.Keys.KEY_TYPE);
        v03.i(bVarA, "of(\"audio_preview_url\",\n…me_point\", \"uri\", \"type\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<String> fVarF = oVar.f(String.class, emptySet, "audioPreviewUrl");
        v03.i(fVarF, "moshi.adapter(String::cl…Set(), \"audioPreviewUrl\")");
        this.nullableStringAdapter = fVarF;
        f<Integer> fVarF2 = oVar.f(Integer.TYPE, emptySet, "durationMs");
        v03.i(fVarF2, "moshi.adapter(Int::class…et(),\n      \"durationMs\")");
        this.intAdapter = fVarF2;
        f<Boolean> fVarF3 = oVar.f(Boolean.class, emptySet, "explicit");
        v03.i(fVarF3, "moshi.adapter(Boolean::c…, emptySet(), \"explicit\")");
        this.nullableBooleanAdapter = fVarF3;
        f<List<Image>> fVarF4 = oVar.f(zo2.j(List.class, Image.class), emptySet, "images");
        v03.i(fVarF4, "moshi.adapter(Types.newP…ptySet(),\n      \"images\")");
        this.nullableListOfImageAdapter = fVarF4;
        f<ResumePoint> fVarF5 = oVar.f(ResumePoint.class, emptySet, "resumePoint");
        v03.i(fVarF5, "moshi.adapter(ResumePoin…mptySet(), \"resumePoint\")");
        this.nullableResumePointAdapter = fVarF5;
    }

    public String toString() {
        return u4.b(35, "GeneratedJsonAdapter(EpisodeSimple)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public EpisodeSimple fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String strFromJson = null;
        Integer numFromJson = null;
        Boolean boolFromJson = null;
        String strFromJson2 = null;
        String strFromJson3 = null;
        List<Image> listFromJson = null;
        Boolean boolFromJson2 = null;
        String strFromJson4 = null;
        String strFromJson5 = null;
        ResumePoint resumePointFromJson = null;
        String strFromJson6 = null;
        String strFromJson7 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        boolean z8 = false;
        boolean z9 = false;
        boolean z10 = false;
        boolean z11 = false;
        boolean z12 = false;
        String strFromJson8 = null;
        while (jsonReader.hasNext()) {
            String str = strFromJson7;
            switch (jsonReader.M0(this.options)) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    break;
                case 0:
                    strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z = true;
                    continue;
                case 1:
                    strFromJson8 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z2 = true;
                    continue;
                case 2:
                    numFromJson = this.intAdapter.fromJson(jsonReader);
                    if (numFromJson == null) {
                        JsonDataException jsonDataExceptionW = ds2.w("durationMs", "duration_ms", jsonReader);
                        v03.i(jsonDataExceptionW, "unexpectedNull(\"duration…   \"duration_ms\", reader)");
                        throw jsonDataExceptionW;
                    }
                    break;
                case 3:
                    boolFromJson = this.nullableBooleanAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z3 = true;
                    continue;
                case 4:
                    strFromJson2 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z4 = true;
                    continue;
                case 5:
                    strFromJson3 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z5 = true;
                    continue;
                case 6:
                    listFromJson = this.nullableListOfImageAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z6 = true;
                    continue;
                case 7:
                    boolFromJson2 = this.nullableBooleanAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z7 = true;
                    continue;
                case 8:
                    strFromJson4 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z8 = true;
                    continue;
                case 9:
                    strFromJson5 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z9 = true;
                    continue;
                case 10:
                    resumePointFromJson = this.nullableResumePointAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z10 = true;
                    continue;
                case 11:
                    strFromJson6 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z11 = true;
                    continue;
                case 12:
                    strFromJson7 = this.nullableStringAdapter.fromJson(jsonReader);
                    z12 = true;
                    continue;
            }
            strFromJson7 = str;
        }
        String str2 = strFromJson7;
        jsonReader.q();
        EpisodeSimple episodeSimple = new EpisodeSimple();
        if (z) {
            episodeSimple.audioPreviewUrl = strFromJson;
        }
        if (z2) {
            episodeSimple.description = strFromJson8;
        }
        episodeSimple.durationMs = numFromJson != null ? numFromJson.intValue() : episodeSimple.durationMs;
        if (z3) {
            episodeSimple.explicit = boolFromJson;
        }
        if (z4) {
            episodeSimple.href = strFromJson2;
        }
        if (z5) {
            episodeSimple.id = strFromJson3;
        }
        if (z6) {
            episodeSimple.images = listFromJson;
        }
        if (z7) {
            episodeSimple.is_playable = boolFromJson2;
        }
        if (z8) {
            episodeSimple.name = strFromJson4;
        }
        if (z9) {
            episodeSimple.releaseDate = strFromJson5;
        }
        if (z10) {
            episodeSimple.resumePoint = resumePointFromJson;
        }
        if (z11) {
            episodeSimple.uri = strFromJson6;
        }
        if (z12) {
            episodeSimple.type = str2;
        }
        return episodeSimple;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, EpisodeSimple episodeSimple) {
        v03.j(mVar, "writer");
        if (episodeSimple == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("audio_preview_url");
        this.nullableStringAdapter.toJson(mVar, episodeSimple.audioPreviewUrl);
        mVar.J0("description");
        this.nullableStringAdapter.toJson(mVar, episodeSimple.description);
        mVar.J0("duration_ms");
        u4.s(episodeSimple.durationMs, this.intAdapter, mVar, "explicit");
        this.nullableBooleanAdapter.toJson(mVar, episodeSimple.explicit);
        mVar.J0("href");
        this.nullableStringAdapter.toJson(mVar, episodeSimple.href);
        mVar.J0("id");
        this.nullableStringAdapter.toJson(mVar, episodeSimple.id);
        mVar.J0("images");
        this.nullableListOfImageAdapter.toJson(mVar, episodeSimple.images);
        mVar.J0("is_playable");
        this.nullableBooleanAdapter.toJson(mVar, episodeSimple.is_playable);
        mVar.J0("name");
        this.nullableStringAdapter.toJson(mVar, episodeSimple.name);
        mVar.J0("release_date");
        this.nullableStringAdapter.toJson(mVar, episodeSimple.releaseDate);
        mVar.J0("resume_point");
        this.nullableResumePointAdapter.toJson(mVar, episodeSimple.resumePoint);
        mVar.J0("uri");
        this.nullableStringAdapter.toJson(mVar, episodeSimple.uri);
        mVar.J0(RxProductState.Keys.KEY_TYPE);
        this.nullableStringAdapter.toJson(mVar, episodeSimple.type);
        mVar.X();
    }
}
