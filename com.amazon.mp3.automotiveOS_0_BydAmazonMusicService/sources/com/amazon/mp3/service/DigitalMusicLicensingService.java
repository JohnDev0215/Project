package com.amazon.mp3.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import androidx.core.app.NotificationCompat;
import com.amazon.mp3.util.Log;
import com.android.vending.licensing.ILicenseResultListener;
import com.android.vending.licensing.ILicensingService;
import com.google.gson.JsonObject;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DigitalMusicLicensingService.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001b2\u00020\u0001:\u0002\u001b\u001cB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u00020\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0013\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/amazon/mp3/service/DigitalMusicLicensingService;", "Landroid/content/ServiceConnection;", "mNonce", "", "mAppContext", "Landroid/content/Context;", "(JLandroid/content/Context;)V", "TAG", "", "kotlin.jvm.PlatformType", "licenseResult", "getLicenseResult", "()Ljava/lang/String;", "mExecuted", "", "mLicensingService", "Lcom/android/vending/licensing/ILicensingService;", "mPackageName", "mSignature", "mSingedData", "onServiceConnected", "", "name", "Landroid/content/ComponentName;", NotificationCompat.CATEGORY_SERVICE, "Landroid/os/IBinder;", "onServiceDisconnected", "Companion", "LicensingResultListener", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DigitalMusicLicensingService implements ServiceConnection {
    private final String TAG;
    private final Context mAppContext;

    @JvmField
    public boolean mExecuted;
    private ILicensingService mLicensingService;
    private final long mNonce;
    private final String mPackageName;

    @JvmField
    public String mSignature;

    @JvmField
    public String mSingedData;

    public DigitalMusicLicensingService(long j, Context mAppContext) {
        Intrinsics.checkNotNullParameter(mAppContext, "mAppContext");
        this.mNonce = j;
        this.mAppContext = mAppContext;
        String packageName = mAppContext.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "mAppContext.packageName");
        this.mPackageName = packageName;
        this.mSingedData = "";
        this.mSignature = "";
        this.TAG = DigitalMusicLicensingService.class.getSimpleName();
    }

    public final String getLicenseResult() {
        Unit unit;
        ILicensingService iLicensingService = this.mLicensingService;
        if (iLicensingService == null) {
            try {
                if (!this.mAppContext.bindService(new Intent("com.android.vending.licensing.ILicensingService").setPackage("com.android.vending"), this, 1)) {
                    Log.debug(this.TAG, "getLVLResult => LVLException: Did not bind");
                    this.mExecuted = true;
                }
            } catch (SecurityException e) {
                Log.debug(this.TAG, Intrinsics.stringPlus("getLVLResult => SecurityException: ", e.getMessage()));
                this.mExecuted = true;
            }
        } else {
            if (iLicensingService == null) {
                unit = null;
            } else {
                try {
                    iLicensingService.checkLicense(this.mNonce, this.mPackageName, new LicensingResultListener(this));
                    unit = Unit.INSTANCE;
                } catch (RemoteException e2) {
                    Log.debug(this.TAG, Intrinsics.stringPlus("getLVLResult => RemoteException: ", e2.getMessage()));
                    this.mExecuted = true;
                }
            }
            if (unit == null) {
                Log.debug(this.TAG, "licensing service is null");
            }
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        while (!this.mExecuted) {
            SystemClock.sleep(100L);
            if (System.currentTimeMillis() - jCurrentTimeMillis > 2000) {
                break;
            }
        }
        long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
        Log.debug(this.TAG, "Waited " + jCurrentTimeMillis2 + " milliseconds until get Device lvl data.");
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("signedData", this.mSingedData);
        jsonObject.addProperty("signature", this.mSignature);
        String string = jsonObject.toString();
        Intrinsics.checkNotNullExpressionValue(string, "result.toString()");
        return string;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName name, IBinder service) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(service, "service");
        ILicensingService iLicensingServiceAsInterface = ILicensingService.Stub.asInterface(service);
        this.mLicensingService = iLicensingServiceAsInterface;
        if (iLicensingServiceAsInterface == null) {
            return;
        }
        try {
            iLicensingServiceAsInterface.checkLicense(this.mNonce, this.mPackageName, new LicensingResultListener(this));
        } catch (RemoteException e) {
            Log.debug(this.TAG, Intrinsics.stringPlus("onServiceConnected => RemoteException: ", e.getMessage()));
            this.mExecuted = true;
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.mLicensingService = null;
    }

    /* JADX INFO: compiled from: DigitalMusicLicensingService.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/service/DigitalMusicLicensingService$LicensingResultListener;", "Lcom/android/vending/licensing/ILicenseResultListener$Stub;", "(Lcom/amazon/mp3/service/DigitalMusicLicensingService;)V", "verifyLicense", "", "responseCode", "", "signedData", "", "signature", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class LicensingResultListener extends ILicenseResultListener.Stub {
        final /* synthetic */ DigitalMusicLicensingService this$0;

        public LicensingResultListener(DigitalMusicLicensingService this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // com.android.vending.licensing.ILicenseResultListener
        public void verifyLicense(int responseCode, String signedData, String signature) {
            Intrinsics.checkNotNullParameter(signedData, "signedData");
            Intrinsics.checkNotNullParameter(signature, "signature");
            DigitalMusicLicensingService digitalMusicLicensingService = this.this$0;
            digitalMusicLicensingService.mSingedData = signedData;
            digitalMusicLicensingService.mSignature = signature;
            if (!Intrinsics.areEqual("", signedData) && !Intrinsics.areEqual("", signature)) {
                Log.debug(this.this$0.TAG, "lvl_device: Success");
            }
            this.this$0.mExecuted = true;
        }
    }
}
