package com.amazon.mp3.auto.carmode.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
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
import com.amazon.mp3.auto.carmode.CarModeUtility;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.Log;
import com.amazon.music.metrics.mts.event.types.ExperienceMode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import rx.functions.Action1;

/* JADX INFO: compiled from: CarModeDisclaimerFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J$\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u001a\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u00102\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/amazon/mp3/auto/carmode/fragment/CarModeDisclaimerFragment;", "Lcom/amazon/mp3/auto/carmode/fragment/CarModeBaseFragment;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "disclaimerContentTextView", "Landroid/widget/TextView;", "disclaimerTitleTextView", "positiveButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "handleDisclaimerDismiss", "", "context", "Landroid/content/Context;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarModeDisclaimerFragment extends CarModeBaseFragment {
    private final String TAG = CarModeDisclaimerFragment.class.getSimpleName();
    private TextView disclaimerContentTextView;
    private TextView disclaimerTitleTextView;
    private BaseButton positiveButton;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.fragment_car_mode_disclaimer, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…laimer, container, false)");
        setRoot(viewInflate);
        View viewFindViewById = getRoot().findViewById(R.id.car_mode_disclaimer_title);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "root.findViewById(R.id.car_mode_disclaimer_title)");
        this.disclaimerTitleTextView = (TextView) viewFindViewById;
        View viewFindViewById2 = getRoot().findViewById(R.id.car_mode_disclaimer_content);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "root.findViewById(R.id.c…_mode_disclaimer_content)");
        this.disclaimerContentTextView = (TextView) viewFindViewById2;
        View viewFindViewById3 = getRoot().findViewById(R.id.presets_ingress_dismiss_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "root.findViewById(R.id.p…s_ingress_dismiss_button)");
        this.positiveButton = (BaseButton) viewFindViewById3;
        return getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        OnBackPressedDispatcher onBackPressedDispatcher;
        Intrinsics.checkNotNullParameter(view, "view");
        AutoUiEventUtil.sendUiPageViewEvent(PageType.CAR_MODE_DISCLAIMER, ExperienceMode.CAR_MODE);
        FragmentActivity activity = getActivity();
        if (activity != null && (onBackPressedDispatcher = activity.getOnBackPressedDispatcher()) != null) {
            onBackPressedDispatcher.addCallback(getViewLifecycleOwner(), new OnBackPressedCallback() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeDisclaimerFragment.onViewCreated.1
                {
                    super(true);
                }

                @Override // androidx.activity.OnBackPressedCallback
                public void handleOnBackPressed() {
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("carModeUserExit", true);
                    CarModeNavigation navigationListener = CarModeDisclaimerFragment.this.getNavigationListener();
                    if (navigationListener == null) {
                        return;
                    }
                    navigationListener.navigateTo(CarModeAction.EXIT_CAR_MODE, bundle);
                }
            });
        }
        getSubscriptions().add(StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeDisclaimerFragment$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CarModeDisclaimerFragment.m278onViewCreated$lambda2(this.f$0, (StyleSheet) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeDisclaimerFragment$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CarModeDisclaimerFragment.m279onViewCreated$lambda3(this.f$0, (Throwable) obj);
            }
        }));
        Context context = getContext();
        BaseButton baseButton = null;
        if (context != null) {
            TextView textView = this.disclaimerContentTextView;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("disclaimerContentTextView");
                textView = null;
            }
            textView.setText(CarModeUtility.INSTANCE.getDisclaimer(context));
        }
        TextView textView2 = this.disclaimerContentTextView;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("disclaimerContentTextView");
            textView2 = null;
        }
        textView2.setMovementMethod(new ScrollingMovementMethod());
        BaseButton baseButton2 = this.positiveButton;
        if (baseButton2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("positiveButton");
        } else {
            baseButton = baseButton2;
        }
        baseButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeDisclaimerFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CarModeDisclaimerFragment.m280onViewCreated$lambda5(this.f$0, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-2, reason: not valid java name */
    public static final void m278onViewCreated$lambda2(CarModeDisclaimerFragment this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BaseButton.StyleBuilder buttonBuilder = styleSheet.getButtonBuilder(ButtonSizeKey.LARGE, ButtonStyleKey.SOLID);
        TextView textView = null;
        if (buttonBuilder != null) {
            String string = this$0.getString(R.string.dmusic_button_ok);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.dmusic_button_ok)");
            BaseButton.StyleBuilder styleBuilderWithText = buttonBuilder.withText(string);
            if (styleBuilderWithText != null) {
                BaseButton baseButton = this$0.positiveButton;
                if (baseButton == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("positiveButton");
                    baseButton = null;
                }
                styleBuilderWithText.applyStyle(baseButton);
            }
        }
        FontStyle fontStyle = styleSheet.getFontStyle(FontStyleKey.HEADLINE_3);
        if (fontStyle != null) {
            FontUtil fontUtil = FontUtil.INSTANCE;
            TextView textView2 = this$0.disclaimerTitleTextView;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("disclaimerTitleTextView");
                textView2 = null;
            }
            fontUtil.applyFontStyle(textView2, fontStyle);
        }
        FontStyle fontStyle2 = styleSheet.getFontStyle(FontStyleKey.PRIMARY_T1_BIG_EMBER_RG);
        if (fontStyle2 == null) {
            return;
        }
        FontUtil fontUtil2 = FontUtil.INSTANCE;
        TextView textView3 = this$0.disclaimerContentTextView;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("disclaimerContentTextView");
        } else {
            textView = textView3;
        }
        fontUtil2.applyFontStyle(textView, fontStyle2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-3, reason: not valid java name */
    public static final void m279onViewCreated$lambda3(CarModeDisclaimerFragment this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.error(this$0.TAG, "Failed to fetch stylesheet. Some widgets will have no styles", th);
        BaseButton baseButton = this$0.positiveButton;
        if (baseButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("positiveButton");
            baseButton = null;
        }
        String string = this$0.getString(R.string.dmusic_button_ok);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.dmusic_button_ok)");
        baseButton.setText(string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-5, reason: not valid java name */
    public static final void m280onViewCreated$lambda5(CarModeDisclaimerFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Context contextRequireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        this$0.handleDisclaimerDismiss(contextRequireContext);
    }

    private final void handleDisclaimerDismiss(Context context) {
        AutoUiEventUtil.sendUiClickEvent$default(ActionType.CAR_MODE_DISCLAIMER_DISMISS, PageType.CAR_MODE_DISCLAIMER, ExperienceMode.CAR_MODE, null, 8, null);
        CarModeUtility carModeUtility = CarModeUtility.INSTANCE;
        carModeUtility.setDisclaimerPreference(context);
        if (carModeUtility.shouldStartWelcomePage(context)) {
            CarModeNavigation navigationListener = getNavigationListener();
            if (navigationListener == null) {
                return;
            }
            CarModeNavigation.DefaultImpls.navigateTo$default(navigationListener, CarModeAction.SHOW_WELCOME_PAGE, null, 2, null);
            return;
        }
        CarModeNavigation navigationListener2 = getNavigationListener();
        if (navigationListener2 == null) {
            return;
        }
        CarModeNavigation.DefaultImpls.navigateTo$default(navigationListener2, CarModeAction.SHOW_HOME_PAGE, null, 2, null);
    }
}
