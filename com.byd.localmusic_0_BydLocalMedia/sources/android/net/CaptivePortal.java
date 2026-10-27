package android.net;

import android.annotation.SystemApi;
import android.net.ICaptivePortal;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public class CaptivePortal implements Parcelable {
    private static final int APP_REQUEST_BASE = 100;

    @SystemApi
    public static final int APP_REQUEST_REEVALUATION_REQUIRED = 100;

    @SystemApi
    public static final int APP_RETURN_DISMISSED = 0;

    @SystemApi
    public static final int APP_RETURN_UNWANTED = 1;

    @SystemApi
    public static final int APP_RETURN_WANTED_AS_IS = 2;
    public static final Parcelable.Creator<CaptivePortal> CREATOR = new Parcelable.Creator<CaptivePortal>() { // from class: android.net.CaptivePortal.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CaptivePortal createFromParcel(Parcel parcel) {
            return new CaptivePortal(parcel.readStrongBinder());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CaptivePortal[] newArray(int i) {
            return new CaptivePortal[i];
        }
    };
    private final IBinder mBinder;

    public @interface EventId {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CaptivePortal(IBinder iBinder) {
        this.mBinder = iBinder;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStrongBinder(this.mBinder);
    }

    public void reportCaptivePortalDismissed() {
        try {
            ICaptivePortal.Stub.asInterface(this.mBinder).appResponse(0);
        } catch (RemoteException unused) {
        }
    }

    public void ignoreNetwork() {
        try {
            ICaptivePortal.Stub.asInterface(this.mBinder).appResponse(1);
        } catch (RemoteException unused) {
        }
    }

    @SystemApi
    public void useNetwork() {
        try {
            ICaptivePortal.Stub.asInterface(this.mBinder).appResponse(2);
        } catch (RemoteException unused) {
        }
    }

    @SystemApi
    public void reevaluateNetwork() {
        try {
            ICaptivePortal.Stub.asInterface(this.mBinder).appRequest(100);
        } catch (RemoteException unused) {
        }
    }

    @SystemApi
    public void logEvent(int i, String str) {
        try {
            ICaptivePortal.Stub.asInterface(this.mBinder).logEvent(i, str);
        } catch (RemoteException unused) {
        }
    }
}
