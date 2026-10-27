package com.android.systemui.imageloader;

import android.graphics.Bitmap;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import com.bumptech.glide.signature.ObjectKey;

/* JADX INFO: loaded from: classes.dex */
public class AppThumbnailLoader implements ModelLoader<AppThumbnail, Bitmap> {
    @Override // com.bumptech.glide.load.model.ModelLoader
    public ModelLoader.LoadData<Bitmap> buildLoadData(AppThumbnail recentTaskBean, int width, int height, Options options) {
        return new ModelLoader.LoadData<>(new ObjectKey(recentTaskBean.getTaskKey()), new AppThumbnailFetcher(recentTaskBean));
    }

    @Override // com.bumptech.glide.load.model.ModelLoader
    public boolean handles(AppThumbnail recentTaskBean) {
        return recentTaskBean.getTaskKey() != null;
    }

    public static class Factory implements ModelLoaderFactory<AppThumbnail, Bitmap> {
        @Override // com.bumptech.glide.load.model.ModelLoaderFactory
        public void teardown() {
        }

        @Override // com.bumptech.glide.load.model.ModelLoaderFactory
        public ModelLoader<AppThumbnail, Bitmap> build(MultiModelLoaderFactory multiFactory) {
            return new AppThumbnailLoader();
        }
    }
}
