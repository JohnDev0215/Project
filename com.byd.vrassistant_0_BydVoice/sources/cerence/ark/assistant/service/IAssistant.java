package cerence.ark.assistant.service;

import android.media.AudioAttributes;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import cerence.ark.assistant.api.prompter.TtsVoice;
import cerence.ark.assistant.framework.AssistantState;
import cerence.ark.assistant.framework.data.AssistantConfiguration;
import cerence.ark.assistant.framework.data.AssistantContextType;
import cerence.ark.assistant.framework.data.Config;
import cerence.ark.assistant.framework.data.wuw.WuwConfig;
import cerence.ark.assistant.framework.data.wuw.WuwInfo;
import cerence.ark.assistant.service.IActivated;
import cerence.ark.assistant.service.ICallback;
import cerence.ark.assistant.service.IErrorListener;
import cerence.ark.assistant.service.IHmiListener;
import cerence.ark.assistant.service.INotificationListener;
import cerence.ark.assistant.service.IPrompterService;
import cerence.ark.assistant.service.IRequestListener;
import cerence.ark.assistant.service.IResultListener;
import cerence.ark.assistant.service.IUnhandledRequestListener;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public interface IAssistant extends IInterface {

    public static class Default implements IAssistant {
        @Override // cerence.ark.assistant.service.IAssistant
        public void activateDevice(String str, ICallback iCallback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public IPrompterService createPrompter(String str, AudioAttributes audioAttributes) throws RemoteException {
            return null;
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void deactivateDevice(String str, ICallback iCallback) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void destroy(ICallback iCallback) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void destroyPrompter(IPrompterService iPrompterService) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public AssistantState getAssistantState() throws RemoteException {
            return null;
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public List<String> getAvailablePrompterNames() throws RemoteException {
            return null;
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public List<String> getAvailableTtsLanguages() throws RemoteException {
            return null;
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public List<TtsVoice> getAvailableTtsVoices(String str) throws RemoteException {
            return null;
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public WuwInfo getWuwInfo() throws RemoteException {
            return null;
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void initialize(Config config, INotificationListener iNotificationListener, IHmiListener iHmiListener, ICallback iCallback) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public boolean overrideConfiguration(String str, String str2) throws RemoteException {
            return false;
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void queryDeviceStatus(String str, IResultListener iResultListener) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void registerActivatedListener(String str, IActivated iActivated) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void registerRequestListener(String str, Map map, IRequestListener iRequestListener, ICallback iCallback) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public boolean requestTrimMemory() throws RemoteException {
            return false;
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void sendAppEvent(String str, ICallback iCallback) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void sendResponse(String str, boolean z, boolean z2, ICallback iCallback) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void setErrorListener(IErrorListener iErrorListener) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public boolean setTtsVoiceId(String str) throws RemoteException {
            return false;
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void setUnhandledRequestListener(IUnhandledRequestListener iUnhandledRequestListener) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public boolean setUserDataRetentionEnabled(boolean z) throws RemoteException {
            return false;
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void setWuwConfig(WuwConfig wuwConfig, ICallback iCallback) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void startVoiceAssistant(AssistantConfiguration assistantConfiguration, ICallback iCallback) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void stopVoiceAssistant(ICallback iCallback) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void unregisterRequestListener(String str) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public boolean updateAssistantContext(AssistantContextType assistantContextType, String str, boolean z, boolean z2) throws RemoteException {
            return false;
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void updateDynamicContent(String str, List<String> list, boolean z, boolean z2) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public boolean updateShortcutCommand(List<String> list, boolean z, boolean z2) throws RemoteException {
            return false;
        }
    }

    void activateDevice(String str, ICallback iCallback) throws RemoteException;

    IPrompterService createPrompter(String str, AudioAttributes audioAttributes) throws RemoteException;

    void deactivateDevice(String str, ICallback iCallback) throws RemoteException;

    void destroy(ICallback iCallback) throws RemoteException;

    void destroyPrompter(IPrompterService iPrompterService) throws RemoteException;

    AssistantState getAssistantState() throws RemoteException;

    List<String> getAvailablePrompterNames() throws RemoteException;

    List<String> getAvailableTtsLanguages() throws RemoteException;

    List<TtsVoice> getAvailableTtsVoices(String str) throws RemoteException;

    WuwInfo getWuwInfo() throws RemoteException;

    void initialize(Config config, INotificationListener iNotificationListener, IHmiListener iHmiListener, ICallback iCallback) throws RemoteException;

    boolean overrideConfiguration(String str, String str2) throws RemoteException;

    void queryDeviceStatus(String str, IResultListener iResultListener) throws RemoteException;

    void registerActivatedListener(String str, IActivated iActivated) throws RemoteException;

    void registerRequestListener(String str, Map map, IRequestListener iRequestListener, ICallback iCallback) throws RemoteException;

    boolean requestTrimMemory() throws RemoteException;

    void sendAppEvent(String str, ICallback iCallback) throws RemoteException;

    void sendResponse(String str, boolean z, boolean z2, ICallback iCallback) throws RemoteException;

    void setErrorListener(IErrorListener iErrorListener) throws RemoteException;

    boolean setTtsVoiceId(String str) throws RemoteException;

    void setUnhandledRequestListener(IUnhandledRequestListener iUnhandledRequestListener) throws RemoteException;

    boolean setUserDataRetentionEnabled(boolean z) throws RemoteException;

    void setWuwConfig(WuwConfig wuwConfig, ICallback iCallback) throws RemoteException;

    void startVoiceAssistant(AssistantConfiguration assistantConfiguration, ICallback iCallback) throws RemoteException;

    void stopVoiceAssistant(ICallback iCallback) throws RemoteException;

    void unregisterRequestListener(String str) throws RemoteException;

    boolean updateAssistantContext(AssistantContextType assistantContextType, String str, boolean z, boolean z2) throws RemoteException;

    void updateDynamicContent(String str, List<String> list, boolean z, boolean z2) throws RemoteException;

    boolean updateShortcutCommand(List<String> list, boolean z, boolean z2) throws RemoteException;

    public static abstract class Stub extends Binder implements IAssistant {
        private static final String DESCRIPTOR = "cerence.ark.assistant.service.IAssistant";
        static final int TRANSACTION_activateDevice = 23;
        static final int TRANSACTION_createPrompter = 15;
        static final int TRANSACTION_deactivateDevice = 24;
        static final int TRANSACTION_destroy = 8;
        static final int TRANSACTION_destroyPrompter = 16;
        static final int TRANSACTION_getAssistantState = 26;
        static final int TRANSACTION_getAvailablePrompterNames = 14;
        static final int TRANSACTION_getAvailableTtsLanguages = 12;
        static final int TRANSACTION_getAvailableTtsVoices = 13;
        static final int TRANSACTION_getWuwInfo = 20;
        static final int TRANSACTION_initialize = 1;
        static final int TRANSACTION_overrideConfiguration = 29;
        static final int TRANSACTION_queryDeviceStatus = 25;
        static final int TRANSACTION_registerActivatedListener = 5;
        static final int TRANSACTION_registerRequestListener = 2;
        static final int TRANSACTION_requestTrimMemory = 27;
        static final int TRANSACTION_sendAppEvent = 9;
        static final int TRANSACTION_sendResponse = 10;
        static final int TRANSACTION_setErrorListener = 22;
        static final int TRANSACTION_setTtsVoiceId = 11;
        static final int TRANSACTION_setUnhandledRequestListener = 4;
        static final int TRANSACTION_setUserDataRetentionEnabled = 28;
        static final int TRANSACTION_setWuwConfig = 19;
        static final int TRANSACTION_startVoiceAssistant = 6;
        static final int TRANSACTION_stopVoiceAssistant = 7;
        static final int TRANSACTION_unregisterRequestListener = 3;
        static final int TRANSACTION_updateAssistantContext = 21;
        static final int TRANSACTION_updateDynamicContent = 17;
        static final int TRANSACTION_updateShortcutCommand = 18;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAssistant asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAssistant)) {
                return (IAssistant) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
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
                    initialize(parcel.readInt() != 0 ? Config.CREATOR.createFromParcel(parcel) : null, INotificationListener.Stub.asInterface(parcel.readStrongBinder()), IHmiListener.Stub.asInterface(parcel.readStrongBinder()), ICallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerRequestListener(parcel.readString(), parcel.readHashMap(getClass().getClassLoader()), IRequestListener.Stub.asInterface(parcel.readStrongBinder()), ICallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterRequestListener(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    setUnhandledRequestListener(IUnhandledRequestListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerActivatedListener(parcel.readString(), IActivated.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    startVoiceAssistant(parcel.readInt() != 0 ? AssistantConfiguration.CREATOR.createFromParcel(parcel) : null, ICallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopVoiceAssistant(ICallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    destroy(ICallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendAppEvent(parcel.readString(), ICallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendResponse(parcel.readString(), parcel.readInt() != 0, parcel.readInt() != 0, ICallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean ttsVoiceId = setTtsVoiceId(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(ttsVoiceId ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> availableTtsLanguages = getAvailableTtsLanguages();
                    parcel2.writeNoException();
                    parcel2.writeStringList(availableTtsLanguages);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<TtsVoice> availableTtsVoices = getAvailableTtsVoices(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(availableTtsVoices);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> availablePrompterNames = getAvailablePrompterNames();
                    parcel2.writeNoException();
                    parcel2.writeStringList(availablePrompterNames);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    IPrompterService iPrompterServiceCreatePrompter = createPrompter(parcel.readString(), parcel.readInt() != 0 ? (AudioAttributes) AudioAttributes.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iPrompterServiceCreatePrompter != null ? iPrompterServiceCreatePrompter.asBinder() : null);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    destroyPrompter(IPrompterService.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateDynamicContent(parcel.readString(), parcel.createStringArrayList(), parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zUpdateShortcutCommand = updateShortcutCommand(parcel.createStringArrayList(), parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zUpdateShortcutCommand ? 1 : 0);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    setWuwConfig(parcel.readInt() != 0 ? WuwConfig.CREATOR.createFromParcel(parcel) : null, ICallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    WuwInfo wuwInfo = getWuwInfo();
                    parcel2.writeNoException();
                    if (wuwInfo != null) {
                        parcel2.writeInt(1);
                        wuwInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zUpdateAssistantContext = updateAssistantContext(parcel.readInt() != 0 ? AssistantContextType.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zUpdateAssistantContext ? 1 : 0);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    setErrorListener(IErrorListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    activateDevice(parcel.readString(), ICallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    deactivateDevice(parcel.readString(), ICallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    queryDeviceStatus(parcel.readString(), IResultListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    AssistantState assistantState = getAssistantState();
                    parcel2.writeNoException();
                    if (assistantState != null) {
                        parcel2.writeInt(1);
                        assistantState.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRequestTrimMemory = requestTrimMemory();
                    parcel2.writeNoException();
                    parcel2.writeInt(zRequestTrimMemory ? 1 : 0);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean userDataRetentionEnabled = setUserDataRetentionEnabled(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(userDataRetentionEnabled ? 1 : 0);
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zOverrideConfiguration = overrideConfiguration(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zOverrideConfiguration ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IAssistant {
            public static IAssistant sDefaultImpl;
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

            @Override // cerence.ark.assistant.service.IAssistant
            public void initialize(Config config, INotificationListener iNotificationListener, IHmiListener iHmiListener, ICallback iCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (config != null) {
                        parcelObtain.writeInt(1);
                        config.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iNotificationListener != null ? iNotificationListener.asBinder() : null);
                    parcelObtain.writeStrongBinder(iHmiListener != null ? iHmiListener.asBinder() : null);
                    parcelObtain.writeStrongBinder(iCallback != null ? iCallback.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().initialize(config, iNotificationListener, iHmiListener, iCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public void registerRequestListener(String str, Map map, IRequestListener iRequestListener, ICallback iCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeMap(map);
                    parcelObtain.writeStrongBinder(iRequestListener != null ? iRequestListener.asBinder() : null);
                    parcelObtain.writeStrongBinder(iCallback != null ? iCallback.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerRequestListener(str, map, iRequestListener, iCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public void unregisterRequestListener(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterRequestListener(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public void setUnhandledRequestListener(IUnhandledRequestListener iUnhandledRequestListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iUnhandledRequestListener != null ? iUnhandledRequestListener.asBinder() : null);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setUnhandledRequestListener(iUnhandledRequestListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public void registerActivatedListener(String str, IActivated iActivated) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iActivated != null ? iActivated.asBinder() : null);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerActivatedListener(str, iActivated);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public void startVoiceAssistant(AssistantConfiguration assistantConfiguration, ICallback iCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (assistantConfiguration != null) {
                        parcelObtain.writeInt(1);
                        assistantConfiguration.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iCallback != null ? iCallback.asBinder() : null);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startVoiceAssistant(assistantConfiguration, iCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public void stopVoiceAssistant(ICallback iCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iCallback != null ? iCallback.asBinder() : null);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopVoiceAssistant(iCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public void destroy(ICallback iCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iCallback != null ? iCallback.asBinder() : null);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().destroy(iCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public void sendAppEvent(String str, ICallback iCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iCallback != null ? iCallback.asBinder() : null);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendAppEvent(str, iCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public void sendResponse(String str, boolean z, boolean z2, ICallback iCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    int i = 1;
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!z2) {
                        i = 0;
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iCallback != null ? iCallback.asBinder() : null);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendResponse(str, z, z2, iCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public boolean setTtsVoiceId(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setTtsVoiceId(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public List<String> getAvailableTtsLanguages() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAvailableTtsLanguages();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public List<TtsVoice> getAvailableTtsVoices(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAvailableTtsVoices(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(TtsVoice.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public List<String> getAvailablePrompterNames() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAvailablePrompterNames();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public IPrompterService createPrompter(String str, AudioAttributes audioAttributes) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (audioAttributes != null) {
                        parcelObtain.writeInt(1);
                        audioAttributes.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createPrompter(str, audioAttributes);
                    }
                    parcelObtain2.readException();
                    return IPrompterService.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public void destroyPrompter(IPrompterService iPrompterService) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iPrompterService != null ? iPrompterService.asBinder() : null);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().destroyPrompter(iPrompterService);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public void updateDynamicContent(String str, List<String> list, boolean z, boolean z2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStringList(list);
                    int i = 1;
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!z2) {
                        i = 0;
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateDynamicContent(str, list, z, z2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public boolean updateShortcutCommand(List<String> list, boolean z, boolean z2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringList(list);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(z2 ? 1 : 0);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateShortcutCommand(list, z, z2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public void setWuwConfig(WuwConfig wuwConfig, ICallback iCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (wuwConfig != null) {
                        parcelObtain.writeInt(1);
                        wuwConfig.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iCallback != null ? iCallback.asBinder() : null);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setWuwConfig(wuwConfig, iCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public WuwInfo getWuwInfo() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWuwInfo();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? WuwInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public boolean updateAssistantContext(AssistantContextType assistantContextType, String str, boolean z, boolean z2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (assistantContextType != null) {
                        parcelObtain.writeInt(1);
                        assistantContextType.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(z2 ? 1 : 0);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateAssistantContext(assistantContextType, str, z, z2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public void setErrorListener(IErrorListener iErrorListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iErrorListener != null ? iErrorListener.asBinder() : null);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setErrorListener(iErrorListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public void activateDevice(String str, ICallback iCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iCallback != null ? iCallback.asBinder() : null);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().activateDevice(str, iCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public void deactivateDevice(String str, ICallback iCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iCallback != null ? iCallback.asBinder() : null);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deactivateDevice(str, iCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public void queryDeviceStatus(String str, IResultListener iResultListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iResultListener != null ? iResultListener.asBinder() : null);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().queryDeviceStatus(str, iResultListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public AssistantState getAssistantState() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAssistantState();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? AssistantState.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public boolean requestTrimMemory() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestTrimMemory();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public boolean setUserDataRetentionEnabled(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setUserDataRetentionEnabled(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public boolean overrideConfiguration(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().overrideConfiguration(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAssistant iAssistant) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iAssistant == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAssistant;
            return true;
        }

        public static IAssistant getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
