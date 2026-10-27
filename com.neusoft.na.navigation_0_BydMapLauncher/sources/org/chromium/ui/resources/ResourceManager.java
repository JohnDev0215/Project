package org.chromium.ui.resources;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.SparseArray;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.gfx.DeviceDisplayInfo;
import org.chromium.ui.resources.ResourceLoader;
import org.chromium.ui.resources.dynamics.DynamicResourceLoader;
import org.chromium.ui.resources.sprites.CrushedSpriteResource;
import org.chromium.ui.resources.sprites.CrushedSpriteResourceLoader;
import org.chromium.ui.resources.statics.StaticResourceLoader;
import org.chromium.ui.resources.system.SystemResourceLoader;

/* JADX INFO: loaded from: classes4.dex */
@JNINamespace("ui")
public class ResourceManager implements ResourceLoader.ResourceLoaderCallback {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final CrushedSpriteResourceLoader mCrushedSpriteResourceLoader;
    private long mNativeResourceManagerPtr;
    private final float mPxToDp;
    private final SparseArray<ResourceLoader> mResourceLoaders = new SparseArray<>();
    private final SparseArray<SparseArray<LayoutResource>> mLoadedResources = new SparseArray<>();

    private native void nativeOnCrushedSpriteResourceReady(long j, int i, Bitmap bitmap, int[][] iArr, int i2, int i3, float f, float f2);

    private native void nativeOnCrushedSpriteResourceReloaded(long j, int i, Bitmap bitmap);

    private native void nativeOnResourceReady(long j, int i, int i2, Bitmap bitmap, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10);

    private ResourceManager(Resources resources, int i, long j) {
        this.mPxToDp = 1.0f / resources.getDisplayMetrics().density;
        registerResourceLoader(new StaticResourceLoader(0, this, resources));
        registerResourceLoader(new DynamicResourceLoader(1, this));
        registerResourceLoader(new DynamicResourceLoader(2, this));
        registerResourceLoader(new SystemResourceLoader(3, this, i));
        this.mCrushedSpriteResourceLoader = new CrushedSpriteResourceLoader(this, resources);
        this.mNativeResourceManagerPtr = j;
    }

    private static ResourceManager create(WindowAndroid windowAndroid, long j) {
        Context context = windowAndroid.getContext().get();
        if (context == null) {
            throw new IllegalStateException("Context should not be null during initialization.");
        }
        DeviceDisplayInfo deviceDisplayInfoCreate = DeviceDisplayInfo.create(context);
        return new ResourceManager(context.getResources(), Math.min(deviceDisplayInfoCreate.getPhysicalDisplayWidth() != 0 ? deviceDisplayInfoCreate.getPhysicalDisplayWidth() : deviceDisplayInfoCreate.getDisplayWidth(), deviceDisplayInfoCreate.getPhysicalDisplayHeight() != 0 ? deviceDisplayInfoCreate.getPhysicalDisplayHeight() : deviceDisplayInfoCreate.getDisplayHeight()), j);
    }

    public DynamicResourceLoader getDynamicResourceLoader() {
        return (DynamicResourceLoader) this.mResourceLoaders.get(1);
    }

    public DynamicResourceLoader getBitmapDynamicResourceLoader() {
        return (DynamicResourceLoader) this.mResourceLoaders.get(2);
    }

    public void preloadResources(int i, int[] iArr, int[] iArr2) {
        ResourceLoader resourceLoader = this.mResourceLoaders.get(i);
        if (iArr2 != null) {
            for (int i2 : iArr2) {
                resourceLoader.preloadResource(Integer.valueOf(i2).intValue());
            }
        }
        if (iArr != null) {
            for (int i3 : iArr) {
                resourceLoader.loadResource(Integer.valueOf(i3).intValue());
            }
        }
    }

    public LayoutResource getResource(int i, int i2) {
        SparseArray<LayoutResource> sparseArray = this.mLoadedResources.get(i);
        if (sparseArray != null) {
            return sparseArray.get(i2);
        }
        return null;
    }

    @Override // org.chromium.ui.resources.ResourceLoader.ResourceLoaderCallback
    public void onResourceLoaded(int i, int i2, Resource resource) {
        if (resource == null) {
            return;
        }
        if (i != 4) {
            saveMetadataForLoadedResource(i, i2, resource);
        }
        if (this.mNativeResourceManagerPtr == 0) {
            return;
        }
        if (i == 4) {
            if (resource.getBitmap() != null) {
                CrushedSpriteResource crushedSpriteResource = (CrushedSpriteResource) resource;
                nativeOnCrushedSpriteResourceReady(this.mNativeResourceManagerPtr, i2, crushedSpriteResource.getBitmap(), crushedSpriteResource.getFrameRectangles(), crushedSpriteResource.getUnscaledSpriteWidth(), crushedSpriteResource.getUnscaledSpriteHeight(), crushedSpriteResource.getScaledSpriteWidth(), crushedSpriteResource.getScaledSpriteHeight());
                return;
            }
            return;
        }
        Rect padding = resource.getPadding();
        Rect aperture = resource.getAperture();
        nativeOnResourceReady(this.mNativeResourceManagerPtr, i, i2, resource.getBitmap(), padding.left, padding.top, padding.right, padding.bottom, aperture.left, aperture.top, aperture.right, aperture.bottom);
    }

    private void saveMetadataForLoadedResource(int i, int i2, Resource resource) {
        SparseArray<LayoutResource> sparseArray = this.mLoadedResources.get(i);
        if (sparseArray == null) {
            sparseArray = new SparseArray<>();
            this.mLoadedResources.put(i, sparseArray);
        }
        sparseArray.put(i2, new LayoutResource(this.mPxToDp, resource));
    }

    private void destroy() {
        this.mNativeResourceManagerPtr = 0L;
    }

    private void resourceRequested(int i, int i2) {
        ResourceLoader resourceLoader = this.mResourceLoaders.get(i);
        if (resourceLoader != null) {
            resourceLoader.loadResource(i2);
        }
    }

    private void preloadResource(int i, int i2) {
        ResourceLoader resourceLoader = this.mResourceLoaders.get(i);
        if (resourceLoader != null) {
            resourceLoader.preloadResource(i2);
        }
    }

    private void crushedSpriteResourceRequested(int i, int i2, boolean z) {
        if (z) {
            Bitmap bitmapReloadResource = this.mCrushedSpriteResourceLoader.reloadResource(i);
            if (bitmapReloadResource != null) {
                nativeOnCrushedSpriteResourceReloaded(this.mNativeResourceManagerPtr, i, bitmapReloadResource);
                return;
            }
            return;
        }
        this.mCrushedSpriteResourceLoader.loadResource(i, i2);
    }

    private long getNativePtr() {
        return this.mNativeResourceManagerPtr;
    }

    private void registerResourceLoader(ResourceLoader resourceLoader) {
        this.mResourceLoaders.put(resourceLoader.getResourceType(), resourceLoader);
    }
}
