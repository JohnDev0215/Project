package android.hardware.input;

import android.hardware.input.IInputDevicesChangedListener;
import android.hardware.input.ITabletModeChangedListener;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.InputDevice;
import android.view.InputEvent;
import android.view.InputMonitor;
import android.view.PointerIcon;
import android.view.VerifiedInputEvent;

/* JADX INFO: loaded from: classes2.dex */
public interface IInputManager extends IInterface {

    public static class Default implements IInputManager {
        @Override // android.hardware.input.IInputManager
        public void addKeyboardLayoutForInputDevice(InputDeviceIdentifier inputDeviceIdentifier, String str) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public void addPortAssociation(String str, int i) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.hardware.input.IInputManager
        public void cancelVibrate(int i, IBinder iBinder) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public void disableInputDevice(int i) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public void enableInputDevice(int i) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public String getCurrentKeyboardLayoutForInputDevice(InputDeviceIdentifier inputDeviceIdentifier) throws RemoteException {
            return null;
        }

        @Override // android.hardware.input.IInputManager
        public String[] getEnabledKeyboardLayoutsForInputDevice(InputDeviceIdentifier inputDeviceIdentifier) throws RemoteException {
            return null;
        }

        @Override // android.hardware.input.IInputManager
        public InputDevice getInputDevice(int i) throws RemoteException {
            return null;
        }

        @Override // android.hardware.input.IInputManager
        public int[] getInputDeviceIds() throws RemoteException {
            return null;
        }

        @Override // android.hardware.input.IInputManager
        public KeyboardLayout getKeyboardLayout(String str) throws RemoteException {
            return null;
        }

        @Override // android.hardware.input.IInputManager
        public KeyboardLayout[] getKeyboardLayouts() throws RemoteException {
            return null;
        }

        @Override // android.hardware.input.IInputManager
        public KeyboardLayout[] getKeyboardLayoutsForInputDevice(InputDeviceIdentifier inputDeviceIdentifier) throws RemoteException {
            return null;
        }

        @Override // android.hardware.input.IInputManager
        public TouchCalibration getTouchCalibrationForInputDevice(String str, int i) throws RemoteException {
            return null;
        }

        @Override // android.hardware.input.IInputManager
        public boolean hasKeys(int i, int i2, int[] iArr, boolean[] zArr) throws RemoteException {
            return false;
        }

        @Override // android.hardware.input.IInputManager
        public boolean injectInputEvent(InputEvent inputEvent, int i) throws RemoteException {
            return false;
        }

        @Override // android.hardware.input.IInputManager
        public int isInTabletMode() throws RemoteException {
            return 0;
        }

        @Override // android.hardware.input.IInputManager
        public boolean isInputDeviceEnabled(int i) throws RemoteException {
            return false;
        }

        @Override // android.hardware.input.IInputManager
        public int isMicMuted() throws RemoteException {
            return 0;
        }

        @Override // android.hardware.input.IInputManager
        public InputMonitor monitorGestureInput(String str, int i) throws RemoteException {
            return null;
        }

        @Override // android.hardware.input.IInputManager
        public void registerInputDevicesChangedListener(IInputDevicesChangedListener iInputDevicesChangedListener) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public void registerTabletModeChangedListener(ITabletModeChangedListener iTabletModeChangedListener) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public void removeKeyboardLayoutForInputDevice(InputDeviceIdentifier inputDeviceIdentifier, String str) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public void removePortAssociation(String str) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public void requestPointerCapture(IBinder iBinder, boolean z) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public void setCurrentKeyboardLayoutForInputDevice(InputDeviceIdentifier inputDeviceIdentifier, String str) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public void setCustomPointerIcon(PointerIcon pointerIcon) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public void setPointerIconType(int i) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public void setTouchCalibrationForInputDevice(String str, int i, TouchCalibration touchCalibration) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public void tryPointerSpeed(int i) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public VerifiedInputEvent verifyInputEvent(InputEvent inputEvent) throws RemoteException {
            return null;
        }

        @Override // android.hardware.input.IInputManager
        public void vibrate(int i, long[] jArr, int i2, IBinder iBinder) throws RemoteException {
        }
    }

