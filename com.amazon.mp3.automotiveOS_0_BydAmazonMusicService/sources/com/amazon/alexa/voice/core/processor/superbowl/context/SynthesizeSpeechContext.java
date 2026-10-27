package com.amazon.alexa.voice.core.processor.superbowl.context;

import com.amazon.alexa.voice.core.internal.util.Preconditions;
import com.amazon.alexa.voice.core.processor.superbowl.Context;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class SynthesizeSpeechContext extends Context {
    private final String activity;
    private final String contentToken;
    private final long offsetInMilliseconds;

    private SynthesizeSpeechContext(Builder builder) {
        super(builder);
        this.contentToken = builder.contentToken;
        this.activity = builder.activity;
        this.offsetInMilliseconds = builder.offsetInMilliseconds;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.Context
    protected JSONObject getPayload() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("token", this.contentToken);
        jSONObject.put("offsetInMilliseconds", this.offsetInMilliseconds);
        jSONObject.put("playerActivity", this.activity);
        return jSONObject;
    }

    public String toString() {
        return "SynthesizeSpeechContext{namespace='" + getNamespace() + "', name='" + getName() + "', contentToken='" + this.contentToken + "', activity='" + this.activity + "', offsetInMilliseconds=" + this.offsetInMilliseconds + '}';
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.Context
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SynthesizeSpeechContext.class != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        SynthesizeSpeechContext synthesizeSpeechContext = (SynthesizeSpeechContext) obj;
        if (this.offsetInMilliseconds == synthesizeSpeechContext.offsetInMilliseconds && this.contentToken.equals(synthesizeSpeechContext.contentToken)) {
            return this.activity.equals(synthesizeSpeechContext.activity);
        }
        return false;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.Context
    public int hashCode() {
        int iHashCode = ((((super.hashCode() * 31) + this.contentToken.hashCode()) * 31) + this.activity.hashCode()) * 31;
        long j = this.offsetInMilliseconds;
        return iHashCode + ((int) (j ^ (j >>> 32)));
    }

    public static final class Builder extends Context.Builder<SynthesizeSpeechContext> {
        private String activity;
        private String contentToken;
        private long offsetInMilliseconds;

        public Builder() {
            super("SpeechSynthesizer", "SpeechState");
        }

        public Builder contentToken(String str) {
            this.contentToken = str;
            return this;
        }

        public Builder activity(String str) {
            this.activity = str;
            return this;
        }

        public Builder offsetInMilliseconds(long j) {
            this.offsetInMilliseconds = j;
            return this;
        }

        public SynthesizeSpeechContext build() {
            Preconditions.min(this.offsetInMilliseconds, 0L, "Offset cannot be negative");
            Preconditions.notNull(this.contentToken, "contentToken == null");
            Preconditions.notNull(this.activity, "activity == null");
            return new SynthesizeSpeechContext(this);
        }
    }
}
