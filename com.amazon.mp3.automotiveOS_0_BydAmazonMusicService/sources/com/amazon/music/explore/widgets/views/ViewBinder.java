package com.amazon.music.explore.widgets.views;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Template;

/* JADX INFO: loaded from: classes4.dex */
public interface ViewBinder<T extends Template> {
    void bind(T t);

    void handleTemplateMethod(String str, Method method);
}
