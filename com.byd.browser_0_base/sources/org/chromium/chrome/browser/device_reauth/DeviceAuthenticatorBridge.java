package org.chromium.chrome.browser.device_reauth;

import J.N;
import android.app.KeyguardManager;
import android.content.Context;
import android.hardware.biometrics.BiometricManager;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
import defpackage.xz;
import java.util.concurrent.Executor;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.task.PostTask;

/* JADX INFO: loaded from: classes.dex */
public class DeviceAuthenticatorBridge {
    public CancellationSignal a;
    public final Context b;
    public long c;
    public final BiometricPrompt d;

    public class a extends BiometricPrompt.AuthenticationCallback {
        public a() {
        }

        @Override // android.hardware.biometrics.BiometricPrompt.AuthenticationCallback
        public final void onAuthenticationError(int i, @NonNull CharSequence charSequence) {
            super.onAuthenticationError(i, charSequence);
            DeviceAuthenticatorBridge deviceAuthenticatorBridge = DeviceAuthenticatorBridge.this;
            if (i == 10) {
                deviceAuthenticatorBridge.a(3);
            } else {
                deviceAuthenticatorBridge.a(4);
            }
        }

        @Override // android.hardware.biometrics.BiometricPrompt.AuthenticationCallback
        public final void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult authenticationResult) {
            super.onAuthenticationSucceeded(authenticationResult);
            int i = Build.VERSION.SDK_INT;
            DeviceAuthenticatorBridge deviceAuthenticatorBridge = DeviceAuthenticatorBridge.this;
            if (i < 30) {
                deviceAuthenticatorBridge.a(0);
            } else if (authenticationResult.getAuthenticationType() == 2) {
                deviceAuthenticatorBridge.a(1);
            } else {
                deviceAuthenticatorBridge.a(2);
            }
        }
    }

    public DeviceAuthenticatorBridge(long j) {
        Context context = xz.a;
        this.b = context;
        this.c = j;
        BiometricPrompt.Builder title = new BiometricPrompt.Builder(context).setTitle(context.getResources().getString(R$string.password_filling_reauth_prompt_title));
        title.setDeviceCredentialAllowed(true);
        title.setConfirmationRequired(false);
        this.d = title.build();
    }

    @CalledByNative
    public static DeviceAuthenticatorBridge create(long j) {
        return new DeviceAuthenticatorBridge(j);
    }

    public final void a(int i) {
        this.a = null;
        long j = this.c;
        if (j != 0) {
            N.M0aiEjGb(j, i);
        }
    }

    @CalledByNative
    public void authenticate() {
        BiometricPrompt biometricPrompt = this.d;
        if (biometricPrompt == null) {
            return;
        }
        CancellationSignal cancellationSignal = new CancellationSignal();
        this.a = cancellationSignal;
        biometricPrompt.authenticate(cancellationSignal, new Executor() { // from class: f90
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                PostTask.c(rh3.a, runnable);
            }
        }, new a());
    }

    @CalledByNative
    public int canAuthenticateWithBiometric() {
        Context context = this.b;
        int iCanAuthenticate = ((BiometricManager) context.getSystemService(BiometricManager.class)).canAuthenticate();
        if (iCanAuthenticate == 0) {
            return ((KeyguardManager) context.getSystemService("keyguard")).isKeyguardSecure() ? 1 : 2;
        }
        if (iCanAuthenticate == 1) {
            return 4;
        }
        if (iCanAuthenticate == 11) {
            return 5;
        }
        if (iCanAuthenticate != 12) {
            return iCanAuthenticate != 15 ? 0 : 6;
        }
        return 3;
    }

    @CalledByNative
    public boolean canAuthenticateWithBiometricOrScreenLock() {
        return canAuthenticateWithBiometric() == 1 || ((KeyguardManager) this.b.getSystemService("keyguard")).isKeyguardSecure();
    }

    @CalledByNative
    public void cancel() {
        CancellationSignal cancellationSignal = this.a;
        if (cancellationSignal != null) {
            cancellationSignal.cancel();
        }
    }

    @CalledByNative
    public void destroy() {
        this.c = 0L;
        cancel();
    }
}
