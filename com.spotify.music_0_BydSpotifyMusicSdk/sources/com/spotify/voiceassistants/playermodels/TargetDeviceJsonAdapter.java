package com.spotify.voiceassistants.playermodels;

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
public final class TargetDeviceJsonAdapter extends f<TargetDevice> {
    private final f<Boolean> booleanAdapter;
    private final JsonReader.b options;
    private final f<String> stringAdapter;

    public TargetDeviceJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("brand", "model", "device_type", "device_id", "voice_enabled");
        v03.i(bVarA, "of(\"brand\", \"model\", \"de…ice_id\", \"voice_enabled\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<String> fVarF = oVar.f(String.class, emptySet, "brand");
        v03.i(fVarF, "moshi.adapter(String::cl…mptySet(),\n      \"brand\")");
        this.stringAdapter = fVarF;
        f<Boolean> fVarF2 = oVar.f(Boolean.TYPE, emptySet, "voiceEnabled");
        v03.i(fVarF2, "moshi.adapter(Boolean::c…(),\n      \"voiceEnabled\")");
        this.booleanAdapter = fVarF2;
    }

    public String toString() {
        return u4.b(34, "GeneratedJsonAdapter(TargetDevice)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public TargetDevice fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Boolean boolFromJson = null;
        String strFromJson = null;
        String strFromJson2 = null;
        String strFromJson3 = null;
        String strFromJson4 = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            Boolean bool = boolFromJson;
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                strFromJson = this.stringAdapter.fromJson(jsonReader);
                if (strFromJson == null) {
                    JsonDataException jsonDataExceptionW = ds2.w("brand", "brand", jsonReader);
                    v03.i(jsonDataExceptionW, "unexpectedNull(\"brand\", …and\",\n            reader)");
                    throw jsonDataExceptionW;
                }
            } else if (iM0 == 1) {
                strFromJson2 = this.stringAdapter.fromJson(jsonReader);
                if (strFromJson2 == null) {
                    JsonDataException jsonDataExceptionW2 = ds2.w("model", "model", jsonReader);
                    v03.i(jsonDataExceptionW2, "unexpectedNull(\"model\", …del\",\n            reader)");
                    throw jsonDataExceptionW2;
                }
            } else if (iM0 == 2) {
                strFromJson3 = this.stringAdapter.fromJson(jsonReader);
                if (strFromJson3 == null) {
                    JsonDataException jsonDataExceptionW3 = ds2.w("deviceType", "device_type", jsonReader);
                    v03.i(jsonDataExceptionW3, "unexpectedNull(\"deviceTy…   \"device_type\", reader)");
                    throw jsonDataExceptionW3;
                }
            } else if (iM0 == 3) {
                strFromJson4 = this.stringAdapter.fromJson(jsonReader);
                if (strFromJson4 == null) {
                    JsonDataException jsonDataExceptionW4 = ds2.w("deviceId", "device_id", jsonReader);
                    v03.i(jsonDataExceptionW4, "unexpectedNull(\"deviceId…     \"device_id\", reader)");
                    throw jsonDataExceptionW4;
                }
            } else if (iM0 == 4) {
                boolFromJson = this.booleanAdapter.fromJson(jsonReader);
                if (boolFromJson == null) {
                    JsonDataException jsonDataExceptionW5 = ds2.w("voiceEnabled", "voice_enabled", jsonReader);
                    v03.i(jsonDataExceptionW5, "unexpectedNull(\"voiceEna… \"voice_enabled\", reader)");
                    throw jsonDataExceptionW5;
                }
            }
            boolFromJson = bool;
        }
        Boolean bool2 = boolFromJson;
        jsonReader.q();
        if (strFromJson == null) {
            JsonDataException jsonDataExceptionO = ds2.o("brand", "brand", jsonReader);
            v03.i(jsonDataExceptionO, "missingProperty(\"brand\", \"brand\", reader)");
            throw jsonDataExceptionO;
        }
        if (strFromJson2 == null) {
            JsonDataException jsonDataExceptionO2 = ds2.o("model", "model", jsonReader);
            v03.i(jsonDataExceptionO2, "missingProperty(\"model\", \"model\", reader)");
            throw jsonDataExceptionO2;
        }
        if (strFromJson3 == null) {
            JsonDataException jsonDataExceptionO3 = ds2.o("deviceType", "device_type", jsonReader);
            v03.i(jsonDataExceptionO3, "missingProperty(\"deviceT…\", \"device_type\", reader)");
            throw jsonDataExceptionO3;
        }
        if (strFromJson4 == null) {
            JsonDataException jsonDataExceptionO4 = ds2.o("deviceId", "device_id", jsonReader);
            v03.i(jsonDataExceptionO4, "missingProperty(\"deviceId\", \"device_id\", reader)");
            throw jsonDataExceptionO4;
        }
        if (bool2 != null) {
            return new TargetDevice(strFromJson, strFromJson2, strFromJson3, strFromJson4, bool2.booleanValue());
        }
        JsonDataException jsonDataExceptionO5 = ds2.o("voiceEnabled", "voice_enabled", jsonReader);
        v03.i(jsonDataExceptionO5, "missingProperty(\"voiceEn…led\",\n            reader)");
        throw jsonDataExceptionO5;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, TargetDevice targetDevice) {
        v03.j(mVar, "writer");
        if (targetDevice == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("brand");
        this.stringAdapter.toJson(mVar, targetDevice.getBrand());
        mVar.J0("model");
        this.stringAdapter.toJson(mVar, targetDevice.getModel());
        mVar.J0("device_type");
        this.stringAdapter.toJson(mVar, targetDevice.getDeviceType());
        mVar.J0("device_id");
        this.stringAdapter.toJson(mVar, targetDevice.getDeviceId());
        mVar.J0("voice_enabled");
        this.booleanAdapter.toJson(mVar, Boolean.valueOf(targetDevice.getVoiceEnabled()));
        mVar.X();
    }
}
