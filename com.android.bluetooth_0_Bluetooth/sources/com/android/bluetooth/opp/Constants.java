package com.android.bluetooth.opp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import java.io.IOException;
import java.util.regex.Pattern;
import javax.obex.HeaderSet;

/* JADX INFO: loaded from: classes.dex */
public class Constants {
    static final String[] ACCEPTABLE_SHARE_INBOUND_TYPES = {"image/*", "video/*", "audio/*", "text/x-vcard", "text/x-vcalendar", "text/calendar", "text/plain", "text/html", "text/xml", "application/zip", "application/vnd.ms-excel", "application/msword", "application/vnd.ms-powerpoint", "application/pdf", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "application/vnd.openxmlformats-officedocument.wordprocessingml.document", "application/vnd.openxmlformats-officedocument.presentationml.presentation", "application/x-hwp"};
    static final String ACTION_ACCEPT = "android.btopp.intent.action.ACCEPT";
    static final String ACTION_BT_OPP_TRANSFER_DONE = "android.nfc.handover.intent.action.TRANSFER_DONE";
    static final String ACTION_BT_OPP_TRANSFER_PROGRESS = "android.nfc.handover.intent.action.TRANSFER_PROGRESS";
    static final String ACTION_COMPLETE_HIDE = "android.btopp.intent.action.HIDE_COMPLETE";
    static final String ACTION_DECLINE = "android.btopp.intent.action.DECLINE";
    static final String ACTION_HANDOVER_SEND = "android.nfc.handover.intent.action.HANDOVER_SEND";
    static final String ACTION_HANDOVER_SEND_MULTIPLE = "android.nfc.handover.intent.action.HANDOVER_SEND_MULTIPLE";
    static final String ACTION_HANDOVER_STARTED = "android.nfc.handover.intent.action.HANDOVER_STARTED";
    static final String ACTION_HIDE = "android.btopp.intent.action.HIDE";
    static final String ACTION_INCOMING_FILE_CONFIRM = "android.btopp.intent.action.CONFIRM";
    static final String ACTION_LIST = "android.btopp.intent.action.LIST";
    static final String ACTION_OPEN = "android.btopp.intent.action.OPEN";
    static final String ACTION_OPEN_INBOUND_TRANSFER = "android.btopp.intent.action.OPEN_INBOUND";
    static final String ACTION_OPEN_OUTBOUND_TRANSFER = "android.btopp.intent.action.OPEN_OUTBOUND";
    static final String ACTION_OPEN_RECEIVED_FILES = "android.btopp.intent.action.OPEN_RECEIVED_FILES";
    static final String ACTION_RETRY = "android.btopp.intent.action.RETRY";
    static final String ACTION_STOP_HANDOVER = "android.btopp.intent.action.STOP_HANDOVER_TRANSFER";
    static final String ACTION_WHITELIST_DEVICE = "android.btopp.intent.action.WHITELIST_DEVICE";
    static final int BATCH_STATUS_FAILED = 3;
    static final int BATCH_STATUS_FINISHED = 2;
    static final int BATCH_STATUS_PENDING = 0;
    static final int BATCH_STATUS_RUNNING = 1;
    static final String BLUETOOTHOPP_CHANNEL_PREFERENCE = "btopp_channels";
    static final String BLUETOOTHOPP_NAME_PREFERENCE = "btopp_names";
    static final int COUNT_HEADER_UNAVAILABLE = -1;
    static final boolean DEBUG = false;
    static final String DEFAULT_STORE_SUBDIR = "/bluetooth";
    static final int DIRECTION_BLUETOOTH_INCOMING = 0;
    static final int DIRECTION_BLUETOOTH_OUTGOING = 1;
    static final String EXTRA_BT_OPP_ADDRESS = "android.nfc.handover.intent.extra.ADDRESS";
    static final String EXTRA_BT_OPP_OBJECT_COUNT = "android.nfc.handover.intent.extra.OBJECT_COUNT";
    static final String EXTRA_BT_OPP_TRANSFER_DIRECTION = "android.nfc.handover.intent.extra.TRANSFER_DIRECTION";
    static final String EXTRA_BT_OPP_TRANSFER_ID = "android.nfc.handover.intent.extra.TRANSFER_ID";
    static final String EXTRA_BT_OPP_TRANSFER_MIMETYPE = "android.nfc.handover.intent.extra.TRANSFER_MIME_TYPE";
    static final String EXTRA_BT_OPP_TRANSFER_PROGRESS = "android.nfc.handover.intent.extra.TRANSFER_PROGRESS";
    static final String EXTRA_BT_OPP_TRANSFER_STATUS = "android.nfc.handover.intent.extra.TRANSFER_STATUS";
    static final String EXTRA_BT_OPP_TRANSFER_URI = "android.nfc.handover.intent.extra.TRANSFER_URI";
    static final String EXTRA_SHOW_ALL_FILES = "android.btopp.intent.extra.SHOW_ALL";
    static final String FILENAME_SEQUENCE_SEPARATOR = "-";
    static final String HANDOVER_STATUS_PERMISSION = "android.permission.NFC_HANDOVER_STATUS";
    static final int HANDOVER_TRANSFER_STATUS_FAILURE = 1;
    static final int HANDOVER_TRANSFER_STATUS_SUCCESS = 0;
    static final int MAX_RECORDS_IN_DATABASE = 50;
    static final String MEDIA_SCANNED = "scanned";
    static final int MEDIA_SCANNED_NOT_SCANNED = 0;
    static final int MEDIA_SCANNED_SCANNED_FAILED = 2;
    static final int MEDIA_SCANNED_SCANNED_OK = 1;
    static final int NFC_ALIVE_CHECK_MS = 10000;
    public static final String TAG = "BluetoothOpp";
    static final String THIS_PACKAGE_NAME = "com.android.bluetooth";
    static final boolean VERBOSE = false;

