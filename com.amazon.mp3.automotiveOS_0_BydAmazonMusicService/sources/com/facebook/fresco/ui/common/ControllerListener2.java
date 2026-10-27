package com.facebook.fresco.ui.common;

import android.net.Uri;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public interface ControllerListener2<INFO> {

    public static class Extras {

        @Nullable
        public Object callerContext;

        @Nullable
        public Map<String, Object> componentExtras;

        @Nullable
        public Map<String, Object> datasourceExtras;

        @Nullable
        public Map<String, Object> imageExtras;

        @Nullable
        public Uri mainUri;

        @Nullable
        public Object scaleType;

        @Nullable
        public Map<String, Object> shortcutExtras;
        public int viewportWidth = -1;
        public int viewportHeight = -1;
        public float focusX = -1.0f;
        public float focusY = -1.0f;
    }

    void onFailure(String id, @Nullable Throwable throwable, @Nullable Extras extraData);

    void onFinalImageSet(String id, @Nullable INFO imageInfo, @Nullable Extras extraData);

    void onIntermediateImageFailed(String id);

    void onIntermediateImageSet(String id, @Nullable INFO imageInfo);

    void onRelease(String id, @Nullable Extras extraData);

    void onSubmit(String id, @Nullable Object callerContext, @Nullable Extras extraData);
}
