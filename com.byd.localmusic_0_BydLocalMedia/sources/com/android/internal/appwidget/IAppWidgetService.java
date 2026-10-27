package com.android.internal.appwidget;

import android.app.IApplicationThread;
import android.app.IServiceConnection;
import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ParceledListSlice;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.widget.RemoteViews;
import com.android.internal.appwidget.IAppWidgetHost;

/* JADX INFO: loaded from: classes4.dex */
public interface IAppWidgetService extends IInterface {

    public static class Default implements IAppWidgetService {
        @Override // com.android.internal.appwidget.IAppWidgetService
        public int allocateAppWidgetId(String str, int i) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.appwidget.IAppWidgetService
        public boolean bindAppWidgetId(String str, int i, int i2, ComponentName componentName, Bundle bundle) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.appwidget.IAppWidgetService
        public boolean bindRemoteViewsService(String str, int i, Intent intent, IApplicationThread iApplicationThread, IBinder iBinder, IServiceConnection iServiceConnection, int i2) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.appwidget.IAppWidgetService
        public IntentSender createAppWidgetConfigIntentSender(String str, int i, int i2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.appwidget.IAppWidgetService
        public void deleteAllHosts() throws RemoteException {
        }

        @Override // com.android.internal.appwidget.IAppWidgetService
        public void deleteAppWidgetId(String str, int i) throws RemoteException {
        }

        @Override // com.android.internal.appwidget.IAppWidgetService
        public void deleteHost(String str, int i) throws RemoteException {
        }

        @Override // com.android.internal.appwidget.IAppWidgetService
        public int[] getAppWidgetIds(ComponentName componentName) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.appwidget.IAppWidgetService
        public int[] getAppWidgetIdsForHost(String str, int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.appwidget.IAppWidgetService
        public AppWidgetProviderInfo getAppWidgetInfo(String str, int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.appwidget.IAppWidgetService
        public Bundle getAppWidgetOptions(String str, int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.appwidget.IAppWidgetService
        public RemoteViews getAppWidgetViews(String str, int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.appwidget.IAppWidgetService
        public ParceledListSlice getInstalledProvidersForProfile(int i, int i2, String str) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.appwidget.IAppWidgetService
        public boolean hasBindAppWidgetPermission(String str, int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.appwidget.IAppWidgetService
        public boolean isBoundWidgetPackage(String str, int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.appwidget.IAppWidgetService
        public boolean isRequestPinAppWidgetSupported() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.appwidget.IAppWidgetService
        public void noteAppWidgetTapped(String str, int i) throws RemoteException {
        }

        @Override // com.android.internal.appwidget.IAppWidgetService
        public void notifyAppWidgetViewDataChanged(String str, int[] iArr, int i) throws RemoteException {
        }

        @Override // com.android.internal.appwidget.IAppWidgetService
        public void partiallyUpdateAppWidgetIds(String str, int[] iArr, RemoteViews remoteViews) throws RemoteException {
        }

        @Override // com.android.internal.appwidget.IAppWidgetService
        public boolean requestPinAppWidget(String str, ComponentName componentName, Bundle bundle, IntentSender intentSender) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.appwidget.IAppWidgetService
        public void setBindAppWidgetPermission(String str, int i, boolean z) throws RemoteException {
        }

        @Override // com.android.internal.appwidget.IAppWidgetService
        public ParceledListSlice startListening(IAppWidgetHost iAppWidgetHost, String str, int i, int[] iArr) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.appwidget.IAppWidgetService
        public void stopListening(String str, int i) throws RemoteException {
        }

        @Override // com.android.internal.appwidget.IAppWidgetService
        public void updateAppWidgetIds(String str, int[] iArr, RemoteViews remoteViews) throws RemoteException {
        }

        @Override // com.android.internal.appwidget.IAppWidgetService
        public void updateAppWidgetOptions(String str, int i, Bundle bundle) throws RemoteException {
        }

        @Override // com.android.internal.appwidget.IAppWidgetService
        public void updateAppWidgetProvider(ComponentName componentName, RemoteViews remoteViews) throws RemoteException {
        }

        @Override // com.android.internal.appwidget.IAppWidgetService
        public void updateAppWidgetProviderInfo(ComponentName componentName, String str) throws RemoteException {
        }
    }

    int allocateAppWidgetId(String str, int i) throws RemoteException;

    boolean bindAppWidgetId(String str, int i, int i2, ComponentName componentName, Bundle bundle) throws RemoteException;

    boolean bindRemoteViewsService(String str, int i, Intent intent, IApplicationThread iApplicationThread, IBinder iBinder, IServiceConnection iServiceConnection, int i2) throws RemoteException;

    IntentSender createAppWidgetConfigIntentSender(String str, int i, int i2) throws RemoteException;

    void deleteAllHosts() throws RemoteException;

    void deleteAppWidgetId(String str, int i) throws RemoteException;

    void deleteHost(String str, int i) throws RemoteException;

    int[] getAppWidgetIds(ComponentName componentName) throws RemoteException;

    int[] getAppWidgetIdsForHost(String str, int i) throws RemoteException;

    AppWidgetProviderInfo getAppWidgetInfo(String str, int i) throws RemoteException;

    Bundle getAppWidgetOptions(String str, int i) throws RemoteException;

    RemoteViews getAppWidgetViews(String str, int i) throws RemoteException;

    ParceledListSlice getInstalledProvidersForProfile(int i, int i2, String str) throws RemoteException;

    boolean hasBindAppWidgetPermission(String str, int i) throws RemoteException;

    boolean isBoundWidgetPackage(String str, int i) throws RemoteException;

    boolean isRequestPinAppWidgetSupported() throws RemoteException;

    void noteAppWidgetTapped(String str, int i) throws RemoteException;

    void notifyAppWidgetViewDataChanged(String str, int[] iArr, int i) throws RemoteException;

    void partiallyUpdateAppWidgetIds(String str, int[] iArr, RemoteViews remoteViews) throws RemoteException;

    boolean requestPinAppWidget(String str, ComponentName componentName, Bundle bundle, IntentSender intentSender) throws RemoteException;

    void setBindAppWidgetPermission(String str, int i, boolean z) throws RemoteException;

    ParceledListSlice startListening(IAppWidgetHost iAppWidgetHost, String str, int i, int[] iArr) throws RemoteException;

    void stopListening(String str, int i) throws RemoteException;

    void updateAppWidgetIds(String str, int[] iArr, RemoteViews remoteViews) throws RemoteException;

    void updateAppWidgetOptions(String str, int i, Bundle bundle) throws RemoteException;

    void updateAppWidgetProvider(ComponentName componentName, RemoteViews remoteViews) throws RemoteException;

    void updateAppWidgetProviderInfo(ComponentName componentName, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IAppWidgetService {
        private static final String DESCRIPTOR = "com.android.internal.appwidget.IAppWidgetService";
        static final int TRANSACTION_allocateAppWidgetId = 3;
        static final int TRANSACTION_bindAppWidgetId = 21;
        static final int TRANSACTION_bindRemoteViewsService = 22;
        static final int TRANSACTION_createAppWidgetConfigIntentSender = 9;
        static final int TRANSACTION_deleteAllHosts = 6;
        static final int TRANSACTION_deleteAppWidgetId = 4;
        static final int TRANSACTION_deleteHost = 5;
        static final int TRANSACTION_getAppWidgetIds = 23;
        static final int TRANSACTION_getAppWidgetIdsForHost = 8;
        static final int TRANSACTION_getAppWidgetInfo = 18;
        static final int TRANSACTION_getAppWidgetOptions = 12;
        static final int TRANSACTION_getAppWidgetViews = 7;
        static final int TRANSACTION_getInstalledProvidersForProfile = 17;
        static final int TRANSACTION_hasBindAppWidgetPermission = 19;
        static final int TRANSACTION_isBoundWidgetPackage = 24;
        static final int TRANSACTION_isRequestPinAppWidgetSupported = 26;
        static final int TRANSACTION_noteAppWidgetTapped = 27;
        static final int TRANSACTION_notifyAppWidgetViewDataChanged = 16;
        static final int TRANSACTION_partiallyUpdateAppWidgetIds = 13;
        static final int TRANSACTION_requestPinAppWidget = 25;
        static final int TRANSACTION_setBindAppWidgetPermission = 20;
        static final int TRANSACTION_startListening = 1;
        static final int TRANSACTION_stopListening = 2;
        static final int TRANSACTION_updateAppWidgetIds = 10;
        static final int TRANSACTION_updateAppWidgetOptions = 11;
        static final int TRANSACTION_updateAppWidgetProvider = 14;
        static final int TRANSACTION_updateAppWidgetProviderInfo = 15;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "startListening";
                case 2:
                    return "stopListening";
                case 3:
                    return "allocateAppWidgetId";
                case 4:
                    return "deleteAppWidgetId";
                case 5:
                    return "deleteHost";
                case 6:
                    return "deleteAllHosts";
                case 7:
                    return "getAppWidgetViews";
                case 8:
                    return "getAppWidgetIdsForHost";
                case 9:
                    return "createAppWidgetConfigIntentSender";
                case 10:
                    return "updateAppWidgetIds";
                case 11:
                    return "updateAppWidgetOptions";
                case 12:
                    return "getAppWidgetOptions";
                case 13:
                    return "partiallyUpdateAppWidgetIds";
                case 14:
                    return "updateAppWidgetProvider";
                case 15:
                    return "updateAppWidgetProviderInfo";
                case 16:
                    return "notifyAppWidgetViewDataChanged";
                case 17:
                    return "getInstalledProvidersForProfile";
                case 18:
                    return "getAppWidgetInfo";
                case 19:
                    return "hasBindAppWidgetPermission";
                case 20:
                    return "setBindAppWidgetPermission";
                case 21:
                    return "bindAppWidgetId";
                case 22:
                    return "bindRemoteViewsService";
                case 23:
                    return "getAppWidgetIds";
                case 24:
                    return "isBoundWidgetPackage";
                case 25:
                    return "requestPinAppWidget";
                case 26:
                    return "isRequestPinAppWidgetSupported";
                case 27:
                    return "noteAppWidgetTapped";
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

        public static IAppWidgetService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAppWidgetService)) {
                return (IAppWidgetService) iInterfaceQueryLocalInterface;
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
                    ParceledListSlice parceledListSliceStartListening = startListening(IAppWidgetHost.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt(), parcel.createIntArray());
                    parcel2.writeNoException();
                    if (parceledListSliceStartListening != null) {
                        parcel2.writeInt(1);
                        parceledListSliceStartListening.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopListening(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iAllocateAppWidgetId = allocateAppWidgetId(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iAllocateAppWidgetId);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    deleteAppWidgetId(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    deleteHost(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    deleteAllHosts();
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    RemoteViews appWidgetViews = getAppWidgetViews(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    if (appWidgetViews != null) {
                        parcel2.writeInt(1);
                        appWidgetViews.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] appWidgetIdsForHost = getAppWidgetIdsForHost(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeIntArray(appWidgetIdsForHost);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    IntentSender intentSenderCreateAppWidgetConfigIntentSender = createAppWidgetConfigIntentSender(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (intentSenderCreateAppWidgetConfigIntentSender != null) {
                        parcel2.writeInt(1);
                        intentSenderCreateAppWidgetConfigIntentSender.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateAppWidgetIds(parcel.readString(), parcel.createIntArray(), parcel.readInt() != 0 ? RemoteViews.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateAppWidgetOptions(parcel.readString(), parcel.readInt(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    Bundle appWidgetOptions = getAppWidgetOptions(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    if (appWidgetOptions != null) {
                        parcel2.writeInt(1);
                        appWidgetOptions.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    partiallyUpdateAppWidgetIds(parcel.readString(), parcel.createIntArray(), parcel.readInt() != 0 ? RemoteViews.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateAppWidgetProvider(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? RemoteViews.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateAppWidgetProviderInfo(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyAppWidgetViewDataChanged(parcel.readString(), parcel.createIntArray(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice installedProvidersForProfile = getInstalledProvidersForProfile(parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    if (installedProvidersForProfile != null) {
                        parcel2.writeInt(1);
                        installedProvidersForProfile.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    AppWidgetProviderInfo appWidgetInfo = getAppWidgetInfo(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    if (appWidgetInfo != null) {
                        parcel2.writeInt(1);
                        appWidgetInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHasBindAppWidgetPermission = hasBindAppWidgetPermission(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zHasBindAppWidgetPermission ? 1 : 0);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    setBindAppWidgetPermission(parcel.readString(), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zBindAppWidgetId = bindAppWidgetId(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zBindAppWidgetId ? 1 : 0);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zBindRemoteViewsService = bindRemoteViewsService(parcel.readString(), parcel.readInt(), parcel.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcel) : null, IApplicationThread.Stub.asInterface(parcel.readStrongBinder()), parcel.readStrongBinder(), IServiceConnection.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zBindRemoteViewsService ? 1 : 0);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] appWidgetIds = getAppWidgetIds(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeIntArray(appWidgetIds);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsBoundWidgetPackage = isBoundWidgetPackage(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsBoundWidgetPackage ? 1 : 0);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRequestPinAppWidget = requestPinAppWidget(parcel.readString(), parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? IntentSender.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zRequestPinAppWidget ? 1 : 0);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsRequestPinAppWidgetSupported = isRequestPinAppWidgetSupported();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsRequestPinAppWidgetSupported ? 1 : 0);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    noteAppWidgetTapped(parcel.readString(), parcel.readInt());
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IAppWidgetService {
            public static IAppWidgetService sDefaultImpl;
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

            @Override // com.android.internal.appwidget.IAppWidgetService
            public ParceledListSlice startListening(IAppWidgetHost iAppWidgetHost, String str, int i, int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iAppWidgetHost != null ? iAppWidgetHost.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeIntArray(iArr);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startListening(iAppWidgetHost, str, i, iArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.appwidget.IAppWidgetService
            public void stopListening(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopListening(str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.appwidget.IAppWidgetService
            public int allocateAppWidgetId(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().allocateAppWidgetId(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.appwidget.IAppWidgetService
            public void deleteAppWidgetId(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteAppWidgetId(str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.appwidget.IAppWidgetService
            public void deleteHost(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteHost(str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.appwidget.IAppWidgetService
            public void deleteAllHosts() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteAllHosts();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.appwidget.IAppWidgetService
            public RemoteViews getAppWidgetViews(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppWidgetViews(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? RemoteViews.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.appwidget.IAppWidgetService
            public int[] getAppWidgetIdsForHost(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppWidgetIdsForHost(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.appwidget.IAppWidgetService
            public IntentSender createAppWidgetConfigIntentSender(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createAppWidgetConfigIntentSender(str, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? IntentSender.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.appwidget.IAppWidgetService
            public void updateAppWidgetIds(String str, int[] iArr, RemoteViews remoteViews) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeIntArray(iArr);
                    if (remoteViews != null) {
                        parcelObtain.writeInt(1);
                        remoteViews.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateAppWidgetIds(str, iArr, remoteViews);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.appwidget.IAppWidgetService
            public void updateAppWidgetOptions(String str, int i, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateAppWidgetOptions(str, i, bundle);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.appwidget.IAppWidgetService
            public Bundle getAppWidgetOptions(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppWidgetOptions(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.appwidget.IAppWidgetService
            public void partiallyUpdateAppWidgetIds(String str, int[] iArr, RemoteViews remoteViews) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeIntArray(iArr);
                    if (remoteViews != null) {
                        parcelObtain.writeInt(1);
                        remoteViews.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().partiallyUpdateAppWidgetIds(str, iArr, remoteViews);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.appwidget.IAppWidgetService
            public void updateAppWidgetProvider(ComponentName componentName, RemoteViews remoteViews) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (remoteViews != null) {
                        parcelObtain.writeInt(1);
                        remoteViews.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateAppWidgetProvider(componentName, remoteViews);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.appwidget.IAppWidgetService
            public void updateAppWidgetProviderInfo(ComponentName componentName, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateAppWidgetProviderInfo(componentName, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.appwidget.IAppWidgetService
            public void notifyAppWidgetViewDataChanged(String str, int[] iArr, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeIntArray(iArr);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyAppWidgetViewDataChanged(str, iArr, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.appwidget.IAppWidgetService
            public ParceledListSlice getInstalledProvidersForProfile(int i, int i2, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInstalledProvidersForProfile(i, i2, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.appwidget.IAppWidgetService
            public AppWidgetProviderInfo getAppWidgetInfo(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppWidgetInfo(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? AppWidgetProviderInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.appwidget.IAppWidgetService
            public boolean hasBindAppWidgetPermission(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasBindAppWidgetPermission(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.appwidget.IAppWidgetService
            public void setBindAppWidgetPermission(String str, int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setBindAppWidgetPermission(str, i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.appwidget.IAppWidgetService
            public boolean bindAppWidgetId(String str, int i, int i2, ComponentName componentName, Bundle bundle) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        boolean zBindAppWidgetId = Stub.getDefaultImpl().bindAppWidgetId(str, i, i2, componentName, bundle);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return zBindAppWidgetId;
                    }
                    parcelObtain2.readException();
                    boolean z = parcelObtain2.readInt() != 0;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return z;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // com.android.internal.appwidget.IAppWidgetService
            public boolean bindRemoteViewsService(String str, int i, Intent intent, IApplicationThread iApplicationThread, IBinder iBinder, IServiceConnection iServiceConnection, int i2) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (intent != null) {
                        parcelObtain.writeInt(1);
                        intent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iApplicationThread != null ? iApplicationThread.asBinder() : null);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeStrongBinder(iServiceConnection != null ? iServiceConnection.asBinder() : null);
                    parcelObtain.writeInt(i2);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        boolean zBindRemoteViewsService = Stub.getDefaultImpl().bindRemoteViewsService(str, i, intent, iApplicationThread, iBinder, iServiceConnection, i2);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return zBindRemoteViewsService;
                    }
                    parcelObtain2.readException();
                    boolean z = parcelObtain2.readInt() != 0;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return z;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // com.android.internal.appwidget.IAppWidgetService
            public int[] getAppWidgetIds(ComponentName componentName) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppWidgetIds(componentName);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.appwidget.IAppWidgetService
            public boolean isBoundWidgetPackage(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isBoundWidgetPackage(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.appwidget.IAppWidgetService
            public boolean requestPinAppWidget(String str, ComponentName componentName, Bundle bundle, IntentSender intentSender) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (intentSender != null) {
                        parcelObtain.writeInt(1);
                        intentSender.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestPinAppWidget(str, componentName, bundle, intentSender);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.appwidget.IAppWidgetService
            public boolean isRequestPinAppWidgetSupported() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isRequestPinAppWidgetSupported();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.appwidget.IAppWidgetService
            public void noteAppWidgetTapped(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(27, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().noteAppWidgetTapped(str, i);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAppWidgetService iAppWidgetService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iAppWidgetService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAppWidgetService;
            return true;
        }

        public static IAppWidgetService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
