package android.view;

import android.content.Context;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.SurfaceControl;
import android.view.WindowManager;
import android.view.WindowlessWindowManager;
import android.view.accessibility.IAccessibilityEmbeddedConnection;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class SurfaceControlViewHost {
    private IAccessibilityEmbeddedConnection mAccessibilityEmbeddedConnection;
    private SurfaceControl mSurfaceControl;
    private final ViewRootImpl mViewRoot;
    private WindowlessWindowManager mWm;

    public static final class SurfacePackage implements Parcelable {
        public static final Parcelable.Creator<SurfacePackage> CREATOR = new Parcelable.Creator<SurfacePackage>() { // from class: android.view.SurfaceControlViewHost.SurfacePackage.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SurfacePackage createFromParcel(Parcel parcel) {
                return new SurfacePackage(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SurfacePackage[] newArray(int i) {
                return new SurfacePackage[i];
            }
        };
        private final IAccessibilityEmbeddedConnection mAccessibilityEmbeddedConnection;
        private SurfaceControl mSurfaceControl;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        SurfacePackage(SurfaceControl surfaceControl, IAccessibilityEmbeddedConnection iAccessibilityEmbeddedConnection) {
            this.mSurfaceControl = surfaceControl;
            this.mAccessibilityEmbeddedConnection = iAccessibilityEmbeddedConnection;
        }

        private SurfacePackage(Parcel parcel) {
            SurfaceControl surfaceControl = new SurfaceControl();
            this.mSurfaceControl = surfaceControl;
            surfaceControl.readFromParcel(parcel);
            this.mAccessibilityEmbeddedConnection = IAccessibilityEmbeddedConnection.Stub.asInterface(parcel.readStrongBinder());
        }

        public SurfaceControl getSurfaceControl() {
            return this.mSurfaceControl;
        }

        public IAccessibilityEmbeddedConnection getAccessibilityEmbeddedConnection() {
            return this.mAccessibilityEmbeddedConnection;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            this.mSurfaceControl.writeToParcel(parcel, i);
            parcel.writeStrongBinder(this.mAccessibilityEmbeddedConnection.asBinder());
        }

        public void release() {
            SurfaceControl surfaceControl = this.mSurfaceControl;
            if (surfaceControl != null) {
                surfaceControl.release();
            }
            this.mSurfaceControl = null;
        }
    }

    public SurfaceControlViewHost(Context context, Display display, WindowlessWindowManager windowlessWindowManager) {
        this(context, display, windowlessWindowManager, false);
    }

    public SurfaceControlViewHost(Context context, Display display, WindowlessWindowManager windowlessWindowManager, boolean z) {
        this.mWm = windowlessWindowManager;
        ViewRootImpl viewRootImpl = new ViewRootImpl(context, display, this.mWm, z);
        this.mViewRoot = viewRootImpl;
        viewRootImpl.forceDisableBLAST();
        this.mAccessibilityEmbeddedConnection = viewRootImpl.getAccessibilityEmbeddedConnection();
    }

    public SurfaceControlViewHost(Context context, Display display, IBinder iBinder) {
        this.mSurfaceControl = new SurfaceControl.Builder().setContainerLayer().setName("SurfaceControlViewHost").setCallsite("SurfaceControlViewHost").build();
        this.mWm = new WindowlessWindowManager(context.getResources().getConfiguration(), this.mSurfaceControl, iBinder);
        ViewRootImpl viewRootImpl = new ViewRootImpl(context, display, this.mWm);
        this.mViewRoot = viewRootImpl;
        viewRootImpl.forceDisableBLAST();
        this.mAccessibilityEmbeddedConnection = viewRootImpl.getAccessibilityEmbeddedConnection();
    }

    protected void finalize() throws Throwable {
        this.mViewRoot.die(false);
    }

    public SurfacePackage getSurfacePackage() {
        if (this.mSurfaceControl == null || this.mAccessibilityEmbeddedConnection == null) {
            return null;
        }
        return new SurfacePackage(this.mSurfaceControl, this.mAccessibilityEmbeddedConnection);
    }

    public void setView(View view, WindowManager.LayoutParams layoutParams) {
        Objects.requireNonNull(view);
        this.mViewRoot.setView(view, layoutParams, null);
    }

    public void setView(View view, int i, int i2) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(i, i2, 2, 0, -2);
        layoutParams.flags |= 16777216;
        setView(view, layoutParams);
    }

    public View getView() {
        return this.mViewRoot.getView();
    }

    public IWindow getWindowToken() {
        return this.mViewRoot.mWindow;
    }

    public WindowlessWindowManager getWindowlessWM() {
        return this.mWm;
    }

    public void relayout(WindowManager.LayoutParams layoutParams) {
        this.mViewRoot.setLayoutParams(layoutParams, false);
        this.mViewRoot.setReportNextDraw();
        this.mWm.setCompletionCallback(this.mViewRoot.mWindow.asBinder(), new WindowlessWindowManager.ResizeCompleteCallback() { // from class: android.view.-$$Lambda$SurfaceControlViewHost$FkT_5_k4GS0HLFsiJX3WFZNFDt4
            @Override // android.view.WindowlessWindowManager.ResizeCompleteCallback
            public final void finished(SurfaceControl.Transaction transaction) {
                transaction.apply();
            }
        });
    }

    public void relayout(int i, int i2) {
        relayout(new WindowManager.LayoutParams(i, i2, 2, 0, -2));
    }

    public void release() {
        this.mViewRoot.die(true);
    }
}
