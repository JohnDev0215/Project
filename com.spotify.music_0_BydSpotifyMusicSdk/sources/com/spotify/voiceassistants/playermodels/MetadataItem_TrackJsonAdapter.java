package com.spotify.voiceassistants.playermodels;

import com.spotify.voiceassistants.playermodels.MetadataItem;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import kotlin.collections.EmptySet;
import p.ds2;
import p.u4;
import p.v03;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
public final class MetadataItem_TrackJsonAdapter extends f<MetadataItem.Track> {
    private volatile Constructor<MetadataItem.Track> constructorRef;
    private final f<List<Image>> listOfImageAdapter;
    private final f<Boolean> nullableBooleanAdapter;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;
    private final f<String> stringAdapter;

    public MetadataItem_TrackJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("uri", "artwork", "track_name", "explicit", "artist_name");
        v03.i(bVarA, "of(\"uri\", \"artwork\", \"tr…explicit\", \"artist_name\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<String> fVarF = oVar.f(String.class, emptySet, "uri");
        v03.i(fVarF, "moshi.adapter(String::cl… emptySet(),\n      \"uri\")");
        this.stringAdapter = fVarF;
        f<List<Image>> fVarF2 = oVar.f(zo2.j(List.class, Image.class), emptySet, "artwork");
        v03.i(fVarF2, "moshi.adapter(Types.newP…tySet(),\n      \"artwork\")");
        this.listOfImageAdapter = fVarF2;
        f<Boolean> fVarF3 = oVar.f(Boolean.class, emptySet, "explicit");
        v03.i(fVarF3, "moshi.adapter(Boolean::c…, emptySet(), \"explicit\")");
        this.nullableBooleanAdapter = fVarF3;
        f<String> fVarF4 = oVar.f(String.class, emptySet, "artistName");
        v03.i(fVarF4, "moshi.adapter(String::cl…emptySet(), \"artistName\")");
        this.nullableStringAdapter = fVarF4;
    }

    public String toString() {
        return u4.b(40, "GeneratedJsonAdapter(MetadataItem.Track)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public MetadataItem.Track fromJson(JsonReader jsonReader) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        int i = -1;
        String strFromJson = null;
        List<Image> listFromJson = null;
        String strFromJson2 = null;
        Boolean boolFromJson = null;
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
                    JsonDataException jsonDataExceptionW3 = ds2.w("trackName", "track_name", jsonReader);
                    v03.i(jsonDataExceptionW3, "unexpectedNull(\"trackNam…    \"track_name\", reader)");
                    throw jsonDataExceptionW3;
                }
            } else if (iM0 == 3) {
                boolFromJson = this.nullableBooleanAdapter.fromJson(jsonReader);
                i &= -9;
            } else if (iM0 == 4) {
                strFromJson3 = this.nullableStringAdapter.fromJson(jsonReader);
                i &= -17;
            }
        }
        jsonReader.q();
        if (i == -25) {
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
                return new MetadataItem.Track(strFromJson, listFromJson, strFromJson2, boolFromJson, strFromJson3);
            }
            JsonDataException jsonDataExceptionO3 = ds2.o("trackName", "track_name", jsonReader);
            v03.i(jsonDataExceptionO3, "missingProperty(\"trackName\", \"track_name\", reader)");
            throw jsonDataExceptionO3;
        }
        Constructor<MetadataItem.Track> declaredConstructor = this.constructorRef;
        int i2 = 7;
        if (declaredConstructor == null) {
            declaredConstructor = MetadataItem.Track.class.getDeclaredConstructor(String.class, List.class, String.class, Boolean.class, String.class, Integer.TYPE, ds2.c);
            this.constructorRef = declaredConstructor;
            v03.i(declaredConstructor, "MetadataItem.Track::clas…his.constructorRef = it }");
            i2 = 7;
        }
        Object[] objArr = new Object[i2];
        if (strFromJson == null) {
            JsonDataException jsonDataExceptionO4 = ds2.o("uri", "uri", jsonReader);
            v03.i(jsonDataExceptionO4, "missingProperty(\"uri\", \"uri\", reader)");
            throw jsonDataExceptionO4;
        }
        objArr[0] = strFromJson;
        if (listFromJson == null) {
            JsonDataException jsonDataExceptionO5 = ds2.o("artwork", "artwork", jsonReader);
            v03.i(jsonDataExceptionO5, "missingProperty(\"artwork\", \"artwork\", reader)");
            throw jsonDataExceptionO5;
        }
        objArr[1] = listFromJson;
        if (strFromJson2 == null) {
            JsonDataException jsonDataExceptionO6 = ds2.o("trackName", "track_name", jsonReader);
            v03.i(jsonDataExceptionO6, "missingProperty(\"trackName\", \"track_name\", reader)");
            throw jsonDataExceptionO6;
        }
        objArr[2] = strFromJson2;
        objArr[3] = boolFromJson;
        objArr[4] = strFromJson3;
        objArr[5] = Integer.valueOf(i);
        objArr[6] = null;
        MetadataItem.Track trackNewInstance = declaredConstructor.newInstance(objArr);
        v03.i(trackNewInstance, "localConstructor.newInst…torMarker */ null\n      )");
        return trackNewInstance;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, MetadataItem.Track track) {
        v03.j(mVar, "writer");
        if (track == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("uri");
        this.stringAdapter.toJson(mVar, track.getUri());
        mVar.J0("artwork");
        this.listOfImageAdapter.toJson(mVar, track.getArtwork());
        mVar.J0("track_name");
        this.stringAdapter.toJson(mVar, track.getTrackName());
        mVar.J0("explicit");
        this.nullableBooleanAdapter.toJson(mVar, track.getExplicit());
        mVar.J0("artist_name");
        this.nullableStringAdapter.toJson(mVar, track.getArtistName());
        mVar.X();
    }
}
