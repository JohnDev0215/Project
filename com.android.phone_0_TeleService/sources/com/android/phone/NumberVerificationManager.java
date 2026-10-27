package com.android.phone;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.telephony.PhoneNumberRange;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.telephony.Call;
import com.android.internal.telephony.INumberVerificationCallback;
import com.android.internal.telephony.Phone;
import com.android.internal.telephony.PhoneFactory;

/* JADX INFO: loaded from: classes.dex */
public class NumberVerificationManager {
    private static String sAuthorizedPackageOverride;
    private static NumberVerificationManager sInstance;
    private INumberVerificationCallback mCallback;
    private PhoneNumberRange mCurrentRange;
    private Handler mHandler;
    private final PhoneListSupplier mPhoneListSupplier;

    interface PhoneListSupplier {
        Phone[] getPhones();
    }

    NumberVerificationManager(PhoneListSupplier phoneListSupplier) {
        this.mPhoneListSupplier = phoneListSupplier;
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    private NumberVerificationManager() {
        this(new PhoneListSupplier() { // from class: com.android.phone.-$$Lambda$fxUIrxSsbnBmVXC9ZDiBS0lZWXU
            @Override // com.android.phone.NumberVerificationManager.PhoneListSupplier
            public final Phone[] getPhones() {
                return PhoneFactory.getPhones();
            }
        });
    }

    public synchronized boolean checkIncomingCall(String str) {
        if (this.mCurrentRange != null && this.mCallback != null) {
            if (!this.mCurrentRange.matches(str)) {
                return false;
            }
            this.mCurrentRange = null;
            try {
                this.mCallback.onCallReceived(str);
                return true;
            } catch (RemoteException unused) {
                Log.w(NumberVerificationManager.class.getSimpleName(), "Remote exception calling verification complete callback");
                return true;
            } finally {
                this.mCallback = null;
            }
        }
        return false;
    }

    synchronized void requestVerification(PhoneNumberRange phoneNumberRange, INumberVerificationCallback iNumberVerificationCallback, long j) {
        if (checkNumberVerificationFeasibility(iNumberVerificationCallback)) {
            this.mCallback = iNumberVerificationCallback;
            this.mCurrentRange = phoneNumberRange;
            this.mHandler.postDelayed(new Runnable() { // from class: com.android.phone.-$$Lambda$NumberVerificationManager$uOBHUk_-X6HBoEFQZU-LU8mOEkM
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$requestVerification$0$NumberVerificationManager();
                }
            }, j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$requestVerification$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$requestVerification$0$NumberVerificationManager() {
        synchronized (this) {
            if (this.mCallback != null && this.mCurrentRange != null) {
                try {
                    this.mCallback.onVerificationFailed(1);
                } catch (RemoteException unused) {
                    Log.w(NumberVerificationManager.class.getSimpleName(), "Remote exception calling verification error callback");
                }
                this.mCallback = null;
                this.mCurrentRange = null;
            }
        }
    }

    private boolean checkNumberVerificationFeasibility(INumberVerificationCallback iNumberVerificationCallback) {
        if (this.mCurrentRange == null && this.mCallback == null) {
            boolean z = false;
            boolean z2 = false;
            for (Phone phone : this.mPhoneListSupplier.getPhones()) {
                if (phone.isInEmergencyCall()) {
                    try {
                        iNumberVerificationCallback.onVerificationFailed(6);
                    } catch (RemoteException unused) {
                        Log.w(NumberVerificationManager.class.getSimpleName(), "Remote exception calling verification error callback");
                    }
                    return false;
                }
                if (phone.isInEcm()) {
                    try {
                        iNumberVerificationCallback.onVerificationFailed(5);
                    } catch (RemoteException unused2) {
                        Log.w(NumberVerificationManager.class.getSimpleName(), "Remote exception calling verification error callback");
                    }
                    return false;
                }
                if (phone.getServiceState().getState() == 0) {
                    z = true;
                }
                if (phone.getRingingCall().getState() == Call.State.IDLE && (phone.getForegroundCall().getState() == Call.State.IDLE || phone.getBackgroundCall().getState() == Call.State.IDLE)) {
                    z2 = true;
                }
            }
            if (!z) {
                try {
                    iNumberVerificationCallback.onVerificationFailed(2);
                } catch (RemoteException unused3) {
                    Log.w(NumberVerificationManager.class.getSimpleName(), "Remote exception calling verification error callback");
                }
                return false;
            }
            if (z2) {
                return true;
            }
            try {
                iNumberVerificationCallback.onVerificationFailed(3);
            } catch (RemoteException unused4) {
                Log.w(NumberVerificationManager.class.getSimpleName(), "Remote exception calling verification error callback");
            }
            return false;
        }
        try {
            iNumberVerificationCallback.onVerificationFailed(4);
        } catch (RemoteException unused5) {
            Log.w(NumberVerificationManager.class.getSimpleName(), "Remote exception calling verification error callback");
        }
        return false;
    }

    public static NumberVerificationManager getInstance() {
        if (sInstance == null) {
            sInstance = new NumberVerificationManager();
        }
        return sInstance;
    }

    static String getAuthorizedPackage(Context context) {
        return !TextUtils.isEmpty(sAuthorizedPackageOverride) ? sAuthorizedPackageOverride : context.getResources().getString(R.string.platform_number_verification_package);
    }

    static void overrideAuthorizedPackage(String str) {
        sAuthorizedPackageOverride = str;
    }
}