    static void updateShareStatus(Context context, int id, int status) {
        Uri contentUri = Uri.parse(BluetoothShare.CONTENT_URI + "/" + id);
        ContentValues updateValues = new ContentValues();
        updateValues.put("status", Integer.valueOf(status));
        context.getContentResolver().update(contentUri, updateValues, null, null);
        sendIntentIfCompleted(context, contentUri, status);
    }

    static void sendIntentIfCompleted(Context context, Uri contentUri, int status) {
        if (BluetoothShare.isStatusCompleted(status)) {
            Intent intent = new Intent(BluetoothShare.TRANSFER_COMPLETED_ACTION);
            intent.setClassName(THIS_PACKAGE_NAME, BluetoothOppReceiver.class.getName());
            intent.setDataAndNormalize(contentUri);
            context.sendBroadcast(intent);
        }
    }

    static boolean mimeTypeMatches(String mimeType, String[] matchAgainst) {
        for (String matchType : matchAgainst) {
            if (mimeTypeMatches(mimeType, matchType)) {
                return true;
            }
        }
        return false;
    }

    private static boolean mimeTypeMatches(String mimeType, String matchAgainst) {
        Pattern p = Pattern.compile(matchAgainst.replaceAll("\\*", "\\.\\*"), 2);
        return p.matcher(mimeType).matches();
    }

    static void logHeader(HeaderSet hs) {
        Log.v(TAG, "Dumping HeaderSet " + hs.toString());
        try {
            Log.v(TAG, "COUNT : " + hs.getHeader(BluetoothShare.STATUS_RUNNING));
            Log.v(TAG, "NAME : " + hs.getHeader(1));
            Log.v(TAG, "TYPE : " + hs.getHeader(66));
            Log.v(TAG, "LENGTH : " + hs.getHeader(195));
            Log.v(TAG, "TIME_ISO_8601 : " + hs.getHeader(68));
            Log.v(TAG, "TIME_4_BYTE : " + hs.getHeader(196));
            Log.v(TAG, "DESCRIPTION : " + hs.getHeader(5));
            Log.v(TAG, "TARGET : " + hs.getHeader(70));
            Log.v(TAG, "HTTP : " + hs.getHeader(71));
            Log.v(TAG, "WHO : " + hs.getHeader(74));
            Log.v(TAG, "OBJECT_CLASS : " + hs.getHeader(79));
            Log.v(TAG, "APPLICATION_PARAMETER : " + hs.getHeader(76));
        } catch (IOException e) {
            Log.e(TAG, "dump HeaderSet error " + e);
        }
    }
}
