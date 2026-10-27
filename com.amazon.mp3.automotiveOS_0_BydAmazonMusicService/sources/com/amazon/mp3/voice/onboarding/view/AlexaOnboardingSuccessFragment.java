package com.amazon.mp3.voice.onboarding.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherKt;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.styles.StyleSheetProvider;
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

/* JADX INFO: compiled from: AlexaOnboardingSuccessFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u001a\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/voice/onboarding/view/AlexaOnboardingSuccessFragment;", "Landroidx/fragment/app/DialogFragment;", "()V", "closeButton", "Landroid/widget/ImageButton;", "hintText", "Landroid/widget/TextView;", "viewModel", "Lcom/amazon/mp3/voice/onboarding/AlexaOnboardingViewModel;", "getViewModel", "()Lcom/amazon/mp3/voice/onboarding/AlexaOnboardingViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AlexaOnboardingSuccessFragment extends DialogFragment {
    private ImageButton closeButton;
    private TextView hintText;

    /* JADX INFO: renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(AlexaOnboardingViewModel.class), new Function0<ViewModelStore>() { // from class: com.amazon.mp3.voice.onboarding.view.AlexaOnboardingSuccessFragment$special$$inlined$activityViewModels$default$1
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
    }, new Function0<ViewModelProvider.Factory>() { // from class: com.amazon.mp3.voice.onboarding.view.AlexaOnboardingSuccessFragment$special$$inlined$activityViewModels$default$2
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
        View viewInflate = inflater.inflate(R.layout.fragment_alexa_onboarding_success, container, false);
        View viewFindViewById = viewInflate.findViewById(R.id.alexa_success_close_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "root.findViewById(R.id.alexa_success_close_button)");
        this.closeButton = (ImageButton) viewFindViewById;
        View viewFindViewById2 = viewInflate.findViewById(R.id.alexa_success_hint_text);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "root.findViewById(R.id.alexa_success_hint_text)");
        this.hintText = (TextView) viewFindViewById2;
        ImageButton imageButton = this.closeButton;
        if (imageButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("closeButton");
            imageButton = null;
        }
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.voice.onboarding.view.AlexaOnboardingSuccessFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AlexaOnboardingSuccessFragment.m1827onCreateView$lambda0(this.f$0, view);
            }
        });
        FragmentActivity activity = getActivity();
        if (activity != null && (onBackPressedDispatcher = activity.getOnBackPressedDispatcher()) != null) {
            OnBackPressedDispatcherKt.addCallback$default(onBackPressedDispatcher, getViewLifecycleOwner(), false, new Function1<OnBackPressedCallback, Unit>() { // from class: com.amazon.mp3.voice.onboarding.view.AlexaOnboardingSuccessFragment.onCreateView.2
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
                    AlexaOnboardingSuccessFragment.this.getViewModel().onBackButtonPressed(PageType.ALEXA_ONBOARDING_SUCCESS);
                }
            }, 2, null);
        }
        return viewInflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-0, reason: not valid java name */
    public static final void m1827onCreateView$lambda0(AlexaOnboardingSuccessFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().onCloseButtonClicked(PageType.ALEXA_ONBOARDING_SUCCESS);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        StyleSheetProvider.getStyleSheet().observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.mp3.voice.onboarding.view.AlexaOnboardingSuccessFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AlexaOnboardingSuccessFragment.m1828onViewCreated$lambda2(this.f$0, (StyleSheet) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-2, reason: not valid java name */
    public static final void m1828onViewCreated$lambda2(AlexaOnboardingSuccessFragment this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FontStyle fontStyle = styleSheet.getFontStyle(FontStyleKey.HEADLINE_4);
        if (fontStyle == null) {
            return;
        }
        FontUtil fontUtil = FontUtil.INSTANCE;
        TextView textView = this$0.hintText;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("hintText");
            textView = null;
        }
        fontUtil.applyFontStyle(textView, fontStyle);
    }
}
