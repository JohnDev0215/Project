package android.telephony.ims;

import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import android.telephony.TelephonyFrameworkInitializer;
import android.telephony.ims.RegistrationManager;
import android.telephony.ims.aidl.IImsCapabilityCallback;
import android.telephony.ims.aidl.IImsRcsController;
import android.telephony.ims.feature.RcsFeature;
import android.util.Log;
import com.android.internal.telephony.IIntegerConsumer;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes3.dex */
public class ImsRcsManager {
    public static final String ACTION_SHOW_CAPABILITY_DISCOVERY_OPT_IN = "android.telephony.ims.action.SHOW_CAPABILITY_DISCOVERY_OPT_IN";
    private static final String TAG = "ImsRcsManager";
    private final Context mContext;
    private final int mSubId;

    public static class AvailabilityCallback {
        private final CapabilityBinder mBinder = new CapabilityBinder(this);

        public void onAvailabilityChanged(RcsFeature.RcsImsCapabilities rcsImsCapabilities) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        static class CapabilityBinder extends IImsCapabilityCallback.Stub {
            private Executor mExecutor;
            private final AvailabilityCallback mLocalCallback;

            @Override // android.telephony.ims.aidl.IImsCapabilityCallback
            public void onChangeCapabilityConfigurationError(int i, int i2, int i3) {
            }

            @Override // android.telephony.ims.aidl.IImsCapabilityCallback
            public void onQueryCapabilityConfiguration(int i, int i2, boolean z) {
            }

            CapabilityBinder(AvailabilityCallback availabilityCallback) {
                this.mLocalCallback = availabilityCallback;
            }

            @Override // android.telephony.ims.aidl.IImsCapabilityCallback
            public void onCapabilitiesStatusChanged(final int i) {
                if (this.mLocalCallback == null) {
                    return;
                }
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    this.mExecutor.execute(new Runnable() { // from class: android.telephony.ims.-$$Lambda$ImsRcsManager$AvailabilityCallback$CapabilityBinder$_nqQBveAGKDISpzPvAsLzckwvV4
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onCapabilitiesStatusChanged$0$ImsRcsManager$AvailabilityCallback$CapabilityBinder(i);
                        }
                    });
                } finally {
                    restoreCallingIdentity(jClearCallingIdentity);
                }
            }

            public /* synthetic */ void lambda$onCapabilitiesStatusChanged$0$ImsRcsManager$AvailabilityCallback$CapabilityBinder(int i) {
                this.mLocalCallback.onAvailabilityChanged(new RcsFeature.RcsImsCapabilities(i));
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setExecutor(Executor executor) {
                this.mExecutor = executor;
            }
        }

        public final IImsCapabilityCallback getBinder() {
            return this.mBinder;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setExecutor(Executor executor) {
            this.mBinder.setExecutor(executor);
        }
    }

    public ImsRcsManager(Context context, int i) {
        this.mSubId = i;
        this.mContext = context;
    }

    public RcsUceAdapter getUceAdapter() {
        return new RcsUceAdapter(this.mContext, this.mSubId);
    }

    public void registerImsRegistrationCallback(Executor executor, RegistrationManager.RegistrationCallback registrationCallback) throws ImsException {
        if (registrationCallback == null) {
            throw new IllegalArgumentException("Must include a non-null RegistrationCallback.");
        }
        if (executor == null) {
            throw new IllegalArgumentException("Must include a non-null Executor.");
        }
        IImsRcsController iImsRcsController = getIImsRcsController();
        if (iImsRcsController == null) {
            Log.e(TAG, "Register registration callback: IImsRcsController is null");
            throw new ImsException("Cannot find remote IMS service", 1);
        }
        registrationCallback.setExecutor(executor);
        try {
            iImsRcsController.registerImsRegistrationCallback(this.mSubId, registrationCallback.getBinder());
        } catch (RemoteException | IllegalStateException e) {
            throw new ImsException(e.getMessage(), 1);
        }
    }

    public void unregisterImsRegistrationCallback(RegistrationManager.RegistrationCallback registrationCallback) {
        if (registrationCallback == null) {
            throw new IllegalArgumentException("Must include a non-null RegistrationCallback.");
        }
        IImsRcsController iImsRcsController = getIImsRcsController();
        if (iImsRcsController == null) {
            Log.e(TAG, "Unregister registration callback: IImsRcsController is null");
            throw new IllegalStateException("Cannot find remote IMS service");
        }
        try {
            iImsRcsController.unregisterImsRegistrationCallback(this.mSubId, registrationCallback.getBinder());
        } catch (RemoteException e) {
            throw e.rethrowAsRuntimeException();
        }
    }

    public void getRegistrationState(Executor executor, Consumer<Integer> consumer) {
        if (consumer == null) {
            throw new IllegalArgumentException("Must include a non-null stateCallback.");
        }
        if (executor == null) {
            throw new IllegalArgumentException("Must include a non-null Executor.");
        }
        IImsRcsController iImsRcsController = getIImsRcsController();
        if (iImsRcsController == null) {
            Log.e(TAG, "Get registration state error: IImsRcsController is null");
            throw new IllegalStateException("Cannot find remote IMS service");
        }
        try {
            iImsRcsController.getImsRcsRegistrationState(this.mSubId, new AnonymousClass1(executor, consumer));
        } catch (RemoteException e) {
            throw e.rethrowAsRuntimeException();
        }
    }

