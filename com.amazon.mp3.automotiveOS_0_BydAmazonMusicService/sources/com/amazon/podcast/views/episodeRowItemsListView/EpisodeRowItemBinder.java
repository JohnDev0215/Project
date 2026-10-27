package com.amazon.podcast.views.episodeRowItemsListView;

import Podcast.Touch.EpisodeRowItemsInterface.v1_0.EpisodeRowItemElement;
import SOACoreInterface.v1_0.Method;
import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.OwnerRegistration;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.bootstrap.FragmentInfoProvider;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.soa.core.Engine;
import java.util.List;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.reflect.KClass;

/* JADX INFO: loaded from: classes5.dex */
public final class EpisodeRowItemBinder implements UniversalBinder<EpisodeRowItemView, EpisodeRowItemModel>, MethodsDispatcher, OwnerRegistration {
    private Fragment fragment;
    private UiMetricAttributes$PageType pageType = UiMetricAttributes$PageType.PODCASTS_SEE_ALL_EPISODES;
    private String ownerId = Podcast.getDefaultOwnerId();
    private Engine engine = Podcast.getEngine();
    private FragmentInfoProvider fragmentInfoProvider = Podcast.getFragmentInfoProvider();

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<EpisodeRowItemModel> getModelClass() {
        return JvmClassMappingKt.getKotlinClass(EpisodeRowItemModel.class);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public EpisodeRowItemView createView(final Context context) {
        this.fragment = this.fragmentInfoProvider.fragmentManager().getFragments().get(this.fragmentInfoProvider.fragmentManager().getFragments().size() - 1);
        return new EpisodeRowItemView(context, this.ownerId, this, this.fragment, this.fragmentInfoProvider.fragmentManager(), this.pageType);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(final EpisodeRowItemView view, final EpisodeRowItemModel model) {
        EpisodeRowItemElement episodeRowItemElementConvertEpisodeRowItemElement = EpisodeRowItemsMethods.convertEpisodeRowItemElement(model, this.pageType, view.getResources(), view.getContext());
        view.setLayoutParams(new ConstraintLayout.LayoutParams(-1, -2));
        view.bind(episodeRowItemElementConvertEpisodeRowItemElement);
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
