package com.amazon.mp3.auto.carmode;

import android.content.Context;
import android.view.View;
import com.amazon.mp3.activity.CarModeFabPreferenceModel;
import com.amazon.mp3.activity.TwoLinesListPreferenceModel;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.auto.AutoUiEventUtil;
import com.amazon.mp3.fragment.viewmodel.BaseViewModel;
import com.amazon.mp3.fragment.viewmodel.TwoLinesPreferenceRecyclerViewModel;
import com.amazon.mp3.fragment.viewmodel.ViewModelCallbacks;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarModePreferencesViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 -2\u00020\u0001:\u0001-B)\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u001a¢\u0006\u0004\b+\u0010,J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\b\u0010\u0007\u001a\u00020\u0002H\u0002J\b\u0010\b\u001a\u00020\u0002H\u0002J\b\u0010\t\u001a\u00020\u0002H\u0002J\b\u0010\n\u001a\u00020\u0002H\u0002R\u001a\u0010\f\u001a\u00020\u000b8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0011\u001a\u00020\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0016\u001a\u00020\u00158\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R$\u0010\u001b\u001a\u0004\u0018\u00010\u001a8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\"\u0010!\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0018\u0010'\u001a\u0004\u0018\u00010\u001a8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b'\u0010\u001cR\u0018\u0010)\u001a\u0004\u0018\u00010(8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b)\u0010*¨\u0006."}, d2 = {"Lcom/amazon/mp3/auto/carmode/CarModePreferencesViewModel;", "Lcom/amazon/mp3/fragment/viewmodel/BaseViewModel;", "", "setupCurrentCheckedPosition", "", "id", "notifyPropertyChanged", "initRecyclerViewModelForShowCarModeFabPreferences", "initViewModelCallbacksForCarModeFAB", "setupSaveButton", "setupCancelButton", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/amazon/mp3/auto/carmode/CarModePreferencesBinding;", "mBinding", "Lcom/amazon/mp3/auto/carmode/CarModePreferencesBinding;", "getMBinding", "()Lcom/amazon/mp3/auto/carmode/CarModePreferencesBinding;", "Lcom/amazon/mp3/activity/CarModeFabPreferenceModel;", "dialogModel", "Lcom/amazon/mp3/activity/CarModeFabPreferenceModel;", "getDialogModel", "()Lcom/amazon/mp3/activity/CarModeFabPreferenceModel;", "Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "viewModelCallbacks", "Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "getViewModelCallbacks", "()Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "setViewModelCallbacks", "(Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;)V", "currentCheckedPositionForShowCarMode", "I", "getCurrentCheckedPositionForShowCarMode", "()I", "setCurrentCheckedPositionForShowCarMode", "(I)V", "viewModelCallbacksForCarMode", "Lcom/amazon/mp3/fragment/viewmodel/TwoLinesPreferenceRecyclerViewModel;", "twoLinesPreferenceRecyclerViewmodelForCarMode", "Lcom/amazon/mp3/fragment/viewmodel/TwoLinesPreferenceRecyclerViewModel;", "<init>", "(Landroid/content/Context;Lcom/amazon/mp3/auto/carmode/CarModePreferencesBinding;Lcom/amazon/mp3/activity/CarModeFabPreferenceModel;Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CarModePreferencesViewModel extends BaseViewModel {
    private final Context context;
    private int currentCheckedPositionForShowCarMode;
    private final CarModeFabPreferenceModel dialogModel;
    private final CarModePreferencesBinding mBinding;
    private TwoLinesPreferenceRecyclerViewModel twoLinesPreferenceRecyclerViewmodelForCarMode;
    private ViewModelCallbacks viewModelCallbacks;
    private ViewModelCallbacks viewModelCallbacksForCarMode;

    public Context getContext() {
        return this.context;
    }

    public final CarModePreferencesBinding getMBinding() {
        return this.mBinding;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CarModePreferencesViewModel(Context context, CarModePreferencesBinding mBinding, CarModeFabPreferenceModel dialogModel, ViewModelCallbacks viewModelCallbacks) {
        super(context, viewModelCallbacks);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mBinding, "mBinding");
        Intrinsics.checkNotNullParameter(dialogModel, "dialogModel");
        this.context = context;
        this.mBinding = mBinding;
        this.dialogModel = dialogModel;
        this.viewModelCallbacks = viewModelCallbacks;
        initViewModelCallbacksForCarModeFAB();
        initRecyclerViewModelForShowCarModeFabPreferences();
        setupCurrentCheckedPosition();
        notifyPropertyChanged(mBinding.getTitleView().getId());
        notifyPropertyChanged(mBinding.getSaveButton().getId());
        notifyPropertyChanged(mBinding.getCancelbutton().getId());
        notifyPropertyChanged(mBinding.getShowCarModeFabRecyclerView().getMRecyclerView().getId());
        setupSaveButton();
        setupCancelButton();
    }

    public ViewModelCallbacks getViewModelCallbacks() {
        return this.viewModelCallbacks;
    }

    public final int getCurrentCheckedPositionForShowCarMode() {
        return this.currentCheckedPositionForShowCarMode;
    }

    public final void setCurrentCheckedPositionForShowCarMode(int i) {
        this.currentCheckedPositionForShowCarMode = i;
    }

    private final void setupCurrentCheckedPosition() {
        for (TwoLinesListPreferenceModel twoLinesListPreferenceModel : this.dialogModel.getShowCarModePreferenceList()) {
            if (twoLinesListPreferenceModel.getIsChecked()) {
                this.currentCheckedPositionForShowCarMode = this.dialogModel.getShowCarModePreferenceList().indexOf(twoLinesListPreferenceModel);
            }
        }
    }

    private final void notifyPropertyChanged(int id) {
        if (id == this.mBinding.getTitleView().getId()) {
            this.mBinding.getTitleView().setText(this.dialogModel.getTitle());
            return;
        }
        if (id == this.mBinding.getShowCarModeFabRecyclerView().getMRecyclerView().getId()) {
            this.mBinding.getShowCarModeFabRecyclerView().getMRecyclerView().setVisibility(0);
            return;
        }
        if (id == this.mBinding.getSaveButton().getId()) {
            this.mBinding.getSaveButton().setText(this.dialogModel.getSaveButtonText());
            this.mBinding.getSaveButton().setVisibility(0);
        } else if (id == this.mBinding.getCancelbutton().getId()) {
            this.mBinding.getCancelbutton().setText(this.dialogModel.getCancelButtonText());
        }
    }

    private final void initRecyclerViewModelForShowCarModeFabPreferences() {
        this.twoLinesPreferenceRecyclerViewmodelForCarMode = new TwoLinesPreferenceRecyclerViewModel(getContext(), this.mBinding.getShowCarModeFabRecyclerView(), this.dialogModel.getShowCarModePreferenceList(), this.viewModelCallbacksForCarMode);
    }

    private final void initViewModelCallbacksForCarModeFAB() {
        this.viewModelCallbacksForCarMode = new ViewModelCallbacks() { // from class: com.amazon.mp3.auto.carmode.CarModePreferencesViewModel.initViewModelCallbacksForCarModeFAB.1
            @Override // com.amazon.mp3.fragment.viewmodel.ViewModelCallbacks
            public void onEventOccurred(int callerId, int actionId, BaseViewModel viewModelInstance) {
                TwoLinesPreferenceRecyclerViewModel twoLinesPreferenceRecyclerViewModel;
                Intrinsics.checkNotNullParameter(viewModelInstance, "viewModelInstance");
                if (actionId != 1 || (twoLinesPreferenceRecyclerViewModel = CarModePreferencesViewModel.this.twoLinesPreferenceRecyclerViewmodelForCarMode) == null) {
                    return;
                }
                CarModePreferencesViewModel.this.setCurrentCheckedPositionForShowCarMode(twoLinesPreferenceRecyclerViewModel.getCurrentCheckedPosition());
            }
        };
    }

    private final void setupSaveButton() {
        final Context context = getContext();
        getMBinding().getSaveButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.CarModePreferencesViewModel$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarModePreferencesViewModel.m259setupSaveButton$lambda2$lambda1(this.f$0, context, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupSaveButton$lambda-2$lambda-1, reason: not valid java name */
    public static final void m259setupSaveButton$lambda2$lambda1(CarModePreferencesViewModel this$0, Context c, View view) {
        ActionType actionType;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(c, "$c");
        String entryValue = this$0.dialogModel.getShowCarModePreferenceList().get(this$0.currentCheckedPositionForShowCarMode).getEntryValue();
        SettingsUtil.setShowCarModeFABPreference(c, entryValue);
        if (Intrinsics.areEqual(entryValue, CarModeShowFABPreferences.ALWAYS.getEntryValue())) {
            actionType = ActionType.SELECT_CAR_MODE_SHOW_ALWAYS;
        } else if (Intrinsics.areEqual(entryValue, CarModeShowFABPreferences.IN_CAR.getEntryValue())) {
            actionType = ActionType.SELECT_CAR_MODE_SHOW_IN_CAR;
        } else {
            actionType = Intrinsics.areEqual(entryValue, CarModeShowFABPreferences.NEVER.getEntryValue()) ? ActionType.SELECT_CAR_MODE_SHOW_NEVER : null;
        }
        if (actionType == null) {
            return;
        }
        AutoUiEventUtil.sendUiClickEvent(actionType, PageType.SETTINGS);
        this$0.sendUiClickMetric$DigitalMusicAndroid3P_automotiveProdRelease("selectSave");
        ViewModelCallbacks viewModelCallbacks = this$0.getViewModelCallbacks();
        if (viewModelCallbacks == null) {
            return;
        }
        viewModelCallbacks.onEventOccurred(1, 1, this$0);
    }

    private final void setupCancelButton() {
        this.mBinding.getCancelbutton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.CarModePreferencesViewModel$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarModePreferencesViewModel.m258setupCancelButton$lambda3(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupCancelButton$lambda-3, reason: not valid java name */
    public static final void m258setupCancelButton$lambda3(CarModePreferencesViewModel this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.sendUiClickMetric$DigitalMusicAndroid3P_automotiveProdRelease("selectCancel");
        ViewModelCallbacks viewModelCallbacks = this$0.getViewModelCallbacks();
        if (viewModelCallbacks == null) {
            return;
        }
        viewModelCallbacks.onEventOccurred(1, 1, this$0);
    }
}
