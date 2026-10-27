package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.Locale;
import java.util.function.Function;
import org.chromium.base.ContentUriUtils;

/* JADX INFO: compiled from: FileUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class pm0 {
    public static final om0 a = new Function() { // from class: om0
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return Boolean.TRUE;
        }
    };

    public static String a(String str) {
        int iLastIndexOf = str.lastIndexOf(47);
        int iLastIndexOf2 = str.lastIndexOf(46);
        return iLastIndexOf >= iLastIndexOf2 ? "" : str.substring(iLastIndexOf2 + 1).toLowerCase(Locale.US);
    }

    public static Uri b(File file) {
        Uri uriB;
        try {
            uriB = ContentUriUtils.b(file);
        } catch (IllegalArgumentException e) {
            Log.e("cr_FileUtils", "Could not create content uri: " + e);
            uriB = null;
        }
        return uriB == null ? Uri.fromFile(file) : uriB;
    }

    public static Bitmap c(Context context, Uri uri) {
        try {
            ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r");
            try {
                if (parcelFileDescriptorOpenFileDescriptor == null) {
                    Log.w("cr_FileUtils", "Null ParcelFileDescriptor from uri " + uri);
                    if (parcelFileDescriptorOpenFileDescriptor != null) {
                        parcelFileDescriptorOpenFileDescriptor.close();
                    }
                    return null;
                }
                FileDescriptor fileDescriptor = parcelFileDescriptorOpenFileDescriptor.getFileDescriptor();
                if (fileDescriptor == null) {
                    Log.w("cr_FileUtils", "Null FileDescriptor from uri " + uri);
                    parcelFileDescriptorOpenFileDescriptor.close();
                    return null;
                }
                Bitmap bitmapDecodeFileDescriptor = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                if (bitmapDecodeFileDescriptor != null) {
                    parcelFileDescriptorOpenFileDescriptor.close();
                    return bitmapDecodeFileDescriptor;
                }
                Log.w("cr_FileUtils", "Failed to decode image from uri " + uri);
                parcelFileDescriptorOpenFileDescriptor.close();
                return null;
            } catch (Throwable th) {
                if (parcelFileDescriptorOpenFileDescriptor != null) {
                    try {
                        parcelFileDescriptorOpenFileDescriptor.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th;
            }
        } catch (IOException unused2) {
            Log.w("cr_FileUtils", "IO exception when reading uri " + uri);
            return null;
        }
    }

    public static void d(File file, Function function) {
        File[] fileArrListFiles;
        if (!file.exists()) {
            file.delete();
            return;
        }
        if (((Boolean) function.apply(file.getPath())).booleanValue()) {
            if (file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
                for (File file2 : fileArrListFiles) {
                    d(file2, function);
                }
            }
            if (file.delete()) {
                return;
            }
            fa1.b("FileUtils", "Failed to delete: %s", file);
        }
    }
}
