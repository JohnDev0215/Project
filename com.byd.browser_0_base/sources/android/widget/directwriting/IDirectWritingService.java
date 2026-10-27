package android.widget.directwriting;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.directwriting.IDirectWritingServiceCallback;

/* JADX INFO: loaded from: classes.dex */
public interface IDirectWritingService extends IInterface {
    public static final String DESCRIPTOR = "android.widget.directwriting.IDirectWritingService";
    public static final String KEY_BUNDLE_CHECK_CAN_BIND = "onlyCheckCanBind";
    public static final String KEY_BUNDLE_CONFIG_FORCE_SHOW_SIP_APP_PRIVATE_COMMAND_LIST = "forceShowSipAppPrivateCommandList";
    public static final String KEY_BUNDLE_CONFIG_HIDE_DELAY = "hideDelay";
    public static final String KEY_BUNDLE_CONFIG_KEEP_WRITING_DELAY = "keepWritingDelay";
    public static final String KEY_BUNDLE_CONFIG_MAX_DISTANCE = "maxDistance";
    public static final String KEY_BUNDLE_CONFIG_TRANSIENT_BAR_REJECT_DISTANCE = "transientBarRejectDistance";
    public static final String KEY_BUNDLE_CONFIG_TRIGGER_HORIZONTAL_SPACE_DEFAULT = "triggerHorizontalSpace";
    public static final String KEY_BUNDLE_CONFIG_TRIGGER_VERTICAL_SPACE = "triggerVerticalSpace";
    public static final String KEY_BUNDLE_EDIT_RECT = "editRect";
    public static final String KEY_BUNDLE_EDIT_RECT_RELOCATED = "onlyRectChanged";
    public static final String KEY_BUNDLE_EVENT = "event";
    public static final String KEY_BUNDLE_ROOT_VIEW_RECT = "rootViewRect";
    public static final String KEY_BUNDLE_SERVICE_HOST_SOURCE = "hostSource";
    public static final String VALUE_BUNDLE_SERVICE_HOST_SOURCE_SAMSUNG_INTERNET = "samsunginternet";
    public static final String VALUE_BUNDLE_SERVICE_HOST_SOURCE_VIEWROOT = "viewroot";
    public static final String VALUE_BUNDLE_SERVICE_HOST_SOURCE_WEBVIEW = "webview";
    public static final String VALUE_SERVICE_HOST_SOURCE_INTERNET = "|samsunginternet";
    public static final String VALUE_SERVICE_HOST_SOURCE_WEBVIEW = "|webview";
    public static final int VERSION = 1;

    public static class _Parcel {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T readTypedObject(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void writeTypedObject(Parcel parcel, T t, int i) {
            if (t == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            }
        }
    }

    void getConfiguration(Bundle bundle);

    String getPackageName();

    int getVersion();

    boolean onBoundedEditTextChanged(Bundle bundle);

    void onDispatchEvent(Bundle bundle);

    void onEditTextActionModeStarted(Bundle bundle);

    void onExtraCommand(String str, Bundle bundle);

    boolean onStartRecognition(Bundle bundle);

    boolean onStopRecognition(Bundle bundle);

    void onTextViewExtraCommand(String str, Bundle bundle);

    void onUpdateImeOptions(int i);

    void onWindowFocusLost(String str);

    boolean registerCallback(IDirectWritingServiceCallback iDirectWritingServiceCallback, String str);

    boolean unregisterCallback(IDirectWritingServiceCallback iDirectWritingServiceCallback);

    public static abstract class Stub extends Binder implements IDirectWritingService {
        static final int TRANSACTION_getConfiguration = 17;
        static final int TRANSACTION_getPackageName = 4;
        static final int TRANSACTION_getVersion = 1;
        static final int TRANSACTION_onBoundedEditTextChanged = 13;
        static final int TRANSACTION_onDispatchEvent = 15;
        static final int TRANSACTION_onEditTextActionModeStarted = 16;
        static final int TRANSACTION_onExtraCommand = 901;
        static final int TRANSACTION_onStartRecognition = 11;
        static final int TRANSACTION_onStopRecognition = 12;
        static final int TRANSACTION_onTextViewExtraCommand = 902;
        static final int TRANSACTION_onUpdateImeOptions = 18;
        static final int TRANSACTION_onWindowFocusLost = 14;
        static final int TRANSACTION_registerCallback = 2;
        static final int TRANSACTION_unregisterCallback = 3;

