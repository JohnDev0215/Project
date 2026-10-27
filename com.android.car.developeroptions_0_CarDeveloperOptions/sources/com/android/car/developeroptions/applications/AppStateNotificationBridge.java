package com.android.car.developeroptions.applications;

import android.app.usage.IUsageStatsManager;
import android.app.usage.UsageEvents;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.applications.AppStateBaseBridge;
import com.android.car.developeroptions.notification.NotificationBackend;
import com.android.settingslib.applications.ApplicationsState;
import com.android.settingslib.utils.StringUtil;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class AppStateNotificationBridge extends AppStateBaseBridge {
    private NotificationBackend mBackend;
    private final Context mContext;
    private IUsageStatsManager mUsageStatsManager;
    protected List<Integer> mUserIds;
    public static final ApplicationsState.AppFilter FILTER_APP_NOTIFICATION_RECENCY = new ApplicationsState.AppFilter() { // from class: com.android.car.developeroptions.applications.AppStateNotificationBridge.1
        @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
        public void init() {
        }

        @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
        public boolean filterApp(ApplicationsState.AppEntry appEntry) {
            NotificationsSentState notificationsSentState = AppStateNotificationBridge.getNotificationsSentState(appEntry);
            return (notificationsSentState == null || notificationsSentState.lastSent == 0) ? false : true;
        }
    };
    public static final ApplicationsState.AppFilter FILTER_APP_NOTIFICATION_FREQUENCY = new ApplicationsState.AppFilter() { // from class: com.android.car.developeroptions.applications.AppStateNotificationBridge.2
        @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
        public void init() {
        }

        @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
        public boolean filterApp(ApplicationsState.AppEntry appEntry) {
            NotificationsSentState notificationsSentState = AppStateNotificationBridge.getNotificationsSentState(appEntry);
            return (notificationsSentState == null || notificationsSentState.sentCount == 0) ? false : true;
        }
    };
    public static final ApplicationsState.AppFilter FILTER_APP_NOTIFICATION_BLOCKED = new ApplicationsState.AppFilter() { // from class: com.android.car.developeroptions.applications.AppStateNotificationBridge.3
        @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
        public void init() {
        }

        @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
        public boolean filterApp(ApplicationsState.AppEntry appEntry) {
            NotificationsSentState notificationsSentState = AppStateNotificationBridge.getNotificationsSentState(appEntry);
            if (notificationsSentState != null) {
                return notificationsSentState.blocked;
            }
            return false;
        }
    };
    public static final Comparator<ApplicationsState.AppEntry> RECENT_NOTIFICATION_COMPARATOR = new Comparator<ApplicationsState.AppEntry>() { // from class: com.android.car.developeroptions.applications.AppStateNotificationBridge.4
        @Override // java.util.Comparator
        public int compare(ApplicationsState.AppEntry appEntry, ApplicationsState.AppEntry appEntry2) {
            NotificationsSentState notificationsSentState = AppStateNotificationBridge.getNotificationsSentState(appEntry);
            NotificationsSentState notificationsSentState2 = AppStateNotificationBridge.getNotificationsSentState(appEntry2);
            if (notificationsSentState == null && notificationsSentState2 != null) {
                return -1;
            }
            if (notificationsSentState != null && notificationsSentState2 == null) {
                return 1;
            }
            if (notificationsSentState != null && notificationsSentState2 != null) {
                long j = notificationsSentState.lastSent;
                long j2 = notificationsSentState2.lastSent;
                if (j < j2) {
                    return 1;
                }
                if (j > j2) {
                    return -1;
                }
            }
            return ApplicationsState.ALPHA_COMPARATOR.compare(appEntry, appEntry2);
        }
    };
    public static final Comparator<ApplicationsState.AppEntry> FREQUENCY_NOTIFICATION_COMPARATOR = new Comparator<ApplicationsState.AppEntry>() { // from class: com.android.car.developeroptions.applications.AppStateNotificationBridge.5
        @Override // java.util.Comparator
        public int compare(ApplicationsState.AppEntry appEntry, ApplicationsState.AppEntry appEntry2) {
            NotificationsSentState notificationsSentState = AppStateNotificationBridge.getNotificationsSentState(appEntry);
            NotificationsSentState notificationsSentState2 = AppStateNotificationBridge.getNotificationsSentState(appEntry2);
            if (notificationsSentState == null && notificationsSentState2 != null) {
                return -1;
            }
            if (notificationsSentState != null && notificationsSentState2 == null) {
                return 1;
            }
            if (notificationsSentState != null && notificationsSentState2 != null) {
                int i = notificationsSentState.sentCount;
                int i2 = notificationsSentState2.sentCount;
                if (i < i2) {
                    return 1;
                }
                if (i > i2) {
                    return -1;
                }
            }
            return ApplicationsState.ALPHA_COMPARATOR.compare(appEntry, appEntry2);
        }
    };

    public static class NotificationsSentState {
        public boolean blockable;
        public boolean blocked;
        public boolean systemApp;
        public int avgSentDaily = 0;
        public int avgSentWeekly = 0;
        public long lastSent = 0;
        public int sentCount = 0;
    }

    public AppStateNotificationBridge(Context context, ApplicationsState applicationsState, AppStateBaseBridge.Callback callback, IUsageStatsManager iUsageStatsManager, UserManager userManager, NotificationBackend notificationBackend) {
        super(applicationsState, callback);
        this.mContext = context;
        this.mUsageStatsManager = iUsageStatsManager;
        this.mBackend = notificationBackend;
        ArrayList arrayList = new ArrayList();
        this.mUserIds = arrayList;
        arrayList.add(Integer.valueOf(this.mContext.getUserId()));
        int managedProfileId = Utils.getManagedProfileId(userManager, this.mContext.getUserId());
        if (managedProfileId != -10000) {
            this.mUserIds.add(Integer.valueOf(managedProfileId));
        }
    }

    @Override // com.android.car.developeroptions.applications.AppStateBaseBridge
    protected void loadAllExtraInfo() {
        ArrayList<ApplicationsState.AppEntry> allApps = this.mAppSession.getAllApps();
        if (allApps == null) {
            Log.d("AppStateNotificationBridge", "No apps.  No extra info loaded");
            return;
        }
        Map<String, NotificationsSentState> aggregatedUsageEvents = getAggregatedUsageEvents();
        for (ApplicationsState.AppEntry appEntry : allApps) {
            NotificationsSentState notificationsSentState = aggregatedUsageEvents.get(getKey(UserHandle.getUserId(appEntry.info.uid), appEntry.info.packageName));
            calculateAvgSentCounts(notificationsSentState);
            addBlockStatus(appEntry, notificationsSentState);
            appEntry.extraInfo = notificationsSentState;
        }
    }

    @Override // com.android.car.developeroptions.applications.AppStateBaseBridge
    protected void updateExtraInfo(ApplicationsState.AppEntry appEntry, String str, int i) {
        NotificationsSentState aggregatedUsageEvents = getAggregatedUsageEvents(UserHandle.getUserId(appEntry.info.uid), appEntry.info.packageName);
        calculateAvgSentCounts(aggregatedUsageEvents);
        addBlockStatus(appEntry, aggregatedUsageEvents);
        appEntry.extraInfo = aggregatedUsageEvents;
    }

    public static CharSequence getSummary(Context context, NotificationsSentState notificationsSentState, int i) {
        if (i == R.id.sort_order_recent_notification) {
            if (notificationsSentState.lastSent == 0) {
                return context.getString(R.string.notifications_sent_never);
            }
            return StringUtil.formatRelativeTime(context, System.currentTimeMillis() - notificationsSentState.lastSent, true);
        }
        if (i != R.id.sort_order_frequent_notification) {
            return "";
        }
        int i2 = notificationsSentState.avgSentWeekly;
        return i2 > 0 ? context.getString(R.string.notifications_sent_weekly, Integer.valueOf(i2)) : context.getString(R.string.notifications_sent_daily, Integer.valueOf(notificationsSentState.avgSentDaily));
    }

    private void addBlockStatus(ApplicationsState.AppEntry appEntry, NotificationsSentState notificationsSentState) {
        if (notificationsSentState != null) {
            NotificationBackend notificationBackend = this.mBackend;
            ApplicationInfo applicationInfo = appEntry.info;
            notificationsSentState.blocked = notificationBackend.getNotificationsBanned(applicationInfo.packageName, applicationInfo.uid);
            boolean zIsSystemApp = this.mBackend.isSystemApp(this.mContext, appEntry.info);
            notificationsSentState.systemApp = zIsSystemApp;
            notificationsSentState.blockable = !zIsSystemApp || (zIsSystemApp && notificationsSentState.blocked);
        }
    }

    private void calculateAvgSentCounts(NotificationsSentState notificationsSentState) {
        if (notificationsSentState != null) {
            notificationsSentState.avgSentDaily = Math.round(notificationsSentState.sentCount / 7.0f);
            int i = notificationsSentState.sentCount;
            if (i < 7) {
                notificationsSentState.avgSentWeekly = i;
            }
        }
    }

    protected Map<String, NotificationsSentState> getAggregatedUsageEvents() {
        ArrayMap arrayMap = new ArrayMap();
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = jCurrentTimeMillis - 604800000;
        Iterator<Integer> it = this.mUserIds.iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            UsageEvents usageEventsQueryEventsForUser = null;
            try {
                usageEventsQueryEventsForUser = this.mUsageStatsManager.queryEventsForUser(j, jCurrentTimeMillis, iIntValue, this.mContext.getPackageName());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            if (usageEventsQueryEventsForUser != null) {
                UsageEvents.Event event = new UsageEvents.Event();
                while (usageEventsQueryEventsForUser.hasNextEvent()) {
                    usageEventsQueryEventsForUser.getNextEvent(event);
                    NotificationsSentState notificationsSentState = (NotificationsSentState) arrayMap.get(getKey(iIntValue, event.getPackageName()));
                    if (notificationsSentState == null) {
                        notificationsSentState = new NotificationsSentState();
                        arrayMap.put(getKey(iIntValue, event.getPackageName()), notificationsSentState);
                    }
                    if (event.getEventType() == 12) {
                        if (event.getTimeStamp() > notificationsSentState.lastSent) {
                            notificationsSentState.lastSent = event.getTimeStamp();
                        }
                        notificationsSentState.sentCount++;
                    }
                }
            }
        }
        return arrayMap;
    }

    protected NotificationsSentState getAggregatedUsageEvents(int i, String str) {
        UsageEvents usageEventsQueryEventsForPackageForUser;
        long jCurrentTimeMillis = System.currentTimeMillis();
        NotificationsSentState notificationsSentState = null;
        try {
            usageEventsQueryEventsForPackageForUser = this.mUsageStatsManager.queryEventsForPackageForUser(jCurrentTimeMillis - 604800000, jCurrentTimeMillis, i, str, this.mContext.getPackageName());
        } catch (RemoteException e) {
            e.printStackTrace();
            usageEventsQueryEventsForPackageForUser = null;
        }
        if (usageEventsQueryEventsForPackageForUser != null) {
            UsageEvents.Event event = new UsageEvents.Event();
            while (usageEventsQueryEventsForPackageForUser.hasNextEvent()) {
                usageEventsQueryEventsForPackageForUser.getNextEvent(event);
                if (event.getEventType() == 12) {
                    if (notificationsSentState == null) {
                        notificationsSentState = new NotificationsSentState();
                    }
                    if (event.getTimeStamp() > notificationsSentState.lastSent) {
                        notificationsSentState.lastSent = event.getTimeStamp();
                    }
                    notificationsSentState.sentCount++;
                }
            }
        }
        return notificationsSentState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static NotificationsSentState getNotificationsSentState(ApplicationsState.AppEntry appEntry) {
        Object obj;
        if (appEntry == null || (obj = appEntry.extraInfo) == null || !(obj instanceof NotificationsSentState)) {
            return null;
        }
        return (NotificationsSentState) obj;
    }

    protected static String getKey(int i, String str) {
        return i + "|" + str;
    }

    public View.OnClickListener getSwitchOnClickListener(final ApplicationsState.AppEntry appEntry) {
        if (appEntry != null) {
            return new View.OnClickListener() { // from class: com.android.car.developeroptions.applications.-$$Lambda$AppStateNotificationBridge$xLiGlIgxPscmYkA7wQvpp4oqkKw
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$getSwitchOnClickListener$0$AppStateNotificationBridge(appEntry, view);
                }
            };
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$getSwitchOnClickListener$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$getSwitchOnClickListener$0$AppStateNotificationBridge(ApplicationsState.AppEntry appEntry, View view) {
        Switch r5 = (Switch) ((ViewGroup) view).findViewById(R.id.switchWidget);
        if (r5 == null || !r5.isEnabled()) {
            return;
        }
        r5.toggle();
        NotificationBackend notificationBackend = this.mBackend;
        ApplicationInfo applicationInfo = appEntry.info;
        notificationBackend.setNotificationsEnabledForPackage(applicationInfo.packageName, applicationInfo.uid, r5.isChecked());
        NotificationsSentState notificationsSentState = getNotificationsSentState(appEntry);
        if (notificationsSentState != null) {
            notificationsSentState.blocked = !r5.isChecked();
        }
    }

    public static final boolean enableSwitch(ApplicationsState.AppEntry appEntry) {
        NotificationsSentState notificationsSentState = getNotificationsSentState(appEntry);
        if (notificationsSentState == null) {
            return false;
        }
        return notificationsSentState.blockable;
    }

    public static final boolean checkSwitch(ApplicationsState.AppEntry appEntry) {
        if (getNotificationsSentState(appEntry) == null) {
            return false;
        }
        return !r0.blocked;
    }
}
