package org.chromium.chrome.browser.download;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import androidx.core.content.FileProvider;
import defpackage.C0175b;
import defpackage.xz;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import org.chromium.chrome.browser.download.b;

/* JADX INFO: loaded from: classes.dex */
public class DownloadFileProvider extends FileProvider {
    public static final String[] i = {"_display_name", "_size"};

    public static Uri c(String str, String str2) {
        return new Uri.Builder().scheme("content").authority(xz.a.getPackageName() + ".DownloadFileProvider").path(str).appendQueryParameter("file", str2).build();
    }

    public static String d(Uri uri, b.c cVar) {
        if (uri == null) {
            return null;
        }
        String path = uri.getPath();
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        if (path.charAt(0) == File.separatorChar && path.length() > 1) {
            path = path.substring(1);
        }
        String queryParameter = uri.getQueryParameter("file");
        StringBuilder sb = new StringBuilder("..");
        String str = File.separator;
        sb.append(str);
        if (queryParameter.contains(sb.toString())) {
            return null;
        }
        if (path.equals("download")) {
            return b.b() + str + queryParameter;
        }
        b.f fVarA = cVar.a();
        if (path.equals("external_volume")) {
            List<File> list = fVarA.a;
            if (!list.isEmpty()) {
                return list.get(0).getAbsolutePath() + str + queryParameter;
            }
        }
        if (path.equals("download_external")) {
            List<File> list2 = fVarA.b;
            if (!list2.isEmpty()) {
                return list2.get(0).getAbsolutePath() + str + queryParameter;
            }
        }
        return null;
    }

    @Override // androidx.core.content.FileProvider, android.content.ContentProvider
    public final void attachInfo(Context context, ProviderInfo providerInfo) {
        super.attachInfo(context, providerInfo);
        if (providerInfo.exported) {
            throw new SecurityException("Provider must not be exported");
        }
        if (!providerInfo.grantUriPermissions) {
            throw new SecurityException("Provider must grant uri permissions");
        }
    }

    @Override // androidx.core.content.FileProvider, android.content.ContentProvider
    public final int delete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException("No external deletes");
    }

    @Override // androidx.core.content.FileProvider, android.content.ContentProvider
    public final String getType(Uri uri) {
        if (uri == null) {
            return null;
        }
        String queryParameter = uri.getQueryParameter("file");
        if (TextUtils.isEmpty(queryParameter)) {
            return null;
        }
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(Uri.parse(queryParameter).toString()));
    }

    @Override // androidx.core.content.FileProvider, android.content.ContentProvider
    public final Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("No external inserts");
    }

    @Override // androidx.core.content.FileProvider, android.content.ContentProvider
    public final ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        int i2;
        String strD = d(uri, new b.c());
        if (strD == null) {
            throw new FileNotFoundException();
        }
        if ("r".equals(str)) {
            i2 = 268435456;
        } else if ("w".equals(str) || "wt".equals(str)) {
            i2 = 738197504;
        } else if ("wa".equals(str)) {
            i2 = 704643072;
        } else if ("rw".equals(str)) {
            i2 = 939524096;
        } else {
            if (!"rwt".equals(str)) {
                throw new IllegalArgumentException(C0175b.j("Invalid mode: ", str));
            }
            i2 = 1006632960;
        }
        return ParcelFileDescriptor.open(new File(strD), i2);
    }

    @Override // androidx.core.content.FileProvider, android.content.ContentProvider
    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        int i2;
        if (strArr == null) {
            strArr = i;
        }
        String[] strArr3 = new String[strArr.length];
        Object[] objArr = new Object[strArr.length];
        String strD = d(uri, new b.c());
        if (TextUtils.isEmpty(strD)) {
            return new MatrixCursor(strArr3, 1);
        }
        File file = new File(strD);
        if (!file.exists() || !file.isFile()) {
            return new MatrixCursor(strArr3, 1);
        }
        int i3 = 0;
        for (String str3 : strArr) {
            if ("_display_name".equals(str3)) {
                strArr3[i3] = "_display_name";
                i2 = i3 + 1;
                objArr[i3] = file.getName();
            } else if ("_size".equals(str3)) {
                strArr3[i3] = "_size";
                i2 = i3 + 1;
                objArr[i3] = Long.valueOf(file.length());
            }
            i3 = i2;
        }
        String[] strArr4 = new String[i3];
        System.arraycopy(strArr3, 0, strArr4, 0, i3);
        Object[] objArr2 = new Object[i3];
        System.arraycopy(objArr, 0, objArr2, 0, i3);
        MatrixCursor matrixCursor = new MatrixCursor(strArr4, 1);
        matrixCursor.addRow(objArr2);
        return matrixCursor;
    }

    @Override // androidx.core.content.FileProvider, android.content.ContentProvider
    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("No external updates");
    }
}
