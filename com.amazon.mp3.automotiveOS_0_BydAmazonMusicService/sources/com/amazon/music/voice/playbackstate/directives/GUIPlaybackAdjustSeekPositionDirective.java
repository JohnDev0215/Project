package com.amazon.music.voice.playbackstate.directives;

import com.amazon.alexa.voice.core.processor.superbowl.ContentProvider;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective;
import com.amazon.alexa.voice.core.processor.superbowl.directives.DirectiveBaseFactory;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class GUIPlaybackAdjustSeekPositionDirective extends SuperbowlDirective {
    public long deltaPosition;

    GUIPlaybackAdjustSeekPositionDirective(Builder builder) {
        super(builder);
        this.deltaPosition = builder.deltaPosition;
    }

    public static final class Builder extends SuperbowlDirective.Builder<GUIPlaybackAdjustSeekPositionDirective> {
        private long deltaPosition;

        public Builder() {
            super("Alexa.SeekController", "AdjustSeekPosition");
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective.Builder
        public GUIPlaybackAdjustSeekPositionDirective build() {
            return new GUIPlaybackAdjustSeekPositionDirective(this);
        }

        public Builder deltaPosition(long j) {
            this.deltaPosition = j;
            return this;
        }
    }

    public static final class Factory extends DirectiveBaseFactory<GUIPlaybackAdjustSeekPositionDirective> {
        @Override // com.amazon.alexa.voice.core.processor.superbowl.DirectiveFactory.Factory
        public GUIPlaybackAdjustSeekPositionDirective create(JSONObject jSONObject, ContentProvider contentProvider) throws JSONException {
            return (GUIPlaybackAdjustSeekPositionDirective) new Builder().deltaPosition(getDeltaPosition(jSONObject)).dialogRequestId(getDialogRequestId(jSONObject)).messageId(getMessageId(jSONObject)).build();
        }

        private long getDeltaPosition(JSONObject jSONObject) throws JSONException {
            return jSONObject.getJSONObject("payload").getLong("deltaPositionMilliseconds");
        }
    }
}
