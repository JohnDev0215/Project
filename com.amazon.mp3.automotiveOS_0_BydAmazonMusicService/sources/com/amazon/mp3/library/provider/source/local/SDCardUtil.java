package com.amazon.mp3.library.provider.source.local;

import android.content.Context;
import android.content.UriPermission;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import androidx.annotation.WorkerThread;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.extensions.ContextKt;
import com.amazon.music.externalstorage.StorageUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes3.dex */
public class SDCardUtil {
    public static final String TAG = "SDCardUtil";

    public static boolean isSDCardRootUri(Uri uri) {
        return DocumentsContract.getTreeDocumentId(uri).endsWith(":");
    }

    public static boolean hasSDCardPermission(Context context) {
        Iterator<UriPermission> it = context.getContentResolver().getPersistedUriPermissions().iterator();
        while (it.hasNext()) {
            if (isSDCardRootUri(it.next().getUri())) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x002c, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static androidx.documentfile.provider.DocumentFile getDocumentFileBySAF(android.content.Context r8, java.lang.String r9) {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r9)
            java.lang.String r0 = getExtSdCardFolder(r8, r0)
            r1 = 0
            if (r0 != 0) goto Ld
            return r1
        Ld:
            java.io.File r2 = new java.io.File     // Catch: java.io.IOException -> L67
            r2.<init>(r9)     // Catch: java.io.IOException -> L67
            java.lang.String r9 = r2.getCanonicalPath()     // Catch: java.io.IOException -> L67
            int r0 = r0.length()
            int r0 = r0 + 1
            java.lang.String r9 = r9.substring(r0)
            android.content.ContentResolver r0 = r8.getContentResolver()
            java.util.List r0 = r0.getPersistedUriPermissions()
            java.util.Iterator r0 = r0.iterator()
        L2c:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L66
            java.lang.Object r2 = r0.next()
            android.content.UriPermission r2 = (android.content.UriPermission) r2
            android.net.Uri r3 = r2.getUri()
            boolean r3 = isSDCardRootUri(r3)
            if (r3 != 0) goto L43
            goto L2c
        L43:
            android.net.Uri r2 = r2.getUri()
            androidx.documentfile.provider.DocumentFile r2 = androidx.documentfile.provider.DocumentFile.fromTreeUri(r8, r2)
            java.lang.String r3 = "/"
            java.lang.String[] r3 = r9.split(r3)
            int r4 = r3.length
            int r5 = r3.length
            r6 = 0
        L54:
            if (r6 >= r5) goto L2c
            r7 = r3[r6]
            androidx.documentfile.provider.DocumentFile r2 = r2.findFile(r7)
            if (r2 == 0) goto L2c
            int r4 = r4 + (-1)
            if (r4 != 0) goto L63
            return r2
        L63:
            int r6 = r6 + 1
            goto L54
        L66:
            return r1
        L67:
            r8 = move-exception
            java.lang.String r9 = com.amazon.mp3.library.provider.source.local.SDCardUtil.TAG
            java.lang.String r8 = r8.getMessage()
            com.amazon.mp3.util.Log.error(r9, r8)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.provider.source.local.SDCardUtil.getDocumentFileBySAF(android.content.Context, java.lang.String):androidx.documentfile.provider.DocumentFile");
    }

    public static String getExtSdCardFolder(final Context context, File file) {
        try {
            for (String str : getExtSdCardPaths(context)) {
                if (file.getCanonicalPath().startsWith(str)) {
                    return str;
                }
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            Log.error(TAG, e.getMessage());
            return null;
        }
    }

    public static String[] getExtSdCardPaths(Context context) {
        ArrayList arrayList = new ArrayList();
        for (File file : context.getExternalFilesDirs("external")) {
            if (file != null && !file.equals(context.getExternalFilesDir("external"))) {
                int iLastIndexOf = file.getAbsolutePath().lastIndexOf("/Android/data");
                if (iLastIndexOf < 0) {
                    Log.error(TAG, "Unexpected external file dir: " + file.getAbsolutePath());
                } else {
                    String strSubstring = file.getAbsolutePath().substring(0, iLastIndexOf);
                    try {
                        strSubstring = new File(strSubstring).getCanonicalPath();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.error(TAG, e.getMessage());
                    }
                    arrayList.add(strSubstring);
                }
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    @WorkerThread
    public static boolean isSdCardReadOnly(final Context context) {
        return Environment.getExternalStorageState().equals("mounted_ro") || !StorageUtil.canDeviceWriteToSdCard(context, Environment.DIRECTORY_MUSIC);
    }

    public static void showSdCardReadOnlyMessage(final Context context) {
        ContextKt.runOnUiThread(context, new Function1() { // from class: com.amazon.mp3.library.provider.source.local.SDCardUtil$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SDCardUtil.lambda$showSdCardReadOnlyMessage$0((Context) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Unit lambda$showSdCardReadOnlyMessage$0(Context context) {
        BauhausToastUtils.showDefaultToast(context, R.string.sdcard_storage_error_read_only, 0);
        return Unit.INSTANCE;
    }
}
