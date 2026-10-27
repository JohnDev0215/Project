package com.airbnb.lottie;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import androidx.annotation.WorkerThread;
import com.airbnb.lottie.model.LottieCompositionCache;
import com.airbnb.lottie.parser.LottieCompositionMoshiParser;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import okio.BufferedSource;
import okio.Okio;

/* JADX INFO: loaded from: classes2.dex */
public class LottieCompositionFactory {
    private static final Map<String, LottieTask<LottieComposition>> taskCache = new HashMap();
    private static final byte[] MAGIC = {80, 75, 3, 4};

    private LottieCompositionFactory() {
    }

    public static void setMaxCacheSize(int i) {
        LottieCompositionCache.getInstance().resize(i);
    }

    public static LottieTask<LottieComposition> fromUrl(Context context, String str) {
        return fromUrl(context, str, "url_" + str);
    }

    public static LottieTask<LottieComposition> fromUrl(final Context context, final String str, @Nullable final String str2) {
        return cache(str2, new Callable<LottieResult<LottieComposition>>() { // from class: com.airbnb.lottie.LottieCompositionFactory.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public LottieResult<LottieComposition> call() {
                LottieResult<LottieComposition> lottieResultFetchSync = L.networkFetcher(context).fetchSync(str, str2);
                if (str2 != null && lottieResultFetchSync.getValue() != null) {
                    LottieCompositionCache.getInstance().put(str2, lottieResultFetchSync.getValue());
                }
                return lottieResultFetchSync;
            }
        });
    }

    public static LottieTask<LottieComposition> fromAsset(Context context, String str) {
        return fromAsset(context, str, "asset_" + str);
    }

    public static LottieTask<LottieComposition> fromAsset(Context context, final String str, @Nullable final String str2) {
        final Context applicationContext = context.getApplicationContext();
        return cache(str2, new Callable<LottieResult<LottieComposition>>() { // from class: com.airbnb.lottie.LottieCompositionFactory.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public LottieResult<LottieComposition> call() {
                return LottieCompositionFactory.fromAssetSync(applicationContext, str, str2);
            }
        });
    }

    @WorkerThread
    public static LottieResult<LottieComposition> fromAssetSync(Context context, String str) {
        return fromAssetSync(context, str, "asset_" + str);
    }

    @WorkerThread
    public static LottieResult<LottieComposition> fromAssetSync(Context context, String str, @Nullable String str2) {
        try {
            if (!str.endsWith(".zip") && !str.endsWith(".lottie")) {
                return fromJsonInputStreamSync(context.getAssets().open(str), str2);
            }
            return fromZipStreamSync(new ZipInputStream(context.getAssets().open(str)), str2);
        } catch (IOException e) {
            return new LottieResult<>((Throwable) e);
        }
    }

    public static LottieTask<LottieComposition> fromRawRes(Context context, @RawRes int i) {
        return fromRawRes(context, i, rawResCacheKey(context, i));
    }

    public static LottieTask<LottieComposition> fromRawRes(Context context, @RawRes final int i, @Nullable final String str) {
        final WeakReference weakReference = new WeakReference(context);
        final Context applicationContext = context.getApplicationContext();
        return cache(str, new Callable<LottieResult<LottieComposition>>() { // from class: com.airbnb.lottie.LottieCompositionFactory.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public LottieResult<LottieComposition> call() {
                Context context2 = (Context) weakReference.get();
                if (context2 == null) {
                    context2 = applicationContext;
                }
                return LottieCompositionFactory.fromRawResSync(context2, i, str);
            }
        });
    }

    @WorkerThread
    public static LottieResult<LottieComposition> fromRawResSync(Context context, @RawRes int i) {
        return fromRawResSync(context, i, rawResCacheKey(context, i));
    }

    @WorkerThread
    public static LottieResult<LottieComposition> fromRawResSync(Context context, @RawRes int i, @Nullable String str) {
        try {
            BufferedSource bufferedSourceBuffer = Okio.buffer(Okio.source(context.getResources().openRawResource(i)));
            if (isZipCompressed(bufferedSourceBuffer).booleanValue()) {
                return fromZipStreamSync(new ZipInputStream(bufferedSourceBuffer.inputStream()), str);
            }
            return fromJsonInputStreamSync(bufferedSourceBuffer.inputStream(), str);
        } catch (Resources.NotFoundException e) {
            return new LottieResult<>((Throwable) e);
        }
    }

    private static String rawResCacheKey(Context context, @RawRes int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("rawRes");
        sb.append(isNightMode(context) ? "_night_" : "_day_");
        sb.append(i);
        return sb.toString();
    }

    private static boolean isNightMode(Context context) {
        return (context.getResources().getConfiguration().uiMode & 48) == 32;
    }

    public static LottieTask<LottieComposition> fromJsonInputStream(final InputStream inputStream, @Nullable final String str) {
        return cache(str, new Callable<LottieResult<LottieComposition>>() { // from class: com.airbnb.lottie.LottieCompositionFactory.4
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public LottieResult<LottieComposition> call() {
                return LottieCompositionFactory.fromJsonInputStreamSync(inputStream, str);
            }
        });
    }

    @WorkerThread
    public static LottieResult<LottieComposition> fromJsonInputStreamSync(InputStream inputStream, @Nullable String str) {
        return fromJsonInputStreamSync(inputStream, str, true);
    }

    @WorkerThread
    private static LottieResult<LottieComposition> fromJsonInputStreamSync(InputStream inputStream, @Nullable String str, boolean z) {
        try {
            return fromJsonReaderSync(JsonReader.of(Okio.buffer(Okio.source(inputStream))), str);
        } finally {
            if (z) {
                Utils.closeQuietly(inputStream);
            }
        }
    }

    @WorkerThread
    public static LottieResult<LottieComposition> fromJsonReaderSync(JsonReader jsonReader, @Nullable String str) {
        return fromJsonReaderSyncInternal(jsonReader, str, true);
    }

    private static LottieResult<LottieComposition> fromJsonReaderSyncInternal(JsonReader jsonReader, @Nullable String str, boolean z) {
        try {
            try {
                LottieComposition lottieComposition = LottieCompositionMoshiParser.parse(jsonReader);
                if (str != null) {
                    LottieCompositionCache.getInstance().put(str, lottieComposition);
                }
                LottieResult<LottieComposition> lottieResult = new LottieResult<>(lottieComposition);
                if (z) {
                    Utils.closeQuietly(jsonReader);
                }
                return lottieResult;
            } catch (Exception e) {
                LottieResult<LottieComposition> lottieResult2 = new LottieResult<>(e);
                if (z) {
                    Utils.closeQuietly(jsonReader);
                }
                return lottieResult2;
            }
        } catch (Throwable th) {
            if (z) {
                Utils.closeQuietly(jsonReader);
            }
            throw th;
        }
    }

    @WorkerThread
    public static LottieResult<LottieComposition> fromZipStreamSync(ZipInputStream zipInputStream, @Nullable String str) {
        try {
            return fromZipStreamSyncInternal(zipInputStream, str);
        } finally {
            Utils.closeQuietly(zipInputStream);
        }
    }

    @WorkerThread
    private static LottieResult<LottieComposition> fromZipStreamSyncInternal(ZipInputStream zipInputStream, @Nullable String str) {
        HashMap map = new HashMap();
        try {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            LottieComposition value = null;
            while (nextEntry != null) {
                String name = nextEntry.getName();
                if (name.contains("__MACOSX")) {
                    zipInputStream.closeEntry();
                } else if (nextEntry.getName().equalsIgnoreCase("manifest.json")) {
                    zipInputStream.closeEntry();
                } else if (nextEntry.getName().contains(".json")) {
                    value = fromJsonReaderSyncInternal(JsonReader.of(Okio.buffer(Okio.source(zipInputStream))), null, false).getValue();
                } else if (name.contains(".png") || name.contains(".webp") || name.contains(".jpg") || name.contains(".jpeg")) {
                    map.put(name.split("/")[r1.length - 1], BitmapFactory.decodeStream(zipInputStream));
                } else {
                    zipInputStream.closeEntry();
                }
                nextEntry = zipInputStream.getNextEntry();
            }
            if (value == null) {
                return new LottieResult<>((Throwable) new IllegalArgumentException("Unable to parse composition"));
            }
            for (Map.Entry entry : map.entrySet()) {
                LottieImageAsset lottieImageAssetFindImageAssetForFileName = findImageAssetForFileName(value, (String) entry.getKey());
                if (lottieImageAssetFindImageAssetForFileName != null) {
                    lottieImageAssetFindImageAssetForFileName.setBitmap(Utils.resizeBitmapIfNeeded((Bitmap) entry.getValue(), lottieImageAssetFindImageAssetForFileName.getWidth(), lottieImageAssetFindImageAssetForFileName.getHeight()));
                }
            }
            for (Map.Entry<String, LottieImageAsset> entry2 : value.getImages().entrySet()) {
                if (entry2.getValue().getBitmap() == null) {
                    return new LottieResult<>((Throwable) new IllegalStateException("There is no image for " + entry2.getValue().getFileName()));
                }
            }
            if (str != null) {
                LottieCompositionCache.getInstance().put(str, value);
            }
            return new LottieResult<>(value);
        } catch (IOException e) {
            return new LottieResult<>((Throwable) e);
        }
    }

    private static Boolean isZipCompressed(BufferedSource bufferedSource) {
        try {
            BufferedSource bufferedSourcePeek = bufferedSource.peek();
            for (byte b : MAGIC) {
                if (bufferedSourcePeek.readByte() != b) {
                    return Boolean.FALSE;
                }
            }
            bufferedSourcePeek.close();
            return Boolean.TRUE;
        } catch (Exception e) {
            Logger.error("Failed to check zip file header", e);
            return Boolean.FALSE;
        }
    }

    @Nullable
    private static LottieImageAsset findImageAssetForFileName(LottieComposition lottieComposition, String str) {
        for (LottieImageAsset lottieImageAsset : lottieComposition.getImages().values()) {
            if (lottieImageAsset.getFileName().equals(str)) {
                return lottieImageAsset;
            }
        }
        return null;
    }

    private static LottieTask<LottieComposition> cache(@Nullable final String str, Callable<LottieResult<LottieComposition>> callable) {
        final LottieComposition lottieComposition = str == null ? null : LottieCompositionCache.getInstance().get(str);
        if (lottieComposition != null) {
            return new LottieTask<>(new Callable<LottieResult<LottieComposition>>() { // from class: com.airbnb.lottie.LottieCompositionFactory.9
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public LottieResult<LottieComposition> call() {
                    return new LottieResult<>(lottieComposition);
                }
            });
        }
        if (str != null) {
            Map<String, LottieTask<LottieComposition>> map = taskCache;
            if (map.containsKey(str)) {
                return map.get(str);
            }
        }
        LottieTask<LottieComposition> lottieTask = new LottieTask<>(callable);
        if (str != null) {
            lottieTask.addListener(new LottieListener<LottieComposition>() { // from class: com.airbnb.lottie.LottieCompositionFactory.10
                @Override // com.airbnb.lottie.LottieListener
                public void onResult(LottieComposition lottieComposition2) {
                    LottieCompositionFactory.taskCache.remove(str);
                }
            });
            lottieTask.addFailureListener(new LottieListener<Throwable>() { // from class: com.airbnb.lottie.LottieCompositionFactory.11
                @Override // com.airbnb.lottie.LottieListener
                public void onResult(Throwable th) {
                    LottieCompositionFactory.taskCache.remove(str);
                }
            });
            taskCache.put(str, lottieTask);
        }
        return lottieTask;
    }
}