    void addKeyboardLayoutForInputDevice(InputDeviceIdentifier inputDeviceIdentifier, String str) throws RemoteException;

    void addPortAssociation(String str, int i) throws RemoteException;

    void cancelVibrate(int i, IBinder iBinder) throws RemoteException;

    void disableInputDevice(int i) throws RemoteException;

    void enableInputDevice(int i) throws RemoteException;

    String getCurrentKeyboardLayoutForInputDevice(InputDeviceIdentifier inputDeviceIdentifier) throws RemoteException;

    String[] getEnabledKeyboardLayoutsForInputDevice(InputDeviceIdentifier inputDeviceIdentifier) throws RemoteException;

    InputDevice getInputDevice(int i) throws RemoteException;

    int[] getInputDeviceIds() throws RemoteException;

    KeyboardLayout getKeyboardLayout(String str) throws RemoteException;

    KeyboardLayout[] getKeyboardLayouts() throws RemoteException;

    KeyboardLayout[] getKeyboardLayoutsForInputDevice(InputDeviceIdentifier inputDeviceIdentifier) throws RemoteException;

    TouchCalibration getTouchCalibrationForInputDevice(String str, int i) throws RemoteException;

    boolean hasKeys(int i, int i2, int[] iArr, boolean[] zArr) throws RemoteException;

    boolean injectInputEvent(InputEvent inputEvent, int i) throws RemoteException;

    int isInTabletMode() throws RemoteException;

    boolean isInputDeviceEnabled(int i) throws RemoteException;

    int isMicMuted() throws RemoteException;

    InputMonitor monitorGestureInput(String str, int i) throws RemoteException;

    void registerInputDevicesChangedListener(IInputDevicesChangedListener iInputDevicesChangedListener) throws RemoteException;

    void registerTabletModeChangedListener(ITabletModeChangedListener iTabletModeChangedListener) throws RemoteException;

    void removeKeyboardLayoutForInputDevice(InputDeviceIdentifier inputDeviceIdentifier, String str) throws RemoteException;

    void removePortAssociation(String str) throws RemoteException;

    void requestPointerCapture(IBinder iBinder, boolean z) throws RemoteException;

    void setCurrentKeyboardLayoutForInputDevice(InputDeviceIdentifier inputDeviceIdentifier, String str) throws RemoteException;

    void setCustomPointerIcon(PointerIcon pointerIcon) throws RemoteException;

    void setPointerIconType(int i) throws RemoteException;

    void setTouchCalibrationForInputDevice(String str, int i, TouchCalibration touchCalibration) throws RemoteException;

    void tryPointerSpeed(int i) throws RemoteException;

    VerifiedInputEvent verifyInputEvent(InputEvent inputEvent) throws RemoteException;

    void vibrate(int i, long[] jArr, int i2, IBinder iBinder) throws RemoteException;

