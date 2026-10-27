package com.android.internal.app;

import android.app.AppOpsManager;
import android.app.AsyncNotedAppOp;
import android.app.RuntimeAppOpAccessMessage;
import android.app.SyncNotedAppOp;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteCallback;
import android.os.RemoteException;
import com.android.internal.app.IAppOpsActiveCallback;
import com.android.internal.app.IAppOpsAsyncNotedCallback;
import com.android.internal.app.IAppOpsCallback;
import com.android.internal.app.IAppOpsNotedCallback;
import com.android.internal.app.IAppOpsStartedCallback;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface IAppOpsService extends IInterface {

    public static class Default implements IAppOpsService {
        @Override // com.android.internal.app.IAppOpsService
        public void addHistoricalOps(AppOpsManager.HistoricalOps historicalOps) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.app.IAppOpsService
        public int checkAudioOperation(int i, int i2, int i3, String str) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.IAppOpsService
        public int checkOperation(int i, int i2, String str) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.IAppOpsService
        public int checkOperationRaw(int i, int i2, String str) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.IAppOpsService
        public int checkPackage(int i, String str) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.IAppOpsService
        public void clearHistory() throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void collectNoteOpCallsForValidation(String str, int i, String str2, long j) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public RuntimeAppOpAccessMessage collectRuntimeAppOpAccessMessage() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.app.IAppOpsService
        public List<AsyncNotedAppOp> extractAsyncOps(String str) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.app.IAppOpsService
        public void finishOperation(IBinder iBinder, int i, int i2, String str, String str2) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void getHistoricalOps(int i, String str, String str2, List<String> list, int i2, long j, long j2, int i3, RemoteCallback remoteCallback) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void getHistoricalOpsFromDiskRaw(int i, String str, String str2, List<String> list, int i2, long j, long j2, int i3, RemoteCallback remoteCallback) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public List<AppOpsManager.PackageOps> getOpsForPackage(int i, String str, int[] iArr) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.app.IAppOpsService
        public List<AppOpsManager.PackageOps> getPackagesForOps(int[] iArr) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.app.IAppOpsService
        public List<AppOpsManager.PackageOps> getUidOps(int i, int[] iArr) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.app.IAppOpsService
        public boolean isOperationActive(int i, int i2, String str) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.app.IAppOpsService
        public int noteOperation(int i, int i2, String str, String str2, boolean z, String str3, boolean z2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.IAppOpsService
        public int noteProxyOperation(int i, int i2, String str, String str2, int i3, String str3, String str4, boolean z, String str5, boolean z2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.IAppOpsService
        public void offsetHistory(long j) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public int permissionToOpCode(String str) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.IAppOpsService
        public void rebootHistory(long j) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void reloadNonHistoricalState() throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void removeUser(int i) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public MessageSamplingConfig reportRuntimeAppOpAccessMessageAndGetConfig(String str, SyncNotedAppOp syncNotedAppOp, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.app.IAppOpsService
        public void resetAllModes(int i, String str) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void resetHistoryParameters() throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void setAudioRestriction(int i, int i2, int i3, int i4, String[] strArr) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void setCameraAudioRestriction(int i) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void setHistoryParameters(int i, long j, int i2) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void setMode(int i, int i2, String str, int i3) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void setUidMode(int i, int i2, int i3) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void setUserRestriction(int i, boolean z, IBinder iBinder, int i2, String[] strArr) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void setUserRestrictions(Bundle bundle, IBinder iBinder, int i) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public boolean shouldCollectNotes(int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.app.IAppOpsService
        public int startOperation(IBinder iBinder, int i, int i2, String str, String str2, boolean z, boolean z2, String str3, boolean z3) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.IAppOpsService
        public void startWatchingActive(int[] iArr, IAppOpsActiveCallback iAppOpsActiveCallback) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void startWatchingAsyncNoted(String str, IAppOpsAsyncNotedCallback iAppOpsAsyncNotedCallback) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void startWatchingMode(int i, String str, IAppOpsCallback iAppOpsCallback) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void startWatchingModeWithFlags(int i, String str, int i2, IAppOpsCallback iAppOpsCallback) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void startWatchingNoted(int[] iArr, IAppOpsNotedCallback iAppOpsNotedCallback) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void startWatchingStarted(int[] iArr, IAppOpsStartedCallback iAppOpsStartedCallback) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void stopWatchingActive(IAppOpsActiveCallback iAppOpsActiveCallback) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void stopWatchingAsyncNoted(String str, IAppOpsAsyncNotedCallback iAppOpsAsyncNotedCallback) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void stopWatchingMode(IAppOpsCallback iAppOpsCallback) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void stopWatchingNoted(IAppOpsNotedCallback iAppOpsNotedCallback) throws RemoteException {
        }

        @Override // com.android.internal.app.IAppOpsService
        public void stopWatchingStarted(IAppOpsStartedCallback iAppOpsStartedCallback) throws RemoteException {
        }
    }

    void addHistoricalOps(AppOpsManager.HistoricalOps historicalOps) throws RemoteException;

    int checkAudioOperation(int i, int i2, int i3, String str) throws RemoteException;

    int checkOperation(int i, int i2, String str) throws RemoteException;

    int checkOperationRaw(int i, int i2, String str) throws RemoteException;

    int checkPackage(int i, String str) throws RemoteException;

    void clearHistory() throws RemoteException;

    void collectNoteOpCallsForValidation(String str, int i, String str2, long j) throws RemoteException;

    RuntimeAppOpAccessMessage collectRuntimeAppOpAccessMessage() throws RemoteException;

    List<AsyncNotedAppOp> extractAsyncOps(String str) throws RemoteException;

    void finishOperation(IBinder iBinder, int i, int i2, String str, String str2) throws RemoteException;

    void getHistoricalOps(int i, String str, String str2, List<String> list, int i2, long j, long j2, int i3, RemoteCallback remoteCallback) throws RemoteException;

    void getHistoricalOpsFromDiskRaw(int i, String str, String str2, List<String> list, int i2, long j, long j2, int i3, RemoteCallback remoteCallback) throws RemoteException;

    List<AppOpsManager.PackageOps> getOpsForPackage(int i, String str, int[] iArr) throws RemoteException;

    List<AppOpsManager.PackageOps> getPackagesForOps(int[] iArr) throws RemoteException;

    List<AppOpsManager.PackageOps> getUidOps(int i, int[] iArr) throws RemoteException;

    boolean isOperationActive(int i, int i2, String str) throws RemoteException;

    int noteOperation(int i, int i2, String str, String str2, boolean z, String str3, boolean z2) throws RemoteException;

    int noteProxyOperation(int i, int i2, String str, String str2, int i3, String str3, String str4, boolean z, String str5, boolean z2) throws RemoteException;

    void offsetHistory(long j) throws RemoteException;

    int permissionToOpCode(String str) throws RemoteException;

    void rebootHistory(long j) throws RemoteException;

    void reloadNonHistoricalState() throws RemoteException;

    void removeUser(int i) throws RemoteException;

    MessageSamplingConfig reportRuntimeAppOpAccessMessageAndGetConfig(String str, SyncNotedAppOp syncNotedAppOp, String str2) throws RemoteException;

    void resetAllModes(int i, String str) throws RemoteException;

    void resetHistoryParameters() throws RemoteException;

    void setAudioRestriction(int i, int i2, int i3, int i4, String[] strArr) throws RemoteException;

    void setCameraAudioRestriction(int i) throws RemoteException;

    void setHistoryParameters(int i, long j, int i2) throws RemoteException;

    void setMode(int i, int i2, String str, int i3) throws RemoteException;

    void setUidMode(int i, int i2, int i3) throws RemoteException;

    void setUserRestriction(int i, boolean z, IBinder iBinder, int i2, String[] strArr) throws RemoteException;

    void setUserRestrictions(Bundle bundle, IBinder iBinder, int i) throws RemoteException;

    boolean shouldCollectNotes(int i) throws RemoteException;

    int startOperation(IBinder iBinder, int i, int i2, String str, String str2, boolean z, boolean z2, String str3, boolean z3) throws RemoteException;

    void startWatchingActive(int[] iArr, IAppOpsActiveCallback iAppOpsActiveCallback) throws RemoteException;

    void startWatchingAsyncNoted(String str, IAppOpsAsyncNotedCallback iAppOpsAsyncNotedCallback) throws RemoteException;

    void startWatchingMode(int i, String str, IAppOpsCallback iAppOpsCallback) throws RemoteException;

    void startWatchingModeWithFlags(int i, String str, int i2, IAppOpsCallback iAppOpsCallback) throws RemoteException;

    void startWatchingNoted(int[] iArr, IAppOpsNotedCallback iAppOpsNotedCallback) throws RemoteException;

    void startWatchingStarted(int[] iArr, IAppOpsStartedCallback iAppOpsStartedCallback) throws RemoteException;

    void stopWatchingActive(IAppOpsActiveCallback iAppOpsActiveCallback) throws RemoteException;

    void stopWatchingAsyncNoted(String str, IAppOpsAsyncNotedCallback iAppOpsAsyncNotedCallback) throws RemoteException;

    void stopWatchingMode(IAppOpsCallback iAppOpsCallback) throws RemoteException;

    void stopWatchingNoted(IAppOpsNotedCallback iAppOpsNotedCallback) throws RemoteException;

    void stopWatchingStarted(IAppOpsStartedCallback iAppOpsStartedCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IAppOpsService {
        private static final String DESCRIPTOR = "com.android.internal.app.IAppOpsService";
        static final int TRANSACTION_addHistoricalOps = 21;
        static final int TRANSACTION_checkAudioOperation = 8;
        static final int TRANSACTION_checkOperation = 1;
        static final int TRANSACTION_checkOperationRaw = 44;
        static final int TRANSACTION_checkPackage = 12;
        static final int TRANSACTION_clearHistory = 23;
        static final int TRANSACTION_collectNoteOpCallsForValidation = 46;
        static final int TRANSACTION_collectRuntimeAppOpAccessMessage = 13;
        static final int TRANSACTION_extractAsyncOps = 43;
        static final int TRANSACTION_finishOperation = 4;
        static final int TRANSACTION_getHistoricalOps = 17;
        static final int TRANSACTION_getHistoricalOpsFromDiskRaw = 18;
        static final int TRANSACTION_getOpsForPackage = 16;
        static final int TRANSACTION_getPackagesForOps = 15;
        static final int TRANSACTION_getUidOps = 25;
        static final int TRANSACTION_isOperationActive = 35;
        static final int TRANSACTION_noteOperation = 2;
        static final int TRANSACTION_noteProxyOperation = 11;
        static final int TRANSACTION_offsetHistory = 19;
        static final int TRANSACTION_permissionToOpCode = 7;
        static final int TRANSACTION_rebootHistory = 24;
        static final int TRANSACTION_reloadNonHistoricalState = 45;
        static final int TRANSACTION_removeUser = 32;
        static final int TRANSACTION_reportRuntimeAppOpAccessMessageAndGetConfig = 14;
        static final int TRANSACTION_resetAllModes = 28;
        static final int TRANSACTION_resetHistoryParameters = 22;
        static final int TRANSACTION_setAudioRestriction = 29;
        static final int TRANSACTION_setCameraAudioRestriction = 10;
        static final int TRANSACTION_setHistoryParameters = 20;
        static final int TRANSACTION_setMode = 27;
        static final int TRANSACTION_setUidMode = 26;
        static final int TRANSACTION_setUserRestriction = 31;
        static final int TRANSACTION_setUserRestrictions = 30;
        static final int TRANSACTION_shouldCollectNotes = 9;
        static final int TRANSACTION_startOperation = 3;
        static final int TRANSACTION_startWatchingActive = 33;
        static final int TRANSACTION_startWatchingAsyncNoted = 41;
        static final int TRANSACTION_startWatchingMode = 5;
        static final int TRANSACTION_startWatchingModeWithFlags = 38;
        static final int TRANSACTION_startWatchingNoted = 39;
        static final int TRANSACTION_startWatchingStarted = 36;
        static final int TRANSACTION_stopWatchingActive = 34;
        static final int TRANSACTION_stopWatchingAsyncNoted = 42;
        static final int TRANSACTION_stopWatchingMode = 6;
        static final int TRANSACTION_stopWatchingNoted = 40;
        static final int TRANSACTION_stopWatchingStarted = 37;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "checkOperation";
                case 2:
                    return "noteOperation";
                case 3:
                    return "startOperation";
                case 4:
                    return "finishOperation";
                case 5:
                    return "startWatchingMode";
                case 6:
                    return "stopWatchingMode";
                case 7:
                    return "permissionToOpCode";
                case 8:
                    return "checkAudioOperation";
                case 9:
                    return "shouldCollectNotes";
                case 10:
                    return "setCameraAudioRestriction";
                case 11:
                    return "noteProxyOperation";
                case 12:
                    return "checkPackage";
                case 13:
                    return "collectRuntimeAppOpAccessMessage";
                case 14:
                    return "reportRuntimeAppOpAccessMessageAndGetConfig";
                case 15:
                    return "getPackagesForOps";
                case 16:
                    return "getOpsForPackage";
                case 17:
                    return "getHistoricalOps";
                case 18:
                    return "getHistoricalOpsFromDiskRaw";
                case 19:
                    return "offsetHistory";
                case 20:
                    return "setHistoryParameters";
                case 21:
                    return "addHistoricalOps";
                case 22:
                    return "resetHistoryParameters";
                case 23:
                    return "clearHistory";
                case 24:
                    return "rebootHistory";
                case 25:
                    return "getUidOps";
                case 26:
                    return "setUidMode";
                case 27:
                    return "setMode";
                case 28:
                    return "resetAllModes";
                case 29:
                    return "setAudioRestriction";
                case 30:
                    return "setUserRestrictions";
                case 31:
                    return "setUserRestriction";
                case 32:
                    return "removeUser";
                case 33:
                    return "startWatchingActive";
                case 34:
                    return "stopWatchingActive";
                case 35:
                    return "isOperationActive";
                case 36:
                    return "startWatchingStarted";
                case 37:
                    return "stopWatchingStarted";
                case 38:
                    return "startWatchingModeWithFlags";
                case 39:
                    return "startWatchingNoted";
                case 40:
                    return "stopWatchingNoted";
                case 41:
                    return "startWatchingAsyncNoted";
                case 42:
                    return "stopWatchingAsyncNoted";
                case 43:
                    return "extractAsyncOps";
                case 44:
                    return "checkOperationRaw";
                case 45:
                    return "reloadNonHistoricalState";
                case 46:
                    return "collectNoteOpCallsForValidation";
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

        public static IAppOpsService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAppOpsService)) {
                return (IAppOpsService) iInterfaceQueryLocalInterface;
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
                    int iCheckOperation = checkOperation(parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iCheckOperation);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iNoteOperation = noteOperation(parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt() != 0, parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(iNoteOperation);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iStartOperation = startOperation(parcel.readStrongBinder(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt() != 0, parcel.readInt() != 0, parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(iStartOperation);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    finishOperation(parcel.readStrongBinder(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    startWatchingMode(parcel.readInt(), parcel.readString(), IAppOpsCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopWatchingMode(IAppOpsCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iPermissionToOpCode = permissionToOpCode(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iPermissionToOpCode);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iCheckAudioOperation = checkAudioOperation(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iCheckAudioOperation);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zShouldCollectNotes = shouldCollectNotes(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zShouldCollectNotes ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    setCameraAudioRestriction(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iNoteProxyOperation = noteProxyOperation(parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt() != 0, parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(iNoteProxyOperation);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iCheckPackage = checkPackage(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iCheckPackage);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    RuntimeAppOpAccessMessage runtimeAppOpAccessMessageCollectRuntimeAppOpAccessMessage = collectRuntimeAppOpAccessMessage();
                    parcel2.writeNoException();
                    if (runtimeAppOpAccessMessageCollectRuntimeAppOpAccessMessage != null) {
                        parcel2.writeInt(1);
                        runtimeAppOpAccessMessageCollectRuntimeAppOpAccessMessage.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    MessageSamplingConfig messageSamplingConfigReportRuntimeAppOpAccessMessageAndGetConfig = reportRuntimeAppOpAccessMessageAndGetConfig(parcel.readString(), parcel.readInt() != 0 ? SyncNotedAppOp.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    if (messageSamplingConfigReportRuntimeAppOpAccessMessageAndGetConfig != null) {
                        parcel2.writeInt(1);
                        messageSamplingConfigReportRuntimeAppOpAccessMessageAndGetConfig.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<AppOpsManager.PackageOps> packagesForOps = getPackagesForOps(parcel.createIntArray());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(packagesForOps);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<AppOpsManager.PackageOps> opsForPackage = getOpsForPackage(parcel.readInt(), parcel.readString(), parcel.createIntArray());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(opsForPackage);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    getHistoricalOps(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.createStringArrayList(), parcel.readInt(), parcel.readLong(), parcel.readLong(), parcel.readInt(), parcel.readInt() != 0 ? RemoteCallback.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    getHistoricalOpsFromDiskRaw(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.createStringArrayList(), parcel.readInt(), parcel.readLong(), parcel.readLong(), parcel.readInt(), parcel.readInt() != 0 ? RemoteCallback.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    offsetHistory(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    setHistoryParameters(parcel.readInt(), parcel.readLong(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    addHistoricalOps(parcel.readInt() != 0 ? AppOpsManager.HistoricalOps.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    resetHistoryParameters();
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearHistory();
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    rebootHistory(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<AppOpsManager.PackageOps> uidOps = getUidOps(parcel.readInt(), parcel.createIntArray());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(uidOps);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    setUidMode(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    setMode(parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    resetAllModes(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAudioRestriction(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.createStringArray());
                    parcel2.writeNoException();
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    setUserRestrictions(parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readStrongBinder(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    setUserRestriction(parcel.readInt(), parcel.readInt() != 0, parcel.readStrongBinder(), parcel.readInt(), parcel.createStringArray());
                    parcel2.writeNoException();
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeUser(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    startWatchingActive(parcel.createIntArray(), IAppOpsActiveCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopWatchingActive(IAppOpsActiveCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsOperationActive = isOperationActive(parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsOperationActive ? 1 : 0);
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    startWatchingStarted(parcel.createIntArray(), IAppOpsStartedCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopWatchingStarted(IAppOpsStartedCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    startWatchingModeWithFlags(parcel.readInt(), parcel.readString(), parcel.readInt(), IAppOpsCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    startWatchingNoted(parcel.createIntArray(), IAppOpsNotedCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopWatchingNoted(IAppOpsNotedCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    startWatchingAsyncNoted(parcel.readString(), IAppOpsAsyncNotedCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopWatchingAsyncNoted(parcel.readString(), IAppOpsAsyncNotedCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<AsyncNotedAppOp> listExtractAsyncOps = extractAsyncOps(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(listExtractAsyncOps);
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iCheckOperationRaw = checkOperationRaw(parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iCheckOperationRaw);
                    return true;
                case 45:
                    parcel.enforceInterface(DESCRIPTOR);
                    reloadNonHistoricalState();
                    parcel2.writeNoException();
                    return true;
                case 46:
                    parcel.enforceInterface(DESCRIPTOR);
                    collectNoteOpCallsForValidation(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IAppOpsService {
            public static IAppOpsService sDefaultImpl;
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

            @Override // com.android.internal.app.IAppOpsService
            public int checkOperation(int i, int i2, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkOperation(i, i2, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public int noteOperation(int i, int i2, String str, String str2, boolean z, String str3, boolean z2) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    int i3 = 1;
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeString(str3);
                    if (!z2) {
                        i3 = 0;
                    }
                    parcelObtain.writeInt(i3);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        int iNoteOperation = Stub.getDefaultImpl().noteOperation(i, i2, str, str2, z, str3, z2);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return iNoteOperation;
                    }
                    parcelObtain2.readException();
                    int i4 = parcelObtain2.readInt();
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return i4;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public int startOperation(IBinder iBinder, int i, int i2, String str, String str2, boolean z, boolean z2, String str3, boolean z3) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    int i3 = 1;
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(z2 ? 1 : 0);
                    parcelObtain.writeString(str3);
                    if (!z3) {
                        i3 = 0;
                    }
                    parcelObtain.writeInt(i3);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        int iStartOperation = Stub.getDefaultImpl().startOperation(iBinder, i, i2, str, str2, z, z2, str3, z3);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return iStartOperation;
                    }
                    parcelObtain2.readException();
                    int i4 = parcelObtain2.readInt();
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return i4;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void finishOperation(IBinder iBinder, int i, int i2, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().finishOperation(iBinder, i, i2, str, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void startWatchingMode(int i, String str, IAppOpsCallback iAppOpsCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iAppOpsCallback != null ? iAppOpsCallback.asBinder() : null);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startWatchingMode(i, str, iAppOpsCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void stopWatchingMode(IAppOpsCallback iAppOpsCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iAppOpsCallback != null ? iAppOpsCallback.asBinder() : null);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopWatchingMode(iAppOpsCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public int permissionToOpCode(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().permissionToOpCode(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public int checkAudioOperation(int i, int i2, int i3, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkAudioOperation(i, i2, i3, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public boolean shouldCollectNotes(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().shouldCollectNotes(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void setCameraAudioRestriction(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setCameraAudioRestriction(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public int noteProxyOperation(int i, int i2, String str, String str2, int i3, String str3, String str4, boolean z, String str5, boolean z2) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeString(str4);
                    int i4 = 1;
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeString(str5);
                    if (!z2) {
                        i4 = 0;
                    }
                    parcelObtain.writeInt(i4);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        int iNoteProxyOperation = Stub.getDefaultImpl().noteProxyOperation(i, i2, str, str2, i3, str3, str4, z, str5, z2);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return iNoteProxyOperation;
                    }
                    parcelObtain2.readException();
                    int i5 = parcelObtain2.readInt();
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return i5;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public int checkPackage(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkPackage(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public RuntimeAppOpAccessMessage collectRuntimeAppOpAccessMessage() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().collectRuntimeAppOpAccessMessage();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? RuntimeAppOpAccessMessage.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public MessageSamplingConfig reportRuntimeAppOpAccessMessageAndGetConfig(String str, SyncNotedAppOp syncNotedAppOp, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (syncNotedAppOp != null) {
                        parcelObtain.writeInt(1);
                        syncNotedAppOp.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reportRuntimeAppOpAccessMessageAndGetConfig(str, syncNotedAppOp, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? MessageSamplingConfig.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public List<AppOpsManager.PackageOps> getPackagesForOps(int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeIntArray(iArr);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPackagesForOps(iArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(AppOpsManager.PackageOps.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public List<AppOpsManager.PackageOps> getOpsForPackage(int i, String str, int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeIntArray(iArr);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getOpsForPackage(i, str, iArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(AppOpsManager.PackageOps.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void getHistoricalOps(int i, String str, String str2, List<String> list, int i2, long j, long j2, int i3, RemoteCallback remoteCallback) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeStringList(list);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    parcelObtain.writeInt(i3);
                    if (remoteCallback != null) {
                        parcelObtain.writeInt(1);
                        remoteCallback.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getHistoricalOps(i, str, str2, list, i2, j, j2, i3, remoteCallback);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        parcelObtain2.readException();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void getHistoricalOpsFromDiskRaw(int i, String str, String str2, List<String> list, int i2, long j, long j2, int i3, RemoteCallback remoteCallback) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeStringList(list);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    parcelObtain.writeInt(i3);
                    if (remoteCallback != null) {
                        parcelObtain.writeInt(1);
                        remoteCallback.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getHistoricalOpsFromDiskRaw(i, str, str2, list, i2, j, j2, i3, remoteCallback);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        parcelObtain2.readException();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void offsetHistory(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().offsetHistory(j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void setHistoryParameters(int i, long j, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setHistoryParameters(i, j, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void addHistoricalOps(AppOpsManager.HistoricalOps historicalOps) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (historicalOps != null) {
                        parcelObtain.writeInt(1);
                        historicalOps.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addHistoricalOps(historicalOps);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void resetHistoryParameters() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().resetHistoryParameters();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void clearHistory() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearHistory();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void rebootHistory(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().rebootHistory(j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public List<AppOpsManager.PackageOps> getUidOps(int i, int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeIntArray(iArr);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUidOps(i, iArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(AppOpsManager.PackageOps.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void setUidMode(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setUidMode(i, i2, i3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void setMode(int i, int i2, String str, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setMode(i, i2, str, i3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void resetAllModes(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().resetAllModes(i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void setAudioRestriction(int i, int i2, int i3, int i4, String[] strArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    parcelObtain.writeStringArray(strArr);
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAudioRestriction(i, i2, i3, i4, strArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void setUserRestrictions(Bundle bundle, IBinder iBinder, int i) throws RemoteException {
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
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setUserRestrictions(bundle, iBinder, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void setUserRestriction(int i, boolean z, IBinder iBinder, int i2, String[] strArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStringArray(strArr);
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setUserRestriction(i, z, iBinder, i2, strArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void removeUser(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeUser(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void startWatchingActive(int[] iArr, IAppOpsActiveCallback iAppOpsActiveCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeIntArray(iArr);
                    parcelObtain.writeStrongBinder(iAppOpsActiveCallback != null ? iAppOpsActiveCallback.asBinder() : null);
                    if (!this.mRemote.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startWatchingActive(iArr, iAppOpsActiveCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void stopWatchingActive(IAppOpsActiveCallback iAppOpsActiveCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iAppOpsActiveCallback != null ? iAppOpsActiveCallback.asBinder() : null);
                    if (!this.mRemote.transact(34, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopWatchingActive(iAppOpsActiveCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public boolean isOperationActive(int i, int i2, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(35, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isOperationActive(i, i2, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void startWatchingStarted(int[] iArr, IAppOpsStartedCallback iAppOpsStartedCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeIntArray(iArr);
                    parcelObtain.writeStrongBinder(iAppOpsStartedCallback != null ? iAppOpsStartedCallback.asBinder() : null);
                    if (!this.mRemote.transact(36, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startWatchingStarted(iArr, iAppOpsStartedCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void stopWatchingStarted(IAppOpsStartedCallback iAppOpsStartedCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iAppOpsStartedCallback != null ? iAppOpsStartedCallback.asBinder() : null);
                    if (!this.mRemote.transact(37, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopWatchingStarted(iAppOpsStartedCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void startWatchingModeWithFlags(int i, String str, int i2, IAppOpsCallback iAppOpsCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iAppOpsCallback != null ? iAppOpsCallback.asBinder() : null);
                    if (!this.mRemote.transact(38, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startWatchingModeWithFlags(i, str, i2, iAppOpsCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void startWatchingNoted(int[] iArr, IAppOpsNotedCallback iAppOpsNotedCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeIntArray(iArr);
                    parcelObtain.writeStrongBinder(iAppOpsNotedCallback != null ? iAppOpsNotedCallback.asBinder() : null);
                    if (!this.mRemote.transact(39, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startWatchingNoted(iArr, iAppOpsNotedCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void stopWatchingNoted(IAppOpsNotedCallback iAppOpsNotedCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iAppOpsNotedCallback != null ? iAppOpsNotedCallback.asBinder() : null);
                    if (!this.mRemote.transact(40, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopWatchingNoted(iAppOpsNotedCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void startWatchingAsyncNoted(String str, IAppOpsAsyncNotedCallback iAppOpsAsyncNotedCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iAppOpsAsyncNotedCallback != null ? iAppOpsAsyncNotedCallback.asBinder() : null);
                    if (!this.mRemote.transact(41, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startWatchingAsyncNoted(str, iAppOpsAsyncNotedCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void stopWatchingAsyncNoted(String str, IAppOpsAsyncNotedCallback iAppOpsAsyncNotedCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iAppOpsAsyncNotedCallback != null ? iAppOpsAsyncNotedCallback.asBinder() : null);
                    if (!this.mRemote.transact(42, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopWatchingAsyncNoted(str, iAppOpsAsyncNotedCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public List<AsyncNotedAppOp> extractAsyncOps(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(43, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().extractAsyncOps(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(AsyncNotedAppOp.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public int checkOperationRaw(int i, int i2, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(44, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkOperationRaw(i, i2, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void reloadNonHistoricalState() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(45, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reloadNonHistoricalState();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IAppOpsService
            public void collectNoteOpCallsForValidation(String str, int i, String str2, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(46, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().collectNoteOpCallsForValidation(str, i, str2, j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAppOpsService iAppOpsService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iAppOpsService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAppOpsService;
            return true;
        }

        public static IAppOpsService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
