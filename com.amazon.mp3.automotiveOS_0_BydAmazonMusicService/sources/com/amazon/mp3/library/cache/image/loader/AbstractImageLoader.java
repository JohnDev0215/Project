package com.amazon.mp3.library.cache.image.loader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.net.BitmapHttpClient;
import com.amazon.mp3.prime.CTAPrimeCache;
import com.amazon.mp3.util.FileUtil;
import com.amazon.mp3.util.IoUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.Profiler;
import com.amazon.mp3.util.StorageInfo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractImageLoader {
    private static String sBaseCacheDirectory;
    private static String sLegacyBaseCacheDirectory;
    private CacheManager mCacheManager;
    protected final ImageLoaderFactory.ItemType mItemType;
    public static final String[] PERMISSIONS_FOR_LOCAL_ARTWORK = {"android.permission.READ_EXTERNAL_STORAGE"};
    private static HashMap<String, String> sCacheDirMap = new HashMap<>();
    private static HashMap<String, String> sLegacyCacheDirMap = new HashMap<>();
    protected final String mTAG = getClass().getSimpleName();
    protected final SecureRandom mRandom = new SecureRandom();

    public AbstractImageLoader(ImageLoaderFactory.ItemType itemType) {
        this.mItemType = itemType;
    }

    protected CacheManager getCacheManager() {
        if (this.mCacheManager == null) {
            this.mCacheManager = CacheManager.getInstance();
        }
        return this.mCacheManager;
    }

    protected Bitmap loadAlbumArtworkFromCTAByAsin(Context context, String albumAsin) {
        if (TextUtils.isEmpty(albumAsin)) {
            return null;
        }
        String artworkUrlForAsin = new CTAPrimeCache(context).getArtworkUrlForAsin(albumAsin);
        if (TextUtils.isEmpty(artworkUrlForAsin)) {
            return null;
        }
        return BitmapHttpClient.bitmapFromUrl(artworkUrlForAsin);
    }

    protected String getCacheDirectory(ImageLoaderFactory.ItemType type, String source) {
        String string;
        try {
            Profiler.begin("Getting the cache directory");
            String str = sBaseCacheDirectory;
            if (str == null) {
                str = AmazonApplication.getContext().getCacheDir().getAbsolutePath() + File.separator + "images";
                sBaseCacheDirectory = str;
            }
            String str2 = type.toString() + source;
            String str3 = sCacheDirMap.get(str2);
            if (str3 == null) {
                if (TextUtils.isEmpty(source)) {
                    string = str + File.separator + type;
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    String str4 = File.separator;
                    sb.append(str4);
                    sb.append(type);
                    sb.append(str4);
                    sb.append(source);
                    string = sb.toString();
                }
                str3 = string;
                sCacheDirMap.put(str2, str3);
            }
            return str3;
        } finally {
            Profiler.end();
        }
    }

    private String getLegacyCacheDirectory(ImageLoaderFactory.ItemType type, String source) {
        String str;
        try {
            Profiler.begin("Getting the legacy cache directory");
            StorageInfo storageInfo = StorageInfo.getInstance();
            String absolutePath = sLegacyBaseCacheDirectory;
            if (absolutePath == null) {
                try {
                    if (storageInfo.isExternalStorageAvailable()) {
                        absolutePath = storageInfo.getExternalStorageDirectory().getAbsolutePath();
                    } else {
                        Log.debug(this.mTAG, "No external storage available.");
                    }
                } catch (StorageInfo.DeviceNotAvailableException unused) {
                    Log.debug(this.mTAG, "Device not mounted - external storage");
                }
                if (absolutePath == null) {
                    try {
                        if (storageInfo.isInternalStorageAvailable()) {
                            absolutePath = storageInfo.getInternalStorageDirectory().getAbsolutePath();
                        } else {
                            Log.debug(this.mTAG, "No internal storage available.");
                        }
                    } catch (StorageInfo.DeviceNotAvailableException unused2) {
                        Log.debug(this.mTAG, "Device not mounted - internal storage");
                    }
                }
                sLegacyBaseCacheDirectory = absolutePath;
            }
            String str2 = type.toString() + source;
            String str3 = sLegacyCacheDirMap.get(str2);
            if (str3 == null && absolutePath != null) {
                if (TextUtils.isEmpty(source)) {
                    str = String.format(Locale.US, "%s/%s/%s/.imagecache/%s", absolutePath, AmazonApplication.getExternalStorageAppDirectory(), AmazonApplication.getExternalStorageTempDirectory(), type);
                } else {
                    str = String.format(Locale.US, "%s/%s/%s/.imagecache/%s/%s", absolutePath, AmazonApplication.getExternalStorageAppDirectory(), AmazonApplication.getExternalStorageTempDirectory(), type, source);
                }
                str3 = str;
                sLegacyCacheDirMap.put(str2, str3);
            }
            return str3;
        } finally {
            Profiler.end();
        }
    }

    private String getCacheFileName(String path, String id) {
        return path + File.separator + id + ".jpg";
    }

    public String getCacheFileName(ImageLoaderFactory.ItemType type, String source, int size, String id) {
        return getCacheFileName(size + File.separator + id + ".jpg", type, source);
    }

    private String getCacheFileName(String filename, ImageLoaderFactory.ItemType type, String source) {
        StringBuilder sb = new StringBuilder();
        sb.append(getLegacyCacheDirectory(type, source));
        String str = File.separator;
        sb.append(str);
        sb.append(filename);
        String string = sb.toString();
        if (new File(string).exists()) {
            return string;
        }
        return getCacheDirectory(type, source) + str + filename;
    }

    public void removeAll(ImageLoaderFactory.ItemType type, String source) {
        String cacheDirectory = getCacheDirectory(type, source);
        if (cacheDirectory != null && cacheDirectory.length() > 2) {
            IoUtil.deleteFilesInDirectory(cacheDirectory, true);
        }
        String legacyCacheDirectory = getLegacyCacheDirectory(type, source);
        if (cacheDirectory == null || cacheDirectory.length() <= 2) {
            return;
        }
        IoUtil.deleteFilesInDirectory(legacyCacheDirectory, true);
    }

    public void removeIds(ImageLoaderFactory.ItemType type, String source, Collection<String> idsToRemove, boolean ignoreCms) {
        File[] fileArrListFiles;
        String cacheDirectory = getCacheDirectory(type, source);
        if (cacheDirectory == null || (fileArrListFiles = new File(cacheDirectory).listFiles()) == null) {
            return;
        }
        for (File file : fileArrListFiles) {
            if (file.isDirectory()) {
                Iterator<String> it = idsToRemove.iterator();
                while (it.hasNext()) {
                    FileUtil.deleteFile(getCacheFileName(file.getAbsolutePath(), it.next()));
                }
            }
        }
    }

    protected String saveToFile(Bitmap image, ImageLoaderFactory.ItemType type, String id) {
        return saveToFile(image, null, type, id, false);
    }

    protected String saveToFile(Bitmap image, String source, ImageLoaderFactory.ItemType type, String id) {
        return saveToFile(image, source, type, id, false);
    }

    protected String saveToFile(Bitmap image, String source, ImageLoaderFactory.ItemType type, String id, boolean forceSave) throws Throwable {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        if (image != null && type != null && id != null) {
            if (image.getHeight() != image.getWidth()) {
                Log.debug(this.mTAG, "Bitmap dimensions are not square: %d x %d", Integer.valueOf(image.getHeight()), Integer.valueOf(image.getWidth()));
            }
            String cacheFileName = getCacheFileName(type, source, Math.max(image.getWidth(), image.getHeight()), id);
            String parent = new File(cacheFileName).getParent();
            if (parent == null) {
                return null;
            }
            File file = new File(parent);
            if (!file.exists()) {
                if (!file.mkdirs()) {
                    return null;
                }
                FileUtil.createEmptyFile(file, ".nomedia");
            }
            if ((file.exists() || file.mkdirs()) && file.exists() && file.canWrite()) {
                if (!forceSave && new File(cacheFileName).exists()) {
                    return cacheFileName;
                }
                String str = cacheFileName + this.mRandom.nextInt();
                try {
                    fileOutputStream = new FileOutputStream(str);
                } catch (FileNotFoundException unused) {
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    boolean zCompress = image.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
                    IoUtil.close(fileOutputStream);
                    if (zCompress) {
                        zCompress = FileUtil.moveFile(str, cacheFileName);
                    }
                    if (!zCompress && !FileUtil.deleteFile(str)) {
                        Log.debug(this.mTAG, "Unable to delete image after unsuccessful cache operation: %s", str);
                    }
                } catch (FileNotFoundException unused2) {
                    fileOutputStream2 = fileOutputStream;
                    IoUtil.close(fileOutputStream2);
                    if (!FileUtil.deleteFile(str)) {
                        Log.debug(this.mTAG, "Unable to delete image after unsuccessful cache operation: %s", str);
                    }
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream2 = fileOutputStream;
                    IoUtil.close(fileOutputStream2);
                    if (!FileUtil.deleteFile(str)) {
                        Log.debug(this.mTAG, "Unable to delete image after unsuccessful cache operation: %s", str);
                    }
                    throw th;
                }
                return cacheFileName;
            }
        }
        return null;
    }

    protected Bitmap resample(Bitmap bitmap, int size) {
        if (bitmap == null) {
            return null;
        }
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        if (height != size || width != size) {
            bitmap = Bitmap.createScaledBitmap(bitmap, size, size, true);
        }
        Bitmap.Config config = bitmap.getConfig();
        Bitmap.Config config2 = Bitmap.Config.RGB_565;
        return config != config2 ? bitmap.copy(config2, false) : bitmap;
    }

    protected Bitmap resample(Bitmap bitmap, int width, int height) {
        if (bitmap == null) {
            return null;
        }
        int height2 = bitmap.getHeight();
        int width2 = bitmap.getWidth();
        if (height2 != height || width2 != width) {
            bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
        }
        Bitmap.Config config = bitmap.getConfig();
        Bitmap.Config config2 = Bitmap.Config.RGB_565;
        return config != config2 ? bitmap.copy(config2, false) : bitmap;
    }

    protected class ScaledImageLoader {
        byte[] mRawData;

        public ScaledImageLoader(String path) throws Throwable {
            RandomAccessFile randomAccessFile;
            if (path == null) {
                return;
            }
            File file = new File(path);
            if (file.exists() && file.isFile()) {
                RandomAccessFile randomAccessFile2 = null;
                try {
                    randomAccessFile = new RandomAccessFile(file, "r");
                } catch (IOException unused) {
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (randomAccessFile.length() > 0) {
                        byte[] bArr = new byte[(int) randomAccessFile.length()];
                        this.mRawData = bArr;
                        randomAccessFile.readFully(bArr);
                        IoUtil.close(randomAccessFile);
                        return;
                    }
                    IoUtil.close(randomAccessFile);
                } catch (IOException unused2) {
                    randomAccessFile2 = randomAccessFile;
                    IoUtil.close(randomAccessFile2);
                } catch (Throwable th2) {
                    th = th2;
                    randomAccessFile2 = randomAccessFile;
                    IoUtil.close(randomAccessFile2);
                    throw th;
                }
            }
        }

        public Bitmap loadAndScale(int scaledSize, boolean resample) {
            if (this.mRawData == null) {
                return null;
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            int i = 1;
            options.inJustDecodeBounds = true;
            options.inDither = false;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            byte[] bArr = this.mRawData;
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            int i2 = options.outWidth >> 1;
            for (int i3 = options.outHeight >> 1; i2 > scaledSize && i3 > scaledSize; i3 >>= 1) {
                i <<= 1;
                i2 >>= 1;
            }
            options.inSampleSize = i;
            options.inJustDecodeBounds = false;
            byte[] bArr2 = this.mRawData;
            Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArr2, 0, bArr2.length, options);
            return (bitmapDecodeByteArray == null || !resample) ? bitmapDecodeByteArray : AbstractImageLoader.this.resample(bitmapDecodeByteArray, scaledSize);
        }

        public Bitmap loadAndScale(int width, int height, boolean resample) {
            if (this.mRawData == null) {
                return null;
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            int i = 1;
            options.inJustDecodeBounds = true;
            options.inDither = false;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            byte[] bArr = this.mRawData;
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            int i2 = options.outWidth >> 1;
            for (int i3 = options.outHeight >> 1; i2 > width && i3 > height; i3 >>= 1) {
                i <<= 1;
                i2 >>= 1;
            }
            options.inSampleSize = i;
            options.inJustDecodeBounds = false;
            byte[] bArr2 = this.mRawData;
            Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArr2, 0, bArr2.length, options);
            return (bitmapDecodeByteArray == null || !resample) ? bitmapDecodeByteArray : AbstractImageLoader.this.resample(bitmapDecodeByteArray, width, height);
        }
    }

    protected Bitmap loadAndScaleFromFile(String path, int scaledSize) {
        return new ScaledImageLoader(path).loadAndScale(scaledSize, false);
    }

    protected Bitmap loadAndScaleFromFile(String path, int scaledWidth, int scaledHeight) {
        return new ScaledImageLoader(path).loadAndScale(scaledWidth, scaledHeight, false);
    }
}
