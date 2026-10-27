package com.spotify.localfiles.localfiles;

import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import kotlin.collections.EmptySet;
import p.ds2;
import p.u4;
import p.v03;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
public final class LocalTracksResponseJsonAdapter extends f<LocalTracksResponse> {
    private volatile Constructor<LocalTracksResponse> constructorRef;
    private final f<Integer> intAdapter;
    private final f<List<LocalTrack>> listOfLocalTrackAdapter;
    private final JsonReader.b options;

    public LocalTracksResponseJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("rows", "unfilteredLength", "lengthInSeconds");
        v03.i(bVarA, "of(\"rows\", \"unfilteredLe…\n      \"lengthInSeconds\")");
        this.options = bVarA;
        ParameterizedType parameterizedTypeJ = zo2.j(List.class, LocalTrack.class);
        EmptySet emptySet = EmptySet.c;
        f<List<LocalTrack>> fVarF = oVar.f(parameterizedTypeJ, emptySet, "items");
        v03.i(fVarF, "moshi.adapter(Types.newP…     emptySet(), \"items\")");
        this.listOfLocalTrackAdapter = fVarF;
        f<Integer> fVarF2 = oVar.f(Integer.TYPE, emptySet, "unfilteredLength");
        v03.i(fVarF2, "moshi.adapter(Int::class…      \"unfilteredLength\")");
        this.intAdapter = fVarF2;
    }

    public String toString() {
        return u4.b(41, "GeneratedJsonAdapter(LocalTracksResponse)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public LocalTracksResponse fromJson(JsonReader jsonReader) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        v03.j(jsonReader, "reader");
        Integer numFromJson = 0;
        jsonReader.c();
        List<LocalTrack> listFromJson = null;
        Integer numFromJson2 = null;
        int i = -1;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                listFromJson = this.listOfLocalTrackAdapter.fromJson(jsonReader);
                if (listFromJson == null) {
                    JsonDataException jsonDataExceptionW = ds2.w("items", "rows", jsonReader);
                    v03.i(jsonDataExceptionW, "unexpectedNull(\"items\",\n…          \"rows\", reader)");
                    throw jsonDataExceptionW;
                }
                i &= -2;
            } else if (iM0 == 1) {
                numFromJson2 = this.intAdapter.fromJson(jsonReader);
                if (numFromJson2 == null) {
                    JsonDataException jsonDataExceptionW2 = ds2.w("unfilteredLength", "unfilteredLength", jsonReader);
                    v03.i(jsonDataExceptionW2, "unexpectedNull(\"unfilter…nfilteredLength\", reader)");
                    throw jsonDataExceptionW2;
                }
            } else if (iM0 == 2) {
                numFromJson = this.intAdapter.fromJson(jsonReader);
                if (numFromJson == null) {
                    JsonDataException jsonDataExceptionW3 = ds2.w("lengthInSeconds", "lengthInSeconds", jsonReader);
                    v03.i(jsonDataExceptionW3, "unexpectedNull(\"lengthIn…lengthInSeconds\", reader)");
                    throw jsonDataExceptionW3;
                }
                i &= -5;
            } else {
                continue;
            }
        }
        jsonReader.q();
        if (i == -6) {
            v03.g(listFromJson, "null cannot be cast to non-null type kotlin.collections.List<com.spotify.localfiles.localfiles.LocalTrack>");
            if (numFromJson2 != null) {
                return new LocalTracksResponse(listFromJson, numFromJson2.intValue(), numFromJson.intValue());
            }
            JsonDataException jsonDataExceptionO = ds2.o("unfilteredLength", "unfilteredLength", jsonReader);
            v03.i(jsonDataExceptionO, "missingProperty(\"unfilte…nfilteredLength\", reader)");
            throw jsonDataExceptionO;
        }
        Constructor<LocalTracksResponse> declaredConstructor = this.constructorRef;
        if (declaredConstructor == null) {
            Class cls = Integer.TYPE;
            declaredConstructor = LocalTracksResponse.class.getDeclaredConstructor(List.class, cls, cls, cls, ds2.c);
            this.constructorRef = declaredConstructor;
            v03.i(declaredConstructor, "LocalTracksResponse::cla…his.constructorRef = it }");
        }
        Object[] objArr = new Object[5];
        objArr[0] = listFromJson;
        if (numFromJson2 == null) {
            JsonDataException jsonDataExceptionO2 = ds2.o("unfilteredLength", "unfilteredLength", jsonReader);
            v03.i(jsonDataExceptionO2, "missingProperty(\"unfilte…h\",\n              reader)");
            throw jsonDataExceptionO2;
        }
        objArr[1] = Integer.valueOf(numFromJson2.intValue());
        objArr[2] = numFromJson;
        objArr[3] = Integer.valueOf(i);
        objArr[4] = null;
        LocalTracksResponse localTracksResponseNewInstance = declaredConstructor.newInstance(objArr);
        v03.i(localTracksResponseNewInstance, "localConstructor.newInst…torMarker */ null\n      )");
        return localTracksResponseNewInstance;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, LocalTracksResponse localTracksResponse) {
        v03.j(mVar, "writer");
        if (localTracksResponse == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("rows");
        this.listOfLocalTrackAdapter.toJson(mVar, localTracksResponse.getItems());
        mVar.J0("unfilteredLength");
        this.intAdapter.toJson(mVar, Integer.valueOf(localTracksResponse.getUnfilteredLength()));
        mVar.J0("lengthInSeconds");
        this.intAdapter.toJson(mVar, Integer.valueOf(localTracksResponse.getLengthInSeconds()));
        mVar.X();
    }
}
