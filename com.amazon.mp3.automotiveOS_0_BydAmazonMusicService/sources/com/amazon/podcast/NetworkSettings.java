package com.amazon.podcast;

import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.DialogActivity;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.playback.activity.StreamingNetworkPreferenceActivity;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.podcast.PodcastEndpointOverrideFactory;
import com.amazon.podcast.bootstrap.NetworkSettingsProvider;

/* JADX INFO: loaded from: classes5.dex */
public final class NetworkSettings extends NetworkSettingsProvider {
    @Override // com.amazon.podcast.bootstrap.NetworkSettingsProvider
    public boolean isStreamingRestricted(final Context context) {
        if (ConnectivityUtil.isWifiOnlyDevice(context)) {
            return false;
        }
        return !ConnectivityUtil.isWifiConnected(context) && "WIFI_ONLY".equals(SettingsUtil.getStreamingNetworkPreference(context, StreamingNetworkPreferenceActivity.Type.STREAMING_NETWORK_PREFERENCE_TYPE_AUDIO));
    }

    @Override // com.amazon.podcast.bootstrap.NetworkSettingsProvider
    public void showStreamingRestricted(final Context context) {
        if (context == null) {
            return;
        }
        Intent startIntent = DialogActivity.getStartIntent(context, R.string.dmusic_playback_error_streaming_network_dialog_header, R.string.dmusic_playback_error_streaming_network_dialog_detail);
        startIntent.addFlags(268435456);
        context.startActivity(startIntent);
    }

    @Override // com.amazon.podcast.bootstrap.NetworkSettingsProvider
    public boolean isOverridingEndpoint() {
        return SettingsUtil.isCustomEndpointEnabled(AmazonApplication.getContext()) && PodcastEndpointOverrideFactory.STAGE.GAMMA.equals(PodcastEndpointOverrideFactory.STAGE.valueOf(Environment.PODCAST.getCurrentOverrideId()));
    }
}
