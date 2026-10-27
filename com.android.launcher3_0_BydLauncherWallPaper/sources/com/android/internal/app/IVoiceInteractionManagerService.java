package com.android.internal.app;

import android.content.ComponentName;
import android.content.Intent;
import android.hardware.soundtrigger.IRecognitionStatusCallback;
import android.hardware.soundtrigger.KeyphraseMetadata;
import android.hardware.soundtrigger.SoundTrigger;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.service.voice.IVoiceInteractionSession;
import com.android.internal.app.IVoiceActionCheckCallback;
import com.android.internal.app.IVoiceInteractionSessionListener;
import com.android.internal.app.IVoiceInteractionSessionShowCallback;
import com.android.internal.app.IVoiceInteractor;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface IVoiceInteractionManagerService extends IInterface {

    public static class Default implements IVoiceInteractionManagerService {
        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public boolean activeServiceSupportsAssist() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public boolean activeServiceSupportsLaunchFromKeyguard() throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public void closeSystemDialogs(IBinder iBinder) throws RemoteException {
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public int deleteKeyphraseSoundModel(int i, String str) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public boolean deliverNewSession(IBinder iBinder, IVoiceInteractionSession iVoiceInteractionSession, IVoiceInteractor iVoiceInteractor) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public void finish(IBinder iBinder) throws RemoteException {
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public ComponentName getActiveServiceComponentName() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public void getActiveServiceSupportedActions(List<String> list, IVoiceActionCheckCallback iVoiceActionCheckCallback) throws RemoteException {
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public int getDisabledShowContext() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public SoundTrigger.ModuleProperties getDspModuleProperties() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public KeyphraseMetadata getEnrolledKeyphraseMetadata(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public SoundTrigger.KeyphraseSoundModel getKeyphraseSoundModel(int i, String str) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public int getParameter(int i, int i2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public int getUserDisabledShowContext() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public void hideCurrentSession() throws RemoteException {
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public boolean hideSessionFromSession(IBinder iBinder) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public boolean isEnrolledForKeyphrase(int i, String str) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public boolean isSessionRunning() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public void launchVoiceAssistFromKeyguard() throws RemoteException {
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public void onLockscreenShown() throws RemoteException {
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public void performDirectAction(IBinder iBinder, String str, Bundle bundle, int i, IBinder iBinder2, RemoteCallback remoteCallback, RemoteCallback remoteCallback2) throws RemoteException {
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public SoundTrigger.ModelParamRange queryParameter(int i, int i2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public void registerVoiceInteractionSessionListener(IVoiceInteractionSessionListener iVoiceInteractionSessionListener) throws RemoteException {
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public void requestDirectActions(IBinder iBinder, int i, IBinder iBinder2, RemoteCallback remoteCallback, RemoteCallback remoteCallback2) throws RemoteException {
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public void setDisabled(boolean z) throws RemoteException {
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public void setDisabledShowContext(int i) throws RemoteException {
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public void setKeepAwake(IBinder iBinder, boolean z) throws RemoteException {
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public int setParameter(int i, int i2, int i3) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public void setUiHints(Bundle bundle) throws RemoteException {
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public void showSession(Bundle bundle, int i) throws RemoteException {
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public boolean showSessionForActiveService(Bundle bundle, int i, IVoiceInteractionSessionShowCallback iVoiceInteractionSessionShowCallback, IBinder iBinder) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public boolean showSessionFromSession(IBinder iBinder, Bundle bundle, int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public int startAssistantActivity(IBinder iBinder, Intent intent, String str, String str2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public int startRecognition(int i, String str, IRecognitionStatusCallback iRecognitionStatusCallback, SoundTrigger.RecognitionConfig recognitionConfig) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public int startVoiceActivity(IBinder iBinder, Intent intent, String str, String str2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public int stopRecognition(int i, IRecognitionStatusCallback iRecognitionStatusCallback) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.IVoiceInteractionManagerService
        public int updateKeyphraseSoundModel(SoundTrigger.KeyphraseSoundModel keyphraseSoundModel) throws RemoteException {
            return 0;
        }
    }

    boolean activeServiceSupportsAssist() throws RemoteException;

    boolean activeServiceSupportsLaunchFromKeyguard() throws RemoteException;

    void closeSystemDialogs(IBinder iBinder) throws RemoteException;

    int deleteKeyphraseSoundModel(int i, String str) throws RemoteException;

    boolean deliverNewSession(IBinder iBinder, IVoiceInteractionSession iVoiceInteractionSession, IVoiceInteractor iVoiceInteractor) throws RemoteException;

    void finish(IBinder iBinder) throws RemoteException;

    ComponentName getActiveServiceComponentName() throws RemoteException;

    void getActiveServiceSupportedActions(List<String> list, IVoiceActionCheckCallback iVoiceActionCheckCallback) throws RemoteException;

    int getDisabledShowContext() throws RemoteException;

    SoundTrigger.ModuleProperties getDspModuleProperties() throws RemoteException;

    KeyphraseMetadata getEnrolledKeyphraseMetadata(String str, String str2) throws RemoteException;

    SoundTrigger.KeyphraseSoundModel getKeyphraseSoundModel(int i, String str) throws RemoteException;

    int getParameter(int i, int i2) throws RemoteException;

    int getUserDisabledShowContext() throws RemoteException;

    void hideCurrentSession() throws RemoteException;

    boolean hideSessionFromSession(IBinder iBinder) throws RemoteException;

    boolean isEnrolledForKeyphrase(int i, String str) throws RemoteException;

    boolean isSessionRunning() throws RemoteException;

    void launchVoiceAssistFromKeyguard() throws RemoteException;

    void onLockscreenShown() throws RemoteException;

    void performDirectAction(IBinder iBinder, String str, Bundle bundle, int i, IBinder iBinder2, RemoteCallback remoteCallback, RemoteCallback remoteCallback2) throws RemoteException;

    SoundTrigger.ModelParamRange queryParameter(int i, int i2) throws RemoteException;

    void registerVoiceInteractionSessionListener(IVoiceInteractionSessionListener iVoiceInteractionSessionListener) throws RemoteException;

    void requestDirectActions(IBinder iBinder, int i, IBinder iBinder2, RemoteCallback remoteCallback, RemoteCallback remoteCallback2) throws RemoteException;

    void setDisabled(boolean z) throws RemoteException;

    void setDisabledShowContext(int i) throws RemoteException;

    void setKeepAwake(IBinder iBinder, boolean z) throws RemoteException;

    int setParameter(int i, int i2, int i3) throws RemoteException;

    void setUiHints(Bundle bundle) throws RemoteException;

    void showSession(Bundle bundle, int i) throws RemoteException;

    boolean showSessionForActiveService(Bundle bundle, int i, IVoiceInteractionSessionShowCallback iVoiceInteractionSessionShowCallback, IBinder iBinder) throws RemoteException;

    boolean showSessionFromSession(IBinder iBinder, Bundle bundle, int i) throws RemoteException;

    int startAssistantActivity(IBinder iBinder, Intent intent, String str, String str2) throws RemoteException;

    int startRecognition(int i, String str, IRecognitionStatusCallback iRecognitionStatusCallback, SoundTrigger.RecognitionConfig recognitionConfig) throws RemoteException;

    int startVoiceActivity(IBinder iBinder, Intent intent, String str, String str2) throws RemoteException;

    int stopRecognition(int i, IRecognitionStatusCallback iRecognitionStatusCallback) throws RemoteException;

    int updateKeyphraseSoundModel(SoundTrigger.KeyphraseSoundModel keyphraseSoundModel) throws RemoteException;

    public static abstract class Stub extends Binder implements IVoiceInteractionManagerService {
        private static final String DESCRIPTOR = "com.android.internal.app.IVoiceInteractionManagerService";
        static final int TRANSACTION_activeServiceSupportsAssist = 29;
        static final int TRANSACTION_activeServiceSupportsLaunchFromKeyguard = 30;
        static final int TRANSACTION_closeSystemDialogs = 8;
        static final int TRANSACTION_deleteKeyphraseSoundModel = 15;
        static final int TRANSACTION_deliverNewSession = 2;
        static final int TRANSACTION_finish = 9;
        static final int TRANSACTION_getActiveServiceComponentName = 24;
        static final int TRANSACTION_getActiveServiceSupportedActions = 33;
        static final int TRANSACTION_getDisabledShowContext = 11;
        static final int TRANSACTION_getDspModuleProperties = 16;
        static final int TRANSACTION_getEnrolledKeyphraseMetadata = 18;
        static final int TRANSACTION_getKeyphraseSoundModel = 13;
        static final int TRANSACTION_getParameter = 22;
        static final int TRANSACTION_getUserDisabledShowContext = 12;
        static final int TRANSACTION_hideCurrentSession = 26;
        static final int TRANSACTION_hideSessionFromSession = 4;
        static final int TRANSACTION_isEnrolledForKeyphrase = 17;
        static final int TRANSACTION_isSessionRunning = 28;
        static final int TRANSACTION_launchVoiceAssistFromKeyguard = 27;
        static final int TRANSACTION_onLockscreenShown = 31;
        static final int TRANSACTION_performDirectAction = 36;
        static final int TRANSACTION_queryParameter = 23;
        static final int TRANSACTION_registerVoiceInteractionSessionListener = 32;
        static final int TRANSACTION_requestDirectActions = 35;
        static final int TRANSACTION_setDisabled = 37;
        static final int TRANSACTION_setDisabledShowContext = 10;
        static final int TRANSACTION_setKeepAwake = 7;
        static final int TRANSACTION_setParameter = 21;
        static final int TRANSACTION_setUiHints = 34;
        static final int TRANSACTION_showSession = 1;
        static final int TRANSACTION_showSessionForActiveService = 25;
        static final int TRANSACTION_showSessionFromSession = 3;
        static final int TRANSACTION_startAssistantActivity = 6;
        static final int TRANSACTION_startRecognition = 19;
        static final int TRANSACTION_startVoiceActivity = 5;
        static final int TRANSACTION_stopRecognition = 20;
        static final int TRANSACTION_updateKeyphraseSoundModel = 14;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "showSession";
                case 2:
                    return "deliverNewSession";
                case 3:
                    return "showSessionFromSession";
                case 4:
                    return "hideSessionFromSession";
                case 5:
                    return "startVoiceActivity";
                case 6:
                    return "startAssistantActivity";
                case 7:
                    return "setKeepAwake";
                case 8:
                    return "closeSystemDialogs";
                case 9:
                    return "finish";
                case 10:
                    return "setDisabledShowContext";
                case 11:
                    return "getDisabledShowContext";
                case 12:
                    return "getUserDisabledShowContext";
                case 13:
                    return "getKeyphraseSoundModel";
                case 14:
                    return "updateKeyphraseSoundModel";
                case 15:
                    return "deleteKeyphraseSoundModel";
                case 16:
                    return "getDspModuleProperties";
                case 17:
                    return "isEnrolledForKeyphrase";
                case 18:
                    return "getEnrolledKeyphraseMetadata";
                case 19:
                    return "startRecognition";
                case 20:
                    return "stopRecognition";
                case 21:
                    return "setParameter";
                case 22:
                    return "getParameter";
                case 23:
                    return "queryParameter";
                case 24:
                    return "getActiveServiceComponentName";
                case 25:
                    return "showSessionForActiveService";
                case 26:
                    return "hideCurrentSession";
                case 27:
                    return "launchVoiceAssistFromKeyguard";
                case 28:
                    return "isSessionRunning";
                case 29:
                    return "activeServiceSupportsAssist";
                case 30:
                    return "activeServiceSupportsLaunchFromKeyguard";
                case 31:
                    return "onLockscreenShown";
                case 32:
                    return "registerVoiceInteractionSessionListener";
                case 33:
                    return "getActiveServiceSupportedActions";
                case 34:
                    return "setUiHints";
                case 35:
                    return "requestDirectActions";
                case 36:
                    return "performDirectAction";
                case 37:
                    return "setDisabled";
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

        public static IVoiceInteractionManagerService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IVoiceInteractionManagerService)) {
                return (IVoiceInteractionManagerService) iInterfaceQueryLocalInterface;
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
                    showSession(parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zDeliverNewSession = deliverNewSession(parcel.readStrongBinder(), IVoiceInteractionSession.Stub.asInterface(parcel.readStrongBinder()), IVoiceInteractor.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zDeliverNewSession ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zShowSessionFromSession = showSessionFromSession(parcel.readStrongBinder(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zShowSessionFromSession ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHideSessionFromSession = hideSessionFromSession(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    parcel2.writeInt(zHideSessionFromSession ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iStartVoiceActivity = startVoiceActivity(parcel.readStrongBinder(), parcel.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iStartVoiceActivity);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iStartAssistantActivity = startAssistantActivity(parcel.readStrongBinder(), parcel.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iStartAssistantActivity);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    setKeepAwake(parcel.readStrongBinder(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    closeSystemDialogs(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    finish(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDisabledShowContext(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    int disabledShowContext = getDisabledShowContext();
                    parcel2.writeNoException();
                    parcel2.writeInt(disabledShowContext);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    int userDisabledShowContext = getUserDisabledShowContext();
                    parcel2.writeNoException();
                    parcel2.writeInt(userDisabledShowContext);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    SoundTrigger.KeyphraseSoundModel keyphraseSoundModel = getKeyphraseSoundModel(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    if (keyphraseSoundModel != null) {
                        parcel2.writeInt(1);
                        keyphraseSoundModel.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iUpdateKeyphraseSoundModel = updateKeyphraseSoundModel(parcel.readInt() != 0 ? SoundTrigger.KeyphraseSoundModel.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(iUpdateKeyphraseSoundModel);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iDeleteKeyphraseSoundModel = deleteKeyphraseSoundModel(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iDeleteKeyphraseSoundModel);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    SoundTrigger.ModuleProperties dspModuleProperties = getDspModuleProperties();
                    parcel2.writeNoException();
                    if (dspModuleProperties != null) {
                        parcel2.writeInt(1);
                        dspModuleProperties.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsEnrolledForKeyphrase = isEnrolledForKeyphrase(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsEnrolledForKeyphrase ? 1 : 0);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    KeyphraseMetadata enrolledKeyphraseMetadata = getEnrolledKeyphraseMetadata(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    if (enrolledKeyphraseMetadata != null) {
                        parcel2.writeInt(1);
                        enrolledKeyphraseMetadata.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iStartRecognition = startRecognition(parcel.readInt(), parcel.readString(), IRecognitionStatusCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? SoundTrigger.RecognitionConfig.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(iStartRecognition);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iStopRecognition = stopRecognition(parcel.readInt(), IRecognitionStatusCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(iStopRecognition);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    int parameter = setParameter(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(parameter);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    int parameter2 = getParameter(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(parameter2);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    SoundTrigger.ModelParamRange modelParamRangeQueryParameter = queryParameter(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (modelParamRangeQueryParameter != null) {
                        parcel2.writeInt(1);
                        modelParamRangeQueryParameter.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    ComponentName activeServiceComponentName = getActiveServiceComponentName();
                    parcel2.writeNoException();
                    if (activeServiceComponentName != null) {
                        parcel2.writeInt(1);
                        activeServiceComponentName.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zShowSessionForActiveService = showSessionForActiveService(parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), IVoiceInteractionSessionShowCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readStrongBinder());
                    parcel2.writeNoException();
                    parcel2.writeInt(zShowSessionForActiveService ? 1 : 0);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    hideCurrentSession();
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    launchVoiceAssistFromKeyguard();
                    parcel2.writeNoException();
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsSessionRunning = isSessionRunning();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsSessionRunning ? 1 : 0);
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zActiveServiceSupportsAssist = activeServiceSupportsAssist();
                    parcel2.writeNoException();
                    parcel2.writeInt(zActiveServiceSupportsAssist ? 1 : 0);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zActiveServiceSupportsLaunchFromKeyguard = activeServiceSupportsLaunchFromKeyguard();
                    parcel2.writeNoException();
                    parcel2.writeInt(zActiveServiceSupportsLaunchFromKeyguard ? 1 : 0);
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    onLockscreenShown();
                    parcel2.writeNoException();
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerVoiceInteractionSessionListener(IVoiceInteractionSessionListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    getActiveServiceSupportedActions(parcel.createStringArrayList(), IVoiceActionCheckCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    setUiHints(parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestDirectActions(parcel.readStrongBinder(), parcel.readInt(), parcel.readStrongBinder(), parcel.readInt() != 0 ? RemoteCallback.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? RemoteCallback.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    performDirectAction(parcel.readStrongBinder(), parcel.readString(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readStrongBinder(), parcel.readInt() != 0 ? RemoteCallback.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? RemoteCallback.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDisabled(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IVoiceInteractionManagerService {
            public static IVoiceInteractionManagerService sDefaultImpl;
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

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public void showSession(Bundle bundle, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showSession(bundle, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public boolean deliverNewSession(IBinder iBinder, IVoiceInteractionSession iVoiceInteractionSession, IVoiceInteractor iVoiceInteractor) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeStrongBinder(iVoiceInteractionSession != null ? iVoiceInteractionSession.asBinder() : null);
                    parcelObtain.writeStrongBinder(iVoiceInteractor != null ? iVoiceInteractor.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().deliverNewSession(iBinder, iVoiceInteractionSession, iVoiceInteractor);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public boolean showSessionFromSession(IBinder iBinder, Bundle bundle, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().showSessionFromSession(iBinder, bundle, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public boolean hideSessionFromSession(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hideSessionFromSession(iBinder);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public int startVoiceActivity(IBinder iBinder, Intent intent, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (intent != null) {
                        parcelObtain.writeInt(1);
                        intent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startVoiceActivity(iBinder, intent, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public int startAssistantActivity(IBinder iBinder, Intent intent, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (intent != null) {
                        parcelObtain.writeInt(1);
                        intent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startAssistantActivity(iBinder, intent, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public void setKeepAwake(IBinder iBinder, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setKeepAwake(iBinder, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public void closeSystemDialogs(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().closeSystemDialogs(iBinder);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public void finish(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().finish(iBinder);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public void setDisabledShowContext(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDisabledShowContext(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public int getDisabledShowContext() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDisabledShowContext();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public int getUserDisabledShowContext() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserDisabledShowContext();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public SoundTrigger.KeyphraseSoundModel getKeyphraseSoundModel(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getKeyphraseSoundModel(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? SoundTrigger.KeyphraseSoundModel.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public int updateKeyphraseSoundModel(SoundTrigger.KeyphraseSoundModel keyphraseSoundModel) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (keyphraseSoundModel != null) {
                        parcelObtain.writeInt(1);
                        keyphraseSoundModel.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateKeyphraseSoundModel(keyphraseSoundModel);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public int deleteKeyphraseSoundModel(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().deleteKeyphraseSoundModel(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public SoundTrigger.ModuleProperties getDspModuleProperties() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDspModuleProperties();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? SoundTrigger.ModuleProperties.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public boolean isEnrolledForKeyphrase(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isEnrolledForKeyphrase(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public KeyphraseMetadata getEnrolledKeyphraseMetadata(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEnrolledKeyphraseMetadata(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? KeyphraseMetadata.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public int startRecognition(int i, String str, IRecognitionStatusCallback iRecognitionStatusCallback, SoundTrigger.RecognitionConfig recognitionConfig) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iRecognitionStatusCallback != null ? iRecognitionStatusCallback.asBinder() : null);
                    if (recognitionConfig != null) {
                        parcelObtain.writeInt(1);
                        recognitionConfig.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startRecognition(i, str, iRecognitionStatusCallback, recognitionConfig);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public int stopRecognition(int i, IRecognitionStatusCallback iRecognitionStatusCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iRecognitionStatusCallback != null ? iRecognitionStatusCallback.asBinder() : null);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stopRecognition(i, iRecognitionStatusCallback);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public int setParameter(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setParameter(i, i2, i3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public int getParameter(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getParameter(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public SoundTrigger.ModelParamRange queryParameter(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().queryParameter(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? SoundTrigger.ModelParamRange.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public ComponentName getActiveServiceComponentName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActiveServiceComponentName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public boolean showSessionForActiveService(Bundle bundle, int i, IVoiceInteractionSessionShowCallback iVoiceInteractionSessionShowCallback, IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iVoiceInteractionSessionShowCallback != null ? iVoiceInteractionSessionShowCallback.asBinder() : null);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().showSessionForActiveService(bundle, i, iVoiceInteractionSessionShowCallback, iBinder);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public void hideCurrentSession() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().hideCurrentSession();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public void launchVoiceAssistFromKeyguard() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().launchVoiceAssistFromKeyguard();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public boolean isSessionRunning() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSessionRunning();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public boolean activeServiceSupportsAssist() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().activeServiceSupportsAssist();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public boolean activeServiceSupportsLaunchFromKeyguard() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().activeServiceSupportsLaunchFromKeyguard();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public void onLockscreenShown() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onLockscreenShown();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public void registerVoiceInteractionSessionListener(IVoiceInteractionSessionListener iVoiceInteractionSessionListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iVoiceInteractionSessionListener != null ? iVoiceInteractionSessionListener.asBinder() : null);
                    if (!this.mRemote.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerVoiceInteractionSessionListener(iVoiceInteractionSessionListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public void getActiveServiceSupportedActions(List<String> list, IVoiceActionCheckCallback iVoiceActionCheckCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringList(list);
                    parcelObtain.writeStrongBinder(iVoiceActionCheckCallback != null ? iVoiceActionCheckCallback.asBinder() : null);
                    if (!this.mRemote.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getActiveServiceSupportedActions(list, iVoiceActionCheckCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public void setUiHints(Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(34, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setUiHints(bundle);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public void requestDirectActions(IBinder iBinder, int i, IBinder iBinder2, RemoteCallback remoteCallback, RemoteCallback remoteCallback2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iBinder2);
                    if (remoteCallback != null) {
                        parcelObtain.writeInt(1);
                        remoteCallback.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (remoteCallback2 != null) {
                        parcelObtain.writeInt(1);
                        remoteCallback2.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(35, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestDirectActions(iBinder, i, iBinder2, remoteCallback, remoteCallback2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public void performDirectAction(IBinder iBinder, String str, Bundle bundle, int i, IBinder iBinder2, RemoteCallback remoteCallback, RemoteCallback remoteCallback2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeString(str);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iBinder2);
                    if (remoteCallback != null) {
                        parcelObtain.writeInt(1);
                        remoteCallback.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (remoteCallback2 != null) {
                        parcelObtain.writeInt(1);
                        remoteCallback2.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(36, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().performDirectAction(iBinder, str, bundle, i, iBinder2, remoteCallback, remoteCallback2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionManagerService
            public void setDisabled(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(37, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDisabled(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IVoiceInteractionManagerService iVoiceInteractionManagerService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iVoiceInteractionManagerService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iVoiceInteractionManagerService;
            return true;
        }

        public static IVoiceInteractionManagerService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
