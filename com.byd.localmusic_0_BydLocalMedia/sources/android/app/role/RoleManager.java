package android.app.role;

import android.annotation.SystemApi;
import android.app.role.IOnRoleHoldersChangedListener;
import android.app.role.IRoleManager;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Process;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.util.ArrayMap;
import android.util.SparseArray;
import com.android.internal.util.Preconditions;
import com.android.internal.util.function.TriConsumer;
import com.android.internal.util.function.pooled.PooledLambda;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public final class RoleManager {
    public static final String ACTION_REQUEST_ROLE = "android.app.role.action.REQUEST_ROLE";
    private static final String LOG_TAG = RoleManager.class.getSimpleName();

    @SystemApi
    public static final int MANAGE_HOLDERS_FLAG_DONT_KILL_APP = 1;
    public static final String PERMISSION_MANAGE_ROLES_FROM_CONTROLLER = "com.android.permissioncontroller.permission.MANAGE_ROLES_FROM_CONTROLLER";
    public static final String ROLE_ASSISTANT = "android.app.role.ASSISTANT";
    public static final String ROLE_BROWSER = "android.app.role.BROWSER";
    public static final String ROLE_CALL_REDIRECTION = "android.app.role.CALL_REDIRECTION";
    public static final String ROLE_CALL_SCREENING = "android.app.role.CALL_SCREENING";
    public static final String ROLE_DIALER = "android.app.role.DIALER";
    public static final String ROLE_EMERGENCY = "android.app.role.EMERGENCY";
    public static final String ROLE_HOME = "android.app.role.HOME";
    public static final String ROLE_SMS = "android.app.role.SMS";
    private final Context mContext;
    private final SparseArray<ArrayMap<OnRoleHoldersChangedListener, OnRoleHoldersChangedListenerDelegate>> mListeners = new SparseArray<>();
    private final Object mListenersLock = new Object();
    private final IRoleManager mService = IRoleManager.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.ROLE_SERVICE));

    public @interface ManageHoldersFlags {
    }

    public RoleManager(Context context) throws ServiceManager.ServiceNotFoundException {
        this.mContext = context;
    }

    public Intent createRequestRoleIntent(String str) {
        Preconditions.checkStringNotEmpty(str, "roleName cannot be null or empty");
        Intent intent = new Intent(ACTION_REQUEST_ROLE);
        intent.setPackage(this.mContext.getPackageManager().getPermissionControllerPackageName());
        intent.putExtra(Intent.EXTRA_ROLE_NAME, str);
        return intent;
    }

    public boolean isRoleAvailable(String str) {
        Preconditions.checkStringNotEmpty(str, "roleName cannot be null or empty");
        try {
            return this.mService.isRoleAvailable(str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isRoleHeld(String str) {
        Preconditions.checkStringNotEmpty(str, "roleName cannot be null or empty");
        try {
            return this.mService.isRoleHeld(str, this.mContext.getPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public List<String> getRoleHolders(String str) {
        return getRoleHoldersAsUser(str, Process.myUserHandle());
    }

    @SystemApi
    public List<String> getRoleHoldersAsUser(String str, UserHandle userHandle) {
        Preconditions.checkStringNotEmpty(str, "roleName cannot be null or empty");
        Objects.requireNonNull(userHandle, "user cannot be null");
        try {
            return this.mService.getRoleHoldersAsUser(str, userHandle.getIdentifier());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void addRoleHolderAsUser(String str, String str2, int i, UserHandle userHandle, Executor executor, Consumer<Boolean> consumer) {
        Preconditions.checkStringNotEmpty(str, "roleName cannot be null or empty");
        Preconditions.checkStringNotEmpty(str2, "packageName cannot be null or empty");
        Objects.requireNonNull(userHandle, "user cannot be null");
        Objects.requireNonNull(executor, "executor cannot be null");
        Objects.requireNonNull(consumer, "callback cannot be null");
        try {
            this.mService.addRoleHolderAsUser(str, str2, i, userHandle.getIdentifier(), createRemoteCallback(executor, consumer));
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void removeRoleHolderAsUser(String str, String str2, int i, UserHandle userHandle, Executor executor, Consumer<Boolean> consumer) {
        Preconditions.checkStringNotEmpty(str, "roleName cannot be null or empty");
        Preconditions.checkStringNotEmpty(str2, "packageName cannot be null or empty");
        Objects.requireNonNull(userHandle, "user cannot be null");
        Objects.requireNonNull(executor, "executor cannot be null");
        Objects.requireNonNull(consumer, "callback cannot be null");
        try {
            this.mService.removeRoleHolderAsUser(str, str2, i, userHandle.getIdentifier(), createRemoteCallback(executor, consumer));
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void clearRoleHoldersAsUser(String str, int i, UserHandle userHandle, Executor executor, Consumer<Boolean> consumer) {
        Preconditions.checkStringNotEmpty(str, "roleName cannot be null or empty");
        Objects.requireNonNull(userHandle, "user cannot be null");
        Objects.requireNonNull(executor, "executor cannot be null");
        Objects.requireNonNull(consumer, "callback cannot be null");
        try {
            this.mService.clearRoleHoldersAsUser(str, i, userHandle.getIdentifier(), createRemoteCallback(executor, consumer));
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private static RemoteCallback createRemoteCallback(final Executor executor, final Consumer<Boolean> consumer) {
        return new RemoteCallback(new RemoteCallback.OnResultListener() { // from class: android.app.role.-$$Lambda$RoleManager$m9y_ZqrQy4gHK3mGDXvG129sdjU
            @Override // android.os.RemoteCallback.OnResultListener
            public final void onResult(Bundle bundle) {
                executor.execute(new Runnable() { // from class: android.app.role.-$$Lambda$RoleManager$DrSVQgbDoLZaqkfPdGzAK3BvOGQ
                    @Override // java.lang.Runnable
                    public final void run() {
                        RoleManager.lambda$createRemoteCallback$0(bundle, consumer);
                    }
                });
            }
        });
    }

    static /* synthetic */ void lambda$createRemoteCallback$0(Bundle bundle, Consumer consumer) {
        boolean z = bundle != null;
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            consumer.accept(Boolean.valueOf(z));
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    @SystemApi
    public void addOnRoleHoldersChangedListenerAsUser(Executor executor, OnRoleHoldersChangedListener onRoleHoldersChangedListener, UserHandle userHandle) {
        Objects.requireNonNull(executor, "executor cannot be null");
        Objects.requireNonNull(onRoleHoldersChangedListener, "listener cannot be null");
        Objects.requireNonNull(userHandle, "user cannot be null");
        int identifier = userHandle.getIdentifier();
        synchronized (this.mListenersLock) {
            ArrayMap<OnRoleHoldersChangedListener, OnRoleHoldersChangedListenerDelegate> arrayMap = this.mListeners.get(identifier);
            if (arrayMap == null) {
                arrayMap = new ArrayMap<>();
                this.mListeners.put(identifier, arrayMap);
            } else if (arrayMap.containsKey(onRoleHoldersChangedListener)) {
                return;
            }
            OnRoleHoldersChangedListenerDelegate onRoleHoldersChangedListenerDelegate = new OnRoleHoldersChangedListenerDelegate(executor, onRoleHoldersChangedListener);
            try {
                this.mService.addOnRoleHoldersChangedListenerAsUser(onRoleHoldersChangedListenerDelegate, identifier);
                arrayMap.put(onRoleHoldersChangedListener, onRoleHoldersChangedListenerDelegate);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    @SystemApi
    public void removeOnRoleHoldersChangedListenerAsUser(OnRoleHoldersChangedListener onRoleHoldersChangedListener, UserHandle userHandle) {
        Objects.requireNonNull(onRoleHoldersChangedListener, "listener cannot be null");
        Objects.requireNonNull(userHandle, "user cannot be null");
        int identifier = userHandle.getIdentifier();
        synchronized (this.mListenersLock) {
            ArrayMap<OnRoleHoldersChangedListener, OnRoleHoldersChangedListenerDelegate> arrayMap = this.mListeners.get(identifier);
            if (arrayMap == null) {
                return;
            }
            OnRoleHoldersChangedListenerDelegate onRoleHoldersChangedListenerDelegate = arrayMap.get(onRoleHoldersChangedListener);
            if (onRoleHoldersChangedListenerDelegate == null) {
                return;
            }
            try {
                this.mService.removeOnRoleHoldersChangedListenerAsUser(onRoleHoldersChangedListenerDelegate, userHandle.getIdentifier());
                arrayMap.remove(onRoleHoldersChangedListener);
                if (arrayMap.isEmpty()) {
                    this.mListeners.remove(identifier);
                }
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    @SystemApi
    public void setRoleNamesFromController(List<String> list) {
        Objects.requireNonNull(list, "roleNames cannot be null");
        try {
            this.mService.setRoleNamesFromController(list);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean addRoleHolderFromController(String str, String str2) {
        Preconditions.checkStringNotEmpty(str, "roleName cannot be null or empty");
        Preconditions.checkStringNotEmpty(str2, "packageName cannot be null or empty");
        try {
            return this.mService.addRoleHolderFromController(str, str2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean removeRoleHolderFromController(String str, String str2) {
        Preconditions.checkStringNotEmpty(str, "roleName cannot be null or empty");
        Preconditions.checkStringNotEmpty(str2, "packageName cannot be null or empty");
        try {
            return this.mService.removeRoleHolderFromController(str, str2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public List<String> getHeldRolesFromController(String str) {
        Preconditions.checkStringNotEmpty(str, "packageName cannot be null or empty");
        try {
            return this.mService.getHeldRolesFromController(str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public String getDefaultSmsPackage(int i) {
        try {
            return this.mService.getDefaultSmsPackage(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private static class OnRoleHoldersChangedListenerDelegate extends IOnRoleHoldersChangedListener.Stub {
        private final Executor mExecutor;
        private final OnRoleHoldersChangedListener mListener;

        OnRoleHoldersChangedListenerDelegate(Executor executor, OnRoleHoldersChangedListener onRoleHoldersChangedListener) {
            this.mExecutor = executor;
            this.mListener = onRoleHoldersChangedListener;
        }

        @Override // android.app.role.IOnRoleHoldersChangedListener
        public void onRoleHoldersChanged(String str, int i) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                this.mExecutor.execute(PooledLambda.obtainRunnable(new TriConsumer() { // from class: android.app.role.-$$Lambda$o94o2jK_ei-IVw-3oY_QJ49zpAA
                    @Override // com.android.internal.util.function.TriConsumer
                    public final void accept(Object obj, Object obj2, Object obj3) {
                        ((OnRoleHoldersChangedListener) obj).onRoleHoldersChanged((String) obj2, (UserHandle) obj3);
                    }
                }, this.mListener, str, UserHandle.of(i)));
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }
}
