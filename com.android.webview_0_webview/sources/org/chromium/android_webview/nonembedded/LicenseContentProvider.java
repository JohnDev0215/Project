package org.chromium.android_webview.nonembedded;

import J.N;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import defpackage.Ms;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class LicenseContentProvider extends ContentProvider implements ContentProvider.PipeDataWriter {
    public void a(ParcelFileDescriptor parcelFileDescriptor, String str) {
        if (WebViewApkApplication.a()) {
            N.MjdhoRtd(parcelFileDescriptor.detachFd());
            return;
        }
        try {
            InputStream inputStreamOpen = getContext().getAssets().open(str);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(parcelFileDescriptor.getFileDescriptor());
                try {
                    Ms.a(inputStreamOpen, fileOutputStream);
                    fileOutputStream.close();
                    if (inputStreamOpen != null) {
                        inputStreamOpen.close();
                    }
                } finally {
                }
            } finally {
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        if (uri == null || !uri.toString().endsWith("LicenseContentProvider/webview_licenses")) {
            return null;
        }
        return "text/html";
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String str) {
        if (uri == null || !uri.toString().endsWith("LicenseContentProvider/webview_licenses")) {
            return null;
        }
        return openPipeHelper(null, null, null, "webview_licenses.notice", this);
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContentProvider.PipeDataWriter
    public /* bridge */ /* synthetic */ void writeDataToPipe(ParcelFileDescriptor parcelFileDescriptor, Uri uri, String str, Bundle bundle, Object obj) {
        a(parcelFileDescriptor, (String) obj);
    }
}
