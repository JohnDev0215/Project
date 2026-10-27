package com.amazon.music.explore.managers;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Template;
import ExternalActionInterface.v1_0.TriggerExternalActionMethod;
import Touch.WidgetsInterface.v1_0.LiveStreamBarkerElement;
import Touch.WidgetsInterface.v1_0.WidgetElement;
import Touch.WidgetsTemplateInterface.v1_0.WidgetsTemplate;
import android.content.Context;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.amazon.music.explore.ExploreFactory;
import com.amazon.music.explore.events.SingleLiveEvent;
import com.amazon.music.explore.skyfire.OwnerIdGenerator;
import com.amazon.music.explore.skyfire.SkyFireApplicationFactory;
import com.amazon.music.explore.skyfire.SkyFireUtils;
import com.amazon.music.explore.skyfire.containers.ContainerManager;
import com.amazon.music.explore.widgets.models.ExploreLiveStreamBarkerModel;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.CustomerMetadataProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.music.platform.providers.MetricsProvider;
import com.amazon.music.skyfire.SkyFireApplication;
import com.amazon.music.skyfire.ui.providers.EnvironmentProvider;
import com.amazon.music.skyfire.ui.providers.NavigationProvider;
import com.amazon.music.skyfire.ui.providers.StorageProvider;
import com.amazon.music.skyfire.ui.skyfire.Methods;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.SectionGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class ExploreLiveStreamManager extends ContainerManager {
    private final AuthenticationProvider authenticationProvider;
    private final BuildInfoProvider buildInfoProvider;
    private final Context context;
    private final CustomerMetadataProvider customerMetadataProvider;
    private final DeviceInfoProvider deviceInfoProvider;
    private final EnvironmentProvider environmentProvider;
    private final MetricsProvider metricsProvider;
    private final NavigationProvider navigationProvider;
    private final SkyFireApplication skyFireApplication;
    private final StorageProvider storageProvider;
    private WidgetsTemplate template;
    private final SingleLiveEvent<SectionGridViewModel> sectionGridModel = new SingleLiveEvent<>();
    private final String ownerId = OwnerIdGenerator.forTemplateContainer();
    private final List<Method> initialMethods = new ArrayList();
    private boolean initialLiveStreamRequest = true;

    public ExploreLiveStreamManager(Context context, AuthenticationProvider authenticationProvider, CustomerMetadataProvider customerMetadataProvider, DeviceInfoProvider deviceInfoProvider, BuildInfoProvider buildInfoProvider, NavigationProvider navigationProvider, StorageProvider storageProvider, EnvironmentProvider environmentProvider, MetricsProvider metricsProvider, LifecycleOwner lifecycleOwner) {
        this.context = context;
        this.authenticationProvider = authenticationProvider;
        this.customerMetadataProvider = customerMetadataProvider;
        this.deviceInfoProvider = deviceInfoProvider;
        this.buildInfoProvider = buildInfoProvider;
        this.navigationProvider = navigationProvider;
        this.storageProvider = storageProvider;
        this.environmentProvider = environmentProvider;
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
        createSectionGridModel(widgetsTemplate);
        this.skyFireApplication.dispatchMethods(str, template.onBound());
        this.skyFireApplication.dispatchMethods(str, template.onViewed());
    }

    @Override // com.amazon.music.explore.skyfire.containers.ContainerManager
    public void handleTemplateMethod(String str, Method method) {
        if (method instanceof TriggerExternalActionMethod) {
            handleExternalAction((TriggerExternalActionMethod) method);
        }
    }

    public SingleLiveEvent<SectionGridViewModel> getModels() {
        if (this.initialLiveStreamRequest) {
            this.initialLiveStreamRequest = false;
        }
        return this.sectionGridModel;
    }

    public boolean isInitialRequest() {
        return this.initialLiveStreamRequest;
    }

    public void requestArtistLiveStream(String str) {
        String str2 = String.format("/showArtistLiveStream/%s", str);
        ArrayList arrayList = new ArrayList();
        this.initialMethods.clear();
        arrayList.add(SkyFireUtils.getInvokeHttpSkillMethod(str2));
        setInitialMethods(arrayList);
    }

    public ExploreLiveStreamManager setInitialMethods(List<Method> list) {
        this.initialMethods.addAll(list);
        this.skyFireApplication.dispatchMethods(this.ownerId, this.initialMethods);
        return this;
    }

    public void mergeModels(PageGridViewModel pageGridViewModel) {
        if (areLiveStreamModelsMerged(pageGridViewModel) || pageGridViewModel == null || this.sectionGridModel.getValue() == null) {
            return;
        }
        ArrayList arrayList = new ArrayList(pageGridViewModel.getModels());
        arrayList.add(1, this.sectionGridModel.getValue());
        pageGridViewModel.setModels(arrayList);
    }

    public static boolean areLiveStreamModelsMerged(PageGridViewModel pageGridViewModel) {
        if (pageGridViewModel == null) {
            return false;
        }
        for (BaseViewModel baseViewModel : pageGridViewModel.getModels()) {
            if (baseViewModel instanceof SectionGridViewModel) {
                Iterator<BaseViewModel> it = ((SectionGridViewModel) baseViewModel).getModels().iterator();
                while (it.hasNext()) {
                    if (it.next() instanceof ExploreLiveStreamBarkerModel) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void createSectionGridModel(WidgetsTemplate widgetsTemplate) {
        ArrayList arrayList = new ArrayList();
        for (WidgetElement widgetElement : widgetsTemplate.widgets()) {
            if (widgetElement instanceof LiveStreamBarkerElement) {
                arrayList.add(new ExploreLiveStreamBarkerModel(this.ownerId, this.skyFireApplication, (LiveStreamBarkerElement) widgetElement));
            }
        }
        this.sectionGridModel.setValue(new SectionGridViewModel(null, arrayList, 4, null, null, null, null, null, null));
    }

    private void handleExternalAction(TriggerExternalActionMethod triggerExternalActionMethod) {
        if (Methods.openLiveStream.equals(triggerExternalActionMethod.name())) {
            ExploreFactory.openLiveStreamPage(this.context, triggerExternalActionMethod.context().get("LIVE_STREAM_ID"));
        }
    }

    private class ContainerManagerLifecycleObserver implements LifecycleObserver {
        private ContainerManagerLifecycleObserver() {
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        public void closeManager() {
            ExploreLiveStreamManager.this.onClose();
        }
    }
}
