package com.amazon.mp3.auto.signin.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.amazon.mp3.auto.AutomotiveSignOutHelper;
import com.amazon.mp3.auto.signin.AutomotiveError;
import com.amazon.mp3.auto.signin.AutomotiveSignInFlowLauncherActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import rx.Subscription;
import rx.functions.Action1;

/* JADX INFO: compiled from: AutomotiveGeneralErrorFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B'\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ&\u0010\u0013\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\u001a\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u00102\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010\u001e\u001a\u00020\u001bH\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/amazon/mp3/auto/signin/fragment/AutomotiveGeneralErrorFragment;", "Landroidx/fragment/app/Fragment;", "()V", "parent", "Lcom/amazon/mp3/auto/signin/AutomotiveSignInFlowLauncherActivity;", "error", "Lcom/amazon/mp3/auto/signin/AutomotiveError;", "shouldSignUserOut", "", "(Lcom/amazon/mp3/auto/signin/AutomotiveSignInFlowLauncherActivity;Lcom/amazon/mp3/auto/signin/AutomotiveError;Z)V", "dismissButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "errorTextView", "Landroid/widget/TextView;", "errorTitleTextView", "root", "Landroid/view/View;", "subscription", "Lrx/Subscription;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "", "onViewCreated", "view", "returnToAuthenticationFlow", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AutomotiveGeneralErrorFragment extends Fragment {
    private BaseButton dismissButton;
    private AutomotiveError error;
    private TextView errorTextView;
    private TextView errorTitleTextView;
    private AutomotiveSignInFlowLauncherActivity parent;
    private View root;
    private boolean shouldSignUserOut;
    private Subscription subscription;

    public AutomotiveGeneralErrorFragment(AutomotiveSignInFlowLauncherActivity automotiveSignInFlowLauncherActivity, AutomotiveError automotiveError, boolean z) {
        this.parent = automotiveSignInFlowLauncherActivity;
        this.error = automotiveError;
        this.shouldSignUserOut = z;
    }

    public AutomotiveGeneralErrorFragment() {
        this(null, null, false);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final String string;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.automotive_general_error_fragment, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…agment, container, false)");
        this.root = viewInflate;
        if (viewInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("root");
            viewInflate = null;
        }
        View viewFindViewById = viewInflate.findViewById(R.id.error_message_text);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "root.findViewById(R.id.error_message_text)");
        this.errorTextView = (TextView) viewFindViewById;
        View view = this.root;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("root");
            view = null;
        }
        View viewFindViewById2 = view.findViewById(R.id.error_title_text);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "root.findViewById(R.id.error_title_text)");
        this.errorTitleTextView = (TextView) viewFindViewById2;
        View view2 = this.root;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("root");
            view2 = null;
        }
        View viewFindViewById3 = view2.findViewById(R.id.error_dismiss_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "root.findViewById(R.id.error_dismiss_button)");
        this.dismissButton = (BaseButton) viewFindViewById3;
        if (this.error == AutomotiveError.CONNECTIVITY_ERROR) {
            string = getString(R.string.dmusic_automotive_try_again_button);
        } else {
            string = getString(R.string.dmusic_automotive_dismiss_button);
        }
        Intrinsics.checkNotNullExpressionValue(string, "if (error == AutomotiveE…dismiss_button)\n        }");
        this.subscription = StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.auto.signin.fragment.AutomotiveGeneralErrorFragment$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                AutomotiveGeneralErrorFragment.m396onCreateView$lambda1(this.f$0, string, (StyleSheet) obj);
            }
        });
        View view3 = this.root;
        if (view3 != null) {
            return view3;
        }
        Intrinsics.throwUninitializedPropertyAccessException("root");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-1, reason: not valid java name */
    public static final void m396onCreateView$lambda1(AutomotiveGeneralErrorFragment this$0, String buttonText, StyleSheet styleSheet) {
        BaseButton.StyleBuilder styleBuilderWithText;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(buttonText, "$buttonText");
        if (this$0.dismissButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dismissButton");
        }
        BaseButton.StyleBuilder buttonBuilder = styleSheet.getButtonBuilder(ButtonSizeKey.LARGE, ButtonStyleKey.SOLID);
        if (buttonBuilder == null || (styleBuilderWithText = buttonBuilder.withText(buttonText)) == null) {
            return;
        }
        BaseButton baseButton = this$0.dismissButton;
        if (baseButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dismissButton");
            baseButton = null;
        }
        styleBuilderWithText.applyStyle(baseButton);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        AutomotiveError automotiveError = this.error;
        BaseButton baseButton = null;
        if (automotiveError != null) {
            TextView textView = this.errorTitleTextView;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("errorTitleTextView");
                textView = null;
            }
            textView.setText(getString(automotiveError.getTitleId()));
            TextView textView2 = this.errorTextView;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("errorTextView");
                textView2 = null;
            }
            textView2.setText(getString(automotiveError.getMessageId()));
        }
        BaseButton baseButton2 = this.dismissButton;
        if (baseButton2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dismissButton");
        } else {
            baseButton = baseButton2;
        }
        baseButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.auto.signin.fragment.AutomotiveGeneralErrorFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AutomotiveGeneralErrorFragment.m397onViewCreated$lambda3(this.f$0, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-3, reason: not valid java name */
    public static final void m397onViewCreated$lambda3(final AutomotiveGeneralErrorFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.shouldSignUserOut) {
            AutomotiveSignOutHelper automotiveSignOutHelper = AutomotiveSignOutHelper.INSTANCE;
            Context applicationContext = this$0.requireContext().getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "requireContext().applicationContext");
            automotiveSignOutHelper.signUserOut(applicationContext, new AutomotiveSignOutHelper.SignOutCallback() { // from class: com.amazon.mp3.auto.signin.fragment.AutomotiveGeneralErrorFragment$onViewCreated$2$1
                @Override // com.amazon.mp3.auto.AutomotiveSignOutHelper.SignOutCallback
                public void signOutComplete() {
                    this.this$0.returnToAuthenticationFlow();
                }

                @Override // com.amazon.mp3.auto.AutomotiveSignOutHelper.SignOutCallback
                public void signOutCancel() {
                    AutomotiveSignInFlowLauncherActivity automotiveSignInFlowLauncherActivity = this.this$0.parent;
                    if (automotiveSignInFlowLauncherActivity == null) {
                        return;
                    }
                    automotiveSignInFlowLauncherActivity.finish();
                }
            });
            return;
        }
        this$0.returnToAuthenticationFlow();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void returnToAuthenticationFlow() {
        AutomotiveSignInFlowLauncherActivity automotiveSignInFlowLauncherActivity = this.parent;
        if (automotiveSignInFlowLauncherActivity == null) {
            return;
        }
        automotiveSignInFlowLauncherActivity.switchFragment(this, automotiveSignInFlowLauncherActivity == null ? null : automotiveSignInFlowLauncherActivity.getCodeBasedSignInFragment());
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        Subscription subscription = this.subscription;
        if (subscription == null) {
            return;
        }
        subscription.unsubscribe();
    }
}
