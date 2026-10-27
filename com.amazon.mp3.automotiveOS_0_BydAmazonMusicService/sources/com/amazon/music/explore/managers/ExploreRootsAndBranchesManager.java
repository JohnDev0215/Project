package com.amazon.music.explore.managers;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Template;
import ExternalActionInterface.v1_0.Track;
import ExternalActionInterface.v1_0.TriggerExternalActionMethod;
import ExternalActionInterface.v1_0.TriggerExternalPlaybackMethod;
import Touch.WidgetsInterface.v1_0.SpotlightCarouselElement;
import Touch.WidgetsInterface.v1_0.WidgetElement;
import Touch.WidgetsTemplateInterface.v1_0.WidgetsTemplate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.amazon.music.explore.events.SingleLiveEvent;
import com.amazon.music.explore.metrics.RootsAndBranchesPerformanceTracker;
import com.amazon.music.explore.skyfire.OwnerIdGenerator;
import com.amazon.music.explore.skyfire.SkyFireApplicationFactory;
import com.amazon.music.explore.skyfire.containers.ContainerManager;
import com.amazon.music.explore.widgets.models.ExploreModel;
import com.amazon.music.explore.widgets.models.SpotlightCarouselModel;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.CustomerMetadataProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.music.platform.providers.MetricsProvider;
import com.amazon.music.skyfire.SkyFireApplication;
import com.amazon.music.skyfire.ui.providers.EnvironmentProvider;
import com.amazon.music.skyfire.ui.providers.NavigationProvider;
import com.amazon.music.skyfire.ui.providers.PlaybackProvider;
import com.amazon.music.skyfire.ui.providers.StorageProvider;
import com.amazon.music.skyfire.ui.skyfire.Methods;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.ShovelerModel;
import com.amazon.music.views.library.models.VerticalTileModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class ExploreRootsAndBranchesManager extends ContainerManager {
    private final AuthenticationProvider authenticationProvider;
    private final BuildInfoProvider buildInfoProvider;
    private final CustomerMetadataProvider customerMetadataProvider;
    private final DeviceInfoProvider deviceInfoProvider;
    private final EnvironmentProvider environmentProvider;
    private final Fragment fragment;
    private final MetricsProvider metricsProvider;
    private final NavigationProvider navigationProvider;
    private final PlaybackProvider playbackProvider;
    private final SkyFireApplication skyFireApplication;
    private final StorageProvider storageProvider;
    private WidgetsTemplate template;
    private final RootsAndBranchesPerformanceTracker tracker;
    private final SingleLiveEvent<List<ExploreModel>> rootsAndBranchesModels = new SingleLiveEvent<>();
    private final String ownerId = OwnerIdGenerator.forTemplateContainer();
    private final List<Method> initialMethods = new ArrayList();

    public ExploreRootsAndBranchesManager(AuthenticationProvider authenticationProvider, CustomerMetadataProvider customerMetadataProvider, DeviceInfoProvider deviceInfoProvider, BuildInfoProvider buildInfoProvider, NavigationProvider navigationProvider, StorageProvider storageProvider, EnvironmentProvider environmentProvider, MetricsProvider metricsProvider, PlaybackProvider playbackProvider, LifecycleOwner lifecycleOwner, Fragment fragment) {
        this.authenticationProvider = authenticationProvider;
        this.customerMetadataProvider = customerMetadataProvider;
        this.deviceInfoProvider = deviceInfoProvider;
        this.buildInfoProvider = buildInfoProvider;
        this.navigationProvider = navigationProvider;
        this.storageProvider = storageProvider;
        this.environmentProvider = environmentProvider;
        this.metricsProvider = metricsProvider;
        this.playbackProvider = playbackProvider;
        this.fragment = fragment;
        this.skyFireApplication = SkyFireApplicationFactory.getSkyFireApplication(this, authenticationProvider, customerMetadataProvider, deviceInfoProvider, buildInfoProvider, storageProvider, environmentProvider);
        RootsAndBranchesPerformanceTracker rootsAndBranchesPerformanceTracker = new RootsAndBranchesPerformanceTracker(metricsProvider);
        this.tracker = rootsAndBranchesPerformanceTracker;
        rootsAndBranchesPerformanceTracker.startTiming();
        lifecycleOwner.getLifecycle().addObserver(new ContainerManagerLifecycleObserver());
    }

    @Override // com.amazon.music.explore.skyfire.containers.ContainerMethodOwner
    public String getOwnerId() {
        return this.ownerId;
    }

    @Override // com.amazon.music.explore.skyfire.containers.ContainerManager
    protected void createTemplate(String str, Template template) {
        this.template = (WidgetsTemplate) template;
        this.skyFireApplication.dispatchMethods(str, template.onCreated());
    }

    @Override // com.amazon.music.explore.skyfire.containers.ContainerManager
    protected void createAndBindTemplate(String str, Template template) {
        bindTemplate(str, template);
    }

    @Override // com.amazon.music.explore.skyfire.containers.ContainerManager
    protected void bindTemplate(String str, Template template) {
        WidgetsTemplate widgetsTemplate = (WidgetsTemplate) template;
        this.template = widgetsTemplate;
        createRootsAndBranchesModels(widgetsTemplate);
        this.skyFireApplication.dispatchMethods(str, template.onBound());
        this.skyFireApplication.dispatchMethods(str, template.onViewed());
    }

    @Override // com.amazon.music.explore.skyfire.containers.ContainerManager
    public void handleTemplateMethod(String str, Method method) {
        if (method instanceof TriggerExternalActionMethod) {
            handleExternalAction((TriggerExternalActionMethod) method);
        } else if (method instanceof TriggerExternalPlaybackMethod) {
            handleExternalPlayback((TriggerExternalPlaybackMethod) method);
        }
    }

    public SingleLiveEvent<List<ExploreModel>> getModels() {
        return this.rootsAndBranchesModels;
    }

    public ExploreRootsAndBranchesManager setInitialMethods(List<Method> list) {
        this.initialMethods.addAll(list);
        this.skyFireApplication.dispatchMethods(this.ownerId, this.initialMethods);
        return this;
    }

    public void mergeModels(PageGridViewModel pageGridViewModel) {
        if (areRootsAndBranchesModelsMerged(pageGridViewModel)) {
            return;
        }
        if (pageGridViewModel != null) {
            this.tracker.timeBrushResponse();
        }
        if (this.rootsAndBranchesModels.getValue() != null) {
            this.tracker.timeExploreResponse();
        }
        if (pageGridViewModel == null || this.rootsAndBranchesModels.getValue() == null) {
            return;
        }
        int artistsPosition = getArtistsPosition(pageGridViewModel);
        ArrayList arrayList = new ArrayList(pageGridViewModel.getModels());
        arrayList.addAll(artistsPosition, this.rootsAndBranchesModels.getValue());
        pageGridViewModel.setModels(arrayList);
    }

    private void createRootsAndBranchesModels(WidgetsTemplate widgetsTemplate) {
        ArrayList arrayList = new ArrayList();
        for (WidgetElement widgetElement : widgetsTemplate.widgets()) {
            if (widgetElement instanceof SpotlightCarouselElement) {
                arrayList.add(new SpotlightCarouselModel(this.ownerId, this.skyFireApplication, (SpotlightCarouselElement) widgetElement));
            }
        }
        this.rootsAndBranchesModels.setValue(arrayList);
    }

    private boolean areRootsAndBranchesModelsMerged(PageGridViewModel pageGridViewModel) {
        if (pageGridViewModel == null) {
            return false;
        }
        Iterator<BaseViewModel> it = pageGridViewModel.getModels().iterator();
        while (it.hasNext()) {
            if (it.next() instanceof SpotlightCarouselModel) {
                return true;
            }
        }
        return false;
    }

    private int getArtistsPosition(PageGridViewModel pageGridViewModel) {
        List<BaseViewModel> models = pageGridViewModel.getModels();
        int size = models.size();
        for (int i = 0; i < models.size(); i++) {
            BaseViewModel baseViewModel = models.get(i);
            if (baseViewModel instanceof ShovelerModel) {
                for (BaseViewModel baseViewModel2 : ((ShovelerModel) baseViewModel).getModels()) {
                    if ((baseViewModel2 instanceof VerticalTileModel) && (((VerticalTileModel) baseViewModel2).getMetadata() instanceof ArtistMetadata)) {
                        size = i;
                    }
                }
            }
        }
        return size;
    }

    private void handleExternalAction(TriggerExternalActionMethod triggerExternalActionMethod) {
        if (Methods.openArtistDetail.equals(triggerExternalActionMethod.name())) {
            this.navigationProvider.openArtistDetail(triggerExternalActionMethod.context().get("ASIN"));
        } else if (Methods.openPlaylistDetail.equals(triggerExternalActionMethod.name())) {
            this.navigationProvider.openPlaylistDetail(triggerExternalActionMethod.context().get("ASIN"), null);
        }
    }

    private void handleExternalPlayback(TriggerExternalPlaybackMethod triggerExternalPlaybackMethod) {
        List<Track> listTracks = triggerExternalPlaybackMethod.tracks();
        FragmentActivity activity = this.fragment.getActivity();
        if (listTracks == null || activity == null) {
            return;
        }
        this.playbackProvider.playTracks(activity, listTracks);
    }

    private class ContainerManagerLifecycleObserver implements LifecycleObserver {
        private ContainerManagerLifecycleObserver() {
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        public void closeManager() {
            ExploreRootsAndBranchesManager.this.onClose();
        }
    }
}
