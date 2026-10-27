package com.amazon.mp3.auto.carmode.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.auto.AutoUiEventUtil;
import com.amazon.mp3.auto.carmode.CarModeAction;
import com.amazon.mp3.auto.carmode.CarModeNavigation;
import com.amazon.mp3.auto.carmode.CarModeUtility;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.language.preference.locale.AppLocales;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.Log;
import com.amazon.music.metrics.mts.event.types.ExperienceMode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.views.BaseButton;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import rx.functions.Action1;

/* JADX INFO: compiled from: CarModeWelcomeFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\u0004H\u0002J$\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u001a\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00102\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010\u001a\u001a\u00020\u0018H\u0002J\b\u0010\u001b\u001a\u00020\u0018H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/amazon/mp3/auto/carmode/fragment/CarModeWelcomeFragment;", "Lcom/amazon/mp3/auto/carmode/fragment/CarModeBaseFragment;", "()V", "INDIA", "", "TAG", "kotlin.jvm.PlatformType", "carModeIntroductionTextView", "Landroid/widget/TextView;", "closeButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "dismissButton", "onBoardingStartButton", "titleTextView", "getLocale", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "registerButtonClickListener", "setTextSize", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarModeWelcomeFragment extends CarModeBaseFragment {
    private TextView carModeIntroductionTextView;
    private BaseButton closeButton;
    private BaseButton dismissButton;
    private BaseButton onBoardingStartButton;
    private TextView titleTextView;
    private final String TAG = CarModeWelcomeFragment.class.getSimpleName();
    private final String INDIA = "IN";

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.fragment_car_mode_welcome, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…elcome, container, false)");
        setRoot(viewInflate);
        View viewFindViewById = getRoot().findViewById(R.id.close_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "root.findViewById(R.id.close_button)");
        this.closeButton = (BaseButton) viewFindViewById;
        View viewFindViewById2 = getRoot().findViewById(R.id.car_mode_introduction);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "root.findViewById(R.id.car_mode_introduction)");
        this.carModeIntroductionTextView = (TextView) viewFindViewById2;
        View viewFindViewById3 = getRoot().findViewById(R.id.onboarding_start_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "root.findViewById(R.id.onboarding_start_button)");
        this.onBoardingStartButton = (BaseButton) viewFindViewById3;
        View viewFindViewById4 = getRoot().findViewById(R.id.welcome_dismiss_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "root.findViewById(R.id.welcome_dismiss_button)");
        this.dismissButton = (BaseButton) viewFindViewById4;
        View viewFindViewById5 = getRoot().findViewById(R.id.car_mode_welcome_title);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "root.findViewById(R.id.car_mode_welcome_title)");
        this.titleTextView = (TextView) viewFindViewById5;
        setTextSize();
        return getRoot();
    }

    private final void setTextSize() {
        String locale = getLocale();
        TextView textView = null;
        if (!StringsKt__StringsKt.contains$default((CharSequence) locale, (CharSequence) this.INDIA, false, 2, (Object) null) || Intrinsics.areEqual(locale, AppLocales.EN_IN_LOCALE.toString())) {
            return;
        }
        TextView textView2 = this.carModeIntroductionTextView;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("carModeIntroductionTextView");
            textView2 = null;
        }
        textView2.setTextSize(0, getResources().getDimension(R.dimen.in_local_car_mode_welcome_description));
        TextView textView3 = this.titleTextView;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("titleTextView");
        } else {
            textView = textView3;
        }
        textView.setTextSize(0, getResources().getDimension(R.dimen.in_local_car_mode_welcome_title));
    }

    private final String getLocale() {
        String string = Locale.getDefault().toString();
        Intrinsics.checkNotNullExpressionValue(string, "locale.toString()");
        return string;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        OnBackPressedDispatcher onBackPressedDispatcher;
        Intrinsics.checkNotNullParameter(view, "view");
        AutoUiEventUtil.sendUiPageViewEvent(PageType.CAR_MODE_ONBOARDING, ExperienceMode.CAR_MODE);
        FragmentActivity activity = getActivity();
        if (activity != null && (onBackPressedDispatcher = activity.getOnBackPressedDispatcher()) != null) {
            onBackPressedDispatcher.addCallback(getViewLifecycleOwner(), new OnBackPressedCallback() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeWelcomeFragment.onViewCreated.1
                {
                    super(true);
                }

                @Override // androidx.activity.OnBackPressedCallback
                public void handleOnBackPressed() {
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("carModeUserExit", true);
                    CarModeNavigation navigationListener = CarModeWelcomeFragment.this.getNavigationListener();
                    if (navigationListener == null) {
                        return;
                    }
                    navigationListener.navigateTo(CarModeAction.EXIT_CAR_MODE, bundle);
                }
            });
        }
        getSubscriptions().add(StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeWelcomeFragment$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CarModeWelcomeFragment.m319onViewCreated$lambda2(this.f$0, (StyleSheet) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeWelcomeFragment$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CarModeWelcomeFragment.m320onViewCreated$lambda3(this.f$0, (Throwable) obj);
            }
        }));
        Drawable drawable = ContextCompat.getDrawable(requireContext(), R.drawable.btn_close);
        BaseButton baseButton = this.closeButton;
        BaseButton baseButton2 = null;
        if (baseButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("closeButton");
            baseButton = null;
        }
        baseButton.setIcon(drawable);
        BaseButton baseButton3 = this.onBoardingStartButton;
        if (baseButton3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onBoardingStartButton");
            baseButton3 = null;
        }
        String string = getString(R.string.dmusic_car_mode_welcome_onboarding_start_button);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.dmusi…_onboarding_start_button)");
        baseButton3.setText(string);
        BaseButton baseButton4 = this.dismissButton;
        if (baseButton4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dismissButton");
        } else {
            baseButton2 = baseButton4;
        }
        String string2 = getString(R.string.dmusic_car_mode_welcome_remind_later_button);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.dmusi…come_remind_later_button)");
        baseButton2.setText(string2);
        registerButtonClickListener();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-2, reason: not valid java name */
    public static final void m319onViewCreated$lambda2(CarModeWelcomeFragment this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FontStyle fontStyle = styleSheet.getFontStyle(FontStyleKey.BASE_HEADLINE_2);
        BaseButton baseButton = null;
        if (fontStyle != null) {
            FontUtil fontUtil = FontUtil.INSTANCE;
            TextView textView = this$0.titleTextView;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("titleTextView");
                textView = null;
            }
            fontUtil.applyFontStyle(textView, fontStyle);
        }
        FontStyle fontStyle2 = styleSheet.getFontStyle(FontStyleKey.BASE_T2_BIG_EMBER_RG);
        if (fontStyle2 != null) {
            FontUtil fontUtil2 = FontUtil.INSTANCE;
            TextView textView2 = this$0.carModeIntroductionTextView;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("carModeIntroductionTextView");
                textView2 = null;
            }
            fontUtil2.applyFontStyle(textView2, fontStyle2);
        }
        BaseButton.StyleBuilder iconBuilder = styleSheet.getIconBuilder(IconSizeKey.MEDIUM, IconStyleKey.BASE_GLASS);
        if (iconBuilder != null) {
            BaseButton baseButton2 = this$0.closeButton;
            if (baseButton2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("closeButton");
                baseButton2 = null;
            }
            iconBuilder.applyStyle(baseButton2);
        }
        ButtonSizeKey buttonSizeKey = ButtonSizeKey.EPIC;
        BaseButton.StyleBuilder buttonBuilder = styleSheet.getButtonBuilder(buttonSizeKey, ButtonStyleKey.SOLID);
        if (buttonBuilder != null) {
            BaseButton baseButton3 = this$0.onBoardingStartButton;
            if (baseButton3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("onBoardingStartButton");
                baseButton3 = null;
            }
            buttonBuilder.applyStyle(baseButton3);
        }
        BaseButton.StyleBuilder buttonBuilder2 = styleSheet.getButtonBuilder(buttonSizeKey, ButtonStyleKey.BASE_GLASS);
        if (buttonBuilder2 == null) {
            return;
        }
        BaseButton baseButton4 = this$0.dismissButton;
        if (baseButton4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dismissButton");
        } else {
            baseButton = baseButton4;
        }
        buttonBuilder2.applyStyle(baseButton);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-3, reason: not valid java name */
    public static final void m320onViewCreated$lambda3(CarModeWelcomeFragment this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.error(this$0.TAG, "Failed to fetch stylesheet. Some widgets will have no styles", th);
    }

    private final void registerButtonClickListener() {
        BaseButton baseButton = this.closeButton;
        BaseButton baseButton2 = null;
        if (baseButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("closeButton");
            baseButton = null;
        }
        baseButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeWelcomeFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarModeWelcomeFragment.m322registerButtonClickListener$lambda6(this.f$0, view);
            }
        });
        BaseButton baseButton3 = this.dismissButton;
        if (baseButton3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dismissButton");
            baseButton3 = null;
        }
        baseButton3.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeWelcomeFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarModeWelcomeFragment.m323registerButtonClickListener$lambda8(this.f$0, view);
            }
        });
        BaseButton baseButton4 = this.onBoardingStartButton;
        if (baseButton4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onBoardingStartButton");
        } else {
            baseButton2 = baseButton4;
        }
        baseButton2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeWelcomeFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarModeWelcomeFragment.m321registerButtonClickListener$lambda10(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: registerButtonClickListener$lambda-6, reason: not valid java name */
    public static final void m322registerButtonClickListener$lambda6(CarModeWelcomeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AutoUiEventUtil.sendUiClickEvent$default(ActionType.CAR_MODE_WELCOME_CLOSE, PageType.CAR_MODE_ONBOARDING, ExperienceMode.CAR_MODE, null, 8, null);
        Context context = this$0.getContext();
        if (context == null) {
            return;
        }
        CarModeUtility.INSTANCE.setOnBoardingPreference(context, false);
        Bundle bundle = new Bundle();
        bundle.putBoolean("carModeLaunch", true);
        CarModeNavigation navigationListener = this$0.getNavigationListener();
        if (navigationListener == null) {
            return;
        }
        navigationListener.navigateTo(CarModeAction.SHOW_HOME_PAGE, bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: registerButtonClickListener$lambda-8, reason: not valid java name */
    public static final void m323registerButtonClickListener$lambda8(CarModeWelcomeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AutoUiEventUtil.sendUiClickEvent$default(ActionType.CAR_MODE_ONBOARDING_DISMISS, PageType.CAR_MODE_ONBOARDING, ExperienceMode.CAR_MODE, null, 8, null);
        CarModeUtility.INSTANCE.setUserDismissedOnBoardingStatus(true);
        Bundle bundle = new Bundle();
        bundle.putBoolean("carModeLaunch", true);
        CarModeNavigation navigationListener = this$0.getNavigationListener();
        if (navigationListener == null) {
            return;
        }
        navigationListener.navigateTo(CarModeAction.SHOW_HOME_PAGE, bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: registerButtonClickListener$lambda-10, reason: not valid java name */
    public static final void m321registerButtonClickListener$lambda10(CarModeWelcomeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getContext() == null) {
            return;
        }
        AutoUiEventUtil.sendUiClickEvent$default(ActionType.CAR_MODE_ONBOARDING_START, PageType.CAR_MODE_ONBOARDING, ExperienceMode.CAR_MODE, null, 8, null);
        CarModeNavigation navigationListener = this$0.getNavigationListener();
        if (navigationListener == null) {
            return;
        }
        CarModeNavigation.DefaultImpls.navigateTo$default(navigationListener, CarModeAction.SHOW_ON_BOARDING_PAGE, null, 2, null);
    }
}
