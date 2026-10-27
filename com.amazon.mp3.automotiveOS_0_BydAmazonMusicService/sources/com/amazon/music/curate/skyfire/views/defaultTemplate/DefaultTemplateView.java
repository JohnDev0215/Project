package com.amazon.music.curate.skyfire.views.defaultTemplate;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Template;
import android.view.View;
import com.amazon.music.curate.R;
import com.amazon.music.curate.skyfire.views.TemplateView;
import com.amazon.music.curate.skyfire.views.TemplateViewContext;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class DefaultTemplateView extends TemplateView<Template> {
    private List<Method> onViewed;

    @Override // com.amazon.music.curate.skyfire.views.TemplateViewBinder
    public void handleTemplateMethod(final String ownerId, final Method method) {
    }

    @Override // com.amazon.music.curate.skyfire.views.TemplateViewBinder
    public void onConfigurationChanged() {
    }

    public DefaultTemplateView(final TemplateViewContext templateViewContext) {
        super(templateViewContext);
        View.inflate(getContext(), R.layout.library_default_view, this);
    }

    @Override // com.amazon.music.curate.skyfire.views.TemplateViewBinder
    public void bind(final Template template) {
        this.onViewed = template.onViewed();
    }
}
