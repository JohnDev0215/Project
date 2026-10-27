package com.amazon.podcast.views.horizontalRowItemsListView;

import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.HorizontalItemElement;
import SOACoreInterface.v1_0.Method;
import android.content.Context;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.providers.EntityTrackingIntentServiceProvider;
import com.amazon.music.views.library.providers.ItemOrdinalProvider;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.OwnerRegistration;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.bootstrap.FragmentInfoProvider;
import com.amazon.soa.core.Engine;
import java.util.List;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.reflect.KClass;

/* JADX INFO: loaded from: classes5.dex */
public final class PodcastEpisodeHorizontalRowItemBinder implements UniversalBinder<HorizontalRowItemView, PodcastEpisodeHorizontalRowItemModel>, MethodsDispatcher, OwnerRegistration {
    private EntityTrackingIntentServiceProvider entityTrackingIntentServiceProvider;
    private Fragment fragment;
    private ItemOrdinalProvider itemOrdinalProvider;
    private String ownerId = Podcast.getDefaultOwnerId();
    private Engine engine = Podcast.getEngine();
    private FragmentInfoProvider fragmentInfoProvider = Podcast.getFragmentInfoProvider();

    public PodcastEpisodeHorizontalRowItemBinder(final ItemOrdinalProvider itemOrdinalProvider, final EntityTrackingIntentServiceProvider entityTrackingIntentServiceProvider) {
        this.itemOrdinalProvider = itemOrdinalProvider;
        this.entityTrackingIntentServiceProvider = entityTrackingIntentServiceProvider;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<PodcastEpisodeHorizontalRowItemModel> getModelClass() {
        return JvmClassMappingKt.getKotlinClass(PodcastEpisodeHorizontalRowItemModel.class);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public HorizontalRowItemView createView(final Context context) {
        this.fragment = this.fragmentInfoProvider.fragmentManager().getFragments().get(this.fragmentInfoProvider.fragmentManager().getFragments().size() - 1);
        return new HorizontalRowItemView(context, this.ownerId, this, this.fragment, true);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(final HorizontalRowItemView view, final PodcastEpisodeHorizontalRowItemModel model) {
        ItemOrdinalProvider itemOrdinalProvider;
        HorizontalItemElement horizontalItemElementConvertEpisodeToHorizontalItemElementForSearchResults = HorizontalRowItemsMethods.convertEpisodeToHorizontalItemElementForSearchResults(model, view.getContext(), view.getResources());
        view.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        if (model.isShouldEnableOrdinal() && model.isShouldShowOrdinal() && model.getIndex().intValue() == -1 && (itemOrdinalProvider = this.itemOrdinalProvider) != null) {
            model.setIndex(Integer.valueOf(itemOrdinalProvider.registerItem(model.getMetadata())));
        }
        view.bind(model.getIndex().intValue(), horizontalItemElementConvertEpisodeToHorizontalItemElementForSearchResults, model, true, this.entityTrackingIntentServiceProvider);
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
}
