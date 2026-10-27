package org.chromium.content.browser;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.RemoteException;
import java.io.IOException;
import org.chromium.base.Log;
import org.chromium.base.TraceEvent;
import org.chromium.content.app.ChromiumLinkerParams;
import org.chromium.content.browser.ChildProcessConnection;
import org.chromium.content.common.IChildProcessCallback;
import org.chromium.content.common.IChildProcessService;

/* JADX INFO: loaded from: classes3.dex */
public class ChildProcessConnectionImpl implements ChildProcessConnection {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "ChildProcessConnect";
    private static Boolean[] sNeedsExtrabindFlags = new Boolean[2];
    private final boolean mAlwaysInForeground;
    private ChildProcessConnection.ConnectionCallback mConnectionCallback;
    private ConnectionParams mConnectionParams;
    private final Context mContext;
    private final ChildProcessCreationParams mCreationParams;
    private final ChildProcessConnection.DeathCallback mDeathCallback;
    private final boolean mInSandbox;
    private ChildServiceConnection mInitialBinding;
    private ChromiumLinkerParams mLinkerParams;
    private ChildServiceConnection mModerateBinding;
    private final ComponentName mServiceName;
    private final int mServiceNumber;
    private ChildServiceConnection mStrongBinding;
    private ChildServiceConnection mWaivedBinding;
    private final Object mLock = new Object();
    private IChildProcessService mService = null;
    private boolean mServiceConnectComplete = false;
    private boolean mServiceDisconnected = false;
    private boolean mWasOomProtected = false;
    private int mPid = 0;
    private int mStrongBindingCount = 0;

    private static class ConnectionParams {
        final IChildProcessCallback mCallback;
        final String[] mCommandLine;
        final FileDescriptorInfo[] mFilesToBeMapped;
        final Bundle mSharedRelros;

        ConnectionParams(String[] strArr, FileDescriptorInfo[] fileDescriptorInfoArr, IChildProcessCallback iChildProcessCallback, Bundle bundle) {
            this.mCommandLine = strArr;
            this.mFilesToBeMapped = fileDescriptorInfoArr;
            this.mCallback = iChildProcessCallback;
            this.mSharedRelros = bundle;
        }
    }

    private class ChildServiceConnection implements ServiceConnection {
        private final int mBindFlags;
        private boolean mBound = false;

        private Intent createServiceBindIntent() {
            Intent intent = new Intent();
            if (ChildProcessConnectionImpl.this.mCreationParams != null) {
                ChildProcessConnectionImpl.this.mCreationParams.addIntentExtras(intent);
            }
            intent.setComponent(ChildProcessConnectionImpl.this.mServiceName);
            return intent;
        }

        public ChildServiceConnection(int i, boolean z) {
            if (z && ChildProcessConnectionImpl.this.mCreationParams != null) {
                i = ChildProcessConnectionImpl.this.mCreationParams.addExtraBindFlags(i);
            }
            this.mBindFlags = i;
        }

        boolean bind(String[] strArr) {
            if (!this.mBound) {
                try {
                    TraceEvent.begin("ChildProcessConnectionImpl.ChildServiceConnection.bind");
                    Intent intentCreateServiceBindIntent = createServiceBindIntent();
                    if (strArr != null) {
                        intentCreateServiceBindIntent.putExtra(ChildProcessConstants.EXTRA_COMMAND_LINE, strArr);
                    }
                    if (ChildProcessConnectionImpl.this.mLinkerParams != null) {
                        ChildProcessConnectionImpl.this.mLinkerParams.addIntentExtras(intentCreateServiceBindIntent);
                    }
                    this.mBound = ChildProcessConnectionImpl.this.mContext.bindService(intentCreateServiceBindIntent, this, this.mBindFlags);
                } finally {
                    TraceEvent.end("ChildProcessConnectionImpl.ChildServiceConnection.bind");
                }
            }
            return this.mBound;
        }

