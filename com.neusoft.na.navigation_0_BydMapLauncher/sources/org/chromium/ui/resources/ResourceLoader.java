package org.chromium.ui.resources;

/* JADX INFO: loaded from: classes4.dex */
public abstract class ResourceLoader {
    private final ResourceLoaderCallback mCallback;
    private final int mResourceType;

    public interface ResourceLoaderCallback {
        void onResourceLoaded(int i, int i2, Resource resource);
    }

    public abstract void loadResource(int i);

    public abstract void preloadResource(int i);

    public ResourceLoader(int i, ResourceLoaderCallback resourceLoaderCallback) {
        this.mResourceType = i;
        this.mCallback = resourceLoaderCallback;
    }

    public int getResourceType() {
        return this.mResourceType;
    }

    protected void notifyLoadFinished(int i, Resource resource) {
        ResourceLoaderCallback resourceLoaderCallback = this.mCallback;
        if (resourceLoaderCallback != null) {
            resourceLoaderCallback.onResourceLoaded(getResourceType(), i, resource);
        }
    }
}
