package android.view;

import android.graphics.Matrix;
import android.os.IBinder;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.IAccessibilityEmbeddedConnection;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes3.dex */
final class AccessibilityEmbeddedConnection extends IAccessibilityEmbeddedConnection.Stub {
    private final Matrix mTmpScreenMatrix = new Matrix();
    private final WeakReference<ViewRootImpl> mViewRootImpl;

    AccessibilityEmbeddedConnection(ViewRootImpl viewRootImpl) {
        this.mViewRootImpl = new WeakReference<>(viewRootImpl);
    }

    @Override // android.view.accessibility.IAccessibilityEmbeddedConnection
    public IBinder associateEmbeddedHierarchy(IBinder iBinder, int i) {
        ViewRootImpl viewRootImpl = this.mViewRootImpl.get();
        if (viewRootImpl == null) {
            return null;
        }
        AccessibilityManager accessibilityManager = AccessibilityManager.getInstance(viewRootImpl.mContext);
        viewRootImpl.mAttachInfo.mLeashedParentToken = iBinder;
        viewRootImpl.mAttachInfo.mLeashedParentAccessibilityViewId = i;
        if (accessibilityManager.isEnabled()) {
            accessibilityManager.associateEmbeddedHierarchy(iBinder, viewRootImpl.mLeashToken);
        }
        return viewRootImpl.mLeashToken;
    }

    @Override // android.view.accessibility.IAccessibilityEmbeddedConnection
    public void disassociateEmbeddedHierarchy() {
        ViewRootImpl viewRootImpl = this.mViewRootImpl.get();
        if (viewRootImpl != null) {
            AccessibilityManager accessibilityManager = AccessibilityManager.getInstance(viewRootImpl.mContext);
            viewRootImpl.mAttachInfo.mLeashedParentToken = null;
            viewRootImpl.mAttachInfo.mLeashedParentAccessibilityViewId = -1;
            viewRootImpl.mAttachInfo.mLocationInParentDisplay.set(0, 0);
            if (accessibilityManager.isEnabled()) {
                accessibilityManager.disassociateEmbeddedHierarchy(viewRootImpl.mLeashToken);
            }
        }
    }

    @Override // android.view.accessibility.IAccessibilityEmbeddedConnection
    public void setScreenMatrix(float[] fArr) {
        ViewRootImpl viewRootImpl = this.mViewRootImpl.get();
        if (viewRootImpl != null) {
            this.mTmpScreenMatrix.setValues(fArr);
            if (viewRootImpl.mAttachInfo.mScreenMatrixInEmbeddedHierarchy == null) {
                viewRootImpl.mAttachInfo.mScreenMatrixInEmbeddedHierarchy = new Matrix();
            }
            viewRootImpl.mAttachInfo.mScreenMatrixInEmbeddedHierarchy.set(this.mTmpScreenMatrix);
        }
    }
}
