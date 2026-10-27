package com.amazon.mp3.billing.event;

import com.amazon.cirrus.shared.model.exception.AccountLockedException;
import com.amazon.cirrus.shared.model.exception.CirrusBaseException;
import com.amazon.cirrus.shared.model.exception.InvalidParameterException;
import com.amazon.cirrus.shared.model.exception.RequestThrottledException;
import com.amazon.cirrus.shared.model.exception.ServiceException;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.subscription.CustomerAlreadySubscribedException;
import com.amazon.music.subscription.EMPAmazonPriceMismatchException;
import com.amazon.music.subscription.FraudulentAccountException;
import com.amazon.music.subscription.IneligibleOfferException;
import com.amazon.stratus.GeocheckFailedException;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.android.gms.cast.MediaError;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: BillingFlexEventBuilder.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\u0018\u0000 !2\u00020\u0001:\u0001!B\u000f\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u001f\u0010 J\u0017\u0010\u0004\u001a\u00020\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\u00002\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006J\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0006J\u0006\u0010\f\u001a\u00020\u000bR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R$\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R$\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR$\u0010\t\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010\u0017\u001a\u0004\b\u001c\u0010\u0019\"\u0004\b\u001d\u0010\u001bR\u0014\u0010\u001e\u001a\u00020\u00068\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u001e\u0010\u0017¨\u0006\""}, d2 = {"Lcom/amazon/mp3/billing/event/BillingFlexEventBuilder;", "", "", "resultCode", "withResultCode", "(Ljava/lang/Integer;)Lcom/amazon/mp3/billing/event/BillingFlexEventBuilder;", "", "resultDebugMessage", "withResultDebugMessage", "loggingContext", "withLoggingContext", "Lcom/amazon/music/metrics/mts/event/definition/flex/FlexEvent;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/amazon/mp3/billing/event/BillingEvent;", "billingEvent", "Lcom/amazon/mp3/billing/event/BillingEvent;", "getBillingEvent", "()Lcom/amazon/mp3/billing/event/BillingEvent;", "Ljava/lang/Integer;", "getResultCode", "()Ljava/lang/Integer;", "setResultCode", "(Ljava/lang/Integer;)V", "Ljava/lang/String;", "getResultDebugMessage", "()Ljava/lang/String;", "setResultDebugMessage", "(Ljava/lang/String;)V", "getLoggingContext", "setLoggingContext", "MAPLE_EMITTER_ID", "<init>", "(Lcom/amazon/mp3/billing/event/BillingEvent;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BillingFlexEventBuilder {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String MAPLE_EMITTER_ID;
    private final BillingEvent billingEvent;
    private String loggingContext;
    private Integer resultCode;
    private String resultDebugMessage;

    public BillingFlexEventBuilder(BillingEvent billingEvent) {
        Intrinsics.checkNotNullParameter(billingEvent, "billingEvent");
        this.billingEvent = billingEvent;
        this.MAPLE_EMITTER_ID = "em_249";
    }

    public final void setResultCode(Integer num) {
        this.resultCode = num;
    }

    public final void setResultDebugMessage(String str) {
        this.resultDebugMessage = str;
    }

    public final void setLoggingContext(String str) {
        this.loggingContext = str;
    }

    public final BillingFlexEventBuilder withResultCode(Integer resultCode) {
        setResultCode(resultCode);
        return this;
    }

    public final BillingFlexEventBuilder withResultDebugMessage(String resultDebugMessage) {
        setResultDebugMessage(resultDebugMessage);
        return this;
    }

    public final BillingFlexEventBuilder withLoggingContext(String loggingContext) {
        Intrinsics.checkNotNullParameter(loggingContext, "loggingContext");
        setLoggingContext(loggingContext);
        return this;
    }

    public final FlexEvent build() {
        FlexEvent flexEventBuild = FlexEvent.builder(this.billingEvent.getFlexEventName()).withEmitterId(this.MAPLE_EMITTER_ID).withFlexStr1(this.billingEvent.getValue()).withFlexStr2(INSTANCE.translateBillingResponseCode(this.resultCode)).withFlexStr3(this.resultDebugMessage).withFlexStr4(this.loggingContext).withFlexNum1(this.resultCode == null ? null : Float.valueOf(r1.intValue())).build();
        Intrinsics.checkNotNullExpressionValue(flexEventBuild, "builder(billingEvent.get…t())\n            .build()");
        return flexEventBuild;
    }

    /* JADX INFO: compiled from: BillingFlexEventBuilder.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u000e\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\bJ\u0015\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eJ\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0006¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/billing/event/BillingFlexEventBuilder$Companion;", "", "()V", "isTargetExceptionClass", "", "currentException", "", "targetExceptionClass", "Lkotlin/reflect/KClass;", "Lcom/amazon/cirrus/shared/model/exception/CirrusBaseException;", "translateBillingResponseCode", "", "responseCode", "", "(Ljava/lang/Integer;)Ljava/lang/String;", "translateExceptionToBillingError", "Lcom/amazon/mp3/billing/event/BillingEvent;", "eventName", "exception", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final BillingEvent translateExceptionToBillingError(String eventName, Throwable exception) {
            Intrinsics.checkNotNullParameter(eventName, "eventName");
            int iHashCode = eventName.hashCode();
            if (iHashCode != -1274186194) {
                if (iHashCode != 161179501) {
                    if (iHashCode == 1830261117 && eventName.equals("purchaseEvaluationEvent")) {
                        if (isTargetExceptionClass(exception, Reflection.getOrCreateKotlinClass(EMPAmazonPriceMismatchException.class))) {
                            return PurchaseEvaluationEvent.EMP_AMAZON_PRICE_MISMATCH_EXCEPTION;
                        }
                        if (isTargetExceptionClass(exception, Reflection.getOrCreateKotlinClass(InvalidParameterException.class))) {
                            return PurchaseEvaluationEvent.INVALID_PARAMETER_EXCEPTION;
                        }
                        if (isTargetExceptionClass(exception, Reflection.getOrCreateKotlinClass(IneligibleOfferException.class))) {
                            return PurchaseEvaluationEvent.INELIGIBLE_OFFER_EXCEPTION;
                        }
                        if (isTargetExceptionClass(exception, Reflection.getOrCreateKotlinClass(CustomerAlreadySubscribedException.class))) {
                            return PurchaseEvaluationEvent.CUSTOMER_ALREADY_SUBSCRIBED_EXCEPTION;
                        }
                        if (isTargetExceptionClass(exception, Reflection.getOrCreateKotlinClass(GeocheckFailedException.class))) {
                            return PurchaseEvaluationEvent.GEO_CHECK_FAILED_EXCEPTION;
                        }
                        if (isTargetExceptionClass(exception, Reflection.getOrCreateKotlinClass(FraudulentAccountException.class))) {
                            return PurchaseEvaluationEvent.FRAUDULENT_ACCOUNT_EXCEPTION;
                        }
                        if (isTargetExceptionClass(exception, Reflection.getOrCreateKotlinClass(ServiceException.class))) {
                            return PurchaseEvaluationEvent.SERVICE_EXCEPTION;
                        }
                        if (isTargetExceptionClass(exception, Reflection.getOrCreateKotlinClass(RequestThrottledException.class))) {
                            return PurchaseEvaluationEvent.REQUEST_THROTTLED_EXCEPTION;
                        }
                        if (isTargetExceptionClass(exception, Reflection.getOrCreateKotlinClass(AccountLockedException.class))) {
                            return PurchaseEvaluationEvent.ACCOUNT_LOCKED_EXCEPTION;
                        }
                        return PurchaseEvaluationEvent.UNKNOWN_ERROR;
                    }
                } else if (eventName.equals("purchaseSubmissionEvent")) {
                    if (isTargetExceptionClass(exception, Reflection.getOrCreateKotlinClass(InvalidParameterException.class))) {
                        return PurchaseSubmissionEvent.INVALID_PARAMETER_EXCEPTION;
                    }
                    if (isTargetExceptionClass(exception, Reflection.getOrCreateKotlinClass(ServiceException.class))) {
                        return PurchaseSubmissionEvent.SERVICE_EXCEPTION;
                    }
                    if (isTargetExceptionClass(exception, Reflection.getOrCreateKotlinClass(RequestThrottledException.class))) {
                        return PurchaseSubmissionEvent.REQUEST_THROTTLED_EXCEPTION;
                    }
                    if (isTargetExceptionClass(exception, Reflection.getOrCreateKotlinClass(AccountLockedException.class))) {
                        return PurchaseSubmissionEvent.ACCOUNT_LOCKED_EXCEPTION;
                    }
                    return PurchaseSubmissionEvent.UNKNOWN_ERROR;
                }
            } else if (eventName.equals("purchaseEligibilityEvent")) {
                return PurchaseEligibilityEvent.UNKNOWN_ERROR;
            }
            return GoogleBillingClientEvent.UNKNOWN_ERROR;
        }

        public final String translateBillingResponseCode(Integer responseCode) {
            return (responseCode != null && responseCode.intValue() == -3) ? "SERVICE_TIMEOUT" : (responseCode != null && responseCode.intValue() == -2) ? "FEATURE_NOT_SUPPORTED" : (responseCode != null && responseCode.intValue() == -1) ? "SERVICE_DISCONNECTED" : (responseCode != null && responseCode.intValue() == 0) ? "OK" : (responseCode != null && responseCode.intValue() == 1) ? "USER_CANCELED" : (responseCode != null && responseCode.intValue() == 2) ? "SERVICE_UNAVAILABLE" : (responseCode != null && responseCode.intValue() == 3) ? "BILLING_UNAVAILABLE" : (responseCode != null && responseCode.intValue() == 4) ? "ITEM_UNAVAILABLE" : (responseCode != null && responseCode.intValue() == 5) ? "DEVELOPER_ERROR" : (responseCode != null && responseCode.intValue() == 6) ? MediaError.ERROR_TYPE_ERROR : (responseCode != null && responseCode.intValue() == 7) ? "ITEM_ALREADY_OWNED" : (responseCode != null && responseCode.intValue() == 8) ? "ITEM_NOT_OWNED" : responseCode == null ? "" : responseCode.toString();
        }

        public final boolean isTargetExceptionClass(Throwable currentException, KClass<? extends CirrusBaseException> targetExceptionClass) {
            String message;
            Intrinsics.checkNotNullParameter(targetExceptionClass, "targetExceptionClass");
            String simpleName = targetExceptionClass.getSimpleName();
            if (simpleName == null) {
                return false;
            }
            Boolean boolValueOf = null;
            if (currentException != null && (message = currentException.getMessage()) != null) {
                boolValueOf = Boolean.valueOf(StringsKt__StringsKt.contains$default((CharSequence) message, (CharSequence) simpleName, false, 2, (Object) null));
            }
            return Intrinsics.areEqual(boolValueOf, Boolean.TRUE);
        }
    }
}
