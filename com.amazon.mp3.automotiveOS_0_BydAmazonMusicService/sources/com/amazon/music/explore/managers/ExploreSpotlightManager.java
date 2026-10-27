package com.amazon.music.explore.managers;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Queue;
import CoreInterface.v1_0.Template;
import ExternalActionInterface.v1_0.ArtistSpotlight;
import ExternalActionInterface.v1_0.PlayableContent;
import ExternalActionInterface.v1_0.TriggerExternalActionMethod;
import ExternalActionInterface.v1_0.TriggerExternalGenericPlaybackMethod;
import Touch.WidgetsInterface.v1_0.FeaturedHorizontalShovelerElement;
import Touch.WidgetsInterface.v1_0.HorizontalItemElement;
import Touch.WidgetsInterface.v1_0.WidgetElement;
import Touch.WidgetsTemplateInterface.v1_0.WidgetsTemplate;
import android.content.Context;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.amazon.music.explore.ExploreResources;
import com.amazon.music.explore.events.SingleLiveEvent;
import com.amazon.music.explore.fragment.PlaybackFragment;
import com.amazon.music.explore.metrics.SpotlightImageLatencyFlexEventEmitter;
import com.amazon.music.explore.picasso.BlurTransformation;
import com.amazon.music.explore.providers.CastingProvider;
import com.amazon.music.explore.skyfire.OwnerIdGenerator;
import com.amazon.music.explore.skyfire.SkyFireApplicationFactory;
import com.amazon.music.explore.skyfire.SkyFireUtils;
import com.amazon.music.explore.skyfire.containers.ContainerManager;
import com.amazon.music.explore.widgets.models.FeaturedShovelerModel;
import com.amazon.music.explore.widgets.models.HorizontalItemModel;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.CustomerMetadataProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.music.platform.providers.MetricsProvider;
import com.amazon.music.skyfire.SkyFireApplication;
import com.amazon.music.skyfire.ui.providers.EnvironmentProvider;
import com.amazon.music.skyfire.ui.providers.NavigationProvider;
import com.amazon.music.skyfire.ui.providers.StorageProvider;
import com.amazon.music.skyfire.ui.skyfire.Contexts;
import com.amazon.music.skyfire.ui.skyfire.Methods;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.ShovelerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public class ExploreSpotlightManager extends ContainerManager {
    private final CastingProvider castingProvider;
    private final Context context;
    private final MetricsProvider metricsProvider;
    private final NavigationProvider navigationProvider;
    private final SkyFireApplication skyFireApplication;
    private final SingleLiveEvent<List<FeaturedShovelerModel>> spotlightModels = new SingleLiveEvent<>();
    private final SingleLiveEvent<Boolean> onModelsReady = new SingleLiveEvent<>();
    private final String ownerId = OwnerIdGenerator.forTemplateContainer();
    private Integer existingFeaturedShovelerPosition = null;
    private boolean areModelsReady = false;

    public ExploreSpotlightManager(Context context, AuthenticationProvider authenticationProvider, CustomerMetadataProvider customerMetadataProvider, DeviceInfoProvider deviceInfoProvider, BuildInfoProvider buildInfoProvider, NavigationProvider navigationProvider, StorageProvider storageProvider, EnvironmentProvider environmentProvider, CastingProvider castingProvider, MetricsProvider metricsProvider, LifecycleOwner lifecycleOwner) {
        this.context = context;
        this.navigationProvider = navigationProvider;
        this.castingProvider = castingProvider;
        this.metricsProvider = metricsProvider;
        this.skyFireApplication = SkyFireApplicationFactory.getSkyFireApplication(this, authenticationProvider, customerMetadataProvider, deviceInfoProvider, buildInfoProvider, storageProvider, environmentProvider);
        lifecycleOwner.getLifecycle().addObserver(new ContainerManagerLifecycleObserver());
    }

    @Override // com.amazon.music.explore.skyfire.containers.ContainerMethodOwner
    public String getOwnerId() {
        return this.ownerId;
    }

    @Override // com.amazon.music.explore.skyfire.containers.ContainerManager
    protected void createTemplate(String str, Template template) {
        this.skyFireApplication.dispatchMethods(str, template.onCreated());
    }

    @Override // com.amazon.music.explore.skyfire.containers.ContainerManager
    protected void createAndBindTemplate(String str, Template template) {
        bindTemplate(str, template);
    }

    @Override // com.amazon.music.explore.skyfire.containers.ContainerManager
    protected void bindTemplate(String str, Template template) {
        if (template instanceof WidgetsTemplate) {
            createSpotlightModels((WidgetsTemplate) template);
        }
        this.skyFireApplication.dispatchMethods(str, template.onBound());
        this.skyFireApplication.dispatchMethods(str, template.onViewed());
    }

    @Override // com.amazon.music.explore.skyfire.containers.ContainerManager
    public void handleTemplateMethod(String str, Method method) {
        if (method instanceof TriggerExternalActionMethod) {
            handleExternalAction((TriggerExternalActionMethod) method);
        } else if (method instanceof TriggerExternalGenericPlaybackMethod) {
            handleGenericPlaybackMethod((TriggerExternalGenericPlaybackMethod) method);
        }
    }

    public SingleLiveEvent<List<FeaturedShovelerModel>> getModels() {
        return this.spotlightModels;
    }

    public SingleLiveEvent<Boolean> onModelsReady() {
        return this.onModelsReady;
    }

    public boolean areModelsReady() {
        return this.areModelsReady;
    }

    public void requestSpotlight(String str) {
        this.skyFireApplication.dispatchMethods(this.ownerId, Collections.singletonList(SkyFireUtils.getInvokeHttpSkillMethod(String.format("/showArtistSpotlightWidget/%s", str), (List<Method>) Collections.singletonList(new TriggerExternalActionMethod() { // from class: com.amazon.music.explore.managers.ExploreSpotlightManager.1
            @Override // ExternalActionInterface.v1_0.TriggerExternalActionMethod
            public Map<String, String> context() {
                return null;
            }

            @Override // ExternalActionInterface.v1_0.TriggerExternalActionMethod
            public String name() {
                return Methods.modelsAreReady;
            }

            @Override // CoreInterface.v1_0.Method
            public Queue queue() {
                return SkyFireUtils.mtHttpQueue();
            }
        }))));
    }

    public void mergeModels(PageGridViewModel pageGridViewModel) {
        if (areSpotlightsMerged(pageGridViewModel) || pageGridViewModel == null || this.spotlightModels.getValue() == null) {
            return;
        }
        ArrayList arrayList = new ArrayList(pageGridViewModel.getModels());
        FeaturedShovelerModel featuredShovelerModel = this.spotlightModels.getValue().get(0);
        Integer existingFeaturedShovelerPosition = getExistingFeaturedShovelerPosition(pageGridViewModel);
        this.existingFeaturedShovelerPosition = existingFeaturedShovelerPosition;
        if (existingFeaturedShovelerPosition != null) {
            featuredShovelerModel.resetModels(((ShovelerModel) arrayList.get(existingFeaturedShovelerPosition.intValue())).getModels());
            for (BaseViewModel baseViewModel : featuredShovelerModel.getChildrenModels()) {
                if (baseViewModel instanceof HorizontalItemModel) {
                    ((HorizontalItemModel) baseViewModel).setParentModelSize(featuredShovelerModel.getChildrenModels().size());
                }
            }
            arrayList.set(this.existingFeaturedShovelerPosition.intValue(), featuredShovelerModel);
        } else {
            int i = ExploreLiveStreamManager.areLiveStreamModelsMerged(pageGridViewModel) ? 2 : 1;
            featuredShovelerModel.resetModels(Collections.emptyList());
            arrayList.add(i, featuredShovelerModel);
        }
        pageGridViewModel.setModels(arrayList);
    }

    public Integer getExistingFeaturedShovelerPosition() {
        return this.existingFeaturedShovelerPosition;
    }

    private Integer getExistingFeaturedShovelerPosition(PageGridViewModel pageGridViewModel) {
        List<BaseViewModel> models = pageGridViewModel.getModels();
        for (int i = 0; i < models.size(); i++) {
            BaseViewModel baseViewModel = models.get(i);
            if ((baseViewModel instanceof ShovelerModel) && ((ShovelerModel) baseViewModel).getTileType() == 9) {
                return Integer.valueOf(i);
            }
        }
        return null;
    }

    private void createSpotlightModels(WidgetsTemplate widgetsTemplate) {
        ArrayList arrayList = new ArrayList();
        for (WidgetElement widgetElement : widgetsTemplate.widgets()) {
            if (widgetElement instanceof FeaturedHorizontalShovelerElement) {
                FeaturedHorizontalShovelerElement featuredHorizontalShovelerElement = (FeaturedHorizontalShovelerElement) widgetElement;
                arrayList.add(new FeaturedShovelerModel(this.ownerId, this.skyFireApplication, featuredHorizontalShovelerElement, Collections.singletonList(ExploreResources.EXPLORE_FEATURED_SHOVELER)));
                Iterator<HorizontalItemElement> it = featuredHorizontalShovelerElement.items().iterator();
                while (it.hasNext()) {
                    preFetchImage(it.next().image());
                }
            }
        }
        this.spotlightModels.setValue(arrayList);
    }

    private boolean areSpotlightsMerged(PageGridViewModel pageGridViewModel) {
        List<String> tags;
        if (pageGridViewModel == null) {
            return false;
        }
        for (BaseViewModel baseViewModel : pageGridViewModel.getModels()) {
            if ((baseViewModel instanceof FeaturedShovelerModel) && (tags = ((FeaturedShovelerModel) baseViewModel).getTags()) != null && tags.contains(ExploreResources.EXPLORE_FEATURED_SHOVELER)) {
                return true;
            }
        }
        return false;
    }

    private void handleExternalAction(TriggerExternalActionMethod triggerExternalActionMethod) {
        if (Methods.modelsAreReady.equals(triggerExternalActionMethod.name())) {
            this.areModelsReady = true;
            this.onModelsReady.setValue(Boolean.TRUE);
        } else if (Methods.setPlaybackViewLoadingImage.equals(triggerExternalActionMethod.name())) {
            PlaybackFragment.setLoadingImage(triggerExternalActionMethod.context().get(Contexts.SetPlaybackViewLoadingImage.imageUrl));
        }
    }

    private void handleGenericPlaybackMethod(TriggerExternalGenericPlaybackMethod triggerExternalGenericPlaybackMethod) {
        if (triggerExternalGenericPlaybackMethod.content() == null || triggerExternalGenericPlaybackMethod.content().isEmpty()) {
            return;
        }
        PlayableContent playableContent = triggerExternalGenericPlaybackMethod.content().get(0);
        if (playableContent instanceof ArtistSpotlight) {
            if (this.castingProvider.isCasting()) {
                this.castingProvider.showCastingContentUnavailable();
            } else {
                this.navigationProvider.openArtistSpotlight(((ArtistSpotlight) playableContent).artistAsin());
            }
        }
    }

    private void preFetchImage(String str) {
        final SpotlightImageLatencyFlexEventEmitter spotlightImageLatencyFlexEventEmitter = new SpotlightImageLatencyFlexEventEmitter(this.metricsProvider);
        Picasso.get().load(str).transform(new BlurTransformation(this.context, 25.0f)).fetch(new Callback() { // from class: com.amazon.music.explore.managers.ExploreSpotlightManager.2
            @Override // com.squareup.picasso.Callback
            public void onError(Exception exc) {
            }

            @Override // com.squareup.picasso.Callback
            public void onSuccess() {
                spotlightImageLatencyFlexEventEmitter.emitFlexEvent();
            }
        });
        spotlightImageLatencyFlexEventEmitter.startTiming();
    }

    private class ContainerManagerLifecycleObserver implements LifecycleObserver {
        private ContainerManagerLifecycleObserver() {
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        public void closeManager() {
            ExploreSpotlightManager.this.onClose();
        }
    }
}
