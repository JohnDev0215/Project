package com.amazon.podcast.views.browseHomeWidgets;

import Podcast.Touch.VerticalItemElementInterface.v1_0.VerticalItemElement;
import Podcast.Touch.VisualShovelerCompactElementInterface.v1_0.VisualShovelerCompactElement;
import Podcast.Touch.VisualShovelerCompactElementInterface.v1_0.VisualShovelerSeeMoreItemElement;
import SOACoreInterface.v1_0.Method;
import android.content.Context;
import androidx.lifecycle.LifecycleOwner;
import com.amazon.music.uicontentview.ContentViewedListener;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.providers.SeeMoreProvider;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.OwnerRegistration;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.views.Strings;
import com.amazon.podcast.views.galleryTemplate.VisualShovelerCompactView;
import com.amazon.podcast.views.verticalItemView.VerticalItemMethods;
import com.amazon.podcast.views.verticalItemView.VerticalItemModel;
import com.amazon.soa.core.Engine;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.reflect.KClass;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class PodcastVisualShovelerCompactBinder implements UniversalBinder<VisualShovelerCompactView, PodcastVisualShovelerCompactModel>, MethodsDispatcher, OwnerRegistration {
    private ContentViewedListener contentViewedListener;
    private LifecycleOwner lifecycleOwner;
    private SeeMoreProvider seeMoreProvider;
    private final UiMetricAttributes$PageType pageType = UiMetricAttributes$PageType.BROWSE_HOME;
    private final String ownerId = Podcast.getDefaultOwnerId();
    private final Engine engine = Podcast.getEngine();

    public PodcastVisualShovelerCompactBinder(final SeeMoreProvider seeMoreProvider, final ContentViewedListener contentViewedListener, final LifecycleOwner lifecycleOwner) {
        this.seeMoreProvider = seeMoreProvider;
        this.contentViewedListener = contentViewedListener;
        this.lifecycleOwner = lifecycleOwner;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<PodcastVisualShovelerCompactModel> getModelClass() {
        return JvmClassMappingKt.getKotlinClass(PodcastVisualShovelerCompactModel.class);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public VisualShovelerCompactView createView(final Context context) {
        return new VisualShovelerCompactView(context, this.ownerId, this, this.lifecycleOwner, this.pageType);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(final VisualShovelerCompactView view, final PodcastVisualShovelerCompactModel model) {
        VerticalItemElement verticalItemElementConvertShowToVerticalItemElement;
        List<VerticalItemModel> verticalItemElements = model.getVerticalItemElements();
        ArrayList arrayList = new ArrayList();
        for (VerticalItemModel verticalItemModel : verticalItemElements) {
            if (!StringUtils.isBlank(verticalItemModel.getPodcastEpisodeCatalogId())) {
                verticalItemElementConvertShowToVerticalItemElement = VerticalItemMethods.convertEpisodeToVerticalItemElement(verticalItemModel, this.pageType, view.getResources(), view.getContext());
            } else {
                verticalItemElementConvertShowToVerticalItemElement = VerticalItemMethods.convertShowToVerticalItemElement(verticalItemModel, this.pageType, view.getResources(), view.getContext());
            }
            if (verticalItemModel.isProgressEnabled()) {
                verticalItemElementConvertShowToVerticalItemElement.setSecondaryText(Strings.getDurationString(view.getResources(), verticalItemModel.getDurationSeconds() * 1000));
            }
            arrayList.add(verticalItemElementConvertShowToVerticalItemElement);
        }
        VisualShovelerCompactElement.Builder builderWithItems = VisualShovelerCompactElement.builder().withHeader(model.getTitle()).withItems(arrayList);
        if (!StringUtils.isBlank(model.getSeeMoreText())) {
            builderWithItems.withSeeMore(VisualShovelerSeeMoreItemElement.builder().withText(model.getSeeMoreText()).withOnItemSelected(Collections.EMPTY_LIST).build());
        }
        view.bind(builderWithItems.build(), model, this.seeMoreProvider);
        reportContentView(model.getViewReferenceId(), verticalItemElements.size());
    }

    @Override // com.amazon.podcast.MethodsDispatcher
    public void dispatch(final String ownerId, final List<Method> methods) {
        this.engine.handleMethods(ownerId, methods);
    }

    @Override // com.amazon.podcast.OwnerRegistration
    public void registerOwner(final String ownerId) {
        this.engine.registerOwner(ownerId);
    }

    @Override // com.amazon.podcast.OwnerRegistration
    public void deregisterOwner(final String ownerId) {
        this.engine.deregisterOwner(ownerId);
    }

    private void reportContentView(final String blockRef, final int size) {
        HashMap map = new HashMap();
        map.put("blockRef", blockRef);
        map.put("firstViewableIndex", Integer.toString(0));
        map.put("lastViewableIndex", Integer.toString(size - 1));
        map.put("numItemsAccessible", Integer.toString(size));
        this.contentViewedListener.onItemRendered(blockRef, map);
    }
}
