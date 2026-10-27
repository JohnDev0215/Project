package com.amazon.mp3.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.activity.WideDialogActivity;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.fragment.model.AudioStatsDialogModel;
import com.amazon.mp3.fragment.model.AudioStatsRowModel;
import com.amazon.mp3.fragment.view.AudioStatsBinding;
import com.amazon.mp3.fragment.viewmodel.AudioStatsViewModel;
import com.amazon.mp3.fragment.viewmodel.BaseViewModel;
import com.amazon.mp3.fragment.viewmodel.ViewModelCallbacks;
import com.amazon.mp3.net.dmls.StreamingBitrate;
import com.amazon.mp3.playback.KatanaNPSBadgeProvider;
import com.amazon.mp3.playback.PlaybackAudioQualityToAudioQuality;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackAttributes;
import com.amazon.music.media.playback.PlaybackAudioQuality;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.PlayerCapabilities;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: FragmentAudioStats.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 .2\u00020\u0001:\u0001.B\u0007¢\u0006\u0004\b,\u0010-J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0002J\u001f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0004\b\t\u0010\nJ\n\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0002J\n\u0010\r\u001a\u0004\u0018\u00010\u000bH\u0002J\n\u0010\u000e\u001a\u0004\u0018\u00010\u000bH\u0002J\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0002J\b\u0010\u0010\u001a\u00020\u0002H\u0016J\b\u0010\u0011\u001a\u00020\u0002H\u0016J\u0012\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0016J&\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u0017\u001a\u00020\u00162\b\u0010\u0019\u001a\u0004\u0018\u00010\u00182\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0016R\u0016\u0010\u001d\u001a\u00020\u001c8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0018\u0010 \u001a\u0004\u0018\u00010\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b \u0010!R\u0017\u0010#\u001a\u00020\"8\u0006¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&R\u0017\u0010(\u001a\u00020'8\u0006¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+¨\u0006/"}, d2 = {"Lcom/amazon/mp3/fragment/FragmentAudioStats;", "Landroidx/fragment/app/DialogFragment;", "", "initViewModelCallbacks", "setViewModel", "", "isCurrentMediaItemDownloaded", "", "Lcom/amazon/mp3/fragment/model/AudioStatsRowModel;", "createAudioStatsList", "(Ljava/lang/Boolean;)Ljava/util/List;", "", "getTrackQualityValue", "getCurrentPlaybackPropertyValue", "getDeviceCapabilityPropertyValue", "createAudioStatsListForCasting", "onResume", "onPause", "Landroid/os/Bundle;", "savedInstanceState", "Landroid/app/Dialog;", "onCreateDialog", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/view/View;", "onCreateView", "Lcom/amazon/mp3/fragment/view/AudioStatsBinding;", "mViewBinding", "Lcom/amazon/mp3/fragment/view/AudioStatsBinding;", "Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "mViewModelCallbacks", "Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "Lcom/amazon/music/media/playback/PlaybackController;", "mPlaybackController", "Lcom/amazon/music/media/playback/PlaybackController;", "getMPlaybackController", "()Lcom/amazon/music/media/playback/PlaybackController;", "Lcom/amazon/music/media/playback/OnPlayStateChangedListener;", "mPlayStateListener", "Lcom/amazon/music/media/playback/OnPlayStateChangedListener;", "getMPlayStateListener", "()Lcom/amazon/music/media/playback/OnPlayStateChangedListener;", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class FragmentAudioStats extends DialogFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = FragmentAudioStats.class.getSimpleName();
    private final OnPlayStateChangedListener mPlayStateListener;
    private final PlaybackController mPlaybackController;
    private AudioStatsBinding mViewBinding = new AudioStatsBinding();
    private ViewModelCallbacks mViewModelCallbacks;

    public FragmentAudioStats() {
        PlaybackController playbackController = Playback.getInstance().getPlaybackController(ControlSource.NONE);
        Intrinsics.checkNotNullExpressionValue(playbackController, "getInstance().getPlaybac…oller(ControlSource.NONE)");
        this.mPlaybackController = playbackController;
        this.mPlayStateListener = new OnPlayStateChangedListener() { // from class: com.amazon.mp3.fragment.FragmentAudioStats$$ExternalSyntheticLambda0
            @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
            public final void onPlayStateChanged() {
                FragmentAudioStats.m1073mPlayStateListener$lambda0(this.f$0);
            }
        };
    }

    /* JADX INFO: compiled from: FragmentAudioStats.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0019\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/fragment/FragmentAudioStats$Companion;", "", "()V", "SD_STAT", "", "TAG", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "newInstance", "Lcom/amazon/mp3/fragment/FragmentAudioStats;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FragmentAudioStats newInstance() {
            return new FragmentAudioStats();
        }

        public final String getTAG() {
            return FragmentAudioStats.TAG;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: mPlayStateListener$lambda-0, reason: not valid java name */
    public static final void m1073mPlayStateListener$lambda0(FragmentAudioStats this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setViewModel();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mPlaybackController.addOnPlayStateChangedListener(this.mPlayStateListener);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mPlaybackController.removeOnPlayStateChangedListener(this.mPlayStateListener);
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (BauhausDialogBuilder.INSTANCE.isBauhausDialogEnabled()) {
            View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.audio_stats_page, (ViewGroup) null);
            Intrinsics.checkNotNullExpressionValue(viewInflate, "from(context).inflate(R.…t.audio_stats_page, null)");
            viewInflate.setBackgroundColor(0);
            this.mViewBinding.setView(viewInflate);
            initViewModelCallbacks();
            setViewModel();
            Context contextRequireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
            return new BauhausDialogBuilder(contextRequireContext).setView(viewInflate).create();
        }
        Dialog dialogOnCreateDialog = super.onCreateDialog(savedInstanceState);
        Intrinsics.checkNotNullExpressionValue(dialogOnCreateDialog, "{\n            super.onCr…dInstanceState)\n        }");
        return dialogOnCreateDialog;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        if (!BauhausDialogBuilder.INSTANCE.isBauhausDialogEnabled()) {
            View viewInflate = inflater.inflate(R.layout.audio_stats_page, container, false);
            Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…s_page, container, false)");
            this.mViewBinding.setView(viewInflate);
            initViewModelCallbacks();
            setViewModel();
            return viewInflate;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private final void initViewModelCallbacks() {
        this.mViewModelCallbacks = new ViewModelCallbacks() { // from class: com.amazon.mp3.fragment.FragmentAudioStats.initViewModelCallbacks.1
            @Override // com.amazon.mp3.fragment.viewmodel.ViewModelCallbacks
            public void onEventOccurred(int callerId, int actionId, BaseViewModel viewModelInstance) {
                Intrinsics.checkNotNullParameter(viewModelInstance, "viewModelInstance");
                if (callerId == 1 && actionId == 2) {
                    if (FragmentAudioStats.this.getActivity() instanceof WideDialogActivity) {
                        FragmentActivity activity = FragmentAudioStats.this.getActivity();
                        if (activity == null) {
                            return;
                        }
                        activity.finish();
                        return;
                    }
                    FragmentAudioStats.this.dismiss();
                }
            }
        };
    }

    private final void setViewModel() {
        StreamingBitrate streamingBitrateFromEntryValue;
        String str;
        String str2 = "";
        MediaItem currentMediaItem = this.mPlaybackController.getCurrentMediaItem();
        Boolean boolIsCurrentMediaItemDownloaded = KatanaNPSBadgeProvider.isCurrentMediaItemDownloaded(currentMediaItem);
        if (ConnectivityUtil.isWifiConnected(getContext())) {
            streamingBitrateFromEntryValue = StreamingBitrate.fromEntryValue(SettingsUtil.getMultiBitrateStreamingPreferenceForWifi(getContext()));
        } else {
            streamingBitrateFromEntryValue = StreamingBitrate.fromEntryValue(SettingsUtil.getMultiBitrateStreamingPreferenceForOTA(getContext()));
        }
        StreamingBitrate streamingBitrate = streamingBitrateFromEntryValue;
        Context context = getContext();
        if (context == null) {
            return;
        }
        KatanaNPSBadgeProvider.AudioQualityBadge currentAudioQualityBadge = KatanaNPSBadgeProvider.getAudioQualityBadge(context, boolIsCurrentMediaItemDownloaded, streamingBitrate, KatanaNPSBadgeProvider.getMaxPlaybackQuality(context, currentMediaItem), currentMediaItem, KatanaNPSBadgeProvider.getCurrentPlaybackAudioQuality());
        String string = context.getString(R.string.dmusic_audio_stats_update_streaming_quality_button);
        Intrinsics.checkNotNullExpressionValue(string, "it.getString(R.string.dm…streaming_quality_button)");
        try {
        } catch (Exception e) {
            Log.error(TAG, "Localized endpoint not available for this local", e);
        }
        if (UserSubscriptionUtil.getUserSubscription().isKatana()) {
            String string2 = EndpointsProvider.get().getKatanaLearnMoreEndpoint().toString();
            Intrinsics.checkNotNullExpressionValue(string2, "get().katanaLearnMoreEndpoint.toString()");
            str = string2;
        } else {
            str = "";
        }
        String string3 = getString(currentAudioQualityBadge.getBadgeTextResId(), str);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(currentAudioQu…ResId, localizedEndpoint)");
        boolean zIsIvy = UserSubscriptionUtil.getUserSubscription().isIvy();
        if (zIsIvy) {
            try {
                String string4 = EndpointsProvider.get().getIvyLearnMoreEndpoint().toString();
                Intrinsics.checkNotNullExpressionValue(string4, "get().ivyLearnMoreEndpoint.toString()");
                str2 = string4;
            } catch (Exception e2) {
                Log.error(TAG, "Localized endpoint not available for this local", e2);
            }
        }
        if (boolIsCurrentMediaItemDownloaded != null) {
            boolIsCurrentMediaItemDownloaded.booleanValue();
            if (boolIsCurrentMediaItemDownloaded.booleanValue()) {
                string = getString(R.string.dmusic_audio_stats_update_download_settings_button);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.dmusi…download_settings_button)");
                string3 = getString(currentAudioQualityBadge.getBadgeTextDownloadedResId(), str);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(currentAudioQu…ResId, localizedEndpoint)");
            }
        }
        String str3 = string;
        String str4 = string3;
        List<AudioStatsRowModel> listCreateAudioStatsList = createAudioStatsList(boolIsCurrentMediaItemDownloaded);
        String string5 = context.getString(R.string.dmusic_audio_stats_title, context.getString(currentAudioQualityBadge.getBadgeTitleResId()));
        Intrinsics.checkNotNullExpressionValue(string5, "it.getString(R.string.dm…tyBadge.badgeTitleResId))");
        String string6 = context.getString(R.string.immersive_badge_nerd_stat_description, str2);
        Intrinsics.checkNotNullExpressionValue(string6, "it.getString(R.string.im…mmersiveLocalizeEndpoint)");
        String string7 = context.getString(R.string.dmusic_got_it_button_text);
        Intrinsics.checkNotNullExpressionValue(string7, "it.getString(R.string.dmusic_got_it_button_text)");
        AudioStatsDialogModel audioStatsDialogModel = new AudioStatsDialogModel(string5, str4, string6, str3, listCreateAudioStatsList, string7);
        AudioStatsBinding audioStatsBinding = this.mViewBinding;
        ViewModelCallbacks viewModelCallbacks = this.mViewModelCallbacks;
        Intrinsics.checkNotNullExpressionValue(currentAudioQualityBadge, "currentAudioQualityBadge");
        new AudioStatsViewModel(context, audioStatsBinding, audioStatsDialogModel, viewModelCallbacks, currentAudioQualityBadge, zIsIvy, boolIsCurrentMediaItemDownloaded);
        MetricsLogger.qualitySettingsDialogViewed(ContentName.STREAMING_INFORMATION_DIALOG);
    }

    private final List<AudioStatsRowModel> createAudioStatsList(Boolean isCurrentMediaItemDownloaded) {
        AudioStatsRowModel audioStatsRowModel;
        if (CastingUtil.isCasting()) {
            return createAudioStatsListForCasting();
        }
        ArrayList arrayList = new ArrayList();
        Context context = getContext();
        if (context != null) {
            String trackQualityValue = getTrackQualityValue();
            String string = context.getString(R.string.dmusic_audio_stats_current_playback_title);
            if (isCurrentMediaItemDownloaded != null) {
                isCurrentMediaItemDownloaded.booleanValue();
                if (isCurrentMediaItemDownloaded.booleanValue()) {
                    string = getString(R.string.dmusic_audio_stats_current_download_title);
                }
            }
            String currentPlaybackPropertyValue = getCurrentPlaybackPropertyValue();
            String deviceCapabilityPropertyValue = getDeviceCapabilityPropertyValue();
            AudioStatsRowModel audioStatsRowModel2 = new AudioStatsRowModel(context.getString(R.string.dmusic_audio_stats_track_quality_title), trackQualityValue);
            AudioStatsRowModel audioStatsRowModel3 = new AudioStatsRowModel(context.getString(R.string.dmusic_device_capability_title), deviceCapabilityPropertyValue);
            AudioStatsRowModel audioStatsRowModel4 = new AudioStatsRowModel(string, currentPlaybackPropertyValue);
            if (StringsKt__StringsJVMKt.equals(currentPlaybackPropertyValue, getString(R.string.dmusic_nps_audio_quality_badge_standard_text), true)) {
                audioStatsRowModel = new AudioStatsRowModel(context.getString(R.string.dmusic_audio_stats_track_codec), context.getString(R.string.dmusic_audio_stats_track_codec_opus));
            } else {
                audioStatsRowModel = new AudioStatsRowModel(context.getString(R.string.dmusic_audio_stats_track_codec), context.getString(R.string.dmusic_audio_stats_track_codec_flac));
            }
            arrayList.add(audioStatsRowModel2);
            arrayList.add(audioStatsRowModel3);
            arrayList.add(audioStatsRowModel4);
            arrayList.add(audioStatsRowModel);
        }
        return arrayList;
    }

    private final String getTrackQualityValue() {
        PlaybackAttributes bestAvailablePlaybackAttributes = this.mPlaybackController.getBestAvailablePlaybackAttributes();
        Intrinsics.checkNotNullExpressionValue(bestAvailablePlaybackAttributes, "mPlaybackController.best…ailablePlaybackAttributes");
        PlaybackAudioQualityToAudioQuality playbackAudioQualityToAudioQuality = new PlaybackAudioQualityToAudioQuality();
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        PlaybackAudioQuality playbackAudioQuality = bestAvailablePlaybackAttributes.getPlaybackAudioQuality();
        Intrinsics.checkNotNullExpressionValue(playbackAudioQuality, "bestAvailable.playbackAudioQuality");
        if (playbackAudioQualityToAudioQuality.isKatana(playbackAudioQuality)) {
            String strValueOf = bestAvailablePlaybackAttributes.getBitDepth() != -1 ? String.valueOf(bestAvailablePlaybackAttributes.getBitDepth()) : null;
            String str = bestAvailablePlaybackAttributes.getSampleRate() != -1 ? decimalFormat.format(Float.valueOf(bestAvailablePlaybackAttributes.getSampleRate() / 1000)) : null;
            if (strValueOf == null || str == null) {
                return null;
            }
            return ((Object) Intrinsics.stringPlus(strValueOf, getString(R.string.dmusic_audio_stats_bitdepth_suffix))) + " / " + ((Object) (((Object) str) + ' ' + getString(R.string.dmusic_audio_stats_frequency_suffix)));
        }
        PlaybackAudioQuality playbackAudioQuality2 = bestAvailablePlaybackAttributes.getPlaybackAudioQuality();
        Intrinsics.checkNotNullExpressionValue(playbackAudioQuality2, "bestAvailable.playbackAudioQuality");
        if (playbackAudioQualityToAudioQuality.isStandard(playbackAudioQuality2)) {
            return getString(R.string.dmusic_nps_audio_quality_badge_standard_text);
        }
        return null;
    }

    private final String getCurrentPlaybackPropertyValue() {
        PlaybackAttributes currentPlaybackAttributes = this.mPlaybackController.getCurrentPlaybackAttributes();
        Intrinsics.checkNotNullExpressionValue(currentPlaybackAttributes, "mPlaybackController.currentPlaybackAttributes");
        PlaybackAudioQualityToAudioQuality playbackAudioQualityToAudioQuality = new PlaybackAudioQualityToAudioQuality();
        PlaybackAudioQuality playbackAudioQuality = currentPlaybackAttributes.getPlaybackAudioQuality();
        Intrinsics.checkNotNullExpressionValue(playbackAudioQuality, "playbackAttributes.getPlaybackAudioQuality()");
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        if (playbackAudioQualityToAudioQuality.isKatana(playbackAudioQuality)) {
            String strValueOf = currentPlaybackAttributes.getBitDepth() != -1 ? String.valueOf(currentPlaybackAttributes.getBitDepth()) : null;
            String str = currentPlaybackAttributes.getSampleRate() != -1 ? decimalFormat.format(Float.valueOf(currentPlaybackAttributes.getSampleRate() / 1000)) : null;
            if (strValueOf == null || str == null) {
                return null;
            }
            return ((Object) Intrinsics.stringPlus(strValueOf, getString(R.string.dmusic_audio_stats_bitdepth_suffix))) + " / " + ((Object) (((Object) str) + ' ' + getString(R.string.dmusic_audio_stats_frequency_suffix)));
        }
        if (playbackAudioQualityToAudioQuality.isStandard(playbackAudioQuality)) {
            return getString(R.string.dmusic_nps_audio_quality_badge_standard_text);
        }
        return null;
    }

    private final String getDeviceCapabilityPropertyValue() {
        String strValueOf;
        String str;
        PlayerCapabilities playerCapabilities = this.mPlaybackController.getPlayerCapabilities();
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        if (playerCapabilities == null) {
            str = null;
            strValueOf = null;
        } else {
            strValueOf = String.valueOf(playerCapabilities.getMaximumBitDepth());
            str = decimalFormat.format(Float.valueOf(playerCapabilities.getMaximumSampleRate() / 1000));
        }
        if (strValueOf == null || str == null) {
            return null;
        }
        return ((Object) Intrinsics.stringPlus(strValueOf, getString(R.string.dmusic_audio_stats_bitdepth_suffix))) + " / " + ((Object) (((Object) str) + ' ' + getString(R.string.dmusic_audio_stats_frequency_suffix)));
    }

    private final List<AudioStatsRowModel> createAudioStatsListForCasting() {
        ArrayList arrayList = new ArrayList();
        Context context = getContext();
        if (context != null) {
            arrayList.add(new AudioStatsRowModel(context.getString(R.string.dmusic_audio_stats_casting_device_capability_error), null));
        }
        return arrayList;
    }
}
