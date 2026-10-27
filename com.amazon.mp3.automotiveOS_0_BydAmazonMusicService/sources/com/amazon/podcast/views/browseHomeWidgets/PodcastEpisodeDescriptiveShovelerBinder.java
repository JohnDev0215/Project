package com.amazon.podcast.views.browseHomeWidgets;

import Podcast.Touch.EpisodeDescriptiveShovelerElementInterface.v1_0.EpisodeDescriptiveSeeMoreItemElement;
import Podcast.Touch.EpisodeDescriptiveShovelerElementInterface.v1_0.EpisodeDescriptiveShovelerElement;
import SOACoreInterface.v1_0.Method;
import android.content.Context;
import androidx.fragment.app.Fragment;
import com.amazon.music.uicontentview.ContentViewedListener;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.providers.SeeMoreProvider;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.OwnerRegistration;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.bootstrap.FragmentInfoProvider;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.views.episodeRowItemsListView.EpisodeRowItemModel;
import com.amazon.podcast.views.episodeRowItemsListView.EpisodeRowItemsMethods;
import com.amazon.podcast.views.galleryTemplate.EpisodeDescriptiveShovelerView;
import com.amazon.soa.core.Engine;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.reflect.KClass;

/* JADX INFO: loaded from: classes5.dex */
public class PodcastEpisodeDescriptiveShovelerBinder implements UniversalBinder<EpisodeDescriptiveShovelerView, PodcastEpisodeDescriptiveShovelerModel>, MethodsDispatcher, OwnerRegistration {
    private ContentViewedListener contentViewedListener;
    private Fragment fragment;
    private SeeMoreProvider seeMoreProvider;
    private final UiMetricAttributes$PageType pageType = UiMetricAttributes$PageType.BROWSE_HOME;
    private final String ownerId = Podcast.getDefaultOwnerId();
    private final Engine engine = Podcast.getEngine();
    private final FragmentInfoProvider fragmentInfoProvider = Podcast.getFragmentInfoProvider();

    public PodcastEpisodeDescriptiveShovelerBinder(final SeeMoreProvider seeMoreProvider, final ContentViewedListener contentViewedListener) {
        this.seeMoreProvider = seeMoreProvider;
        this.contentViewedListener = contentViewedListener;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<PodcastEpisodeDescriptiveShovelerModel> getModelClass() {
        return JvmClassMappingKt.getKotlinClass(PodcastEpisodeDescriptiveShovelerModel.class);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public EpisodeDescriptiveShovelerView createView(final Context context) {
        this.fragment = this.fragmentInfoProvider.fragmentManager().getFragments().get(this.fragmentInfoProvider.fragmentManager().getFragments().size() - 1);
        return new EpisodeDescriptiveShovelerView(context, this.ownerId, this, this.fragment, this.fragmentInfoProvider.fragmentManager(), this.pageType);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(final EpisodeDescriptiveShovelerView view, final PodcastEpisodeDescriptiveShovelerModel model) {
        List<EpisodeRowItemModel> episodeRowItemModels = model.getEpisodeRowItemModels();
        ArrayList arrayList = new ArrayList();
        Iterator<EpisodeRowItemModel> it = episodeRowItemModels.iterator();
        while (it.hasNext()) {
            arrayList.add(EpisodeRowItemsMethods.convertEpisodeRowItemElement(it.next(), this.pageType, view.getResources(), view.getContext()));
        }
        view.bind(EpisodeDescriptiveShovelerElement.builder().withHeader(model.getTitle()).withItems(arrayList).withSeeMore(EpisodeDescriptiveSeeMoreItemElement.builder().withText(model.getSeeMoreText()).withOnItemSelected(Collections.EMPTY_LIST).build()).build(), model, this.seeMoreProvider);
        reportContentView(model.getViewReferenceId(), episodeRowItemModels.size());
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
