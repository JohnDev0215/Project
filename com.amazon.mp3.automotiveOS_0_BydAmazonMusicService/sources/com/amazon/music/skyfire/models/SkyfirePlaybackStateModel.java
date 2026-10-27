package com.amazon.music.skyfire.models;

import androidx.core.app.FrameMetricsAggregator;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel;
import com.google.android.gms.cast.MediaError;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.EnumSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: PlaybackModels.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 H2\u00020\u0001:\u0002GHB\u008f\u0001\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u000f\u001a\u00020\n\u0012\u0006\u0010\u0010\u001a\u00020\n\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018¢\u0006\u0002\u0010\u0019B\u008b\u0001\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u000f\u001a\u00020\n\u0012\b\b\u0002\u0010\u0010\u001a\u00020\n\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0016¢\u0006\u0002\u0010\u001aJ!\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020\u00002\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020FHÇ\u0001R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001c\"\u0004\b \u0010\u001eR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001c\"\u0004\b\"\u0010\u001eR\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010)\"\u0004\b*\u0010+R\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001a\u0010\u000b\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010&\"\u0004\b1\u0010(R\u001a\u0010\u0010\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010&\"\u0004\b3\u0010(R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010\u001c\"\u0004\b=\u0010\u001eR\u001a\u0010\u000f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010&\"\u0004\b?\u0010(¨\u0006I"}, d2 = {"Lcom/amazon/music/skyfire/models/SkyfirePlaybackStateModel;", "", "seen1", "", "title", "", "albumTitle", "artist", "playbackDuration", "elapsedPlaybackTime", "", "playbackRate", "isLoading", "", "artworkUrl", MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, "playbackSpeed", "shuffleState", "Lcom/amazon/music/skyfire/models/ShuffleControlState;", "repeatState", "Lcom/amazon/music/skyfire/models/RepeatControlState;", "controls", "Lcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;IFFZLjava/lang/String;FFLcom/amazon/music/skyfire/models/ShuffleControlState;Lcom/amazon/music/skyfire/models/RepeatControlState;Lcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IFFZLjava/lang/String;FFLcom/amazon/music/skyfire/models/ShuffleControlState;Lcom/amazon/music/skyfire/models/RepeatControlState;Lcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel;)V", "getAlbumTitle", "()Ljava/lang/String;", "setAlbumTitle", "(Ljava/lang/String;)V", "getArtist", "setArtist", "getArtworkUrl", "setArtworkUrl", "getControls", "()Lcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel;", "getElapsedPlaybackTime", "()F", "setElapsedPlaybackTime", "(F)V", "()Z", "setLoading", "(Z)V", "getPlaybackDuration", "()I", "setPlaybackDuration", "(I)V", "getPlaybackRate", "setPlaybackRate", "getPlaybackSpeed", "setPlaybackSpeed", "getRepeatState", "()Lcom/amazon/music/skyfire/models/RepeatControlState;", "setRepeatState", "(Lcom/amazon/music/skyfire/models/RepeatControlState;)V", "getShuffleState", "()Lcom/amazon/music/skyfire/models/ShuffleControlState;", "setShuffleState", "(Lcom/amazon/music/skyfire/models/ShuffleControlState;)V", "getTitle", "setTitle", "getVolume", "setVolume", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "DMSFCoreAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
@Serializable
public final class SkyfirePlaybackStateModel {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private String albumTitle;
    private String artist;
    private String artworkUrl;
    private final SkyfirePlaybackControlsModel controls;
    private float elapsedPlaybackTime;
    private boolean isLoading;
    private int playbackDuration;
    private float playbackRate;
    private float playbackSpeed;
    private RepeatControlState repeatState;
    private ShuffleControlState shuffleState;
    private String title;
    private float volume;

    /* JADX INFO: compiled from: PlaybackModels.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/skyfire/models/SkyfirePlaybackStateModel$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/amazon/music/skyfire/models/SkyfirePlaybackStateModel;", "DMSFCoreAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<SkyfirePlaybackStateModel> serializer() {
            return SkyfirePlaybackStateModel$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ SkyfirePlaybackStateModel(int i, String str, String str2, String str3, int i2, float f, float f2, boolean z, String str4, float f3, float f4, ShuffleControlState shuffleControlState, RepeatControlState repeatControlState, SkyfirePlaybackControlsModel skyfirePlaybackControlsModel, SerializationConstructorMarker serializationConstructorMarker) {
        if (312 != (i & MediaError.DetailedErrorCode.HLS_NETWORK_PLAYLIST)) {
            PluginExceptionsKt.throwMissingFieldException(i, MediaError.DetailedErrorCode.HLS_NETWORK_PLAYLIST, SkyfirePlaybackStateModel$$serializer.INSTANCE.getDescriptor());
        }
        if ((i & 1) == 0) {
            this.title = null;
        } else {
            this.title = str;
        }
        if ((i & 2) == 0) {
            this.albumTitle = null;
        } else {
            this.albumTitle = str2;
        }
        if ((i & 4) == 0) {
            this.artist = null;
        } else {
            this.artist = str3;
        }
        this.playbackDuration = i2;
        this.elapsedPlaybackTime = f;
        this.playbackRate = f2;
        this.isLoading = (i & 64) == 0 ? false : z;
        if ((i & 128) == 0) {
            this.artworkUrl = null;
        } else {
            this.artworkUrl = str4;
        }
        this.volume = f3;
        this.playbackSpeed = (i & 512) == 0 ? 1.0f : f4;
        if ((i & 1024) == 0) {
            this.shuffleState = null;
        } else {
            this.shuffleState = shuffleControlState;
        }
        if ((i & 2048) == 0) {
            this.repeatState = null;
        } else {
            this.repeatState = repeatControlState;
        }
        this.controls = (i & 4096) == 0 ? new SkyfirePlaybackControlsModel((SkyfirePlaybackControlsModel.ButtonState) null, (SkyfirePlaybackControlsModel.ButtonState) null, (SkyfirePlaybackControlsModel.ButtonState) null, (SkyfirePlaybackControlsModel.ButtonState) null, (SkyfirePlaybackControlsModel.ButtonState) null, (SkyfirePlaybackControlsModel.ButtonState) null, (SkyfirePlaybackControlsModel.ButtonState) null, (SkyfirePlaybackControlsModel.ButtonState) null, (List) null, FrameMetricsAggregator.EVERY_DURATION, (DefaultConstructorMarker) null) : skyfirePlaybackControlsModel;
    }

    public SkyfirePlaybackStateModel(String str, String str2, String str3, int i, float f, float f2, boolean z, String str4, float f3, float f4, ShuffleControlState shuffleControlState, RepeatControlState repeatControlState, SkyfirePlaybackControlsModel controls) {
        Intrinsics.checkNotNullParameter(controls, "controls");
        this.title = str;
        this.albumTitle = str2;
        this.artist = str3;
        this.playbackDuration = i;
        this.elapsedPlaybackTime = f;
        this.playbackRate = f2;
        this.isLoading = z;
        this.artworkUrl = str4;
        this.volume = f3;
        this.playbackSpeed = f4;
        this.shuffleState = shuffleControlState;
        this.repeatState = repeatControlState;
        this.controls = controls;
    }

    @JvmStatic
    public static final void write$Self(SkyfirePlaybackStateModel self, CompositeEncoder output, SerialDescriptor serialDesc) {
        Intrinsics.checkNotNullParameter(self, "self");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
        if (output.shouldEncodeElementDefault(serialDesc, 0) || self.title != null) {
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.title);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || self.albumTitle != null) {
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.albumTitle);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || self.artist != null) {
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.artist);
        }
        output.encodeIntElement(serialDesc, 3, self.playbackDuration);
        output.encodeFloatElement(serialDesc, 4, self.elapsedPlaybackTime);
        output.encodeFloatElement(serialDesc, 5, self.playbackRate);
        if (output.shouldEncodeElementDefault(serialDesc, 6) || self.isLoading) {
            output.encodeBooleanElement(serialDesc, 6, self.isLoading);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 7) || self.artworkUrl != null) {
            output.encodeNullableSerializableElement(serialDesc, 7, StringSerializer.INSTANCE, self.artworkUrl);
        }
        output.encodeFloatElement(serialDesc, 8, self.volume);
        if (output.shouldEncodeElementDefault(serialDesc, 9) || !Intrinsics.areEqual((Object) Float.valueOf(self.playbackSpeed), (Object) Float.valueOf(1.0f))) {
            output.encodeFloatElement(serialDesc, 9, self.playbackSpeed);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 10) || self.shuffleState != null) {
            output.encodeNullableSerializableElement(serialDesc, 10, new EnumSerializer("com.amazon.music.skyfire.models.ShuffleControlState", ShuffleControlState.values()), self.shuffleState);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 11) || self.repeatState != null) {
            output.encodeNullableSerializableElement(serialDesc, 11, new EnumSerializer("com.amazon.music.skyfire.models.RepeatControlState", RepeatControlState.values()), self.repeatState);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 12) || !Intrinsics.areEqual(self.controls, new SkyfirePlaybackControlsModel((SkyfirePlaybackControlsModel.ButtonState) null, (SkyfirePlaybackControlsModel.ButtonState) null, (SkyfirePlaybackControlsModel.ButtonState) null, (SkyfirePlaybackControlsModel.ButtonState) null, (SkyfirePlaybackControlsModel.ButtonState) null, (SkyfirePlaybackControlsModel.ButtonState) null, (SkyfirePlaybackControlsModel.ButtonState) null, (SkyfirePlaybackControlsModel.ButtonState) null, (List) null, FrameMetricsAggregator.EVERY_DURATION, (DefaultConstructorMarker) null))) {
            output.encodeSerializableElement(serialDesc, 12, SkyfirePlaybackControlsModel$$serializer.INSTANCE, self.controls);
        }
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String str) {
        this.title = str;
    }

    public final String getAlbumTitle() {
        return this.albumTitle;
    }

    public final void setAlbumTitle(String str) {
        this.albumTitle = str;
    }

    public final String getArtist() {
        return this.artist;
    }

    public final void setArtist(String str) {
        this.artist = str;
    }

    public final int getPlaybackDuration() {
        return this.playbackDuration;
    }

    public final void setPlaybackDuration(int i) {
        this.playbackDuration = i;
    }

    public final float getElapsedPlaybackTime() {
        return this.elapsedPlaybackTime;
    }

    public final void setElapsedPlaybackTime(float f) {
        this.elapsedPlaybackTime = f;
    }

    public final float getPlaybackRate() {
        return this.playbackRate;
    }

    public final void setPlaybackRate(float f) {
        this.playbackRate = f;
    }

    /* JADX INFO: renamed from: isLoading, reason: from getter */
    public final boolean getIsLoading() {
        return this.isLoading;
    }

    public final void setLoading(boolean z) {
        this.isLoading = z;
    }

    public final String getArtworkUrl() {
        return this.artworkUrl;
    }

    public final void setArtworkUrl(String str) {
        this.artworkUrl = str;
    }

    public final float getVolume() {
        return this.volume;
    }

    public final void setVolume(float f) {
        this.volume = f;
    }

    public final float getPlaybackSpeed() {
        return this.playbackSpeed;
    }

    public final void setPlaybackSpeed(float f) {
        this.playbackSpeed = f;
    }

    public final ShuffleControlState getShuffleState() {
        return this.shuffleState;
    }

    public final void setShuffleState(ShuffleControlState shuffleControlState) {
        this.shuffleState = shuffleControlState;
    }

    public final RepeatControlState getRepeatState() {
        return this.repeatState;
    }

    public final void setRepeatState(RepeatControlState repeatControlState) {
        this.repeatState = repeatControlState;
    }

    public /* synthetic */ SkyfirePlaybackStateModel(String str, String str2, String str3, int i, float f, float f2, boolean z, String str4, float f3, float f4, ShuffleControlState shuffleControlState, RepeatControlState repeatControlState, SkyfirePlaybackControlsModel skyfirePlaybackControlsModel, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, i, f, f2, (i2 & 64) != 0 ? false : z, (i2 & 128) != 0 ? null : str4, f3, (i2 & 512) != 0 ? 1.0f : f4, (i2 & 1024) != 0 ? null : shuffleControlState, (i2 & 2048) != 0 ? null : repeatControlState, (i2 & 4096) != 0 ? new SkyfirePlaybackControlsModel((SkyfirePlaybackControlsModel.ButtonState) null, (SkyfirePlaybackControlsModel.ButtonState) null, (SkyfirePlaybackControlsModel.ButtonState) null, (SkyfirePlaybackControlsModel.ButtonState) null, (SkyfirePlaybackControlsModel.ButtonState) null, (SkyfirePlaybackControlsModel.ButtonState) null, (SkyfirePlaybackControlsModel.ButtonState) null, (SkyfirePlaybackControlsModel.ButtonState) null, (List) null, FrameMetricsAggregator.EVERY_DURATION, (DefaultConstructorMarker) null) : skyfirePlaybackControlsModel);
    }

    public final SkyfirePlaybackControlsModel getControls() {
        return this.controls;
    }
}
