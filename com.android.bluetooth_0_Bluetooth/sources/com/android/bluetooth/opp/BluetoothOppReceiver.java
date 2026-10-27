package com.android.bluetooth.opp;

import android.app.NotificationManager;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;
import com.android.bluetooth.R;
import com.android.bluetooth.mapapi.BluetoothMapContract;
import com.android.vcard.VCardConfig;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothOppReceiver extends BroadcastReceiver {
    private static final boolean D = false;
    private static final String TAG = "BluetoothOppReceiver";
    private static final boolean V = false;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action == null) {
            return;
        }
        if (action.equals("android.bluetooth.devicepicker.action.DEVICE_SELECTED")) {
            BluetoothOppManager mOppManager = BluetoothOppManager.getInstance(context);
            BluetoothDevice remoteDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            if (remoteDevice == null) {
                mOppManager.cleanUpSendingFileInfo();
                return;
            }
            mOppManager.startTransfer(remoteDevice);
            String deviceName = mOppManager.getDeviceName(remoteDevice);
            int batchSize = mOppManager.getBatchSize();
            Toast.makeText(context, mOppManager.mMultipleFlag ? context.getString(R.string.bt_toast_5, Integer.toString(batchSize), deviceName) : context.getString(R.string.bt_toast_4, deviceName), 0).show();
            return;
        }
        if (action.equals("android.btopp.intent.action.CONFIRM")) {
            Uri uri = intent.getData();
            Intent in = new Intent(context, (Class<?>) BluetoothOppIncomingFileConfirmActivity.class);
            in.setFlags(VCardConfig.FLAG_REFRAIN_QP_TO_NAME_PROPERTIES);
            in.setDataAndNormalize(uri);
            context.startActivity(in);
            return;
        }
        if (action.equals("android.btopp.intent.action.DECLINE")) {
            Uri uri2 = intent.getData();
            ContentValues values = new ContentValues();
            values.put(BluetoothShare.USER_CONFIRMATION, (Integer) 3);
            context.getContentResolver().update(uri2, values, null, null);
            cancelNotification(context, BluetoothOppNotification.NOTIFICATION_ID_PROGRESS);
            return;
        }
        if (action.equals("android.btopp.intent.action.ACCEPT")) {
            Uri uri3 = intent.getData();
            ContentValues values2 = new ContentValues();
            values2.put(BluetoothShare.USER_CONFIRMATION, (Integer) 1);
            context.getContentResolver().update(uri3, values2, null, null);
            return;
        }
        if (action.equals("android.btopp.intent.action.OPEN") || action.equals("android.btopp.intent.action.LIST")) {
            new BluetoothOppTransferInfo();
            Uri uri4 = intent.getData();
            BluetoothOppTransferInfo transInfo = BluetoothOppUtility.queryRecord(context, uri4);
            if (transInfo == null) {
                Log.e(TAG, "Error: Can not get data from db");
                return;
            }
            if (transInfo.mDirection != 1 || !BluetoothShare.isStatusSuccess(transInfo.mStatus)) {
                Intent in2 = new Intent(context, (Class<?>) BluetoothOppTransferActivity.class);
                in2.setFlags(335544320);
                in2.setDataAndNormalize(uri4);
                context.startActivity(in2);
                return;
            }
            BluetoothOppUtility.openReceivedFile(context, transInfo.mFileName, transInfo.mFileType, transInfo.mTimeStamp, uri4);
            BluetoothOppUtility.updateVisibilityToHidden(context, uri4);
            return;
        }
        if (action.equals("android.btopp.intent.action.OPEN_OUTBOUND")) {
            Intent in3 = new Intent(context, (Class<?>) BluetoothOppTransferHistory.class);
            in3.setFlags(335544320);
            in3.putExtra(BluetoothShare.DIRECTION, 0);
            context.startActivity(in3);
            return;
        }
        if (action.equals("android.btopp.intent.action.OPEN_INBOUND")) {
            Intent in4 = new Intent(context, (Class<?>) BluetoothOppTransferHistory.class);
            in4.setFlags(335544320);
            in4.putExtra(BluetoothShare.DIRECTION, 1);
            context.startActivity(in4);
            return;
        }
        if (action.equals("android.btopp.intent.action.OPEN_RECEIVED_FILES")) {
            Intent in5 = new Intent(context, (Class<?>) BluetoothOppTransferHistory.class);
            in5.setFlags(335544320);
            in5.putExtra(BluetoothShare.DIRECTION, 1);
            in5.putExtra("android.btopp.intent.extra.SHOW_ALL", true);
            context.startActivity(in5);
            return;
        }
        if (action.equals("android.btopp.intent.action.HIDE")) {
            Cursor cursor = context.getContentResolver().query(intent.getData(), null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    int visibilityColumn = cursor.getColumnIndexOrThrow(BluetoothShare.VISIBILITY);
                    int visibility = cursor.getInt(visibilityColumn);
                    int userConfirmationColumn = cursor.getColumnIndexOrThrow(BluetoothShare.USER_CONFIRMATION);
                    int userConfirmation = cursor.getInt(userConfirmationColumn);
                    if (userConfirmation == 0 && visibility == 0) {
                        ContentValues values3 = new ContentValues();
                        values3.put(BluetoothShare.VISIBILITY, (Integer) 1);
                        context.getContentResolver().update(intent.getData(), values3, null, null);
                    }
                }
                cursor.close();
                return;
            }
            return;
        }
        if (action.equals("android.btopp.intent.action.HIDE_COMPLETE")) {
            ContentValues updateValues = new ContentValues();
            updateValues.put(BluetoothShare.VISIBILITY, (Integer) 1);
            context.getContentResolver().update(BluetoothShare.CONTENT_URI, updateValues, "status >= '200' AND (visibility IS NULL OR visibility == '0') AND (confirm != '5')", null);
            return;
        }
        if (action.equals(BluetoothShare.TRANSFER_COMPLETED_ACTION)) {
            String toastMsg = null;
            new BluetoothOppTransferInfo();
            BluetoothOppTransferInfo transInfo2 = BluetoothOppUtility.queryRecord(context, intent.getData());
            if (transInfo2 == null) {
                Log.e(TAG, "Error: Can not get data from db");
                return;
            }
            if (transInfo2.mHandoverInitiated) {
                Intent handoverIntent = new Intent("android.nfc.handover.intent.action.TRANSFER_DONE");
                if (transInfo2.mDirection == 1) {
                    handoverIntent.putExtra("android.nfc.handover.intent.extra.TRANSFER_DIRECTION", 0);
                } else {
                    handoverIntent.putExtra("android.nfc.handover.intent.extra.TRANSFER_DIRECTION", 1);
                }
                handoverIntent.putExtra("android.nfc.handover.intent.extra.TRANSFER_ID", transInfo2.mID);
                handoverIntent.putExtra("android.nfc.handover.intent.extra.ADDRESS", transInfo2.mDestAddr);
                if (BluetoothShare.isStatusSuccess(transInfo2.mStatus)) {
                    handoverIntent.putExtra("android.nfc.handover.intent.extra.TRANSFER_STATUS", 0);
                    handoverIntent.putExtra("android.nfc.handover.intent.extra.TRANSFER_URI", transInfo2.mFileName);
                    handoverIntent.putExtra("android.nfc.handover.intent.extra.TRANSFER_MIME_TYPE", transInfo2.mFileType);
                } else {
                    handoverIntent.putExtra("android.nfc.handover.intent.extra.TRANSFER_STATUS", 1);
                }
                context.sendBroadcast(handoverIntent, "android.permission.NFC_HANDOVER_STATUS");
                return;
            }
            if (BluetoothShare.isStatusSuccess(transInfo2.mStatus)) {
                if (transInfo2.mDirection == 0) {
                    toastMsg = context.getString(R.string.notification_sent, transInfo2.mFileName);
                } else if (transInfo2.mDirection == 1) {
                    toastMsg = context.getString(R.string.notification_received, transInfo2.mFileName);
                }
            } else if (BluetoothShare.isStatusError(transInfo2.mStatus)) {
                if (transInfo2.mDirection == 0) {
                    toastMsg = context.getString(R.string.notification_sent_fail, transInfo2.mFileName);
                } else if (transInfo2.mDirection == 1) {
                    toastMsg = context.getString(R.string.download_fail_line1);
                }
            }
            if (toastMsg != null) {
                Toast.makeText(context, toastMsg, 0).show();
            }
        }
    }

    private void cancelNotification(Context context, int id) {
        NotificationManager notMgr = (NotificationManager) context.getSystemService(BluetoothMapContract.RECEPTION_STATE_NOTIFICATION);
        if (notMgr == null) {
            return;
        }
        notMgr.cancel(id);
    }
}
