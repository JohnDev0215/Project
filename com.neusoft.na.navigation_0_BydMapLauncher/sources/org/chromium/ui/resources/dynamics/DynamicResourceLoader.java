package org.chromium.ui.resources.dynamics;

import android.util.SparseArray;
import org.chromium.ui.resources.ResourceLoader;

/* JADX INFO: loaded from: classes4.dex */
public class DynamicResourceLoader extends ResourceLoader {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final SparseArray<DynamicResource> mDynamicResources;

    @Override // org.chromium.ui.resources.ResourceLoader
    public void preloadResource(int i) {
    }

    public DynamicResourceLoader(int i, ResourceLoader.ResourceLoaderCallback resourceLoaderCallback) {
        super(i, resourceLoaderCallback);
        this.mDynamicResources = new SparseArray<>();
    }

    public void registerResource(int i, DynamicResource dynamicResource) {
        this.mDynamicResources.put(i, dynamicResource);
    }

    public void unregisterResource(int i) {
        this.mDynamicResources.remove(i);
    }

    @Override // org.chromium.ui.resources.ResourceLoader
    public void loadResource(int i) {
        DynamicResource dynamicResource = this.mDynamicResources.get(i);
        if (dynamicResource != null && dynamicResource.isDirty()) {
            notifyLoadFinished(i, dynamicResource);
        }
    }
}
