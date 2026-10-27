package com.android.services.telephony.rcs;

import android.content.Context;
import android.net.Uri;
import android.telephony.ims.ImsReasonInfo;
import android.telephony.ims.aidl.IImsCapabilityCallback;
import android.telephony.ims.aidl.IImsRegistrationCallback;
import android.util.ArrayMap;
import android.util.Log;
import com.android.ims.FeatureConnector;
import com.android.ims.IFeatureConnector;
import com.android.ims.ImsException;
import com.android.ims.RcsFeatureManager;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.telephony.IndentingPrintWriter;
import com.android.internal.telephony.imsphone.ImsRegistrationCallbackHelper;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public class RcsFeatureController {
    private final Context mContext;
    private FeatureConnector<RcsFeatureManager> mFeatureConnector;
    private FeatureConnector.Listener<RcsFeatureManager> mFeatureConnectorListener;
    private FeatureConnectorFactory<RcsFeatureManager> mFeatureFactory;
    private RcsFeatureManager mFeatureManager;
    private final Map<Class<?>, Feature> mFeatures;
    private final ImsRegistrationCallbackHelper mImsRcsRegistrationHelper;
    private final Object mLock;
    private ImsRegistrationCallbackHelper.ImsRegistrationUpdate mRcsRegistrationUpdate;
    private RegistrationHelperFactory mRegistrationHelperFactory;
    private final int mSlotId;

    public interface Feature {
        void onAssociatedSubscriptionUpdated(int i);

        void onDestroy();

        void onRcsConnected(RcsFeatureManager rcsFeatureManager);

        void onRcsDisconnected();
    }

    @VisibleForTesting
    public interface FeatureConnectorFactory<T extends IFeatureConnector> {
        FeatureConnector<T> create(Context context, int i, FeatureConnector.Listener<T> listener, Executor executor, String str);
    }

    @VisibleForTesting
    public interface RegistrationHelperFactory {
        ImsRegistrationCallbackHelper create(ImsRegistrationCallbackHelper.ImsRegistrationUpdate imsRegistrationUpdate, Executor executor);
    }

    public RcsFeatureController(Context context, int i) {
        this.mFeatureFactory = $$Lambda$nxDW_ESATl7VlrjUJWNz_PbiGKw.INSTANCE;
        this.mRegistrationHelperFactory = $$Lambda$b6iWFcKy2djyZUHL1tLUOq8ve8U.INSTANCE;
        this.mFeatures = new ArrayMap();
        this.mLock = new Object();
        this.mFeatureConnectorListener = new FeatureConnector.Listener<RcsFeatureManager>() { // from class: com.android.services.telephony.rcs.RcsFeatureController.1
            /* JADX INFO: renamed from: getFeatureManager, reason: merged with bridge method [inline-methods] */
            public RcsFeatureManager m2getFeatureManager() {
                return new RcsFeatureManager(RcsFeatureController.this.mContext, RcsFeatureController.this.mSlotId);
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.android.ims.ImsException */
            public void connectionReady(RcsFeatureManager rcsFeatureManager) throws ImsException {
                if (rcsFeatureManager == null) {
                    RcsFeatureController.this.logw("connectionReady returned null RcsFeatureManager");
                    return;
                }
                try {
                    RcsFeatureController.this.setupConnectionToService(rcsFeatureManager);
                    RcsFeatureController.this.updateConnectionStatus(rcsFeatureManager);
                } catch (android.telephony.ims.ImsException e) {
                    throw new ImsException(e.getMessage(), 106);
                }
            }

            public void connectionUnavailable() {
                RcsFeatureController.this.removeConnectionToService();
                RcsFeatureController.this.updateConnectionStatus(null);
            }
        };
        ImsRegistrationCallbackHelper.ImsRegistrationUpdate imsRegistrationUpdate = new ImsRegistrationCallbackHelper.ImsRegistrationUpdate(this) { // from class: com.android.services.telephony.rcs.RcsFeatureController.2
            public void handleImsRegistered(int i2) {
            }

            public void handleImsRegistering(int i2) {
            }

            public void handleImsSubscriberAssociatedUriChanged(Uri[] uriArr) {
            }

            public void handleImsUnregistered(ImsReasonInfo imsReasonInfo) {
            }
        };
        this.mRcsRegistrationUpdate = imsRegistrationUpdate;
        this.mContext = context;
        this.mSlotId = i;
        this.mImsRcsRegistrationHelper = this.mRegistrationHelperFactory.create(imsRegistrationUpdate, context.getMainExecutor());
    }

    @VisibleForTesting
    public RcsFeatureController(Context context, int i, RegistrationHelperFactory registrationHelperFactory) {
        this.mFeatureFactory = $$Lambda$nxDW_ESATl7VlrjUJWNz_PbiGKw.INSTANCE;
        this.mRegistrationHelperFactory = $$Lambda$b6iWFcKy2djyZUHL1tLUOq8ve8U.INSTANCE;
        this.mFeatures = new ArrayMap();
        this.mLock = new Object();
        this.mFeatureConnectorListener = new FeatureConnector.Listener<RcsFeatureManager>() { // from class: com.android.services.telephony.rcs.RcsFeatureController.1
            /* JADX INFO: renamed from: getFeatureManager, reason: merged with bridge method [inline-methods] */
            public RcsFeatureManager m2getFeatureManager() {
                return new RcsFeatureManager(RcsFeatureController.this.mContext, RcsFeatureController.this.mSlotId);
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.android.ims.ImsException */
            public void connectionReady(RcsFeatureManager rcsFeatureManager) throws ImsException {
                if (rcsFeatureManager == null) {
                    RcsFeatureController.this.logw("connectionReady returned null RcsFeatureManager");
                    return;
                }
                try {
                    RcsFeatureController.this.setupConnectionToService(rcsFeatureManager);
                    RcsFeatureController.this.updateConnectionStatus(rcsFeatureManager);
                } catch (android.telephony.ims.ImsException e) {
                    throw new ImsException(e.getMessage(), 106);
                }
            }

            public void connectionUnavailable() {
                RcsFeatureController.this.removeConnectionToService();
                RcsFeatureController.this.updateConnectionStatus(null);
            }
        };
        ImsRegistrationCallbackHelper.ImsRegistrationUpdate imsRegistrationUpdate = new ImsRegistrationCallbackHelper.ImsRegistrationUpdate(this) { // from class: com.android.services.telephony.rcs.RcsFeatureController.2
            public void handleImsRegistered(int i2) {
            }

            public void handleImsRegistering(int i2) {
            }

            public void handleImsSubscriberAssociatedUriChanged(Uri[] uriArr) {
            }

            public void handleImsUnregistered(ImsReasonInfo imsReasonInfo) {
            }
        };
        this.mRcsRegistrationUpdate = imsRegistrationUpdate;
        this.mContext = context;
        this.mSlotId = i;
        this.mRegistrationHelperFactory = registrationHelperFactory;
        this.mImsRcsRegistrationHelper = registrationHelperFactory.create(imsRegistrationUpdate, context.getMainExecutor());
    }

    public void connect() {
        synchronized (this.mLock) {
            if (this.mFeatureConnector != null) {
                return;
            }
            FeatureConnector<T> featureConnectorCreate = this.mFeatureFactory.create(this.mContext, this.mSlotId, this.mFeatureConnectorListener, this.mContext.getMainExecutor(), "RcsFeatureController");
            this.mFeatureConnector = featureConnectorCreate;
            featureConnectorCreate.connect();
        }
    }

    public <T extends Feature> void addFeature(T t, Class<T> cls) {
        synchronized (this.mLock) {
            this.mFeatures.put(cls, t);
        }
        RcsFeatureManager featureManager = getFeatureManager();
        if (featureManager != null) {
            t.onRcsConnected(featureManager);
        } else {
            t.onRcsDisconnected();
        }
    }

    public <T> T getFeature(Class<T> cls) {
        T t;
        synchronized (this.mLock) {
            t = (T) this.mFeatures.get(cls);
        }
        return t;
    }

    public <T> void removeFeature(Class<T> cls) {
        synchronized (this.mLock) {
            this.mFeatures.remove(cls).onDestroy();
        }
    }

    public boolean hasActiveFeatures() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mFeatures.size() > 0;
        }
        return z;
    }

    public void updateAssociatedSubscription(int i) {
        RcsFeatureManager featureManager = getFeatureManager();
        if (featureManager != null) {
            try {
                featureManager.updateCapabilities();
            } catch (android.telephony.ims.ImsException e) {
                Log.w("RcsFeatureController", "associatedSubscriptionChanged failed:" + e);
            }
        }
        synchronized (this.mLock) {
            Iterator<Feature> it = this.mFeatures.values().iterator();
            while (it.hasNext()) {
                it.next().onAssociatedSubscriptionUpdated(i);
            }
        }
    }

    public void destroy() {
        synchronized (this.mLock) {
            Log.i("RcsFeatureController", "destroy: slotId=" + this.mSlotId);
            if (this.mFeatureConnector != null) {
                this.mFeatureConnector.disconnect();
            }
            for (Feature feature : this.mFeatures.values()) {
                feature.onRcsDisconnected();
                feature.onDestroy();
            }
            this.mFeatures.clear();
        }
    }

    @VisibleForTesting
    public void setFeatureConnectorFactory(FeatureConnectorFactory featureConnectorFactory) {
        this.mFeatureFactory = featureConnectorFactory;
    }

    public void registerImsRegistrationCallback(int i, IImsRegistrationCallback iImsRegistrationCallback) throws android.telephony.ims.ImsException {
        RcsFeatureManager featureManager = getFeatureManager();
        if (featureManager == null) {
            throw new android.telephony.ims.ImsException("Service is not available", 1);
        }
        featureManager.registerImsRegistrationCallback(i, iImsRegistrationCallback);
    }

    public void unregisterImsRegistrationCallback(int i, IImsRegistrationCallback iImsRegistrationCallback) {
        RcsFeatureManager featureManager = getFeatureManager();
        if (featureManager != null) {
            featureManager.unregisterImsRegistrationCallback(i, iImsRegistrationCallback);
        }
    }

    public void registerRcsAvailabilityCallback(int i, IImsCapabilityCallback iImsCapabilityCallback) throws android.telephony.ims.ImsException {
        RcsFeatureManager featureManager = getFeatureManager();
        if (featureManager == null) {
            throw new android.telephony.ims.ImsException("Service is not available", 1);
        }
        featureManager.registerRcsAvailabilityCallback(i, iImsCapabilityCallback);
    }

    public void unregisterRcsAvailabilityCallback(int i, IImsCapabilityCallback iImsCapabilityCallback) {
        RcsFeatureManager featureManager = getFeatureManager();
        if (featureManager != null) {
            featureManager.unregisterRcsAvailabilityCallback(i, iImsCapabilityCallback);
        }
    }

    public boolean isCapable(int i, int i2) throws android.telephony.ims.ImsException {
        RcsFeatureManager featureManager = getFeatureManager();
        if (featureManager == null) {
            throw new android.telephony.ims.ImsException("Service is not available", 1);
        }
        return featureManager.isCapable(i, i2);
    }

    public boolean isAvailable(int i) throws android.telephony.ims.ImsException {
        RcsFeatureManager featureManager = getFeatureManager();
        if (featureManager == null) {
            throw new android.telephony.ims.ImsException("Service is not available", 1);
        }
        return featureManager.isAvailable(i);
    }

    public void getRegistrationTech(Consumer<Integer> consumer) {
        RcsFeatureManager featureManager = getFeatureManager();
        if (featureManager != null) {
            featureManager.getImsRegistrationTech(consumer);
        }
        consumer.accept(-1);
    }

    public void getRegistrationState(Consumer<Integer> consumer) {
        consumer.accept(Integer.valueOf(this.mImsRcsRegistrationHelper.getImsRegistrationState()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupConnectionToService(RcsFeatureManager rcsFeatureManager) throws android.telephony.ims.ImsException {
        rcsFeatureManager.openConnection();
        rcsFeatureManager.updateCapabilities();
        rcsFeatureManager.registerImsRegistrationCallback(this.mImsRcsRegistrationHelper.getCallbackBinder());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeConnectionToService() {
        RcsFeatureManager featureManager = getFeatureManager();
        if (featureManager != null) {
            featureManager.unregisterImsRegistrationCallback(this.mImsRcsRegistrationHelper.getCallbackBinder());
            featureManager.releaseConnection();
        }
        this.mImsRcsRegistrationHelper.reset();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateConnectionStatus(RcsFeatureManager rcsFeatureManager) {
        synchronized (this.mLock) {
            this.mFeatureManager = rcsFeatureManager;
            if (rcsFeatureManager != null) {
                Iterator<Feature> it = this.mFeatures.values().iterator();
                while (it.hasNext()) {
                    it.next().onRcsConnected(rcsFeatureManager);
                }
            } else {
                Iterator<Feature> it2 = this.mFeatures.values().iterator();
                while (it2.hasNext()) {
                    it2.next().onRcsDisconnected();
                }
            }
        }
    }

    private RcsFeatureManager getFeatureManager() {
        RcsFeatureManager rcsFeatureManager;
        synchronized (this.mLock) {
            rcsFeatureManager = this.mFeatureManager;
        }
        return rcsFeatureManager;
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        IndentingPrintWriter indentingPrintWriter = new IndentingPrintWriter(printWriter, "  ");
        indentingPrintWriter.print("slotId=");
        indentingPrintWriter.println(this.mSlotId);
        indentingPrintWriter.print("RegistrationState=");
        indentingPrintWriter.println(this.mImsRcsRegistrationHelper.getImsRegistrationState());
        indentingPrintWriter.print("connected=");
        synchronized (this.mLock) {
            indentingPrintWriter.println(this.mFeatureManager != null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logw(String str) {
        StringBuilder logPrefix = getLogPrefix();
        logPrefix.append(str);
        Log.w("RcsFeatureController", logPrefix.toString());
    }

    private StringBuilder getLogPrefix() {
        StringBuilder sb = new StringBuilder("[");
        sb.append(this.mSlotId);
        sb.append("] ");
        return sb;
    }
}
