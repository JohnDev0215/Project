package com.amazon.mp3.playback.harley;

import android.content.Context;
import com.amazon.mp3.playback.activity.StreamingNetworkPreferenceActivity;
import com.amazon.mp3.playback.service.PlaybackServiceUtil;
import com.amazon.music.media.playback.config.VideoStreamingSettingsProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PrimeVideoStreamingProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\n\u0010\u000bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/playback/harley/PrimeVideoStreamingSettingsProvider;", "Lcom/amazon/music/media/playback/config/VideoStreamingSettingsProvider;", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "", "isStreamingRestricted", "()Z", "<init>", "(Landroid/content/Context;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class PrimeVideoStreamingSettingsProvider implements VideoStreamingSettingsProvider {
    private final Context context;

    public PrimeVideoStreamingSettingsProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // com.amazon.music.media.playback.config.VideoStreamingSettingsProvider
    public boolean isStreamingRestricted() {
        return PlaybackServiceUtil.isStreamingRestricted(this.context, StreamingNetworkPreferenceActivity.Type.STREAMING_NETWORK_PREFERENCE_TYPE_VIDEO);
    }
}
