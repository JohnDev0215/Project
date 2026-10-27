package com.amazon.mp3.fragment.viewmodel;

import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import com.amazon.mp3.activity.WideDialogActivity;
import com.amazon.mp3.activity.WideDialogActivityPurpose;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.DownloadQualityPreferences;
import com.amazon.mp3.fragment.model.AudioStatsDialogModel;
import com.amazon.mp3.fragment.view.AudioStatsBinding;
import com.amazon.mp3.playback.KatanaNPSBadgeProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AudioStatsViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 42\u00020\u0001:\u00014BC\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\u001b\u0012\u0006\u0010#\u001a\u00020\"\u0012\u0006\u0010'\u001a\u00020\u0006\u0012\b\u0010*\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b2\u00103J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0007\u001a\u00020\u0006H\u0002J\b\u0010\b\u001a\u00020\u0004H\u0002J\b\u0010\t\u001a\u00020\u0004H\u0002J\b\u0010\n\u001a\u00020\u0004H\u0002J\b\u0010\u000b\u001a\u00020\u0004H\u0002R\u001a\u0010\r\u001a\u00020\f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0012\u001a\u00020\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0017\u001a\u00020\u00168\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR$\u0010\u001c\u001a\u0004\u0018\u00010\u001b8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u0017\u0010#\u001a\u00020\"8\u0006¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&R\u0017\u0010'\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b'\u0010)R\u0019\u0010*\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b*\u0010,R\u0018\u0010.\u001a\u0004\u0018\u00010-8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b.\u0010/R\u0018\u00100\u001a\u0004\u0018\u00010\u001b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b0\u0010\u001dR\u0016\u00101\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b1\u0010(¨\u00065"}, d2 = {"Lcom/amazon/mp3/fragment/viewmodel/AudioStatsViewModel;", "Lcom/amazon/mp3/fragment/viewmodel/BaseViewModel;", "", "id", "", "notifyPropertyChanged", "", "showAudioSettingsButton", "setupGotitButton", "setupAudioSettingsUpdateButton", "setupAudioStatsContainer", "initRecyclerViewModel", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/amazon/mp3/fragment/view/AudioStatsBinding;", "mBinding", "Lcom/amazon/mp3/fragment/view/AudioStatsBinding;", "getMBinding", "()Lcom/amazon/mp3/fragment/view/AudioStatsBinding;", "Lcom/amazon/mp3/fragment/model/AudioStatsDialogModel;", "dialogModel", "Lcom/amazon/mp3/fragment/model/AudioStatsDialogModel;", "getDialogModel", "()Lcom/amazon/mp3/fragment/model/AudioStatsDialogModel;", "Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "viewModelCallbacks", "Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "getViewModelCallbacks", "()Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "setViewModelCallbacks", "(Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;)V", "Lcom/amazon/mp3/playback/KatanaNPSBadgeProvider$AudioQualityBadge;", "audioQualityBadge", "Lcom/amazon/mp3/playback/KatanaNPSBadgeProvider$AudioQualityBadge;", "getAudioQualityBadge", "()Lcom/amazon/mp3/playback/KatanaNPSBadgeProvider$AudioQualityBadge;", "isImmersiveContent", "Z", "()Z", "isCurrentMediaItemDownloaded", "Ljava/lang/Boolean;", "()Ljava/lang/Boolean;", "Lcom/amazon/mp3/fragment/viewmodel/AudioStatsRecyclerViewModel;", "mAudioStatsRecyclerViewModel", "Lcom/amazon/mp3/fragment/viewmodel/AudioStatsRecyclerViewModel;", "mViewModelCallbacks", "isStreamingInfoExpanded", "<init>", "(Landroid/content/Context;Lcom/amazon/mp3/fragment/view/AudioStatsBinding;Lcom/amazon/mp3/fragment/model/AudioStatsDialogModel;Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;Lcom/amazon/mp3/playback/KatanaNPSBadgeProvider$AudioQualityBadge;ZLjava/lang/Boolean;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class AudioStatsViewModel extends BaseViewModel {
    private final KatanaNPSBadgeProvider.AudioQualityBadge audioQualityBadge;
    private final Context context;
    private final AudioStatsDialogModel dialogModel;
    private final Boolean isCurrentMediaItemDownloaded;
    private final boolean isImmersiveContent;
    private boolean isStreamingInfoExpanded;
    private AudioStatsRecyclerViewModel mAudioStatsRecyclerViewModel;
    private final AudioStatsBinding mBinding;
    private ViewModelCallbacks mViewModelCallbacks;
    private ViewModelCallbacks viewModelCallbacks;

    public Context getContext() {
        return this.context;
    }

    public ViewModelCallbacks getViewModelCallbacks() {
        return this.viewModelCallbacks;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AudioStatsViewModel(Context context, AudioStatsBinding mBinding, AudioStatsDialogModel dialogModel, ViewModelCallbacks viewModelCallbacks, KatanaNPSBadgeProvider.AudioQualityBadge audioQualityBadge, boolean z, Boolean bool) {
        super(context, viewModelCallbacks);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mBinding, "mBinding");
        Intrinsics.checkNotNullParameter(dialogModel, "dialogModel");
        Intrinsics.checkNotNullParameter(audioQualityBadge, "audioQualityBadge");
        this.context = context;
        this.mBinding = mBinding;
        this.dialogModel = dialogModel;
        this.viewModelCallbacks = viewModelCallbacks;
        this.audioQualityBadge = audioQualityBadge;
        this.isImmersiveContent = z;
        this.isCurrentMediaItemDownloaded = bool;
        initRecyclerViewModel();
        notifyPropertyChanged(mBinding.getMPageTitleView().getId());
        notifyPropertyChanged(mBinding.getMMessageView().getId());
        notifyPropertyChanged(mBinding.getMGotItButton().getId());
        notifyPropertyChanged(mBinding.getMUpdateAudioSettingsButton().getId());
        notifyPropertyChanged(mBinding.getMImmersiveNerdStatDescription().getId());
        notifyPropertyChanged(mBinding.getMImmersiveContent().getId());
        setupAudioSettingsUpdateButton();
        setupAudioStatsContainer();
        setupGotitButton();
    }

    /* JADX INFO: renamed from: isCurrentMediaItemDownloaded, reason: from getter */
    public final Boolean getIsCurrentMediaItemDownloaded() {
        return this.isCurrentMediaItemDownloaded;
    }

    private final void notifyPropertyChanged(int id) {
        boolean zShowAudioSettingsButton = showAudioSettingsButton();
        if (id == this.mBinding.getMPageTitleView().getId()) {
            this.mBinding.getMPageTitleView().setText(this.dialogModel.getPageTitle());
            return;
        }
        if (id == this.mBinding.getMMessageView().getId()) {
            this.mBinding.getMMessageView().setText(Html.fromHtml(this.dialogModel.getMessage().toString()));
            this.mBinding.getMMessageView().setMovementMethod(LinkMovementMethod.getInstance());
            return;
        }
        if (id == this.mBinding.getMImmersiveContent().getId()) {
            this.mBinding.getMSeparatorMiddle().setVisibility(this.isImmersiveContent ? 0 : 8);
            this.mBinding.getMImmersiveContent().setVisibility(this.isImmersiveContent ? 0 : 8);
            return;
        }
        if (id == this.mBinding.getMGotItButton().getId()) {
            this.mBinding.getMGotItButton().setText(this.dialogModel.getGotItButtonText());
            return;
        }
        if (id == this.mBinding.getMImmersiveNerdStatDescription().getId()) {
            this.mBinding.getMImmersiveNerdStatDescription().setVisibility(0);
            this.mBinding.getMImmersiveNerdStatDescription().setText(Html.fromHtml(this.dialogModel.getImmersiveDescription()));
            this.mBinding.getMImmersiveNerdStatDescription().setMovementMethod(LinkMovementMethod.getInstance());
        } else if (id == this.mBinding.getMUpdateAudioSettingsButton().getId()) {
            if (zShowAudioSettingsButton) {
                this.mBinding.getMUpdateAudioSettingsButton().setVisibility(0);
                this.mBinding.getMUpdateAudioSettingsButton().setText(this.dialogModel.getUpdateAudioSettingsText());
            } else {
                this.mBinding.getMUpdateAudioSettingsButton().setVisibility(8);
            }
        }
    }

    private final boolean showAudioSettingsButton() {
        KatanaNPSBadgeProvider.AudioQualityBadge audioQualityBadge = this.audioQualityBadge;
        if (audioQualityBadge == KatanaNPSBadgeProvider.AudioQualityBadge.STANDARD || audioQualityBadge == KatanaNPSBadgeProvider.AudioQualityBadge.HD || audioQualityBadge == KatanaNPSBadgeProvider.AudioQualityBadge.UHD || audioQualityBadge == KatanaNPSBadgeProvider.AudioQualityBadge.THREE_DIMENSIONAL) {
            return false;
        }
        Boolean bool = this.isCurrentMediaItemDownloaded;
        if (bool != null) {
            bool.booleanValue();
            if (getIsCurrentMediaItemDownloaded().booleanValue()) {
                return !Intrinsics.areEqual(SettingsUtil.getDownloadQualityPreference(getContext()), DownloadQualityPreferences.AUTO.getEntryValue());
            }
        }
        return true;
    }

    private final void setupGotitButton() {
        this.mBinding.getMGotItButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.fragment.viewmodel.AudioStatsViewModel$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudioStatsViewModel.m1161setupGotitButton$lambda1(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupGotitButton$lambda-1, reason: not valid java name */
    public static final void m1161setupGotitButton$lambda1(AudioStatsViewModel this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ViewModelCallbacks viewModelCallbacks = this$0.getViewModelCallbacks();
        if (viewModelCallbacks != null) {
            viewModelCallbacks.onEventOccurred(1, 2, this$0);
        }
        this$0.sendUiClickMetric$DigitalMusicAndroid3P_automotiveProdRelease("selectGotIt");
    }

    private final void setupAudioSettingsUpdateButton() {
        this.mBinding.getMUpdateAudioSettingsButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.fragment.viewmodel.AudioStatsViewModel$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudioStatsViewModel.m1160setupAudioSettingsUpdateButton$lambda2(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupAudioSettingsUpdateButton$lambda-2, reason: not valid java name */
    public static final void m1160setupAudioSettingsUpdateButton$lambda2(AudioStatsViewModel this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ViewModelCallbacks viewModelCallbacks = this$0.getViewModelCallbacks();
        if (viewModelCallbacks != null) {
            viewModelCallbacks.onEventOccurred(1, 2, this$0);
        }
        Boolean bool = this$0.isCurrentMediaItemDownloaded;
        if (bool != null && bool.booleanValue()) {
            WideDialogActivity.Companion.showActivity$default(WideDialogActivity.INSTANCE, this$0.getContext(), WideDialogActivityPurpose.SHOW_KATANA_DOWNLOAD_QUALITY_PREFERENCES, R.style.Theme_AmazonMP3_Dialog, null, 8, null);
            this$0.sendUiClickMetric$DigitalMusicAndroid3P_automotiveProdRelease("goDownloadSettings");
        } else {
            WideDialogActivity.Companion.showActivity$default(WideDialogActivity.INSTANCE, this$0.getContext(), WideDialogActivityPurpose.SHOW_KATANA_AUDIO_QUALITY_PREFERENCES, R.style.Theme_AmazonMP3_Dialog, null, 8, null);
            this$0.sendUiClickMetric$DigitalMusicAndroid3P_automotiveProdRelease("goAudioQualitySettings");
        }
    }

    private final void setupAudioStatsContainer() {
        this.mBinding.getMKatanaRecyclerViewBinding().getMRecyclerView().setVisibility(0);
        this.isStreamingInfoExpanded = true;
    }

    private final void initRecyclerViewModel() {
        this.mAudioStatsRecyclerViewModel = new AudioStatsRecyclerViewModel(getContext(), this.mBinding.getMKatanaRecyclerViewBinding(), this.dialogModel.getAudioStatsList(), this.mViewModelCallbacks);
        this.mBinding.getMKatanaRecyclerViewBinding().getMRecyclerView().setVisibility(8);
    }
}
