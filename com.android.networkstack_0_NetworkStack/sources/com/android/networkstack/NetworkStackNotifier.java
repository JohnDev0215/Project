package com.android.networkstack;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.text.TextUtils;
import com.android.networkstack.NetworkStackNotifier;
import com.android.networkstack.apishim.NetworkInformationShimImpl;
import com.android.networkstack.apishim.common.CaptivePortalDataShim;
import com.android.networkstack.apishim.common.NetworkInformationShim;
import java.util.Hashtable;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: classes.dex */
public class NetworkStackNotifier {
    protected static final String CHANNEL_CONNECTED = "connected_note_loud";
    protected static final String CHANNEL_VENUE_INFO = "connected_note";
    protected static final long CONNECTED_NOTIFICATION_TIMEOUT_MS = 20000;
    private final Context mContext;
    private Network mDefaultNetwork;
    private final Dependencies mDependencies;
    private final Handler mHandler;
    private final NetworkInformationShim mInfoShim;
    private final Hashtable<Network, TrackedNetworkStatus> mNetworkStatus;
    private final NotificationManager mNotificationManager;

    /* JADX INFO: Access modifiers changed from: private */
    static class TrackedNetworkStatus {
        private LinkProperties mLinkProperties;
        private NetworkCapabilities mNetworkCapabilities;
        private int mShownNotification;
        private boolean mValidatedNotificationPending;

        private TrackedNetworkStatus() {
            this.mShownNotification = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isValidated() {
            NetworkCapabilities networkCapabilities = this.mNetworkCapabilities;
            if (networkCapabilities == null) {
                return false;
            }
            return networkCapabilities.hasCapability(16);
        }
    }

    protected static class Dependencies {
        protected Dependencies() {
        }

        public PendingIntent getActivityPendingIntent(Context context, Intent intent, int i) {
            return PendingIntent.getActivity(context, 0, intent, i);
        }
    }

    public NetworkStackNotifier(Context context, Looper looper) {
        this(context, looper, new Dependencies());
    }

    protected NetworkStackNotifier(Context context, Looper looper, Dependencies dependencies) {
        this.mNetworkStatus = new Hashtable<>();
        this.mInfoShim = NetworkInformationShimImpl.newInstance();
        this.mContext = context;
        this.mHandler = new Handler(looper);
        this.mDependencies = dependencies;
        this.mNotificationManager = (NotificationManager) getContextAsUser(this.mContext, UserHandle.ALL).getSystemService(NotificationManager.class);
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(ConnectivityManager.class);
        connectivityManager.registerDefaultNetworkCallback(new DefaultNetworkCallback(), this.mHandler);
        connectivityManager.registerNetworkCallback(new NetworkRequest.Builder().addTransportType(1).build(), new AllNetworksCallback(), this.mHandler);
        createNotificationChannel(CHANNEL_CONNECTED, R.string.notification_channel_name_connected, R.string.notification_channel_description_connected, 4);
        createNotificationChannel(CHANNEL_VENUE_INFO, R.string.notification_channel_name_network_venue_info, R.string.notification_channel_description_network_venue_info, 3);
    }

    protected Handler getHandler() {
        return this.mHandler;
    }

    private void createNotificationChannel(String str, int i, int i2, int i3) {
        Resources resources = this.mContext.getResources();
        NotificationChannel notificationChannel = new NotificationChannel(str, resources.getString(i), i3);
        notificationChannel.setDescription(resources.getString(i2));
        getNotificationManagerForChannels().createNotificationChannel(notificationChannel);
    }

    private NotificationManager getNotificationManagerForChannels() {
        return (NotificationManager) this.mContext.getSystemService(NotificationManager.class);
    }

