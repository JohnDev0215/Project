package com.android.bluetooth.opp;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.util.EventLog;
import android.util.Log;
import com.android.bluetooth.R;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothOppSendFileInfo {
    private static final boolean D = false;
    static final BluetoothOppSendFileInfo SEND_FILE_INFO_ERROR = new BluetoothOppSendFileInfo(null, null, 0, null, BluetoothShare.STATUS_FILE_ERROR);
    private static final String TAG = "BluetoothOppSendFileInfo";
    public final String mData;
    public final String mFileName;
    public final FileInputStream mInputStream;
    public final long mLength;
    public final String mMimetype;
    public final int mStatus;

    public BluetoothOppSendFileInfo(String fileName, String type, long length, FileInputStream inputStream, int status) {
        this.mFileName = fileName;
        this.mMimetype = type;
        this.mLength = length;
        this.mInputStream = inputStream;
        this.mStatus = status;
        this.mData = null;
    }

    public BluetoothOppSendFileInfo(String data, String type, long length, int status) {
        this.mFileName = null;
        this.mInputStream = null;
        this.mData = data;
        this.mMimetype = type;
        this.mLength = length;
        this.mStatus = status;
    }

    public static BluetoothOppSendFileInfo generateFileInfo(Context context, Uri uri, String type, boolean fromExternal) throws IOException {
        String fileName;
        String str;
        String fileName2;
        String contentType;
        long length;
        FileInputStream is;
        long length2;
        int i;
        Cursor metadataCursor;
        String[] strArr;
        ContentResolver contentResolver = context.getContentResolver();
        String scheme = uri.getScheme();
        if (!"content".equals(scheme)) {
            fileName = "content";
            str = TAG;
            if (!"file".equals(scheme)) {
                return SEND_FILE_INFO_ERROR;
            }
            if (uri.getPath() == null) {
                Log.e(str, "Invalid URI path: " + uri);
                return SEND_FILE_INFO_ERROR;
            }
            if (fromExternal && !BluetoothOppUtility.isInExternalStorageDir(uri)) {
                EventLog.writeEvent(1397638484, "35310991", -1, uri.getPath());
                Log.e(str, "File based URI not in Environment.getExternalStorageDirectory() is not allowed.");
                return SEND_FILE_INFO_ERROR;
            }
            fileName2 = uri.getLastPathSegment();
            contentType = type;
            File f = new File(uri.getPath());
            length = f.length();
        } else {
            if (fromExternal && BluetoothOppUtility.isForbiddenContent(uri)) {
                EventLog.writeEvent(1397638484, "179910660", -1, uri.toString());
                Log.e(TAG, "Content from forbidden URI is not allowed.");
                return SEND_FILE_INFO_ERROR;
            }
            contentType = contentResolver.getType(uri);
            try {
                strArr = new String[]{"_display_name", "_size"};
                fileName2 = null;
                length = 0;
                fileName = "content";
                i = -1;
                str = TAG;
            } catch (SQLiteException e) {
                fileName2 = null;
                length = 0;
                fileName = "content";
                i = -1;
                str = TAG;
            } catch (SecurityException e2) {
                str = TAG;
            }
            try {
                metadataCursor = contentResolver.query(uri, strArr, null, null, null);
            } catch (SQLiteException e3) {
                metadataCursor = null;
            } catch (SecurityException e4) {
                Log.e(str, "generateFileInfo: Permission error, could not access URI: " + uri);
                return SEND_FILE_INFO_ERROR;
            }
            Cursor metadataCursor2 = metadataCursor;
            if (metadataCursor2 != null) {
                try {
                    if (metadataCursor2.moveToFirst()) {
                        int indexName = metadataCursor2.getColumnIndex("_display_name");
                        int indexSize = metadataCursor2.getColumnIndex("_size");
                        if (indexName != i) {
                            fileName2 = metadataCursor2.getString(indexName);
                        }
                        if (indexSize != i) {
                            length = metadataCursor2.getLong(indexSize);
                        }
                    }
                    metadataCursor2.close();
                } catch (Throwable th) {
                    metadataCursor2.close();
                    throw th;
                }
            }
            if (fileName2 == null) {
                fileName2 = uri.getLastPathSegment();
            }
        }
        FileInputStream is2 = null;
        if (scheme.equals(fileName)) {
            try {
                AssetFileDescriptor fd = contentResolver.openAssetFileDescriptor(uri, "r");
                long statLength = fd.getLength();
                if (length != statLength && statLength > 0) {
                    Log.e(str, "Content provider length is wrong (" + Long.toString(length) + "), using stat length (" + Long.toString(statLength) + ")");
                    length = statLength;
                }
                try {
                    is2 = fd.createInputStream();
                    if (length == 0) {
                        long length3 = getStreamSize(is2);
                        try {
                            Log.w(str, "File length not provided. Length from stream = " + length3);
                            fd = contentResolver.openAssetFileDescriptor(uri, "r");
                            is2 = fd.createInputStream();
                            length = length3;
                        } catch (FileNotFoundException e5) {
                            length = length3;
                        } catch (IOException e6) {
                            e = e6;
                            length = length3;
                            try {
                                fd.close();
                            } catch (IOException e7) {
                            }
                        } catch (SecurityException e8) {
                            return SEND_FILE_INFO_ERROR;
                        }
                    }
                } catch (IOException e9) {
                    e = e9;
                }
            } catch (FileNotFoundException e10) {
            } catch (SecurityException e11) {
            }
        }
        if (is2 != null) {
            is = is2;
            length2 = length;
        } else {
            try {
                FileInputStream is3 = (FileInputStream) contentResolver.openInputStream(uri);
                if (length == 0) {
                    length = getStreamSize(is3);
                    is3 = (FileInputStream) contentResolver.openInputStream(uri);
                }
                is = is3;
                length2 = length;
            } catch (FileNotFoundException e12) {
                return SEND_FILE_INFO_ERROR;
            } catch (IOException e13) {
                return SEND_FILE_INFO_ERROR;
            }
        }
        if (length2 == 0) {
            Log.e(str, "Could not determine size of file");
            return SEND_FILE_INFO_ERROR;
        }
        if (length2 > 4294967295L) {
            Log.e(str, "File of size: " + length2 + " bytes can't be transferred");
            throw new IllegalArgumentException(context.getString(R.string.bluetooth_opp_file_limit_exceeded));
        }
        return new BluetoothOppSendFileInfo(fileName2, contentType, length2, is, 0);
    }

    private static long getStreamSize(FileInputStream is) throws IOException {
        long length = 0;
        byte[] unused = new byte[4096];
        int bytesRead = is.read(unused, 0, 4096);
        while (bytesRead != -1) {
            length += (long) bytesRead;
            bytesRead = is.read(unused, 0, 4096);
        }
        return length;
    }
}
