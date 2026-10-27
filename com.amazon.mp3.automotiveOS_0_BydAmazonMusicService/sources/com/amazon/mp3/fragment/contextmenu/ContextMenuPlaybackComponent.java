package com.amazon.mp3.fragment.contextmenu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.navigation.DeeplinkIntentKey;
import com.amazon.mp3.playback.PlaybackListener;
import com.amazon.mp3.playback.harley.PlayQueueSequencer;
import com.amazon.mp3.playback.service.metrics.PlaybackMetricInformation;
import com.amazon.mp3.playback.start.StartPlaybackUtil;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.metrics.mts.event.types.DirectedPlayStatus;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;

/* JADX INFO: loaded from: classes3.dex */
public class ContextMenuPlaybackComponent {
    public static void startPlayback(final Context context, final Uri contentUri, int position, @Nullable String sortOrder, @Nullable PlaybackMetricInformation metricInformation, PlaybackPageType playbackpageType, final long startTime, boolean isFullScreen, final boolean isVideo, final Uri collectionUri) {
        startPlayback(context, contentUri, position, sortOrder, metricInformation, playbackpageType, startTime, isFullScreen, false, isVideo, collectionUri);
    }

    public static void startPlayback(final Context context, final Uri contentUri, int position, @Nullable String sortOrder, @Nullable PlaybackMetricInformation metricInformation, PlaybackPageType playbackpageType, final long startTime, boolean isFullScreen, final boolean isVideo, final Uri collectionUri, @Nullable ControlSource playbackControlSource, @Nullable final PlaybackListener playbackListener) {
        startPlayback(context, contentUri, position, sortOrder, metricInformation, playbackpageType, startTime, isFullScreen, false, isVideo, collectionUri, playbackControlSource == null ? ControlSource.APP_UI : playbackControlSource, playbackListener);
    }

    public static void startPlayback(final Context context, final Uri contentUri, int position, @Nullable String sortOrder, @Nullable PlaybackMetricInformation metricInformation, PlaybackPageType playbackpageType, final long startTime, boolean isFullScreen, boolean shuffle, final boolean isVideo, final Uri collectionUri) {
        startPlayback(context, contentUri, position, sortOrder, metricInformation, playbackpageType, startTime, isFullScreen, shuffle, isVideo, collectionUri, ControlSource.APP_UI, null);
    }

    @SuppressLint({"CheckResult"})
    public static void startPlayback(final Context context, final Uri contentUri, final int position, @Nullable final String sortOrder, @Nullable PlaybackMetricInformation metricInformation, PlaybackPageType playbackpageType, final long startTime, boolean isFullScreen, final boolean shuffle, final boolean isVideo, final Uri collectionUri, @NonNull final ControlSource playbackControlSource, @Nullable final PlaybackListener playbackListener) {
        Context context2;
        PlaybackMetricInformation playbackMetricInformation = metricInformation == null ? new PlaybackMetricInformation(DirectedPlayStatus.NOT_DIRECTED, playbackpageType) : metricInformation;
        if (AmazonApplication.getCapabilities().isCloudQueuePlaybackEnabled()) {
            final PlaybackMetricInformation playbackMetricInformation2 = playbackMetricInformation;
            context2 = context;
            StartPlaybackUtil.startCollectionPlayback(context2, null, collectionUri, null, position, shuffle, false, sortOrder, playbackpageType, false, playbackControlSource, null, false, playbackListener, null, false, true, new NoOpCloudQueueClientHandler() { // from class: com.amazon.mp3.fragment.contextmenu.ContextMenuPlaybackComponent$$ExternalSyntheticLambda0
                @Override // com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler
                public final void handlePlayback() {
                    ContextMenuPlaybackComponent.lambda$startPlayback$0(playbackControlSource, contentUri, sortOrder, position, shuffle, playbackMetricInformation2, startTime);
                }
            });
        } else {
            if (!CastingUtil.isCastingToAlexa() || MediaProvider.Tracks.isTrack(contentUri)) {
                PlayQueueSequencer.getInstance().start(playbackControlSource, contentUri, null, null, sortOrder, position, shuffle, playbackMetricInformation, startTime);
            } else {
                PlayQueueSequencer.getInstance().start(playbackControlSource, contentUri, null, null, sortOrder, position, shuffle, playbackMetricInformation, startTime);
            }
            context2 = context;
        }
        if (context2 == null || !isFullScreen) {
            return;
        }
        Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(context2, MediaProvider.NowPlaying.CONTENT_URI);
        if (context2 == context.getApplicationContext()) {
            intentForContentUri.addFlags(268435456);
        }
        FragmentActivity fragmentActivity = (FragmentActivity) context2;
        if (fragmentActivity.getIntent() != null && fragmentActivity.getIntent().getExtras() != null && !fragmentActivity.isFinishing()) {
            DeeplinkIntentKey deeplinkIntentKey = DeeplinkIntentKey.KEY_ASSOCIATE_TAG;
            intentForContentUri.putExtra(deeplinkIntentKey.toString(), fragmentActivity.getIntent().getStringExtra(deeplinkIntentKey.toString()));
            DeeplinkIntentKey deeplinkIntentKey2 = DeeplinkIntentKey.KEY_REF_MARKER;
            intentForContentUri.putExtra(deeplinkIntentKey2.toString(), fragmentActivity.getIntent().getStringExtra(deeplinkIntentKey2.toString()));
        }
        context2.startActivity(intentForContentUri);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$startPlayback$0(ControlSource controlSource, Uri uri, String str, int i, boolean z, PlaybackMetricInformation playbackMetricInformation, long j) {
        PlayQueueSequencer.getInstance().start(controlSource, uri, null, null, str, i, z, playbackMetricInformation, j);
    }
}
