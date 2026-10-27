package com.amazon.music.explore.managers;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Queue;
import CoreInterface.v1_0.Template;
import ExternalActionInterface.v1_0.TriggerExternalActionMethod;
import Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePagesClientInformation;
import Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePagesTemplate;
import Touch.WidgetsInterface.v1_0.IngressButtonElement;
import Touch.WidgetsInterface.v1_0.PillNavigatorWidgetItemElement;
import Touch.WidgetsInterface.v1_0.WidgetElement;
import Touch.WidgetsTemplateInterface.v1_0.WidgetsTemplate;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.amazon.music.explore.R;
import com.amazon.music.explore.events.SingleLiveEvent;
import com.amazon.music.explore.skyfire.ExploreClientInformationCache;
import com.amazon.music.explore.skyfire.OwnerIdGenerator;
import com.amazon.music.explore.skyfire.SkyFireApplicationFactory;
import com.amazon.music.explore.skyfire.SkyFireUtils;
import com.amazon.music.explore.skyfire.containers.ContainerManager;
import com.amazon.music.explore.views.swipeablePages.StageSwipeablePagesView;
import com.amazon.music.explore.views.swipeablePages.SwipeablePagesView;
import com.amazon.music.explore.widgets.views.ErrorView;
import com.amazon.music.explore.widgets.views.IngressButtonView;
import com.amazon.music.explore.widgets.views.PillNavigatorWidgetItemView;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.CustomerMetadataProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.music.skyfire.SkyFireApplication;
import com.amazon.music.skyfire.ui.providers.EnvironmentProvider;
import com.amazon.music.skyfire.ui.providers.NavigationProvider;
import com.amazon.music.skyfire.ui.providers.StorageProvider;
import com.amazon.music.skyfire.ui.skyfire.Contexts;
import com.amazon.music.skyfire.ui.skyfire.Methods;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class ExploreXrayManager extends ContainerManager {
    private final AuthenticationProvider authenticationProvider;
    private final BuildInfoProvider buildInfoProvider;
    private final Context context;
    private final CustomerMetadataProvider customerMetadataProvider;
    private final DeviceInfoProvider deviceInfoProvider;
    private final EnvironmentProvider environmentProvider;
    private final boolean isChartsV3Enabled;
    private final boolean isStage;
    private final NavigationProvider navigationProvider;
    private final SkyFireApplication skyFireApplication;
    private final StorageProvider storageProvider;
    private final SingleLiveEvent<IngressButtonView> ingressButton = new SingleLiveEvent<>();
    private final SingleLiveEvent<PillNavigatorWidgetItemView> pillButton = new SingleLiveEvent<>();
    private final SingleLiveEvent<View> view = new SingleLiveEvent<>();
    private final String ownerId = OwnerIdGenerator.forTemplateContainer();

    public ExploreXrayManager(Context context, AuthenticationProvider authenticationProvider, CustomerMetadataProvider customerMetadataProvider, DeviceInfoProvider deviceInfoProvider, BuildInfoProvider buildInfoProvider, NavigationProvider navigationProvider, StorageProvider storageProvider, EnvironmentProvider environmentProvider, LifecycleOwner lifecycleOwner, boolean z, boolean z2) {
        this.context = context;
        this.authenticationProvider = authenticationProvider;
        this.customerMetadataProvider = customerMetadataProvider;
        this.deviceInfoProvider = deviceInfoProvider;
        this.buildInfoProvider = buildInfoProvider;
        this.navigationProvider = navigationProvider;
        this.storageProvider = storageProvider;
        this.environmentProvider = environmentProvider;
        this.skyFireApplication = SkyFireApplicationFactory.getSkyFireApplication(this, authenticationProvider, customerMetadataProvider, deviceInfoProvider, buildInfoProvider, storageProvider, environmentProvider);
        lifecycleOwner.getLifecycle().addObserver(new ContainerManagerLifecycleObserver());
        this.isStage = z;
        this.isChartsV3Enabled = z2;
        ExploreClientInformationCache exploreClientInformationCache = ExploreClientInformationCache.INSTANCE;
        exploreClientInformationCache.isXrayStageEnabled = z;
        exploreClientInformationCache.isChartsV3Enabled = z2;
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
        if (template instanceof SwipeablePagesTemplate) {
            createSwipeablePagesView((SwipeablePagesTemplate) template);
        } else if (template instanceof WidgetsTemplate) {
            createButton((WidgetsTemplate) template);
        }
        this.skyFireApplication.dispatchMethods(str, template.onBound());
        this.skyFireApplication.dispatchMethods(str, template.onViewed());
    }

    @Override // com.amazon.music.explore.skyfire.containers.ContainerManager
    public void handleTemplateMethod(String str, Method method) {
        if (method instanceof TriggerExternalActionMethod) {
            handleExternalAction((TriggerExternalActionMethod) method);
        }
    }

    public SingleLiveEvent<IngressButtonView> getIngressButton() {
        return this.ingressButton;
    }

    public SingleLiveEvent<PillNavigatorWidgetItemView> getPillButton() {
        return this.pillButton;
    }

    public SingleLiveEvent<View> getView() {
        return this.view;
    }

    public void requestXrayButton(String str) {
        this.skyFireApplication.dispatchMethods(this.ownerId, Collections.singletonList(SkyFireUtils.getInvokeHttpSkillMethod(String.format("/showXrayButton/%s", str), Collections.singletonMap("assetType", "AUDIO"), null, null, Collections.singletonList(SwipeablePagesClientInformation.class.getCanonicalName()))));
    }

    public void requestXray(String str) {
        String str2 = String.format("/showXray/%s", str);
        ArrayList arrayList = new ArrayList();
        arrayList.add(createDisplayErrorScreenMethod());
        this.skyFireApplication.dispatchMethods(this.ownerId, Collections.singletonList(SkyFireUtils.getInvokeHttpSkillMethod(str2, Collections.singletonMap("assetType", "AUDIO"), arrayList, null, Collections.singletonList(SwipeablePagesClientInformation.class.getCanonicalName()))));
    }

    public StageSwipeablePagesView createStageSwipeablePagesView(SwipeablePagesTemplate swipeablePagesTemplate, int i, int i2, boolean z) {
        return new StageSwipeablePagesView(this.ownerId, this.context, this.skyFireApplication, swipeablePagesTemplate, i, i2, z);
    }

    public PillNavigatorWidgetItemView createStagePillNavigatorItemView(PillNavigatorWidgetItemElement pillNavigatorWidgetItemElement) {
        return new PillNavigatorWidgetItemView(this.ownerId, this.context, this.skyFireApplication, pillNavigatorWidgetItemElement);
    }

    private TriggerExternalActionMethod createDisplayErrorScreenMethod() {
        final Resources resources = this.context.getResources();
        return new TriggerExternalActionMethod() { // from class: com.amazon.music.explore.managers.ExploreXrayManager.1
            @Override // ExternalActionInterface.v1_0.TriggerExternalActionMethod
            public String name() {
                return Methods.displayErrorScreen;
            }

            @Override // ExternalActionInterface.v1_0.TriggerExternalActionMethod
            public Map<String, String> context() {
                return new HashMap<String, String>() { // from class: com.amazon.music.explore.managers.ExploreXrayManager.1.1
                    {
                        put(Contexts.DisplayErrorScreen.primaryText, resources.getString(R.string.explore_something_went_wrong));
                        put(Contexts.DisplayErrorScreen.secondaryText, resources.getString(R.string.explore_xray_is_down));
                        put(Contexts.DisplayErrorScreen.buttonText, resources.getString(R.string.explore_close));
                    }
                };
            }

            @Override // CoreInterface.v1_0.Method
            public Queue queue() {
                return SkyFireUtils.stHttpQueue();
            }
        };
    }

    private void createButton(WidgetsTemplate widgetsTemplate) {
        if (widgetsTemplate.widgets() != null) {
            if (widgetsTemplate.widgets().isEmpty()) {
                this.ingressButton.setValue(null);
                this.pillButton.setValue(null);
                return;
            }
            WidgetElement widgetElement = widgetsTemplate.widgets().get(0);
            if (widgetElement instanceof IngressButtonElement) {
                this.ingressButton.setValue(new IngressButtonView(this.ownerId, this.context, this.skyFireApplication, (IngressButtonElement) widgetElement));
                return;
            }
            if (widgetElement instanceof PillNavigatorWidgetItemElement) {
                this.pillButton.setValue(new PillNavigatorWidgetItemView(this.ownerId, this.context, this.skyFireApplication, (PillNavigatorWidgetItemElement) widgetElement));
            }
        }
    }

    private void createSwipeablePagesView(SwipeablePagesTemplate swipeablePagesTemplate) {
        if (this.isStage) {
            this.view.setValue(new StageSwipeablePagesView(this.ownerId, this.context, this.skyFireApplication, swipeablePagesTemplate));
        } else {
            this.view.setValue(new SwipeablePagesView(this.ownerId, this.context, this.skyFireApplication, swipeablePagesTemplate));
        }
    }

    private void handleExternalAction(TriggerExternalActionMethod triggerExternalActionMethod) {
        String strName = triggerExternalActionMethod.name();
        strName.hashCode();
        switch (strName) {
            case "OPEN_PLAYLIST_DETAIL":
                this.navigationProvider.openPlaylistDetail(triggerExternalActionMethod.context().get("ASIN"), null);
                break;
            case "OPEN_POPULAR_CONTENT":
                this.navigationProvider.openChartsPage(triggerExternalActionMethod.context().get(Contexts.OpenPopularContent.node), triggerExternalActionMethod.context().get(Contexts.OpenPopularContent.widgetId));
                break;
            case "OPEN_ALBUM_DETAIL":
                this.navigationProvider.openAlbumDetail(triggerExternalActionMethod.context().get("ASIN"), triggerExternalActionMethod.context().get("TRACK_ASIN"));
                break;
            case "OPEN_ARTIST_DETAIL":
                this.navigationProvider.openArtistDetail(triggerExternalActionMethod.context().get("ASIN"));
                break;
            case "OPEN_PODCAST_LINK":
                this.navigationProvider.openPodcastDetail(triggerExternalActionMethod.context().get("URL"));
                break;
            case "DISPLAY_ERROR_SCREEN":
                ErrorView errorView = new ErrorView(this.context);
                errorView.setPrimaryText(triggerExternalActionMethod.context().get(Contexts.DisplayErrorScreen.primaryText));
                errorView.setSecondaryText(triggerExternalActionMethod.context().get(Contexts.DisplayErrorScreen.secondaryText));
                errorView.setButtonText(triggerExternalActionMethod.context().get(Contexts.DisplayErrorScreen.buttonText));
                errorView.setButtonClickListener(new View.OnClickListener() { // from class: com.amazon.music.explore.managers.ExploreXrayManager$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.lambda$handleExternalAction$0(view);
                    }
                });
                this.view.setValue(errorView);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$handleExternalAction$0(View view) {
        ((Activity) this.context).onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.music.explore.skyfire.containers.ContainerManager
    public void onClose() {
        if (this.isStage) {
            SkyFireApplicationFactory.deregisterContainerMethods();
        }
        super.onClose();
    }

    private class ContainerManagerLifecycleObserver implements LifecycleObserver {
        private ContainerManagerLifecycleObserver() {
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        public void closeManager() {
            ExploreXrayManager.this.onClose();
        }
    }
}
