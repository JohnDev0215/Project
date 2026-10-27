package com.amazon.music.explore.activity;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Template;
import ExternalActionInterface.v1_0.PlayableContent;
import ExternalActionInterface.v1_0.Track;
import ExternalActionInterface.v1_0.TriggerExternalActionMethod;
import ExternalActionInterface.v1_0.TriggerExternalGenericPlaybackMethod;
import ExternalActionInterface.v1_0.TriggerExternalPlaybackMethod;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.amazon.music.explore.Mappers;
import com.amazon.music.explore.picasso.VideoRequestHandler;
import com.amazon.music.explore.providers.ExplicitFilterProvider;
import com.amazon.music.explore.providers.WakeWordProvider;
import com.amazon.music.explore.skyfire.SkyFireApplicationFactory;
import com.amazon.music.explore.skyfire.containers.StandaloneContainerActivity;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.CustomerMetadataProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.music.platform.providers.MetricsProvider;
import com.amazon.music.skyfire.SkyFireApplication;
import com.amazon.music.skyfire.core.template.OwnerIdGenerator;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.skyfire.ui.providers.EnvironmentProvider;
import com.amazon.music.skyfire.ui.providers.ExternalActionProvider;
import com.amazon.music.skyfire.ui.providers.NavigationProvider;
import com.amazon.music.skyfire.ui.providers.PlaybackProvider;
import com.amazon.music.skyfire.ui.providers.SharingProvider;
import com.amazon.music.skyfire.ui.providers.StorageProvider;
import com.amazon.music.skyfire.ui.providers.UserSubscriptionProvider;
import com.google.android.gms.common.util.CollectionUtils;
import com.squareup.picasso.Picasso;
import dagger.android.AndroidInjection;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public abstract class BaseExploreActivity<T extends Template> extends StandaloneContainerActivity<T> {
    public static String METHODS_TO_INVOKE = "METHODS_TO_INVOKE";
    public AuthenticationProvider authenticationProvider;
    public BuildInfoProvider buildInfoProvider;
    public CustomerMetadataProvider customerMetadataProvider;
    public DeviceInfoProvider deviceInfoProvider;
    public EnvironmentProvider environmentProvider;
    public ExplicitFilterProvider explicitFilterProvider;
    public ExternalActionProvider externalActionProvider;
    protected List<Method> initialMethods;
    public MetricsProvider metricsProvider;
    public NavigationProvider navigationProvider;
    public PlaybackProvider playbackProvider;
    public SharingProvider sharingProvider;
    protected SkyFireApplication skyFireApplication;
    public StorageProvider storageProvider;
    protected T template;
    public UserSubscriptionProvider userSubscriptionProvider;
    public WakeWordProvider wakeWordProvider;
    private final Logger logger = LoggerFactory.getLogger(LiveStreamActivity.class.getSimpleName());
    protected String ownerId = OwnerIdGenerator.forTemplateList();

    protected boolean shouldRunInitialMethodsOnResume() {
        return true;
    }

    @Override // com.amazon.music.explore.skyfire.containers.ContainerMethodOwner
    public String getOwnerId() {
        return this.ownerId;
    }

    @Override // com.amazon.music.explore.skyfire.containers.StandaloneContainerActivity
    public void createTemplate(String str, T t) {
        this.template = t;
        this.skyFireApplication.dispatchMethods(str, t.onCreated());
    }

    @Override // com.amazon.music.explore.skyfire.containers.StandaloneContainerActivity
    public void createAndBindTemplate(String str, T t) {
        this.template = t;
        this.skyFireApplication.dispatchMethods(str, t.onBound());
        this.skyFireApplication.dispatchMethods(str, t.onViewed());
    }

    @Override // com.amazon.music.explore.skyfire.containers.StandaloneContainerActivity
    public void bindTemplate(String str, T t) {
        this.template = t;
        this.skyFireApplication.dispatchMethods(str, t.onBound());
        this.skyFireApplication.dispatchMethods(str, t.onViewed());
    }

    @Override // com.amazon.music.explore.skyfire.containers.StandaloneContainerActivity
    public void handleTemplateMethod(String str, Method method) {
        if (method instanceof TriggerExternalActionMethod) {
            handleExternalAction((TriggerExternalActionMethod) method);
        } else if (method instanceof TriggerExternalPlaybackMethod) {
            handleExternalPlayback((TriggerExternalPlaybackMethod) method);
        } else if (method instanceof TriggerExternalGenericPlaybackMethod) {
            handleGenericPlaybackMethod((TriggerExternalGenericPlaybackMethod) method);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        String string;
        AndroidInjection.inject(this);
        this.skyFireApplication = SkyFireApplicationFactory.getSkyFireApplication(this, this.authenticationProvider, this.customerMetadataProvider, this.deviceInfoProvider, this.buildInfoProvider, this.storageProvider, this.environmentProvider);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null && (string = intent.getExtras().getString(METHODS_TO_INVOKE)) != null) {
            try {
                this.initialMethods = (List) Mappers.mapper().readValue(string, Mappers.mapper().getTypeFactory().constructCollectionType(List.class, Method.class));
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        initPicasso();
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        if (shouldRunInitialMethodsOnResume()) {
            runInitialMethods();
        }
        super.onResume();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        onClose();
        super.onDestroy();
    }

    public MethodsDispatcher getMethodsDispatcher() {
        return this.skyFireApplication;
    }

    public T getTemplate() {
        return this.template;
    }

    protected void runInitialMethods() {
        List<Method> list = this.initialMethods;
        if (list != null) {
            this.skyFireApplication.dispatchMethods(this.ownerId, list);
        }
    }

    private void initPicasso() {
        try {
            Picasso.setSingletonInstance(new Picasso.Builder(getApplicationContext()).addRequestHandler(new VideoRequestHandler()).build());
        } catch (IllegalStateException unused) {
        }
    }

    protected void handleExternalAction(TriggerExternalActionMethod triggerExternalActionMethod) {
        Map<String, String> mapContext = triggerExternalActionMethod.context();
        ExternalActionProvider externalActionProvider = this.externalActionProvider;
        String strName = triggerExternalActionMethod.name();
        if (mapContext == null) {
            mapContext = Collections.emptyMap();
        }
        externalActionProvider.handleAction(this, strName, mapContext, null);
    }

    protected void handleExternalPlayback(TriggerExternalPlaybackMethod triggerExternalPlaybackMethod) {
        List<Track> listTracks = triggerExternalPlaybackMethod.tracks();
        if (listTracks != null) {
            this.playbackProvider.playTracks(this, listTracks);
        }
    }

    protected void handleGenericPlaybackMethod(TriggerExternalGenericPlaybackMethod triggerExternalGenericPlaybackMethod) {
        if (CollectionUtils.isEmpty(triggerExternalGenericPlaybackMethod.content())) {
            return;
        }
        List<PlayableContent> listContent = triggerExternalGenericPlaybackMethod.content();
        Objects.requireNonNull(listContent);
        this.playbackProvider.play(listContent.get(0), this);
    }
}
