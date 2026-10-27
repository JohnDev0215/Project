package com.android.bluetooth.opp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.text.format.Formatter;
import com.android.bluetooth.R;
import com.android.bluetooth.mapapi.BluetoothMapContract;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
class BluetoothOppNotification {
    static final String CONFIRM = "(confirm == '1' OR confirm == '2' OR confirm == '5')";
    private static final int NOTIFICATION_ID_INBOUND_COMPLETE = -1000006;
    private static final int NOTIFICATION_ID_OUTBOUND_COMPLETE = -1000005;
    public static final int NOTIFICATION_ID_PROGRESS = -1000004;
    private static final int NOTIFY = 0;
    static final String NOT_THROUGH_HANDOVER = "(confirm != '5')";
    private static final String OPP_NOTIFICATION_CHANNEL = "opp_notification_channel";
    static final String STATUS = "(status == '192')";
    private static final String TAG = "BluetoothOppNotification";
    private static final boolean V = false;
    static final String VISIBLE = "(visibility IS NULL OR visibility == '0')";
    static final String WHERE_COMPLETED = "status >= '200' AND (visibility IS NULL OR visibility == '0') AND (confirm != '5')";
    private static final String WHERE_COMPLETED_INBOUND = "status >= '200' AND (visibility IS NULL OR visibility == '0') AND (confirm != '5') AND (direction == 1)";
    private static final String WHERE_COMPLETED_OUTBOUND = "status >= '200' AND (visibility IS NULL OR visibility == '0') AND (confirm != '5') AND (direction == 0)";
    static final String WHERE_CONFIRM_PENDING = "confirm == '0' AND (visibility IS NULL OR visibility == '0')";
    static final String WHERE_RUNNING = "(status == '192') AND (visibility IS NULL OR visibility == '0') AND (confirm == '1' OR confirm == '2' OR confirm == '5')";
    private ContentResolver mContentResolver;
    private Context mContext;
    private NotificationChannel mNotificationChannel;
    public NotificationManager mNotificationMgr;
    private HashMap<String, NotificationItem> mNotifications;
    private NotificationUpdateThread mUpdateNotificationThread;
    private int mPendingUpdate = 0;
    private boolean mUpdateCompleteNotification = true;
    private Handler mHandler = new Handler() { // from class: com.android.bluetooth.opp.BluetoothOppNotification.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                synchronized (BluetoothOppNotification.this) {
                    if (BluetoothOppNotification.this.mPendingUpdate <= 0 || BluetoothOppNotification.this.mUpdateNotificationThread != null) {
                        if (BluetoothOppNotification.this.mPendingUpdate > 0) {
                            BluetoothOppNotification.this.mHandler.sendMessageDelayed(BluetoothOppNotification.this.mHandler.obtainMessage(0), 1000L);
                        }
                    } else {
                        BluetoothOppNotification.this.mUpdateNotificationThread = BluetoothOppNotification.this.new NotificationUpdateThread();
                        BluetoothOppNotification.this.mUpdateNotificationThread.start();
                        BluetoothOppNotification.this.mHandler.sendMessageDelayed(BluetoothOppNotification.this.mHandler.obtainMessage(0), 1000L);
                    }
                }
            }
        }
    };

    static class NotificationItem {
        public String description;
        public String destination;
        public int direction;
        public int id;
        public long totalCurrent = 0;
        public long totalTotal = 0;
        public long timeStamp = 0;
        public boolean handoverInitiated = false;

        NotificationItem() {
        }
    }

    BluetoothOppNotification(Context ctx) {
        this.mContentResolver = null;
        this.mContext = ctx;
        this.mNotificationMgr = (NotificationManager) ctx.getSystemService(BluetoothMapContract.RECEPTION_STATE_NOTIFICATION);
        NotificationChannel notificationChannel = new NotificationChannel(OPP_NOTIFICATION_CHANNEL, this.mContext.getString(R.string.opp_notification_group), 4);
        this.mNotificationChannel = notificationChannel;
        this.mNotificationMgr.createNotificationChannel(notificationChannel);
        this.mNotifications = new HashMap<>();
        this.mContentResolver = this.mContext.getContentResolver();
    }

    public void updateNotification() {
        synchronized (this) {
            int i = this.mPendingUpdate + 1;
            this.mPendingUpdate = i;
            if (i > 1) {
                return;
            }
            if (!this.mHandler.hasMessages(0)) {
                this.mHandler.sendMessage(this.mHandler.obtainMessage(0));
            }
        }
    }

    private class NotificationUpdateThread extends Thread {
        NotificationUpdateThread() {
            super("Notification Update Thread");
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Process.setThreadPriority(10);
            synchronized (BluetoothOppNotification.this) {
                if (BluetoothOppNotification.this.mUpdateNotificationThread == this) {
                    BluetoothOppNotification.this.mPendingUpdate = 0;
                } else {
                    throw new IllegalStateException("multiple UpdateThreads in BluetoothOppNotification");
                }
            }
            BluetoothOppNotification.this.updateActiveNotification();
            BluetoothOppNotification.this.updateCompletedNotification();
            BluetoothOppNotification.this.updateIncomingFileConfirmNotification();
            synchronized (BluetoothOppNotification.this) {
                BluetoothOppNotification.this.mUpdateNotificationThread = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateActiveNotification() {
        float progress;
        boolean z;
        int dataIndex;
        int filenameHintIndex;
        int confirmIndex;
        int destinationIndex;
        Cursor cursor = this.mContentResolver.query(BluetoothShare.CONTENT_URI, null, WHERE_RUNNING, null, "_id");
        if (cursor == null) {
            return;
        }
        if (cursor.getCount() > 0) {
            this.mUpdateCompleteNotification = false;
        } else {
            this.mUpdateCompleteNotification = true;
        }
        int timestampIndex = cursor.getColumnIndexOrThrow("timestamp");
        int directionIndex = cursor.getColumnIndexOrThrow(BluetoothShare.DIRECTION);
        int idIndex = cursor.getColumnIndexOrThrow("_id");
        int totalBytesIndex = cursor.getColumnIndexOrThrow(BluetoothShare.TOTAL_BYTES);
        int currentBytesIndex = cursor.getColumnIndexOrThrow(BluetoothShare.CURRENT_BYTES);
        int dataIndex2 = cursor.getColumnIndexOrThrow(BluetoothShare._DATA);
        int filenameHintIndex2 = cursor.getColumnIndexOrThrow(BluetoothShare.FILENAME_HINT);
        int confirmIndex2 = cursor.getColumnIndexOrThrow(BluetoothShare.USER_CONFIRMATION);
        int destinationIndex2 = cursor.getColumnIndexOrThrow(BluetoothShare.DESTINATION);
        this.mNotifications.clear();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            long timeStamp = cursor.getLong(timestampIndex);
            int dir = cursor.getInt(directionIndex);
            int id = cursor.getInt(idIndex);
            int directionIndex2 = directionIndex;
            long total = cursor.getLong(totalBytesIndex);
            int idIndex2 = idIndex;
            int totalBytesIndex2 = totalBytesIndex;
            long current = cursor.getLong(currentBytesIndex);
            int timestampIndex2 = timestampIndex;
            int confirmation = cursor.getInt(confirmIndex2);
            int currentBytesIndex2 = currentBytesIndex;
            String destination = cursor.getString(destinationIndex2);
            String fileName = cursor.getString(dataIndex2);
            if (fileName == null) {
                fileName = cursor.getString(filenameHintIndex2);
            }
            if (fileName != null) {
                dataIndex = dataIndex2;
                filenameHintIndex = filenameHintIndex2;
            } else {
                dataIndex = dataIndex2;
                filenameHintIndex = filenameHintIndex2;
                fileName = this.mContext.getString(R.string.unknown_file);
            }
            String batchID = Long.toString(timeStamp);
            if (this.mNotifications.containsKey(batchID)) {
                confirmIndex = confirmIndex2;
                destinationIndex = destinationIndex2;
            } else {
                NotificationItem item = new NotificationItem();
                item.timeStamp = timeStamp;
                item.id = id;
                item.direction = dir;
                int id2 = item.direction;
                if (id2 == 0) {
                    confirmIndex = confirmIndex2;
                    destinationIndex = destinationIndex2;
                    item.description = this.mContext.getString(R.string.notification_sending, fileName);
                } else {
                    confirmIndex = confirmIndex2;
                    destinationIndex = destinationIndex2;
                    if (item.direction == 1) {
                        item.description = this.mContext.getString(R.string.notification_receiving, fileName);
                    }
                }
                item.totalCurrent = current;
                item.totalTotal = total;
                item.handoverInitiated = confirmation == 5;
                item.destination = destination;
                this.mNotifications.put(batchID, item);
            }
            cursor.moveToNext();
            directionIndex = directionIndex2;
            idIndex = idIndex2;
            totalBytesIndex = totalBytesIndex2;
            timestampIndex = timestampIndex2;
            currentBytesIndex = currentBytesIndex2;
            dataIndex2 = dataIndex;
            filenameHintIndex2 = filenameHintIndex;
            confirmIndex2 = confirmIndex;
            destinationIndex2 = destinationIndex;
        }
        cursor.close();
        for (NotificationItem item2 : this.mNotifications.values()) {
            if (item2.handoverInitiated) {
                if (item2.totalTotal == -1) {
                    progress = -1.0f;
                } else {
                    progress = item2.totalCurrent / item2.totalTotal;
                }
                Intent intent = new Intent("android.nfc.handover.intent.action.TRANSFER_PROGRESS");
                if (item2.direction == 1) {
                    intent.putExtra("android.nfc.handover.intent.extra.TRANSFER_DIRECTION", 0);
                } else {
                    intent.putExtra("android.nfc.handover.intent.extra.TRANSFER_DIRECTION", 1);
                }
                intent.putExtra("android.nfc.handover.intent.extra.TRANSFER_ID", item2.id);
                intent.putExtra("android.nfc.handover.intent.extra.TRANSFER_PROGRESS", progress);
                intent.putExtra("android.nfc.handover.intent.extra.ADDRESS", item2.destination);
                this.mContext.sendBroadcast(intent, "android.permission.NFC_HANDOVER_STATUS");
            } else {
                Notification.Builder b = new Notification.Builder(this.mContext, OPP_NOTIFICATION_CHANNEL);
                b.setOnlyAlertOnce(true);
                b.setColor(this.mContext.getResources().getColor(android.R.color.system_notification_accent_color, this.mContext.getTheme()));
                b.setContentTitle(item2.description);
                b.setSubText(BluetoothOppUtility.formatProgressText(item2.totalTotal, item2.totalCurrent));
                if (item2.totalTotal != 0) {
                    b.setProgress(100, (int) ((item2.totalCurrent * 100) / item2.totalTotal), item2.totalTotal == -1);
                } else {
                    b.setProgress(100, 100, item2.totalTotal == -1);
                }
                b.setWhen(item2.timeStamp);
                if (item2.direction == 0) {
                    b.setSmallIcon(android.R.drawable.stat_sys_upload);
                    z = true;
                } else {
                    z = true;
                    if (item2.direction == 1) {
                        b.setSmallIcon(android.R.drawable.stat_sys_download);
                    }
                }
                b.setOngoing(z);
                b.setLocalOnly(z);
                Intent intent2 = new Intent("android.btopp.intent.action.LIST");
                intent2.setClassName("com.android.bluetooth", BluetoothOppReceiver.class.getName());
                intent2.setDataAndNormalize(Uri.parse(BluetoothShare.CONTENT_URI + "/" + item2.id));
                b.setContentIntent(PendingIntent.getBroadcast(this.mContext, 0, intent2, 0));
                this.mNotificationMgr.notify(NOTIFICATION_ID_PROGRESS, b.build());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCompletedNotification() {
        int inboundSuccNumber;
        int inboundFailNumber;
        long timeStamp = 0;
        int outboundSuccNumber = 0;
        int outboundFailNumber = 0;
        Cursor cursor = this.mContentResolver.query(BluetoothShare.CONTENT_URI, null, WHERE_COMPLETED_OUTBOUND, null, "timestamp DESC");
        if (cursor == null) {
            return;
        }
        int timestampIndex = cursor.getColumnIndexOrThrow("timestamp");
        int statusIndex = cursor.getColumnIndexOrThrow("status");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if (cursor.isFirst()) {
                timeStamp = cursor.getLong(timestampIndex);
            }
            int status = cursor.getInt(statusIndex);
            if (BluetoothShare.isStatusError(status)) {
                outboundFailNumber++;
            } else {
                outboundSuccNumber++;
            }
            cursor.moveToNext();
        }
        cursor.close();
        int outboundNum = outboundSuccNumber + outboundFailNumber;
        if (outboundNum > 0) {
            inboundSuccNumber = 0;
            String unsuccessCaption = this.mContext.getResources().getQuantityString(R.plurals.noti_caption_unsuccessful, outboundFailNumber, Integer.valueOf(outboundFailNumber));
            String caption = this.mContext.getResources().getQuantityString(R.plurals.noti_caption_success, outboundSuccNumber, Integer.valueOf(outboundSuccNumber), unsuccessCaption);
            Intent contentIntent = new Intent("android.btopp.intent.action.OPEN_OUTBOUND").setClassName("com.android.bluetooth", BluetoothOppReceiver.class.getName());
            Intent deleteIntent = new Intent("android.btopp.intent.action.HIDE_COMPLETE").setClassName("com.android.bluetooth", BluetoothOppReceiver.class.getName());
            inboundFailNumber = 0;
            Notification outNoti = new Notification.Builder(this.mContext, OPP_NOTIFICATION_CHANNEL).setOnlyAlertOnce(true).setContentTitle(this.mContext.getString(R.string.outbound_noti_title)).setContentText(caption).setSmallIcon(android.R.drawable.stat_sys_upload_done).setColor(this.mContext.getResources().getColor(android.R.color.system_notification_accent_color, this.mContext.getTheme())).setContentIntent(PendingIntent.getBroadcast(this.mContext, 0, contentIntent, 0)).setDeleteIntent(PendingIntent.getBroadcast(this.mContext, 0, deleteIntent, 0)).setWhen(timeStamp).setLocalOnly(true).build();
            this.mNotificationMgr.notify(NOTIFICATION_ID_OUTBOUND_COMPLETE, outNoti);
        } else {
            inboundSuccNumber = 0;
            inboundFailNumber = 0;
            NotificationManager notificationManager = this.mNotificationMgr;
            if (notificationManager != null) {
                notificationManager.cancel(NOTIFICATION_ID_OUTBOUND_COMPLETE);
            }
        }
        Cursor cursor2 = this.mContentResolver.query(BluetoothShare.CONTENT_URI, null, WHERE_COMPLETED_INBOUND, null, "timestamp DESC");
        if (cursor2 == null) {
            return;
        }
        cursor2.moveToFirst();
        int inboundSuccNumber2 = inboundSuccNumber;
        int inboundFailNumber2 = inboundFailNumber;
        while (!cursor2.isAfterLast()) {
            if (cursor2.isFirst()) {
                timeStamp = cursor2.getLong(timestampIndex);
            }
            int status2 = cursor2.getInt(statusIndex);
            if (BluetoothShare.isStatusError(status2)) {
                inboundFailNumber2++;
            } else {
                inboundSuccNumber2++;
            }
            cursor2.moveToNext();
        }
        cursor2.close();
        int inboundNum = inboundSuccNumber2 + inboundFailNumber2;
        if (inboundNum <= 0) {
            NotificationManager notificationManager2 = this.mNotificationMgr;
            if (notificationManager2 != null) {
                notificationManager2.cancel(NOTIFICATION_ID_INBOUND_COMPLETE);
                return;
            }
            return;
        }
        String unsuccessCaption2 = this.mContext.getResources().getQuantityString(R.plurals.noti_caption_unsuccessful, inboundFailNumber2, Integer.valueOf(inboundFailNumber2));
        String caption2 = this.mContext.getResources().getQuantityString(R.plurals.noti_caption_success, inboundSuccNumber2, Integer.valueOf(inboundSuccNumber2), unsuccessCaption2);
        Intent contentIntent2 = new Intent("android.btopp.intent.action.OPEN_INBOUND").setClassName("com.android.bluetooth", BluetoothOppReceiver.class.getName());
        Intent deleteIntent2 = new Intent("android.btopp.intent.action.HIDE_COMPLETE").setClassName("com.android.bluetooth", BluetoothOppReceiver.class.getName());
        Notification inNoti = new Notification.Builder(this.mContext, OPP_NOTIFICATION_CHANNEL).setOnlyAlertOnce(true).setContentTitle(this.mContext.getString(R.string.inbound_noti_title)).setContentText(caption2).setSmallIcon(android.R.drawable.stat_sys_download_done).setColor(this.mContext.getResources().getColor(android.R.color.system_notification_accent_color, this.mContext.getTheme())).setContentIntent(PendingIntent.getBroadcast(this.mContext, 0, contentIntent2, 0)).setDeleteIntent(PendingIntent.getBroadcast(this.mContext, 0, deleteIntent2, 0)).setWhen(timeStamp).setLocalOnly(true).build();
        this.mNotificationMgr.notify(NOTIFICATION_ID_INBOUND_COMPLETE, inNoti);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateIncomingFileConfirmNotification() {
        Cursor cursor = this.mContentResolver.query(BluetoothShare.CONTENT_URI, null, WHERE_CONFIRM_PENDING, null, "_id");
        if (cursor == null) {
            return;
        }
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            BluetoothOppTransferInfo info = new BluetoothOppTransferInfo();
            BluetoothOppUtility.fillRecord(this.mContext, cursor, info);
            Uri contentUri = Uri.parse(BluetoothShare.CONTENT_URI + "/" + info.mID);
            Intent baseIntent = new Intent().setDataAndNormalize(contentUri).setClassName("com.android.bluetooth", BluetoothOppReceiver.class.getName());
            Notification.Action actionDecline = new Notification.Action.Builder(Icon.createWithResource(this.mContext, R.drawable.ic_decline), this.mContext.getText(R.string.incoming_file_confirm_cancel), PendingIntent.getBroadcast(this.mContext, 0, new Intent(baseIntent).setAction("android.btopp.intent.action.DECLINE"), 0)).build();
            Notification.Action actionAccept = new Notification.Action.Builder(Icon.createWithResource(this.mContext, R.drawable.ic_accept), this.mContext.getText(R.string.incoming_file_confirm_ok), PendingIntent.getBroadcast(this.mContext, 0, new Intent(baseIntent).setAction("android.btopp.intent.action.ACCEPT"), 0)).build();
            Notification public_n = new Notification.Builder(this.mContext, OPP_NOTIFICATION_CHANNEL).setOnlyAlertOnce(true).setOngoing(true).setWhen(info.mTimeStamp.longValue()).addAction(actionDecline).addAction(actionAccept).setContentIntent(PendingIntent.getBroadcast(this.mContext, 0, new Intent(baseIntent).setAction("android.btopp.intent.action.CONFIRM"), 0)).setDeleteIntent(PendingIntent.getBroadcast(this.mContext, 0, new Intent(baseIntent).setAction("android.btopp.intent.action.HIDE"), 0)).setColor(this.mContext.getResources().getColor(android.R.color.system_notification_accent_color, this.mContext.getTheme())).setContentTitle(this.mContext.getText(R.string.incoming_file_confirm_Notification_title)).setContentText(info.mFileName).setStyle(new Notification.BigTextStyle().bigText(this.mContext.getString(R.string.incoming_file_confirm_Notification_content, info.mDeviceName, info.mFileName))).setSubText(Formatter.formatFileSize(this.mContext, info.mTotalBytes)).setSmallIcon(R.drawable.bt_incomming_file_notification).setLocalOnly(true).build();
            Notification n = new Notification.Builder(this.mContext, OPP_NOTIFICATION_CHANNEL).setOnlyAlertOnce(true).setOngoing(true).setWhen(info.mTimeStamp.longValue()).setContentIntent(PendingIntent.getBroadcast(this.mContext, 0, new Intent(baseIntent).setAction("android.btopp.intent.action.CONFIRM"), 0)).setDeleteIntent(PendingIntent.getBroadcast(this.mContext, 0, new Intent(baseIntent).setAction("android.btopp.intent.action.HIDE"), 0)).setColor(this.mContext.getResources().getColor(android.R.color.system_notification_accent_color, this.mContext.getTheme())).setContentTitle(this.mContext.getText(R.string.incoming_file_confirm_Notification_title)).setContentText(info.mFileName).setStyle(new Notification.BigTextStyle().bigText(this.mContext.getString(R.string.incoming_file_confirm_Notification_content, info.mDeviceName, info.mFileName))).setSubText(Formatter.formatFileSize(this.mContext, info.mTotalBytes)).setSmallIcon(R.drawable.bt_incomming_file_notification).setLocalOnly(true).setVisibility(0).setPublicVersion(public_n).build();
            this.mNotificationMgr.notify(NOTIFICATION_ID_PROGRESS, n);
            cursor.moveToNext();
        }
        cursor.close();
    }

    void cancelNotifications() {
        this.mHandler.removeCallbacksAndMessages(null);
        this.mNotificationMgr.cancelAll();
    }
}
