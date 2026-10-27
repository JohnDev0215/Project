package com.byd.music.utils.log;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import androidx.documentfile.provider.DocumentFile;
import com.byd.music.utils.LogUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class DocumentsUtils {
    private static final String TAG = DocumentsUtils.class.getSimpleName();
    private static List<String> sExtSdCardPaths = new ArrayList();
    public static String mRootPath = "";

    private DocumentsUtils() {
    }

    public static void cleanCache() {
        sExtSdCardPaths.clear();
    }

    public static String[] getExtSdCardPaths(Context context) {
        if (sExtSdCardPaths.size() > 0) {
            return (String[]) sExtSdCardPaths.toArray(new String[0]);
        }
        for (File file : context.getExternalFilesDirs("external")) {
            if (file != null && !file.equals(context.getExternalFilesDir("external"))) {
                int iLastIndexOf = file.getAbsolutePath().lastIndexOf("/Android/data");
                if (iLastIndexOf < 0) {
                    LogUtil.w(TAG, "Unexpected external file dir: " + file.getAbsolutePath());
                } else {
                    String strSubstring = file.getAbsolutePath().substring(0, iLastIndexOf);
                    try {
                        strSubstring = new File(strSubstring).getCanonicalPath();
                    } catch (IOException unused) {
                    }
                    sExtSdCardPaths.add(strSubstring);
                }
            }
        }
        if (sExtSdCardPaths.isEmpty()) {
            sExtSdCardPaths.add("/storage/sdcard1");
        }
        return (String[]) sExtSdCardPaths.toArray(new String[0]);
    }

    private static String getExtSdCardFolder(File file, Context context) {
        String str = mRootPath;
        if (str == null) {
            return null;
        }
        return str;
    }

    public static boolean isOnExtSdCard(File file, Context context) {
        return getExtSdCardFolder(file, context) != null;
    }

    public static DocumentFile getDocumentFile(File file, boolean z, Context context) {
        String strSubstring;
        boolean z2;
        String canonicalPath;
        if (Build.VERSION.SDK_INT <= 19) {
            return DocumentFile.fromFile(file);
        }
        String extSdCardFolder = getExtSdCardFolder(file, context);
        if (extSdCardFolder == null) {
            return null;
        }
        try {
            canonicalPath = file.getCanonicalPath();
        } catch (IOException | Exception unused) {
            return null;
        } catch (Exception unused2) {
        }
        if (extSdCardFolder.equals(canonicalPath)) {
            strSubstring = null;
            z2 = true;
        } else {
            strSubstring = canonicalPath.substring(extSdCardFolder.length() + 1);
            z2 = false;
        }
        String documentSP = LogControlerUtil.getInstance().getDocumentSP(extSdCardFolder, null);
        Uri uri = documentSP != null ? Uri.parse(documentSP) : null;
        if (uri == null) {
            return null;
        }
        DocumentFile documentFileFromTreeUri = DocumentFile.fromTreeUri(context, uri);
        if (z2) {
            return documentFileFromTreeUri;
        }
        String[] strArrSplit = strSubstring.split("/");
        for (int i = 0; i < strArrSplit.length; i++) {
            DocumentFile documentFileFindFile = documentFileFromTreeUri.findFile(strArrSplit[i]);
            if (documentFileFindFile != null && documentFileFindFile.exists()) {
                documentFileFromTreeUri = documentFileFindFile;
            } else if (i < strArrSplit.length - 1 || z) {
                documentFileFromTreeUri = documentFileFromTreeUri.createDirectory(strArrSplit[i]);
            } else {
                documentFileFromTreeUri = documentFileFromTreeUri.createFile("", strArrSplit[i]);
            }
        }
        return documentFileFromTreeUri;
    }

    public static boolean mkdirs(Context context, File file) {
        boolean zMkdirs = file.mkdirs();
        if (zMkdirs || !isOnExtSdCard(file, context)) {
            return zMkdirs;
        }
        DocumentFile documentFile = getDocumentFile(file, true, context);
        return documentFile != null && documentFile.canWrite();
    }

    public static boolean delete(Context context, File file) {
        DocumentFile documentFile;
        boolean zDelete = file.delete();
        return (zDelete || !isOnExtSdCard(file, context) || (documentFile = getDocumentFile(file, false, context)) == null) ? zDelete : documentFile.delete();
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean canWrite(java.io.File r4) {
        /*
            boolean r0 = r4.exists()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L10
            boolean r0 = r4.canWrite()
            if (r0 == 0) goto L10
            r0 = r1
            goto L11
        L10:
            r0 = r2
        L11:
            if (r0 != 0) goto L40
            boolean r3 = r4.exists()
            if (r3 != 0) goto L40
            boolean r3 = r4.isDirectory()     // Catch: java.io.IOException -> L3c
            if (r3 != 0) goto L2c
            boolean r3 = r4.createNewFile()     // Catch: java.io.IOException -> L3c
            if (r3 == 0) goto L39
            boolean r4 = r4.delete()     // Catch: java.io.IOException -> L3c
            if (r4 == 0) goto L39
            goto L3a
        L2c:
            boolean r3 = r4.mkdirs()     // Catch: java.io.IOException -> L3c
            if (r3 == 0) goto L39
            boolean r4 = r4.delete()     // Catch: java.io.IOException -> L3c
            if (r4 == 0) goto L39
            goto L3a
        L39:
            r1 = r2
        L3a:
            r0 = r1
            goto L40
        L3c:
            r4 = move-exception
            r4.printStackTrace()
        L40:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.utils.log.DocumentsUtils.canWrite(java.io.File):boolean");
    }

    public static boolean canWrite(Context context, File file) {
        boolean zCanWrite = canWrite(file);
        if (zCanWrite || !isOnExtSdCard(file, context)) {
            return zCanWrite;
        }
        DocumentFile documentFile = getDocumentFile(file, true, context);
        return documentFile != null && documentFile.canWrite();
    }

    public static boolean renameTo(Context context, File file, File file2) {
        DocumentFile documentFileFromFile;
        boolean zRenameTo = file.renameTo(file2);
        if (!zRenameTo && isOnExtSdCard(file2, context)) {
            if (isOnExtSdCard(file, context)) {
                documentFileFromFile = getDocumentFile(file, false, context);
            } else {
                documentFileFromFile = DocumentFile.fromFile(file);
            }
            DocumentFile documentFile = getDocumentFile(file2.getParentFile(), true, context);
            if (documentFileFromFile != null && documentFile != null) {
                try {
                    if (file.getParent().equals(file2.getParent())) {
                        zRenameTo = documentFileFromFile.renameTo(file2.getName());
                    } else if (Build.VERSION.SDK_INT >= 24) {
                        zRenameTo = DocumentsContract.moveDocument(context.getContentResolver(), documentFileFromFile.getUri(), documentFileFromFile.getParentFile().getUri(), documentFile.getUri()) != null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return zRenameTo;
    }

    public static InputStream getInputStream(Context context, File file) throws Throwable {
        InputStream fileInputStream = null;
        try {
            if (!canWrite(file) && isOnExtSdCard(file, context)) {
                DocumentFile documentFile = getDocumentFile(file, false, context);
                if (documentFile != null && documentFile.canWrite()) {
                    fileInputStream = context.getContentResolver().openInputStream(documentFile.getUri());
                }
            } else {
                fileInputStream = new FileInputStream(file);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileInputStream;
    }

    public static OutputStream getOutputStream(Context context, File file) {
        OutputStream fileOutputStream = null;
        try {
            if (!canWrite(file) && isOnExtSdCard(file, context)) {
                DocumentFile documentFile = getDocumentFile(file, false, context);
                if (documentFile != null && documentFile.canWrite()) {
                    fileOutputStream = context.getContentResolver().openOutputStream(documentFile.getUri());
                }
            } else {
                fileOutputStream = new FileOutputStream(file);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileOutputStream;
    }

    public static boolean saveTreeUri(Context context, String str, Uri uri) {
        DocumentFile documentFileFromTreeUri = DocumentFile.fromTreeUri(context, uri);
        if (documentFileFromTreeUri != null && documentFileFromTreeUri.canWrite()) {
            LogControlerUtil.getInstance().setDocumentSP(str, uri.toString());
            return true;
        }
        LogUtil.e(TAG, "no write permission: " + str);
        return false;
    }

    public static boolean checkWritableRootPath(Context context, String str) {
        mRootPath = str;
        File file = new File(str);
        if (file.canWrite()) {
            return false;
        }
        if (isOnExtSdCard(file, context)) {
            DocumentFile documentFile = getDocumentFile(file, true, context);
            return documentFile == null || !documentFile.canWrite();
        }
        String documentSP = LogControlerUtil.getInstance().getDocumentSP(str, "");
        if (documentSP == null || documentSP.isEmpty()) {
            return true;
        }
        DocumentFile documentFileFromTreeUri = DocumentFile.fromTreeUri(context, Uri.parse(documentSP));
        return documentFileFromTreeUri == null || !documentFileFromTreeUri.canWrite();
    }
}
