package com.amazon.mp3.fragment.viewmodel;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.amazon.mp3.activity.StreamingPreferenceModel;
import com.amazon.mp3.activity.TwoLinesListPreferenceModel;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.fragment.KatanaPreferencesPurpose;
import com.amazon.mp3.fragment.view.KatanaPreferencesBinding;
import com.amazon.mp3.net.dmls.StreamingBitrate;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.media.playback.Playback;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KatanaStreamingPreferencesViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u0000 72\u00020\u0001:\u00017B)\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0018\u001a\u00020\u0017\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c¢\u0006\u0004\b5\u00106J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\b\u0010\u0007\u001a\u00020\u0002H\u0002J\b\u0010\b\u001a\u00020\u0002H\u0002J\b\u0010\t\u001a\u00020\u0002H\u0002J\b\u0010\n\u001a\u00020\u0002H\u0002J\b\u0010\u000b\u001a\u00020\u0002H\u0002J\b\u0010\f\u001a\u00020\u0002H\u0002R\u001a\u0010\u000e\u001a\u00020\r8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0013\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0018\u001a\u00020\u00178\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR$\u0010\u001d\u001a\u0004\u0018\u00010\u001c8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\"\u0010#\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\"\u0010)\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b)\u0010$\u001a\u0004\b*\u0010&\"\u0004\b+\u0010(R\u0018\u0010,\u001a\u0004\u0018\u00010\u001c8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b,\u0010\u001eR\u0018\u0010-\u001a\u0004\u0018\u00010\u001c8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b-\u0010\u001eR\u0018\u0010/\u001a\u0004\u0018\u00010.8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b/\u00100R\u0018\u00101\u001a\u0004\u0018\u00010.8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b1\u00100R\u0016\u00103\u001a\u0002028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b3\u00104¨\u00068"}, d2 = {"Lcom/amazon/mp3/fragment/viewmodel/KatanaStreamingPreferencesViewModel;", "Lcom/amazon/mp3/fragment/viewmodel/BaseViewModel;", "", "setupCurrentCheckedPosition", "", "id", "notifyPropertyChanged", "initRecyclerViewModelForWifiSettings", "initRecyclerViewModelForOTASettings", "initViewModelCallbacksForWifiSettings", "initViewModelCallbacksForOTASettings", "setupSaveButton", "setupCancelButton", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/amazon/mp3/fragment/view/KatanaPreferencesBinding;", "mBinding", "Lcom/amazon/mp3/fragment/view/KatanaPreferencesBinding;", "getMBinding", "()Lcom/amazon/mp3/fragment/view/KatanaPreferencesBinding;", "Lcom/amazon/mp3/activity/StreamingPreferenceModel;", "dialogModel", "Lcom/amazon/mp3/activity/StreamingPreferenceModel;", "getDialogModel", "()Lcom/amazon/mp3/activity/StreamingPreferenceModel;", "Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "viewModelCallbacks", "Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "getViewModelCallbacks", "()Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "setViewModelCallbacks", "(Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;)V", "currentCheckedPositionForOTA", "I", "getCurrentCheckedPositionForOTA", "()I", "setCurrentCheckedPositionForOTA", "(I)V", "currentCheckedPositionForWifi", "getCurrentCheckedPositionForWifi", "setCurrentCheckedPositionForWifi", "mViewModelCallbacksForWifiSettings", "mViewModelCallbacksForOTASettings", "Lcom/amazon/mp3/fragment/viewmodel/TwoLinesPreferenceRecyclerViewModel;", "mTwoLinesPreferenceRecyclerViewModelForOTA", "Lcom/amazon/mp3/fragment/viewmodel/TwoLinesPreferenceRecyclerViewModel;", "mTwoLinesPreferenceRecyclerViewModelForWifi", "", "isMobileDataSupportedDevice", "Z", "<init>", "(Landroid/content/Context;Lcom/amazon/mp3/fragment/view/KatanaPreferencesBinding;Lcom/amazon/mp3/activity/StreamingPreferenceModel;Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class KatanaStreamingPreferencesViewModel extends BaseViewModel {
    private final Context context;
    private int currentCheckedPositionForOTA;
    private int currentCheckedPositionForWifi;
    private final StreamingPreferenceModel dialogModel;
    private boolean isMobileDataSupportedDevice;
    private final KatanaPreferencesBinding mBinding;
    private TwoLinesPreferenceRecyclerViewModel mTwoLinesPreferenceRecyclerViewModelForOTA;
    private TwoLinesPreferenceRecyclerViewModel mTwoLinesPreferenceRecyclerViewModelForWifi;
    private ViewModelCallbacks mViewModelCallbacksForOTASettings;
    private ViewModelCallbacks mViewModelCallbacksForWifiSettings;
    private ViewModelCallbacks viewModelCallbacks;

    public Context getContext() {
        return this.context;
    }

    public final KatanaPreferencesBinding getMBinding() {
        return this.mBinding;
    }

    public final StreamingPreferenceModel getDialogModel() {
        return this.dialogModel;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KatanaStreamingPreferencesViewModel(Context context, KatanaPreferencesBinding mBinding, StreamingPreferenceModel dialogModel, ViewModelCallbacks viewModelCallbacks) {
        super(context, viewModelCallbacks);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mBinding, "mBinding");
        Intrinsics.checkNotNullParameter(dialogModel, "dialogModel");
        this.context = context;
        this.mBinding = mBinding;
        this.dialogModel = dialogModel;
        this.viewModelCallbacks = viewModelCallbacks;
        this.isMobileDataSupportedDevice = !ConnectivityUtil.isWifiOnlyDevice(getContext());
        initViewModelCallbacksForOTASettings();
        initViewModelCallbacksForWifiSettings();
        initRecyclerViewModelForWifiSettings();
        initRecyclerViewModelForOTASettings();
        setupCurrentCheckedPosition();
        notifyPropertyChanged(mBinding.getMTitleView().getId());
        notifyPropertyChanged(mBinding.getMNetworkPreferenceContainer().getId());
        notifyPropertyChanged(mBinding.getMSaveButton().getId());
        notifyPropertyChanged(mBinding.getMCancelButton().getId());
        notifyPropertyChanged(mBinding.getMSummaryHeader().getId());
        notifyPropertyChanged(mBinding.getMKatanaRecyclerViewBinding().getMRecyclerView().getId());
        setupSaveButton();
        setupCancelButton();
    }

    public ViewModelCallbacks getViewModelCallbacks() {
        return this.viewModelCallbacks;
    }

    public final int getCurrentCheckedPositionForOTA() {
        return this.currentCheckedPositionForOTA;
    }

    public final void setCurrentCheckedPositionForOTA(int i) {
        this.currentCheckedPositionForOTA = i;
    }

    public final int getCurrentCheckedPositionForWifi() {
        return this.currentCheckedPositionForWifi;
    }

    public final void setCurrentCheckedPositionForWifi(int i) {
        this.currentCheckedPositionForWifi = i;
    }

    private final void setupCurrentCheckedPosition() {
        for (TwoLinesListPreferenceModel twoLinesListPreferenceModel : this.dialogModel.getOtaPreferenceList()) {
            if (twoLinesListPreferenceModel.getIsChecked()) {
                this.currentCheckedPositionForOTA = this.dialogModel.getOtaPreferenceList().indexOf(twoLinesListPreferenceModel);
            }
        }
        for (TwoLinesListPreferenceModel twoLinesListPreferenceModel2 : this.dialogModel.getWifiPreferenceList()) {
            if (twoLinesListPreferenceModel2.getIsChecked()) {
                this.currentCheckedPositionForWifi = this.dialogModel.getWifiPreferenceList().indexOf(twoLinesListPreferenceModel2);
            }
        }
    }

    private final void notifyPropertyChanged(int id) {
        if (id == this.mBinding.getMTitleView().getId()) {
            this.mBinding.getMTitleView().setText(this.dialogModel.getTitle());
            return;
        }
        if (id == this.mBinding.getMSummaryHeader().getId()) {
            this.mBinding.getMSummaryHeader().setVisibility(0);
            this.mBinding.getMMobileDataTitle().setVisibility(this.isMobileDataSupportedDevice ? 0 : 8);
            TextView mWifiSummary = this.mBinding.getMWifiSummary();
            Context context = getContext();
            Object[] objArr = new Object[1];
            objArr[0] = UserSubscriptionUtil.getUserSubscription().isKatana() ? getContext().getString(R.string.dmusic_multi_bitrate_wifi_message_subtext) : "";
            mWifiSummary.setText(context.getString(R.string.dmusic_multi_bitrate_wifi_message, objArr));
            return;
        }
        if (id == this.mBinding.getMKatanaRecyclerViewBinding().getMRecyclerView().getId()) {
            this.mBinding.getMKatanaRecyclerViewBinding().getMRecyclerView().setVisibility(this.isMobileDataSupportedDevice ? 0 : 8);
            return;
        }
        if (id == this.mBinding.getMNetworkPreferenceContainer().getId()) {
            this.mBinding.getMNetworkPreferenceContainer().setVisibility(8);
            return;
        }
        if (id == this.mBinding.getMSaveButton().getId()) {
            this.mBinding.getMSaveButton().setText(this.dialogModel.getSaveButtonText());
            this.mBinding.getMSaveButton().setVisibility(0);
        } else if (id == this.mBinding.getMCancelButton().getId()) {
            this.mBinding.getMCancelButton().setText(this.dialogModel.getCancelButtonText());
        }
    }

    private final void initRecyclerViewModelForWifiSettings() {
        this.mTwoLinesPreferenceRecyclerViewModelForWifi = new TwoLinesPreferenceRecyclerViewModel(getContext(), this.mBinding.getMKatanaRecyclerViewBindingForWifi(), this.dialogModel.getWifiPreferenceList(), this.mViewModelCallbacksForWifiSettings);
    }

    private final void initRecyclerViewModelForOTASettings() {
        this.mTwoLinesPreferenceRecyclerViewModelForOTA = new TwoLinesPreferenceRecyclerViewModel(getContext(), this.mBinding.getMKatanaRecyclerViewBinding(), this.dialogModel.getOtaPreferenceList(), this.mViewModelCallbacksForOTASettings);
    }

    private final void initViewModelCallbacksForWifiSettings() {
        this.mViewModelCallbacksForWifiSettings = new ViewModelCallbacks() { // from class: com.amazon.mp3.fragment.viewmodel.KatanaStreamingPreferencesViewModel.initViewModelCallbacksForWifiSettings.1
            @Override // com.amazon.mp3.fragment.viewmodel.ViewModelCallbacks
            public void onEventOccurred(int callerId, int actionId, BaseViewModel viewModelInstance) {
                TwoLinesPreferenceRecyclerViewModel twoLinesPreferenceRecyclerViewModel;
                Intrinsics.checkNotNullParameter(viewModelInstance, "viewModelInstance");
                if (actionId != 1 || (twoLinesPreferenceRecyclerViewModel = KatanaStreamingPreferencesViewModel.this.mTwoLinesPreferenceRecyclerViewModelForWifi) == null) {
                    return;
                }
                KatanaStreamingPreferencesViewModel katanaStreamingPreferencesViewModel = KatanaStreamingPreferencesViewModel.this;
                katanaStreamingPreferencesViewModel.setCurrentCheckedPositionForWifi(twoLinesPreferenceRecyclerViewModel.getCurrentCheckedPosition());
                String metricEventForWIFI = StreamingBitrate.fromEntryValue(katanaStreamingPreferencesViewModel.getDialogModel().getWifiPreferenceList().get(katanaStreamingPreferencesViewModel.getCurrentCheckedPositionForWifi()).getEntryValue()).getMetricEventForWIFI();
                Intrinsics.checkNotNullExpressionValue(metricEventForWIFI, "fromEntryValue(dialogMod…Value).metricEventForWIFI");
                katanaStreamingPreferencesViewModel.sendUiClickMetric$DigitalMusicAndroid3P_automotiveProdRelease(metricEventForWIFI);
            }
        };
    }

    private final void initViewModelCallbacksForOTASettings() {
        this.mViewModelCallbacksForOTASettings = new ViewModelCallbacks() { // from class: com.amazon.mp3.fragment.viewmodel.KatanaStreamingPreferencesViewModel.initViewModelCallbacksForOTASettings.1
            @Override // com.amazon.mp3.fragment.viewmodel.ViewModelCallbacks
            public void onEventOccurred(int callerId, int actionId, BaseViewModel viewModelInstance) {
                TwoLinesPreferenceRecyclerViewModel twoLinesPreferenceRecyclerViewModel;
                Intrinsics.checkNotNullParameter(viewModelInstance, "viewModelInstance");
                if (actionId != 1 || (twoLinesPreferenceRecyclerViewModel = KatanaStreamingPreferencesViewModel.this.mTwoLinesPreferenceRecyclerViewModelForOTA) == null) {
                    return;
                }
                KatanaStreamingPreferencesViewModel katanaStreamingPreferencesViewModel = KatanaStreamingPreferencesViewModel.this;
                katanaStreamingPreferencesViewModel.setCurrentCheckedPositionForOTA(twoLinesPreferenceRecyclerViewModel.getCurrentCheckedPosition());
                String metricEventForOTA = StreamingBitrate.fromEntryValue(katanaStreamingPreferencesViewModel.getDialogModel().getOtaPreferenceList().get(katanaStreamingPreferencesViewModel.getCurrentCheckedPositionForOTA()).getEntryValue()).getMetricEventForOTA();
                Intrinsics.checkNotNullExpressionValue(metricEventForOTA, "fromEntryValue(dialogMod…yValue).metricEventForOTA");
                katanaStreamingPreferencesViewModel.sendUiClickMetric$DigitalMusicAndroid3P_automotiveProdRelease(metricEventForOTA);
            }
        };
    }

    private final void setupSaveButton() {
        final Context context = getContext();
        getMBinding().getMSaveButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.fragment.viewmodel.KatanaStreamingPreferencesViewModel$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                KatanaStreamingPreferencesViewModel.m1175setupSaveButton$lambda1$lambda0(this.f$0, context, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupSaveButton$lambda-1$lambda-0, reason: not valid java name */
    public static final void m1175setupSaveButton$lambda1$lambda0(KatanaStreamingPreferencesViewModel this$0, Context c, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(c, "$c");
        String entryValue = this$0.dialogModel.getOtaPreferenceList().get(this$0.currentCheckedPositionForOTA).getEntryValue();
        String entryValue2 = this$0.dialogModel.getWifiPreferenceList().get(this$0.currentCheckedPositionForWifi).getEntryValue();
        SettingsUtil.setMultiBitrateStreamingPreferenceForOTA(c, entryValue);
        SettingsUtil.setMultiBitrateStreamingPreferenceForWifi(c, entryValue2);
        Playback.getInstance().getPlaybackConfig().setBitRateSelectionForWifi(StreamingBitrate.fromEntryValue(entryValue2).convertToBitRateSelection());
        Playback.getInstance().getPlaybackConfig().setBitRateSelectionForOTA(StreamingBitrate.fromEntryValue(entryValue).convertToBitRateSelection());
        this$0.sendUiClickMetric$DigitalMusicAndroid3P_automotiveProdRelease("selectSave");
        ViewModelCallbacks viewModelCallbacks = this$0.getViewModelCallbacks();
        if (viewModelCallbacks == null) {
            return;
        }
        viewModelCallbacks.onEventOccurred(KatanaPreferencesPurpose.STREAMING_PREFERENCES.ordinal(), 1, this$0);
    }

    private final void setupCancelButton() {
        this.mBinding.getMCancelButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.fragment.viewmodel.KatanaStreamingPreferencesViewModel$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                KatanaStreamingPreferencesViewModel.m1174setupCancelButton$lambda2(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupCancelButton$lambda-2, reason: not valid java name */
    public static final void m1174setupCancelButton$lambda2(KatanaStreamingPreferencesViewModel this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.sendUiClickMetric$DigitalMusicAndroid3P_automotiveProdRelease("selectCancel");
        ViewModelCallbacks viewModelCallbacks = this$0.getViewModelCallbacks();
        if (viewModelCallbacks == null) {
            return;
        }
        viewModelCallbacks.onEventOccurred(KatanaPreferencesPurpose.STREAMING_PREFERENCES.ordinal(), 1, this$0);
    }
}
