package com.android.internal.telecom;

import android.media.MediaMetrics;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.telecom.CallAudioState;
import android.telecom.ConnectionRequest;
import android.telecom.Logging.Session;
import android.telecom.PhoneAccountHandle;
import android.telephony.ims.ImsCallProfile;
import com.android.internal.telecom.IConnectionServiceAdapter;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface IConnectionService extends IInterface {

    public static class Default implements IConnectionService {
        @Override // com.android.internal.telecom.IConnectionService
        public void abort(String str, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void addConferenceParticipants(String str, List<Uri> list, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void addConnectionServiceAdapter(IConnectionServiceAdapter iConnectionServiceAdapter, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void answer(String str, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void answerVideo(String str, int i, Session.Info info) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void conference(String str, String str2, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void connectionServiceFocusGained(Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void connectionServiceFocusLost(Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void consultativeTransfer(String str, String str2, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void createConference(PhoneAccountHandle phoneAccountHandle, String str, ConnectionRequest connectionRequest, boolean z, boolean z2, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void createConferenceComplete(String str, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void createConferenceFailed(PhoneAccountHandle phoneAccountHandle, String str, ConnectionRequest connectionRequest, boolean z, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void createConnection(PhoneAccountHandle phoneAccountHandle, String str, ConnectionRequest connectionRequest, boolean z, boolean z2, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void createConnectionComplete(String str, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void createConnectionFailed(PhoneAccountHandle phoneAccountHandle, String str, ConnectionRequest connectionRequest, boolean z, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void deflect(String str, Uri uri, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void disconnect(String str, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void handoverComplete(String str, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void handoverFailed(String str, ConnectionRequest connectionRequest, int i, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void hold(String str, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void mergeConference(String str, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void onCallAudioStateChanged(String str, CallAudioState callAudioState, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void onExtrasChanged(String str, Bundle bundle, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void onPostDialContinue(String str, boolean z, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void playDtmfTone(String str, char c, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void pullExternalCall(String str, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void reject(String str, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void rejectWithMessage(String str, String str2, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void rejectWithReason(String str, int i, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void removeConnectionServiceAdapter(IConnectionServiceAdapter iConnectionServiceAdapter, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void respondToRttUpgradeRequest(String str, ParcelFileDescriptor parcelFileDescriptor, ParcelFileDescriptor parcelFileDescriptor2, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void sendCallEvent(String str, String str2, Bundle bundle, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void silence(String str, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void splitFromConference(String str, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void startRtt(String str, ParcelFileDescriptor parcelFileDescriptor, ParcelFileDescriptor parcelFileDescriptor2, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void stopDtmfTone(String str, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void stopRtt(String str, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void swapConference(String str, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void transfer(String str, Uri uri, boolean z, Session.Info info) throws RemoteException {
        }

        @Override // com.android.internal.telecom.IConnectionService
        public void unhold(String str, Session.Info info) throws RemoteException {
        }
    }

    void abort(String str, Session.Info info) throws RemoteException;

    void addConferenceParticipants(String str, List<Uri> list, Session.Info info) throws RemoteException;

    void addConnectionServiceAdapter(IConnectionServiceAdapter iConnectionServiceAdapter, Session.Info info) throws RemoteException;

    void answer(String str, Session.Info info) throws RemoteException;

    void answerVideo(String str, int i, Session.Info info) throws RemoteException;

    void conference(String str, String str2, Session.Info info) throws RemoteException;

    void connectionServiceFocusGained(Session.Info info) throws RemoteException;

    void connectionServiceFocusLost(Session.Info info) throws RemoteException;

    void consultativeTransfer(String str, String str2, Session.Info info) throws RemoteException;

    void createConference(PhoneAccountHandle phoneAccountHandle, String str, ConnectionRequest connectionRequest, boolean z, boolean z2, Session.Info info) throws RemoteException;

    void createConferenceComplete(String str, Session.Info info) throws RemoteException;

    void createConferenceFailed(PhoneAccountHandle phoneAccountHandle, String str, ConnectionRequest connectionRequest, boolean z, Session.Info info) throws RemoteException;

    void createConnection(PhoneAccountHandle phoneAccountHandle, String str, ConnectionRequest connectionRequest, boolean z, boolean z2, Session.Info info) throws RemoteException;

    void createConnectionComplete(String str, Session.Info info) throws RemoteException;

    void createConnectionFailed(PhoneAccountHandle phoneAccountHandle, String str, ConnectionRequest connectionRequest, boolean z, Session.Info info) throws RemoteException;

    void deflect(String str, Uri uri, Session.Info info) throws RemoteException;

    void disconnect(String str, Session.Info info) throws RemoteException;

    void handoverComplete(String str, Session.Info info) throws RemoteException;

    void handoverFailed(String str, ConnectionRequest connectionRequest, int i, Session.Info info) throws RemoteException;

    void hold(String str, Session.Info info) throws RemoteException;

    void mergeConference(String str, Session.Info info) throws RemoteException;

    void onCallAudioStateChanged(String str, CallAudioState callAudioState, Session.Info info) throws RemoteException;

    void onExtrasChanged(String str, Bundle bundle, Session.Info info) throws RemoteException;

    void onPostDialContinue(String str, boolean z, Session.Info info) throws RemoteException;

    void playDtmfTone(String str, char c, Session.Info info) throws RemoteException;

    void pullExternalCall(String str, Session.Info info) throws RemoteException;

    void reject(String str, Session.Info info) throws RemoteException;

    void rejectWithMessage(String str, String str2, Session.Info info) throws RemoteException;

    void rejectWithReason(String str, int i, Session.Info info) throws RemoteException;

    void removeConnectionServiceAdapter(IConnectionServiceAdapter iConnectionServiceAdapter, Session.Info info) throws RemoteException;

    void respondToRttUpgradeRequest(String str, ParcelFileDescriptor parcelFileDescriptor, ParcelFileDescriptor parcelFileDescriptor2, Session.Info info) throws RemoteException;

    void sendCallEvent(String str, String str2, Bundle bundle, Session.Info info) throws RemoteException;

    void silence(String str, Session.Info info) throws RemoteException;

    void splitFromConference(String str, Session.Info info) throws RemoteException;

    void startRtt(String str, ParcelFileDescriptor parcelFileDescriptor, ParcelFileDescriptor parcelFileDescriptor2, Session.Info info) throws RemoteException;

    void stopDtmfTone(String str, Session.Info info) throws RemoteException;

    void stopRtt(String str, Session.Info info) throws RemoteException;

    void swapConference(String str, Session.Info info) throws RemoteException;

    void transfer(String str, Uri uri, boolean z, Session.Info info) throws RemoteException;

    void unhold(String str, Session.Info info) throws RemoteException;

    public static abstract class Stub extends Binder implements IConnectionService {
        private static final String DESCRIPTOR = "com.android.internal.telecom.IConnectionService";
        static final int TRANSACTION_abort = 9;
        static final int TRANSACTION_addConferenceParticipants = 29;
        static final int TRANSACTION_addConnectionServiceAdapter = 1;
        static final int TRANSACTION_answer = 11;
        static final int TRANSACTION_answerVideo = 10;
        static final int TRANSACTION_conference = 25;
        static final int TRANSACTION_connectionServiceFocusGained = 38;
        static final int TRANSACTION_connectionServiceFocusLost = 37;
        static final int TRANSACTION_consultativeTransfer = 17;
        static final int TRANSACTION_createConference = 6;
        static final int TRANSACTION_createConferenceComplete = 7;
        static final int TRANSACTION_createConferenceFailed = 8;
        static final int TRANSACTION_createConnection = 3;
        static final int TRANSACTION_createConnectionComplete = 4;
        static final int TRANSACTION_createConnectionFailed = 5;
        static final int TRANSACTION_deflect = 12;
        static final int TRANSACTION_disconnect = 18;
        static final int TRANSACTION_handoverComplete = 40;
        static final int TRANSACTION_handoverFailed = 39;
        static final int TRANSACTION_hold = 20;
        static final int TRANSACTION_mergeConference = 27;
        static final int TRANSACTION_onCallAudioStateChanged = 22;
        static final int TRANSACTION_onExtrasChanged = 33;
        static final int TRANSACTION_onPostDialContinue = 30;
        static final int TRANSACTION_playDtmfTone = 23;
        static final int TRANSACTION_pullExternalCall = 31;
        static final int TRANSACTION_reject = 13;
        static final int TRANSACTION_rejectWithMessage = 15;
        static final int TRANSACTION_rejectWithReason = 14;
        static final int TRANSACTION_removeConnectionServiceAdapter = 2;
        static final int TRANSACTION_respondToRttUpgradeRequest = 36;
        static final int TRANSACTION_sendCallEvent = 32;
        static final int TRANSACTION_silence = 19;
        static final int TRANSACTION_splitFromConference = 26;
        static final int TRANSACTION_startRtt = 34;
        static final int TRANSACTION_stopDtmfTone = 24;
        static final int TRANSACTION_stopRtt = 35;
        static final int TRANSACTION_swapConference = 28;
        static final int TRANSACTION_transfer = 16;
        static final int TRANSACTION_unhold = 21;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "addConnectionServiceAdapter";
                case 2:
                    return "removeConnectionServiceAdapter";
                case 3:
                    return "createConnection";
                case 4:
                    return "createConnectionComplete";
                case 5:
                    return "createConnectionFailed";
                case 6:
                    return "createConference";
                case 7:
                    return "createConferenceComplete";
                case 8:
                    return "createConferenceFailed";
                case 9:
                    return "abort";
                case 10:
                    return "answerVideo";
                case 11:
                    return "answer";
                case 12:
                    return "deflect";
                case 13:
                    return "reject";
                case 14:
                    return "rejectWithReason";
                case 15:
                    return "rejectWithMessage";
                case 16:
                    return "transfer";
                case 17:
                    return "consultativeTransfer";
                case 18:
                    return MediaMetrics.Value.DISCONNECT;
                case 19:
                    return "silence";
                case 20:
                    return "hold";
                case 21:
                    return "unhold";
                case 22:
                    return "onCallAudioStateChanged";
                case 23:
                    return "playDtmfTone";
                case 24:
                    return "stopDtmfTone";
                case 25:
                    return ImsCallProfile.EXTRA_CONFERENCE;
                case 26:
                    return "splitFromConference";
                case 27:
                    return "mergeConference";
                case 28:
                    return "swapConference";
                case 29:
                    return "addConferenceParticipants";
                case 30:
                    return "onPostDialContinue";
                case 31:
                    return "pullExternalCall";
                case 32:
                    return "sendCallEvent";
                case 33:
                    return "onExtrasChanged";
                case 34:
                    return "startRtt";
                case 35:
                    return "stopRtt";
                case 36:
                    return "respondToRttUpgradeRequest";
                case 37:
                    return "connectionServiceFocusLost";
                case 38:
                    return "connectionServiceFocusGained";
                case 39:
                    return "handoverFailed";
                case 40:
                    return "handoverComplete";
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

        public static IConnectionService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IConnectionService)) {
                return (IConnectionService) iInterfaceQueryLocalInterface;
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
                    addConnectionServiceAdapter(IConnectionServiceAdapter.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeConnectionServiceAdapter(IConnectionServiceAdapter.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    createConnection(parcel.readInt() != 0 ? PhoneAccountHandle.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt() != 0 ? ConnectionRequest.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0, parcel.readInt() != 0, parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    createConnectionComplete(parcel.readString(), parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    createConnectionFailed(parcel.readInt() != 0 ? PhoneAccountHandle.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt() != 0 ? ConnectionRequest.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0, parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    createConference(parcel.readInt() != 0 ? PhoneAccountHandle.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt() != 0 ? ConnectionRequest.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0, parcel.readInt() != 0, parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    createConferenceComplete(parcel.readString(), parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    createConferenceFailed(parcel.readInt() != 0 ? PhoneAccountHandle.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt() != 0 ? ConnectionRequest.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0, parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    abort(parcel.readString(), parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    answerVideo(parcel.readString(), parcel.readInt(), parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    answer(parcel.readString(), parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    deflect(parcel.readString(), parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    reject(parcel.readString(), parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    rejectWithReason(parcel.readString(), parcel.readInt(), parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    rejectWithMessage(parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    transfer(parcel.readString(), parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0, parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    consultativeTransfer(parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    disconnect(parcel.readString(), parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    silence(parcel.readString(), parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    hold(parcel.readString(), parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    unhold(parcel.readString(), parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    onCallAudioStateChanged(parcel.readString(), parcel.readInt() != 0 ? CallAudioState.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    playDtmfTone(parcel.readString(), (char) parcel.readInt(), parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopDtmfTone(parcel.readString(), parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    conference(parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    splitFromConference(parcel.readString(), parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    mergeConference(parcel.readString(), parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    swapConference(parcel.readString(), parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    addConferenceParticipants(parcel.readString(), parcel.createTypedArrayList(Uri.CREATOR), parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPostDialContinue(parcel.readString(), parcel.readInt() != 0, parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    pullExternalCall(parcel.readString(), parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendCallEvent(parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    onExtrasChanged(parcel.readString(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    startRtt(parcel.readString(), parcel.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopRtt(parcel.readString(), parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    respondToRttUpgradeRequest(parcel.readString(), parcel.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    connectionServiceFocusLost(parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    connectionServiceFocusGained(parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    handoverFailed(parcel.readString(), parcel.readInt() != 0 ? ConnectionRequest.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    handoverComplete(parcel.readString(), parcel.readInt() != 0 ? Session.Info.CREATOR.createFromParcel(parcel) : null);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IConnectionService {
            public static IConnectionService sDefaultImpl;
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

            @Override // com.android.internal.telecom.IConnectionService
            public void addConnectionServiceAdapter(IConnectionServiceAdapter iConnectionServiceAdapter, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iConnectionServiceAdapter != null ? iConnectionServiceAdapter.asBinder() : null);
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().addConnectionServiceAdapter(iConnectionServiceAdapter, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void removeConnectionServiceAdapter(IConnectionServiceAdapter iConnectionServiceAdapter, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iConnectionServiceAdapter != null ? iConnectionServiceAdapter.asBinder() : null);
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().removeConnectionServiceAdapter(iConnectionServiceAdapter, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void createConnection(PhoneAccountHandle phoneAccountHandle, String str, ConnectionRequest connectionRequest, boolean z, boolean z2, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (phoneAccountHandle != null) {
                        parcelObtain.writeInt(1);
                        phoneAccountHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (connectionRequest != null) {
                        parcelObtain.writeInt(1);
                        connectionRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(z2 ? 1 : 0);
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().createConnection(phoneAccountHandle, str, connectionRequest, z, z2, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void createConnectionComplete(String str, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().createConnectionComplete(str, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void createConnectionFailed(PhoneAccountHandle phoneAccountHandle, String str, ConnectionRequest connectionRequest, boolean z, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (phoneAccountHandle != null) {
                        parcelObtain.writeInt(1);
                        phoneAccountHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (connectionRequest != null) {
                        parcelObtain.writeInt(1);
                        connectionRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().createConnectionFailed(phoneAccountHandle, str, connectionRequest, z, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void createConference(PhoneAccountHandle phoneAccountHandle, String str, ConnectionRequest connectionRequest, boolean z, boolean z2, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (phoneAccountHandle != null) {
                        parcelObtain.writeInt(1);
                        phoneAccountHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (connectionRequest != null) {
                        parcelObtain.writeInt(1);
                        connectionRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(z2 ? 1 : 0);
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().createConference(phoneAccountHandle, str, connectionRequest, z, z2, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void createConferenceComplete(String str, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().createConferenceComplete(str, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void createConferenceFailed(PhoneAccountHandle phoneAccountHandle, String str, ConnectionRequest connectionRequest, boolean z, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (phoneAccountHandle != null) {
                        parcelObtain.writeInt(1);
                        phoneAccountHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (connectionRequest != null) {
                        parcelObtain.writeInt(1);
                        connectionRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().createConferenceFailed(phoneAccountHandle, str, connectionRequest, z, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void abort(String str, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().abort(str, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void answerVideo(String str, int i, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(10, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().answerVideo(str, i, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void answer(String str, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(11, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().answer(str, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void deflect(String str, Uri uri, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(12, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().deflect(str, uri, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void reject(String str, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(13, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().reject(str, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void rejectWithReason(String str, int i, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(14, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().rejectWithReason(str, i, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void rejectWithMessage(String str, String str2, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(15, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().rejectWithMessage(str, str2, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void transfer(String str, Uri uri, boolean z, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(16, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().transfer(str, uri, z, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void consultativeTransfer(String str, String str2, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(17, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().consultativeTransfer(str, str2, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void disconnect(String str, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(18, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().disconnect(str, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void silence(String str, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(19, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().silence(str, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void hold(String str, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(20, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().hold(str, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void unhold(String str, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(21, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().unhold(str, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void onCallAudioStateChanged(String str, CallAudioState callAudioState, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (callAudioState != null) {
                        parcelObtain.writeInt(1);
                        callAudioState.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(22, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCallAudioStateChanged(str, callAudioState, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void playDtmfTone(String str, char c, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(c);
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(23, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().playDtmfTone(str, c, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void stopDtmfTone(String str, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(24, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().stopDtmfTone(str, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void conference(String str, String str2, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(25, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().conference(str, str2, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void splitFromConference(String str, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(26, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().splitFromConference(str, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void mergeConference(String str, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(27, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().mergeConference(str, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void swapConference(String str, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(28, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().swapConference(str, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void addConferenceParticipants(String str, List<Uri> list, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeTypedList(list);
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(29, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().addConferenceParticipants(str, list, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void onPostDialContinue(String str, boolean z, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(30, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPostDialContinue(str, z, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void pullExternalCall(String str, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(31, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().pullExternalCall(str, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void sendCallEvent(String str, String str2, Bundle bundle, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(32, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().sendCallEvent(str, str2, bundle, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void onExtrasChanged(String str, Bundle bundle, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(33, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onExtrasChanged(str, bundle, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void startRtt(String str, ParcelFileDescriptor parcelFileDescriptor, ParcelFileDescriptor parcelFileDescriptor2, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (parcelFileDescriptor != null) {
                        parcelObtain.writeInt(1);
                        parcelFileDescriptor.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (parcelFileDescriptor2 != null) {
                        parcelObtain.writeInt(1);
                        parcelFileDescriptor2.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(34, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().startRtt(str, parcelFileDescriptor, parcelFileDescriptor2, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void stopRtt(String str, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(35, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().stopRtt(str, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void respondToRttUpgradeRequest(String str, ParcelFileDescriptor parcelFileDescriptor, ParcelFileDescriptor parcelFileDescriptor2, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (parcelFileDescriptor != null) {
                        parcelObtain.writeInt(1);
                        parcelFileDescriptor.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (parcelFileDescriptor2 != null) {
                        parcelObtain.writeInt(1);
                        parcelFileDescriptor2.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(36, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().respondToRttUpgradeRequest(str, parcelFileDescriptor, parcelFileDescriptor2, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void connectionServiceFocusLost(Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(37, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().connectionServiceFocusLost(info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void connectionServiceFocusGained(Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(38, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().connectionServiceFocusGained(info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void handoverFailed(String str, ConnectionRequest connectionRequest, int i, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (connectionRequest != null) {
                        parcelObtain.writeInt(1);
                        connectionRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(39, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().handoverFailed(str, connectionRequest, i, info);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.IConnectionService
            public void handoverComplete(String str, Session.Info info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (info != null) {
                        parcelObtain.writeInt(1);
                        info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(40, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().handoverComplete(str, info);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IConnectionService iConnectionService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iConnectionService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iConnectionService;
            return true;
        }

        public static IConnectionService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
