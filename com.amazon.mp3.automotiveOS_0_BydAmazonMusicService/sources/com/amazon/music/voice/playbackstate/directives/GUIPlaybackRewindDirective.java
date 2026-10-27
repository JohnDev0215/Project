package com.amazon.music.voice.playbackstate.directives;

import com.amazon.alexa.voice.core.processor.superbowl.ContentProvider;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective;
import com.amazon.alexa.voice.core.processor.superbowl.directives.DirectiveBaseFactory;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: GUIPlaybackRewindDirective.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \t2\u00020\u0001:\u0003\n\t\u000bB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"}, d2 = {"Lcom/amazon/music/voice/playbackstate/directives/GUIPlaybackRewindDirective;", "Lcom/amazon/alexa/voice/core/processor/superbowl/SuperbowlDirective;", "Lcom/amazon/music/voice/playbackstate/directives/GUIPlaybackRewindDirective$Builder;", "builder", "Lcom/amazon/music/voice/playbackstate/directives/GUIPlaybackRewindDirective$Builder;", "getBuilder", "()Lcom/amazon/music/voice/playbackstate/directives/GUIPlaybackRewindDirective$Builder;", "<init>", "(Lcom/amazon/music/voice/playbackstate/directives/GUIPlaybackRewindDirective$Builder;)V", "Companion", "Builder", "Factory", "DMMVoice_release"}, k = 1, mv = {1, 6, 0})
public final class GUIPlaybackRewindDirective extends SuperbowlDirective {
    private final Builder builder;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GUIPlaybackRewindDirective(Builder builder) {
        super(builder);
        Intrinsics.checkNotNullParameter(builder, "builder");
        this.builder = builder;
    }

    /* JADX INFO: compiled from: GUIPlaybackRewindDirective.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0002H\u0016¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/voice/playbackstate/directives/GUIPlaybackRewindDirective$Builder;", "Lcom/amazon/alexa/voice/core/processor/superbowl/SuperbowlDirective$Builder;", "Lcom/amazon/music/voice/playbackstate/directives/GUIPlaybackRewindDirective;", "()V", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "DMMVoice_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Builder extends SuperbowlDirective.Builder<GUIPlaybackRewindDirective> {
        public Builder() {
            super("Alexa.PlaybackController", "Rewind");
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective.Builder
        public GUIPlaybackRewindDirective build() {
            return new GUIPlaybackRewindDirective(this);
        }
    }

    /* JADX INFO: compiled from: GUIPlaybackRewindDirective.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"Lcom/amazon/music/voice/playbackstate/directives/GUIPlaybackRewindDirective$Factory;", "Lcom/amazon/alexa/voice/core/processor/superbowl/directives/DirectiveBaseFactory;", "Lcom/amazon/music/voice/playbackstate/directives/GUIPlaybackRewindDirective;", "()V", "create", "p0", "Lorg/json/JSONObject;", "p1", "Lcom/amazon/alexa/voice/core/processor/superbowl/ContentProvider;", "DMMVoice_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Factory extends DirectiveBaseFactory<GUIPlaybackRewindDirective> {
        @Override // com.amazon.alexa.voice.core.processor.superbowl.DirectiveFactory.Factory
        public GUIPlaybackRewindDirective create(JSONObject p0, ContentProvider p1) {
            SuperbowlDirective superbowlDirectiveBuild = new Builder().messageId(getMessageId(p0)).dialogRequestId(getDialogRequestId(p0)).build();
            Intrinsics.checkNotNullExpressionValue(superbowlDirectiveBuild, "Builder()\n              …                 .build()");
            return (GUIPlaybackRewindDirective) superbowlDirectiveBuild;
        }
    }
}
