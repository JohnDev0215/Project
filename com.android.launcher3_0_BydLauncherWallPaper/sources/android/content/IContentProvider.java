package android.content;

import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ICancellationSignal;
import android.os.IInterface;
import android.os.ParcelFileDescriptor;
import android.os.RemoteCallback;
import android.os.RemoteException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public interface IContentProvider extends IInterface {
    public static final int APPLY_BATCH_TRANSACTION = 20;
    public static final int BULK_INSERT_TRANSACTION = 13;
    public static final int CALL_TRANSACTION = 21;
    public static final int CANONICALIZE_ASYNC_TRANSACTION = 30;
    public static final int CANONICALIZE_TRANSACTION = 25;
    public static final int CHECK_URI_PERMISSION_TRANSACTION = 28;
    public static final int CREATE_CANCELATION_SIGNAL_TRANSACTION = 24;
    public static final int DELETE_TRANSACTION = 4;
    public static final int GET_STREAM_TYPES_TRANSACTION = 22;
    public static final int GET_TYPE_ASYNC_TRANSACTION = 29;
    public static final int GET_TYPE_TRANSACTION = 2;
    public static final int INSERT_TRANSACTION = 3;
    public static final int OPEN_ASSET_FILE_TRANSACTION = 15;
    public static final int OPEN_FILE_TRANSACTION = 14;
    public static final int OPEN_TYPED_ASSET_FILE_TRANSACTION = 23;
    public static final int QUERY_TRANSACTION = 1;
    public static final int REFRESH_TRANSACTION = 27;
    public static final int UNCANONICALIZE_TRANSACTION = 26;
    public static final int UPDATE_TRANSACTION = 10;
    public static final String descriptor = "android.content.IContentProvider";

    ContentProviderResult[] applyBatch(String str, String str2, String str3, ArrayList<ContentProviderOperation> arrayList) throws RemoteException, OperationApplicationException;

    int bulkInsert(String str, String str2, Uri uri, ContentValues[] contentValuesArr) throws RemoteException;

    Bundle call(String str, String str2, String str3, String str4, String str5, Bundle bundle) throws RemoteException;

    Uri canonicalize(String str, String str2, Uri uri) throws RemoteException;

    void canonicalizeAsync(String str, String str2, Uri uri, RemoteCallback remoteCallback) throws RemoteException;

    int checkUriPermission(String str, String str2, Uri uri, int i, int i2) throws RemoteException;

    ICancellationSignal createCancellationSignal() throws RemoteException;

    int delete(String str, String str2, Uri uri, Bundle bundle) throws RemoteException;

    String[] getStreamTypes(Uri uri, String str) throws RemoteException;

    String getType(Uri uri) throws RemoteException;

    void getTypeAsync(Uri uri, RemoteCallback remoteCallback) throws RemoteException;

    Uri insert(String str, String str2, Uri uri, ContentValues contentValues, Bundle bundle) throws RemoteException;

    AssetFileDescriptor openAssetFile(String str, String str2, Uri uri, String str3, ICancellationSignal iCancellationSignal) throws RemoteException, FileNotFoundException;

    ParcelFileDescriptor openFile(String str, String str2, Uri uri, String str3, ICancellationSignal iCancellationSignal, IBinder iBinder) throws RemoteException, FileNotFoundException;

    AssetFileDescriptor openTypedAssetFile(String str, String str2, Uri uri, String str3, Bundle bundle, ICancellationSignal iCancellationSignal) throws RemoteException, FileNotFoundException;

    Cursor query(String str, String str2, Uri uri, String[] strArr, Bundle bundle, ICancellationSignal iCancellationSignal) throws RemoteException;

    boolean refresh(String str, String str2, Uri uri, Bundle bundle, ICancellationSignal iCancellationSignal) throws RemoteException;

    Uri uncanonicalize(String str, String str2, Uri uri) throws RemoteException;

    int update(String str, String str2, Uri uri, ContentValues contentValues, Bundle bundle) throws RemoteException;

    @Deprecated
    default Uri insert(String str, Uri uri, ContentValues contentValues) throws RemoteException {
        return insert(str, null, uri, contentValues, null);
    }

    @Deprecated
    default int bulkInsert(String str, Uri uri, ContentValues[] contentValuesArr) throws RemoteException {
        return bulkInsert(str, null, uri, contentValuesArr);
    }

    @Deprecated
    default int delete(String str, Uri uri, String str2, String[] strArr) throws RemoteException {
        return delete(str, (String) null, uri, ContentResolver.createSqlQueryBundle(str2, strArr));
    }

    @Deprecated
    default int update(String str, Uri uri, ContentValues contentValues, String str2, String[] strArr) throws RemoteException {
        return update(str, (String) null, uri, contentValues, ContentResolver.createSqlQueryBundle(str2, strArr));
    }

    @Deprecated
    default Bundle call(String str, String str2, String str3, Bundle bundle) throws RemoteException {
        return call(str, null, "unknown", str2, str3, bundle);
    }
}
