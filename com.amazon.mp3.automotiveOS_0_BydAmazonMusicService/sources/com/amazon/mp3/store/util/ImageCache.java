package com.amazon.mp3.store.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.cache.LruCachePolicy;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.BitmapHttpClient;
import com.amazon.mp3.util.IoUtil;
import com.amazon.mp3.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public class ImageCache {
    private static final HashMap<Dimen, SoftReference<IterableLruCachePolicy>> sLruCaches = new HashMap<>();
    private final boolean mAutoResizeEnabled;
    private IterableLruCachePolicy mCache;
    private Bitmap.Config mColorDepth;
    private Context mContext;
    private final Drawable mDefaultImage;
    private final String mDiskCacheDirectory;
    private int mImageHeight;
    private int mImageWidth;

    private static final class Dimen {
        int mHeight;
        int mWidth;

        public Dimen(int width, int height) {
            this.mWidth = width;
            this.mHeight = height;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Dimen)) {
                return false;
            }
            Dimen dimen = (Dimen) o;
            return dimen.mWidth == this.mWidth && dimen.mHeight == this.mHeight;
        }

        public int hashCode() {
            int i = this.mWidth;
            int i2 = this.mHeight;
            return i * i2 * (i > i2 ? 31 : 37);
        }
    }

    public ImageCache(Context context, int imageWidth, int imageHeight, Bitmap.Config colorDepth) {
        this(context, imageWidth, imageHeight, colorDepth, true);
    }

    public ImageCache(Context context, int imageWidth, int imageHeight, Bitmap.Config colorDepth, boolean autoResizeEnabled) {
        this.mImageWidth = -1;
        this.mImageHeight = -1;
        this.mColorDepth = Bitmap.Config.RGB_565;
        this.mAutoResizeEnabled = autoResizeEnabled;
        this.mContext = context.getApplicationContext();
        if (imageWidth <= 0 && imageHeight <= 0) {
            throw new IllegalArgumentException("imageWidth and imageHeight have to be positive integers");
        }
        HashMap<Dimen, SoftReference<IterableLruCachePolicy>> map = sLruCaches;
        synchronized (map) {
            Dimen dimen = new Dimen(imageWidth, imageHeight);
            SoftReference<IterableLruCachePolicy> softReference = map.get(dimen);
            if (softReference != null) {
                this.mCache = softReference.get();
            }
            if (this.mCache == null) {
                this.mCache = new IterableLruCachePolicy((1572864 / ((imageWidth * imageHeight) * 3)) + 1, false);
                map.put(dimen, new SoftReference<>(this.mCache));
            }
        }
        Resources resources = context.getResources();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, R.drawable.placeholder_sm, options);
        if (imageWidth <= options.outWidth) {
            this.mDefaultImage = resources.getDrawable(R.drawable.placeholder_sm);
        } else {
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(resources, R.drawable.placeholder, options2);
            if (imageWidth <= options2.outWidth) {
                this.mDefaultImage = new BitmapDrawable(resources, getScaledImage(resources, options2, imageWidth, R.drawable.placeholder));
            } else {
                BitmapFactory.Options options3 = new BitmapFactory.Options();
                options3.inJustDecodeBounds = true;
                BitmapFactory.decodeResource(resources, R.drawable.placeholder_large, options3);
                this.mDefaultImage = new BitmapDrawable(resources, getScaledImage(resources, options3, imageWidth, R.drawable.placeholder_large));
            }
        }
        this.mDiskCacheDirectory = getDiskCacheDirectory(context);
        this.mImageWidth = imageWidth;
        this.mImageHeight = imageHeight;
        this.mColorDepth = colorDepth;
    }

    private Bitmap getScaledImage(final Resources resources, final BitmapFactory.Options imageOptions, final int imageWidth, final int imageResourceId) {
        imageOptions.inJustDecodeBounds = false;
        imageOptions.inPreferredConfig = Bitmap.Config.RGB_565;
        imageOptions.inSampleSize = imageOptions.outWidth / imageWidth;
        return BitmapFactory.decodeResource(resources, imageResourceId, imageOptions);
    }

    public int getImageWidth() {
        return this.mImageWidth;
    }

    public Drawable cacheOnCurrentThread(String url) throws Throwable {
        Bitmap result;
        int i;
        if (url != null && "".equals(url)) {
            return this.mDefaultImage;
        }
        synchronized (this.mCache) {
            Drawable drawable = this.mCache.get("store", url);
            if (drawable != null) {
                return drawable;
            }
            if (url != null) {
                BitmapDrawable imageFromDiskCache = getImageFromDiskCache(url);
                if (imageFromDiskCache == null || imageFromDiskCache.getIntrinsicWidth() != this.mImageWidth) {
                    if (imageFromDiskCache == null || (imageFromDiskCache.getIntrinsicHeight() < this.mImageHeight && imageFromDiskCache.getIntrinsicWidth() < this.mImageWidth)) {
                        try {
                            BitmapHttpClient bitmapHttpClient = new BitmapHttpClient();
                            bitmapHttpClient.execute(Uri.parse(url));
                            result = bitmapHttpClient.getResult();
                        } catch (AbstractHttpClient.HttpClientException unused) {
                            return this.mDefaultImage;
                        }
                    } else {
                        result = imageFromDiskCache.getBitmap();
                    }
                    if (this.mAutoResizeEnabled && (i = this.mImageHeight) > 0 && this.mImageWidth > 0 && (i != result.getHeight() || this.mImageWidth != result.getWidth())) {
                        Matrix matrix = new Matrix();
                        matrix.postScale(this.mImageWidth / result.getWidth(), this.mImageHeight / result.getHeight());
                        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(result, 0, 0, result.getWidth(), result.getHeight(), matrix, true);
                        result.recycle();
                        result = bitmapCreateBitmap;
                    }
                    Bitmap.Config config = result.getConfig();
                    Bitmap.Config config2 = this.mColorDepth;
                    if (config != config2) {
                        Bitmap bitmapCopy = result.copy(config2, false);
                        result.recycle();
                        result = bitmapCopy;
                    }
                    cacheToDisk(result, url);
                    imageFromDiskCache = new BitmapDrawable(this.mContext.getResources(), result);
                }
                synchronized (this.mCache) {
                    this.mCache.put("store", url, imageFromDiskCache);
                }
                return imageFromDiskCache;
            }
            return this.mDefaultImage;
        }
    }

    public static void clearDiskCache() {
        IoUtil.deleteFilesInDirectory(getDiskCacheDirectory(AmazonApplication.getContext()));
    }

    private static class IterableLruCachePolicy extends LruCachePolicy<String, Drawable> implements Iterable<Drawable> {
        public IterableLruCachePolicy(int maxSize, boolean enableSoftCache) {
            super(maxSize, enableSoftCache);
        }

        @Override // java.lang.Iterable
        public Iterator<Drawable> iterator() {
            Iterator<Drawable> it = super.iterator("store");
            return it != null ? it : new ArrayList().iterator();
        }
    }

    private BitmapDrawable getImageFromDiskCache(String originalUrl) {
        try {
            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(getDiskCacheFileName(originalUrl));
            if (bitmapDecodeFile != null) {
                return new BitmapDrawable(this.mContext.getResources(), bitmapDecodeFile);
            }
            return null;
        } catch (Exception e) {
            Log.warning("ImageCache", "Could not get image from disk cache", e);
            return null;
        }
    }

    private void cacheToDisk(Bitmap image, String originalUrl) throws Throwable {
        String diskCacheFileName;
        FileOutputStream fileOutputStream;
        String diskCacheDirectory = getDiskCacheDirectory();
        if (diskCacheDirectory == null) {
            return;
        }
        File file = new File(diskCacheDirectory);
        if ((file.exists() || file.mkdirs()) && file.exists() && file.canWrite() && (diskCacheFileName = getDiskCacheFileName(originalUrl)) != null) {
            FileOutputStream fileOutputStream2 = null;
            try {
                fileOutputStream = new FileOutputStream(diskCacheFileName);
            } catch (FileNotFoundException unused) {
            } catch (Throwable th) {
                th = th;
            }
            try {
                boolean zCompress = image.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    Log.error("ImageCache", "Could not close output stream", e);
                }
                if (zCompress || new File(diskCacheFileName).delete()) {
                    return;
                }
                Log.warning("ImageCache", "Could not delete file");
                Log.debug("ImageCache", "File: %s", diskCacheFileName);
            } catch (FileNotFoundException unused2) {
                fileOutputStream2 = fileOutputStream;
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (IOException e2) {
                        Log.error("ImageCache", "Could not close output stream", e2);
                    }
                }
                if (new File(diskCacheFileName).delete()) {
                    return;
                }
                Log.warning("ImageCache", "Could not delete file");
                Log.debug("ImageCache", "File: %s", diskCacheFileName);
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream2 = fileOutputStream;
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (IOException e3) {
                        Log.error("ImageCache", "Could not close output stream", e3);
                    }
                }
                if (!new File(diskCacheFileName).delete()) {
                    Log.warning("ImageCache", "Could not delete file");
                    Log.debug("ImageCache", "File: %s", diskCacheFileName);
                    throw th;
                }
                throw th;
            }
        }
    }

    private String getDiskCacheDirectory() {
        return this.mDiskCacheDirectory;
    }

    private String getDiskCacheFileName(String originalUrl) {
        return getDiskCacheDirectory() + originalUrl.hashCode();
    }

    private static String getDiskCacheDirectory(Context context) {
        return context.getCacheDir().getAbsolutePath() + "/.imagecache/";
    }
}