        void unbind() {
            if (this.mBound) {
                ChildProcessConnectionImpl.this.mContext.unbindService(this);
                this.mBound = false;
            }
        }

        boolean isBound() {
            return this.mBound;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            synchronized (ChildProcessConnectionImpl.this.mLock) {
                if (ChildProcessConnectionImpl.this.mServiceConnectComplete) {
                    return;
                }
                try {
                    TraceEvent.begin("ChildProcessConnectionImpl.ChildServiceConnection.onServiceConnected");
                    ChildProcessConnectionImpl.this.mServiceConnectComplete = true;
                    ChildProcessConnectionImpl.this.mService = IChildProcessService.Stub.asInterface(iBinder);
                    if (ChildProcessConnectionImpl.this.mConnectionParams != null) {
                        ChildProcessConnectionImpl.this.doConnectionSetupLocked();
                    }
                } finally {
                    TraceEvent.end("ChildProcessConnectionImpl.ChildServiceConnection.onServiceConnected");
                }
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            synchronized (ChildProcessConnectionImpl.this.mLock) {
                if (ChildProcessConnectionImpl.this.mServiceDisconnected) {
                    return;
                }
                ChildProcessConnectionImpl childProcessConnectionImpl = ChildProcessConnectionImpl.this;
                childProcessConnectionImpl.mWasOomProtected = childProcessConnectionImpl.isCurrentlyOomProtected();
                ChildProcessConnectionImpl.this.mServiceDisconnected = true;
                Log.w(ChildProcessConnectionImpl.TAG, "onServiceDisconnected (crash or killed by oom): pid=%d", Integer.valueOf(ChildProcessConnectionImpl.this.mPid));
                ChildProcessConnectionImpl.this.stop();
                ChildProcessConnectionImpl.this.mDeathCallback.onChildProcessDied(ChildProcessConnectionImpl.this);
                if (ChildProcessConnectionImpl.this.mConnectionCallback != null) {
                    ChildProcessConnectionImpl.this.mConnectionCallback.onConnected(0);
                }
                ChildProcessConnectionImpl.this.mConnectionCallback = null;
            }
        }
    }

    ChildProcessConnectionImpl(Context context, int i, boolean z, ChildProcessConnection.DeathCallback deathCallback, String str, ChromiumLinkerParams chromiumLinkerParams, boolean z2, ChildProcessCreationParams childProcessCreationParams) {
        this.mInitialBinding = null;
        this.mStrongBinding = null;
        this.mWaivedBinding = null;
        this.mModerateBinding = null;
        this.mLinkerParams = null;
        this.mContext = context;
        this.mServiceNumber = i;
        this.mInSandbox = z;
        this.mDeathCallback = deathCallback;
        ComponentName componentName = new ComponentName(childProcessCreationParams != null ? childProcessCreationParams.getPackageName() : context.getPackageName(), str + i);
        this.mServiceName = componentName;
        this.mLinkerParams = chromiumLinkerParams;
        this.mAlwaysInForeground = z2;
        this.mCreationParams = childProcessCreationParams;
        int i2 = z2 ? 65 : 1;
        boolean zIsExportedService = isExportedService(z, context, componentName);
        this.mInitialBinding = new ChildServiceConnection(i2, zIsExportedService);
        this.mStrongBinding = new ChildServiceConnection(65, zIsExportedService);
        this.mWaivedBinding = new ChildServiceConnection(33, zIsExportedService);
        this.mModerateBinding = new ChildServiceConnection(1, zIsExportedService);
    }

    private static boolean isExportedService(boolean z, Context context, ComponentName componentName) {
        Boolean bool = sNeedsExtrabindFlags[z ? 1 : 0];
        if (bool != null) {
            return bool.booleanValue();
        }
        boolean z2 = false;
        try {
            z2 = context.getPackageManager().getServiceInfo(componentName, 0).exported;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Could not retrieve info about service %s", componentName, e);
        }
        sNeedsExtrabindFlags[z ? 1 : 0] = Boolean.valueOf(z2);
        return z2;
    }

