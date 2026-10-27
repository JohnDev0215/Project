package com.android.internal.inputmethod;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.InputChannel;
import com.android.internal.view.IInputMethodSession;

/* JADX INFO: loaded from: classes4.dex */
public class MultiClientInputMethodPrivilegedOperations {
    private static final String TAG = "MultiClientInputMethodPrivilegedOperations";
    private final OpsHolder mOps = new OpsHolder();

    private static final class OpsHolder {
        private IMultiClientInputMethodPrivilegedOperations mPrivOps;

        private OpsHolder() {
        }

        public synchronized void set(IMultiClientInputMethodPrivilegedOperations iMultiClientInputMethodPrivilegedOperations) {
            if (this.mPrivOps != null) {
                throw new IllegalStateException("IMultiClientInputMethodPrivilegedOperations must be set at most once. privOps=" + iMultiClientInputMethodPrivilegedOperations);
            }
            this.mPrivOps = iMultiClientInputMethodPrivilegedOperations;
        }

        private static String getCallerMethodName() {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            return stackTrace.length <= 4 ? "<bottom of call stack>" : stackTrace[4].getMethodName();
        }

        public synchronized void dispose() {
            this.mPrivOps = null;
        }

        public synchronized IMultiClientInputMethodPrivilegedOperations getAndWarnIfNull() {
            if (this.mPrivOps == null) {
                Log.e(MultiClientInputMethodPrivilegedOperations.TAG, getCallerMethodName() + " is ignored. Call it within attachToken() and InputMethodService.onDestroy()");
            }
            return this.mPrivOps;
        }
    }

    public void set(IMultiClientInputMethodPrivilegedOperations iMultiClientInputMethodPrivilegedOperations) {
        this.mOps.set(iMultiClientInputMethodPrivilegedOperations);
    }

    public void dispose() {
        this.mOps.dispose();
    }

    public IBinder createInputMethodWindowToken(int i) {
        IMultiClientInputMethodPrivilegedOperations andWarnIfNull = this.mOps.getAndWarnIfNull();
        if (andWarnIfNull == null) {
            return null;
        }
        try {
            return andWarnIfNull.createInputMethodWindowToken(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void deleteInputMethodWindowToken(IBinder iBinder) {
        IMultiClientInputMethodPrivilegedOperations andWarnIfNull = this.mOps.getAndWarnIfNull();
        if (andWarnIfNull == null) {
            return;
        }
        try {
            andWarnIfNull.deleteInputMethodWindowToken(iBinder);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void acceptClient(int i, IInputMethodSession iInputMethodSession, IMultiClientInputMethodSession iMultiClientInputMethodSession, InputChannel inputChannel) {
        IMultiClientInputMethodPrivilegedOperations andWarnIfNull = this.mOps.getAndWarnIfNull();
        if (andWarnIfNull == null) {
            return;
        }
        try {
            andWarnIfNull.acceptClient(i, iInputMethodSession, iMultiClientInputMethodSession, inputChannel);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void reportImeWindowTarget(int i, int i2, IBinder iBinder) {
        IMultiClientInputMethodPrivilegedOperations andWarnIfNull = this.mOps.getAndWarnIfNull();
        if (andWarnIfNull == null) {
            return;
        }
        try {
            andWarnIfNull.reportImeWindowTarget(i, i2, iBinder);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isUidAllowedOnDisplay(int i, int i2) {
        IMultiClientInputMethodPrivilegedOperations andWarnIfNull = this.mOps.getAndWarnIfNull();
        if (andWarnIfNull == null) {
            return false;
        }
        try {
            return andWarnIfNull.isUidAllowedOnDisplay(i, i2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setActive(int i, boolean z) {
        IMultiClientInputMethodPrivilegedOperations andWarnIfNull = this.mOps.getAndWarnIfNull();
        if (andWarnIfNull == null) {
            return;
        }
        try {
            andWarnIfNull.setActive(i, z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }
}
