package com.android.systemui.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import com.android.systemui.imageloader.AppLogoLoader;
import com.android.systemui.imageloader.AppThumbnailLoader;
import com.android.systemui.recents.RecentTaskBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.executor.GlideExecutor;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class AppImageGlideModule extends AppGlideModule {
    private static final int DISK_CACHE_SIZE = 104857600;
    private static final int MEMORY_CACHE_SIZE = 5242880;
    private String appRootPath;

    @Override // com.bumptech.glide.module.AppGlideModule
    public boolean isManifestParsingEnabled() {
        return false;
    }

    @Override // com.bumptech.glide.module.AppGlideModule, com.bumptech.glide.module.AppliesOptions
    public void applyOptions(Context context, GlideBuilder builder) {
        super.applyOptions(context, builder);
        BydLogHelper.logD("applyOptions");
        builder.setBitmapPool(new LruBitmapPool(5242880L));
        builder.setMemoryCache(new LruResourceCache(5242880L));
        this.appRootPath = Environment.getExternalStorageDirectory() + "/glideCatch";
        final File file = new File(this.appRootPath);
        builder.setDiskCache(new DiskCache.Factory() { // from class: com.android.systemui.imageloader.AppImageGlideModule.1
            @Override // com.bumptech.glide.load.engine.cache.DiskCache.Factory
            public DiskCache build() {
                return DiskLruCacheWrapper.create(file, 104857600L);
            }
        });
        builder.setSourceExecutor(GlideExecutor.newSourceExecutor());
        builder.setDiskCacheExecutor(GlideExecutor.newDiskCacheExecutor());
        builder.setLogLevel(2);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.format(DecodeFormat.PREFER_ARGB_8888);
        builder.setDefaultRequestOptions(requestOptions);
    }

    @Override // com.bumptech.glide.module.LibraryGlideModule, com.bumptech.glide.module.RegistersComponents
    public void registerComponents(Context context, Glide glide, Registry registry) {
        super.registerComponents(context, glide, registry);
        BydLogHelper.logD("registerComponents");
        registry.prepend(RecentTaskBean.class, Bitmap.class, new AppLogoLoader.Factory());
        registry.prepend(AppThumbnail.class, Bitmap.class, new AppThumbnailLoader.Factory());
    }
}
