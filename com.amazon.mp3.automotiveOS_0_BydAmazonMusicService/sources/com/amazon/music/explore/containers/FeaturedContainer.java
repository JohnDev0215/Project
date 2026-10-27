package com.amazon.music.explore.containers;

import CoreInterface.v1_0.Method;
import ExternalActionInterface.v1_0.ArtistSpotlight;
import ExternalActionInterface.v1_0.PlayableContent;
import ExternalActionInterface.v1_0.TriggerExternalGenericPlaybackMethod;
import Touch.WidgetsInterface.v1_0.FeaturedHorizontalShovelerElement;
import Touch.WidgetsInterface.v1_0.HorizontalItemElement;
import Touch.WidgetsInterface.v1_0.WidgetElement;
import Touch.WidgetsTemplateInterface.v1_0.WidgetsTemplate;
import android.content.Context;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.explore.ExploreResources;
import com.amazon.music.explore.fragment.PlaybackFragment;
import com.amazon.music.explore.managers.ExploreLiveStreamManager;
import com.amazon.music.explore.metrics.SpotlightImageLatencyFlexEventEmitter;
import com.amazon.music.explore.picasso.BlurTransformation;
import com.amazon.music.explore.providers.CastingProvider;
import com.amazon.music.explore.widgets.models.FeaturedShovelerModel;
import com.amazon.music.explore.widgets.models.HorizontalItemModel;
import com.amazon.music.platform.providers.MetricsProvider;
import com.amazon.music.skyfire.ui.container.TemplateContainer;
import com.amazon.music.skyfire.ui.providers.ExternalActionProvider;
import com.amazon.music.skyfire.ui.providers.NavigationProvider;
import com.amazon.music.skyfire.ui.providers.SkyFireConfigurationProvider;
import com.amazon.music.skyfire.ui.providers.SkyFireProvider;
import com.amazon.music.skyfire.ui.skyfire.Contexts;
import com.amazon.music.skyfire.ui.skyfire.Methods;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.ShovelerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsJvmKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: FeaturedContainer.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0004\u0018\u0000 32\b\u0012\u0004\u0012\u00020\u00020\u0001:\u00013B=\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0002\u0010\u0011J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010 \u001a\u00020\u0002H\u0002J\u0010\u0010!\u001a\u00020\"2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0002J\u000e\u0010'\u001a\u00020$2\u0006\u0010\u001d\u001a\u00020\u001eJ\u0010\u0010(\u001a\u00020$2\u0006\u0010 \u001a\u00020\u0002H\u0016J\u0010\u0010)\u001a\u00020$2\u0006\u0010*\u001a\u00020+H\u0016J$\u0010,\u001a\u00020$2\u0006\u0010-\u001a\u00020.2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020.00H\u0016J\u0010\u00101\u001a\u00020$2\u0006\u00102\u001a\u00020.H\u0002R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u00064"}, d2 = {"Lcom/amazon/music/explore/containers/FeaturedContainer;", "Lcom/amazon/music/skyfire/ui/container/TemplateContainer;", "LTouch/WidgetsTemplateInterface/v1_0/WidgetsTemplate;", "context", "Landroid/content/Context;", "skyFireProvider", "Lcom/amazon/music/skyfire/ui/providers/SkyFireProvider;", "skyFireConfigurationProvider", "Lcom/amazon/music/skyfire/ui/providers/SkyFireConfigurationProvider;", "externalActionProvider", "Lcom/amazon/music/skyfire/ui/providers/ExternalActionProvider;", "metricsProvider", "Lcom/amazon/music/platform/providers/MetricsProvider;", "navigationProvider", "Lcom/amazon/music/skyfire/ui/providers/NavigationProvider;", "castingProvider", "Lcom/amazon/music/explore/providers/CastingProvider;", "(Landroid/content/Context;Lcom/amazon/music/skyfire/ui/providers/SkyFireProvider;Lcom/amazon/music/skyfire/ui/providers/SkyFireConfigurationProvider;Lcom/amazon/music/skyfire/ui/providers/ExternalActionProvider;Lcom/amazon/music/platform/providers/MetricsProvider;Lcom/amazon/music/skyfire/ui/providers/NavigationProvider;Lcom/amazon/music/explore/providers/CastingProvider;)V", "getCastingProvider", "()Lcom/amazon/music/explore/providers/CastingProvider;", "getMetricsProvider", "()Lcom/amazon/music/platform/providers/MetricsProvider;", "museModels", "", "Lcom/amazon/music/explore/widgets/models/FeaturedShovelerModel;", "getNavigationProvider", "()Lcom/amazon/music/skyfire/ui/providers/NavigationProvider;", "areModelsMerged", "", "pageGridViewModel", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "createMuseModels", "template", "getFeaturedShovelerIndex", "", "handleGenericPlaybackMethod", "", "spotlight", "LExternalActionInterface/v1_0/ArtistSpotlight;", "merge", "onBindTemplate", "onHandleMethod", "method", "LCoreInterface/v1_0/Method;", "onTriggerExternalAction", "action", "", "args", "", "preFetchImage", ContextMappingConstants.IMAGE, "Companion", "DMMExplore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FeaturedContainer extends TemplateContainer<WidgetsTemplate> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Logger logger;
    private final CastingProvider castingProvider;
    private final MetricsProvider metricsProvider;
    private List<? extends FeaturedShovelerModel> museModels;
    private final NavigationProvider navigationProvider;

    public final MetricsProvider getMetricsProvider() {
        return this.metricsProvider;
    }

    public final NavigationProvider getNavigationProvider() {
        return this.navigationProvider;
    }

    public final CastingProvider getCastingProvider() {
        return this.castingProvider;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FeaturedContainer(Context context, SkyFireProvider skyFireProvider, SkyFireConfigurationProvider skyFireConfigurationProvider, ExternalActionProvider externalActionProvider, MetricsProvider metricsProvider, NavigationProvider navigationProvider, CastingProvider castingProvider) {
        super(context, skyFireProvider, skyFireConfigurationProvider, externalActionProvider);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(skyFireProvider, "skyFireProvider");
        Intrinsics.checkNotNullParameter(skyFireConfigurationProvider, "skyFireConfigurationProvider");
        Intrinsics.checkNotNullParameter(externalActionProvider, "externalActionProvider");
        Intrinsics.checkNotNullParameter(metricsProvider, "metricsProvider");
        Intrinsics.checkNotNullParameter(navigationProvider, "navigationProvider");
        Intrinsics.checkNotNullParameter(castingProvider, "castingProvider");
        this.metricsProvider = metricsProvider;
        this.navigationProvider = navigationProvider;
        this.castingProvider = castingProvider;
    }

    @Override // com.amazon.music.skyfire.ui.container.TemplateContainer, com.amazon.music.skyfire.ui.skyfire.TemplateMethodListener
    public void onBindTemplate(WidgetsTemplate template) {
        Intrinsics.checkNotNullParameter(template, "template");
        this.museModels = createMuseModels(template);
        super.onBindTemplate(template);
    }

    @Override // com.amazon.music.skyfire.ui.container.TemplateContainer, com.amazon.music.skyfire.ui.skyfire.TemplateMethodListener
    public void onHandleMethod(Method method) {
        Intrinsics.checkNotNullParameter(method, "method");
        if (method instanceof TriggerExternalGenericPlaybackMethod) {
            List<PlayableContent> listContent = ((TriggerExternalGenericPlaybackMethod) method).content();
            PlayableContent playableContent = listContent == null ? null : (PlayableContent) CollectionsKt___CollectionsKt.firstOrNull((List) listContent);
            ArtistSpotlight artistSpotlight = playableContent instanceof ArtistSpotlight ? (ArtistSpotlight) playableContent : null;
            if (artistSpotlight == null) {
                return;
            }
            handleGenericPlaybackMethod(artistSpotlight);
            return;
        }
        super.onHandleMethod(method);
    }

    @Override // com.amazon.music.skyfire.ui.container.TemplateContainer
    public void onTriggerExternalAction(String action, Map<String, String> args) {
        Intrinsics.checkNotNullParameter(action, "action");
        Intrinsics.checkNotNullParameter(args, "args");
        if (Intrinsics.areEqual(action, Methods.setPlaybackViewLoadingImage)) {
            PlaybackFragment.setLoadingImage(args.get(Contexts.SetPlaybackViewLoadingImage.imageUrl));
        } else {
            super.onTriggerExternalAction(action, args);
        }
    }

    public final void merge(PageGridViewModel pageGridViewModel) {
        Intrinsics.checkNotNullParameter(pageGridViewModel, "pageGridViewModel");
        if (areModelsMerged(pageGridViewModel)) {
            return;
        }
        List<? extends FeaturedShovelerModel> list = this.museModels;
        FeaturedShovelerModel featuredShovelerModel = list == null ? null : (FeaturedShovelerModel) CollectionsKt___CollectionsKt.firstOrNull((List) list);
        if (featuredShovelerModel == null) {
            return;
        }
        ArrayList arrayList = new ArrayList(pageGridViewModel.getModels());
        int featuredShovelerIndex = getFeaturedShovelerIndex(pageGridViewModel);
        if (featuredShovelerIndex != -1) {
            Object obj = arrayList.get(featuredShovelerIndex);
            if (obj == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.models.ShovelerModel");
            }
            featuredShovelerModel.resetModels(((ShovelerModel) obj).getModels());
            List<BaseViewModel> childrenModels = featuredShovelerModel.getChildrenModels();
            Intrinsics.checkNotNullExpressionValue(childrenModels, "newShoveler\n                    .childrenModels");
            ArrayList arrayList2 = new ArrayList();
            for (Object obj2 : childrenModels) {
                if (obj2 instanceof HorizontalItemModel) {
                    arrayList2.add(obj2);
                }
            }
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                ((HorizontalItemModel) it.next()).setParentModelSize(featuredShovelerModel.getChildrenModels().size());
            }
            arrayList.set(featuredShovelerIndex, featuredShovelerModel);
        } else {
            int i = ExploreLiveStreamManager.areLiveStreamModelsMerged(pageGridViewModel) ? 2 : 1;
            featuredShovelerModel.resetModels(Collections.emptyList());
            arrayList.add(i, featuredShovelerModel);
        }
        pageGridViewModel.setModels(arrayList);
    }

    private final boolean areModelsMerged(PageGridViewModel pageGridViewModel) {
        List<String> tags;
        List<BaseViewModel> models = pageGridViewModel.getModels();
        if (!(models instanceof Collection) || !models.isEmpty()) {
            for (BaseViewModel baseViewModel : models) {
                FeaturedShovelerModel featuredShovelerModel = baseViewModel instanceof FeaturedShovelerModel ? (FeaturedShovelerModel) baseViewModel : null;
                if ((featuredShovelerModel == null || (tags = featuredShovelerModel.getTags()) == null || !tags.contains(ExploreResources.EXPLORE_FEATURED_SHOVELER)) ? false : true) {
                    return true;
                }
            }
        }
        return false;
    }

    private final List<FeaturedShovelerModel> createMuseModels(WidgetsTemplate template) {
        List listListOf = CollectionsKt__CollectionsJVMKt.listOf(ExploreResources.EXPLORE_FEATURED_SHOVELER);
        List<WidgetElement> listWidgets = template.widgets();
        Intrinsics.checkNotNullExpressionValue(listWidgets, "template.widgets()");
        List<FeaturedHorizontalShovelerElement> listFilterIsInstance = CollectionsKt___CollectionsJvmKt.filterIsInstance(listWidgets, FeaturedHorizontalShovelerElement.class);
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listFilterIsInstance, 10));
        for (FeaturedHorizontalShovelerElement featuredHorizontalShovelerElement : listFilterIsInstance) {
            List<HorizontalItemElement> listItems = featuredHorizontalShovelerElement.items();
            Intrinsics.checkNotNullExpressionValue(listItems, "w.items()");
            Iterator<T> it = listItems.iterator();
            while (it.hasNext()) {
                String strImage = ((HorizontalItemElement) it.next()).image();
                Intrinsics.checkNotNullExpressionValue(strImage, "item.image()");
                preFetchImage(strImage);
            }
            arrayList.add(new FeaturedShovelerModel(getListenerId(), getSkyFireApplication(), featuredHorizontalShovelerElement, listListOf));
        }
        return arrayList;
    }

    private final int getFeaturedShovelerIndex(PageGridViewModel pageGridViewModel) {
        int i = 0;
        for (BaseViewModel baseViewModel : pageGridViewModel.getModels()) {
            ShovelerModel shovelerModel = baseViewModel instanceof ShovelerModel ? (ShovelerModel) baseViewModel : null;
            if (shovelerModel != null && shovelerModel.getTileType() == 9) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private final void preFetchImage(String image) {
        final SpotlightImageLatencyFlexEventEmitter spotlightImageLatencyFlexEventEmitter = new SpotlightImageLatencyFlexEventEmitter(this.metricsProvider);
        Picasso.get().load(image).transform(new BlurTransformation(getContext(), 25.0f)).fetch(new Callback() { // from class: com.amazon.music.explore.containers.FeaturedContainer.preFetchImage.1
            @Override // com.squareup.picasso.Callback
            public void onError(Exception e) {
                Intrinsics.checkNotNullParameter(e, "e");
            }

            @Override // com.squareup.picasso.Callback
            public void onSuccess() {
                spotlightImageLatencyFlexEventEmitter.emitFlexEvent();
            }
        });
        spotlightImageLatencyFlexEventEmitter.startTiming();
    }

    private final void handleGenericPlaybackMethod(ArtistSpotlight spotlight) {
        if (this.castingProvider.isCasting()) {
            this.castingProvider.showCastingContentUnavailable();
            return;
        }
        NavigationProvider navigationProvider = this.navigationProvider;
        String strArtistAsin = spotlight.artistAsin();
        Intrinsics.checkNotNull(strArtistAsin);
        Intrinsics.checkNotNullExpressionValue(strArtistAsin, "spotlight.artistAsin()!!");
        navigationProvider.openArtistSpotlight(strArtistAsin);
    }

    /* JADX INFO: compiled from: FeaturedContainer.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/explore/containers/FeaturedContainer$Companion;", "", "()V", "logger", "Lorg/slf4j/Logger;", "getLogger", "()Lorg/slf4j/Logger;", "DMMExplore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Logger getLogger() {
            return FeaturedContainer.logger;
        }
    }

    static {
        Logger logger2 = LoggerFactory.getLogger(FeaturedContainer.class.getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger2, "getLogger(FeaturedContai…r::class.java.simpleName)");
        logger = logger2;
    }
}
