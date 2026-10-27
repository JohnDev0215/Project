package com.amazon.mp3.auto.carmode.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
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
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import rx.functions.Action1;

/* JADX INFO: compiled from: CarModeOnBoardingAlexaFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J$\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u001a\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/auto/carmode/fragment/CarModeOnBoardingAlexaFragment;", "Lcom/amazon/mp3/auto/carmode/fragment/CarModeBaseFragment;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "alexaDescription", "Landroid/widget/TextView;", "alexaHintsHeaderTextView", "alexaHintsTextView", "closeButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "titleTextView", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarModeOnBoardingAlexaFragment extends CarModeBaseFragment {
    private final String TAG = CarModeOnBoardingAlexaFragment.class.getSimpleName();
    private TextView alexaDescription;
    private TextView alexaHintsHeaderTextView;
    private TextView alexaHintsTextView;
    private BaseButton closeButton;
    private TextView titleTextView;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.fragment_car_mode_onboarding_alexa, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…_alexa, container, false)");
        setRoot(viewInflate);
        View viewFindViewById = getRoot().findViewById(R.id.example_close_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "root.findViewById(R.id.example_close_button)");
        this.closeButton = (BaseButton) viewFindViewById;
        View viewFindViewById2 = getRoot().findViewById(R.id.example_title);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "root.findViewById(R.id.example_title)");
        this.titleTextView = (TextView) viewFindViewById2;
        View viewFindViewById3 = getRoot().findViewById(R.id.example_description);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "root.findViewById(R.id.example_description)");
        this.alexaDescription = (TextView) viewFindViewById3;
        View viewFindViewById4 = getRoot().findViewById(R.id.alexa_example_hints_header);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "root.findViewById(R.id.alexa_example_hints_header)");
        this.alexaHintsHeaderTextView = (TextView) viewFindViewById4;
        View viewFindViewById5 = getRoot().findViewById(R.id.alexa_example_hints);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "root.findViewById(R.id.alexa_example_hints)");
        this.alexaHintsTextView = (TextView) viewFindViewById5;
        return getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        getSubscriptions().add(StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeOnBoardingAlexaFragment$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CarModeOnBoardingAlexaFragment.m289onViewCreated$lambda4(this.f$0, (StyleSheet) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeOnBoardingAlexaFragment$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CarModeOnBoardingAlexaFragment.m290onViewCreated$lambda5(this.f$0, (Throwable) obj);
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
        BaseButton baseButton3 = this.closeButton;
        if (baseButton3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("closeButton");
        } else {
            baseButton2 = baseButton3;
        }
        baseButton2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeOnBoardingAlexaFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CarModeOnBoardingAlexaFragment.m291onViewCreated$lambda7(this.f$0, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-4, reason: not valid java name */
    public static final void m289onViewCreated$lambda4(CarModeOnBoardingAlexaFragment this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BaseButton.StyleBuilder iconBuilder = styleSheet.getIconBuilder(IconSizeKey.MEDIUM, IconStyleKey.BASE_GLASS);
        TextView textView = null;
        if (iconBuilder != null) {
            BaseButton baseButton = this$0.closeButton;
            if (baseButton == null) {
                Intrinsics.throwUninitializedPropertyAccessException("closeButton");
                baseButton = null;
            }
            iconBuilder.applyStyle(baseButton);
        }
        FontStyle fontStyle = styleSheet.getFontStyle(FontStyleKey.BASE_HEADLINE_2);
        if (fontStyle != null) {
            FontUtil fontUtil = FontUtil.INSTANCE;
            TextView textView2 = this$0.titleTextView;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("titleTextView");
                textView2 = null;
            }
            fontUtil.applyFontStyle(textView2, fontStyle);
        }
        FontStyle fontStyle2 = styleSheet.getFontStyle(FontStyleKey.BASE_T2_BIG_EMBER_RG);
        if (fontStyle2 != null) {
            FontUtil fontUtil2 = FontUtil.INSTANCE;
            TextView textView3 = this$0.alexaDescription;
            if (textView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("alexaDescription");
                textView3 = null;
            }
            fontUtil2.applyFontStyle(textView3, fontStyle2);
        }
        FontStyle fontStyle3 = styleSheet.getFontStyle(FontStyleKey.BASE_T2_BIG_EMBER_BD);
        if (fontStyle3 != null) {
            FontUtil fontUtil3 = FontUtil.INSTANCE;
            TextView textView4 = this$0.alexaHintsHeaderTextView;
            if (textView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("alexaHintsHeaderTextView");
                textView4 = null;
            }
            fontUtil3.applyFontStyle(textView4, fontStyle3);
        }
        FontStyle fontStyle4 = styleSheet.getFontStyle(FontStyleKey.BASE_HEADLINE_4_TALL_EMBER_RG_IT);
        if (fontStyle4 == null) {
            return;
        }
        FontUtil fontUtil4 = FontUtil.INSTANCE;
        TextView textView5 = this$0.alexaHintsTextView;
        if (textView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("alexaHintsTextView");
        } else {
            textView = textView5;
        }
        fontUtil4.applyFontStyle(textView, fontStyle4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-5, reason: not valid java name */
    public static final void m290onViewCreated$lambda5(CarModeOnBoardingAlexaFragment this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.error(this$0.TAG, "Failed to fetch stylesheet. Some widgets will have no styles", th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-7, reason: not valid java name */
    public static final void m291onViewCreated$lambda7(CarModeOnBoardingAlexaFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AutoUiEventUtil.sendUiClickEvent$default(ActionType.CAR_MODE_ONBOARDING_COMPLETE, PageType.CAR_MODE_ONBOARDING, ExperienceMode.CAR_MODE, null, 8, null);
        CarModeUtility carModeUtility = CarModeUtility.INSTANCE;
        Context contextRequireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        carModeUtility.setOnBoardingPreference(contextRequireContext, false);
        Bundle bundle = new Bundle();
        bundle.putBoolean("carModeLaunch", true);
        CarModeNavigation navigationListener = this$0.getNavigationListener();
        if (navigationListener == null) {
            return;
        }
        navigationListener.navigateTo(CarModeAction.SHOW_HOME_PAGE, bundle);
    }
}
