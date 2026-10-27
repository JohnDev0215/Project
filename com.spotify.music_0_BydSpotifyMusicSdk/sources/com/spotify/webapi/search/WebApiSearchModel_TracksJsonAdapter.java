package com.spotify.webapi.search;

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
public final class WebApiSearchModel_TracksJsonAdapter extends f<WebApiSearchModel$Tracks> {
    public final JsonReader.b a;
    public final f b;
    public volatile Constructor c;

    public WebApiSearchModel_TracksJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("items");
        v03.i(bVarA, "of(\"items\")");
        this.a = bVarA;
        f fVarF = oVar.f(zo2.j(List.class, WebApiSearchModel$TrackItem.class), EmptySet.c, "items");
        v03.i(fVarF, "moshi.adapter(Types.newP…va), emptySet(), \"items\")");
        this.b = fVarF;
    }

    @Override // com.squareup.moshi.f
    public final WebApiSearchModel$Tracks fromJson(JsonReader jsonReader) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        List list = null;
        int i = -1;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.a);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                list = (List) this.b.fromJson(jsonReader);
                i &= -2;
            }
        }
        jsonReader.q();
        if (i == -2) {
            return new WebApiSearchModel$Tracks(list);
        }
        Constructor declaredConstructor = this.c;
        if (declaredConstructor == null) {
            declaredConstructor = WebApiSearchModel$Tracks.class.getDeclaredConstructor(List.class, Integer.TYPE, ds2.c);
            this.c = declaredConstructor;
            v03.i(declaredConstructor, "WebApiSearchModel.Tracks…his.constructorRef = it }");
        }
        Object objNewInstance = declaredConstructor.newInstance(list, Integer.valueOf(i), null);
        v03.i(objNewInstance, "localConstructor.newInst…torMarker */ null\n      )");
        return (WebApiSearchModel$Tracks) objNewInstance;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, WebApiSearchModel$Tracks webApiSearchModel$Tracks) {
        WebApiSearchModel$Tracks webApiSearchModel$Tracks2 = webApiSearchModel$Tracks;
        v03.j(mVar, "writer");
        if (webApiSearchModel$Tracks2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("items");
        this.b.toJson(mVar, webApiSearchModel$Tracks2.a);
        mVar.X();
    }

    public final String toString() {
        return u4.b(46, "GeneratedJsonAdapter(WebApiSearchModel.Tracks)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
