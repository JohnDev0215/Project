package org.chromium.ui.resources.sprites;

import android.content.res.Resources;
import android.graphics.Bitmap;
import org.chromium.ui.resources.ResourceLoader;

/* JADX INFO: loaded from: classes4.dex */
public class CrushedSpriteResourceLoader {
    private final ResourceLoader.ResourceLoaderCallback mCallback;
    private final Resources mResources;

    public CrushedSpriteResourceLoader(ResourceLoader.ResourceLoaderCallback resourceLoaderCallback, Resources resources) {
        this.mCallback = resourceLoaderCallback;
        this.mResources = resources;
    }

    public void loadResource(int i, int i2) {
        this.mCallback.onResourceLoaded(4, i, new CrushedSpriteResource(i, i2, this.mResources));
    }

    public Bitmap reloadResource(int i) {
        return CrushedSpriteResource.loadBitmap(i, this.mResources);
    }
}
