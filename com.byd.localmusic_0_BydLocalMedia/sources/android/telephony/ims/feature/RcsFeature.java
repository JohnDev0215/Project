package android.telephony.ims.feature;

import android.annotation.SystemApi;
import android.net.Uri;
import android.os.RemoteException;
import android.telephony.ims.RcsContactUceCapability;
import android.telephony.ims.aidl.IImsCapabilityCallback;
import android.telephony.ims.aidl.IImsRcsFeature;
import android.telephony.ims.aidl.IRcsFeatureListener;
import android.telephony.ims.feature.ImsFeature;
import android.telephony.ims.stub.RcsPresenceExchangeImplBase;
import android.telephony.ims.stub.RcsSipOptionsImplBase;
import android.util.Log;
import com.android.internal.telephony.util.TelephonyUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
@SystemApi
public class RcsFeature extends ImsFeature {
    private static final String LOG_TAG = "RcsFeature";
    private final RcsFeatureBinder mImsRcsBinder;
    private IRcsFeatureListener mListenerBinder;
    private RcsPresenceExchangeImplBase mPresExchange;
    private RcsSipOptionsImplBase mSipOptions;

    @Override // android.telephony.ims.feature.ImsFeature
    public void changeEnabledCapabilities(CapabilityChangeRequest capabilityChangeRequest, ImsFeature.CapabilityCallbackProxy capabilityCallbackProxy) {
    }

    @Override // android.telephony.ims.feature.ImsFeature
    public void onFeatureReady() {
    }

    @Override // android.telephony.ims.feature.ImsFeature
    public void onFeatureRemoved() {
    }