    public void notifyCaptivePortalValidationPending(final Network network) {
        this.mHandler.post(new Runnable() { // from class: com.android.networkstack.-$$Lambda$NetworkStackNotifier$so3ltzQ_oU3xLz06HSylUo77-L4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$notifyCaptivePortalValidationPending$0$NetworkStackNotifier(network);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setCaptivePortalValidationPending, reason: merged with bridge method [inline-methods] */
    public void lambda$notifyCaptivePortalValidationPending$0$NetworkStackNotifier(Network network) {
        updateNetworkStatus(network, new Consumer() { // from class: com.android.networkstack.-$$Lambda$NetworkStackNotifier$6UgY1mKuzghjKiltzhKHA6DYlXw
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                NetworkStackNotifier.lambda$setCaptivePortalValidationPending$1((NetworkStackNotifier.TrackedNetworkStatus) obj);
            }
        });
    }

    static /* synthetic */ void lambda$setCaptivePortalValidationPending$1(TrackedNetworkStatus trackedNetworkStatus) {
        trackedNetworkStatus.mValidatedNotificationPending = true;
        trackedNetworkStatus.mShownNotification = 0;
    }

    private CaptivePortalDataShim getCaptivePortalData(TrackedNetworkStatus trackedNetworkStatus) {
        return this.mInfoShim.getCaptivePortalData(trackedNetworkStatus.mLinkProperties);
    }

    private String getSsid(TrackedNetworkStatus trackedNetworkStatus) {
        return this.mInfoShim.getSsid(trackedNetworkStatus.mNetworkCapabilities);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNetworkStatus(Network network, Consumer<TrackedNetworkStatus> consumer) {
        consumer.accept(this.mNetworkStatus.computeIfAbsent(network, new Function() { // from class: com.android.networkstack.-$$Lambda$NetworkStackNotifier$7ZHhPHEOdkJ-UR7SGYVNjqRfKug
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return NetworkStackNotifier.lambda$updateNetworkStatus$2((Network) obj);
            }
        }));
    }

    static /* synthetic */ TrackedNetworkStatus lambda$updateNetworkStatus$2(Network network) {
        return new TrackedNetworkStatus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNotifications(Network network) {
        Notification.Builder contentIntent;
        TrackedNetworkStatus trackedNetworkStatus = this.mNetworkStatus.get(network);
        if (trackedNetworkStatus == null) {
            return;
        }
        boolean z = !TextUtils.isEmpty(getSsid(trackedNetworkStatus));
        CaptivePortalDataShim captivePortalData = getCaptivePortalData(trackedNetworkStatus);
        boolean z2 = captivePortalData != null && captivePortalData.getVenueInfoUrl() != null && trackedNetworkStatus.isValidated() && isVenueInfoNotificationEnabled() && network.equals(this.mDefaultNetwork) && z;
        boolean z3 = trackedNetworkStatus.mValidatedNotificationPending && trackedNetworkStatus.isValidated() && z;
        String notificationTag = getNotificationTag(network);
        Resources resources = this.mContext.getResources();
        String str = CHANNEL_CONNECTED;
        if (z2) {
            if (trackedNetworkStatus.mShownNotification == 2) {
                return;
            }
            Intent identifier = new Intent("android.intent.action.VIEW").setFlags(268435456).setData(captivePortalData.getVenueInfoUrl()).putExtra("android.net.extra.NETWORK", network).setIdentifier(Long.toString(network.getNetworkHandle()));
            if (!z3) {
                str = CHANNEL_VENUE_INFO;
            }
            contentIntent = getNotificationBuilder(str, trackedNetworkStatus, resources, getSsid(trackedNetworkStatus)).setContentText(resources.getString(R.string.tap_for_info)).setContentIntent(this.mDependencies.getActivityPendingIntent(getContextAsUser(this.mContext, UserHandle.CURRENT), identifier, 67108864));
            trackedNetworkStatus.mShownNotification = 2;
        } else if (z3) {
            if (trackedNetworkStatus.mShownNotification == 1) {
                return;
            }
            contentIntent = getNotificationBuilder(CHANNEL_CONNECTED, trackedNetworkStatus, resources, getSsid(trackedNetworkStatus)).setTimeoutAfter(CONNECTED_NOTIFICATION_TIMEOUT_MS).setContentText(resources.getString(R.string.connected)).setContentIntent(this.mDependencies.getActivityPendingIntent(getContextAsUser(this.mContext, UserHandle.CURRENT), new Intent("android.settings.WIFI_SETTINGS"), 67108864));
            trackedNetworkStatus.mShownNotification = 1;
        } else {
            if (trackedNetworkStatus.mShownNotification == 0 || trackedNetworkStatus.mShownNotification == 1) {
                return;
            }
            dismissNotification(notificationTag, trackedNetworkStatus);
            return;
        }
        if (z3) {
            trackedNetworkStatus.mValidatedNotificationPending = false;
        }
        this.mNotificationManager.notify(notificationTag, 1, contentIntent.build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissNotification(String str, TrackedNetworkStatus trackedNetworkStatus) {
        this.mNotificationManager.cancel(str, 1);
        trackedNetworkStatus.mShownNotification = 0;
    }

    private Notification.Builder getNotificationBuilder(String str, TrackedNetworkStatus trackedNetworkStatus, Resources resources, String str2) {
        return new Notification.Builder(this.mContext, str).setContentTitle(str2).setSmallIcon(R.drawable.icon_wifi);
    }

    private static Context getContextAsUser(Context context, UserHandle userHandle) {
        try {
            return context.createPackageContextAsUser(context.getPackageName(), 0, userHandle);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("NetworkStack own package not found", e);
        }
    }

    private boolean isVenueInfoNotificationEnabled() {
        NotificationChannel notificationChannel = getNotificationManagerForChannels().getNotificationChannel(CHANNEL_VENUE_INFO);
        return (notificationChannel == null || notificationChannel.getImportance() == 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getNotificationTag(Network network) {
        return Long.toString(network.getNetworkHandle());
    }

    private class DefaultNetworkCallback extends ConnectivityManager.NetworkCallback {
        private DefaultNetworkCallback() {
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            updateDefaultNetwork(network);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            updateDefaultNetwork(null);
        }

        private void updateDefaultNetwork(Network network) {
            Network network2 = NetworkStackNotifier.this.mDefaultNetwork;
            NetworkStackNotifier.this.mDefaultNetwork = network;
            if (network2 != null) {
                NetworkStackNotifier.this.updateNotifications(network2);
            }
            if (network != null) {
                NetworkStackNotifier.this.updateNotifications(network);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class AllNetworksCallback extends ConnectivityManager.NetworkCallback {
        private AllNetworksCallback() {
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLinkPropertiesChanged(Network network, final LinkProperties linkProperties) {
            NetworkStackNotifier.this.updateNetworkStatus(network, new Consumer() { // from class: com.android.networkstack.-$$Lambda$NetworkStackNotifier$AllNetworksCallback$vW0ScR-CnMHF7OmlO7Y6qtqJ6Us
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((NetworkStackNotifier.TrackedNetworkStatus) obj).mLinkProperties = linkProperties;
                }
            });
            NetworkStackNotifier.this.updateNotifications(network);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(Network network, final NetworkCapabilities networkCapabilities) {
            NetworkStackNotifier.this.updateNetworkStatus(network, new Consumer() { // from class: com.android.networkstack.-$$Lambda$NetworkStackNotifier$AllNetworksCallback$VVBD4s8gGCOEP2T5lZR-qf3ws04
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((NetworkStackNotifier.TrackedNetworkStatus) obj).mNetworkCapabilities = networkCapabilities;
                }
            });
            NetworkStackNotifier.this.updateNotifications(network);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            TrackedNetworkStatus trackedNetworkStatus = (TrackedNetworkStatus) NetworkStackNotifier.this.mNetworkStatus.remove(network);
            if (trackedNetworkStatus == null) {
                return;
            }
            NetworkStackNotifier.this.dismissNotification(NetworkStackNotifier.getNotificationTag(network), trackedNetworkStatus);
        }
    }
}
