package com.amazon.mp3.billing;

import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.subscription.EvaluateSubscriptionCreationResponse;
import com.amazon.music.subscription.PriceWithCurrency;
import com.amazon.music.subscription.SubmitSubscriptionCreationResponse;
import com.amazon.stratus.PaymentInfo;
import kotlin.Metadata;

/* JADX INFO: compiled from: SubscriptionManagerProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J<\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\t2\b\u0010\f\u001a\u0004\u0018\u00010\tH&J(\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\tH&¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/billing/SubscriptionManagerProvider;", "", "evaluateSubscriptionCreation", "Lcom/amazon/music/subscription/EvaluateSubscriptionCreationResponse;", "paymentInfo", "Lcom/amazon/stratus/PaymentInfo;", "displayPrice", "Lcom/amazon/music/subscription/PriceWithCurrency;", ParserUtil.SCHEDULE_ID_QUERY_PARAM_NAME, "", Splash.PARAMS_SESSION_ID, "associatesTag", "empSku", "submitSubscriptionCreation", "Lcom/amazon/music/subscription/SubmitSubscriptionCreationResponse;", "orderInitToken", "productId", "receiptData", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface SubscriptionManagerProvider {
    EvaluateSubscriptionCreationResponse evaluateSubscriptionCreation(PaymentInfo paymentInfo, PriceWithCurrency displayPrice, String scheduleId, String sessionId, String associatesTag, String empSku);

    SubmitSubscriptionCreationResponse submitSubscriptionCreation(PaymentInfo paymentInfo, String orderInitToken, String productId, String receiptData);
}
