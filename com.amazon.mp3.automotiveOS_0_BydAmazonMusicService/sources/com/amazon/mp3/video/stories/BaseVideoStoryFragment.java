package com.amazon.mp3.video.stories;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Template;
import ExternalActionInterface.v1_0.TriggerExternalActionMethod;
import android.content.Context;
import android.os.Bundle;
import com.amazon.music.explore.fragment.StandaloneContainerFragment;
import com.amazon.music.explore.providers.AnimationProvider;
import com.amazon.music.explore.providers.CastingProvider;
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
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseVideoStoryFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000º\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003B\t¢\u0006\u0006\b\u0083\u0001\u0010\u0084\u0001J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00028\u0000H\u0014¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00028\u0000H\u0014¢\u0006\u0004\b\u000b\u0010\nJ\u001f\u0010\f\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00028\u0000H\u0014¢\u0006\u0004\b\f\u0010\nJ\u001c\u0010\u000f\u001a\u00020\b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0014J\u0012\u0010\u0012\u001a\u00020\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010\u0013\u001a\u00020\bH\u0016J\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\u0014J\u0016\u0010\u0018\u001a\u00020\b2\u000e\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u0016J\u0010\u0010\u001a\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u0019H\u0016R\"\u0010\u001c\u001a\u00020\u001b8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\"\u0010#\u001a\u00020\"8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\"\u0010*\u001a\u00020)8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\"\u00101\u001a\u0002008\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b1\u00102\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\"\u00108\u001a\u0002078\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b8\u00109\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\"\u0010?\u001a\u00020>8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b?\u0010@\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR\"\u0010F\u001a\u00020E8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\bF\u0010G\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR\"\u0010M\u001a\u00020L8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\bM\u0010N\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010RR\"\u0010T\u001a\u00020S8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\bT\u0010U\u001a\u0004\bV\u0010W\"\u0004\bX\u0010YR\"\u0010[\u001a\u00020Z8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b[\u0010\\\u001a\u0004\b]\u0010^\"\u0004\b_\u0010`R\"\u0010b\u001a\u00020a8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\bb\u0010c\u001a\u0004\bd\u0010e\"\u0004\bf\u0010gR\"\u0010i\u001a\u00020h8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\bi\u0010j\u001a\u0004\bk\u0010l\"\u0004\bm\u0010nR\"\u0010p\u001a\u00020o8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\bp\u0010q\u001a\u0004\br\u0010s\"\u0004\bt\u0010uR\"\u0010w\u001a\u00020v8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\bw\u0010x\u001a\u0004\by\u0010z\"\u0004\b{\u0010|R\u0016\u0010~\u001a\u00020}8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b~\u0010\u007fR!\u0010\u0080\u0001\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u00168\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0080\u0001\u0010\u0081\u0001R\u0017\u0010\u0006\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u0006\u0010\u0082\u0001¨\u0006\u0085\u0001"}, d2 = {"Lcom/amazon/mp3/video/stories/BaseVideoStoryFragment;", "LCoreInterface/v1_0/Template;", "T", "Lcom/amazon/music/explore/fragment/StandaloneContainerFragment;", "", "getOwnerId", "ownerId", "template", "", "onCreateTemplate", "(Ljava/lang/String;LCoreInterface/v1_0/Template;)V", "onCreateAndBindTemplate", "onBindTemplate", "LCoreInterface/v1_0/Method;", "method", "onHandleTemplateMethod", "Landroid/os/Bundle;", "savedInstanceState", "onCreate", "onDestroy", "Lcom/amazon/music/skyfire/platform/MethodsDispatcher;", "getMethodsDispatcher", "", "methods", "setInitialMethods", "LExternalActionInterface/v1_0/TriggerExternalActionMethod;", "onHandleExternalAction", "Lcom/amazon/music/platform/providers/AuthenticationProvider;", "authenticationProvider", "Lcom/amazon/music/platform/providers/AuthenticationProvider;", "getAuthenticationProvider", "()Lcom/amazon/music/platform/providers/AuthenticationProvider;", "setAuthenticationProvider", "(Lcom/amazon/music/platform/providers/AuthenticationProvider;)V", "Lcom/amazon/music/platform/providers/CustomerMetadataProvider;", "customerMetadataProvider", "Lcom/amazon/music/platform/providers/CustomerMetadataProvider;", "getCustomerMetadataProvider", "()Lcom/amazon/music/platform/providers/CustomerMetadataProvider;", "setCustomerMetadataProvider", "(Lcom/amazon/music/platform/providers/CustomerMetadataProvider;)V", "Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "deviceInfoProvider", "Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "getDeviceInfoProvider", "()Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "setDeviceInfoProvider", "(Lcom/amazon/music/platform/providers/DeviceInfoProvider;)V", "Lcom/amazon/music/platform/providers/BuildInfoProvider;", "buildInfoProvider", "Lcom/amazon/music/platform/providers/BuildInfoProvider;", "getBuildInfoProvider", "()Lcom/amazon/music/platform/providers/BuildInfoProvider;", "setBuildInfoProvider", "(Lcom/amazon/music/platform/providers/BuildInfoProvider;)V", "Lcom/amazon/music/skyfire/ui/providers/ExternalActionProvider;", "externalActionProvider", "Lcom/amazon/music/skyfire/ui/providers/ExternalActionProvider;", "getExternalActionProvider", "()Lcom/amazon/music/skyfire/ui/providers/ExternalActionProvider;", "setExternalActionProvider", "(Lcom/amazon/music/skyfire/ui/providers/ExternalActionProvider;)V", "Lcom/amazon/music/skyfire/ui/providers/StorageProvider;", "storageProvider", "Lcom/amazon/music/skyfire/ui/providers/StorageProvider;", "getStorageProvider", "()Lcom/amazon/music/skyfire/ui/providers/StorageProvider;", "setStorageProvider", "(Lcom/amazon/music/skyfire/ui/providers/StorageProvider;)V", "Lcom/amazon/music/skyfire/ui/providers/EnvironmentProvider;", "environmentProvider", "Lcom/amazon/music/skyfire/ui/providers/EnvironmentProvider;", "getEnvironmentProvider", "()Lcom/amazon/music/skyfire/ui/providers/EnvironmentProvider;", "setEnvironmentProvider", "(Lcom/amazon/music/skyfire/ui/providers/EnvironmentProvider;)V", "Lcom/amazon/music/skyfire/ui/providers/PlaybackProvider;", "playbackProvider", "Lcom/amazon/music/skyfire/ui/providers/PlaybackProvider;", "getPlaybackProvider", "()Lcom/amazon/music/skyfire/ui/providers/PlaybackProvider;", "setPlaybackProvider", "(Lcom/amazon/music/skyfire/ui/providers/PlaybackProvider;)V", "Lcom/amazon/music/skyfire/ui/providers/UpsellProvider;", "upsellProvider", "Lcom/amazon/music/skyfire/ui/providers/UpsellProvider;", "getUpsellProvider", "()Lcom/amazon/music/skyfire/ui/providers/UpsellProvider;", "setUpsellProvider", "(Lcom/amazon/music/skyfire/ui/providers/UpsellProvider;)V", "Lcom/amazon/music/explore/providers/CastingProvider;", "castingProvider", "Lcom/amazon/music/explore/providers/CastingProvider;", "getCastingProvider", "()Lcom/amazon/music/explore/providers/CastingProvider;", "setCastingProvider", "(Lcom/amazon/music/explore/providers/CastingProvider;)V", "Lcom/amazon/music/explore/providers/AnimationProvider;", "animationProvider", "Lcom/amazon/music/explore/providers/AnimationProvider;", "getAnimationProvider", "()Lcom/amazon/music/explore/providers/AnimationProvider;", "setAnimationProvider", "(Lcom/amazon/music/explore/providers/AnimationProvider;)V", "Lcom/amazon/music/skyfire/ui/providers/StyleSheetProvider;", "styleSheetProvider", "Lcom/amazon/music/skyfire/ui/providers/StyleSheetProvider;", "getStyleSheetProvider", "()Lcom/amazon/music/skyfire/ui/providers/StyleSheetProvider;", "setStyleSheetProvider", "(Lcom/amazon/music/skyfire/ui/providers/StyleSheetProvider;)V", "Lcom/amazon/music/platform/providers/MetricsProvider;", "metricsProvider", "Lcom/amazon/music/platform/providers/MetricsProvider;", "getMetricsProvider", "()Lcom/amazon/music/platform/providers/MetricsProvider;", "setMetricsProvider", "(Lcom/amazon/music/platform/providers/MetricsProvider;)V", "Lcom/amazon/music/skyfire/ui/providers/SharingProvider;", "sharingProvider", "Lcom/amazon/music/skyfire/ui/providers/SharingProvider;", "getSharingProvider", "()Lcom/amazon/music/skyfire/ui/providers/SharingProvider;", "setSharingProvider", "(Lcom/amazon/music/skyfire/ui/providers/SharingProvider;)V", "Lcom/amazon/music/skyfire/SkyFireApplication;", "skyFireApplication", "Lcom/amazon/music/skyfire/SkyFireApplication;", "initialMethods", "Ljava/util/List;", "Ljava/lang/String;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class BaseVideoStoryFragment<T extends Template> extends StandaloneContainerFragment<T> {
    public AnimationProvider animationProvider;
    public AuthenticationProvider authenticationProvider;
    public BuildInfoProvider buildInfoProvider;
    public CastingProvider castingProvider;
    public CustomerMetadataProvider customerMetadataProvider;
    public DeviceInfoProvider deviceInfoProvider;
    public EnvironmentProvider environmentProvider;
    public ExternalActionProvider externalActionProvider;
    private List<? extends Method> initialMethods;
    public MetricsProvider metricsProvider;
    private String ownerId;
    public PlaybackProvider playbackProvider;
    public SharingProvider sharingProvider;
    private SkyFireApplication skyFireApplication;
    public StorageProvider storageProvider;
    public StyleSheetProvider styleSheetProvider;
    public UpsellProvider upsellProvider;

    public BaseVideoStoryFragment() {
        String strForTemplateList = OwnerIdGenerator.forTemplateList();
        Intrinsics.checkNotNullExpressionValue(strForTemplateList, "forTemplateList()");
        this.ownerId = strForTemplateList;
    }

    public final AuthenticationProvider getAuthenticationProvider() {
        AuthenticationProvider authenticationProvider = this.authenticationProvider;
        if (authenticationProvider != null) {
            return authenticationProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("authenticationProvider");
        return null;
    }

    public final CustomerMetadataProvider getCustomerMetadataProvider() {
        CustomerMetadataProvider customerMetadataProvider = this.customerMetadataProvider;
        if (customerMetadataProvider != null) {
            return customerMetadataProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("customerMetadataProvider");
        return null;
    }

    public final DeviceInfoProvider getDeviceInfoProvider() {
        DeviceInfoProvider deviceInfoProvider = this.deviceInfoProvider;
        if (deviceInfoProvider != null) {
            return deviceInfoProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("deviceInfoProvider");
        return null;
    }

    public final BuildInfoProvider getBuildInfoProvider() {
        BuildInfoProvider buildInfoProvider = this.buildInfoProvider;
        if (buildInfoProvider != null) {
            return buildInfoProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("buildInfoProvider");
        return null;
    }

    public final ExternalActionProvider getExternalActionProvider() {
        ExternalActionProvider externalActionProvider = this.externalActionProvider;
        if (externalActionProvider != null) {
            return externalActionProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("externalActionProvider");
        return null;
    }

    public final StorageProvider getStorageProvider() {
        StorageProvider storageProvider = this.storageProvider;
        if (storageProvider != null) {
            return storageProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("storageProvider");
        return null;
    }

    public final EnvironmentProvider getEnvironmentProvider() {
        EnvironmentProvider environmentProvider = this.environmentProvider;
        if (environmentProvider != null) {
            return environmentProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("environmentProvider");
        return null;
    }

    public final PlaybackProvider getPlaybackProvider() {
        PlaybackProvider playbackProvider = this.playbackProvider;
        if (playbackProvider != null) {
            return playbackProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("playbackProvider");
        return null;
    }

    @Override // com.amazon.music.explore.skyfire.containers.ContainerMethodOwner
    public String getOwnerId() {
        return this.ownerId;
    }

    @Override // com.amazon.music.explore.fragment.StandaloneContainerFragment
    protected void onCreateTemplate(String ownerId, T template) {
        Intrinsics.checkNotNullParameter(ownerId, "ownerId");
        Intrinsics.checkNotNullParameter(template, "template");
        SkyFireApplication skyFireApplication = this.skyFireApplication;
        if (skyFireApplication == null) {
            Intrinsics.throwUninitializedPropertyAccessException("skyFireApplication");
            skyFireApplication = null;
        }
        skyFireApplication.dispatchMethods(ownerId, template.onCreated());
    }

    @Override // com.amazon.music.explore.fragment.StandaloneContainerFragment
    protected void onCreateAndBindTemplate(String ownerId, T template) {
        Intrinsics.checkNotNullParameter(ownerId, "ownerId");
        Intrinsics.checkNotNullParameter(template, "template");
        onBindTemplate(ownerId, template);
    }

    @Override // com.amazon.music.explore.fragment.StandaloneContainerFragment
    protected void onBindTemplate(String ownerId, T template) {
        Intrinsics.checkNotNullParameter(ownerId, "ownerId");
        Intrinsics.checkNotNullParameter(template, "template");
        SkyFireApplication skyFireApplication = this.skyFireApplication;
        SkyFireApplication skyFireApplication2 = null;
        if (skyFireApplication == null) {
            Intrinsics.throwUninitializedPropertyAccessException("skyFireApplication");
            skyFireApplication = null;
        }
        skyFireApplication.dispatchMethods(ownerId, template.onBound());
        SkyFireApplication skyFireApplication3 = this.skyFireApplication;
        if (skyFireApplication3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("skyFireApplication");
        } else {
            skyFireApplication2 = skyFireApplication3;
        }
        skyFireApplication2.dispatchMethods(ownerId, template.onViewed());
    }

    @Override // com.amazon.music.explore.fragment.StandaloneContainerFragment
    protected void onHandleTemplateMethod(String ownerId, Method method) {
        if (method instanceof TriggerExternalActionMethod) {
            onHandleExternalAction((TriggerExternalActionMethod) method);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        SkyFireApplication skyFireApplication = VideoStorySkyFireProvider.INSTANCE.getSkyFireApplication(this, getAuthenticationProvider(), getCustomerMetadataProvider(), getDeviceInfoProvider(), getBuildInfoProvider(), getStorageProvider(), getEnvironmentProvider());
        this.skyFireApplication = skyFireApplication;
        List<? extends Method> list = this.initialMethods;
        if (list != null) {
            if (skyFireApplication == null) {
                Intrinsics.throwUninitializedPropertyAccessException("skyFireApplication");
                skyFireApplication = null;
            }
            skyFireApplication.dispatchMethods(this.ownerId, list);
        }
        super.onCreate(savedInstanceState);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        onClose();
        super.onDestroy();
    }

    public final MethodsDispatcher<Method> getMethodsDispatcher() {
        SkyFireApplication skyFireApplication = this.skyFireApplication;
        if (skyFireApplication != null) {
            return skyFireApplication;
        }
        Intrinsics.throwUninitializedPropertyAccessException("skyFireApplication");
        return null;
    }

    public final void setInitialMethods(List<? extends Method> methods) {
        this.initialMethods = methods;
    }

    public void onHandleExternalAction(TriggerExternalActionMethod method) {
        Intrinsics.checkNotNullParameter(method, "method");
        Context context = getContext();
        if (context == null) {
            return;
        }
        Map<String, String> mapContext = method.context();
        ExternalActionProvider externalActionProvider = getExternalActionProvider();
        String strName = method.name();
        Intrinsics.checkNotNullExpressionValue(strName, "method.name()");
        if (mapContext == null) {
            mapContext = MapsKt__MapsKt.emptyMap();
        }
        externalActionProvider.handleAction(context, strName, mapContext, null);
    }
}