    /* JADX INFO: renamed from: android.telephony.ims.ImsRcsManager$1, reason: invalid class name */
    class AnonymousClass1 extends IIntegerConsumer.Stub {
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ Consumer val$stateCallback;

        AnonymousClass1(Executor executor, Consumer consumer) {
            this.val$executor = executor;
            this.val$stateCallback = consumer;
        }

        @Override // com.android.internal.telephony.IIntegerConsumer
        public void accept(final int i) {
            Executor executor = this.val$executor;
            final Consumer consumer = this.val$stateCallback;
            executor.execute(new Runnable() { // from class: android.telephony.ims.-$$Lambda$ImsRcsManager$1$a-OFPU-uP9aLQpIZrRCK7hfz34w
                @Override // java.lang.Runnable
                public final void run() {
                    consumer.accept(Integer.valueOf(i));
                }
            });
        }
    }

    public void getRegistrationTransportType(Executor executor, Consumer<Integer> consumer) {
        if (consumer == null) {
            throw new IllegalArgumentException("Must include a non-null transportTypeCallback.");
        }
        if (executor == null) {
            throw new IllegalArgumentException("Must include a non-null Executor.");
        }
        IImsRcsController iImsRcsController = getIImsRcsController();
        if (iImsRcsController == null) {
            Log.e(TAG, "Get registration transport type error: IImsRcsController is null");
            throw new IllegalStateException("Cannot find remote IMS service");
        }
        try {
            iImsRcsController.getImsRcsRegistrationTransportType(this.mSubId, new AnonymousClass2(executor, consumer));
        } catch (RemoteException e) {
            throw e.rethrowAsRuntimeException();
        }
    }

    /* JADX INFO: renamed from: android.telephony.ims.ImsRcsManager$2, reason: invalid class name */
    class AnonymousClass2 extends IIntegerConsumer.Stub {
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ Consumer val$transportTypeCallback;

        AnonymousClass2(Executor executor, Consumer consumer) {
            this.val$executor = executor;
            this.val$transportTypeCallback = consumer;
        }

        @Override // com.android.internal.telephony.IIntegerConsumer
        public void accept(final int i) {
            Executor executor = this.val$executor;
            final Consumer consumer = this.val$transportTypeCallback;
            executor.execute(new Runnable() { // from class: android.telephony.ims.-$$Lambda$ImsRcsManager$2$PZc53i-lJ03kVP81Rd9ZlbGGXXs
                @Override // java.lang.Runnable
                public final void run() {
                    consumer.accept(Integer.valueOf(i));
                }
            });
        }
    }

    public void registerRcsAvailabilityCallback(Executor executor, AvailabilityCallback availabilityCallback) throws ImsException {
        if (availabilityCallback == null) {
            throw new IllegalArgumentException("Must include a non-null AvailabilityCallback.");
        }
        if (executor == null) {
            throw new IllegalArgumentException("Must include a non-null Executor.");
        }
        IImsRcsController iImsRcsController = getIImsRcsController();
        if (iImsRcsController != null) {
            availabilityCallback.setExecutor(executor);
            try {
                iImsRcsController.registerRcsAvailabilityCallback(this.mSubId, availabilityCallback.getBinder());
                return;
            } catch (RemoteException e) {
                Log.e(TAG, "Error calling IImsRcsController#registerRcsAvailabilityCallback", e);
                throw new ImsException("Remote IMS Service is not available", 1);
            }
        }
        Log.e(TAG, "Register availability callback: IImsRcsController is null");
        throw new ImsException("Cannot find remote IMS service", 1);
    }

    public void unregisterRcsAvailabilityCallback(AvailabilityCallback availabilityCallback) throws ImsException {
        if (availabilityCallback == null) {
            throw new IllegalArgumentException("Must include a non-null AvailabilityCallback.");
        }
        IImsRcsController iImsRcsController = getIImsRcsController();
        if (iImsRcsController == null) {
            Log.e(TAG, "Unregister availability callback: IImsRcsController is null");
            throw new ImsException("Cannot find remote IMS service", 1);
        }
        try {
            iImsRcsController.unregisterRcsAvailabilityCallback(this.mSubId, availabilityCallback.getBinder());
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling IImsRcsController#unregisterRcsAvailabilityCallback", e);
            throw new ImsException("Remote IMS Service is not available", 1);
        }
    }

    public boolean isCapable(int i, int i2) throws ImsException {
        IImsRcsController iImsRcsController = getIImsRcsController();
        if (iImsRcsController == null) {
            Log.e(TAG, "isCapable: IImsRcsController is null");
            throw new ImsException("Cannot find remote IMS service", 1);
        }
        try {
            return iImsRcsController.isCapable(this.mSubId, i, i2);
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling IImsRcsController#isCapable", e);
            throw new ImsException("Remote IMS Service is not available", 1);
        }
    }

    public boolean isAvailable(int i) throws ImsException {
        IImsRcsController iImsRcsController = getIImsRcsController();
        if (iImsRcsController == null) {
            Log.e(TAG, "isAvailable: IImsRcsController is null");
            throw new ImsException("Cannot find remote IMS service", 1);
        }
        try {
            return iImsRcsController.isAvailable(this.mSubId, i);
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling IImsRcsController#isAvailable", e);
            throw new ImsException("Remote IMS Service is not available", 1);
        }
    }

    private IImsRcsController getIImsRcsController() {
        return IImsRcsController.Stub.asInterface(TelephonyFrameworkInitializer.getTelephonyServiceManager().getTelephonyImsServiceRegisterer().get());
    }
}
