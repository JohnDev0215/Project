package com.amazon.mp3.language.preference.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.fragment.BaseFragment;
import com.amazon.mp3.fragment.viewmodel.BaseViewModel;
import com.amazon.mp3.fragment.viewmodel.ViewModelCallbacks;
import com.amazon.mp3.language.preference.fragment.view.AppDisplayLanguagePreferenceBinding;
import com.amazon.mp3.language.preference.fragment.viewmodel.AppDisplayLanguageViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FragmentAppDisplayLanguagePreferences.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0002J&\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010\u0011\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/language/preference/fragment/FragmentAppDisplayLanguagePreferences;", "Lcom/amazon/mp3/fragment/BaseFragment;", "()V", "mViewBinding", "Lcom/amazon/mp3/language/preference/fragment/view/AppDisplayLanguagePreferenceBinding;", "mViewModelCallbacks", "Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "initViewModelCallbacks", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "setViewModel", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FragmentAppDisplayLanguagePreferences extends BaseFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private AppDisplayLanguagePreferenceBinding mViewBinding = new AppDisplayLanguagePreferenceBinding();
    private ViewModelCallbacks mViewModelCallbacks;

    /* JADX INFO: compiled from: FragmentAppDisplayLanguagePreferences.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/mp3/language/preference/fragment/FragmentAppDisplayLanguagePreferences$Companion;", "", "()V", "newInstance", "Lcom/amazon/mp3/language/preference/fragment/FragmentAppDisplayLanguagePreferences;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FragmentAppDisplayLanguagePreferences newInstance() {
            return new FragmentAppDisplayLanguagePreferences();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.fragment_display_language_setting_preferences, container, false);
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
        new AppDisplayLanguageViewModel(context, this.mViewBinding, this.mViewModelCallbacks);
    }

    private final void initViewModelCallbacks() {
        this.mViewModelCallbacks = new ViewModelCallbacks() { // from class: com.amazon.mp3.language.preference.fragment.FragmentAppDisplayLanguagePreferences.initViewModelCallbacks.1
            @Override // com.amazon.mp3.fragment.viewmodel.ViewModelCallbacks
            public void onEventOccurred(int callerId, int actionId, BaseViewModel viewModelInstance) {
                FragmentActivity activity;
                Intrinsics.checkNotNullParameter(viewModelInstance, "viewModelInstance");
                if (actionId != 1 || (activity = FragmentAppDisplayLanguagePreferences.this.getActivity()) == null) {
                    return;
                }
                activity.finish();
            }
        };
    }
}
