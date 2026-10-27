package android.view;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.MergedConfiguration;
import android.view.DisplayCutout;
import android.view.IScrollCaptureController;
import com.android.internal.os.IResultReceiver;

/* JADX INFO: loaded from: classes3.dex */
public interface IWindow extends IInterface {

    public static class Default implements IWindow {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.view.IWindow
        public void closeSystemDialogs(String str) throws RemoteException {
        }

        @Override // android.view.IWindow
        public void dispatchAppVisibility(boolean z) throws RemoteException {
        }

        @Override // android.view.IWindow
        public void dispatchDragEvent(DragEvent dragEvent) throws RemoteException {
        }

        @Override // android.view.IWindow
        public void dispatchGetNewSurface() throws RemoteException {
        }

        @Override // android.view.IWindow
        public void dispatchPointerCaptureChanged(boolean z) throws RemoteException {
        }

        @Override // android.view.IWindow
        public void dispatchSystemUiVisibilityChanged(int i, int i2, int i3, int i4) throws RemoteException {
        }

        @Override // android.view.IWindow
        public void dispatchWallpaperCommand(String str, int i, int i2, int i3, Bundle bundle, boolean z) throws RemoteException {
        }

        @Override // android.view.IWindow
        public void dispatchWallpaperOffsets(float f, float f2, float f3, float f4, float f5, boolean z) throws RemoteException {
        }

        @Override // android.view.IWindow
        public void dispatchWindowShown() throws RemoteException {
        }

        @Override // android.view.IWindow
        public void executeCommand(String str, String str2, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
        }

        @Override // android.view.IWindow
        public void hideInsets(int i, boolean z) throws RemoteException {
        }

        @Override // android.view.IWindow
        public void insetsChanged(InsetsState insetsState) throws RemoteException {
        }

        @Override // android.view.IWindow
        public void insetsControlChanged(InsetsState insetsState, InsetsSourceControl[] insetsSourceControlArr) throws RemoteException {
        }

        @Override // android.view.IWindow
        public void locationInParentDisplayChanged(Point point) throws RemoteException {
        }

        @Override // android.view.IWindow
        public void moved(int i, int i2) throws RemoteException {
        }

        @Override // android.view.IWindow
        public void requestAppKeyboardShortcuts(IResultReceiver iResultReceiver, int i) throws RemoteException {
        }

        @Override // android.view.IWindow
        public void requestScrollCapture(IScrollCaptureController iScrollCaptureController) throws RemoteException {
        }

        @Override // android.view.IWindow
        public void resized(Rect rect, Rect rect2, Rect rect3, Rect rect4, boolean z, MergedConfiguration mergedConfiguration, Rect rect5, boolean z2, boolean z3, int i, DisplayCutout.ParcelableWrapper parcelableWrapper) throws RemoteException {
        }

        @Override // android.view.IWindow
        public void showInsets(int i, boolean z) throws RemoteException {
        }

        @Override // android.view.IWindow
        public void updatePointerIcon(float f, float f2) throws RemoteException {
        }

        @Override // android.view.IWindow
        public void windowFocusChanged(boolean z, boolean z2) throws RemoteException {
        }
    }

    void closeSystemDialogs(String str) throws RemoteException;

    void dispatchAppVisibility(boolean z) throws RemoteException;

    void dispatchDragEvent(DragEvent dragEvent) throws RemoteException;

    void dispatchGetNewSurface() throws RemoteException;

    void dispatchPointerCaptureChanged(boolean z) throws RemoteException;

    void dispatchSystemUiVisibilityChanged(int i, int i2, int i3, int i4) throws RemoteException;

    void dispatchWallpaperCommand(String str, int i, int i2, int i3, Bundle bundle, boolean z) throws RemoteException;

    void dispatchWallpaperOffsets(float f, float f2, float f3, float f4, float f5, boolean z) throws RemoteException;

    void dispatchWindowShown() throws RemoteException;

