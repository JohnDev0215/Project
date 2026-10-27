package android.content;

import android.Manifest;
import android.annotation.SystemApi;
import android.app.AppOpsManager;
import android.content.pm.PathPermission;
import android.content.pm.ProviderInfo;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.media.AudioSystem;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.IBinder;
import android.os.ICancellationSignal;
import android.os.ParcelFileDescriptor;
import android.os.ParcelableException;
import android.os.Process;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.os.Trace;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class ContentProvider implements ContentInterface, ComponentCallbacks2 {
    private static final String TAG = "ContentProvider";
    private String[] mAuthorities;
    private String mAuthority;
    private ThreadLocal<Pair<String, String>> mCallingPackage;
    private Context mContext;
    private boolean mExported;
    private int mMyUid;
    private boolean mNoPerms;
    private PathPermission[] mPathPermissions;
    private String mReadPermission;
    private boolean mSingleUser;
    private Transport mTransport;
    private String mWritePermission;

    public interface PipeDataWriter<T> {
        void writeDataToPipe(ParcelFileDescriptor parcelFileDescriptor, Uri uri, String str, Bundle bundle, T t);
    }

    public Bundle call(String str, String str2, Bundle bundle) {
        return null;
    }

    @Override // android.content.ContentInterface
    public Uri canonicalize(Uri uri) {
        return null;
    }

    @Override // android.content.ContentInterface
    @SystemApi
    public int checkUriPermission(Uri uri, int i, int i2) {
        return -1;
    }

    public abstract int delete(Uri uri, String str, String[] strArr);

    @Override // android.content.ContentInterface
    public String[] getStreamTypes(Uri uri, String str) {
        return null;
    }

    @Override // android.content.ContentInterface
    public abstract String getType(Uri uri);

    public abstract Uri insert(Uri uri, ContentValues contentValues);

    protected boolean isTemporary() {
        return false;
    }

    public void onCallingPackageChanged() {
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
    }

    public abstract boolean onCreate();

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
    }

    @Override // android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
    }

    public abstract Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2);

    @Override // android.content.ContentInterface
    public boolean refresh(Uri uri, Bundle bundle, CancellationSignal cancellationSignal) {
        return false;
    }

    @Override // android.content.ContentInterface
    public Uri uncanonicalize(Uri uri) {
        return uri;
    }

    public abstract int update(Uri uri, ContentValues contentValues, String str, String[] strArr);

    public ContentProvider() {
        this.mContext = null;
        this.mTransport = new Transport();
    }

    public ContentProvider(Context context, String str, String str2, PathPermission[] pathPermissionArr) {
        this.mContext = null;
        this.mTransport = new Transport();
        this.mContext = context;
        this.mReadPermission = str;
        this.mWritePermission = str2;
        this.mPathPermissions = pathPermissionArr;
    }

    public static ContentProvider coerceToLocalContentProvider(IContentProvider iContentProvider) {
        if (iContentProvider instanceof Transport) {
            return ((Transport) iContentProvider).getContentProvider();
        }
        return null;
    }

    class Transport extends ContentProviderNative {
        volatile ContentInterface mInterface;
        volatile AppOpsManager mAppOpsManager = null;
        volatile int mReadOp = -1;
        volatile int mWriteOp = -1;

        Transport() {
            this.mInterface = ContentProvider.this;
        }

        ContentProvider getContentProvider() {
            return ContentProvider.this;
        }

        @Override // android.content.ContentProviderNative
        public String getProviderName() {
            return getContentProvider().getClass().getName();
        }

        @Override // android.content.IContentProvider
        public Cursor query(String str, String str2, Uri uri, String[] strArr, Bundle bundle, ICancellationSignal iCancellationSignal) {
            Uri uriMaybeGetUriWithoutUserId = ContentProvider.this.maybeGetUriWithoutUserId(ContentProvider.this.validateIncomingUri(uri));
            if (enforceReadPermission(str, str2, uriMaybeGetUriWithoutUserId, null) == 0) {
                Trace.traceBegin(1048576L, "query");
                Pair callingPackage = ContentProvider.this.setCallingPackage(new Pair(str, str2));
                try {
                    try {
                        return this.mInterface.query(uriMaybeGetUriWithoutUserId, strArr, bundle, CancellationSignal.fromTransport(iCancellationSignal));
                    } catch (RemoteException e) {
                        throw e.rethrowAsRuntimeException();
                    }
                } finally {
                    ContentProvider.this.setCallingPackage(callingPackage);
                    Trace.traceEnd(1048576L);
                }
            }
            if (strArr == null) {
                Pair callingPackage2 = ContentProvider.this.setCallingPackage(new Pair(str, str2));
                try {
                    try {
                        Cursor cursorQuery = this.mInterface.query(uriMaybeGetUriWithoutUserId, strArr, bundle, CancellationSignal.fromTransport(iCancellationSignal));
                        if (cursorQuery == null) {
                            return null;
                        }
                        return new MatrixCursor(cursorQuery.getColumnNames(), 0);
                    } catch (RemoteException e2) {
                        throw e2.rethrowAsRuntimeException();
                    }
                } finally {
                    ContentProvider.this.setCallingPackage(callingPackage2);
                }
            }
            return new MatrixCursor(strArr, 0);
        }

        @Override // android.content.IContentProvider
        public String getType(Uri uri) {
            Uri uriMaybeGetUriWithoutUserId = ContentProvider.this.maybeGetUriWithoutUserId(ContentProvider.this.validateIncomingUri(uri));
            Trace.traceBegin(1048576L, "getType");
            try {
                try {
                    return this.mInterface.getType(uriMaybeGetUriWithoutUserId);
                } catch (RemoteException e) {
                    throw e.rethrowAsRuntimeException();
                }
            } finally {
                Trace.traceEnd(1048576L);
            }
        }

        @Override // android.content.IContentProvider
        public void getTypeAsync(Uri uri, RemoteCallback remoteCallback) {
            Bundle bundle = new Bundle();
            try {
                bundle.putString("result", getType(uri));
            } catch (Exception e) {
                bundle.putParcelable("error", new ParcelableException(e));
            }
            remoteCallback.sendResult(bundle);
        }

        @Override // android.content.IContentProvider
        public Uri insert(String str, String str2, Uri uri, ContentValues contentValues, Bundle bundle) {
            Uri uriValidateIncomingUri = ContentProvider.this.validateIncomingUri(uri);
            int userIdFromUri = ContentProvider.getUserIdFromUri(uriValidateIncomingUri);
            Uri uriMaybeGetUriWithoutUserId = ContentProvider.this.maybeGetUriWithoutUserId(uriValidateIncomingUri);
            if (enforceWritePermission(str, str2, uriMaybeGetUriWithoutUserId, null) != 0) {
                Pair callingPackage = ContentProvider.this.setCallingPackage(new Pair(str, str2));
                try {
                    return ContentProvider.this.rejectInsert(uriMaybeGetUriWithoutUserId, contentValues);
                } finally {
                    ContentProvider.this.setCallingPackage(callingPackage);
                }
            }
            Trace.traceBegin(1048576L, "insert");
            Pair callingPackage2 = ContentProvider.this.setCallingPackage(new Pair(str, str2));
            try {
                try {
                    return ContentProvider.maybeAddUserId(this.mInterface.insert(uriMaybeGetUriWithoutUserId, contentValues, bundle), userIdFromUri);
                } catch (RemoteException e) {
                    throw e.rethrowAsRuntimeException();
                }
            } finally {
                ContentProvider.this.setCallingPackage(callingPackage2);
                Trace.traceEnd(1048576L);
            }
        }

        @Override // android.content.IContentProvider
        public int bulkInsert(String str, String str2, Uri uri, ContentValues[] contentValuesArr) {
            Uri uriMaybeGetUriWithoutUserId = ContentProvider.this.maybeGetUriWithoutUserId(ContentProvider.this.validateIncomingUri(uri));
            if (enforceWritePermission(str, str2, uriMaybeGetUriWithoutUserId, null) != 0) {
                return 0;
            }
            Trace.traceBegin(1048576L, "bulkInsert");
            Pair callingPackage = ContentProvider.this.setCallingPackage(new Pair(str, str2));
            try {
                try {
                    return this.mInterface.bulkInsert(uriMaybeGetUriWithoutUserId, contentValuesArr);
                } catch (RemoteException e) {
                    throw e.rethrowAsRuntimeException();
                }
            } finally {
                ContentProvider.this.setCallingPackage(callingPackage);
                Trace.traceEnd(1048576L);
            }
        }

        @Override // android.content.IContentProvider
        public ContentProviderResult[] applyBatch(String str, String str2, String str3, ArrayList<ContentProviderOperation> arrayList) throws OperationApplicationException {
            ContentProvider.this.validateIncomingAuthority(str3);
            int size = arrayList.size();
            int[] iArr = new int[size];
            for (int i = 0; i < size; i++) {
                ContentProviderOperation contentProviderOperation = arrayList.get(i);
                Uri uri = contentProviderOperation.getUri();
                iArr[i] = ContentProvider.getUserIdFromUri(uri);
                Uri uriMaybeGetUriWithoutUserId = ContentProvider.this.maybeGetUriWithoutUserId(ContentProvider.this.validateIncomingUri(uri));
                if (!Objects.equals(contentProviderOperation.getUri(), uriMaybeGetUriWithoutUserId)) {
                    ContentProviderOperation contentProviderOperation2 = new ContentProviderOperation(contentProviderOperation, uriMaybeGetUriWithoutUserId);
                    arrayList.set(i, contentProviderOperation2);
                    contentProviderOperation = contentProviderOperation2;
                }
                if (contentProviderOperation.isReadOperation() && enforceReadPermission(str, str2, uriMaybeGetUriWithoutUserId, null) != 0) {
                    throw new OperationApplicationException("App op not allowed", 0);
                }
                if (contentProviderOperation.isWriteOperation() && enforceWritePermission(str, str2, uriMaybeGetUriWithoutUserId, null) != 0) {
                    throw new OperationApplicationException("App op not allowed", 0);
                }
            }
            Trace.traceBegin(1048576L, "applyBatch");
            Pair callingPackage = ContentProvider.this.setCallingPackage(new Pair(str, str2));
            try {
                try {
                    ContentProviderResult[] contentProviderResultArrApplyBatch = this.mInterface.applyBatch(str3, arrayList);
                    if (contentProviderResultArrApplyBatch != null) {
                        for (int i2 = 0; i2 < contentProviderResultArrApplyBatch.length; i2++) {
                            if (iArr[i2] != -2) {
                                contentProviderResultArrApplyBatch[i2] = new ContentProviderResult(contentProviderResultArrApplyBatch[i2], iArr[i2]);
                            }
                        }
                    }
                    return contentProviderResultArrApplyBatch;
                } catch (RemoteException e) {
                    throw e.rethrowAsRuntimeException();
                }
            } finally {
                ContentProvider.this.setCallingPackage(callingPackage);
                Trace.traceEnd(1048576L);
            }
        }

        @Override // android.content.IContentProvider
        public int delete(String str, String str2, Uri uri, Bundle bundle) {
            Uri uriMaybeGetUriWithoutUserId = ContentProvider.this.maybeGetUriWithoutUserId(ContentProvider.this.validateIncomingUri(uri));
            if (enforceWritePermission(str, str2, uriMaybeGetUriWithoutUserId, null) != 0) {
                return 0;
            }
            Trace.traceBegin(1048576L, "delete");
            Pair callingPackage = ContentProvider.this.setCallingPackage(new Pair(str, str2));
            try {
                try {
                    return this.mInterface.delete(uriMaybeGetUriWithoutUserId, bundle);
                } catch (RemoteException e) {
                    throw e.rethrowAsRuntimeException();
                }
            } finally {
                ContentProvider.this.setCallingPackage(callingPackage);
                Trace.traceEnd(1048576L);
            }
        }

        @Override // android.content.IContentProvider
        public int update(String str, String str2, Uri uri, ContentValues contentValues, Bundle bundle) {
            Uri uriMaybeGetUriWithoutUserId = ContentProvider.this.maybeGetUriWithoutUserId(ContentProvider.this.validateIncomingUri(uri));
            if (enforceWritePermission(str, str2, uriMaybeGetUriWithoutUserId, null) != 0) {
                return 0;
            }
            Trace.traceBegin(1048576L, "update");
            Pair callingPackage = ContentProvider.this.setCallingPackage(new Pair(str, str2));
            try {
                try {
                    return this.mInterface.update(uriMaybeGetUriWithoutUserId, contentValues, bundle);
                } catch (RemoteException e) {
                    throw e.rethrowAsRuntimeException();
                }
            } finally {
                ContentProvider.this.setCallingPackage(callingPackage);
                Trace.traceEnd(1048576L);
            }
        }

        @Override // android.content.IContentProvider
        public ParcelFileDescriptor openFile(String str, String str2, Uri uri, String str3, ICancellationSignal iCancellationSignal, IBinder iBinder) throws FileNotFoundException {
            Uri uriMaybeGetUriWithoutUserId = ContentProvider.this.maybeGetUriWithoutUserId(ContentProvider.this.validateIncomingUri(uri));
            enforceFilePermission(str, str2, uriMaybeGetUriWithoutUserId, str3, iBinder);
            Trace.traceBegin(1048576L, "openFile");
            Pair callingPackage = ContentProvider.this.setCallingPackage(new Pair(str, str2));
            try {
                try {
                    return this.mInterface.openFile(uriMaybeGetUriWithoutUserId, str3, CancellationSignal.fromTransport(iCancellationSignal));
                } catch (RemoteException e) {
                    throw e.rethrowAsRuntimeException();
                }
            } finally {
                ContentProvider.this.setCallingPackage(callingPackage);
                Trace.traceEnd(1048576L);
            }
        }

        @Override // android.content.IContentProvider
        public AssetFileDescriptor openAssetFile(String str, String str2, Uri uri, String str3, ICancellationSignal iCancellationSignal) throws FileNotFoundException {
            Uri uriMaybeGetUriWithoutUserId = ContentProvider.this.maybeGetUriWithoutUserId(ContentProvider.this.validateIncomingUri(uri));
            enforceFilePermission(str, str2, uriMaybeGetUriWithoutUserId, str3, null);
            Trace.traceBegin(1048576L, "openAssetFile");
            Pair callingPackage = ContentProvider.this.setCallingPackage(new Pair(str, str2));
            try {
                try {
                    return this.mInterface.openAssetFile(uriMaybeGetUriWithoutUserId, str3, CancellationSignal.fromTransport(iCancellationSignal));
                } catch (RemoteException e) {
                    throw e.rethrowAsRuntimeException();
                }
            } finally {
                ContentProvider.this.setCallingPackage(callingPackage);
                Trace.traceEnd(1048576L);
            }
        }

        @Override // android.content.IContentProvider
        public Bundle call(String str, String str2, String str3, String str4, String str5, Bundle bundle) {
            ContentProvider.this.validateIncomingAuthority(str3);
            Bundle.setDefusable(bundle, true);
            Trace.traceBegin(1048576L, "call");
            Pair callingPackage = ContentProvider.this.setCallingPackage(new Pair(str, str2));
            try {
                try {
                    return this.mInterface.call(str3, str4, str5, bundle);
                } catch (RemoteException e) {
                    throw e.rethrowAsRuntimeException();
                }
            } finally {
                ContentProvider.this.setCallingPackage(callingPackage);
                Trace.traceEnd(1048576L);
            }
        }

        @Override // android.content.IContentProvider
        public String[] getStreamTypes(Uri uri, String str) {
            Uri uriMaybeGetUriWithoutUserId = ContentProvider.this.maybeGetUriWithoutUserId(ContentProvider.this.validateIncomingUri(uri));
            Trace.traceBegin(1048576L, "getStreamTypes");
            try {
                try {
                    return this.mInterface.getStreamTypes(uriMaybeGetUriWithoutUserId, str);
                } catch (RemoteException e) {
                    throw e.rethrowAsRuntimeException();
                }
            } finally {
                Trace.traceEnd(1048576L);
            }
        }

        @Override // android.content.IContentProvider
        public AssetFileDescriptor openTypedAssetFile(String str, String str2, Uri uri, String str3, Bundle bundle, ICancellationSignal iCancellationSignal) throws FileNotFoundException {
            Bundle.setDefusable(bundle, true);
            Uri uriMaybeGetUriWithoutUserId = ContentProvider.this.maybeGetUriWithoutUserId(ContentProvider.this.validateIncomingUri(uri));
            enforceFilePermission(str, str2, uriMaybeGetUriWithoutUserId, "r", null);
            Trace.traceBegin(1048576L, "openTypedAssetFile");
            Pair callingPackage = ContentProvider.this.setCallingPackage(new Pair(str, str2));
            try {
                try {
                    return this.mInterface.openTypedAssetFile(uriMaybeGetUriWithoutUserId, str3, bundle, CancellationSignal.fromTransport(iCancellationSignal));
                } catch (RemoteException e) {
                    throw e.rethrowAsRuntimeException();
                }
            } finally {
                ContentProvider.this.setCallingPackage(callingPackage);
                Trace.traceEnd(1048576L);
            }
        }

        @Override // android.content.IContentProvider
        public ICancellationSignal createCancellationSignal() {
            return CancellationSignal.createTransport();
        }

        @Override // android.content.IContentProvider
        public Uri canonicalize(String str, String str2, Uri uri) {
            Uri uriValidateIncomingUri = ContentProvider.this.validateIncomingUri(uri);
            int userIdFromUri = ContentProvider.getUserIdFromUri(uriValidateIncomingUri);
            Uri uriWithoutUserId = ContentProvider.getUriWithoutUserId(uriValidateIncomingUri);
            if (enforceReadPermission(str, str2, uriWithoutUserId, null) != 0) {
                return null;
            }
            Trace.traceBegin(1048576L, "canonicalize");
            Pair callingPackage = ContentProvider.this.setCallingPackage(new Pair(str, str2));
            try {
                try {
                    return ContentProvider.maybeAddUserId(this.mInterface.canonicalize(uriWithoutUserId), userIdFromUri);
                } catch (RemoteException e) {
                    throw e.rethrowAsRuntimeException();
                }
            } finally {
                ContentProvider.this.setCallingPackage(callingPackage);
                Trace.traceEnd(1048576L);
            }
        }

        @Override // android.content.IContentProvider
        public void canonicalizeAsync(String str, String str2, Uri uri, RemoteCallback remoteCallback) {
            Bundle bundle = new Bundle();
            try {
                bundle.putParcelable("result", canonicalize(str, str2, uri));
            } catch (Exception e) {
                bundle.putParcelable("error", new ParcelableException(e));
            }
            remoteCallback.sendResult(bundle);
        }

        @Override // android.content.IContentProvider
        public Uri uncanonicalize(String str, String str2, Uri uri) {
            Uri uriValidateIncomingUri = ContentProvider.this.validateIncomingUri(uri);
            int userIdFromUri = ContentProvider.getUserIdFromUri(uriValidateIncomingUri);
            Uri uriWithoutUserId = ContentProvider.getUriWithoutUserId(uriValidateIncomingUri);
            if (enforceReadPermission(str, str2, uriWithoutUserId, null) != 0) {
                return null;
            }
            Trace.traceBegin(1048576L, "uncanonicalize");
            Pair callingPackage = ContentProvider.this.setCallingPackage(new Pair(str, str2));
            try {
                try {
                    return ContentProvider.maybeAddUserId(this.mInterface.uncanonicalize(uriWithoutUserId), userIdFromUri);
                } catch (RemoteException e) {
                    throw e.rethrowAsRuntimeException();
                }
            } finally {
                ContentProvider.this.setCallingPackage(callingPackage);
                Trace.traceEnd(1048576L);
            }
        }

        @Override // android.content.IContentProvider
        public boolean refresh(String str, String str2, Uri uri, Bundle bundle, ICancellationSignal iCancellationSignal) throws RemoteException {
            Uri uriWithoutUserId = ContentProvider.getUriWithoutUserId(ContentProvider.this.validateIncomingUri(uri));
            if (enforceReadPermission(str, str2, uriWithoutUserId, null) != 0) {
                return false;
            }
            Trace.traceBegin(1048576L, "refresh");
            Pair callingPackage = ContentProvider.this.setCallingPackage(new Pair(str, str2));
            try {
                return this.mInterface.refresh(uriWithoutUserId, bundle, CancellationSignal.fromTransport(iCancellationSignal));
            } finally {
                ContentProvider.this.setCallingPackage(callingPackage);
                Trace.traceEnd(1048576L);
            }
        }

        @Override // android.content.IContentProvider
        public int checkUriPermission(String str, String str2, Uri uri, int i, int i2) {
            Uri uriMaybeGetUriWithoutUserId = ContentProvider.this.maybeGetUriWithoutUserId(ContentProvider.this.validateIncomingUri(uri));
            Trace.traceBegin(1048576L, "checkUriPermission");
            Pair callingPackage = ContentProvider.this.setCallingPackage(new Pair(str, str2));
            try {
                try {
                    return this.mInterface.checkUriPermission(uriMaybeGetUriWithoutUserId, i, i2);
                } catch (RemoteException e) {
                    throw e.rethrowAsRuntimeException();
                }
            } finally {
                ContentProvider.this.setCallingPackage(callingPackage);
                Trace.traceEnd(1048576L);
            }
        }

        private void enforceFilePermission(String str, String str2, Uri uri, String str3, IBinder iBinder) throws SecurityException, FileNotFoundException {
            if (str3 != null && str3.indexOf(119) != -1) {
                if (enforceWritePermission(str, str2, uri, iBinder) != 0) {
                    throw new FileNotFoundException("App op not allowed");
                }
            } else if (enforceReadPermission(str, str2, uri, iBinder) != 0) {
                throw new FileNotFoundException("App op not allowed");
            }
        }

        private int enforceReadPermission(String str, String str2, Uri uri, IBinder iBinder) throws SecurityException {
            int iEnforceReadPermissionInner = ContentProvider.this.enforceReadPermissionInner(uri, str, str2, iBinder);
            return iEnforceReadPermissionInner != 0 ? iEnforceReadPermissionInner : noteProxyOp(str, str2, this.mReadOp);
        }

        private int enforceWritePermission(String str, String str2, Uri uri, IBinder iBinder) throws SecurityException {
            int iEnforceWritePermissionInner = ContentProvider.this.enforceWritePermissionInner(uri, str, str2, iBinder);
            return iEnforceWritePermissionInner != 0 ? iEnforceWritePermissionInner : noteProxyOp(str, str2, this.mWriteOp);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int noteProxyOp(String str, String str2, int i) {
            if (i == -1) {
                return 0;
            }
            int iNoteProxyOp = this.mAppOpsManager.noteProxyOp(i, str, Binder.getCallingUid(), str2, (String) null);
            if (iNoteProxyOp == 3) {
                return 1;
            }
            return iNoteProxyOp;
        }
    }

    boolean checkUser(int i, int i2, Context context) {
        return UserHandle.getUserId(i2) == context.getUserId() || this.mSingleUser || context.checkPermission(Manifest.permission.INTERACT_ACROSS_USERS, i, i2) == 0 || context.checkPermission(Manifest.permission.INTERACT_ACROSS_USERS_FULL, i, i2) == 0;
    }

    private int checkPermissionAndAppOp(String str, String str2, String str3, IBinder iBinder) {
        if (getContext().checkPermission(str, Binder.getCallingPid(), Binder.getCallingUid(), iBinder) != 0) {
            return 2;
        }
        return this.mTransport.noteProxyOp(str2, str3, AppOpsManager.permissionToOpCode(str));
    }

    protected int enforceReadPermissionInner(Uri uri, String str, String str2, IBinder iBinder) throws SecurityException {
        String str3;
        int iMax;
        String str4;
        Context context = getContext();
        int callingPid = Binder.getCallingPid();
        int callingUid = Binder.getCallingUid();
        if (UserHandle.isSameApp(callingUid, this.mMyUid)) {
            return 0;
        }
        String str5 = null;
        if (this.mExported && checkUser(callingPid, callingUid, context)) {
            String readPermission = getReadPermission();
            if (readPermission != null) {
                int iCheckPermissionAndAppOp = checkPermissionAndAppOp(readPermission, str, str2, iBinder);
                if (iCheckPermissionAndAppOp == 0) {
                    return 0;
                }
                iMax = Math.max(0, iCheckPermissionAndAppOp);
                str5 = readPermission;
            } else {
                iMax = 0;
            }
            boolean z = readPermission == null;
            PathPermission[] pathPermissions = getPathPermissions();
            if (pathPermissions != null) {
                String path = uri.getPath();
                for (PathPermission pathPermission : pathPermissions) {
                    boolean z2 = z;
                    String readPermission2 = pathPermission.getReadPermission();
                    if (readPermission2 == null || !pathPermission.match(path)) {
                        z = z2;
                    } else {
                        int iCheckPermissionAndAppOp2 = checkPermissionAndAppOp(readPermission2, str, str2, iBinder);
                        if (iCheckPermissionAndAppOp2 == 0) {
                            return 0;
                        }
                        iMax = Math.max(iMax, iCheckPermissionAndAppOp2);
                        str5 = readPermission2;
                        z = false;
                    }
                }
            }
            if (z) {
                return 0;
            }
            str3 = str5;
        } else {
            str3 = null;
            iMax = 0;
        }
        if (context.checkUriPermission((!this.mSingleUser || UserHandle.isSameUser(this.mMyUid, callingUid)) ? uri : maybeAddUserId(uri, UserHandle.getUserId(callingUid)), callingPid, callingUid, 1, iBinder) == 0) {
            return 0;
        }
        if (iMax == 1) {
            return 1;
        }
        if (Manifest.permission.MANAGE_DOCUMENTS.equals(this.mReadPermission)) {
            str4 = " requires that you obtain access using ACTION_OPEN_DOCUMENT or related APIs";
        } else {
            str4 = this.mExported ? " requires " + str3 + ", or grantUriPermission()" : " requires the provider be exported, or grantUriPermission()";
        }
        throw new SecurityException("Permission Denial: reading " + getClass().getName() + " uri " + uri + " from pid=" + callingPid + ", uid=" + callingUid + str4);
    }

    protected int enforceWritePermissionInner(Uri uri, String str, String str2, IBinder iBinder) throws SecurityException {
        String str3;
        int iMax;
        Context context = getContext();
        int callingPid = Binder.getCallingPid();
        int callingUid = Binder.getCallingUid();
        if (UserHandle.isSameApp(callingUid, this.mMyUid)) {
            return 0;
        }
        String str4 = null;
        if (this.mExported && checkUser(callingPid, callingUid, context)) {
            String writePermission = getWritePermission();
            if (writePermission != null) {
                int iCheckPermissionAndAppOp = checkPermissionAndAppOp(writePermission, str, str2, iBinder);
                if (iCheckPermissionAndAppOp == 0) {
                    return 0;
                }
                iMax = Math.max(0, iCheckPermissionAndAppOp);
                str4 = writePermission;
            } else {
                iMax = 0;
            }
            boolean z = writePermission == null;
            PathPermission[] pathPermissions = getPathPermissions();
            if (pathPermissions != null) {
                String path = uri.getPath();
                for (PathPermission pathPermission : pathPermissions) {
                    String writePermission2 = pathPermission.getWritePermission();
                    if (writePermission2 != null && pathPermission.match(path)) {
                        int iCheckPermissionAndAppOp2 = checkPermissionAndAppOp(writePermission2, str, str2, iBinder);
                        if (iCheckPermissionAndAppOp2 == 0) {
                            return 0;
                        }
                        iMax = Math.max(iMax, iCheckPermissionAndAppOp2);
                        str4 = writePermission2;
                        z = false;
                    }
                }
            }
            if (z) {
                return 0;
            }
            str3 = str4;
        } else {
            str3 = null;
            iMax = 0;
        }
        if (context.checkUriPermission(uri, callingPid, callingUid, 2, iBinder) == 0) {
            return 0;
        }
        if (iMax == 1) {
            return 1;
        }
        throw new SecurityException("Permission Denial: writing " + getClass().getName() + " uri " + uri + " from pid=" + callingPid + ", uid=" + callingUid + (this.mExported ? " requires " + str3 + ", or grantUriPermission()" : " requires the provider be exported, or grantUriPermission()"));
    }

    public final Context getContext() {
        return this.mContext;
    }

    public final Context requireContext() {
        Context context = getContext();
        if (context != null) {
            return context;
        }
        throw new IllegalStateException("Cannot find context from the provider.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Pair<String, String> setCallingPackage(Pair<String, String> pair) {
        Pair<String, String> pair2 = this.mCallingPackage.get();
        this.mCallingPackage.set(pair);
        onCallingPackageChanged();
        return pair2;
    }

    public final String getCallingPackage() {
        Pair<String, String> pair = this.mCallingPackage.get();
        if (pair == null) {
            return null;
        }
        this.mTransport.mAppOpsManager.checkPackage(Binder.getCallingUid(), pair.first);
        return pair.first;
    }

    public final String getCallingAttributionTag() {
        Pair<String, String> pair = this.mCallingPackage.get();
        if (pair != null) {
            return pair.second;
        }
        return null;
    }

    @Deprecated
    public final String getCallingFeatureId() {
        return getCallingAttributionTag();
    }

    public final String getCallingPackageUnchecked() {
        Pair<String, String> pair = this.mCallingPackage.get();
        if (pair != null) {
            return pair.first;
        }
        return null;
    }

    public final class CallingIdentity {
        public final long binderToken;
        public final Pair<String, String> callingPackage;

        public CallingIdentity(long j, Pair<String, String> pair) {
            this.binderToken = j;
            this.callingPackage = pair;
        }
    }

    public final CallingIdentity clearCallingIdentity() {
        return new CallingIdentity(Binder.clearCallingIdentity(), setCallingPackage(null));
    }

    public final void restoreCallingIdentity(CallingIdentity callingIdentity) {
        Binder.restoreCallingIdentity(callingIdentity.binderToken);
        this.mCallingPackage.set(callingIdentity.callingPackage);
    }

    protected final void setAuthorities(String str) {
        if (str != null) {
            if (str.indexOf(59) == -1) {
                this.mAuthority = str;
                this.mAuthorities = null;
            } else {
                this.mAuthority = null;
                this.mAuthorities = str.split(";");
            }
        }
    }

    protected final boolean matchesOurAuthorities(String str) {
        String str2 = this.mAuthority;
        if (str2 != null) {
            return str2.equals(str);
        }
        String[] strArr = this.mAuthorities;
        if (strArr != null) {
            int length = strArr.length;
            for (int i = 0; i < length; i++) {
                if (this.mAuthorities[i].equals(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected final void setReadPermission(String str) {
        this.mReadPermission = str;
    }

    public final String getReadPermission() {
        return this.mReadPermission;
    }

    protected final void setWritePermission(String str) {
        this.mWritePermission = str;
    }

    public final String getWritePermission() {
        return this.mWritePermission;
    }

    protected final void setPathPermissions(PathPermission[] pathPermissionArr) {
        this.mPathPermissions = pathPermissionArr;
    }

    public final PathPermission[] getPathPermissions() {
        return this.mPathPermissions;
    }

    public final void setAppOps(int i, int i2) {
        if (this.mNoPerms) {
            return;
        }
        this.mTransport.mReadOp = i;
        this.mTransport.mWriteOp = i2;
    }

    public AppOpsManager getAppOpsManager() {
        return this.mTransport.mAppOpsManager;
    }

    public final void setTransportLoggingEnabled(boolean z) {
        Transport transport = this.mTransport;
        if (transport == null) {
            return;
        }
        if (z) {
            transport.mInterface = new LoggingContentInterface(getClass().getSimpleName(), this);
        } else {
            transport.mInterface = this;
        }
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
        return query(uri, strArr, str, strArr2, str2);
    }

    @Override // android.content.ContentInterface
    public Cursor query(Uri uri, String[] strArr, Bundle bundle, CancellationSignal cancellationSignal) {
        if (bundle == null) {
            bundle = Bundle.EMPTY;
        }
        String string = bundle.getString(ContentResolver.QUERY_ARG_SQL_SORT_ORDER);
        if (string == null && bundle.containsKey(ContentResolver.QUERY_ARG_SORT_COLUMNS)) {
            string = ContentResolver.createSqlSortClause(bundle);
        }
        return query(uri, strArr, bundle.getString(ContentResolver.QUERY_ARG_SQL_SELECTION), bundle.getStringArray(ContentResolver.QUERY_ARG_SQL_SELECTION_ARGS), string, cancellationSignal);
    }

    public Uri rejectInsert(Uri uri, ContentValues contentValues) {
        return uri.buildUpon().appendPath(AudioSystem.LEGACY_REMOTE_SUBMIX_ADDRESS).build();
    }

    @Override // android.content.ContentInterface
    public Uri insert(Uri uri, ContentValues contentValues, Bundle bundle) {
        return insert(uri, contentValues);
    }

    @Override // android.content.ContentInterface
    public int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        int length = contentValuesArr.length;
        for (ContentValues contentValues : contentValuesArr) {
            insert(uri, contentValues);
        }
        return length;
    }

    @Override // android.content.ContentInterface
    public int delete(Uri uri, Bundle bundle) {
        if (bundle == null) {
            bundle = Bundle.EMPTY;
        }
        return delete(uri, bundle.getString(ContentResolver.QUERY_ARG_SQL_SELECTION), bundle.getStringArray(ContentResolver.QUERY_ARG_SQL_SELECTION_ARGS));
    }

    @Override // android.content.ContentInterface
    public int update(Uri uri, ContentValues contentValues, Bundle bundle) {
        if (bundle == null) {
            bundle = Bundle.EMPTY;
        }
        return update(uri, contentValues, bundle.getString(ContentResolver.QUERY_ARG_SQL_SELECTION), bundle.getStringArray(ContentResolver.QUERY_ARG_SQL_SELECTION_ARGS));
    }

    public ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        throw new FileNotFoundException("No files supported by provider at " + uri);
    }

    @Override // android.content.ContentInterface
    public ParcelFileDescriptor openFile(Uri uri, String str, CancellationSignal cancellationSignal) throws FileNotFoundException {
        return openFile(uri, str);
    }

    public AssetFileDescriptor openAssetFile(Uri uri, String str) throws FileNotFoundException {
        ParcelFileDescriptor parcelFileDescriptorOpenFile = openFile(uri, str);
        if (parcelFileDescriptorOpenFile != null) {
            return new AssetFileDescriptor(parcelFileDescriptorOpenFile, 0L, -1L);
        }
        return null;
    }

    @Override // android.content.ContentInterface
    public AssetFileDescriptor openAssetFile(Uri uri, String str, CancellationSignal cancellationSignal) throws FileNotFoundException {
        return openAssetFile(uri, str);
    }

    protected final ParcelFileDescriptor openFileHelper(Uri uri, String str) throws FileNotFoundException {
        Cursor cursorQuery = query(uri, new String[]{"_data"}, null, null, null);
        int count = cursorQuery != null ? cursorQuery.getCount() : 0;
        if (count != 1) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            if (count == 0) {
                throw new FileNotFoundException("No entry for " + uri);
            }
            throw new FileNotFoundException("Multiple items at " + uri);
        }
        cursorQuery.moveToFirst();
        int columnIndex = cursorQuery.getColumnIndex("_data");
        String string = columnIndex >= 0 ? cursorQuery.getString(columnIndex) : null;
        cursorQuery.close();
        if (string == null) {
            throw new FileNotFoundException("Column _data not found.");
        }
        return ParcelFileDescriptor.open(new File(string), ParcelFileDescriptor.parseMode(str));
    }

    public AssetFileDescriptor openTypedAssetFile(Uri uri, String str, Bundle bundle) throws FileNotFoundException {
        if ("*/*".equals(str)) {
            return openAssetFile(uri, "r");
        }
        String type = getType(uri);
        if (type != null && ClipDescription.compareMimeTypes(type, str)) {
            return openAssetFile(uri, "r");
        }
        throw new FileNotFoundException("Can't open " + uri + " as type " + str);
    }

    @Override // android.content.ContentInterface
    public AssetFileDescriptor openTypedAssetFile(Uri uri, String str, Bundle bundle, CancellationSignal cancellationSignal) throws FileNotFoundException {
        return openTypedAssetFile(uri, str, bundle);
    }

    public <T> ParcelFileDescriptor openPipeHelper(final Uri uri, final String str, final Bundle bundle, final T t, final PipeDataWriter<T> pipeDataWriter) throws FileNotFoundException {
        try {
            final ParcelFileDescriptor[] parcelFileDescriptorArrCreatePipe = ParcelFileDescriptor.createPipe();
            new AsyncTask<Object, Object, Object>() { // from class: android.content.ContentProvider.1
                @Override // android.os.AsyncTask
                protected Object doInBackground(Object... objArr) {
                    pipeDataWriter.writeDataToPipe(parcelFileDescriptorArrCreatePipe[1], uri, str, bundle, t);
                    try {
                        parcelFileDescriptorArrCreatePipe[1].close();
                        return null;
                    } catch (IOException e) {
                        Log.w(ContentProvider.TAG, "Failure closing pipe", e);
                        return null;
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[]) null);
            return parcelFileDescriptorArrCreatePipe[0];
        } catch (IOException unused) {
            throw new FileNotFoundException("failure making pipe");
        }
    }

    public IContentProvider getIContentProvider() {
        return this.mTransport;
    }

    public void attachInfoForTesting(Context context, ProviderInfo providerInfo) {
        attachInfo(context, providerInfo, true);
    }

    public void attachInfo(Context context, ProviderInfo providerInfo) {
        attachInfo(context, providerInfo, false);
    }

    private void attachInfo(Context context, ProviderInfo providerInfo, boolean z) {
        Transport transport;
        this.mNoPerms = z;
        this.mCallingPackage = new ThreadLocal<>();
        if (this.mContext == null) {
            this.mContext = context;
            if (context != null && (transport = this.mTransport) != null) {
                transport.mAppOpsManager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            }
            this.mMyUid = Process.myUid();
            if (providerInfo != null) {
                setReadPermission(providerInfo.readPermission);
                setWritePermission(providerInfo.writePermission);
                setPathPermissions(providerInfo.pathPermissions);
                this.mExported = providerInfo.exported;
                this.mSingleUser = (providerInfo.flags & 1073741824) != 0;
                setAuthorities(providerInfo.authority);
            }
            if (Build.IS_DEBUGGABLE) {
                setTransportLoggingEnabled(Log.isLoggable(getClass().getSimpleName(), 2));
            }
            onCreate();
        }
    }

    @Override // android.content.ContentInterface
    public ContentProviderResult[] applyBatch(String str, ArrayList<ContentProviderOperation> arrayList) throws OperationApplicationException {
        return applyBatch(arrayList);
    }

    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> arrayList) throws OperationApplicationException {
        int size = arrayList.size();
        ContentProviderResult[] contentProviderResultArr = new ContentProviderResult[size];
        for (int i = 0; i < size; i++) {
            contentProviderResultArr[i] = arrayList.get(i).apply(this, contentProviderResultArr, i);
        }
        return contentProviderResultArr;
    }

    @Override // android.content.ContentInterface
    public Bundle call(String str, String str2, String str3, Bundle bundle) {
        return call(str2, str3, bundle);
    }

    public void shutdown() {
        Log.w(TAG, "implement ContentProvider shutdown() to make sure all database connections are gracefully shutdown");
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("nothing to dump");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void validateIncomingAuthority(String str) throws SecurityException {
        String str2;
        if (matchesOurAuthorities(getAuthorityWithoutUserId(str))) {
            return;
        }
        String str3 = "The authority " + str + " does not match the one of the contentProvider: ";
        if (this.mAuthority != null) {
            str2 = str3 + this.mAuthority;
        } else {
            str2 = str3 + Arrays.toString(this.mAuthorities);
        }
        throw new SecurityException(str2);
    }

    public Uri validateIncomingUri(Uri uri) throws SecurityException {
        int userIdFromAuthority;
        String authority = uri.getAuthority();
        if (!this.mSingleUser && (userIdFromAuthority = getUserIdFromAuthority(authority, -2)) != -2 && userIdFromAuthority != this.mContext.getUserId()) {
            throw new SecurityException("trying to query a ContentProvider in user " + this.mContext.getUserId() + " with a uri belonging to user " + userIdFromAuthority);
        }
        validateIncomingAuthority(authority);
        String encodedPath = uri.getEncodedPath();
        if (encodedPath == null || encodedPath.indexOf("//") == -1) {
            return uri;
        }
        Uri uriBuild = uri.buildUpon().encodedPath(encodedPath.replaceAll("//+", "/")).build();
        Log.w(TAG, "Normalized " + uri + " to " + uriBuild + " to avoid possible security issues");
        return uriBuild;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Uri maybeGetUriWithoutUserId(Uri uri) {
        return this.mSingleUser ? uri : getUriWithoutUserId(uri);
    }

    public static int getUserIdFromAuthority(String str, int i) {
        int iLastIndexOf;
        if (str == null || (iLastIndexOf = str.lastIndexOf(64)) == -1) {
            return i;
        }
        try {
            return Integer.parseInt(str.substring(0, iLastIndexOf));
        } catch (NumberFormatException e) {
            Log.w(TAG, "Error parsing userId.", e);
            return -10000;
        }
    }

    public static int getUserIdFromAuthority(String str) {
        return getUserIdFromAuthority(str, -2);
    }

    public static int getUserIdFromUri(Uri uri, int i) {
        return uri == null ? i : getUserIdFromAuthority(uri.getAuthority(), i);
    }

    public static int getUserIdFromUri(Uri uri) {
        return getUserIdFromUri(uri, -2);
    }

    public static UserHandle getUserHandleFromUri(Uri uri) {
        return UserHandle.of(getUserIdFromUri(uri, Process.myUserHandle().getIdentifier()));
    }

    public static String getAuthorityWithoutUserId(String str) {
        if (str == null) {
            return null;
        }
        return str.substring(str.lastIndexOf(64) + 1);
    }

    public static Uri getUriWithoutUserId(Uri uri) {
        if (uri == null) {
            return null;
        }
        Uri.Builder builderBuildUpon = uri.buildUpon();
        builderBuildUpon.authority(getAuthorityWithoutUserId(uri.getAuthority()));
        return builderBuildUpon.build();
    }

    public static boolean uriHasUserId(Uri uri) {
        if (uri == null) {
            return false;
        }
        return !TextUtils.isEmpty(uri.getUserInfo());
    }

    public static Uri maybeAddUserId(Uri uri, int i) {
        if (uri == null) {
            return null;
        }
        if (i == -2 || !"content".equals(uri.getScheme()) || uriHasUserId(uri)) {
            return uri;
        }
        Uri.Builder builderBuildUpon = uri.buildUpon();
        builderBuildUpon.encodedAuthority("" + i + "@" + uri.getEncodedAuthority());
        return builderBuildUpon.build();
    }
}
