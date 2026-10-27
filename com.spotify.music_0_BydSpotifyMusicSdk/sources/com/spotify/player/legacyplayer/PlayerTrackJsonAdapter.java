package com.spotify.player.legacyplayer;

import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.util.Map;
import kotlin.collections.EmptySet;
import p.ds2;
import p.u4;
import p.v03;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
public final class PlayerTrackJsonAdapter extends f<PlayerTrack> {
    public final JsonReader.b a;
    public final f b;
    public final f c;
    public final f d;

    public PlayerTrackJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("uri", "uid", "album_uri", "artist_uri", "provider", "metadata");
        v03.i(bVarA, "of(\"uri\", \"uid\", \"album_…, \"provider\", \"metadata\")");
        this.a = bVarA;
        EmptySet emptySet = EmptySet.c;
        f fVarF = oVar.f(String.class, emptySet, "uri");
        v03.i(fVarF, "moshi.adapter(String::cl… emptySet(),\n      \"uri\")");
        this.b = fVarF;
        f fVarF2 = oVar.f(String.class, emptySet, "uid");
        v03.i(fVarF2, "moshi.adapter(String::cl…\n      emptySet(), \"uid\")");
        this.c = fVarF2;
        f fVarF3 = oVar.f(zo2.j(Map.class, String.class, String.class), emptySet, "metadata");
        v03.i(fVarF3, "moshi.adapter(Types.newP…, emptySet(), \"metadata\")");
        this.d = fVarF3;
    }

    @Override // com.squareup.moshi.f
    public final PlayerTrack fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        Map map = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.a);
            f fVar = this.c;
            switch (iM0) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    break;
                case 0:
                    str = (String) this.b.fromJson(jsonReader);
                    if (str == null) {
                        JsonDataException jsonDataExceptionW = ds2.w("uri", "uri", jsonReader);
                        v03.i(jsonDataExceptionW, "unexpectedNull(\"uri\", \"uri\", reader)");
                        throw jsonDataExceptionW;
                    }
                    break;
                    break;
                case 1:
                    str2 = (String) fVar.fromJson(jsonReader);
                    break;
                case 2:
                    str3 = (String) fVar.fromJson(jsonReader);
                    break;
                case 3:
                    str4 = (String) fVar.fromJson(jsonReader);
                    break;
                case 4:
                    str5 = (String) fVar.fromJson(jsonReader);
                    break;
                case 5:
                    map = (Map) this.d.fromJson(jsonReader);
                    break;
            }
        }
        jsonReader.q();
        if (str != null) {
            return new PlayerTrack(str, str2, str3, str4, str5, map);
        }
        JsonDataException jsonDataExceptionO = ds2.o("uri", "uri", jsonReader);
        v03.i(jsonDataExceptionO, "missingProperty(\"uri\", \"uri\", reader)");
        throw jsonDataExceptionO;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, PlayerTrack playerTrack) {
        PlayerTrack playerTrack2 = playerTrack;
        v03.j(mVar, "writer");
        if (playerTrack2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("uri");
        this.b.toJson(mVar, playerTrack2.c);
        mVar.J0("uid");
        String str = playerTrack2.e;
        f fVar = this.c;
        fVar.toJson(mVar, str);
        mVar.J0("album_uri");
        fVar.toJson(mVar, playerTrack2.k);
        mVar.J0("artist_uri");
        fVar.toJson(mVar, playerTrack2.l);
        mVar.J0("provider");
        fVar.toJson(mVar, playerTrack2.m);
        mVar.J0("metadata");
        this.d.toJson(mVar, playerTrack2.n);
        mVar.X();
    }

    public final String toString() {
        return u4.b(33, "GeneratedJsonAdapter(PlayerTrack)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
