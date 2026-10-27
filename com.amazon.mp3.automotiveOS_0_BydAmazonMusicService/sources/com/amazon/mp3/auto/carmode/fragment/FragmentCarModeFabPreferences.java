package com.amazon.mp3.auto.carmode.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.activity.CarModeFabPreferenceModel;
import com.amazon.mp3.activity.TwoLinesListPreferenceModel;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.auto.carmode.CarModePreferencesBinding;
import com.amazon.mp3.auto.carmode.CarModePreferencesViewModel;
import com.amazon.mp3.auto.carmode.CarModeShowFABPreferences;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.fragment.BaseFragment;
import com.amazon.mp3.fragment.viewmodel.BaseViewModel;
import com.amazon.mp3.fragment.viewmodel.ViewModelCallbacks;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FragmentCarModeFabPreferences.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002J\b\u0010\n\u001a\u00020\u000bH\u0002J&\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u000bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/auto/carmode/fragment/FragmentCarModeFabPreferences;", "Lcom/amazon/mp3/fragment/BaseFragment;", "()V", "mViewBinding", "Lcom/amazon/mp3/auto/carmode/CarModePreferencesBinding;", "mViewModelCallbacks", "Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "createCarModeShowFABPreferencesList", "", "Lcom/amazon/mp3/activity/TwoLinesListPreferenceModel;", "initViewModelCallbacks", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "setViewModel", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FragmentCarModeFabPreferences extends BaseFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private CarModePreferencesBinding mViewBinding = new CarModePreferencesBinding();
    private ViewModelCallbacks mViewModelCallbacks;

    /* JADX INFO: compiled from: FragmentCarModeFabPreferences.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/mp3/auto/carmode/fragment/FragmentCarModeFabPreferences$Companion;", "", "()V", "newInstance", "Lcom/amazon/mp3/auto/carmode/fragment/FragmentCarModeFabPreferences;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FragmentCarModeFabPreferences newInstance() {
            return new FragmentCarModeFabPreferences();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.fragment_car_mode_fab_settings_preferences, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…rences, container, false)");
        this.mViewBinding.setView(viewInflate);
        initViewModelCallbacks();
        setViewModel();
        return viewInflate;
    }

    private final void setViewModel() {
        Context context = getContext();
        if (context == null) {
            return;
        }
        List<TwoLinesListPreferenceModel> listCreateCarModeShowFABPreferencesList = createCarModeShowFABPreferencesList();
        String string = context.getString(R.string.dmusic_settings_car_mode_ingress_fab_title);
        Intrinsics.checkNotNullExpressionValue(string, "it.getString(dmusic_sett…r_mode_ingress_fab_title)");
        String string2 = context.getString(R.string.dmusic_button_save);
        Intrinsics.checkNotNullExpressionValue(string2, "it.getString(dmusic_button_save)");
        String string3 = context.getString(R.string.dmusic_button_cancel);
        Intrinsics.checkNotNullExpressionValue(string3, "it.getString(dmusic_button_cancel)");
        new CarModePreferencesViewModel(context, this.mViewBinding, new CarModeFabPreferenceModel(string, listCreateCarModeShowFABPreferencesList, null, string2, string3), this.mViewModelCallbacks);
    }

    private final List<TwoLinesListPreferenceModel> createCarModeShowFABPreferencesList() {
        ArrayList arrayList = new ArrayList();
        Context context = getContext();
        if (context != null) {
            String showCarModeFABPreference = SettingsUtil.getShowCarModeFABPreference(context);
            CarModeShowFABPreferences[] carModeShowFABPreferencesArrValues = CarModeShowFABPreferences.values();
            int i = 0;
            int length = carModeShowFABPreferencesArrValues.length;
            while (i < length) {
                CarModeShowFABPreferences carModeShowFABPreferences = carModeShowFABPreferencesArrValues[i];
                i++;
                String string = carModeShowFABPreferences.getEntryResourceDescription() == -1 ? null : context.getString(carModeShowFABPreferences.getEntryResourceDescription());
                String string2 = context.getString(carModeShowFABPreferences.getEntryResourceId());
                Intrinsics.checkNotNullExpressionValue(string2, "it.getString(item.getEntryResourceId())");
                arrayList.add(new TwoLinesListPreferenceModel(string2, carModeShowFABPreferences.getEntryValue(), string, Intrinsics.areEqual(carModeShowFABPreferences.getEntryValue(), showCarModeFABPreference)));
            }
        }
        return arrayList;
    }

    private final void initViewModelCallbacks() {
        this.mViewModelCallbacks = new ViewModelCallbacks() { // from class: com.amazon.mp3.auto.carmode.fragment.FragmentCarModeFabPreferences.initViewModelCallbacks.1
            @Override // com.amazon.mp3.fragment.viewmodel.ViewModelCallbacks
            public void onEventOccurred(int callerId, int actionId, BaseViewModel viewModelInstance) {
                FragmentActivity activity;
                Intrinsics.checkNotNullParameter(viewModelInstance, "viewModelInstance");
                if (actionId != 1 || (activity = FragmentCarModeFabPreferences.this.getActivity()) == null) {
                    return;
                }
                activity.finish();
            }
        };
    }
}