    @Override // org.chromium.content.browser.ChildProcessConnection
    public int getServiceNumber() {
        return this.mServiceNumber;
    }

    @Override // org.chromium.content.browser.ChildProcessConnection
    public boolean isInSandbox() {
        return this.mInSandbox;
    }

    @Override // org.chromium.content.browser.ChildProcessConnection
    public String getPackageName() {
        ChildProcessCreationParams childProcessCreationParams = this.mCreationParams;
        return childProcessCreationParams != null ? childProcessCreationParams.getPackageName() : this.mContext.getPackageName();
    }

    @Override // org.chromium.content.browser.ChildProcessConnection
    public IChildProcessService getService() {
        IChildProcessService iChildProcessService;
        synchronized (this.mLock) {
            iChildProcessService = this.mService;
        }
        return iChildProcessService;
    }

    @Override // org.chromium.content.browser.ChildProcessConnection
    public int getPid() {
        int i;
        synchronized (this.mLock) {
            i = this.mPid;
        }
        return i;
    }

    @Override // org.chromium.content.browser.ChildProcessConnection
    public void start(String[] strArr) {
        try {
            TraceEvent.begin("ChildProcessConnectionImpl.start");
            synchronized (this.mLock) {
                if (!this.mInitialBinding.bind(strArr)) {
                    Log.e(TAG, "Failed to establish the service connection.", new Object[0]);
                    this.mDeathCallback.onChildProcessDied(this);
                } else {
                    this.mWaivedBinding.bind(null);
                }
            }
        } finally {
            TraceEvent.end("ChildProcessConnectionImpl.start");
        }
    }

    @Override // org.chromium.content.browser.ChildProcessConnection
    public void setupConnection(String[] strArr, FileDescriptorInfo[] fileDescriptorInfoArr, IChildProcessCallback iChildProcessCallback, ChildProcessConnection.ConnectionCallback connectionCallback, Bundle bundle) {
        synchronized (this.mLock) {
            if (this.mServiceDisconnected) {
                Log.w(TAG, "Tried to setup a connection that already disconnected.", new Object[0]);
                connectionCallback.onConnected(0);
                return;
            }
            try {
                TraceEvent.begin("ChildProcessConnectionImpl.setupConnection");
                this.mConnectionCallback = connectionCallback;
                this.mConnectionParams = new ConnectionParams(strArr, fileDescriptorInfoArr, iChildProcessCallback, bundle);
                if (this.mServiceConnectComplete) {
                    doConnectionSetupLocked();
                }
            } finally {
                TraceEvent.end("ChildProcessConnectionImpl.setupConnection");
            }
        }
    }

