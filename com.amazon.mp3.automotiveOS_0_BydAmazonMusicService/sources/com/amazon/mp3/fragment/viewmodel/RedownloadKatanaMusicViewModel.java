package com.amazon.mp3.fragment.viewmodel;

import android.content.Context;
import android.view.View;
import com.amazon.mp3.fragment.RedownloadMusicDialogPurpose;
import com.amazon.mp3.fragment.model.RedownloadOfflineMusicDialogModel;
import com.amazon.mp3.fragment.view.RedownloadOfflineMusicFragmentBinding;
import com.amazon.mp3.library.item.RedownloadableTrack;
import com.amazon.mp3.library.item.TrackType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RedownloadKatanaMusicViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u0000 +2\u00020\u0001:\u0001+B3\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u001a\u0012\b\u0010 \u001a\u0004\u0018\u00010\u001f¢\u0006\u0004\b)\u0010*J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0006\u001a\u00020\u0004H\u0002J\b\u0010\u0007\u001a\u00020\u0004H\u0002J\b\u0010\b\u001a\u00020\u0004H\u0002R\u001a\u0010\n\u001a\u00020\t8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\"\u0010\u0014\u001a\u00020\u00138\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0019\u0010\u001b\u001a\u0004\u0018\u00010\u001a8\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR$\u0010 \u001a\u0004\u0018\u00010\u001f8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u0016\u0010'\u001a\u00020&8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b'\u0010(¨\u0006,"}, d2 = {"Lcom/amazon/mp3/fragment/viewmodel/RedownloadKatanaMusicViewModel;", "Lcom/amazon/mp3/fragment/viewmodel/BaseViewModel;", "", "id", "", "notifyPropertyChanged", "setupPrimaryButton", "setupSecondaryButton", "setupCloseIcon", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/amazon/mp3/fragment/view/RedownloadOfflineMusicFragmentBinding;", "mBinding", "Lcom/amazon/mp3/fragment/view/RedownloadOfflineMusicFragmentBinding;", "getMBinding", "()Lcom/amazon/mp3/fragment/view/RedownloadOfflineMusicFragmentBinding;", "Lcom/amazon/mp3/fragment/model/RedownloadOfflineMusicDialogModel;", "dialogModel", "Lcom/amazon/mp3/fragment/model/RedownloadOfflineMusicDialogModel;", "getDialogModel", "()Lcom/amazon/mp3/fragment/model/RedownloadOfflineMusicDialogModel;", "setDialogModel", "(Lcom/amazon/mp3/fragment/model/RedownloadOfflineMusicDialogModel;)V", "Lcom/amazon/mp3/library/item/RedownloadableTrack;", "redownloadableTrack", "Lcom/amazon/mp3/library/item/RedownloadableTrack;", "getRedownloadableTrack", "()Lcom/amazon/mp3/library/item/RedownloadableTrack;", "Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "viewModelCallbacks", "Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "getViewModelCallbacks", "()Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "setViewModelCallbacks", "(Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;)V", "", "actionTaken", "Z", "<init>", "(Landroid/content/Context;Lcom/amazon/mp3/fragment/view/RedownloadOfflineMusicFragmentBinding;Lcom/amazon/mp3/fragment/model/RedownloadOfflineMusicDialogModel;Lcom/amazon/mp3/library/item/RedownloadableTrack;Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class RedownloadKatanaMusicViewModel extends BaseViewModel {
    private static final String TAG = RedownloadKatanaMusicViewModel.class.getSimpleName();
    private boolean actionTaken;
    private final Context context;
    private RedownloadOfflineMusicDialogModel dialogModel;
    private final RedownloadOfflineMusicFragmentBinding mBinding;
    private final RedownloadableTrack redownloadableTrack;
    private ViewModelCallbacks viewModelCallbacks;

    public Context getContext() {
        return this.context;
    }

    public final RedownloadOfflineMusicFragmentBinding getMBinding() {
        return this.mBinding;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RedownloadKatanaMusicViewModel(Context context, RedownloadOfflineMusicFragmentBinding mBinding, RedownloadOfflineMusicDialogModel dialogModel, RedownloadableTrack redownloadableTrack, ViewModelCallbacks viewModelCallbacks) {
        super(context, viewModelCallbacks);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mBinding, "mBinding");
        Intrinsics.checkNotNullParameter(dialogModel, "dialogModel");
        this.context = context;
        this.mBinding = mBinding;
        this.dialogModel = dialogModel;
        this.redownloadableTrack = redownloadableTrack;
        this.viewModelCallbacks = viewModelCallbacks;
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

    public ViewModelCallbacks getViewModelCallbacks() {
        return this.viewModelCallbacks;
    }

    private final void notifyPropertyChanged(int id) {
        if (id == this.mBinding.getMPrimaryButton().getId()) {
            RedownloadableTrack redownloadableTrack = this.redownloadableTrack;
            if (redownloadableTrack == null || redownloadableTrack.getTrackType() == TrackType.CATALOG_PLAYLIST_TRACK) {
                this.mBinding.getMPrimaryButton().setVisibility(8);
            }
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
        this.mBinding.getMPrimaryButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.fragment.viewmodel.RedownloadKatanaMusicViewModel$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RedownloadKatanaMusicViewModel.m1178setupPrimaryButton$lambda0(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupPrimaryButton$lambda-0, reason: not valid java name */
    public static final void m1178setupPrimaryButton$lambda0(RedownloadKatanaMusicViewModel this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.actionTaken = true;
        this$0.sendUiClickMetric$DigitalMusicAndroid3P_automotiveProdRelease("selectQueueForDownload");
        ViewModelCallbacks viewModelCallbacks = this$0.getViewModelCallbacks();
        if (viewModelCallbacks == null) {
            return;
        }
        viewModelCallbacks.onEventOccurred(RedownloadMusicDialogPurpose.DOWNGRADE_KATANA_CONTENT.ordinal(), 2, this$0);
    }

    private final void setupSecondaryButton() {
        getContext();
        getMBinding().getMSecondaryButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.fragment.viewmodel.RedownloadKatanaMusicViewModel$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RedownloadKatanaMusicViewModel.m1179setupSecondaryButton$lambda2$lambda1(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupSecondaryButton$lambda-2$lambda-1, reason: not valid java name */
    public static final void m1179setupSecondaryButton$lambda2$lambda1(RedownloadKatanaMusicViewModel this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.actionTaken = true;
        this$0.sendUiClickMetric$DigitalMusicAndroid3P_automotiveProdRelease("selectRemoveUnavailableDownloads");
        ViewModelCallbacks viewModelCallbacks = this$0.getViewModelCallbacks();
        if (viewModelCallbacks == null) {
            return;
        }
        viewModelCallbacks.onEventOccurred(RedownloadMusicDialogPurpose.DOWNGRADE_KATANA_CONTENT.ordinal(), 0, this$0);
    }

    private final void setupCloseIcon() {
        this.mBinding.getMCloseDialogIcon().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.fragment.viewmodel.RedownloadKatanaMusicViewModel$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RedownloadKatanaMusicViewModel.m1177setupCloseIcon$lambda3(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupCloseIcon$lambda-3, reason: not valid java name */
    public static final void m1177setupCloseIcon$lambda3(RedownloadKatanaMusicViewModel this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ViewModelCallbacks viewModelCallbacks = this$0.getViewModelCallbacks();
        if (viewModelCallbacks == null) {
            return;
        }
        viewModelCallbacks.onEventOccurred(RedownloadMusicDialogPurpose.DOWNGRADE_KATANA_CONTENT.ordinal(), 1, this$0);
    }
}
