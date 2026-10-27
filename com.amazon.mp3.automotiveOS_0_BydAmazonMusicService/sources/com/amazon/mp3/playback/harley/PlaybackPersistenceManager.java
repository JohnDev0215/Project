package com.amazon.mp3.playback.harley;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.amazon.digitalmusicxp.clients.CloudQueueClient;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.basequeue.NoOpCloudQueueClient;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.cloudqueue.CloudQueueClientSingleton;
import com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil;
import com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.library.item.DeluxeContentUtil;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.nowplaying.AlexaSequencer;
import com.amazon.mp3.playback.service.metrics.PlaybackMetricInformation;
import com.amazon.mp3.prime.station.StationStorageUtil;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.StringUtil;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.PlaybackProvider;
import com.amazon.music.media.playback.RepeatMode;
import com.amazon.music.media.playback.SequencerPlaybackProvider;
import com.amazon.music.media.playback.config.SequencerPersistenceProvider;
import com.amazon.music.media.playback.sequencer.Sequencer;
import com.amazon.music.media.playback.sequencer.impl.PlaceholderSequencer;
import com.amazon.music.media.playback.sequencer.impl.StationSequencer;
import com.amazon.music.media.playback.util.AndroidUtils;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.FeatureGateProvider;
import io.reactivex.functions.Consumer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class PlaybackPersistenceManager extends SequencerPersistenceProvider {
    private static final String TAG = "PlaybackPersistenceManager";
    private static PlaybackPersistenceManager sPlaybackPersistenceManager;
    private boolean mHarleyReady;
    private String mricToken;
    private static final long SAVE_POSITION_INTERVAL_MILLIS = TimeUnit.SECONDS.toMillis(1);
    private static final long MAX_RETAIN_POSITION_MILLIS = TimeUnit.MINUTES.toMillis(30);
    private static final List<MediaItemId.Type> RECOVERABLE_ID_TYPES = Arrays.asList(MediaItemId.Type.ASIN, MediaItemId.Type.OWNED, MediaItemId.Type.LIVE, MediaItemId.Type.ONDEMAND, MediaItemId.Type.LUID, MediaItemId.Type.LOCAL_URI);
    private static final String DEFAULT_TRACK_SELECTION = DeluxeContentUtil.getNonDeluxeSelection();
    private static final String[] DEFAULT_TRACK_SELECTION_ARGS = DeluxeContentUtil.getNonDeluxeSelectionArgs();

    private PlaybackPersistenceManager() {
        super(SAVE_POSITION_INTERVAL_MILLIS);
        this.mricToken = ChildUserUtil.INSTANCE.getMusicRequestIdentityContextToken(AmazonApplication.getApplication().getApplicationContext());
    }

    public static synchronized PlaybackPersistenceManager getInstance() {
        if (sPlaybackPersistenceManager == null) {
            sPlaybackPersistenceManager = new PlaybackPersistenceManager();
        }
        return sPlaybackPersistenceManager;
    }

    public void onHarleyReady() {
        this.mHarleyReady = true;
        restore();
    }

    private boolean saveStationData(final StationSequencer sequencer, final JSONObject collectionData) throws JSONException {
        if (sequencer.getStationItem() == null || StringUtils.isEmpty(sequencer.getStationItem().getKey())) {
            Log.debug(TAG, "Preventing an empty station key station from being added to the recents table");
            return false;
        }
        StationStorageUtil.toJson(sequencer.getStationItem(), collectionData);
        return true;
    }

    private boolean saveQueueData(final PlayQueueSequencer sequencer, final JSONObject collectionData) throws JSONException {
        collectionData.put("uri", sequencer.getUri());
        collectionData.put("order", sequencer.getSortOrder());
        if (!TextUtils.equals(sequencer.getSelection(), DEFAULT_TRACK_SELECTION)) {
            collectionData.put("selection", sequencer.getSelection());
        }
        if (sequencer.getSelectionArgs() == null || sequencer.getSelectionArgs() == DEFAULT_TRACK_SELECTION_ARGS) {
            return true;
        }
        JSONArray jSONArray = new JSONArray();
        for (String str : sequencer.getSelectionArgs()) {
            jSONArray.put(str);
        }
        collectionData.put("selection_args", jSONArray);
        return true;
    }

    private boolean saveAlexaTrackData(final AlexaSequencer alexaSequencer, final JSONObject collectionData) throws JSONException {
        MediaItem mediaItem;
        MediaItemId mediaItemId;
        if (alexaSequencer == null || (mediaItem = alexaSequencer.getMediaItem(alexaSequencer.getCurrentIndex())) == null || (mediaItemId = mediaItem.getMediaItemId(MediaItemId.Type.CONTENT_URI)) == null) {
            return false;
        }
        collectionData.put("uri", Uri.parse(mediaItemId.getId()));
        return true;
    }

    private Sequencer restoreStation(final JSONObject collectionData, final PlaybackMetricInformation metricInformation) throws JSONException {
        if (CloudQueueSequencer.INSTANCE.isCloudQueuePlaying()) {
            return null;
        }
        return StationUtils.createStationSequencer(getPlaybackController().getPlayback().getPlaybackConfig().getContext(), ControlSource.APP_UI, StationStorageUtil.fromJson(collectionData), metricInformation.getPageType(), null);
    }

    private Sequencer restoreQueue(final JSONObject collectionData, final PlaybackMetricInformation metricInformation, final int position, final RepeatMode repeatMode, final boolean shuffle, @Nullable final PlaceholderSequencer placeholderSequencer, boolean replaceQueue) throws JSONException {
        String[] strArr;
        if (CloudQueueSequencer.INSTANCE.isCloudQueuePlaying() || !canRestore(placeholderSequencer)) {
            return null;
        }
        Uri uri = Uri.parse(collectionData.getString("uri"));
        String strOptString = collectionData.optString("order", null);
        String strOptString2 = collectionData.optString("selection");
        if (TextUtils.isEmpty(strOptString2)) {
            strOptString2 = DEFAULT_TRACK_SELECTION;
        }
        String str = strOptString2;
        JSONArray jSONArrayOptJSONArray = collectionData.optJSONArray("selection_args");
        if (jSONArrayOptJSONArray == null) {
            strArr = DEFAULT_TRACK_SELECTION_ARGS;
        } else {
            String[] strArr2 = new String[jSONArrayOptJSONArray.length()];
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                strArr2[i] = jSONArrayOptJSONArray.getString(i);
            }
            strArr = strArr2;
        }
        PlayQueueSequencer playQueueSequencer = PlayQueueSequencer.getInstance();
        playQueueSequencer.setup(uri, str, strArr, strOptString, position, shuffle, metricInformation, null, replaceQueue);
        playQueueSequencer.setRepeatMode(repeatMode);
        return playQueueSequencer;
    }

    private boolean canRestore(@Nullable final PlaceholderSequencer placeholderSequencer) {
        return (placeholderSequencer != null && isCurrentSequencer(placeholderSequencer)) || shouldRestore();
    }

    private boolean isCurrentSequencer(final PlaceholderSequencer sequencer) {
        PlaybackProvider playbackProvider = getPlaybackController().getPlayback().getPlaybackProvider();
        return (playbackProvider instanceof SequencerPlaybackProvider) && sequencer.equals(((SequencerPlaybackProvider) playbackProvider).getSequencer());
    }

    public void restoreAlexaRecents(final Uri contentUri, final ControlSource controlSource) {
        SequencerPlaybackProvider sequencerPlaybackProvider;
        final long jNow = Clock.now();
        final PlaceholderSequencer placeholderSequencer = new PlaceholderSequencer();
        Playback playback = getPlaybackController().getPlayback();
        PlaybackProvider playbackProvider = playback.getPlaybackProvider();
        if (playbackProvider instanceof SequencerPlaybackProvider) {
            sequencerPlaybackProvider = (SequencerPlaybackProvider) playbackProvider;
        } else {
            sequencerPlaybackProvider = (SequencerPlaybackProvider) playback.getPlaybackConfig().getDefaultPlaybackProvider(this.mricToken);
            getPlaybackController().stop(ChangeReason.NEW_SOURCE);
            Log.info(TAG, "restoreAlexaRecents, calling playback.setPlaybackProvider -> shutdown(false)");
            playback.setPlaybackProvider(sequencerPlaybackProvider);
        }
        sequencerPlaybackProvider.startSequencer(placeholderSequencer, controlSource, jNow);
        ThreadUtils.runInBackground(new Runnable() { // from class: com.amazon.mp3.playback.harley.PlaybackPersistenceManager$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$restoreAlexaRecents$1(contentUri, placeholderSequencer, controlSource, jNow);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$restoreAlexaRecents$1(Uri uri, final PlaceholderSequencer placeholderSequencer, final ControlSource controlSource, final long j) {
        final Sequencer sequencerRestoreFromUri = restoreFromUri(uri, true);
        ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.mp3.playback.harley.PlaybackPersistenceManager$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$restoreAlexaRecents$0(placeholderSequencer, sequencerRestoreFromUri, controlSource, j);
            }
        });
    }

    public void restoreFromRecents(final JSONObject jsonObject, final ControlSource controlSource) {
        SequencerPlaybackProvider sequencerPlaybackProvider;
        final long jNow = Clock.now();
        final PlaceholderSequencer placeholderSequencer = new PlaceholderSequencer();
        Playback playback = getPlaybackController().getPlayback();
        PlaybackProvider playbackProvider = playback.getPlaybackProvider();
        if (playbackProvider instanceof SequencerPlaybackProvider) {
            sequencerPlaybackProvider = (SequencerPlaybackProvider) playbackProvider;
        } else {
            sequencerPlaybackProvider = (SequencerPlaybackProvider) playback.getPlaybackConfig().getDefaultPlaybackProvider(this.mricToken);
            getPlaybackController().stop(ChangeReason.NEW_SOURCE);
            Log.info(TAG, "restoreFromRecents, calling on setPlaybackProvider -> shutdown(false)");
            playback.setPlaybackProvider(sequencerPlaybackProvider);
        }
        sequencerPlaybackProvider.startSequencer(placeholderSequencer, controlSource, jNow);
        ThreadUtils.runInBackground(new Runnable() { // from class: com.amazon.mp3.playback.harley.PlaybackPersistenceManager.1
            @Override // java.lang.Runnable
            public void run() {
                final Sequencer sequencerRestoreFromJson = PlaybackPersistenceManager.this.restoreFromJson(jsonObject, true, placeholderSequencer);
                ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.mp3.playback.harley.PlaybackPersistenceManager.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                        PlaybackPersistenceManager.this.lambda$restoreAlexaRecents$0(placeholderSequencer, sequencerRestoreFromJson, controlSource, jNow);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onRestoreFromRecents, reason: merged with bridge method [inline-methods] */
    public void lambda$restoreAlexaRecents$0(final PlaceholderSequencer placeholder, final Sequencer sequencer, final ControlSource controlSource, final long requestTime) {
        PlaybackProvider playbackProvider = getPlaybackController().getPlayback().getPlaybackProvider();
        if (playbackProvider instanceof SequencerPlaybackProvider) {
            SequencerPlaybackProvider sequencerPlaybackProvider = (SequencerPlaybackProvider) playbackProvider;
            if (sequencerPlaybackProvider.getSequencer() == placeholder) {
                if (sequencer == null) {
                    Log.info(TAG, "Failed to restore sequencer from recents");
                } else if (getPlaybackController().getPlayStatus().willPlay()) {
                    sequencerPlaybackProvider.startSequencer(sequencer, controlSource, requestTime);
                } else {
                    sequencerPlaybackProvider.setSequencer(sequencer, controlSource);
                }
            }
        }
    }

    private Sequencer restoreFromUri(final Uri contentUri, final boolean fromRecents) {
        PlayQueueSequencer playQueueSequencer = PlayQueueSequencer.getInstance();
        PlaybackMetricInformation playbackMetricInformationCreateFromJSONObject = null;
        try {
            playbackMetricInformationCreateFromJSONObject = PlaybackMetricInformation.createFromJSONObject(new JSONObject());
            if (fromRecents) {
                playbackMetricInformationCreateFromJSONObject.setPageType(PlaybackPageType.RECENTLY_PLAYED);
            }
        } catch (JSONException e) {
            Log.info(TAG, "Unable to restore Now Playing as the format changed", e);
        }
        playQueueSequencer.setup(contentUri, DEFAULT_TRACK_SELECTION, DEFAULT_TRACK_SELECTION_ARGS, null, 0, false, playbackMetricInformationCreateFromJSONObject, null, true);
        return playQueueSequencer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Sequencer restoreFromJson(final JSONObject jsonObject, final boolean fromRecents, @Nullable final PlaceholderSequencer placeholderSequencer) {
        RepeatMode repeatMode;
        Sequencer sequencerRestoreQueue;
        try {
            int i = jsonObject.getInt("repeat");
            if (i == 1) {
                repeatMode = RepeatMode.REPEAT_ONE;
            } else if (i == 2) {
                repeatMode = RepeatMode.REPEAT_ALL;
            } else {
                repeatMode = RepeatMode.REPEAT_NONE;
            }
            RepeatMode repeatMode2 = repeatMode;
            boolean z = jsonObject.getBoolean("shuffle");
            String string = jsonObject.getString("provider_class");
            JSONObject jSONObject = jsonObject.getJSONObject("provider");
            PlaybackMetricInformation playbackMetricInformationCreateFromJSONObject = PlaybackMetricInformation.createFromJSONObject(jsonObject);
            if (fromRecents) {
                playbackMetricInformationCreateFromJSONObject.setPageType(PlaybackPageType.RECENTLY_PLAYED);
            }
            if ("com.amazon.mp3.library.provider.source.nowplaying.StationTrackProvider".equals(string)) {
                sequencerRestoreQueue = restoreStation(jSONObject, playbackMetricInformationCreateFromJSONObject);
            } else if ("com.amazon.mp3.library.provider.source.nowplaying.LibraryTrackProvider".equals(string)) {
                sequencerRestoreQueue = restoreQueue(jSONObject, playbackMetricInformationCreateFromJSONObject, jsonObject.getInt("position"), repeatMode2, z, placeholderSequencer, fromRecents);
            } else {
                Log.info(TAG, "Unexpected collectionType while restoring: " + string);
                return null;
            }
            return sequencerRestoreQueue;
        } catch (JSONException e) {
            Log.info(TAG, "Unable to restore Now Playing as the format changed", e);
            return null;
        }
    }

    public Context getContext() {
        return getPlaybackController().getPlayback().getPlaybackConfig().getContext();
    }

    @Override // com.amazon.music.media.playback.config.SequencerPersistenceProvider
    @SuppressLint({"CheckResult"})
    @WorkerThread
    protected void onRestore() {
        MediaItemId mediaItemId;
        long j;
        if (this.mHarleyReady) {
            if (AndroidUtils.isApplicationInForeground() || getPlaybackController().getPlayStatus().userExpectsPlayback()) {
                SharedPreferences prefs = SettingsUtil.getPrefs(getContext());
                String string = prefs.getString(getContext().getString(R.string.setting_key_now_playing_state), null);
                if (string == null) {
                    Log.info(TAG, "No Now Playing State to restore");
                } else {
                    try {
                        JSONObject jSONObject = new JSONObject(string);
                        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
                        if (featureGateProvider != null && featureGateProvider.isFeatureAvailable(Feature.cq_non_station_playback_restoration)) {
                            final String string2 = jSONObject.getString("cloud_queue_id");
                            if (StringUtil.isNullOrWhiteSpaces(string2)) {
                                return;
                            }
                            CloudQueueClientSingleton.INSTANCE.getCloudQueueClientObservable().subscribe(new Consumer() { // from class: com.amazon.mp3.playback.harley.PlaybackPersistenceManager$$ExternalSyntheticLambda0
                                @Override // io.reactivex.functions.Consumer
                                public final void accept(Object obj) throws Exception {
                                    this.f$0.lambda$onRestore$2(string2, (CloudQueueClient) obj);
                                }
                            }, new Consumer() { // from class: com.amazon.mp3.playback.harley.PlaybackPersistenceManager$$ExternalSyntheticLambda1
                                @Override // io.reactivex.functions.Consumer
                                public final void accept(Object obj) throws Exception {
                                    PlaybackPersistenceManager.lambda$onRestore$3(string2, (Throwable) obj);
                                }
                            });
                            return;
                        }
                        Sequencer sequencerRestoreFromJson = restoreFromJson(jSONObject, false, null);
                        if (sequencerRestoreFromJson != null) {
                            long j2 = prefs.getLong(getContext().getString(R.string.setting_key_last_playback_progress_time), 0L);
                            long jCurrentTimeMillis = System.currentTimeMillis();
                            if (j2 > jCurrentTimeMillis || jCurrentTimeMillis - j2 > MAX_RETAIN_POSITION_MILLIS) {
                                mediaItemId = null;
                                j = 0;
                            } else {
                                String strOptString = jSONObject.optString("track_uri", null);
                                if (StringUtil.isNullOrWhiteSpaces(strOptString)) {
                                    mediaItemId = null;
                                    j = 0;
                                } else {
                                    mediaItemId = new MediaItemId(Uri.parse(strOptString));
                                    j = prefs.getLong(getContext().getString(R.string.setting_key_last_playback_progress), 0L);
                                }
                            }
                            restore(sequencerRestoreFromJson, mediaItemId, j);
                            return;
                        }
                    } catch (JSONException e) {
                        Log.warning(TAG, "Unable to restore Now Playing State", e);
                    }
                }
                restore(null, null, 0L);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onRestore$2(String str, CloudQueueClient cloudQueueClient) throws Exception {
        if ((cloudQueueClient instanceof NoOpCloudQueueClient) || CloudQueueSequencer.INSTANCE.isCloudQueuePlaying()) {
            return;
        }
        CloudQueuePlaybackUtil.INSTANCE.restoreLocalInteractionPlayback(cloudQueueClient, str, getContext(), this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$onRestore$3(String str, Throwable th) throws Exception {
        android.util.Log.e(TAG, "Failed to restore cloud queue with id: " + str, th);
    }

    public boolean saveCollectionState(final JSONObject outSaveState) throws JSONException {
        Sequencer sequencer;
        PlaybackProvider playbackProvider = getPlaybackController().getPlayback().getPlaybackProvider();
        if (!(playbackProvider instanceof SequencerPlaybackProvider) || (sequencer = ((SequencerPlaybackProvider) playbackProvider).getSequencer()) == null) {
            return false;
        }
        return saveCollectionState(sequencer, outSaveState);
    }

    private boolean saveCollectionState(final Sequencer sequencer, final JSONObject outSaveState) throws JSONException {
        int actualIndex;
        PlaybackController playbackController = getPlaybackController();
        JSONObject jSONObject = new JSONObject();
        String str = "com.amazon.mp3.library.provider.source.nowplaying.LibraryTrackProvider";
        int i = 0;
        if (sequencer instanceof StationSequencer) {
            if (!saveStationData((StationSequencer) sequencer, jSONObject)) {
                return false;
            }
            outSaveState.put("position", 0);
            str = "com.amazon.mp3.library.provider.source.nowplaying.StationTrackProvider";
        } else if (sequencer instanceof PlayQueueSequencer) {
            PlayQueueSequencer playQueueSequencer = (PlayQueueSequencer) sequencer;
            if (!saveQueueData(playQueueSequencer, jSONObject)) {
                return false;
            }
            Uri uri = playQueueSequencer.getUri();
            if (uri != null && MediaProvider.Tracks.isTrack(uri)) {
                actualIndex = 0;
            } else if (playbackController.isShuffled()) {
                actualIndex = playbackController.getUnshuffledIndex(playbackController.getCurrentIndex());
            } else {
                actualIndex = playQueueSequencer.getActualIndex();
            }
            outSaveState.put("position", actualIndex);
        } else if (sequencer instanceof AlexaSequencer) {
            if (!saveAlexaTrackData((AlexaSequencer) sequencer, jSONObject)) {
                return false;
            }
            outSaveState.put("position", 0);
        } else {
            if (!(sequencer instanceof CloudQueueSequencer)) {
                return false;
            }
            outSaveState.put("cloud_queue_id", ((CloudQueueSequencer) sequencer).getQueueId());
        }
        outSaveState.put("provider_class", str);
        outSaveState.put("provider", jSONObject);
        int i2 = AnonymousClass2.$SwitchMap$com$amazon$music$media$playback$RepeatMode[playbackController.getRepeatMode().ordinal()];
        if (i2 == 1) {
            i = 1;
        } else if (i2 == 2) {
            i = 2;
        }
        outSaveState.put("repeat", i);
        outSaveState.put("shuffle", playbackController.isShuffled());
        MediaItem currentMediaItem = playbackController.getCurrentMediaItem();
        String string = null;
        outSaveState.put("track_luid", MediaItem.getMediaItemIdString(currentMediaItem, MediaItemId.Type.LUID, null));
        if (currentMediaItem != null) {
            Iterator<MediaItemId.Type> it = RECOVERABLE_ID_TYPES.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                MediaItemId mediaItemId = currentMediaItem.getMediaItemId(it.next());
                if (mediaItemId != null) {
                    string = mediaItemId.toString();
                    break;
                }
            }
        }
        outSaveState.put("track_uri", string);
        return true;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.playback.harley.PlaybackPersistenceManager$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$media$playback$RepeatMode;

        static {
            int[] iArr = new int[RepeatMode.values().length];
            $SwitchMap$com$amazon$music$media$playback$RepeatMode = iArr;
            try {
                iArr[RepeatMode.REPEAT_ONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$RepeatMode[RepeatMode.REPEAT_ALL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // com.amazon.music.media.playback.config.SequencerPersistenceProvider
    @WorkerThread
    protected void onSaveSequencerState(@NonNull final Sequencer sequencer) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!saveCollectionState(sequencer, jSONObject)) {
                Log.warning(TAG, "Unable to save Now Playing State");
                onDeleteSequencerState();
                return;
            }
        } catch (JSONException e) {
            Log.warning(TAG, "Unable to save Now Playing State", e);
        }
        SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(getContext()).edit();
        editorEdit.putString(getContext().getString(R.string.setting_key_now_playing_state), jSONObject.toString());
        editorEdit.apply();
    }

    @Override // com.amazon.music.media.playback.config.SequencerPersistenceProvider
    @WorkerThread
    protected void onSavePosition(final long positionMillis) {
        SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(getContext()).edit();
        editorEdit.putLong(getContext().getString(R.string.setting_key_last_playback_progress), positionMillis);
        editorEdit.putLong(getContext().getString(R.string.setting_key_last_playback_progress_time), System.currentTimeMillis());
        editorEdit.apply();
    }

    @Override // com.amazon.music.media.playback.config.SequencerPersistenceProvider
    @WorkerThread
    protected void onDeleteSequencerState() {
        SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(getContext()).edit();
        editorEdit.remove(getContext().getString(R.string.setting_key_playback_service_shuffle));
        editorEdit.remove(getContext().getString(R.string.setting_key_playback_service_repeat_mode));
        editorEdit.remove(getContext().getString(R.string.setting_key_now_playing_state));
        editorEdit.remove(getContext().getString(R.string.setting_key_last_playback_progress));
        editorEdit.remove(getContext().getString(R.string.setting_key_last_playback_progress_time));
        editorEdit.apply();
    }
}
