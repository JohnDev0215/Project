package com.amazon.mp3.store.html5.bridge;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.util.Log;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class Message {
    private static final AtomicLong sNextMessageId = new AtomicLong(0);
    private final String mId;
    private final String mName;
    private final JSONObject mOptions;
    private final Type mType;

    public enum Type {
        Request("request"),
        Response("response"),
        Broadcast("broadcast");

        private String mRawType;

        Type(String rawType) {
            this.mRawType = rawType;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mRawType;
        }

        public static Type fromString(String type) throws IllegalArgumentException {
            Type type2 = Request;
            if (type2.toString().equals(type)) {
                return type2;
            }
            Type type3 = Response;
            if (type3.toString().equals(type)) {
                return type3;
            }
            Type type4 = Broadcast;
            if (type4.toString().equals(type)) {
                return type4;
            }
            throw new IllegalArgumentException("Invalid type!");
        }
    }

    private Message(String id, Type type, String name, JSONObject options) {
        if (id == null || id.length() == 0 || name == null || name.length() == 0 || type == null) {
            throw new IllegalArgumentException("id, type, and name must not be null");
        }
        options = options == null ? new JSONObject() : options;
        this.mId = id;
        this.mType = type;
        this.mName = name;
        this.mOptions = options;
    }

    public static Message fromJsonString(String rawMessage) {
        try {
            JSONObject jSONObject = new JSONObject(rawMessage);
            return new Message(jSONObject.getString("id"), Type.fromString(jSONObject.getString(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)), jSONObject.getString("name"), jSONObject.getJSONObject("options"));
        } catch (JSONException unused) {
            Log.info("JsBridge.Message", "Received corrupted message: " + rawMessage);
            return null;
        }
    }

    public static Message createRequest(String name) throws IllegalArgumentException {
        return createRequest(name, null);
    }

    public static Message createRequest(String name, JSONObject options) throws IllegalArgumentException {
        return new Message(newMessageId(), Type.Request, name, options);
    }

    public static Message createBroadcast(String name, JSONObject options) throws IllegalArgumentException {
        return new Message(newMessageId(), Type.Broadcast, name, options);
    }

    public static Message createResponse(String id, String name, JSONObject options) throws IllegalArgumentException {
        return new Message(id, Type.Response, name, options);
    }

    public JSONObject getOptions() {
        return this.mOptions;
    }

    public String getId() {
        return this.mId;
    }

    public Type getType() {
        return this.mType;
    }

    public String getName() {
        return this.mName;
    }

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.mId);
            jSONObject.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, this.mType.toString());
            jSONObject.put("name", this.mName);
            jSONObject.put("options", this.mOptions);
        } catch (JSONException unused) {
            Log.error("JsBridge.Message", "Failed to convert message to string!");
        }
        return jSONObject.toString();
    }

    private static String newMessageId() {
        return "android-" + String.valueOf(sNextMessageId.incrementAndGet());
    }
}
