package com.amazon.music.skyfire.models;

import PlaybackInterface.v1_0.MediaControlStatesElement;
import PlaybackInterface.v1_0.NextControlStateElement;
import PlaybackInterface.v1_0.PlayControlStateElement;
import PlaybackInterface.v1_0.PlaybackSpeedControlOptionElement;
import PlaybackInterface.v1_0.PlaybackSpeedControlStateElement;
import PlaybackInterface.v1_0.PreviousControlStateElement;
import PlaybackInterface.v1_0.RepeatControlStateElement;
import PlaybackInterface.v1_0.ShuffleControlStateElement;
import PlaybackInterface.v1_0.SkipBackwardControlStateElement;
import PlaybackInterface.v1_0.SkipForwardControlStateElement;
import androidx.core.app.FrameMetricsAggregator;
import com.amazon.music.find.utils.PageUriUtils;
import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.EnumSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* JADX INFO: compiled from: PlaybackModels.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 32\u00020\u0001:\u00041234By\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u0012Bw\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e¢\u0006\u0002\u0010\u0013J!\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00002\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200HÇ\u0001R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0015\"\u0004\b\u0019\u0010\u0017R\u001c\u0010\f\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0015\"\u0004\b\u001b\u0010\u0017R\"\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0015\"\u0004\b!\u0010\u0017R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0015\"\u0004\b#\u0010\u0017R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0015\"\u0004\b%\u0010\u0017R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0015\"\u0004\b'\u0010\u0017R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0015\"\u0004\b)\u0010\u0017¨\u00065"}, d2 = {"Lcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel;", "", "seen1", "", PageUriUtils.DO_PLAY_VALUE, "Lcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel$ButtonState;", "next", "previous", "skipBackward", "skipForward", "repeat", "shuffle", "playbackSpeed", "playbackSpeedOptions", "", "Lcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel$PlaybackSpeedOption;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel$ButtonState;Lcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel$ButtonState;Lcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel$ButtonState;Lcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel$ButtonState;Lcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel$ButtonState;Lcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel$ButtonState;Lcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel$ButtonState;Lcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel$ButtonState;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel$ButtonState;Lcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel$ButtonState;Lcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel$ButtonState;Lcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel$ButtonState;Lcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel$ButtonState;Lcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel$ButtonState;Lcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel$ButtonState;Lcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel$ButtonState;Ljava/util/List;)V", "getNext", "()Lcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel$ButtonState;", "setNext", "(Lcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel$ButtonState;)V", "getPlay", "setPlay", "getPlaybackSpeed", "setPlaybackSpeed", "getPlaybackSpeedOptions", "()Ljava/util/List;", "setPlaybackSpeedOptions", "(Ljava/util/List;)V", "getPrevious", "setPrevious", "getRepeat", "setRepeat", "getShuffle", "setShuffle", "getSkipBackward", "setSkipBackward", "getSkipForward", "setSkipForward", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "ButtonState", "Companion", "PlaybackSpeedOption", "DMSFCoreAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
@Serializable
public final class SkyfirePlaybackControlsModel {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final float DEFAULT_PLAYBACK_SPEED = 1.0f;
    private ButtonState next;
    private ButtonState play;
    private ButtonState playbackSpeed;
    private List<PlaybackSpeedOption> playbackSpeedOptions;
    private ButtonState previous;
    private ButtonState repeat;
    private ButtonState shuffle;
    private ButtonState skipBackward;
    private ButtonState skipForward;

    public SkyfirePlaybackControlsModel() {
        this((ButtonState) null, (ButtonState) null, (ButtonState) null, (ButtonState) null, (ButtonState) null, (ButtonState) null, (ButtonState) null, (ButtonState) null, (List) null, FrameMetricsAggregator.EVERY_DURATION, (DefaultConstructorMarker) null);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ SkyfirePlaybackControlsModel(int i, ButtonState buttonState, ButtonState buttonState2, ButtonState buttonState3, ButtonState buttonState4, ButtonState buttonState5, ButtonState buttonState6, ButtonState buttonState7, ButtonState buttonState8, List list, SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 0) != 0) {
            PluginExceptionsKt.throwMissingFieldException(i, 0, SkyfirePlaybackControlsModel$$serializer.INSTANCE.getDescriptor());
        }
        if ((i & 1) == 0) {
            this.play = null;
        } else {
            this.play = buttonState;
        }
        if ((i & 2) == 0) {
            this.next = null;
        } else {
            this.next = buttonState2;
        }
        if ((i & 4) == 0) {
            this.previous = null;
        } else {
            this.previous = buttonState3;
        }
        if ((i & 8) == 0) {
            this.skipBackward = null;
        } else {
            this.skipBackward = buttonState4;
        }
        if ((i & 16) == 0) {
            this.skipForward = null;
        } else {
            this.skipForward = buttonState5;
        }
        if ((i & 32) == 0) {
            this.repeat = null;
        } else {
            this.repeat = buttonState6;
        }
        if ((i & 64) == 0) {
            this.shuffle = null;
        } else {
            this.shuffle = buttonState7;
        }
        if ((i & 128) == 0) {
            this.playbackSpeed = null;
        } else {
            this.playbackSpeed = buttonState8;
        }
        if ((i & 256) == 0) {
            this.playbackSpeedOptions = null;
        } else {
            this.playbackSpeedOptions = list;
        }
    }

    public SkyfirePlaybackControlsModel(ButtonState buttonState, ButtonState buttonState2, ButtonState buttonState3, ButtonState buttonState4, ButtonState buttonState5, ButtonState buttonState6, ButtonState buttonState7, ButtonState buttonState8, List<PlaybackSpeedOption> list) {
        this.play = buttonState;
        this.next = buttonState2;
        this.previous = buttonState3;
        this.skipBackward = buttonState4;
        this.skipForward = buttonState5;
        this.repeat = buttonState6;
        this.shuffle = buttonState7;
        this.playbackSpeed = buttonState8;
        this.playbackSpeedOptions = list;
    }

    @JvmStatic
    public static final void write$Self(SkyfirePlaybackControlsModel self, CompositeEncoder output, SerialDescriptor serialDesc) {
        Intrinsics.checkNotNullParameter(self, "self");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
        if (output.shouldEncodeElementDefault(serialDesc, 0) || self.play != null) {
            output.encodeNullableSerializableElement(serialDesc, 0, new EnumSerializer("com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel.ButtonState", ButtonState.values()), self.play);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || self.next != null) {
            output.encodeNullableSerializableElement(serialDesc, 1, new EnumSerializer("com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel.ButtonState", ButtonState.values()), self.next);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || self.previous != null) {
            output.encodeNullableSerializableElement(serialDesc, 2, new EnumSerializer("com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel.ButtonState", ButtonState.values()), self.previous);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 3) || self.skipBackward != null) {
            output.encodeNullableSerializableElement(serialDesc, 3, new EnumSerializer("com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel.ButtonState", ButtonState.values()), self.skipBackward);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 4) || self.skipForward != null) {
            output.encodeNullableSerializableElement(serialDesc, 4, new EnumSerializer("com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel.ButtonState", ButtonState.values()), self.skipForward);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 5) || self.repeat != null) {
            output.encodeNullableSerializableElement(serialDesc, 5, new EnumSerializer("com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel.ButtonState", ButtonState.values()), self.repeat);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 6) || self.shuffle != null) {
            output.encodeNullableSerializableElement(serialDesc, 6, new EnumSerializer("com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel.ButtonState", ButtonState.values()), self.shuffle);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 7) || self.playbackSpeed != null) {
            output.encodeNullableSerializableElement(serialDesc, 7, new EnumSerializer("com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel.ButtonState", ButtonState.values()), self.playbackSpeed);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 8) || self.playbackSpeedOptions != null) {
            output.encodeNullableSerializableElement(serialDesc, 8, new ArrayListSerializer(SkyfirePlaybackControlsModel$PlaybackSpeedOption$$serializer.INSTANCE), self.playbackSpeedOptions);
        }
    }

    public /* synthetic */ SkyfirePlaybackControlsModel(ButtonState buttonState, ButtonState buttonState2, ButtonState buttonState3, ButtonState buttonState4, ButtonState buttonState5, ButtonState buttonState6, ButtonState buttonState7, ButtonState buttonState8, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : buttonState, (i & 2) != 0 ? null : buttonState2, (i & 4) != 0 ? null : buttonState3, (i & 8) != 0 ? null : buttonState4, (i & 16) != 0 ? null : buttonState5, (i & 32) != 0 ? null : buttonState6, (i & 64) != 0 ? null : buttonState7, (i & 128) != 0 ? null : buttonState8, (i & 256) == 0 ? list : null);
    }

    public final ButtonState getPlay() {
        return this.play;
    }

    public final void setPlay(ButtonState buttonState) {
        this.play = buttonState;
    }

    public final ButtonState getNext() {
        return this.next;
    }

    public final void setNext(ButtonState buttonState) {
        this.next = buttonState;
    }

    public final ButtonState getPrevious() {
        return this.previous;
    }

    public final void setPrevious(ButtonState buttonState) {
        this.previous = buttonState;
    }

    public final ButtonState getSkipBackward() {
        return this.skipBackward;
    }

    public final void setSkipBackward(ButtonState buttonState) {
        this.skipBackward = buttonState;
    }

    public final ButtonState getSkipForward() {
        return this.skipForward;
    }

    public final void setSkipForward(ButtonState buttonState) {
        this.skipForward = buttonState;
    }

    public final ButtonState getRepeat() {
        return this.repeat;
    }

    public final void setRepeat(ButtonState buttonState) {
        this.repeat = buttonState;
    }

    public final ButtonState getShuffle() {
        return this.shuffle;
    }

    public final void setShuffle(ButtonState buttonState) {
        this.shuffle = buttonState;
    }

    public final ButtonState getPlaybackSpeed() {
        return this.playbackSpeed;
    }

    public final void setPlaybackSpeed(ButtonState buttonState) {
        this.playbackSpeed = buttonState;
    }

    public final List<PlaybackSpeedOption> getPlaybackSpeedOptions() {
        return this.playbackSpeedOptions;
    }

    public final void setPlaybackSpeedOptions(List<PlaybackSpeedOption> list) {
        this.playbackSpeedOptions = list;
    }

    /* JADX INFO: compiled from: PlaybackModels.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u0000 \u00052\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel$ButtonState;", "", "(Ljava/lang/String;I)V", "ENABLED", "DISABLED", "Companion", "DMSFCoreAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum ButtonState {
        ENABLED,
        DISABLED;


        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        /* JADX INFO: compiled from: PlaybackModels.kt */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0017\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel$ButtonState$Companion;", "", "()V", "from", "Lcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel$ButtonState;", "isDisabled", "", "(Ljava/lang/Boolean;)Lcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel$ButtonState;", "DMSFCoreAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final ButtonState from(Boolean isDisabled) {
                if (Intrinsics.areEqual(isDisabled, Boolean.FALSE)) {
                    return ButtonState.ENABLED;
                }
                if (Intrinsics.areEqual(isDisabled, Boolean.TRUE)) {
                    return ButtonState.DISABLED;
                }
                return null;
            }
        }
    }

    /* JADX INFO: compiled from: PlaybackModels.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00182\u00020\u0001:\u0002\u0017\u0018B+\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\u000bJ!\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016HÇ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel$PlaybackSpeedOption;", "", "seen1", "", "text", "", "speed", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;DLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;D)V", "getSpeed", "()D", "getText", "()Ljava/lang/String;", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "DMSFCoreAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    @Serializable
    public static final class PlaybackSpeedOption {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final double speed;
        private final String text;

        /* JADX INFO: compiled from: PlaybackModels.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel$PlaybackSpeedOption$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel$PlaybackSpeedOption;", "DMSFCoreAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<PlaybackSpeedOption> serializer() {
                return SkyfirePlaybackControlsModel$PlaybackSpeedOption$$serializer.INSTANCE;
            }
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ PlaybackSpeedOption(int i, String str, double d, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, SkyfirePlaybackControlsModel$PlaybackSpeedOption$$serializer.INSTANCE.getDescriptor());
            }
            this.text = str;
            this.speed = d;
        }

        public PlaybackSpeedOption(String text, double d) {
            Intrinsics.checkNotNullParameter(text, "text");
            this.text = text;
            this.speed = d;
        }

        @JvmStatic
        public static final void write$Self(PlaybackSpeedOption self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Intrinsics.checkNotNullParameter(self, "self");
            Intrinsics.checkNotNullParameter(output, "output");
            Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
            output.encodeStringElement(serialDesc, 0, self.text);
            output.encodeDoubleElement(serialDesc, 1, self.speed);
        }

        public final double getSpeed() {
            return this.speed;
        }

        public final String getText() {
            return this.text;
        }
    }

    /* JADX INFO: compiled from: PlaybackModels.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\nHÆ\u0001R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel$Companion;", "", "()V", "DEFAULT_PLAYBACK_SPEED", "", "fromControlStatesElement", "Lcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel;", "element", "LPlaybackInterface/v1_0/MediaControlStatesElement;", "serializer", "Lkotlinx/serialization/KSerializer;", "DMSFCoreAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<SkyfirePlaybackControlsModel> serializer() {
            return SkyfirePlaybackControlsModel$$serializer.INSTANCE;
        }

        public final SkyfirePlaybackControlsModel fromControlStatesElement(MediaControlStatesElement element) {
            PlaybackSpeedControlStateElement playbackSpeedControlStateElementPlaybackSpeed;
            List<PlaybackSpeedControlOptionElement> listOptions;
            ArrayList arrayList;
            PlayControlStateElement playControlStateElementPlay;
            NextControlStateElement next;
            PreviousControlStateElement previousControlStateElementPrevious;
            SkipBackwardControlStateElement skipBackwardControlStateElementSkipBackward;
            SkipForwardControlStateElement skipForwardControlStateElementSkipForward;
            RepeatControlStateElement repeatControlStateElementRepeat;
            ShuffleControlStateElement shuffleControlStateElementShuffle;
            PlaybackSpeedControlStateElement playbackSpeedControlStateElementPlaybackSpeed2;
            Boolean boolIsDisabled = null;
            if (element == null || (playbackSpeedControlStateElementPlaybackSpeed = element.playbackSpeed()) == null || (listOptions = playbackSpeedControlStateElementPlaybackSpeed.options()) == null) {
                arrayList = null;
            } else {
                ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listOptions, 10));
                for (PlaybackSpeedControlOptionElement playbackSpeedControlOptionElement : listOptions) {
                    String strText = playbackSpeedControlOptionElement.text();
                    Intrinsics.checkNotNullExpressionValue(strText, "option.text()");
                    Double dSpeed = playbackSpeedControlOptionElement.speed();
                    Intrinsics.checkNotNullExpressionValue(dSpeed, "option.speed()");
                    arrayList2.add(new PlaybackSpeedOption(strText, dSpeed.doubleValue()));
                }
                arrayList = arrayList2;
            }
            ButtonState.Companion companion = ButtonState.INSTANCE;
            ButtonState buttonStateFrom = companion.from((element == null || (playControlStateElementPlay = element.play()) == null) ? null : playControlStateElementPlay.isDisabled());
            ButtonState buttonStateFrom2 = companion.from((element == null || (next = element.next()) == null) ? null : next.isDisabled());
            ButtonState buttonStateFrom3 = companion.from((element == null || (previousControlStateElementPrevious = element.previous()) == null) ? null : previousControlStateElementPrevious.isDisabled());
            ButtonState buttonStateFrom4 = companion.from((element == null || (skipBackwardControlStateElementSkipBackward = element.skipBackward()) == null) ? null : skipBackwardControlStateElementSkipBackward.isDisabled());
            ButtonState buttonStateFrom5 = companion.from((element == null || (skipForwardControlStateElementSkipForward = element.skipForward()) == null) ? null : skipForwardControlStateElementSkipForward.isDisabled());
            ButtonState buttonStateFrom6 = companion.from((element == null || (repeatControlStateElementRepeat = element.repeat()) == null) ? null : repeatControlStateElementRepeat.isDisabled());
            ButtonState buttonStateFrom7 = companion.from((element == null || (shuffleControlStateElementShuffle = element.shuffle()) == null) ? null : shuffleControlStateElementShuffle.isDisabled());
            if (element != null && (playbackSpeedControlStateElementPlaybackSpeed2 = element.playbackSpeed()) != null) {
                boolIsDisabled = playbackSpeedControlStateElementPlaybackSpeed2.isDisabled();
            }
            return new SkyfirePlaybackControlsModel(buttonStateFrom, buttonStateFrom2, buttonStateFrom3, buttonStateFrom4, buttonStateFrom5, buttonStateFrom6, buttonStateFrom7, companion.from(boolIsDisabled), arrayList);
        }
    }
}
