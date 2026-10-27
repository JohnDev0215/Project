package com.amazon.mp3.fragment.viewmodel;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.amazon.mp3.activity.DownloadPreferenceModel;
import com.amazon.mp3.activity.TwoLinesListPreferenceModel;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.DownloadQualityPreferences;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.fragment.KatanaPreferencesPurpose;
import com.amazon.mp3.fragment.view.KatanaPreferencesBinding;
import com.amazon.mp3.util.ConnectivityUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KatanaDownloadPreferencesViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 72\u00020\u0001:\u00017B)\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d¢\u0006\u0004\b5\u00106J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0002J\b\u0010\u0005\u001a\u00020\u0002H\u0002J\u0010\u0010\b\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\b\u0010\t\u001a\u00020\u0002H\u0002J\b\u0010\n\u001a\u00020\u0002H\u0002J\b\u0010\u000b\u001a\u00020\u0002H\u0002R\u001a\u0010\r\u001a\u00020\f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0012\u001a\u00020\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\"\u0010\u0017\u001a\u00020\u00168\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR$\u0010\u001e\u001a\u0004\u0018\u00010\u001d8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\"\u0010$\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\"\u0010+\u001a\u00020*8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u0016\u00101\u001a\u00020\u001d8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b1\u0010\u001fR\u0018\u00103\u001a\u0004\u0018\u0001028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b3\u00104¨\u00068"}, d2 = {"Lcom/amazon/mp3/fragment/viewmodel/KatanaDownloadPreferencesViewModel;", "Lcom/amazon/mp3/fragment/viewmodel/BaseViewModel;", "", "initViewModelCallbacks", "setupCurrentCheckedPosition", "initRecyclerViewModel", "", "id", "notifyPropertyChanged", "setupCheckbox", "setupSaveButton", "setupCancelButton", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/amazon/mp3/fragment/view/KatanaPreferencesBinding;", "mBinding", "Lcom/amazon/mp3/fragment/view/KatanaPreferencesBinding;", "getMBinding", "()Lcom/amazon/mp3/fragment/view/KatanaPreferencesBinding;", "Lcom/amazon/mp3/activity/DownloadPreferenceModel;", "dialogModel", "Lcom/amazon/mp3/activity/DownloadPreferenceModel;", "getDialogModel", "()Lcom/amazon/mp3/activity/DownloadPreferenceModel;", "setDialogModel", "(Lcom/amazon/mp3/activity/DownloadPreferenceModel;)V", "Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "viewModelCallbacks", "Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "getViewModelCallbacks", "()Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "setViewModelCallbacks", "(Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;)V", "currentCheckedPosition", "I", "getCurrentCheckedPosition", "()I", "setCurrentCheckedPosition", "(I)V", "", "downloadOnWifiChecked", "Z", "getDownloadOnWifiChecked", "()Z", "setDownloadOnWifiChecked", "(Z)V", "mViewModelCallbacks", "Lcom/amazon/mp3/fragment/viewmodel/TwoLinesPreferenceRecyclerViewModel;", "mTwoLinesPreferenceRecyclerViewModel", "Lcom/amazon/mp3/fragment/viewmodel/TwoLinesPreferenceRecyclerViewModel;", "<init>", "(Landroid/content/Context;Lcom/amazon/mp3/fragment/view/KatanaPreferencesBinding;Lcom/amazon/mp3/activity/DownloadPreferenceModel;Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class KatanaDownloadPreferencesViewModel extends BaseViewModel {
    private final Context context;
    private int currentCheckedPosition;
    private DownloadPreferenceModel dialogModel;
    private boolean downloadOnWifiChecked;
    private final KatanaPreferencesBinding mBinding;
    private TwoLinesPreferenceRecyclerViewModel mTwoLinesPreferenceRecyclerViewModel;
    private ViewModelCallbacks mViewModelCallbacks;
    private ViewModelCallbacks viewModelCallbacks;

    public Context getContext() {
        return this.context;
    }

    public final KatanaPreferencesBinding getMBinding() {
        return this.mBinding;
    }

    public final DownloadPreferenceModel getDialogModel() {
        return this.dialogModel;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KatanaDownloadPreferencesViewModel(Context context, KatanaPreferencesBinding mBinding, DownloadPreferenceModel dialogModel, ViewModelCallbacks viewModelCallbacks) {
        super(context, viewModelCallbacks);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mBinding, "mBinding");
        Intrinsics.checkNotNullParameter(dialogModel, "dialogModel");
        this.context = context;
        this.mBinding = mBinding;
        this.dialogModel = dialogModel;
        this.viewModelCallbacks = viewModelCallbacks;
        this.downloadOnWifiChecked = true;
        this.mViewModelCallbacks = new ViewModelCallbacks() { // from class: com.amazon.mp3.fragment.viewmodel.KatanaDownloadPreferencesViewModel$mViewModelCallbacks$1
            @Override // com.amazon.mp3.fragment.viewmodel.ViewModelCallbacks
            public void onEventOccurred(int callerId, int actionId, BaseViewModel viewModelInstance) {
                TwoLinesPreferenceRecyclerViewModel twoLinesPreferenceRecyclerViewModel;
                Intrinsics.checkNotNullParameter(viewModelInstance, "viewModelInstance");
                if (actionId != 1 || (twoLinesPreferenceRecyclerViewModel = this.this$0.mTwoLinesPreferenceRecyclerViewModel) == null) {
                    return;
                }
                this.this$0.setCurrentCheckedPosition(twoLinesPreferenceRecyclerViewModel.getCurrentCheckedPosition());
            }
        };
        initViewModelCallbacks();
        initRecyclerViewModel();
        setupCurrentCheckedPosition();
        notifyPropertyChanged(mBinding.getMTitleView().getId());
        notifyPropertyChanged(mBinding.getMDownloadOnWifiCheckbox().getId());
        notifyPropertyChanged(mBinding.getMMessageView().getId());
        notifyPropertyChanged(mBinding.getMSaveButton().getId());
        notifyPropertyChanged(mBinding.getMCancelButton().getId());
        setupSaveButton();
        setupCancelButton();
        setupCheckbox();
    }

    public ViewModelCallbacks getViewModelCallbacks() {
        return this.viewModelCallbacks;
    }

    public final int getCurrentCheckedPosition() {
        return this.currentCheckedPosition;
    }

    public final void setCurrentCheckedPosition(int i) {
        this.currentCheckedPosition = i;
    }

    private final void initViewModelCallbacks() {
        this.mViewModelCallbacks = new ViewModelCallbacks() { // from class: com.amazon.mp3.fragment.viewmodel.KatanaDownloadPreferencesViewModel.initViewModelCallbacks.1
            @Override // com.amazon.mp3.fragment.viewmodel.ViewModelCallbacks
            public void onEventOccurred(int callerId, int actionId, BaseViewModel viewModelInstance) {
                TwoLinesPreferenceRecyclerViewModel twoLinesPreferenceRecyclerViewModel;
                Intrinsics.checkNotNullParameter(viewModelInstance, "viewModelInstance");
                if (actionId != 1 || (twoLinesPreferenceRecyclerViewModel = KatanaDownloadPreferencesViewModel.this.mTwoLinesPreferenceRecyclerViewModel) == null) {
                    return;
                }
                KatanaDownloadPreferencesViewModel katanaDownloadPreferencesViewModel = KatanaDownloadPreferencesViewModel.this;
                katanaDownloadPreferencesViewModel.setCurrentCheckedPosition(twoLinesPreferenceRecyclerViewModel.getCurrentCheckedPosition());
                katanaDownloadPreferencesViewModel.sendUiClickMetric$DigitalMusicAndroid3P_automotiveProdRelease(DownloadQualityPreferences.INSTANCE.fromEntryValue(katanaDownloadPreferencesViewModel.getDialogModel().getDownloadPreferenceList().get(katanaDownloadPreferencesViewModel.getCurrentCheckedPosition()).getEntryValue()).getMetricEvent());
            }
        };
    }

    private final void setupCurrentCheckedPosition() {
        for (TwoLinesListPreferenceModel twoLinesListPreferenceModel : this.dialogModel.getDownloadPreferenceList()) {
            if (twoLinesListPreferenceModel.getIsChecked()) {
                this.currentCheckedPosition = this.dialogModel.getDownloadPreferenceList().indexOf(twoLinesListPreferenceModel);
            }
        }
        this.downloadOnWifiChecked = SettingsUtil.getBooleanPref(getContext(), R.string.setting_key_downloads_wifi_only_pref);
    }

    private final void initRecyclerViewModel() {
        this.mTwoLinesPreferenceRecyclerViewModel = new TwoLinesPreferenceRecyclerViewModel(getContext(), this.mBinding.getMKatanaRecyclerViewBinding(), this.dialogModel.getDownloadPreferenceList(), this.mViewModelCallbacks);
    }

    private final void notifyPropertyChanged(int id) {
        if (id == this.mBinding.getMTitleView().getId()) {
            this.mBinding.getMTitleView().setText(this.dialogModel.getTitle());
            return;
        }
        if (id == this.mBinding.getMMessageView().getId()) {
            this.mBinding.getMMessageView().setText(this.dialogModel.getMessage(), TextView.BufferType.SPANNABLE);
            this.mBinding.getMMessageView().setMovementMethod(LinkMovementMethod.getInstance());
            return;
        }
        if (id == this.mBinding.getMDownloadOnWifiCheckbox().getId()) {
            if (ConnectivityUtil.isWifiOnlyDevice(getContext())) {
                this.mBinding.getMDownloadOnWifiCheckbox().setVisibility(8);
                return;
            } else {
                this.mBinding.getMDownloadOnWifiCheckbox().setVisibility(0);
                this.mBinding.getMDownloadOnWifiCheckbox().setChecked(this.downloadOnWifiChecked);
                return;
            }
        }
        if (id == this.mBinding.getMSaveButton().getId()) {
            this.mBinding.getMSaveButton().setText(this.dialogModel.getSaveButtonText());
        } else if (id == this.mBinding.getMCancelButton().getId()) {
            this.mBinding.getMCancelButton().setText(this.dialogModel.getCancelButtonText());
        }
    }

    private final void setupCheckbox() {
        if (ConnectivityUtil.isWifiOnlyDevice()) {
            return;
        }
        getContext();
        getMBinding().getMDownloadOnWifiCheckbox().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.amazon.mp3.fragment.viewmodel.KatanaDownloadPreferencesViewModel$$ExternalSyntheticLambda0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                KatanaDownloadPreferencesViewModel.m1172setupCheckbox$lambda1$lambda0(this.f$0, compoundButton, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupCheckbox$lambda-1$lambda-0, reason: not valid java name */
    public static final void m1172setupCheckbox$lambda1$lambda0(KatanaDownloadPreferencesViewModel this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.downloadOnWifiChecked = z;
        if (z) {
            this$0.sendUiClickMetric$DigitalMusicAndroid3P_automotiveProdRelease("selectDownloadOnWifiOn");
        } else {
            this$0.sendUiClickMetric$DigitalMusicAndroid3P_automotiveProdRelease("selectDownloadOnWifiOff");
        }
        this$0.notifyPropertyChanged(this$0.mBinding.getMDownloadOnWifiCheckbox().getId());
    }

    private final void setupSaveButton() {
        final Context context = getContext();
        getMBinding().getMSaveButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.fragment.viewmodel.KatanaDownloadPreferencesViewModel$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                KatanaDownloadPreferencesViewModel.m1173setupSaveButton$lambda3$lambda2(this.f$0, context, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupSaveButton$lambda-3$lambda-2, reason: not valid java name */
    public static final void m1173setupSaveButton$lambda3$lambda2(KatanaDownloadPreferencesViewModel this$0, Context c, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(c, "$c");
        SettingsUtil.setDownloadQualityPreference(c, this$0.dialogModel.getDownloadPreferenceList().get(this$0.currentCheckedPosition).getEntryValue());
        MusicDownloader.getInstance(this$0.getContext()).updateHarleyConfig();
        SettingsUtil.setBooleanPref(c, R.string.setting_key_downloads_wifi_only_pref, this$0.downloadOnWifiChecked);
        MusicDownloader.getInstance(this$0.getContext()).setDownloadOnWifiOnly(this$0.downloadOnWifiChecked);
        this$0.sendUiClickMetric$DigitalMusicAndroid3P_automotiveProdRelease("selectSave");
        ViewModelCallbacks viewModelCallbacks = this$0.getViewModelCallbacks();
        if (viewModelCallbacks == null) {
            return;
        }
        viewModelCallbacks.onEventOccurred(KatanaPreferencesPurpose.DOWNLOAD_PREFERENCES.ordinal(), 1, this$0);
    }

    private final void setupCancelButton() {
        this.mBinding.getMCancelButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.fragment.viewmodel.KatanaDownloadPreferencesViewModel$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                KatanaDownloadPreferencesViewModel.m1171setupCancelButton$lambda4(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupCancelButton$lambda-4, reason: not valid java name */
    public static final void m1171setupCancelButton$lambda4(KatanaDownloadPreferencesViewModel this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.sendUiClickMetric$DigitalMusicAndroid3P_automotiveProdRelease("selectCancel");
        ViewModelCallbacks viewModelCallbacks = this$0.getViewModelCallbacks();
        if (viewModelCallbacks == null) {
            return;
        }
        viewModelCallbacks.onEventOccurred(KatanaPreferencesPurpose.DOWNLOAD_PREFERENCES.ordinal(), 1, this$0);
    }
}
