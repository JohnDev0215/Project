package com.neusoft.de.nts.OneCoreTest.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.transportMessages.BaseProtocolError;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.transportMessages.NotificationProtocolError;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.transportMessages.RequestProtocolError;
import io.gsonfire.GsonFireBuilder;
import io.gsonfire.PostProcessor;
import io.gsonfire.PreProcessor;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes2.dex */
public enum OneCoreJson {
    INSTANCE;

    private final List<String> CMD_KEY_ERASE_LIST;
    private final Map<String, String> CMD_KEY_REPLACE_MAP;
    private final Map<String, String> NTF_KEY_REPLACE_MAP;
    private final Gson gson;

    OneCoreJson() {
        TreeMap treeMap = new TreeMap();
        this.CMD_KEY_REPLACE_MAP = treeMap;
        TreeMap treeMap2 = new TreeMap();
        this.NTF_KEY_REPLACE_MAP = treeMap2;
        ArrayList arrayList = new ArrayList();
        this.CMD_KEY_ERASE_LIST = arrayList;
        treeMap.put("command", "method");
        treeMap.put("messageType", "type");
        treeMap2.put("command", "signal");
        treeMap2.put("messageType", "type");
        arrayList.add("subComponent");
        this.gson = new GsonFireBuilder().registerPostProcessor(BaseCommand.class, new PostProcessor<BaseCommand>() { // from class: com.neusoft.de.nts.OneCoreTest.utils.OneCoreJson.6
            @Override // io.gsonfire.PostProcessor
            public void postDeserialize(BaseCommand baseCommand, JsonElement jsonElement, Gson gson) {
            }

            @Override // io.gsonfire.PostProcessor
            public void postSerialize(JsonElement jsonElement, BaseCommand baseCommand, Gson gson) {
                if (jsonElement.isJsonNull()) {
                    return;
                }
                if (baseCommand instanceof BaseNotification) {
                    OneCoreJson oneCoreJson = OneCoreJson.this;
                    oneCoreJson.postSerializer(jsonElement, oneCoreJson.NTF_KEY_REPLACE_MAP, OneCoreJson.this.CMD_KEY_ERASE_LIST);
                } else {
                    OneCoreJson oneCoreJson2 = OneCoreJson.this;
                    oneCoreJson2.postSerializer(jsonElement, oneCoreJson2.CMD_KEY_REPLACE_MAP, OneCoreJson.this.CMD_KEY_ERASE_LIST);
                }
            }
        }).registerPreProcessor(NotificationProtocolError.class, new PreProcessor<NotificationProtocolError>() { // from class: com.neusoft.de.nts.OneCoreTest.utils.OneCoreJson.5
            @Override // io.gsonfire.PreProcessor
            public void preDeserialize(Class<? extends NotificationProtocolError> cls, JsonElement jsonElement, Gson gson) {
                if (jsonElement.isJsonNull()) {
                    return;
                }
                OneCoreJson oneCoreJson = OneCoreJson.this;
                oneCoreJson.preDeserializer(jsonElement, oneCoreJson.NTF_KEY_REPLACE_MAP);
            }
        }).registerPreProcessor(RequestProtocolError.class, new PreProcessor<RequestProtocolError>() { // from class: com.neusoft.de.nts.OneCoreTest.utils.OneCoreJson.4
            @Override // io.gsonfire.PreProcessor
            public void preDeserialize(Class<? extends RequestProtocolError> cls, JsonElement jsonElement, Gson gson) {
                if (jsonElement.isJsonNull()) {
                    return;
                }
                OneCoreJson oneCoreJson = OneCoreJson.this;
                oneCoreJson.preDeserializer(jsonElement, oneCoreJson.CMD_KEY_REPLACE_MAP);
            }
        }).registerPreProcessor(BaseNotification.class, new PreProcessor<BaseNotification>() { // from class: com.neusoft.de.nts.OneCoreTest.utils.OneCoreJson.3
            @Override // io.gsonfire.PreProcessor
            public void preDeserialize(Class<? extends BaseNotification> cls, JsonElement jsonElement, Gson gson) {
                if (jsonElement.isJsonNull() || BaseProtocolError.class.isAssignableFrom(cls)) {
                    return;
                }
                OneCoreJson oneCoreJson = OneCoreJson.this;
                oneCoreJson.preDeserializer(jsonElement, oneCoreJson.NTF_KEY_REPLACE_MAP);
            }
        }).registerPreProcessor(BaseResponse.class, new PreProcessor<BaseResponse>() { // from class: com.neusoft.de.nts.OneCoreTest.utils.OneCoreJson.2
            @Override // io.gsonfire.PreProcessor
            public void preDeserialize(Class<? extends BaseResponse> cls, JsonElement jsonElement, Gson gson) {
                if (jsonElement.isJsonNull()) {
                    return;
                }
                OneCoreJson oneCoreJson = OneCoreJson.this;
                oneCoreJson.preDeserializer(jsonElement, oneCoreJson.CMD_KEY_REPLACE_MAP);
            }
        }).registerPreProcessor(BaseRequest.class, new PreProcessor<BaseRequest>() { // from class: com.neusoft.de.nts.OneCoreTest.utils.OneCoreJson.1
            @Override // io.gsonfire.PreProcessor
            public void preDeserialize(Class<? extends BaseRequest> cls, JsonElement jsonElement, Gson gson) {
                if (jsonElement.isJsonNull()) {
                    return;
                }
                OneCoreJson oneCoreJson = OneCoreJson.this;
                oneCoreJson.preDeserializer(jsonElement, oneCoreJson.CMD_KEY_REPLACE_MAP);
            }
        }).createGsonBuilder().registerTypeHierarchyAdapter(Map.class, new OneCoreJsonMapTypeAdapter()).registerTypeAdapter(ByteBuffer.class, new OneCoreJsonByteBufferAdapter()).create();
    }

    void postSerializer(JsonElement jsonElement, Map<String, String> map, List<String> list) {
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        for (String str : list) {
            if (asJsonObject.has(str)) {
                asJsonObject.remove(str);
            }
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (asJsonObject.has(entry.getKey())) {
                asJsonObject.add(entry.getValue(), asJsonObject.get(entry.getKey()));
                asJsonObject.remove(entry.getKey());
            }
        }
    }

    void preDeserializer(JsonElement jsonElement, Map<String, String> map) {
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (asJsonObject.has(entry.getValue())) {
                asJsonObject.add(entry.getKey(), asJsonObject.get(entry.getValue()));
                asJsonObject.remove(entry.getValue());
            }
        }
    }

    public Gson get() {
        return this.gson;
    }
}
