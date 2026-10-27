package com.byd.util;

import android.car.Car;
import android.car.VehicleAreaDoor;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.media.ExifInterface;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import androidx.core.content.FileProvider;
import com.byd.bean.FileBean;
import com.byd.dilink.provider.DiLinkBluetoothDeviceConstants;
import java.io.File;
import java.io.FileFilter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Stack;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Comparator<File> f2983a = new b();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final ThreadLocal<DecimalFormat> f2984b = new c();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static SimpleDateFormat f2985c = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss", Locale.getDefault());
    private static final String[] d;

    class a implements FileFilter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f2986a;

        a(int i) {
            this.f2986a = i;
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            if (file.isFile() && file.exists() && file.length() != 0) {
                String name = file.getName();
                if (name.startsWith(".")) {
                    return false;
                }
                int i = this.f2986a;
                return i != 1 ? i != 2 ? m.c(name) || m.e(name) : m.e(name) : m.c(name);
            }
            w.c("illegal file : " + file.getPath() + " " + file.exists() + " " + file.length());
            return false;
        }
    }

    class b implements Comparator<File> {
        b() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(File file, File file2) {
            long jLastModified = file2.lastModified() - file.lastModified();
            if (jLastModified < 0) {
                return -1;
            }
            return jLastModified > 0 ? 1 : 0;
        }
    }

    class c extends ThreadLocal<DecimalFormat> {
        c() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public DecimalFormat initialValue() {
            return new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(Locale.CHINESE));
        }
    }

    public interface d {
        boolean isCancel();

        void onProgress(long j, long j2);
    }

    static {
        if (Build.VERSION.SDK_INT >= 29) {
            d = new String[]{"datetaken", "date_modified", "date_added"};
        } else {
            d = new String[]{"date_modified", "date_added"};
        }
    }

    private static void A(Context context, int i, File file, b.a.k.s sVar) throws InterruptedException {
        x(context, new a(i), file, sVar);
    }

    public static boolean B(String str, String str2, d dVar) {
        return new File(str).isDirectory() ? C(str, str2, dVar) : D(str, str2, dVar);
    }

    private static boolean C(String str, String str2, d dVar) {
        if (b(str, str2, dVar)) {
            return f(new File(str));
        }
        return false;
    }

    private static boolean D(String str, String str2, d dVar) throws Throwable {
        boolean zC = c(str, str2, dVar);
        return zC ? e(str) : zC;
    }

    public static void E(File file, long j) {
        try {
            ExifInterface exifInterface = new ExifInterface(file.getAbsolutePath());
            exifInterface.setAttribute("DateTimeOriginal", f2985c.format(new Date(j)));
            exifInterface.saveAttributes();
            try {
                ParcelFileDescriptor parcelFileDescriptorOpen = ParcelFileDescriptor.open(file, VehicleAreaDoor.DOOR_HOOD);
                parcelFileDescriptorOpen.getFileDescriptor().sync();
                parcelFileDescriptorOpen.close();
            } catch (Exception e) {
                e.printStackTrace();
                w.c("saveFileTimeExif exception : " + e.getMessage());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static boolean a(String str, String str2, d dVar) {
        return new File(str).isDirectory() ? b(str, str2, dVar) : c(str, str2, dVar);
    }

    private static boolean b(String str, String str2, d dVar) {
        String str3;
        String str4;
        LinkedList linkedList = new LinkedList();
        File file = new File(str);
        String parent = file.getParent();
        linkedList.addLast(file);
        int i = 0;
        while (!linkedList.isEmpty()) {
            File file2 = (File) linkedList.removeFirst();
            String absolutePath = file2.getAbsolutePath();
            String str5 = File.separator;
            if (str2.endsWith(str5)) {
                str3 = str2 + absolutePath.substring(parent.length());
            } else {
                str3 = str2 + str5 + absolutePath.substring(parent.length());
            }
            new File(str3).mkdir();
            File[] fileArrListFiles = file2.listFiles();
            int i2 = 0;
            while (true) {
                if (i2 < fileArrListFiles.length) {
                    File file3 = fileArrListFiles[i2];
                    if (file3.isDirectory()) {
                        linkedList.addLast(file3);
                    } else {
                        String absolutePath2 = file3.getAbsolutePath();
                        String str6 = File.separator;
                        if (str2.endsWith(str6)) {
                            str4 = str2 + absolutePath2.substring(parent.length());
                        } else {
                            str4 = str2 + str6 + absolutePath2.substring(parent.length());
                        }
                        if (!c(absolutePath2, str4, dVar)) {
                            i++;
                            break;
                        }
                    }
                    i2++;
                }
            }
        }
        return i == 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0116 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x00f6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0134 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:118:0x00ec A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:120:0x012a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:122:0x010a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0120 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0100 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:147:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00e6 A[Catch: all -> 0x0113, TRY_LEAVE, TryCatch #10 {all -> 0x0113, blocks: (B:8:0x001b, B:9:0x002e, B:11:0x0035, B:12:0x0038, B:14:0x003e, B:15:0x0042, B:17:0x004c, B:20:0x0053, B:21:0x0057, B:61:0x00c0, B:63:0x00e6), top: B:126:0x0001 }] */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r5v3, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r5v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean c(java.lang.String r13, java.lang.String r14, com.byd.util.n.d r15) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 317
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.util.n.c(java.lang.String, java.lang.String, com.byd.util.n$d):boolean");
    }

    public static long d(String str) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            try {
                mediaMetadataRetriever.setDataSource(str);
                long j = Long.parseLong(mediaMetadataRetriever.extractMetadata(9));
                try {
                    mediaMetadataRetriever.release();
                    return j;
                } catch (Exception e) {
                    e.printStackTrace();
                    return j;
                }
            } finally {
                try {
                    mediaMetadataRetriever.release();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        } catch (Exception e3) {
            w.c("decodeDurationFromFile exception : " + e3.getMessage() + " | " + str);
            return 0L;
        }
    }

    private static boolean e(String str) {
        try {
            return new File(str).delete();
        } catch (Exception e) {
            Log.e("m_tag", "delFile exception : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static boolean f(File file) {
        Stack stack = new Stack();
        stack.push(file);
        while (!stack.isEmpty()) {
            File file2 = (File) stack.peek();
            File[] fileArrListFiles = file2.listFiles();
            if (fileArrListFiles == null || fileArrListFiles.length == 0) {
                file2.delete();
                stack.pop();
            } else {
                for (File file3 : fileArrListFiles) {
                    if (file3.isDirectory()) {
                        stack.push(file3);
                    } else {
                        file3.delete();
                    }
                }
            }
        }
        return true;
    }

    public static boolean g(Context context, List<String> list) {
        if (list != null && list.size() != 0) {
            ContentResolver contentResolver = context.getContentResolver();
            Uri contentUri = MediaStore.Files.getContentUri("external");
            int size = list.size();
            ArrayList<ContentProviderOperation> arrayList = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                arrayList.add(ContentProviderOperation.newDelete(contentUri).withSelection("_data=?", new String[]{list.get(i)}).build());
            }
            try {
                z = contentResolver.applyBatch("media", arrayList).length == size;
                w.c("deleteFromContent applyBatch : " + size + " result:" + z);
            } catch (Exception e) {
                e.printStackTrace();
                w.c("deleteFromContent exception : " + e.getMessage());
            }
        }
        return z;
    }

    public static FileBean h(Context context, File file) {
        long jR;
        int i;
        String name = file.getName();
        boolean zE = m.e(name);
        String path = file.getPath();
        long jP = p(context, path);
        if (jP == 0) {
            jP = file.lastModified();
        }
        long j = jP;
        if (zE) {
            jR = r(context, path);
            i = 2;
        } else {
            jR = -1;
            i = 1;
        }
        long j2 = jR;
        FileBean fileBean = new FileBean(name, path, 2, i, j);
        if (j2 > 0) {
            fileBean.setVideoDuration(j2);
        }
        return fileBean;
    }

    public static String i(long j) {
        StringBuilder sb;
        String str;
        if (j >= 1073741824) {
            sb = new StringBuilder();
            sb.append(f2984b.get().format(j / 1.073741824E9d));
            str = "GB";
        } else if (j >= 1048576) {
            sb = new StringBuilder();
            sb.append(f2984b.get().format(j / 1048576.0d));
            str = "MB";
        } else if (j >= 1024) {
            sb = new StringBuilder();
            sb.append(f2984b.get().format(j / 1024.0d));
            str = "KB";
        } else {
            sb = new StringBuilder();
            sb.append(f2984b.get().format(j));
            str = "B";
        }
        sb.append(str);
        return sb.toString();
    }

    public static Uri j(Context context, File file) {
        Uri uri = m.b(m.a(file.getName())) ? MediaStore.Images.Media.EXTERNAL_CONTENT_URI : MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        Cursor cursorQuery = context.getContentResolver().query(uri, new String[]{DiLinkBluetoothDeviceConstants._ID}, "_data=?", new String[]{file.getPath()}, null);
        if (cursorQuery != null) {
            uriWithAppendedId = cursorQuery.moveToFirst() ? ContentUris.withAppendedId(uri, cursorQuery.getInt(0)) : null;
            cursorQuery.close();
        }
        return uriWithAppendedId;
    }

    public static Uri k(Context context, File file) {
        Uri uriJ = j(context, file);
        if (uriJ != null) {
            return uriJ;
        }
        return FileProvider.getUriForFile(context, context.getApplicationInfo().packageName + ".fileProvider", new File(file.getPath()));
    }

    private static String l(Context context, Uri uri, String str, String[] strArr) {
        String string = null;
        try {
            Cursor cursorQuery = context.getContentResolver().query(uri, null, str, strArr, null);
            if (cursorQuery != null) {
                String[] columnNames = cursorQuery.getColumnNames();
                boolean z = false;
                int i = 0;
                while (true) {
                    if (i >= columnNames.length) {
                        break;
                    }
                    if ("_data".equals(columnNames[i])) {
                        z = true;
                        break;
                    }
                    i++;
                }
                if (z && cursorQuery.moveToFirst()) {
                    string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data"));
                }
                cursorQuery.close();
            }
        } catch (Exception unused) {
        }
        return string;
    }

    public static String m(Context context) {
        File externalCacheDir = context.getExternalCacheDir();
        if (externalCacheDir == null) {
            externalCacheDir = new File("/sdcard/Android/data/" + context.getApplicationInfo().packageName + "/cache");
        }
        if (!externalCacheDir.exists()) {
            externalCacheDir.mkdirs();
        }
        return externalCacheDir.getPath();
    }

    public static String n(String str) {
        int iLastIndexOf = str.lastIndexOf(File.separator);
        return iLastIndexOf > -1 ? str.substring(iLastIndexOf + 1) : str;
    }

    public static String o(String str) {
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
        return file.getPath() + "/" + System.currentTimeMillis();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0056 A[PHI: r2
  0x0056: PHI (r2v5 long) = (r2v4 long), (r2v7 long) binds: [B:14:0x0037, B:16:0x0048] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static long p(android.content.Context r6, java.lang.String r7) {
        /*
            android.content.ContentResolver r0 = r6.getContentResolver()
            android.net.Uri r1 = b.a.k.i.d
            java.lang.String[] r2 = com.byd.util.n.d
            r6 = 1
            java.lang.String[] r4 = new java.lang.String[r6]
            r6 = 0
            r4[r6] = r7
            java.lang.String r3 = "_data=?"
            r5 = 0
            android.database.Cursor r7 = r0.query(r1, r2, r3, r4, r5)
            r0 = 0
            if (r7 == 0) goto L5a
            boolean r2 = r7.moveToFirst()
            if (r2 == 0) goto L57
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 29
            if (r2 < r3) goto L34
            java.lang.String r6 = r7.getString(r6)
            boolean r2 = android.text.TextUtils.isEmpty(r6)
            if (r2 != 0) goto L34
            long r2 = java.lang.Long.parseLong(r6)     // Catch: java.lang.Exception -> L34
            goto L35
        L34:
            r2 = r0
        L35:
            int r6 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r6 != 0) goto L56
            java.lang.String r6 = "date_modified"
            int r6 = r7.getColumnIndexOrThrow(r6)
            long r2 = r7.getLong(r6)
            r4 = 1000(0x3e8, double:4.94E-321)
            long r2 = r2 * r4
            int r6 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r6 != 0) goto L56
            java.lang.String r6 = "date_added"
            int r6 = r7.getColumnIndexOrThrow(r6)
            long r0 = r7.getLong(r6)
            long r0 = r0 * r4
            goto L57
        L56:
            r0 = r2
        L57:
            r7.close()
        L5a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.util.n.p(android.content.Context, java.lang.String):long");
    }

    public static String q(Context context, Uri uri) {
        Uri uri2 = null;
        if ((Build.VERSION.SDK_INT >= 19) && DocumentsContract.isDocumentUri(context, uri)) {
            if (t(uri)) {
                String[] strArrSplit = DocumentsContract.getDocumentId(uri).split(":");
                if ("primary".equalsIgnoreCase(strArrSplit[0])) {
                    return Environment.getExternalStorageDirectory() + "/" + strArrSplit[1];
                }
            } else {
                if (s(uri)) {
                    return l(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), null, null);
                }
                if (u(uri)) {
                    String[] strArrSplit2 = DocumentsContract.getDocumentId(uri).split(":");
                    String str = strArrSplit2[0];
                    if ("image".equals(str)) {
                        uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(str)) {
                        uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if (Car.AUDIO_SERVICE.equals(str)) {
                        uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }
                    return l(context, uri2, "_id=?", new String[]{strArrSplit2[1]});
                }
            }
        } else {
            if ("content".equalsIgnoreCase(uri.getScheme())) {
                return l(context, uri, null, null);
            }
            if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        }
        if ("com.android.mtp.documents".equals(uri.getAuthority())) {
            return null;
        }
        return uri.getPath();
    }

    public static long r(Context context, String str) {
        Cursor cursorQuery = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]{"duration"}, "_data=?", new String[]{str}, null);
        if (cursorQuery != null) {
            j = cursorQuery.moveToFirst() ? cursorQuery.getLong(0) : -1L;
            cursorQuery.close();
        }
        return j;
    }

    private static boolean s(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private static boolean t(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private static boolean u(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean v(String str) {
        return Pattern.compile("^(\\-|\\+)?\\d+(\\.\\d+)?$").matcher(str).matches();
    }

    public static void w(Context context, FileBean fileBean) {
        double dJ;
        double dJ2;
        try {
            ExifInterface exifInterface = new ExifInterface(fileBean.getPath());
            String attribute = exifInterface.getAttribute("GPSLatitudeRef");
            String attribute2 = exifInterface.getAttribute("GPSLongitudeRef");
            String attribute3 = exifInterface.getAttribute("GPSLatitude");
            String attribute4 = exifInterface.getAttribute("GPSLongitude");
            if (attribute != null) {
                if (v(attribute)) {
                    dJ2 = Double.parseDouble(attribute);
                } else if (attribute3 != null) {
                    dJ2 = e.J(attribute3, attribute);
                }
                fileBean.setLat(dJ2);
            }
            if (attribute2 != null) {
                if (v(attribute2)) {
                    dJ = Double.parseDouble(attribute2);
                } else if (attribute4 != null) {
                    dJ = e.J(attribute4, attribute2);
                }
                fileBean.setLng(dJ);
            }
            w.c("file location :" + attribute + attribute2 + "|" + String.valueOf(attribute4).replaceAll("0", "x"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void x(Context context, FileFilter fileFilter, File file, b.a.k.s sVar) throws InterruptedException {
        w.c("load from folder normally");
        File[] fileArrListFiles = file.listFiles(fileFilter);
        if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
            return;
        }
        for (File file2 : fileArrListFiles) {
            Thread.sleep(0L);
            sVar.a(h(context, file2));
        }
    }

    public static void y(Context context, String str, int i, b.a.k.s sVar) throws InterruptedException {
        int i2;
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            w.c("folder not exists : " + str);
            return;
        }
        if (str.startsWith("/storage/FFFF-FFF")) {
            w.c("load other screen folder : " + str);
            A(context, i, file, sVar);
            return;
        }
        boolean z = true;
        Cursor cursorQuery = context.getContentResolver().query(b.a.k.i.d, new String[]{DiLinkBluetoothDeviceConstants._ID}, "_data=?", new String[]{str}, null);
        if (cursorQuery != null) {
            if (cursorQuery.moveToFirst()) {
                i2 = cursorQuery.getInt(0);
            } else {
                i2 = 0;
                z = false;
            }
            cursorQuery.close();
        } else {
            i2 = 0;
            z = false;
        }
        if (!z) {
            A(context, i, file, sVar);
            return;
        }
        if (!x.k(context, file, i2)) {
            A(context, i, file, sVar);
            return;
        }
        w.c("load from folder by content : " + str + " | parentId : " + i2);
        try {
            new b.a.k.j(context, false, i, i2).c(sVar);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InterruptedException("ImageAndVideoLoaderFromFolder exception");
        }
    }

    public static void z(Context context, String str, b.a.k.s sVar) throws InterruptedException {
        y(context, str, 0, sVar);
    }
}
