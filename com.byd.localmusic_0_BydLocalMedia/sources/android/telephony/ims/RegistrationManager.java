package android.telephony.ims;

import android.net.Uri;
import android.os.Binder;
import android.telephony.ims.aidl.IImsRegistrationCallback;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes3.dex */
public interface RegistrationManager {
    public static final Map<Integer, Integer> IMS_REG_TO_ACCESS_TYPE_MAP = new HashMap<Integer, Integer>() { // from class: android.telephony.ims.RegistrationManager.1
        {
            put(-1, -1);
            put(0, 1);
            put(1, 2);
        }
    };
    public static final int REGISTRATION_STATE_NOT_REGISTERED = 0;
    public static final int REGISTRATION_STATE_REGISTERED = 2;
    public static final int REGISTRATION_STATE_REGISTERING = 1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ImsRegistrationState {
    }

    void getRegistrationState(Executor executor, Consumer<Integer> consumer);

    void getRegistrationTransportType(Executor executor, Consumer<Integer> consumer);

    void registerImsRegistrationCallback(Executor executor, RegistrationCallback registrationCallback) throws ImsException;

    void unregisterImsRegistrationCallback(RegistrationCallback registrationCallback);

    public static class RegistrationCallback {
        private final RegistrationBinder mBinder = new RegistrationBinder(this);

        public void onRegistered(int i) {
        }

        public void onRegistering(int i) {
        }

        public void onSubscriberAssociatedUriChanged(Uri[] uriArr) {
        }

        public void onTechnologyChangeFailed(int i, ImsReasonInfo imsReasonInfo) {
        }

        public void onUnregistered(ImsReasonInfo imsReasonInfo) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        static class RegistrationBinder extends IImsRegistrationCallback.Stub {
            private Executor mExecutor;
            private final RegistrationCallback mLocalCallback;

            RegistrationBinder(RegistrationCallback registrationCallback) {
                this.mLocalCallback = registrationCallback;
            }

            @Override // android.telephony.ims.aidl.IImsRegistrationCallback
            public void onRegistered(final int i) {
                if (this.mLocalCallback == null) {
                    return;
                }
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    this.mExecutor.execute(new Runnable() { // from class: android.telephony.ims.-$$Lambda$RegistrationManager$RegistrationCallback$RegistrationBinder$uTxkp6C02qJxic1W_dkZRCQ6aRw
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onRegistered$0$RegistrationManager$RegistrationCallback$RegistrationBinder(i);
                        }
                    });
                } finally {
                    restoreCallingIdentity(jClearCallingIdentity);
                }
            }

            public /* synthetic */ void lambda$onRegistered$0$RegistrationManager$RegistrationCallback$RegistrationBinder(int i) {
                this.mLocalCallback.onRegistered(getAccessType(i));
            }

            @Override // android.telephony.ims.aidl.IImsRegistrationCallback
            public void onRegistering(final int i) {
                if (this.mLocalCallback == null) {
                    return;
                }
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    this.mExecutor.execute(new Runnable() { // from class: android.telephony.ims.-$$Lambda$RegistrationManager$RegistrationCallback$RegistrationBinder$U5KsDZQk3N6Mv43G9MidRPHRmv8
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onRegistering$1$RegistrationManager$RegistrationCallback$RegistrationBinder(i);
                        }
                    });
                } finally {
                    restoreCallingIdentity(jClearCallingIdentity);
                }
            }

            public /* synthetic */ void lambda$onRegistering$1$RegistrationManager$RegistrationCallback$RegistrationBinder(int i) {
                this.mLocalCallback.onRegistering(getAccessType(i));
            }

            @Override // android.telephony.ims.aidl.IImsRegistrationCallback
            public void onDeregistered(final ImsReasonInfo imsReasonInfo) {
                if (this.mLocalCallback == null) {
                    return;
                }
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    this.mExecutor.execute(new Runnable() { // from class: android.telephony.ims.-$$Lambda$RegistrationManager$RegistrationCallback$RegistrationBinder$APeqso3VzZZ0eUf5slP1k5xoCME
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onDeregistered$2$RegistrationManager$RegistrationCallback$RegistrationBinder(imsReasonInfo);
                        }
                    });
                } finally {
                    restoreCallingIdentity(jClearCallingIdentity);
                }
            }

            public /* synthetic */ void lambda$onDeregistered$2$RegistrationManager$RegistrationCallback$RegistrationBinder(ImsReasonInfo imsReasonInfo) {
                this.mLocalCallback.onUnregistered(imsReasonInfo);
            }

            @Override // android.telephony.ims.aidl.IImsRegistrationCallback
            public void onTechnologyChangeFailed(final int i, final ImsReasonInfo imsReasonInfo) {
                if (this.mLocalCallback == null) {
                    return;
                }
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    this.mExecutor.execute(new Runnable() { // from class: android.telephony.ims.-$$Lambda$RegistrationManager$RegistrationCallback$RegistrationBinder$fgbmOxWK5ZyS5zNpLgTSXknOOJ4
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onTechnologyChangeFailed$3$RegistrationManager$RegistrationCallback$RegistrationBinder(i, imsReasonInfo);
                        }
                    });
                } finally {
                    restoreCallingIdentity(jClearCallingIdentity);
                }
            }

            public /* synthetic */ void lambda$onTechnologyChangeFailed$3$RegistrationManager$RegistrationCallback$RegistrationBinder(int i, ImsReasonInfo imsReasonInfo) {
                this.mLocalCallback.onTechnologyChangeFailed(getAccessType(i), imsReasonInfo);
            }

            @Override // android.telephony.ims.aidl.IImsRegistrationCallback
            public void onSubscriberAssociatedUriChanged(final Uri[] uriArr) {
                if (this.mLocalCallback == null) {
                    return;
                }
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    this.mExecutor.execute(new Runnable() { // from class: android.telephony.ims.-$$Lambda$RegistrationManager$RegistrationCallback$RegistrationBinder$DX_-dWIBwwX2oqDoRnq49RndG7s
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onSubscriberAssociatedUriChanged$4$RegistrationManager$RegistrationCallback$RegistrationBinder(uriArr);
                        }
                    });
                } finally {
                    restoreCallingIdentity(jClearCallingIdentity);
                }
            }

            public /* synthetic */ void lambda$onSubscriberAssociatedUriChanged$4$RegistrationManager$RegistrationCallback$RegistrationBinder(Uri[] uriArr) {
                this.mLocalCallback.onSubscriberAssociatedUriChanged(uriArr);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setExecutor(Executor executor) {
                this.mExecutor = executor;
            }

            private static int getAccessType(int i) {
                if (!RegistrationManager.IMS_REG_TO_ACCESS_TYPE_MAP.containsKey(Integer.valueOf(i))) {
                    Log.w("RegistrationManager", "RegistrationBinder - invalid regType returned: " + i);
                    return -1;
                }
                return RegistrationManager.IMS_REG_TO_ACCESS_TYPE_MAP.get(Integer.valueOf(i)).intValue();
            }
        }

        public final IImsRegistrationCallback getBinder() {
            return this.mBinder;
        }

        public void setExecutor(Executor executor) {
            this.mBinder.setExecutor(executor);
        }
    }
}
