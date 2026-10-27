package com.squareup.picasso;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.StatFs;
import android.provider.Settings;
import android.util.Log;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import okio.BufferedSource;
import okio.ByteString;

/* JADX INFO: loaded from: classes7.dex */
final class Utils {
    static final StringBuilder MAIN_THREAD_KEY_BUILDER = new StringBuilder();
    private static final ByteString WEBP_FILE_HEADER_RIFF = ByteString.encodeUtf8("RIFF");
    private static final ByteString WEBP_FILE_HEADER_WEBP = ByteString.encodeUtf8("WEBP");

    private Utils() {
    }

    static int getBitmapBytes(Bitmap bitmap) {
        int allocationByteCount = bitmap.getAllocationByteCount();
        if (allocationByteCount >= 0) {
            return allocationByteCount;
        }
        throw new IllegalStateException("Negative size: " + bitmap);
    }

    static <T> T checkNotNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    static void checkNotMain() {
        if (isMain()) {
            throw new IllegalStateException("Method call should not happen from the main thread.");
        }
    }

    static void checkMain() {
        if (!isMain()) {
            throw new IllegalStateException("Method call should happen from the main thread.");
        }
    }

    static boolean isMain() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    static String getLogIdsForHunter(BitmapHunter bitmapHunter) {
        return getLogIdsForHunter(bitmapHunter, "");
    }

    static String getLogIdsForHunter(BitmapHunter bitmapHunter, String str) {
        StringBuilder sb = new StringBuilder(str);
        Action action = bitmapHunter.getAction();
        if (action != null) {
            sb.append(action.request.logId());
        }
        List<Action> actions = bitmapHunter.getActions();
        if (actions != null) {
            int size = actions.size();
            for (int i = 0; i < size; i++) {
                if (i > 0 || action != null) {
                    sb.append(", ");
                }
                sb.append(actions.get(i).request.logId());
            }
        }
        return sb.toString();
    }

    static void log(String str, String str2, String str3) {
        log(str, str2, str3, "");
    }

    static void log(String str, String str2, String str3, String str4) {
        Log.d("Picasso", String.format("%1$-11s %2$-12s %3$s %4$s", str, str2, str3, str4));
    }

    static String createKey(Request request) {
        StringBuilder sb = MAIN_THREAD_KEY_BUILDER;
        String strCreateKey = createKey(request, sb);
        sb.setLength(0);
        return strCreateKey;
    }

    static String createKey(Request request, StringBuilder sb) {
        String str = request.stableKey;
        if (str != null) {
            sb.ensureCapacity(str.length() + 50);
            sb.append(request.stableKey);
        } else {
            Uri uri = request.uri;
            if (uri != null) {
                String string = uri.toString();
                sb.ensureCapacity(string.length() + 50);
                sb.append(string);
            } else {
                sb.ensureCapacity(50);
                sb.append(request.resourceId);
            }
        }
        sb.append('\n');
        if (request.rotationDegrees != 0.0f) {
            sb.append("rotation:");
            sb.append(request.rotationDegrees);
            if (request.hasRotationPivot) {
                sb.append('@');
                sb.append(request.rotationPivotX);
                sb.append('x');
                sb.append(request.rotationPivotY);
            }
            sb.append('\n');
        }
        if (request.hasSize()) {
            sb.append("resize:");
            sb.append(request.targetWidth);
            sb.append('x');
            sb.append(request.targetHeight);
            sb.append('\n');
        }
        if (request.centerCrop) {
            sb.append("centerCrop:");
            sb.append(request.centerCropGravity);
            sb.append('\n');
        } else if (request.centerInside) {
            sb.append("centerInside");
            sb.append('\n');
        }
        List<Transformation> list = request.transformations;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                sb.append(request.transformations.get(i).key());
                sb.append('\n');
            }
        }
        return sb.toString();
    }

    static File createDefaultCacheDir(Context context) {
        File file = new File(context.getApplicationContext().getCacheDir(), "picasso-cache");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    @TargetApi(18)
    static long calculateDiskCacheSize(File file) {
        long blockCountLong;
        try {
            StatFs statFs = new StatFs(file.getAbsolutePath());
            blockCountLong = (statFs.getBlockCountLong() * statFs.getBlockSizeLong()) / 50;
        } catch (IllegalArgumentException unused) {
            blockCountLong = 5242880;
        }
        return Math.max(Math.min(blockCountLong, 52428800L), 5242880L);
    }

    static int calculateMemoryCacheSize(Context context) {
        ActivityManager activityManager = (ActivityManager) getService(context, MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME);
        return (int) ((((long) ((context.getApplicationInfo().flags & 1048576) != 0 ? activityManager.getLargeMemoryClass() : activityManager.getMemoryClass())) * 1048576) / 7);
    }

    static boolean isAirplaneModeOn(Context context) {
        try {
            return Settings.Global.getInt(context.getContentResolver(), "airplane_mode_on", 0) != 0;
        } catch (NullPointerException | SecurityException unused) {
            return false;
        }
    }

    static <T> T getService(Context context, String str) {
        return (T) context.getSystemService(str);
    }

    static boolean hasPermission(Context context, String str) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    static boolean isWebPFile(BufferedSource bufferedSource) throws IOException {
        return bufferedSource.rangeEquals(0L, WEBP_FILE_HEADER_RIFF) && bufferedSource.rangeEquals(8L, WEBP_FILE_HEADER_WEBP);
    }

    static int getResourceId(Resources resources, Request request) throws FileNotFoundException {
        Uri uri;
        int i = request.resourceId;
        if (i != 0 || (uri = request.uri) == null) {
            return i;
        }
        String authority = uri.getAuthority();
        if (authority == null) {
            throw new FileNotFoundException("No package provided: " + request.uri);
        }
        List<String> pathSegments = request.uri.getPathSegments();
        if (pathSegments == null || pathSegments.isEmpty()) {
            throw new FileNotFoundException("No path segments: " + request.uri);
        }
        if (pathSegments.size() == 1) {
            try {
                return Integer.parseInt(pathSegments.get(0));
            } catch (NumberFormatException unused) {
                throw new FileNotFoundException("Last path segment is not a resource ID: " + request.uri);
            }
        }
        if (pathSegments.size() == 2) {
            return resources.getIdentifier(pathSegments.get(1), pathSegments.get(0), authority);
        }
        throw new FileNotFoundException("More than two path segments: " + request.uri);
    }

    static Resources getResources(Context context, Request request) throws FileNotFoundException {
        Uri uri;
        if (request.resourceId != 0 || (uri = request.uri) == null) {
            return context.getResources();
        }
        String authority = uri.getAuthority();
        if (authority == null) {
            throw new FileNotFoundException("No package provided: " + request.uri);
        }
        try {
            return context.getPackageManager().getResourcesForApplication(authority);
        } catch (PackageManager.NameNotFoundException unused) {
            throw new FileNotFoundException("Unable to obtain resources for package: " + request.uri);
        }
    }

    static void flushStackLocalLeaks(Looper looper) {
        Handler handler = new Handler(looper) { // from class: com.squareup.picasso.Utils.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                sendMessageDelayed(obtainMessage(), 1000L);
            }
        };
        handler.sendMessageDelayed(handler.obtainMessage(), 1000L);
    }

    static class PicassoThreadFactory implements ThreadFactory {
        PicassoThreadFactory() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new PicassoThread(runnable);
        }
    }

    private static class PicassoThread extends Thread {
        PicassoThread(Runnable runnable) {
            super(runnable);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Process.setThreadPriority(10);
            super.run();
        }
    }
}
