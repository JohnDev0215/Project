package com.android.systemui.imageloader;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.android.systemui.recents.RecentTaskBean;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import com.bumptech.glide.signature.ObjectKey;

/* JADX INFO: loaded from: classes.dex */
public class AppLogoLoader implements ModelLoader<RecentTaskBean, Bitmap> {
    @Override // com.bumptech.glide.load.model.ModelLoader
    public ModelLoader.LoadData<Bitmap> buildLoadData(RecentTaskBean recentTaskBean, int width, int height, Options options) {
        return new ModelLoader.LoadData<>(new ObjectKey(recentTaskBean.getAppName()), new AppLogoFetcher(recentTaskBean));
    }

    @Override // com.bumptech.glide.load.model.ModelLoader
    public boolean handles(RecentTaskBean recentTaskBean) {
        return !TextUtils.isEmpty(recentTaskBean.getAppName());
    }

    public static class Factory implements ModelLoaderFactory<RecentTaskBean, Bitmap> {
        @Override // com.bumptech.glide.load.model.ModelLoaderFactory
        public void teardown() {
        }

        @Override // com.bumptech.glide.load.model.ModelLoaderFactory
        public ModelLoader<RecentTaskBean, Bitmap> build(MultiModelLoaderFactory multiFactory) {
            return new AppLogoLoader();
        }
    }
}
