package com.android.car.developeroptions.password;

import android.hardware.biometrics.BiometricPrompt;
import android.os.Bundle;
import android.os.CancellationSignal;
import androidx.fragment.app.FragmentTransaction;
import com.android.car.developeroptions.core.InstrumentedFragment;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class BiometricFragment extends InstrumentedFragment {
    private BiometricPrompt.AuthenticationCallback mAuthenticationCallback = new AnonymousClass1();
    private BiometricPrompt mBiometricPrompt;
    private Bundle mBundle;
    private CancellationSignal mCancellationSignal;
    private BiometricPrompt.AuthenticationCallback mClientCallback;
    private Executor mClientExecutor;
    private int mUserId;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1585;
    }

    /* JADX INFO: renamed from: com.android.car.developeroptions.password.BiometricFragment$1, reason: invalid class name */
    class AnonymousClass1 extends BiometricPrompt.AuthenticationCallback {
        AnonymousClass1() {
        }

        @Override // android.hardware.biometrics.BiometricPrompt.AuthenticationCallback
        public void onAuthenticationError(final int i, final CharSequence charSequence) {
            BiometricFragment.this.mClientExecutor.execute(new Runnable() { // from class: com.android.car.developeroptions.password.-$$Lambda$BiometricFragment$1$lanp52ZOhHnF__y884dtVPgwZSc
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onAuthenticationError$0$BiometricFragment$1(i, charSequence);
                }
            });
            BiometricFragment.this.cleanup();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onAuthenticationError$0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$onAuthenticationError$0$BiometricFragment$1(int i, CharSequence charSequence) {
            BiometricFragment.this.mClientCallback.onAuthenticationError(i, charSequence);
        }

        @Override // android.hardware.biometrics.BiometricPrompt.AuthenticationCallback
        public void onAuthenticationSucceeded(final BiometricPrompt.AuthenticationResult authenticationResult) {
            BiometricFragment.this.mClientExecutor.execute(new Runnable() { // from class: com.android.car.developeroptions.password.-$$Lambda$BiometricFragment$1$HqwpmS3lIFh3uDw4Hp6hZDWy7GA
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onAuthenticationSucceeded$1$BiometricFragment$1(authenticationResult);
                }
            });
            BiometricFragment.this.cleanup();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onAuthenticationSucceeded$1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$onAuthenticationSucceeded$1$BiometricFragment$1(BiometricPrompt.AuthenticationResult authenticationResult) {
            BiometricFragment.this.mClientCallback.onAuthenticationSucceeded(authenticationResult);
        }
    }

    public static BiometricFragment newInstance(Bundle bundle) {
        BiometricFragment biometricFragment = new BiometricFragment();
        biometricFragment.setArguments(bundle);
        return biometricFragment;
    }

    public void setCallbacks(Executor executor, BiometricPrompt.AuthenticationCallback authenticationCallback) {
        this.mClientExecutor = executor;
        this.mClientCallback = authenticationCallback;
    }

    public void setUser(int i) {
        this.mUserId = i;
    }

    public void cancel() {
        CancellationSignal cancellationSignal = this.mCancellationSignal;
        if (cancellationSignal != null) {
            cancellationSignal.cancel();
        }
        cleanup();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cleanup() {
        if (getActivity() != null) {
            FragmentTransaction fragmentTransactionBeginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransactionBeginTransaction.remove(this);
            fragmentTransactionBeginTransaction.commit();
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
        this.mBundle = getArguments();
        this.mBiometricPrompt = new BiometricPrompt.Builder(getContext()).setTitle(this.mBundle.getString("title")).setUseDefaultTitle().setDeviceCredentialAllowed(true).setSubtitle(this.mBundle.getString("subtitle")).setDescription(this.mBundle.getString("description")).setConfirmationRequired(this.mBundle.getBoolean("require_confirmation", true)).build();
        CancellationSignal cancellationSignal = new CancellationSignal();
        this.mCancellationSignal = cancellationSignal;
        this.mBiometricPrompt.authenticateUser(cancellationSignal, this.mClientExecutor, this.mAuthenticationCallback, this.mUserId);
    }
}
