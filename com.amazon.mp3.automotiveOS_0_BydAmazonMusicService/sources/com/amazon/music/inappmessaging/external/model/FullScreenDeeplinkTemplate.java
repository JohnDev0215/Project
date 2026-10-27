package com.amazon.music.inappmessaging.external.model;

import androidx.annotation.NonNull;
import com.amazon.music.inappmessaging.internal.model.DynamicMessage;
import java.io.Serializable;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class FullScreenDeeplinkTemplate implements Serializable {
    private static final String DEEPLINK = "deeplink";
    public final String deeplink;
    public final String messageRef;
    public final String templateId;

    public FullScreenDeeplinkTemplate(final String deeplink, final String messageRef, final String templateId) {
        this.deeplink = deeplink;
        this.messageRef = messageRef;
        this.templateId = templateId;
    }

    public static FullScreenDeeplinkTemplate fromDynamicMessage(@NonNull final DynamicMessage dynamicMessage) throws IllegalArgumentException {
        Map<String, String> map = dynamicMessage.templateAttributes;
        if (map == null || map.get(DEEPLINK) == null) {
            throw new IllegalArgumentException("Failed to build FullScreenDeeplinkTemplate due to missing required fields");
        }
        return new FullScreenDeeplinkTemplate(dynamicMessage.templateAttributes.get(DEEPLINK), dynamicMessage.messageRef, dynamicMessage.templateId);
    }
}
