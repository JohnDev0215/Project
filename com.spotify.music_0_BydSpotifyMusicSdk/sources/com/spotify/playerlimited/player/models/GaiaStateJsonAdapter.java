package com.spotify.playerlimited.player.models;

import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.lang.reflect.GenericArrayType;
import kotlin.collections.EmptySet;
import p.ds2;
import p.u4;
import p.v03;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
public final class GaiaStateJsonAdapter extends f<GaiaState> {
    public final JsonReader.b a;
    public final f b;
    public final f c;

    public GaiaStateJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("devices", "is_active", "is_active_on_same_device", "is_connected", "should_use_local_playback");
        v03.i(bVarA, "of(\"devices\", \"is_active…ould_use_local_playback\")");
        this.a = bVarA;
        GenericArrayType genericArrayTypeB = zo2.b(ConnectDevice.class);
        EmptySet emptySet = EmptySet.c;
        f fVarF = oVar.f(genericArrayTypeB, emptySet, "devices");
        v03.i(fVarF, "moshi.adapter(Types.arra…), emptySet(), \"devices\")");
        this.b = fVarF;
        f fVarF2 = oVar.f(Boolean.TYPE, emptySet, "isActive");
        v03.i(fVarF2, "moshi.adapter(Boolean::c…ySet(),\n      \"isActive\")");
        this.c = fVarF2;
    }

    @Override // com.squareup.moshi.f
    public final GaiaState fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        ConnectDevice[] connectDeviceArr = null;
        Boolean bool = null;
        Boolean bool2 = null;
        Boolean bool3 = null;
        boolean z = false;
        Boolean bool4 = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.a);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 != 0) {
                f fVar = this.c;
                if (iM0 == 1) {
                    bool4 = (Boolean) fVar.fromJson(jsonReader);
                    if (bool4 == null) {
                        JsonDataException jsonDataExceptionW = ds2.w("isActive", "is_active", jsonReader);
                        v03.i(jsonDataExceptionW, "unexpectedNull(\"isActive…     \"is_active\", reader)");
                        throw jsonDataExceptionW;
                    }
                } else if (iM0 == 2) {
                    bool = (Boolean) fVar.fromJson(jsonReader);
                    if (bool == null) {
                        JsonDataException jsonDataExceptionW2 = ds2.w("isActiveOnSameDevice", "is_active_on_same_device", jsonReader);
                        v03.i(jsonDataExceptionW2, "unexpectedNull(\"isActive…_on_same_device\", reader)");
                        throw jsonDataExceptionW2;
                    }
                } else if (iM0 == 3) {
                    bool2 = (Boolean) fVar.fromJson(jsonReader);
                    if (bool2 == null) {
                        JsonDataException jsonDataExceptionW3 = ds2.w("isConnected", "is_connected", jsonReader);
                        v03.i(jsonDataExceptionW3, "unexpectedNull(\"isConnec…, \"is_connected\", reader)");
                        throw jsonDataExceptionW3;
                    }
                } else if (iM0 == 4 && (bool3 = (Boolean) fVar.fromJson(jsonReader)) == null) {
                    JsonDataException jsonDataExceptionW4 = ds2.w("localPlaybackEnabled", "should_use_local_playback", jsonReader);
                    v03.i(jsonDataExceptionW4, "unexpectedNull(\"localPla…_local_playback\", reader)");
                    throw jsonDataExceptionW4;
                }
            } else {
                connectDeviceArr = (ConnectDevice[]) this.b.fromJson(jsonReader);
                z = true;
            }
        }
        jsonReader.q();
        GaiaState gaiaState = new GaiaState();
        if (z) {
            gaiaState.e = connectDeviceArr;
        }
        gaiaState.a = bool4 != null ? bool4.booleanValue() : gaiaState.a;
        gaiaState.c = bool != null ? bool.booleanValue() : gaiaState.c;
        gaiaState.b = bool2 != null ? bool2.booleanValue() : gaiaState.b;
        gaiaState.d = bool3 != null ? bool3.booleanValue() : gaiaState.d;
        return gaiaState;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, GaiaState gaiaState) {
        GaiaState gaiaState2 = gaiaState;
        v03.j(mVar, "writer");
        if (gaiaState2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("devices");
        this.b.toJson(mVar, gaiaState2.e);
        mVar.J0("is_active");
        Boolean boolValueOf = Boolean.valueOf(gaiaState2.a);
        f fVar = this.c;
        fVar.toJson(mVar, boolValueOf);
        mVar.J0("is_active_on_same_device");
        u4.w(gaiaState2.c, fVar, mVar, "is_connected");
        u4.w(gaiaState2.b, fVar, mVar, "should_use_local_playback");
        fVar.toJson(mVar, Boolean.valueOf(gaiaState2.d));
        mVar.X();
    }

    public final String toString() {
        return u4.b(31, "GeneratedJsonAdapter(GaiaState)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
