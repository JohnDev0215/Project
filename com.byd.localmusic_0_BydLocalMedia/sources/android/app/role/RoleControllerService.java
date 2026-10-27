package android.app.role;

import android.Manifest;
import android.annotation.SystemApi;
import android.app.Service;
import android.app.role.IRoleController;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.RemoteCallback;
import com.android.internal.util.Preconditions;
import com.android.internal.util.function.QuadConsumer;
import com.android.internal.util.function.QuintConsumer;
import com.android.internal.util.function.pooled.PooledLambda;
import java.util.Objects;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: classes.dex */
@SystemApi
public abstract class RoleControllerService extends Service {
    public static final String SERVICE_INTERFACE = "android.app.role.RoleControllerService";
    private Handler mWorkerHandler;
    private HandlerThread mWorkerThread;

    public abstract boolean onAddRoleHolder(String str, String str2, int i);

    public abstract boolean onClearRoleHolders(String str, int i);

    public abstract boolean onGrantDefaultRoles();

    public abstract boolean onIsApplicationQualifiedForRole(String str, String str2);

    public abstract boolean onIsRoleVisible(String str);

    public abstract boolean onRemoveRoleHolder(String str, String str2, int i);

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        HandlerThread handlerThread = new HandlerThread(RoleControllerService.class.getSimpleName());
        this.mWorkerThread = handlerThread;
        handlerThread.start();
        this.mWorkerHandler = new Handler(this.mWorkerThread.getLooper());
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.mWorkerThread.quitSafely();
    }

    /* JADX INFO: renamed from: android.app.role.RoleControllerService$1, reason: invalid class name */
    class AnonymousClass1 extends IRoleController.Stub {
        AnonymousClass1() {
        }

        @Override // android.app.role.IRoleController
        public void grantDefaultRoles(RemoteCallback remoteCallback) {
            enforceCallerSystemUid("grantDefaultRoles");
            Objects.requireNonNull(remoteCallback, "callback cannot be null");
            RoleControllerService.this.mWorkerHandler.sendMessage(PooledLambda.obtainMessage(new BiConsumer() { // from class: android.app.role.-$$Lambda$RoleControllerService$1$-fmj7uDKaG3BoLl6bhtrA675gRI
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((RoleControllerService) obj).grantDefaultRoles((RemoteCallback) obj2);
                }
            }, RoleControllerService.this, remoteCallback));
        }

        @Override // android.app.role.IRoleController
        public void onAddRoleHolder(String str, String str2, int i, RemoteCallback remoteCallback) {
            enforceCallerSystemUid("onAddRoleHolder");
            Preconditions.checkStringNotEmpty(str, "roleName cannot be null or empty");
            Preconditions.checkStringNotEmpty(str2, "packageName cannot be null or empty");
            Objects.requireNonNull(remoteCallback, "callback cannot be null");
            RoleControllerService.this.mWorkerHandler.sendMessage(PooledLambda.obtainMessage(new QuintConsumer() { // from class: android.app.role.-$$Lambda$RoleControllerService$1$UVI1sAWAcBnt3Enqn2IT-Lirwtk
                @Override // com.android.internal.util.function.QuintConsumer
                public final void accept(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
                    ((RoleControllerService) obj).onAddRoleHolder((String) obj2, (String) obj3, ((Integer) obj4).intValue(), (RemoteCallback) obj5);
                }
            }, RoleControllerService.this, str, str2, Integer.valueOf(i), remoteCallback));
        }

        @Override // android.app.role.IRoleController
        public void onRemoveRoleHolder(String str, String str2, int i, RemoteCallback remoteCallback) {
            enforceCallerSystemUid("onRemoveRoleHolder");
            Preconditions.checkStringNotEmpty(str, "roleName cannot be null or empty");
            Preconditions.checkStringNotEmpty(str2, "packageName cannot be null or empty");
            Objects.requireNonNull(remoteCallback, "callback cannot be null");
            RoleControllerService.this.mWorkerHandler.sendMessage(PooledLambda.obtainMessage(new QuintConsumer() { // from class: android.app.role.-$$Lambda$RoleControllerService$1$PB6H1df6VvLzUJ3hhB_75mN3u7s
                @Override // com.android.internal.util.function.QuintConsumer
                public final void accept(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
                    ((RoleControllerService) obj).onRemoveRoleHolder((String) obj2, (String) obj3, ((Integer) obj4).intValue(), (RemoteCallback) obj5);
                }
            }, RoleControllerService.this, str, str2, Integer.valueOf(i), remoteCallback));
        }

        @Override // android.app.role.IRoleController
        public void onClearRoleHolders(String str, int i, RemoteCallback remoteCallback) {
            enforceCallerSystemUid("onClearRoleHolders");
            Preconditions.checkStringNotEmpty(str, "roleName cannot be null or empty");
            Objects.requireNonNull(remoteCallback, "callback cannot be null");
            RoleControllerService.this.mWorkerHandler.sendMessage(PooledLambda.obtainMessage(new QuadConsumer() { // from class: android.app.role.-$$Lambda$RoleControllerService$1$dBm1t_MGyEA9yMAxoOUMOhYVmPo
                @Override // com.android.internal.util.function.QuadConsumer
                public final void accept(Object obj, Object obj2, Object obj3, Object obj4) {
                    ((RoleControllerService) obj).onClearRoleHolders((String) obj2, ((Integer) obj3).intValue(), (RemoteCallback) obj4);
                }
            }, RoleControllerService.this, str, Integer.valueOf(i), remoteCallback));
        }

        private void enforceCallerSystemUid(String str) {
            if (Binder.getCallingUid() == 1000) {
                return;
            }
            throw new SecurityException("Only the system process can call " + str + "()");
        }

        @Override // android.app.role.IRoleController
        public void isApplicationQualifiedForRole(String str, String str2, RemoteCallback remoteCallback) {
            RoleControllerService.this.enforceCallingPermission(Manifest.permission.MANAGE_ROLE_HOLDERS, null);
            Preconditions.checkStringNotEmpty(str, "roleName cannot be null or empty");
            Preconditions.checkStringNotEmpty(str2, "packageName cannot be null or empty");
            Objects.requireNonNull(remoteCallback, "callback cannot be null");
            remoteCallback.sendResult(RoleControllerService.this.onIsApplicationQualifiedForRole(str, str2) ? Bundle.EMPTY : null);
        }

        @Override // android.app.role.IRoleController
        public void isApplicationVisibleForRole(String str, String str2, RemoteCallback remoteCallback) {
            RoleControllerService.this.enforceCallingPermission(Manifest.permission.MANAGE_ROLE_HOLDERS, null);
            Preconditions.checkStringNotEmpty(str, "roleName cannot be null or empty");
            Preconditions.checkStringNotEmpty(str2, "packageName cannot be null or empty");
            Objects.requireNonNull(remoteCallback, "callback cannot be null");
            remoteCallback.sendResult(RoleControllerService.this.onIsApplicationVisibleForRole(str, str2) ? Bundle.EMPTY : null);
        }

        @Override // android.app.role.IRoleController
        public void isRoleVisible(String str, RemoteCallback remoteCallback) {
            RoleControllerService.this.enforceCallingPermission(Manifest.permission.MANAGE_ROLE_HOLDERS, null);
            Preconditions.checkStringNotEmpty(str, "roleName cannot be null or empty");
            Objects.requireNonNull(remoteCallback, "callback cannot be null");
            remoteCallback.sendResult(RoleControllerService.this.onIsRoleVisible(str) ? Bundle.EMPTY : null);
        }
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return new AnonymousClass1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void grantDefaultRoles(RemoteCallback remoteCallback) {
        remoteCallback.sendResult(onGrantDefaultRoles() ? Bundle.EMPTY : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAddRoleHolder(String str, String str2, int i, RemoteCallback remoteCallback) {
        remoteCallback.sendResult(onAddRoleHolder(str, str2, i) ? Bundle.EMPTY : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRemoveRoleHolder(String str, String str2, int i, RemoteCallback remoteCallback) {
        remoteCallback.sendResult(onRemoveRoleHolder(str, str2, i) ? Bundle.EMPTY : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onClearRoleHolders(String str, int i, RemoteCallback remoteCallback) {
        remoteCallback.sendResult(onClearRoleHolders(str, i) ? Bundle.EMPTY : null);
    }

    public boolean onIsApplicationVisibleForRole(String str, String str2) {
        return onIsApplicationQualifiedForRole(str, str2);
    }
}
