package com.amazon.podcast.views;

import SOATemplateListInterface.v1_0.BindTemplateMethod;
import SOATemplateListInterface.v1_0.Template;
import androidx.appcompat.app.AppCompatActivity;

/* JADX INFO: loaded from: classes5.dex */
public abstract class TemplateActivity<T extends Template> extends AppCompatActivity implements ViewBinder<T> {
    public abstract String getOwnerId();

    public abstract Template getTemplate();

    @Override // com.amazon.podcast.views.ViewBinder
    public void onConfigurationChanged() {
    }

    public void bindTemplate(final String ownerId, final BindTemplateMethod method) {
        if (ownerId.equals(getOwnerId())) {
            bind(method.getTemplate(), true);
        } else {
            handleTemplateMethod(ownerId, method);
        }
    }
}
