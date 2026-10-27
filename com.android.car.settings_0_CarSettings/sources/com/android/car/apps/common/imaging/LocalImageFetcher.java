package com.android.car.apps.common.imaging;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.util.LruCache;
import com.android.car.apps.common.BitmapUtils;
import com.android.car.apps.common.CommonFlags;
import com.android.car.apps.common.R;
import com.android.car.apps.common.UriUtils;
import com.android.car.apps.common.util.CarAppsIOUtils;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: classes5.dex */
public class LocalImageFetcher {
    private static final int KB = 1024;
    private static final int MB = 1048576;
    private static LocalImageFetcher sInstance;
    private final boolean mFlagRemoteImages;
    private final LruCache<ImageKey, Drawable> mMemoryCache;
    private final int mPoolSize;
    private final LruCache<String, Executor> mThreadPools;
    private static final String TAG = "LocalImageFetcher";
    private static final boolean L_WARN = Log.isLoggable(TAG, 5);
    private static final boolean L_DEBUG = Log.isLoggable(TAG, 3);
    private final Map<ImageKey, HashSet<BiConsumer<ImageKey, Drawable>>> mConsumers = new HashMap(20);
    private final Map<ImageKey, ImageLoadingTask> mTasks = new HashMap(20);

    public static LocalImageFetcher getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new LocalImageFetcher(context);
        }
        return sInstance;
    }

    private LocalImageFetcher(Context context) {
        Resources res = context.getResources();
        int maxPools = res.getInteger(R.integer.image_fetcher_thread_pools_max_count);
        this.mPoolSize = res.getInteger(R.integer.image_fetcher_thread_pool_size);
        this.mThreadPools = new LruCache<>(maxPools);
        int cacheSizeMB = res.getInteger(R.integer.bitmap_memory_cache_max_size_mb);
        final int drawableDefaultWeightKB = res.getInteger(R.integer.drawable_default_weight_kb);
        this.mMemoryCache = new LruCache<ImageKey, Drawable>(1048576 * cacheSizeMB) { // from class: com.android.car.apps.common.imaging.LocalImageFetcher.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.util.LruCache
            public int sizeOf(ImageKey key, Drawable drawable) {
                if (drawable instanceof BitmapDrawable) {
                    return ((BitmapDrawable) drawable).getBitmap().getAllocationByteCount();
                }
                return drawableDefaultWeightKB * 1024;
            }
        };
        this.mFlagRemoteImages = CommonFlags.getInstance(context).shouldFlagImproperImageRefs();
    }

    private Executor getThreadPool(String packageName) {
        Executor result = this.mThreadPools.get(packageName);
        if (result == null) {
            Executor result2 = Executors.newFixedThreadPool(this.mPoolSize);
            this.mThreadPools.put(packageName, result2);
            return result2;
        }
        return result;
    }

    public void getImage(Context context, ImageKey key, BiConsumer<ImageKey, Drawable> consumer) {
        Drawable cached = this.mMemoryCache.get(key);
        if (cached != null) {
            consumer.accept(key, cached);
            return;
        }
        ImageLoadingTask task = this.mTasks.get(key);
        HashSet<BiConsumer<ImageKey, Drawable>> consumers = this.mConsumers.get(key);
        if (consumers == null) {
            consumers = new HashSet<>(3);
            if (task != null && L_WARN) {
                Log.w(TAG, "Expected no task here for " + key);
            }
            this.mConsumers.put(key, consumers);
        }
        consumers.add(consumer);
        if (task == null) {
            String packageName = UriUtils.getPackageName(context, key.mImageUri);
            if (packageName != null) {
                ImageLoadingTask task2 = new ImageLoadingTask(context, key, this.mFlagRemoteImages);
                this.mTasks.put(key, task2);
                task2.executeOnExecutor(getThreadPool(packageName), new Void[0]);
                if (L_DEBUG) {
                    Log.d(TAG, "Added task " + key.mImageUri);
                    return;
                }
                return;
            }
            Log.e(TAG, "No package for " + key.mImageUri);
        }
    }

    public void cancelRequest(ImageKey key, BiConsumer<ImageKey, Drawable> consumer) {
        HashSet<BiConsumer<ImageKey, Drawable>> consumers = this.mConsumers.get(key);
        if (consumers != null) {
            boolean removed = consumers.remove(consumer);
            if (consumers.isEmpty()) {
                this.mConsumers.remove(key);
                ImageLoadingTask task = this.mTasks.remove(key);
                if (task != null) {
                    task.cancel(true);
                    if (L_DEBUG) {
                        Log.d(TAG, "Canceled task " + key.mImageUri);
                    }
                } else if (L_WARN) {
                    Log.w(TAG, "cancelRequest missing task for: " + key);
                }
            }
            if (!removed && L_WARN) {
                Log.w(TAG, "cancelRequest missing consumer for: " + key);
                return;
            }
            return;
        }
        boolean removed2 = L_WARN;
        if (removed2) {
            Log.w(TAG, "cancelRequest has no consumers for: " + key);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fulfilRequests(ImageLoadingTask task, Drawable drawable) {
        ImageKey key = task.mImageKey;
        ImageLoadingTask pendingTask = this.mTasks.get(key);
        if (pendingTask == task) {
            if (drawable != null) {
                this.mMemoryCache.put(key, drawable);
            }
            HashSet<BiConsumer<ImageKey, Drawable>> consumers = this.mConsumers.remove(key);
            this.mTasks.remove(key);
            if (consumers != null) {
                for (BiConsumer<ImageKey, Drawable> consumer : consumers) {
                    consumer.accept(key, drawable);
                }
                return;
            }
            return;
        }
        if (L_WARN) {
            Log.w(TAG, "A new task already started for: " + task.mImageKey);
        }
    }

    private static class ImageLoadingTask extends AsyncTask<Void, Void, Drawable> {
        private final boolean mFlagRemoteImages;
        private final ImageKey mImageKey;
        private final WeakReference<Context> mWeakContext;
        private final ImageDecoder.OnHeaderDecodedListener mOnHeaderDecodedListener = new ImageDecoder.OnHeaderDecodedListener() { // from class: com.android.car.apps.common.imaging.LocalImageFetcher.ImageLoadingTask.1
            @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
            public void onHeaderDecoded(ImageDecoder decoder, ImageDecoder.ImageInfo info, ImageDecoder.Source source) {
                if (!ImageLoadingTask.this.isCancelled()) {
                    decoder.setAllocator(ImageLoadingTask.this.mAllocatorMode);
                    int maxW = ImageLoadingTask.this.mImageKey.mMaxImageSize.getWidth();
                    int maxH = ImageLoadingTask.this.mImageKey.mMaxImageSize.getHeight();
                    int imgW = info.getSize().getWidth();
                    int imgH = info.getSize().getHeight();
                    if (imgW > maxW || imgH > maxH) {
                        float scale = Math.min(maxW / imgW, maxH / imgH);
                        decoder.setTargetSize(Math.round(imgW * scale), Math.round(imgH * scale));
                        return;
                    }
                    return;
                }
                throw new CancellationException();
            }
        };
        private int mAllocatorMode = 1;

        ImageLoadingTask(Context context, ImageKey request, boolean flagRemoteImages) {
            this.mWeakContext = new WeakReference<>(context.getApplicationContext());
            this.mImageKey = request;
            this.mFlagRemoteImages = flagRemoteImages;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Drawable doInBackground(Void... voids) {
            try {
            } catch (IOException ioe) {
                Log.e(LocalImageFetcher.TAG, "ImageLoadingTask#doInBackground: " + ioe);
            } catch (CancellationException e) {
                return null;
            }
            if (isCancelled()) {
                return null;
            }
            Uri imageUri = this.mImageKey.mImageUri;
            Context context = this.mWeakContext.get();
            if (context == null) {
                return null;
            }
            if (UriUtils.isAndroidResourceUri(imageUri)) {
                return UriUtils.getDrawable(context, UriUtils.getIconResource(context, imageUri));
            }
            if (UriUtils.isContentUri(imageUri)) {
                ContentResolver resolver = context.getContentResolver();
                ImageDecoder.Source src = ImageDecoder.createSource(resolver, imageUri);
                return ImageDecoder.decodeDrawable(src, this.mOnHeaderDecodedListener);
            }
            if (this.mFlagRemoteImages) {
                this.mAllocatorMode = 1;
                URL url = new URL(imageUri.toString());
                InputStream is = new BufferedInputStream(url.openStream());
                try {
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    try {
                        CarAppsIOUtils.copy(is, bytes);
                        ImageDecoder.Source src2 = ImageDecoder.createSource(ByteBuffer.wrap(bytes.toByteArray()));
                        Bitmap decoded = ImageDecoder.decodeBitmap(src2, this.mOnHeaderDecodedListener);
                        Bitmap tinted = BitmapUtils.createTintedBitmap(decoded, context.getColor(R.color.improper_image_refs_tint_color));
                        BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(), tinted);
                        bytes.close();
                        is.close();
                        return bitmapDrawable;
                    } finally {
                    }
                } finally {
                }
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Drawable drawable) {
            if (LocalImageFetcher.L_DEBUG) {
                Log.d(LocalImageFetcher.TAG, "onPostExecute canceled:  " + isCancelled() + " drawable: " + drawable + " " + this.mImageKey.mImageUri);
            }
            if (!isCancelled()) {
                if (LocalImageFetcher.sInstance != null) {
                    LocalImageFetcher.sInstance.fulfilRequests(this, drawable);
                } else {
                    Log.e(LocalImageFetcher.TAG, "ImageLoadingTask#onPostExecute: LocalImageFetcher was reset !");
                }
            }
        }
    }
}
