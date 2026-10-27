package com.amazon.music.explore.skyfire.containers;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Template;
import TemplateContainerInterface.v1_0.BindStandaloneContainerMethod;
import TemplateContainerInterface.v1_0.CreateAndBindStandaloneContainerMethod;
import TemplateContainerInterface.v1_0.CreateStandaloneContainerMethod;
import androidx.appcompat.app.AppCompatActivity;
import com.amazon.music.explore.skyfire.containers.ContainerMethodOwner;

/* JADX INFO: loaded from: classes4.dex */
public abstract class StandaloneContainerActivity<T extends Template> extends AppCompatActivity implements ContainerMethodOwner {
    private ContainerMethodOwner.CloseCallback closeCallback;

    protected abstract void bindTemplate(String str, T t);

    protected abstract void createAndBindTemplate(String str, T t);

    protected abstract void createTemplate(String str, T t);

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
        if (method instanceof CreateStandaloneContainerMethod) {
            createTemplate(str, ((CreateStandaloneContainerMethod) method).template());
            return;
        }
        if (method instanceof CreateAndBindStandaloneContainerMethod) {
            createAndBindTemplate(str, ((CreateAndBindStandaloneContainerMethod) method).template());
        } else if (method instanceof BindStandaloneContainerMethod) {
            bindTemplate(str, ((BindStandaloneContainerMethod) method).template());
        } else {
            handleTemplateMethod(str, method);
        }
    }
}
