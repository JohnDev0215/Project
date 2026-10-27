package android.accessibilityservice;

import android.content.pm.ParceledListSlice;
import android.graphics.Region;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import android.view.accessibility.IAccessibilityInteractionConnectionCallback;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IAccessibilityServiceConnection extends IInterface {

    public static class Default implements IAccessibilityServiceConnection {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.accessibilityservice.IAccessibilityServiceConnection
        public void disableSelf() throws RemoteException {
        }

        @Override // android.accessibilityservice.IAccessibilityServiceConnection
        public void dispatchGesture(int i, ParceledListSlice parceledListSlice, int i2) throws RemoteException {
        }

        @Override // android.accessibilityservice.IAccessibilityServiceConnection
        public String[] findAccessibilityNodeInfoByAccessibilityId(int i, long j, int i2, IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback, int i3, long j2, Bundle bundle) throws RemoteException {
            return null;
        }

        @Override // android.accessibilityservice.IAccessibilityServiceConnection
        public String[] findAccessibilityNodeInfosByText(int i, long j, String str, int i2, IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback, long j2) throws RemoteException {
            return null;
        }

        @Override // android.accessibilityservice.IAccessibilityServiceConnection
        public String[] findAccessibilityNodeInfosByViewId(int i, long j, String str, int i2, IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback, long j2) throws RemoteException {
            return null;
        }

        @Override // android.accessibilityservice.IAccessibilityServiceConnection
        public String[] findFocus(int i, long j, int i2, int i3, IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback, long j2) throws RemoteException {
            return null;
        }

        @Override // android.accessibilityservice.IAccessibilityServiceConnection
        public String[] focusSearch(int i, long j, int i2, int i3, IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback, long j2) throws RemoteException {
            return null;
        }

        @Override // android.accessibilityservice.IAccessibilityServiceConnection
        public float getMagnificationCenterX(int i) throws RemoteException {
            return 0.0f;
        }

        @Override // android.accessibilityservice.IAccessibilityServiceConnection
        public float getMagnificationCenterY(int i) throws RemoteException {
            return 0.0f;
        }

        @Override // android.accessibilityservice.IAccessibilityServiceConnection
        public Region getMagnificationRegion(int i) throws RemoteException {
            return null;
        }

        @Override // android.accessibilityservice.IAccessibilityServiceConnection
        public float getMagnificationScale(int i) throws RemoteException {
            return 0.0f;
        }

        @Override // android.accessibilityservice.IAccessibilityServiceConnection
        public IBinder getOverlayWindowToken(int i) throws RemoteException {
            return null;
        }

        @Override // android.accessibilityservice.IAccessibilityServiceConnection
        public AccessibilityServiceInfo getServiceInfo() throws RemoteException {
            return null;
        }

        @Override // android.accessibilityservice.IAccessibilityServiceConnection
        public int getSoftKeyboardShowMode() throws RemoteException {
            return 0;
        }

        @Override // android.accessibilityservice.IAccessibilityServiceConnection
        public List<AccessibilityNodeInfo.AccessibilityAction> getSystemActions() throws RemoteException {
            return null;
        }

        @Override // android.accessibilityservice.IAccessibilityServiceConnection
        public AccessibilityWindowInfo getWindow(int i) throws RemoteException {
            return null;
        }

        @Override // android.accessibilityservice.IAccessibilityServiceConnection
        public int getWindowIdForLeashToken(IBinder iBinder) throws RemoteException {
            return 0;
        }

        @Override // android.accessibilityservice.IAccessibilityServiceConnection
        public AccessibilityWindowInfo.WindowListSparseArray getWindows() throws RemoteException {
            return null;
        }

        @Override // android.accessibilityservice.IAccessibilityServiceConnection
        public boolean isAccessibilityButtonAvailable() throws RemoteException {
            return false;
        }

        @Override // android.accessibilityservice.IAccessibilityServiceConnection
        public boolean isFingerprintGestureDetectionAvailable() throws RemoteException {
            return false;
        }

        @Override // android.accessibilityservice.IAccessibilityServiceConnection
        public boolean performAccessibilityAction(int i, long j, int i2, Bundle bundle, int i3, IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback, long j2) throws RemoteException {
            return false;
        }

        @Override // android.accessibilityservice.IAccessibilityServiceConnection
        public boolean performGlobalAction(int i) throws RemoteException {
            return false;
        }

        @Override // android.accessibilityservice.IAccessibilityServiceConnection
        public boolean resetMagnification(int i, boolean z) throws RemoteException {
            return false;
        }

        @Override // android.accessibilityservice.IAccessibilityServiceConnection
        public void sendGesture(int i, ParceledListSlice parceledListSlice) throws RemoteException {
        }

        @Override // android.accessibilityservice.IAccessibilityServiceConnection
        public void setGestureDetectionPassthroughRegion(int i, Region region) throws RemoteException {
        }

        @Override // android.accessibilityservice.IAccessibilityServiceConnection
        public void setMagnificationCallbackEnabled(int i, boolean z) throws RemoteException {
        }

        @Override // android.accessibilityservice.IAccessibilityServiceConnection
        public boolean setMagnificationScaleAndCenter(int i, float f, float f2, float f3, boolean z) throws RemoteException {
            return false;
        }

        @Override // android.accessibilityservice.IAccessibilityServiceConnection
        public void setOnKeyEventResult(boolean z, int i) throws RemoteException {
        }

        @Override // android.accessibilityservice.IAccessibilityServiceConnection
        public void setServiceInfo(AccessibilityServiceInfo accessibilityServiceInfo) throws RemoteException {
        }

        @Override // android.accessibilityservice.IAccessibilityServiceConnection
        public void setSoftKeyboardCallbackEnabled(boolean z) throws RemoteException {
        }

        @Override // android.accessibilityservice.IAccessibilityServiceConnection
        public boolean setSoftKeyboardShowMode(int i) throws RemoteException {
            return false;
        }

        @Override // android.accessibilityservice.IAccessibilityServiceConnection
        public void setTouchExplorationPassthroughRegion(int i, Region region) throws RemoteException {
        }

        @Override // android.accessibilityservice.IAccessibilityServiceConnection
        public boolean switchToInputMethod(String str) throws RemoteException {
            return false;
        }

        @Override // android.accessibilityservice.IAccessibilityServiceConnection
        public void takeScreenshot(int i, RemoteCallback remoteCallback) throws RemoteException {
        }
    }

    void disableSelf() throws RemoteException;

    void dispatchGesture(int i, ParceledListSlice parceledListSlice, int i2) throws RemoteException;

    String[] findAccessibilityNodeInfoByAccessibilityId(int i, long j, int i2, IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback, int i3, long j2, Bundle bundle) throws RemoteException;

    String[] findAccessibilityNodeInfosByText(int i, long j, String str, int i2, IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback, long j2) throws RemoteException;

    String[] findAccessibilityNodeInfosByViewId(int i, long j, String str, int i2, IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback, long j2) throws RemoteException;

    String[] findFocus(int i, long j, int i2, int i3, IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback, long j2) throws RemoteException;

    String[] focusSearch(int i, long j, int i2, int i3, IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback, long j2) throws RemoteException;

    float getMagnificationCenterX(int i) throws RemoteException;

    float getMagnificationCenterY(int i) throws RemoteException;

    Region getMagnificationRegion(int i) throws RemoteException;

    float getMagnificationScale(int i) throws RemoteException;

    IBinder getOverlayWindowToken(int i) throws RemoteException;

    AccessibilityServiceInfo getServiceInfo() throws RemoteException;

    int getSoftKeyboardShowMode() throws RemoteException;

    List<AccessibilityNodeInfo.AccessibilityAction> getSystemActions() throws RemoteException;

    AccessibilityWindowInfo getWindow(int i) throws RemoteException;

    int getWindowIdForLeashToken(IBinder iBinder) throws RemoteException;

    AccessibilityWindowInfo.WindowListSparseArray getWindows() throws RemoteException;

    boolean isAccessibilityButtonAvailable() throws RemoteException;

    boolean isFingerprintGestureDetectionAvailable() throws RemoteException;

    boolean performAccessibilityAction(int i, long j, int i2, Bundle bundle, int i3, IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback, long j2) throws RemoteException;

    boolean performGlobalAction(int i) throws RemoteException;

    boolean resetMagnification(int i, boolean z) throws RemoteException;

    void sendGesture(int i, ParceledListSlice parceledListSlice) throws RemoteException;

    void setGestureDetectionPassthroughRegion(int i, Region region) throws RemoteException;

    void setMagnificationCallbackEnabled(int i, boolean z) throws RemoteException;

    boolean setMagnificationScaleAndCenter(int i, float f, float f2, float f3, boolean z) throws RemoteException;

    void setOnKeyEventResult(boolean z, int i) throws RemoteException;

    void setServiceInfo(AccessibilityServiceInfo accessibilityServiceInfo) throws RemoteException;

    void setSoftKeyboardCallbackEnabled(boolean z) throws RemoteException;

    boolean setSoftKeyboardShowMode(int i) throws RemoteException;

    void setTouchExplorationPassthroughRegion(int i, Region region) throws RemoteException;

    boolean switchToInputMethod(String str) throws RemoteException;

    void takeScreenshot(int i, RemoteCallback remoteCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IAccessibilityServiceConnection {
        private static final String DESCRIPTOR = "android.accessibilityservice.IAccessibilityServiceConnection";
        static final int TRANSACTION_disableSelf = 13;
        static final int TRANSACTION_dispatchGesture = 28;
        static final int TRANSACTION_findAccessibilityNodeInfoByAccessibilityId = 2;
        static final int TRANSACTION_findAccessibilityNodeInfosByText = 3;
        static final int TRANSACTION_findAccessibilityNodeInfosByViewId = 4;
        static final int TRANSACTION_findFocus = 5;
        static final int TRANSACTION_focusSearch = 6;
        static final int TRANSACTION_getMagnificationCenterX = 16;
        static final int TRANSACTION_getMagnificationCenterY = 17;
        static final int TRANSACTION_getMagnificationRegion = 18;
        static final int TRANSACTION_getMagnificationScale = 15;
        static final int TRANSACTION_getOverlayWindowToken = 30;
        static final int TRANSACTION_getServiceInfo = 10;
        static final int TRANSACTION_getSoftKeyboardShowMode = 23;
        static final int TRANSACTION_getSystemActions = 12;
        static final int TRANSACTION_getWindow = 8;
        static final int TRANSACTION_getWindowIdForLeashToken = 31;
        static final int TRANSACTION_getWindows = 9;
        static final int TRANSACTION_isAccessibilityButtonAvailable = 26;
        static final int TRANSACTION_isFingerprintGestureDetectionAvailable = 29;
        static final int TRANSACTION_performAccessibilityAction = 7;
        static final int TRANSACTION_performGlobalAction = 11;
        static final int TRANSACTION_resetMagnification = 19;
        static final int TRANSACTION_sendGesture = 27;
        static final int TRANSACTION_setGestureDetectionPassthroughRegion = 33;
        static final int TRANSACTION_setMagnificationCallbackEnabled = 21;
        static final int TRANSACTION_setMagnificationScaleAndCenter = 20;
        static final int TRANSACTION_setOnKeyEventResult = 14;
        static final int TRANSACTION_setServiceInfo = 1;
        static final int TRANSACTION_setSoftKeyboardCallbackEnabled = 24;
        static final int TRANSACTION_setSoftKeyboardShowMode = 22;
        static final int TRANSACTION_setTouchExplorationPassthroughRegion = 34;
        static final int TRANSACTION_switchToInputMethod = 25;
        static final int TRANSACTION_takeScreenshot = 32;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "setServiceInfo";
                case 2:
                    return "findAccessibilityNodeInfoByAccessibilityId";
                case 3:
                    return "findAccessibilityNodeInfosByText";
                case 4:
                    return "findAccessibilityNodeInfosByViewId";
                case 5:
                    return "findFocus";
                case 6:
                    return "focusSearch";
                case 7:
                    return "performAccessibilityAction";
                case 8:
                    return "getWindow";
                case 9:
                    return "getWindows";
                case 10:
                    return "getServiceInfo";
                case 11:
                    return "performGlobalAction";
                case 12:
                    return "getSystemActions";
                case 13:
                    return "disableSelf";
                case 14:
                    return "setOnKeyEventResult";
                case 15:
                    return "getMagnificationScale";
                case 16:
                    return "getMagnificationCenterX";
                case 17:
                    return "getMagnificationCenterY";
                case 18:
                    return "getMagnificationRegion";
                case 19:
                    return "resetMagnification";
                case 20:
                    return "setMagnificationScaleAndCenter";
                case 21:
                    return "setMagnificationCallbackEnabled";
                case 22:
                    return "setSoftKeyboardShowMode";
                case 23:
                    return "getSoftKeyboardShowMode";
                case 24:
                    return "setSoftKeyboardCallbackEnabled";
                case 25:
                    return "switchToInputMethod";
                case 26:
                    return "isAccessibilityButtonAvailable";
                case 27:
                    return "sendGesture";
                case 28:
                    return "dispatchGesture";
                case 29:
                    return "isFingerprintGestureDetectionAvailable";
                case 30:
                    return "getOverlayWindowToken";
                case 31:
                    return "getWindowIdForLeashToken";
                case 32:
                    return "takeScreenshot";
                case 33:
                    return "setGestureDetectionPassthroughRegion";
                case 34:
                    return "setTouchExplorationPassthroughRegion";
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

        public static IAccessibilityServiceConnection asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAccessibilityServiceConnection)) {
                return (IAccessibilityServiceConnection) iInterfaceQueryLocalInterface;
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
                    setServiceInfo(parcel.readInt() != 0 ? AccessibilityServiceInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] strArrFindAccessibilityNodeInfoByAccessibilityId = findAccessibilityNodeInfoByAccessibilityId(parcel.readInt(), parcel.readLong(), parcel.readInt(), IAccessibilityInteractionConnectionCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readLong(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeStringArray(strArrFindAccessibilityNodeInfoByAccessibilityId);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] strArrFindAccessibilityNodeInfosByText = findAccessibilityNodeInfosByText(parcel.readInt(), parcel.readLong(), parcel.readString(), parcel.readInt(), IAccessibilityInteractionConnectionCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeStringArray(strArrFindAccessibilityNodeInfosByText);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] strArrFindAccessibilityNodeInfosByViewId = findAccessibilityNodeInfosByViewId(parcel.readInt(), parcel.readLong(), parcel.readString(), parcel.readInt(), IAccessibilityInteractionConnectionCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeStringArray(strArrFindAccessibilityNodeInfosByViewId);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] strArrFindFocus = findFocus(parcel.readInt(), parcel.readLong(), parcel.readInt(), parcel.readInt(), IAccessibilityInteractionConnectionCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeStringArray(strArrFindFocus);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] strArrFocusSearch = focusSearch(parcel.readInt(), parcel.readLong(), parcel.readInt(), parcel.readInt(), IAccessibilityInteractionConnectionCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeStringArray(strArrFocusSearch);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zPerformAccessibilityAction = performAccessibilityAction(parcel.readInt(), parcel.readLong(), parcel.readInt(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), IAccessibilityInteractionConnectionCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(zPerformAccessibilityAction ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    AccessibilityWindowInfo window = getWindow(parcel.readInt());
                    parcel2.writeNoException();
                    if (window != null) {
                        parcel2.writeInt(1);
                        window.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    AccessibilityWindowInfo.WindowListSparseArray windows = getWindows();
                    parcel2.writeNoException();
                    if (windows != null) {
                        parcel2.writeInt(1);
                        windows.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    AccessibilityServiceInfo serviceInfo = getServiceInfo();
                    parcel2.writeNoException();
                    if (serviceInfo != null) {
                        parcel2.writeInt(1);
                        serviceInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zPerformGlobalAction = performGlobalAction(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zPerformGlobalAction ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<AccessibilityNodeInfo.AccessibilityAction> systemActions = getSystemActions();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(systemActions);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    disableSelf();
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    setOnKeyEventResult(parcel.readInt() != 0, parcel.readInt());
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    float magnificationScale = getMagnificationScale(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeFloat(magnificationScale);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    float magnificationCenterX = getMagnificationCenterX(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeFloat(magnificationCenterX);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    float magnificationCenterY = getMagnificationCenterY(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeFloat(magnificationCenterY);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    Region magnificationRegion = getMagnificationRegion(parcel.readInt());
                    parcel2.writeNoException();
                    if (magnificationRegion != null) {
                        parcel2.writeInt(1);
                        magnificationRegion.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zResetMagnification = resetMagnification(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zResetMagnification ? 1 : 0);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean magnificationScaleAndCenter = setMagnificationScaleAndCenter(parcel.readInt(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(magnificationScaleAndCenter ? 1 : 0);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    setMagnificationCallbackEnabled(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean softKeyboardShowMode = setSoftKeyboardShowMode(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(softKeyboardShowMode ? 1 : 0);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    int softKeyboardShowMode2 = getSoftKeyboardShowMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(softKeyboardShowMode2);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSoftKeyboardCallbackEnabled(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zSwitchToInputMethod = switchToInputMethod(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zSwitchToInputMethod ? 1 : 0);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsAccessibilityButtonAvailable = isAccessibilityButtonAvailable();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsAccessibilityButtonAvailable ? 1 : 0);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendGesture(parcel.readInt(), parcel.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    dispatchGesture(parcel.readInt(), parcel.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsFingerprintGestureDetectionAvailable = isFingerprintGestureDetectionAvailable();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsFingerprintGestureDetectionAvailable ? 1 : 0);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    IBinder overlayWindowToken = getOverlayWindowToken(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(overlayWindowToken);
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    int windowIdForLeashToken = getWindowIdForLeashToken(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    parcel2.writeInt(windowIdForLeashToken);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    takeScreenshot(parcel.readInt(), parcel.readInt() != 0 ? RemoteCallback.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    setGestureDetectionPassthroughRegion(parcel.readInt(), parcel.readInt() != 0 ? Region.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    setTouchExplorationPassthroughRegion(parcel.readInt(), parcel.readInt() != 0 ? Region.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IAccessibilityServiceConnection {
            public static IAccessibilityServiceConnection sDefaultImpl;
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

            @Override // android.accessibilityservice.IAccessibilityServiceConnection
            public void setServiceInfo(AccessibilityServiceInfo accessibilityServiceInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (accessibilityServiceInfo != null) {
                        parcelObtain.writeInt(1);
                        accessibilityServiceInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setServiceInfo(accessibilityServiceInfo);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.accessibilityservice.IAccessibilityServiceConnection
            public String[] findAccessibilityNodeInfoByAccessibilityId(int i, long j, int i2, IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback, int i3, long j2, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iAccessibilityInteractionConnectionCallback != null ? iAccessibilityInteractionConnectionCallback.asBinder() : null);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeLong(j2);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().findAccessibilityNodeInfoByAccessibilityId(i, j, i2, iAccessibilityInteractionConnectionCallback, i3, j2, bundle);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.accessibilityservice.IAccessibilityServiceConnection
            public String[] findAccessibilityNodeInfosByText(int i, long j, String str, int i2, IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback, long j2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iAccessibilityInteractionConnectionCallback != null ? iAccessibilityInteractionConnectionCallback.asBinder() : null);
                    parcelObtain.writeLong(j2);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().findAccessibilityNodeInfosByText(i, j, str, i2, iAccessibilityInteractionConnectionCallback, j2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.accessibilityservice.IAccessibilityServiceConnection
            public String[] findAccessibilityNodeInfosByViewId(int i, long j, String str, int i2, IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback, long j2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iAccessibilityInteractionConnectionCallback != null ? iAccessibilityInteractionConnectionCallback.asBinder() : null);
                    parcelObtain.writeLong(j2);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().findAccessibilityNodeInfosByViewId(i, j, str, i2, iAccessibilityInteractionConnectionCallback, j2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.accessibilityservice.IAccessibilityServiceConnection
            public String[] findFocus(int i, long j, int i2, int i3, IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback, long j2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeStrongBinder(iAccessibilityInteractionConnectionCallback != null ? iAccessibilityInteractionConnectionCallback.asBinder() : null);
                    parcelObtain.writeLong(j2);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().findFocus(i, j, i2, i3, iAccessibilityInteractionConnectionCallback, j2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.accessibilityservice.IAccessibilityServiceConnection
            public String[] focusSearch(int i, long j, int i2, int i3, IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback, long j2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeStrongBinder(iAccessibilityInteractionConnectionCallback != null ? iAccessibilityInteractionConnectionCallback.asBinder() : null);
                    parcelObtain.writeLong(j2);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().focusSearch(i, j, i2, i3, iAccessibilityInteractionConnectionCallback, j2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.accessibilityservice.IAccessibilityServiceConnection
            public boolean performAccessibilityAction(int i, long j, int i2, Bundle bundle, int i3, IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback, long j2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i2);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeStrongBinder(iAccessibilityInteractionConnectionCallback != null ? iAccessibilityInteractionConnectionCallback.asBinder() : null);
                    parcelObtain.writeLong(j2);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().performAccessibilityAction(i, j, i2, bundle, i3, iAccessibilityInteractionConnectionCallback, j2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.accessibilityservice.IAccessibilityServiceConnection
            public AccessibilityWindowInfo getWindow(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWindow(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? AccessibilityWindowInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.accessibilityservice.IAccessibilityServiceConnection
            public AccessibilityWindowInfo.WindowListSparseArray getWindows() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWindows();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? AccessibilityWindowInfo.WindowListSparseArray.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.accessibilityservice.IAccessibilityServiceConnection
            public AccessibilityServiceInfo getServiceInfo() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getServiceInfo();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? AccessibilityServiceInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.accessibilityservice.IAccessibilityServiceConnection
            public boolean performGlobalAction(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().performGlobalAction(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.accessibilityservice.IAccessibilityServiceConnection
            public List<AccessibilityNodeInfo.AccessibilityAction> getSystemActions() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSystemActions();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(AccessibilityNodeInfo.AccessibilityAction.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.accessibilityservice.IAccessibilityServiceConnection
            public void disableSelf() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disableSelf();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.accessibilityservice.IAccessibilityServiceConnection
            public void setOnKeyEventResult(boolean z, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(14, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setOnKeyEventResult(z, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.accessibilityservice.IAccessibilityServiceConnection
            public float getMagnificationScale(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMagnificationScale(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readFloat();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.accessibilityservice.IAccessibilityServiceConnection
            public float getMagnificationCenterX(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMagnificationCenterX(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readFloat();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.accessibilityservice.IAccessibilityServiceConnection
            public float getMagnificationCenterY(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMagnificationCenterY(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readFloat();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.accessibilityservice.IAccessibilityServiceConnection
            public Region getMagnificationRegion(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMagnificationRegion(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Region.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.accessibilityservice.IAccessibilityServiceConnection
            public boolean resetMagnification(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().resetMagnification(i, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.accessibilityservice.IAccessibilityServiceConnection
            public boolean setMagnificationScaleAndCenter(int i, float f, float f2, float f3, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeFloat(f);
                    parcelObtain.writeFloat(f2);
                    parcelObtain.writeFloat(f3);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setMagnificationScaleAndCenter(i, f, f2, f3, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.accessibilityservice.IAccessibilityServiceConnection
            public void setMagnificationCallbackEnabled(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setMagnificationCallbackEnabled(i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.accessibilityservice.IAccessibilityServiceConnection
            public boolean setSoftKeyboardShowMode(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setSoftKeyboardShowMode(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.accessibilityservice.IAccessibilityServiceConnection
            public int getSoftKeyboardShowMode() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSoftKeyboardShowMode();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.accessibilityservice.IAccessibilityServiceConnection
            public void setSoftKeyboardCallbackEnabled(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSoftKeyboardCallbackEnabled(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.accessibilityservice.IAccessibilityServiceConnection
            public boolean switchToInputMethod(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().switchToInputMethod(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.accessibilityservice.IAccessibilityServiceConnection
            public boolean isAccessibilityButtonAvailable() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAccessibilityButtonAvailable();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.accessibilityservice.IAccessibilityServiceConnection
            public void sendGesture(int i, ParceledListSlice parceledListSlice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (parceledListSlice != null) {
                        parcelObtain.writeInt(1);
                        parceledListSlice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendGesture(i, parceledListSlice);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.accessibilityservice.IAccessibilityServiceConnection
            public void dispatchGesture(int i, ParceledListSlice parceledListSlice, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (parceledListSlice != null) {
                        parcelObtain.writeInt(1);
                        parceledListSlice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dispatchGesture(i, parceledListSlice, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.accessibilityservice.IAccessibilityServiceConnection
            public boolean isFingerprintGestureDetectionAvailable() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isFingerprintGestureDetectionAvailable();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.accessibilityservice.IAccessibilityServiceConnection
            public IBinder getOverlayWindowToken(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getOverlayWindowToken(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readStrongBinder();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.accessibilityservice.IAccessibilityServiceConnection
            public int getWindowIdForLeashToken(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWindowIdForLeashToken(iBinder);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.accessibilityservice.IAccessibilityServiceConnection
            public void takeScreenshot(int i, RemoteCallback remoteCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (remoteCallback != null) {
                        parcelObtain.writeInt(1);
                        remoteCallback.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().takeScreenshot(i, remoteCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.accessibilityservice.IAccessibilityServiceConnection
            public void setGestureDetectionPassthroughRegion(int i, Region region) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (region != null) {
                        parcelObtain.writeInt(1);
                        region.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setGestureDetectionPassthroughRegion(i, region);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.accessibilityservice.IAccessibilityServiceConnection
            public void setTouchExplorationPassthroughRegion(int i, Region region) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (region != null) {
                        parcelObtain.writeInt(1);
                        region.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(34, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setTouchExplorationPassthroughRegion(i, region);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAccessibilityServiceConnection iAccessibilityServiceConnection) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iAccessibilityServiceConnection == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAccessibilityServiceConnection;
            return true;
        }

        public static IAccessibilityServiceConnection getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
