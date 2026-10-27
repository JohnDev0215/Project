package com.spotify.voiceassistants.playermodels;

import com.squareup.moshi.JsonDataException;
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
public final class CommandPlayRequestJsonAdapter extends f<CommandPlayRequest> {
    private final f<Boolean> booleanAdapter;
    private volatile Constructor<CommandPlayRequest> constructorRef;
    private final JsonReader.b options;
    private final f<String> stringAdapter;

    public CommandPlayRequestJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("action", "device_type", "voice_enabled");
        v03.i(bVarA, "of(\"action\", \"device_type\",\n      \"voice_enabled\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<String> fVarF = oVar.f(String.class, emptySet, "action");
        v03.i(fVarF, "moshi.adapter(String::cl…ptySet(),\n      \"action\")");
        this.stringAdapter = fVarF;
        f<Boolean> fVarF2 = oVar.f(Boolean.TYPE, emptySet, "voiceEnabled");
        v03.i(fVarF2, "moshi.adapter(Boolean::c…(),\n      \"voiceEnabled\")");
        this.booleanAdapter = fVarF2;
    }

    public String toString() {
        return u4.b(40, "GeneratedJsonAdapter(CommandPlayRequest)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public CommandPlayRequest fromJson(JsonReader jsonReader) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        v03.j(jsonReader, "reader");
        Boolean boolFromJson = Boolean.FALSE;
        jsonReader.c();
        int i = -1;
        String strFromJson = null;
        String strFromJson2 = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                strFromJson = this.stringAdapter.fromJson(jsonReader);
                if (strFromJson == null) {
                    JsonDataException jsonDataExceptionW = ds2.w("action", "action", jsonReader);
                    v03.i(jsonDataExceptionW, "unexpectedNull(\"action\",…        \"action\", reader)");
                    throw jsonDataExceptionW;
                }
            } else if (iM0 == 1) {
                strFromJson2 = this.stringAdapter.fromJson(jsonReader);
                if (strFromJson2 == null) {
                    JsonDataException jsonDataExceptionW2 = ds2.w("deviceTypes", "device_type", jsonReader);
                    v03.i(jsonDataExceptionW2, "unexpectedNull(\"deviceTy…\", \"device_type\", reader)");
                    throw jsonDataExceptionW2;
                }
            } else if (iM0 == 2) {
                boolFromJson = this.booleanAdapter.fromJson(jsonReader);
                if (boolFromJson == null) {
                    JsonDataException jsonDataExceptionW3 = ds2.w("voiceEnabled", "voice_enabled", jsonReader);
                    v03.i(jsonDataExceptionW3, "unexpectedNull(\"voiceEna… \"voice_enabled\", reader)");
                    throw jsonDataExceptionW3;
                }
                i &= -5;
            } else {
                continue;
            }
        }
        jsonReader.q();
        if (i == -5) {
            if (strFromJson == null) {
                JsonDataException jsonDataExceptionO = ds2.o("action", "action", jsonReader);
                v03.i(jsonDataExceptionO, "missingProperty(\"action\", \"action\", reader)");
                throw jsonDataExceptionO;
            }
            if (strFromJson2 != null) {
                return new CommandPlayRequest(strFromJson, strFromJson2, boolFromJson.booleanValue());
            }
            JsonDataException jsonDataExceptionO2 = ds2.o("deviceTypes", "device_type", jsonReader);
            v03.i(jsonDataExceptionO2, "missingProperty(\"deviceT…e\",\n              reader)");
            throw jsonDataExceptionO2;
        }
        Constructor<CommandPlayRequest> declaredConstructor = this.constructorRef;
        if (declaredConstructor == null) {
            declaredConstructor = CommandPlayRequest.class.getDeclaredConstructor(String.class, String.class, Boolean.TYPE, Integer.TYPE, ds2.c);
            this.constructorRef = declaredConstructor;
            v03.i(declaredConstructor, "CommandPlayRequest::clas…his.constructorRef = it }");
        }
        Object[] objArr = new Object[5];
        if (strFromJson == null) {
            JsonDataException jsonDataExceptionO3 = ds2.o("action", "action", jsonReader);
            v03.i(jsonDataExceptionO3, "missingProperty(\"action\", \"action\", reader)");
            throw jsonDataExceptionO3;
        }
        objArr[0] = strFromJson;
        if (strFromJson2 == null) {
            JsonDataException jsonDataExceptionO4 = ds2.o("deviceTypes", "device_type", jsonReader);
            v03.i(jsonDataExceptionO4, "missingProperty(\"deviceT…\", \"device_type\", reader)");
            throw jsonDataExceptionO4;
        }
        objArr[1] = strFromJson2;
        objArr[2] = boolFromJson;
        objArr[3] = Integer.valueOf(i);
        objArr[4] = null;
        CommandPlayRequest commandPlayRequestNewInstance = declaredConstructor.newInstance(objArr);
        v03.i(commandPlayRequestNewInstance, "localConstructor.newInst…torMarker */ null\n      )");
        return commandPlayRequestNewInstance;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, CommandPlayRequest commandPlayRequest) {
        v03.j(mVar, "writer");
        if (commandPlayRequest == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("action");
        this.stringAdapter.toJson(mVar, commandPlayRequest.getAction());
        mVar.J0("device_type");
        this.stringAdapter.toJson(mVar, commandPlayRequest.getDeviceTypes());
        mVar.J0("voice_enabled");
        this.booleanAdapter.toJson(mVar, Boolean.valueOf(commandPlayRequest.getVoiceEnabled()));
        mVar.X();
    }
}
