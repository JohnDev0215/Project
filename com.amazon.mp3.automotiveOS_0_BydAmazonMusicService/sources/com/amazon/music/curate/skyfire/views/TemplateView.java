package com.amazon.music.curate.skyfire.views;

import CoreInterface.v1_0.Template;
import android.widget.LinearLayout;
import androidx.lifecycle.LifecycleOwner;
import com.amazon.music.curate.skyfire.OwnerRegistration;
import com.amazon.music.skyfire.platform.MethodsDispatcher;

/* JADX INFO: loaded from: classes4.dex */
public abstract class TemplateView<T extends Template> extends LinearLayout implements TemplateViewBinder<T> {
    private final TemplateViewContext templateViewContext;

    public TemplateView(final TemplateViewContext templateViewContext) {
        super(templateViewContext.getContext());
        this.templateViewContext = templateViewContext;
        setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
    }

    public String getOwnerId() {
        return this.templateViewContext.getOwnerId();
    }

    public MethodsDispatcher getMethodsDispatcher() {
        return this.templateViewContext.getMethodsDispatcher();
    }

    public LifecycleOwner getLifecycleOwner() {
        return this.templateViewContext.getViewLifecycleOwner();
    }

    public void handleBackPressed() {
        this.templateViewContext.getParentFragmentManager().popBackStack();
    }

    public OwnerRegistration getOwnerRegistration() {
        return this.templateViewContext.getOwnerRegistration();
    }
}
