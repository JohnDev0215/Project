package android.window;

import android.view.SurfaceControl;
import android.window.IWindowContainerTransactionCallback;

/* JADX INFO: loaded from: classes3.dex */
public abstract class WindowContainerTransactionCallback {
    final IWindowContainerTransactionCallback mInterface = new IWindowContainerTransactionCallback.Stub() { // from class: android.window.WindowContainerTransactionCallback.1
        @Override // android.window.IWindowContainerTransactionCallback
        public void onTransactionReady(int i, SurfaceControl.Transaction transaction) {
            WindowContainerTransactionCallback.this.onTransactionReady(i, transaction);
        }
    };

    public abstract void onTransactionReady(int i, SurfaceControl.Transaction transaction);
}