        public static class Proxy implements IDirectWritingService {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.widget.directwriting.IDirectWritingService
            public void getConfiguration(Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingService.DESCRIPTOR);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(17, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    if (parcelObtain2.readInt() != 0) {
                        bundle.readFromParcel(parcelObtain2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return IDirectWritingService.DESCRIPTOR;
            }

            @Override // android.widget.directwriting.IDirectWritingService
            public String getPackageName() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingService.DESCRIPTOR);
                    this.mRemote.transact(4, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingService
            public int getVersion() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingService.DESCRIPTOR);
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingService
            public boolean onBoundedEditTextChanged(Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingService.DESCRIPTOR);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(13, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingService
            public void onDispatchEvent(Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingService.DESCRIPTOR);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(15, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingService
            public void onEditTextActionModeStarted(Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingService.DESCRIPTOR);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(16, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingService
            public void onExtraCommand(String str, Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingService.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(901, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    if (parcelObtain2.readInt() != 0) {
                        bundle.readFromParcel(parcelObtain2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingService
            public boolean onStartRecognition(Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingService.DESCRIPTOR);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(11, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingService
            public boolean onStopRecognition(Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingService.DESCRIPTOR);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(12, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingService
            public void onTextViewExtraCommand(String str, Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingService.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(902, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    if (parcelObtain2.readInt() != 0) {
                        bundle.readFromParcel(parcelObtain2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingService
            public void onUpdateImeOptions(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingService.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(18, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingService
            public void onWindowFocusLost(String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingService.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(14, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingService
            public boolean registerCallback(IDirectWritingServiceCallback iDirectWritingServiceCallback, String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingService.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iDirectWritingServiceCallback);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingService
            public boolean unregisterCallback(IDirectWritingServiceCallback iDirectWritingServiceCallback) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingService.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iDirectWritingServiceCallback);
                    this.mRemote.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IDirectWritingService.DESCRIPTOR);
        }

        public static IDirectWritingService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(IDirectWritingService.DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IDirectWritingService)) ? new Proxy(iBinder) : (IDirectWritingService) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IDirectWritingService.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IDirectWritingService.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                int version = getVersion();
                parcel2.writeNoException();
                parcel2.writeInt(version);
            } else if (i == 2) {
                boolean zRegisterCallback = registerCallback(IDirectWritingServiceCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(zRegisterCallback ? 1 : 0);
            } else if (i == 3) {
                boolean zUnregisterCallback = unregisterCallback(IDirectWritingServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(zUnregisterCallback ? 1 : 0);
            } else if (i == 4) {
                String packageName = getPackageName();
                parcel2.writeNoException();
                parcel2.writeString(packageName);
            } else if (i == 901) {
                String string = parcel.readString();
                Bundle bundle = (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR);
                onExtraCommand(string, bundle);
                parcel2.writeNoException();
                _Parcel.writeTypedObject(parcel2, bundle, 1);
            } else if (i != 902) {
                switch (i) {
                    case 11:
                        boolean zOnStartRecognition = onStartRecognition((Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        parcel2.writeNoException();
                        parcel2.writeInt(zOnStartRecognition ? 1 : 0);
                        break;
                    case 12:
                        boolean zOnStopRecognition = onStopRecognition((Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        parcel2.writeNoException();
                        parcel2.writeInt(zOnStopRecognition ? 1 : 0);
                        break;
                    case 13:
                        boolean zOnBoundedEditTextChanged = onBoundedEditTextChanged((Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        parcel2.writeNoException();
                        parcel2.writeInt(zOnBoundedEditTextChanged ? 1 : 0);
                        break;
                    case 14:
                        onWindowFocusLost(parcel.readString());
                        parcel2.writeNoException();
                        break;
                    case 15:
                        onDispatchEvent((Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        parcel2.writeNoException();
                        break;
                    case 16:
                        onEditTextActionModeStarted((Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        parcel2.writeNoException();
                        break;
                    case 17:
                        Bundle bundle2 = (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR);
                        getConfiguration(bundle2);
                        parcel2.writeNoException();
                        _Parcel.writeTypedObject(parcel2, bundle2, 1);
                        break;
                    case 18:
                        onUpdateImeOptions(parcel.readInt());
                        parcel2.writeNoException();
                        break;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                String string2 = parcel.readString();
                Bundle bundle3 = (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR);
                onTextViewExtraCommand(string2, bundle3);
                parcel2.writeNoException();
                _Parcel.writeTypedObject(parcel2, bundle3, 1);
            }
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }
    }

    public static class Default implements IDirectWritingService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.widget.directwriting.IDirectWritingService
        public String getPackageName() {
            return null;
        }

        @Override // android.widget.directwriting.IDirectWritingService
        public int getVersion() {
            return 0;
        }

        @Override // android.widget.directwriting.IDirectWritingService
        public boolean onBoundedEditTextChanged(Bundle bundle) {
            return false;
        }

        @Override // android.widget.directwriting.IDirectWritingService
        public boolean onStartRecognition(Bundle bundle) {
            return false;
        }

        @Override // android.widget.directwriting.IDirectWritingService
        public boolean onStopRecognition(Bundle bundle) {
            return false;
        }

        @Override // android.widget.directwriting.IDirectWritingService
        public boolean registerCallback(IDirectWritingServiceCallback iDirectWritingServiceCallback, String str) {
            return false;
        }

        @Override // android.widget.directwriting.IDirectWritingService
        public boolean unregisterCallback(IDirectWritingServiceCallback iDirectWritingServiceCallback) {
            return false;
        }

        @Override // android.widget.directwriting.IDirectWritingService
        public void getConfiguration(Bundle bundle) {
        }

        @Override // android.widget.directwriting.IDirectWritingService
        public void onDispatchEvent(Bundle bundle) {
        }

        @Override // android.widget.directwriting.IDirectWritingService
        public void onEditTextActionModeStarted(Bundle bundle) {
        }

        @Override // android.widget.directwriting.IDirectWritingService
        public void onUpdateImeOptions(int i) {
        }

        @Override // android.widget.directwriting.IDirectWritingService
        public void onWindowFocusLost(String str) {
        }

        @Override // android.widget.directwriting.IDirectWritingService
        public void onExtraCommand(String str, Bundle bundle) {
        }

        @Override // android.widget.directwriting.IDirectWritingService
        public void onTextViewExtraCommand(String str, Bundle bundle) {
        }
    }
}
