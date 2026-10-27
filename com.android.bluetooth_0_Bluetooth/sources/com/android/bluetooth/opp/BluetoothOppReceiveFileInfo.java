package com.android.bluetooth.opp;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.INetd;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothOppReceiveFileInfo {
    private static final boolean D = false;
    private static final int OPP_LENGTH_OF_FILE_NAME = 237;
    private static final boolean V = false;
    private static String sDesiredStoragePath = null;
    public String mData;
    public String mFileName;
    public Uri mInsertUri;
    public long mLength;
    public int mStatus;

    public BluetoothOppReceiveFileInfo(String data, long length, int status) {
        this.mData = data;
        this.mStatus = status;
        this.mLength = length;
    }

    public BluetoothOppReceiveFileInfo(String filename, long length, Uri insertUri, int status) {
        this.mFileName = filename;
        this.mStatus = status;
        this.mInsertUri = insertUri;
        this.mLength = length;
    }

    public BluetoothOppReceiveFileInfo(int status) {
        this(null, 0L, null, status);
    }

    public static BluetoothOppReceiveFileInfo generateFileInfo(Context context, int id) {
        long length;
        String extension;
        byte[] oldfilename;
        byte[] newfilename;
        ContentResolver contentResolver = context.getContentResolver();
        Uri contentUri = Uri.parse(BluetoothShare.CONTENT_URI + "/" + id);
        String hint = null;
        String mimeType = null;
        long length2 = 0;
        Cursor metadataCursor = contentResolver.query(contentUri, new String[]{BluetoothShare.FILENAME_HINT, BluetoothShare.TOTAL_BYTES, BluetoothShare.MIMETYPE}, null, null, null);
        if (metadataCursor == null) {
            length = 0;
        } else {
            try {
                if (metadataCursor.moveToFirst()) {
                    hint = metadataCursor.getString(0);
                    length2 = metadataCursor.getLong(1);
                    mimeType = metadataCursor.getString(2);
                }
                metadataCursor.close();
                length = length2;
            } catch (Throwable th) {
                metadataCursor.close();
                throw th;
            }
        }
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return new BluetoothOppReceiveFileInfo(BluetoothShare.STATUS_ERROR_NO_SDCARD);
        }
        String filename = choosefilename(hint);
        if (filename == null) {
            return new BluetoothOppReceiveFileInfo(BluetoothShare.STATUS_FILE_ERROR);
        }
        int dotIndex = filename.lastIndexOf(".");
        if (dotIndex < 0) {
            if (mimeType == null) {
                return new BluetoothOppReceiveFileInfo(BluetoothShare.STATUS_FILE_ERROR);
            }
            extension = INetd.NEXTHOP_NONE;
        } else {
            extension = filename.substring(dotIndex);
            filename = filename.substring(0, dotIndex);
        }
        if (filename.getBytes().length > OPP_LENGTH_OF_FILE_NAME) {
            Log.i(Constants.TAG, " File Name Length :" + filename.length());
            Log.i(Constants.TAG, " File Name Length in Bytes:" + filename.getBytes().length);
            try {
                oldfilename = filename.getBytes("UTF-8");
                newfilename = new byte[OPP_LENGTH_OF_FILE_NAME];
            } catch (UnsupportedEncodingException e) {
                e = e;
            }
            try {
                System.arraycopy(oldfilename, 0, newfilename, 0, OPP_LENGTH_OF_FILE_NAME);
                filename = new String(newfilename, "UTF-8");
            } catch (UnsupportedEncodingException e2) {
                e = e2;
                Log.e(Constants.TAG, "Exception: " + e);
            }
        }
        DateFormat dateFormat = new SimpleDateFormat("_hhmmss");
        String currentTime = dateFormat.format(Calendar.getInstance().getTime());
        String fullfilename = filename + currentTime + extension;
        ContentValues mediaContentValues = new ContentValues();
        mediaContentValues.put("_display_name", fullfilename);
        mediaContentValues.put("mime_type", mimeType);
        mediaContentValues.put("relative_path", Environment.DIRECTORY_DOWNLOADS);
        Uri insertUri = contentResolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, mediaContentValues);
        if (insertUri == null) {
            return new BluetoothOppReceiveFileInfo(BluetoothShare.STATUS_FILE_ERROR);
        }
        Log.d(Constants.TAG, "file crated, insertUri:" + insertUri.toString());
        return new BluetoothOppReceiveFileInfo(fullfilename, length, insertUri, 0);
    }

    private static String choosefilename(String hint) {
        if (0 != 0 || hint == null || hint.endsWith("/") || hint.endsWith("\\")) {
            return null;
        }
        String hint2 = hint.replace('\\', '/').replaceAll("\\s", " ").replaceAll("[:\"<>*?|]", "_");
        int index = hint2.lastIndexOf(47) + 1;
        if (index > 0) {
            String filename = hint2.substring(index);
            return filename;
        }
        return hint2;
    }
}
