package com.android.server.connectivity.ipmemorystore;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.IIpMemoryStore;
import android.net.ipmemorystore.Blob;
import android.net.ipmemorystore.IOnBlobRetrievedListener;
import android.net.ipmemorystore.IOnL2KeyResponseListener;
import android.net.ipmemorystore.IOnNetworkAttributesRetrievedListener;
import android.net.ipmemorystore.IOnSameL3NetworkResponseListener;
import android.net.ipmemorystore.IOnStatusAndCountListener;
import android.net.ipmemorystore.IOnStatusListener;
import android.net.ipmemorystore.NetworkAttributes;
import android.net.ipmemorystore.NetworkAttributesParcelable;
import android.net.ipmemorystore.SameL3NetworkResponse;
import android.net.ipmemorystore.Status;
import android.net.ipmemorystore.StatusParcelable;
import android.os.RemoteException;
import android.util.Log;
import com.android.server.connectivity.ipmemorystore.IpMemoryStoreDatabase;
import com.android.server.connectivity.ipmemorystore.RegularMaintenanceJobService;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public class IpMemoryStoreService extends IIpMemoryStore.Stub {
    private static final String TAG = IpMemoryStoreService.class.getSimpleName();
    final Context mContext;
    final SQLiteDatabase mDb;
    final ExecutorService mExecutor;

    protected int getDbSizeThreshold() {
        return 10485760;
    }

    @Override // android.net.IIpMemoryStore
    public String getInterfaceHash() {
        return "31826566143ef882d67fac9f24566f73df4907b4";
    }

    @Override // android.net.IIpMemoryStore
    public int getInterfaceVersion() {
        return 7;
    }

    public IpMemoryStoreService(Context context) {
        String str = TAG;
        this.mContext = context;
        IpMemoryStoreDatabase.DbHelper dbHelper = new IpMemoryStoreDatabase.DbHelper(context);
        SQLiteDatabase sQLiteDatabase = null;
        try {
            SQLiteDatabase writableDatabase = dbHelper.getWritableDatabase();
            if (writableDatabase == null) {
                Log.e(str, "Unexpected null return of getWriteableDatabase");
            }
            sQLiteDatabase = writableDatabase;
        } catch (SQLException e) {
            Log.e(str, "Can't open the Ip Memory Store database", e);
        } catch (Exception e2) {
            Log.wtf(str, "Impossible exception Ip Memory Store database", e2);
        }
        this.mDb = sQLiteDatabase;
        this.mExecutor = Executors.newSingleThreadExecutor();
        RegularMaintenanceJobService.schedule(this.mContext, this);
    }

    private StatusParcelable makeStatus(int i) {
        return new Status(i).toParcelable();
    }

    @Override // android.net.IIpMemoryStore
    public void storeNetworkAttributes(final String str, NetworkAttributesParcelable networkAttributesParcelable, final IOnStatusListener iOnStatusListener) {
        final NetworkAttributes networkAttributes = networkAttributesParcelable == null ? null : new NetworkAttributes(networkAttributesParcelable);
        this.mExecutor.execute(new Runnable() { // from class: com.android.server.connectivity.ipmemorystore.-$$Lambda$IpMemoryStoreService$D__o7MlN7G4Ah9N9Rd76bfswx_s
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$storeNetworkAttributes$0$IpMemoryStoreService(str, networkAttributes, iOnStatusListener);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$storeNetworkAttributes$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$storeNetworkAttributes$0$IpMemoryStoreService(String str, NetworkAttributes networkAttributes, IOnStatusListener iOnStatusListener) {
        try {
            int iStoreNetworkAttributesAndBlobSync = storeNetworkAttributesAndBlobSync(str, networkAttributes, null, null, null);
            if (iOnStatusListener != null) {
                iOnStatusListener.onComplete(makeStatus(iStoreNetworkAttributesAndBlobSync));
            }
        } catch (RemoteException unused) {
        }
    }

    @Override // android.net.IIpMemoryStore
    public void storeBlob(final String str, final String str2, final String str3, Blob blob, final IOnStatusListener iOnStatusListener) {
        final byte[] bArr = blob == null ? null : blob.data;
        this.mExecutor.execute(new Runnable() { // from class: com.android.server.connectivity.ipmemorystore.-$$Lambda$IpMemoryStoreService$g37dRFQvkn5qEnGTEimTUj8WdfQ
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$storeBlob$1$IpMemoryStoreService(str, str2, str3, bArr, iOnStatusListener);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$storeBlob$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$storeBlob$1$IpMemoryStoreService(String str, String str2, String str3, byte[] bArr, IOnStatusListener iOnStatusListener) {
        try {
            int iStoreNetworkAttributesAndBlobSync = storeNetworkAttributesAndBlobSync(str, null, str2, str3, bArr);
            if (iOnStatusListener != null) {
                iOnStatusListener.onComplete(makeStatus(iStoreNetworkAttributesAndBlobSync));
            }
        } catch (RemoteException unused) {
        }
    }

    private int storeNetworkAttributesAndBlobSync(String str, NetworkAttributes networkAttributes, String str2, String str3, byte[] bArr) {
        if (str == null) {
            return -2;
        }
        if (networkAttributes == null && bArr == null) {
            return -2;
        }
        if (bArr != null && (str2 == null || str3 == null)) {
            return -2;
        }
        SQLiteDatabase sQLiteDatabase = this.mDb;
        if (sQLiteDatabase == null) {
            return -3;
        }
        try {
            long expiry = IpMemoryStoreDatabase.getExpiry(sQLiteDatabase, str);
            if (expiry == -1) {
                expiry = System.currentTimeMillis();
            }
            return bArr == null ? IpMemoryStoreDatabase.storeNetworkAttributes(this.mDb, str, RelevanceUtils.bumpExpiryDate(expiry), networkAttributes) : IpMemoryStoreDatabase.storeBlob(this.mDb, str, str2, str3, bArr);
        } catch (Exception e) {
            String str4 = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Exception while storing for key {");
            sb.append(str);
            sb.append("} ; NetworkAttributes {");
            Object obj = networkAttributes;
            if (networkAttributes == null) {
                obj = "null";
            }
            sb.append(obj);
            sb.append("} ; clientId {");
            if (str2 == null) {
                str2 = "null";
            }
            sb.append(str2);
            sb.append("} ; name {");
            if (str3 == null) {
                str3 = "null";
            }
            sb.append(str3);
            sb.append("} ; data {");
            sb.append(Utils.byteArrayToString(bArr));
            sb.append("}");
            Log.e(str4, sb.toString(), e);
            return -1;
        }
    }

    @Override // android.net.IIpMemoryStore
    public void findL2Key(final NetworkAttributesParcelable networkAttributesParcelable, final IOnL2KeyResponseListener iOnL2KeyResponseListener) {
        if (iOnL2KeyResponseListener == null) {
            return;
        }
        this.mExecutor.execute(new Runnable() { // from class: com.android.server.connectivity.ipmemorystore.-$$Lambda$IpMemoryStoreService$uTiEAFhCJuFbV05gJVEPnOqKxRM
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$findL2Key$2$IpMemoryStoreService(networkAttributesParcelable, iOnL2KeyResponseListener);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$findL2Key$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$findL2Key$2$IpMemoryStoreService(NetworkAttributesParcelable networkAttributesParcelable, IOnL2KeyResponseListener iOnL2KeyResponseListener) {
        try {
            if (networkAttributesParcelable == null) {
                iOnL2KeyResponseListener.onL2KeyResponse(makeStatus(-2), null);
            } else if (this.mDb == null) {
                iOnL2KeyResponseListener.onL2KeyResponse(makeStatus(-2), null);
            } else {
                iOnL2KeyResponseListener.onL2KeyResponse(makeStatus(0), IpMemoryStoreDatabase.findClosestAttributes(this.mDb, new NetworkAttributes(networkAttributesParcelable)));
            }
        } catch (RemoteException unused) {
        }
    }

    @Override // android.net.IIpMemoryStore
    public void isSameNetwork(final String str, final String str2, final IOnSameL3NetworkResponseListener iOnSameL3NetworkResponseListener) {
        if (iOnSameL3NetworkResponseListener == null) {
            return;
        }
        this.mExecutor.execute(new Runnable() { // from class: com.android.server.connectivity.ipmemorystore.-$$Lambda$IpMemoryStoreService$r8E_mM1Sg3uIrSkqsaZn21YDRJ0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$isSameNetwork$3$IpMemoryStoreService(str, str2, iOnSameL3NetworkResponseListener);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$isSameNetwork$3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$isSameNetwork$3$IpMemoryStoreService(String str, String str2, IOnSameL3NetworkResponseListener iOnSameL3NetworkResponseListener) {
        try {
            if (str == null || str2 == null) {
                iOnSameL3NetworkResponseListener.onSameL3NetworkResponse(makeStatus(-2), null);
                return;
            }
            if (this.mDb == null) {
                iOnSameL3NetworkResponseListener.onSameL3NetworkResponse(makeStatus(-2), null);
                return;
            }
            try {
                NetworkAttributes networkAttributesRetrieveNetworkAttributes = IpMemoryStoreDatabase.retrieveNetworkAttributes(this.mDb, str);
                NetworkAttributes networkAttributesRetrieveNetworkAttributes2 = IpMemoryStoreDatabase.retrieveNetworkAttributes(this.mDb, str2);
                if (networkAttributesRetrieveNetworkAttributes != null && networkAttributesRetrieveNetworkAttributes2 != null) {
                    iOnSameL3NetworkResponseListener.onSameL3NetworkResponse(makeStatus(0), new SameL3NetworkResponse(str, str2, networkAttributesRetrieveNetworkAttributes.getNetworkGroupSamenessConfidence(networkAttributesRetrieveNetworkAttributes2)).toParcelable());
                    return;
                }
                iOnSameL3NetworkResponseListener.onSameL3NetworkResponse(makeStatus(0), new SameL3NetworkResponse(str, str2, -1.0f).toParcelable());
            } catch (Exception unused) {
                iOnSameL3NetworkResponseListener.onSameL3NetworkResponse(makeStatus(-1), null);
            }
        } catch (RemoteException unused2) {
        }
    }

    @Override // android.net.IIpMemoryStore
    public void retrieveNetworkAttributes(final String str, final IOnNetworkAttributesRetrievedListener iOnNetworkAttributesRetrievedListener) {
        if (iOnNetworkAttributesRetrievedListener == null) {
            return;
        }
        this.mExecutor.execute(new Runnable() { // from class: com.android.server.connectivity.ipmemorystore.-$$Lambda$IpMemoryStoreService$VAgKYLJkveVgUJIbqtwqZy9r5Nk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$retrieveNetworkAttributes$4$IpMemoryStoreService(str, iOnNetworkAttributesRetrievedListener);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$retrieveNetworkAttributes$4, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$retrieveNetworkAttributes$4$IpMemoryStoreService(String str, IOnNetworkAttributesRetrievedListener iOnNetworkAttributesRetrievedListener) {
        try {
            if (str == null) {
                iOnNetworkAttributesRetrievedListener.onNetworkAttributesRetrieved(makeStatus(-2), str, null);
                return;
            }
            if (this.mDb == null) {
                iOnNetworkAttributesRetrievedListener.onNetworkAttributesRetrieved(makeStatus(-3), str, null);
                return;
            }
            try {
                NetworkAttributes networkAttributesRetrieveNetworkAttributes = IpMemoryStoreDatabase.retrieveNetworkAttributes(this.mDb, str);
                iOnNetworkAttributesRetrievedListener.onNetworkAttributesRetrieved(makeStatus(0), str, networkAttributesRetrieveNetworkAttributes == null ? null : networkAttributesRetrieveNetworkAttributes.toParcelable());
            } catch (Exception unused) {
                iOnNetworkAttributesRetrievedListener.onNetworkAttributesRetrieved(makeStatus(-1), str, null);
            }
        } catch (RemoteException unused2) {
        }
    }

    @Override // android.net.IIpMemoryStore
    public void retrieveBlob(final String str, final String str2, final String str3, final IOnBlobRetrievedListener iOnBlobRetrievedListener) {
        if (iOnBlobRetrievedListener == null) {
            return;
        }
        this.mExecutor.execute(new Runnable() { // from class: com.android.server.connectivity.ipmemorystore.-$$Lambda$IpMemoryStoreService$2zXvadnWYjrsixRPJ8GeHSEmox4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$retrieveBlob$5$IpMemoryStoreService(str, iOnBlobRetrievedListener, str3, str2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$retrieveBlob$5, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$retrieveBlob$5$IpMemoryStoreService(String str, IOnBlobRetrievedListener iOnBlobRetrievedListener, String str2, String str3) {
        try {
            if (str == null) {
                iOnBlobRetrievedListener.onBlobRetrieved(makeStatus(-2), str, str2, null);
                return;
            }
            if (this.mDb == null) {
                iOnBlobRetrievedListener.onBlobRetrieved(makeStatus(-3), str, str2, null);
                return;
            }
            try {
                Blob blob = new Blob();
                blob.data = IpMemoryStoreDatabase.retrieveBlob(this.mDb, str, str3, str2);
                iOnBlobRetrievedListener.onBlobRetrieved(makeStatus(0), str, str2, blob);
            } catch (Exception unused) {
                iOnBlobRetrievedListener.onBlobRetrieved(makeStatus(-1), str, str2, null);
            }
        } catch (RemoteException unused2) {
        }
    }

    @Override // android.net.IIpMemoryStore
    public void delete(final String str, final boolean z, final IOnStatusAndCountListener iOnStatusAndCountListener) {
        this.mExecutor.execute(new Runnable() { // from class: com.android.server.connectivity.ipmemorystore.-$$Lambda$IpMemoryStoreService$uN_kWipBbKRNYAXR8JaEiVCa_Qw
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$delete$6$IpMemoryStoreService(str, z, iOnStatusAndCountListener);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$delete$6, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$delete$6$IpMemoryStoreService(String str, boolean z, IOnStatusAndCountListener iOnStatusAndCountListener) {
        try {
            StatusAndCount statusAndCountDelete = IpMemoryStoreDatabase.delete(this.mDb, str, z);
            if (iOnStatusAndCountListener != null) {
                iOnStatusAndCountListener.onComplete(makeStatus(statusAndCountDelete.status), statusAndCountDelete.count);
            }
        } catch (RemoteException unused) {
        }
    }

    @Override // android.net.IIpMemoryStore
    public void deleteCluster(final String str, final boolean z, final IOnStatusAndCountListener iOnStatusAndCountListener) {
        this.mExecutor.execute(new Runnable() { // from class: com.android.server.connectivity.ipmemorystore.-$$Lambda$IpMemoryStoreService$Cl84pX_qIXu5M-ppFVyLQ76ZHeM
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$deleteCluster$7$IpMemoryStoreService(str, z, iOnStatusAndCountListener);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$deleteCluster$7, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$deleteCluster$7$IpMemoryStoreService(String str, boolean z, IOnStatusAndCountListener iOnStatusAndCountListener) {
        try {
            StatusAndCount statusAndCountDeleteCluster = IpMemoryStoreDatabase.deleteCluster(this.mDb, str, z);
            if (iOnStatusAndCountListener != null) {
                iOnStatusAndCountListener.onComplete(makeStatus(statusAndCountDeleteCluster.status), statusAndCountDeleteCluster.count);
            }
        } catch (RemoteException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$factoryReset$8, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$factoryReset$8$IpMemoryStoreService() {
        IpMemoryStoreDatabase.wipeDataUponNetworkReset(this.mDb);
    }

    @Override // android.net.IIpMemoryStore
    public void factoryReset() {
        this.mExecutor.execute(new Runnable() { // from class: com.android.server.connectivity.ipmemorystore.-$$Lambda$IpMemoryStoreService$DpANC5VvOV1GuxM0SsyE_FH3noA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$factoryReset$8$IpMemoryStoreService();
            }
        });
    }

    private long getDbSize() {
        try {
            return new File(this.mDb.getPath()).length();
        } catch (SecurityException e) {
            Log.e(TAG, "Read db size access deny.", e);
            return 0L;
        }
    }

    boolean isDbSizeOverThreshold() {
        return getDbSize() > ((long) getDbSizeThreshold());
    }

    void fullMaintenance(final IOnStatusListener iOnStatusListener, final RegularMaintenanceJobService.InterruptMaintenance interruptMaintenance) {
        this.mExecutor.execute(new Runnable() { // from class: com.android.server.connectivity.ipmemorystore.-$$Lambda$IpMemoryStoreService$aeyHrnDXK4XRWCIZrK5D_8KpeKM
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$fullMaintenance$9$IpMemoryStoreService(iOnStatusListener, interruptMaintenance);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$fullMaintenance$9, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$fullMaintenance$9$IpMemoryStoreService(IOnStatusListener iOnStatusListener, RegularMaintenanceJobService.InterruptMaintenance interruptMaintenance) {
        try {
            if (this.mDb == null) {
                iOnStatusListener.onComplete(makeStatus(-3));
                return;
            }
            if (checkForInterrupt(iOnStatusListener, interruptMaintenance)) {
                return;
            }
            int iDropAllExpiredRecords = IpMemoryStoreDatabase.dropAllExpiredRecords(this.mDb);
            if (checkForInterrupt(iOnStatusListener, interruptMaintenance)) {
                return;
            }
            for (int i = 0; isDbSizeOverThreshold() && i < 500; i++) {
                if (checkForInterrupt(iOnStatusListener, interruptMaintenance)) {
                    return;
                }
                int totalRecordNumber = IpMemoryStoreDatabase.getTotalRecordNumber(this.mDb);
                long dbSize = getDbSize();
                iDropAllExpiredRecords = IpMemoryStoreDatabase.dropNumberOfRecords(this.mDb, Math.max((int) (totalRecordNumber * (dbSize == 0 ? 0.0f : (dbSize - ((long) getDbSizeThreshold())) / dbSize)), 5));
                if (checkForInterrupt(iOnStatusListener, interruptMaintenance)) {
                    return;
                }
            }
            iOnStatusListener.onComplete(makeStatus(iDropAllExpiredRecords));
        } catch (RemoteException unused) {
        }
    }

    private boolean checkForInterrupt(IOnStatusListener iOnStatusListener, RegularMaintenanceJobService.InterruptMaintenance interruptMaintenance) throws RemoteException {
        if (!interruptMaintenance.isInterrupted()) {
            return false;
        }
        iOnStatusListener.onComplete(makeStatus(-1000000001));
        return true;
    }
}
