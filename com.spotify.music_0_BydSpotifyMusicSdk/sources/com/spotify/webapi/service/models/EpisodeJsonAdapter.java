package com.spotify.webapi.service.models;

import com.spotify.connectivity.productstate.RxProductState;
import com.spotify.webapi.service.models.Search;
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
public final class EpisodeJsonAdapter extends f<Episode> {
    private final f<Integer> intAdapter;
    private final f<Boolean> nullableBooleanAdapter;
    private final f<List<Image>> nullableListOfImageAdapter;
    private final f<ResumePoint> nullableResumePointAdapter;
    private final f<ShowSimple> nullableShowSimpleAdapter;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public EpisodeJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a(Search.Type.SHOW, "audio_preview_url", "description", "duration_ms", "explicit", "href", "id", "images", "is_playable", "name", "release_date", "resume_point", "uri", RxProductState.Keys.KEY_TYPE);
        v03.i(bVarA, "of(\"show\", \"audio_previe…me_point\", \"uri\", \"type\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<ShowSimple> fVarF = oVar.f(ShowSimple.class, emptySet, Search.Type.SHOW);
        v03.i(fVarF, "moshi.adapter(ShowSimple…java, emptySet(), \"show\")");
        this.nullableShowSimpleAdapter = fVarF;
        f<String> fVarF2 = oVar.f(String.class, emptySet, "audioPreviewUrl");
        v03.i(fVarF2, "moshi.adapter(String::cl…Set(), \"audioPreviewUrl\")");
        this.nullableStringAdapter = fVarF2;
        f<Integer> fVarF3 = oVar.f(Integer.TYPE, emptySet, "durationMs");
        v03.i(fVarF3, "moshi.adapter(Int::class…et(),\n      \"durationMs\")");
        this.intAdapter = fVarF3;
        f<Boolean> fVarF4 = oVar.f(Boolean.class, emptySet, "explicit");
        v03.i(fVarF4, "moshi.adapter(Boolean::c…, emptySet(), \"explicit\")");
        this.nullableBooleanAdapter = fVarF4;
        f<List<Image>> fVarF5 = oVar.f(zo2.j(List.class, Image.class), emptySet, "images");
        v03.i(fVarF5, "moshi.adapter(Types.newP…ptySet(),\n      \"images\")");
        this.nullableListOfImageAdapter = fVarF5;
        f<ResumePoint> fVarF6 = oVar.f(ResumePoint.class, emptySet, "resumePoint");
        v03.i(fVarF6, "moshi.adapter(ResumePoin…mptySet(), \"resumePoint\")");
        this.nullableResumePointAdapter = fVarF6;
    }

    public String toString() {
        return u4.b(29, "GeneratedJsonAdapter(Episode)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public Episode fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        ShowSimple showSimpleFromJson = null;
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
        boolean z13 = false;
        String strFromJson8 = null;
        while (jsonReader.hasNext()) {
            String str = strFromJson7;
            switch (jsonReader.M0(this.options)) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    break;
                case 0:
                    showSimpleFromJson = this.nullableShowSimpleAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z = true;
                    continue;
                case 1:
                    strFromJson8 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z2 = true;
                    continue;
                case 2:
                    strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z3 = true;
                    continue;
                case 3:
                    numFromJson = this.intAdapter.fromJson(jsonReader);
                    if (numFromJson == null) {
                        JsonDataException jsonDataExceptionW = ds2.w("durationMs", "duration_ms", jsonReader);
                        v03.i(jsonDataExceptionW, "unexpectedNull(\"duration…   \"duration_ms\", reader)");
                        throw jsonDataExceptionW;
                    }
                    break;
                case 4:
                    boolFromJson = this.nullableBooleanAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z4 = true;
                    continue;
                case 5:
                    strFromJson2 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z5 = true;
                    continue;
                case 6:
                    strFromJson3 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z6 = true;
                    continue;
                case 7:
                    listFromJson = this.nullableListOfImageAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z7 = true;
                    continue;
                case 8:
                    boolFromJson2 = this.nullableBooleanAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z8 = true;
                    continue;
                case 9:
                    strFromJson4 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z9 = true;
                    continue;
                case 10:
                    strFromJson5 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z10 = true;
                    continue;
                case 11:
                    resumePointFromJson = this.nullableResumePointAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z11 = true;
                    continue;
                case 12:
                    strFromJson6 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z12 = true;
                    continue;
                case 13:
                    strFromJson7 = this.nullableStringAdapter.fromJson(jsonReader);
                    z13 = true;
                    continue;
            }
            strFromJson7 = str;
        }
        String str2 = strFromJson7;
        jsonReader.q();
        Episode episode = new Episode();
        if (z) {
            episode.show = showSimpleFromJson;
        }
        if (z2) {
            episode.audioPreviewUrl = strFromJson8;
        }
        if (z3) {
            episode.description = strFromJson;
        }
        episode.durationMs = numFromJson != null ? numFromJson.intValue() : episode.durationMs;
        if (z4) {
            episode.explicit = boolFromJson;
        }
        if (z5) {
            episode.href = strFromJson2;
        }
        if (z6) {
            episode.id = strFromJson3;
        }
        if (z7) {
            episode.images = listFromJson;
        }
        if (z8) {
            episode.is_playable = boolFromJson2;
        }
        if (z9) {
            episode.name = strFromJson4;
        }
        if (z10) {
            episode.releaseDate = strFromJson5;
        }
        if (z11) {
            episode.resumePoint = resumePointFromJson;
        }
        if (z12) {
            episode.uri = strFromJson6;
        }
        if (z13) {
            episode.type = str2;
        }
        return episode;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, Episode episode) {
        v03.j(mVar, "writer");
        if (episode == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0(Search.Type.SHOW);
        this.nullableShowSimpleAdapter.toJson(mVar, episode.show);
        mVar.J0("audio_preview_url");
        this.nullableStringAdapter.toJson(mVar, episode.audioPreviewUrl);
        mVar.J0("description");
        this.nullableStringAdapter.toJson(mVar, episode.description);
        mVar.J0("duration_ms");
        u4.s(episode.durationMs, this.intAdapter, mVar, "explicit");
        this.nullableBooleanAdapter.toJson(mVar, episode.explicit);
        mVar.J0("href");
        this.nullableStringAdapter.toJson(mVar, episode.href);
        mVar.J0("id");
        this.nullableStringAdapter.toJson(mVar, episode.id);
        mVar.J0("images");
        this.nullableListOfImageAdapter.toJson(mVar, episode.images);
        mVar.J0("is_playable");
        this.nullableBooleanAdapter.toJson(mVar, episode.is_playable);
        mVar.J0("name");
        this.nullableStringAdapter.toJson(mVar, episode.name);
        mVar.J0("release_date");
        this.nullableStringAdapter.toJson(mVar, episode.releaseDate);
        mVar.J0("resume_point");
        this.nullableResumePointAdapter.toJson(mVar, episode.resumePoint);
        mVar.J0("uri");
        this.nullableStringAdapter.toJson(mVar, episode.uri);
        mVar.J0(RxProductState.Keys.KEY_TYPE);
        this.nullableStringAdapter.toJson(mVar, episode.type);
        mVar.X();
    }
}
