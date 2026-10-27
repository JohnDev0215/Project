package org.libpag;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: classes3.dex */
public class CacheManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static CacheManager f4001a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f4002b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f4003c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ConcurrentHashMap<String, a> f4004d = new ConcurrentHashMap<>();

    public static class b implements Comparable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f4007a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public File f4008b;

        public b(File file) {
            this.f4008b = file;
            this.f4007a = file.lastModified();
        }

        @Override // java.lang.Comparable
        public int compareTo(Object obj) {
            long j = ((b) obj).f4007a;
            long j2 = this.f4007a;
            if (j2 < j) {
                return -1;
            }
            return j2 == j ? 0 : 1;
        }
    }

    private CacheManager() {
    }

    public static native int ContentVersion(PAGComposition pAGComposition);

    public static CacheManager a(Context context) {
        CacheManager cacheManager = f4001a;
        if (cacheManager != null) {
            return cacheManager;
        }
        if (context == null) {
            return null;
        }
        synchronized (CacheManager.class) {
            CacheManager cacheManager2 = f4001a;
            if (cacheManager2 != null) {
                return cacheManager2;
            }
            CacheManager cacheManager3 = new CacheManager();
            f4001a = cacheManager3;
            cacheManager3.f4003c = context.getApplicationContext();
            CacheManager cacheManager4 = f4001a;
            cacheManager4.f4002b = a(cacheManager4.f4003c, null);
            return f4001a;
        }
    }

    public void b(String str) {
        a aVar = this.f4004d.get(str);
        if (aVar == null) {
            return;
        }
        aVar.g();
        aVar.e();
        this.f4004d.remove(str);
        aVar.h();
    }

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private ImageCache f4005a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ReentrantReadWriteLock f4006b = new ReentrantReadWriteLock();

        public static a a(String str, int i, int i2, int i3) {
            ImageCache imageCacheA = ImageCache.a(str, i, i2, i3);
            if (imageCacheA == null) {
                return null;
            }
            a aVar = new a();
            aVar.f4005a = imageCacheA;
            return aVar;
        }

        public boolean b(int i, Bitmap bitmap) {
            g();
            boolean zB = this.f4005a.b(i, bitmap);
            h();
            return zB;
        }

        public void c() {
            this.f4006b.readLock().lock();
        }

        public void d() {
            this.f4006b.readLock().unlock();
        }

        public void e() {
            this.f4005a.release();
        }

        public void f() {
            g();
            this.f4005a.releaseSaveBuffer();
            h();
        }

        public void g() {
            this.f4006b.writeLock().lock();
        }

        public void h() {
            this.f4006b.writeLock().unlock();
        }

        public boolean a() {
            g();
            boolean zFlushSave = this.f4005a.flushSave();
            h();
            return zFlushSave;
        }

        public boolean b() {
            c();
            boolean zIsAllCached = this.f4005a.isAllCached();
            d();
            return zIsAllCached;
        }

        public boolean a(int i, Bitmap bitmap) {
            c();
            boolean zA = this.f4005a.a(i, bitmap);
            d();
            return zA;
        }

        public boolean a(int i) {
            c();
            boolean zIsCached = this.f4005a.isCached(i);
            d();
            return zIsCached;
        }
    }

    public static class ImageCache {
        private long nativeContext;

        static {
            org.extra.tools.a.a("pag");
            nativeInit();
        }

        private ImageCache(long j) {
            this.nativeContext = 0L;
            this.nativeContext = j;
        }

        private static native long SetupCache(String str, int i, int i2, int i3, boolean z);

        public static ImageCache a(String str, int i, int i2, int i3) {
            boolean z;
            File file = new File(str);
            if (file.exists()) {
                z = false;
            } else {
                file.getParentFile().mkdirs();
                z = true;
            }
            long jSetupCache = SetupCache(str, i, i2, i3, z);
            if (jSetupCache != 0) {
                return new ImageCache(jSetupCache);
            }
            file.delete();
            return null;
        }

        private static native void nativeInit();

        public boolean b(int i, Bitmap bitmap) {
            boolean z = false;
            if (bitmap == null) {
                return false;
            }
            if (Build.VERSION.SDK_INT >= 26 && bitmap.getConfig() == Bitmap.Config.HARDWARE) {
                z = true;
            }
            return putBitmapToSaveBuffer(i, bitmap, bitmap.getByteCount(), z);
        }

        public native boolean flushSave();

        public native boolean inflateBitmap(int i, Bitmap bitmap, int i2, boolean z);

        public native boolean isAllCached();

        public native boolean isCached(int i);

        public native boolean putBitmapToSaveBuffer(int i, Bitmap bitmap, int i2, boolean z);

        public native void release();

        public native void releaseSaveBuffer();

        public boolean a(int i, Bitmap bitmap) {
            boolean z = false;
            if (bitmap == null) {
                return false;
            }
            if (Build.VERSION.SDK_INT >= 26 && bitmap.getConfig() == Bitmap.Config.HARDWARE) {
                z = true;
            }
            return inflateBitmap(i, bitmap, bitmap.getByteCount(), z);
        }
    }

    public void a() {
        try {
            File[] fileArrListFiles = new File(this.f4002b).listFiles();
            if (fileArrListFiles.length == 0) {
                return;
            }
            int length = fileArrListFiles.length;
            b[] bVarArr = new b[length];
            long length2 = 0;
            long length3 = 0;
            for (int i = 0; i < fileArrListFiles.length; i++) {
                length3 += fileArrListFiles[i].length();
                bVarArr[i] = new b(fileArrListFiles[i]);
            }
            if (length3 < PAGImageView.f4029b) {
                return;
            }
            Arrays.sort(bVarArr);
            long j = (long) (PAGImageView.f4029b * 0.39999998f);
            for (int i2 = 0; i2 < length; i2++) {
                b bVar = bVarArr[i2];
                length2 += bVar.f4008b.length();
                bVar.f4008b.delete();
                if (length3 - length2 <= j) {
                    return;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public a a(String str, int i, int i2, int i3) {
        a aVar = this.f4004d.get(str);
        if (aVar != null) {
            return aVar;
        }
        synchronized (this) {
            a aVarA = this.f4004d.get(str);
            if (aVarA == null) {
                aVarA = a.a(a(str), i, i2, i3);
            }
            if (aVarA == null) {
                return null;
            }
            this.f4004d.put(str, aVarA);
            return aVarA;
        }
    }

    public String a(String str) {
        return this.f4002b + File.separator + str;
    }

    private static String a(Context context, String str) {
        String path;
        if (TextUtils.isEmpty(str)) {
            str = "PAGImageViewCache";
        }
        if (!"mounted".equals(Environment.getExternalStorageState()) && Environment.isExternalStorageRemovable()) {
            path = context.getCacheDir().getPath();
        } else {
            path = context.getExternalCacheDir().getPath();
        }
        return path + File.separator + str;
    }
}
