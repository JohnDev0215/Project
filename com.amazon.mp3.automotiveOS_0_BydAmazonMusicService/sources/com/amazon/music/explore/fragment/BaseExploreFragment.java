package com.amazon.music.explore.fragment;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Template;
import ExternalActionInterface.v1_0.TriggerExternalActionMethod;
import android.content.Context;
import android.os.Bundle;
import com.amazon.music.explore.providers.AnimationProvider;
import com.amazon.music.explore.providers.CastingProvider;
import com.amazon.music.explore.skyfire.SkyFireApplicationFactory;
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
import com.amazon.music.skyfire.ui.providers.PlaybackProvider;
import com.amazon.music.skyfire.ui.providers.SharingProvider;
import com.amazon.music.skyfire.ui.providers.StorageProvider;
import com.amazon.music.skyfire.ui.providers.StyleSheetProvider;
import com.amazon.music.skyfire.ui.providers.UpsellProvider;
import dagger.android.support.AndroidSupportInjection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class BaseExploreFragment<T extends Template> extends StandaloneContainerFragment<T> {
    public AnimationProvider animationProvider;
    public AuthenticationProvider authenticationProvider;
    public BuildInfoProvider buildInfoProvider;
    public CastingProvider castingProvider;
    public CustomerMetadataProvider customerMetadataProvider;
    public DeviceInfoProvider deviceInfoProvider;
    public EnvironmentProvider environmentProvider;
    public ExternalActionProvider externalActionProvider;
    private List<Method> initialMethods;
    public MetricsProvider metricsProvider;
    private String ownerId = OwnerIdGenerator.forTemplateList();
    public PlaybackProvider playbackProvider;
    public SharingProvider sharingProvider;
    private SkyFireApplication skyFireApplication;
    public StorageProvider storageProvider;
    public StyleSheetProvider styleSheetProvider;
    public UpsellProvider upsellProvider;

    @Override // com.amazon.music.explore.skyfire.containers.ContainerMethodOwner
    public String getOwnerId() {
        return this.ownerId;
    }

    @Override // com.amazon.music.explore.fragment.StandaloneContainerFragment
    public void onCreateTemplate(String str, T t) {
        this.skyFireApplication.dispatchMethods(str, t.onCreated());
    }

    @Override // com.amazon.music.explore.fragment.StandaloneContainerFragment
    public void onCreateAndBindTemplate(String str, T t) {
        onBindTemplate(str, t);
    }

    @Override // com.amazon.music.explore.fragment.StandaloneContainerFragment
    public void onBindTemplate(String str, T t) {
        this.skyFireApplication.dispatchMethods(str, t.onBound());
        this.skyFireApplication.dispatchMethods(str, t.onViewed());
    }

    @Override // com.amazon.music.explore.fragment.StandaloneContainerFragment
    public void onHandleTemplateMethod(String str, Method method) {
        if (method instanceof TriggerExternalActionMethod) {
            onHandleExternalAction((TriggerExternalActionMethod) method);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        AndroidSupportInjection.inject(this);
        SkyFireApplication skyFireApplication = SkyFireApplicationFactory.getSkyFireApplication(this, this.authenticationProvider, this.customerMetadataProvider, this.deviceInfoProvider, this.buildInfoProvider, this.storageProvider, this.environmentProvider);
        this.skyFireApplication = skyFireApplication;
        List<Method> list = this.initialMethods;
        if (list != null) {
            skyFireApplication.dispatchMethods(this.ownerId, list);
        }
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        onClose();
        super.onDestroy();
    }

    public MethodsDispatcher getMethodsDispatcher() {
        return this.skyFireApplication;
    }

    public void setInitialMethods(List<Method> list) {
        this.initialMethods = list;
    }

    protected void onHandleExternalAction(TriggerExternalActionMethod triggerExternalActionMethod) {
        Context context = getContext();
        if (context != null) {
            Map<String, String> mapContext = triggerExternalActionMethod.context();
            ExternalActionProvider externalActionProvider = this.externalActionProvider;
            String strName = triggerExternalActionMethod.name();
            if (mapContext == null) {
                mapContext = Collections.emptyMap();
            }
            externalActionProvider.handleAction(context, strName, mapContext, null);
        }
    }
}
