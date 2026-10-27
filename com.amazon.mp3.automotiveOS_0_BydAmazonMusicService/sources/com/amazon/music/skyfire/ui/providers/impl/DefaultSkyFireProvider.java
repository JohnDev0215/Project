package com.amazon.music.skyfire.ui.providers.impl;

import CoreInterface.v1_0.Method;
import ExternalActionInterface.v1_0.ImmutablePreFetchMethod;
import ExternalActionInterface.v1_0.ImmutableShowUpsellMethod;
import ExternalActionInterface.v1_0.ImmutableToggleButtonMethod;
import ExternalActionInterface.v1_0.ImmutableTriggerExternalActionMethod;
import ExternalActionInterface.v1_0.ImmutableTriggerExternalContextMenuMethod;
import ExternalActionInterface.v1_0.ImmutableTriggerExternalGenericPlaybackMethod;
import ExternalActionInterface.v1_0.ImmutableTriggerExternalPlaybackMethod;
import ExternalUriInterface.v1_0.ImmutableNavigateToExternalUriMethod;
import InteractionInterface.v1_0.ImmutableSetHttpSkillUrlMethod;
import InteractionInterface.v1_0.SetHttpSkillUrlMethod;
import TemplateContainerInterface.v1_0.ImmutableBindManagedContainerMethod;
import TemplateContainerInterface.v1_0.ImmutableBindStandaloneContainerMethod;
import TemplateContainerInterface.v1_0.ImmutableCreateAndBindManagedContainerMethod;
import TemplateContainerInterface.v1_0.ImmutableCreateAndBindStandaloneContainerMethod;
import TemplateContainerInterface.v1_0.ImmutableCreateManagedContainerMethod;
import TemplateContainerInterface.v1_0.ImmutableCreateStandaloneContainerMethod;
import Touch.DetailTemplateInterface.v1_0.ImmutableAddVerticalItemsMethod;
import Touch.LiveStreamPlayerTemplateInterface.v1_0.ImmutableDisplayGenericErrorMethod;
import Touch.MultiSelectorTemplateInterface.v1_0.ImmutableTriggerExternalSelectElementMethod;
import Touch.PageInterface.v1_0.ImmutableAddVerticalItemsToLastWidgetMethod;
import Touch.PageInterface.v1_0.ImmutableAddWidgetsToTemplateMethod;
import Touch.PageInterface.v1_0.ImmutableRemoveWidgetsFromTemplateMethod;
import Touch.PageInterface.v1_0.ImmutableToggleWidgetsMethod;
import Touch.SelectionTemplateInterface.v1_0.ImmutableChangeSelectionMethod;
import Touch.SelectionTemplateInterface.v1_0.ImmutableConfirmSelectionMethod;
import Touch.SelectionTemplateInterface.v1_0.ImmutableOpenSelectionMethod;
import android.content.Context;
import android.os.Looper;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.CustomerMetadataProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.music.skyfire.SkyFireApplication;
import com.amazon.music.skyfire.core.network.CapabilitiesInfo;
import com.amazon.music.skyfire.core.network.HttpSkillInfo;
import com.amazon.music.skyfire.core.network.NetworkInterceptor;
import com.amazon.music.skyfire.core.network.NetworkMethodsRegistry;
import com.amazon.music.skyfire.core.performance.SkillLatencyTracker;
import com.amazon.music.skyfire.core.template.Handlers;
import com.amazon.music.skyfire.platform.MethodCallback;
import com.amazon.music.skyfire.platform.OnMethodExecutedListener;
import com.amazon.music.skyfire.ui.providers.PlaybackProvider;
import com.amazon.music.skyfire.ui.providers.SkyFireConfigurationProvider;
import com.amazon.music.skyfire.ui.providers.SkyFireProvider;
import com.amazon.music.skyfire.ui.providers.impl.DefaultSkyFireProvider;
import com.amazon.music.skyfire.ui.skyfire.AndroidClientInformationCache;
import com.amazon.music.skyfire.ui.skyfire.ExternalActionMethodsRegistry;
import com.amazon.music.skyfire.ui.skyfire.MethodListener;
import com.amazon.music.skyfire.ui.skyfire.MethodRouter;
import com.amazon.music.skyfire.ui.skyfire.SkyFireAuthentication;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: DefaultSkyFireProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u001f2\u00020\u0001:\u0004\u001f !\"BW\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015¢\u0006\u0002\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u0018X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u001cX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/amazon/music/skyfire/ui/providers/impl/DefaultSkyFireProvider;", "Lcom/amazon/music/skyfire/ui/providers/SkyFireProvider;", "context", "Landroid/content/Context;", "authenticationProvider", "Lcom/amazon/music/platform/providers/AuthenticationProvider;", "customerMetadataProvider", "Lcom/amazon/music/platform/providers/CustomerMetadataProvider;", "deviceInfoProvider", "Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "buildInfoProvider", "Lcom/amazon/music/platform/providers/BuildInfoProvider;", "playbackProvider", "Lcom/amazon/music/skyfire/ui/providers/PlaybackProvider;", "capabilitiesInfo", "Lcom/amazon/music/skyfire/core/network/CapabilitiesInfo;", "networkInterceptor", "Lcom/amazon/music/skyfire/core/network/NetworkInterceptor;", "skillLatencyTracker", "Lcom/amazon/music/skyfire/core/performance/SkillLatencyTracker;", "skyFireConfigurationProvider", "Lcom/amazon/music/skyfire/ui/providers/SkyFireConfigurationProvider;", "(Landroid/content/Context;Lcom/amazon/music/platform/providers/AuthenticationProvider;Lcom/amazon/music/platform/providers/CustomerMetadataProvider;Lcom/amazon/music/platform/providers/DeviceInfoProvider;Lcom/amazon/music/platform/providers/BuildInfoProvider;Lcom/amazon/music/skyfire/ui/providers/PlaybackProvider;Lcom/amazon/music/skyfire/core/network/CapabilitiesInfo;Lcom/amazon/music/skyfire/core/network/NetworkInterceptor;Lcom/amazon/music/skyfire/core/performance/SkillLatencyTracker;Lcom/amazon/music/skyfire/ui/providers/SkyFireConfigurationProvider;)V", "router", "Lcom/amazon/music/skyfire/ui/skyfire/MethodRouter;", "getRouter", "()Lcom/amazon/music/skyfire/ui/skyfire/MethodRouter;", "skyFireApplication", "Lcom/amazon/music/skyfire/SkyFireApplication;", "getSkyFireApplication", "()Lcom/amazon/music/skyfire/SkyFireApplication;", "Companion", "ProviderMethodRouter", "SkyFireHttpSkillInfo", "SkyFireMethodsRegistry", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DefaultSkyFireProvider implements SkyFireProvider {
    private static final Logger logger;
    private final MethodRouter router;
    private final SkyFireApplication skyFireApplication;
    private final SkyFireConfigurationProvider skyFireConfigurationProvider;

    public DefaultSkyFireProvider(Context context, AuthenticationProvider authenticationProvider, CustomerMetadataProvider customerMetadataProvider, DeviceInfoProvider deviceInfoProvider, BuildInfoProvider buildInfoProvider, PlaybackProvider playbackProvider, CapabilitiesInfo capabilitiesInfo, NetworkInterceptor networkInterceptor, SkillLatencyTracker skillLatencyTracker, SkyFireConfigurationProvider skyFireConfigurationProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(authenticationProvider, "authenticationProvider");
        Intrinsics.checkNotNullParameter(customerMetadataProvider, "customerMetadataProvider");
        Intrinsics.checkNotNullParameter(deviceInfoProvider, "deviceInfoProvider");
        Intrinsics.checkNotNullParameter(buildInfoProvider, "buildInfoProvider");
        Intrinsics.checkNotNullParameter(playbackProvider, "playbackProvider");
        Intrinsics.checkNotNullParameter(capabilitiesInfo, "capabilitiesInfo");
        Intrinsics.checkNotNullParameter(networkInterceptor, "networkInterceptor");
        Intrinsics.checkNotNullParameter(skillLatencyTracker, "skillLatencyTracker");
        Intrinsics.checkNotNullParameter(skyFireConfigurationProvider, "skyFireConfigurationProvider");
        this.skyFireConfigurationProvider = skyFireConfigurationProvider;
        this.skyFireApplication = new SkyFireApplication();
        NetworkMethodsRegistry networkMethodsRegistry = new NetworkMethodsRegistry(getSkyFireApplication(), new DefaultDeviceInfoProvider(deviceInfoProvider), new DefaultBuildInfoProvider(deviceInfoProvider, buildInfoProvider), new SkyFireHttpSkillInfo(this), capabilitiesInfo, networkInterceptor, skillLatencyTracker, new SkyFireAuthentication(authenticationProvider, customerMetadataProvider), AndroidClientInformationCache.INSTANCE);
        networkMethodsRegistry.registerMethods();
        networkMethodsRegistry.setMethodsDispatcher(getSkyFireApplication());
        new SkyFireMethodsRegistry(this, getSkyFireApplication()).registerMethods();
        new ExternalActionMethodsRegistry(context, getSkyFireApplication(), playbackProvider).registerMethods();
        this.router = new ProviderMethodRouter(this, getSkyFireApplication());
        ((ProviderMethodRouter) getRouter()).registerMethods();
    }

    @Override // com.amazon.music.skyfire.ui.providers.SkyFireProvider
    public MethodRouter getRouter() {
        return this.router;
    }

    @Override // com.amazon.music.skyfire.ui.providers.SkyFireProvider
    public SkyFireApplication getSkyFireApplication() {
        return this.skyFireApplication;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: DefaultSkyFireProvider.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J&\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\t2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0002H\u0002J\u0011\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\nH\u0096\u0002J\u0011\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\nH\u0096\u0002J\u0006\u0010\u0017\u001a\u00020\u000eR\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/amazon/music/skyfire/ui/providers/impl/DefaultSkyFireProvider$ProviderMethodRouter;", "Lcom/amazon/music/skyfire/platform/MethodCallback;", "LCoreInterface/v1_0/Method;", "Lcom/amazon/music/skyfire/ui/skyfire/MethodRouter;", "skyFireApplication", "Lcom/amazon/music/skyfire/SkyFireApplication;", "(Lcom/amazon/music/skyfire/ui/providers/impl/DefaultSkyFireProvider;Lcom/amazon/music/skyfire/SkyFireApplication;)V", "handlerMap", "", "", "Lcom/amazon/music/skyfire/ui/skyfire/MethodListener;", "getSkyFireApplication", "()Lcom/amazon/music/skyfire/SkyFireApplication;", "executeMethod", "", "method", "ownerId", "onMethodExecutedListener", "Lcom/amazon/music/skyfire/platform/OnMethodExecutedListener;", "invokeListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "minusAssign", "plusAssign", "registerMethods", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    final class ProviderMethodRouter implements MethodCallback<Method>, MethodRouter {
        private final Map<String, MethodListener> handlerMap;
        private final SkyFireApplication skyFireApplication;
        final /* synthetic */ DefaultSkyFireProvider this$0;

        public ProviderMethodRouter(DefaultSkyFireProvider this$0, SkyFireApplication skyFireApplication) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(skyFireApplication, "skyFireApplication");
            this.this$0 = this$0;
            this.skyFireApplication = skyFireApplication;
            this.handlerMap = new LinkedHashMap();
        }

        public final SkyFireApplication getSkyFireApplication() {
            return this.skyFireApplication;
        }

        public final void registerMethods() {
            List listListOf = CollectionsKt__CollectionsKt.listOf((Object[]) new Class[]{ImmutableBindStandaloneContainerMethod.class, ImmutableBindManagedContainerMethod.class, ImmutableCreateStandaloneContainerMethod.class, ImmutableCreateManagedContainerMethod.class, ImmutableCreateAndBindStandaloneContainerMethod.class, ImmutableCreateAndBindManagedContainerMethod.class, ImmutableTriggerExternalContextMenuMethod.class, ImmutableTriggerExternalActionMethod.class, ImmutableNavigateToExternalUriMethod.class, ImmutableShowUpsellMethod.class, ImmutableTriggerExternalGenericPlaybackMethod.class, ImmutableTriggerExternalPlaybackMethod.class, ImmutableDisplayGenericErrorMethod.class, ImmutableAddVerticalItemsMethod.class, ImmutableAddWidgetsToTemplateMethod.class, ImmutableToggleButtonMethod.class, ImmutableOpenSelectionMethod.class, ImmutableChangeSelectionMethod.class, ImmutableConfirmSelectionMethod.class, ImmutableTriggerExternalSelectElementMethod.class, ImmutableAddVerticalItemsToLastWidgetMethod.class, ImmutableToggleWidgetsMethod.class, ImmutableRemoveWidgetsFromTemplateMethod.class, ImmutablePreFetchMethod.class});
            SkyFireApplication skyFireApplication = this.skyFireApplication;
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listListOf, 10));
            Iterator it = listListOf.iterator();
            while (it.hasNext()) {
                arrayList.add(((Class) it.next()).getCanonicalName());
            }
            skyFireApplication.registerMethods(CollectionsKt___CollectionsKt.toSet(arrayList), SetsKt__SetsKt.emptySet(), this);
        }

        @Override // com.amazon.music.skyfire.platform.MethodCallback
        public void executeMethod(Method method, String ownerId, OnMethodExecutedListener<Method> onMethodExecutedListener) {
            Intrinsics.checkNotNullParameter(method, "method");
            Intrinsics.checkNotNullParameter(ownerId, "ownerId");
            Intrinsics.checkNotNullParameter(onMethodExecutedListener, "onMethodExecutedListener");
            MethodListener methodListener = this.handlerMap.get(ownerId);
            if (methodListener == null || !this.skyFireApplication.isOwnerAvailable(ownerId)) {
                onMethodExecutedListener.onMethodExecuted(method);
            } else {
                invokeListener(methodListener, method);
                onMethodExecutedListener.onMethodExecuted(method);
            }
        }

        private final void invokeListener(final MethodListener listener, final Method method) {
            DefaultSkyFireProvider.logger.debug("Routing " + ((Object) method.getClass().getSimpleName()) + " to " + ((Object) listener.getClass().getSimpleName()));
            if (Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
                listener.handleMethod(method);
            } else {
                Handlers.getForeground().post(new Runnable() { // from class: com.amazon.music.skyfire.ui.providers.impl.DefaultSkyFireProvider$ProviderMethodRouter$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        DefaultSkyFireProvider.ProviderMethodRouter.m2202invokeListener$lambda1(listener, method);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: invokeListener$lambda-1, reason: not valid java name */
        public static final void m2202invokeListener$lambda1(MethodListener listener, Method method) {
            Intrinsics.checkNotNullParameter(listener, "$listener");
            Intrinsics.checkNotNullParameter(method, "$method");
            listener.handleMethod(method);
        }

        @Override // com.amazon.music.skyfire.ui.skyfire.MethodRouter
        public void plusAssign(MethodListener listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            synchronized (this.handlerMap) {
                if (!this.handlerMap.containsKey(listener.getListenerId())) {
                    this.handlerMap.put(listener.getListenerId(), listener);
                    getSkyFireApplication().registerOwner(listener.getListenerId());
                    DefaultSkyFireProvider.logger.debug("Registered listener " + ((Object) listener.getClass().getSimpleName()) + " (" + listener.getListenerId() + ')');
                }
                Unit unit = Unit.INSTANCE;
            }
        }

        @Override // com.amazon.music.skyfire.ui.skyfire.MethodRouter
        public void minusAssign(MethodListener listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            synchronized (this.handlerMap) {
                if (this.handlerMap.remove(listener.getListenerId()) != null) {
                    getSkyFireApplication().unregisterOwner(listener.getListenerId());
                    DefaultSkyFireProvider.logger.debug("Deregistered listener " + ((Object) listener.getClass().getSimpleName()) + " (" + listener.getListenerId() + ')');
                }
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    /* JADX INFO: compiled from: DefaultSkyFireProvider.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/skyfire/ui/providers/impl/DefaultSkyFireProvider$SkyFireHttpSkillInfo;", "Lcom/amazon/music/skyfire/core/network/HttpSkillInfo;", "(Lcom/amazon/music/skyfire/ui/providers/impl/DefaultSkyFireProvider;)V", ImagesContract.URL, "", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private final class SkyFireHttpSkillInfo implements HttpSkillInfo {
        final /* synthetic */ DefaultSkyFireProvider this$0;

        public SkyFireHttpSkillInfo(DefaultSkyFireProvider this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // com.amazon.music.skyfire.core.network.HttpSkillInfo
        public String url() {
            return this.this$0.skyFireConfigurationProvider.getServiceURL();
        }
    }

    /* JADX INFO: compiled from: DefaultSkyFireProvider.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J&\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\u000eH\u0016J\u0006\u0010\u000f\u001a\u00020\tR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/skyfire/ui/providers/impl/DefaultSkyFireProvider$SkyFireMethodsRegistry;", "Lcom/amazon/music/skyfire/platform/MethodCallback;", "LCoreInterface/v1_0/Method;", "skyFireApplication", "Lcom/amazon/music/skyfire/SkyFireApplication;", "(Lcom/amazon/music/skyfire/ui/providers/impl/DefaultSkyFireProvider;Lcom/amazon/music/skyfire/SkyFireApplication;)V", "getSkyFireApplication", "()Lcom/amazon/music/skyfire/SkyFireApplication;", "executeMethod", "", "method", "ownerId", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/amazon/music/skyfire/platform/OnMethodExecutedListener;", "registerMethods", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private final class SkyFireMethodsRegistry implements MethodCallback<Method> {
        private final SkyFireApplication skyFireApplication;
        final /* synthetic */ DefaultSkyFireProvider this$0;

        public SkyFireMethodsRegistry(DefaultSkyFireProvider this$0, SkyFireApplication skyFireApplication) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(skyFireApplication, "skyFireApplication");
            this.this$0 = this$0;
            this.skyFireApplication = skyFireApplication;
        }

        public final SkyFireApplication getSkyFireApplication() {
            return this.skyFireApplication;
        }

        public final void registerMethods() {
            HashSet hashSet = new HashSet();
            String canonicalName = ImmutableSetHttpSkillUrlMethod.class.getCanonicalName();
            Intrinsics.checkNotNull(canonicalName);
            hashSet.add(canonicalName);
            this.skyFireApplication.registerMethods(hashSet, SetsKt__SetsKt.emptySet(), this);
        }

        @Override // com.amazon.music.skyfire.platform.MethodCallback
        public void executeMethod(Method method, String ownerId, OnMethodExecutedListener<Method> listener) {
            Intrinsics.checkNotNullParameter(method, "method");
            Intrinsics.checkNotNullParameter(ownerId, "ownerId");
            Intrinsics.checkNotNullParameter(listener, "listener");
            if (method instanceof SetHttpSkillUrlMethod) {
                this.this$0.skyFireConfigurationProvider.setServiceURL(((SetHttpSkillUrlMethod) method).url());
                listener.onMethodExecuted(method);
            }
        }
    }

    static {
        Logger logger2 = LoggerFactory.getLogger(DefaultSkyFireProvider.class.getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger2, "getLogger(DefaultSkyFire…r::class.java.simpleName)");
        logger = logger2;
    }
}
