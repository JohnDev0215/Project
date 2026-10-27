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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class PodcastHorizontalRowItemBinder implements UniversalBinder<HorizontalRowItemView, PodcastHorizontalRowItemModel>, MethodsDispatcher, OwnerRegistration {
    private static final Logger logger = LoggerFactory.getLogger("PodcastHorizontalRowItemBinder");
    private EntityTrackingIntentServiceProvider entityTrackingIntentServiceProvider;
    private Fragment fragment;
    private ItemOrdinalProvider itemOrdinalProvider;
    private String ownerId = Podcast.getDefaultOwnerId();
    private Engine engine = Podcast.getEngine();
    private FragmentInfoProvider fragmentInfoProvider = Podcast.getFragmentInfoProvider();

    public PodcastHorizontalRowItemBinder(final ItemOrdinalProvider itemOrdinalProvider, final EntityTrackingIntentServiceProvider entityTrackingIntentServiceProvider) {
        this.itemOrdinalProvider = itemOrdinalProvider;
        this.entityTrackingIntentServiceProvider = entityTrackingIntentServiceProvider;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<PodcastHorizontalRowItemModel> getModelClass() {
        return JvmClassMappingKt.getKotlinClass(PodcastHorizontalRowItemModel.class);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public HorizontalRowItemView createView(final Context context) {
        this.fragment = this.fragmentInfoProvider.fragmentManager().getFragments().get(this.fragmentInfoProvider.fragmentManager().getFragments().size() - 1);
        return new HorizontalRowItemView(context, this.ownerId, this, this.fragment, true);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(final HorizontalRowItemView view, final PodcastHorizontalRowItemModel horizontalRowItemModel) {
        HorizontalItemElement horizontalItemElementConvertHorizontalItemElement;
        ItemOrdinalProvider itemOrdinalProvider;
        boolean zIsSearchResult = horizontalRowItemModel.isSearchResult();
        if (zIsSearchResult) {
            horizontalItemElementConvertHorizontalItemElement = HorizontalRowItemsMethods.convertHorizontalItemElementForSearchResults(horizontalRowItemModel, view.getContext(), view.getResources());
        } else {
            horizontalItemElementConvertHorizontalItemElement = HorizontalRowItemsMethods.convertHorizontalItemElement(horizontalRowItemModel, view.getContext(), view.getResources());
        }
        HorizontalItemElement horizontalItemElement = horizontalItemElementConvertHorizontalItemElement;
        view.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        if (horizontalRowItemModel.isShouldEnableOrdinal() && horizontalRowItemModel.isShouldShowOrdinal() && horizontalRowItemModel.getIndex().intValue() == -1 && (itemOrdinalProvider = this.itemOrdinalProvider) != null) {
            horizontalRowItemModel.setIndex(Integer.valueOf(itemOrdinalProvider.registerItem(horizontalRowItemModel.getMetadata())));
        }
        view.bind(horizontalRowItemModel.getIndex().intValue(), horizontalItemElement, horizontalRowItemModel, zIsSearchResult, this.entityTrackingIntentServiceProvider);
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