    @Override // android.telephony.ims.feature.ImsFeature
    public boolean queryCapabilityConfiguration(int i, int i2) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class RcsFeatureBinder extends IImsRcsFeature.Stub {
        private final Executor mExecutor;
        private final RcsFeature mReference;

        RcsFeatureBinder(RcsFeature rcsFeature, Executor executor) {
            this.mReference = rcsFeature;
            this.mExecutor = executor;
        }

        @Override // android.telephony.ims.aidl.IImsRcsFeature
        public void setListener(IRcsFeatureListener iRcsFeatureListener) {
            this.mReference.setListener(iRcsFeatureListener);
        }

        @Override // android.telephony.ims.aidl.IImsRcsFeature
        public int queryCapabilityStatus() throws RemoteException {
            return ((Integer) executeMethodAsyncForResult(new Supplier() { // from class: android.telephony.ims.feature.-$$Lambda$RcsFeature$RcsFeatureBinder$M_Xsfh7yLPmebDSvMzAvEPPUmE0
                @Override // java.util.function.Supplier
                public final Object get() {
                    return this.f$0.lambda$queryCapabilityStatus$0$RcsFeature$RcsFeatureBinder();
                }
            }, "queryCapabilityStatus")).intValue();
        }

        public /* synthetic */ Integer lambda$queryCapabilityStatus$0$RcsFeature$RcsFeatureBinder() {
            return Integer.valueOf(this.mReference.queryCapabilityStatus().mCapabilities);
        }

        @Override // android.telephony.ims.aidl.IImsRcsFeature
        public void addCapabilityCallback(final IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException {
            executeMethodAsync(new Runnable() { // from class: android.telephony.ims.feature.-$$Lambda$RcsFeature$RcsFeatureBinder$FP3fQsjEKVwpq4qlvcr28BKvPZ8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$addCapabilityCallback$1$RcsFeature$RcsFeatureBinder(iImsCapabilityCallback);
                }
            }, "addCapabilityCallback");
        }

        public /* synthetic */ void lambda$addCapabilityCallback$1$RcsFeature$RcsFeatureBinder(IImsCapabilityCallback iImsCapabilityCallback) {
            this.mReference.addCapabilityCallback(iImsCapabilityCallback);
        }

        public /* synthetic */ void lambda$removeCapabilityCallback$2$RcsFeature$RcsFeatureBinder(IImsCapabilityCallback iImsCapabilityCallback) {
            this.mReference.removeCapabilityCallback(iImsCapabilityCallback);
        }

        @Override // android.telephony.ims.aidl.IImsRcsFeature
        public void removeCapabilityCallback(final IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException {
            executeMethodAsync(new Runnable() { // from class: android.telephony.ims.feature.-$$Lambda$RcsFeature$RcsFeatureBinder$xfOc6cnT5pzpCqMa06Osbyq4DSg
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$removeCapabilityCallback$2$RcsFeature$RcsFeatureBinder(iImsCapabilityCallback);
                }
            }, "removeCapabilityCallback");
        }

        @Override // android.telephony.ims.aidl.IImsRcsFeature
        public void changeCapabilitiesConfiguration(final CapabilityChangeRequest capabilityChangeRequest, final IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException {
            executeMethodAsync(new Runnable() { // from class: android.telephony.ims.feature.-$$Lambda$RcsFeature$RcsFeatureBinder$KzhM6fJ3lOKAUZU1vw70hi3DCU4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$changeCapabilitiesConfiguration$3$RcsFeature$RcsFeatureBinder(capabilityChangeRequest, iImsCapabilityCallback);
                }
            }, "changeCapabilitiesConfiguration");
        }

        public /* synthetic */ void lambda$changeCapabilitiesConfiguration$3$RcsFeature$RcsFeatureBinder(CapabilityChangeRequest capabilityChangeRequest, IImsCapabilityCallback iImsCapabilityCallback) {
            this.mReference.requestChangeEnabledCapabilities(capabilityChangeRequest, iImsCapabilityCallback);
        }

        public /* synthetic */ void lambda$queryCapabilityConfiguration$4$RcsFeature$RcsFeatureBinder(int i, int i2, IImsCapabilityCallback iImsCapabilityCallback) {
            this.mReference.queryCapabilityConfigurationInternal(i, i2, iImsCapabilityCallback);
        }

        @Override // android.telephony.ims.aidl.IImsRcsFeature
        public void queryCapabilityConfiguration(final int i, final int i2, final IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException {
            executeMethodAsync(new Runnable() { // from class: android.telephony.ims.feature.-$$Lambda$RcsFeature$RcsFeatureBinder$MVZfrdsQjdvMg2JJ3V1ZgyNoaXs
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$queryCapabilityConfiguration$4$RcsFeature$RcsFeatureBinder(i, i2, iImsCapabilityCallback);
                }
            }, "queryCapabilityConfiguration");
        }

        @Override // android.telephony.ims.aidl.IImsRcsFeature
        public int getFeatureState() throws RemoteException {
            final RcsFeature rcsFeature = this.mReference;
            Objects.requireNonNull(rcsFeature);
            return ((Integer) executeMethodAsyncForResult(new Supplier() { // from class: android.telephony.ims.feature.-$$Lambda$45UAFJHVNK8x4IbCIjJCvNdDYHU
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(rcsFeature.getFeatureState());
                }
            }, "getFeatureState")).intValue();
        }

        public /* synthetic */ void lambda$requestCapabilities$5$RcsFeature$RcsFeatureBinder(List list, int i) {
            this.mReference.getPresenceExchangeInternal().requestCapabilities(list, i);
        }

