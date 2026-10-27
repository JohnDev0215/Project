package com.android.bluetooth.mapapi;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.android.bluetooth.mapapi.BluetoothMapContract;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class BluetoothMapEmailProvider extends ContentProvider {
    private static final boolean D = true;
    private static final int MATCH_ACCOUNT = 1;
    private static final int MATCH_FOLDER = 3;
    private static final int MATCH_MESSAGE = 2;
    private static final String TAG = "BluetoothMapEmailProvider";
    private String mAuthority;
    private UriMatcher mMatcher;
    protected ContentResolver mResolver;
    private Uri CONTENT_URI = null;
    private PipeReader mPipeReader = new PipeReader();
    private PipeWriter mPipeWriter = new PipeWriter();

    public interface PipeDataReader<T> {
        void readDataFromPipe(ParcelFileDescriptor parcelFileDescriptor, Uri uri, String str, Bundle bundle, T t);
    }

    protected abstract void UpdateMimeMessageFromStream(FileInputStream fileInputStream, long j, long j2) throws IOException;

    protected abstract void WriteMessageToStream(long j, long j2, boolean z, boolean z2, FileOutputStream fileOutputStream) throws IOException;

    protected abstract int deleteMessage(String str, String str2);

    protected abstract Uri getContentUri();

    protected abstract String insertMessage(String str, String str2);

    protected abstract Cursor queryAccount(String[] strArr, String str, String[] strArr2, String str2);

    protected abstract Cursor queryFolder(String str, String[] strArr, String str2, String[] strArr2, String str3);

    protected abstract Cursor queryMessage(String str, String[] strArr, String str2, String[] strArr2, String str3);

    protected abstract int syncFolder(long j, long j2);

    protected abstract int updateAccount(String str, int i);

    protected abstract int updateMessage(String str, Long l, Long l2, Boolean bool);

    @Override // android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo info) {
        this.mAuthority = info.authority;
        UriMatcher uriMatcher = new UriMatcher(-1);
        this.mMatcher = uriMatcher;
        uriMatcher.addURI(this.mAuthority, BluetoothMapContract.TABLE_ACCOUNT, 1);
        this.mMatcher.addURI(this.mAuthority, "#/Folder", 3);
        this.mMatcher.addURI(this.mAuthority, "#/Message", 2);
        if (!info.exported) {
            throw new SecurityException("Provider must be exported");
        }
        if (!"android.permission.BLUETOOTH_MAP".equals(info.writePermission)) {
            throw new SecurityException("Provider must be protected by android.permission.BLUETOOTH_MAP");
        }
        this.mResolver = context.getContentResolver();
        super.attachInfo(context, info);
    }

    public class PipeReader implements PipeDataReader<Cursor> {
        public PipeReader() {
        }

        /* JADX WARN: Removed duplicated region for block: B:31:0x0063 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:8:0x0047 -> B:28:0x005f). Please report as a decompilation issue!!! */
        @Override // com.android.bluetooth.mapapi.BluetoothMapEmailProvider.PipeDataReader
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void readDataFromPipe(android.os.ParcelFileDescriptor r9, android.net.Uri r10, java.lang.String r11, android.os.Bundle r12, android.database.Cursor r13) throws java.lang.Throwable {
            /*
                r8 = this;
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "readDataFromPipe(): uri="
                r0.append(r1)
                java.lang.String r1 = r10.toString()
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                java.lang.String r1 = "BluetoothMapEmailProvider"
                android.util.Log.v(r1, r0)
                r0 = 0
                java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L4d java.io.IOException -> L51
                java.io.FileDescriptor r2 = r9.getFileDescriptor()     // Catch: java.lang.Throwable -> L4d java.io.IOException -> L51
                r3.<init>(r2)     // Catch: java.lang.Throwable -> L4d java.io.IOException -> L51
                java.lang.String r0 = r10.getLastPathSegment()     // Catch: java.io.IOException -> L4b java.lang.Throwable -> L60
                java.lang.Long r0 = java.lang.Long.valueOf(r0)     // Catch: java.io.IOException -> L4b java.lang.Throwable -> L60
                long r6 = r0.longValue()     // Catch: java.io.IOException -> L4b java.lang.Throwable -> L60
                java.lang.String r0 = com.android.bluetooth.mapapi.BluetoothMapEmailProvider.getAccountId(r10)     // Catch: java.io.IOException -> L4b java.lang.Throwable -> L60
                java.lang.Long r0 = java.lang.Long.valueOf(r0)     // Catch: java.io.IOException -> L4b java.lang.Throwable -> L60
                long r4 = r0.longValue()     // Catch: java.io.IOException -> L4b java.lang.Throwable -> L60
                com.android.bluetooth.mapapi.BluetoothMapEmailProvider r2 = com.android.bluetooth.mapapi.BluetoothMapEmailProvider.this     // Catch: java.io.IOException -> L4b java.lang.Throwable -> L60
                r2.UpdateMimeMessageFromStream(r3, r4, r6)     // Catch: java.io.IOException -> L4b java.lang.Throwable -> L60
                r3.close()     // Catch: java.io.IOException -> L46
            L45:
                goto L5f
            L46:
                r0 = move-exception
                android.util.Log.w(r1, r0)
                goto L5f
            L4b:
                r0 = move-exception
                goto L54
            L4d:
                r2 = move-exception
                r3 = r0
                r0 = r2
                goto L61
            L51:
                r2 = move-exception
                r3 = r0
                r0 = r2
            L54:
                java.lang.String r2 = "IOException: "
                android.util.Log.w(r1, r2, r0)     // Catch: java.lang.Throwable -> L60
                if (r3 == 0) goto L45
                r3.close()     // Catch: java.io.IOException -> L46
                goto L45
            L5f:
                return
            L60:
                r0 = move-exception
            L61:
                if (r3 == 0) goto L6c
                r3.close()     // Catch: java.io.IOException -> L67
                goto L6c
            L67:
                r2 = move-exception
                android.util.Log.w(r1, r2)
                goto L6d
            L6c:
            L6d:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.mapapi.BluetoothMapEmailProvider.PipeReader.readDataFromPipe(android.os.ParcelFileDescriptor, android.net.Uri, java.lang.String, android.os.Bundle, android.database.Cursor):void");
        }
    }

    public class PipeWriter implements ContentProvider.PipeDataWriter<Cursor> {
        public PipeWriter() {
        }

        @Override // android.content.ContentProvider.PipeDataWriter
        public void writeDataToPipe(ParcelFileDescriptor output, Uri uri, String mimeType, Bundle opts, Cursor c) throws Throwable {
            Throwable th;
            FileOutputStream fout;
            boolean includeAttachments;
            boolean download;
            long messageId;
            long accountId;
            Log.d(BluetoothMapEmailProvider.TAG, "writeDataToPipe(): uri=" + uri.toString() + " - getLastPathSegment() = " + uri.getLastPathSegment());
            FileOutputStream fout2 = null;
            try {
                fout = new FileOutputStream(output.getFileDescriptor());
                includeAttachments = true;
                download = false;
                try {
                    List<String> segments = uri.getPathSegments();
                    messageId = Long.parseLong(segments.get(2));
                    accountId = Long.parseLong(BluetoothMapEmailProvider.getAccountId(uri));
                    if (segments.size() >= 4) {
                        String format = segments.get(3);
                        if (format.equalsIgnoreCase(BluetoothMapContract.FILE_MSG_NO_ATTACHMENTS)) {
                            includeAttachments = false;
                        } else if (format.equalsIgnoreCase(BluetoothMapContract.FILE_MSG_DOWNLOAD_NO_ATTACHMENTS)) {
                            includeAttachments = false;
                            download = true;
                        } else if (format.equalsIgnoreCase(BluetoothMapContract.FILE_MSG_DOWNLOAD)) {
                            download = true;
                        }
                    }
                } catch (IOException e) {
                    e = e;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException e2) {
                e = e2;
            } catch (Throwable th3) {
                th = th3;
                th = th;
                try {
                    fout2.flush();
                } catch (IOException e3) {
                    Log.w(BluetoothMapEmailProvider.TAG, "IOException: ", e3);
                }
                try {
                    fout2.close();
                    throw th;
                } catch (IOException e4) {
                    Log.w(BluetoothMapEmailProvider.TAG, "IOException: ", e4);
                    throw th;
                }
            }
            try {
                BluetoothMapEmailProvider.this.WriteMessageToStream(accountId, messageId, includeAttachments, download, fout);
                try {
                    fout.flush();
                } catch (IOException e5) {
                    Log.w(BluetoothMapEmailProvider.TAG, "IOException: ", e5);
                }
                try {
                    fout.close();
                } catch (IOException e6) {
                    Log.w(BluetoothMapEmailProvider.TAG, "IOException: ", e6);
                }
            } catch (IOException e7) {
                e = e7;
                fout2 = fout;
                try {
                    Log.w(BluetoothMapEmailProvider.TAG, e);
                    try {
                        fout2.flush();
                    } catch (IOException e8) {
                        Log.w(BluetoothMapEmailProvider.TAG, "IOException: ", e8);
                    }
                    try {
                        fout2.close();
                    } catch (IOException e9) {
                        Log.w(BluetoothMapEmailProvider.TAG, "IOException: ", e9);
                    }
                } catch (Throwable th4) {
                    th = th4;
                    th = th;
                    fout2.flush();
                    fout2.close();
                    throw th;
                }
            } catch (Throwable th5) {
                th = th5;
                th = th;
                fout2 = fout;
                fout2.flush();
                fout2.close();
                throw th;
            }
        }
    }

    protected void onAccountChanged(String accountId) {
        Uri newUri;
        String str = this.mAuthority;
        if (str == null) {
            return;
        }
        if (accountId == null) {
            newUri = BluetoothMapContract.buildAccountUri(str);
        } else {
            newUri = BluetoothMapContract.buildAccountUriwithId(str, accountId);
        }
        Log.d(TAG, "onAccountChanged() accountId = " + accountId + " URI: " + newUri);
        this.mResolver.notifyChange(newUri, null);
    }

    protected void onMessageChanged(String accountId, String messageId) {
        Uri newUri;
        String str = this.mAuthority;
        if (str == null) {
            return;
        }
        if (accountId == null) {
            newUri = BluetoothMapContract.buildMessageUri(str);
        } else if (messageId == null) {
            newUri = BluetoothMapContract.buildMessageUri(str, accountId);
        } else {
            newUri = BluetoothMapContract.buildMessageUriWithId(str, accountId, messageId);
        }
        Log.d(TAG, "onMessageChanged() accountId = " + accountId + " messageId = " + messageId + " URI: " + newUri);
        this.mResolver.notifyChange(newUri, null);
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return "Email";
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String mode) throws FileNotFoundException {
        long callingId = Binder.clearCallingIdentity();
        Log.d(TAG, "openFile(): uri=" + uri.toString() + " - getLastPathSegment() = " + uri.getLastPathSegment());
        try {
            try {
                return mode.equals("w") ? openInversePipeHelper(uri, null, null, null, this.mPipeReader) : openPipeHelper(uri, null, null, null, this.mPipeWriter);
            } catch (IOException e) {
                Log.w(TAG, e);
                Binder.restoreCallingIdentity(callingId);
                return null;
            }
        } finally {
            Binder.restoreCallingIdentity(callingId);
        }
    }

    private <T> ParcelFileDescriptor openInversePipeHelper(final Uri uri, final String mimeType, final Bundle opts, final T args, final PipeDataReader<T> func) throws FileNotFoundException {
        try {
            final ParcelFileDescriptor[] fds = ParcelFileDescriptor.createPipe();
            AsyncTask<Object, Object, Object> task = new AsyncTask<Object, Object, Object>() { // from class: com.android.bluetooth.mapapi.BluetoothMapEmailProvider.1
                @Override // android.os.AsyncTask
                protected Object doInBackground(Object... params) {
                    func.readDataFromPipe(fds[0], uri, mimeType, opts, args);
                    try {
                        fds[0].close();
                        return null;
                    } catch (IOException e) {
                        Log.w(BluetoothMapEmailProvider.TAG, "Failure closing pipe", e);
                        return null;
                    }
                }
            };
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[]) null);
            return fds[1];
        } catch (IOException e) {
            throw new FileNotFoundException("failure making pipe");
        }
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String where, String[] selectionArgs) {
        Log.d(TAG, "delete(): uri=" + uri.toString());
        String table = uri.getPathSegments().get(1);
        if (table == null) {
            throw new IllegalArgumentException("Table missing in URI");
        }
        String messageId = uri.getLastPathSegment();
        if (messageId == null) {
            throw new IllegalArgumentException("Message ID missing in update values!");
        }
        String accountId = getAccountId(uri);
        if (accountId == null) {
            throw new IllegalArgumentException("Account ID missing in update values!");
        }
        long callingId = Binder.clearCallingIdentity();
        try {
            if (table.equals(BluetoothMapContract.TABLE_MESSAGE)) {
                return deleteMessage(accountId, messageId);
            }
            Log.w(TAG, "Unknown table name: " + table);
            return 0;
        } finally {
            Binder.restoreCallingIdentity(callingId);
        }
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues values) {
        String table = uri.getLastPathSegment();
        if (table == null) {
            throw new IllegalArgumentException("Table missing in URI");
        }
        String accountId = getAccountId(uri);
        Long folderId = values.getAsLong(BluetoothMapContract.MessageColumns.FOLDER_ID);
        if (folderId == null) {
            throw new IllegalArgumentException("FolderId missing in ContentValues");
        }
        long callingId = Binder.clearCallingIdentity();
        Log.d(TAG, "insert(): uri=" + uri.toString() + " - getLastPathSegment() = " + uri.getLastPathSegment());
        try {
            if (table.equals(BluetoothMapContract.TABLE_MESSAGE)) {
                String id = insertMessage(accountId, folderId.toString());
                Log.i(TAG, "insert() ID: " + id);
                return Uri.parse(uri.toString() + "/" + id);
            }
            Log.w(TAG, "Unknown table name: " + table);
            return null;
        } finally {
            Binder.restoreCallingIdentity(callingId);
        }
    }

    protected String[] convertProjection(String[] projection, Map<String, String> projectionMap) {
        String[] newProjection = new String[projection.length];
        for (int i = 0; i < projection.length; i++) {
            newProjection[i] = projectionMap.get(projection[i]) + " as " + projection[i];
        }
        return newProjection;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) throws Throwable {
        long callingId = Binder.clearCallingIdentity();
        try {
            int iMatch = this.mMatcher.match(uri);
            if (iMatch == 1) {
                try {
                    Cursor cursorQueryAccount = queryAccount(projection, selection, selectionArgs, sortOrder);
                    Binder.restoreCallingIdentity(callingId);
                    return cursorQueryAccount;
                } catch (Throwable th) {
                    th = th;
                    Binder.restoreCallingIdentity(callingId);
                    throw th;
                }
            }
            if (iMatch == 2) {
                String accountId = getAccountId(uri);
                Cursor cursorQueryMessage = queryMessage(accountId, projection, selection, selectionArgs, sortOrder);
                Binder.restoreCallingIdentity(callingId);
                return cursorQueryMessage;
            }
            if (iMatch == 3) {
                String accountId2 = getAccountId(uri);
                Cursor cursorQueryFolder = queryFolder(accountId2, projection, selection, selectionArgs, sortOrder);
                Binder.restoreCallingIdentity(callingId);
                return cursorQueryFolder;
            }
            throw new UnsupportedOperationException("Unsupported Uri " + uri);
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        String table = uri.getLastPathSegment();
        if (table == null) {
            throw new IllegalArgumentException("Table missing in URI");
        }
        if (selection != null) {
            throw new IllegalArgumentException("selection shall not be used, ContentValues shall contain the data");
        }
        long callingId = Binder.clearCallingIdentity();
        Log.w(TAG, "update(): uri=" + uri.toString() + " - getLastPathSegment() = " + uri.getLastPathSegment());
        try {
            if (table.equals(BluetoothMapContract.TABLE_ACCOUNT)) {
                String accountId = values.getAsString("_id");
                if (accountId == null) {
                    throw new IllegalArgumentException("Account ID missing in update values!");
                }
                Integer exposeFlag = values.getAsInteger(BluetoothMapContract.AccountColumns.FLAG_EXPOSE);
                if (exposeFlag != null) {
                    return updateAccount(accountId, exposeFlag.intValue());
                }
                throw new IllegalArgumentException("Expose flag missing in update values!");
            }
            if (table.equals(BluetoothMapContract.TABLE_FOLDER)) {
                return 0;
            }
            if (!table.equals(BluetoothMapContract.TABLE_MESSAGE)) {
                Log.w(TAG, "Unknown table name: " + table);
                return 0;
            }
            String accountId2 = getAccountId(uri);
            Long messageId = values.getAsLong("_id");
            if (messageId == null) {
                throw new IllegalArgumentException("Message ID missing in update values!");
            }
            Long folderId = values.getAsLong(BluetoothMapContract.MessageColumns.FOLDER_ID);
            Boolean flagRead = values.getAsBoolean(BluetoothMapContract.MessageColumns.FLAG_READ);
            return updateMessage(accountId2, messageId, folderId, flagRead);
        } finally {
            Binder.restoreCallingIdentity(callingId);
        }
    }

    @Override // android.content.ContentProvider
    public Bundle call(String method, String arg, Bundle extras) {
        long callingId = Binder.clearCallingIdentity();
        Log.d(TAG, "call(): method=" + method + " arg=" + arg + "ThreadId: " + Thread.currentThread().getId());
        try {
            if (!method.equals(BluetoothMapContract.METHOD_UPDATE_FOLDER)) {
                return null;
            }
            long accountId = extras.getLong(BluetoothMapContract.EXTRA_UPDATE_ACCOUNT_ID, -1L);
            if (accountId == -1) {
                Log.w(TAG, "No account ID in CALL");
                return null;
            }
            long folderId = extras.getLong(BluetoothMapContract.EXTRA_UPDATE_FOLDER_ID, -1L);
            if (folderId == -1) {
                Log.w(TAG, "No folder ID in CALL");
                return null;
            }
            int ret = syncFolder(accountId, folderId);
            if (ret == 0) {
                return new Bundle();
            }
            return null;
        } finally {
            Binder.restoreCallingIdentity(callingId);
        }
    }

    @Override // android.content.ContentProvider
    public void shutdown() {
    }

    public static String getAccountId(Uri uri) {
        List<String> segments = uri.getPathSegments();
        if (segments.size() < 1) {
            throw new IllegalArgumentException("No AccountId pressent in URI: " + uri);
        }
        return segments.get(0);
    }
}
