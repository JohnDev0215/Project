package com.amazon.mp3.auto.carmode.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.amazon.mp3.auto.AutoUiEventUtil;
import com.amazon.mp3.auto.carmode.CarModeAction;
import com.amazon.mp3.auto.carmode.CarModeNavigation;
import com.amazon.mp3.auto.carmode.CarModeUtility;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.Log;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
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
import com.google.android.gms.cast.MediaTrack;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import rx.functions.Action1;

/* JADX INFO: compiled from: CarModeOnBoardingPresetFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0005¢\u0006\u0002\u0010\u0002J$\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u001a\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/amazon/mp3/auto/carmode/fragment/CarModeOnBoardingPresetFragment;", "Lcom/amazon/mp3/auto/carmode/fragment/CarModeBaseFragment;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "closeButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "descriptionTextView", "Landroid/widget/TextView;", "exampleImageView", "Landroid/widget/ImageView;", "titleTextView", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarModeOnBoardingPresetFragment extends CarModeBaseFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String TAG = CarModeOnBoardingPresetFragment.class.getSimpleName();
    private BaseButton closeButton;
    private TextView descriptionTextView;
    private ImageView exampleImageView;
    private TextView titleTextView;

    /* JADX INFO: compiled from: CarModeOnBoardingPresetFragment.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/auto/carmode/fragment/CarModeOnBoardingPresetFragment$Companion;", "", "()V", "DESCRIPTION_KEY", "", "GRADIENT_KEY", "IMAGE_ID_KEY", "TITLE_KEY", "newInstance", "Lcom/amazon/mp3/auto/carmode/fragment/CarModeOnBoardingPresetFragment;", "title", "", MediaTrack.ROLE_DESCRIPTION, ContextMappingConstants.IMAGE, "gradient", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CarModeOnBoardingPresetFragment newInstance(int title, int description, int image, int gradient) {
            CarModeOnBoardingPresetFragment carModeOnBoardingPresetFragment = new CarModeOnBoardingPresetFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("argument_title", title);
            bundle.putInt("argument_description", description);
            bundle.putInt("argument_image_id", image);
            bundle.putInt("argument_gradient_key", gradient);
            carModeOnBoardingPresetFragment.setArguments(bundle);
            return carModeOnBoardingPresetFragment;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.fragment_car_mode_onboarding_preset, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…preset, container, false)");
        setRoot(viewInflate);
        View viewFindViewById = getRoot().findViewById(R.id.example_title);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "root.findViewById(R.id.example_title)");
        this.titleTextView = (TextView) viewFindViewById;
        View viewFindViewById2 = getRoot().findViewById(R.id.example_close_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "root.findViewById(R.id.example_close_button)");
        this.closeButton = (BaseButton) viewFindViewById2;
        View viewFindViewById3 = getRoot().findViewById(R.id.example_description);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "root.findViewById(R.id.example_description)");
        this.descriptionTextView = (TextView) viewFindViewById3;
        View viewFindViewById4 = getRoot().findViewById(R.id.example_image);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "root.findViewById(R.id.example_image)");
        this.exampleImageView = (ImageView) viewFindViewById4;
        return getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        Bundle arguments = getArguments();
        BaseButton baseButton = null;
        if (arguments != null) {
            TextView textView = this.titleTextView;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("titleTextView");
                textView = null;
            }
            Context context = getContext();
            textView.setText(context == null ? null : context.getString(arguments.getInt("argument_title")));
            TextView textView2 = this.descriptionTextView;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("descriptionTextView");
                textView2 = null;
            }
            Context context2 = getContext();
            textView2.setText(context2 == null ? null : context2.getString(arguments.getInt("argument_description")));
            Drawable drawable = ContextCompat.getDrawable(requireContext(), arguments.getInt("argument_image_id"));
            if (drawable != null) {
                ImageView imageView = this.exampleImageView;
                if (imageView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("exampleImageView");
                    imageView = null;
                }
                imageView.setImageDrawable(drawable);
            }
            Drawable drawable2 = ContextCompat.getDrawable(requireContext(), arguments.getInt("argument_gradient_key"));
            if (drawable2 != null) {
                getRoot().setBackground(drawable2);
            }
        }
        getSubscriptions().add(StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeOnBoardingPresetFragment$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CarModeOnBoardingPresetFragment.m295onViewCreated$lambda5(this.f$0, (StyleSheet) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeOnBoardingPresetFragment$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CarModeOnBoardingPresetFragment.m296onViewCreated$lambda6(this.f$0, (Throwable) obj);
            }
        }));
        Drawable drawable3 = ContextCompat.getDrawable(requireContext(), R.drawable.btn_close);
        BaseButton baseButton2 = this.closeButton;
        if (baseButton2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("closeButton");
            baseButton2 = null;
        }
        baseButton2.setIcon(drawable3);
        BaseButton baseButton3 = this.closeButton;
        if (baseButton3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("closeButton");
        } else {
            baseButton = baseButton3;
        }
        baseButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeOnBoardingPresetFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CarModeOnBoardingPresetFragment.m297onViewCreated$lambda9(this.f$0, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-5, reason: not valid java name */
    public static final void m295onViewCreated$lambda5(CarModeOnBoardingPresetFragment this$0, StyleSheet styleSheet) {
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
        if (fontStyle2 == null) {
            return;
        }
        FontUtil fontUtil2 = FontUtil.INSTANCE;
        TextView textView3 = this$0.descriptionTextView;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("descriptionTextView");
        } else {
            textView = textView3;
        }
        fontUtil2.applyFontStyle(textView, fontStyle2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-6, reason: not valid java name */
    public static final void m296onViewCreated$lambda6(CarModeOnBoardingPresetFragment this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.error(this$0.TAG, "Failed to fetch stylesheet. Some widgets will have no styles", th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-9, reason: not valid java name */
    public static final void m297onViewCreated$lambda9(CarModeOnBoardingPresetFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AutoUiEventUtil.sendUiClickEvent$default(ActionType.CAR_MODE_ONBOARDING_CLOSE, PageType.CAR_MODE_ONBOARDING, ExperienceMode.CAR_MODE, null, 8, null);
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
}
