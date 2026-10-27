package com.amazon.alexa.voice.core.processor.superbowl.directives;

import com.amazon.alexa.voice.core.internal.util.Preconditions;
import com.amazon.alexa.voice.core.processor.superbowl.Content;
import com.amazon.alexa.voice.core.processor.superbowl.ContentProvider;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.common.internal.ImagesContract;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class SynthesizeSpeechDirective extends SuperbowlDirective {
    private final Content audioContent;
    private final String caption;
    private final String ssml;
    private final String token;

    SynthesizeSpeechDirective(Builder builder) {
        super(builder);
        this.token = builder.token;
        this.audioContent = builder.audioContent;
        this.caption = builder.caption;
        this.ssml = builder.ssml;
    }

    public Content getContent() {
        return this.audioContent;
    }

    public String getToken() {
        return this.token;
    }

    public String getSsml() {
        return this.ssml;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SynthesizeSpeechDirective.class != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        SynthesizeSpeechDirective synthesizeSpeechDirective = (SynthesizeSpeechDirective) obj;
        if (!this.token.equals(synthesizeSpeechDirective.token)) {
            return false;
        }
        String str = this.caption;
        if (str != null ? !str.equals(synthesizeSpeechDirective.caption) : synthesizeSpeechDirective.caption != null) {
            return false;
        }
        String str2 = this.ssml;
        if (str2 != null ? str2.equals(synthesizeSpeechDirective.ssml) : synthesizeSpeechDirective.ssml == null) {
            return this.audioContent.equals(synthesizeSpeechDirective.audioContent);
        }
        return false;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective
    public int hashCode() {
        int iHashCode = ((((super.hashCode() * 31) + this.token.hashCode()) * 31) + this.audioContent.hashCode()) * 31;
        String str = this.caption;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.ssml;
        return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "SynthesizeSpeech{messageId='" + getMessageId() + "', dialogRequestId='" + getDialogRequestId() + "', token='" + this.token + "', caption='" + this.caption + "', ssml='" + this.ssml + "', audioContent=" + this.audioContent + '}';
    }

    public static final class Builder extends SuperbowlDirective.Builder<SynthesizeSpeechDirective> {
        Content audioContent;
        String caption;
        String ssml;
        String token;

        public Builder() {
            super("SpeechSynthesizer", "Speak");
        }

        public Builder token(String str) {
            this.token = str;
            return this;
        }

        public Builder audioContent(Content content) {
            this.audioContent = content;
            return this;
        }

        public Builder caption(String str) {
            this.caption = str;
            return this;
        }

        public Builder ssml(String str) {
            this.ssml = str;
            return this;
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective.Builder
        public SynthesizeSpeechDirective build() {
            Preconditions.notNull(this.token, "token == null");
            Preconditions.notNull(this.audioContent, "audioContent == null");
            return new SynthesizeSpeechDirective(this);
        }
    }

    public static final class Factory extends DirectiveBaseFactory<SynthesizeSpeechDirective> {
        @Override // com.amazon.alexa.voice.core.processor.superbowl.DirectiveFactory.Factory
        public SynthesizeSpeechDirective create(JSONObject jSONObject, ContentProvider contentProvider) throws JSONException {
            JSONObject jSONObject2 = jSONObject.getJSONObject("payload");
            ContentUrl contentUrl = new ContentUrl(jSONObject2.getString(ImagesContract.URL));
            if (!contentUrl.isAttachedContent()) {
                throw new IllegalArgumentException("Unsupported scheme or invalid url " + contentUrl.getUrl());
            }
            Content content = contentProvider.get(contentUrl.getContentId());
            if (content == null) {
                throw new IllegalArgumentException("No content attached " + contentUrl.getContentId());
            }
            return (SynthesizeSpeechDirective) new Builder().audioContent(content).token(jSONObject2.getString("token")).caption(jSONObject2.optString(MediaTrack.ROLE_CAPTION, null)).ssml(jSONObject2.optString("ssml", null)).messageId(getMessageId(jSONObject)).dialogRequestId(getDialogRequestId(jSONObject)).build();
        }
    }
}
