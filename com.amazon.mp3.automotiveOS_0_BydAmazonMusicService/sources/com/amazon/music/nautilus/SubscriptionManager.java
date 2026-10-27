package com.amazon.music.nautilus;

import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.amazon.music.subscription.EvaluateSubscriptionCreationResponse;
import com.amazon.music.subscription.PriceWithCurrency;
import com.amazon.music.subscription.SubmitSubscriptionCreationResponse;
import com.amazon.stratus.PaymentInfo;
import com.android.volley.VolleyError;
import java.util.Arrays;
import java.util.HashSet;
import org.apache.commons.lang3.Validate;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class SubscriptionManager {
    private final MusicSubscriptionStateChangeListenerDispatcher musicSubscriptionStateChangeListenerDispatcher;
    private final SubscriptionCache subscriptionCache;
    private final SubscriptionCacheSaver subscriptionCacheSaver;
    private final SubscriptionManagementService subscriptionManagementService;
    private final UpdateService updateService;

    public SubscriptionManager(MusicSubscriptionServiceConfiguration musicSubscriptionServiceConfiguration) {
        Validate.notNull(musicSubscriptionServiceConfiguration);
        SubscriptionCache subscriptionCache = new SubscriptionCache();
        this.subscriptionCache = subscriptionCache;
        this.subscriptionCacheSaver = new SubscriptionCacheSaver(subscriptionCache, new MusicSubscriptionStateComparator());
        MusicSubscriptionStateChangeListenerDispatcher musicSubscriptionStateChangeListenerDispatcher = new MusicSubscriptionStateChangeListenerDispatcher();
        this.musicSubscriptionStateChangeListenerDispatcher = musicSubscriptionStateChangeListenerDispatcher;
        this.updateService = new UpdateService(new UpdateServiceRunnableFactory(subscriptionCache, musicSubscriptionServiceConfiguration, musicSubscriptionStateChangeListenerDispatcher));
        this.subscriptionManagementService = new SubscriptionManagementService(new MusicSubscriptionService(musicSubscriptionServiceConfiguration), new SubscriptionManagementServiceResponseParser(), new SubscriptionManagmentServiceRequestBuilder(musicSubscriptionServiceConfiguration));
        if (musicSubscriptionServiceConfiguration.shouldDisableInitialUpdate()) {
            return;
        }
        update();
    }

    public EvaluateSubscriptionCreationResponse evaluateSubscriptionCreation(PaymentInfo paymentInfo, PriceWithCurrency priceWithCurrency, String str, String str2, String str3, String str4) throws VolleyError {
        return this.subscriptionManagementService.evaluateSubscriptionFromService(paymentInfo, priceWithCurrency, str, str2, str3, str4);
    }

    public SubmitSubscriptionCreationResponse submitSubscriptionCreation(PaymentInfo paymentInfo, String str, String str2, String str3) throws VolleyError {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("schema", "amazon.payments.emp.googleInApp@2.0");
            jSONObject.put("applicationId", "com.amazon.mp3");
            jSONObject.put("isSubscriptionProduct", true);
            jSONObject.put("productType", "SUBSCRIPTION");
            jSONObject.put(CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME, "com.amazon.mp3");
            jSONObject.put("productId", str2);
            jSONObject.put("token", str3);
            return this.subscriptionManagementService.submitSubscriptionFromService(paymentInfo, str, jSONObject.toString());
        } catch (JSONException e) {
            throw new IllegalArgumentException("EmpBillingDetails json object was not constructed correctly", e);
        }
    }

    public boolean hasFreeTrial() {
        SubscriptionOffers cachedSubscriptionOffers = this.subscriptionCache.getCachedSubscriptionOffers();
        if (cachedSubscriptionOffers == null) {
            return false;
        }
        return cachedSubscriptionOffers.hasFreeTrial();
    }

    public boolean isOnHomeOnlyTrial() {
        Subscription cachedSubscription = this.subscriptionCache.getCachedSubscription();
        return (cachedSubscription == null || cachedSubscription.getPlanId() == null || cachedSubscription.getRolloverPlanId() == null || !cachedSubscription.getPlanId().equals("HAWKFIRE_ALL_DEVICES_TRIAL") || !cachedSubscription.getRolloverPlanId().equals("HAWKFIRE_HOME_ONLY_MONTHLY")) ? false : true;
    }

    public boolean isOnGoogleInAppBillingPlan() {
        Subscription cachedSubscription = this.subscriptionCache.getCachedSubscription();
        if (cachedSubscription == null || cachedSubscription.getPlanId() == null) {
            return false;
        }
        return cachedSubscription.getPlanId().startsWith("ANDROID_");
    }

    public boolean isOn1PPlan() {
        Subscription cachedSubscription = this.subscriptionCache.getCachedSubscription();
        if (cachedSubscription == null || cachedSubscription.getPlanId() == null) {
            return false;
        }
        return cachedSubscription.getPlanId().startsWith("HAWKFIRE_");
    }

    public boolean isOnFamilyPlan() {
        Subscription cachedSubscription = this.subscriptionCache.getCachedSubscription();
        if (cachedSubscription == null || cachedSubscription.getPlanId() == null) {
            return false;
        }
        return new HashSet(Arrays.asList("HAWKFIRE_FAMILY_MONTHLY", "HAWKFIRE_FAMILY_ANNUAL", "HAWKFIRE_FAMILY_TRIAL")).contains(cachedSubscription.getPlanId());
    }

    public synchronized void update() {
        this.updateService.update();
    }

    public void registerListener(MusicSubscriptionStateChangeListener musicSubscriptionStateChangeListener) {
        this.musicSubscriptionStateChangeListenerDispatcher.registerListener(musicSubscriptionStateChangeListener);
    }

    public void deregisterListener(MusicSubscriptionStateChangeListener musicSubscriptionStateChangeListener) {
        this.musicSubscriptionStateChangeListenerDispatcher.deregisterListener(musicSubscriptionStateChangeListener);
    }
}
