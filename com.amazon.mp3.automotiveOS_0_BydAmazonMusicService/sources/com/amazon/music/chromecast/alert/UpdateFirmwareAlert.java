package com.amazon.music.chromecast.alert;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class UpdateFirmwareAlert extends ReceiverAlert<Attribute> {
    public UpdateFirmwareAlert(JSONObject jSONObject) throws InstantiationException {
        super(jSONObject);
    }

    public enum Attribute {
        MODEL_NAME("modelName"),
        UNUSABLE_AUDIO_QUALITY("unusableAudioQuality");

        private final String value;

        Attribute(String str) {
            this.value = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.value;
        }
    }
}
