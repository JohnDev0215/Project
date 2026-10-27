package com.amazon.podcast.views;

import SOACoreInterface.v1_0.Method;
import SOATemplateListInterface.v1_0.Template;

/* JADX INFO: loaded from: classes5.dex */
public interface ViewBinder<T extends Template> {
    void bind(final T t, final boolean hideActionBar);

    void handleTemplateMethod(final String ownerId, final Method method);

    void onConfigurationChanged();
}