        @Override // android.telephony.ims.aidl.IImsRcsFeature
        public void requestCapabilities(final List<Uri> list, final int i) throws RemoteException {
            executeMethodAsync(new Runnable() { // from class: android.telephony.ims.feature.-$$Lambda$RcsFeature$RcsFeatureBinder$dDflkKfpW9nVgt1b4_Uw-_AF8m8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$requestCapabilities$5$RcsFeature$RcsFeatureBinder(list, i);
                }
            }, "requestCapabilities");
        }

        public /* synthetic */ void lambda$updateCapabilities$6$RcsFeature$RcsFeatureBinder(RcsContactUceCapability rcsContactUceCapability, int i) {
            this.mReference.getPresenceExchangeInternal().updateCapabilities(rcsContactUceCapability, i);
        }

        @Override // android.telephony.ims.aidl.IImsRcsFeature
        public void updateCapabilities(final RcsContactUceCapability rcsContactUceCapability, final int i) throws RemoteException {
            executeMethodAsync(new Runnable() { // from class: android.telephony.ims.feature.-$$Lambda$RcsFeature$RcsFeatureBinder$mMh1klMwhxUYl0X2ql2T5uAS8oY
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$updateCapabilities$6$RcsFeature$RcsFeatureBinder(rcsContactUceCapability, i);
                }
            }, "updateCapabilities");
        }

        public /* synthetic */ void lambda$sendCapabilityRequest$7$RcsFeature$RcsFeatureBinder(Uri uri, RcsContactUceCapability rcsContactUceCapability, int i) {
            this.mReference.getOptionsExchangeInternal().sendCapabilityRequest(uri, rcsContactUceCapability, i);
        }

        @Override // android.telephony.ims.aidl.IImsRcsFeature
        public void sendCapabilityRequest(final Uri uri, final RcsContactUceCapability rcsContactUceCapability, final int i) throws RemoteException {
            executeMethodAsync(new Runnable() { // from class: android.telephony.ims.feature.-$$Lambda$RcsFeature$RcsFeatureBinder$KBfbIAfuyQhgeV5OXetqYAOMpDE
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$sendCapabilityRequest$7$RcsFeature$RcsFeatureBinder(uri, rcsContactUceCapability, i);
                }
            }, "sendCapabilityRequest");
        }

        public /* synthetic */ void lambda$respondToCapabilityRequest$8$RcsFeature$RcsFeatureBinder(String str, RcsContactUceCapability rcsContactUceCapability, int i) {
            this.mReference.getOptionsExchangeInternal().respondToCapabilityRequest(str, rcsContactUceCapability, i);
        }

        @Override // android.telephony.ims.aidl.IImsRcsFeature
        public void respondToCapabilityRequest(final String str, final RcsContactUceCapability rcsContactUceCapability, final int i) throws RemoteException {
            executeMethodAsync(new Runnable() { // from class: android.telephony.ims.feature.-$$Lambda$RcsFeature$RcsFeatureBinder$uINaboXBbmj01kSBzEjvFkkEyrQ
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$respondToCapabilityRequest$8$RcsFeature$RcsFeatureBinder(str, rcsContactUceCapability, i);
                }
            }, "respondToCapabilityRequest");
        }

        public /* synthetic */ void lambda$respondToCapabilityRequestWithError$9$RcsFeature$RcsFeatureBinder(Uri uri, int i, String str, int i2) {
            this.mReference.getOptionsExchangeInternal().respondToCapabilityRequestWithError(uri, i, str, i2);
        }

        @Override // android.telephony.ims.aidl.IImsRcsFeature
        public void respondToCapabilityRequestWithError(final Uri uri, final int i, final String str, final int i2) throws RemoteException {
            executeMethodAsync(new Runnable() { // from class: android.telephony.ims.feature.-$$Lambda$RcsFeature$RcsFeatureBinder$KfGHlTIPDjp_wBV1DAurP8YpIZw
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$respondToCapabilityRequestWithError$9$RcsFeature$RcsFeatureBinder(uri, i, str, i2);
                }
            }, "respondToCapabilityRequestWithError");
        }

        private void executeMethodAsync(final Runnable runnable, String str) throws RemoteException {
            try {
                CompletableFuture.runAsync(new Runnable() { // from class: android.telephony.ims.feature.-$$Lambda$RcsFeature$RcsFeatureBinder$BKNRXehVxeScxlIdApDhMRQovpo
                    @Override // java.lang.Runnable
                    public final void run() {
                        TelephonyUtils.runWithCleanCallingIdentity(runnable);
                    }
                }, this.mExecutor).join();
            } catch (CancellationException | CompletionException e) {
                Log.w(RcsFeature.LOG_TAG, "RcsFeatureBinder - " + str + " exception: " + e.getMessage());
                throw new RemoteException(e.getMessage());
            }
        }

        private <T> T executeMethodAsyncForResult(final Supplier<T> supplier, String str) throws RemoteException {
            try {
                return (T) CompletableFuture.supplyAsync(new Supplier() { // from class: android.telephony.ims.feature.-$$Lambda$RcsFeature$RcsFeatureBinder$Ca-Lrg0AjuEm0MywLfQlW_OLbac
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return TelephonyUtils.runWithCleanCallingIdentity(supplier);
                    }
                }, this.mExecutor).get();
            } catch (InterruptedException | ExecutionException e) {
                Log.w(RcsFeature.LOG_TAG, "RcsFeatureBinder - " + str + " exception: " + e.getMessage());
                throw new RemoteException(e.getMessage());
            }
        }
    }

    public static class RcsImsCapabilities extends ImsFeature.Capabilities {
        public static final int CAPABILITY_TYPE_NONE = 0;
        public static final int CAPABILITY_TYPE_OPTIONS_UCE = 1;
        public static final int CAPABILITY_TYPE_PRESENCE_UCE = 2;

        @Retention(RetentionPolicy.SOURCE)
        public @interface RcsImsCapabilityFlag {
        }

        public RcsImsCapabilities(int i) {
            super(i);
        }

        private RcsImsCapabilities(ImsFeature.Capabilities capabilities) {
            super(capabilities.getMask());
        }

        @Override // android.telephony.ims.feature.ImsFeature.Capabilities
        public void addCapabilities(int i) {
            super.addCapabilities(i);
        }

        @Override // android.telephony.ims.feature.ImsFeature.Capabilities
        public void removeCapabilities(int i) {
            super.removeCapabilities(i);
        }

        @Override // android.telephony.ims.feature.ImsFeature.Capabilities
        public boolean isCapable(int i) {
            return super.isCapable(i);
        }
    }

    public RcsFeature() {
        this.mImsRcsBinder = new RcsFeatureBinder(this, new Executor() { // from class: android.telephony.ims.feature.-$$Lambda$_14QHG018Z6p13d3hzJuGTWnNeo
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                runnable.run();
            }
        });
    }

    public RcsFeature(Executor executor) {
        if (executor == null) {
            throw new IllegalArgumentException("executor can not be null.");
        }
        this.mImsRcsBinder = new RcsFeatureBinder(this, executor);
    }

    @Override // android.telephony.ims.feature.ImsFeature
    public final RcsImsCapabilities queryCapabilityStatus() {
        return new RcsImsCapabilities(super.queryCapabilityStatus());
    }

    public final void notifyCapabilitiesStatusChanged(RcsImsCapabilities rcsImsCapabilities) {
        if (rcsImsCapabilities == null) {
            throw new IllegalArgumentException("RcsImsCapabilities must be non-null!");
        }
        super.notifyCapabilitiesStatusChanged((ImsFeature.Capabilities) rcsImsCapabilities);
    }

    public RcsSipOptionsImplBase getOptionsExchangeImpl() {
        return new RcsSipOptionsImplBase();
    }

    public RcsPresenceExchangeImplBase getPresenceExchangeImpl() {
        return new RcsPresenceExchangeImplBase();
    }

    @Override // android.telephony.ims.feature.ImsFeature
    public final IImsRcsFeature getBinder() {
        return this.mImsRcsBinder;
    }

    public IRcsFeatureListener getListener() {
        IRcsFeatureListener iRcsFeatureListener;
        synchronized (this.mLock) {
            iRcsFeatureListener = this.mListenerBinder;
        }
        return iRcsFeatureListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setListener(IRcsFeatureListener iRcsFeatureListener) {
        synchronized (this.mLock) {
            this.mListenerBinder = iRcsFeatureListener;
            if (iRcsFeatureListener != null) {
                onFeatureReady();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RcsPresenceExchangeImplBase getPresenceExchangeInternal() {
        RcsPresenceExchangeImplBase rcsPresenceExchangeImplBase;
        synchronized (this.mLock) {
            if (this.mPresExchange == null) {
                RcsPresenceExchangeImplBase presenceExchangeImpl = getPresenceExchangeImpl();
                this.mPresExchange = presenceExchangeImpl;
                presenceExchangeImpl.initialize(this);
            }
            rcsPresenceExchangeImplBase = this.mPresExchange;
        }
        return rcsPresenceExchangeImplBase;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RcsSipOptionsImplBase getOptionsExchangeInternal() {
        RcsSipOptionsImplBase rcsSipOptionsImplBase;
        synchronized (this.mLock) {
            if (this.mSipOptions == null) {
                RcsSipOptionsImplBase optionsExchangeImpl = getOptionsExchangeImpl();
                this.mSipOptions = optionsExchangeImpl;
                optionsExchangeImpl.initialize(this);
            }
            rcsSipOptionsImplBase = this.mSipOptions;
        }
        return rcsSipOptionsImplBase;
    }
}
