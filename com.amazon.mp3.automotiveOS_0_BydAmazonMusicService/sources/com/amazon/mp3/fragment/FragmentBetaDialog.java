package com.amazon.mp3.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.fragment.model.BetaDialogModel;
import com.amazon.mp3.fragment.view.FragmentBetaDialogBinding;
import com.amazon.mp3.fragment.viewmodel.BaseViewModel;
import com.amazon.mp3.fragment.viewmodel.BetaDialogViewModel;
import com.amazon.mp3.fragment.viewmodel.ViewModelCallbacks;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FragmentBetaDialog.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0002J&\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u000bH\u0002R\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/fragment/FragmentBetaDialog;", "Lcom/amazon/mp3/fragment/BaseFragment;", "()V", "mPurpose", "", "Ljava/lang/Integer;", "mViewBinding", "Lcom/amazon/mp3/fragment/view/FragmentBetaDialogBinding;", "mViewModelCallbacks", "Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "initViewModelCallbacks", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "setViewModel", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FragmentBetaDialog extends BaseFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String PURPOSE = "purpose";
    private Integer mPurpose;
    private FragmentBetaDialogBinding mViewBinding = new FragmentBetaDialogBinding();
    private ViewModelCallbacks mViewModelCallbacks;

    /* JADX INFO: compiled from: FragmentBetaDialog.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/fragment/FragmentBetaDialog$Companion;", "", "()V", "PURPOSE", "", "newInstance", "Lcom/amazon/mp3/fragment/FragmentBetaDialog;", "purpose", "Lcom/amazon/mp3/fragment/BetaDialogPurpose;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FragmentBetaDialog newInstance(BetaDialogPurpose purpose) {
            Intrinsics.checkNotNullParameter(purpose, "purpose");
            FragmentBetaDialog fragmentBetaDialog = new FragmentBetaDialog();
            Bundle bundle = new Bundle();
            bundle.putInt(FragmentBetaDialog.PURPOSE, purpose.ordinal());
            fragmentBetaDialog.setArguments(bundle);
            return fragmentBetaDialog;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.fragment_beta_dialog, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…dialog, container, false)");
        this.mViewBinding.setView(viewInflate);
        initViewModelCallbacks();
        Bundle arguments = getArguments();
        if (arguments != null) {
            Integer numValueOf = Integer.valueOf(arguments.getInt(PURPOSE, Integer.MIN_VALUE));
            this.mPurpose = numValueOf;
            if (numValueOf == null || numValueOf.intValue() != Integer.MIN_VALUE) {
                setViewModel();
            }
        }
        return viewInflate;
    }

    private final void initViewModelCallbacks() {
        this.mViewModelCallbacks = new ViewModelCallbacks() { // from class: com.amazon.mp3.fragment.FragmentBetaDialog.initViewModelCallbacks.1
            @Override // com.amazon.mp3.fragment.viewmodel.ViewModelCallbacks
            public void onEventOccurred(int callerId, int actionId, BaseViewModel viewModelInstance) {
                FragmentActivity activity;
                Intrinsics.checkNotNullParameter(viewModelInstance, "viewModelInstance");
                if (actionId != 1 || (activity = FragmentBetaDialog.this.getActivity()) == null) {
                    return;
                }
                activity.finish();
            }
        };
    }

    private final void setViewModel() {
        Context context;
        Integer num = this.mPurpose;
        int iOrdinal = BetaDialogPurpose.HARLEY_DIALOG.ordinal();
        if (num == null || num.intValue() != iOrdinal || (context = getContext()) == null) {
            return;
        }
        String string = context.getResources().getString(R.string.dmusic_beta_dialog_title);
        Intrinsics.checkNotNullExpressionValue(string, "it.resources.getString(R…dmusic_beta_dialog_title)");
        String string2 = context.getResources().getString(R.string.dmusic_beta_dialog_body);
        Intrinsics.checkNotNullExpressionValue(string2, "it.resources.getString(R….dmusic_beta_dialog_body)");
        String string3 = context.getResources().getString(R.string.dmusic_beta_dialog_ok_button);
        Intrinsics.checkNotNullExpressionValue(string3, "it.resources.getString(R…ic_beta_dialog_ok_button)");
        new BetaDialogViewModel(context, this.mViewBinding, new BetaDialogModel(string, string2, string3), this.mViewModelCallbacks);
    }
}
