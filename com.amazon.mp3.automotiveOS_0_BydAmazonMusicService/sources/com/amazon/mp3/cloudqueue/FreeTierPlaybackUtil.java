package com.amazon.mp3.cloudqueue;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.Nullable;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifier;
import com.amazon.mp3.cloudqueue.sequencer.FreeTierSequencer;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.util.TrackUtil;
import com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellInformation;
import com.amazon.mp3.providers.CatalogEnabilityProvider;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.view.player.NowPlayingViewConfig;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.PlaybackProvider;
import com.amazon.music.media.playback.SequencerPlaybackProvider;
import com.amazon.music.media.playback.sequencer.Sequencer;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class FreeTierPlaybackUtil {
    private static final String TAG = "FreeTierPlaybackUtil";

    public static void playStationV2(final Context context, final List<PlayableEntityIdentifier> playableEntityIdentifierList, final UpsellInformation upsellInformation, final boolean startFullScreen) {
        playCloudStation(context, playableEntityIdentifierList, true, true, startFullScreen, PlaybackPageType.SFA, Clock.now(), upsellInformation);
    }

    public static void playCloudStation(final Context context, final PlayableEntityIdentifier playableEntityIdentifier, final boolean startPlayback, final boolean isOnDemandPlayMode, final boolean startFullscreen, final PlaybackPageType playbackPageType) {
        playCloudStation(context, playableEntityIdentifier, startPlayback, isOnDemandPlayMode, startFullscreen, playbackPageType, Clock.now(), (MediaCollectionType) null);
    }

    public static void playCloudStation(final Context context, final PlayableEntityIdentifier playableEntityIdentifier, final boolean startPlayback, final boolean isOnDemandPlayMode, final boolean startFullscreen, final PlaybackPageType playbackPageType, final MediaCollectionType collectionType) {
        playCloudStation(context, playableEntityIdentifier, startPlayback, isOnDemandPlayMode, startFullscreen, playbackPageType, Clock.now(), collectionType);
    }

    public static void playCloudStation(final Context context, final PlayableEntityIdentifier playableEntityIdentifier, final boolean startPlayback, final boolean isOnDemandPlayMode, final boolean startFullscreen, final PlaybackPageType playbackPageType, final long startTime, final MediaCollectionType collectionType) {
        playCloudStation(context, Collections.singletonList(playableEntityIdentifier), startPlayback, isOnDemandPlayMode, startFullscreen, playbackPageType, startTime, null, collectionType);
    }

    public static void playCloudStation(final Context context, final List<PlayableEntityIdentifier> playableEntityIdentifierList, final boolean startPlayback, final boolean isOnDemandPlayMode, final boolean startFullscreen, final PlaybackPageType playbackPageType, final long startTime, final UpsellInformation upsellInformation) {
        playCloudStation(context, playableEntityIdentifierList, startPlayback, isOnDemandPlayMode, startFullscreen, playbackPageType, startTime, upsellInformation, null);
    }

    public static void playCloudStation(final Context context, final List<PlayableEntityIdentifier> playableEntityIdentifierList, final boolean startPlayback, final boolean isOnDemandPlayMode, final boolean startFullscreen, final PlaybackPageType playbackPageType, final long startTime, final UpsellInformation upsellInformation, final MediaCollectionType collectionType) {
        playCloudStation(context, ControlSource.APP_UI, playableEntityIdentifierList, null, startPlayback, isOnDemandPlayMode, startFullscreen, playbackPageType, startTime, upsellInformation, collectionType, isOnDemandPlayMode, null, null);
    }

    public static void startPlaylistOnDemand(final Context context, final PlayableEntityIdentifier playableEntityIdentifier, final boolean startPlayback, final boolean isOnDemandPlayMode, final boolean startFullscreen, final PlaybackPageType playbackPageType, final MediaCollectionType collectionType, final String startAtAsin) {
        playCloudStation(context, null, Collections.singletonList(playableEntityIdentifier), null, startPlayback, isOnDemandPlayMode, startFullscreen, playbackPageType, Clock.now(), null, collectionType, false, startAtAsin, null);
    }

    public static void startPlaylistOnDemand(final Context context, final ControlSource controlSource, final PlayableEntityIdentifier playableEntityIdentifier, final boolean startPlayback, final boolean isOnDemandPlayMode, final boolean startFullscreen, final PlaybackPageType playbackPageType, final MediaCollectionType collectionType, final String startAtAsin) {
        playCloudStation(context, controlSource, Collections.singletonList(playableEntityIdentifier), null, startPlayback, isOnDemandPlayMode, startFullscreen, playbackPageType, Clock.now(), null, collectionType, false, startAtAsin, null);
    }

    public static void playCloudStation(final Context context, final ControlSource controlSource, final List<PlayableEntityIdentifier> playableEntityIdentifierList, final String pageToken, final boolean startPlayback, final boolean isOnDemandPlayMode, final boolean startFullscreen, final PlaybackPageType playbackPageType, final long startTime, final UpsellInformation upsellInformation, final MediaCollectionType collectionType, final boolean isShuffled, final String startAtAsin, @Nullable final MediaCollectionId additionalMediaCollectionId) {
        playCloudStation(context, controlSource, playableEntityIdentifierList, pageToken, startPlayback, isOnDemandPlayMode, startFullscreen, playbackPageType, startTime, upsellInformation, collectionType, isShuffled, startAtAsin, Collections.singletonList(additionalMediaCollectionId), null);
    }

    public static void playCloudStation(final Context context, final ControlSource controlSource, final List<PlayableEntityIdentifier> playableEntityIdentifierList, final String pageToken, final boolean startPlayback, final boolean isOnDemandPlayMode, final boolean startFullscreen, final PlaybackPageType playbackPageType, final long startTime, final UpsellInformation upsellInformation, final MediaCollectionType collectionType, final boolean isShuffled, final String startAtAsin, @Nullable final List<MediaCollectionId> additionalMediaCollectionIds, @Nullable final String queueTitle) {
        ThreadUtils.runInBackground(new Runnable() { // from class: com.amazon.mp3.cloudqueue.FreeTierPlaybackUtil.1
            @Override // java.lang.Runnable
            public void run() {
                SequencerPlaybackProvider sequencerPlaybackProvider;
                Playback playback = Playback.getInstance();
                PlaybackController playbackController = playback.getPlaybackController(controlSource);
                List list = playableEntityIdentifierList;
                if (list != null) {
                    try {
                        List<String> listExtractIdentifierStrings = FreeTierPlaybackUtil.extractIdentifierStrings(list);
                        List<String> stationIdentifiers = FreeTierPlaybackUtil.getStationIdentifiers(playback);
                        if (!listExtractIdentifierStrings.isEmpty() && listExtractIdentifierStrings.equals(stationIdentifiers)) {
                            boolean z = true;
                            boolean z2 = AmazonApplication.getCapabilities().isOnDemandPlaylistsEnabled() && GetOnDemandPlaylistsService.INSTANCE.get().isOnDemandPlaylist(listExtractIdentifierStrings.get(0));
                            if ((!z2 || (z2 && FreeTierPlaybackUtil.isAdCurrentlyPlaying(playbackController))) && playbackController.getCurrentMediaItem().getPlaybackPageType() == playbackPageType) {
                                String str = FreeTierPlaybackUtil.TAG;
                                StringBuilder sb = new StringBuilder();
                                sb.append("isOnDemandGoldenPlaylistCapabilitiesEnabled: ");
                                sb.append(AmazonApplication.getCapabilities().isOnDemandPlaylistsEnabled());
                                sb.append(" isOnDemandGoldenPlaylistServiceEnabled ");
                                sb.append(GetOnDemandPlaylistsService.INSTANCE.get().isOnDemandPlaylist(listExtractIdentifierStrings.get(0)));
                                sb.append(" isAdCurrentlyPlaying: ");
                                sb.append(FreeTierPlaybackUtil.isAdCurrentlyPlaying(playbackController));
                                sb.append(" isPlaybackPageTypeSame: ");
                                if (playbackController.getCurrentMediaItem().getPlaybackPageType() != playbackPageType) {
                                    z = false;
                                }
                                sb.append(z);
                                Log.debug(str, sb.toString());
                                FreeTierPlaybackUtil.resumeCloudStation(startFullscreen, context, playbackController);
                                return;
                            }
                        }
                    } catch (NullPointerException unused) {
                        Log.info(FreeTierPlaybackUtil.TAG, "No station currently playing, or new or currently playing station had no identifiers.");
                    }
                }
                FreeTierSequencer freeTierSequencerCreateCloudQueueSequencer = QueueEntityUtil.createCloudQueueSequencer(context, playbackPageType, isOnDemandPlayMode, playableEntityIdentifierList, pageToken, collectionType, ControlSource.ALEXA.equals(controlSource), isShuffled, startAtAsin, additionalMediaCollectionIds, queueTitle);
                PlaybackProvider playbackProvider = playback.getPlaybackProvider();
                if (playbackProvider instanceof SequencerPlaybackProvider) {
                    sequencerPlaybackProvider = (SequencerPlaybackProvider) playbackProvider;
                } else {
                    sequencerPlaybackProvider = (SequencerPlaybackProvider) playback.getPlaybackConfig().getDefaultPlaybackProvider(ChildUserUtil.INSTANCE.getMusicRequestIdentityContextToken(context));
                    playbackController.stop(ChangeReason.NEW_SOURCE);
                    playback.setPlaybackProvider(sequencerPlaybackProvider);
                }
                if (startPlayback) {
                    sequencerPlaybackProvider.startSequencer(freeTierSequencerCreateCloudQueueSequencer, playbackController.getControlSource(), startTime);
                } else {
                    sequencerPlaybackProvider.setSequencer(freeTierSequencerCreateCloudQueueSequencer, playbackController.getControlSource());
                }
                if (startFullscreen) {
                    Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(context, MediaProvider.NowPlaying.CONTENT_URI);
                    intentForContentUri.putExtra("com.amazon.music.station.EXTRA_SHOW_STATION_LOADING", startFullscreen);
                    UpsellInformation upsellInformation2 = upsellInformation;
                    if (upsellInformation2 != null) {
                        intentForContentUri.putExtra("com.amazon.music.playback.EXTRA_SHOW_UNLIMITED_UPSELL", upsellInformation2);
                    }
                    if (!(context instanceof Activity)) {
                        intentForContentUri.setFlags(268435456);
                    }
                    context.startActivity(intentForContentUri);
                    Context context2 = context;
                    if (context2 instanceof Activity) {
                        ((Activity) context2).overridePendingTransition(R.anim.slide_in_up, R.anim.fade_out);
                    }
                }
            }
        });
    }

    public static List<String> extractIdentifierStrings(final List<PlayableEntityIdentifier> playableEntityIdentifierList) {
        ArrayList arrayList = new ArrayList();
        Iterator<PlayableEntityIdentifier> it = playableEntityIdentifierList.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getIdentifier());
        }
        return arrayList;
    }

    public static List<String> getStationIdentifiers(final Playback playback) {
        PlaybackProvider playbackProvider = playback.getPlaybackProvider();
        if (playbackProvider instanceof SequencerPlaybackProvider) {
            Sequencer sequencer = ((SequencerPlaybackProvider) playbackProvider).getSequencer();
            if (sequencer instanceof FreeTierSequencer) {
                return extractIdentifierStrings(((FreeTierSequencer) sequencer).getQueuePlayableEntityIdentifierList());
            }
        }
        return new ArrayList();
    }

    public static void resumeCloudStation(final boolean startFullscreen, final Context context, final PlaybackController playbackController) {
        if (startFullscreen) {
            context.startActivity(LibraryActivityFactory.getIntentForContentUri(context, MediaProvider.NowPlaying.CONTENT_URI));
            if (context instanceof Activity) {
                ((Activity) context).overridePendingTransition(R.anim.slide_in_up, R.anim.fade_out);
            }
        }
        playbackController.play();
    }

    public static boolean canHandleAdClick(@Nullable Uri adUri) {
        Context context = AmazonApplication.getContext();
        if (adUri != null && context != null) {
            if (new Intent("android.intent.action.VIEW", adUri).resolveActivity(context.getPackageManager()) != null) {
                return true;
            }
            Log.error(TAG, "can not handle ad uri " + adUri);
        }
        return false;
    }

    public static boolean isAdCurrentlyPlaying(final PlaybackController playbackController) {
        return playbackController != null && NowPlayingViewConfig.PlayerContentType.fromMediaItem(playbackController.getCurrentMediaItem()) == NowPlayingViewConfig.PlayerContentType.AD;
    }

    public static boolean shouldBlockPlaybackAndDownload(ContentEnabilityProvider contentEnabilityProvider, ContentMetadata metadata, List<TrackMetadata> trackMetadataList, boolean isSourceLocal) {
        if (contentEnabilityProvider == null) {
            contentEnabilityProvider = new CatalogEnabilityProvider();
        }
        boolean zShouldEnableContent = contentEnabilityProvider.shouldEnableContent(metadata);
        Integer numValueOf = null;
        if (metadata instanceof PlaylistMetadata) {
            numValueOf = ((PlaylistMetadata) metadata).getTrackCount();
        } else if (metadata instanceof AlbumMetadata) {
            numValueOf = ((AlbumMetadata) metadata).getTrackCount();
        } else if (metadata instanceof ArtistMetadata) {
            Long trackCount = ((ArtistMetadata) metadata).getTrackCount();
            numValueOf = Integer.valueOf(trackCount == null ? 0 : trackCount.intValue());
        }
        return (UserSubscriptionUtil.isNightwingOnly() && AmazonApplication.getCapabilities().isFreeTierUserPlaylistEditingEnabled()) && !(zShouldEnableContent && (numValueOf != null && numValueOf.intValue() > 0) && TrackUtil.containsAtLeastOneAvailableTrack(trackMetadataList, Boolean.valueOf(isSourceLocal ^ true), contentEnabilityProvider));
    }
}
