package com.spotify.voiceassistants.playermodels;

import com.spotify.voiceassistants.playermodels.MetadataItem;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import kotlin.collections.EmptySet;
import p.ds2;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class MetadataItem_ErrorJsonAdapter extends f<MetadataItem.Error> {
    private volatile Constructor<MetadataItem.Error> constructorRef;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public MetadataItem_ErrorJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("error");
        v03.i(bVarA, "of(\"error\")");
        this.options = bVarA;
        f<String> fVarF = oVar.f(String.class, EmptySet.c, "error");
        v03.i(fVarF, "moshi.adapter(String::cl…     emptySet(), \"error\")");
        this.nullableStringAdapter = fVarF;
    }

    public String toString() {
        return u4.b(40, "GeneratedJsonAdapter(MetadataItem.Error)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public MetadataItem.Error fromJson(JsonReader jsonReader) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String strFromJson = null;
        int i = -1;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                i &= -2;
            }
        }
        jsonReader.q();
        if (i == -2) {
            return new MetadataItem.Error(strFromJson);
        }
        Constructor<MetadataItem.Error> declaredConstructor = this.constructorRef;
        if (declaredConstructor == null) {
            declaredConstructor = MetadataItem.Error.class.getDeclaredConstructor(String.class, Integer.TYPE, ds2.c);
            this.constructorRef = declaredConstructor;
            v03.i(declaredConstructor, "MetadataItem.Error::clas…his.constructorRef = it }");
        }
        MetadataItem.Error errorNewInstance = declaredConstructor.newInstance(strFromJson, Integer.valueOf(i), null);
        v03.i(errorNewInstance, "localConstructor.newInst…torMarker */ null\n      )");
        return errorNewInstance;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, MetadataItem.Error error) {
        v03.j(mVar, "writer");
        if (error == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("error");
        this.nullableStringAdapter.toJson(mVar, error.getError());
        mVar.X();
    }
}
