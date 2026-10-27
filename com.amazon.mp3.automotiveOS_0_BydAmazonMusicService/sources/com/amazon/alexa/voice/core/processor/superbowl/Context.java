package com.amazon.alexa.voice.core.processor.superbowl;

import com.amazon.alexa.voice.core.internal.util.Preconditions;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Context {
    private final String name;
    private final String namespace;

    protected Context(Builder<? extends Context> builder) {
        Preconditions.notNull(builder.namespace, "namespace == null");
        Preconditions.notNull(builder.name, "name == null");
        this.namespace = builder.namespace;
        this.name = builder.name;
    }

    public final String getNamespace() {
        return this.namespace;
    }

    public final String getName() {
        return this.name;
    }

    protected JSONObject getPayload() throws JSONException {
        return new JSONObject();
    }

    protected JSONObject getHeader() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("namespace", this.namespace);
        jSONObject.put("name", this.name);
        return jSONObject;
    }

    public JSONObject toJsonObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("header", getHeader());
        jSONObject.put("payload", getPayload());
        return jSONObject;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Context context = (Context) obj;
        if (this.namespace.equals(context.namespace)) {
            return this.name.equals(context.name);
        }
        return false;
    }

    public int hashCode() {
        return (this.namespace.hashCode() * 31) + this.name.hashCode();
    }

    public static abstract class Builder<T extends Context> {
        protected final String name;
        protected final String namespace;

        protected Builder(String str, String str2) {
            this.namespace = str;
            this.name = str2;
        }
    }
}
