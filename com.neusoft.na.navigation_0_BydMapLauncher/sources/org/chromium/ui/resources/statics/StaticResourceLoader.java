package org.chromium.ui.resources.statics;

import android.content.res.Resources;
import org.chromium.ui.resources.Resource;
import org.chromium.ui.resources.ResourceLoader;
import org.chromium.ui.resources.async.AsyncPreloadResourceLoader;

/* JADX INFO: loaded from: classes4.dex */
public class StaticResourceLoader extends AsyncPreloadResourceLoader {
    public StaticResourceLoader(int i, ResourceLoader.ResourceLoaderCallback resourceLoaderCallback, final Resources resources) {
        super(i, resourceLoaderCallback, new AsyncPreloadResourceLoader.ResourceCreator() { // from class: org.chromium.ui.resources.statics.StaticResourceLoader.1
            @Override // org.chromium.ui.resources.async.AsyncPreloadResourceLoader.ResourceCreator
            public Resource create(int i2) {
                return StaticResource.create(resources, i2, 0, 0);
            }
        });
    }
}
