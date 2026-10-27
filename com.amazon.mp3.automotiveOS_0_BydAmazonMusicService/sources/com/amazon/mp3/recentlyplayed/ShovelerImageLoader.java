package com.amazon.mp3.recentlyplayed;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.IArtCache;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;

/* JADX INFO: loaded from: classes4.dex */
public class ShovelerImageLoader implements IArtCache.IArtLoadedListener {
    private BroadcastReceiver mCacheManagerReceiver = new CacheManagerImageReceiver();
    private CacheManager mCacheManger = CacheManager.getInstance();
    private Context mContext;
    private Listener mListener;

    public interface Listener {
        void onImageLoaded(Drawable d, String source, String id);
    }

    public ShovelerImageLoader(Context context) {
        this.mContext = context;
        context.registerReceiver(this.mCacheManagerReceiver, new IntentFilter("com.amazon.mp3.library.cache.image.CacheManager.ACTION_ALBUM_ARTWORK_CACHED"));
    }

    public void close() {
        this.mCacheManger.pause();
        this.mListener = null;
        this.mContext.unregisterReceiver(this.mCacheManagerReceiver);
    }

    public Drawable loadImage(final ImageLoaderFactory.ItemType itemType, final String source, final int size, final String id) {
        if (ImageLoaderFactory.ItemType.GENRE.equals(itemType)) {
            return this.mCacheManger.get(itemType, source, size, id);
        }
        return this.mCacheManger.getOnCurrentThread(itemType, source, size, id, true);
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
        this.mCacheManger.unpause();
    }

    @Override // com.amazon.mp3.library.cache.image.IArtCache.IArtLoadedListener
    public void onDrawableLoaded(Drawable d, String source, long id) {
        onImageLoaded(d, source, String.valueOf(id));
    }

    @Override // com.amazon.mp3.library.cache.image.IArtCache.IArtLoadedListener
    public void onDrawableLoaded(Drawable d, String source, String id) {
        onImageLoaded(d, source, id);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onImageLoaded(Drawable d, String source, String id) {
        Listener listener = this.mListener;
        if (listener != null) {
            listener.onImageLoaded(d, source, id);
        }
    }

    private class CacheManagerImageReceiver extends BroadcastReceiver {
        private CacheManagerImageReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra("com.amazon.mp3.library.cache.image.CacheManager.EXTRA_IMAGE_TYPE", -1);
            int intExtra2 = intent.getIntExtra("com.amazon.mp3.library.cache.image.CacheManager.EXTRA_IMAGE_SIZE", -1);
            String stringExtra = intent.getStringExtra("com.amazon.mp3.library.cache.image.CacheManager.EXTRA_IMAGE_SOURCE");
            String stringExtra2 = intent.getStringExtra("com.amazon.mp3.library.cache.image.CacheManager.EXTRA_IMAGE_ID");
            ImageLoaderFactory.ItemType imageType = ShovelerImageLoader.getImageType(intExtra);
            if (imageType != null) {
                ShovelerImageLoader.this.onImageLoaded(ShovelerImageLoader.this.mCacheManger.get(imageType, stringExtra, intExtra2, stringExtra2), stringExtra, stringExtra2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ImageLoaderFactory.ItemType getImageType(int value) {
        return ImageLoaderFactory.ItemType.getItemType(value);
    }
}
