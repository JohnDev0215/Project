package com.amazon.mp3.auto.signin.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.auto.AutoUiEventUtil;
import com.amazon.mp3.auto.signin.AutomotiveSignInFlowLauncherActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import rx.Subscription;
import rx.functions.Action1;

/* JADX INFO: compiled from: AutomotiveManuallySignInFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000i\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0007*\u00010\u0018\u00002\u00020\u0001B\t\b\u0016¢\u0006\u0004\b3\u00104B\u0011\b\u0016\u0012\u0006\u00105\u001a\u00020\u0012¢\u0006\u0004\b3\u0010\u0018J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0002J\b\u0010\u0005\u001a\u00020\u0002H\u0002J&\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016J\u001a\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010\u0010\u001a\u00020\u0002H\u0016J\b\u0010\u0011\u001a\u00020\u0002H\u0016R$\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0019\u001a\u00020\f8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u001b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0016\u0010\u001f\u001a\u00020\u001e8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0016\u0010\"\u001a\u00020!8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\"\u0010#R\u0016\u0010$\u001a\u00020!8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b$\u0010#R\u0016\u0010%\u001a\u00020\f8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b%\u0010\u001aR\u0016\u0010'\u001a\u00020&8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b'\u0010(R\u0016\u0010)\u001a\u00020&8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b)\u0010(R\u0016\u0010+\u001a\u00020*8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b+\u0010,R\u0018\u0010.\u001a\u0004\u0018\u00010-8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b.\u0010/R\u0014\u00101\u001a\u0002008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u00102¨\u00066"}, d2 = {"Lcom/amazon/mp3/auto/signin/fragment/AutomotiveManuallySignInFragment;", "Landroidx/fragment/app/Fragment;", "", "userInputValidation", "getUsernameInput", "getPasswordInput", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/os/Bundle;", "savedInstanceState", "Landroid/view/View;", "onCreateView", "view", "onViewCreated", "onStart", "onDestroyView", "Lcom/amazon/mp3/auto/signin/AutomotiveSignInFlowLauncherActivity;", "parent", "Lcom/amazon/mp3/auto/signin/AutomotiveSignInFlowLauncherActivity;", "getParent", "()Lcom/amazon/mp3/auto/signin/AutomotiveSignInFlowLauncherActivity;", "setParent", "(Lcom/amazon/mp3/auto/signin/AutomotiveSignInFlowLauncherActivity;)V", "root", "Landroid/view/View;", "Lcom/amazon/ui/foundations/views/BaseButton;", "signInButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "Landroid/widget/ImageView;", "backButton", "Landroid/widget/ImageView;", "Landroid/widget/EditText;", "usernameInput", "Landroid/widget/EditText;", "passwordInput", "legalStatement", "Landroid/widget/TextView;", "conditionsOfUseTextView", "Landroid/widget/TextView;", "privacyNoticeTextView", "Landroidx/constraintlayout/widget/ConstraintLayout;", "constraintLayout", "Landroidx/constraintlayout/widget/ConstraintLayout;", "Lrx/Subscription;", "subscription", "Lrx/Subscription;", "com/amazon/mp3/auto/signin/fragment/AutomotiveManuallySignInFragment$buttonControlWatcher$1", "buttonControlWatcher", "Lcom/amazon/mp3/auto/signin/fragment/AutomotiveManuallySignInFragment$buttonControlWatcher$1;", "<init>", "()V", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class AutomotiveManuallySignInFragment extends Fragment {
    private ImageView backButton;
    private final AutomotiveManuallySignInFragment$buttonControlWatcher$1 buttonControlWatcher;
    private TextView conditionsOfUseTextView;
    private ConstraintLayout constraintLayout;
    private View legalStatement;
    private AutomotiveSignInFlowLauncherActivity parent;
    private EditText passwordInput;
    private TextView privacyNoticeTextView;
    private View root;
    private BaseButton signInButton;
    private Subscription subscription;
    private EditText usernameInput;

    /* JADX WARN: Type inference failed for: r0v0, types: [com.amazon.mp3.auto.signin.fragment.AutomotiveManuallySignInFragment$buttonControlWatcher$1] */
    public AutomotiveManuallySignInFragment() {
        this.buttonControlWatcher = new TextWatcher() { // from class: com.amazon.mp3.auto.signin.fragment.AutomotiveManuallySignInFragment$buttonControlWatcher$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                this.this$0.userInputValidation();
            }
        };
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.amazon.mp3.auto.signin.fragment.AutomotiveManuallySignInFragment$buttonControlWatcher$1] */
    public AutomotiveManuallySignInFragment(AutomotiveSignInFlowLauncherActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.buttonControlWatcher = new TextWatcher() { // from class: com.amazon.mp3.auto.signin.fragment.AutomotiveManuallySignInFragment$buttonControlWatcher$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                this.this$0.userInputValidation();
            }
        };
        this.parent = activity;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.automotive_manually_sign_in_fragment, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…agment, container, false)");
        this.root = viewInflate;
        if (viewInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("root");
            viewInflate = null;
        }
        this.signInButton = (BaseButton) viewInflate.findViewById(R.id.sign_in_button);
        View view = this.root;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("root");
            view = null;
        }
        View viewFindViewById = view.findViewById(R.id.action_goback_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "root.findViewById(R.id.action_goback_button)");
        this.backButton = (ImageView) viewFindViewById;
        View view2 = this.root;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("root");
            view2 = null;
        }
        View viewFindViewById2 = view2.findViewById(R.id.username_input_box);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "root.findViewById(R.id.username_input_box)");
        this.usernameInput = (EditText) viewFindViewById2;
        View view3 = this.root;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("root");
            view3 = null;
        }
        View viewFindViewById3 = view3.findViewById(R.id.password_input_box);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "root.findViewById(R.id.password_input_box)");
        this.passwordInput = (EditText) viewFindViewById3;
        View view4 = this.root;
        if (view4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("root");
            view4 = null;
        }
        View viewFindViewById4 = view4.findViewById(R.id.automotive_legal_statement);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "root.findViewById(R.id.automotive_legal_statement)");
        this.legalStatement = viewFindViewById4;
        if (viewFindViewById4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("legalStatement");
            viewFindViewById4 = null;
        }
        View viewFindViewById5 = viewFindViewById4.findViewById(R.id.conditions_of_use);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "legalStatement.findViewB…d(R.id.conditions_of_use)");
        this.conditionsOfUseTextView = (TextView) viewFindViewById5;
        View view5 = this.legalStatement;
        if (view5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("legalStatement");
            view5 = null;
        }
        View viewFindViewById6 = view5.findViewById(R.id.privacy_notice);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "legalStatement.findViewById(R.id.privacy_notice)");
        this.privacyNoticeTextView = (TextView) viewFindViewById6;
        View view6 = this.root;
        if (view6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("root");
            view6 = null;
        }
        View viewFindViewById7 = view6.findViewById(R.id.constraint_layout);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById7, "root.findViewById(R.id.constraint_layout)");
        this.constraintLayout = (ConstraintLayout) viewFindViewById7;
        this.subscription = StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.auto.signin.fragment.AutomotiveManuallySignInFragment$$ExternalSyntheticLambda4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                AutomotiveManuallySignInFragment.m403onCreateView$lambda1(this.f$0, (StyleSheet) obj);
            }
        });
        if (Resources.getSystem().getDisplayMetrics().heightPixels < Resources.getSystem().getDisplayMetrics().widthPixels) {
            ConstraintSet constraintSet = new ConstraintSet();
            ConstraintLayout constraintLayout = this.constraintLayout;
            if (constraintLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("constraintLayout");
                constraintLayout = null;
            }
            constraintSet.clone(constraintLayout);
            constraintSet.clear(R.id.automotive_legal_statement, 4);
            constraintSet.connect(R.id.automotive_legal_statement, 3, R.id.sign_in_button, 4, ScreenUtil.convertDpToPx(80));
            ConstraintLayout constraintLayout2 = this.constraintLayout;
            if (constraintLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("constraintLayout");
                constraintLayout2 = null;
            }
            constraintSet.applyTo(constraintLayout2);
        }
        View view7 = this.root;
        if (view7 != null) {
            return view7;
        }
        Intrinsics.throwUninitializedPropertyAccessException("root");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-1, reason: not valid java name */
    public static final void m403onCreateView$lambda1(AutomotiveManuallySignInFragment this$0, StyleSheet styleSheet) {
        BaseButton.StyleBuilder buttonBuilder;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BaseButton baseButton = this$0.signInButton;
        if (baseButton == null || (buttonBuilder = styleSheet.getButtonBuilder(ButtonSizeKey.LARGE, ButtonStyleKey.OUTLINE)) == null) {
            return;
        }
        String string = this$0.getString(R.string.dmusic_automotive_sign_in_title);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.dmusi…automotive_sign_in_title)");
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
        EditText editText = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("conditionsOfUseTextView");
            textView = null;
        }
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.auto.signin.fragment.AutomotiveManuallySignInFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AutomotiveManuallySignInFragment.m404onViewCreated$lambda2(this.f$0, view2);
            }
        });
        TextView textView2 = this.privacyNoticeTextView;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("privacyNoticeTextView");
            textView2 = null;
        }
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.auto.signin.fragment.AutomotiveManuallySignInFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AutomotiveManuallySignInFragment.m405onViewCreated$lambda3(this.f$0, view2);
            }
        });
        ImageView imageView = this.backButton;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backButton");
            imageView = null;
        }
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.auto.signin.fragment.AutomotiveManuallySignInFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AutomotiveManuallySignInFragment.m406onViewCreated$lambda4(this.f$0, view2);
            }
        });
        BaseButton baseButton = this.signInButton;
        if (baseButton != null) {
            baseButton.setEnabled(false);
        }
        BaseButton baseButton2 = this.signInButton;
        if (baseButton2 != null) {
            baseButton2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.auto.signin.fragment.AutomotiveManuallySignInFragment$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    AutomotiveManuallySignInFragment.m407onViewCreated$lambda5(this.f$0, view2);
                }
            });
        }
        EditText editText2 = this.passwordInput;
        if (editText2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("passwordInput");
            editText2 = null;
        }
        editText2.addTextChangedListener(this.buttonControlWatcher);
        EditText editText3 = this.usernameInput;
        if (editText3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("usernameInput");
        } else {
            editText = editText3;
        }
        editText.addTextChangedListener(this.buttonControlWatcher);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-2, reason: not valid java name */
    public static final void m404onViewCreated$lambda2(AutomotiveManuallySignInFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AutomotiveSignInFlowLauncherActivity automotiveSignInFlowLauncherActivity = this$0.parent;
        if (automotiveSignInFlowLauncherActivity == null) {
            return;
        }
        automotiveSignInFlowLauncherActivity.switchFragment(this$0, automotiveSignInFlowLauncherActivity == null ? null : automotiveSignInFlowLauncherActivity.getLegalPageFragment());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-3, reason: not valid java name */
    public static final void m405onViewCreated$lambda3(AutomotiveManuallySignInFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AutomotiveSignInFlowLauncherActivity automotiveSignInFlowLauncherActivity = this$0.parent;
        if (automotiveSignInFlowLauncherActivity == null) {
            return;
        }
        automotiveSignInFlowLauncherActivity.switchFragment(this$0, automotiveSignInFlowLauncherActivity == null ? null : automotiveSignInFlowLauncherActivity.getLegalPageFragment());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-4, reason: not valid java name */
    public static final void m406onViewCreated$lambda4(AutomotiveManuallySignInFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AutomotiveSignInFlowLauncherActivity automotiveSignInFlowLauncherActivity = this$0.parent;
        if (automotiveSignInFlowLauncherActivity == null) {
            return;
        }
        automotiveSignInFlowLauncherActivity.switchFragment(this$0, automotiveSignInFlowLauncherActivity == null ? null : automotiveSignInFlowLauncherActivity.getCodeBasedSignInFragment());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-5, reason: not valid java name */
    public static final void m407onViewCreated$lambda5(AutomotiveManuallySignInFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getUsernameInput();
        this$0.getPasswordInput();
        AutoUiEventUtil.sendUiClickEvent(ActionType.SELECT_AUTOMOTIVE_OS_SIGN_IN, PageType.AUTOMOTIVE_OS_SIGN_IN);
        AutomotiveSignInFlowLauncherActivity automotiveSignInFlowLauncherActivity = this$0.parent;
        if (automotiveSignInFlowLauncherActivity == null) {
            return;
        }
        automotiveSignInFlowLauncherActivity.startRxSignInFlow();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        AutomotiveSignInFlowLauncherActivity automotiveSignInFlowLauncherActivity = this.parent;
        if (automotiveSignInFlowLauncherActivity == null) {
            return;
        }
        automotiveSignInFlowLauncherActivity.checkConnectivity();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        Subscription subscription = this.subscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        EditText editText = this.passwordInput;
        EditText editText2 = null;
        if (editText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("passwordInput");
            editText = null;
        }
        editText.removeTextChangedListener(this.buttonControlWatcher);
        EditText editText3 = this.usernameInput;
        if (editText3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("usernameInput");
        } else {
            editText2 = editText3;
        }
        editText2.removeTextChangedListener(this.buttonControlWatcher);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void userInputValidation() {
        BaseButton baseButton;
        EditText editText = this.usernameInput;
        EditText editText2 = null;
        if (editText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("usernameInput");
            editText = null;
        }
        if (TextUtils.isEmpty(editText.getText())) {
            return;
        }
        EditText editText3 = this.passwordInput;
        if (editText3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("passwordInput");
        } else {
            editText2 = editText3;
        }
        if (TextUtils.isEmpty(editText2.getText()) || (baseButton = this.signInButton) == null) {
            return;
        }
        baseButton.setEnabled(true);
    }

    private final void getUsernameInput() {
        AutomotiveSignInFlowLauncherActivity automotiveSignInFlowLauncherActivity = this.parent;
        if (automotiveSignInFlowLauncherActivity == null) {
            return;
        }
        EditText editText = this.usernameInput;
        if (editText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("usernameInput");
            editText = null;
        }
        automotiveSignInFlowLauncherActivity.setUsername(editText.getText().toString());
    }

    private final void getPasswordInput() {
        AutomotiveSignInFlowLauncherActivity automotiveSignInFlowLauncherActivity = this.parent;
        if (automotiveSignInFlowLauncherActivity == null) {
            return;
        }
        EditText editText = this.passwordInput;
        if (editText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("passwordInput");
            editText = null;
        }
        automotiveSignInFlowLauncherActivity.setPassword(editText.getText().toString());
    }
}
