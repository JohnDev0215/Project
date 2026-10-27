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
public final class MetadataItem_ShowJsonAdapter extends f<MetadataItem.Show> {
    private final f<List<Image>> listOfImageAdapter;
    private final JsonReader.b options;
    private final f<String> stringAdapter;

    public MetadataItem_ShowJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("uri", "artwork", "show_name", "publisher_name");
        v03.i(bVarA, "of(\"uri\", \"artwork\", \"sh…,\n      \"publisher_name\")");
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
        return u4.b(39, "GeneratedJsonAdapter(MetadataItem.Show)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public MetadataItem.Show fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String strFromJson = null;
        List<Image> listFromJson = null;
        String strFromJson2 = null;
        String strFromJson3 = null;
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
            } else if (iM0 == 2) {
                strFromJson2 = this.stringAdapter.fromJson(jsonReader);
                if (strFromJson2 == null) {
                    JsonDataException jsonDataExceptionW3 = ds2.w("showName", "show_name", jsonReader);
                    v03.i(jsonDataExceptionW3, "unexpectedNull(\"showName…     \"show_name\", reader)");
                    throw jsonDataExceptionW3;
                }
            } else if (iM0 == 3 && (strFromJson3 = this.stringAdapter.fromJson(jsonReader)) == null) {
                JsonDataException jsonDataExceptionW4 = ds2.w("publisherName", "publisher_name", jsonReader);
                v03.i(jsonDataExceptionW4, "unexpectedNull(\"publishe…\"publisher_name\", reader)");
                throw jsonDataExceptionW4;
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
        if (strFromJson2 == null) {
            JsonDataException jsonDataExceptionO3 = ds2.o("showName", "show_name", jsonReader);
            v03.i(jsonDataExceptionO3, "missingProperty(\"showName\", \"show_name\", reader)");
            throw jsonDataExceptionO3;
        }
        if (strFromJson3 != null) {
            return new MetadataItem.Show(strFromJson, listFromJson, strFromJson2, strFromJson3);
        }
        JsonDataException jsonDataExceptionO4 = ds2.o("publisherName", "publisher_name", jsonReader);
        v03.i(jsonDataExceptionO4, "missingProperty(\"publish…\"publisher_name\", reader)");
        throw jsonDataExceptionO4;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, MetadataItem.Show show) {
        v03.j(mVar, "writer");
        if (show == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("uri");
        this.stringAdapter.toJson(mVar, show.getUri());
        mVar.J0("artwork");
        this.listOfImageAdapter.toJson(mVar, show.getArtwork());
        mVar.J0("show_name");
        this.stringAdapter.toJson(mVar, show.getShowName());
        mVar.J0("publisher_name");
        this.stringAdapter.toJson(mVar, show.getPublisherName());
        mVar.X();
    }
}
