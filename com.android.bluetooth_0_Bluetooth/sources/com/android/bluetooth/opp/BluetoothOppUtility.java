package com.android.bluetooth.opp;

import android.app.NotificationManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.INetd;
import android.net.Uri;
import android.os.Environment;
import android.os.SystemProperties;
import android.util.Log;
import com.android.bluetooth.R;
import com.android.bluetooth.mapapi.BluetoothMapContract;
import com.android.vcard.VCardConfig;
import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothOppUtility {
    private static final boolean D = false;
    private static final String TAG = "BluetoothOppUtility";
    private static final boolean V = false;
    private static Boolean sNoSdCard = null;
    private static final ConcurrentHashMap<Uri, BluetoothOppSendFileInfo> sSendFileMap = new ConcurrentHashMap<>();

    public static boolean isBluetoothShareUri(Uri uri) {
        return uri.toString().startsWith(BluetoothShare.CONTENT_URI.toString());
    }

    public static BluetoothOppTransferInfo queryRecord(Context context, Uri uri) {
        BluetoothOppTransferInfo info = new BluetoothOppTransferInfo();
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                fillRecord(context, cursor, info);
            }
            cursor.close();
            return info;
        }
        return null;
    }

    public static void fillRecord(Context context, Cursor cursor, BluetoothOppTransferInfo info) {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        info.mID = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
        info.mStatus = cursor.getInt(cursor.getColumnIndexOrThrow("status"));
        info.mDirection = cursor.getInt(cursor.getColumnIndexOrThrow(BluetoothShare.DIRECTION));
        info.mTotalBytes = cursor.getLong(cursor.getColumnIndexOrThrow(BluetoothShare.TOTAL_BYTES));
        info.mCurrentBytes = cursor.getLong(cursor.getColumnIndexOrThrow(BluetoothShare.CURRENT_BYTES));
        info.mTimeStamp = Long.valueOf(cursor.getLong(cursor.getColumnIndexOrThrow("timestamp")));
        info.mDestAddr = cursor.getString(cursor.getColumnIndexOrThrow(BluetoothShare.DESTINATION));
        info.mFileName = cursor.getString(cursor.getColumnIndexOrThrow(BluetoothShare._DATA));
        if (info.mFileName == null) {
            info.mFileName = cursor.getString(cursor.getColumnIndexOrThrow(BluetoothShare.FILENAME_HINT));
        }
        if (info.mFileName == null) {
            info.mFileName = context.getString(R.string.unknown_file);
        }
        info.mFileUri = cursor.getString(cursor.getColumnIndexOrThrow("uri"));
        if (info.mFileUri != null) {
            Uri u = Uri.parse(info.mFileUri);
            info.mFileType = context.getContentResolver().getType(u);
        } else {
            Uri u2 = Uri.parse(info.mFileName);
            info.mFileType = context.getContentResolver().getType(u2);
        }
        if (info.mFileType == null) {
            info.mFileType = cursor.getString(cursor.getColumnIndexOrThrow(BluetoothShare.MIMETYPE));
        }
        BluetoothDevice remoteDevice = adapter.getRemoteDevice(info.mDestAddr);
        info.mDeviceName = BluetoothOppManager.getInstance(context).getDeviceName(remoteDevice);
        int confirmationType = cursor.getInt(cursor.getColumnIndexOrThrow(BluetoothShare.USER_CONFIRMATION));
        info.mHandoverInitiated = confirmationType == 5;
    }

    public static ArrayList<String> queryTransfersInBatch(Context context, Long timeStamp) {
        ArrayList<String> uris = new ArrayList<>();
        String where = "timestamp == " + timeStamp;
        Cursor metadataCursor = context.getContentResolver().query(BluetoothShare.CONTENT_URI, new String[]{BluetoothShare._DATA}, where, null, "_id");
        if (metadataCursor == null) {
            return null;
        }
        metadataCursor.moveToFirst();
        while (!metadataCursor.isAfterLast()) {
            String fileName = metadataCursor.getString(0);
            Uri path = Uri.parse(fileName);
            if (path.getScheme() == null) {
                path = Uri.fromFile(new File(fileName));
            }
            uris.add(path.toString());
            metadataCursor.moveToNext();
        }
        metadataCursor.close();
        return uris;
    }

    public static void openReceivedFile(Context context, String fileName, String mimetype, Long timeStamp, Uri uri) {
        if (fileName == null || mimetype == null) {
            Log.e(TAG, "ERROR: Para fileName ==null, or mimetype == null");
            return;
        }
        if (!isBluetoothShareUri(uri)) {
            Log.e(TAG, "Trying to open a file that wasn't transfered over Bluetooth");
            return;
        }
        Uri path = null;
        Cursor metadataCursor = context.getContentResolver().query(uri, new String[]{"uri"}, null, null, null);
        if (metadataCursor != null) {
            try {
                if (metadataCursor.moveToFirst()) {
                    path = Uri.parse(metadataCursor.getString(0));
                }
            } finally {
                metadataCursor.close();
            }
        }
        if (path == null) {
            Log.e(TAG, "file uri not exist");
            return;
        }
        if (!fileExists(context, path)) {
            Intent in = new Intent(context, (Class<?>) BluetoothOppBtErrorActivity.class);
            in.setFlags(VCardConfig.FLAG_REFRAIN_QP_TO_NAME_PROPERTIES);
            in.putExtra("title", context.getString(R.string.not_exist_file));
            in.putExtra("content", context.getString(R.string.not_exist_file_desc));
            context.startActivity(in);
            context.getContentResolver().delete(uri, null, null);
            return;
        }
        if (isRecognizedFileType(context, path, mimetype)) {
            Intent activityIntent = new Intent("android.intent.action.VIEW");
            activityIntent.setDataAndTypeAndNormalize(path, mimetype);
            context.getPackageManager().queryIntentActivities(activityIntent, 65536);
            activityIntent.setFlags(VCardConfig.FLAG_REFRAIN_QP_TO_NAME_PROPERTIES);
            activityIntent.addFlags(1);
            try {
                context.startActivity(activityIntent);
                return;
            } catch (ActivityNotFoundException e) {
                return;
            }
        }
        Intent in2 = new Intent(context, (Class<?>) BluetoothOppBtErrorActivity.class);
        in2.setFlags(VCardConfig.FLAG_REFRAIN_QP_TO_NAME_PROPERTIES);
        in2.putExtra("title", context.getString(R.string.unknown_file));
        in2.putExtra("content", context.getString(R.string.unknown_file_desc));
        context.startActivity(in2);
    }

    static boolean fileExists(Context context, Uri uri) {
        ContentResolver resolver = context.getContentResolver();
        try {
            resolver.openFileDescriptor(uri, "r");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isRecognizedFileType(Context context, Uri fileUri, String mimetype) {
        Intent mimetypeIntent = new Intent("android.intent.action.VIEW");
        mimetypeIntent.setDataAndTypeAndNormalize(fileUri, mimetype);
        List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(mimetypeIntent, 65536);
        if (list.size() != 0) {
            return true;
        }
        return false;
    }

    public static void updateVisibilityToHidden(Context context, Uri uri) {
        ContentValues updateValues = new ContentValues();
        updateValues.put(BluetoothShare.VISIBILITY, (Integer) 1);
        context.getContentResolver().update(uri, updateValues, null, null);
    }

    public static String formatProgressText(long totalBytes, long currentBytes) {
        DecimalFormat df = new DecimalFormat("0%");
        df.setRoundingMode(RoundingMode.DOWN);
        double percent = 0.0d;
        if (totalBytes > 0) {
            percent = currentBytes / totalBytes;
        }
        return df.format(percent);
    }

    public static boolean deviceHasNoSdCard() {
        if (sNoSdCard == null) {
            String characteristics = SystemProperties.get("ro.build.characteristics", INetd.NEXTHOP_NONE);
            sNoSdCard = Boolean.valueOf(Arrays.asList(characteristics).contains("nosdcard"));
        }
        return sNoSdCard.booleanValue();
    }

    public static String getStatusDescription(Context context, int statusCode, String deviceName) {
        int id;
        if (statusCode == 190) {
            String ret = context.getString(R.string.status_pending);
            return ret;
        }
        if (statusCode == 192) {
            String ret2 = context.getString(R.string.status_running);
            return ret2;
        }
        if (statusCode == 200) {
            String ret3 = context.getString(R.string.status_success);
            return ret3;
        }
        if (statusCode == 406) {
            String ret4 = context.getString(R.string.status_not_accept);
            return ret4;
        }
        if (statusCode == 403) {
            String ret5 = context.getString(R.string.status_forbidden);
            return ret5;
        }
        if (statusCode == 490) {
            String ret6 = context.getString(R.string.status_canceled);
            return ret6;
        }
        if (statusCode == 492) {
            String ret7 = context.getString(R.string.status_file_error);
            return ret7;
        }
        if (statusCode == 493) {
            if (deviceHasNoSdCard()) {
                id = R.string.status_no_sd_card_nosdcard;
            } else {
                id = R.string.status_no_sd_card_default;
            }
            String ret8 = context.getString(id);
            return ret8;
        }
        if (statusCode == 497) {
            String ret9 = context.getString(R.string.status_connection_error);
            return ret9;
        }
        if (statusCode == 494) {
            int id2 = deviceHasNoSdCard() ? R.string.bt_sm_2_1_nosdcard : R.string.bt_sm_2_1_default;
            String ret10 = context.getString(id2);
            return ret10;
        }
        if (statusCode == 400 || statusCode == 411 || statusCode == 412 || statusCode == 495 || statusCode == 496) {
            String ret11 = context.getString(R.string.status_protocol_error);
            return ret11;
        }
        String ret12 = context.getString(R.string.status_unknown_error);
        return ret12;
    }

    public static void retryTransfer(Context context, BluetoothOppTransferInfo transInfo) {
        ContentValues values = new ContentValues();
        values.put("uri", transInfo.mFileUri);
        values.put(BluetoothShare.MIMETYPE, transInfo.mFileType);
        values.put(BluetoothShare.DESTINATION, transInfo.mDestAddr);
        context.getContentResolver().insert(BluetoothShare.CONTENT_URI, values);
    }

    static Uri originalUri(Uri uri) {
        String mUri = uri.toString();
        int atIndex = mUri.lastIndexOf("@");
        if (atIndex != -1) {
            return Uri.parse(mUri.substring(0, atIndex));
        }
        return uri;
    }

    static Uri generateUri(Uri uri, BluetoothOppSendFileInfo sendFileInfo) {
        String fileInfo = sendFileInfo.toString();
        int atIndex = fileInfo.lastIndexOf("@");
        return Uri.parse(uri + fileInfo.substring(atIndex));
    }

    static void putSendFileInfo(Uri uri, BluetoothOppSendFileInfo sendFileInfo) {
        if (sendFileInfo == BluetoothOppSendFileInfo.SEND_FILE_INFO_ERROR) {
            Log.e(TAG, "putSendFileInfo: bad sendFileInfo, URI: " + uri);
        }
        sSendFileMap.put(uri, sendFileInfo);
    }

    static BluetoothOppSendFileInfo getSendFileInfo(Uri uri) {
        BluetoothOppSendFileInfo info = sSendFileMap.get(uri);
        return info != null ? info : BluetoothOppSendFileInfo.SEND_FILE_INFO_ERROR;
    }

    static void closeSendFileInfo(Uri uri) {
        BluetoothOppSendFileInfo info = sSendFileMap.remove(uri);
        if (info != null && info.mInputStream != null) {
            try {
                info.mInputStream.close();
            } catch (IOException e) {
            }
        }
    }

    static boolean isInExternalStorageDir(Uri uri) {
        if (!"file".equals(uri.getScheme())) {
            Log.e(TAG, "Not a file URI: " + uri);
            return false;
        }
        File file = new File(uri.getCanonicalUri().getPath());
        return isSameOrSubDirectory(Environment.getExternalStorageDirectory(), file);
    }

    static boolean isForbiddenContent(Uri uri) {
        if ("com.android.bluetooth.map.MmsFileProvider".equals(uri.getHost())) {
            return true;
        }
        return false;
    }

    static boolean isSameOrSubDirectory(File base, File child) {
        try {
            File base2 = base.getCanonicalFile();
            for (File parentFile = child.getCanonicalFile(); parentFile != null; parentFile = parentFile.getParentFile()) {
                if (base2.equals(parentFile)) {
                    return true;
                }
            }
            return false;
        } catch (IOException ex) {
            Log.e(TAG, "Error while accessing file", ex);
            return false;
        }
    }

    protected static void cancelNotification(Context ctx) {
        NotificationManager nm = (NotificationManager) ctx.getSystemService(BluetoothMapContract.RECEPTION_STATE_NOTIFICATION);
        nm.cancel(BluetoothOppNotification.NOTIFICATION_ID_PROGRESS);
    }
}
