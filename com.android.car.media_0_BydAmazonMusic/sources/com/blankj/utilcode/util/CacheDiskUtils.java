package com.blankj.utilcode.util;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.NonNull;
import com.blankj.utilcode.constant.CacheConstants;
import java.io.File;
import java.io.FilenameFilter;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class CacheDiskUtils implements CacheConstants {
    private static final Map<String, CacheDiskUtils> CACHE_MAP = new HashMap();
    private static final String CACHE_PREFIX = "cdu_";
    private static final int DEFAULT_MAX_COUNT = Integer.MAX_VALUE;
    private static final long DEFAULT_MAX_SIZE = Long.MAX_VALUE;
    private static final String TYPE_BITMAP = "bi_";
    private static final String TYPE_BYTE = "by_";
    private static final String TYPE_DRAWABLE = "dr_";
    private static final String TYPE_JSON_ARRAY = "ja_";
    private static final String TYPE_JSON_OBJECT = "jo_";
    private static final String TYPE_PARCELABLE = "pa_";
    private static final String TYPE_SERIALIZABLE = "se_";
    private static final String TYPE_STRING = "st_";
    private final File mCacheDir;
    private final String mCacheKey;
    private c mDiskCacheManager;
    private final int mMaxCount;
    private final long mMaxSize;

    public static final class b {
        private b() {
        }

        public static byte[] a(byte[] bArr, int i, int i2) {
            int i3 = i2 - i;
            if (i3 >= 0) {
                byte[] bArr2 = new byte[i3];
                System.arraycopy(bArr, i, bArr2, 0, Math.min(bArr.length - i, i3));
                return bArr2;
            }
            throw new IllegalArgumentException(i + " > " + i2);
        }

        public static boolean b(byte[] bArr) {
            return bArr != null && bArr.length >= 14 && bArr[0] == 95 && bArr[1] == 36 && bArr[12] == 36 && bArr[13] == 95;
        }
    }

    public static final class c {
        public final AtomicLong a;
        public final AtomicInteger b;
        public final long c;
        public final int d;
        public final Map<File, Long> e;
        public final File f;
        public final Thread g;

        public class a implements Runnable {
            public final /* synthetic */ File a;

            /* JADX INFO: renamed from: com.blankj.utilcode.util.CacheDiskUtils$c$a$a, reason: collision with other inner class name */
            public class C0014a implements FilenameFilter {
                public C0014a(a aVar) {
                }

                @Override // java.io.FilenameFilter
                public final boolean accept(File file, String str) {
                    return str.startsWith(CacheDiskUtils.CACHE_PREFIX);
                }
            }

            public a(File file) {
                this.a = file;
            }

            @Override // java.lang.Runnable
            public final void run() {
                File[] fileArrListFiles = this.a.listFiles(new C0014a(this));
                if (fileArrListFiles != null) {
                    int length = 0;
                    int i = 0;
                    for (File file : fileArrListFiles) {
                        length = (int) (file.length() + ((long) length));
                        i++;
                        c.this.e.put(file, Long.valueOf(file.lastModified()));
                    }
                    c.this.a.getAndAdd(length);
                    c.this.b.getAndAdd(i);
                }
            }
        }

        public static void a(c cVar, File file) {
            Long lValueOf = Long.valueOf(System.currentTimeMillis());
            file.setLastModified(lValueOf.longValue());
            cVar.e.put(file, lValueOf);
        }

        public static boolean b(c cVar, String str) {
            File file = new File(cVar.f, cVar.c(str));
            if (!file.exists()) {
                file = null;
            }
            if (file == null) {
                return true;
            }
            if (!file.delete()) {
                return false;
            }
            cVar.a.addAndGet(-file.length());
            cVar.b.addAndGet(-1);
            cVar.e.remove(file);
            return true;
        }

        public final String c(String str) {
            StringBuilder sbK = spi.c.k(CacheDiskUtils.CACHE_PREFIX);
            sbK.append(str.substring(0, 3));
            sbK.append(str.substring(3).hashCode());
            return sbK.toString();
        }

        public final void d() {
            try {
                this.g.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private c(File file, long j, int i) {
            this.e = Collections.synchronizedMap(new HashMap());
            this.f = file;
            this.c = j;
            this.d = i;
            this.a = new AtomicLong();
            this.b = new AtomicInteger();
            Thread thread = new Thread(new a(file));
            this.g = thread;
            thread.start();
        }
    }

    private CacheDiskUtils(String str, File file, long j, int i) {
        this.mCacheKey = str;
        this.mCacheDir = file;
        this.mMaxSize = j;
        this.mMaxCount = i;
    }

    private c getDiskCacheManager() {
        if (this.mCacheDir.exists()) {
            if (this.mDiskCacheManager == null) {
                this.mDiskCacheManager = new c(this.mCacheDir, this.mMaxSize, this.mMaxCount);
            }
        } else if (this.mCacheDir.mkdirs()) {
            this.mDiskCacheManager = new c(this.mCacheDir, this.mMaxSize, this.mMaxCount);
        } else {
            StringBuilder sbK = spi.c.k("can't make dirs in ");
            sbK.append(this.mCacheDir.getAbsolutePath());
            Log.e("CacheDiskUtils", sbK.toString());
        }
        return this.mDiskCacheManager;
    }

    public static CacheDiskUtils getInstance() {
        return getInstance("", Long.MAX_VALUE, Integer.MAX_VALUE);
    }

    private byte[] realGetBytes(@NonNull String str) {
        return realGetBytes(str, null);
    }

    private void realPutBytes(String str, byte[] bArr, int i) {
        c diskCacheManager;
        if (bArr == null || (diskCacheManager = getDiskCacheManager()) == null) {
            return;
        }
        if (i >= 0) {
            byte[] bytes = String.format(Locale.getDefault(), "_$%010d$_", Long.valueOf((System.currentTimeMillis() / 1000) + ((long) i))).getBytes();
            byte[] bArr2 = new byte[bytes.length + bArr.length];
            System.arraycopy(bytes, 0, bArr2, 0, bytes.length);
            System.arraycopy(bArr, 0, bArr2, bytes.length, bArr.length);
            bArr = bArr2;
        }
        diskCacheManager.d();
        File file = new File(diskCacheManager.f, diskCacheManager.c(str));
        if (file.exists()) {
            diskCacheManager.b.addAndGet(-1);
            diskCacheManager.a.addAndGet(-file.length());
        }
        FileIOUtils.writeFileFromBytesByChannel(file, bArr, true);
        c.a(diskCacheManager, file);
        diskCacheManager.b.addAndGet(1);
        diskCacheManager.a.addAndGet(file.length());
        while (true) {
            if (diskCacheManager.b.get() <= diskCacheManager.d && diskCacheManager.a.get() <= diskCacheManager.c) {
                return;
            }
            AtomicLong atomicLong = diskCacheManager.a;
            long j = 0;
            if (!diskCacheManager.e.isEmpty()) {
                Long l = Long.MAX_VALUE;
                File key = null;
                Set<Map.Entry<File, Long>> setEntrySet = diskCacheManager.e.entrySet();
                synchronized (diskCacheManager.e) {
                    for (Map.Entry<File, Long> entry : setEntrySet) {
                        Long value = entry.getValue();
                        if (value.longValue() < l.longValue()) {
                            key = entry.getKey();
                            l = value;
                        }
                    }
                }
                if (key != null) {
                    long length = key.length();
                    if (key.delete()) {
                        diskCacheManager.e.remove(key);
                        j = length;
                    }
                }
            }
            atomicLong.addAndGet(-j);
            diskCacheManager.b.addAndGet(-1);
        }
    }

    public boolean clear() {
        c diskCacheManager = getDiskCacheManager();
        boolean z = true;
        if (diskCacheManager == null) {
            return true;
        }
        File[] fileArrListFiles = diskCacheManager.f.listFiles(new com.blankj.utilcode.util.a(diskCacheManager));
        if (fileArrListFiles != null && fileArrListFiles.length > 0) {
            for (File file : fileArrListFiles) {
                if (file.delete()) {
                    diskCacheManager.a.addAndGet(-file.length());
                    diskCacheManager.b.addAndGet(-1);
                    diskCacheManager.e.remove(file);
                } else {
                    z = false;
                }
            }
            if (z) {
                diskCacheManager.e.clear();
                diskCacheManager.a.set(0L);
                diskCacheManager.b.set(0);
            }
        }
        return z;
    }

    public Bitmap getBitmap(@NonNull String str, Bitmap bitmap) {
        byte[] bArrRealGetBytes = realGetBytes(spi.c.f(TYPE_BITMAP, str));
        return bArrRealGetBytes == null ? bitmap : ImageUtils.bytes2Bitmap(bArrRealGetBytes);
    }

    public byte[] getBytes(@NonNull String str, byte[] bArr) {
        return realGetBytes(spi.c.f(TYPE_BYTE, str), bArr);
    }

    public int getCacheCount() {
        c diskCacheManager = getDiskCacheManager();
        if (diskCacheManager == null) {
            return 0;
        }
        diskCacheManager.d();
        return diskCacheManager.b.get();
    }

    public long getCacheSize() {
        c diskCacheManager = getDiskCacheManager();
        if (diskCacheManager == null) {
            return 0L;
        }
        diskCacheManager.d();
        return diskCacheManager.a.get();
    }

    public Drawable getDrawable(@NonNull String str, Drawable drawable) {
        byte[] bArrRealGetBytes = realGetBytes(spi.c.f(TYPE_DRAWABLE, str));
        return bArrRealGetBytes == null ? drawable : ImageUtils.bytes2Drawable(bArrRealGetBytes);
    }

    public JSONArray getJSONArray(@NonNull String str, JSONArray jSONArray) {
        byte[] bArrRealGetBytes = realGetBytes(spi.c.f(TYPE_JSON_ARRAY, str));
        return bArrRealGetBytes == null ? jSONArray : ConvertUtils.bytes2JSONArray(bArrRealGetBytes);
    }

    public JSONObject getJSONObject(@NonNull String str, JSONObject jSONObject) {
        byte[] bArrRealGetBytes = realGetBytes(spi.c.f(TYPE_JSON_OBJECT, str));
        return bArrRealGetBytes == null ? jSONObject : ConvertUtils.bytes2JSONObject(bArrRealGetBytes);
    }

    public <T> T getParcelable(@NonNull String str, @NonNull Parcelable.Creator<T> creator, T t) {
        byte[] bArrRealGetBytes = realGetBytes(spi.c.f(TYPE_PARCELABLE, str));
        return bArrRealGetBytes == null ? t : (T) ConvertUtils.bytes2Parcelable(bArrRealGetBytes, creator);
    }

    public Object getSerializable(@NonNull String str, Object obj) {
        byte[] bArrRealGetBytes = realGetBytes(spi.c.f(TYPE_SERIALIZABLE, str));
        return bArrRealGetBytes == null ? obj : ConvertUtils.bytes2Object(bArrRealGetBytes);
    }

    public String getString(@NonNull String str, String str2) {
        byte[] bArrRealGetBytes = realGetBytes(spi.c.f(TYPE_STRING, str));
        return bArrRealGetBytes == null ? str2 : ConvertUtils.bytes2String(bArrRealGetBytes);
    }

    public void put(@NonNull String str, Bitmap bitmap, int i) {
        realPutBytes(spi.c.f(TYPE_BITMAP, str), ImageUtils.bitmap2Bytes(bitmap), i);
    }

    public boolean remove(@NonNull String str) {
        c diskCacheManager = getDiskCacheManager();
        if (diskCacheManager == null) {
            return true;
        }
        if (c.b(diskCacheManager, TYPE_BYTE + str)) {
            if (c.b(diskCacheManager, TYPE_STRING + str)) {
                if (c.b(diskCacheManager, TYPE_JSON_OBJECT + str)) {
                    if (c.b(diskCacheManager, TYPE_JSON_ARRAY + str)) {
                        if (c.b(diskCacheManager, TYPE_BITMAP + str)) {
                            if (c.b(diskCacheManager, TYPE_DRAWABLE + str)) {
                                if (c.b(diskCacheManager, TYPE_PARCELABLE + str)) {
                                    if (c.b(diskCacheManager, TYPE_SERIALIZABLE + str)) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public String toString() {
        return this.mCacheKey + "@" + Integer.toHexString(hashCode());
    }

    public static CacheDiskUtils getInstance(String str) {
        return getInstance(str, Long.MAX_VALUE, Integer.MAX_VALUE);
    }

    private byte[] realGetBytes(@NonNull String str, byte[] bArr) {
        long j;
        c diskCacheManager = getDiskCacheManager();
        if (diskCacheManager == null) {
            return bArr;
        }
        File file = new File(diskCacheManager.f, diskCacheManager.c(str));
        if (!file.exists()) {
            file = null;
        }
        if (file == null) {
            return bArr;
        }
        byte[] file2BytesByChannel = FileIOUtils.readFile2BytesByChannel(file);
        if (b.b(file2BytesByChannel)) {
            try {
                j = Long.parseLong(new String(b.a(file2BytesByChannel, 2, 12))) * 1000;
            } catch (NumberFormatException unused) {
                j = -1;
            }
        } else {
            j = -1;
        }
        if (j != -1 && System.currentTimeMillis() > j) {
            c.b(diskCacheManager, str);
            return bArr;
        }
        c.a(diskCacheManager, file);
        return b.b(file2BytesByChannel) ? b.a(file2BytesByChannel, 14, file2BytesByChannel.length) : file2BytesByChannel;
    }

    public static CacheDiskUtils getInstance(long j, int i) {
        return getInstance("", j, i);
    }

    public static CacheDiskUtils getInstance(@NonNull File file) {
        return getInstance(file, Long.MAX_VALUE, Integer.MAX_VALUE);
    }

    public static CacheDiskUtils getInstance(@NonNull File file, long j, int i) {
        String str = file.getAbsoluteFile() + "_" + j + "_" + i;
        Map<String, CacheDiskUtils> map = CACHE_MAP;
        CacheDiskUtils cacheDiskUtils = map.get(str);
        if (cacheDiskUtils == null) {
            synchronized (CacheDiskUtils.class) {
                cacheDiskUtils = map.get(str);
                if (cacheDiskUtils == null) {
                    CacheDiskUtils cacheDiskUtils2 = new CacheDiskUtils(str, file, j, i);
                    map.put(str, cacheDiskUtils2);
                    cacheDiskUtils = cacheDiskUtils2;
                }
            }
        }
        return cacheDiskUtils;
    }

    public byte[] getBytes(@NonNull String str) {
        return getBytes(str, null);
    }

    public Bitmap getBitmap(@NonNull String str) {
        return getBitmap(str, null);
    }

    public Drawable getDrawable(@NonNull String str) {
        return getDrawable(str, null);
    }

    public JSONArray getJSONArray(@NonNull String str) {
        return getJSONArray(str, null);
    }

    public JSONObject getJSONObject(@NonNull String str) {
        return getJSONObject(str, null);
    }

    public <T> T getParcelable(@NonNull String str, @NonNull Parcelable.Creator<T> creator) {
        return (T) getParcelable(str, creator, null);
    }

    public Object getSerializable(@NonNull String str) {
        return getSerializable(str, null);
    }

    public String getString(@NonNull String str) {
        return getString(str, null);
    }

    public void put(@NonNull String str, Drawable drawable, int i) {
        realPutBytes(spi.c.f(TYPE_DRAWABLE, str), ImageUtils.drawable2Bytes(drawable), i);
    }

    public static CacheDiskUtils getInstance(String str, long j, int i) {
        if (StringUtils.isSpace(str)) {
            str = "cacheUtils";
        }
        return getInstance(new File(Utils.getApp().getCacheDir(), str), j, i);
    }

    public void put(@NonNull String str, Parcelable parcelable, int i) {
        realPutBytes(spi.c.f(TYPE_PARCELABLE, str), ConvertUtils.parcelable2Bytes(parcelable), i);
    }

    public void put(@NonNull String str, Serializable serializable, int i) {
        realPutBytes(spi.c.f(TYPE_SERIALIZABLE, str), ConvertUtils.serializable2Bytes(serializable), i);
    }

    public void put(@NonNull String str, String str2, int i) {
        realPutBytes(spi.c.f(TYPE_STRING, str), ConvertUtils.string2Bytes(str2), i);
    }

    public void put(@NonNull String str, JSONArray jSONArray, int i) {
        realPutBytes(spi.c.f(TYPE_JSON_ARRAY, str), ConvertUtils.jsonArray2Bytes(jSONArray), i);
    }

    public void put(@NonNull String str, JSONObject jSONObject, int i) {
        realPutBytes(spi.c.f(TYPE_JSON_OBJECT, str), ConvertUtils.jsonObject2Bytes(jSONObject), i);
    }

    public void put(@NonNull String str, byte[] bArr, int i) {
        realPutBytes(spi.c.f(TYPE_BYTE, str), bArr, i);
    }

    public void put(@NonNull String str, byte[] bArr) {
        put(str, bArr, -1);
    }

    public void put(@NonNull String str, String str2) {
        put(str, str2, -1);
    }

    public void put(@NonNull String str, JSONObject jSONObject) {
        put(str, jSONObject, -1);
    }

    public void put(@NonNull String str, JSONArray jSONArray) {
        put(str, jSONArray, -1);
    }

    public void put(@NonNull String str, Bitmap bitmap) {
        put(str, bitmap, -1);
    }

    public void put(@NonNull String str, Drawable drawable) {
        put(str, drawable, -1);
    }

    public void put(@NonNull String str, Parcelable parcelable) {
        put(str, parcelable, -1);
    }

    public void put(@NonNull String str, Serializable serializable) {
        put(str, serializable, -1);
    }
}
