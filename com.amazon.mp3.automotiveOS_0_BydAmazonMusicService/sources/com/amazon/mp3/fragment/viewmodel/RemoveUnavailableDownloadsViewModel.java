package com.amazon.mp3.fragment.viewmodel;

import android.content.Context;
import android.view.View;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.download.redownload.ReDownloadOfflineMusicService;
import com.amazon.mp3.fragment.RedownloadMusicDialogPurpose;
import com.amazon.mp3.fragment.model.RedownloadOfflineMusicDialogModel;
import com.amazon.mp3.fragment.view.RedownloadOfflineMusicFragmentBinding;
import com.amazon.mp3.library.provider.source.cirrus.dbvalueupdate.RemovePreWidevineDRMContentOperation;
import com.amazon.mp3.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import rx.Completable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: RemoveUnavailableDownloadsViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u0000 '2\u00020\u0001:\u0001'B)\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u001a¢\u0006\u0004\b%\u0010&J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0006\u001a\u00020\u0004H\u0002J\b\u0010\u0007\u001a\u00020\u0004H\u0002J\b\u0010\b\u001a\u00020\u0004H\u0002R\u001a\u0010\n\u001a\u00020\t8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\"\u0010\u0014\u001a\u00020\u00138\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R$\u0010\u001b\u001a\u0004\u0018\u00010\u001a8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001c\u0010#\u001a\n \"*\u0004\u0018\u00010!0!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$¨\u0006("}, d2 = {"Lcom/amazon/mp3/fragment/viewmodel/RemoveUnavailableDownloadsViewModel;", "Lcom/amazon/mp3/fragment/viewmodel/BaseViewModel;", "", "id", "", "notifyPropertyChanged", "setupPrimaryButton", "setupSecondaryButton", "setupCloseIcon", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/amazon/mp3/fragment/view/RedownloadOfflineMusicFragmentBinding;", "mBinding", "Lcom/amazon/mp3/fragment/view/RedownloadOfflineMusicFragmentBinding;", "getMBinding", "()Lcom/amazon/mp3/fragment/view/RedownloadOfflineMusicFragmentBinding;", "Lcom/amazon/mp3/fragment/model/RedownloadOfflineMusicDialogModel;", "dialogModel", "Lcom/amazon/mp3/fragment/model/RedownloadOfflineMusicDialogModel;", "getDialogModel", "()Lcom/amazon/mp3/fragment/model/RedownloadOfflineMusicDialogModel;", "setDialogModel", "(Lcom/amazon/mp3/fragment/model/RedownloadOfflineMusicDialogModel;)V", "Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "viewModelCallbacks", "Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "getViewModelCallbacks", "()Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "setViewModelCallbacks", "(Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;)V", "", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "<init>", "(Landroid/content/Context;Lcom/amazon/mp3/fragment/view/RedownloadOfflineMusicFragmentBinding;Lcom/amazon/mp3/fragment/model/RedownloadOfflineMusicDialogModel;Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class RemoveUnavailableDownloadsViewModel extends BaseViewModel {
    private final String TAG;
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

    public ViewModelCallbacks getViewModelCallbacks() {
        return this.viewModelCallbacks;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RemoveUnavailableDownloadsViewModel(Context context, RedownloadOfflineMusicFragmentBinding mBinding, RedownloadOfflineMusicDialogModel dialogModel, ViewModelCallbacks viewModelCallbacks) {
        super(context, viewModelCallbacks);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mBinding, "mBinding");
        Intrinsics.checkNotNullParameter(dialogModel, "dialogModel");
        this.context = context;
        this.mBinding = mBinding;
        this.dialogModel = dialogModel;
        this.viewModelCallbacks = viewModelCallbacks;
        this.TAG = RemoveUnavailableDownloadsViewModel.class.getSimpleName();
        notifyPropertyChanged(mBinding.getMPrimaryButton().getId());
        notifyPropertyChanged(mBinding.getMSecondaryButton().getId());
        notifyPropertyChanged(mBinding.getMCheckbox().getId());
        notifyPropertyChanged(mBinding.getMTitleView().getId());
        notifyPropertyChanged(mBinding.getMMessageView().getId());
        notifyPropertyChanged(mBinding.getMCloseDialogIcon().getId());
        setupPrimaryButton();
        setupSecondaryButton();
        setupCloseIcon();
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
            this.mBinding.getMCheckbox().setVisibility(8);
        } else if (id == this.mBinding.getMCloseDialogIcon().getId()) {
            this.mBinding.getMCloseDialogIcon().setVisibility(this.dialogModel.getCloseButtonVisibility());
        }
    }

    private final void setupPrimaryButton() {
        final Context context = getContext();
        getMBinding().getMPrimaryButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.fragment.viewmodel.RemoveUnavailableDownloadsViewModel$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RemoveUnavailableDownloadsViewModel.m1196setupPrimaryButton$lambda4$lambda3(this.f$0, context, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupPrimaryButton$lambda-4$lambda-3, reason: not valid java name */
    public static final void m1196setupPrimaryButton$lambda4$lambda3(final RemoveUnavailableDownloadsViewModel this$0, final Context c, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(c, "$c");
        ReDownloadOfflineMusicService.INSTANCE.cancel(this$0.getContext());
        SettingsUtil.setBooleanPref(this$0.getContext(), R.string.setting_key_redownload_popup_dismissed, true);
        SettingsUtil.setShouldShowRedownloadUIUntilAcknowledged(c, false);
        Completable.fromAction(new Action0() { // from class: com.amazon.mp3.fragment.viewmodel.RemoveUnavailableDownloadsViewModel$$ExternalSyntheticLambda3
            @Override // rx.functions.Action0
            public final void call() throws InterruptedException {
                RemoveUnavailableDownloadsViewModel.m1197setupPrimaryButton$lambda4$lambda3$lambda0(this.f$0, c);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action0() { // from class: com.amazon.mp3.fragment.viewmodel.RemoveUnavailableDownloadsViewModel$$ExternalSyntheticLambda4
            @Override // rx.functions.Action0
            public final void call() {
                RemoveUnavailableDownloadsViewModel.m1198setupPrimaryButton$lambda4$lambda3$lambda1(this.f$0);
            }
        }, new Action1() { // from class: com.amazon.mp3.fragment.viewmodel.RemoveUnavailableDownloadsViewModel$$ExternalSyntheticLambda5
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                RemoveUnavailableDownloadsViewModel.m1199setupPrimaryButton$lambda4$lambda3$lambda2(this.f$0, (Throwable) obj);
            }
        });
        this$0.sendUiClickMetric$DigitalMusicAndroid3P_automotiveProdRelease("selectConfirm");
        ViewModelCallbacks viewModelCallbacks = this$0.getViewModelCallbacks();
        if (viewModelCallbacks == null) {
            return;
        }
        viewModelCallbacks.onEventOccurred(RedownloadMusicDialogPurpose.SHOW_REMOVE_UNAVAILABLE_DOWNLOADS_CONFIRMATION.ordinal(), 1, this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupPrimaryButton$lambda-4$lambda-3$lambda-0, reason: not valid java name */
    public static final void m1197setupPrimaryButton$lambda4$lambda3$lambda0(RemoveUnavailableDownloadsViewModel this$0, Context c) throws InterruptedException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(c, "$c");
        int i = 0;
        while (MusicDownloader.getInstance(this$0.getContext()).isDownloading()) {
            Log.verbose(this$0.TAG, "downloading, awaiting cancellation");
            Thread.sleep(100L);
            int i2 = i + 1;
            if (i > 20) {
                break;
            } else {
                i = i2;
            }
        }
        RemovePreWidevineDRMContentOperation.INSTANCE.removeAll(c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupPrimaryButton$lambda-4$lambda-3$lambda-1, reason: not valid java name */
    public static final void m1198setupPrimaryButton$lambda4$lambda3$lambda1(RemoveUnavailableDownloadsViewModel this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.info(this$0.TAG, "Successfully removed all PreWidevine downloads");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupPrimaryButton$lambda-4$lambda-3$lambda-2, reason: not valid java name */
    public static final void m1199setupPrimaryButton$lambda4$lambda3$lambda2(RemoveUnavailableDownloadsViewModel this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.error(this$0.TAG, Intrinsics.stringPlus("Failed to remove PreWidevine downloads with message: ", th.getMessage()));
    }

    private final void setupSecondaryButton() {
        getContext();
        getMBinding().getMSecondaryButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.fragment.viewmodel.RemoveUnavailableDownloadsViewModel$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RemoveUnavailableDownloadsViewModel.m1200setupSecondaryButton$lambda6$lambda5(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupSecondaryButton$lambda-6$lambda-5, reason: not valid java name */
    public static final void m1200setupSecondaryButton$lambda6$lambda5(RemoveUnavailableDownloadsViewModel this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.sendUiClickMetric$DigitalMusicAndroid3P_automotiveProdRelease("selectDismiss");
        ViewModelCallbacks viewModelCallbacks = this$0.getViewModelCallbacks();
        if (viewModelCallbacks == null) {
            return;
        }
        viewModelCallbacks.onEventOccurred(RedownloadMusicDialogPurpose.SHOW_REMOVE_UNAVAILABLE_DOWNLOADS_CONFIRMATION.ordinal(), 1, this$0);
    }

    private final void setupCloseIcon() {
        this.mBinding.getMCloseDialogIcon().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.fragment.viewmodel.RemoveUnavailableDownloadsViewModel$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RemoveUnavailableDownloadsViewModel.m1195setupCloseIcon$lambda7(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupCloseIcon$lambda-7, reason: not valid java name */
    public static final void m1195setupCloseIcon$lambda7(RemoveUnavailableDownloadsViewModel this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SettingsUtil.setBooleanPref(this$0.getContext(), R.string.setting_key_redownload_popup_dismissed, true);
        this$0.sendUiClickMetric$DigitalMusicAndroid3P_automotiveProdRelease("selectDismiss");
        ViewModelCallbacks viewModelCallbacks = this$0.getViewModelCallbacks();
        if (viewModelCallbacks == null) {
            return;
        }
        viewModelCallbacks.onEventOccurred(RedownloadMusicDialogPurpose.SHOW_REMOVE_UNAVAILABLE_DOWNLOADS_CONFIRMATION.ordinal(), 1, this$0);
    }
}
