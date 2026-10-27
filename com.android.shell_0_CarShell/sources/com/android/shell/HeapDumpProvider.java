package com.android.shell;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.ParcelFileDescriptor;
import java.io.File;
import java.io.FileNotFoundException;

/* JADX INFO: loaded from: classes.dex */
public class HeapDumpProvider extends ContentProvider {
    private static final Object sLock = new Object();
    private File mRoot;

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return "application/octet-stream";
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        boolean zMkdir;
        synchronized (sLock) {
            File file = new File(getContext().createCredentialProtectedStorageContext().getFilesDir(), "heapdumps");
            this.mRoot = file;
            zMkdir = file.mkdir();
        }
        return zMkdir;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("Insert not allowed.");
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return new File(this.mRoot, Uri.decode(sanitizePath(uri.getEncodedPath()))).delete() ? 1 : 0;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("Update not allowed.");
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        ParcelFileDescriptor parcelFileDescriptorOpen;
        String strDecode = Uri.decode(sanitizePath(uri.getEncodedPath()));
        int i = Binder.getCallingUid() == 1000 ? 738197504 : 268435456;
        synchronized (sLock) {
            parcelFileDescriptorOpen = ParcelFileDescriptor.open(new File(this.mRoot, strDecode), i);
        }
        return parcelFileDescriptorOpen;
    }

    static Uri makeUri(String str) {
        return Uri.parse("content://com.android.shell.heapdump/" + str + "_javaheap.bin");
    }

    private String sanitizePath(String str) {
        return str.replaceAll("[^a-zA-Z0-9_.]", "");
    }
}
