package android.ext.services.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Icon;
import android.media.AudioAttributes;
import android.os.Bundle;
import android.os.Parcelable;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class NotificationEntry {
    private final NotificationChannel mChannel;
    private final Context mContext;
    private final PackageManager mPackageManager;
    private final StatusBarNotification mSbn;
    private final SmsHelper mSmsHelper;
    private int mTargetSdkVersion = 25;
    private final boolean mPreChannelsNotification = isPreChannelsNotification();
    private final AudioAttributes mAttributes = calculateAudioAttributes();
    private final int mImportance = calculateInitialImportance();

    private int clamp(int i, int i2, int i3) {
        return i < i2 ? i2 : i > i3 ? i3 : i;
    }

    public NotificationEntry(Context context, PackageManager packageManager, StatusBarNotification statusBarNotification, NotificationChannel notificationChannel, SmsHelper smsHelper) {
        this.mContext = context;
        this.mSbn = cloneStatusBarNotificationLight(statusBarNotification);
        this.mChannel = notificationChannel;
        this.mPackageManager = packageManager;
        this.mSmsHelper = smsHelper;
    }

    private static void lightenNotificationPayload(Notification notification) {
        Object obj;
        notification.tickerView = null;
        notification.contentView = null;
        notification.bigContentView = null;
        notification.headsUpContentView = null;
        notification.largeIcon = null;
        Bundle bundle = notification.extras;
        if (bundle == null || bundle.isEmpty()) {
            return;
        }
        Set<String> setKeySet = notification.extras.keySet();
        int size = setKeySet.size();
        String[] strArr = (String[]) setKeySet.toArray(new String[size]);
        for (int i = 0; i < size; i++) {
            String str = strArr[i];
            if (!"android.tv.EXTENSIONS".equals(str) && !"android.messages".equals(str) && !"android.messagingUser".equals(str) && !"android.people.list".equals(str) && (obj = notification.extras.get(str)) != null && ((obj instanceof Parcelable) || (obj instanceof Parcelable[]) || (obj instanceof SparseArray) || (obj instanceof ArrayList))) {
                notification.extras.remove(str);
            }
        }
    }

    private Notification cloneNotificationLight(Notification notification) {
        Notification notificationBuild = Notification.Builder.recoverBuilder(this.mContext, notification).setSmallIcon(0).setLargeIcon((Icon) null).build();
        lightenNotificationPayload(notificationBuild);
        return notificationBuild;
    }

    public StatusBarNotification cloneStatusBarNotificationLight(StatusBarNotification statusBarNotification) {
        return new StatusBarNotification(statusBarNotification.getPackageName(), statusBarNotification.getOpPkg(), statusBarNotification.getId(), statusBarNotification.getTag(), statusBarNotification.getUid(), 0, 0, cloneNotificationLight(statusBarNotification.getNotification()), statusBarNotification.getUser(), statusBarNotification.getPostTime());
    }

    private boolean isPreChannelsNotification() {
        try {
            ApplicationInfo applicationInfoAsUser = this.mPackageManager.getApplicationInfoAsUser(this.mSbn.getPackageName(), 131072, this.mSbn.getUser());
            if (applicationInfoAsUser != null) {
                this.mTargetSdkVersion = applicationInfoAsUser.targetSdkVersion;
            }
        } catch (PackageManager.NameNotFoundException unused) {
            Log.w("NotificationEntry", "Couldn't look up " + this.mSbn.getPackageName());
        }
        return "miscellaneous".equals(getChannel().getId()) && this.mTargetSdkVersion < 26;
    }

    private AudioAttributes calculateAudioAttributes() {
        Notification notification = getNotification();
        AudioAttributes audioAttributes = getChannel().getAudioAttributes();
        if (audioAttributes == null) {
            audioAttributes = Notification.AUDIO_ATTRIBUTES_DEFAULT;
        }
        if (!this.mPreChannelsNotification || getChannel().hasUserSetSound()) {
            return audioAttributes;
        }
        AudioAttributes audioAttributes2 = notification.audioAttributes;
        if (audioAttributes2 != null) {
            return audioAttributes2;
        }
        int i = notification.audioStreamType;
        if (i >= 0) {
            return new AudioAttributes.Builder().setLegacyStreamType(notification.audioStreamType).build();
        }
        Log.w("NotificationEntry", String.format("Invalid stream type: %d", Integer.valueOf(i)));
        return audioAttributes;
    }

    private int calculateInitialImportance() {
        Notification notification = getNotification();
        int importance = getChannel().getImportance();
        int i = 2;
        if ((notification.flags & 128) != 0) {
            notification.priority = 2;
        }
        int iClamp = clamp(notification.priority, -2, 2);
        notification.priority = iClamp;
        if (iClamp == -2) {
            i = 1;
        } else if (iClamp != -1) {
            i = (iClamp == 0 || !(iClamp == 1 || iClamp == 2)) ? 3 : 4;
        }
        if (!this.mPreChannelsNotification) {
            return importance;
        }
        if (importance != -1000 && !getChannel().hasUserSetImportance()) {
            return importance;
        }
        if (notification.fullScreenIntent != null) {
            return 4;
        }
        return i;
    }

    public boolean isCategory(String str) {
        return Objects.equals(getNotification().category, str);
    }

    public boolean isPublicVersionCategory(String str) {
        Notification notification = getNotification().publicVersion;
        if (notification == null) {
            return false;
        }
        return Objects.equals(notification.category, str);
    }

    public boolean isAudioAttributesUsage(int i) {
        AudioAttributes audioAttributes = this.mAttributes;
        return audioAttributes != null && audioAttributes.getUsage() == i;
    }

    private boolean hasPerson() {
        ArrayList parcelableArrayList = getNotification().extras.getParcelableArrayList("android.people.list");
        return (parcelableArrayList == null || parcelableArrayList.isEmpty()) ? false : true;
    }

    protected boolean hasStyle(Class cls) {
        return cls.getName().equals(getNotification().extras.getString("android.template"));
    }

    protected boolean isOngoing() {
        return (getNotification().flags & 64) != 0;
    }

    protected boolean involvesPeople() {
        return isMessaging() || hasStyle(Notification.InboxStyle.class) || hasPerson() || isDefaultSmsApp();
    }

    private boolean isDefaultSmsApp() {
        String defaultSmsPackage = this.mSmsHelper.getDefaultSmsPackage();
        if (defaultSmsPackage == null) {
            return false;
        }
        return this.mSbn.getPackageName().equals(defaultSmsPackage);
    }

    protected boolean isMessaging() {
        return isCategory("msg") || isPublicVersionCategory("msg") || hasStyle(Notification.MessagingStyle.class);
    }

    public StatusBarNotification getSbn() {
        return this.mSbn;
    }

    public Notification getNotification() {
        return getSbn().getNotification();
    }

    public NotificationChannel getChannel() {
        return this.mChannel;
    }

    public int getImportance() {
        return this.mImportance;
    }
}
