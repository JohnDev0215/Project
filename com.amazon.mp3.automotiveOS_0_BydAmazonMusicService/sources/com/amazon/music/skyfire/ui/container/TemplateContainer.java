package com.amazon.music.skyfire.ui.container;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Template;
import ExternalActionInterface.v1_0.ImmutableTriggerExternalActionMethod;
import ExternalActionInterface.v1_0.TriggerExternalActionMethod;
import ExternalUriInterface.v1_0.NavigateToExternalUriMethod;
import TemplateContainerInterface.v1_0.BindManagedContainerMethod;
import TemplateContainerInterface.v1_0.CreateAndBindManagedContainerMethod;
import TemplateContainerInterface.v1_0.CreateManagedContainerMethod;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import com.amazon.music.skyfire.SkyFireApplication;
import com.amazon.music.skyfire.ui.ktx.AnyKt;
import com.amazon.music.skyfire.ui.ktx.SkyFireKt;
import com.amazon.music.skyfire.ui.providers.ExternalActionProvider;
import com.amazon.music.skyfire.ui.providers.SkyFireConfigurationProvider;
import com.amazon.music.skyfire.ui.providers.SkyFireProvider;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.skyfire.ui.skyfire.Queues;
import com.amazon.music.skyfire.ui.skyfire.SkillMethodBuilder;
import com.amazon.music.skyfire.ui.skyfire.TemplateMethodListener;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: TemplateContainer.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\f\b&\u0018\u0000 B*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\u00020\u0004:\u0001BB%\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u000e\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,J\u0018\u0010-\u001a\u00020*2\u0006\u0010.\u001a\u00020/2\u0006\u0010+\u001a\u00020,H\u0002J\u001f\u00100\u001a\u00020*2\u0012\u00101\u001a\n\u0012\u0006\b\u0001\u0012\u00020302\"\u000203¢\u0006\u0002\u00104J&\u00100\u001a\u00020*2\u0006\u00105\u001a\u00020\u00152\u0016\b\u0002\u00106\u001a\u0010\u0012\u0004\u0012\u00020\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u001507J\u000e\u00108\u001a\u00020*2\u0006\u00109\u001a\u000203J\u0015\u0010:\u001a\u00020*2\u0006\u0010'\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010;J\u0015\u0010<\u001a\u00020*2\u0006\u0010'\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010;J\u0010\u0010=\u001a\u00020*2\u0006\u00109\u001a\u000203H\u0016J$\u0010>\u001a\u00020*2\u0006\u0010?\u001a\u00020\u00152\u0012\u0010@\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u001507H\u0016J&\u0010A\u001a\u00020*2\u0006\u00105\u001a\u00020\u00152\u0016\b\u0002\u00106\u001a\u0010\u0012\u0004\u0012\u00020\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u001507R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001b\u001a\u00020\u001c8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001a0$8F¢\u0006\u0006\u001a\u0004\b%\u0010&R\u0017\u0010'\u001a\b\u0012\u0004\u0012\u00028\u00000$¢\u0006\b\n\u0000\u001a\u0004\b(\u0010&¨\u0006C"}, d2 = {"Lcom/amazon/music/skyfire/ui/container/TemplateContainer;", "T", "LCoreInterface/v1_0/Template;", "Lcom/amazon/music/skyfire/ui/skyfire/TemplateMethodListener;", "Lcom/amazon/music/skyfire/ui/skyfire/DispatcherContext;", "context", "Landroid/content/Context;", "skyFireProvider", "Lcom/amazon/music/skyfire/ui/providers/SkyFireProvider;", "skyFireConfigurationProvider", "Lcom/amazon/music/skyfire/ui/providers/SkyFireConfigurationProvider;", "externalActionProvider", "Lcom/amazon/music/skyfire/ui/providers/ExternalActionProvider;", "(Landroid/content/Context;Lcom/amazon/music/skyfire/ui/providers/SkyFireProvider;Lcom/amazon/music/skyfire/ui/providers/SkyFireConfigurationProvider;Lcom/amazon/music/skyfire/ui/providers/ExternalActionProvider;)V", "getContext", "()Landroid/content/Context;", "getExternalActionProvider", "()Lcom/amazon/music/skyfire/ui/providers/ExternalActionProvider;", "isAttached", "Ljava/util/concurrent/atomic/AtomicBoolean;", "listenerId", "", "getListenerId", "()Ljava/lang/String;", "mutableStatus", "Landroidx/lifecycle/MutableLiveData;", "", "skyFireApplication", "Lcom/amazon/music/skyfire/SkyFireApplication;", "getSkyFireApplication", "()Lcom/amazon/music/skyfire/SkyFireApplication;", "getSkyFireConfigurationProvider", "()Lcom/amazon/music/skyfire/ui/providers/SkyFireConfigurationProvider;", "getSkyFireProvider", "()Lcom/amazon/music/skyfire/ui/providers/SkyFireProvider;", NotificationCompat.CATEGORY_STATUS, "Landroidx/lifecycle/LiveData;", "getStatus", "()Landroidx/lifecycle/LiveData;", "template", "getTemplate", "attach", "", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "detach", "lifecycleObserver", "Landroidx/lifecycle/LifecycleObserver;", "dispatch", "methods", "", "LCoreInterface/v1_0/Method;", "([LCoreInterface/v1_0/Method;)V", "path", "params", "", "handleMethod", "method", "onBindTemplate", "(LCoreInterface/v1_0/Template;)V", "onCreateTemplate", "onHandleMethod", "onTriggerExternalAction", "action", "args", "requestTemplate", "Companion", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class TemplateContainer<T extends Template> implements TemplateMethodListener<T>, DispatcherContext {
    private static final String ACTION_REQUEST_COMPLETE;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int STATUS_COMPLETE;
    private static final int STATUS_INCOMPLETE = 0;
    private static final Logger logger;
    private final Context context;
    private final ExternalActionProvider externalActionProvider;
    private final AtomicBoolean isAttached;
    private final String listenerId;
    private final MutableLiveData<Integer> mutableStatus;
    private final SkyFireConfigurationProvider skyFireConfigurationProvider;
    private final SkyFireProvider skyFireProvider;
    private final LiveData<T> template;

    public TemplateContainer(Context context, SkyFireProvider skyFireProvider, SkyFireConfigurationProvider skyFireConfigurationProvider, ExternalActionProvider externalActionProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(skyFireProvider, "skyFireProvider");
        Intrinsics.checkNotNullParameter(skyFireConfigurationProvider, "skyFireConfigurationProvider");
        Intrinsics.checkNotNullParameter(externalActionProvider, "externalActionProvider");
        this.context = context;
        this.skyFireProvider = skyFireProvider;
        this.skyFireConfigurationProvider = skyFireConfigurationProvider;
        this.externalActionProvider = externalActionProvider;
        MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();
        this.mutableStatus = mutableLiveData;
        this.isAttached = new AtomicBoolean();
        this.template = new MutableLiveData();
        mutableLiveData.setValue(Integer.valueOf(STATUS_INCOMPLETE));
        this.listenerId = SkyFireKt.generateUniqueId(this);
    }

    public final Context getContext() {
        return this.context;
    }

    public final SkyFireProvider getSkyFireProvider() {
        return this.skyFireProvider;
    }

    public final SkyFireConfigurationProvider getSkyFireConfigurationProvider() {
        return this.skyFireConfigurationProvider;
    }

    public final ExternalActionProvider getExternalActionProvider() {
        return this.externalActionProvider;
    }

    public final LiveData<T> getTemplate() {
        return this.template;
    }

    public final LiveData<Integer> getStatus() {
        return this.mutableStatus;
    }

    @Override // com.amazon.music.skyfire.ui.skyfire.MethodListener, com.amazon.music.skyfire.ui.skyfire.DispatcherContext
    public final String getListenerId() {
        return this.listenerId;
    }

    @Override // com.amazon.music.skyfire.ui.skyfire.DispatcherContext
    public final SkyFireApplication getSkyFireApplication() {
        return this.skyFireProvider.getSkyFireApplication();
    }

    @Override // com.amazon.music.skyfire.ui.skyfire.MethodListener
    public final void handleMethod(Method method) {
        Intrinsics.checkNotNullParameter(method, "method");
        if (method instanceof CreateManagedContainerMethod) {
            Template template = ((CreateManagedContainerMethod) method).template();
            if (template == null) {
                throw new NullPointerException("null cannot be cast to non-null type T of com.amazon.music.skyfire.ui.container.TemplateContainer");
            }
            onCreateTemplate(template);
            return;
        }
        if (method instanceof CreateAndBindManagedContainerMethod) {
            Template template2 = ((CreateAndBindManagedContainerMethod) method).template();
            if (template2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type T of com.amazon.music.skyfire.ui.container.TemplateContainer");
            }
            onBindTemplate(template2);
            return;
        }
        if (method instanceof BindManagedContainerMethod) {
            Template template3 = ((BindManagedContainerMethod) method).template();
            if (template3 == null) {
                throw new NullPointerException("null cannot be cast to non-null type T of com.amazon.music.skyfire.ui.container.TemplateContainer");
            }
            onBindTemplate(template3);
            return;
        }
        onHandleMethod(method);
    }

    @Override // com.amazon.music.skyfire.ui.skyfire.TemplateMethodListener
    public void onCreateTemplate(T template) {
        Intrinsics.checkNotNullParameter(template, "template");
        ((MutableLiveData) this.template).setValue(template);
        getSkyFireApplication().dispatchMethods(this.listenerId, template.onCreated());
    }

    @Override // com.amazon.music.skyfire.ui.skyfire.TemplateMethodListener
    public void onBindTemplate(T template) {
        Intrinsics.checkNotNullParameter(template, "template");
        ((MutableLiveData) this.template).setValue(template);
        getSkyFireApplication().dispatchMethods(this.listenerId, template.onBound());
        getSkyFireApplication().dispatchMethods(this.listenerId, template.onViewed());
    }

    public void onTriggerExternalAction(String action, Map<String, String> args) {
        Intrinsics.checkNotNullParameter(action, "action");
        Intrinsics.checkNotNullParameter(args, "args");
        if (Intrinsics.areEqual(action, ACTION_REQUEST_COMPLETE)) {
            this.mutableStatus.setValue(Integer.valueOf(STATUS_COMPLETE));
            return;
        }
        if (ExternalActionProvider.DefaultImpls.handleAction$default(this.externalActionProvider, this.context, action, args, null, 8, null)) {
            return;
        }
        logger.warn("Don't know how to trigger action '" + action + '\'');
    }

    @Override // com.amazon.music.skyfire.ui.skyfire.TemplateMethodListener
    public void onHandleMethod(Method method) {
        Intrinsics.checkNotNullParameter(method, "method");
        if (method instanceof NavigateToExternalUriMethod) {
            try {
                String strUri = ((NavigateToExternalUriMethod) method).uri();
                Intrinsics.checkNotNullExpressionValue(strUri, "method.uri()");
                Intent intent = new Intent("android.intent.action.VIEW", AnyKt.toUri(strUri));
                intent.setFlags(268435456);
                this.context.startActivity(intent);
                return;
            } catch (Exception e) {
                logger.error(Intrinsics.stringPlus("Unable to open URL ", ((NavigateToExternalUriMethod) method).uri()), (Throwable) e);
                return;
            }
        }
        if (method instanceof TriggerExternalActionMethod) {
            TriggerExternalActionMethod triggerExternalActionMethod = (TriggerExternalActionMethod) method;
            String strName = triggerExternalActionMethod.name();
            Intrinsics.checkNotNullExpressionValue(strName, "method.name()");
            Map<String, String> mapContext = triggerExternalActionMethod.context();
            if (mapContext == null) {
                mapContext = MapsKt__MapsKt.emptyMap();
            }
            onTriggerExternalAction(strName, mapContext);
            return;
        }
        logger.warn("Don't know how to handle method '" + method + '\'');
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void requestTemplate$default(TemplateContainer templateContainer, String str, Map map, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: requestTemplate");
        }
        if ((i & 2) != 0) {
            map = MapsKt__MapsKt.emptyMap();
        }
        templateContainer.requestTemplate(str, map);
    }

    public final void requestTemplate(final String path, final Map<String, String> params) {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(params, "params");
        Integer value = getStatus().getValue();
        int i = STATUS_INCOMPLETE;
        if (value == null || value.intValue() != i) {
            this.mutableStatus.setValue(Integer.valueOf(i));
        }
        dispatch(SkyFireKt.buildSkillMethod(this.skyFireConfigurationProvider, new Function1<SkillMethodBuilder, Unit>() { // from class: com.amazon.music.skyfire.ui.container.TemplateContainer.requestTemplate.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SkillMethodBuilder skillMethodBuilder) {
                invoke2(skillMethodBuilder);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SkillMethodBuilder buildSkillMethod) {
                Intrinsics.checkNotNullParameter(buildSkillMethod, "$this$buildSkillMethod");
                buildSkillMethod.setSkillPath(path);
                buildSkillMethod.getQueryParams().putAll(params);
                buildSkillMethod.getAfter().add(ImmutableTriggerExternalActionMethod.builder().name(TemplateContainer.ACTION_REQUEST_COMPLETE).queue(Queues.INSTANCE.getMultiThreaded()).build());
            }
        }));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void dispatch$default(TemplateContainer templateContainer, String str, Map map, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: dispatch");
        }
        if ((i & 2) != 0) {
            map = MapsKt__MapsKt.emptyMap();
        }
        templateContainer.dispatch(str, map);
    }

    public final void dispatch(final String path, final Map<String, String> params) {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(params, "params");
        dispatch(SkyFireKt.buildSkillMethod(this.skyFireConfigurationProvider, new Function1<SkillMethodBuilder, Unit>() { // from class: com.amazon.music.skyfire.ui.container.TemplateContainer.dispatch.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SkillMethodBuilder skillMethodBuilder) {
                invoke2(skillMethodBuilder);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SkillMethodBuilder buildSkillMethod) {
                Intrinsics.checkNotNullParameter(buildSkillMethod, "$this$buildSkillMethod");
                buildSkillMethod.setSkillPath(path);
                buildSkillMethod.getQueryParams().putAll(params);
            }
        }));
    }

    public final void dispatch(Method... methods) {
        Intrinsics.checkNotNullParameter(methods, "methods");
        if (!this.isAttached.get()) {
            throw new IllegalStateException("Container not attached");
        }
        this.skyFireProvider.getSkyFireApplication().dispatchMethods(this.listenerId, CollectionsKt__CollectionsKt.listOf(Arrays.copyOf(methods, methods.length)));
    }

    public final void attach(final LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        if (this.isAttached.compareAndSet(false, true)) {
            logger.info("Subscribing " + ((Object) getClass().getSimpleName()) + " to SkyFireProvider");
            LifecycleObserver lifecycleObserver = new LifecycleObserver(this) { // from class: com.amazon.music.skyfire.ui.container.TemplateContainer$attach$observer$1
                final /* synthetic */ TemplateContainer<T> this$0;

                {
                    this.this$0 = this;
                }

                @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                public final void removeListener() {
                    this.this$0.detach(this, lifecycleOwner);
                }
            };
            this.skyFireProvider.getRouter().plusAssign(this);
            lifecycleOwner.getLifecycle().addObserver(lifecycleObserver);
            return;
        }
        throw new IllegalStateException("Container already attached");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void detach(LifecycleObserver lifecycleObserver, LifecycleOwner lifecycleOwner) {
        if (!this.isAttached.compareAndSet(true, false)) {
            throw new IllegalStateException("Unexpected subscription state");
        }
        logger.info("Unsubscribing " + ((Object) getClass().getSimpleName()) + " from SkyFireProvider");
        lifecycleOwner.getLifecycle().removeObserver(lifecycleObserver);
        this.skyFireProvider.getRouter().minusAssign(this);
    }

    /* JADX INFO: compiled from: TemplateContainer.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/music/skyfire/ui/container/TemplateContainer$Companion;", "", "()V", "ACTION_REQUEST_COMPLETE", "", "STATUS_COMPLETE", "", "getSTATUS_COMPLETE", "()I", "STATUS_INCOMPLETE", "getSTATUS_INCOMPLETE", "logger", "Lorg/slf4j/Logger;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getSTATUS_INCOMPLETE() {
            return TemplateContainer.STATUS_INCOMPLETE;
        }

        public final int getSTATUS_COMPLETE() {
            return TemplateContainer.STATUS_COMPLETE;
        }
    }

    static {
        Logger logger2 = LoggerFactory.getLogger(TemplateContainer.class.getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger2, "getLogger(TemplateContai…r::class.java.simpleName)");
        logger = logger2;
        STATUS_COMPLETE = 1;
        ACTION_REQUEST_COMPLETE = "REQUEST_COMPLETE";
    }
}
