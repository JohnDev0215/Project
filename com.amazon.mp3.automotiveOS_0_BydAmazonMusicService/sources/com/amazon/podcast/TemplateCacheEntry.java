package com.amazon.podcast;

import SOATemplateListInterface.v1_0.Template;
import com.amazon.podcast.bootstrap.ActionBarProvider;

/* JADX INFO: loaded from: classes5.dex */
public final class TemplateCacheEntry {
    private final ActionBarProvider actionBarProvider;
    private final boolean hideActionBar;
    private final boolean isAlertTemplate;
    private final boolean isCreateAndBind;
    private final MethodsDispatcher methodsDispatcher;
    private final OwnerRegistration ownerRegistration;
    private final Template template;

    public TemplateCacheEntry(final Template template, final boolean hideActionBar, final boolean isCreateAndBind, final boolean isAlertTemplate, final ActionBarProvider actionBarProvider, final MethodsDispatcher methodsDispatcher, final OwnerRegistration ownerRegistration) {
        this.template = template;
        this.hideActionBar = hideActionBar;
        this.isCreateAndBind = isCreateAndBind;
        this.isAlertTemplate = isAlertTemplate;
        this.actionBarProvider = actionBarProvider;
        this.methodsDispatcher = methodsDispatcher;
        this.ownerRegistration = ownerRegistration;
    }

    public Template getTemplate() {
        return this.template;
    }

    public ActionBarProvider getActionBarProvider() {
        return this.actionBarProvider;
    }

    public boolean isCreateAndBind() {
        return this.isCreateAndBind;
    }

    public boolean isAlertTemplate() {
        return this.isAlertTemplate;
    }

    public boolean isHideActionBar() {
        return this.hideActionBar;
    }

    public MethodsDispatcher getMethodsDispatcher() {
        return this.methodsDispatcher;
    }

    public OwnerRegistration getOwnerRegistration() {
        return this.ownerRegistration;
    }
}
