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
public final class WebApiSearchModel_ArtistsJsonAdapter extends f<WebApiSearchModel$Artists> {
    public final JsonReader.b a;
    public final f b;
    public volatile Constructor c;

    public WebApiSearchModel_ArtistsJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("items");
        v03.i(bVarA, "of(\"items\")");
        this.a = bVarA;
        f fVarF = oVar.f(zo2.j(List.class, WebApiSearchModel$ArtistItem.class), EmptySet.c, "items");
        v03.i(fVarF, "moshi.adapter(Types.newP…va), emptySet(), \"items\")");
        this.b = fVarF;
    }

    @Override // com.squareup.moshi.f
    public final WebApiSearchModel$Artists fromJson(JsonReader jsonReader) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
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
            return new WebApiSearchModel$Artists(list);
        }
        Constructor declaredConstructor = this.c;
        if (declaredConstructor == null) {
            declaredConstructor = WebApiSearchModel$Artists.class.getDeclaredConstructor(List.class, Integer.TYPE, ds2.c);
            this.c = declaredConstructor;
            v03.i(declaredConstructor, "WebApiSearchModel.Artist…his.constructorRef = it }");
        }
        Object objNewInstance = declaredConstructor.newInstance(list, Integer.valueOf(i), null);
        v03.i(objNewInstance, "localConstructor.newInst…torMarker */ null\n      )");
        return (WebApiSearchModel$Artists) objNewInstance;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, WebApiSearchModel$Artists webApiSearchModel$Artists) {
        WebApiSearchModel$Artists webApiSearchModel$Artists2 = webApiSearchModel$Artists;
        v03.j(mVar, "writer");
        if (webApiSearchModel$Artists2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("items");
        this.b.toJson(mVar, webApiSearchModel$Artists2.a);
        mVar.X();
    }

    public final String toString() {
        return u4.b(47, "GeneratedJsonAdapter(WebApiSearchModel.Artists)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
