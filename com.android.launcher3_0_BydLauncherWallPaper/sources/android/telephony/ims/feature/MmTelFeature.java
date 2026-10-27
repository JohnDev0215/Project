package android.telephony.ims.feature;

import android.annotation.SystemApi;
import android.os.Bundle;
import android.os.Message;
import android.os.RemoteException;
import android.telephony.ims.ImsCallProfile;
import android.telephony.ims.ImsReasonInfo;
import android.telephony.ims.aidl.IImsCapabilityCallback;
import android.telephony.ims.aidl.IImsMmTelFeature;
import android.telephony.ims.aidl.IImsMmTelListener;
import android.telephony.ims.aidl.IImsSmsListener;
import android.telephony.ims.feature.ImsFeature;
import android.telephony.ims.stub.ImsCallSessionImplBase;
import android.telephony.ims.stub.ImsEcbmImplBase;
import android.telephony.ims.stub.ImsMultiEndpointImplBase;
import android.telephony.ims.stub.ImsSmsImplBase;
import android.telephony.ims.stub.ImsUtImplBase;
import com.android.ims.internal.IImsCallSession;
import com.android.ims.internal.IImsEcbm;
import com.android.ims.internal.IImsMultiEndpoint;
import com.android.ims.internal.IImsUt;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes3.dex */
public class MmTelFeature extends ImsFeature {

    @SystemApi
    public static final String EXTRA_IS_UNKNOWN_CALL = "android.telephony.ims.feature.extra.IS_UNKNOWN_CALL";

    @SystemApi
    public static final String EXTRA_IS_USSD = "android.telephony.ims.feature.extra.IS_USSD";
    private static final String LOG_TAG = "MmTelFeature";

    @SystemApi
    public static final int PROCESS_CALL_CSFB = 1;

