package android.telephony.euicc;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import android.service.euicc.EuiccProfileInfo;
import android.telephony.TelephonyFrameworkInitializer;
import android.util.Log;
import com.android.internal.telephony.euicc.IAuthenticateServerCallback;
import com.android.internal.telephony.euicc.ICancelSessionCallback;
import com.android.internal.telephony.euicc.IDeleteProfileCallback;
import com.android.internal.telephony.euicc.IDisableProfileCallback;
import com.android.internal.telephony.euicc.IEuiccCardController;
import com.android.internal.telephony.euicc.IGetAllProfilesCallback;
import com.android.internal.telephony.euicc.IGetDefaultSmdpAddressCallback;
import com.android.internal.telephony.euicc.IGetEuiccChallengeCallback;
import com.android.internal.telephony.euicc.IGetEuiccInfo1Callback;
import com.android.internal.telephony.euicc.IGetEuiccInfo2Callback;
import com.android.internal.telephony.euicc.IGetProfileCallback;
import com.android.internal.telephony.euicc.IGetRulesAuthTableCallback;
import com.android.internal.telephony.euicc.IGetSmdsAddressCallback;
import com.android.internal.telephony.euicc.IListNotificationsCallback;
import com.android.internal.telephony.euicc.ILoadBoundProfilePackageCallback;
import com.android.internal.telephony.euicc.IPrepareDownloadCallback;
import com.android.internal.telephony.euicc.IRemoveNotificationFromListCallback;
import com.android.internal.telephony.euicc.IResetMemoryCallback;
import com.android.internal.telephony.euicc.IRetrieveNotificationCallback;
import com.android.internal.telephony.euicc.IRetrieveNotificationListCallback;
import com.android.internal.telephony.euicc.ISetDefaultSmdpAddressCallback;
import com.android.internal.telephony.euicc.ISetNicknameCallback;
import com.android.internal.telephony.euicc.ISwitchToProfileCallback;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
@SystemApi
public class EuiccCardManager {
    public static final int CANCEL_REASON_END_USER_REJECTED = 0;
    public static final int CANCEL_REASON_POSTPONED = 1;
    public static final int CANCEL_REASON_PPR_NOT_ALLOWED = 3;
    public static final int CANCEL_REASON_TIMEOUT = 2;
    public static final int RESET_OPTION_DELETE_FIELD_LOADED_TEST_PROFILES = 2;
    public static final int RESET_OPTION_DELETE_OPERATIONAL_PROFILES = 1;
    public static final int RESET_OPTION_RESET_DEFAULT_SMDP_ADDRESS = 4;
    public static final int RESULT_CALLER_NOT_ALLOWED = -3;
    public static final int RESULT_EUICC_NOT_FOUND = -2;
    public static final int RESULT_OK = 0;
    public static final int RESULT_UNKNOWN_ERROR = -1;
    private static final String TAG = "EuiccCardManager";
    private final Context mContext;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CancelReason {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ResetOption {
    }

    public interface ResultCallback<T> {
        void onComplete(int i, T t);
    }

    public EuiccCardManager(Context context) {
        this.mContext = context;
    }

    private IEuiccCardController getIEuiccCardController() {
        return IEuiccCardController.Stub.asInterface(TelephonyFrameworkInitializer.getTelephonyServiceManager().getEuiccCardControllerServiceRegisterer().get());
    }

    /* JADX INFO: renamed from: android.telephony.euicc.EuiccCardManager$1, reason: invalid class name */
    class AnonymousClass1 extends IGetAllProfilesCallback.Stub {
        final /* synthetic */ ResultCallback val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass1(Executor executor, ResultCallback resultCallback) {
            this.val$executor = executor;
            this.val$callback = resultCallback;
        }

        @Override // com.android.internal.telephony.euicc.IGetAllProfilesCallback
        public void onComplete(final int i, final EuiccProfileInfo[] euiccProfileInfoArr) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final ResultCallback resultCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.euicc.-$$Lambda$EuiccCardManager$1$h5quF-Aitbm0swElwGqCWv99na8
                    @Override // java.lang.Runnable
                    public final void run() {
                        resultCallback.onComplete(i, euiccProfileInfoArr);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    public void requestAllProfiles(String str, Executor executor, ResultCallback<EuiccProfileInfo[]> resultCallback) {
        try {
            getIEuiccCardController().getAllProfiles(this.mContext.getOpPackageName(), str, new AnonymousClass1(executor, resultCallback));
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling getAllProfiles", e);
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: renamed from: android.telephony.euicc.EuiccCardManager$2, reason: invalid class name */
    class AnonymousClass2 extends IGetProfileCallback.Stub {
        final /* synthetic */ ResultCallback val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass2(Executor executor, ResultCallback resultCallback) {
            this.val$executor = executor;
            this.val$callback = resultCallback;
        }

        @Override // com.android.internal.telephony.euicc.IGetProfileCallback
        public void onComplete(final int i, final EuiccProfileInfo euiccProfileInfo) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final ResultCallback resultCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.euicc.-$$Lambda$EuiccCardManager$2$El_n1yQwiEjb8iRaw1EMdAQTSrc
                    @Override // java.lang.Runnable
                    public final void run() {
                        resultCallback.onComplete(i, euiccProfileInfo);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    public void requestProfile(String str, String str2, Executor executor, ResultCallback<EuiccProfileInfo> resultCallback) {
        try {
            getIEuiccCardController().getProfile(this.mContext.getOpPackageName(), str, str2, new AnonymousClass2(executor, resultCallback));
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling getProfile", e);
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: renamed from: android.telephony.euicc.EuiccCardManager$3, reason: invalid class name */
    class AnonymousClass3 extends IDisableProfileCallback.Stub {
        final /* synthetic */ ResultCallback val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass3(Executor executor, ResultCallback resultCallback) {
            this.val$executor = executor;
            this.val$callback = resultCallback;
        }

        @Override // com.android.internal.telephony.euicc.IDisableProfileCallback
        public void onComplete(final int i) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final ResultCallback resultCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.euicc.-$$Lambda$EuiccCardManager$3$dAhRHhdWfBQit0uxzjczWsD94iE
                    @Override // java.lang.Runnable
                    public final void run() {
                        resultCallback.onComplete(i, null);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    public void disableProfile(String str, String str2, boolean z, Executor executor, ResultCallback<Void> resultCallback) {
        try {
            getIEuiccCardController().disableProfile(this.mContext.getOpPackageName(), str, str2, z, new AnonymousClass3(executor, resultCallback));
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling disableProfile", e);
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: renamed from: android.telephony.euicc.EuiccCardManager$4, reason: invalid class name */
    class AnonymousClass4 extends ISwitchToProfileCallback.Stub {
        final /* synthetic */ ResultCallback val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass4(Executor executor, ResultCallback resultCallback) {
            this.val$executor = executor;
            this.val$callback = resultCallback;
        }

        @Override // com.android.internal.telephony.euicc.ISwitchToProfileCallback
        public void onComplete(final int i, final EuiccProfileInfo euiccProfileInfo) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final ResultCallback resultCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.euicc.-$$Lambda$EuiccCardManager$4$t11MWb_Y_Imb_HRJAnnsYCxER38
                    @Override // java.lang.Runnable
                    public final void run() {
                        resultCallback.onComplete(i, euiccProfileInfo);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    public void switchToProfile(String str, String str2, boolean z, Executor executor, ResultCallback<EuiccProfileInfo> resultCallback) {
        try {
            getIEuiccCardController().switchToProfile(this.mContext.getOpPackageName(), str, str2, z, new AnonymousClass4(executor, resultCallback));
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling switchToProfile", e);
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: renamed from: android.telephony.euicc.EuiccCardManager$5, reason: invalid class name */
    class AnonymousClass5 extends ISetNicknameCallback.Stub {
        final /* synthetic */ ResultCallback val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass5(Executor executor, ResultCallback resultCallback) {
            this.val$executor = executor;
            this.val$callback = resultCallback;
        }

        @Override // com.android.internal.telephony.euicc.ISetNicknameCallback
        public void onComplete(final int i) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final ResultCallback resultCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.euicc.-$$Lambda$EuiccCardManager$5$6l6sRhRYS6PxP569ohKqJAwAbfc
                    @Override // java.lang.Runnable
                    public final void run() {
                        resultCallback.onComplete(i, null);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    public void setNickname(String str, String str2, String str3, Executor executor, ResultCallback<Void> resultCallback) {
        try {
            getIEuiccCardController().setNickname(this.mContext.getOpPackageName(), str, str2, str3, new AnonymousClass5(executor, resultCallback));
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling setNickname", e);
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: renamed from: android.telephony.euicc.EuiccCardManager$6, reason: invalid class name */
    class AnonymousClass6 extends IDeleteProfileCallback.Stub {
        final /* synthetic */ ResultCallback val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass6(Executor executor, ResultCallback resultCallback) {
            this.val$executor = executor;
            this.val$callback = resultCallback;
        }

        @Override // com.android.internal.telephony.euicc.IDeleteProfileCallback
        public void onComplete(final int i) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final ResultCallback resultCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.euicc.-$$Lambda$EuiccCardManager$6$ObQ2ESOVdZX-IJTLGLCtO7tEMmY
                    @Override // java.lang.Runnable
                    public final void run() {
                        resultCallback.onComplete(i, null);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    public void deleteProfile(String str, String str2, Executor executor, ResultCallback<Void> resultCallback) {
        try {
            getIEuiccCardController().deleteProfile(this.mContext.getOpPackageName(), str, str2, new AnonymousClass6(executor, resultCallback));
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling deleteProfile", e);
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: renamed from: android.telephony.euicc.EuiccCardManager$7, reason: invalid class name */
    class AnonymousClass7 extends IResetMemoryCallback.Stub {
        final /* synthetic */ ResultCallback val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass7(Executor executor, ResultCallback resultCallback) {
            this.val$executor = executor;
            this.val$callback = resultCallback;
        }

        @Override // com.android.internal.telephony.euicc.IResetMemoryCallback
        public void onComplete(final int i) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final ResultCallback resultCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.euicc.-$$Lambda$EuiccCardManager$7$ApDS_aSNRXvqaiP5pcvpuNfQuB0
                    @Override // java.lang.Runnable
                    public final void run() {
                        resultCallback.onComplete(i, null);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    public void resetMemory(String str, int i, Executor executor, ResultCallback<Void> resultCallback) {
        try {
            getIEuiccCardController().resetMemory(this.mContext.getOpPackageName(), str, i, new AnonymousClass7(executor, resultCallback));
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling resetMemory", e);
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: renamed from: android.telephony.euicc.EuiccCardManager$8, reason: invalid class name */
    class AnonymousClass8 extends IGetDefaultSmdpAddressCallback.Stub {
        final /* synthetic */ ResultCallback val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass8(Executor executor, ResultCallback resultCallback) {
            this.val$executor = executor;
            this.val$callback = resultCallback;
        }

        @Override // com.android.internal.telephony.euicc.IGetDefaultSmdpAddressCallback
        public void onComplete(final int i, final String str) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final ResultCallback resultCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.euicc.-$$Lambda$EuiccCardManager$8$bb-1T2Nvdanv8onnjmqBfWs1MeE
                    @Override // java.lang.Runnable
                    public final void run() {
                        resultCallback.onComplete(i, str);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    public void requestDefaultSmdpAddress(String str, Executor executor, ResultCallback<String> resultCallback) {
        try {
            getIEuiccCardController().getDefaultSmdpAddress(this.mContext.getOpPackageName(), str, new AnonymousClass8(executor, resultCallback));
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling getDefaultSmdpAddress", e);
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: renamed from: android.telephony.euicc.EuiccCardManager$9, reason: invalid class name */
    class AnonymousClass9 extends IGetSmdsAddressCallback.Stub {
        final /* synthetic */ ResultCallback val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass9(Executor executor, ResultCallback resultCallback) {
            this.val$executor = executor;
            this.val$callback = resultCallback;
        }

        @Override // com.android.internal.telephony.euicc.IGetSmdsAddressCallback
        public void onComplete(final int i, final String str) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final ResultCallback resultCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.euicc.-$$Lambda$EuiccCardManager$9$3JzGxVuQCTNZiLeUUyO8GCHmTvk
                    @Override // java.lang.Runnable
                    public final void run() {
                        resultCallback.onComplete(i, str);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    public void requestSmdsAddress(String str, Executor executor, ResultCallback<String> resultCallback) {
        try {
            getIEuiccCardController().getSmdsAddress(this.mContext.getOpPackageName(), str, new AnonymousClass9(executor, resultCallback));
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling getSmdsAddress", e);
            throw e.rethrowFromSystemServer();
        }
    }

    public void setDefaultSmdpAddress(String str, String str2, Executor executor, ResultCallback<Void> resultCallback) {
        try {
            getIEuiccCardController().setDefaultSmdpAddress(this.mContext.getOpPackageName(), str, str2, new AnonymousClass10(executor, resultCallback));
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling setDefaultSmdpAddress", e);
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: renamed from: android.telephony.euicc.EuiccCardManager$10, reason: invalid class name */
    class AnonymousClass10 extends ISetDefaultSmdpAddressCallback.Stub {
        final /* synthetic */ ResultCallback val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass10(Executor executor, ResultCallback resultCallback) {
            this.val$executor = executor;
            this.val$callback = resultCallback;
        }

        @Override // com.android.internal.telephony.euicc.ISetDefaultSmdpAddressCallback
        public void onComplete(final int i) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final ResultCallback resultCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.euicc.-$$Lambda$EuiccCardManager$10$D7SpCRcEYBXymhW18Oh5CFVYkxU
                    @Override // java.lang.Runnable
                    public final void run() {
                        resultCallback.onComplete(i, null);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    /* JADX INFO: renamed from: android.telephony.euicc.EuiccCardManager$11, reason: invalid class name */
    class AnonymousClass11 extends IGetRulesAuthTableCallback.Stub {
        final /* synthetic */ ResultCallback val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass11(Executor executor, ResultCallback resultCallback) {
            this.val$executor = executor;
            this.val$callback = resultCallback;
        }

        @Override // com.android.internal.telephony.euicc.IGetRulesAuthTableCallback
        public void onComplete(final int i, final EuiccRulesAuthTable euiccRulesAuthTable) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final ResultCallback resultCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.euicc.-$$Lambda$EuiccCardManager$11$6f_aow8gsC2Vl1o95OXqQ-dmj6w
                    @Override // java.lang.Runnable
                    public final void run() {
                        resultCallback.onComplete(i, euiccRulesAuthTable);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    public void requestRulesAuthTable(String str, Executor executor, ResultCallback<EuiccRulesAuthTable> resultCallback) {
        try {
            getIEuiccCardController().getRulesAuthTable(this.mContext.getOpPackageName(), str, new AnonymousClass11(executor, resultCallback));
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling getRulesAuthTable", e);
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: renamed from: android.telephony.euicc.EuiccCardManager$12, reason: invalid class name */
    class AnonymousClass12 extends IGetEuiccChallengeCallback.Stub {
        final /* synthetic */ ResultCallback val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass12(Executor executor, ResultCallback resultCallback) {
            this.val$executor = executor;
            this.val$callback = resultCallback;
        }

        @Override // com.android.internal.telephony.euicc.IGetEuiccChallengeCallback
        public void onComplete(final int i, final byte[] bArr) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final ResultCallback resultCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.euicc.-$$Lambda$EuiccCardManager$12$FVtW9FPSamEPcKMIHqpc5FL_FFQ
                    @Override // java.lang.Runnable
                    public final void run() {
                        resultCallback.onComplete(i, bArr);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    public void requestEuiccChallenge(String str, Executor executor, ResultCallback<byte[]> resultCallback) {
        try {
            getIEuiccCardController().getEuiccChallenge(this.mContext.getOpPackageName(), str, new AnonymousClass12(executor, resultCallback));
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling getEuiccChallenge", e);
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: renamed from: android.telephony.euicc.EuiccCardManager$13, reason: invalid class name */
    class AnonymousClass13 extends IGetEuiccInfo1Callback.Stub {
        final /* synthetic */ ResultCallback val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass13(Executor executor, ResultCallback resultCallback) {
            this.val$executor = executor;
            this.val$callback = resultCallback;
        }

        @Override // com.android.internal.telephony.euicc.IGetEuiccInfo1Callback
        public void onComplete(final int i, final byte[] bArr) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final ResultCallback resultCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.euicc.-$$Lambda$EuiccCardManager$13$hHXuIbgNhbI_2GYmKjQ5pr6GRDQ
                    @Override // java.lang.Runnable
                    public final void run() {
                        resultCallback.onComplete(i, bArr);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    public void requestEuiccInfo1(String str, Executor executor, ResultCallback<byte[]> resultCallback) {
        try {
            getIEuiccCardController().getEuiccInfo1(this.mContext.getOpPackageName(), str, new AnonymousClass13(executor, resultCallback));
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling getEuiccInfo1", e);
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: renamed from: android.telephony.euicc.EuiccCardManager$14, reason: invalid class name */
    class AnonymousClass14 extends IGetEuiccInfo2Callback.Stub {
        final /* synthetic */ ResultCallback val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass14(Executor executor, ResultCallback resultCallback) {
            this.val$executor = executor;
            this.val$callback = resultCallback;
        }

        @Override // com.android.internal.telephony.euicc.IGetEuiccInfo2Callback
        public void onComplete(final int i, final byte[] bArr) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final ResultCallback resultCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.euicc.-$$Lambda$EuiccCardManager$14$QCQlwS0TkNW5o04a1DGpgTB1aUI
                    @Override // java.lang.Runnable
                    public final void run() {
                        resultCallback.onComplete(i, bArr);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    public void requestEuiccInfo2(String str, Executor executor, ResultCallback<byte[]> resultCallback) {
        try {
            getIEuiccCardController().getEuiccInfo2(this.mContext.getOpPackageName(), str, new AnonymousClass14(executor, resultCallback));
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling getEuiccInfo2", e);
            throw e.rethrowFromSystemServer();
        }
    }

    public void authenticateServer(String str, String str2, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, Executor executor, ResultCallback<byte[]> resultCallback) {
        try {
            getIEuiccCardController().authenticateServer(this.mContext.getOpPackageName(), str, str2, bArr, bArr2, bArr3, bArr4, new AnonymousClass15(executor, resultCallback));
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling authenticateServer", e);
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: renamed from: android.telephony.euicc.EuiccCardManager$15, reason: invalid class name */
    class AnonymousClass15 extends IAuthenticateServerCallback.Stub {
        final /* synthetic */ ResultCallback val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass15(Executor executor, ResultCallback resultCallback) {
            this.val$executor = executor;
            this.val$callback = resultCallback;
        }

        @Override // com.android.internal.telephony.euicc.IAuthenticateServerCallback
        public void onComplete(final int i, final byte[] bArr) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final ResultCallback resultCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.euicc.-$$Lambda$EuiccCardManager$15$VIlakF1TlIE9dlhMudSSqieb1O4
                    @Override // java.lang.Runnable
                    public final void run() {
                        resultCallback.onComplete(i, bArr);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    public void prepareDownload(String str, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, Executor executor, ResultCallback<byte[]> resultCallback) {
        try {
            getIEuiccCardController().prepareDownload(this.mContext.getOpPackageName(), str, bArr, bArr2, bArr3, bArr4, new AnonymousClass16(executor, resultCallback));
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling prepareDownload", e);
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: renamed from: android.telephony.euicc.EuiccCardManager$16, reason: invalid class name */
    class AnonymousClass16 extends IPrepareDownloadCallback.Stub {
        final /* synthetic */ ResultCallback val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass16(Executor executor, ResultCallback resultCallback) {
            this.val$executor = executor;
            this.val$callback = resultCallback;
        }

        @Override // com.android.internal.telephony.euicc.IPrepareDownloadCallback
        public void onComplete(final int i, final byte[] bArr) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final ResultCallback resultCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.euicc.-$$Lambda$EuiccCardManager$16$eK19hFgMlOzmN1vMHO6tUdEI8x4
                    @Override // java.lang.Runnable
                    public final void run() {
                        resultCallback.onComplete(i, bArr);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    public void loadBoundProfilePackage(String str, byte[] bArr, Executor executor, ResultCallback<byte[]> resultCallback) {
        try {
            getIEuiccCardController().loadBoundProfilePackage(this.mContext.getOpPackageName(), str, bArr, new AnonymousClass17(executor, resultCallback));
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling loadBoundProfilePackage", e);
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: renamed from: android.telephony.euicc.EuiccCardManager$17, reason: invalid class name */
    class AnonymousClass17 extends ILoadBoundProfilePackageCallback.Stub {
        final /* synthetic */ ResultCallback val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass17(Executor executor, ResultCallback resultCallback) {
            this.val$executor = executor;
            this.val$callback = resultCallback;
        }

        @Override // com.android.internal.telephony.euicc.ILoadBoundProfilePackageCallback
        public void onComplete(final int i, final byte[] bArr) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final ResultCallback resultCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.euicc.-$$Lambda$EuiccCardManager$17$nO4nNzsH5jtHkUOG7gonuAzU5j4
                    @Override // java.lang.Runnable
                    public final void run() {
                        resultCallback.onComplete(i, bArr);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    public void cancelSession(String str, byte[] bArr, int i, Executor executor, ResultCallback<byte[]> resultCallback) {
        try {
            getIEuiccCardController().cancelSession(this.mContext.getOpPackageName(), str, bArr, i, new AnonymousClass18(executor, resultCallback));
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling cancelSession", e);
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: renamed from: android.telephony.euicc.EuiccCardManager$18, reason: invalid class name */
    class AnonymousClass18 extends ICancelSessionCallback.Stub {
        final /* synthetic */ ResultCallback val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass18(Executor executor, ResultCallback resultCallback) {
            this.val$executor = executor;
            this.val$callback = resultCallback;
        }

        @Override // com.android.internal.telephony.euicc.ICancelSessionCallback
        public void onComplete(final int i, final byte[] bArr) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final ResultCallback resultCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.euicc.-$$Lambda$EuiccCardManager$18$AVaNr-yyFOHl_sp6xS0awlcM5z4
                    @Override // java.lang.Runnable
                    public final void run() {
                        resultCallback.onComplete(i, bArr);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    /* JADX INFO: renamed from: android.telephony.euicc.EuiccCardManager$19, reason: invalid class name */
    class AnonymousClass19 extends IListNotificationsCallback.Stub {
        final /* synthetic */ ResultCallback val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass19(Executor executor, ResultCallback resultCallback) {
            this.val$executor = executor;
            this.val$callback = resultCallback;
        }

        @Override // com.android.internal.telephony.euicc.IListNotificationsCallback
        public void onComplete(final int i, final EuiccNotification[] euiccNotificationArr) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final ResultCallback resultCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.euicc.-$$Lambda$EuiccCardManager$19$C8EGiAJfQ892gxEj9Ws6hhnKWG8
                    @Override // java.lang.Runnable
                    public final void run() {
                        resultCallback.onComplete(i, euiccNotificationArr);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    public void listNotifications(String str, int i, Executor executor, ResultCallback<EuiccNotification[]> resultCallback) {
        try {
            getIEuiccCardController().listNotifications(this.mContext.getOpPackageName(), str, i, new AnonymousClass19(executor, resultCallback));
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling listNotifications", e);
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: renamed from: android.telephony.euicc.EuiccCardManager$20, reason: invalid class name */
    class AnonymousClass20 extends IRetrieveNotificationListCallback.Stub {
        final /* synthetic */ ResultCallback val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass20(Executor executor, ResultCallback resultCallback) {
            this.val$executor = executor;
            this.val$callback = resultCallback;
        }

        @Override // com.android.internal.telephony.euicc.IRetrieveNotificationListCallback
        public void onComplete(final int i, final EuiccNotification[] euiccNotificationArr) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final ResultCallback resultCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.euicc.-$$Lambda$EuiccCardManager$20$f5e9aPd4u_H4Kq9Y0XIG3u87gRo
                    @Override // java.lang.Runnable
                    public final void run() {
                        resultCallback.onComplete(i, euiccNotificationArr);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    public void retrieveNotificationList(String str, int i, Executor executor, ResultCallback<EuiccNotification[]> resultCallback) {
        try {
            getIEuiccCardController().retrieveNotificationList(this.mContext.getOpPackageName(), str, i, new AnonymousClass20(executor, resultCallback));
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling retrieveNotificationList", e);
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: renamed from: android.telephony.euicc.EuiccCardManager$21, reason: invalid class name */
    class AnonymousClass21 extends IRetrieveNotificationCallback.Stub {
        final /* synthetic */ ResultCallback val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass21(Executor executor, ResultCallback resultCallback) {
            this.val$executor = executor;
            this.val$callback = resultCallback;
        }

        @Override // com.android.internal.telephony.euicc.IRetrieveNotificationCallback
        public void onComplete(final int i, final EuiccNotification euiccNotification) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final ResultCallback resultCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.euicc.-$$Lambda$EuiccCardManager$21$43NhBOaV0pxsfw_1XRhGrzFKhtA
                    @Override // java.lang.Runnable
                    public final void run() {
                        resultCallback.onComplete(i, euiccNotification);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    public void retrieveNotification(String str, int i, Executor executor, ResultCallback<EuiccNotification> resultCallback) {
        try {
            getIEuiccCardController().retrieveNotification(this.mContext.getOpPackageName(), str, i, new AnonymousClass21(executor, resultCallback));
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling retrieveNotification", e);
            throw e.rethrowFromSystemServer();
        }
    }

    public void removeNotificationFromList(String str, int i, Executor executor, ResultCallback<Void> resultCallback) {
        try {
            getIEuiccCardController().removeNotificationFromList(this.mContext.getOpPackageName(), str, i, new AnonymousClass22(executor, resultCallback));
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling removeNotificationFromList", e);
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: renamed from: android.telephony.euicc.EuiccCardManager$22, reason: invalid class name */
    class AnonymousClass22 extends IRemoveNotificationFromListCallback.Stub {
        final /* synthetic */ ResultCallback val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass22(Executor executor, ResultCallback resultCallback) {
            this.val$executor = executor;
            this.val$callback = resultCallback;
        }

        @Override // com.android.internal.telephony.euicc.IRemoveNotificationFromListCallback
        public void onComplete(final int i) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final ResultCallback resultCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.euicc.-$$Lambda$EuiccCardManager$22$K0TI8s0sg_Ktv69tdXs5OklNU9M
                    @Override // java.lang.Runnable
                    public final void run() {
                        resultCallback.onComplete(i, null);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }
}
