package com.amazon.music.skyfire.core.template;

import CoreInterface.v1_0.Method;
import android.os.Looper;
import com.amazon.music.skyfire.platform.MethodCallback;
import com.amazon.music.skyfire.platform.OnMethodExecutedListener;

/* JADX INFO: loaded from: classes4.dex */
public class TemplateMethodsRegistry implements MethodCallback<Method> {
    private final TemplateStack templateStack;

    @Override // com.amazon.music.skyfire.platform.MethodCallback
    public /* bridge */ /* synthetic */ void executeMethod(Method method, String str, OnMethodExecutedListener<Method> onMethodExecutedListener) {
        executeMethod2(method, str, (OnMethodExecutedListener) onMethodExecutedListener);
    }

    public TemplateMethodsRegistry(TemplateStack templateStack) {
        this.templateStack = templateStack;
    }

    /* JADX INFO: renamed from: executeMethod, reason: avoid collision after fix types in other method */
    public void executeMethod2(final Method method, final String str, final OnMethodExecutedListener onMethodExecutedListener) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.templateStack.handleMethod(str, method);
            onMethodExecutedListener.onMethodExecuted(method);
        } else {
            Handlers.getForeground().post(new Runnable() { // from class: com.amazon.music.skyfire.core.template.TemplateMethodsRegistry.1
                @Override // java.lang.Runnable
                public void run() {
                    TemplateMethodsRegistry.this.templateStack.handleMethod(str, method);
                    onMethodExecutedListener.onMethodExecuted(method);
                }
            });
        }
    }
}
