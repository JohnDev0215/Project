package com.spotify.playerlimited.player.models;

import com.spotify.connectivity.productstate.RxProductState;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import kotlin.collections.EmptySet;
import p.ds2;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectDeviceJsonAdapter extends f<ConnectDevice> {
    public final JsonReader.b a;
    public final f b;
    public final f c;

    public ConnectDeviceJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("identifier", "is_active", "is_self", "name", "physical_identifier", RxProductState.Keys.KEY_TYPE);
        v03.i(bVarA, "of(\"identifier\", \"is_act…ical_identifier\", \"type\")");
        this.a = bVarA;
        EmptySet emptySet = EmptySet.c;
        f fVarF = oVar.f(String.class, emptySet, "identifier");
        v03.i(fVarF, "moshi.adapter(String::cl…emptySet(), \"identifier\")");
        this.b = fVarF;
        f fVarF2 = oVar.f(Boolean.TYPE, emptySet, "isActive");
        v03.i(fVarF2, "moshi.adapter(Boolean::c…ySet(),\n      \"isActive\")");
        this.c = fVarF2;
    }

    @Override // com.squareup.moshi.f
    public final ConnectDevice fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String str = null;
        Boolean bool = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        Boolean bool2 = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.a);
            f fVar = this.c;
            f fVar2 = this.b;
            switch (iM0) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    break;
                case 0:
                    str = (String) fVar2.fromJson(jsonReader);
                    z = true;
                    break;
                case 1:
                    bool2 = (Boolean) fVar.fromJson(jsonReader);
                    if (bool2 == null) {
                        JsonDataException jsonDataExceptionW = ds2.w("isActive", "is_active", jsonReader);
                        v03.i(jsonDataExceptionW, "unexpectedNull(\"isActive…     \"is_active\", reader)");
                        throw jsonDataExceptionW;
                    }
                    break;
                    break;
                case 2:
                    bool = (Boolean) fVar.fromJson(jsonReader);
                    if (bool == null) {
                        JsonDataException jsonDataExceptionW2 = ds2.w("isSelf", "is_self", jsonReader);
                        v03.i(jsonDataExceptionW2, "unexpectedNull(\"isSelf\",…       \"is_self\", reader)");
                        throw jsonDataExceptionW2;
                    }
                    break;
                    break;
                case 3:
                    str2 = (String) fVar2.fromJson(jsonReader);
                    z2 = true;
                    break;
                case 4:
                    str3 = (String) fVar2.fromJson(jsonReader);
                    z3 = true;
                    break;
                case 5:
                    str4 = (String) fVar2.fromJson(jsonReader);
                    z4 = true;
                    break;
            }
        }
        jsonReader.q();
        ConnectDevice connectDevice = new ConnectDevice();
        if (z) {
            connectDevice.e = str;
        }
        connectDevice.a = bool2 != null ? bool2.booleanValue() : connectDevice.a;
        connectDevice.b = bool != null ? bool.booleanValue() : connectDevice.b;
        if (z2) {
            connectDevice.c = str2;
        }
        if (z3) {
            connectDevice.d = str3;
        }
        if (z4) {
            connectDevice.f = str4;
        }
        return connectDevice;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, ConnectDevice connectDevice) {
        ConnectDevice connectDevice2 = connectDevice;
        v03.j(mVar, "writer");
        if (connectDevice2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("identifier");
        String str = connectDevice2.e;
        f fVar = this.b;
        fVar.toJson(mVar, str);
        mVar.J0("is_active");
        Boolean boolValueOf = Boolean.valueOf(connectDevice2.a);
        f fVar2 = this.c;
        fVar2.toJson(mVar, boolValueOf);
        mVar.J0("is_self");
        u4.w(connectDevice2.b, fVar2, mVar, "name");
        fVar.toJson(mVar, connectDevice2.c);
        mVar.J0("physical_identifier");
        fVar.toJson(mVar, connectDevice2.d);
        mVar.J0(RxProductState.Keys.KEY_TYPE);
        fVar.toJson(mVar, connectDevice2.f);
        mVar.X();
    }

    public final String toString() {
        return u4.b(35, "GeneratedJsonAdapter(ConnectDevice)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