    public static abstract class Stub extends Binder implements IInputManager {
        private static final String DESCRIPTOR = "android.hardware.input.IInputManager";
        static final int TRANSACTION_addKeyboardLayoutForInputDevice = 18;
        static final int TRANSACTION_addPortAssociation = 30;
        static final int TRANSACTION_cancelVibrate = 25;
        static final int TRANSACTION_disableInputDevice = 5;
        static final int TRANSACTION_enableInputDevice = 4;
        static final int TRANSACTION_getCurrentKeyboardLayoutForInputDevice = 15;
        static final int TRANSACTION_getEnabledKeyboardLayoutsForInputDevice = 17;
        static final int TRANSACTION_getInputDevice = 1;
        static final int TRANSACTION_getInputDeviceIds = 2;
        static final int TRANSACTION_getKeyboardLayout = 14;
        static final int TRANSACTION_getKeyboardLayouts = 12;
        static final int TRANSACTION_getKeyboardLayoutsForInputDevice = 13;
        static final int TRANSACTION_getTouchCalibrationForInputDevice = 10;
        static final int TRANSACTION_hasKeys = 6;
        static final int TRANSACTION_injectInputEvent = 8;
        static final int TRANSACTION_isInTabletMode = 21;
        static final int TRANSACTION_isInputDeviceEnabled = 3;
        static final int TRANSACTION_isMicMuted = 23;
        static final int TRANSACTION_monitorGestureInput = 29;
        static final int TRANSACTION_registerInputDevicesChangedListener = 20;
        static final int TRANSACTION_registerTabletModeChangedListener = 22;
        static final int TRANSACTION_removeKeyboardLayoutForInputDevice = 19;
        static final int TRANSACTION_removePortAssociation = 31;
        static final int TRANSACTION_requestPointerCapture = 28;
        static final int TRANSACTION_setCurrentKeyboardLayoutForInputDevice = 16;
        static final int TRANSACTION_setCustomPointerIcon = 27;
        static final int TRANSACTION_setPointerIconType = 26;
        static final int TRANSACTION_setTouchCalibrationForInputDevice = 11;
        static final int TRANSACTION_tryPointerSpeed = 7;
        static final int TRANSACTION_verifyInputEvent = 9;
        static final int TRANSACTION_vibrate = 24;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "getInputDevice";
                case 2:
                    return "getInputDeviceIds";
                case 3:
                    return "isInputDeviceEnabled";
                case 4:
                    return "enableInputDevice";
                case 5:
                    return "disableInputDevice";
                case 6:
                    return "hasKeys";
                case 7:
                    return "tryPointerSpeed";
                case 8:
                    return "injectInputEvent";
                case 9:
                    return "verifyInputEvent";
                case 10:
                    return "getTouchCalibrationForInputDevice";
                case 11:
                    return "setTouchCalibrationForInputDevice";
                case 12:
                    return "getKeyboardLayouts";
                case 13:
                    return "getKeyboardLayoutsForInputDevice";
                case 14:
                    return "getKeyboardLayout";
                case 15:
                    return "getCurrentKeyboardLayoutForInputDevice";
                case 16:
                    return "setCurrentKeyboardLayoutForInputDevice";
                case 17:
                    return "getEnabledKeyboardLayoutsForInputDevice";
                case 18:
                    return "addKeyboardLayoutForInputDevice";
                case 19:
                    return "removeKeyboardLayoutForInputDevice";
                case 20:
                    return "registerInputDevicesChangedListener";
                case 21:
                    return "isInTabletMode";
                case 22:
                    return "registerTabletModeChangedListener";
                case 23:
                    return "isMicMuted";
                case 24:
                    return "vibrate";
                case 25:
                    return "cancelVibrate";
                case 26:
                    return "setPointerIconType";
                case 27:
                    return "setCustomPointerIcon";
                case 28:
                    return "requestPointerCapture";
                case 29:
                    return "monitorGestureInput";
                case 30:
                    return "addPortAssociation";
                case 31:
                    return "removePortAssociation";
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

