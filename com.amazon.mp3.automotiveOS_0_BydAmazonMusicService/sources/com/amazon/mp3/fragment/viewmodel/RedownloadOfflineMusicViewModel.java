package com.amazon.mp3.fragment.viewmodel;

import android.content.Context;
import android.view.View;
import android.widget.CompoundButton;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.download.redownload.ReDownloadOfflineMusicService;
import com.amazon.mp3.fragment.RedownloadMusicDialogPurpose;
import com.amazon.mp3.fragment.model.RedownloadOfflineMusicDialogModel;
import com.amazon.mp3.fragment.view.RedownloadOfflineMusicFragmentBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RedownloadOfflineMusicViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u0000 '2\u00020\u0001:\u0001'B)\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\u001b¢\u0006\u0004\b%\u0010&J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0006\u001a\u00020\u0004H\u0002J\b\u0010\u0007\u001a\u00020\u0004H\u0002J\b\u0010\b\u001a\u00020\u0004H\u0002J\u0006\u0010\t\u001a\u00020\u0004R\u001a\u0010\u000b\u001a\u00020\n8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\"\u0010\u0015\u001a\u00020\u00148\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR$\u0010\u001c\u001a\u0004\u0018\u00010\u001b8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u0016\u0010#\u001a\u00020\"8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b#\u0010$¨\u0006("}, d2 = {"Lcom/amazon/mp3/fragment/viewmodel/RedownloadOfflineMusicViewModel;", "Lcom/amazon/mp3/fragment/viewmodel/BaseViewModel;", "", "id", "", "notifyPropertyChanged", "setupCheckbox", "setupPrimaryButton", "setupSecondaryButton", "onDialogDismissed", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/amazon/mp3/fragment/view/RedownloadOfflineMusicFragmentBinding;", "mBinding", "Lcom/amazon/mp3/fragment/view/RedownloadOfflineMusicFragmentBinding;", "getMBinding", "()Lcom/amazon/mp3/fragment/view/RedownloadOfflineMusicFragmentBinding;", "Lcom/amazon/mp3/fragment/model/RedownloadOfflineMusicDialogModel;", "dialogModel", "Lcom/amazon/mp3/fragment/model/RedownloadOfflineMusicDialogModel;", "getDialogModel", "()Lcom/amazon/mp3/fragment/model/RedownloadOfflineMusicDialogModel;", "setDialogModel", "(Lcom/amazon/mp3/fragment/model/RedownloadOfflineMusicDialogModel;)V", "Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "viewModelCallbacks", "Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "getViewModelCallbacks", "()Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "setViewModelCallbacks", "(Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;)V", "", "actionTaken", "Z", "<init>", "(Landroid/content/Context;Lcom/amazon/mp3/fragment/view/RedownloadOfflineMusicFragmentBinding;Lcom/amazon/mp3/fragment/model/RedownloadOfflineMusicDialogModel;Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class RedownloadOfflineMusicViewModel extends BaseViewModel {
    private static final String TAG = RedownloadOfflineMusicViewModel.class.getSimpleName();
    private boolean actionTaken;
    private final Context context;
    private RedownloadOfflineMusicDialogModel dialogModel;
    private final RedownloadOfflineMusicFragmentBinding mBinding;
    private ViewModelCallbacks viewModelCallbacks;

    public Context getContext() {
        return this.context;
    }

    public final RedownloadOfflineMusicFragmentBinding getMBinding() {
        return this.mBinding;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RedownloadOfflineMusicViewModel(Context context, RedownloadOfflineMusicFragmentBinding mBinding, RedownloadOfflineMusicDialogModel dialogModel, ViewModelCallbacks viewModelCallbacks) {
        super(context, viewModelCallbacks);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mBinding, "mBinding");
        Intrinsics.checkNotNullParameter(dialogModel, "dialogModel");
        this.context = context;
        this.mBinding = mBinding;
        this.dialogModel = dialogModel;
        this.viewModelCallbacks = viewModelCallbacks;
        notifyPropertyChanged(mBinding.getMPrimaryButton().getId());
        notifyPropertyChanged(mBinding.getMSecondaryButton().getId());
        notifyPropertyChanged(mBinding.getMCheckbox().getId());
        notifyPropertyChanged(mBinding.getMTitleView().getId());
        notifyPropertyChanged(mBinding.getMMessageView().getId());
        notifyPropertyChanged(mBinding.getMCloseDialogIcon().getId());
        setupCheckbox();
        setupPrimaryButton();
        setupSecondaryButton();
    }

    public ViewModelCallbacks getViewModelCallbacks() {
        return this.viewModelCallbacks;
    }

    private final void notifyPropertyChanged(int id) {
        if (id == this.mBinding.getMPrimaryButton().getId()) {
            this.mBinding.getMPrimaryButton().setText(this.dialogModel.getPrimaryButtonText());
            return;
        }
        if (id == this.mBinding.getMSecondaryButton().getId()) {
            this.mBinding.getMSecondaryButton().setText(this.dialogModel.getSecondaryButtonText());
            return;
        }
        if (id == this.mBinding.getMMessageView().getId()) {
            this.mBinding.getMMessageView().setText(this.dialogModel.getMessage());
            return;
        }
        if (id == this.mBinding.getMTitleView().getId()) {
            this.mBinding.getMTitleView().setText(this.dialogModel.getTitle());
        } else if (id == this.mBinding.getMCheckbox().getId()) {
            this.mBinding.getMCheckbox().setChecked(!SettingsUtil.getBooleanPref(getContext(), R.string.setting_key_downloads_wifi_only_pref));
        } else if (id == this.mBinding.getMCloseDialogIcon().getId()) {
            this.mBinding.getMCloseDialogIcon().setVisibility(this.dialogModel.getCloseButtonVisibility());
        }
    }

    private final void setupCheckbox() {
        final Context context = getContext();
        getMBinding().getMCheckbox().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.amazon.mp3.fragment.viewmodel.RedownloadOfflineMusicViewModel$$ExternalSyntheticLambda2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                RedownloadOfflineMusicViewModel.m1180setupCheckbox$lambda1$lambda0(context, this, compoundButton, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupCheckbox$lambda-1$lambda-0, reason: not valid java name */
    public static final void m1180setupCheckbox$lambda1$lambda0(Context it, RedownloadOfflineMusicViewModel this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(it, "$it");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SettingsUtil.setBooleanPref(it, R.string.setting_key_downloads_wifi_only_pref, !z);
        MusicDownloader.getInstance(this$0.getContext()).setDownloadOnWifiOnly(!z);
        if (z) {
            this$0.sendUiClickMetric$DigitalMusicAndroid3P_automotiveProdRelease("selectUpdateDownloadsWifiOn");
        } else {
            this$0.sendUiClickMetric$DigitalMusicAndroid3P_automotiveProdRelease("selectUpdateDownloadsWifiOff");
        }
        this$0.notifyPropertyChanged(this$0.mBinding.getMPrimaryButton().getId());
    }

    private final void setupPrimaryButton() {
        this.mBinding.getMPrimaryButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.fragment.viewmodel.RedownloadOfflineMusicViewModel$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RedownloadOfflineMusicViewModel.m1181setupPrimaryButton$lambda2(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupPrimaryButton$lambda-2, reason: not valid java name */
    public static final void m1181setupPrimaryButton$lambda2(RedownloadOfflineMusicViewModel this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.actionTaken = true;
        ReDownloadOfflineMusicService.Companion.queueOrStart$default(ReDownloadOfflineMusicService.INSTANCE, false, 1, null);
        SettingsUtil.setBooleanPref(this$0.getContext(), R.string.setting_key_redownload_ribbon_dismissed, true);
        SettingsUtil.setBooleanPref(this$0.getContext(), R.string.setting_key_redownload_popup_dismissed, true);
        SettingsUtil.setShouldShowRedownloadUIUntilAcknowledged(this$0.getContext(), false);
        this$0.sendUiClickMetric$DigitalMusicAndroid3P_automotiveProdRelease("selectQueueForDownload");
        ViewModelCallbacks viewModelCallbacks = this$0.getViewModelCallbacks();
        if (viewModelCallbacks == null) {
            return;
        }
        viewModelCallbacks.onEventOccurred(RedownloadMusicDialogPurpose.SHOW_REDOWNLOAD_MESSAGE.ordinal(), 1, this$0);
    }

    private final void setupSecondaryButton() {
        getContext();
        getMBinding().getMSecondaryButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.fragment.viewmodel.RedownloadOfflineMusicViewModel$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RedownloadOfflineMusicViewModel.m1182setupSecondaryButton$lambda4$lambda3(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupSecondaryButton$lambda-4$lambda-3, reason: not valid java name */
    public static final void m1182setupSecondaryButton$lambda4$lambda3(RedownloadOfflineMusicViewModel this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.actionTaken = true;
        this$0.sendUiClickMetric$DigitalMusicAndroid3P_automotiveProdRelease("selectRemoveUnavailableDownloads");
        ViewModelCallbacks viewModelCallbacks = this$0.getViewModelCallbacks();
        if (viewModelCallbacks == null) {
            return;
        }
        viewModelCallbacks.onEventOccurred(RedownloadMusicDialogPurpose.SHOW_REDOWNLOAD_MESSAGE.ordinal(), 0, this$0);
    }

    public final void onDialogDismissed() {
        SettingsUtil.setBooleanPref(getContext(), R.string.setting_key_redownload_popup_dismissed, true);
        if (!this.actionTaken) {
            ReDownloadOfflineMusicService.Companion.queueOrStart$default(ReDownloadOfflineMusicService.INSTANCE, false, 1, null);
        }
        sendUiClickMetric$DigitalMusicAndroid3P_automotiveProdRelease("selectDismiss");
        ViewModelCallbacks viewModelCallbacks = getViewModelCallbacks();
        if (viewModelCallbacks == null) {
            return;
        }
        viewModelCallbacks.onEventOccurred(RedownloadMusicDialogPurpose.SHOW_REDOWNLOAD_MESSAGE.ordinal(), 1, this);
    }
}