    @SystemApi
    public static final int PROCESS_CALL_IMS = 0;
    private final IImsMmTelFeature mImsMMTelBinder = new IImsMmTelFeature.Stub() { // from class: android.telephony.ims.feature.MmTelFeature.1
        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public void setListener(IImsMmTelListener iImsMmTelListener) {
            MmTelFeature.this.setListener(iImsMmTelListener);
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public int getFeatureState() throws RemoteException {
            try {
                return MmTelFeature.this.getFeatureState();
            } catch (Exception e) {
                throw new RemoteException(e.getMessage());
            }
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public ImsCallProfile createCallProfile(int i, int i2) throws RemoteException {
            ImsCallProfile imsCallProfileCreateCallProfile;
            synchronized (MmTelFeature.this.mLock) {
                try {
                    try {
                        imsCallProfileCreateCallProfile = MmTelFeature.this.createCallProfile(i, i2);
                    } catch (Exception e) {
                        throw new RemoteException(e.getMessage());
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return imsCallProfileCreateCallProfile;
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public IImsCallSession createCallSession(ImsCallProfile imsCallProfile) throws RemoteException {
            IImsCallSession iImsCallSessionCreateCallSessionInterface;
            synchronized (MmTelFeature.this.mLock) {
                iImsCallSessionCreateCallSessionInterface = MmTelFeature.this.createCallSessionInterface(imsCallProfile);
            }
            return iImsCallSessionCreateCallSessionInterface;
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public int shouldProcessCall(String[] strArr) {
            int iShouldProcessCall;
            synchronized (MmTelFeature.this.mLock) {
                iShouldProcessCall = MmTelFeature.this.shouldProcessCall(strArr);
            }
            return iShouldProcessCall;
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public IImsUt getUtInterface() throws RemoteException {
            IImsUt utInterface;
            synchronized (MmTelFeature.this.mLock) {
                utInterface = MmTelFeature.this.getUtInterface();
            }
            return utInterface;
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public IImsEcbm getEcbmInterface() throws RemoteException {
            IImsEcbm ecbmInterface;
            synchronized (MmTelFeature.this.mLock) {
                ecbmInterface = MmTelFeature.this.getEcbmInterface();
            }
            return ecbmInterface;
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public void setUiTtyMode(int i, Message message) throws RemoteException {
            synchronized (MmTelFeature.this.mLock) {
                try {
                    try {
                        MmTelFeature.this.setUiTtyMode(i, message);
                    } catch (Exception e) {
                        throw new RemoteException(e.getMessage());
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public IImsMultiEndpoint getMultiEndpointInterface() throws RemoteException {
            IImsMultiEndpoint multiEndpointInterface;
            synchronized (MmTelFeature.this.mLock) {
                multiEndpointInterface = MmTelFeature.this.getMultiEndpointInterface();
            }
            return multiEndpointInterface;
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public int queryCapabilityStatus() {
            return MmTelFeature.this.queryCapabilityStatus().mCapabilities;
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public void addCapabilityCallback(IImsCapabilityCallback iImsCapabilityCallback) {
            MmTelFeature.this.addCapabilityCallback(iImsCapabilityCallback);
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public void removeCapabilityCallback(IImsCapabilityCallback iImsCapabilityCallback) {
            MmTelFeature.this.removeCapabilityCallback(iImsCapabilityCallback);
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public void changeCapabilitiesConfiguration(CapabilityChangeRequest capabilityChangeRequest, IImsCapabilityCallback iImsCapabilityCallback) {
            MmTelFeature.this.requestChangeEnabledCapabilities(capabilityChangeRequest, iImsCapabilityCallback);
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public void queryCapabilityConfiguration(int i, int i2, IImsCapabilityCallback iImsCapabilityCallback) {
            MmTelFeature.this.queryCapabilityConfigurationInternal(i, i2, iImsCapabilityCallback);
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public void setSmsListener(IImsSmsListener iImsSmsListener) {
            MmTelFeature.this.setSmsListener(iImsSmsListener);
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public void sendSms(int i, int i2, String str, String str2, boolean z, byte[] bArr) {
            synchronized (MmTelFeature.this.mLock) {
                MmTelFeature.this.sendSms(i, i2, str, str2, z, bArr);
            }
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public void acknowledgeSms(int i, int i2, int i3) {
            synchronized (MmTelFeature.this.mLock) {
                MmTelFeature.this.acknowledgeSms(i, i2, i3);
            }
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public void acknowledgeSmsReport(int i, int i2, int i3) {
            synchronized (MmTelFeature.this.mLock) {
                MmTelFeature.this.acknowledgeSmsReport(i, i2, i3);
            }
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public String getSmsFormat() {
            String smsFormat;
            synchronized (MmTelFeature.this.mLock) {
                smsFormat = MmTelFeature.this.getSmsFormat();
            }
            return smsFormat;
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public void onSmsReady() {
            synchronized (MmTelFeature.this.mLock) {
                MmTelFeature.this.onSmsReady();
            }
        }
    };
    private IImsMmTelListener mListener;

    public static class Listener extends IImsMmTelListener.Stub {
        @Override // android.telephony.ims.aidl.IImsMmTelListener
        @SystemApi
        public void onIncomingCall(IImsCallSession iImsCallSession, Bundle bundle) {
        }

        @Override // android.telephony.ims.aidl.IImsMmTelListener
        @SystemApi
        public void onRejectedCall(ImsCallProfile imsCallProfile, ImsReasonInfo imsReasonInfo) {
        }

        @Override // android.telephony.ims.aidl.IImsMmTelListener
        @SystemApi
        public void onVoiceMessageCountUpdate(int i) {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ProcessCallResult {
    }

    @Override // android.telephony.ims.feature.ImsFeature
    @SystemApi
    public void changeEnabledCapabilities(CapabilityChangeRequest capabilityChangeRequest, ImsFeature.CapabilityCallbackProxy capabilityCallbackProxy) {
    }

    @SystemApi
    public ImsCallProfile createCallProfile(int i, int i2) {
        return null;
    }

    @SystemApi
    public ImsCallSessionImplBase createCallSession(ImsCallProfile imsCallProfile) {
        return null;
    }

    @Override // android.telephony.ims.feature.ImsFeature
    @SystemApi
    public void onFeatureReady() {
    }

    @Override // android.telephony.ims.feature.ImsFeature
    @SystemApi
    public void onFeatureRemoved() {
    }

    @Override // android.telephony.ims.feature.ImsFeature
    @SystemApi
    public boolean queryCapabilityConfiguration(int i, int i2) {
        return false;
    }

    @SystemApi
    public void setUiTtyMode(int i, Message message) {
    }

    @SystemApi
    public int shouldProcessCall(String[] strArr) {
        return 0;
    }

    @SystemApi
    public MmTelFeature() {
    }

    public static class MmTelCapabilities extends ImsFeature.Capabilities {
        public static final int CAPABILITY_TYPE_SMS = 8;
        public static final int CAPABILITY_TYPE_UT = 4;
        public static final int CAPABILITY_TYPE_VIDEO = 2;
        public static final int CAPABILITY_TYPE_VOICE = 1;

        @Retention(RetentionPolicy.SOURCE)
        public @interface MmTelCapability {
        }

        @SystemApi
        public MmTelCapabilities() {
        }

        @SystemApi
        @Deprecated
        public MmTelCapabilities(ImsFeature.Capabilities capabilities) {
            this.mCapabilities = capabilities.mCapabilities;
        }

        @SystemApi
        public MmTelCapabilities(int i) {
            super(i);
        }

        @Override // android.telephony.ims.feature.ImsFeature.Capabilities
        @SystemApi
        public final void addCapabilities(int i) {
            super.addCapabilities(i);
        }

        @Override // android.telephony.ims.feature.ImsFeature.Capabilities
        @SystemApi
        public final void removeCapabilities(int i) {
            super.removeCapabilities(i);
        }

        @Override // android.telephony.ims.feature.ImsFeature.Capabilities
        @SystemApi
        public final boolean isCapable(int i) {
            return super.isCapable(i);
        }

        @Override // android.telephony.ims.feature.ImsFeature.Capabilities
        public String toString() {
            return "MmTel Capabilities - [Voice: " + isCapable(1) + " Video: " + isCapable(2) + " UT: " + isCapable(4) + " SMS: " + isCapable(8) + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setListener(IImsMmTelListener iImsMmTelListener) {
        synchronized (this.mLock) {
            this.mListener = iImsMmTelListener;
            if (iImsMmTelListener != null) {
                onFeatureReady();
            }
        }
    }

    private IImsMmTelListener getListener() {
        IImsMmTelListener iImsMmTelListener;
        synchronized (this.mLock) {
            iImsMmTelListener = this.mListener;
        }
        return iImsMmTelListener;
    }

    @Override // android.telephony.ims.feature.ImsFeature
    @SystemApi
    public final MmTelCapabilities queryCapabilityStatus() {
        return new MmTelCapabilities(super.queryCapabilityStatus());
    }

    @SystemApi
    public final void notifyCapabilitiesStatusChanged(MmTelCapabilities mmTelCapabilities) {
        if (mmTelCapabilities == null) {
            throw new IllegalArgumentException("MmTelCapabilities must be non-null!");
        }
        super.notifyCapabilitiesStatusChanged((ImsFeature.Capabilities) mmTelCapabilities);
    }

    @SystemApi
    public final void notifyIncomingCall(ImsCallSessionImplBase imsCallSessionImplBase, Bundle bundle) {
        if (imsCallSessionImplBase == null || bundle == null) {
            throw new IllegalArgumentException("ImsCallSessionImplBase and Bundle can not be null.");
        }
        IImsMmTelListener listener = getListener();
        if (listener == null) {
            throw new IllegalStateException("Session is not available.");
        }
        try {
            listener.onIncomingCall(imsCallSessionImplBase.getServiceImpl(), bundle);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @SystemApi
    public final void notifyRejectedCall(ImsCallProfile imsCallProfile, ImsReasonInfo imsReasonInfo) {
        if (imsCallProfile == null || imsReasonInfo == null) {
            throw new IllegalArgumentException("ImsCallProfile and ImsReasonInfo must not be null.");
        }
        IImsMmTelListener listener = getListener();
        if (listener == null) {
            throw new IllegalStateException("Session is not available.");
        }
        try {
            listener.onRejectedCall(imsCallProfile, imsReasonInfo);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public final void notifyIncomingCallSession(IImsCallSession iImsCallSession, Bundle bundle) {
        IImsMmTelListener listener = getListener();
        if (listener == null) {
            throw new IllegalStateException("Session is not available.");
        }
        try {
            listener.onIncomingCall(iImsCallSession, bundle);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @SystemApi
    public final void notifyVoiceMessageCountUpdate(int i) {
        IImsMmTelListener listener = getListener();
        if (listener == null) {
            throw new IllegalStateException("Session is not available.");
        }
        try {
            listener.onVoiceMessageCountUpdate(i);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public IImsCallSession createCallSessionInterface(ImsCallProfile imsCallProfile) throws RemoteException {
        ImsCallSessionImplBase imsCallSessionImplBaseCreateCallSession = createCallSession(imsCallProfile);
        if (imsCallSessionImplBaseCreateCallSession != null) {
            return imsCallSessionImplBaseCreateCallSession.getServiceImpl();
        }
        return null;
    }

    protected IImsUt getUtInterface() throws RemoteException {
        ImsUtImplBase ut = getUt();
        if (ut != null) {
            return ut.getInterface();
        }
        return null;
    }

    protected IImsEcbm getEcbmInterface() throws RemoteException {
        ImsEcbmImplBase ecbm = getEcbm();
        if (ecbm != null) {
            return ecbm.getImsEcbm();
        }
        return null;
    }

    public IImsMultiEndpoint getMultiEndpointInterface() throws RemoteException {
        ImsMultiEndpointImplBase multiEndpoint = getMultiEndpoint();
        if (multiEndpoint != null) {
            return multiEndpoint.getIImsMultiEndpoint();
        }
        return null;
    }

    @SystemApi
    public ImsUtImplBase getUt() {
        return new ImsUtImplBase();
    }

    @SystemApi
    public ImsEcbmImplBase getEcbm() {
        return new ImsEcbmImplBase();
    }

    @SystemApi
    public ImsMultiEndpointImplBase getMultiEndpoint() {
        return new ImsMultiEndpointImplBase();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSmsListener(IImsSmsListener iImsSmsListener) {
        getSmsImplementation().registerSmsListener(iImsSmsListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSms(int i, int i2, String str, String str2, boolean z, byte[] bArr) {
        getSmsImplementation().sendSms(i, i2, str, str2, z, bArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void acknowledgeSms(int i, int i2, int i3) {
        getSmsImplementation().acknowledgeSms(i, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void acknowledgeSmsReport(int i, int i2, int i3) {
        getSmsImplementation().acknowledgeSmsReport(i, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSmsReady() {
        getSmsImplementation().onReady();
    }

    @SystemApi
    public ImsSmsImplBase getSmsImplementation() {
        return new ImsSmsImplBase();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getSmsFormat() {
        return getSmsImplementation().getSmsFormat();
    }

    @Override // android.telephony.ims.feature.ImsFeature
    public final IImsMmTelFeature getBinder() {
        return this.mImsMMTelBinder;
    }
}
