package android.ext.services.notification;

/* JADX INFO: loaded from: classes.dex */
public class NotificationCategorizer {
    boolean shouldSilence(int i) {
        return i < 1;
    }

    public boolean shouldSilence(NotificationEntry notificationEntry) {
        return shouldSilence(getCategory(notificationEntry));
    }

    public int getCategory(NotificationEntry notificationEntry) {
        if (notificationEntry.getChannel() == null) {
            return -2;
        }
        if (notificationEntry.getChannel().getImportance() == 1) {
            return -3;
        }
        if (notificationEntry.isCategory("reminder")) {
            return 2;
        }
        if (notificationEntry.isCategory("event")) {
            return 1;
        }
        if (notificationEntry.isCategory("alarm") || notificationEntry.isAudioAttributesUsage(4)) {
            return 5;
        }
        if (notificationEntry.isCategory("call")) {
            return 6;
        }
        if (notificationEntry.involvesPeople()) {
            return 4;
        }
        if (notificationEntry.getSbn().getUid() < 10000) {
            return notificationEntry.getImportance() >= 3 ? 3 : 0;
        }
        if (notificationEntry.getChannel().getImportance() == 4) {
            return 7;
        }
        return notificationEntry.isOngoing() ? -1 : -2;
    }
}
