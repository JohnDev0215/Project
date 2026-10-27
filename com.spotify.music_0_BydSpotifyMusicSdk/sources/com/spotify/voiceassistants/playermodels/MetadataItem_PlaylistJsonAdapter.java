package com.spotify.voiceassistants.playermodels;

import com.spotify.voiceassistants.playermodels.MetadataItem;
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
public final class MetadataItem_PlaylistJsonAdapter extends f<MetadataItem.Playlist> {
    private final f<List<Image>> listOfImageAdapter;
    private final JsonReader.b options;
    private final f<String> stringAdapter;

    public MetadataItem_PlaylistJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("uri", "artwork", "playlist_name");
        v03.i(bVarA, "of(\"uri\", \"artwork\", \"playlist_name\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<String> fVarF = oVar.f(String.class, emptySet, "uri");
        v03.i(fVarF, "moshi.adapter(String::cl… emptySet(),\n      \"uri\")");
        this.stringAdapter = fVarF;
        f<List<Image>> fVarF2 = oVar.f(zo2.j(List.class, Image.class), emptySet, "artwork");
        v03.i(fVarF2, "moshi.adapter(Types.newP…tySet(),\n      \"artwork\")");
        this.listOfImageAdapter = fVarF2;
    }

    public String toString() {
        return u4.b(43, "GeneratedJsonAdapter(MetadataItem.Playlist)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public MetadataItem.Playlist fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String strFromJson = null;
        List<Image> listFromJson = null;
        String strFromJson2 = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                strFromJson = this.stringAdapter.fromJson(jsonReader);
                if (strFromJson == null) {
                    JsonDataException jsonDataExceptionW = ds2.w("uri", "uri", jsonReader);
                    v03.i(jsonDataExceptionW, "unexpectedNull(\"uri\", \"uri\", reader)");
                    throw jsonDataExceptionW;
                }
            } else if (iM0 == 1) {
                listFromJson = this.listOfImageAdapter.fromJson(jsonReader);
                if (listFromJson == null) {
                    JsonDataException jsonDataExceptionW2 = ds2.w("artwork", "artwork", jsonReader);
                    v03.i(jsonDataExceptionW2, "unexpectedNull(\"artwork\"…       \"artwork\", reader)");
                    throw jsonDataExceptionW2;
                }
            } else if (iM0 == 2 && (strFromJson2 = this.stringAdapter.fromJson(jsonReader)) == null) {
                JsonDataException jsonDataExceptionW3 = ds2.w("playlistName", "playlist_name", jsonReader);
                v03.i(jsonDataExceptionW3, "unexpectedNull(\"playlist… \"playlist_name\", reader)");
                throw jsonDataExceptionW3;
            }
        }
        jsonReader.q();
        if (strFromJson == null) {
            JsonDataException jsonDataExceptionO = ds2.o("uri", "uri", jsonReader);
            v03.i(jsonDataExceptionO, "missingProperty(\"uri\", \"uri\", reader)");
            throw jsonDataExceptionO;
        }
        if (listFromJson == null) {
            JsonDataException jsonDataExceptionO2 = ds2.o("artwork", "artwork", jsonReader);
            v03.i(jsonDataExceptionO2, "missingProperty(\"artwork\", \"artwork\", reader)");
            throw jsonDataExceptionO2;
        }
        if (strFromJson2 != null) {
            return new MetadataItem.Playlist(strFromJson, listFromJson, strFromJson2);
        }
        JsonDataException jsonDataExceptionO3 = ds2.o("playlistName", "playlist_name", jsonReader);
        v03.i(jsonDataExceptionO3, "missingProperty(\"playlis…ame\",\n            reader)");
        throw jsonDataExceptionO3;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, MetadataItem.Playlist playlist) {
        v03.j(mVar, "writer");
        if (playlist == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("uri");
        this.stringAdapter.toJson(mVar, playlist.getUri());
        mVar.J0("artwork");
        this.listOfImageAdapter.toJson(mVar, playlist.getArtwork());
        mVar.J0("playlist_name");
        this.stringAdapter.toJson(mVar, playlist.getPlaylistName());
        mVar.X();
    }
}
