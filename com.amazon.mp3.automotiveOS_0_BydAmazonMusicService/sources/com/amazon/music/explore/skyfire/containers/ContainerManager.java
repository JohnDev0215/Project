package com.amazon.music.explore.skyfire.containers;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Template;
import TemplateContainerInterface.v1_0.BindManagedContainerMethod;
import TemplateContainerInterface.v1_0.CreateAndBindManagedContainerMethod;
import TemplateContainerInterface.v1_0.CreateManagedContainerMethod;
import com.amazon.music.explore.skyfire.containers.ContainerMethodOwner;

/* JADX INFO: loaded from: classes4.dex */
public abstract class ContainerManager implements ContainerMethodOwner {
    private ContainerMethodOwner.CloseCallback closeCallback;

    protected abstract void bindTemplate(String str, Template template);

    protected abstract void createAndBindTemplate(String str, Template template);

    protected abstract void createTemplate(String str, Template template);

    protected abstract void handleTemplateMethod(String str, Method method);

    @Override // com.amazon.music.explore.skyfire.containers.ContainerMethodOwner
    public void setCloseCallback(ContainerMethodOwner.CloseCallback closeCallback) {
        this.closeCallback = closeCallback;
    }

    protected void onClose() {
        this.closeCallback.onClose();
    }

    @Override // com.amazon.music.explore.skyfire.containers.ContainerMethodOwner
    public void handleMethod(String str, Method method) {
        if (method instanceof CreateManagedContainerMethod) {
            createTemplate(str, ((CreateManagedContainerMethod) method).template());
            return;
        }
        if (method instanceof CreateAndBindManagedContainerMethod) {
            createAndBindTemplate(str, ((CreateAndBindManagedContainerMethod) method).template());
        } else if (method instanceof BindManagedContainerMethod) {
            bindTemplate(str, ((BindManagedContainerMethod) method).template());
        } else {
            handleTemplateMethod(str, method);
        }
    }
}
