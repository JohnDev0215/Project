package com.byd.cleardata.i;

import android.annotation.SuppressLint;
import android.app.usage.StorageStatsManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.media.ExifInterface;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.RemoteException;
import android.os.StatFs;
import android.os.UserHandle;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import androidx.core.content.FileProvider;
import com.byd.cleardata.FileApplication;
import com.byd.cleardata.R;
import com.byd.cleardata.ui.f.j;
import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String[] f2694a = {"B", "KB", "MB", "GB", "TB"};

    class a extends SimpleFileVisitor<Path> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.byd.cleardata.g.b f2695a;

        a(com.byd.cleardata.g.b bVar) {
            this.f2695a = bVar;
        }

        @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public FileVisitResult postVisitDirectory(Path path, IOException iOException) throws IOException {
            Files.delete(path);
            return e.I(this.f2695a, true);
        }

        @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
            Files.delete(path);
            return e.I(this.f2695a, true);
        }
    }

    class b extends SimpleFileVisitor<Path> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ long[] f2696a;

        b(long[] jArr) {
            this.f2696a = jArr;
        }

        @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes basicFileAttributes) {
            long[] jArr = this.f2696a;
            jArr[0] = jArr[0] + Files.size(path);
            return FileVisitResult.CONTINUE;
        }

        @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) {
            long[] jArr = this.f2696a;
            jArr[0] = jArr[0] + Files.size(path);
            return FileVisitResult.CONTINUE;
        }

        @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public FileVisitResult visitFileFailed(Path path, IOException iOException) {
            c.d("visitFileFailed", iOException);
            try {
                long[] jArr = this.f2696a;
                jArr[0] = jArr[0] + Files.size(path);
            } catch (Throwable th) {
                c.h("visitFileFailed getSize error", th);
            }
            return FileVisitResult.CONTINUE;
        }
    }

    public static long A(Context context) {
        long cacheBytes = 0;
        if (context == null) {
            return 0L;
        }
        StorageStatsManager storageStatsManager = (StorageStatsManager) context.getSystemService("storagestats");
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
        c.b(", TotalCache, packages.size = " + installedPackages.size());
        for (PackageInfo packageInfo : installedPackages) {
            try {
                cacheBytes += storageStatsManager.queryStatsForPackage(StorageManager.UUID_DEFAULT, packageInfo.packageName, UserHandle.getUserHandleForUid(packageManager.getApplicationInfo(packageInfo.packageName, 128).uid)).getCacheBytes();
            } catch (PackageManager.NameNotFoundException | IOException | RuntimeException e) {
                c.g(", e: " + e.toString());
            }
        }
        c.b(", TotalCache, size = " + cacheBytes);
        return cacheBytes;
    }

    public static long B(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        try {
            StatFs statFs = new StatFs(str);
            return statFs.getBlockCountLong() * statFs.getBlockSizeLong();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0L;
        }
    }

    public static Uri C(Context context, com.byd.cleardata.d.d dVar) {
        return D(context, dVar.w());
    }

    public static Uri D(Context context, File file) {
        return FileProvider.e(context, "com.byd.cleardata.fileprovider", file);
    }

    public static String E(File file) throws IOException {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            if (file != null) {
                try {
                    mediaMetadataRetriever.setDataSource(file.getPath());
                } catch (Exception e) {
                    Log.e("TAG", "MediaMetadataRetriever exception " + e);
                    mediaMetadataRetriever.release();
                    return null;
                }
            }
            return mediaMetadataRetriever.extractMetadata(18) + "*" + mediaMetadataRetriever.extractMetadata(19);
        } finally {
            mediaMetadataRetriever.release();
        }
    }

    @SuppressLint({"NewApi"})
    public static boolean F(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        return Paths.get(str2, new String[0]).startsWith(Paths.get(str, new String[0]));
    }

    @SuppressLint({"NewApi"})
    public static boolean G(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return Files.isDirectory(Paths.get(str, new String[0]), LinkOption.NOFOLLOW_LINKS);
            } catch (InvalidPathException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean H(List<?> list) {
        return (list == null || list.isEmpty()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"NewApi"})
    public static FileVisitResult I(com.byd.cleardata.g.b bVar, boolean z) throws IOException {
        if (z) {
            return (bVar == null || (bVar.a(z) && z)) ? FileVisitResult.CONTINUE : FileVisitResult.TERMINATE;
        }
        throw new IOException();
    }

    public static boolean J(Context context, com.byd.cleardata.d.d dVar) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addFlags(1);
            intent.setDataAndType(C(context, dVar), r(dVar.w()));
            context.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
            e = e;
            q.a(R.string.no_app_available);
            c.h("openFile failed", e);
            return false;
        } catch (Exception e2) {
            e = e2;
            c.h("openFile failed", e);
            return false;
        }
    }

    public static File K(File file, String str) {
        if (file == null || TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            String str2 = file.getParent() + File.separator + str;
            if (file.renameTo(new File(str2))) {
                return new File(str2);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File b(String str, String str2) {
        File file = new File(str);
        if (!file.exists()) {
            return null;
        }
        File file2 = new File(file, str2);
        if (file2.exists() || !file2.mkdir()) {
            return null;
        }
        return file2;
    }

    public static void c(Context context, j.g gVar) {
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
        c.b("----clearAllAppCache----packages.size = " + installedPackages.size());
        if (gVar != null) {
            gVar.a(installedPackages.size());
        }
        c.b("-----clearAllAppCache-----start clear.");
        Iterator<PackageInfo> it = installedPackages.iterator();
        while (it.hasNext()) {
            packageManager.deleteApplicationCacheFiles(it.next().packageName, gVar);
        }
        if (gVar != null) {
            try {
                gVar.onRemoveCompleted((String) null, true);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        c.b("------clearAllAppCache-----clear end.");
    }

    public static String d(float f) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return decimalFormat.format(f);
    }

    public static boolean e(File file) {
        if (file == null || !file.exists()) {
            return true;
        }
        if (file.isFile()) {
            if (file.delete()) {
                return true;
            }
            m.a("rm -rf " + file.getPath());
            return true;
        }
        for (File file2 : file.listFiles()) {
            e(file2);
        }
        return true;
    }

    @SuppressLint({"NewApi"})
    public static boolean f(String str) {
        return g(str, null);
    }

    @SuppressLint({"NewApi"})
    public static boolean g(String str, com.byd.cleardata.g.b bVar) {
        if (str != null) {
            Path path = Paths.get(str, new String[0]);
            try {
                if (Files.isDirectory(path, new LinkOption[0])) {
                    Files.walkFileTree(path, new a(bVar));
                    return true;
                }
                Files.deleteIfExists(path);
                if (!Files.exists(path, new LinkOption[0])) {
                    return true;
                }
                c.b("delete file force");
                m.a("rm -rf " + str);
                return true;
            } catch (Throwable th) {
                c.d("delete file error", th);
            }
        }
        return false;
    }

    @SuppressLint({"NewApi"})
    public static boolean h(File file) {
        if (file != null) {
            return i(file.getAbsolutePath());
        }
        return false;
    }

    @SuppressLint({"NewApi"})
    public static boolean i(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return Files.exists(Paths.get(str, new String[0]), LinkOption.NOFOLLOW_LINKS);
            } catch (Throwable th) {
                c.h("exists error", th);
            }
        }
        return false;
    }

    public static long j(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        try {
            StatFs statFs = new StatFs(str);
            return statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0L;
        }
    }

    public static String k(float f) {
        String[] strArr = f2694a;
        int i = 0;
        String str = strArr[0];
        int length = strArr.length;
        while (true) {
            if (i >= length) {
                break;
            }
            String str2 = strArr[i];
            if (f < 1000.0f) {
                str = str2;
                break;
            }
            f /= 1024.0f;
            i++;
        }
        return d(f) + str;
    }

    @SuppressLint({"NewApi"})
    public static long l(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        if (G(str)) {
            return p(str);
        }
        try {
            return Files.size(Paths.get(str, new String[0]));
        } catch (IOException e) {
            c.d("getFileSize error", e);
            return -1L;
        }
    }

    public static String[] m(String str) {
        String strSubstring;
        int iLastIndexOf = str.lastIndexOf(".");
        strSubstring = "";
        if (iLastIndexOf >= 0) {
            String strSubstring2 = iLastIndexOf > 0 ? str.substring(0, iLastIndexOf) : "";
            strSubstring = str.substring(iLastIndexOf);
            str = strSubstring2;
        }
        return new String[]{str, strSubstring};
    }

    @SuppressLint({"NewApi"})
    public static long n(File file) {
        File[] fileArrListFiles;
        if (file == null || !file.exists()) {
            return 0L;
        }
        long jN = 1;
        if (!file.isDirectory() || (fileArrListFiles = file.listFiles()) == null) {
            return 1L;
        }
        for (File file2 : fileArrListFiles) {
            jN += n(file2);
        }
        return jN;
    }

    public static long o(File file) {
        if (file.isFile()) {
            return file.length() + 0;
        }
        File[] fileArrListFiles = file.listFiles();
        long length = file.length() + 0;
        if (fileArrListFiles == null) {
            return length;
        }
        for (File file2 : fileArrListFiles) {
            length += o(file2);
        }
        return length;
    }

    @SuppressLint({"NewApi"})
    public static long p(String str) {
        long[] jArr = new long[1];
        try {
            Files.walkFileTree(Paths.get(str, new String[0]), new b(jArr));
            return jArr[0];
        } catch (IOException e) {
            c.d("getFolderSize error", e);
            return -1L;
        }
    }

    @SuppressLint({"NewApi"})
    public static long q(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        try {
            return Files.readAttributes(Paths.get(str, new String[0]), BasicFileAttributes.class, new LinkOption[0]).creationTime().toMillis();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0L;
        }
    }

    public static String r(File file) {
        String strY = y(file);
        if (strY == null) {
            return "file/*";
        }
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(strY);
        return !TextUtils.isEmpty(mimeTypeFromExtension) ? mimeTypeFromExtension : "file/*";
    }

    public static String s(String str, String str2) {
        String[] strArrM = m(str2);
        String str3 = strArrM[0];
        String str4 = strArrM[1];
        String str5 = str3 + str4;
        String str6 = null;
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            str6 = str + "/" + str5;
            if (!i(str6)) {
                break;
            }
            str5 = str3 + '(' + i + ')' + str4;
        }
        if (i(str6)) {
            return null;
        }
        return str6;
    }

    public static String t(String str, String str2) {
        String[] strArrM = m(str2);
        String str3 = strArrM[0];
        String str4 = strArrM[1];
        String str5 = str3 + str4;
        String str6 = null;
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            str6 = str + "/" + str5;
            if (!i(str6)) {
                break;
            }
            str5 = str3 + i + str4;
        }
        if (i(str6)) {
            return null;
        }
        return str5;
    }

    public static long u(File file) {
        Date date;
        try {
            String attribute = new ExifInterface(file.toString()).getAttribute("DateTime");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss", com.byd.cleardata.i.a.b(FileApplication.a()));
            if (attribute != null && (date = simpleDateFormat.parse(attribute)) != null) {
                return date.getTime();
            }
        } catch (Throwable th) {
            c.h("getPictureDate error", th);
        }
        return file.lastModified();
    }

    public static String v(File file) {
        try {
            float[] fArr = new float[2];
            new ExifInterface(file.toString()).getLatLong(fArr);
            c.e("locations", Float.valueOf(fArr[0]), Float.valueOf(fArr[1]));
            List<Address> fromLocation = new Geocoder(FileApplication.a(), Locale.getDefault()).getFromLocation(fArr[0], fArr[1], 1);
            if (fromLocation == null || fromLocation.size() <= 0) {
                return "";
            }
            Address address = fromLocation.get(0);
            return address.getAdminArea() + address.getLocality() + address.getThoroughfare();
        } catch (Throwable th) {
            c.h("getPictureLocation error", th);
            return "";
        }
    }

    public static String w(File file) {
        String str = null;
        try {
            ExifInterface exifInterface = new ExifInterface(file.toString());
            String attribute = exifInterface.getAttribute("ImageWidth");
            String attribute2 = exifInterface.getAttribute("ImageLength");
            if (!"0".equals(attribute) && !"0".equals(attribute2)) {
                str = attribute + "*" + attribute2;
            }
        } catch (Throwable th) {
            c.h("getPicturePixel error", th);
        }
        if (str != null) {
            return str;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.toString(), options);
        return options.outWidth + "*" + options.outHeight;
    }

    public static String x(long j) {
        double d2 = j;
        String[] strArr = f2694a;
        int i = 0;
        String str = strArr[0];
        int length = strArr.length;
        while (true) {
            if (i >= length) {
                break;
            }
            String str2 = strArr[i];
            if (d2 < 1024.0d) {
                str = str2;
                break;
            }
            d2 /= 1024.0d;
            i++;
        }
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        return numberFormat.format(d2) + str;
    }

    public static String y(File file) {
        int iLastIndexOf;
        if (file == null) {
            return null;
        }
        String lowerCase = file.getName().toLowerCase();
        if (lowerCase.equals("") || lowerCase.endsWith(".") || (iLastIndexOf = lowerCase.lastIndexOf(".")) == -1) {
            return null;
        }
        return lowerCase.substring(iLastIndexOf + 1).toLowerCase(Locale.US);
    }

    public static String z(String str) {
        int iLastIndexOf;
        if (TextUtils.isEmpty(str) || str.equals("") || str.endsWith(".") || (iLastIndexOf = str.lastIndexOf(".")) == -1) {
            return null;
        }
        return str.substring(iLastIndexOf + 1).toLowerCase(Locale.US);
    }
}
