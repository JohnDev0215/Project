package com.amazon.mp3.billing;

import com.amazon.mp3.hawkfire.upsell.SubscriptionManagerSingleton;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.subscription.EvaluateSubscriptionCreationResponse;
import com.amazon.music.subscription.PriceWithCurrency;
import com.amazon.music.subscription.SubmitSubscriptionCreationResponse;
import com.amazon.stratus.PaymentInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultSubscriptionManagerProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J<\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\b\u0010\r\u001a\u0004\u0018\u00010\nH\u0016J(\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\nH\u0016¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/billing/DefaultSubscriptionManagerProvider;", "Lcom/amazon/mp3/billing/SubscriptionManagerProvider;", "()V", "evaluateSubscriptionCreation", "Lcom/amazon/music/subscription/EvaluateSubscriptionCreationResponse;", "paymentInfo", "Lcom/amazon/stratus/PaymentInfo;", "displayPrice", "Lcom/amazon/music/subscription/PriceWithCurrency;", ParserUtil.SCHEDULE_ID_QUERY_PARAM_NAME, "", Splash.PARAMS_SESSION_ID, "associatesTag", "empSku", "submitSubscriptionCreation", "Lcom/amazon/music/subscription/SubmitSubscriptionCreationResponse;", "orderInitToken", "productId", "receiptData", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DefaultSubscriptionManagerProvider implements SubscriptionManagerProvider {
    @Override // com.amazon.mp3.billing.SubscriptionManagerProvider
    public EvaluateSubscriptionCreationResponse evaluateSubscriptionCreation(PaymentInfo paymentInfo, PriceWithCurrency displayPrice, String scheduleId, String sessionId, String associatesTag, String empSku) throws Exception {
        Intrinsics.checkNotNullParameter(paymentInfo, "paymentInfo");
        Intrinsics.checkNotNullParameter(displayPrice, "displayPrice");
        Intrinsics.checkNotNullParameter(scheduleId, "scheduleId");
        Intrinsics.checkNotNullParameter(sessionId, "sessionId");
        if (!SubscriptionManagerSingleton.isInitialized()) {
            throw new IllegalStateException("SubscriptionManagerSingleton is not initialized yet");
        }
        EvaluateSubscriptionCreationResponse evaluateSubscriptionCreationResponseEvaluateSubscriptionCreation = SubscriptionManagerSingleton.getInstance().evaluateSubscriptionCreation(paymentInfo, displayPrice, scheduleId, sessionId, associatesTag, empSku);
        if (evaluateSubscriptionCreationResponseEvaluateSubscriptionCreation != null) {
            return evaluateSubscriptionCreationResponseEvaluateSubscriptionCreation;
        }
        throw new Exception("Null response, most likely due to timeout");
    }

    @Override // com.amazon.mp3.billing.SubscriptionManagerProvider
    public SubmitSubscriptionCreationResponse submitSubscriptionCreation(PaymentInfo paymentInfo, String orderInitToken, String productId, String receiptData) throws Exception {
        Intrinsics.checkNotNullParameter(paymentInfo, "paymentInfo");
        Intrinsics.checkNotNullParameter(orderInitToken, "orderInitToken");
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(receiptData, "receiptData");
        if (!SubscriptionManagerSingleton.isInitialized()) {
            throw new IllegalStateException("SubscriptionManagerSingleton is not initialized yet");
        }
        SubmitSubscriptionCreationResponse submitSubscriptionCreationResponseSubmitSubscriptionCreation = SubscriptionManagerSingleton.getInstance().submitSubscriptionCreation(paymentInfo, orderInitToken, productId, receiptData);
        if (submitSubscriptionCreationResponseSubmitSubscriptionCreation != null) {
            return submitSubscriptionCreationResponseSubmitSubscriptionCreation;
        }
        throw new Exception("Null response, most likely due to timeout");
    }
}