    void executeCommand(String str, String str2, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException;

    void hideInsets(int i, boolean z) throws RemoteException;

    void insetsChanged(InsetsState insetsState) throws RemoteException;

    void insetsControlChanged(InsetsState insetsState, InsetsSourceControl[] insetsSourceControlArr) throws RemoteException;

    void locationInParentDisplayChanged(Point point) throws RemoteException;

    void moved(int i, int i2) throws RemoteException;

    void requestAppKeyboardShortcuts(IResultReceiver iResultReceiver, int i) throws RemoteException;

    void requestScrollCapture(IScrollCaptureController iScrollCaptureController) throws RemoteException;

    void resized(Rect rect, Rect rect2, Rect rect3, Rect rect4, boolean z, MergedConfiguration mergedConfiguration, Rect rect5, boolean z2, boolean z3, int i, DisplayCutout.ParcelableWrapper parcelableWrapper) throws RemoteException;

    void showInsets(int i, boolean z) throws RemoteException;

    void updatePointerIcon(float f, float f2) throws RemoteException;

    void windowFocusChanged(boolean z, boolean z2) throws RemoteException;

    public static abstract class Stub extends Binder implements IWindow {
        private static final String DESCRIPTOR = "android.view.IWindow";
        static final int TRANSACTION_closeSystemDialogs = 12;
        static final int TRANSACTION_dispatchAppVisibility = 9;
        static final int TRANSACTION_dispatchDragEvent = 15;
        static final int TRANSACTION_dispatchGetNewSurface = 10;
        static final int TRANSACTION_dispatchPointerCaptureChanged = 20;
        static final int TRANSACTION_dispatchSystemUiVisibilityChanged = 17;
        static final int TRANSACTION_dispatchWallpaperCommand = 14;
        static final int TRANSACTION_dispatchWallpaperOffsets = 13;
        static final int TRANSACTION_dispatchWindowShown = 18;
        static final int TRANSACTION_executeCommand = 1;
        static final int TRANSACTION_hideInsets = 7;
        static final int TRANSACTION_insetsChanged = 4;
        static final int TRANSACTION_insetsControlChanged = 5;
        static final int TRANSACTION_locationInParentDisplayChanged = 3;
        static final int TRANSACTION_moved = 8;
        static final int TRANSACTION_requestAppKeyboardShortcuts = 19;
        static final int TRANSACTION_requestScrollCapture = 21;
        static final int TRANSACTION_resized = 2;
        static final int TRANSACTION_showInsets = 6;
        static final int TRANSACTION_updatePointerIcon = 16;
        static final int TRANSACTION_windowFocusChanged = 11;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "executeCommand";
                case 2:
                    return "resized";
                case 3:
                    return "locationInParentDisplayChanged";
                case 4:
                    return "insetsChanged";
                case 5:
                    return "insetsControlChanged";
                case 6:
                    return "showInsets";
                case 7:
                    return "hideInsets";
                case 8:
                    return "moved";
                case 9:
                    return "dispatchAppVisibility";
                case 10:
                    return "dispatchGetNewSurface";
                case 11:
                    return "windowFocusChanged";
                case 12:
                    return "closeSystemDialogs";
                case 13:
                    return "dispatchWallpaperOffsets";
                case 14:
                    return "dispatchWallpaperCommand";
                case 15:
                    return "dispatchDragEvent";
                case 16:
                    return "updatePointerIcon";
                case 17:
                    return "dispatchSystemUiVisibilityChanged";
                case 18:
                    return "dispatchWindowShown";
                case 19:
                    return "requestAppKeyboardShortcuts";
                case 20:
                    return "dispatchPointerCaptureChanged";
                case 21:
                    return "requestScrollCapture";
                default:
                    return null;
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWindow asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IWindow)) {
                return (IWindow) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    executeCommand(parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    resized(parcel.readInt() != 0 ? Rect.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Rect.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Rect.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Rect.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0, parcel.readInt() != 0 ? MergedConfiguration.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Rect.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0, parcel.readInt() != 0, parcel.readInt(), parcel.readInt() != 0 ? DisplayCutout.ParcelableWrapper.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    locationInParentDisplayChanged(parcel.readInt() != 0 ? Point.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    insetsChanged(parcel.readInt() != 0 ? InsetsState.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    insetsControlChanged(parcel.readInt() != 0 ? InsetsState.CREATOR.createFromParcel(parcel) : null, (InsetsSourceControl[]) parcel.createTypedArray(InsetsSourceControl.CREATOR));
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    showInsets(parcel.readInt(), parcel.readInt() != 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    hideInsets(parcel.readInt(), parcel.readInt() != 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    moved(parcel.readInt(), parcel.readInt());
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    dispatchAppVisibility(parcel.readInt() != 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    dispatchGetNewSurface();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    windowFocusChanged(parcel.readInt() != 0, parcel.readInt() != 0);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    closeSystemDialogs(parcel.readString());
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    dispatchWallpaperOffsets(parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readInt() != 0);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    dispatchWallpaperCommand(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    dispatchDragEvent(parcel.readInt() != 0 ? DragEvent.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    updatePointerIcon(parcel.readFloat(), parcel.readFloat());
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    dispatchSystemUiVisibilityChanged(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    dispatchWindowShown();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestAppKeyboardShortcuts(IResultReceiver.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    dispatchPointerCaptureChanged(parcel.readInt() != 0);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestScrollCapture(IScrollCaptureController.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IWindow {
            public static IWindow sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.view.IWindow
            public void executeCommand(String str, String str2, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (parcelFileDescriptor != null) {
                        parcelObtain.writeInt(1);
                        parcelFileDescriptor.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().executeCommand(str, str2, parcelFileDescriptor);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindow
            public void resized(Rect rect, Rect rect2, Rect rect3, Rect rect4, boolean z, MergedConfiguration mergedConfiguration, Rect rect5, boolean z2, boolean z3, int i, DisplayCutout.ParcelableWrapper parcelableWrapper) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (rect != null) {
                        parcelObtain.writeInt(1);
                        rect.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (rect2 != null) {
                        parcelObtain.writeInt(1);
                        rect2.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (rect3 != null) {
                        parcelObtain.writeInt(1);
                        rect3.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (rect4 != null) {
                        parcelObtain.writeInt(1);
                        rect4.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (mergedConfiguration != null) {
                        parcelObtain.writeInt(1);
                        mergedConfiguration.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (rect5 != null) {
                        parcelObtain.writeInt(1);
                        rect5.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(z2 ? 1 : 0);
                    parcelObtain.writeInt(z3 ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (parcelableWrapper != null) {
                        parcelObtain.writeInt(1);
                        parcelableWrapper.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        parcelObtain.recycle();
                    } else {
                        Stub.getDefaultImpl().resized(rect, rect2, rect3, rect4, z, mergedConfiguration, rect5, z2, z3, i, parcelableWrapper);
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.view.IWindow
            public void locationInParentDisplayChanged(Point point) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (point != null) {
                        parcelObtain.writeInt(1);
                        point.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().locationInParentDisplayChanged(point);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindow
            public void insetsChanged(InsetsState insetsState) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (insetsState != null) {
                        parcelObtain.writeInt(1);
                        insetsState.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().insetsChanged(insetsState);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindow
            public void insetsControlChanged(InsetsState insetsState, InsetsSourceControl[] insetsSourceControlArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (insetsState != null) {
                        parcelObtain.writeInt(1);
                        insetsState.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeTypedArray(insetsSourceControlArr, 0);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().insetsControlChanged(insetsState, insetsSourceControlArr);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindow
            public void showInsets(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().showInsets(i, z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindow
            public void hideInsets(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().hideInsets(i, z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindow
            public void moved(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().moved(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindow
            public void dispatchAppVisibility(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().dispatchAppVisibility(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindow
            public void dispatchGetNewSurface() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(10, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().dispatchGetNewSurface();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindow
            public void windowFocusChanged(boolean z, boolean z2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(z2 ? 1 : 0);
                    if (this.mRemote.transact(11, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().windowFocusChanged(z, z2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindow
            public void closeSystemDialogs(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(12, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().closeSystemDialogs(str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindow
            public void dispatchWallpaperOffsets(float f, float f2, float f3, float f4, float f5, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeFloat(f);
                    parcelObtain.writeFloat(f2);
                    parcelObtain.writeFloat(f3);
                    parcelObtain.writeFloat(f4);
                    parcelObtain.writeFloat(f5);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(13, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().dispatchWallpaperOffsets(f, f2, f3, f4, f5, z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindow
            public void dispatchWallpaperCommand(String str, int i, int i2, int i3, Bundle bundle, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(14, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().dispatchWallpaperCommand(str, i, i2, i3, bundle, z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindow
            public void dispatchDragEvent(DragEvent dragEvent) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (dragEvent != null) {
                        parcelObtain.writeInt(1);
                        dragEvent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(15, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().dispatchDragEvent(dragEvent);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindow
            public void updatePointerIcon(float f, float f2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeFloat(f);
                    parcelObtain.writeFloat(f2);
                    if (this.mRemote.transact(16, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().updatePointerIcon(f, f2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindow
            public void dispatchSystemUiVisibilityChanged(int i, int i2, int i3, int i4) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    if (this.mRemote.transact(17, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().dispatchSystemUiVisibilityChanged(i, i2, i3, i4);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindow
            public void dispatchWindowShown() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(18, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().dispatchWindowShown();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindow
            public void requestAppKeyboardShortcuts(IResultReceiver iResultReceiver, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iResultReceiver != null ? iResultReceiver.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(19, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().requestAppKeyboardShortcuts(iResultReceiver, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindow
            public void dispatchPointerCaptureChanged(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(20, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().dispatchPointerCaptureChanged(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindow
            public void requestScrollCapture(IScrollCaptureController iScrollCaptureController) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iScrollCaptureController != null ? iScrollCaptureController.asBinder() : null);
                    if (this.mRemote.transact(21, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().requestScrollCapture(iScrollCaptureController);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IWindow iWindow) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iWindow == null) {
                return false;
            }
            Proxy.sDefaultImpl = iWindow;
            return true;
        }

        public static IWindow getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
