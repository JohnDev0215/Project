package com.amazon.music.voice.playbackstate.directives;

import com.amazon.alexa.voice.core.processor.superbowl.ContentProvider;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective;
import com.amazon.alexa.voice.core.processor.superbowl.directives.DirectiveBaseFactory;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: GUIPlaybackSetSeekPositionDirective.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\n\u0018\u0000 \u000e2\u00020\u0001:\u0003\u000f\u000e\u0010B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\f\u0010\rR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/voice/playbackstate/directives/GUIPlaybackSetSeekPositionDirective;", "Lcom/amazon/alexa/voice/core/processor/superbowl/SuperbowlDirective;", "Lcom/amazon/music/voice/playbackstate/directives/GUIPlaybackSetSeekPositionDirective$Builder;", "builder", "Lcom/amazon/music/voice/playbackstate/directives/GUIPlaybackSetSeekPositionDirective$Builder;", "getBuilder", "()Lcom/amazon/music/voice/playbackstate/directives/GUIPlaybackSetSeekPositionDirective$Builder;", "", "position", "J", "getPosition", "()J", "<init>", "(Lcom/amazon/music/voice/playbackstate/directives/GUIPlaybackSetSeekPositionDirective$Builder;)V", "Companion", "Builder", "Factory", "DMMVoice_release"}, k = 1, mv = {1, 6, 0})
public final class GUIPlaybackSetSeekPositionDirective extends SuperbowlDirective {
    private final Builder builder;
    private final long position;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GUIPlaybackSetSeekPositionDirective(Builder builder) {
        super(builder);
        Intrinsics.checkNotNullParameter(builder, "builder");
        this.builder = builder;
        this.position = builder.getPosition();
    }

    public final long getPosition() {
        return this.position;
    }

    /* JADX INFO: compiled from: GUIPlaybackSetSeekPositionDirective.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\f\u0010\rJ\b\u0010\u0003\u001a\u00020\u0002H\u0016J\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u0007\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/voice/playbackstate/directives/GUIPlaybackSetSeekPositionDirective$Builder;", "Lcom/amazon/alexa/voice/core/processor/superbowl/SuperbowlDirective$Builder;", "Lcom/amazon/music/voice/playbackstate/directives/GUIPlaybackSetSeekPositionDirective;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "", "position", "seekPosition", "J", "getPosition", "()J", "setPosition", "(J)V", "<init>", "()V", "DMMVoice_release"}, k = 1, mv = {1, 6, 0})
    public static final class Builder extends SuperbowlDirective.Builder<GUIPlaybackSetSeekPositionDirective> {
        private long position;

        public Builder() {
            super("Alexa.SeekController", "SetSeekPosition");
        }

        public final long getPosition() {
            return this.position;
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective.Builder
        public GUIPlaybackSetSeekPositionDirective build() {
            return new GUIPlaybackSetSeekPositionDirective(this);
        }

        public final Builder seekPosition(long position) {
            this.position = position;
            return this;
        }
    }

    /* JADX INFO: compiled from: GUIPlaybackSetSeekPositionDirective.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006H\u0002¨\u0006\f"}, d2 = {"Lcom/amazon/music/voice/playbackstate/directives/GUIPlaybackSetSeekPositionDirective$Factory;", "Lcom/amazon/alexa/voice/core/processor/superbowl/directives/DirectiveBaseFactory;", "Lcom/amazon/music/voice/playbackstate/directives/GUIPlaybackSetSeekPositionDirective;", "()V", "create", "p0", "Lorg/json/JSONObject;", "p1", "Lcom/amazon/alexa/voice/core/processor/superbowl/ContentProvider;", "getSeekPosition", "", "json", "DMMVoice_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Factory extends DirectiveBaseFactory<GUIPlaybackSetSeekPositionDirective> {
        @Override // com.amazon.alexa.voice.core.processor.superbowl.DirectiveFactory.Factory
        public GUIPlaybackSetSeekPositionDirective create(JSONObject p0, ContentProvider p1) {
            SuperbowlDirective superbowlDirectiveBuild = new Builder().seekPosition(getSeekPosition(p0)).messageId(getMessageId(p0)).dialogRequestId(getDialogRequestId(p0)).build();
            Intrinsics.checkNotNullExpressionValue(superbowlDirectiveBuild, "Builder()\n              …                 .build()");
            return (GUIPlaybackSetSeekPositionDirective) superbowlDirectiveBuild;
        }

        private final long getSeekPosition(JSONObject json) throws Exception {
            JSONObject jSONObject;
            Long lValueOf = null;
            if (json != null && (jSONObject = json.getJSONObject("payload")) != null) {
                lValueOf = Long.valueOf(jSONObject.getLong("positionMilliseconds"));
            }
            if (lValueOf != null) {
                return lValueOf.longValue();
            }
            throw new Exception("Unable to parse positionInMilliSeconds for GUIPlaybackSetSeekPositionDirective");
        }
    }
}
