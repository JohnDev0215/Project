package cerence.ark.assistant.service;

import android.media.AudioAttributes;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import cerence.ark.assistant.api.prompter.PrompterInstance;
import cerence.ark.assistant.api.prompter.TtsVoice;
import cerence.ark.assistant.framework.AssistantState;
import cerence.ark.assistant.framework.data.AssistantConfiguration;
import cerence.ark.assistant.framework.data.AssistantContextType;
import cerence.ark.assistant.framework.data.Config;
import cerence.ark.assistant.framework.data.LogConsumer;
import cerence.ark.assistant.framework.data.RppLocationType;
import cerence.ark.assistant.framework.data.VoiceConfig;
import cerence.ark.assistant.framework.data.wuw.WuwConfig;
import cerence.ark.assistant.service.ICallback;
import cerence.ark.assistant.service.IDictationManagerListener;
import cerence.ark.assistant.service.IErrorListener;
import cerence.ark.assistant.service.IHmiListener;
import cerence.ark.assistant.service.INotificationListener;
import cerence.ark.assistant.service.IPrompterService;
import cerence.ark.assistant.service.IRequestListener;
import cerence.ark.assistant.service.IUnhandledRequestListener;
import cerence.ark.assistant.service.RemoteUpdateListener;
import cerence.ark.assistant.service.RemoteUpdater;
import cerence.ark.assistant.service.SpeechSynthesisCallback;
import cerence.ark.assistant.service.TokenProviderCallback;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public interface IAssistant extends IInterface {
    public static final String DESCRIPTOR = "cerence.ark.assistant.service.IAssistant";

    public static class Default implements IAssistant {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void cancelListening(ICallback iCallback) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void clearUserData(ICallback iCallback) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public RemoteResult configureLogConsumer(LogConsumer logConsumer) throws RemoteException {
            return null;
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public IPrompterService createPrompter(PrompterInstance prompterInstance, AudioAttributes audioAttributes) throws RemoteException {
            return null;
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public RemoteUpdater createUpdater(RemoteUpdateListener remoteUpdateListener) throws RemoteException {
            return null;
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void destroy(ICallback iCallback) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void destroyPrompter(IPrompterService iPrompterService) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public RemoteResult destroyUpdater(RemoteUpdater remoteUpdater) throws RemoteException {
            return null;
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public AssistantState getAssistantState() throws RemoteException {
            return null;
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public List<String> getAvailableAsrLanguages() throws RemoteException {
            return null;
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public List<PrompterInstance> getAvailablePrompterInstances() throws RemoteException {
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
        public List<PrompterInstance> getDialogPrompterInstances() throws RemoteException {
            return null;
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public List<String> getLogConsumerNames() throws RemoteException {
            return null;
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public PrompterInstance getTtsPrompterInstance() throws RemoteException {
            return null;
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public String getVersion() throws RemoteException {
            return null;
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void initialize(Config config, IErrorListener iErrorListener, TokenProviderCallback tokenProviderCallback, ICallback iCallback) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public RemoteResult overrideConfiguration(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void registerRequestListener(String str, Map map, IRequestListener iRequestListener, ICallback iCallback) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public RemoteResult requestTrimMemory() throws RemoteException {
            return null;
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void sendAppEvent(String str, String str2, int i, int i2, ICallback iCallback) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void sendResponse(String str, String str2, int i, int i2, ICallback iCallback) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void setDynamicWords(List<String> list, String str, int i, int i2, ICallback iCallback) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void setSpeechSynthesisCallback(SpeechSynthesisCallback speechSynthesisCallback) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void setUnhandledRequestListener(IUnhandledRequestListener iUnhandledRequestListener) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public RemoteResult setVoiceConfig(VoiceConfig voiceConfig) throws RemoteException {
            return null;
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void setWuwConfig(WuwConfig wuwConfig, ICallback iCallback) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void startListening(String str, IDictationManagerListener iDictationManagerListener, ICallback iCallback) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void startVoiceAssistant(AssistantConfiguration assistantConfiguration, INotificationListener iNotificationListener, IHmiListener iHmiListener, ICallback iCallback) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void stopListening(ICallback iCallback) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void stopVoiceAssistant(ICallback iCallback) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void unregisterRequestListener(String str) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void updateAssistantContext(AssistantContextType assistantContextType, String str, String str2, int i, int i2, ICallback iCallback) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void updateDynamicContent(String str, List<String> list, String str2, int i, int i2, ICallback iCallback) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public void updateLocation(String str, String str2, String str3, ICallback iCallback) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public RemoteResult updateUdeDataConfig(String str, String str2, String str3) throws RemoteException {
            return null;
        }

        @Override // cerence.ark.assistant.service.IAssistant
        public RemoteResult updateUdeRppConfig(RppLocationType rppLocationType, String str, String str2, String str3) throws RemoteException {
            return null;
        }
    }

    void cancelListening(ICallback iCallback) throws RemoteException;

    void clearUserData(ICallback iCallback) throws RemoteException;

    RemoteResult configureLogConsumer(LogConsumer logConsumer) throws RemoteException;

    IPrompterService createPrompter(PrompterInstance prompterInstance, AudioAttributes audioAttributes) throws RemoteException;

    RemoteUpdater createUpdater(RemoteUpdateListener remoteUpdateListener) throws RemoteException;

    void destroy(ICallback iCallback) throws RemoteException;

    void destroyPrompter(IPrompterService iPrompterService) throws RemoteException;

    RemoteResult destroyUpdater(RemoteUpdater remoteUpdater) throws RemoteException;

    AssistantState getAssistantState() throws RemoteException;

    List<String> getAvailableAsrLanguages() throws RemoteException;

    List<PrompterInstance> getAvailablePrompterInstances() throws RemoteException;

    List<String> getAvailableTtsLanguages() throws RemoteException;

    List<TtsVoice> getAvailableTtsVoices(String str) throws RemoteException;

    List<PrompterInstance> getDialogPrompterInstances() throws RemoteException;

    List<String> getLogConsumerNames() throws RemoteException;

    PrompterInstance getTtsPrompterInstance() throws RemoteException;

    String getVersion() throws RemoteException;

    void initialize(Config config, IErrorListener iErrorListener, TokenProviderCallback tokenProviderCallback, ICallback iCallback) throws RemoteException;

    RemoteResult overrideConfiguration(String str, String str2) throws RemoteException;

    void registerRequestListener(String str, Map map, IRequestListener iRequestListener, ICallback iCallback) throws RemoteException;

    RemoteResult requestTrimMemory() throws RemoteException;

    void sendAppEvent(String str, String str2, int i, int i2, ICallback iCallback) throws RemoteException;

    void sendResponse(String str, String str2, int i, int i2, ICallback iCallback) throws RemoteException;

    void setDynamicWords(List<String> list, String str, int i, int i2, ICallback iCallback) throws RemoteException;

    void setSpeechSynthesisCallback(SpeechSynthesisCallback speechSynthesisCallback) throws RemoteException;

    void setUnhandledRequestListener(IUnhandledRequestListener iUnhandledRequestListener) throws RemoteException;

    RemoteResult setVoiceConfig(VoiceConfig voiceConfig) throws RemoteException;

    void setWuwConfig(WuwConfig wuwConfig, ICallback iCallback) throws RemoteException;

    void startListening(String str, IDictationManagerListener iDictationManagerListener, ICallback iCallback) throws RemoteException;

    void startVoiceAssistant(AssistantConfiguration assistantConfiguration, INotificationListener iNotificationListener, IHmiListener iHmiListener, ICallback iCallback) throws RemoteException;

    void stopListening(ICallback iCallback) throws RemoteException;

    void stopVoiceAssistant(ICallback iCallback) throws RemoteException;

    void unregisterRequestListener(String str) throws RemoteException;

    void updateAssistantContext(AssistantContextType assistantContextType, String str, String str2, int i, int i2, ICallback iCallback) throws RemoteException;

    void updateDynamicContent(String str, List<String> list, String str2, int i, int i2, ICallback iCallback) throws RemoteException;

    void updateLocation(String str, String str2, String str3, ICallback iCallback) throws RemoteException;

    RemoteResult updateUdeDataConfig(String str, String str2, String str3) throws RemoteException;

    RemoteResult updateUdeRppConfig(RppLocationType rppLocationType, String str, String str2, String str3) throws RemoteException;

    public static abstract class Stub extends Binder implements IAssistant {
        static final int TRANSACTION_cancelListening = 27;
        static final int TRANSACTION_clearUserData = 28;
        static final int TRANSACTION_configureLogConsumer = 35;
        static final int TRANSACTION_createPrompter = 18;
        static final int TRANSACTION_createUpdater = 31;
        static final int TRANSACTION_destroy = 7;
        static final int TRANSACTION_destroyPrompter = 20;
        static final int TRANSACTION_destroyUpdater = 32;
        static final int TRANSACTION_getAssistantState = 24;
        static final int TRANSACTION_getAvailableAsrLanguages = 12;
        static final int TRANSACTION_getAvailablePrompterInstances = 16;
        static final int TRANSACTION_getAvailableTtsLanguages = 13;
        static final int TRANSACTION_getAvailableTtsVoices = 14;
        static final int TRANSACTION_getDialogPrompterInstances = 15;
        static final int TRANSACTION_getLogConsumerNames = 34;
        static final int TRANSACTION_getTtsPrompterInstance = 17;
        static final int TRANSACTION_getVersion = 11;
        static final int TRANSACTION_initialize = 1;
        static final int TRANSACTION_overrideConfiguration = 33;
        static final int TRANSACTION_registerRequestListener = 2;
        static final int TRANSACTION_requestTrimMemory = 38;
        static final int TRANSACTION_sendAppEvent = 8;
        static final int TRANSACTION_sendResponse = 9;
        static final int TRANSACTION_setDynamicWords = 29;
        static final int TRANSACTION_setSpeechSynthesisCallback = 19;
        static final int TRANSACTION_setUnhandledRequestListener = 4;
        static final int TRANSACTION_setVoiceConfig = 10;
        static final int TRANSACTION_setWuwConfig = 22;
        static final int TRANSACTION_startListening = 25;
        static final int TRANSACTION_startVoiceAssistant = 5;
        static final int TRANSACTION_stopListening = 26;
        static final int TRANSACTION_stopVoiceAssistant = 6;
        static final int TRANSACTION_unregisterRequestListener = 3;
        static final int TRANSACTION_updateAssistantContext = 23;
        static final int TRANSACTION_updateDynamicContent = 21;
        static final int TRANSACTION_updateLocation = 30;
        static final int TRANSACTION_updateUdeDataConfig = 36;
        static final int TRANSACTION_updateUdeRppConfig = 37;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IAssistant.DESCRIPTOR);
        }

        public static IAssistant asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(IAssistant.DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAssistant)) {
                return (IAssistant) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IAssistant.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IAssistant.DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    initialize((Config) _Parcel.readTypedObject(parcel, Config.CREATOR), IErrorListener.Stub.asInterface(parcel.readStrongBinder()), TokenProviderCallback.Stub.asInterface(parcel.readStrongBinder()), ICallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    registerRequestListener(parcel.readString(), parcel.readHashMap(getClass().getClassLoader()), IRequestListener.Stub.asInterface(parcel.readStrongBinder()), ICallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    unregisterRequestListener(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    setUnhandledRequestListener(IUnhandledRequestListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    startVoiceAssistant((AssistantConfiguration) _Parcel.readTypedObject(parcel, AssistantConfiguration.CREATOR), INotificationListener.Stub.asInterface(parcel.readStrongBinder()), IHmiListener.Stub.asInterface(parcel.readStrongBinder()), ICallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    stopVoiceAssistant(ICallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    destroy(ICallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 8:
                    sendAppEvent(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), ICallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    sendResponse(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), ICallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    RemoteResult voiceConfig = setVoiceConfig((VoiceConfig) _Parcel.readTypedObject(parcel, VoiceConfig.CREATOR));
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, voiceConfig, 1);
                    return true;
                case 11:
                    String version = getVersion();
                    parcel2.writeNoException();
                    parcel2.writeString(version);
                    return true;
                case 12:
                    List<String> availableAsrLanguages = getAvailableAsrLanguages();
                    parcel2.writeNoException();
                    parcel2.writeStringList(availableAsrLanguages);
                    return true;
                case 13:
                    List<String> availableTtsLanguages = getAvailableTtsLanguages();
                    parcel2.writeNoException();
                    parcel2.writeStringList(availableTtsLanguages);
                    return true;
                case 14:
                    List<TtsVoice> availableTtsVoices = getAvailableTtsVoices(parcel.readString());
                    parcel2.writeNoException();
                    _Parcel.writeTypedList(parcel2, availableTtsVoices, 1);
                    return true;
                case 15:
                    List<PrompterInstance> dialogPrompterInstances = getDialogPrompterInstances();
                    parcel2.writeNoException();
                    _Parcel.writeTypedList(parcel2, dialogPrompterInstances, 1);
                    return true;
                case 16:
                    List<PrompterInstance> availablePrompterInstances = getAvailablePrompterInstances();
                    parcel2.writeNoException();
                    _Parcel.writeTypedList(parcel2, availablePrompterInstances, 1);
                    return true;
                case 17:
                    PrompterInstance ttsPrompterInstance = getTtsPrompterInstance();
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, ttsPrompterInstance, 1);
                    return true;
                case 18:
                    IPrompterService iPrompterServiceCreatePrompter = createPrompter((PrompterInstance) _Parcel.readTypedObject(parcel, PrompterInstance.CREATOR), (AudioAttributes) _Parcel.readTypedObject(parcel, AudioAttributes.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeStrongInterface(iPrompterServiceCreatePrompter);
                    return true;
                case 19:
                    setSpeechSynthesisCallback(SpeechSynthesisCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 20:
                    destroyPrompter(IPrompterService.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 21:
                    updateDynamicContent(parcel.readString(), parcel.createStringArrayList(), parcel.readString(), parcel.readInt(), parcel.readInt(), ICallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 22:
                    setWuwConfig((WuwConfig) _Parcel.readTypedObject(parcel, WuwConfig.CREATOR), ICallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 23:
                    updateAssistantContext((AssistantContextType) _Parcel.readTypedObject(parcel, AssistantContextType.CREATOR), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), ICallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 24:
                    AssistantState assistantState = getAssistantState();
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, assistantState, 1);
                    return true;
                case 25:
                    startListening(parcel.readString(), IDictationManagerListener.Stub.asInterface(parcel.readStrongBinder()), ICallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 26:
                    stopListening(ICallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 27:
                    cancelListening(ICallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 28:
                    clearUserData(ICallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 29:
                    setDynamicWords(parcel.createStringArrayList(), parcel.readString(), parcel.readInt(), parcel.readInt(), ICallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 30:
                    updateLocation(parcel.readString(), parcel.readString(), parcel.readString(), ICallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 31:
                    RemoteUpdater remoteUpdaterCreateUpdater = createUpdater(RemoteUpdateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeStrongInterface(remoteUpdaterCreateUpdater);
                    return true;
                case 32:
                    RemoteResult remoteResultDestroyUpdater = destroyUpdater(RemoteUpdater.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, remoteResultDestroyUpdater, 1);
                    return true;
                case 33:
                    RemoteResult remoteResultOverrideConfiguration = overrideConfiguration(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, remoteResultOverrideConfiguration, 1);
                    return true;
                case 34:
                    List<String> logConsumerNames = getLogConsumerNames();
                    parcel2.writeNoException();
                    parcel2.writeStringList(logConsumerNames);
                    return true;
                case 35:
                    RemoteResult remoteResultConfigureLogConsumer = configureLogConsumer((LogConsumer) _Parcel.readTypedObject(parcel, LogConsumer.CREATOR));
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, remoteResultConfigureLogConsumer, 1);
                    return true;
                case 36:
                    RemoteResult remoteResultUpdateUdeDataConfig = updateUdeDataConfig(parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, remoteResultUpdateUdeDataConfig, 1);
                    return true;
                case 37:
                    RemoteResult remoteResultUpdateUdeRppConfig = updateUdeRppConfig((RppLocationType) _Parcel.readTypedObject(parcel, RppLocationType.CREATOR), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, remoteResultUpdateUdeRppConfig, 1);
                    return true;
                case 38:
                    RemoteResult remoteResultRequestTrimMemory = requestTrimMemory();
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, remoteResultRequestTrimMemory, 1);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IAssistant {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return IAssistant.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public void initialize(Config config, IErrorListener iErrorListener, TokenProviderCallback tokenProviderCallback, ICallback iCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    _Parcel.writeTypedObject(parcelObtain, config, 0);
                    parcelObtain.writeStrongInterface(iErrorListener);
                    parcelObtain.writeStrongInterface(tokenProviderCallback);
                    parcelObtain.writeStrongInterface(iCallback);
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
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
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeMap(map);
                    parcelObtain.writeStrongInterface(iRequestListener);
                    parcelObtain.writeStrongInterface(iCallback);
                    this.mRemote.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
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
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
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
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iUnhandledRequestListener);
                    this.mRemote.transact(4, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public void startVoiceAssistant(AssistantConfiguration assistantConfiguration, INotificationListener iNotificationListener, IHmiListener iHmiListener, ICallback iCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    _Parcel.writeTypedObject(parcelObtain, assistantConfiguration, 0);
                    parcelObtain.writeStrongInterface(iNotificationListener);
                    parcelObtain.writeStrongInterface(iHmiListener);
                    parcelObtain.writeStrongInterface(iCallback);
                    this.mRemote.transact(5, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
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
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iCallback);
                    this.mRemote.transact(6, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
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
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iCallback);
                    this.mRemote.transact(7, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public void sendAppEvent(String str, String str2, int i, int i2, ICallback iCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongInterface(iCallback);
                    this.mRemote.transact(8, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public void sendResponse(String str, String str2, int i, int i2, ICallback iCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongInterface(iCallback);
                    this.mRemote.transact(9, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public RemoteResult setVoiceConfig(VoiceConfig voiceConfig) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    _Parcel.writeTypedObject(parcelObtain, voiceConfig, 0);
                    this.mRemote.transact(10, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (RemoteResult) _Parcel.readTypedObject(parcelObtain2, RemoteResult.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public String getVersion() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    this.mRemote.transact(11, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public List<String> getAvailableAsrLanguages() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    this.mRemote.transact(12, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
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
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    this.mRemote.transact(13, parcelObtain, parcelObtain2, 0);
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
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(14, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(TtsVoice.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public List<PrompterInstance> getDialogPrompterInstances() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    this.mRemote.transact(15, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(PrompterInstance.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public List<PrompterInstance> getAvailablePrompterInstances() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    this.mRemote.transact(16, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(PrompterInstance.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public PrompterInstance getTtsPrompterInstance() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    this.mRemote.transact(17, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (PrompterInstance) _Parcel.readTypedObject(parcelObtain2, PrompterInstance.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public IPrompterService createPrompter(PrompterInstance prompterInstance, AudioAttributes audioAttributes) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    _Parcel.writeTypedObject(parcelObtain, prompterInstance, 0);
                    _Parcel.writeTypedObject(parcelObtain, audioAttributes, 0);
                    this.mRemote.transact(18, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return IPrompterService.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public void setSpeechSynthesisCallback(SpeechSynthesisCallback speechSynthesisCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(speechSynthesisCallback);
                    this.mRemote.transact(19, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
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
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iPrompterService);
                    this.mRemote.transact(20, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public void updateDynamicContent(String str, List<String> list, String str2, int i, int i2, ICallback iCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStringList(list);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongInterface(iCallback);
                    this.mRemote.transact(21, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
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
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    _Parcel.writeTypedObject(parcelObtain, wuwConfig, 0);
                    parcelObtain.writeStrongInterface(iCallback);
                    this.mRemote.transact(22, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public void updateAssistantContext(AssistantContextType assistantContextType, String str, String str2, int i, int i2, ICallback iCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    _Parcel.writeTypedObject(parcelObtain, assistantContextType, 0);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongInterface(iCallback);
                    this.mRemote.transact(23, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
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
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    this.mRemote.transact(24, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (AssistantState) _Parcel.readTypedObject(parcelObtain2, AssistantState.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public void startListening(String str, IDictationManagerListener iDictationManagerListener, ICallback iCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongInterface(iDictationManagerListener);
                    parcelObtain.writeStrongInterface(iCallback);
                    this.mRemote.transact(25, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public void stopListening(ICallback iCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iCallback);
                    this.mRemote.transact(26, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public void cancelListening(ICallback iCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iCallback);
                    this.mRemote.transact(27, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public void clearUserData(ICallback iCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iCallback);
                    this.mRemote.transact(28, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public void setDynamicWords(List<String> list, String str, int i, int i2, ICallback iCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    parcelObtain.writeStringList(list);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongInterface(iCallback);
                    this.mRemote.transact(29, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public void updateLocation(String str, String str2, String str3, ICallback iCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeStrongInterface(iCallback);
                    this.mRemote.transact(30, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public RemoteUpdater createUpdater(RemoteUpdateListener remoteUpdateListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(remoteUpdateListener);
                    this.mRemote.transact(31, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return RemoteUpdater.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public RemoteResult destroyUpdater(RemoteUpdater remoteUpdater) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(remoteUpdater);
                    this.mRemote.transact(32, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (RemoteResult) _Parcel.readTypedObject(parcelObtain2, RemoteResult.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public RemoteResult overrideConfiguration(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    this.mRemote.transact(33, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (RemoteResult) _Parcel.readTypedObject(parcelObtain2, RemoteResult.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public List<String> getLogConsumerNames() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    this.mRemote.transact(34, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public RemoteResult configureLogConsumer(LogConsumer logConsumer) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    _Parcel.writeTypedObject(parcelObtain, logConsumer, 0);
                    this.mRemote.transact(35, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (RemoteResult) _Parcel.readTypedObject(parcelObtain2, RemoteResult.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public RemoteResult updateUdeDataConfig(String str, String str2, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    this.mRemote.transact(36, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (RemoteResult) _Parcel.readTypedObject(parcelObtain2, RemoteResult.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public RemoteResult updateUdeRppConfig(RppLocationType rppLocationType, String str, String str2, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    _Parcel.writeTypedObject(parcelObtain, rppLocationType, 0);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    this.mRemote.transact(37, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (RemoteResult) _Parcel.readTypedObject(parcelObtain2, RemoteResult.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IAssistant
            public RemoteResult requestTrimMemory() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAssistant.DESCRIPTOR);
                    this.mRemote.transact(38, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (RemoteResult) _Parcel.readTypedObject(parcelObtain2, RemoteResult.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }

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
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void writeTypedList(Parcel parcel, List<T> list, int i) {
            if (list == null) {
                parcel.writeInt(-1);
                return;
            }
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                writeTypedObject(parcel, list.get(i2), i);
            }
        }
    }
}
