package com.byd.filemanager.k;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.media.ExifInterface;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.StatFs;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import androidx.core.content.FileProvider;
import com.byd.filemanager.FileApplication;
import com.byd.filemanager.R;
import com.byd.filemanager.bean.FileItem;
import com.byd.filemanager.bean.FileType;
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
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: FileUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String[] f3608a = {"B", "KB", "MB", "GB", "TB"};

    /* JADX INFO: compiled from: FileUtils.java */
    class a extends SimpleFileVisitor<Path> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.byd.filemanager.j.b f3609a;

        a(com.byd.filemanager.j.b bVar) {
            this.f3609a = bVar;
        }

        @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public FileVisitResult postVisitDirectory(Path path, IOException iOException) throws IOException {
            Files.delete(path);
            return d.I(this.f3609a, true);
        }

        @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
            Files.delete(path);
            return d.I(this.f3609a, true);
        }
    }

    /* JADX INFO: compiled from: FileUtils.java */
    class b extends SimpleFileVisitor<Path> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ long[] f3610a;

        b(long[] jArr) {
            this.f3610a = jArr;
        }

        @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
            long[] jArr = this.f3610a;
            jArr[0] = jArr[0] + Files.size(path);
            return FileVisitResult.CONTINUE;
        }

        @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
            long[] jArr = this.f3610a;
            jArr[0] = jArr[0] + Files.size(path);
            return FileVisitResult.CONTINUE;
        }

        @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public FileVisitResult visitFileFailed(Path path, IOException iOException) throws IOException {
            com.byd.filemanager.k.b.d("visitFileFailed", iOException);
            try {
                long[] jArr = this.f3610a;
                jArr[0] = jArr[0] + Files.size(path);
            } catch (Throwable th) {
                com.byd.filemanager.k.b.h("visitFileFailed getSize error", th);
            }
            return FileVisitResult.CONTINUE;
        }
    }

    public static long A(String str) {
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

    public static Uri B(Context context, FileItem fileItem) {
        fileItem.w();
        return l(context, fileItem);
    }

    public static Uri C(Context context, File file) {
        return FileProvider.e(context, "com.byd.filemanager.fileprovider", file);
    }

    public static String D(File file) throws IOException {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            if (file != null) {
                try {
                    mediaMetadataRetriever.setDataSource(file.getPath());
                } catch (Exception e2) {
                    Log.e("TAG", "MediaMetadataRetriever exception " + e2);
                    mediaMetadataRetriever.release();
                    return null;
                }
            }
            return mediaMetadataRetriever.extractMetadata(18) + "*" + mediaMetadataRetriever.extractMetadata(19);
        } finally {
            mediaMetadataRetriever.release();
        }
    }

    public static boolean E(FileItem fileItem) {
        String strK;
        boolean z = (fileItem == null || (strK = com.byd.filemanager.e.f().k(2)) == null || !new File(strK, "install_apps.dat").exists()) ? false : true;
        com.byd.filemanager.k.b.b("isCanInstallApp " + z);
        return z;
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
            } catch (InvalidPathException e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    public static boolean H(List<?> list) {
        return (list == null || list.isEmpty()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"NewApi"})
    public static FileVisitResult I(com.byd.filemanager.j.b bVar, boolean z) throws IOException {
        if (z) {
            return (bVar == null || (bVar.a(z) && z)) ? FileVisitResult.CONTINUE : FileVisitResult.TERMINATE;
        }
        throw new IOException();
    }

    public static boolean J(Context context, FileItem fileItem) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addFlags(1);
            intent.setDataAndType(B(context, fileItem), r(fileItem.w()));
            context.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e2) {
            n.a(R.string.no_app_available);
            com.byd.filemanager.k.b.h("openFile failed", e2);
            return false;
        } catch (Exception e3) {
            com.byd.filemanager.k.b.h("openFile failed", e3);
            return false;
        }
    }

    public static boolean K(Context context, File file) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addFlags(1);
            intent.setDataAndType(C(context, file), r(file));
            context.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e2) {
            e2.printStackTrace();
            n.a(R.string.no_app_available);
            return false;
        } catch (Exception e3) {
            e3.printStackTrace();
            com.byd.filemanager.k.b.h("openFile failed", e3);
            return false;
        }
    }

    private static Uri L(Context context, Uri uri, File file) {
        Cursor cursorQuery = context.getContentResolver().query(uri, new String[]{"_id"}, "_data=? ", new String[]{file.getAbsolutePath()}, null);
        Uri uriWithAppendedPath = null;
        if (cursorQuery != null) {
            if (cursorQuery.moveToFirst()) {
                uriWithAppendedPath = Uri.withAppendedPath(uri, cursorQuery.getInt(cursorQuery.getColumnIndex("_id")) + "");
            }
            cursorQuery.close();
        }
        return uriWithAppendedPath;
    }

    public static File M(File file, String str) {
        com.byd.filemanager.k.b.b("file=" + file + ",name=" + str);
        if (file == null || TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            String str2 = file.getParent() + File.separator + str;
            com.byd.filemanager.k.b.b("newName=" + str2);
            boolean zRenameTo = file.renameTo(new File(str2));
            com.byd.filemanager.k.b.b("isSuccess:" + zRenameTo);
            if (zRenameTo) {
                return new File(str2);
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
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

    public static String c(float f) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return decimalFormat.format(f);
    }

    public static boolean d(File file) {
        if (file == null || !file.exists()) {
            return true;
        }
        if (file.isFile()) {
            if (file.delete()) {
                return true;
            }
            k.a("rm -rf " + file.getPath());
            return true;
        }
        for (File file2 : file.listFiles()) {
            d(file2);
        }
        return true;
    }

    @SuppressLint({"NewApi"})
    public static boolean e(String str) {
        return f(str, null);
    }

    @SuppressLint({"NewApi"})
    public static boolean f(String str, com.byd.filemanager.j.b bVar) {
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
                com.byd.filemanager.k.b.b("delete file force");
                k.a("rm -rf " + str);
                return true;
            } catch (Throwable th) {
                com.byd.filemanager.k.b.d("delete file error", th);
            }
        }
        return false;
    }

    @SuppressLint({"NewApi"})
    public static boolean g(File file) {
        if (file != null) {
            return file.exists();
        }
        return false;
    }

    @SuppressLint({"NewApi"})
    public static boolean h(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return new File(str).exists();
    }

    public static long i(String str) {
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

    public static String j(float f) {
        String[] strArr = f3608a;
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
        return c(f) + str;
    }

    @SuppressLint({"NewApi"})
    public static long k(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        if (G(str)) {
            return p(str);
        }
        try {
            return Files.size(Paths.get(str, new String[0]));
        } catch (IOException e2) {
            com.byd.filemanager.k.b.d("getFileSize error", e2);
            return -1L;
        }
    }

    public static Uri l(Context context, FileItem fileItem) {
        Uri uriL = L(context, fileItem.G() == FileType.VIDEO ? MediaStore.Video.Media.getContentUri("external") : fileItem.G() == FileType.IMAGE ? MediaStore.Images.Media.getContentUri("external") : fileItem.G() == FileType.AUDIO ? MediaStore.Audio.Media.getContentUri("external") : MediaStore.Files.getContentUri("external"), fileItem.w());
        if (uriL == null) {
            uriL = L(context, MediaStore.Files.getContentUri("external"), fileItem.w());
        }
        if (uriL == null) {
            try {
                uriL = FileProvider.e(context, "com.byd.filemanager.fileprovider", fileItem.w());
            } catch (IllegalArgumentException e2) {
                Log.e("TAG", "FileProvider.getUriForFile error", e2);
                return null;
            }
        }
        Log.i("TAG", "uri:" + uriL.toString());
        return uriL;
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
        } catch (IOException e2) {
            com.byd.filemanager.k.b.d("getFolderSize error", e2);
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
        com.byd.filemanager.k.b.b("suffix = " + strY);
        if (strY == null) {
            return "file/*";
        }
        if ("ape".equals(strY)) {
            return "audio/*";
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
            if (!h(str6)) {
                break;
            }
            str5 = str3 + '(' + i + ')' + str4;
        }
        if (h(str6)) {
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
            if (!h(str6)) {
                break;
            }
            str5 = str3 + i + str4;
        }
        if (h(str6)) {
            return null;
        }
        return str5;
    }

    public static long u(File file) {
        Date date;
        try {
            String attribute = new ExifInterface(file.toString()).getAttribute("DateTime");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss", com.byd.filemanager.k.a.a(FileApplication.a()));
            if (attribute != null && (date = simpleDateFormat.parse(attribute)) != null) {
                return date.getTime();
            }
        } catch (Throwable th) {
            com.byd.filemanager.k.b.h("getPictureDate error", th);
        }
        return file.lastModified();
    }

    public static String v(File file) {
        try {
            float[] fArr = new float[2];
            new ExifInterface(file.toString()).getLatLong(fArr);
            com.byd.filemanager.k.b.e("locations", Float.valueOf(fArr[0]), Float.valueOf(fArr[1]));
            List<Address> fromLocation = new Geocoder(FileApplication.a(), Locale.getDefault()).getFromLocation(fArr[0], fArr[1], 1);
            if (fromLocation == null || fromLocation.size() <= 0) {
                return "";
            }
            Address address = fromLocation.get(0);
            return address.getAdminArea() + address.getLocality() + address.getThoroughfare();
        } catch (Throwable th) {
            com.byd.filemanager.k.b.h("getPictureLocation error", th);
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
            com.byd.filemanager.k.b.h("getPicturePixel error", th);
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
        String[] strArr = f3608a;
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
