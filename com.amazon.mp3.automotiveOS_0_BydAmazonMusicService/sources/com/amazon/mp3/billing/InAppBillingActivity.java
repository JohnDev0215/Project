package com.amazon.mp3.billing;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.billing.BillingClientManager;
import com.amazon.mp3.billing.BillingErrorDialogFragment;
import com.amazon.mp3.billing.event.BillingEvent;
import com.amazon.mp3.billing.event.GoogleBillingClientEvent;
import com.amazon.mp3.billing.event.PurchaseEvaluationEvent;
import com.amazon.mp3.billing.event.PurchaseSubmissionEvent;
import com.amazon.mp3.config.AssociateTag;
import com.amazon.mp3.fragment.navigation.BrushFragmentNavigation;
import com.amazon.mp3.platform.providers.Coroutines;
import com.amazon.mp3.util.Log;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.inappmessaging.external.DynamicMessageDialogFragmentListener;
import com.amazon.music.platform.Outcome;
import com.android.billingclient.api.Purchase;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: InAppBillingActivity.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u0000 /2\u00020\u00012\u00020\u0002:\u0001/B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0012\u0010\u001f\u001a\u00020\u000f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0014J\b\u0010\"\u001a\u00020\u000fH\u0014J\u0012\u0010#\u001a\u00020\u000f2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\u0012\u0010&\u001a\u00020\u000f2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\"\u0010'\u001a\u00020\u000f2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020)2\b\u0010+\u001a\u0004\u0018\u00010)H\u0002J\b\u0010,\u001a\u00020\u000fH\u0002J\b\u0010-\u001a\u00020\u000fH\u0002J\b\u0010.\u001a\u00020\u000fH\u0002R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/amazon/mp3/billing/InAppBillingActivity;", "Lcom/amazon/mp3/activity/BaseActivity;", "Lcom/amazon/music/inappmessaging/external/DynamicMessageDialogFragmentListener;", "()V", "billingClientManager", "Lcom/amazon/mp3/billing/BillingClientManager;", "getBillingClientManager", "()Lcom/amazon/mp3/billing/BillingClientManager;", "billingClientManager$delegate", "Lkotlin/Lazy;", "compositeDisposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "spinner", "Landroid/widget/ProgressBar;", "dismissSpinner", "", "handleBillingEvent", "billingEvent", "Lcom/amazon/mp3/billing/event/BillingEvent;", "handleBillingGoogleClientEvent", "googleBillingClientEvent", "Lcom/amazon/mp3/billing/event/GoogleBillingClientEvent;", "handlePurchaseEvaluationEvent", "purchaseEvaluationEvent", "Lcom/amazon/mp3/billing/event/PurchaseEvaluationEvent;", "handlePurchaseSubmissionEvent", "purchaseSubmissionEvent", "Lcom/amazon/mp3/billing/event/PurchaseSubmissionEvent;", "handlePurchases", "purchase", "Lcom/android/billingclient/api/Purchase;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onDismissClickEvent", "dialog", "Landroid/content/DialogInterface;", "onShow", "processDynamicMessagingFlow", "skuName", "", ParserUtil.SCHEDULE_ID_QUERY_PARAM_NAME, "associatesTag", "registerSubscriptions", "shouldNavigateToHomeAndFinish", "showSpinner", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class InAppBillingActivity extends BaseActivity implements DynamicMessageDialogFragmentListener {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = Reflection.getOrCreateKotlinClass(InAppBillingActivity.class).getSimpleName();

    /* JADX INFO: renamed from: billingClientManager$delegate, reason: from kotlin metadata */
    private final Lazy billingClientManager = LazyKt__LazyJVMKt.lazy(new Function0<BillingClientManager>() { // from class: com.amazon.mp3.billing.InAppBillingActivity$billingClientManager$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BillingClientManager invoke() {
            BillingClientManager.Companion companion = BillingClientManager.INSTANCE;
            Context applicationContext = this.this$0.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
            return companion.getInstance(applicationContext);
        }
    });
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ProgressBar spinner;

    /* JADX INFO: compiled from: InAppBillingActivity.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[PurchaseEvaluationEvent.values().length];
            iArr[PurchaseEvaluationEvent.EVALUATION_SUCCESS.ordinal()] = 1;
            iArr[PurchaseEvaluationEvent.EMP_AMAZON_PRICE_MISMATCH_EXCEPTION.ordinal()] = 2;
            iArr[PurchaseEvaluationEvent.CUSTOMER_ALREADY_SUBSCRIBED_EXCEPTION.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[PurchaseSubmissionEvent.values().length];
            iArr2[PurchaseSubmissionEvent.SUBMISSION_SUCCESS.ordinal()] = 1;
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[GoogleBillingClientEvent.values().length];
            iArr3[GoogleBillingClientEvent.BILLING_SETUP_SUCCESS.ordinal()] = 1;
            iArr3[GoogleBillingClientEvent.QUERY_SKU_SUCCESS.ordinal()] = 2;
            iArr3[GoogleBillingClientEvent.LAUNCH_BILLING_FLOW_SUCCESS.ordinal()] = 3;
            iArr3[GoogleBillingClientEvent.ON_PURCHASES_UPDATED_SUCCESS.ordinal()] = 4;
            iArr3[GoogleBillingClientEvent.QUERY_PURCHASES_SUCCESS.ordinal()] = 5;
            iArr3[GoogleBillingClientEvent.ON_PURCHASES_UPDATED_USER_CANCELLED.ordinal()] = 6;
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    @JvmStatic
    public static final Intent getStartIntent(Context context) {
        return INSTANCE.getStartIntent(context);
    }

    @Override // com.amazon.music.inappmessaging.external.DynamicMessageDialogFragmentListener
    public void onShow(DialogInterface dialog) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BillingClientManager getBillingClientManager() {
        return (BillingClientManager) this.billingClientManager.getValue();
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upsell_container);
        View viewFindViewById = findViewById(R.id.upsell_loading_spinner);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(R.id.upsell_loading_spinner)");
        this.spinner = (ProgressBar) viewFindViewById;
        getLifecycle().addObserver(getBillingClientManager());
        registerSubscriptions();
        Bundle extras = getIntent().getExtras();
        String string = extras == null ? null : extras.getString("com.amazon.mp3.billing.EXTRA_SKU_NAME");
        String string2 = extras == null ? null : extras.getString("com.amazon.mp3.billing.EXTRA_SCHEDULE_ID");
        String string3 = extras != null ? extras.getString("com.amazon.mp3.billing.EXTRA_ASSOCIATES_TAG") : null;
        if (string3 == null || string3.length() == 0) {
            string3 = AssociateTag.getInstance(getApplicationContext()).getAssociateTag();
        }
        if (string == null || string2 == null) {
            return;
        }
        processDynamicMessagingFlow(string, string2, string3);
    }

    @Override // com.amazon.music.inappmessaging.external.DynamicMessageDialogFragmentListener
    public void onDismissClickEvent(DialogInterface dialog) {
        shouldNavigateToHomeAndFinish();
    }

    private final void shouldNavigateToHomeAndFinish() {
        if (isTaskRoot()) {
            Context applicationContext = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
            BrushFragmentNavigation.navigateToHome(applicationContext);
        }
        finish();
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.compositeDisposable.clear();
        getLifecycle().removeObserver(getBillingClientManager());
    }

    private final void registerSubscriptions() {
        Disposable disposableSubscribe = getBillingClientManager().getPurchasesObservable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.mp3.billing.InAppBillingActivity$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                InAppBillingActivity.m412registerSubscriptions$lambda0(this.f$0, (List) obj);
            }
        }, new Consumer() { // from class: com.amazon.mp3.billing.InAppBillingActivity$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                InAppBillingActivity.m413registerSubscriptions$lambda1((Throwable) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "billingClientManager.pur…wable)\n                })");
        this.compositeDisposable.add(disposableSubscribe);
        Disposable disposableSubscribe2 = getBillingClientManager().getBillingEventObservable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.mp3.billing.InAppBillingActivity$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                InAppBillingActivity.m414registerSubscriptions$lambda2(this.f$0, (BillingEvent) obj);
            }
        }, new Consumer() { // from class: com.amazon.mp3.billing.InAppBillingActivity$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                InAppBillingActivity.m415registerSubscriptions$lambda3((Throwable) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe2, "billingClientManager.bil…wable)\n                })");
        this.compositeDisposable.add(disposableSubscribe2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: registerSubscriptions$lambda-0, reason: not valid java name */
    public static final void m412registerSubscriptions$lambda0(InAppBillingActivity this$0, List purchases) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(purchases, "purchases");
        Iterator it = purchases.iterator();
        while (it.hasNext()) {
            this$0.handlePurchases((Purchase) it.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: registerSubscriptions$lambda-1, reason: not valid java name */
    public static final void m413registerSubscriptions$lambda1(Throwable th) {
        Log.error(TAG, "onCreate: subscribe purchases publisher failed", th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: registerSubscriptions$lambda-2, reason: not valid java name */
    public static final void m414registerSubscriptions$lambda2(InAppBillingActivity this$0, BillingEvent event) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(event, "event");
        this$0.handleBillingEvent(event);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: registerSubscriptions$lambda-3, reason: not valid java name */
    public static final void m415registerSubscriptions$lambda3(Throwable th) {
        Log.error(TAG, "onCreate: subscribe billingError publisher failed", th);
    }

    private final void handlePurchases(Purchase purchase) {
        if (purchase.getPurchaseState() == 2) {
            BillingErrorDialogFragment.INSTANCE.createPendingPurchaseDialog(this).show(getSupportFragmentManager(), (String) null);
            dismissSpinner();
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.billing.InAppBillingActivity$processDynamicMessagingFlow$1, reason: invalid class name */
    /* JADX INFO: compiled from: InAppBillingActivity.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.billing.InAppBillingActivity$processDynamicMessagingFlow$1", f = "InAppBillingActivity.kt", i = {}, l = {121, 124}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $associatesTag;
        final /* synthetic */ String $scheduleId;
        final /* synthetic */ String $skuName;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, String str2, String str3, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$skuName = str;
            this.$scheduleId = str2;
            this.$associatesTag = str3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return InAppBillingActivity.this.new AnonymousClass1(this.$skuName, this.$scheduleId, this.$associatesTag, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x0062  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) {
            /*
                r7 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r7.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L1e
                if (r1 == r3) goto L1a
                if (r1 != r2) goto L12
                kotlin.ResultKt.throwOnFailure(r8)
                goto L5c
            L12:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r0)
                throw r8
            L1a:
                kotlin.ResultKt.throwOnFailure(r8)
                goto L35
            L1e:
                kotlin.ResultKt.throwOnFailure(r8)
                com.amazon.mp3.billing.InAppBillingActivity r8 = com.amazon.mp3.billing.InAppBillingActivity.this
                com.amazon.mp3.billing.InAppBillingActivity.access$showSpinner(r8)
                com.amazon.mp3.billing.InAppBillingActivity r8 = com.amazon.mp3.billing.InAppBillingActivity.this
                com.amazon.mp3.billing.BillingClientManager r8 = com.amazon.mp3.billing.InAppBillingActivity.access$getBillingClientManager(r8)
                r7.label = r3
                java.lang.Object r8 = r8.waitForBillingServiceReady(r7)
                if (r8 != r0) goto L35
                return r0
            L35:
                java.lang.Boolean r8 = (java.lang.Boolean) r8
                boolean r8 = r8.booleanValue()
                if (r8 != 0) goto L47
                java.lang.String r8 = com.amazon.mp3.billing.InAppBillingActivity.access$getTAG$cp()
                java.lang.String r0 = "BillingClient still not ready after 6 seconds wait, stop purchase flow"
                com.amazon.mp3.util.Log.error(r8, r0)
                goto L8f
            L47:
                com.amazon.mp3.billing.InAppBillingActivity r8 = com.amazon.mp3.billing.InAppBillingActivity.this
                com.amazon.mp3.billing.BillingClientManager r8 = com.amazon.mp3.billing.InAppBillingActivity.access$getBillingClientManager(r8)
                java.lang.String r1 = r7.$skuName
                java.lang.String r3 = r7.$scheduleId
                java.lang.String r4 = r7.$associatesTag
                r7.label = r2
                java.lang.Object r8 = r8.evaluateSubscriptionCreation(r1, r3, r4, r7)
                if (r8 != r0) goto L5c
                return r0
            L5c:
                com.amazon.music.platform.Outcome r8 = (com.amazon.music.platform.Outcome) r8
                boolean r0 = r8 instanceof com.amazon.music.platform.Outcome.Success
                if (r0 == 0) goto L8f
                java.lang.String r0 = com.amazon.mp3.billing.InAppBillingActivity.access$getTAG$cp()
                r1 = r8
                com.amazon.music.platform.Outcome$Success r1 = (com.amazon.music.platform.Outcome.Success) r1
                java.lang.Object r1 = r1.getValue()
                java.lang.String r2 = "evaluateSubscriptionCreation success, orderPurchaseToken="
                java.lang.String r1 = kotlin.jvm.internal.Intrinsics.stringPlus(r2, r1)
                com.amazon.mp3.util.Log.info(r0, r1)
                com.amazon.mp3.billing.InAppBillingActivity r0 = com.amazon.mp3.billing.InAppBillingActivity.this
                androidx.lifecycle.LifecycleCoroutineScope r1 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r0)
                kotlinx.coroutines.MainCoroutineDispatcher r2 = kotlinx.coroutines.Dispatchers.getMain()
                r3 = 0
                com.amazon.mp3.billing.InAppBillingActivity$processDynamicMessagingFlow$1$1 r4 = new com.amazon.mp3.billing.InAppBillingActivity$processDynamicMessagingFlow$1$1
                com.amazon.mp3.billing.InAppBillingActivity r0 = com.amazon.mp3.billing.InAppBillingActivity.this
                java.lang.String r5 = r7.$skuName
                r6 = 0
                r4.<init>(r0, r5, r8, r6)
                r5 = 2
                kotlinx.coroutines.BuildersKt.launch$default(r1, r2, r3, r4, r5, r6)
            L8f:
                kotlin.Unit r8 = kotlin.Unit.INSTANCE
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.billing.InAppBillingActivity.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: renamed from: com.amazon.mp3.billing.InAppBillingActivity$processDynamicMessagingFlow$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: InAppBillingActivity.kt */
        @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
        @DebugMetadata(c = "com.amazon.mp3.billing.InAppBillingActivity$processDynamicMessagingFlow$1$1", f = "InAppBillingActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C00721 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Outcome<BillingClientManager.OrderPurchaseToken> $result;
            final /* synthetic */ String $skuName;
            int label;
            final /* synthetic */ InAppBillingActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00721(InAppBillingActivity inAppBillingActivity, String str, Outcome<BillingClientManager.OrderPurchaseToken> outcome, Continuation<? super C00721> continuation) {
                super(2, continuation);
                this.this$0 = inAppBillingActivity;
                this.$skuName = str;
                this.$result = outcome;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00721(this.this$0, this.$skuName, this.$result, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00721) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.this$0.getBillingClientManager().launchBillingFlow(this.this$0, this.$skuName, (BillingClientManager.OrderPurchaseToken) ((Outcome.Success) this.$result).getValue());
                return Unit.INSTANCE;
            }
        }
    }

    private final void processDynamicMessagingFlow(String skuName, String scheduleId, String associatesTag) {
        BuildersKt__Builders_commonKt.launch$default(Coroutines.INSTANCE.getIoScope(), null, null, new AnonymousClass1(skuName, scheduleId, associatesTag, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showSpinner() {
        ProgressBar progressBar = this.spinner;
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("spinner");
            progressBar = null;
        }
        progressBar.setVisibility(0);
    }

    private final void dismissSpinner() {
        ProgressBar progressBar = this.spinner;
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("spinner");
            progressBar = null;
        }
        progressBar.setVisibility(4);
    }

    private final void handleBillingEvent(BillingEvent billingEvent) {
        if (billingEvent instanceof PurchaseEvaluationEvent) {
            handlePurchaseEvaluationEvent((PurchaseEvaluationEvent) billingEvent);
            return;
        }
        if (billingEvent instanceof PurchaseSubmissionEvent) {
            handlePurchaseSubmissionEvent((PurchaseSubmissionEvent) billingEvent);
        } else if (billingEvent instanceof GoogleBillingClientEvent) {
            handleBillingGoogleClientEvent((GoogleBillingClientEvent) billingEvent);
        } else {
            Log.error(TAG, Intrinsics.stringPlus("receive unsupported billing error ", billingEvent));
        }
    }

    private final void handlePurchaseEvaluationEvent(PurchaseEvaluationEvent purchaseEvaluationEvent) {
        int i = WhenMappings.$EnumSwitchMapping$0[purchaseEvaluationEvent.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    BillingErrorDialogFragment.INSTANCE.createAlreadySubscribedDialog(this).show(getSupportFragmentManager(), (String) null);
                    return;
                } else {
                    BillingErrorDialogFragment.INSTANCE.createGeneralFailureDialog(this).show(getSupportFragmentManager(), (String) null);
                    return;
                }
            }
            BillingErrorDialogFragment.Companion companion = BillingErrorDialogFragment.INSTANCE;
            String musicTerritoryOfResidence = AccountDetailUtil.getMusicTerritoryOfResidence();
            Intrinsics.checkNotNullExpressionValue(musicTerritoryOfResidence, "getMusicTerritoryOfResidence()");
            companion.createTerritoryMismatchDialog(musicTerritoryOfResidence, this).show(getSupportFragmentManager(), (String) null);
        }
    }

    private final void handlePurchaseSubmissionEvent(PurchaseSubmissionEvent purchaseSubmissionEvent) {
        if (WhenMappings.$EnumSwitchMapping$1[purchaseSubmissionEvent.ordinal()] == 1) {
            new WelcomeToUnlimitedDialogFragment(this).show(getSupportFragmentManager(), (String) null);
        } else {
            BillingErrorDialogFragment.INSTANCE.createSubmissionRetryErrorDialog(this).show(getSupportFragmentManager(), (String) null);
        }
        dismissSpinner();
    }

    private final void handleBillingGoogleClientEvent(GoogleBillingClientEvent googleBillingClientEvent) {
        switch (WhenMappings.$EnumSwitchMapping$2[googleBillingClientEvent.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                break;
            case 6:
                finish();
                break;
            default:
                BillingErrorDialogFragment.INSTANCE.createGeneralFailureDialog(this).show(getSupportFragmentManager(), (String) null);
                break;
        }
    }

    /* JADX INFO: compiled from: InAppBillingActivity.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/billing/InAppBillingActivity$Companion;", "", "()V", "EXTRA_ASSOCIATES_TAG", "", "EXTRA_REF", "EXTRA_SCHEDULE_ID", "EXTRA_SKU_NAME", "TAG", "getStartIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Intent getStartIntent(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(context, (Class<?>) InAppBillingActivity.class);
            if (!(context instanceof Activity)) {
                intent.addFlags(268435456);
            }
            return intent;
        }
    }
}
