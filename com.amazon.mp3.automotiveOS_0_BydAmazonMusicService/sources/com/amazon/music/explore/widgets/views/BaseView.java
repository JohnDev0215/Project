package com.amazon.music.explore.widgets.views;

import CoreInterface.v1_0.Template;
import android.content.Context;
import android.widget.LinearLayout;
import com.amazon.music.skyfire.platform.MethodsDispatcher;

/* JADX INFO: loaded from: classes4.dex */
public abstract class BaseView<T extends Template> extends LinearLayout implements ViewBinder<T> {
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;

    public BaseView(String str, Context context, MethodsDispatcher methodsDispatcher) {
        super(context);
        setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        this.ownerId = str;
        this.methodsDispatcher = methodsDispatcher;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public MethodsDispatcher getMethodsDispatcher() {
        return this.methodsDispatcher;
    }
}
