package com.amazon.mp3.billing;

import UpsellInterface.v1_0.UpsellType;
import android.app.Activity;
import android.content.Context;
import androidx.annotation.WorkerThread;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import com.amazon.hermes.CoralError;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.billing.BillingClientWrapper;
import com.amazon.mp3.billing.event.BillingEvent;
import com.amazon.mp3.billing.event.BillingFlexEventBuilder;
import com.amazon.mp3.billing.event.GoogleBillingClientEvent;
import com.amazon.mp3.billing.event.PurchaseSubmissionEvent;
import com.amazon.mp3.platform.providers.DefaultDispatcherProvider;
import com.amazon.mp3.platform.providers.DispatcherProvider;
import com.amazon.mp3.platform.providers.SessionIdProviderImpl;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.authentication.AuthDataType;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.stratus.PaymentInfo;
import com.amazon.stratus.PaymentsPreferenceKey;
import com.android.billingclient.api.AccountIdentifiers;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesResponseListener;
import com.android.billingclient.api.PurchasesUpdatedListener;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: BillingClientManager.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001a\u0018\u0000 ]2\u00020\u00012\u00020\u00022\u00020\u0003:\u0004^]_`B#\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010;\u001a\u00020:\u0012\b\b\u0002\u0010>\u001a\u00020=¢\u0006\u0004\b[\u0010\\J5\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\u0006H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fJ\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u00102\n\u0010\u000f\u001a\u00060\rj\u0002`\u000eH\u0002J\b\u0010\u0013\u001a\u00020\u0012H\u0003J\b\u0010\u0014\u001a\u00020\u0012H\u0007J\u0013\u0010\u0016\u001a\u00020\u0015H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017J \u0010\u001d\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u00182\u000e\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001aH\u0016J'\u0010!\u001a\u00020\u00122\u000e\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001a2\u0006\u0010\u001e\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\u001f\u0010 J\u001e\u0010\"\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u00182\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0016J3\u0010%\u001a\b\u0012\u0004\u0012\u00020$0#2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\u0006H\u0086@ø\u0001\u0000¢\u0006\u0004\b%\u0010&J\u000e\u0010(\u001a\u00020\u00122\u0006\u0010'\u001a\u00020\u001bJ\u001e\u0010,\u001a\u00020\u00122\u0006\u0010*\u001a\u00020)2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010+\u001a\u00020$J\u0017\u0010/\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0000¢\u0006\u0004\b-\u0010.J\u001f\u00105\u001a\u0002022\u0006\u0010'\u001a\u00020\u001b2\u0006\u00101\u001a\u000200H\u0000¢\u0006\u0004\b3\u00104J\u0017\u00108\u001a\u0002002\u0006\u0010\u0005\u001a\u00020\u0004H\u0000¢\u0006\u0004\b6\u00107R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u00109R\u0014\u0010;\u001a\u00020:8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b;\u0010<R\u0014\u0010>\u001a\u00020=8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b>\u0010?R\u0016\u0010A\u001a\u00020@8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bA\u0010BR \u0010E\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0D0C8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bE\u0010FR#\u0010H\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0D0G8\u0006¢\u0006\f\n\u0004\bH\u0010I\u001a\u0004\bJ\u0010KR(\u0010L\u001a\b\u0012\u0004\u0012\u00020\u001b0D8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bL\u0010M\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR(\u0010R\u001a\b\u0012\u0004\u0012\u0002020C8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bR\u0010F\u001a\u0004\bS\u0010T\"\u0004\bU\u0010VR\u001d\u0010W\u001a\b\u0012\u0004\u0012\u0002020G8\u0006¢\u0006\f\n\u0004\bW\u0010I\u001a\u0004\bX\u0010KR\u0016\u0010Y\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bY\u0010Z\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006a"}, d2 = {"Lcom/amazon/mp3/billing/BillingClientManager;", "Lcom/android/billingclient/api/PurchasesUpdatedListener;", "Lcom/android/billingclient/api/PurchasesResponseListener;", "Landroidx/lifecycle/LifecycleObserver;", "Landroid/content/Context;", "context", "", "skuName", ParserUtil.SCHEDULE_ID_QUERY_PARAM_NAME, "associatesTag", "Lcom/amazon/mp3/billing/BillingClientManager$BillingEvaluateResponse;", "evaluateSubscriptionCreationHelper", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "e", "", "transformCoralError", "", "onCustomerUpgrade", "onCreate", "", "waitForBillingServiceReady", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/android/billingclient/api/BillingResult;", "billingResult", "", "Lcom/android/billingclient/api/Purchase;", "purchasesList", "onPurchasesUpdated", "loggingContext", "submitUnacknowledgedPurchases$DigitalMusicAndroid3P_automotiveProdRelease", "(Ljava/util/List;Ljava/lang/String;)V", "submitUnacknowledgedPurchases", "onQueryPurchasesResponse", "Lcom/amazon/music/platform/Outcome;", "Lcom/amazon/mp3/billing/BillingClientManager$OrderPurchaseToken;", "evaluateSubscriptionCreation", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "purchase", "submitSubscriptionCreation", "Landroid/app/Activity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "orderPurchaseToken", "launchBillingFlow", "getSessionId$DigitalMusicAndroid3P_automotiveProdRelease", "(Landroid/content/Context;)Ljava/lang/String;", "getSessionId", "Lcom/amazon/stratus/PaymentInfo;", "paymentInfo", "Lcom/amazon/mp3/billing/event/BillingEvent;", "submitSubscriptionCreationHelper$DigitalMusicAndroid3P_automotiveProdRelease", "(Lcom/android/billingclient/api/Purchase;Lcom/amazon/stratus/PaymentInfo;)Lcom/amazon/mp3/billing/event/BillingEvent;", "submitSubscriptionCreationHelper", "getPaymentInfo$DigitalMusicAndroid3P_automotiveProdRelease", "(Landroid/content/Context;)Lcom/amazon/stratus/PaymentInfo;", "getPaymentInfo", "Landroid/content/Context;", "Lcom/amazon/mp3/billing/SubscriptionManagerProvider;", "subscriptionManagerProvider", "Lcom/amazon/mp3/billing/SubscriptionManagerProvider;", "Lcom/amazon/mp3/platform/providers/DispatcherProvider;", "dispatcherProvider", "Lcom/amazon/mp3/platform/providers/DispatcherProvider;", "Lcom/amazon/mp3/billing/BillingClientWrapper;", "billingClientWrapper", "Lcom/amazon/mp3/billing/BillingClientWrapper;", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "", "purchasesPublisher", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "Lio/reactivex/rxjava3/core/Observable;", "purchasesObservable", "Lio/reactivex/rxjava3/core/Observable;", "getPurchasesObservable", "()Lio/reactivex/rxjava3/core/Observable;", "currentPurchases", "Ljava/util/List;", "getCurrentPurchases", "()Ljava/util/List;", "setCurrentPurchases", "(Ljava/util/List;)V", "billingEventPublisher", "getBillingEventPublisher$DigitalMusicAndroid3P_automotiveProdRelease", "()Lio/reactivex/rxjava3/subjects/PublishSubject;", "setBillingEventPublisher$DigitalMusicAndroid3P_automotiveProdRelease", "(Lio/reactivex/rxjava3/subjects/PublishSubject;)V", "billingEventObservable", "getBillingEventObservable", "recentSubmittedGoogleOrderId", "Ljava/lang/String;", "<init>", "(Landroid/content/Context;Lcom/amazon/mp3/billing/SubscriptionManagerProvider;Lcom/amazon/mp3/platform/providers/DispatcherProvider;)V", "Companion", "BillingEvaluateResponse", "OrderPurchaseToken", "PaymentPreferenceType", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BillingClientManager implements PurchasesUpdatedListener, PurchasesResponseListener, LifecycleObserver {
    private static volatile BillingClientManager INSTANCE;
    private BillingClientWrapper billingClientWrapper;
    private final Observable<BillingEvent> billingEventObservable;
    private PublishSubject<BillingEvent> billingEventPublisher;
    private final Context context;
    private List<? extends Purchase> currentPurchases;
    private final DispatcherProvider dispatcherProvider;
    private final Observable<List<Purchase>> purchasesObservable;
    private final PublishSubject<List<Purchase>> purchasesPublisher;
    private String recentSubmittedGoogleOrderId;
    private final SubscriptionManagerProvider subscriptionManagerProvider;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = Reflection.getOrCreateKotlinClass(BillingClientManager.class).getSimpleName();

    /* JADX INFO: compiled from: BillingClientManager.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/billing/BillingClientManager$PaymentPreferenceType;", "", "(Ljava/lang/String;I)V", "MUSIC_SUBS", "DMUSIC_PREMIUM_SVCS", UpsellType.PRIME, "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum PaymentPreferenceType {
        MUSIC_SUBS,
        DMUSIC_PREMIUM_SVCS,
        PRIME
    }

    /* JADX INFO: renamed from: com.amazon.mp3.billing.BillingClientManager$evaluateSubscriptionCreation$1, reason: invalid class name */
    /* JADX INFO: compiled from: BillingClientManager.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.billing.BillingClientManager", f = "BillingClientManager.kt", i = {0}, l = {196}, m = "evaluateSubscriptionCreation", n = {"this"}, s = {"L$0"})
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
            return BillingClientManager.this.evaluateSubscriptionCreation(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.billing.BillingClientManager$evaluateSubscriptionCreationHelper$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BillingClientManager.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.billing.BillingClientManager", f = "BillingClientManager.kt", i = {0, 0, 0, 0, 0}, l = {243}, m = "evaluateSubscriptionCreationHelper", n = {"this", "context", "skuName", ParserUtil.SCHEDULE_ID_QUERY_PARAM_NAME, "associatesTag"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
    static final class C03021 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C03021(Continuation<? super C03021> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return BillingClientManager.this.evaluateSubscriptionCreationHelper(null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.billing.BillingClientManager$waitForBillingServiceReady$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BillingClientManager.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.billing.BillingClientManager", f = "BillingClientManager.kt", i = {0, 0}, l = {91}, m = "waitForBillingServiceReady", n = {"this", "loggingContext"}, s = {"L$0", "L$1"})
    static final class C03031 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C03031(Continuation<? super C03031> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return BillingClientManager.this.waitForBillingServiceReady(this);
        }
    }

    @JvmStatic
    public static final BillingClientManager getInstance(Context context) {
        return INSTANCE.getInstance(context);
    }

    public BillingClientManager(Context context, SubscriptionManagerProvider subscriptionManagerProvider, DispatcherProvider dispatcherProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(subscriptionManagerProvider, "subscriptionManagerProvider");
        Intrinsics.checkNotNullParameter(dispatcherProvider, "dispatcherProvider");
        this.context = context;
        this.subscriptionManagerProvider = subscriptionManagerProvider;
        this.dispatcherProvider = dispatcherProvider;
        PublishSubject<List<Purchase>> publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create()");
        this.purchasesPublisher = publishSubjectCreate;
        Observable<List<Purchase>> observableHide = publishSubjectCreate.hide();
        Intrinsics.checkNotNullExpressionValue(observableHide, "purchasesPublisher.hide()");
        this.purchasesObservable = observableHide;
        this.currentPurchases = CollectionsKt__CollectionsKt.emptyList();
        PublishSubject<BillingEvent> publishSubjectCreate2 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate2, "create()");
        this.billingEventPublisher = publishSubjectCreate2;
        Observable<BillingEvent> observableHide2 = publishSubjectCreate2.hide();
        Intrinsics.checkNotNullExpressionValue(observableHide2, "billingEventPublisher.hide()");
        this.billingEventObservable = observableHide2;
        this.recentSubmittedGoogleOrderId = "";
        this.billingClientWrapper = new BillingClientWrapper(context, this, this, dispatcherProvider);
    }

    public /* synthetic */ BillingClientManager(Context context, SubscriptionManagerProvider subscriptionManagerProvider, DispatcherProvider dispatcherProvider, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? new DefaultSubscriptionManagerProvider() : subscriptionManagerProvider, (i & 4) != 0 ? DefaultDispatcherProvider.INSTANCE : dispatcherProvider);
    }

    public final Observable<List<Purchase>> getPurchasesObservable() {
        return this.purchasesObservable;
    }

    public final PublishSubject<BillingEvent> getBillingEventPublisher$DigitalMusicAndroid3P_automotiveProdRelease() {
        return this.billingEventPublisher;
    }

    public final Observable<BillingEvent> getBillingEventObservable() {
        return this.billingEventObservable;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public final void onCreate() {
        this.billingClientWrapper.startConnection();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object waitForBillingServiceReady(kotlin.coroutines.Continuation<? super java.lang.Boolean> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.amazon.mp3.billing.BillingClientManager.C03031
            if (r0 == 0) goto L13
            r0 = r8
            com.amazon.mp3.billing.BillingClientManager$waitForBillingServiceReady$1 r0 = (com.amazon.mp3.billing.BillingClientManager.C03031) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.billing.BillingClientManager$waitForBillingServiceReady$1 r0 = new com.amazon.mp3.billing.BillingClientManager$waitForBillingServiceReady$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3a
            if (r2 != r3) goto L32
            java.lang.Object r1 = r0.L$1
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r0 = r0.L$0
            com.amazon.mp3.billing.BillingClientManager r0 = (com.amazon.mp3.billing.BillingClientManager) r0
            kotlin.ResultKt.throwOnFailure(r8)
            r3 = r1
            goto L52
        L32:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L3a:
            kotlin.ResultKt.throwOnFailure(r8)
            java.lang.String r8 = "waitForBillingServiceReady"
            com.amazon.mp3.billing.BillingClientWrapper r2 = r7.billingClientWrapper
            r0.L$0 = r7
            r0.L$1 = r8
            r0.label = r3
            java.lang.Object r0 = r2.waitForClientReady(r0)
            if (r0 != r1) goto L4f
            return r1
        L4f:
            r3 = r8
            r8 = r0
            r0 = r7
        L52:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            if (r8 != 0) goto L6c
            io.reactivex.rxjava3.subjects.PublishSubject r0 = r0.getBillingEventPublisher$DigitalMusicAndroid3P_automotiveProdRelease()
            com.amazon.mp3.billing.BillingClientWrapper$Companion r1 = com.amazon.mp3.billing.BillingClientWrapper.INSTANCE
            com.amazon.mp3.billing.event.GoogleBillingClientEvent r2 = com.amazon.mp3.billing.event.GoogleBillingClientEvent.BILLING_SETUP_OTHER_ERRORS
            r4 = 0
            r5 = 4
            r6 = 0
            com.amazon.mp3.billing.event.BillingEvent r1 = com.amazon.mp3.billing.BillingClientWrapper.Companion.emitMetrics$default(r1, r2, r3, r4, r5, r6)
            r0.onNext(r1)
        L6c:
            java.lang.Boolean r8 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.billing.BillingClientManager.waitForBillingServiceReady(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.android.billingclient.api.PurchasesUpdatedListener
    public void onPurchasesUpdated(BillingResult billingResult, List<Purchase> purchasesList) {
        Intrinsics.checkNotNullParameter(billingResult, "billingResult");
        BillingClientWrapper.Companion companion = BillingClientWrapper.INSTANCE;
        if (companion.isBillingResultOk(billingResult)) {
            this.billingEventPublisher.onNext(companion.emitMetrics(GoogleBillingClientEvent.ON_PURCHASES_UPDATED_SUCCESS, "onPurchasesUpdated", billingResult));
            submitUnacknowledgedPurchases$DigitalMusicAndroid3P_automotiveProdRelease(purchasesList, "onPurchasesUpdated");
        } else if (companion.isBillingResultUserCanceled(billingResult)) {
            this.billingEventPublisher.onNext(companion.emitMetrics(GoogleBillingClientEvent.ON_PURCHASES_UPDATED_USER_CANCELLED, "onPurchasesUpdated", billingResult));
        } else {
            this.billingEventPublisher.onNext(companion.emitMetrics(GoogleBillingClientEvent.ON_PURCHASES_UPDATED_OTHER_ERRORS, "onPurchasesUpdated", billingResult));
        }
    }

    @Override // com.android.billingclient.api.PurchasesResponseListener
    public void onQueryPurchasesResponse(BillingResult billingResult, List<Purchase> purchasesList) {
        Intrinsics.checkNotNullParameter(billingResult, "billingResult");
        Intrinsics.checkNotNullParameter(purchasesList, "purchasesList");
        BillingClientWrapper.Companion companion = BillingClientWrapper.INSTANCE;
        if (companion.isBillingResultOk(billingResult)) {
            GoogleBillingClientEvent googleBillingClientEvent = GoogleBillingClientEvent.QUERY_PURCHASES_SUCCESS;
            companion.emitMetrics(googleBillingClientEvent, "onQueryPurchasesResponse", billingResult);
            this.billingEventPublisher.onNext(googleBillingClientEvent);
            submitUnacknowledgedPurchases$DigitalMusicAndroid3P_automotiveProdRelease(purchasesList, "onQueryPurchasesResponse");
            return;
        }
        GoogleBillingClientEvent googleBillingClientEvent2 = GoogleBillingClientEvent.QUERY_PURCHASES_OTHER_ERRORS;
        companion.emitMetrics(googleBillingClientEvent2, "onQueryPurchasesResponse", billingResult);
        this.billingEventPublisher.onNext(googleBillingClientEvent2);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object evaluateSubscriptionCreation(java.lang.String r8, java.lang.String r9, java.lang.String r10, kotlin.coroutines.Continuation<? super com.amazon.music.platform.Outcome<com.amazon.mp3.billing.BillingClientManager.OrderPurchaseToken>> r11) {
        /*
            r7 = this;
            boolean r0 = r11 instanceof com.amazon.mp3.billing.BillingClientManager.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r11
            com.amazon.mp3.billing.BillingClientManager$evaluateSubscriptionCreation$1 r0 = (com.amazon.mp3.billing.BillingClientManager.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.billing.BillingClientManager$evaluateSubscriptionCreation$1 r0 = new com.amazon.mp3.billing.BillingClientManager$evaluateSubscriptionCreation$1
            r0.<init>(r11)
        L18:
            r6 = r0
            java.lang.Object r11 = r6.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 1
            if (r1 == 0) goto L36
            if (r1 != r2) goto L2e
            java.lang.Object r8 = r6.L$0
            com.amazon.mp3.billing.BillingClientManager r8 = (com.amazon.mp3.billing.BillingClientManager) r8
            kotlin.ResultKt.throwOnFailure(r11)
            goto L4c
        L2e:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L36:
            kotlin.ResultKt.throwOnFailure(r11)
            android.content.Context r11 = r7.context
            r6.L$0 = r7
            r6.label = r2
            r1 = r7
            r2 = r11
            r3 = r8
            r4 = r9
            r5 = r10
            java.lang.Object r11 = r1.evaluateSubscriptionCreationHelper(r2, r3, r4, r5, r6)
            if (r11 != r0) goto L4b
            return r0
        L4b:
            r8 = r7
        L4c:
            com.amazon.mp3.billing.BillingClientManager$BillingEvaluateResponse r11 = (com.amazon.mp3.billing.BillingClientManager.BillingEvaluateResponse) r11
            com.amazon.mp3.billing.event.BillingEvent r9 = r11.getEvent()
            io.reactivex.rxjava3.subjects.PublishSubject r8 = r8.getBillingEventPublisher$DigitalMusicAndroid3P_automotiveProdRelease()
            r8.onNext(r9)
            com.amazon.mp3.billing.BillingClientWrapper$Companion r0 = com.amazon.mp3.billing.BillingClientWrapper.INSTANCE
            r3 = 0
            r4 = 4
            r5 = 0
            java.lang.String r2 = "evaluateSubscriptionCreation"
            r1 = r9
            com.amazon.mp3.billing.BillingClientWrapper.Companion.emitMetrics$default(r0, r1, r2, r3, r4, r5)
            com.amazon.mp3.billing.BillingClientManager$OrderPurchaseToken r8 = r11.getOrderPurchaseToken()
            if (r8 == 0) goto L74
            com.amazon.music.platform.Outcome$Success r8 = new com.amazon.music.platform.Outcome$Success
            com.amazon.mp3.billing.BillingClientManager$OrderPurchaseToken r9 = r11.getOrderPurchaseToken()
            r8.<init>(r9)
            goto L82
        L74:
            com.amazon.music.platform.Outcome$Failure r8 = new com.amazon.music.platform.Outcome$Failure
            java.lang.Exception r10 = new java.lang.Exception
            java.lang.String r9 = r9.getValue()
            r10.<init>(r9)
            r8.<init>(r10)
        L82:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.billing.BillingClientManager.evaluateSubscriptionCreation(java.lang.String, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void submitSubscriptionCreation(Purchase purchase) {
        Intrinsics.checkNotNullParameter(purchase, "purchase");
        BillingEvent billingEventSubmitSubscriptionCreationHelper$DigitalMusicAndroid3P_automotiveProdRelease = submitSubscriptionCreationHelper$DigitalMusicAndroid3P_automotiveProdRelease(purchase, getPaymentInfo$DigitalMusicAndroid3P_automotiveProdRelease(this.context));
        this.billingEventPublisher.onNext(billingEventSubmitSubscriptionCreationHelper$DigitalMusicAndroid3P_automotiveProdRelease);
        BillingClientWrapper.Companion.emitMetrics$default(BillingClientWrapper.INSTANCE, billingEventSubmitSubscriptionCreationHelper$DigitalMusicAndroid3P_automotiveProdRelease, "submitSubscriptionCreation", null, 4, null);
        if (billingEventSubmitSubscriptionCreationHelper$DigitalMusicAndroid3P_automotiveProdRelease == PurchaseSubmissionEvent.SUBMISSION_SUCCESS) {
            onCustomerUpgrade();
        }
    }

    public final void launchBillingFlow(Activity activity, String skuName, OrderPurchaseToken orderPurchaseToken) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(skuName, "skuName");
        Intrinsics.checkNotNullParameter(orderPurchaseToken, "orderPurchaseToken");
        this.billingEventPublisher.onNext(this.billingClientWrapper.launchBillingFlow(activity, skuName, orderPurchaseToken.getOrderInitToken(), orderPurchaseToken.getPurchaseHandleId()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object evaluateSubscriptionCreationHelper(android.content.Context r24, java.lang.String r25, java.lang.String r26, java.lang.String r27, kotlin.coroutines.Continuation<? super com.amazon.mp3.billing.BillingClientManager.BillingEvaluateResponse> r28) {
        /*
            Method dump skipped, instruction units count: 325
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.billing.BillingClientManager.evaluateSubscriptionCreationHelper(android.content.Context, java.lang.String, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final String getSessionId$DigitalMusicAndroid3P_automotiveProdRelease(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            String strFind = AmazonApplication.getAuthInfoMAPR5().find(AuthDataType.SESSION_ID);
            Intrinsics.checkNotNullExpressionValue(strFind, "{\n            val authIn…ype.SESSION_ID)\n        }");
            return strFind;
        } catch (Exception e) {
            Log.error(TAG, "getSessionId failed", e);
            return SessionIdProviderImpl.INSTANCE.getGeneratedSessionId(context);
        }
    }

    public final BillingEvent submitSubscriptionCreationHelper$DigitalMusicAndroid3P_automotiveProdRelease(Purchase purchase, PaymentInfo paymentInfo) {
        String obfuscatedProfileId;
        Intrinsics.checkNotNullParameter(purchase, "purchase");
        Intrinsics.checkNotNullParameter(paymentInfo, "paymentInfo");
        ArrayList<String> skus = purchase.getSkus();
        Intrinsics.checkNotNullExpressionValue(skus, "purchase.skus");
        String str = (String) CollectionsKt___CollectionsKt.firstOrNull((List) skus);
        if (str == null || !BillingClientWrapper.INSTANCE.isSkuValid(str)) {
            Log.error(TAG, "skus should not be empty or invalid in purchase=" + purchase + ", skip submit");
            return PurchaseSubmissionEvent.INVALID_SKU;
        }
        AccountIdentifiers accountIdentifiers = purchase.getAccountIdentifiers();
        String str2 = "";
        if (accountIdentifiers != null && (obfuscatedProfileId = accountIdentifiers.getObfuscatedProfileId()) != null) {
            str2 = obfuscatedProfileId;
        }
        String purchaseToken = purchase.getPurchaseToken();
        Intrinsics.checkNotNullExpressionValue(purchaseToken, "purchase.purchaseToken");
        try {
            Log.info(TAG, Intrinsics.stringPlus("submitSubmissionCreation success, status=", this.subscriptionManagerProvider.submitSubscriptionCreation(paymentInfo, str2, str, purchaseToken).getSubscription().getStatus()));
            return PurchaseSubmissionEvent.SUBMISSION_SUCCESS;
        } catch (Exception e) {
            Log.error(TAG, "submitSubmissionCreation failed", e);
            return BillingFlexEventBuilder.INSTANCE.translateExceptionToBillingError("purchaseSubmissionEvent", transformCoralError(e));
        }
    }

    private final Throwable transformCoralError(Exception e) {
        return e instanceof CoralError ? e.getCause() : e;
    }

    public final PaymentInfo getPaymentInfo$DigitalMusicAndroid3P_automotiveProdRelease(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        AccountDetailUtil accountDetailUtil = AccountDetailUtil.get(context);
        String customerId = accountDetailUtil.getCustomerId();
        Marketplace homeMarketPlace = accountDetailUtil.getHomeMarketPlace();
        PaymentInfo paymentInfoBuild = new PaymentInfo.Builder().withPaymentPortalClientId(accountDetailUtil.isUserInEUMarketplace() ? "Amazon-Music" : "Subs:DigitalMusic").withPaymentsPreferenceKey(PaymentsPreferenceKey.builder().withCustomerId(customerId).withMarketplaceId(homeMarketPlace.getObfuscatedId()).withPreferenceType(PaymentPreferenceType.DMUSIC_PREMIUM_SVCS.name()).withExternalReferenceId(null).withPaymentMethodId(null).withPaymentPreferenceId(null).build()).build();
        Intrinsics.checkNotNullExpressionValue(paymentInfoBuild, "Builder()\n              …\n                .build()");
        return paymentInfoBuild;
    }

    @WorkerThread
    private final void onCustomerUpgrade() {
        AccountManagerSingleton.get().updateUser();
        if (!UserSubscriptionUtil.isNightwingOnly()) {
            BillingClientWrapper.Companion.emitMetrics$default(BillingClientWrapper.INSTANCE, PurchaseSubmissionEvent.UPGRADE_SUCCESS, "onCustomerUpgrade", null, 4, null);
        } else {
            BillingClientWrapper.Companion.emitMetrics$default(BillingClientWrapper.INSTANCE, PurchaseSubmissionEvent.UPGRADE_PENDING, "onCustomerUpgrade", null, 4, null);
        }
    }

    /* JADX INFO: compiled from: BillingClientManager.kt */
    @Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0080\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\n\u001a\u00020\t\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/billing/BillingClientManager$BillingEvaluateResponse;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/mp3/billing/event/BillingEvent;", NotificationCompat.CATEGORY_EVENT, "Lcom/amazon/mp3/billing/event/BillingEvent;", "getEvent", "()Lcom/amazon/mp3/billing/event/BillingEvent;", "Lcom/amazon/mp3/billing/BillingClientManager$OrderPurchaseToken;", "orderPurchaseToken", "Lcom/amazon/mp3/billing/BillingClientManager$OrderPurchaseToken;", "getOrderPurchaseToken", "()Lcom/amazon/mp3/billing/BillingClientManager$OrderPurchaseToken;", "<init>", "(Lcom/amazon/mp3/billing/event/BillingEvent;Lcom/amazon/mp3/billing/BillingClientManager$OrderPurchaseToken;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class BillingEvaluateResponse {
        private final BillingEvent event;
        private final OrderPurchaseToken orderPurchaseToken;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BillingEvaluateResponse)) {
                return false;
            }
            BillingEvaluateResponse billingEvaluateResponse = (BillingEvaluateResponse) other;
            return Intrinsics.areEqual(this.event, billingEvaluateResponse.event) && Intrinsics.areEqual(this.orderPurchaseToken, billingEvaluateResponse.orderPurchaseToken);
        }

        public int hashCode() {
            int iHashCode = this.event.hashCode() * 31;
            OrderPurchaseToken orderPurchaseToken = this.orderPurchaseToken;
            return iHashCode + (orderPurchaseToken == null ? 0 : orderPurchaseToken.hashCode());
        }

        public String toString() {
            return "BillingEvaluateResponse(event=" + this.event + ", orderPurchaseToken=" + this.orderPurchaseToken + ')';
        }

        public BillingEvaluateResponse(BillingEvent event, OrderPurchaseToken orderPurchaseToken) {
            Intrinsics.checkNotNullParameter(event, "event");
            this.event = event;
            this.orderPurchaseToken = orderPurchaseToken;
        }

        public /* synthetic */ BillingEvaluateResponse(BillingEvent billingEvent, OrderPurchaseToken orderPurchaseToken, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(billingEvent, (i & 2) != 0 ? null : orderPurchaseToken);
        }

        public final BillingEvent getEvent() {
            return this.event;
        }

        public final OrderPurchaseToken getOrderPurchaseToken() {
            return this.orderPurchaseToken;
        }
    }

    /* JADX INFO: compiled from: BillingClientManager.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\f¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/billing/BillingClientManager$OrderPurchaseToken;", "", "", "toString", "", "hashCode", "other", "", "equals", "orderInitToken", "Ljava/lang/String;", "getOrderInitToken", "()Ljava/lang/String;", "purchaseHandleId", "getPurchaseHandleId", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class OrderPurchaseToken {
        private final String orderInitToken;
        private final String purchaseHandleId;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof OrderPurchaseToken)) {
                return false;
            }
            OrderPurchaseToken orderPurchaseToken = (OrderPurchaseToken) other;
            return Intrinsics.areEqual(this.orderInitToken, orderPurchaseToken.orderInitToken) && Intrinsics.areEqual(this.purchaseHandleId, orderPurchaseToken.purchaseHandleId);
        }

        public int hashCode() {
            return (this.orderInitToken.hashCode() * 31) + this.purchaseHandleId.hashCode();
        }

        public String toString() {
            return "OrderPurchaseToken(orderInitToken=" + this.orderInitToken + ", purchaseHandleId=" + this.purchaseHandleId + ')';
        }

        public OrderPurchaseToken(String orderInitToken, String purchaseHandleId) {
            Intrinsics.checkNotNullParameter(orderInitToken, "orderInitToken");
            Intrinsics.checkNotNullParameter(purchaseHandleId, "purchaseHandleId");
            this.orderInitToken = orderInitToken;
            this.purchaseHandleId = purchaseHandleId;
        }

        public final String getOrderInitToken() {
            return this.orderInitToken;
        }

        public final String getPurchaseHandleId() {
            return this.purchaseHandleId;
        }
    }

    /* JADX INFO: compiled from: BillingClientManager.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000fH\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/billing/BillingClientManager$Companion;", "", "()V", "INSTANCE", "Lcom/amazon/mp3/billing/BillingClientManager;", "PAYMENT_PORTAL_CLIENT_ID_FOR_EU", "", "PAYMENT_PORTAL_CLIENT_ID_FOR_NON_EU", "TAG", "getAmountFromSkuAmountMicro", "Ljava/math/BigDecimal;", "amountMicro", "", "getInstance", "context", "Landroid/content/Context;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final BillingClientManager getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            BillingClientManager billingClientManager = BillingClientManager.INSTANCE;
            if (billingClientManager == null) {
                synchronized (this) {
                    billingClientManager = BillingClientManager.INSTANCE;
                    if (billingClientManager == null) {
                        Context applicationContext = context.getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext, "context.applicationContext");
                        billingClientManager = new BillingClientManager(applicationContext, null, null, 6, null);
                        BillingClientManager.INSTANCE = billingClientManager;
                    }
                }
            }
            return billingClientManager;
        }

        public final BigDecimal getAmountFromSkuAmountMicro(long amountMicro) {
            BigDecimal bigDecimalDivide = BigDecimal.valueOf(amountMicro).divide(BigDecimal.valueOf(1000000L));
            Intrinsics.checkNotNullExpressionValue(bigDecimalDivide, "amountMicroDecimal.divid…Decimal.valueOf(1000000))");
            return bigDecimalDivide;
        }
    }

    public final void submitUnacknowledgedPurchases$DigitalMusicAndroid3P_automotiveProdRelease(List<Purchase> purchasesList, String loggingContext) {
        Intrinsics.checkNotNullParameter(loggingContext, "loggingContext");
        if (purchasesList == null) {
            Log.error(TAG, Intrinsics.stringPlus(loggingContext, ": purchase list is null"));
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = purchasesList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            ArrayList<String> skus = ((Purchase) next).getSkus();
            Intrinsics.checkNotNullExpressionValue(skus, "it.skus");
            String str = (String) CollectionsKt___CollectionsKt.firstOrNull((List) skus);
            if (str != null && BillingClientWrapper.INSTANCE.isSkuValid(str)) {
                arrayList.add(next);
            }
        }
        this.currentPurchases = arrayList;
        if (!arrayList.isEmpty()) {
            this.purchasesPublisher.onNext((List<Purchase>) this.currentPurchases);
            for (Purchase purchase : this.currentPurchases) {
                Log.debug(TAG, loggingContext + ": purchased received orderId=" + purchase.getOrderId() + " purchaseToken=" + purchase.getPurchaseToken() + " state=" + purchase.getPurchaseState());
                if (purchase.getPurchaseState() == 1 && !purchase.isAcknowledged() && !Intrinsics.areEqual(purchase.getOrderId(), this.recentSubmittedGoogleOrderId)) {
                    String orderId = purchase.getOrderId();
                    Intrinsics.checkNotNullExpressionValue(orderId, "purchase.orderId");
                    this.recentSubmittedGoogleOrderId = orderId;
                    BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(this.dispatcherProvider.io()), null, null, new BillingClientManager$submitUnacknowledgedPurchases$2$1(loggingContext, purchase, this, null), 3, null);
                }
            }
        }
    }
}
