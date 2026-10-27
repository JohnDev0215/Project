package com.amazon.mp3.voice.onboarding.view;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
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
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.voice.onboarding.AlexaOnboardingUIUtil;
import com.amazon.mp3.voice.onboarding.AlexaOnboardingViewModel;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.voice.util.AlexaLocaleUtil;
import com.amazon.music.voice.util.AlexaTOUUtil;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: AlexaTOUFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u001a\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00132\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001d"}, d2 = {"Lcom/amazon/mp3/voice/onboarding/view/AlexaTOUFragment;", "Landroidx/fragment/app/Fragment;", "()V", "negativeButton", "Lcom/amazon/mp3/voice/onboarding/view/OnboardingNegativeButton;", "onboardingImageView", "Landroid/widget/ImageView;", "positiveButton", "Lcom/amazon/mp3/voice/onboarding/view/OnboardingPositiveButton;", "titleTextView", "Landroid/widget/TextView;", "touTextView", "viewModel", "Lcom/amazon/mp3/voice/onboarding/AlexaOnboardingViewModel;", "getViewModel", "()Lcom/amazon/mp3/voice/onboarding/AlexaOnboardingViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AlexaTOUFragment extends Fragment {
    private OnboardingNegativeButton negativeButton;
    private ImageView onboardingImageView;
    private OnboardingPositiveButton positiveButton;
    private TextView titleTextView;
    private TextView touTextView;

    /* JADX INFO: renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(AlexaOnboardingViewModel.class), new Function0<ViewModelStore>() { // from class: com.amazon.mp3.voice.onboarding.view.AlexaTOUFragment$special$$inlined$activityViewModels$default$1
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
    }, new Function0<ViewModelProvider.Factory>() { // from class: com.amazon.mp3.voice.onboarding.view.AlexaTOUFragment$special$$inlined$activityViewModels$default$2
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
        View viewInflate = inflater.inflate(R.layout.fragment_alexa_tou, container, false);
        View viewFindViewById = viewInflate.findViewById(R.id.alexa_tou_title);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "root.findViewById(R.id.alexa_tou_title)");
        this.titleTextView = (TextView) viewFindViewById;
        View viewFindViewById2 = viewInflate.findViewById(R.id.alexa_onboarding_image);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "root.findViewById(R.id.alexa_onboarding_image)");
        this.onboardingImageView = (ImageView) viewFindViewById2;
        View viewFindViewById3 = viewInflate.findViewById(R.id.alexa_tou_text);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "root.findViewById(R.id.alexa_tou_text)");
        this.touTextView = (TextView) viewFindViewById3;
        View viewFindViewById4 = viewInflate.findViewById(R.id.alexa_tou_positive_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "root.findViewById(R.id.alexa_tou_positive_button)");
        this.positiveButton = (OnboardingPositiveButton) viewFindViewById4;
        View viewFindViewById5 = viewInflate.findViewById(R.id.alexa_tou_negative_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "root.findViewById(R.id.alexa_tou_negative_button)");
        this.negativeButton = (OnboardingNegativeButton) viewFindViewById5;
        TextView textView = this.touTextView;
        OnboardingNegativeButton onboardingNegativeButton = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("touTextView");
            textView = null;
        }
        textView.setMovementMethod(new LinkMovementMethod());
        TextView textView2 = this.touTextView;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("touTextView");
            textView2 = null;
        }
        textView2.setText(AlexaTOUUtil.getTOUForLocale(AlexaLocaleUtil.getSavedLocale(getContext()), AccountDetailUtil.getMusicTerritoryOfResidence()));
        OnboardingPositiveButton onboardingPositiveButton = this.positiveButton;
        if (onboardingPositiveButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("positiveButton");
            onboardingPositiveButton = null;
        }
        onboardingPositiveButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.voice.onboarding.view.AlexaTOUFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AlexaTOUFragment.m1830onCreateView$lambda0(this.f$0, view);
            }
        });
        OnboardingNegativeButton onboardingNegativeButton2 = this.negativeButton;
        if (onboardingNegativeButton2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("negativeButton");
        } else {
            onboardingNegativeButton = onboardingNegativeButton2;
        }
        onboardingNegativeButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.voice.onboarding.view.AlexaTOUFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AlexaTOUFragment.m1831onCreateView$lambda1(this.f$0, view);
            }
        });
        FragmentActivity activity = getActivity();
        if (activity != null && (onBackPressedDispatcher = activity.getOnBackPressedDispatcher()) != null) {
            OnBackPressedDispatcherKt.addCallback$default(onBackPressedDispatcher, getViewLifecycleOwner(), false, new Function1<OnBackPressedCallback, Unit>() { // from class: com.amazon.mp3.voice.onboarding.view.AlexaTOUFragment.onCreateView.3
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
                    AlexaTOUFragment.this.getViewModel().onBackButtonPressed(PageType.ALEXA_TOU);
                }
            }, 2, null);
        }
        return viewInflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-0, reason: not valid java name */
    public static final void m1830onCreateView$lambda0(AlexaTOUFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().onPositiveButtonClicked(PageType.ALEXA_TOU);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-1, reason: not valid java name */
    public static final void m1831onCreateView$lambda1(AlexaTOUFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().onNegativeButtonClicked(PageType.ALEXA_TOU);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        StyleSheetProvider.getStyleSheet().observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.mp3.voice.onboarding.view.AlexaTOUFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AlexaTOUFragment.m1832onViewCreated$lambda3(this.f$0, (StyleSheet) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-3, reason: not valid java name */
    public static final void m1832onViewCreated$lambda3(AlexaTOUFragment this$0, StyleSheet styleSheet) {
        FontStyle fontStyleCopy$default;
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
        FontStyle fontStyle = styleSheet.getFontStyle(FontStyleKey.INDEX);
        if (fontStyle != null && (fontStyleCopy$default = FontStyle.copy$default(fontStyle, null, null, 0, Boolean.FALSE, 7, null)) != null) {
            FontUtil fontUtil = FontUtil.INSTANCE;
            TextView textView2 = this$0.touTextView;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("touTextView");
                textView2 = null;
            }
            fontUtil.applyFontStyle(textView2, fontStyleCopy$default);
        }
        OnboardingPositiveButton onboardingPositiveButton = this$0.positiveButton;
        if (onboardingPositiveButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("positiveButton");
            onboardingPositiveButton = null;
        }
        String string = this$0.getString(R.string.dmusic_button_agree_and_continue);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.dmusi…utton_agree_and_continue)");
        onboardingPositiveButton.initButton(styleSheet, string);
        OnboardingNegativeButton onboardingNegativeButton2 = this$0.negativeButton;
        if (onboardingNegativeButton2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("negativeButton");
        } else {
            onboardingNegativeButton = onboardingNegativeButton2;
        }
        String string2 = this$0.getString(R.string.dmusic_button_maybe_later);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.dmusic_button_maybe_later)");
        onboardingNegativeButton.initButton(styleSheet, string2);
    }
}
