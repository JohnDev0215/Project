package com.amazon.mp3.video.stories;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Template;
import TemplateContainerInterface.v1_0.BindStandaloneContainerMethod;
import TemplateContainerInterface.v1_0.CreateAndBindStandaloneContainerMethod;
import TemplateContainerInterface.v1_0.CreateStandaloneContainerMethod;
import android.content.Context;
import com.amazon.mp3.dmsfcore.providers.DMSFEnvironmentProvider;
import com.amazon.mp3.dmsfcore.providers.DMSFStorageProvider;
import com.amazon.mp3.platform.providers.AuthenticationProviderImpl;
import com.amazon.mp3.platform.providers.CustomerMetadataProviderImpl;
import com.amazon.music.explore.skyfire.OwnerIdGenerator;
import com.amazon.music.explore.skyfire.containers.ContainerMethodOwner;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.music.skyfire.SkyFireApplication;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoStoryPlaybackDataRemoteDataSource.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 !2\u00020\u0001:\u0002 !B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0016\u0010\u0013\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0014\u0010\u0014\u001a\u00020\u00112\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016J\b\u0010\u0018\u001a\u00020\nH\u0016J\u0018\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u0017H\u0016J\u000e\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u0017J\u0006\u0010\u001d\u001a\u00020\u0011J\u000e\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\bH\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/amazon/mp3/video/stories/VideoStoryPlaybackDataRemoteDataSource;", "Lcom/amazon/music/explore/skyfire/containers/ContainerMethodOwner;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "callback", "Lcom/amazon/mp3/video/stories/VideoStoryPlaybackDataRemoteDataSource$Callback;", "closeCallback", "Lcom/amazon/music/explore/skyfire/containers/ContainerMethodOwner$CloseCallback;", "ownerId", "", "kotlin.jvm.PlatformType", "skyFireApplication", "Lcom/amazon/music/skyfire/SkyFireApplication;", "template", "LCoreInterface/v1_0/Template;", "bindTemplate", "", "createAndBindTemplate", "createTemplate", "dispatchMethods", "methods", "", "LCoreInterface/v1_0/Method;", "getOwnerId", "handleMethod", "p0", "method", "handleTemplateMethod", "removeCallback", "setCallback", "setCloseCallback", "Callback", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VideoStoryPlaybackDataRemoteDataSource implements ContainerMethodOwner {
    private Callback callback;
    private ContainerMethodOwner.CloseCallback closeCallback;
    private final String ownerId;
    private final SkyFireApplication skyFireApplication;
    private Template template;

    /* JADX INFO: compiled from: VideoStoryPlaybackDataRemoteDataSource.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/video/stories/VideoStoryPlaybackDataRemoteDataSource$Callback;", "", "onDataLoaded", "", "template", "LCoreInterface/v1_0/Template;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface Callback {
        void onDataLoaded(Template template);
    }

    public final void handleTemplateMethod(Method method) {
        Intrinsics.checkNotNullParameter(method, "method");
    }

    public VideoStoryPlaybackDataRemoteDataSource(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.ownerId = OwnerIdGenerator.forTemplateContainer();
        AuthenticationProviderImpl authenticationProviderImpl = new AuthenticationProviderImpl(context, null, 2, null);
        CustomerMetadataProviderImpl customerMetadataProviderImpl = new CustomerMetadataProviderImpl(context, null, 2, 0 == true ? 1 : 0);
        DMSFStorageProvider dMSFStorageProvider = new DMSFStorageProvider(context);
        DMSFEnvironmentProvider dMSFEnvironmentProvider = new DMSFEnvironmentProvider(context);
        VideoStorySkyFireProvider videoStorySkyFireProvider = VideoStorySkyFireProvider.INSTANCE;
        Providers providers = Providers.INSTANCE;
        this.skyFireApplication = videoStorySkyFireProvider.getSkyFireApplication(this, authenticationProviderImpl, customerMetadataProviderImpl, (DeviceInfoProvider) providers.get(DeviceInfoProvider.class), (BuildInfoProvider) providers.get(BuildInfoProvider.class), dMSFStorageProvider, dMSFEnvironmentProvider);
    }

    public final void dispatchMethods(List<? extends Method> methods) {
        Intrinsics.checkNotNullParameter(methods, "methods");
        this.skyFireApplication.dispatchMethods(this.ownerId, methods);
    }

    @Override // com.amazon.music.explore.skyfire.containers.ContainerMethodOwner
    public String getOwnerId() {
        String ownerId = this.ownerId;
        Intrinsics.checkNotNullExpressionValue(ownerId, "ownerId");
        return ownerId;
    }

    @Override // com.amazon.music.explore.skyfire.containers.ContainerMethodOwner
    public void handleMethod(String p0, Method method) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(method, "method");
        if (method instanceof CreateStandaloneContainerMethod) {
            String ownerId = this.ownerId;
            Intrinsics.checkNotNullExpressionValue(ownerId, "ownerId");
            Template template = ((CreateStandaloneContainerMethod) method).template();
            Intrinsics.checkNotNullExpressionValue(template, "method.template()");
            createTemplate(ownerId, template);
            return;
        }
        if (method instanceof CreateAndBindStandaloneContainerMethod) {
            Template template2 = ((CreateAndBindStandaloneContainerMethod) method).template();
            Intrinsics.checkNotNullExpressionValue(template2, "method.template()");
            createAndBindTemplate(template2);
        } else {
            if (method instanceof BindStandaloneContainerMethod) {
                Template template3 = ((BindStandaloneContainerMethod) method).template();
                Intrinsics.checkNotNullExpressionValue(template3, "method.template()");
                bindTemplate(template3);
                return;
            }
            handleTemplateMethod(method);
        }
    }

    @Override // com.amazon.music.explore.skyfire.containers.ContainerMethodOwner
    public void setCloseCallback(ContainerMethodOwner.CloseCallback p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        this.closeCallback = p0;
    }

    public final void createTemplate(String ownerId, Template template) {
        Intrinsics.checkNotNullParameter(ownerId, "ownerId");
        Intrinsics.checkNotNullParameter(template, "template");
        this.skyFireApplication.dispatchMethods(ownerId, template.onCreated());
    }

    private final void createAndBindTemplate(Template template) {
        bindTemplate(template);
    }

    private final void bindTemplate(Template template) {
        this.template = template;
        Callback callback = this.callback;
        if (callback == null) {
            return;
        }
        callback.onDataLoaded(template);
    }

    public final void setCallback(Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.callback = callback;
    }

    public final void removeCallback() {
        ContainerMethodOwner.CloseCallback closeCallback = this.closeCallback;
        if (closeCallback != null) {
            closeCallback.onClose();
        }
        this.callback = null;
    }
}
