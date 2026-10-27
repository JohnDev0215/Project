package com.spotify.webapi.search;

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
public final class WebApiSearchModel_ResponseJsonAdapter extends f<WebApiSearchModel$Response> {
    public final JsonReader.b a;
    public final f b;
    public final f c;
    public final f d;
    public final f e;
    public final f f;
    public volatile Constructor g;

    public WebApiSearchModel_ResponseJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("albums", "artists", "playlists", "episodes", "tracks");
        v03.i(bVarA, "of(\"albums\", \"artists\", …    \"episodes\", \"tracks\")");
        this.a = bVarA;
        EmptySet emptySet = EmptySet.c;
        f fVarF = oVar.f(WebApiSearchModel$Albums.class, emptySet, "albums");
        v03.i(fVarF, "moshi.adapter(WebApiSear…va, emptySet(), \"albums\")");
        this.b = fVarF;
        f fVarF2 = oVar.f(WebApiSearchModel$Artists.class, emptySet, "artists");
        v03.i(fVarF2, "moshi.adapter(WebApiSear…a, emptySet(), \"artists\")");
        this.c = fVarF2;
        f fVarF3 = oVar.f(WebApiSearchModel$Playlists.class, emptySet, "playlists");
        v03.i(fVarF3, "moshi.adapter(WebApiSear… emptySet(), \"playlists\")");
        this.d = fVarF3;
        f fVarF4 = oVar.f(WebApiSearchModel$Episodes.class, emptySet, "episodes");
        v03.i(fVarF4, "moshi.adapter(WebApiSear…, emptySet(), \"episodes\")");
        this.e = fVarF4;
        f fVarF5 = oVar.f(WebApiSearchModel$Tracks.class, emptySet, "tracks");
        v03.i(fVarF5, "moshi.adapter(WebApiSear…va, emptySet(), \"tracks\")");
        this.f = fVarF5;
    }

    @Override // com.squareup.moshi.f
    public final WebApiSearchModel$Response fromJson(JsonReader jsonReader) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        WebApiSearchModel$Albums webApiSearchModel$Albums = null;
        WebApiSearchModel$Artists webApiSearchModel$Artists = null;
        WebApiSearchModel$Playlists webApiSearchModel$Playlists = null;
        WebApiSearchModel$Episodes webApiSearchModel$Episodes = null;
        WebApiSearchModel$Tracks webApiSearchModel$Tracks = null;
        int i = -1;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.a);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                webApiSearchModel$Albums = (WebApiSearchModel$Albums) this.b.fromJson(jsonReader);
                i &= -2;
            } else if (iM0 == 1) {
                webApiSearchModel$Artists = (WebApiSearchModel$Artists) this.c.fromJson(jsonReader);
                i &= -3;
            } else if (iM0 == 2) {
                webApiSearchModel$Playlists = (WebApiSearchModel$Playlists) this.d.fromJson(jsonReader);
                i &= -5;
            } else if (iM0 == 3) {
                webApiSearchModel$Episodes = (WebApiSearchModel$Episodes) this.e.fromJson(jsonReader);
                i &= -9;
            } else if (iM0 == 4) {
                webApiSearchModel$Tracks = (WebApiSearchModel$Tracks) this.f.fromJson(jsonReader);
                i &= -17;
            }
        }
        jsonReader.q();
        if (i == -32) {
            return new WebApiSearchModel$Response(webApiSearchModel$Albums, webApiSearchModel$Artists, webApiSearchModel$Playlists, webApiSearchModel$Episodes, webApiSearchModel$Tracks);
        }
        Constructor declaredConstructor = this.g;
        if (declaredConstructor == null) {
            declaredConstructor = WebApiSearchModel$Response.class.getDeclaredConstructor(WebApiSearchModel$Albums.class, WebApiSearchModel$Artists.class, WebApiSearchModel$Playlists.class, WebApiSearchModel$Episodes.class, WebApiSearchModel$Tracks.class, Integer.TYPE, ds2.c);
            this.g = declaredConstructor;
            v03.i(declaredConstructor, "WebApiSearchModel.Respon…his.constructorRef = it }");
        }
        Object objNewInstance = declaredConstructor.newInstance(webApiSearchModel$Albums, webApiSearchModel$Artists, webApiSearchModel$Playlists, webApiSearchModel$Episodes, webApiSearchModel$Tracks, Integer.valueOf(i), null);
        v03.i(objNewInstance, "localConstructor.newInst…torMarker */ null\n      )");
        return (WebApiSearchModel$Response) objNewInstance;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, WebApiSearchModel$Response webApiSearchModel$Response) {
        WebApiSearchModel$Response webApiSearchModel$Response2 = webApiSearchModel$Response;
        v03.j(mVar, "writer");
        if (webApiSearchModel$Response2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("albums");
        this.b.toJson(mVar, webApiSearchModel$Response2.a);
        mVar.J0("artists");
        this.c.toJson(mVar, webApiSearchModel$Response2.b);
        mVar.J0("playlists");
        this.d.toJson(mVar, webApiSearchModel$Response2.c);
        mVar.J0("episodes");
        this.e.toJson(mVar, webApiSearchModel$Response2.d);
        mVar.J0("tracks");
        this.f.toJson(mVar, webApiSearchModel$Response2.e);
        mVar.X();
    }

    public final String toString() {
        return u4.b(48, "GeneratedJsonAdapter(WebApiSearchModel.Response)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
