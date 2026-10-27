package com.amazon.mp3.voice.onboarding.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import com.amazon.mp3.activity.GrantMicrophonePermissionsActivity;
import com.amazon.mp3.activity.GrantPermissionActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.voice.onboarding.AlexaOnboardingViewModel;
import com.amazon.mp3.voice.wakeword.WakeWordHelper;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: AlexaOnboardingActivity.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \u00142\u00020\u0001:\u0002\u0014\u0015B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\b\u0010\r\u001a\u00020\nH\u0002J\b\u0010\u000e\u001a\u00020\nH\u0002J\b\u0010\u000f\u001a\u00020\nH\u0002J\b\u0010\u0010\u001a\u00020\nH\u0002J\b\u0010\u0011\u001a\u00020\nH\u0002J\b\u0010\u0012\u001a\u00020\nH\u0002J\b\u0010\u0013\u001a\u00020\nH\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/voice/onboarding/view/AlexaOnboardingActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "viewModel", "Lcom/amazon/mp3/voice/onboarding/AlexaOnboardingViewModel;", "getViewModel", "()Lcom/amazon/mp3/voice/onboarding/AlexaOnboardingViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "registerCloseUIObserver", "registerDisplayExplainPermissionPageObserver", "registerDisplaySuccessPageObserver", "registerDisplayTOUPageObserver", "registerEnableWakeWordObserver", "registerObservers", "registerRequestMicPermissionObserver", "Companion", "MicPermissionResultReceiver", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AlexaOnboardingActivity extends AppCompatActivity {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = AlexaOnboardingActivity.class.getSimpleName();

    /* JADX INFO: renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(AlexaOnboardingViewModel.class), new Function0<ViewModelStore>() { // from class: com.amazon.mp3.voice.onboarding.view.AlexaOnboardingActivity$special$$inlined$viewModels$default$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ViewModelStore invoke() {
            ViewModelStore viewModelStore = this.getViewModelStore();
            Intrinsics.checkNotNullExpressionValue(viewModelStore, "viewModelStore");
            return viewModelStore;
        }
    }, new Function0<ViewModelProvider.Factory>() { // from class: com.amazon.mp3.voice.onboarding.view.AlexaOnboardingActivity$special$$inlined$viewModels$default$1
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ViewModelProvider.Factory invoke() {
            return this.getDefaultViewModelProviderFactory();
        }
    });

    /* JADX INFO: compiled from: AlexaOnboardingActivity.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/voice/onboarding/view/AlexaOnboardingActivity$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG() {
            return AlexaOnboardingActivity.TAG;
        }
    }

    public final AlexaOnboardingViewModel getViewModel() {
        return (AlexaOnboardingViewModel) this.viewModel.getValue();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alexa_onboarding);
        registerObservers();
        getViewModel().displayOnboardingView();
    }

    private final void registerObservers() {
        registerDisplayTOUPageObserver();
        registerDisplayExplainPermissionPageObserver();
        registerDisplaySuccessPageObserver();
        registerRequestMicPermissionObserver();
        registerCloseUIObserver();
        registerEnableWakeWordObserver();
    }

    private final void registerDisplayTOUPageObserver() {
        getViewModel().getDisplayTOUPage().observe(this, new Observer() { // from class: com.amazon.mp3.voice.onboarding.view.AlexaOnboardingActivity$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AlexaOnboardingActivity.m1824registerDisplayTOUPageObserver$lambda1(this.f$0, (Unit) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: registerDisplayTOUPageObserver$lambda-1, reason: not valid java name */
    public static final void m1824registerDisplayTOUPageObserver$lambda1(AlexaOnboardingActivity this$0, Unit unit) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.info(TAG, "navigateToTOUPage");
        FragmentManager supportFragmentManager = this$0.getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        FragmentTransaction fragmentTransactionBeginTransaction = supportFragmentManager.beginTransaction();
        Intrinsics.checkExpressionValueIsNotNull(fragmentTransactionBeginTransaction, "beginTransaction()");
        fragmentTransactionBeginTransaction.setReorderingAllowed(true);
        fragmentTransactionBeginTransaction.replace(R.id.onboarding_fragment, new AlexaTOUFragment());
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
    }

    private final void registerDisplayExplainPermissionPageObserver() {
        getViewModel().getDisplayExplainPermissionPage().observe(this, new Observer() { // from class: com.amazon.mp3.voice.onboarding.view.AlexaOnboardingActivity$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AlexaOnboardingActivity.m1822registerDisplayExplainPermissionPageObserver$lambda3(this.f$0, (Unit) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: registerDisplayExplainPermissionPageObserver$lambda-3, reason: not valid java name */
    public static final void m1822registerDisplayExplainPermissionPageObserver$lambda3(AlexaOnboardingActivity this$0, Unit unit) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.info(TAG, "navigateToExplainPermissionPage");
        FragmentManager supportFragmentManager = this$0.getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        FragmentTransaction fragmentTransactionBeginTransaction = supportFragmentManager.beginTransaction();
        Intrinsics.checkExpressionValueIsNotNull(fragmentTransactionBeginTransaction, "beginTransaction()");
        fragmentTransactionBeginTransaction.setReorderingAllowed(true);
        fragmentTransactionBeginTransaction.replace(R.id.onboarding_fragment, new AlexaExplainPermissionFragment());
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
    }

    private final void registerDisplaySuccessPageObserver() {
        getViewModel().getDisplaySuccessPage().observe(this, new Observer() { // from class: com.amazon.mp3.voice.onboarding.view.AlexaOnboardingActivity$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AlexaOnboardingActivity.m1823registerDisplaySuccessPageObserver$lambda5(this.f$0, (Unit) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: registerDisplaySuccessPageObserver$lambda-5, reason: not valid java name */
    public static final void m1823registerDisplaySuccessPageObserver$lambda5(AlexaOnboardingActivity this$0, Unit unit) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.info(TAG, "navigateToSuccessPage");
        FragmentManager supportFragmentManager = this$0.getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        FragmentTransaction fragmentTransactionBeginTransaction = supportFragmentManager.beginTransaction();
        Intrinsics.checkExpressionValueIsNotNull(fragmentTransactionBeginTransaction, "beginTransaction()");
        fragmentTransactionBeginTransaction.setReorderingAllowed(true);
        fragmentTransactionBeginTransaction.replace(R.id.onboarding_fragment, new AlexaOnboardingSuccessFragment());
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
    }

    private final void registerRequestMicPermissionObserver() {
        getViewModel().getRequestMicPermission().observe(this, new Observer() { // from class: com.amazon.mp3.voice.onboarding.view.AlexaOnboardingActivity$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AlexaOnboardingActivity.m1826registerRequestMicPermissionObserver$lambda6(this.f$0, (Unit) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: registerRequestMicPermissionObserver$lambda-6, reason: not valid java name */
    public static final void m1826registerRequestMicPermissionObserver$lambda6(AlexaOnboardingActivity this$0, Unit unit) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.info(TAG, "navigateToGrantMicPermissionsPage");
        GrantMicrophonePermissionsActivity.requestIfNotGranted(this$0, new MicPermissionResultReceiver(this$0));
    }

    private final void registerCloseUIObserver() {
        getViewModel().getCloseUI().observe(this, new Observer() { // from class: com.amazon.mp3.voice.onboarding.view.AlexaOnboardingActivity$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AlexaOnboardingActivity.m1821registerCloseUIObserver$lambda7(this.f$0, (Unit) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: registerCloseUIObserver$lambda-7, reason: not valid java name */
    public static final void m1821registerCloseUIObserver$lambda7(AlexaOnboardingActivity this$0, Unit unit) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.info(TAG, "closeUI");
        this$0.finish();
    }

    private final void registerEnableWakeWordObserver() {
        getViewModel().getEnableWakeWord().observe(this, new Observer() { // from class: com.amazon.mp3.voice.onboarding.view.AlexaOnboardingActivity$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AlexaOnboardingActivity.m1825registerEnableWakeWordObserver$lambda8(this.f$0, (Unit) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: registerEnableWakeWordObserver$lambda-8, reason: not valid java name */
    public static final void m1825registerEnableWakeWordObserver$lambda8(AlexaOnboardingActivity this$0, Unit unit) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.info(TAG, "enableWakeWord");
        WakeWordHelper.resumeWakeWordService(this$0, true);
    }

    /* JADX INFO: compiled from: AlexaOnboardingActivity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001f\u0010\u0003\u001a\u00020\u00042\u0010\u0010\u0005\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0007\u0018\u00010\u0006H\u0014¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\u0004H\u0014¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/voice/onboarding/view/AlexaOnboardingActivity$MicPermissionResultReceiver;", "Lcom/amazon/mp3/activity/GrantPermissionActivity$PermissionResultReceiver;", "(Lcom/amazon/mp3/voice/onboarding/view/AlexaOnboardingActivity;)V", "onDenied", "", "permissions", "", "", "([Ljava/lang/String;)V", "onGranted", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class MicPermissionResultReceiver extends GrantPermissionActivity.PermissionResultReceiver {
        final /* synthetic */ AlexaOnboardingActivity this$0;

        public MicPermissionResultReceiver(AlexaOnboardingActivity this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // com.amazon.mp3.activity.GrantPermissionActivity.PermissionResultReceiver
        protected void onGranted() {
            super.onGranted();
            Log.info(AlexaOnboardingActivity.INSTANCE.getTAG(), "mic permission granted");
            this.this$0.getViewModel().onPermissionResult(true);
        }

        @Override // com.amazon.mp3.activity.GrantPermissionActivity.PermissionResultReceiver
        protected void onDenied(String[] permissions) {
            super.onDenied(permissions);
            Log.info(AlexaOnboardingActivity.INSTANCE.getTAG(), "mic permission denied");
            this.this$0.getViewModel().onPermissionResult(false);
        }
    }
}
