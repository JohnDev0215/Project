package com.amazon.mp3.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer;
import com.amazon.mp3.playback.harley.PlayQueueSequencer;
import com.amazon.mp3.recentlyplayed.RecentsFactory;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackProvider;
import com.amazon.music.media.playback.SequencerPlaybackProvider;
import com.amazon.music.media.playback.sequencer.Sequencer;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.recents.RecentsException;
import com.amazon.music.recents.model.RecentUnfilteredTrackActivityRequest;
import com.amazon.nimblymusicservice.GetRecentUnfilteredTrackResponse;
import com.amazon.nimblymusicservice.TrackEventList;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutoPlaySFAUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u001f\u0010 J\b\u0010\u0003\u001a\u00020\u0002H\u0007J\b\u0010\u0004\u001a\u00020\u0002H\u0007J\b\u0010\u0006\u001a\u00020\u0005H\u0007J\b\u0010\u0007\u001a\u00020\u0002H\u0007J\b\u0010\b\u001a\u00020\u0002H\u0007J\b\u0010\n\u001a\u00020\tH\u0002J\b\u0010\u000b\u001a\u00020\u0005H\u0007J\b\u0010\f\u001a\u00020\u0005H\u0007J\b\u0010\r\u001a\u00020\u0002H\u0007J\b\u0010\u000e\u001a\u00020\u0005H\u0007J\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u000fH\u0007J\u0010\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u000fH\u0007J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u000fH\u0003J\u0010\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u000fH\u0007J\b\u0010\u0016\u001a\u00020\u0005H\u0007J\u0010\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u000fH\u0007J\u0010\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u000fH\u0007J\u0010\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u000fH\u0007R\u0016\u0010\u001a\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0016\u0010\u001c\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010\u001bR\u0016\u0010\u001d\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010\u001e¨\u0006!"}, d2 = {"Lcom/amazon/mp3/util/AutoPlaySFAUtil;", "", "", "onSequencerChanged", "onInitiateSfa", "", "shouldShowSfaDialog", "onSfaDialogShown", "sendEndOfQueueMetric", "", "getSfaPlayQueueSize", "isPlayQueueSequencer", "isCloudQueueSequencer", "increaseDialogSeenTracks", "shouldNotSeeSFADialogWhileSFAPlaying", "Landroid/content/Context;", "context", "customerDismissedDialogAndShouldNotSeeItAgain", "didCustomerDismissDialog", "Lcom/amazon/music/recents/model/RecentUnfilteredTrackActivityRequest;", "getRecentUnfilteredTrackRequest", "isCustomerInEarlyLifeCycle", "isCustomerTopListeners", "resetSfaFlags", "isAutoPlayAvailable", "setSfaAvaliableFlag", "showSfaDialogFlag", "Z", "initiateSfa", "currentSongIndexOfDialog", "I", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class AutoPlaySFAUtil {
    public static final AutoPlaySFAUtil INSTANCE = new AutoPlaySFAUtil();
    private static int currentSongIndexOfDialog;
    private static boolean initiateSfa;
    private static boolean showSfaDialogFlag;

    private AutoPlaySFAUtil() {
    }

    @JvmStatic
    public static final void onSequencerChanged() {
        if (initiateSfa) {
            initiateSfa = false;
        } else {
            showSfaDialogFlag = false;
        }
    }

    @JvmStatic
    public static final void onInitiateSfa() {
        initiateSfa = true;
        showSfaDialogFlag = true;
        currentSongIndexOfDialog = 0;
    }

    @JvmStatic
    public static final boolean shouldShowSfaDialog() {
        return showSfaDialogFlag;
    }

    @JvmStatic
    public static final void onSfaDialogShown() {
        showSfaDialogFlag = false;
    }

    @JvmStatic
    public static final void sendEndOfQueueMetric() {
        MetricsHolder.getManager().handleEvent(FlexEvent.builder("endOfQueueReached").withFlexNum1(Float.valueOf(INSTANCE.getSfaPlayQueueSize())).build());
    }

    private final int getSfaPlayQueueSize() {
        PlayQueueSequencer playQueueSequencer = PlayQueueSequencer.getInstance();
        if (playQueueSequencer == null) {
            return 0;
        }
        return playQueueSequencer.getNumMediaItems();
    }

    @JvmStatic
    public static final boolean isPlayQueueSequencer() {
        Sequencer sequencer;
        PlaybackProvider playbackProvider = Playback.getInstance().getPlaybackProvider();
        SequencerPlaybackProvider sequencerPlaybackProvider = playbackProvider instanceof SequencerPlaybackProvider ? (SequencerPlaybackProvider) playbackProvider : null;
        if (sequencerPlaybackProvider == null || (sequencer = sequencerPlaybackProvider.getSequencer()) == null) {
            return false;
        }
        return sequencer instanceof PlayQueueSequencer;
    }

    @JvmStatic
    public static final boolean isCloudQueueSequencer() {
        Sequencer sequencer;
        PlaybackProvider playbackProvider = Playback.getInstance().getPlaybackProvider();
        SequencerPlaybackProvider sequencerPlaybackProvider = playbackProvider instanceof SequencerPlaybackProvider ? (SequencerPlaybackProvider) playbackProvider : null;
        if (sequencerPlaybackProvider == null || (sequencer = sequencerPlaybackProvider.getSequencer()) == null) {
            return false;
        }
        return sequencer instanceof CloudQueueSequencer;
    }

    @JvmStatic
    public static final void increaseDialogSeenTracks() {
        currentSongIndexOfDialog++;
    }

    @JvmStatic
    public static final boolean shouldNotSeeSFADialogWhileSFAPlaying() {
        return currentSongIndexOfDialog > 3 && shouldShowSfaDialog();
    }

    @JvmStatic
    public static final void customerDismissedDialogAndShouldNotSeeItAgain(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences.Editor editorEdit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editorEdit.putBoolean("autoPlayDialogDismissed", true);
        editorEdit.commit();
    }

    @JvmStatic
    public static final boolean didCustomerDismissDialog(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("autoPlayDialogDismissed", false);
    }

    @JvmStatic
    private static final RecentUnfilteredTrackActivityRequest getRecentUnfilteredTrackRequest(Context context) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, 0, 0, 0, 0, 0);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(3, -5);
        AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(context);
        RecentUnfilteredTrackActivityRequest recentUnfilteredTrackActivityRequestBuild = new RecentUnfilteredTrackActivityRequest.Builder(AccountDetailUtil.getMusicTerritoryOfResidence(), accountCredentialStorage.getDeviceId(), accountCredentialStorage.getDeviceType()).withStartTime(calendar.getTime()).withEndTime(calendar2.getTime()).withFilterMoreThanThirtySeconds(Boolean.TRUE).withFlatten(Boolean.FALSE).withMaxResults(30).build();
        Intrinsics.checkNotNullExpressionValue(recentUnfilteredTrackActivityRequestBuild, "Builder(\n               …\n                .build()");
        return recentUnfilteredTrackActivityRequestBuild;
    }

    @JvmStatic
    public static final boolean isCustomerInEarlyLifeCycle(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            GetRecentUnfilteredTrackResponse recentUnfilteredTrackActivity = RecentsFactory.createRecentsController(context).getRecentUnfilteredTrackActivity(getRecentUnfilteredTrackRequest(context));
            if (recentUnfilteredTrackActivity != null) {
                TrackEventList trackEventList = recentUnfilteredTrackActivity.getTrackEventList();
                if ((trackEventList == null ? null : trackEventList.getEvents()) != null) {
                    Intrinsics.checkNotNullExpressionValue(trackEventList.getEvents(), "trackEventList.events");
                    return !r2.isEmpty();
                }
            }
            return false;
        } catch (RecentsException e) {
            Log.error("Failed getting recent tracks for Autoplay Prime check", e.getMessage());
            return false;
        }
    }

    @JvmStatic
    public static final boolean isCustomerTopListeners() {
        long ghostListeningTimeoutMillis = Playback.getInstance().getPlaybackConfig().getGhostListeningTimeoutMillis();
        return ghostListeningTimeoutMillis < ((long) 10800000) && ghostListeningTimeoutMillis > 0;
    }

    @JvmStatic
    public static final void resetSfaFlags(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences.Editor editorEdit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        SettingsUtil.setSfa(context, false);
        editorEdit.putBoolean("isAutoPlaySfaCustomer", false);
        editorEdit.commit();
    }

    @JvmStatic
    public static final boolean isAutoPlayAvailable(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("isAutoPlaySfaCustomer", false);
    }

    @JvmStatic
    public static final void setSfaAvaliableFlag(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences.Editor editorEdit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editorEdit.putBoolean("isAutoPlaySfaCustomer", true);
        editorEdit.commit();
    }
}
