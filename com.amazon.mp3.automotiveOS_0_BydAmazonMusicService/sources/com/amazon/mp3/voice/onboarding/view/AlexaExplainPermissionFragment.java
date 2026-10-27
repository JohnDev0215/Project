package com.amazon.mp3.voice.onboarding.view;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.voice.onboarding.AlexaOnboardingUIUtil;
import com.amazon.mp3.voice.onboarding.AlexaOnboardingViewModel;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: AlexaExplainPermissionFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u001a\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00142\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001e"}, d2 = {"Lcom/amazon/mp3/voice/onboarding/view/AlexaExplainPermissionFragment;", "Landroidx/fragment/app/Fragment;", "()V", "message", "Landroid/widget/TextView;", "negativeButton", "Lcom/amazon/mp3/voice/onboarding/view/OnboardingNegativeButton;", "onboardingImageView", "Landroid/widget/ImageView;", "positiveButton", "Lcom/amazon/mp3/voice/onboarding/view/OnboardingPositiveButton;", "settingsMessage", "titleTextView", "viewModel", "Lcom/amazon/mp3/voice/onboarding/AlexaOnboardingViewModel;", "getViewModel", "()Lcom/amazon/mp3/voice/onboarding/AlexaOnboardingViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AlexaExplainPermissionFragment extends Fragment {
    private TextView message;
    private OnboardingNegativeButton negativeButton;
    private ImageView onboardingImageView;
    private OnboardingPositiveButton positiveButton;
    private TextView settingsMessage;
    private TextView titleTextView;

    /* JADX INFO: renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(AlexaOnboardingViewModel.class), new Function0<ViewModelStore>() { // from class: com.amazon.mp3.voice.onboarding.view.AlexaExplainPermissionFragment$special$$inlined$activityViewModels$default$1
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ViewModelStore invoke() {
            FragmentActivity fragmentActivityRequireActivity = this.requireActivity();
            Intrinsics.checkExpressionValueIsNotNull(fragmentActivityRequireActivity, "requireActivity()");
            ViewModelStore viewModelStore = fragmentActivityRequireActivity.getViewModelStore();
            Intrinsics.checkExpressionValueIsNotNull(viewModelStore, "requireActivity().viewModelStore");
            return viewModelStore;
        }
    }, new Function0<ViewModelProvider.Factory>() { // from class: com.amazon.mp3.voice.onboarding.view.AlexaExplainPermissionFragment$special$$inlined$activityViewModels$default$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ViewModelProvider.Factory invoke() {
            FragmentActivity fragmentActivityRequireActivity = this.requireActivity();
            Intrinsics.checkExpressionValueIsNotNull(fragmentActivityRequireActivity, "requireActivity()");
            ViewModelProvider.Factory defaultViewModelProviderFactory = fragmentActivityRequireActivity.getDefaultViewModelProviderFactory();
            Intrinsics.checkExpressionValueIsNotNull(defaultViewModelProviderFactory, "requireActivity().defaultViewModelProviderFactory");
            return defaultViewModelProviderFactory;
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    public final AlexaOnboardingViewModel getViewModel() {
        return (AlexaOnboardingViewModel) this.viewModel.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        OnBackPressedDispatcher onBackPressedDispatcher;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.fragment_alexa_explain_permission, container, false);
        View viewFindViewById = viewInflate.findViewById(R.id.alexa_explain_permission_title);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "root.findViewById(R.id.a…explain_permission_title)");
        this.titleTextView = (TextView) viewFindViewById;
        View viewFindViewById2 = viewInflate.findViewById(R.id.alexa_onboarding_image);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "root.findViewById(R.id.alexa_onboarding_image)");
        this.onboardingImageView = (ImageView) viewFindViewById2;
        View viewFindViewById3 = viewInflate.findViewById(R.id.alexa_explain_permission_message);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "root.findViewById(R.id.a…plain_permission_message)");
        this.message = (TextView) viewFindViewById3;
        View viewFindViewById4 = viewInflate.findViewById(R.id.alexa_explain_permission_settings_message);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "root.findViewById(R.id.a…mission_settings_message)");
        this.settingsMessage = (TextView) viewFindViewById4;
        View viewFindViewById5 = viewInflate.findViewById(R.id.alexa_explain_permission_positive_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "root.findViewById(R.id.a…rmission_positive_button)");
        this.positiveButton = (OnboardingPositiveButton) viewFindViewById5;
        View viewFindViewById6 = viewInflate.findViewById(R.id.alexa_explain_permission_negative_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "root.findViewById(R.id.a…rmission_negative_button)");
        this.negativeButton = (OnboardingNegativeButton) viewFindViewById6;
        TextView textView = this.settingsMessage;
        OnboardingNegativeButton onboardingNegativeButton = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("settingsMessage");
            textView = null;
        }
        textView.setVisibility(shouldShowRequestPermissionRationale("android.permission.RECORD_AUDIO") ? 8 : 0);
        OnboardingPositiveButton onboardingPositiveButton = this.positiveButton;
        if (onboardingPositiveButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("positiveButton");
            onboardingPositiveButton = null;
        }
        onboardingPositiveButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.voice.onboarding.view.AlexaExplainPermissionFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AlexaExplainPermissionFragment.m1816onCreateView$lambda0(this.f$0, view);
            }
        });
        OnboardingNegativeButton onboardingNegativeButton2 = this.negativeButton;
        if (onboardingNegativeButton2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("negativeButton");
        } else {
            onboardingNegativeButton = onboardingNegativeButton2;
        }
        onboardingNegativeButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.voice.onboarding.view.AlexaExplainPermissionFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AlexaExplainPermissionFragment.m1817onCreateView$lambda1(this.f$0, view);
            }
        });
        FragmentActivity activity = getActivity();
        if (activity != null && (onBackPressedDispatcher = activity.getOnBackPressedDispatcher()) != null) {
            OnBackPressedDispatcherKt.addCallback$default(onBackPressedDispatcher, getViewLifecycleOwner(), false, new Function1<OnBackPressedCallback, Unit>() { // from class: com.amazon.mp3.voice.onboarding.view.AlexaExplainPermissionFragment.onCreateView.3
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(OnBackPressedCallback onBackPressedCallback) {
                    invoke2(onBackPressedCallback);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(OnBackPressedCallback addCallback) {
                    Intrinsics.checkNotNullParameter(addCallback, "$this$addCallback");
                    AlexaExplainPermissionFragment.this.getViewModel().onBackButtonPressed(PageType.ALEXA_EXPLAIN_PERMISSION);
                }
            }, 2, null);
        }
        return viewInflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-0, reason: not valid java name */
    public static final void m1816onCreateView$lambda0(AlexaExplainPermissionFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().onPositiveButtonClicked(PageType.ALEXA_EXPLAIN_PERMISSION);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-1, reason: not valid java name */
    public static final void m1817onCreateView$lambda1(AlexaExplainPermissionFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().onNegativeButtonClicked(PageType.ALEXA_EXPLAIN_PERMISSION);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        StyleSheetProvider.getStyleSheet().observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.mp3.voice.onboarding.view.AlexaExplainPermissionFragment$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AlexaExplainPermissionFragment.m1818onViewCreated$lambda3(this.f$0, (StyleSheet) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-3, reason: not valid java name */
    public static final void m1818onViewCreated$lambda3(AlexaExplainPermissionFragment this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AlexaOnboardingUIUtil.Companion companion = AlexaOnboardingUIUtil.INSTANCE;
        TextView textView = this$0.titleTextView;
        OnboardingNegativeButton onboardingNegativeButton = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("titleTextView");
            textView = null;
        }
        Resources resources = this$0.getResources();
        Intrinsics.checkNotNullExpressionValue(resources, "resources");
        Intrinsics.checkNotNullExpressionValue(styleSheet, "styleSheet");
        companion.setOnboardingTitle(textView, resources, styleSheet);
        ImageView imageView = this$0.onboardingImageView;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onboardingImageView");
            imageView = null;
        }
        Resources resources2 = this$0.getResources();
        Intrinsics.checkNotNullExpressionValue(resources2, "resources");
        companion.setOnboardingImageSize(imageView, resources2);
        FontStyle fontStyle = styleSheet.getFontStyle(FontStyleKey.PRIMARY);
        if (fontStyle != null) {
            FontUtil fontUtil = FontUtil.INSTANCE;
            TextView textView2 = this$0.message;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("message");
                textView2 = null;
            }
            fontUtil.applyFontStyle(textView2, fontStyle);
            TextView textView3 = this$0.settingsMessage;
            if (textView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("settingsMessage");
                textView3 = null;
            }
            fontUtil.applyFontStyle(textView3, fontStyle);
        }
        TextView textView4 = this$0.settingsMessage;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("settingsMessage");
            textView4 = null;
        }
        String string = this$0.getString(textView4.getVisibility() == 0 ? R.string.dmusic_button_goto_settings : R.string.dmusic_button_continue);
        Intrinsics.checkNotNullExpressionValue(string, "if (settingsMessage.visi…ontinue\n                )");
        OnboardingPositiveButton onboardingPositiveButton = this$0.positiveButton;
        if (onboardingPositiveButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("positiveButton");
            onboardingPositiveButton = null;
        }
        onboardingPositiveButton.initButton(styleSheet, string);
        OnboardingNegativeButton onboardingNegativeButton2 = this$0.negativeButton;
        if (onboardingNegativeButton2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("negativeButton");
        } else {
            onboardingNegativeButton = onboardingNegativeButton2;
        }
        String string2 = this$0.getString(R.string.dmusic_button_cancel);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.dmusic_button_cancel)");
        onboardingNegativeButton.initButton(styleSheet, string2);
    }
}
