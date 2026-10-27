package com.amazon.podcast.views.verticalItemView;

import Podcast.Touch.VerticalItemElementInterface.v1_0.VerticalItemElement;
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
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public final class VerticalItemBinder implements UniversalBinder<VerticalItemView, VerticalItemModel>, MethodsDispatcher, OwnerRegistration {
    private Fragment fragment;
    private UiMetricAttributes$PageType pageType = UiMetricAttributes$PageType.PODCASTS_SEE_ALL_EPISODES;
    private String ownerId = Podcast.getDefaultOwnerId();
    private Engine engine = Podcast.getEngine();
    private FragmentInfoProvider fragmentInfoProvider = Podcast.getFragmentInfoProvider();

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<VerticalItemModel> getModelClass() {
        return JvmClassMappingKt.getKotlinClass(VerticalItemModel.class);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public VerticalItemView createView(final Context context) {
        this.fragment = this.fragmentInfoProvider.fragmentManager().getFragments().get(this.fragmentInfoProvider.fragmentManager().getFragments().size() - 1);
        return new VerticalItemView(context, this.ownerId, this, this.fragment, this.fragmentInfoProvider.fragmentManager(), this.pageType);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(final VerticalItemView view, final VerticalItemModel model) {
        VerticalItemElement verticalItemElementConvertShowToVerticalItemElement;
        if (!StringUtils.isBlank(model.getPodcastEpisodeCatalogId())) {
            verticalItemElementConvertShowToVerticalItemElement = VerticalItemMethods.convertEpisodeToVerticalItemElement(model, this.pageType, view.getResources(), view.getContext());
        } else {
            verticalItemElementConvertShowToVerticalItemElement = VerticalItemMethods.convertShowToVerticalItemElement(model, this.pageType, view.getResources(), view.getContext());
        }
        view.setLayoutParams(new ConstraintLayout.LayoutParams(-1, -2));
        view.bind(verticalItemElementConvertShowToVerticalItemElement);
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
