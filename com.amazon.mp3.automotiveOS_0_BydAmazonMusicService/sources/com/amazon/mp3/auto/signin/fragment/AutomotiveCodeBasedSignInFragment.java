package com.amazon.mp3.auto.signin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.auto.signin.AutomotiveSignInFlowLauncherActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.Log;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import rx.Subscription;
import rx.functions.Action1;

/* JADX INFO: compiled from: AutomotiveCodeBasedSignInFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 22\u00020\u0001:\u00012B\t\b\u0016¢\u0006\u0004\b/\u00100B\u0011\b\u0016\u0012\u0006\u00101\u001a\u00020\u0013¢\u0006\u0004\b/\u0010\u0019J\b\u0010\u0003\u001a\u00020\u0002H\u0002J&\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0016J\u001a\u0010\r\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0016J\b\u0010\u000e\u001a\u00020\u0002H\u0016J\b\u0010\u000f\u001a\u00020\u0002H\u0016J\u0010\u0010\u0012\u001a\u00020\u00022\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010R$\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\"\u0010\u001a\u001a\u00020\n8\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0018\u0010!\u001a\u0004\u0018\u00010 8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b!\u0010\"R\u0016\u0010$\u001a\u00020#8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b$\u0010%R\u0016\u0010'\u001a\u00020&8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b'\u0010(R\u0016\u0010)\u001a\u00020\n8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b)\u0010\u001bR\u0016\u0010*\u001a\u00020&8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b*\u0010(R\u0016\u0010+\u001a\u00020&8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b+\u0010(R\u0018\u0010-\u001a\u0004\u0018\u00010,8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b-\u0010.¨\u00063"}, d2 = {"Lcom/amazon/mp3/auto/signin/fragment/AutomotiveCodeBasedSignInFragment;", "Landroidx/fragment/app/Fragment;", "", "updateQRCode", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/os/Bundle;", "savedInstanceState", "Landroid/view/View;", "onCreateView", "view", "onViewCreated", "onStart", "onStop", "", "code", "setCode", "Lcom/amazon/mp3/auto/signin/AutomotiveSignInFlowLauncherActivity;", "parent", "Lcom/amazon/mp3/auto/signin/AutomotiveSignInFlowLauncherActivity;", "getParent", "()Lcom/amazon/mp3/auto/signin/AutomotiveSignInFlowLauncherActivity;", "setParent", "(Lcom/amazon/mp3/auto/signin/AutomotiveSignInFlowLauncherActivity;)V", "root", "Landroid/view/View;", "getRoot", "()Landroid/view/View;", "setRoot", "(Landroid/view/View;)V", "Lcom/amazon/ui/foundations/views/BaseButton;", "signInManuallyButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "Landroid/widget/ImageView;", "qrCodeImage", "Landroid/widget/ImageView;", "Landroid/widget/TextView;", "codeTextView", "Landroid/widget/TextView;", "legalStatement", "conditionsOfUseTextView", "privacyNoticeTextView", "Lrx/Subscription;", "subscription", "Lrx/Subscription;", "<init>", "()V", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class AutomotiveCodeBasedSignInFragment extends Fragment {
    private static final String TAG = AutomotiveCodeBasedSignInFragment.class.getSimpleName();
    private TextView codeTextView;
    private TextView conditionsOfUseTextView;
    private View legalStatement;
    private AutomotiveSignInFlowLauncherActivity parent;
    private TextView privacyNoticeTextView;
    private ImageView qrCodeImage;
    public View root;
    private BaseButton signInManuallyButton;
    private Subscription subscription;

    public AutomotiveCodeBasedSignInFragment() {
    }

    public AutomotiveCodeBasedSignInFragment(AutomotiveSignInFlowLauncherActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.parent = activity;
    }

    public final View getRoot() {
        View view = this.root;
        if (view != null) {
            return view;
        }
        Intrinsics.throwUninitializedPropertyAccessException("root");
        return null;
    }

    public final void setRoot(View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        this.root = view;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.automotive_code_based_sign_in_fragment, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…agment, container, false)");
        setRoot(viewInflate);
        this.signInManuallyButton = (BaseButton) getRoot().findViewById(R.id.sign_in_manually_button);
        View viewFindViewById = getRoot().findViewById(R.id.qr_code_img);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "root.findViewById(R.id.qr_code_img)");
        this.qrCodeImage = (ImageView) viewFindViewById;
        View viewFindViewById2 = getRoot().findViewById(R.id.shortcode_label);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "root.findViewById(R.id.shortcode_label)");
        this.codeTextView = (TextView) viewFindViewById2;
        View viewFindViewById3 = getRoot().findViewById(R.id.automotive_legal_statement);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "root.findViewById(R.id.automotive_legal_statement)");
        this.legalStatement = viewFindViewById3;
        View view = null;
        if (viewFindViewById3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("legalStatement");
            viewFindViewById3 = null;
        }
        View viewFindViewById4 = viewFindViewById3.findViewById(R.id.conditions_of_use);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "legalStatement.findViewB…d(R.id.conditions_of_use)");
        this.conditionsOfUseTextView = (TextView) viewFindViewById4;
        View view2 = this.legalStatement;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("legalStatement");
        } else {
            view = view2;
        }
        View viewFindViewById5 = view.findViewById(R.id.privacy_notice);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "legalStatement.findViewById(R.id.privacy_notice)");
        this.privacyNoticeTextView = (TextView) viewFindViewById5;
        this.subscription = StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.auto.signin.fragment.AutomotiveCodeBasedSignInFragment$$ExternalSyntheticLambda3
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                AutomotiveCodeBasedSignInFragment.m390onCreateView$lambda1(this.f$0, (StyleSheet) obj);
            }
        });
        return getRoot();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-1, reason: not valid java name */
    public static final void m390onCreateView$lambda1(AutomotiveCodeBasedSignInFragment this$0, StyleSheet styleSheet) {
        BaseButton.StyleBuilder buttonBuilder;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BaseButton baseButton = this$0.signInManuallyButton;
        if (baseButton == null || (buttonBuilder = styleSheet.getButtonBuilder(ButtonSizeKey.LARGE, ButtonStyleKey.OUTLINE)) == null) {
            return;
        }
        String string = this$0.getString(R.string.dmusic_automotive_use_credentials_button);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.dmusi…e_use_credentials_button)");
        BaseButton.StyleBuilder styleBuilderWithText = buttonBuilder.withText(string);
        if (styleBuilderWithText == null) {
            return;
        }
        styleBuilderWithText.applyStyle(baseButton);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        TextView textView = this.conditionsOfUseTextView;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("conditionsOfUseTextView");
            textView = null;
        }
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.auto.signin.fragment.AutomotiveCodeBasedSignInFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AutomotiveCodeBasedSignInFragment.m391onViewCreated$lambda2(this.f$0, view2);
            }
        });
        TextView textView3 = this.privacyNoticeTextView;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("privacyNoticeTextView");
        } else {
            textView2 = textView3;
        }
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.auto.signin.fragment.AutomotiveCodeBasedSignInFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AutomotiveCodeBasedSignInFragment.m392onViewCreated$lambda3(this.f$0, view2);
            }
        });
        BaseButton baseButton = this.signInManuallyButton;
        if (baseButton != null) {
            baseButton.setEnabled(true);
        }
        BaseButton baseButton2 = this.signInManuallyButton;
        if (baseButton2 == null) {
            return;
        }
        baseButton2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.auto.signin.fragment.AutomotiveCodeBasedSignInFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AutomotiveCodeBasedSignInFragment.m393onViewCreated$lambda4(this.f$0, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-2, reason: not valid java name */
    public static final void m391onViewCreated$lambda2(AutomotiveCodeBasedSignInFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AutomotiveSignInFlowLauncherActivity automotiveSignInFlowLauncherActivity = this$0.parent;
        if (automotiveSignInFlowLauncherActivity == null) {
            return;
        }
        automotiveSignInFlowLauncherActivity.switchFragment(this$0, automotiveSignInFlowLauncherActivity == null ? null : automotiveSignInFlowLauncherActivity.getLegalPageFragment());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-3, reason: not valid java name */
    public static final void m392onViewCreated$lambda3(AutomotiveCodeBasedSignInFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AutomotiveSignInFlowLauncherActivity automotiveSignInFlowLauncherActivity = this$0.parent;
        if (automotiveSignInFlowLauncherActivity == null) {
            return;
        }
        automotiveSignInFlowLauncherActivity.switchFragment(this$0, automotiveSignInFlowLauncherActivity == null ? null : automotiveSignInFlowLauncherActivity.getLegalPageFragment());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-4, reason: not valid java name */
    public static final void m393onViewCreated$lambda4(AutomotiveCodeBasedSignInFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AutomotiveSignInFlowLauncherActivity automotiveSignInFlowLauncherActivity = this$0.parent;
        if (automotiveSignInFlowLauncherActivity == null) {
            return;
        }
        automotiveSignInFlowLauncherActivity.switchFragment(this$0, automotiveSignInFlowLauncherActivity == null ? null : automotiveSignInFlowLauncherActivity.getManuallySignInFragment());
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        AutomotiveSignInFlowLauncherActivity automotiveSignInFlowLauncherActivity = this.parent;
        if (automotiveSignInFlowLauncherActivity != null) {
            automotiveSignInFlowLauncherActivity.checkConnectivity();
        }
        AutomotiveSignInFlowLauncherActivity automotiveSignInFlowLauncherActivity2 = this.parent;
        if (automotiveSignInFlowLauncherActivity2 != null) {
            automotiveSignInFlowLauncherActivity2.generateCode();
        }
        AutomotiveSignInFlowLauncherActivity automotiveSignInFlowLauncherActivity3 = this.parent;
        setCode(automotiveSignInFlowLauncherActivity3 == null ? null : automotiveSignInFlowLauncherActivity3.getLinkCode());
        Log.debug(AutomotiveSignInFlowLauncherActivity.INSTANCE.getTAG(), "Subscribed to link code generator.");
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        AutomotiveSignInFlowLauncherActivity automotiveSignInFlowLauncherActivity = this.parent;
        if (automotiveSignInFlowLauncherActivity != null) {
            automotiveSignInFlowLauncherActivity.cancelLinkCodeGeneration();
        }
        AutomotiveSignInFlowLauncherActivity automotiveSignInFlowLauncherActivity2 = this.parent;
        if (automotiveSignInFlowLauncherActivity2 != null) {
            automotiveSignInFlowLauncherActivity2.unsubscribeFromSignIn();
        }
        Subscription subscription = this.subscription;
        if (subscription == null) {
            return;
        }
        subscription.unsubscribe();
    }

    public final void setCode(String code) {
        if (code == null) {
            Log.error(TAG, "Cannot set code text to null value!");
            return;
        }
        TextView textView = this.codeTextView;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("codeTextView");
            textView = null;
        }
        textView.setText(code);
        updateQRCode();
    }

    private final void updateQRCode() {
        ImageView imageView = this.qrCodeImage;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("qrCodeImage");
            imageView = null;
        }
        AutomotiveSignInFlowLauncherActivity automotiveSignInFlowLauncherActivity = this.parent;
        imageView.setImageBitmap(automotiveSignInFlowLauncherActivity != null ? automotiveSignInFlowLauncherActivity.getQRCode() : null);
    }
}
