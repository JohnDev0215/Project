package com.amazon.music.media.playback.config;

import android.graphics.Bitmap;
import android.net.Uri;
import androidx.annotation.NonNull;
import com.amazon.music.media.playback.config.PlaybackConfig;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class ScalingImageLoaders {
    private static final Map<PlaybackConfig, ScalingImageLoaders> loadersMap = new WeakHashMap();
    private WeakReference<PlaybackConfig> configRef;
    private WeakReference<ItemLoader<Uri, Bitmap>> sourceLoaderRef;
    private final Object lock = new Object();
    private final Map<SizeInfo, WeakReference<CachedItemLoader<Uri, Bitmap>>> loaders = new HashMap();
    private final PlaybackConfig.OnConfigurationChangedListener listener = new PlaybackConfig.OnConfigurationChangedListener() { // from class: com.amazon.music.media.playback.config.ScalingImageLoaders.1
        @Override // com.amazon.music.media.playback.config.PlaybackConfig.OnConfigurationChangedListener
        public void onConfigurationChanged(PlaybackConfig playbackConfig) {
            synchronized (ScalingImageLoaders.this.lock) {
                if (ScalingImageLoaders.this.sourceLoaderRef == null) {
                    return;
                }
                ItemLoader<Uri, Bitmap> imageLoader = playbackConfig.getImageLoader();
                if (imageLoader == ((ItemLoader) ScalingImageLoaders.this.sourceLoaderRef.get())) {
                    return;
                }
                ScalingImageLoaders.this.sourceLoaderRef = new WeakReference(imageLoader);
                ScalingImageLoaders.this.onLoaderChanged(imageLoader);
                if (ScalingImageLoaders.this.loaders.isEmpty()) {
                    ScalingImageLoaders.this.sourceLoaderRef = null;
                    playbackConfig.removeOnConfigurationChangedListener(this);
                }
            }
        }
    };

    public static ScalingImageLoaders getInstance(@NonNull PlaybackConfig playbackConfig) {
        ScalingImageLoaders scalingImageLoaders;
        Map<PlaybackConfig, ScalingImageLoaders> map = loadersMap;
        synchronized (map) {
            scalingImageLoaders = map.get(Validate.notNull(playbackConfig));
            if (scalingImageLoaders == null) {
                scalingImageLoaders = new ScalingImageLoaders(playbackConfig);
                map.put(playbackConfig, scalingImageLoaders);
            }
        }
        return scalingImageLoaders;
    }

    private static class SizeInfo {
        public final int height;
        public final int width;

        public SizeInfo(int i, int i2) {
            this.width = Math.max(0, i);
            this.height = Math.max(0, i2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            SizeInfo sizeInfo = (SizeInfo) obj;
            return this.width == sizeInfo.width && this.height == sizeInfo.height;
        }

        public int hashCode() {
            int i = this.width;
            int i2 = this.height;
            return i + i2 + (i % 31) + (i2 % 17);
        }
    }

    private ScalingImageLoaders(PlaybackConfig playbackConfig) {
        this.configRef = new WeakReference<>(playbackConfig);
    }

    public ItemLoader<Uri, Bitmap> getImageLoader(int i, int i2) {
        ItemLoader<Uri, Bitmap> imageLoader;
        CachedItemLoader<Uri, Bitmap> cachedItemLoader;
        SizeInfo sizeInfo = new SizeInfo(i, i2);
        synchronized (this.loaders) {
            WeakReference<CachedItemLoader<Uri, Bitmap>> weakReference = this.loaders.get(sizeInfo);
            if (weakReference != null && (cachedItemLoader = weakReference.get()) != null) {
                return cachedItemLoader;
            }
            PlaybackConfig playbackConfig = this.configRef.get();
            if (playbackConfig != null) {
                imageLoader = playbackConfig.getImageLoader();
                if (this.sourceLoaderRef == null) {
                    this.sourceLoaderRef = new WeakReference<>(imageLoader);
                    playbackConfig.addOnConfigurationChangedListener(this.listener);
                }
            } else {
                imageLoader = this.sourceLoaderRef.get();
            }
            if (imageLoader == null) {
                throw new IllegalStateException("Source PlaybackConfig or ImageLoader has been garbage collected!");
            }
            CachedItemLoader<Uri, Bitmap> cachedItemLoaderCreateLoader = createLoader(imageLoader, sizeInfo.width, sizeInfo.height);
            this.loaders.put(sizeInfo, new WeakReference<>(cachedItemLoaderCreateLoader));
            return cachedItemLoaderCreateLoader;
        }
    }

    private CachedItemLoader<Uri, Bitmap> createLoader(ItemLoader<Uri, Bitmap> itemLoader, int i, int i2) {
        return new CachedItemLoader<>(new ConvertingItemLoader(itemLoader, new BitmapScalingConverter(i, i2)), 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onLoaderChanged(ItemLoader<Uri, Bitmap> itemLoader) {
        Iterator<WeakReference<CachedItemLoader<Uri, Bitmap>>> it = this.loaders.values().iterator();
        while (it.hasNext()) {
            CachedItemLoader<Uri, Bitmap> cachedItemLoader = it.next().get();
            if (cachedItemLoader == null) {
                it.remove();
            } else {
                ((ConvertingItemLoader) cachedItemLoader.getSource()).setSource(itemLoader);
            }
        }
    }
}
