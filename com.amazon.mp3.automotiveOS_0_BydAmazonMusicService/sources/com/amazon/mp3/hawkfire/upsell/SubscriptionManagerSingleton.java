package com.amazon.mp3.hawkfire.upsell;

import android.content.Context;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.environment.MusicURL;
import com.amazon.mp3.net.RequestInterceptorFactory;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.weblab.ExperimentWeblabs;
import com.amazon.mp3.weblab.LegacyWeblabController;
import com.amazon.mp3.weblab.Weblab;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.AccountStateChangeListener;
import com.amazon.music.account.AccountStateField;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.nautilus.MSOSConfiguration;
import com.amazon.music.nautilus.MSOSSubscriptionManager;
import com.amazon.music.nautilus.MusicSubscriptionServiceConfiguration;
import com.amazon.music.nautilus.SubscriptionManager;
import com.amazon.music.platform.data.WeblabTreatment;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes3.dex */
public class SubscriptionManagerSingleton {
    private static MSOSSubscriptionManager msosSubscriptionManager;
    private static SubscriptionManager sSubscriptionManager;
    private static AtomicBoolean initialized = new AtomicBoolean(false);
    private static final String TAG = SubscriptionManagerSingleton.class.getSimpleName();

    public static void initialize(final Context context) {
        synchronized (SubscriptionManagerSingleton.class) {
            initialized.set(true);
            sSubscriptionManager = buildSubscriptionManager(context.getApplicationContext());
            msosSubscriptionManager = buildMSOSSubscriptionManager(context.getApplicationContext());
        }
        setAccountStateChangedListener();
        sSubscriptionManager.update();
        WeblabTreatment treatmentAndDontTrigger = LegacyWeblabController.getInstance().getTreatmentAndDontTrigger(Weblab.DM_SUBS_1CLICK_CONCURRENCY_FAMILY_SIGNUP_UPSELL_AB.toString());
        WeblabTreatment treatmentAndDontTrigger2 = LegacyWeblabController.getInstance().getTreatmentAndDontTrigger(ExperimentWeblabs.DM_SUBS_CONCURRENCY_ONE_CLICK_SIGNUP.getAbWeblab());
        WeblabTreatment treatmentAndDontTrigger3 = LegacyWeblabController.getInstance().getTreatmentAndDontTrigger(Weblab.DM_SUBS_1CLICK_CONCURRENCY_FAMILY_UPGRADE_UPSELL_AB.toString());
        WeblabTreatment weblabTreatment = WeblabTreatment.T1;
        if ((weblabTreatment.equals(treatmentAndDontTrigger) || weblabTreatment.equals(treatmentAndDontTrigger2) || weblabTreatment.equals(treatmentAndDontTrigger3)) && isTerritorySupportedByMSOS()) {
            try {
                msosSubscriptionManager.update();
            } catch (Exception e) {
                Log.error(TAG, e.getMessage());
            }
        }
    }

    public static synchronized boolean isInitialized() {
        return initialized.get();
    }

    public static synchronized SubscriptionManager getInstance() {
        if (initialized.get()) {
        } else {
            throw new IllegalStateException("Cannot call getInstance() without first calling initialize()");
        }
        return sSubscriptionManager;
    }

    public static synchronized MSOSSubscriptionManager getMSOSInstance() throws IllegalStateException, UnsupportedMarketplaceException {
        if (initialized.get()) {
            if (!isTerritorySupportedByMSOS()) {
                throw new UnsupportedMarketplaceException(String.format("Unsupported territory %s", AccountDetailUtil.getMusicTerritoryOfResidence()));
            }
        } else {
            throw new IllegalStateException("Cannot call getMSOSInstance() without first calling initialize()");
        }
        return msosSubscriptionManager;
    }

    private static boolean isTerritorySupportedByMSOS() {
        return Environment.RESOV2_URL.get() != null;
    }

    private static MSOSSubscriptionManager buildMSOSSubscriptionManager(Context context) {
        String deviceId = AccountCredentialStorage.get(context).getDeviceId();
        String deviceType = AccountCredentialStorage.get(context).getDeviceType();
        AccountDetailUtil.get(context);
        try {
            return new MSOSSubscriptionManager(new MSOSConfiguration(new MSOSConfiguration.Builder(deviceId, deviceType, AccountDetailUtil.getMusicTerritoryOfResidence(), Environment.RESOV2_URL.getUrl(), new RequestInterceptorFactory(context.getApplicationContext()).provideRequestInterceptor())));
        } catch (Exception e) {
            Log.error(TAG, "Could not create MSOSConfiguration", e);
            return null;
        }
    }

    private static SubscriptionManager buildSubscriptionManager(Context context) {
        AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(context);
        String deviceId = accountCredentialStorage.getDeviceId();
        String deviceType = accountCredentialStorage.getDeviceType();
        AccountDetailUtil.get(context);
        return new SubscriptionManager(new MusicSubscriptionServiceConfiguration.Builder(deviceId, deviceType, AccountDetailUtil.getMusicTerritoryOfResidence(), MusicURL.getNautilusUrl(), new RequestInterceptorFactory(context).provideRequestInterceptor()).withInitialUpdateDisabled().build());
    }

    private static void setAccountStateChangedListener() {
        AccountManagerSingleton.get().registerListener(new AccountStateChangeListener() { // from class: com.amazon.mp3.hawkfire.upsell.SubscriptionManagerSingleton$$ExternalSyntheticLambda0
            @Override // com.amazon.music.account.AccountStateChangeListener
            public final void onAccountStateChange(Set set) {
                SubscriptionManagerSingleton.lambda$setAccountStateChangedListener$1(set);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$setAccountStateChangedListener$1(Set set) {
        if (set.contains(AccountStateField.USER_BENEFITS)) {
            ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.hawkfire.upsell.SubscriptionManagerSingleton$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    SubscriptionManagerSingleton.lambda$setAccountStateChangedListener$0();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$setAccountStateChangedListener$0() {
        try {
            msosSubscriptionManager.update();
        } catch (Exception e) {
            Log.error(TAG, e.getMessage());
        }
    }
}
