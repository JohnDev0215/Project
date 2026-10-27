package android.permission;

import android.Manifest;
import android.annotation.SystemApi;
import android.app.ActivityThread;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.UserHandle;
import android.permission.IPermissionController;
import android.provider.DeviceConfig;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Pair;
import com.android.internal.infra.AndroidFuture;
import com.android.internal.infra.RemoteStream;
import com.android.internal.infra.ServiceConnector;
import com.android.internal.util.CollectionUtils;
import com.android.internal.util.FunctionalUtils;
import com.android.internal.util.Preconditions;
import java.io.FileDescriptor;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import libcore.util.EmptyArray;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class PermissionControllerManager {
    private static final int CHUNK_SIZE = 4096;
    public static final int COUNT_ONLY_WHEN_GRANTED = 1;
    public static final int COUNT_WHEN_SYSTEM = 2;
    public static final int REASON_INSTALLER_POLICY_VIOLATION = 2;
    public static final int REASON_MALWARE = 1;
    private static final long REQUEST_TIMEOUT_MILLIS = 60000;
    private static final long UNBIND_TIMEOUT_MILLIS = 10000;
    private final Context mContext;
    private final Handler mHandler;
    private final ServiceConnector<IPermissionController> mRemoteService;
    private static final String TAG = PermissionControllerManager.class.getSimpleName();
    private static final Object sLock = new Object();
    private static ArrayMap<Pair<Integer, Thread>, ServiceConnector<IPermissionController>> sRemoteServices = new ArrayMap<>(1);

    @Retention(RetentionPolicy.SOURCE)
    public @interface CountPermissionAppsFlag {
    }

    public interface OnCountPermissionAppsResultCallback {
        void onCountPermissionApps(int i);
    }

    public interface OnGetAppPermissionResultCallback {
        void onGetAppPermissions(List<RuntimePermissionPresentationInfo> list);
    }

    public interface OnPermissionUsageResultCallback {
        void onPermissionUsageResult(List<RuntimePermissionUsageInfo> list);
    }

    public static abstract class OnRevokeRuntimePermissionsCallback {
        public abstract void onRevokeRuntimePermissions(Map<String, List<String>> map);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Reason {
    }

    public PermissionControllerManager(Context context, final Handler handler) {
        synchronized (sLock) {
            Pair<Integer, Thread> pair = new Pair<>(Integer.valueOf(context.getUserId()), handler.getLooper().getThread());
            ServiceConnector<IPermissionController> serviceConnector = sRemoteServices.get(pair);
            if (serviceConnector == null) {
                Intent intent = new Intent(PermissionControllerService.SERVICE_INTERFACE);
                intent.setPackage(context.getPackageManager().getPermissionControllerPackageName());
                ServiceConnector.Impl<IPermissionController> impl = new ServiceConnector.Impl<IPermissionController>(ActivityThread.currentApplication(), new Intent(PermissionControllerService.SERVICE_INTERFACE).setComponent(context.getPackageManager().resolveService(intent, 0).getComponentInfo().getComponentName()), 0, context.getUserId(), new Function() { // from class: android.permission.-$$Lambda$ViMr_PAGHrCLBQPYNzqdYUNU5zI
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return IPermissionController.Stub.asInterface((IBinder) obj);
                    }
                }) { // from class: android.permission.PermissionControllerManager.1
                    @Override // com.android.internal.infra.ServiceConnector.Impl
                    protected long getAutoDisconnectTimeoutMs() {
                        return 10000L;
                    }

                    @Override // com.android.internal.infra.ServiceConnector.Impl
                    protected long getRequestTimeoutMs() {
                        return 60000L;
                    }

                    @Override // com.android.internal.infra.ServiceConnector.Impl
                    protected Handler getJobHandler() {
                        return handler;
                    }
                };
                sRemoteServices.put(pair, impl);
                serviceConnector = impl;
            }
            this.mRemoteService = serviceConnector;
        }
        this.mContext = context;
        this.mHandler = handler;
    }

    private void enforceSomePermissionsGrantedToSelf(String... strArr) {
        for (String str : strArr) {
            if (this.mContext.checkSelfPermission(str) == 0) {
                return;
            }
        }
        throw new SecurityException("At lest one of the following permissions is required: " + Arrays.toString(strArr));
    }

    public void revokeRuntimePermissions(final Map<String, List<String>> map, final boolean z, final int i, Executor executor, final OnRevokeRuntimePermissionsCallback onRevokeRuntimePermissionsCallback) {
        Preconditions.checkNotNull(executor);
        Preconditions.checkNotNull(onRevokeRuntimePermissionsCallback);
        Preconditions.checkNotNull(map);
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            Preconditions.checkNotNull(entry.getKey());
            Preconditions.checkCollectionElementsNotNull(entry.getValue(), DeviceConfig.NAMESPACE_PERMISSIONS);
        }
        enforceSomePermissionsGrantedToSelf(Manifest.permission.REVOKE_RUNTIME_PERMISSIONS);
        this.mRemoteService.postAsync(new ServiceConnector.Job() { // from class: android.permission.-$$Lambda$PermissionControllerManager$-00c_YE4DXfkvjweKlMro3pl_G8
            @Override // com.android.internal.infra.ServiceConnector.Job
            public final Object run(Object obj) {
                return this.f$0.lambda$revokeRuntimePermissions$0$PermissionControllerManager(map, z, i, (IPermissionController) obj);
            }
        }).whenCompleteAsync((BiConsumer<? super R, ? super Throwable>) new BiConsumer() { // from class: android.permission.-$$Lambda$PermissionControllerManager$EuAUoa5oQvhIEr2FodjehlRNmHY
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                PermissionControllerManager.lambda$revokeRuntimePermissions$1(onRevokeRuntimePermissionsCallback, (Map) obj, (Throwable) obj2);
            }
        }, executor);
    }

    public /* synthetic */ CompletableFuture lambda$revokeRuntimePermissions$0$PermissionControllerManager(Map map, boolean z, int i, IPermissionController iPermissionController) throws Exception {
        Bundle bundle = new Bundle();
        for (Map.Entry entry : map.entrySet()) {
            bundle.putStringArrayList((String) entry.getKey(), new ArrayList<>((Collection) entry.getValue()));
        }
        AndroidFuture androidFuture = new AndroidFuture();
        iPermissionController.revokeRuntimePermissions(bundle, z, i, this.mContext.getPackageName(), androidFuture);
        return androidFuture;
    }

    static /* synthetic */ void lambda$revokeRuntimePermissions$1(OnRevokeRuntimePermissionsCallback onRevokeRuntimePermissionsCallback, Map map, Throwable th) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (th != null) {
                Log.e(TAG, "Failure when revoking runtime permissions " + map, th);
                onRevokeRuntimePermissionsCallback.onRevokeRuntimePermissions(Collections.emptyMap());
            } else {
                onRevokeRuntimePermissionsCallback.onRevokeRuntimePermissions(map);
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void setRuntimePermissionGrantStateByDeviceAdmin(final String str, final String str2, final String str3, final int i, Executor executor, final Consumer<Boolean> consumer) {
        Preconditions.checkStringNotEmpty(str);
        Preconditions.checkStringNotEmpty(str2);
        Preconditions.checkStringNotEmpty(str3);
        boolean z = true;
        if (i != 1 && i != 2 && i != 0) {
            z = false;
        }
        Preconditions.checkArgument(z);
        Preconditions.checkNotNull(executor);
        Preconditions.checkNotNull(consumer);
        this.mRemoteService.postAsync(new ServiceConnector.Job() { // from class: android.permission.-$$Lambda$PermissionControllerManager$ESMSOXHMTX1zwjBZlBwmbWKWJso
            @Override // com.android.internal.infra.ServiceConnector.Job
            public final Object run(Object obj) {
                return PermissionControllerManager.lambda$setRuntimePermissionGrantStateByDeviceAdmin$2(str, str2, str3, i, (IPermissionController) obj);
            }
        }).whenCompleteAsync((BiConsumer<? super R, ? super Throwable>) new BiConsumer() { // from class: android.permission.-$$Lambda$PermissionControllerManager$nFhNDDZWDMvY1EMapbsu7-rktxg
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                PermissionControllerManager.lambda$setRuntimePermissionGrantStateByDeviceAdmin$3(str2, consumer, (Boolean) obj, (Throwable) obj2);
            }
        }, executor);
    }

    static /* synthetic */ CompletableFuture lambda$setRuntimePermissionGrantStateByDeviceAdmin$2(String str, String str2, String str3, int i, IPermissionController iPermissionController) throws Exception {
        AndroidFuture androidFuture = new AndroidFuture();
        iPermissionController.setRuntimePermissionGrantStateByDeviceAdmin(str, str2, str3, i, androidFuture);
        return androidFuture;
    }

    static /* synthetic */ void lambda$setRuntimePermissionGrantStateByDeviceAdmin$3(String str, Consumer consumer, Boolean bool, Throwable th) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (th != null) {
                Log.e(TAG, "Error setting permissions state for device admin " + str, th);
                consumer.accept(false);
            } else {
                consumer.accept(Boolean.valueOf(Boolean.TRUE.equals(bool)));
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void getRuntimePermissionBackup(final UserHandle userHandle, Executor executor, final Consumer<byte[]> consumer) {
        Preconditions.checkNotNull(userHandle);
        Preconditions.checkNotNull(executor);
        Preconditions.checkNotNull(consumer);
        enforceSomePermissionsGrantedToSelf(Manifest.permission.GET_RUNTIME_PERMISSIONS);
        this.mRemoteService.postAsync(new ServiceConnector.Job() { // from class: android.permission.-$$Lambda$PermissionControllerManager$Iy-7wiKMCV-MFSPGyIJxP_DSf8E
            @Override // com.android.internal.infra.ServiceConnector.Job
            public final Object run(Object obj) {
                return RemoteStream.receiveBytes(new FunctionalUtils.ThrowingConsumer() { // from class: android.permission.-$$Lambda$PermissionControllerManager$eHuRmDpRAUfA3qanHHMVMV_C0lI
                    @Override // com.android.internal.util.FunctionalUtils.ThrowingConsumer
                    public final void acceptOrThrow(Object obj2) throws RemoteException {
                        iPermissionController.getRuntimePermissionBackup(userHandle, (ParcelFileDescriptor) obj2);
                    }
                });
            }
        }).whenCompleteAsync((BiConsumer<? super R, ? super Throwable>) new BiConsumer() { // from class: android.permission.-$$Lambda$PermissionControllerManager$wPNqW0yZff7KXoWmrKVyzMgY2jc
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                PermissionControllerManager.lambda$getRuntimePermissionBackup$6(consumer, (byte[]) obj, (Throwable) obj2);
            }
        }, executor);
    }

    static /* synthetic */ void lambda$getRuntimePermissionBackup$6(Consumer consumer, byte[] bArr, Throwable th) {
        if (th != null) {
            Log.e(TAG, "Error getting permission backup", th);
            consumer.accept(EmptyArray.BYTE);
        } else {
            consumer.accept(bArr);
        }
    }

    public void stageAndApplyRuntimePermissionsBackup(final byte[] bArr, final UserHandle userHandle) {
        Preconditions.checkNotNull(bArr);
        Preconditions.checkNotNull(userHandle);
        enforceSomePermissionsGrantedToSelf(Manifest.permission.GRANT_RUNTIME_PERMISSIONS, Manifest.permission.RESTORE_RUNTIME_PERMISSIONS);
        this.mRemoteService.postAsync(new ServiceConnector.Job() { // from class: android.permission.-$$Lambda$PermissionControllerManager$DtZA5ILHLibh9MBqPQNGrWZ0CSw
            @Override // com.android.internal.infra.ServiceConnector.Job
            public final Object run(Object obj) {
                return RemoteStream.sendBytes((FunctionalUtils.ThrowingConsumer<ParcelFileDescriptor>) new FunctionalUtils.ThrowingConsumer() { // from class: android.permission.-$$Lambda$PermissionControllerManager$ofaZqHrEKxR5dyXeNlRSyB1Lawk
                    @Override // com.android.internal.util.FunctionalUtils.ThrowingConsumer
                    public final void acceptOrThrow(Object obj2) throws RemoteException {
                        iPermissionController.stageAndApplyRuntimePermissionsBackup(userHandle, (ParcelFileDescriptor) obj2);
                    }
                }, bArr);
            }
        }).whenComplete((BiConsumer<? super R, ? super Throwable>) new BiConsumer() { // from class: android.permission.-$$Lambda$PermissionControllerManager$S2VSU3Pp--uq4UNaUiz9gMU65xU
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                PermissionControllerManager.lambda$stageAndApplyRuntimePermissionsBackup$9((Void) obj, (Throwable) obj2);
            }
        });
    }

    static /* synthetic */ void lambda$stageAndApplyRuntimePermissionsBackup$9(Void r1, Throwable th) {
        if (th != null) {
            Log.e(TAG, "Error sending permission backup", th);
        }
    }

    public void applyStagedRuntimePermissionBackup(final String str, final UserHandle userHandle, Executor executor, final Consumer<Boolean> consumer) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(userHandle);
        Preconditions.checkNotNull(executor);
        Preconditions.checkNotNull(consumer);
        enforceSomePermissionsGrantedToSelf(Manifest.permission.GRANT_RUNTIME_PERMISSIONS, Manifest.permission.RESTORE_RUNTIME_PERMISSIONS);
        this.mRemoteService.postAsync(new ServiceConnector.Job() { // from class: android.permission.-$$Lambda$PermissionControllerManager$2gyb4miANgsuR_Cn3HPTnP6sL54
            @Override // com.android.internal.infra.ServiceConnector.Job
            public final Object run(Object obj) {
                return PermissionControllerManager.lambda$applyStagedRuntimePermissionBackup$10(str, userHandle, (IPermissionController) obj);
            }
        }).whenCompleteAsync((BiConsumer<? super R, ? super Throwable>) new BiConsumer() { // from class: android.permission.-$$Lambda$PermissionControllerManager$vBYanTuMAWBbfOp_XdHzQXYNpXY
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                PermissionControllerManager.lambda$applyStagedRuntimePermissionBackup$11(str, consumer, (Boolean) obj, (Throwable) obj2);
            }
        }, executor);
    }

    static /* synthetic */ CompletableFuture lambda$applyStagedRuntimePermissionBackup$10(String str, UserHandle userHandle, IPermissionController iPermissionController) throws Exception {
        AndroidFuture androidFuture = new AndroidFuture();
        iPermissionController.applyStagedRuntimePermissionBackup(str, userHandle, androidFuture);
        return androidFuture;
    }

    static /* synthetic */ void lambda$applyStagedRuntimePermissionBackup$11(String str, Consumer consumer, Boolean bool, Throwable th) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (th != null) {
                Log.e(TAG, "Error restoring delayed permissions for " + str, th);
                consumer.accept(true);
            } else {
                consumer.accept(Boolean.valueOf(Boolean.TRUE.equals(bool)));
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void dump(final FileDescriptor fileDescriptor, final String[] strArr) {
        try {
            this.mRemoteService.post(new ServiceConnector.VoidJob() { // from class: android.permission.-$$Lambda$PermissionControllerManager$QKXRZuAcOE3JWRlH0VmPcShX4LM
                @Override // com.android.internal.infra.ServiceConnector.VoidJob
                public final void runNoResult(Object obj) throws RemoteException {
                    ((IPermissionController) obj).asBinder().dump(fileDescriptor, strArr);
                }
            }).get(60000L, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            Log.e(TAG, "Could not get dump", e);
        }
    }

    public void getAppPermissions(final String str, final OnGetAppPermissionResultCallback onGetAppPermissionResultCallback, final Handler handler) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(onGetAppPermissionResultCallback);
        if (handler == null) {
            handler = this.mHandler;
        }
        this.mRemoteService.postAsync(new ServiceConnector.Job() { // from class: android.permission.-$$Lambda$PermissionControllerManager$qbjquKkizobHfe5rpkz_pBZGMm0
            @Override // com.android.internal.infra.ServiceConnector.Job
            public final Object run(Object obj) {
                return PermissionControllerManager.lambda$getAppPermissions$13(str, (IPermissionController) obj);
            }
        }).whenComplete((BiConsumer<? super R, ? super Throwable>) new BiConsumer() { // from class: android.permission.-$$Lambda$PermissionControllerManager$6Ag5ugR0frhyp44qzIn5jlbtZRg
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                handler.post(new Runnable() { // from class: android.permission.-$$Lambda$PermissionControllerManager$a1mv0hj0w2CVT-PkiUJ634frWCM
                    @Override // java.lang.Runnable
                    public final void run() {
                        PermissionControllerManager.lambda$getAppPermissions$14(th, onGetAppPermissionResultCallback, list);
                    }
                });
            }
        });
    }

    static /* synthetic */ CompletableFuture lambda$getAppPermissions$13(String str, IPermissionController iPermissionController) throws Exception {
        AndroidFuture androidFuture = new AndroidFuture();
        iPermissionController.getAppPermissions(str, androidFuture);
        return androidFuture;
    }

    static /* synthetic */ void lambda$getAppPermissions$14(Throwable th, OnGetAppPermissionResultCallback onGetAppPermissionResultCallback, List list) {
        if (th != null) {
            Log.e(TAG, "Error getting app permission", th);
            onGetAppPermissionResultCallback.onGetAppPermissions(Collections.emptyList());
        } else {
            onGetAppPermissionResultCallback.onGetAppPermissions(CollectionUtils.emptyIfNull(list));
        }
    }

    public void revokeRuntimePermission(final String str, final String str2) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(str2);
        this.mRemoteService.run(new ServiceConnector.VoidJob() { // from class: android.permission.-$$Lambda$PermissionControllerManager$fCp0CnfICduEKFO6T7fn3jMOSBI
            @Override // com.android.internal.infra.ServiceConnector.VoidJob
            public final void runNoResult(Object obj) throws RemoteException {
                ((IPermissionController) obj).revokeRuntimePermission(str, str2);
            }
        });
    }

    public void countPermissionApps(final List<String> list, final int i, final OnCountPermissionAppsResultCallback onCountPermissionAppsResultCallback, final Handler handler) {
        Preconditions.checkCollectionElementsNotNull(list, "permissionNames");
        Preconditions.checkFlagsArgument(i, 3);
        Preconditions.checkNotNull(onCountPermissionAppsResultCallback);
        if (handler == null) {
            handler = this.mHandler;
        }
        this.mRemoteService.postAsync(new ServiceConnector.Job() { // from class: android.permission.-$$Lambda$PermissionControllerManager$F3XGmg9VDZwhqc9cSceeDfZkCBo
            @Override // com.android.internal.infra.ServiceConnector.Job
            public final Object run(Object obj) {
                return PermissionControllerManager.lambda$countPermissionApps$17(list, i, (IPermissionController) obj);
            }
        }).whenComplete((BiConsumer<? super R, ? super Throwable>) new BiConsumer() { // from class: android.permission.-$$Lambda$PermissionControllerManager$3DXENZY34HBEEqo36SfSnKNAHK4
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                handler.post(new Runnable() { // from class: android.permission.-$$Lambda$PermissionControllerManager$TDPE-yM_sr87lWH1NcSHItfBmxI
                    @Override // java.lang.Runnable
                    public final void run() {
                        PermissionControllerManager.lambda$countPermissionApps$18(th, onCountPermissionAppsResultCallback, num);
                    }
                });
            }
        });
    }

    static /* synthetic */ CompletableFuture lambda$countPermissionApps$17(List list, int i, IPermissionController iPermissionController) throws Exception {
        AndroidFuture androidFuture = new AndroidFuture();
        iPermissionController.countPermissionApps(list, i, androidFuture);
        return androidFuture;
    }

    static /* synthetic */ void lambda$countPermissionApps$18(Throwable th, OnCountPermissionAppsResultCallback onCountPermissionAppsResultCallback, Integer num) {
        if (th != null) {
            Log.e(TAG, "Error counting permission apps", th);
            onCountPermissionAppsResultCallback.onCountPermissionApps(0);
        } else {
            onCountPermissionAppsResultCallback.onCountPermissionApps(num.intValue());
        }
    }

    public void getPermissionUsages(final boolean z, final long j, Executor executor, final OnPermissionUsageResultCallback onPermissionUsageResultCallback) {
        Preconditions.checkArgumentNonnegative(j);
        Preconditions.checkNotNull(executor);
        Preconditions.checkNotNull(onPermissionUsageResultCallback);
        this.mRemoteService.postAsync(new ServiceConnector.Job() { // from class: android.permission.-$$Lambda$PermissionControllerManager$qxGLAK8X0GMfe_vQr0NFGapPRN4
            @Override // com.android.internal.infra.ServiceConnector.Job
            public final Object run(Object obj) {
                return PermissionControllerManager.lambda$getPermissionUsages$20(z, j, (IPermissionController) obj);
            }
        }).whenCompleteAsync((BiConsumer<? super R, ? super Throwable>) new BiConsumer() { // from class: android.permission.-$$Lambda$PermissionControllerManager$bTHE-ox64GS5Ys00-8kOUGjAWhY
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                PermissionControllerManager.lambda$getPermissionUsages$21(onPermissionUsageResultCallback, (List) obj, (Throwable) obj2);
            }
        }, executor);
    }

    static /* synthetic */ CompletableFuture lambda$getPermissionUsages$20(boolean z, long j, IPermissionController iPermissionController) throws Exception {
        AndroidFuture androidFuture = new AndroidFuture();
        iPermissionController.getPermissionUsages(z, j, androidFuture);
        return androidFuture;
    }

    static /* synthetic */ void lambda$getPermissionUsages$21(OnPermissionUsageResultCallback onPermissionUsageResultCallback, List list, Throwable th) {
        if (th != null) {
            Log.e(TAG, "Error getting permission usages", th);
            onPermissionUsageResultCallback.onPermissionUsageResult(Collections.emptyList());
        } else {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                onPermissionUsageResultCallback.onPermissionUsageResult(CollectionUtils.emptyIfNull(list));
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    public void grantOrUpgradeDefaultRuntimePermissions(Executor executor, final Consumer<Boolean> consumer) {
        this.mRemoteService.postAsync(new ServiceConnector.Job() { // from class: android.permission.-$$Lambda$PermissionControllerManager$cn7Tw4pO8bRiaLbV2UGd6fgcSO4
            @Override // com.android.internal.infra.ServiceConnector.Job
            public final Object run(Object obj) {
                return PermissionControllerManager.lambda$grantOrUpgradeDefaultRuntimePermissions$22((IPermissionController) obj);
            }
        }).whenCompleteAsync((BiConsumer<? super R, ? super Throwable>) new BiConsumer() { // from class: android.permission.-$$Lambda$PermissionControllerManager$iZg46VI1PF4HbTl45C2Rv0XwOm0
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                PermissionControllerManager.lambda$grantOrUpgradeDefaultRuntimePermissions$23(consumer, (Boolean) obj, (Throwable) obj2);
            }
        }, executor);
    }

    static /* synthetic */ CompletableFuture lambda$grantOrUpgradeDefaultRuntimePermissions$22(IPermissionController iPermissionController) throws Exception {
        AndroidFuture androidFuture = new AndroidFuture();
        iPermissionController.grantOrUpgradeDefaultRuntimePermissions(androidFuture);
        return androidFuture;
    }

    static /* synthetic */ void lambda$grantOrUpgradeDefaultRuntimePermissions$23(Consumer consumer, Boolean bool, Throwable th) {
        if (th != null) {
            Log.e(TAG, "Error granting or upgrading runtime permissions", th);
            consumer.accept(false);
        } else {
            consumer.accept(Boolean.valueOf(Boolean.TRUE.equals(bool)));
        }
    }

    public void updateUserSensitive() {
        updateUserSensitiveForApp(-1);
    }

    public void updateUserSensitiveForApp(final int i) {
        this.mRemoteService.postAsync(new ServiceConnector.Job() { // from class: android.permission.-$$Lambda$PermissionControllerManager$MqIts8mfNZAqQWR1_qc2_3bh08M
            @Override // com.android.internal.infra.ServiceConnector.Job
            public final Object run(Object obj) {
                return PermissionControllerManager.lambda$updateUserSensitiveForApp$24(i, (IPermissionController) obj);
            }
        }).whenComplete((BiConsumer<? super R, ? super Throwable>) new BiConsumer() { // from class: android.permission.-$$Lambda$PermissionControllerManager$LeLHpbJU3pY1PZAWD3x9Fi9QxHI
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                PermissionControllerManager.lambda$updateUserSensitiveForApp$25(i, (Void) obj, (Throwable) obj2);
            }
        });
    }

    static /* synthetic */ CompletableFuture lambda$updateUserSensitiveForApp$24(int i, IPermissionController iPermissionController) throws Exception {
        AndroidFuture androidFuture = new AndroidFuture();
        iPermissionController.updateUserSensitiveForApp(i, androidFuture);
        return androidFuture;
    }

    static /* synthetic */ void lambda$updateUserSensitiveForApp$25(int i, Void r3, Throwable th) {
        if (th != null) {
            Log.e(TAG, "Error updating user_sensitive flags for uid " + i, th);
        }
    }

    public void notifyOneTimePermissionSessionTimeout(final String str) {
        this.mRemoteService.run(new ServiceConnector.VoidJob() { // from class: android.permission.-$$Lambda$PermissionControllerManager$MqYq3LpSVm-2tnlerpH7tgxg0Us
            @Override // com.android.internal.infra.ServiceConnector.VoidJob
            public final void runNoResult(Object obj) throws RemoteException {
                ((IPermissionController) obj).notifyOneTimePermissionSessionTimeout(str);
            }
        });
    }
}
