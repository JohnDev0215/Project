package com.amazon.music.explore.widgets.binders;

import android.content.Context;
import com.amazon.music.explore.widgets.models.ExploreLiveStreamBarkerModel;
import com.amazon.music.imageloader.ImageLoader;
import com.amazon.music.views.library.binders.LivestreamBarkerBinder;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.click.ClickListenerFactory;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.LivestreamBarkerView;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: loaded from: classes4.dex */
public class ExploreLiveStreamBarkerBinder implements UniversalBinder<LivestreamBarkerView, ExploreLiveStreamBarkerModel> {
    private final ClickListenerFactory clickListenerFactory;
    private Context context;
    private final boolean fullScreen;
    private final ImageLoader imageLoader;
    private final StyleSheet styleSheet;

    public ExploreLiveStreamBarkerBinder(StyleSheet styleSheet, ImageLoader imageLoader, ClickListenerFactory clickListenerFactory, boolean z) {
        this.styleSheet = styleSheet;
        this.imageLoader = imageLoader;
        this.clickListenerFactory = clickListenerFactory;
        this.fullScreen = z;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<ExploreLiveStreamBarkerModel> getModelClass() {
        return Reflection.createKotlinClass(ExploreLiveStreamBarkerModel.class);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public LivestreamBarkerView createView(Context context) {
        this.context = context;
        return new LivestreamBarkerView(context, this.styleSheet, null, 0, 0);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(LivestreamBarkerView livestreamBarkerView, ExploreLiveStreamBarkerModel exploreLiveStreamBarkerModel) {
        LivestreamBarkerBinder.bindWithView(this.context, this.styleSheet, this.imageLoader, this.clickListenerFactory, this.fullScreen, livestreamBarkerView, exploreLiveStreamBarkerModel.convertToBrushV2Model());
        ViewTriggerUtility.getInstance().triggerElementView(exploreLiveStreamBarkerModel, exploreLiveStreamBarkerModel.getWidget().onViewed());
    }
}
