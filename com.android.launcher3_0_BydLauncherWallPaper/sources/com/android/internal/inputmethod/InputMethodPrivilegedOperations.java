package com.android.internal.inputmethod;

import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodSubtype;

/* JADX INFO: loaded from: classes4.dex */
public final class InputMethodPrivilegedOperations {
    private static final String TAG = "InputMethodPrivilegedOperations";
    private final OpsHolder mOps = new OpsHolder();

    private static final class OpsHolder {
        private IInputMethodPrivilegedOperations mPrivOps;

        private OpsHolder() {
        }

        public synchronized void set(IInputMethodPrivilegedOperations iInputMethodPrivilegedOperations) {
            if (this.mPrivOps != null) {
                throw new IllegalStateException("IInputMethodPrivilegedOperations must be set at most once. privOps=" + iInputMethodPrivilegedOperations);
            }
            this.mPrivOps = iInputMethodPrivilegedOperations;
        }

        private static String getCallerMethodName() {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            return stackTrace.length <= 4 ? "<bottom of call stack>" : stackTrace[4].getMethodName();
        }

        public synchronized IInputMethodPrivilegedOperations getAndWarnIfNull() {
            if (this.mPrivOps == null) {
                Log.e(InputMethodPrivilegedOperations.TAG, getCallerMethodName() + " is ignored. Call it within attachToken() and InputMethodService.onDestroy()");
            }
            return this.mPrivOps;
        }
    }

    public void set(IInputMethodPrivilegedOperations iInputMethodPrivilegedOperations) {
        this.mOps.set(iInputMethodPrivilegedOperations);
    }

    public void setImeWindowStatus(int i, int i2) {
        IInputMethodPrivilegedOperations andWarnIfNull = this.mOps.getAndWarnIfNull();
        if (andWarnIfNull == null) {
            return;
        }
        try {
            andWarnIfNull.setImeWindowStatus(i, i2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void reportStartInput(IBinder iBinder) {
        IInputMethodPrivilegedOperations andWarnIfNull = this.mOps.getAndWarnIfNull();
        if (andWarnIfNull == null) {
            return;
        }
        try {
            andWarnIfNull.reportStartInput(iBinder);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public IInputContentUriToken createInputContentUriToken(Uri uri, String str) {
        IInputMethodPrivilegedOperations andWarnIfNull = this.mOps.getAndWarnIfNull();
        if (andWarnIfNull == null) {
            return null;
        }
        try {
            return andWarnIfNull.createInputContentUriToken(uri, str);
        } catch (RemoteException unused) {
            return null;
        }
    }

    public void reportFullscreenMode(boolean z) {
        IInputMethodPrivilegedOperations andWarnIfNull = this.mOps.getAndWarnIfNull();
        if (andWarnIfNull == null) {
            return;
        }
        try {
            andWarnIfNull.reportFullscreenMode(z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void updateStatusIcon(String str, int i) {
        IInputMethodPrivilegedOperations andWarnIfNull = this.mOps.getAndWarnIfNull();
        if (andWarnIfNull == null) {
            return;
        }
        try {
            andWarnIfNull.updateStatusIcon(str, i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setInputMethod(String str) {
        IInputMethodPrivilegedOperations andWarnIfNull = this.mOps.getAndWarnIfNull();
        if (andWarnIfNull == null) {
            return;
        }
        try {
            andWarnIfNull.setInputMethod(str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setInputMethodAndSubtype(String str, InputMethodSubtype inputMethodSubtype) {
        IInputMethodPrivilegedOperations andWarnIfNull = this.mOps.getAndWarnIfNull();
        if (andWarnIfNull == null) {
            return;
        }
        try {
            andWarnIfNull.setInputMethodAndSubtype(str, inputMethodSubtype);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void hideMySoftInput(int i) {
        IInputMethodPrivilegedOperations andWarnIfNull = this.mOps.getAndWarnIfNull();
        if (andWarnIfNull == null) {
            return;
        }
        try {
            andWarnIfNull.hideMySoftInput(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void showMySoftInput(int i) {
        IInputMethodPrivilegedOperations andWarnIfNull = this.mOps.getAndWarnIfNull();
        if (andWarnIfNull == null) {
            return;
        }
        try {
            andWarnIfNull.showMySoftInput(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean switchToPreviousInputMethod() {
        IInputMethodPrivilegedOperations andWarnIfNull = this.mOps.getAndWarnIfNull();
        if (andWarnIfNull == null) {
            return false;
        }
        try {
            return andWarnIfNull.switchToPreviousInputMethod();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean switchToNextInputMethod(boolean z) {
        IInputMethodPrivilegedOperations andWarnIfNull = this.mOps.getAndWarnIfNull();
        if (andWarnIfNull == null) {
            return false;
        }
        try {
            return andWarnIfNull.switchToNextInputMethod(z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean shouldOfferSwitchingToNextInputMethod() {
        IInputMethodPrivilegedOperations andWarnIfNull = this.mOps.getAndWarnIfNull();
        if (andWarnIfNull == null) {
            return false;
        }
        try {
            return andWarnIfNull.shouldOfferSwitchingToNextInputMethod();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void notifyUserAction() {
        IInputMethodPrivilegedOperations andWarnIfNull = this.mOps.getAndWarnIfNull();
        if (andWarnIfNull == null) {
            return;
        }
        try {
            andWarnIfNull.notifyUserAction();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void reportPreRendered(EditorInfo editorInfo) {
        IInputMethodPrivilegedOperations andWarnIfNull = this.mOps.getAndWarnIfNull();
        if (andWarnIfNull == null) {
            return;
        }
        try {
            andWarnIfNull.reportPreRendered(editorInfo);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void applyImeVisibility(IBinder iBinder, boolean z) {
        IInputMethodPrivilegedOperations andWarnIfNull = this.mOps.getAndWarnIfNull();
        if (andWarnIfNull == null) {
            return;
        }
        try {
            andWarnIfNull.applyImeVisibility(iBinder, z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }
}
