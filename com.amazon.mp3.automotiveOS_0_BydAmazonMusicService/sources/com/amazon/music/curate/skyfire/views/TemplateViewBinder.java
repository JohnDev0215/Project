package com.amazon.music.curate.skyfire.views;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Template;

/* JADX INFO: loaded from: classes4.dex */
public interface TemplateViewBinder<T extends Template> {
    void bind(final T t);

    void handleTemplateMethod(final String ownerId, final Method method);

    void onConfigurationChanged();
}
