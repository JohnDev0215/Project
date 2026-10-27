package android.app.role;

import android.app.ActivityThread;
import android.app.role.IRoleController;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteCallback;
import android.util.Log;
import android.util.SparseArray;
import com.android.internal.infra.AndroidFuture;
import com.android.internal.infra.ServiceConnector;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: classes.dex */
public class RoleControllerManager {
    private static final String LOG_TAG = "RoleControllerManager";
    private static final long REQUEST_TIMEOUT_MILLIS = 15000;
    private static volatile ComponentName sRemoteServiceComponentName;
    private final ServiceConnector<IRoleController> mRemoteService;
    private static final Object sRemoteServicesLock = new Object();
    private static final SparseArray<ServiceConnector<IRoleController>> sRemoteServices = new SparseArray<>();

    public static void initializeRemoteServiceComponentName(Context context) {
        sRemoteServiceComponentName = getRemoteServiceComponentName(context);
    }

    public static RoleControllerManager createWithInitializedRemoteServiceComponentName(Handler handler, Context context) {
        return new RoleControllerManager(sRemoteServiceComponentName, handler, context);
    }

    private RoleControllerManager(ComponentName componentName, final Handler handler, Context context) {
        synchronized (sRemoteServicesLock) {
            int userId = context.getUserId();
            SparseArray<ServiceConnector<IRoleController>> sparseArray = sRemoteServices;
            ServiceConnector<IRoleController> serviceConnector = sparseArray.get(userId);
            if (serviceConnector == null) {
                ServiceConnector.Impl<IRoleController> impl = new ServiceConnector.Impl<IRoleController>(ActivityThread.currentApplication(), new Intent(RoleControllerService.SERVICE_INTERFACE).setComponent(componentName), 0, userId, new Function() { // from class: android.app.role.-$$Lambda$CQhA4ZPg2g3ruvW6iubVBmv43UE
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return IRoleController.Stub.asInterface((IBinder) obj);
                    }
                }) { // from class: android.app.role.RoleControllerManager.1
                    @Override // com.android.internal.infra.ServiceConnector.Impl
                    protected Handler getJobHandler() {
                        return handler;
                    }
                };
                sparseArray.put(userId, impl);
                serviceConnector = impl;
            }
            this.mRemoteService = serviceConnector;
        }
    }

    public RoleControllerManager(Context context) {
        this(getRemoteServiceComponentName(context), context.getMainThreadHandler(), context);
    }

    private static ComponentName getRemoteServiceComponentName(Context context) {
        Intent intent = new Intent(RoleControllerService.SERVICE_INTERFACE);
        PackageManager packageManager = context.getPackageManager();
        intent.setPackage(packageManager.getPermissionControllerPackageName());
        return packageManager.resolveService(intent, 0).getComponentInfo().getComponentName();
    }

    public void grantDefaultRoles(Executor executor, Consumer<Boolean> consumer) {
        propagateCallback(this.mRemoteService.postAsync(new ServiceConnector.Job() { // from class: android.app.role.-$$Lambda$RoleControllerManager$3NMUz6ZBN-bqbmb4t84VnEZKk2Q
            @Override // com.android.internal.infra.ServiceConnector.Job
            public final Object run(Object obj) {
                return RoleControllerManager.lambda$grantDefaultRoles$0((IRoleController) obj);
            }
        }), "grantDefaultRoles", executor, consumer);
    }

    static /* synthetic */ CompletableFuture lambda$grantDefaultRoles$0(IRoleController iRoleController) throws Exception {
        AndroidFuture androidFuture = new AndroidFuture();
        Objects.requireNonNull(androidFuture);
        iRoleController.grantDefaultRoles(new RemoteCallback(new $$Lambda$ng3HLBbY6a0mKtsl6yiBYZ8Gkg(androidFuture)));
        return androidFuture;
    }

    public void onAddRoleHolder(final String str, final String str2, final int i, RemoteCallback remoteCallback) {
        propagateCallback(this.mRemoteService.postAsync(new ServiceConnector.Job() { // from class: android.app.role.-$$Lambda$RoleControllerManager$vc0SQYvAeoxz8MIh3V6rOMT4Vng
            @Override // com.android.internal.infra.ServiceConnector.Job
            public final Object run(Object obj) {
                return RoleControllerManager.lambda$onAddRoleHolder$1(str, str2, i, (IRoleController) obj);
            }
        }), "onAddRoleHolder", remoteCallback);
    }

    static /* synthetic */ CompletableFuture lambda$onAddRoleHolder$1(String str, String str2, int i, IRoleController iRoleController) throws Exception {
        AndroidFuture androidFuture = new AndroidFuture();
        Objects.requireNonNull(androidFuture);
        iRoleController.onAddRoleHolder(str, str2, i, new RemoteCallback(new $$Lambda$ng3HLBbY6a0mKtsl6yiBYZ8Gkg(androidFuture)));
        return androidFuture;
    }

    public void onRemoveRoleHolder(final String str, final String str2, final int i, RemoteCallback remoteCallback) {
        propagateCallback(this.mRemoteService.postAsync(new ServiceConnector.Job() { // from class: android.app.role.-$$Lambda$RoleControllerManager$irAzHpt671c8Y9o3wYm8VV249nA
            @Override // com.android.internal.infra.ServiceConnector.Job
            public final Object run(Object obj) {
                return RoleControllerManager.lambda$onRemoveRoleHolder$2(str, str2, i, (IRoleController) obj);
            }
        }), "onRemoveRoleHolder", remoteCallback);
    }

    static /* synthetic */ CompletableFuture lambda$onRemoveRoleHolder$2(String str, String str2, int i, IRoleController iRoleController) throws Exception {
        AndroidFuture androidFuture = new AndroidFuture();
        Objects.requireNonNull(androidFuture);
        iRoleController.onRemoveRoleHolder(str, str2, i, new RemoteCallback(new $$Lambda$ng3HLBbY6a0mKtsl6yiBYZ8Gkg(androidFuture)));
        return androidFuture;
    }

    public void onClearRoleHolders(final String str, final int i, RemoteCallback remoteCallback) {
        propagateCallback(this.mRemoteService.postAsync(new ServiceConnector.Job() { // from class: android.app.role.-$$Lambda$RoleControllerManager$-OU_gEmhwnbe01tno2EjmTI-eiY
            @Override // com.android.internal.infra.ServiceConnector.Job
            public final Object run(Object obj) {
                return RoleControllerManager.lambda$onClearRoleHolders$3(str, i, (IRoleController) obj);
            }
        }), "onClearRoleHolders", remoteCallback);
    }

    static /* synthetic */ CompletableFuture lambda$onClearRoleHolders$3(String str, int i, IRoleController iRoleController) throws Exception {
        AndroidFuture androidFuture = new AndroidFuture();
        Objects.requireNonNull(androidFuture);
        iRoleController.onClearRoleHolders(str, i, new RemoteCallback(new $$Lambda$ng3HLBbY6a0mKtsl6yiBYZ8Gkg(androidFuture)));
        return androidFuture;
    }

    public void isApplicationQualifiedForRole(final String str, final String str2, Executor executor, Consumer<Boolean> consumer) {
        propagateCallback(this.mRemoteService.postAsync(new ServiceConnector.Job() { // from class: android.app.role.-$$Lambda$RoleControllerManager$3rnzWlakyQzrGwPZC_5jkCawE6U
            @Override // com.android.internal.infra.ServiceConnector.Job
            public final Object run(Object obj) {
                return RoleControllerManager.lambda$isApplicationQualifiedForRole$4(str, str2, (IRoleController) obj);
            }
        }), "isApplicationQualifiedForRole", executor, consumer);
    }

    static /* synthetic */ CompletableFuture lambda$isApplicationQualifiedForRole$4(String str, String str2, IRoleController iRoleController) throws Exception {
        AndroidFuture androidFuture = new AndroidFuture();
        Objects.requireNonNull(androidFuture);
        iRoleController.isApplicationQualifiedForRole(str, str2, new RemoteCallback(new $$Lambda$ng3HLBbY6a0mKtsl6yiBYZ8Gkg(androidFuture)));
        return androidFuture;
    }

    public void isApplicationVisibleForRole(final String str, final String str2, Executor executor, Consumer<Boolean> consumer) {
        propagateCallback(this.mRemoteService.postAsync(new ServiceConnector.Job() { // from class: android.app.role.-$$Lambda$RoleControllerManager$Y-JOMSpFQ1NbFsZ73KjOP3-Onbo
            @Override // com.android.internal.infra.ServiceConnector.Job
            public final Object run(Object obj) {
                return RoleControllerManager.lambda$isApplicationVisibleForRole$5(str, str2, (IRoleController) obj);
            }
        }), "isApplicationVisibleForRole", executor, consumer);
    }

    static /* synthetic */ CompletableFuture lambda$isApplicationVisibleForRole$5(String str, String str2, IRoleController iRoleController) throws Exception {
        AndroidFuture androidFuture = new AndroidFuture();
        Objects.requireNonNull(androidFuture);
        iRoleController.isApplicationVisibleForRole(str, str2, new RemoteCallback(new $$Lambda$ng3HLBbY6a0mKtsl6yiBYZ8Gkg(androidFuture)));
        return androidFuture;
    }

    public void isRoleVisible(final String str, Executor executor, Consumer<Boolean> consumer) {
        propagateCallback(this.mRemoteService.postAsync(new ServiceConnector.Job() { // from class: android.app.role.-$$Lambda$RoleControllerManager$4HMVNVi2n_I2cEWuGoWphVf6JX0
            @Override // com.android.internal.infra.ServiceConnector.Job
            public final Object run(Object obj) {
                return RoleControllerManager.lambda$isRoleVisible$6(str, (IRoleController) obj);
            }
        }), "isRoleVisible", executor, consumer);
    }

    static /* synthetic */ CompletableFuture lambda$isRoleVisible$6(String str, IRoleController iRoleController) throws Exception {
        AndroidFuture androidFuture = new AndroidFuture();
        Objects.requireNonNull(androidFuture);
        iRoleController.isRoleVisible(str, new RemoteCallback(new $$Lambda$ng3HLBbY6a0mKtsl6yiBYZ8Gkg(androidFuture)));
        return androidFuture;
    }

    private void propagateCallback(AndroidFuture<Bundle> androidFuture, final String str, final Executor executor, final Consumer<Boolean> consumer) {
        androidFuture.orTimeout(REQUEST_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS).whenComplete(new BiConsumer() { // from class: android.app.role.-$$Lambda$RoleControllerManager$hgevFDUi7EVgH1XwtsCDSA04Jhk
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                executor.execute(new Runnable() { // from class: android.app.role.-$$Lambda$RoleControllerManager$nCcY1in9U0v-z7bA98LMVYjUe7s
                    @Override // java.lang.Runnable
                    public final void run() {
                        RoleControllerManager.lambda$propagateCallback$7(th, str, consumer, bundle);
                    }
                });
            }
        });
    }

    static /* synthetic */ void lambda$propagateCallback$7(Throwable th, String str, Consumer consumer, Bundle bundle) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (th != null) {
                Log.e(LOG_TAG, "Error calling " + str + "()", th);
                consumer.accept(false);
            } else {
                consumer.accept(Boolean.valueOf(bundle != null));
            }
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        } catch (Throwable th2) {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
            throw th2;
        }
    }

    private void propagateCallback(AndroidFuture<Bundle> androidFuture, final String str, final RemoteCallback remoteCallback) {
        androidFuture.orTimeout(REQUEST_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS).whenComplete(new BiConsumer() { // from class: android.app.role.-$$Lambda$RoleControllerManager$zUoKoK42yvUuMsrWmoxQH_a09PQ
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                RoleControllerManager.lambda$propagateCallback$9(str, remoteCallback, (Bundle) obj, (Throwable) obj2);
            }
        });
    }

    static /* synthetic */ void lambda$propagateCallback$9(String str, RemoteCallback remoteCallback, Bundle bundle, Throwable th) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (th != null) {
                Log.e(LOG_TAG, "Error calling " + str + "()", th);
                remoteCallback.sendResult(null);
            } else {
                remoteCallback.sendResult(bundle);
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }
}