        public static IInputManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IInputManager)) {
                return (IInputManager) iInterfaceQueryLocalInterface;
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
                    InputDevice inputDevice = getInputDevice(parcel.readInt());
                    parcel2.writeNoException();
                    if (inputDevice != null) {
                        parcel2.writeInt(1);
                        inputDevice.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] inputDeviceIds = getInputDeviceIds();
                    parcel2.writeNoException();
                    parcel2.writeIntArray(inputDeviceIds);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsInputDeviceEnabled = isInputDeviceEnabled(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsInputDeviceEnabled ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    enableInputDevice(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    disableInputDevice(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    int i3 = parcel.readInt();
                    int i4 = parcel.readInt();
                    int[] iArrCreateIntArray = parcel.createIntArray();
                    int i5 = parcel.readInt();
                    boolean[] zArr = i5 >= 0 ? new boolean[i5] : null;
                    boolean zHasKeys = hasKeys(i3, i4, iArrCreateIntArray, zArr);
                    parcel2.writeNoException();
                    parcel2.writeInt(zHasKeys ? 1 : 0);
                    parcel2.writeBooleanArray(zArr);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    tryPointerSpeed(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zInjectInputEvent = injectInputEvent(parcel.readInt() != 0 ? InputEvent.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zInjectInputEvent ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    VerifiedInputEvent verifiedInputEventVerifyInputEvent = verifyInputEvent(parcel.readInt() != 0 ? InputEvent.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (verifiedInputEventVerifyInputEvent != null) {
                        parcel2.writeInt(1);
                        verifiedInputEventVerifyInputEvent.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    TouchCalibration touchCalibrationForInputDevice = getTouchCalibrationForInputDevice(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    if (touchCalibrationForInputDevice != null) {
                        parcel2.writeInt(1);
                        touchCalibrationForInputDevice.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    setTouchCalibrationForInputDevice(parcel.readString(), parcel.readInt(), parcel.readInt() != 0 ? TouchCalibration.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    KeyboardLayout[] keyboardLayouts = getKeyboardLayouts();
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(keyboardLayouts, 1);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    KeyboardLayout[] keyboardLayoutsForInputDevice = getKeyboardLayoutsForInputDevice(parcel.readInt() != 0 ? InputDeviceIdentifier.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(keyboardLayoutsForInputDevice, 1);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    KeyboardLayout keyboardLayout = getKeyboardLayout(parcel.readString());
                    parcel2.writeNoException();
                    if (keyboardLayout != null) {
                        parcel2.writeInt(1);
                        keyboardLayout.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    String currentKeyboardLayoutForInputDevice = getCurrentKeyboardLayoutForInputDevice(parcel.readInt() != 0 ? InputDeviceIdentifier.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeString(currentKeyboardLayoutForInputDevice);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    setCurrentKeyboardLayoutForInputDevice(parcel.readInt() != 0 ? InputDeviceIdentifier.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] enabledKeyboardLayoutsForInputDevice = getEnabledKeyboardLayoutsForInputDevice(parcel.readInt() != 0 ? InputDeviceIdentifier.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeStringArray(enabledKeyboardLayoutsForInputDevice);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    addKeyboardLayoutForInputDevice(parcel.readInt() != 0 ? InputDeviceIdentifier.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeKeyboardLayoutForInputDevice(parcel.readInt() != 0 ? InputDeviceIdentifier.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerInputDevicesChangedListener(IInputDevicesChangedListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iIsInTabletMode = isInTabletMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(iIsInTabletMode);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerTabletModeChangedListener(ITabletModeChangedListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iIsMicMuted = isMicMuted();
                    parcel2.writeNoException();
                    parcel2.writeInt(iIsMicMuted);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    vibrate(parcel.readInt(), parcel.createLongArray(), parcel.readInt(), parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    cancelVibrate(parcel.readInt(), parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    setPointerIconType(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    setCustomPointerIcon(parcel.readInt() != 0 ? PointerIcon.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestPointerCapture(parcel.readStrongBinder(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    InputMonitor inputMonitorMonitorGestureInput = monitorGestureInput(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    if (inputMonitorMonitorGestureInput != null) {
                        parcel2.writeInt(1);
                        inputMonitorMonitorGestureInput.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    addPortAssociation(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    removePortAssociation(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IInputManager {
            public static IInputManager sDefaultImpl;
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

            @Override // android.hardware.input.IInputManager
            public InputDevice getInputDevice(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInputDevice(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? InputDevice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public int[] getInputDeviceIds() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInputDeviceIds();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public boolean isInputDeviceEnabled(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isInputDeviceEnabled(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void enableInputDevice(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enableInputDevice(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void disableInputDevice(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disableInputDevice(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public boolean hasKeys(int i, int i2, int[] iArr, boolean[] zArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeIntArray(iArr);
                    if (zArr == null) {
                        parcelObtain.writeInt(-1);
                    } else {
                        parcelObtain.writeInt(zArr.length);
                    }
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasKeys(i, i2, iArr, zArr);
                    }
                    parcelObtain2.readException();
                    boolean z = parcelObtain2.readInt() != 0;
                    parcelObtain2.readBooleanArray(zArr);
                    return z;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void tryPointerSpeed(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().tryPointerSpeed(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public boolean injectInputEvent(InputEvent inputEvent, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (inputEvent != null) {
                        parcelObtain.writeInt(1);
                        inputEvent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().injectInputEvent(inputEvent, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public VerifiedInputEvent verifyInputEvent(InputEvent inputEvent) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (inputEvent != null) {
                        parcelObtain.writeInt(1);
                        inputEvent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().verifyInputEvent(inputEvent);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? VerifiedInputEvent.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public TouchCalibration getTouchCalibrationForInputDevice(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTouchCalibrationForInputDevice(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? TouchCalibration.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void setTouchCalibrationForInputDevice(String str, int i, TouchCalibration touchCalibration) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (touchCalibration != null) {
                        parcelObtain.writeInt(1);
                        touchCalibration.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setTouchCalibrationForInputDevice(str, i, touchCalibration);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public KeyboardLayout[] getKeyboardLayouts() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getKeyboardLayouts();
                    }
                    parcelObtain2.readException();
                    return (KeyboardLayout[]) parcelObtain2.createTypedArray(KeyboardLayout.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public KeyboardLayout[] getKeyboardLayoutsForInputDevice(InputDeviceIdentifier inputDeviceIdentifier) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (inputDeviceIdentifier != null) {
                        parcelObtain.writeInt(1);
                        inputDeviceIdentifier.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getKeyboardLayoutsForInputDevice(inputDeviceIdentifier);
                    }
                    parcelObtain2.readException();
                    return (KeyboardLayout[]) parcelObtain2.createTypedArray(KeyboardLayout.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public KeyboardLayout getKeyboardLayout(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getKeyboardLayout(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? KeyboardLayout.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public String getCurrentKeyboardLayoutForInputDevice(InputDeviceIdentifier inputDeviceIdentifier) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (inputDeviceIdentifier != null) {
                        parcelObtain.writeInt(1);
                        inputDeviceIdentifier.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentKeyboardLayoutForInputDevice(inputDeviceIdentifier);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void setCurrentKeyboardLayoutForInputDevice(InputDeviceIdentifier inputDeviceIdentifier, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (inputDeviceIdentifier != null) {
                        parcelObtain.writeInt(1);
                        inputDeviceIdentifier.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setCurrentKeyboardLayoutForInputDevice(inputDeviceIdentifier, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public String[] getEnabledKeyboardLayoutsForInputDevice(InputDeviceIdentifier inputDeviceIdentifier) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (inputDeviceIdentifier != null) {
                        parcelObtain.writeInt(1);
                        inputDeviceIdentifier.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEnabledKeyboardLayoutsForInputDevice(inputDeviceIdentifier);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void addKeyboardLayoutForInputDevice(InputDeviceIdentifier inputDeviceIdentifier, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (inputDeviceIdentifier != null) {
                        parcelObtain.writeInt(1);
                        inputDeviceIdentifier.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addKeyboardLayoutForInputDevice(inputDeviceIdentifier, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void removeKeyboardLayoutForInputDevice(InputDeviceIdentifier inputDeviceIdentifier, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (inputDeviceIdentifier != null) {
                        parcelObtain.writeInt(1);
                        inputDeviceIdentifier.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeKeyboardLayoutForInputDevice(inputDeviceIdentifier, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void registerInputDevicesChangedListener(IInputDevicesChangedListener iInputDevicesChangedListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iInputDevicesChangedListener != null ? iInputDevicesChangedListener.asBinder() : null);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerInputDevicesChangedListener(iInputDevicesChangedListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public int isInTabletMode() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isInTabletMode();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void registerTabletModeChangedListener(ITabletModeChangedListener iTabletModeChangedListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iTabletModeChangedListener != null ? iTabletModeChangedListener.asBinder() : null);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerTabletModeChangedListener(iTabletModeChangedListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public int isMicMuted() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isMicMuted();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void vibrate(int i, long[] jArr, int i2, IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLongArray(jArr);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().vibrate(i, jArr, i2, iBinder);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void cancelVibrate(int i, IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancelVibrate(i, iBinder);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void setPointerIconType(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPointerIconType(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void setCustomPointerIcon(PointerIcon pointerIcon) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (pointerIcon != null) {
                        parcelObtain.writeInt(1);
                        pointerIcon.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setCustomPointerIcon(pointerIcon);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void requestPointerCapture(IBinder iBinder, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestPointerCapture(iBinder, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public InputMonitor monitorGestureInput(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().monitorGestureInput(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? InputMonitor.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void addPortAssociation(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addPortAssociation(str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void removePortAssociation(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removePortAssociation(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInputManager iInputManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iInputManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iInputManager;
            return true;
        }

        public static IInputManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
