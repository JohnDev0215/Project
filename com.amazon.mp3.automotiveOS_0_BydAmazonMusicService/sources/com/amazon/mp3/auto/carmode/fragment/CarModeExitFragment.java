package com.amazon.mp3.auto.carmode.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.auto.AutoUiEventUtil;
import com.amazon.mp3.auto.carmode.CarModeAction;
import com.amazon.mp3.auto.carmode.CarModeNavigation;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.Log;
import com.amazon.music.metrics.mts.event.types.ExperienceMode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import rx.functions.Action1;

/* JADX INFO: compiled from: CarModeExitFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J$\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u001a\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u0015\u001a\u00020\nH\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/amazon/mp3/auto/carmode/fragment/CarModeExitFragment;", "Lcom/amazon/mp3/auto/carmode/fragment/CarModeBaseFragment;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "carModeAcceptButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "carModeCancelButton", "handleExitClick", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "setupButtonClickListeners", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarModeExitFragment extends CarModeBaseFragment {
    private final String TAG = CarModeExitFragment.class.getSimpleName();
    private BaseButton carModeAcceptButton;
    private BaseButton carModeCancelButton;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int i;
        TextView textView;
        OnBackPressedDispatcher onBackPressedDispatcher;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.car_mode_exit_fragment, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…agment, container, false)");
        setRoot(viewInflate);
        FragmentActivity activity = getActivity();
        if (activity != null && (onBackPressedDispatcher = activity.getOnBackPressedDispatcher()) != null) {
            onBackPressedDispatcher.addCallback(getViewLifecycleOwner(), new OnBackPressedCallback() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeExitFragment.onCreateView.1
                {
                    super(true);
                }

                @Override // androidx.activity.OnBackPressedCallback
                public void handleOnBackPressed() {
                    CarModeExitFragment.this.handleExitClick();
                }
            });
        }
        Bundle arguments = getArguments();
        if (arguments != null && (i = arguments.getInt("EXIT_STRING_ID_KEY", -1)) != -1 && (textView = (TextView) getRoot().findViewById(R.id.preset_exit_title)) != null) {
            textView.setText(i);
        }
        View viewFindViewById = getRoot().findViewById(R.id.presets_exit_cancel_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "root.findViewById(R.id.presets_exit_cancel_button)");
        this.carModeCancelButton = (BaseButton) viewFindViewById;
        View viewFindViewById2 = getRoot().findViewById(R.id.presets_exit_accept_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "root.findViewById(R.id.presets_exit_accept_button)");
        this.carModeAcceptButton = (BaseButton) viewFindViewById2;
        return getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        AutoUiEventUtil.sendUiPageViewEvent(PageType.CAR_MODE_EXIT, ExperienceMode.CAR_MODE);
        getSubscriptions().add(StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeExitFragment$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CarModeExitFragment.m283onViewCreated$lambda1(this.f$0, (StyleSheet) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeExitFragment$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CarModeExitFragment.m284onViewCreated$lambda2(this.f$0, (Throwable) obj);
            }
        }));
        setupButtonClickListeners();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-1, reason: not valid java name */
    public static final void m283onViewCreated$lambda1(CarModeExitFragment this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ButtonSizeKey buttonSizeKey = ButtonSizeKey.LARGE;
        BaseButton.StyleBuilder buttonBuilder = styleSheet.getButtonBuilder(buttonSizeKey, ButtonStyleKey.GLASS);
        BaseButton baseButton = null;
        if (buttonBuilder != null) {
            String string = this$0.getString(R.string.dmusic_button_cancel);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.dmusic_button_cancel)");
            BaseButton.StyleBuilder styleBuilderWithText = buttonBuilder.withText(string);
            if (styleBuilderWithText != null) {
                BaseButton baseButton2 = this$0.carModeCancelButton;
                if (baseButton2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("carModeCancelButton");
                    baseButton2 = null;
                }
                styleBuilderWithText.applyStyle(baseButton2);
            }
        }
        BaseButton.StyleBuilder buttonBuilder2 = styleSheet.getButtonBuilder(buttonSizeKey, ButtonStyleKey.OUTLINE);
        if (buttonBuilder2 == null) {
            return;
        }
        String string2 = this$0.getString(R.string.dmusic_car_mode_first_time_exit);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.dmusi…car_mode_first_time_exit)");
        BaseButton.StyleBuilder styleBuilderWithText2 = buttonBuilder2.withText(string2);
        if (styleBuilderWithText2 == null) {
            return;
        }
        BaseButton baseButton3 = this$0.carModeAcceptButton;
        if (baseButton3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("carModeAcceptButton");
        } else {
            baseButton = baseButton3;
        }
        styleBuilderWithText2.applyStyle(baseButton);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-2, reason: not valid java name */
    public static final void m284onViewCreated$lambda2(CarModeExitFragment this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.error(this$0.TAG, "Failed to fetch stylesheet. Some widgets will have no styles", th);
        BaseButton baseButton = this$0.carModeCancelButton;
        BaseButton baseButton2 = null;
        if (baseButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("carModeCancelButton");
            baseButton = null;
        }
        String string = this$0.getString(R.string.dmusic_button_cancel);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.dmusic_button_cancel)");
        baseButton.setText(string);
        BaseButton baseButton3 = this$0.carModeAcceptButton;
        if (baseButton3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("carModeAcceptButton");
        } else {
            baseButton2 = baseButton3;
        }
        String string2 = this$0.getString(R.string.dmusic_car_mode_first_time_exit);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.dmusi…car_mode_first_time_exit)");
        baseButton2.setText(string2);
    }

    private final void setupButtonClickListeners() {
        BaseButton baseButton = this.carModeCancelButton;
        BaseButton baseButton2 = null;
        if (baseButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("carModeCancelButton");
            baseButton = null;
        }
        baseButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeExitFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarModeExitFragment.m285setupButtonClickListeners$lambda3(this.f$0, view);
            }
        });
        BaseButton baseButton3 = this.carModeAcceptButton;
        if (baseButton3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("carModeAcceptButton");
        } else {
            baseButton2 = baseButton3;
        }
        baseButton2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeExitFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarModeExitFragment.m286setupButtonClickListeners$lambda4(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupButtonClickListeners$lambda-3, reason: not valid java name */
    public static final void m285setupButtonClickListeners$lambda3(CarModeExitFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AutoUiEventUtil.sendUiClickEvent$default(ActionType.CAR_MODE_EXIT_CANCEL, PageType.CAR_MODE_EXIT, ExperienceMode.CAR_MODE, null, 8, null);
        if (this$0.getParentFragmentManager().getBackStackEntryCount() == 0) {
            FragmentActivity activity = this$0.getActivity();
            if (activity == null) {
                return;
            }
            activity.onBackPressed();
            return;
        }
        this$0.getParentFragmentManager().popBackStack();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupButtonClickListeners$lambda-4, reason: not valid java name */
    public static final void m286setupButtonClickListeners$lambda4(CarModeExitFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AutoUiEventUtil.sendUiClickEvent$default(ActionType.CAR_MODE_EXIT_CONFIRM, PageType.CAR_MODE_EXIT, ExperienceMode.CAR_MODE, null, 8, null);
        this$0.handleExitClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleExitClick() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("carModeUserExit", true);
        CarModeNavigation navigationListener = getNavigationListener();
        if (navigationListener == null) {
            return;
        }
        navigationListener.navigateTo(CarModeAction.EXIT_CAR_MODE, bundle);
    }
}
