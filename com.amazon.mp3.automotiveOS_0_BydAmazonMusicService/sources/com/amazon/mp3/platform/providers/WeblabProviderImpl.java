package com.amazon.mp3.platform.providers;

import android.content.Context;
import android.os.Build;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.account.preauth.PreAuthUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.config.ApplicationAttributes;
import com.amazon.mp3.prime.browse.BrowseLanguageProvider;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.stability.StabilityManager;
import com.amazon.mp3.util.IoUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.weblab.Weblab;
import com.amazon.music.authentication.AuthDataType;
import com.amazon.music.authentication.AuthInfo;
import com.amazon.music.authentication.AuthenticationException;
import com.amazon.music.featuregating.weblabs.WeblabClientInfo;
import com.amazon.music.featuregating.weblabs.WeblabConfigurationReader;
import com.amazon.music.featuregating.weblabs.WeblabCustomerInfo;
import com.amazon.music.featuregating.weblabs.WeblabDescriptor;
import com.amazon.music.featuregating.weblabs.WeblabManager;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.platform.data.WeblabTreatment;
import com.amazon.music.platform.providers.WeblabProvider;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: WeblabProviderImpl.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 02\u00020\u0001:\u00010B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b.\u0010/J.\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002J6\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002J\u0010\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0012H\u0002J\u0018\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0004H\u0016J\u0016\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u0012J\u000e\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u0012J\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\t0\bR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0005\u0010\u001dR\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0016\u0010\"\u001a\u00020!8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010%\u001a\u00020$8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010&R(\u0010(\u001a\u0004\u0018\u00010\u00122\b\u0010'\u001a\u0004\u0018\u00010\u00128\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+R(\u0010,\u001a\u0004\u0018\u00010\u00122\b\u0010'\u001a\u0004\u0018\u00010\u00128\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b,\u0010)\u001a\u0004\b-\u0010+¨\u00061"}, d2 = {"Lcom/amazon/mp3/platform/providers/WeblabProviderImpl;", "Lcom/amazon/music/platform/providers/WeblabProvider;", "Landroid/content/Context;", "context", "", "isAuthenticated", "Lcom/amazon/music/authentication/AuthInfo;", "authInfo", "", "Lcom/amazon/music/featuregating/weblabs/WeblabDescriptor;", "weblabs", "", "reset", "Lcom/amazon/music/featuregating/weblabs/WeblabClientInfo;", "clientInfo", "Lcom/amazon/music/featuregating/weblabs/WeblabCustomerInfo;", "customerInfo", "createOrUpdateClient", "", "flexEvent", "emmitWeblabProviderImplMetric", "weblabName", "shouldTrigger", "Lcom/amazon/music/platform/data/WeblabTreatment;", "getTreatment", "treatment", "lockWeblab", "unlockWeblab", "getAllRegisteredWeblabs", "Ljava/lang/Boolean;", "Lcom/amazon/music/featuregating/weblabs/WeblabManager;", "weblabManager", "Lcom/amazon/music/featuregating/weblabs/WeblabManager;", "Lcom/amazon/mp3/stability/StabilityManager;", "stabilityManager", "Lcom/amazon/mp3/stability/StabilityManager;", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "accountCompositeDisposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "<set-?>", "overriddenWeblab", "Ljava/lang/String;", "getOverriddenWeblab", "()Ljava/lang/String;", "overriddenTreatment", "getOverriddenTreatment", "<init>", "(Landroid/content/Context;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class WeblabProviderImpl implements WeblabProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = WeblabProviderImpl.class.getSimpleName();
    private static volatile WeblabProviderImpl instance;
    private final CompositeDisposable accountCompositeDisposable;
    private Boolean isAuthenticated;
    private String overriddenTreatment;
    private String overriddenWeblab;
    private StabilityManager stabilityManager;
    private WeblabManager weblabManager;

    public /* synthetic */ WeblabProviderImpl(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    private WeblabProviderImpl(final Context context) {
        this.stabilityManager = StabilityManager.INSTANCE.getInstance();
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        this.accountCompositeDisposable = compositeDisposable;
        compositeDisposable.add(AccountCredentialUtil.get(context).getIsAuthenticatedObservable().subscribeOn(Schedulers.computation()).filter(new Predicate() { // from class: com.amazon.mp3.platform.providers.WeblabProviderImpl$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return WeblabProviderImpl.m1376_init_$lambda0(this.f$0, (Boolean) obj);
            }
        }).subscribe(new Consumer() { // from class: com.amazon.mp3.platform.providers.WeblabProviderImpl$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                WeblabProviderImpl.m1377_init_$lambda2(this.f$0, context, (Boolean) obj);
            }
        }, new Consumer() { // from class: com.amazon.mp3.platform.providers.WeblabProviderImpl$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                WeblabProviderImpl.m1378_init_$lambda3((Throwable) obj);
            }
        }));
    }

    public final String getOverriddenWeblab() {
        return this.overriddenWeblab;
    }

    public final String getOverriddenTreatment() {
        return this.overriddenTreatment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-0, reason: not valid java name */
    public static final boolean m1376_init_$lambda0(WeblabProviderImpl this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return !Intrinsics.areEqual(this$0.isAuthenticated, bool);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-2, reason: not valid java name */
    public static final void m1377_init_$lambda2(WeblabProviderImpl this$0, Context context, Boolean isAuthenticated) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        synchronized (this$0) {
            this$0.isAuthenticated = isAuthenticated;
            AuthInfo authInfo = AmazonApplication.getAuthInfoMAPR5();
            Intrinsics.checkNotNullExpressionValue(isAuthenticated, "isAuthenticated");
            boolean zBooleanValue = isAuthenticated.booleanValue();
            Intrinsics.checkNotNullExpressionValue(authInfo, "authInfo");
            this$0.reset(context, zBooleanValue, authInfo, Companion.allWeblabs$default(INSTANCE, context, 0, 2, null));
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-3, reason: not valid java name */
    public static final void m1378_init_$lambda3(Throwable th) {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Failed to create a weblab due to error: ");
        sb.append((Object) th.getMessage());
        sb.append(" cause: ");
        Throwable cause = th.getCause();
        sb.append((Object) (cause == null ? null : cause.getMessage()));
        Log.error(str, sb.toString());
    }

    private final void reset(Context context, boolean isAuthenticated, AuthInfo authInfo, List<WeblabDescriptor> weblabs) {
        String obfuscatedId;
        long jCurrentTimeMillis = System.currentTimeMillis();
        String sessionId = SessionIdProviderImpl.INSTANCE.getSessionId(isAuthenticated, context);
        long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
        String str = TAG;
        Log.debug(str, Intrinsics.stringPlus("Time taken  to fetch session ID: ", Long.valueOf(jCurrentTimeMillis2)));
        String directedId = INSTANCE.getDirectedId(authInfo);
        if (sessionId != null && (!isAuthenticated || (isAuthenticated && directedId != null))) {
            if (isAuthenticated) {
                obfuscatedId = AccountDetailUtil.get(context).getHomeMarketPlaceId();
                Intrinsics.checkNotNullExpressionValue(obfuscatedId, "{\n                Accoun…rketPlaceId\n            }");
            } else {
                Marketplace marketplaceFromLocaleCountry = PreAuthUtil.INSTANCE.getMarketplaceFromLocaleCountry(new BrowseLanguageProvider().getLocale(context));
                obfuscatedId = marketplaceFromLocaleCountry == null ? null : marketplaceFromLocaleCountry.getObfuscatedId();
                Intrinsics.checkNotNullExpressionValue(obfuscatedId, "{\n                PreAut…bfuscatedId\n            }");
            }
            Log.debug(str, Intrinsics.stringPlus("MarketplaceId used for Weblab: ", obfuscatedId));
            WeblabCustomerInfo weblabCustomerInfo = new WeblabCustomerInfo(obfuscatedId, sessionId, directedId);
            String version = ApplicationAttributes.getInstance(context).getVersion();
            Intrinsics.checkNotNullExpressionValue(version, "getInstance(context).version");
            createOrUpdateClient(context, isAuthenticated, new WeblabClientInfo("DigitalMusicAndroid3PWeblabs", "DigitalMusicAndroid3P", version, String.valueOf(Build.VERSION.SDK_INT)), weblabCustomerInfo, weblabs);
            return;
        }
        Log.error(str, "Failed to update or create weblab client!");
        if (isAuthenticated) {
            emmitWeblabProviderImplMetric("FAILED_TO_UPDATE_WEBLAB_PROVIDER");
        }
    }

    private final void createOrUpdateClient(Context context, boolean isAuthenticated, WeblabClientInfo clientInfo, WeblabCustomerInfo customerInfo, List<WeblabDescriptor> weblabs) {
        if (this.weblabManager != null) {
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new AnonymousClass1(customerInfo, null), 3, null);
            return;
        }
        try {
            this.weblabManager = new WeblabManager(context, isAuthenticated, weblabs, customerInfo, clientInfo, null, 32, null);
        } catch (Exception e) {
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("WeblabManager Init Failed  with exception: ");
            sb.append((Object) e.getMessage());
            sb.append(" cause: ");
            Throwable cause = e.getCause();
            sb.append((Object) (cause != null ? cause.getMessage() : null));
            Log.error(str, sb.toString());
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.platform.providers.WeblabProviderImpl$createOrUpdateClient$1, reason: invalid class name */
    /* JADX INFO: compiled from: WeblabProviderImpl.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.platform.providers.WeblabProviderImpl$createOrUpdateClient$1", f = "WeblabProviderImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ WeblabCustomerInfo $customerInfo;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(WeblabCustomerInfo weblabCustomerInfo, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$customerInfo = weblabCustomerInfo;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return WeblabProviderImpl.this.new AnonymousClass1(this.$customerInfo, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Exception {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            WeblabManager weblabManager = WeblabProviderImpl.this.weblabManager;
            if (weblabManager != null) {
                weblabManager.refreshClient(this.$customerInfo);
            }
            return Unit.INSTANCE;
        }
    }

    @Override // com.amazon.music.platform.providers.WeblabProvider
    public WeblabTreatment getTreatment(String weblabName, boolean shouldTrigger) {
        WeblabTreatment weblabTreatmentTreatmentWithoutTriggering;
        Intrinsics.checkNotNullParameter(weblabName, "weblabName");
        if (shouldTrigger) {
            WeblabManager weblabManager = this.weblabManager;
            weblabTreatmentTreatmentWithoutTriggering = weblabManager != null ? weblabManager.treatment(weblabName) : null;
            if (weblabTreatmentTreatmentWithoutTriggering == null) {
                weblabTreatmentTreatmentWithoutTriggering = WeblabTreatment.C;
            }
        } else {
            WeblabManager weblabManager2 = this.weblabManager;
            weblabTreatmentTreatmentWithoutTriggering = weblabManager2 != null ? weblabManager2.treatmentWithoutTriggering(weblabName) : null;
            if (weblabTreatmentTreatmentWithoutTriggering == null) {
                weblabTreatmentTreatmentWithoutTriggering = WeblabTreatment.C;
            }
        }
        if (weblabTreatmentTreatmentWithoutTriggering != WeblabTreatment.C && !StringsKt__StringsKt.contains((CharSequence) weblabName, (CharSequence) "_AA_", true)) {
            this.stabilityManager.recordFeatureFlag(weblabName, weblabTreatmentTreatmentWithoutTriggering.getTreatment());
        }
        return weblabTreatmentTreatmentWithoutTriggering;
    }

    public final void lockWeblab(String weblabName, String treatment) {
        Intrinsics.checkNotNullParameter(weblabName, "weblabName");
        Intrinsics.checkNotNullParameter(treatment, "treatment");
        this.overriddenWeblab = weblabName;
        this.overriddenTreatment = treatment;
        Log.debug(TAG, "Locking weblab: " + weblabName + " to treatment: " + treatment);
        WeblabManager weblabManager = this.weblabManager;
        if (weblabManager == null) {
            return;
        }
        weblabManager.lockWeblab(weblabName, WeblabTreatment.INSTANCE.from(treatment));
    }

    public final void unlockWeblab(String weblabName) {
        Intrinsics.checkNotNullParameter(weblabName, "weblabName");
        this.overriddenWeblab = null;
        this.overriddenTreatment = null;
        Log.debug(TAG, Intrinsics.stringPlus("Unlocking weblab: ", weblabName));
        WeblabManager weblabManager = this.weblabManager;
        if (weblabManager == null) {
            return;
        }
        weblabManager.unlockWeblab(weblabName);
    }

    public final List<WeblabDescriptor> getAllRegisteredWeblabs() {
        WeblabManager weblabManager = this.weblabManager;
        List<WeblabDescriptor> registeredWeblabs = weblabManager == null ? null : weblabManager.getRegisteredWeblabs();
        return registeredWeblabs == null ? CollectionsKt__CollectionsKt.emptyList() : registeredWeblabs;
    }

    private final void emmitWeblabProviderImplMetric(String flexEvent) {
        MetricsLogger.sendEvent(FlexEvent.builder(TAG).withHcFlexStr1(flexEvent).build());
    }

    /* JADX INFO: compiled from: WeblabProviderImpl.kt */
    @Metadata(bv = {}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ \u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u0002J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\tH\u0002J\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\fJ\u0010\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0002H\u0007R\u0014\u0010\u0012\u001a\u00020\u000e8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0015\u001a\n \u0014*\u0004\u0018\u00010\u000e0\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0013R\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u000e8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0019\u0010\u0013R\u0014\u0010\u001a\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u001a\u0010\u001b¨\u0006\u001e"}, d2 = {"Lcom/amazon/mp3/platform/providers/WeblabProviderImpl$Companion;", "", "Landroid/content/Context;", "context", "", "resourceId", "", "Lcom/amazon/music/featuregating/weblabs/WeblabDescriptor;", "allWeblabs", "", "legacyWeblabs", "modernWeblabs", "Lcom/amazon/music/authentication/AuthInfo;", "authInfo", "", "getDirectedId", "Lcom/amazon/music/platform/providers/WeblabProvider;", "getInstance", "FAILED_TO_UPDATE_WEBLAB_PROVIDER", "Ljava/lang/String;", "kotlin.jvm.PlatformType", "TAG", "Lcom/amazon/mp3/platform/providers/WeblabProviderImpl;", "instance", "Lcom/amazon/mp3/platform/providers/WeblabProviderImpl;", "unauthenticatedSessionIdKey", "weblabsResourceId", "I", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        static /* synthetic */ List allWeblabs$default(Companion companion, Context context, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = R.raw.weblabs;
            }
            return companion.allWeblabs(context, i);
        }

        private final List<WeblabDescriptor> allWeblabs(Context context, int resourceId) {
            return CollectionsKt___CollectionsKt.toList(CollectionsKt___CollectionsKt.union(legacyWeblabs(), modernWeblabs(context, resourceId)));
        }

        private final Set<WeblabDescriptor> legacyWeblabs() {
            String[] allWeblabs = Weblab.getAllWeblabs();
            Intrinsics.checkNotNullExpressionValue(allWeblabs, "getAllWeblabs()");
            ArrayList arrayList = new ArrayList(allWeblabs.length);
            int length = allWeblabs.length;
            int i = 0;
            while (i < length) {
                String it = allWeblabs[i];
                i++;
                Intrinsics.checkNotNullExpressionValue(it, "it");
                arrayList.add(new WeblabDescriptor(it, null, 2, null));
            }
            return CollectionsKt___CollectionsKt.toSet(arrayList);
        }

        private final Set<WeblabDescriptor> modernWeblabs(Context context, int resourceId) {
            try {
                InputStream inputStreamOpenRawResource = context.getResources().openRawResource(resourceId);
                Intrinsics.checkNotNullExpressionValue(inputStreamOpenRawResource, "context.resources.openRawResource(resourceId)");
                String jsonString = IoUtil.streamToString(inputStreamOpenRawResource);
                inputStreamOpenRawResource.close();
                WeblabConfigurationReader weblabConfigurationReader = WeblabConfigurationReader.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(jsonString, "jsonString");
                return CollectionsKt___CollectionsKt.toSet(weblabConfigurationReader.read(jsonString));
            } catch (Exception e) {
                Log.error(WeblabProviderImpl.TAG, Intrinsics.stringPlus("Failed to init configuration with resourceId ", Integer.valueOf(resourceId)), e);
                return SetsKt__SetsKt.emptySet();
            }
        }

        public final String getDirectedId(AuthInfo authInfo) {
            if (authInfo == null) {
                Log.error(WeblabProviderImpl.TAG, "Unable to fetch directedID due to missing AuthInfo");
                return null;
            }
            try {
                return authInfo.find(AuthDataType.DIRECTED_ID);
            } catch (AuthenticationException e) {
                Log.error(WeblabProviderImpl.TAG, Intrinsics.stringPlus("Unable to fetch session ID from AuthInfo ", e.getMessage()));
                return null;
            } catch (Exception e2) {
                String str = WeblabProviderImpl.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("MAP token fetch failed with error ");
                sb.append((Object) e2.getMessage());
                sb.append(", cause: ");
                Throwable cause = e2.getCause();
                sb.append((Object) (cause == null ? null : cause.getMessage()));
                Log.error(str, sb.toString());
                return null;
            }
        }

        @JvmStatic
        public final WeblabProvider getInstance(Context context) {
            WeblabProviderImpl weblabProviderImpl;
            Intrinsics.checkNotNullParameter(context, "context");
            WeblabProviderImpl weblabProviderImpl2 = WeblabProviderImpl.instance;
            if (weblabProviderImpl2 != null) {
                return weblabProviderImpl2;
            }
            synchronized (this) {
                weblabProviderImpl = WeblabProviderImpl.instance;
                if (weblabProviderImpl == null) {
                    weblabProviderImpl = new WeblabProviderImpl(context, null);
                    WeblabProviderImpl.instance = weblabProviderImpl;
                }
            }
            return weblabProviderImpl;
        }
    }
}
