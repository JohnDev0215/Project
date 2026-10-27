package com.amazon.mp3.billing;

import android.app.Activity;
import android.content.Context;
import androidx.mediarouter.media.MediaRouter;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.billing.event.BillingEvent;
import com.amazon.mp3.billing.event.BillingFlexEventBuilder;
import com.amazon.mp3.billing.event.GoogleBillingClientEvent;
import com.amazon.mp3.billing.event.PurchaseEvaluationEvent;
import com.amazon.mp3.billing.event.PurchaseSubmissionEvent;
import com.amazon.mp3.platform.providers.DispatcherProvider;
import com.amazon.mp3.util.Log;
import com.amazon.music.metrics.MetricsHolder;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.PurchasesResponseListener;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.visualon.OSMPUtils.voOSType;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

/* JADX INFO: compiled from: BillingClientWrapper.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 M2\u00020\u0001:\u0001MB)\u0012\u0006\u0010!\u001a\u00020 \u0012\u0006\u0010$\u001a\u00020#\u0012\u0006\u0010'\u001a\u00020&\u0012\b\b\u0002\u0010J\u001a\u00020I¢\u0006\u0004\bK\u0010LJ\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0002J&\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007J\u0010\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0016J\b\u0010\u0011\u001a\u00020\u000fH\u0016J!\u0010\u0016\u001a\u00020\u000f2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0012H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u0013\u0010\u0017\u001a\u00020\u0002H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0018\u0010\u0019\u001a\u00020\u000f2\u0010\b\u0002\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0012J\u0006\u0010\u001a\u001a\u00020\u000fJ\u0006\u0010\u001b\u001a\u00020\u000fJ\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\b\u0010\b\u001a\u0004\u0018\u00010\u0007J\u001f\u0010\u001e\u001a\u0004\u0018\u00010\u001c2\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fR\u0014\u0010!\u001a\u00020 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"R\u0014\u0010$\u001a\u00020#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%R\u0014\u0010'\u001a\u00020&8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010(R\"\u0010*\u001a\u00020)8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001c\u00101\u001a\b\u0012\u0004\u0012\u00020\u001c008\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b1\u00102R\"\u00104\u001a\u0002038\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b4\u00105\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u0014\u0010;\u001a\u00020:8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b;\u0010<R\u0014\u0010>\u001a\u00020=8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b>\u0010?R\"\u0010@\u001a\u00020\u00028\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b@\u0010A\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER\u0016\u0010G\u001a\u00020F8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bG\u0010H\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006N"}, d2 = {"Lcom/amazon/mp3/billing/BillingClientWrapper;", "Lcom/android/billingclient/api/BillingClientStateListener;", "", "doesNotSupportSubscriptionsFeature", "isReady", "Landroid/app/Activity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "", "skuName", "orderInitToken", "purchaseHandleId", "Lcom/amazon/mp3/billing/event/BillingEvent;", "launchBillingFlow", "Lcom/android/billingclient/api/BillingResult;", "result", "", "onBillingSetupFinished", "onBillingServiceDisconnected", "Lkotlin/Function0;", "callback", "retryBillingServiceConnection$DigitalMusicAndroid3P_automotiveProdRelease", "(Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "retryBillingServiceConnection", "waitForClientReady", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "querySkusAsync", "startConnection", "queryPurchasesAsync", "Lcom/android/billingclient/api/SkuDetails;", "getSkuDetails", "getSkuDetailsSuspend", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Lcom/android/billingclient/api/PurchasesResponseListener;", "purchasesResponseListener", "Lcom/android/billingclient/api/PurchasesResponseListener;", "Lcom/android/billingclient/api/PurchasesUpdatedListener;", "purchasesUpdatedListener", "Lcom/android/billingclient/api/PurchasesUpdatedListener;", "Lcom/android/billingclient/api/BillingClient;", "billingClient", "Lcom/android/billingclient/api/BillingClient;", "getBillingClient$DigitalMusicAndroid3P_automotiveProdRelease", "()Lcom/android/billingclient/api/BillingClient;", "setBillingClient$DigitalMusicAndroid3P_automotiveProdRelease", "(Lcom/android/billingclient/api/BillingClient;)V", "", "currentSkuDetails", "Ljava/util/List;", "", "reconnectDuration", "J", "getReconnectDuration$DigitalMusicAndroid3P_automotiveProdRelease", "()J", "setReconnectDuration$DigitalMusicAndroid3P_automotiveProdRelease", "(J)V", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "coroutineExceptionHandler", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "Lkotlinx/coroutines/CoroutineScope;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "isSubsBillingFeatureSupported", "Z", "isSubsBillingFeatureSupported$DigitalMusicAndroid3P_automotiveProdRelease", "()Z", "setSubsBillingFeatureSupported$DigitalMusicAndroid3P_automotiveProdRelease", "(Z)V", "Lcom/amazon/mp3/billing/ObfuscatedAccountIdEncryptionUtil;", "obfuscatedAccountIdEncryptionUtil", "Lcom/amazon/mp3/billing/ObfuscatedAccountIdEncryptionUtil;", "Lcom/amazon/mp3/platform/providers/DispatcherProvider;", "dispatchers", "<init>", "(Landroid/content/Context;Lcom/android/billingclient/api/PurchasesResponseListener;Lcom/android/billingclient/api/PurchasesUpdatedListener;Lcom/amazon/mp3/platform/providers/DispatcherProvider;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BillingClientWrapper implements BillingClientStateListener {
    private static volatile BillingClient BILLING_CLIENT_INSTANCE;
    private static final long BILLING_SERVICE_WAIT_DURATION_SECONDS;
    private static final List<String> LIST_OF_SKUS;
    private static final long RECONNECT_INITIAL_DURATION_MILLIS;
    private static final long RECONNECT_MAX_DURATION_MILLIS;
    private BillingClient billingClient;
    private final Context context;
    private final CoroutineExceptionHandler coroutineExceptionHandler;
    private final CoroutineScope coroutineScope;
    private List<? extends SkuDetails> currentSkuDetails;
    private boolean isSubsBillingFeatureSupported;
    private ObfuscatedAccountIdEncryptionUtil obfuscatedAccountIdEncryptionUtil;
    private final PurchasesResponseListener purchasesResponseListener;
    private final PurchasesUpdatedListener purchasesUpdatedListener;
    private long reconnectDuration;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = Reflection.getOrCreateKotlinClass(BillingClientWrapper.class).getSimpleName();

    /* JADX INFO: renamed from: com.amazon.mp3.billing.BillingClientWrapper$getSkuDetailsSuspend$1, reason: invalid class name */
    /* JADX INFO: compiled from: BillingClientWrapper.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.billing.BillingClientWrapper", f = "BillingClientWrapper.kt", i = {0}, l = {392}, m = "getSkuDetailsSuspend", n = {"this_$iv"}, s = {"L$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return BillingClientWrapper.this.getSkuDetailsSuspend(null, this);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.billing.BillingClientWrapper$waitForClientReady$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BillingClientWrapper.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.billing.BillingClientWrapper", f = "BillingClientWrapper.kt", i = {0}, l = {voOSType.VOOSMP_PID_DISABLE_FORCE_OPENGL}, m = "waitForClientReady", n = {"this"}, s = {"L$0"})
    static final class C03061 extends ContinuationImpl {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C03061(Continuation<? super C03061> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return BillingClientWrapper.this.waitForClientReady(this);
        }
    }

    public BillingClientWrapper(Context context, PurchasesResponseListener purchasesResponseListener, PurchasesUpdatedListener purchasesUpdatedListener, DispatcherProvider dispatchers) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(purchasesResponseListener, "purchasesResponseListener");
        Intrinsics.checkNotNullParameter(purchasesUpdatedListener, "purchasesUpdatedListener");
        Intrinsics.checkNotNullParameter(dispatchers, "dispatchers");
        this.context = context;
        this.purchasesResponseListener = purchasesResponseListener;
        this.purchasesUpdatedListener = purchasesUpdatedListener;
        this.billingClient = INSTANCE.getBillingClientInstance(context, purchasesUpdatedListener);
        this.currentSkuDetails = CollectionsKt__CollectionsKt.emptyList();
        this.reconnectDuration = RECONNECT_INITIAL_DURATION_MILLIS;
        BillingClientWrapper$special$$inlined$CoroutineExceptionHandler$1 billingClientWrapper$special$$inlined$CoroutineExceptionHandler$1 = new BillingClientWrapper$special$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.INSTANCE);
        this.coroutineExceptionHandler = billingClientWrapper$special$$inlined$CoroutineExceptionHandler$1;
        this.coroutineScope = CoroutineScopeKt.CoroutineScope(dispatchers.io().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null)).plus(billingClientWrapper$special$$inlined$CoroutineExceptionHandler$1));
        this.isSubsBillingFeatureSupported = true;
        this.obfuscatedAccountIdEncryptionUtil = new ObfuscatedAccountIdEncryptionUtil();
    }

    /* JADX INFO: compiled from: BillingClientWrapper.kt */
    @Metadata(bv = {}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\f\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b(\u0010)J\f\u0010\u0004\u001a\u00020\u0003*\u00020\u0002H\u0002J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0002J\u000e\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0002J\u0010\u0010\n\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\u0003J\"\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002J\u0018\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0011H\u0007R$\u0010\u0015\u001a\u0004\u0018\u00010\u00138\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00030\u001e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\u001dR\u0014\u0010\"\u001a\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010\u001dR\u0014\u0010#\u001a\u00020\u00038\u0006X\u0086T¢\u0006\u0006\n\u0004\b#\u0010$R\u0014\u0010%\u001a\u00020\u00038\u0006X\u0086T¢\u0006\u0006\n\u0004\b%\u0010$R\u0014\u0010&\u001a\u00020\u00038\u0006X\u0086T¢\u0006\u0006\n\u0004\b&\u0010$R\u0016\u0010'\u001a\u0004\u0018\u00010\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010$¨\u0006*"}, d2 = {"Lcom/amazon/mp3/billing/BillingClientWrapper$Companion;", "", "Lcom/android/billingclient/api/BillingResult;", "", "toDebugString", "result", "", "isBillingResultOk", "isBillingResultUserCanceled", "skuName", "isSkuValid", "Lcom/amazon/mp3/billing/event/BillingEvent;", "billingEvent", "loggingContext", "emitMetrics", "Landroid/content/Context;", "context", "Lcom/android/billingclient/api/PurchasesUpdatedListener;", "purchasesUpdatedListener", "Lcom/android/billingclient/api/BillingClient;", "getBillingClientInstance", "BILLING_CLIENT_INSTANCE", "Lcom/android/billingclient/api/BillingClient;", "getBILLING_CLIENT_INSTANCE", "()Lcom/android/billingclient/api/BillingClient;", "setBILLING_CLIENT_INSTANCE", "(Lcom/android/billingclient/api/BillingClient;)V", "", "BILLING_SERVICE_WAIT_DURATION_SECONDS", "J", "", "LIST_OF_SKUS", "Ljava/util/List;", "RECONNECT_INITIAL_DURATION_MILLIS", "RECONNECT_MAX_DURATION_MILLIS", "SKU_UNLIMITED_ALLDEVICES_MONTHLY", "Ljava/lang/String;", "SKU_UNLIMITED_ALLDEVICES_MONTHLY_PROMO", "SKU_UNLIMITED_ALLDEVICES_MONTHLY_TRIAL", "TAG", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isBillingResultOk(BillingResult result) {
            Intrinsics.checkNotNullParameter(result, "result");
            return result.getResponseCode() == 0;
        }

        public final boolean isBillingResultUserCanceled(BillingResult result) {
            Intrinsics.checkNotNullParameter(result, "result");
            return result.getResponseCode() == 1;
        }

        public final boolean isSkuValid(String skuName) {
            return CollectionsKt___CollectionsKt.contains(BillingClientWrapper.LIST_OF_SKUS, skuName);
        }

        private final String toDebugString(BillingResult billingResult) {
            String strTranslateBillingResponseCode = BillingFlexEventBuilder.INSTANCE.translateBillingResponseCode(Integer.valueOf(billingResult.getResponseCode()));
            String debugMessage = billingResult.getDebugMessage();
            Intrinsics.checkNotNullExpressionValue(debugMessage, "this.debugMessage");
            if (debugMessage.length() > 0) {
                return "code=" + strTranslateBillingResponseCode + "  message=" + billingResult.getDebugMessage();
            }
            return Intrinsics.stringPlus("code=", strTranslateBillingResponseCode);
        }

        public static /* synthetic */ BillingEvent emitMetrics$default(Companion companion, BillingEvent billingEvent, String str, BillingResult billingResult, int i, Object obj) {
            if ((i & 4) != 0) {
                billingResult = null;
            }
            return companion.emitMetrics(billingEvent, str, billingResult);
        }

        public final BillingEvent emitMetrics(BillingEvent billingEvent, String loggingContext, BillingResult result) {
            Intrinsics.checkNotNullParameter(billingEvent, "billingEvent");
            Intrinsics.checkNotNullParameter(loggingContext, "loggingContext");
            String debugString = result == null ? null : toDebugString(result);
            GoogleBillingClientEvent googleBillingClientEvent = GoogleBillingClientEvent.BILLING_SETUP_SUCCESS;
            if (((billingEvent == googleBillingClientEvent || billingEvent == GoogleBillingClientEvent.LAUNCH_BILLING_FLOW_SUCCESS) || billingEvent == GoogleBillingClientEvent.QUERY_SKU_SUCCESS) || billingEvent == GoogleBillingClientEvent.QUERY_PURCHASES_SUCCESS) {
                Log.info(BillingClientWrapper.TAG, loggingContext + " success, type=" + billingEvent.getValue() + ", name=" + billingEvent.getFlexEventName() + ", " + ((Object) debugString));
            } else {
                if ((billingEvent == PurchaseEvaluationEvent.EVALUATION_SUCCESS || billingEvent == PurchaseSubmissionEvent.SUBMISSION_SUCCESS) || billingEvent == PurchaseSubmissionEvent.UPGRADE_SUCCESS) {
                    Log.info(BillingClientWrapper.TAG, Intrinsics.stringPlus(loggingContext, " success"));
                } else {
                    Log.error(BillingClientWrapper.TAG, loggingContext + " failed, type=" + billingEvent.getValue() + ", name=" + billingEvent.getFlexEventName() + ", " + ((Object) debugString));
                }
            }
            if (billingEvent == googleBillingClientEvent || billingEvent == GoogleBillingClientEvent.QUERY_PURCHASES_SUCCESS) {
                return billingEvent;
            }
            MetricsHolder.getManager().handleEvent(new BillingFlexEventBuilder(billingEvent).withResultCode(result == null ? null : Integer.valueOf(result.getResponseCode())).withResultDebugMessage(result != null ? result.getDebugMessage() : null).withLoggingContext(loggingContext).build());
            return billingEvent;
        }

        public final BillingClient getBILLING_CLIENT_INSTANCE() {
            return BillingClientWrapper.BILLING_CLIENT_INSTANCE;
        }

        public final void setBILLING_CLIENT_INSTANCE(BillingClient billingClient) {
            BillingClientWrapper.BILLING_CLIENT_INSTANCE = billingClient;
        }

        @JvmStatic
        public final BillingClient getBillingClientInstance(Context context, PurchasesUpdatedListener purchasesUpdatedListener) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(purchasesUpdatedListener, "purchasesUpdatedListener");
            BillingClient billing_client_instance = getBILLING_CLIENT_INSTANCE();
            if (billing_client_instance == null) {
                synchronized (this) {
                    Companion companion = BillingClientWrapper.INSTANCE;
                    BillingClient billing_client_instance2 = companion.getBILLING_CLIENT_INSTANCE();
                    if (billing_client_instance2 == null) {
                        BillingClient billingClientBuild = BillingClient.newBuilder(context).setListener(purchasesUpdatedListener).enablePendingPurchases().build();
                        companion.setBILLING_CLIENT_INSTANCE(billingClientBuild);
                        Intrinsics.checkNotNullExpressionValue(billingClientBuild, "newBuilder(context)\n    …NG_CLIENT_INSTANCE = it }");
                        billing_client_instance = billingClientBuild;
                    } else {
                        billing_client_instance = billing_client_instance2;
                    }
                }
            }
            return billing_client_instance;
        }
    }

    static {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        RECONNECT_INITIAL_DURATION_MILLIS = timeUnit.toMillis(1L);
        RECONNECT_MAX_DURATION_MILLIS = TimeUnit.MINUTES.toMillis(15L);
        BILLING_SERVICE_WAIT_DURATION_SECONDS = timeUnit.toMillis(2L);
        LIST_OF_SKUS = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"com.amazon.music.android.iab.unlimited.alldevices.monthly.paid", "com.amazon.music.android.iab.unlimited.alldevices.monthly.freetrial", "com.amazon.music.android.iab.unlimited.alldevices.monthly.primedaypromo"});
    }

    /* JADX INFO: renamed from: getReconnectDuration$DigitalMusicAndroid3P_automotiveProdRelease, reason: from getter */
    public final long getReconnectDuration() {
        return this.reconnectDuration;
    }

    public final void setReconnectDuration$DigitalMusicAndroid3P_automotiveProdRelease(long j) {
        this.reconnectDuration = j;
    }

    /* JADX INFO: renamed from: isSubsBillingFeatureSupported$DigitalMusicAndroid3P_automotiveProdRelease, reason: from getter */
    public final boolean getIsSubsBillingFeatureSupported() {
        return this.isSubsBillingFeatureSupported;
    }

    public final BillingEvent launchBillingFlow(Activity activity, String skuName, String orderInitToken, String purchaseHandleId) {
        GoogleBillingClientEvent googleBillingClientEvent;
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(skuName, "skuName");
        Intrinsics.checkNotNullParameter(orderInitToken, "orderInitToken");
        Intrinsics.checkNotNullParameter(purchaseHandleId, "purchaseHandleId");
        if (!this.isSubsBillingFeatureSupported) {
            return Companion.emitMetrics$default(INSTANCE, GoogleBillingClientEvent.LAUNCH_BILLING_FLOW_SUBS_BILLING_NOT_SUPPORTED, "launchBillingFlow", null, 4, null);
        }
        if (!isReady()) {
            return Companion.emitMetrics$default(INSTANCE, GoogleBillingClientEvent.LAUNCH_BILLING_FLOW_CLIENT_NOT_READY, "launchBillingFlow", null, 4, null);
        }
        SkuDetails skuDetails = getSkuDetails(skuName);
        if (skuDetails == null) {
            Log.error(TAG, Intrinsics.stringPlus("sku not found, sku=", skuName));
            return Companion.emitMetrics$default(INSTANCE, GoogleBillingClientEvent.LAUNCH_BILLING_FLOW_INVALID_SKU, "launchBillingFlow", null, 4, null);
        }
        BillingFlowParams billingFlowParamsBuild = BillingFlowParams.newBuilder().setSkuDetails(skuDetails).setObfuscatedAccountId(this.obfuscatedAccountIdEncryptionUtil.generateObfuscatedAccountId(purchaseHandleId)).setObfuscatedProfileId(orderInitToken).build();
        Intrinsics.checkNotNullExpressionValue(billingFlowParamsBuild, "newBuilder()\n           …\n                .build()");
        BillingResult billingResultLaunchBillingFlow = this.billingClient.launchBillingFlow(activity, billingFlowParamsBuild);
        Intrinsics.checkNotNullExpressionValue(billingResultLaunchBillingFlow, "billingClient.launchBillingFlow(activity, params)");
        Companion companion = INSTANCE;
        if (companion.isBillingResultOk(billingResultLaunchBillingFlow)) {
            googleBillingClientEvent = GoogleBillingClientEvent.LAUNCH_BILLING_FLOW_SUCCESS;
        } else {
            googleBillingClientEvent = GoogleBillingClientEvent.LAUNCH_BILLING_FLOW_OTHER_ERRORS;
        }
        return companion.emitMetrics(googleBillingClientEvent, "launchBillingFlow", billingResultLaunchBillingFlow);
    }

    @Override // com.android.billingclient.api.BillingClientStateListener
    public void onBillingSetupFinished(BillingResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        int responseCode = result.getResponseCode();
        if (responseCode != 0) {
            if (responseCode == 3) {
                Log.error(TAG, Intrinsics.stringPlus("onBillingSetupFinished", " failed due to billing unavailable, disable retry"));
                INSTANCE.emitMetrics(GoogleBillingClientEvent.BILLING_SETUP_BILLING_UNAVAILABLE, "onBillingSetupFinished", result);
                this.isSubsBillingFeatureSupported = false;
                return;
            } else {
                INSTANCE.emitMetrics(GoogleBillingClientEvent.BILLING_SETUP_OTHER_ERRORS, "onBillingSetupFinished", result);
                BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new C03051(null), 3, null);
                return;
            }
        }
        String str = TAG;
        Log.info(str, Intrinsics.stringPlus("onBillingSetupFinished", " completed"));
        this.reconnectDuration = RECONNECT_INITIAL_DURATION_MILLIS;
        if (doesNotSupportSubscriptionsFeature()) {
            Log.error(str, Intrinsics.stringPlus("onBillingSetupFinished", " completed, but Subscription feature is not supported, disable retry"));
            INSTANCE.emitMetrics(GoogleBillingClientEvent.BILLING_SETUP_BILLING_AVAILABLE_BUT_SUBS_NOT_SUPPORTED, "onBillingSetupFinished", result);
            this.isSubsBillingFeatureSupported = false;
        } else {
            Log.debug(str, Intrinsics.stringPlus("onBillingSetupFinished", " success"));
            INSTANCE.emitMetrics(GoogleBillingClientEvent.BILLING_SETUP_SUCCESS, "onBillingSetupFinished", result);
            queryPurchasesAsync();
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.billing.BillingClientWrapper$onBillingSetupFinished$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BillingClientWrapper.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.billing.BillingClientWrapper$onBillingSetupFinished$1", f = "BillingClientWrapper.kt", i = {}, l = {245}, m = "invokeSuspend", n = {}, s = {})
    static final class C03051 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C03051(Continuation<? super C03051> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return BillingClientWrapper.this.new C03051(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03051) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.amazon.mp3.billing.BillingClientWrapper$onBillingSetupFinished$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: BillingClientWrapper.kt */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        /* synthetic */ class C00711 extends FunctionReferenceImpl implements Function0<Unit> {
            C00711(Object obj) {
                super(0, obj, BillingClientWrapper.class, "startConnection", "startConnection()V", 0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                ((BillingClientWrapper) this.receiver).startConnection();
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                BillingClientWrapper billingClientWrapper = BillingClientWrapper.this;
                C00711 c00711 = new C00711(BillingClientWrapper.this);
                this.label = 1;
                if (billingClientWrapper.retryBillingServiceConnection$DigitalMusicAndroid3P_automotiveProdRelease(c00711, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    private final boolean doesNotSupportSubscriptionsFeature() {
        return -2 == this.billingClient.isFeatureSupported("subscriptions").getResponseCode();
    }

    /* JADX INFO: renamed from: com.amazon.mp3.billing.BillingClientWrapper$onBillingServiceDisconnected$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BillingClientWrapper.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.billing.BillingClientWrapper$onBillingServiceDisconnected$1", f = "BillingClientWrapper.kt", i = {}, l = {MediaRouter.GlobalMediaRouter.CallbackHandler.MSG_ROUTE_VOLUME_CHANGED}, m = "invokeSuspend", n = {}, s = {})
    static final class C03041 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C03041(Continuation<? super C03041> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return BillingClientWrapper.this.new C03041(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03041) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.amazon.mp3.billing.BillingClientWrapper$onBillingServiceDisconnected$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: BillingClientWrapper.kt */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        /* synthetic */ class C00701 extends FunctionReferenceImpl implements Function0<Unit> {
            C00701(Object obj) {
                super(0, obj, BillingClientWrapper.class, "startConnection", "startConnection()V", 0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                ((BillingClientWrapper) this.receiver).startConnection();
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                BillingClientWrapper billingClientWrapper = BillingClientWrapper.this;
                C00701 c00701 = new C00701(BillingClientWrapper.this);
                this.label = 1;
                if (billingClientWrapper.retryBillingServiceConnection$DigitalMusicAndroid3P_automotiveProdRelease(c00701, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    @Override // com.android.billingclient.api.BillingClientStateListener
    public void onBillingServiceDisconnected() {
        Log.error(TAG, "onBillingServiceDisconnected");
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new C03041(null), 3, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object retryBillingServiceConnection$DigitalMusicAndroid3P_automotiveProdRelease(kotlin.jvm.functions.Function0<kotlin.Unit> r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof com.amazon.mp3.billing.BillingClientWrapper$retryBillingServiceConnection$1
            if (r0 == 0) goto L13
            r0 = r9
            com.amazon.mp3.billing.BillingClientWrapper$retryBillingServiceConnection$1 r0 = (com.amazon.mp3.billing.BillingClientWrapper$retryBillingServiceConnection$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.billing.BillingClientWrapper$retryBillingServiceConnection$1 r0 = new com.amazon.mp3.billing.BillingClientWrapper$retryBillingServiceConnection$1
            r0.<init>(r7, r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3b
            if (r2 != r3) goto L33
            long r1 = r0.J$0
            java.lang.Object r8 = r0.L$1
            kotlin.jvm.functions.Function0 r8 = (kotlin.jvm.functions.Function0) r8
            java.lang.Object r0 = r0.L$0
            com.amazon.mp3.billing.BillingClientWrapper r0 = (com.amazon.mp3.billing.BillingClientWrapper) r0
            kotlin.ResultKt.throwOnFailure(r9)
            goto L74
        L33:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L3b:
            kotlin.ResultKt.throwOnFailure(r9)
            boolean r9 = r7.getIsSubsBillingFeatureSupported()
            if (r9 != 0) goto L4f
            java.lang.String r8 = com.amazon.mp3.billing.BillingClientWrapper.TAG
            java.lang.String r9 = "skip retry, subs billing feature is not supported"
            com.amazon.mp3.util.Log.info(r8, r9)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L4f:
            long r4 = r7.getReconnectDuration()
            java.lang.String r9 = com.amazon.mp3.billing.BillingClientWrapper.TAG
            java.lang.Long r2 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r4)
            java.lang.String r6 = "retryBillingServiceConnection duration="
            java.lang.String r2 = kotlin.jvm.internal.Intrinsics.stringPlus(r6, r2)
            com.amazon.mp3.util.Log.info(r9, r2)
            r0.L$0 = r7
            r0.L$1 = r8
            r0.J$0 = r4
            r0.label = r3
            java.lang.Object r9 = kotlinx.coroutines.DelayKt.delay(r4, r0)
            if (r9 != r1) goto L72
            return r1
        L72:
            r0 = r7
            r1 = r4
        L74:
            r8.invoke()
            r8 = 2
            long r8 = (long) r8
            long r1 = r1 * r8
            long r8 = com.amazon.mp3.billing.BillingClientWrapper.RECONNECT_MAX_DURATION_MILLIS
            long r8 = java.lang.Math.min(r1, r8)
            r0.setReconnectDuration$DigitalMusicAndroid3P_automotiveProdRelease(r8)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.billing.BillingClientWrapper.retryBillingServiceConnection$DigitalMusicAndroid3P_automotiveProdRelease(kotlin.jvm.functions.Function0, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final boolean isReady() {
        return this.billingClient.isReady();
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x005b -> B:12:0x002e). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object waitForClientReady(kotlin.coroutines.Continuation<? super java.lang.Boolean> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.amazon.mp3.billing.BillingClientWrapper.C03061
            if (r0 == 0) goto L13
            r0 = r8
            com.amazon.mp3.billing.BillingClientWrapper$waitForClientReady$1 r0 = (com.amazon.mp3.billing.BillingClientWrapper.C03061) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.billing.BillingClientWrapper$waitForClientReady$1 r0 = new com.amazon.mp3.billing.BillingClientWrapper$waitForClientReady$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L38
            if (r2 != r3) goto L30
            int r2 = r0.I$0
            java.lang.Object r4 = r0.L$0
            com.amazon.mp3.billing.BillingClientWrapper r4 = (com.amazon.mp3.billing.BillingClientWrapper) r4
            kotlin.ResultKt.throwOnFailure(r8)
        L2e:
            r8 = r2
            goto L3d
        L30:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L38:
            kotlin.ResultKt.throwOnFailure(r8)
            r4 = r7
            r8 = r3
        L3d:
            r2 = 5
            if (r8 >= r2) goto L6a
            int r2 = r8 + 1
            boolean r8 = r4.isReady()
            if (r8 != 0) goto L5e
            java.lang.String r8 = com.amazon.mp3.billing.BillingClientWrapper.TAG
            java.lang.String r5 = "BillingClient not ready yet. Retry"
            com.amazon.mp3.util.Log.info(r8, r5)
            long r5 = com.amazon.mp3.billing.BillingClientWrapper.BILLING_SERVICE_WAIT_DURATION_SECONDS
            r0.L$0 = r4
            r0.I$0 = r2
            r0.label = r3
            java.lang.Object r8 = kotlinx.coroutines.DelayKt.delay(r5, r0)
            if (r8 != r1) goto L2e
            return r1
        L5e:
            java.lang.String r8 = com.amazon.mp3.billing.BillingClientWrapper.TAG
            java.lang.String r0 = "BillingClient ready"
            com.amazon.mp3.util.Log.info(r8, r0)
            java.lang.Boolean r8 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r8
        L6a:
            java.lang.String r8 = com.amazon.mp3.billing.BillingClientWrapper.TAG
            java.lang.String r0 = "BillingClient cannot be connected"
            com.amazon.mp3.util.Log.error(r8, r0)
            r8 = 0
            java.lang.Boolean r8 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.billing.BillingClientWrapper.waitForClientReady(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void querySkusAsync(final Function0<Unit> callback) {
        final String str = "querySkusAsync";
        if (!isReady()) {
            Companion.emitMetrics$default(INSTANCE, GoogleBillingClientEvent.QUERY_SKU_CLIENT_NOT_READY, "querySkusAsync", null, 4, null);
            return;
        }
        if (!this.isSubsBillingFeatureSupported) {
            Companion.emitMetrics$default(INSTANCE, GoogleBillingClientEvent.QUERY_SKU_SUBS_BILLING_NOT_SUPPORTED, "querySkusAsync", null, 4, null);
            return;
        }
        SkuDetailsParams skuDetailsParamsBuild = SkuDetailsParams.newBuilder().setType("subs").setSkusList(LIST_OF_SKUS).build();
        Intrinsics.checkNotNullExpressionValue(skuDetailsParamsBuild, "newBuilder()\n           …KUS)\n            .build()");
        this.billingClient.querySkuDetailsAsync(skuDetailsParamsBuild, new SkuDetailsResponseListener() { // from class: com.amazon.mp3.billing.BillingClientWrapper$$ExternalSyntheticLambda0
            @Override // com.android.billingclient.api.SkuDetailsResponseListener
            public final void onSkuDetailsResponse(BillingResult billingResult, List list) {
                BillingClientWrapper.m410querySkusAsync$lambda1(str, this, callback, billingResult, list);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: querySkusAsync$lambda-1, reason: not valid java name */
    public static final void m410querySkusAsync$lambda1(String loggingContext, BillingClientWrapper this$0, Function0 function0, BillingResult billingResult, List skuDetails) {
        Intrinsics.checkNotNullParameter(loggingContext, "$loggingContext");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(billingResult, "billingResult");
        String str = TAG;
        Log.info(str, loggingContext + " successful " + skuDetails);
        Companion companion = INSTANCE;
        if (companion.isBillingResultOk(billingResult)) {
            if (!(skuDetails == null || skuDetails.isEmpty())) {
                if (skuDetails.size() != LIST_OF_SKUS.size()) {
                    Log.warning(str, Intrinsics.stringPlus("mismatched sku size in response ", skuDetails));
                }
                Intrinsics.checkNotNullExpressionValue(skuDetails, "skuDetails");
                this$0.currentSkuDetails = skuDetails;
                Companion.emitMetrics$default(companion, GoogleBillingClientEvent.QUERY_SKU_SUCCESS, loggingContext, null, 4, null);
                if (function0 == null) {
                    return;
                }
                function0.invoke();
                return;
            }
            companion.emitMetrics(GoogleBillingClientEvent.QUERY_SKU_NO_PRODUCTS_RETURNED, loggingContext, billingResult);
            return;
        }
        companion.emitMetrics(GoogleBillingClientEvent.QUERY_SKU_OTHER_ERRORS, loggingContext, billingResult);
    }

    public final void startConnection() {
        if (isReady()) {
            return;
        }
        Log.debug(TAG, "startConnection");
        this.billingClient.startConnection(this);
    }

    public final void queryPurchasesAsync() {
        if (!isReady()) {
            Companion.emitMetrics$default(INSTANCE, GoogleBillingClientEvent.QUERY_PURCHASES_CLIENT_NOT_READY, "queryPurchasesAsync", null, 4, null);
        } else if (!this.isSubsBillingFeatureSupported) {
            Companion.emitMetrics$default(INSTANCE, GoogleBillingClientEvent.QUERY_PURCHASES_SUBS_BILLING_NOT_SUPPORTED, "queryPurchasesAsync", null, 4, null);
        } else {
            this.billingClient.queryPurchasesAsync("subs", this.purchasesResponseListener);
        }
    }

    public final SkuDetails getSkuDetails(String skuName) {
        Object next;
        Iterator<T> it = this.currentSkuDetails.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (Intrinsics.areEqual(((SkuDetails) next).getSku(), skuName)) {
                break;
            }
        }
        return (SkuDetails) next;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getSkuDetailsSuspend(java.lang.String r16, kotlin.coroutines.Continuation<? super com.android.billingclient.api.SkuDetails> r17) {
        /*
            r15 = this;
            r1 = r15
            r0 = r17
            boolean r2 = r0 instanceof com.amazon.mp3.billing.BillingClientWrapper.AnonymousClass1
            if (r2 == 0) goto L16
            r2 = r0
            com.amazon.mp3.billing.BillingClientWrapper$getSkuDetailsSuspend$1 r2 = (com.amazon.mp3.billing.BillingClientWrapper.AnonymousClass1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L16
            int r3 = r3 - r4
            r2.label = r3
            goto L1b
        L16:
            com.amazon.mp3.billing.BillingClientWrapper$getSkuDetailsSuspend$1 r2 = new com.amazon.mp3.billing.BillingClientWrapper$getSkuDetailsSuspend$1
            r2.<init>(r0)
        L1b:
            java.lang.Object r0 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 0
            r6 = 1
            if (r4 == 0) goto L3b
            if (r4 != r6) goto L33
            java.lang.Object r2 = r2.L$0
            com.amazon.mp3.platform.providers.Coroutines r2 = (com.amazon.mp3.platform.providers.Coroutines) r2
            kotlin.ResultKt.throwOnFailure(r0)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L31
            goto L54
        L31:
            r0 = move-exception
            goto L58
        L33:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L3b:
            kotlin.ResultKt.throwOnFailure(r0)
            com.amazon.mp3.platform.providers.Coroutines r4 = com.amazon.mp3.platform.providers.Coroutines.INSTANCE
            r7 = 15000(0x3a98, double:7.411E-320)
            com.amazon.mp3.billing.BillingClientWrapper$getSkuDetailsSuspend$$inlined$suspendCoroutineWithTimeout$default$1 r0 = new com.amazon.mp3.billing.BillingClientWrapper$getSkuDetailsSuspend$$inlined$suspendCoroutineWithTimeout$default$1     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L56
            r9 = r16
            r0.<init>(r5, r15, r9)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L56
            r2.L$0 = r4     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L56
            r2.label = r6     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L56
            java.lang.Object r0 = kotlinx.coroutines.TimeoutKt.withTimeout(r7, r0, r2)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L56
            if (r0 != r3) goto L54
            return r3
        L54:
            r5 = r0
            goto L76
        L56:
            r0 = move-exception
            r2 = r4
        L58:
            java.lang.String r2 = r2.getTAG()
            java.lang.StackTraceElement[] r6 = r0.getStackTrace()
            java.lang.String r0 = "e.stackTrace"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r0)
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 62
            r14 = 0
            java.lang.String r7 = "\n"
            java.lang.String r0 = kotlin.collections.ArraysKt.joinToString$default(r6, r7, r8, r9, r10, r11, r12, r13, r14)
            com.amazon.mp3.util.Log.error(r2, r0)
        L76:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.billing.BillingClientWrapper.getSkuDetailsSuspend(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
