package com.amazon.mp3.fragment.viewmodel;

import android.content.Context;
import android.view.View;
import com.amazon.mp3.fragment.BetaDialogPurpose;
import com.amazon.mp3.fragment.model.BetaDialogModel;
import com.amazon.mp3.fragment.view.FragmentBetaDialogBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BetaDialogViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \"2\u00020\u0001:\u0001\"B)\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019¢\u0006\u0004\b \u0010!J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0006\u001a\u00020\u0004H\u0002J\b\u0010\u0007\u001a\u00020\u0004H\u0002R\u001a\u0010\t\u001a\u00020\b8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\"\u0010\u0013\u001a\u00020\u00128\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R$\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u0006#"}, d2 = {"Lcom/amazon/mp3/fragment/viewmodel/BetaDialogViewModel;", "Lcom/amazon/mp3/fragment/viewmodel/BaseViewModel;", "", "id", "", "notifyPropertyChanged", "setupPrimaryButton", "setupCloseIcon", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/amazon/mp3/fragment/view/FragmentBetaDialogBinding;", "mBinding", "Lcom/amazon/mp3/fragment/view/FragmentBetaDialogBinding;", "getMBinding", "()Lcom/amazon/mp3/fragment/view/FragmentBetaDialogBinding;", "Lcom/amazon/mp3/fragment/model/BetaDialogModel;", "dialogModel", "Lcom/amazon/mp3/fragment/model/BetaDialogModel;", "getDialogModel", "()Lcom/amazon/mp3/fragment/model/BetaDialogModel;", "setDialogModel", "(Lcom/amazon/mp3/fragment/model/BetaDialogModel;)V", "Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "viewModelCallbacks", "Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "getViewModelCallbacks", "()Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "setViewModelCallbacks", "(Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;)V", "<init>", "(Landroid/content/Context;Lcom/amazon/mp3/fragment/view/FragmentBetaDialogBinding;Lcom/amazon/mp3/fragment/model/BetaDialogModel;Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BetaDialogViewModel extends BaseViewModel {
    private final Context context;
    private BetaDialogModel dialogModel;
    private final FragmentBetaDialogBinding mBinding;
    private ViewModelCallbacks viewModelCallbacks;

    public Context getContext() {
        return this.context;
    }

    public final FragmentBetaDialogBinding getMBinding() {
        return this.mBinding;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BetaDialogViewModel(Context context, FragmentBetaDialogBinding mBinding, BetaDialogModel dialogModel, ViewModelCallbacks viewModelCallbacks) {
        super(context, viewModelCallbacks);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mBinding, "mBinding");
        Intrinsics.checkNotNullParameter(dialogModel, "dialogModel");
        this.context = context;
        this.mBinding = mBinding;
        this.dialogModel = dialogModel;
        this.viewModelCallbacks = viewModelCallbacks;
        notifyPropertyChanged(mBinding.getMPrimaryButton().getId());
        notifyPropertyChanged(mBinding.getMTitleView().getId());
        notifyPropertyChanged(mBinding.getMMessageView().getId());
        setupPrimaryButton();
        setupCloseIcon();
    }

    public ViewModelCallbacks getViewModelCallbacks() {
        return this.viewModelCallbacks;
    }

    private final void notifyPropertyChanged(int id) {
        if (id == this.mBinding.getMPrimaryButton().getId()) {
            this.mBinding.getMPrimaryButton().setText(this.dialogModel.getPrimaryButtonText());
        } else if (id == this.mBinding.getMMessageView().getId()) {
            this.mBinding.getMMessageView().setText(this.dialogModel.getMessage());
        } else if (id == this.mBinding.getMTitleView().getId()) {
            this.mBinding.getMTitleView().setText(this.dialogModel.getTitle());
        }
    }

    private final void setupPrimaryButton() {
        getContext();
        getMBinding().getMPrimaryButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.fragment.viewmodel.BetaDialogViewModel$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BetaDialogViewModel.m1163setupPrimaryButton$lambda1$lambda0(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupPrimaryButton$lambda-1$lambda-0, reason: not valid java name */
    public static final void m1163setupPrimaryButton$lambda1$lambda0(BetaDialogViewModel this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ViewModelCallbacks viewModelCallbacks = this$0.getViewModelCallbacks();
        if (viewModelCallbacks == null) {
            return;
        }
        viewModelCallbacks.onEventOccurred(BetaDialogPurpose.HARLEY_DIALOG.ordinal(), 1, this$0);
    }

    private final void setupCloseIcon() {
        this.mBinding.getMCloseDialogIcon().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.fragment.viewmodel.BetaDialogViewModel$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BetaDialogViewModel.m1162setupCloseIcon$lambda2(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupCloseIcon$lambda-2, reason: not valid java name */
    public static final void m1162setupCloseIcon$lambda2(BetaDialogViewModel this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ViewModelCallbacks viewModelCallbacks = this$0.getViewModelCallbacks();
        if (viewModelCallbacks == null) {
            return;
        }
        viewModelCallbacks.onEventOccurred(BetaDialogPurpose.HARLEY_DIALOG.ordinal(), 1, this$0);
    }
}
