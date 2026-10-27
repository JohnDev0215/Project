package com.amazon.music.explore.widgets.metadata;

import CoreInterface.v1_0.Method;
import android.net.Uri;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.views.library.metadata.ContentMetadata;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class ExploreMetadata implements ContentMetadata {
    private final MethodsDispatcher methodsDispatcher;
    private final List<Method> onItemSelected;
    private final String ownerId;

    @Override // com.amazon.music.views.library.metadata.ContentMetadata
    public List<String> getTags() {
        return null;
    }

    @Override // com.amazon.music.views.library.metadata.ContentMetadata
    public String getViewId() {
        return null;
    }

    public void setUri(Uri uri) {
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public MethodsDispatcher getMethodsDispatcher() {
        return this.methodsDispatcher;
    }

    public List<Method> getOnItemSelected() {
        return this.onItemSelected;
    }

    @Override // com.amazon.music.views.library.metadata.ContentMetadata
    public Uri getUri() {
        return Uri.parse("");
    }

    public ExploreMetadata(String str, MethodsDispatcher methodsDispatcher, List<Method> list) {
        this.ownerId = str;
        this.methodsDispatcher = methodsDispatcher;
        this.onItemSelected = list;
    }
}
