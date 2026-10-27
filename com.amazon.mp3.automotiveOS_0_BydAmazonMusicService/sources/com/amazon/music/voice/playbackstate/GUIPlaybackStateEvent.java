package com.amazon.music.voice.playbackstate;

import com.amazon.alexa.voice.core.internal.util.DateTimeUtils;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent;
import com.amazon.identity.auth.device.utils.AccountConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class GUIPlaybackStateEvent extends SuperbowlEvent {
    private MediaMetadata mediaMetadata;

    private GUIPlaybackStateEvent(Builder builder) {
        super(builder);
        this.mediaMetadata = builder.mediaMetadata;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent
    protected JSONObject getPayload() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject.put("change", jSONObject2);
        JSONObject jSONObject3 = new JSONObject();
        jSONObject2.put("cause", jSONObject3);
        jSONObject3.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "APP_INTERACTION");
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject4 = new JSONObject();
        jSONObject4.put("namespace", "Alexa.PlaybackStateReporter");
        jSONObject4.put("name", "PlaybackState");
        JSONObject jSONObject5 = new JSONObject();
        jSONObject4.put("value", jSONObject5);
        jSONObject5.put("state", "PLAYING");
        jSONObject5.put("supportedOperations", new JSONArray());
        MediaMetadata mediaMetadata = this.mediaMetadata;
        if (mediaMetadata != null) {
            jSONObject5.put("media", mediaMetadata.toJsonObject());
        } else {
            jSONObject5.put("media", new JSONObject());
        }
        jSONObject5.put("positionMilliseconds", 1000);
        jSONObject5.put("shuffle", "NOT_SHUFFLED");
        jSONObject5.put("repeat", "NOT_REPEATED");
        jSONObject5.put("favorite", "NOT_RATED");
        jSONObject4.put("timeOfSample", getDateTime());
        jSONObject4.put("uncertaintyInMilliseconds", 0);
        jSONArray.put(jSONObject4);
        jSONObject2.put("properties", jSONArray);
        return jSONObject;
    }

    protected String getDateTime() {
        return DateTimeUtils.iso8601(System.currentTimeMillis());
    }

    public String toString() {
        return getClass().getSimpleName() + "{namespace='" + getNamespace() + "', name='" + getName() + "', messageId='" + getMessageId() + "', dialogRequestId='" + getDialogRequestId() + "'}";
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent
    protected JSONObject getHeader() throws JSONException {
        return super.getHeader();
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent
    public JSONObject toJsonObject() throws JSONException {
        return super.toJsonObject();
    }

    public static class Builder extends SuperbowlEvent.Builder<GUIPlaybackStateEvent> {
        protected MediaMetadata mediaMetadata;

        public Builder() {
            super("Alexa", "ChangeReport");
        }

        public Builder media(MediaMetadata mediaMetadata) {
            this.mediaMetadata = mediaMetadata;
            return this;
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent.Builder
        public GUIPlaybackStateEvent build() {
            return new GUIPlaybackStateEvent(this);
        }
    }
}
