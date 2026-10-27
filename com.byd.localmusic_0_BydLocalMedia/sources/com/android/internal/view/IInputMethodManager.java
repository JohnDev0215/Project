package com.android.internal.view;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodSubtype;
import com.android.internal.view.IInputContext;
import com.android.internal.view.IInputMethodClient;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface IInputMethodManager extends IInterface {

    public static class Default implements IInputMethodManager {
        @Override // com.android.internal.view.IInputMethodManager
        public void addClient(IInputMethodClient iInputMethodClient, IInputContext iInputContext, int i) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.view.IInputMethodManager
        public InputMethodSubtype getCurrentInputMethodSubtype() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.view.IInputMethodManager
        public List<InputMethodInfo> getEnabledInputMethodList(int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.view.IInputMethodManager
        public List<InputMethodSubtype> getEnabledInputMethodSubtypeList(String str, boolean z) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.view.IInputMethodManager
        public List<InputMethodInfo> getInputMethodList(int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.view.IInputMethodManager
        public int getInputMethodWindowVisibleHeight() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.view.IInputMethodManager
        public InputMethodSubtype getLastInputMethodSubtype() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.view.IInputMethodManager
        public boolean hideSoftInput(IInputMethodClient iInputMethodClient, IBinder iBinder, int i, ResultReceiver resultReceiver) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.view.IInputMethodManager
        public boolean isInputMethodPickerShownForTest() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.view.IInputMethodManager
        public void removeImeSurface() throws RemoteException {
        }

        @Override // com.android.internal.view.IInputMethodManager
        public void removeImeSurfaceFromWindow(IBinder iBinder) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputMethodManager
        public void reportActivityView(IInputMethodClient iInputMethodClient, int i, float[] fArr) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputMethodManager
        public void reportPerceptible(IBinder iBinder, boolean z) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputMethodManager
        public void setAdditionalInputMethodSubtypes(String str, InputMethodSubtype[] inputMethodSubtypeArr) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputMethodManager
        public void showInputMethodAndSubtypeEnablerFromClient(IInputMethodClient iInputMethodClient, String str) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputMethodManager
        public void showInputMethodPickerFromClient(IInputMethodClient iInputMethodClient, int i) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputMethodManager
        public void showInputMethodPickerFromSystem(IInputMethodClient iInputMethodClient, int i, int i2) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputMethodManager
        public boolean showSoftInput(IInputMethodClient iInputMethodClient, IBinder iBinder, int i, ResultReceiver resultReceiver) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.view.IInputMethodManager
        public InputBindResult startInputOrWindowGainedFocus(int i, IInputMethodClient iInputMethodClient, IBinder iBinder, int i2, int i3, int i4, EditorInfo editorInfo, IInputContext iInputContext, int i5, int i6) throws RemoteException {
            return null;
        }
    }

    void addClient(IInputMethodClient iInputMethodClient, IInputContext iInputContext, int i) throws RemoteException;

    InputMethodSubtype getCurrentInputMethodSubtype() throws RemoteException;

    List<InputMethodInfo> getEnabledInputMethodList(int i) throws RemoteException;

    List<InputMethodSubtype> getEnabledInputMethodSubtypeList(String str, boolean z) throws RemoteException;

    List<InputMethodInfo> getInputMethodList(int i) throws RemoteException;

    int getInputMethodWindowVisibleHeight() throws RemoteException;

    InputMethodSubtype getLastInputMethodSubtype() throws RemoteException;

    boolean hideSoftInput(IInputMethodClient iInputMethodClient, IBinder iBinder, int i, ResultReceiver resultReceiver) throws RemoteException;

    boolean isInputMethodPickerShownForTest() throws RemoteException;

    void removeImeSurface() throws RemoteException;

    void removeImeSurfaceFromWindow(IBinder iBinder) throws RemoteException;

    void reportActivityView(IInputMethodClient iInputMethodClient, int i, float[] fArr) throws RemoteException;

    void reportPerceptible(IBinder iBinder, boolean z) throws RemoteException;

    void setAdditionalInputMethodSubtypes(String str, InputMethodSubtype[] inputMethodSubtypeArr) throws RemoteException;

    void showInputMethodAndSubtypeEnablerFromClient(IInputMethodClient iInputMethodClient, String str) throws RemoteException;

    void showInputMethodPickerFromClient(IInputMethodClient iInputMethodClient, int i) throws RemoteException;

    void showInputMethodPickerFromSystem(IInputMethodClient iInputMethodClient, int i, int i2) throws RemoteException;

    boolean showSoftInput(IInputMethodClient iInputMethodClient, IBinder iBinder, int i, ResultReceiver resultReceiver) throws RemoteException;

    InputBindResult startInputOrWindowGainedFocus(int i, IInputMethodClient iInputMethodClient, IBinder iBinder, int i2, int i3, int i4, EditorInfo editorInfo, IInputContext iInputContext, int i5, int i6) throws RemoteException;

    public static abstract class Stub extends Binder implements IInputMethodManager {
        private static final String DESCRIPTOR = "com.android.internal.view.IInputMethodManager";
        static final int TRANSACTION_addClient = 1;
        static final int TRANSACTION_getCurrentInputMethodSubtype = 13;
        static final int TRANSACTION_getEnabledInputMethodList = 3;
        static final int TRANSACTION_getEnabledInputMethodSubtypeList = 4;
        static final int TRANSACTION_getInputMethodList = 2;
        static final int TRANSACTION_getInputMethodWindowVisibleHeight = 15;
        static final int TRANSACTION_getLastInputMethodSubtype = 5;
        static final int TRANSACTION_hideSoftInput = 7;
        static final int TRANSACTION_isInputMethodPickerShownForTest = 12;
        static final int TRANSACTION_removeImeSurface = 18;
        static final int TRANSACTION_removeImeSurfaceFromWindow = 19;
        static final int TRANSACTION_reportActivityView = 16;
        static final int TRANSACTION_reportPerceptible = 17;
        static final int TRANSACTION_setAdditionalInputMethodSubtypes = 14;
        static final int TRANSACTION_showInputMethodAndSubtypeEnablerFromClient = 11;
        static final int TRANSACTION_showInputMethodPickerFromClient = 9;
        static final int TRANSACTION_showInputMethodPickerFromSystem = 10;
        static final int TRANSACTION_showSoftInput = 6;
        static final int TRANSACTION_startInputOrWindowGainedFocus = 8;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "addClient";
                case 2:
                    return "getInputMethodList";
                case 3:
                    return "getEnabledInputMethodList";
                case 4:
                    return "getEnabledInputMethodSubtypeList";
                case 5:
                    return "getLastInputMethodSubtype";
                case 6:
                    return "showSoftInput";
                case 7:
                    return "hideSoftInput";
                case 8:
                    return "startInputOrWindowGainedFocus";
                case 9:
                    return "showInputMethodPickerFromClient";
                case 10:
                    return "showInputMethodPickerFromSystem";
                case 11:
                    return "showInputMethodAndSubtypeEnablerFromClient";
                case 12:
                    return "isInputMethodPickerShownForTest";
                case 13:
                    return "getCurrentInputMethodSubtype";
                case 14:
                    return "setAdditionalInputMethodSubtypes";
                case 15:
                    return "getInputMethodWindowVisibleHeight";
                case 16:
                    return "reportActivityView";
                case 17:
                    return "reportPerceptible";
                case 18:
                    return "removeImeSurface";
                case 19:
                    return "removeImeSurfaceFromWindow";
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

        public static IInputMethodManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IInputMethodManager)) {
                return (IInputMethodManager) iInterfaceQueryLocalInterface;
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
                    addClient(IInputMethodClient.Stub.asInterface(parcel.readStrongBinder()), IInputContext.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<InputMethodInfo> inputMethodList = getInputMethodList(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(inputMethodList);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<InputMethodInfo> enabledInputMethodList = getEnabledInputMethodList(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(enabledInputMethodList);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<InputMethodSubtype> enabledInputMethodSubtypeList = getEnabledInputMethodSubtypeList(parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeTypedList(enabledInputMethodSubtypeList);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    InputMethodSubtype lastInputMethodSubtype = getLastInputMethodSubtype();
                    parcel2.writeNoException();
                    if (lastInputMethodSubtype != null) {
                        parcel2.writeInt(1);
                        lastInputMethodSubtype.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zShowSoftInput = showSoftInput(IInputMethodClient.Stub.asInterface(parcel.readStrongBinder()), parcel.readStrongBinder(), parcel.readInt(), parcel.readInt() != 0 ? ResultReceiver.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zShowSoftInput ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHideSoftInput = hideSoftInput(IInputMethodClient.Stub.asInterface(parcel.readStrongBinder()), parcel.readStrongBinder(), parcel.readInt(), parcel.readInt() != 0 ? ResultReceiver.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zHideSoftInput ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    InputBindResult inputBindResultStartInputOrWindowGainedFocus = startInputOrWindowGainedFocus(parcel.readInt(), IInputMethodClient.Stub.asInterface(parcel.readStrongBinder()), parcel.readStrongBinder(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? EditorInfo.CREATOR.createFromParcel(parcel) : null, IInputContext.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (inputBindResultStartInputOrWindowGainedFocus != null) {
                        parcel2.writeInt(1);
                        inputBindResultStartInputOrWindowGainedFocus.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    showInputMethodPickerFromClient(IInputMethodClient.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    showInputMethodPickerFromSystem(IInputMethodClient.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    showInputMethodAndSubtypeEnablerFromClient(IInputMethodClient.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsInputMethodPickerShownForTest = isInputMethodPickerShownForTest();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsInputMethodPickerShownForTest ? 1 : 0);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    InputMethodSubtype currentInputMethodSubtype = getCurrentInputMethodSubtype();
                    parcel2.writeNoException();
                    if (currentInputMethodSubtype != null) {
                        parcel2.writeInt(1);
                        currentInputMethodSubtype.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAdditionalInputMethodSubtypes(parcel.readString(), (InputMethodSubtype[]) parcel.createTypedArray(InputMethodSubtype.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    int inputMethodWindowVisibleHeight = getInputMethodWindowVisibleHeight();
                    parcel2.writeNoException();
                    parcel2.writeInt(inputMethodWindowVisibleHeight);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportActivityView(IInputMethodClient.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.createFloatArray());
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportPerceptible(parcel.readStrongBinder(), parcel.readInt() != 0);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeImeSurface();
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeImeSurfaceFromWindow(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IInputMethodManager {
            public static IInputMethodManager sDefaultImpl;
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

            @Override // com.android.internal.view.IInputMethodManager
            public void addClient(IInputMethodClient iInputMethodClient, IInputContext iInputContext, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iInputMethodClient != null ? iInputMethodClient.asBinder() : null);
                    parcelObtain.writeStrongBinder(iInputContext != null ? iInputContext.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addClient(iInputMethodClient, iInputContext, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.IInputMethodManager
            public List<InputMethodInfo> getInputMethodList(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInputMethodList(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(InputMethodInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.IInputMethodManager
            public List<InputMethodInfo> getEnabledInputMethodList(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEnabledInputMethodList(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(InputMethodInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.IInputMethodManager
            public List<InputMethodSubtype> getEnabledInputMethodSubtypeList(String str, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEnabledInputMethodSubtypeList(str, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(InputMethodSubtype.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.IInputMethodManager
            public InputMethodSubtype getLastInputMethodSubtype() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLastInputMethodSubtype();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? InputMethodSubtype.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.IInputMethodManager
            public boolean showSoftInput(IInputMethodClient iInputMethodClient, IBinder iBinder, int i, ResultReceiver resultReceiver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iInputMethodClient != null ? iInputMethodClient.asBinder() : null);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i);
                    if (resultReceiver != null) {
                        parcelObtain.writeInt(1);
                        resultReceiver.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().showSoftInput(iInputMethodClient, iBinder, i, resultReceiver);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.IInputMethodManager
            public boolean hideSoftInput(IInputMethodClient iInputMethodClient, IBinder iBinder, int i, ResultReceiver resultReceiver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iInputMethodClient != null ? iInputMethodClient.asBinder() : null);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i);
                    if (resultReceiver != null) {
                        parcelObtain.writeInt(1);
                        resultReceiver.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hideSoftInput(iInputMethodClient, iBinder, i, resultReceiver);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.IInputMethodManager
            public InputBindResult startInputOrWindowGainedFocus(int i, IInputMethodClient iInputMethodClient, IBinder iBinder, int i2, int i3, int i4, EditorInfo editorInfo, IInputContext iInputContext, int i5, int i6) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iInputMethodClient != null ? iInputMethodClient.asBinder() : null);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    if (editorInfo != null) {
                        parcelObtain.writeInt(1);
                        editorInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iInputContext != null ? iInputContext.asBinder() : null);
                    parcelObtain.writeInt(i5);
                    parcelObtain.writeInt(i6);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        InputBindResult inputBindResultStartInputOrWindowGainedFocus = Stub.getDefaultImpl().startInputOrWindowGainedFocus(i, iInputMethodClient, iBinder, i2, i3, i4, editorInfo, iInputContext, i5, i6);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return inputBindResultStartInputOrWindowGainedFocus;
                    }
                    parcelObtain2.readException();
                    InputBindResult inputBindResultCreateFromParcel = parcelObtain2.readInt() != 0 ? InputBindResult.CREATOR.createFromParcel(parcelObtain2) : null;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return inputBindResultCreateFromParcel;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // com.android.internal.view.IInputMethodManager
            public void showInputMethodPickerFromClient(IInputMethodClient iInputMethodClient, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iInputMethodClient != null ? iInputMethodClient.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showInputMethodPickerFromClient(iInputMethodClient, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.IInputMethodManager
            public void showInputMethodPickerFromSystem(IInputMethodClient iInputMethodClient, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iInputMethodClient != null ? iInputMethodClient.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showInputMethodPickerFromSystem(iInputMethodClient, i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.IInputMethodManager
            public void showInputMethodAndSubtypeEnablerFromClient(IInputMethodClient iInputMethodClient, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iInputMethodClient != null ? iInputMethodClient.asBinder() : null);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showInputMethodAndSubtypeEnablerFromClient(iInputMethodClient, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.IInputMethodManager
            public boolean isInputMethodPickerShownForTest() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isInputMethodPickerShownForTest();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.IInputMethodManager
            public InputMethodSubtype getCurrentInputMethodSubtype() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentInputMethodSubtype();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? InputMethodSubtype.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.IInputMethodManager
            public void setAdditionalInputMethodSubtypes(String str, InputMethodSubtype[] inputMethodSubtypeArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeTypedArray(inputMethodSubtypeArr, 0);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAdditionalInputMethodSubtypes(str, inputMethodSubtypeArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.IInputMethodManager
            public int getInputMethodWindowVisibleHeight() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInputMethodWindowVisibleHeight();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.IInputMethodManager
            public void reportActivityView(IInputMethodClient iInputMethodClient, int i, float[] fArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iInputMethodClient != null ? iInputMethodClient.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeFloatArray(fArr);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportActivityView(iInputMethodClient, i, fArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.IInputMethodManager
            public void reportPerceptible(IBinder iBinder, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(17, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().reportPerceptible(iBinder, z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.IInputMethodManager
            public void removeImeSurface() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeImeSurface();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.IInputMethodManager
            public void removeImeSurfaceFromWindow(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeImeSurfaceFromWindow(iBinder);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInputMethodManager iInputMethodManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iInputMethodManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iInputMethodManager;
            return true;
        }

        public static IInputMethodManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
