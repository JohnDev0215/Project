package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.core.ImagePipelineConfigInterface;
import com.facebook.imagepipeline.image.EncodedImageOrigin;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public interface ProducerContext {
    void addCallbacks(ProducerContextCallbacks callbacks);

    Object getCallerContext();

    @Nullable
    <E> E getExtra(String key);

    Map<String, Object> getExtras();

    String getId();

    ImagePipelineConfigInterface getImagePipelineConfig();

    ImageRequest getImageRequest();

    ImageRequest.RequestLevel getLowestPermittedRequestLevel();

    Priority getPriority();

    ProducerListener2 getProducerListener();

    @Nullable
    String getUiComponentId();

    boolean isIntermediateResultExpected();

    boolean isPrefetch();

    void putExtras(@Nullable Map<String, ?> extras);

    void putOriginExtra(@Nullable String origin);

    void putOriginExtra(@Nullable String origin, @Nullable String subcategory);

    void setEncodedImageOrigin(EncodedImageOrigin encodedImageOrigin);

    <E> void setExtra(String key, @Nullable E value);
}
