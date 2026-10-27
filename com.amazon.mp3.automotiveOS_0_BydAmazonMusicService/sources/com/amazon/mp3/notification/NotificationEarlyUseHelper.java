package com.amazon.mp3.notification;

import android.app.Activity;
import android.app.NotificationChannel;
import android.content.Context;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.activity.ATCWebActivity;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.client.activity.LauncherActivity;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.ftu.FTUActivity;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.inappmessaging.internal.model.Splash;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NotificationEarlyUseHelper.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0017B\t\b\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0018\u0010\b\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0018\u0010\n\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0018\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\rH\u0002J\u0016\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0011J\u000e\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/notification/NotificationEarlyUseHelper;", "", "Landroid/app/Activity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Lcom/amazon/mp3/notification/NotificationEarlyUseHelper$CustomerDropFrom;", "customerDropFrom", "", "shouldShowEarlyUseExperiment", "isDropFromOnboardingFlow", "", "startNotificationWork", "Landroidx/work/Data;", "getNotificationData", "Landroidx/core/app/NotificationManagerCompat;", "notificationManager", "areNotificationsEnabled", "triggerNotificationEarlyUseExperiment", "Landroid/content/Context;", "context", "cancelNotification", "getCustomerDropFrom", "<init>", "()V", "CustomerDropFrom", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class NotificationEarlyUseHelper {
    public static final NotificationEarlyUseHelper INSTANCE = new NotificationEarlyUseHelper();

    /* JADX INFO: compiled from: NotificationEarlyUseHelper.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/notification/NotificationEarlyUseHelper$CustomerDropFrom;", "", "(Ljava/lang/String;I)V", "ATC", "FTU", "OTHERS", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum CustomerDropFrom {
        ATC,
        FTU,
        OTHERS
    }

    private NotificationEarlyUseHelper() {
    }

    public final void triggerNotificationEarlyUseExperiment(Activity activity, CustomerDropFrom customerDropFrom) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(customerDropFrom, "customerDropFrom");
        if (shouldShowEarlyUseExperiment(activity, customerDropFrom)) {
            NotificationEarlyUseMetricsUtil.INSTANCE.trackEvent(customerDropFrom);
            startNotificationWork(activity, customerDropFrom);
        }
    }

    public final void cancelNotification(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        WorkManager.getInstance(context).cancelAllWorkByTag("early_use_notification");
    }

    public final CustomerDropFrom getCustomerDropFrom(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (activity instanceof ATCWebActivity) {
            ATCWebActivity aTCWebActivity = (ATCWebActivity) activity;
            if (!aTCWebActivity.getCustomerCompletedATC() && (aTCWebActivity.getCustomerFlow() == ATCWebActivity.CustomerFlow.firstTimeCustomer || Intrinsics.areEqual(aTCWebActivity.getRefMarker(), "dm_early_use_ftu_push"))) {
                return CustomerDropFrom.ATC;
            }
            return CustomerDropFrom.OTHERS;
        }
        if (activity instanceof FTUActivity) {
            Boolean isShowingIAM = ((FTUActivity) activity).getIsShowingIAM();
            Intrinsics.checkNotNullExpressionValue(isShowingIAM, "activity.isShowingIAM");
            if (isShowingIAM.booleanValue()) {
                return CustomerDropFrom.FTU;
            }
            return CustomerDropFrom.OTHERS;
        }
        return CustomerDropFrom.OTHERS;
    }

    private final boolean shouldShowEarlyUseExperiment(Activity activity, CustomerDropFrom customerDropFrom) {
        if (Feature.early_use_notification.isEnabled(true) && customerDropFrom != CustomerDropFrom.OTHERS && isDropFromOnboardingFlow(activity, customerDropFrom)) {
            NotificationManagerCompat notificationManagerCompatFrom = NotificationManagerCompat.from(activity.getApplicationContext());
            Intrinsics.checkNotNullExpressionValue(notificationManagerCompatFrom, "from(activity.applicationContext)");
            if (areNotificationsEnabled(notificationManagerCompatFrom)) {
                return true;
            }
        }
        return false;
    }

    private final boolean isDropFromOnboardingFlow(Activity activity, CustomerDropFrom customerDropFrom) {
        return (activity instanceof ATCWebActivity) || (activity instanceof FTUActivity) || (activity instanceof LauncherActivity) || ((activity instanceof MusicHomeActivity) && customerDropFrom == CustomerDropFrom.ATC);
    }

    private final void startNotificationWork(Activity activity, CustomerDropFrom customerDropFrom) {
        OneTimeWorkRequest oneTimeWorkRequestBuild = new OneTimeWorkRequest.Builder(DelayNotificationWorker.class).addTag("early_use_notification").setInputData(getNotificationData(activity, customerDropFrom)).build();
        Intrinsics.checkNotNullExpressionValue(oneTimeWorkRequestBuild, "Builder(DelayNotificatio…                 .build()");
        WorkManager.getInstance(activity.getApplicationContext()).enqueue(oneTimeWorkRequestBuild);
    }

    private final Data getNotificationData(Activity activity, CustomerDropFrom customerDropFrom) throws Throwable {
        Context applicationContext = activity.getApplicationContext();
        int i = 0;
        if (customerDropFrom != CustomerDropFrom.FTU) {
            Pair[] pairArr = {TuplesKt.to("notification_title", applicationContext.getString(R.string.dmusic_early_use_notification_ATC_title)), TuplesKt.to("notification_body", applicationContext.getString(R.string.dmusic_early_use_notification_ATC_body)), TuplesKt.to("deeplink", EndpointsProvider.get().getEarlyUseNotificationATCDeeplinkEndpoint()), TuplesKt.to(Splash.PARAMS_CAMPAIGN_ID, "ENG-Onboarding-ATC-Notification")};
            Data.Builder builder = new Data.Builder();
            while (i < 4) {
                Pair pair = pairArr[i];
                i++;
                builder.put((String) pair.getFirst(), pair.getSecond());
            }
            Data dataBuild = builder.build();
            Intrinsics.checkNotNullExpressionValue(dataBuild, "dataBuilder.build()");
            return dataBuild;
        }
        if (UserSubscriptionUtil.getUserSubscription().isNightwingOnly()) {
            Pair[] pairArr2 = {TuplesKt.to("notification_title", applicationContext.getString(R.string.dmusic_early_use_notification_FTU_nightwing_title)), TuplesKt.to("notification_body", applicationContext.getString(R.string.dmusic_early_use_notification_FTU_nightwing_body)), TuplesKt.to("deeplink", EndpointsProvider.get().getEarlyUseNotificationFTUDeeplinkEndpoint()), TuplesKt.to(Splash.PARAMS_CAMPAIGN_ID, "ENG-Onboarding-FTU-Notification")};
            Data.Builder builder2 = new Data.Builder();
            while (i < 4) {
                Pair pair2 = pairArr2[i];
                i++;
                builder2.put((String) pair2.getFirst(), pair2.getSecond());
            }
            Data dataBuild2 = builder2.build();
            Intrinsics.checkNotNullExpressionValue(dataBuild2, "dataBuilder.build()");
            return dataBuild2;
        }
        if (UserSubscriptionUtil.isHawkfire() || !(UserSubscriptionUtil.getUserSubscription().isPrimeOnly() || UserSubscriptionUtil.getUserSubscription().isSonicRush())) {
            Pair[] pairArr3 = {TuplesKt.to("notification_title", applicationContext.getString(R.string.dmusic_early_use_notification_FTU_hawkfire_title)), TuplesKt.to("notification_body", applicationContext.getString(R.string.dmusic_early_use_notification_FTU_hawkfire_body)), TuplesKt.to("deeplink", EndpointsProvider.get().getEarlyUseNotificationFTUDeeplinkEndpoint()), TuplesKt.to(Splash.PARAMS_CAMPAIGN_ID, "ENG-Onboarding-FTU-Notification")};
            Data.Builder builder3 = new Data.Builder();
            while (i < 4) {
                Pair pair3 = pairArr3[i];
                i++;
                builder3.put((String) pair3.getFirst(), pair3.getSecond());
            }
            Data dataBuild3 = builder3.build();
            Intrinsics.checkNotNullExpressionValue(dataBuild3, "dataBuilder.build()");
            return dataBuild3;
        }
        Pair[] pairArr4 = {TuplesKt.to("notification_title", applicationContext.getString(R.string.dmusic_early_use_notification_FTU_prime_title)), TuplesKt.to("notification_body", applicationContext.getString(R.string.dmusic_early_use_notification_FTU_prime_body)), TuplesKt.to("deeplink", EndpointsProvider.get().getEarlyUseNotificationFTUDeeplinkEndpoint()), TuplesKt.to(Splash.PARAMS_CAMPAIGN_ID, "ENG-Onboarding-FTU-Notification")};
        Data.Builder builder4 = new Data.Builder();
        while (i < 4) {
            Pair pair4 = pairArr4[i];
            i++;
            builder4.put((String) pair4.getFirst(), pair4.getSecond());
        }
        Data dataBuild4 = builder4.build();
        Intrinsics.checkNotNullExpressionValue(dataBuild4, "dataBuilder.build()");
        return dataBuild4;
    }

    private final boolean areNotificationsEnabled(NotificationManagerCompat notificationManager) {
        Object next;
        if (notificationManager.areNotificationsEnabled()) {
            if (!PlatformUtil.isVersionOrGreater(26)) {
                return true;
            }
            List<NotificationChannel> notificationChannels = notificationManager.getNotificationChannels();
            Intrinsics.checkNotNullExpressionValue(notificationChannels, "notificationManager.notificationChannels");
            Iterator<T> it = notificationChannels.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (((NotificationChannel) next).getImportance() == 0) {
                    break;
                }
            }
            if (next == null) {
                return true;
            }
        }
        return false;
    }
}
