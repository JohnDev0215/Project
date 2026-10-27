package com.amazon.music.skyfire.ui.skyfire;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Template;
import kotlin.Metadata;

/* JADX INFO: compiled from: TemplateMethodListener.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003J\u0015\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0007J\u0015\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0007J\u0010\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\f"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/TemplateMethodListener;", "T", "LCoreInterface/v1_0/Template;", "Lcom/amazon/music/skyfire/ui/skyfire/MethodListener;", "onBindTemplate", "", "template", "(LCoreInterface/v1_0/Template;)V", "onCreateTemplate", "onHandleMethod", "method", "LCoreInterface/v1_0/Method;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface TemplateMethodListener<T extends Template> extends MethodListener {
    void onBindTemplate(T template);

    void onCreateTemplate(T template);

    void onHandleMethod(Method method);
}
