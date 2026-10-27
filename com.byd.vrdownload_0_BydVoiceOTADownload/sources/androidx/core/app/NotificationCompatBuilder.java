package androidx.core.app;

import android.app.Notification;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import androidx.collection.ArraySet;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.drawable.IconCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
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

    NotificationCompatBuilder(NotificationCompat.Builder b) {
        this.mBuilderCompat = b;
        this.mContext = b.mContext;
        Notification.Builder builder = new Notification.Builder(b.mContext, b.mChannelId);
        this.mBuilder = builder;
        Notification notification = b.mNotification;
        builder.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, b.mTickerView).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(b.mContentTitle).setContentText(b.mContentText).setContentInfo(b.mContentInfo).setContentIntent(b.mContentIntent).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(b.mFullScreenIntent, (notification.flags & 128) != 0).setLargeIcon(b.mLargeIcon).setNumber(b.mNumber).setProgress(b.mProgressMax, b.mProgress, b.mProgressIndeterminate);
        builder.setSubText(b.mSubText).setUsesChronometer(b.mUseChronometer).setPriority(b.mPriority);
        Iterator<NotificationCompat.Action> it = b.mActions.iterator();
        while (it.hasNext()) {
            addAction(it.next());
        }
        if (b.mExtras != null) {
            this.mExtras.putAll(b.mExtras);
        }
        this.mContentView = b.mContentView;
        this.mBigContentView = b.mBigContentView;
        this.mBuilder.setShowWhen(b.mShowWhen);
        this.mBuilder.setLocalOnly(b.mLocalOnly).setGroup(b.mGroupKey).setGroupSummary(b.mGroupSummary).setSortKey(b.mSortKey);
        this.mGroupAlertBehavior = b.mGroupAlertBehavior;
        this.mBuilder.setCategory(b.mCategory).setColor(b.mColor).setVisibility(b.mVisibility).setPublicVersion(b.mPublicVersion).setSound(notification.sound, notification.audioAttributes);
        ArrayList<String> arrayList = b.mPeople;
        if (arrayList != null && !arrayList.isEmpty()) {
            Iterator<String> it2 = arrayList.iterator();
            while (it2.hasNext()) {
                this.mBuilder.addPerson(it2.next());
            }
        }
        this.mHeadsUpContentView = b.mHeadsUpContentView;
        if (b.mInvisibleActions.size() > 0) {
            Bundle bundle = b.getExtras().getBundle("android.car.EXTENSIONS");
            bundle = bundle == null ? new Bundle() : bundle;
            Bundle bundle2 = new Bundle(bundle);
            Bundle bundle3 = new Bundle();
            for (int i = 0; i < b.mInvisibleActions.size(); i++) {
                bundle3.putBundle(Integer.toString(i), NotificationCompatJellybean.getBundleForAction(b.mInvisibleActions.get(i)));
            }
            bundle.putBundle("invisible_actions", bundle3);
            bundle2.putBundle("invisible_actions", bundle3);
            b.getExtras().putBundle("android.car.EXTENSIONS", bundle);
            this.mExtras.putBundle("android.car.EXTENSIONS", bundle2);
        }
        if (b.mSmallIcon != null) {
            this.mBuilder.setSmallIcon(b.mSmallIcon);
        }
        this.mBuilder.setExtras(b.mExtras).setRemoteInputHistory(b.mRemoteInputHistory);
        if (b.mContentView != null) {
            this.mBuilder.setCustomContentView(b.mContentView);
        }
        if (b.mBigContentView != null) {
            this.mBuilder.setCustomBigContentView(b.mBigContentView);
        }
        if (b.mHeadsUpContentView != null) {
            this.mBuilder.setCustomHeadsUpContentView(b.mHeadsUpContentView);
        }
        this.mBuilder.setBadgeIconType(b.mBadgeIcon).setSettingsText(b.mSettingsText).setShortcutId(b.mShortcutId).setTimeoutAfter(b.mTimeout).setGroupAlertBehavior(b.mGroupAlertBehavior);
        if (b.mColorizedSet) {
            this.mBuilder.setColorized(b.mColorized);
        }
        if (!TextUtils.isEmpty(b.mChannelId)) {
            this.mBuilder.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
        }
        Iterator<Person> it3 = b.mPersonList.iterator();
        while (it3.hasNext()) {
            this.mBuilder.addPerson(it3.next().toAndroidPerson());
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.mBuilder.setAllowSystemGeneratedContextualActions(b.mAllowSystemGeneratedContextualActions);
            this.mBuilder.setBubbleMetadata(NotificationCompat.BubbleMetadata.toPlatform(b.mBubbleMetadata));
            if (b.mLocusId != null) {
                this.mBuilder.setLocusId(b.mLocusId.toLocusId());
            }
        }
        if (b.mSilent) {
            if (this.mBuilderCompat.mGroupSummary) {
                this.mGroupAlertBehavior = 2;
            } else {
                this.mGroupAlertBehavior = 1;
            }
            this.mBuilder.setVibrate(null);
            this.mBuilder.setSound(null);
            notification.defaults &= -2;
            notification.defaults &= -3;
            this.mBuilder.setDefaults(notification.defaults);
            if (TextUtils.isEmpty(this.mBuilderCompat.mGroupKey)) {
                this.mBuilder.setGroup(NotificationCompat.GROUP_KEY_SILENT);
            }
            this.mBuilder.setGroupAlertBehavior(this.mGroupAlertBehavior);
        }
    }

    private static List<String> combineLists(final List<String> first, final List<String> second) {
        if (first == null) {
            return second;
        }
        if (second == null) {
            return first;
        }
        ArraySet arraySet = new ArraySet(first.size() + second.size());
        arraySet.addAll(first);
        arraySet.addAll(second);
        return new ArrayList(arraySet);
    }

    private static List<String> getPeople(final List<Person> people) {
        if (people == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(people.size());
        Iterator<Person> it = people.iterator();
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
        } else if (this.mBuilderCompat.mContentView != null) {
            notificationBuildInternal.contentView = this.mBuilderCompat.mContentView;
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
        builder.setAllowGeneratedReplies(action.getAllowGeneratedReplies());
        bundle.putInt("android.support.action.semanticAction", action.getSemanticAction());
        builder.setSemanticAction(action.getSemanticAction());
        if (Build.VERSION.SDK_INT >= 29) {
            builder.setContextual(action.isContextual());
        }
        bundle.putBoolean("android.support.action.showsUserInterface", action.getShowsUserInterface());
        builder.addExtras(bundle);
        this.mBuilder.addAction(builder.build());
    }

    protected Notification buildInternal() {
        return this.mBuilder.build();
    }

    private void removeSoundAndVibration(Notification notification) {
        notification.sound = null;
        notification.vibrate = null;
        notification.defaults &= -2;
        notification.defaults &= -3;
    }
}
