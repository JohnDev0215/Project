package com.android.car.developeroptions.notification;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.INotificationManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class NotificationStation extends SettingsPreferenceFragment {
    private static final String TAG = NotificationStation.class.getSimpleName();
    private Context mContext;
    private Handler mHandler;
    private INotificationManager mNoMan;
    private PackageManager mPm;
    private NotificationListenerService.RankingMap mRanking;
    private Runnable mRefreshListRunnable = new Runnable() { // from class: com.android.car.developeroptions.notification.NotificationStation.1
        @Override // java.lang.Runnable
        public void run() {
            NotificationStation.this.refreshList();
        }
    };
    private final NotificationListenerService mListener = new NotificationListenerService() { // from class: com.android.car.developeroptions.notification.NotificationStation.2
        @Override // android.service.notification.NotificationListenerService
        public void onNotificationPosted(StatusBarNotification statusBarNotification, NotificationListenerService.RankingMap rankingMap) {
            Object[] objArr = new Object[2];
            objArr[0] = statusBarNotification.getNotification();
            objArr[1] = Integer.valueOf(rankingMap != null ? rankingMap.getOrderedKeys().length : 0);
            NotificationStation.logd("onNotificationPosted: %s, with update for %d", objArr);
            NotificationStation.this.mRanking = rankingMap;
            NotificationStation.this.scheduleRefreshList();
        }

        @Override // android.service.notification.NotificationListenerService
        public void onNotificationRemoved(StatusBarNotification statusBarNotification, NotificationListenerService.RankingMap rankingMap) {
            Object[] objArr = new Object[1];
            objArr[0] = Integer.valueOf(rankingMap == null ? 0 : rankingMap.getOrderedKeys().length);
            NotificationStation.logd("onNotificationRankingUpdate with update for %d", objArr);
            NotificationStation.this.mRanking = rankingMap;
            NotificationStation.this.scheduleRefreshList();
        }

        @Override // android.service.notification.NotificationListenerService
        public void onNotificationRankingUpdate(NotificationListenerService.RankingMap rankingMap) {
            Object[] objArr = new Object[1];
            objArr[0] = Integer.valueOf(rankingMap == null ? 0 : rankingMap.getOrderedKeys().length);
            NotificationStation.logd("onNotificationRankingUpdate with update for %d", objArr);
            NotificationStation.this.mRanking = rankingMap;
            NotificationStation.this.scheduleRefreshList();
        }

        @Override // android.service.notification.NotificationListenerService
        public void onListenerConnected() {
            NotificationStation.this.mRanking = getCurrentRanking();
            Object[] objArr = new Object[1];
            objArr[0] = Integer.valueOf(NotificationStation.this.mRanking == null ? 0 : NotificationStation.this.mRanking.getOrderedKeys().length);
            NotificationStation.logd("onListenerConnected with update for %d", objArr);
            NotificationStation.this.scheduleRefreshList();
        }
    };
    private final Comparator<HistoricalNotificationInfo> mNotificationSorter = new Comparator<HistoricalNotificationInfo>(this) { // from class: com.android.car.developeroptions.notification.NotificationStation.3
        @Override // java.util.Comparator
        public int compare(HistoricalNotificationInfo historicalNotificationInfo, HistoricalNotificationInfo historicalNotificationInfo2) {
            return Long.compare(historicalNotificationInfo2.timestamp, historicalNotificationInfo.timestamp);
        }
    };

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 75;
    }

    private static class HistoricalNotificationInfo {
        public boolean active;
        public CharSequence extra;
        public Drawable icon;
        public String pkg;
        public Drawable pkgicon;
        public CharSequence pkgname;
        public long timestamp;
        public CharSequence title;
        public int user;

        private HistoricalNotificationInfo() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scheduleRefreshList() {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacks(this.mRefreshListRunnable);
            this.mHandler.postDelayed(this.mRefreshListRunnable, 100L);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        logd("onAttach(%s)", activity.getClass().getSimpleName());
        super.onAttach(activity);
        this.mHandler = new Handler(activity.getMainLooper());
        this.mContext = activity;
        this.mPm = activity.getPackageManager();
        this.mNoMan = INotificationManager.Stub.asInterface(ServiceManager.getService("notification"));
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, androidx.fragment.app.Fragment
    public void onDetach() {
        logd("onDetach()", new Object[0]);
        this.mHandler.removeCallbacks(this.mRefreshListRunnable);
        this.mHandler = null;
        super.onDetach();
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onPause() {
        try {
            this.mListener.unregisterAsSystemService();
        } catch (RemoteException e) {
            Log.e(TAG, "Cannot unregister listener", e);
        }
        super.onPause();
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        logd("onActivityCreated(%s)", bundle);
        super.onActivityCreated(bundle);
        Utils.forceCustomPadding(getListView(), false);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        logd("onResume()", new Object[0]);
        super.onResume();
        try {
            this.mListener.registerAsSystemService(this.mContext, new ComponentName(this.mContext.getPackageName(), getClass().getCanonicalName()), ActivityManager.getCurrentUser());
        } catch (RemoteException e) {
            Log.e(TAG, "Cannot register listener", e);
        }
        refreshList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshList() {
        List<HistoricalNotificationInfo> listLoadNotifications = loadNotifications();
        if (listLoadNotifications != null) {
            int size = listLoadNotifications.size();
            logd("adding %d infos", Integer.valueOf(size));
            Collections.sort(listLoadNotifications, this.mNotificationSorter);
            if (getPreferenceScreen() == null) {
                setPreferenceScreen(getPreferenceManager().createPreferenceScreen(getContext()));
            }
            getPreferenceScreen().removeAll();
            for (int i = 0; i < size; i++) {
                getPreferenceScreen().addPreference(new HistoricalNotificationPreference(getPrefContext(), listLoadNotifications.get(i)));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void logd(String str, Object... objArr) {
        String str2 = TAG;
        if (objArr != null && objArr.length != 0) {
            str = String.format(str, objArr);
        }
        Log.d(str2, str);
    }

    private static CharSequence bold(CharSequence charSequence) {
        if (charSequence.length() == 0) {
            return charSequence;
        }
        SpannableString spannableString = new SpannableString(charSequence);
        spannableString.setSpan(new StyleSpan(1), 0, charSequence.length(), 0);
        return spannableString;
    }

    private static String getTitleString(Notification notification) {
        CharSequence charSequence;
        Bundle bundle = notification.extras;
        if (bundle != null) {
            charSequence = bundle.getCharSequence("android.title");
            if (TextUtils.isEmpty(charSequence)) {
                charSequence = notification.extras.getCharSequence("android.text");
            }
        } else {
            charSequence = null;
        }
        if (TextUtils.isEmpty(charSequence) && !TextUtils.isEmpty(notification.tickerText)) {
            charSequence = notification.tickerText;
        }
        return String.valueOf(charSequence);
    }

    private static String formatPendingIntent(PendingIntent pendingIntent) {
        StringBuilder sb = new StringBuilder();
        IntentSender intentSender = pendingIntent.getIntentSender();
        sb.append("Intent(pkg=");
        sb.append(intentSender.getCreatorPackage());
        try {
            if (ActivityManager.getService().isIntentSenderAnActivity(intentSender.getTarget())) {
                sb.append(" (activity)");
            }
        } catch (RemoteException unused) {
        }
        sb.append(")");
        return sb.toString();
    }

    private List<HistoricalNotificationInfo> loadNotifications() {
        boolean z;
        int i;
        int currentUser = ActivityManager.getCurrentUser();
        AnonymousClass1 anonymousClass1 = null;
        try {
            StatusBarNotification[] activeNotifications = this.mNoMan.getActiveNotifications(this.mContext.getPackageName());
            boolean z2 = true;
            StatusBarNotification[] historicalNotifications = this.mNoMan.getHistoricalNotifications(this.mContext.getPackageName(), 50, true);
            ArrayList arrayList = new ArrayList(activeNotifications.length + historicalNotifications.length);
            int i2 = 2;
            StatusBarNotification[][] statusBarNotificationArr = {activeNotifications, historicalNotifications};
            int i3 = 0;
            while (i3 < i2) {
                StatusBarNotification[] statusBarNotificationArr2 = statusBarNotificationArr[i3];
                int length = statusBarNotificationArr2.length;
                int i4 = 0;
                while (i4 < length) {
                    StatusBarNotification statusBarNotification = statusBarNotificationArr2[i4];
                    if ((statusBarNotification.getUserId() != -1 ? z2 : false) && (statusBarNotification.getUserId() != currentUser ? z2 : false)) {
                        i = i2;
                        z = z2;
                    } else {
                        Notification notification = statusBarNotification.getNotification();
                        HistoricalNotificationInfo historicalNotificationInfo = new HistoricalNotificationInfo();
                        historicalNotificationInfo.pkg = statusBarNotification.getPackageName();
                        int userId = statusBarNotification.getUserId();
                        historicalNotificationInfo.user = userId;
                        historicalNotificationInfo.icon = loadIconDrawable(historicalNotificationInfo.pkg, userId, notification.icon);
                        historicalNotificationInfo.pkgicon = loadPackageIconDrawable(historicalNotificationInfo.pkg, historicalNotificationInfo.user);
                        historicalNotificationInfo.pkgname = loadPackageName(historicalNotificationInfo.pkg);
                        String titleString = getTitleString(notification);
                        historicalNotificationInfo.title = titleString;
                        if (TextUtils.isEmpty(titleString)) {
                            historicalNotificationInfo.title = getString(R.string.notification_log_no_title);
                        }
                        historicalNotificationInfo.timestamp = statusBarNotification.getPostTime();
                        int i5 = notification.priority;
                        notification.getChannelId();
                        statusBarNotification.getKey();
                        historicalNotificationInfo.active = statusBarNotificationArr2 == activeNotifications;
                        historicalNotificationInfo.extra = generateExtraText(statusBarNotification, historicalNotificationInfo);
                        z = true;
                        i = 2;
                        logd("   [%d] %s: %s", Long.valueOf(historicalNotificationInfo.timestamp), historicalNotificationInfo.pkg, historicalNotificationInfo.title);
                        arrayList.add(historicalNotificationInfo);
                    }
                    i4++;
                    z2 = z;
                    i2 = i;
                    anonymousClass1 = null;
                }
                i3++;
                anonymousClass1 = null;
            }
            return arrayList;
        } catch (RemoteException e) {
            Log.e(TAG, "Cannot load Notifications: ", e);
            return null;
        }
    }

    private CharSequence generateExtraText(StatusBarNotification statusBarNotification, HistoricalNotificationInfo historicalNotificationInfo) {
        NotificationListenerService.RankingMap rankingMap;
        NotificationListenerService.Ranking ranking = new NotificationListenerService.Ranking();
        Notification notification = statusBarNotification.getNotification();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        String string = getString(R.string.notification_log_details_delimiter);
        spannableStringBuilder.append(bold(getString(R.string.notification_log_details_package))).append((CharSequence) string).append((CharSequence) historicalNotificationInfo.pkg).append((CharSequence) "\n").append(bold(getString(R.string.notification_log_details_key))).append((CharSequence) string).append((CharSequence) statusBarNotification.getKey());
        spannableStringBuilder.append((CharSequence) "\n").append(bold(getString(R.string.notification_log_details_icon))).append((CharSequence) string).append((CharSequence) String.valueOf(notification.getSmallIcon()));
        spannableStringBuilder.append((CharSequence) "\n").append(bold("channelId")).append((CharSequence) string).append((CharSequence) String.valueOf(notification.getChannelId()));
        spannableStringBuilder.append((CharSequence) "\n").append(bold("postTime")).append((CharSequence) string).append((CharSequence) String.valueOf(statusBarNotification.getPostTime()));
        if (notification.getTimeoutAfter() != 0) {
            spannableStringBuilder.append((CharSequence) "\n").append(bold("timeoutAfter")).append((CharSequence) string).append((CharSequence) String.valueOf(notification.getTimeoutAfter()));
        }
        if (statusBarNotification.isGroup()) {
            spannableStringBuilder.append((CharSequence) "\n").append(bold(getString(R.string.notification_log_details_group))).append((CharSequence) string).append((CharSequence) String.valueOf(statusBarNotification.getGroupKey()));
            if (notification.isGroupSummary()) {
                spannableStringBuilder.append(bold(getString(R.string.notification_log_details_group_summary)));
            }
        }
        if (historicalNotificationInfo.active && (rankingMap = this.mRanking) != null && rankingMap.getRanking(statusBarNotification.getKey(), ranking) && ranking.getLastAudiblyAlertedMillis() > 0) {
            spannableStringBuilder.append((CharSequence) "\n").append(bold(getString(R.string.notification_log_details_alerted)));
        }
        try {
            NotificationChannel notificationChannelForPackage = this.mNoMan.getNotificationChannelForPackage(statusBarNotification.getPackageName(), statusBarNotification.getUid(), notification.getChannelId(), (String) null, false);
            spannableStringBuilder.append((CharSequence) "\n").append(bold(getString(R.string.notification_log_details_sound))).append((CharSequence) string);
            if (notificationChannelForPackage == null || notificationChannelForPackage.getImportance() == -1000) {
                if ((notification.defaults & 1) != 0) {
                    spannableStringBuilder.append((CharSequence) getString(R.string.notification_log_details_default));
                } else if (notification.sound != null) {
                    spannableStringBuilder.append((CharSequence) notification.sound.toString());
                } else {
                    spannableStringBuilder.append((CharSequence) getString(R.string.notification_log_details_none));
                }
            } else {
                spannableStringBuilder.append((CharSequence) String.valueOf(notificationChannelForPackage.getSound()));
            }
            spannableStringBuilder.append((CharSequence) "\n").append(bold(getString(R.string.notification_log_details_vibrate))).append((CharSequence) string);
            if (notificationChannelForPackage == null || notificationChannelForPackage.getImportance() == -1000) {
                if ((notification.defaults & 2) != 0) {
                    spannableStringBuilder.append((CharSequence) getString(R.string.notification_log_details_default));
                } else if (notification.vibrate != null) {
                    spannableStringBuilder.append((CharSequence) getString(R.string.notification_log_details_vibrate_pattern));
                } else {
                    spannableStringBuilder.append((CharSequence) getString(R.string.notification_log_details_none));
                }
            } else if (notificationChannelForPackage.getVibrationPattern() != null) {
                spannableStringBuilder.append((CharSequence) getString(R.string.notification_log_details_vibrate_pattern));
            } else {
                spannableStringBuilder.append((CharSequence) getString(R.string.notification_log_details_none));
            }
        } catch (RemoteException e) {
            Log.d(TAG, "cannot read channel info", e);
        }
        spannableStringBuilder.append((CharSequence) "\n").append(bold(getString(R.string.notification_log_details_visibility))).append((CharSequence) string).append((CharSequence) Notification.visibilityToString(notification.visibility));
        if (notification.publicVersion != null) {
            spannableStringBuilder.append((CharSequence) "\n").append(bold(getString(R.string.notification_log_details_public_version))).append((CharSequence) string).append((CharSequence) getTitleString(notification.publicVersion));
        }
        spannableStringBuilder.append((CharSequence) "\n").append(bold(getString(R.string.notification_log_details_priority))).append((CharSequence) string).append((CharSequence) Notification.priorityToString(notification.priority));
        if (historicalNotificationInfo.active) {
            NotificationListenerService.RankingMap rankingMap2 = this.mRanking;
            if (rankingMap2 != null && rankingMap2.getRanking(statusBarNotification.getKey(), ranking)) {
                spannableStringBuilder.append((CharSequence) "\n").append(bold(getString(R.string.notification_log_details_importance))).append((CharSequence) string).append((CharSequence) NotificationListenerService.Ranking.importanceToString(ranking.getImportance()));
                if (ranking.getImportanceExplanation() != null) {
                    spannableStringBuilder.append((CharSequence) "\n").append(bold(getString(R.string.notification_log_details_explanation))).append((CharSequence) string).append(ranking.getImportanceExplanation());
                }
                spannableStringBuilder.append((CharSequence) "\n").append(bold(getString(R.string.notification_log_details_badge))).append((CharSequence) string).append((CharSequence) Boolean.toString(ranking.canShowBadge()));
            } else if (this.mRanking == null) {
                spannableStringBuilder.append((CharSequence) "\n").append(bold(getString(R.string.notification_log_details_ranking_null)));
            } else {
                spannableStringBuilder.append((CharSequence) "\n").append(bold(getString(R.string.notification_log_details_ranking_none)));
            }
        }
        if (notification.contentIntent != null) {
            spannableStringBuilder.append((CharSequence) "\n").append(bold(getString(R.string.notification_log_details_content_intent))).append((CharSequence) string).append((CharSequence) formatPendingIntent(notification.contentIntent));
        }
        if (notification.deleteIntent != null) {
            spannableStringBuilder.append((CharSequence) "\n").append(bold(getString(R.string.notification_log_details_delete_intent))).append((CharSequence) string).append((CharSequence) formatPendingIntent(notification.deleteIntent));
        }
        if (notification.fullScreenIntent != null) {
            spannableStringBuilder.append((CharSequence) "\n").append(bold(getString(R.string.notification_log_details_full_screen_intent))).append((CharSequence) string).append((CharSequence) formatPendingIntent(notification.fullScreenIntent));
        }
        Notification.Action[] actionArr = notification.actions;
        if (actionArr != null && actionArr.length > 0) {
            spannableStringBuilder.append((CharSequence) "\n").append(bold(getString(R.string.notification_log_details_actions)));
            int i = 0;
            while (true) {
                Notification.Action[] actionArr2 = notification.actions;
                if (i >= actionArr2.length) {
                    break;
                }
                Notification.Action action = actionArr2[i];
                spannableStringBuilder.append((CharSequence) "\n  ").append((CharSequence) String.valueOf(i)).append(' ').append(bold(getString(R.string.notification_log_details_title))).append((CharSequence) string).append(action.title);
                if (action.actionIntent != null) {
                    spannableStringBuilder.append((CharSequence) "\n    ").append(bold(getString(R.string.notification_log_details_content_intent))).append((CharSequence) string).append((CharSequence) formatPendingIntent(action.actionIntent));
                }
                if (action.getRemoteInputs() != null) {
                    spannableStringBuilder.append((CharSequence) "\n    ").append(bold(getString(R.string.notification_log_details_remoteinput))).append((CharSequence) string).append((CharSequence) String.valueOf(action.getRemoteInputs().length));
                }
                i++;
            }
        }
        if (notification.contentView != null) {
            spannableStringBuilder.append((CharSequence) "\n").append(bold(getString(R.string.notification_log_details_content_view))).append((CharSequence) string).append((CharSequence) notification.contentView.toString());
        }
        Bundle bundle = notification.extras;
        if (bundle != null && bundle.size() > 0) {
            spannableStringBuilder.append((CharSequence) "\n").append(bold(getString(R.string.notification_log_details_extras)));
            for (String str : notification.extras.keySet()) {
                String strValueOf = String.valueOf(notification.extras.get(str));
                if (strValueOf.length() > 100) {
                    strValueOf = strValueOf.substring(0, 100) + "...";
                }
                spannableStringBuilder.append((CharSequence) "\n  ").append((CharSequence) str).append((CharSequence) string).append((CharSequence) strValueOf);
            }
        }
        Parcel parcelObtain = Parcel.obtain();
        notification.writeToParcel(parcelObtain, 0);
        spannableStringBuilder.append((CharSequence) "\n").append(bold(getString(R.string.notification_log_details_parcel))).append((CharSequence) string).append((CharSequence) String.valueOf(parcelObtain.dataPosition())).append(' ').append(bold(getString(R.string.notification_log_details_ashmem))).append((CharSequence) string).append((CharSequence) String.valueOf(parcelObtain.getBlobAshmemSize())).append((CharSequence) "\n");
        return spannableStringBuilder;
    }

    private Resources getResourcesForUserPackage(String str, int i) {
        if (str != null) {
            if (i == -1) {
                i = 0;
            }
            try {
                return this.mPm.getResourcesForApplicationAsUser(str, i);
            } catch (PackageManager.NameNotFoundException e) {
                Log.e(TAG, "Icon package not found: " + str, e);
                return null;
            }
        }
        return this.mContext.getResources();
    }

    private Drawable loadPackageIconDrawable(String str, int i) {
        try {
            return this.mPm.getApplicationIcon(str);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Cannot get application icon", e);
            return null;
        }
    }

    private CharSequence loadPackageName(String str) {
        try {
            ApplicationInfo applicationInfo = this.mPm.getApplicationInfo(str, 4194304);
            if (applicationInfo != null) {
                return this.mPm.getApplicationLabel(applicationInfo);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Cannot load package name", e);
        }
        return str;
    }

    private Drawable loadIconDrawable(String str, int i, int i2) {
        Resources resourcesForUserPackage = getResourcesForUserPackage(str, i);
        if (i2 == 0) {
            return null;
        }
        try {
            return resourcesForUserPackage.getDrawable(i2, null);
        } catch (RuntimeException e) {
            String str2 = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Icon not found in ");
            sb.append(str != null ? Integer.valueOf(i2) : "<system>");
            sb.append(": ");
            sb.append(Integer.toHexString(i2));
            Log.w(str2, sb.toString(), e);
            return null;
        }
    }

    private static class HistoricalNotificationPreference extends Preference {
        private static long sLastExpandedTimestamp;
        private final HistoricalNotificationInfo mInfo;

        @Override // androidx.preference.Preference
        public void performClick() {
        }

        public HistoricalNotificationPreference(Context context, HistoricalNotificationInfo historicalNotificationInfo) {
            super(context);
            setLayoutResource(R.layout.notification_log_row);
            this.mInfo = historicalNotificationInfo;
        }

        @Override // androidx.preference.Preference
        public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
            super.onBindViewHolder(preferenceViewHolder);
            if (this.mInfo.icon != null) {
                ((ImageView) preferenceViewHolder.findViewById(R.id.icon)).setImageDrawable(this.mInfo.icon);
            }
            if (this.mInfo.pkgicon != null) {
                ((ImageView) preferenceViewHolder.findViewById(R.id.pkgicon)).setImageDrawable(this.mInfo.pkgicon);
            }
            preferenceViewHolder.findViewById(R.id.timestamp).setTime(this.mInfo.timestamp);
            ((TextView) preferenceViewHolder.findViewById(R.id.title)).setText(this.mInfo.title);
            ((TextView) preferenceViewHolder.findViewById(R.id.pkgname)).setText(this.mInfo.pkgname);
            final TextView textView = (TextView) preferenceViewHolder.findViewById(R.id.extra);
            textView.setText(this.mInfo.extra);
            textView.setVisibility(this.mInfo.timestamp == sLastExpandedTimestamp ? 0 : 8);
            preferenceViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.notification.NotificationStation.HistoricalNotificationPreference.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    TextView textView2 = textView;
                    textView2.setVisibility(textView2.getVisibility() == 0 ? 8 : 0);
                    long unused = HistoricalNotificationPreference.sLastExpandedTimestamp = HistoricalNotificationPreference.this.mInfo.timestamp;
                }
            });
            preferenceViewHolder.itemView.setAlpha(this.mInfo.active ? 1.0f : 0.5f);
        }
    }
}
