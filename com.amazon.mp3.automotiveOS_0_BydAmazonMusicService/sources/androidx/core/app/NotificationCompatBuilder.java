package androidx.core.app;

import android.app.Notification;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.collection.ArraySet;
import androidx.core.app.NotificationCompat;
import androidx.core.content.LocusIdCompat;
import androidx.core.graphics.drawable.IconCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
class NotificationCompatBuilder implements NotificationBuilderWithBuilderAccessor {
    private RemoteViews mBigContentView;
    private final Notification.Builder mBuilder;
    private final NotificationCompat.Builder mBuilderCompat;
    private RemoteViews mContentView;
    private final Context mContext;
    private int mGroupAlertBehavior;
    private RemoteViews mHeadsUpContentView;
    private final List<Bundle> mActionExtrasList = new ArrayList();
    private final Bundle mExtras = new Bundle();

    NotificationCompatBuilder(NotificationCompat.Builder builder) {
        List listCombineLists;
        int i;
        this.mBuilderCompat = builder;
        this.mContext = builder.mContext;
        if (Build.VERSION.SDK_INT >= 26) {
            this.mBuilder = new Notification.Builder(builder.mContext, builder.mChannelId);
        } else {
            this.mBuilder = new Notification.Builder(builder.mContext);
        }
        Notification notification = builder.mNotification;
        this.mBuilder.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, builder.mTickerView).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(builder.mContentTitle).setContentText(builder.mContentText).setContentInfo(builder.mContentInfo).setContentIntent(builder.mContentIntent).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(builder.mFullScreenIntent, (notification.flags & 128) != 0).setLargeIcon(builder.mLargeIcon).setNumber(builder.mNumber).setProgress(builder.mProgressMax, builder.mProgress, builder.mProgressIndeterminate);
        this.mBuilder.setSubText(builder.mSubText).setUsesChronometer(builder.mUseChronometer).setPriority(builder.mPriority);
        Iterator<NotificationCompat.Action> it = builder.mActions.iterator();
        while (it.hasNext()) {
            addAction(it.next());
        }
        Bundle bundle = builder.mExtras;
        if (bundle != null) {
            this.mExtras.putAll(bundle);
        }
        int i2 = Build.VERSION.SDK_INT;
        this.mContentView = builder.mContentView;
        this.mBigContentView = builder.mBigContentView;
        this.mBuilder.setShowWhen(builder.mShowWhen);
        this.mBuilder.setLocalOnly(builder.mLocalOnly).setGroup(builder.mGroupKey).setGroupSummary(builder.mGroupSummary).setSortKey(builder.mSortKey);
        this.mGroupAlertBehavior = builder.mGroupAlertBehavior;
        this.mBuilder.setCategory(builder.mCategory).setColor(builder.mColor).setVisibility(builder.mVisibility).setPublicVersion(builder.mPublicVersion).setSound(notification.sound, notification.audioAttributes);
        if (i2 < 28) {
            listCombineLists = combineLists(getPeople(builder.mPersonList), builder.mPeople);
        } else {
            listCombineLists = builder.mPeople;
        }
        if (listCombineLists != null && !listCombineLists.isEmpty()) {
            Iterator it2 = listCombineLists.iterator();
            while (it2.hasNext()) {
                this.mBuilder.addPerson((String) it2.next());
            }
        }
        this.mHeadsUpContentView = builder.mHeadsUpContentView;
        if (builder.mInvisibleActions.size() > 0) {
            Bundle bundle2 = builder.getExtras().getBundle("android.car.EXTENSIONS");
            bundle2 = bundle2 == null ? new Bundle() : bundle2;
            Bundle bundle3 = new Bundle(bundle2);
            Bundle bundle4 = new Bundle();
            for (int i3 = 0; i3 < builder.mInvisibleActions.size(); i3++) {
                bundle4.putBundle(Integer.toString(i3), NotificationCompatJellybean.getBundleForAction(builder.mInvisibleActions.get(i3)));
            }
            bundle2.putBundle("invisible_actions", bundle4);
            bundle3.putBundle("invisible_actions", bundle4);
            builder.getExtras().putBundle("android.car.EXTENSIONS", bundle2);
            this.mExtras.putBundle("android.car.EXTENSIONS", bundle3);
        }
        int i4 = Build.VERSION.SDK_INT;
        Icon icon = builder.mSmallIcon;
        if (icon != null) {
            this.mBuilder.setSmallIcon(icon);
        }
        this.mBuilder.setExtras(builder.mExtras).setRemoteInputHistory(builder.mRemoteInputHistory);
        RemoteViews remoteViews = builder.mContentView;
        if (remoteViews != null) {
            this.mBuilder.setCustomContentView(remoteViews);
        }
        RemoteViews remoteViews2 = builder.mBigContentView;
        if (remoteViews2 != null) {
            this.mBuilder.setCustomBigContentView(remoteViews2);
        }
        RemoteViews remoteViews3 = builder.mHeadsUpContentView;
        if (remoteViews3 != null) {
            this.mBuilder.setCustomHeadsUpContentView(remoteViews3);
        }
        if (i4 >= 26) {
            this.mBuilder.setBadgeIconType(builder.mBadgeIcon).setSettingsText(builder.mSettingsText).setShortcutId(builder.mShortcutId).setTimeoutAfter(builder.mTimeout).setGroupAlertBehavior(builder.mGroupAlertBehavior);
            if (builder.mColorizedSet) {
                this.mBuilder.setColorized(builder.mColorized);
            }
            if (!TextUtils.isEmpty(builder.mChannelId)) {
                this.mBuilder.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
            }
        }
        if (i4 >= 28) {
            Iterator<Person> it3 = builder.mPersonList.iterator();
            while (it3.hasNext()) {
                this.mBuilder.addPerson(it3.next().toAndroidPerson());
            }
        }
        int i5 = Build.VERSION.SDK_INT;
        if (i5 >= 29) {
            this.mBuilder.setAllowSystemGeneratedContextualActions(builder.mAllowSystemGeneratedContextualActions);
            this.mBuilder.setBubbleMetadata(NotificationCompat.BubbleMetadata.toPlatform(builder.mBubbleMetadata));
            LocusIdCompat locusIdCompat = builder.mLocusId;
            if (locusIdCompat != null) {
                this.mBuilder.setLocusId(locusIdCompat.toLocusId());
            }
        }
        if (i5 >= 31 && (i = builder.mFgsDeferBehavior) != 0) {
            this.mBuilder.setForegroundServiceBehavior(i);
        }
        if (builder.mSilent) {
            if (this.mBuilderCompat.mGroupSummary) {
                this.mGroupAlertBehavior = 2;
            } else {
                this.mGroupAlertBehavior = 1;
            }
            this.mBuilder.setVibrate(null);
            this.mBuilder.setSound(null);
            int i6 = notification.defaults & (-2) & (-3);
            notification.defaults = i6;
            this.mBuilder.setDefaults(i6);
            if (i5 >= 26) {
                if (TextUtils.isEmpty(this.mBuilderCompat.mGroupKey)) {
                    this.mBuilder.setGroup(NotificationCompat.GROUP_KEY_SILENT);
                }
                this.mBuilder.setGroupAlertBehavior(this.mGroupAlertBehavior);
            }
        }
    }

    @Nullable
    private static List<String> combineLists(@Nullable List<String> list, @Nullable List<String> list2) {
        if (list == null) {
            return list2;
        }
        if (list2 == null) {
            return list;
        }
        ArraySet arraySet = new ArraySet(list.size() + list2.size());
        arraySet.addAll(list);
        arraySet.addAll(list2);
        return new ArrayList(arraySet);
    }

    @Nullable
    private static List<String> getPeople(@Nullable List<Person> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<Person> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().resolveToLegacyUri());
        }
        return arrayList;
    }

    @Override // androidx.core.app.NotificationBuilderWithBuilderAccessor
    public Notification.Builder getBuilder() {
        return this.mBuilder;
    }

    Context getContext() {
        return this.mContext;
    }

    public Notification build() {
        Bundle extras;
        RemoteViews remoteViewsMakeHeadsUpContentView;
        RemoteViews remoteViewsMakeBigContentView;
        NotificationCompat.Style style = this.mBuilderCompat.mStyle;
        if (style != null) {
            style.apply(this);
        }
        RemoteViews remoteViewsMakeContentView = style != null ? style.makeContentView(this) : null;
        Notification notificationBuildInternal = buildInternal();
        if (remoteViewsMakeContentView != null) {
            notificationBuildInternal.contentView = remoteViewsMakeContentView;
        } else {
            RemoteViews remoteViews = this.mBuilderCompat.mContentView;
            if (remoteViews != null) {
                notificationBuildInternal.contentView = remoteViews;
            }
        }
        if (style != null && (remoteViewsMakeBigContentView = style.makeBigContentView(this)) != null) {
            notificationBuildInternal.bigContentView = remoteViewsMakeBigContentView;
        }
        if (style != null && (remoteViewsMakeHeadsUpContentView = this.mBuilderCompat.mStyle.makeHeadsUpContentView(this)) != null) {
            notificationBuildInternal.headsUpContentView = remoteViewsMakeHeadsUpContentView;
        }
        if (style != null && (extras = NotificationCompat.getExtras(notificationBuildInternal)) != null) {
            style.addCompatExtras(extras);
        }
        return notificationBuildInternal;
    }

    private void addAction(NotificationCompat.Action action) {
        Bundle bundle;
        IconCompat iconCompat = action.getIconCompat();
        Notification.Action.Builder builder = new Notification.Action.Builder(iconCompat != null ? iconCompat.toIcon() : null, action.getTitle(), action.getActionIntent());
        if (action.getRemoteInputs() != null) {
            for (android.app.RemoteInput remoteInput : RemoteInput.fromCompat(action.getRemoteInputs())) {
                builder.addRemoteInput(remoteInput);
            }
        }
        if (action.getExtras() != null) {
            bundle = new Bundle(action.getExtras());
        } else {
            bundle = new Bundle();
        }
        bundle.putBoolean("android.support.allowGeneratedReplies", action.getAllowGeneratedReplies());
        int i = Build.VERSION.SDK_INT;
        builder.setAllowGeneratedReplies(action.getAllowGeneratedReplies());
        bundle.putInt("android.support.action.semanticAction", action.getSemanticAction());
        if (i >= 28) {
            builder.setSemanticAction(action.getSemanticAction());
        }
        if (i >= 29) {
            builder.setContextual(action.isContextual());
        }
        if (i >= 31) {
            builder.setAuthenticationRequired(action.isAuthenticationRequired());
        }
        bundle.putBoolean("android.support.action.showsUserInterface", action.getShowsUserInterface());
        builder.addExtras(bundle);
        this.mBuilder.addAction(builder.build());
    }

    protected Notification buildInternal() {
        if (Build.VERSION.SDK_INT >= 26) {
            return this.mBuilder.build();
        }
        Notification notificationBuild = this.mBuilder.build();
        if (this.mGroupAlertBehavior != 0) {
            if (notificationBuild.getGroup() != null && (notificationBuild.flags & 512) != 0 && this.mGroupAlertBehavior == 2) {
                removeSoundAndVibration(notificationBuild);
            }
            if (notificationBuild.getGroup() != null && (notificationBuild.flags & 512) == 0 && this.mGroupAlertBehavior == 1) {
                removeSoundAndVibration(notificationBuild);
            }
        }
        return notificationBuild;
    }

    private void removeSoundAndVibration(Notification notification) {
        notification.sound = null;
        notification.vibrate = null;
        notification.defaults = notification.defaults & (-2) & (-3);
    }
}