    @Override // org.chromium.content.browser.ChildProcessConnection
    public void stop() {
        synchronized (this.mLock) {
            this.mInitialBinding.unbind();
            this.mStrongBinding.unbind();
            this.mWaivedBinding.unbind();
            this.mModerateBinding.unbind();
            this.mStrongBindingCount = 0;
            if (this.mService != null) {
                this.mService = null;
            }
            this.mConnectionParams = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doConnectionSetupLocked() {
        try {
            TraceEvent.begin("ChildProcessConnectionImpl.doConnectionSetupLocked");
            try {
                this.mPid = this.mService.setupConnection(ChildProcessLauncher.createsServiceBundle(this.mConnectionParams.mCommandLine, this.mConnectionParams.mFilesToBeMapped, this.mConnectionParams.mSharedRelros), this.mConnectionParams.mCallback);
            } catch (RemoteException e) {
                Log.e(TAG, "Failed to setup connection.", e);
            }
            try {
                for (FileDescriptorInfo fileDescriptorInfo : this.mConnectionParams.mFilesToBeMapped) {
                    fileDescriptorInfo.mFd.close();
                }
            } catch (IOException e2) {
                Log.w(TAG, "Failed to close FD.", e2);
            }
            this.mConnectionParams = null;
            ChildProcessConnection.ConnectionCallback connectionCallback = this.mConnectionCallback;
            if (connectionCallback != null) {
                connectionCallback.onConnected(this.mPid);
            }
            this.mConnectionCallback = null;
        } finally {
            TraceEvent.end("ChildProcessConnectionImpl.doConnectionSetupLocked");
        }
    }

    @Override // org.chromium.content.browser.ChildProcessConnection
    public boolean isInitialBindingBound() {
        boolean zIsBound;
        synchronized (this.mLock) {
            zIsBound = this.mInitialBinding.isBound();
        }
        return zIsBound;
    }

    @Override // org.chromium.content.browser.ChildProcessConnection
    public boolean isStrongBindingBound() {
        boolean zIsBound;
        synchronized (this.mLock) {
            zIsBound = this.mStrongBinding.isBound();
        }
        return zIsBound;
    }

    @Override // org.chromium.content.browser.ChildProcessConnection
    public void removeInitialBinding() {
        synchronized (this.mLock) {
            this.mInitialBinding.unbind();
        }
    }

    @Override // org.chromium.content.browser.ChildProcessConnection
    public boolean isOomProtectedOrWasWhenDied() {
        synchronized (this.mLock) {
            if (this.mServiceDisconnected) {
                return this.mWasOomProtected;
            }
            return isCurrentlyOomProtected();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isCurrentlyOomProtected() {
        synchronized (this.mLock) {
            if (this.mAlwaysInForeground) {
                return ChildProcessLauncher.isApplicationInForeground();
            }
            return this.mInitialBinding.isBound() || this.mStrongBinding.isBound();
        }
    }

    @Override // org.chromium.content.browser.ChildProcessConnection
    public void dropOomBindings() {
        synchronized (this.mLock) {
            this.mInitialBinding.unbind();
            this.mStrongBindingCount = 0;
            this.mStrongBinding.unbind();
            this.mModerateBinding.unbind();
        }
    }

    @Override // org.chromium.content.browser.ChildProcessConnection
    public void addStrongBinding() {
        synchronized (this.mLock) {
            if (this.mService == null) {
                Log.w(TAG, "The connection is not bound for %d", Integer.valueOf(this.mPid));
                return;
            }
            if (this.mStrongBindingCount == 0) {
                this.mStrongBinding.bind(null);
            }
            this.mStrongBindingCount++;
        }
    }

    @Override // org.chromium.content.browser.ChildProcessConnection
    public void removeStrongBinding() {
        synchronized (this.mLock) {
            if (this.mService == null) {
                Log.w(TAG, "The connection is not bound for %d", Integer.valueOf(this.mPid));
                return;
            }
            int i = this.mStrongBindingCount - 1;
            this.mStrongBindingCount = i;
            if (i == 0) {
                this.mStrongBinding.unbind();
            }
        }
    }

    @Override // org.chromium.content.browser.ChildProcessConnection
    public boolean isModerateBindingBound() {
        boolean zIsBound;
        synchronized (this.mLock) {
            zIsBound = this.mModerateBinding.isBound();
        }
        return zIsBound;
    }

    @Override // org.chromium.content.browser.ChildProcessConnection
    public void addModerateBinding() {
        synchronized (this.mLock) {
            if (this.mService == null) {
                Log.w(TAG, "The connection is not bound for %d", Integer.valueOf(this.mPid));
            } else {
                this.mModerateBinding.bind(null);
            }
        }
    }

    @Override // org.chromium.content.browser.ChildProcessConnection
    public void removeModerateBinding() {
        synchronized (this.mLock) {
            if (this.mService == null) {
                Log.w(TAG, "The connection is not bound for %d", Integer.valueOf(this.mPid));
            } else {
                this.mModerateBinding.unbind();
            }
        }
    }

    public boolean crashServiceForTesting() throws RemoteException {
        try {
            this.mService.crashIntentionallyForTesting();
            return false;
        } catch (DeadObjectException unused) {
            return true;
        }
    }

    public boolean isConnected() {
        return this.mService != null;
    }
}
