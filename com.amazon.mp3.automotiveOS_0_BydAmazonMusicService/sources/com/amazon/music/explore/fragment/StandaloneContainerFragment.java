package com.amazon.music.explore.fragment;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Template;
import TemplateContainerInterface.v1_0.BindStandaloneContainerMethod;
import TemplateContainerInterface.v1_0.CreateAndBindStandaloneContainerMethod;
import TemplateContainerInterface.v1_0.CreateStandaloneContainerMethod;
import androidx.fragment.app.Fragment;
import com.amazon.music.explore.skyfire.containers.ContainerMethodOwner;

/* JADX INFO: loaded from: classes4.dex */
public abstract class StandaloneContainerFragment<T extends Template> extends Fragment implements ContainerMethodOwner {
    private ContainerMethodOwner.CloseCallback closeCallback;

    protected abstract void onBindTemplate(String str, T t);

    protected abstract void onCreateAndBindTemplate(String str, T t);

    protected abstract void onCreateTemplate(String str, T t);

    protected abstract void onHandleTemplateMethod(String str, Method method);

    @Override // com.amazon.music.explore.skyfire.containers.ContainerMethodOwner
    public void setCloseCallback(ContainerMethodOwner.CloseCallback closeCallback) {
        this.closeCallback = closeCallback;
    }

    protected void onClose() {
        this.closeCallback.onClose();
    }

    @Override // com.amazon.music.explore.skyfire.containers.ContainerMethodOwner
    public void handleMethod(String str, Method method) {
        if (method instanceof CreateStandaloneContainerMethod) {
            onCreateTemplate(str, ((CreateStandaloneContainerMethod) method).template());
            return;
        }
        if (method instanceof CreateAndBindStandaloneContainerMethod) {
            onCreateAndBindTemplate(str, ((CreateAndBindStandaloneContainerMethod) method).template());
        } else if (method instanceof BindStandaloneContainerMethod) {
            onBindTemplate(str, ((BindStandaloneContainerMethod) method).template());
        } else {
            onHandleTemplateMethod(str, method);
        }
    }
}
