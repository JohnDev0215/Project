package com.amazon.music.skyfire.core.template;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Template;
import TemplateListInterface.v1_0.BindTemplateMethod;

/* JADX INFO: loaded from: classes4.dex */
public interface TemplateInstance {
    void bindTemplateMethod(String str, BindTemplateMethod bindTemplateMethod);

    void close();

    void closeAsSingleInstance();

    void closeAsSingleUse();

    void create(Template template);

    void createAndBind(Template template);

    String getOwnerId();

    Template getTemplate();

    void handleTemplateMethod(String str, Method method);

    void invalidate();
}
