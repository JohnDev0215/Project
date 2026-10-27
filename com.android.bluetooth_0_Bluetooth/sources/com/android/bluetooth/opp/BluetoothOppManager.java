package com.android.bluetooth.opp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Process;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.android.bluetooth.R;
import com.android.vcard.VCardConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothOppManager {
    private static final int ALLOWED_INSERT_SHARE_THREAD_NUMBER = 3;
    private static final String ARRAYLIST_ITEM_SEPERATOR = ";";
    private static final String FILE_URI = "FILE_URI";
    private static final String FILE_URIS = "FILE_URIS";
    private static final Object INSTANCE_LOCK = new Object();
    private static final String MIME_TYPE = "MIMETYPE";
    private static final String MIME_TYPE_MULTIPLE = "MIMETYPE_MULTIPLE";
    private static final String MULTIPLE_FLAG = "MULTIPLE_FLAG";
    private static final String OPP_PREFERENCE_FILE = "OPPMGR";
    private static final String SENDING_FLAG = "SENDINGFLAG";
    private static final String TAG = "BluetoothOppManager";
    private static final boolean V = false;
    private static final int WHITELIST_DURATION_MS = 15000;
    private static BluetoothOppManager sInstance;
    private BluetoothAdapter mAdapter;
    private Context mContext;
    private int mFileNumInBatch;
    private boolean mInitialized;
    private boolean mIsHandoverInitiated;
    private String mMimeTypeOfSendingFile;
    private String mMimeTypeOfSendingFiles;
    public boolean mMultipleFlag;
    public boolean mSendingFlag;
    private String mUriOfSendingFile;
    private ArrayList<Uri> mUrisOfSendingFiles;
    private int mInsertShareThreadNum = 0;
    private List<Pair<String, Long>> mWhitelist = new ArrayList();

    static /* synthetic */ int access$008(BluetoothOppManager x0) {
        int i = x0.mInsertShareThreadNum;
        x0.mInsertShareThreadNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$010(BluetoothOppManager x0) {
        int i = x0.mInsertShareThreadNum;
        x0.mInsertShareThreadNum = i - 1;
        return i;
    }

    public static BluetoothOppManager getInstance(Context context) {
        BluetoothOppManager bluetoothOppManager;
        synchronized (INSTANCE_LOCK) {
            if (sInstance == null) {
                sInstance = new BluetoothOppManager();
            }
            sInstance.init(context);
            bluetoothOppManager = sInstance;
        }
        return bluetoothOppManager;
    }

    private boolean init(Context context) {
        if (this.mInitialized) {
            return true;
        }
        this.mInitialized = true;
        this.mContext = context;
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        restoreApplicationData();
        return true;
    }

    private void cleanupWhitelist() {
        long curTime = SystemClock.elapsedRealtime();
        Iterator<Pair<String, Long>> iter = this.mWhitelist.iterator();
        while (iter.hasNext()) {
            Pair<String, Long> entry = iter.next();
            if (curTime - ((Long) entry.second).longValue() > 15000) {
                iter.remove();
            }
        }
    }

    public synchronized void addToWhitelist(String address) {
        if (address == null) {
            return;
        }
        Iterator<Pair<String, Long>> iter = this.mWhitelist.iterator();
        while (iter.hasNext()) {
            Pair<String, Long> entry = iter.next();
            if (((String) entry.first).equals(address)) {
                iter.remove();
            }
        }
        this.mWhitelist.add(new Pair<>(address, Long.valueOf(SystemClock.elapsedRealtime())));
    }

    public synchronized boolean isWhitelisted(String address) {
        cleanupWhitelist();
        for (Pair<String, Long> entry : this.mWhitelist) {
            if (((String) entry.first).equals(address)) {
                return true;
            }
        }
        return false;
    }

    private void restoreApplicationData() {
        SharedPreferences settings = this.mContext.getSharedPreferences(OPP_PREFERENCE_FILE, 0);
        this.mSendingFlag = settings.getBoolean(SENDING_FLAG, false);
        this.mMimeTypeOfSendingFile = settings.getString(MIME_TYPE, null);
        this.mUriOfSendingFile = settings.getString(FILE_URI, null);
        this.mMimeTypeOfSendingFiles = settings.getString(MIME_TYPE_MULTIPLE, null);
        this.mMultipleFlag = settings.getBoolean(MULTIPLE_FLAG, false);
        String strUris = settings.getString(FILE_URIS, null);
        this.mUrisOfSendingFiles = new ArrayList<>();
        if (strUris != null) {
            String[] splitUri = strUris.split(ARRAYLIST_ITEM_SEPERATOR);
            for (String str : splitUri) {
                this.mUrisOfSendingFiles.add(Uri.parse(str));
            }
        }
        this.mContext.getSharedPreferences(OPP_PREFERENCE_FILE, 0).edit().clear().apply();
    }

    private void storeApplicationData() {
        SharedPreferences.Editor editor = this.mContext.getSharedPreferences(OPP_PREFERENCE_FILE, 0).edit();
        editor.putBoolean(SENDING_FLAG, this.mSendingFlag);
        editor.putBoolean(MULTIPLE_FLAG, this.mMultipleFlag);
        if (this.mMultipleFlag) {
            editor.putString(MIME_TYPE_MULTIPLE, this.mMimeTypeOfSendingFiles);
            StringBuilder sb = new StringBuilder();
            int count = this.mUrisOfSendingFiles.size();
            for (int i = 0; i < count; i++) {
                Uri uriContent = this.mUrisOfSendingFiles.get(i);
                sb.append(uriContent);
                sb.append(ARRAYLIST_ITEM_SEPERATOR);
            }
            String strUris = sb.toString();
            editor.putString(FILE_URIS, strUris);
            editor.remove(MIME_TYPE);
            editor.remove(FILE_URI);
        } else {
            editor.putString(MIME_TYPE, this.mMimeTypeOfSendingFile);
            editor.putString(FILE_URI, this.mUriOfSendingFile);
            editor.remove(MIME_TYPE_MULTIPLE);
            editor.remove(FILE_URIS);
        }
        editor.apply();
    }

    public void saveSendingFileInfo(String mimeType, String uriString, boolean isHandover, boolean fromExternal) throws IllegalArgumentException {
        synchronized (this) {
            this.mMultipleFlag = false;
            this.mMimeTypeOfSendingFile = mimeType;
            this.mIsHandoverInitiated = isHandover;
            Uri uri = Uri.parse(uriString);
            BluetoothOppSendFileInfo sendFileInfo = BluetoothOppSendFileInfo.generateFileInfo(this.mContext, uri, mimeType, fromExternal);
            Uri uri2 = BluetoothOppUtility.generateUri(uri, sendFileInfo);
            BluetoothOppUtility.putSendFileInfo(uri2, sendFileInfo);
            this.mUriOfSendingFile = uri2.toString();
            storeApplicationData();
        }
    }

    public void saveSendingFileInfo(String mimeType, ArrayList<Uri> uris, boolean isHandover, boolean fromExternal) throws IllegalArgumentException {
        synchronized (this) {
            this.mMultipleFlag = true;
            this.mMimeTypeOfSendingFiles = mimeType;
            this.mUrisOfSendingFiles = new ArrayList<>();
            this.mIsHandoverInitiated = isHandover;
            for (Uri uri : uris) {
                BluetoothOppSendFileInfo sendFileInfo = BluetoothOppSendFileInfo.generateFileInfo(this.mContext, uri, mimeType, fromExternal);
                Uri uri2 = BluetoothOppUtility.generateUri(uri, sendFileInfo);
                this.mUrisOfSendingFiles.add(uri2);
                BluetoothOppUtility.putSendFileInfo(uri2, sendFileInfo);
            }
            storeApplicationData();
        }
    }

    public boolean isEnabled() {
        BluetoothAdapter bluetoothAdapter = this.mAdapter;
        if (bluetoothAdapter != null) {
            return bluetoothAdapter.isEnabled();
        }
        return false;
    }

    public void enableBluetooth() {
        BluetoothAdapter bluetoothAdapter = this.mAdapter;
        if (bluetoothAdapter != null) {
            bluetoothAdapter.enable();
        }
    }

    public void disableBluetooth() {
        BluetoothAdapter bluetoothAdapter = this.mAdapter;
        if (bluetoothAdapter != null) {
            bluetoothAdapter.disable();
        }
    }

    public String getDeviceName(BluetoothDevice device) {
        String deviceName = null;
        if (device != null && (deviceName = device.getAlias()) == null) {
            deviceName = BluetoothOppPreference.getInstance(this.mContext).getName(device);
        }
        if (deviceName == null) {
            String deviceName2 = this.mContext.getString(R.string.unknown_device);
            return deviceName2;
        }
        return deviceName;
    }

    public int getBatchSize() {
        int i;
        synchronized (this) {
            i = this.mFileNumInBatch;
        }
        return i;
    }

    public void startTransfer(BluetoothDevice device) {
        synchronized (this) {
            if (this.mInsertShareThreadNum > 3) {
                Log.e(TAG, "Too many shares user triggered concurrently!");
                Intent in = new Intent(this.mContext, (Class<?>) BluetoothOppBtErrorActivity.class);
                in.setFlags(VCardConfig.FLAG_REFRAIN_QP_TO_NAME_PROPERTIES);
                in.putExtra("title", this.mContext.getString(R.string.enabling_progress_title));
                in.putExtra("content", this.mContext.getString(R.string.ErrorTooManyRequests));
                this.mContext.startActivity(in);
                return;
            }
            InsertShareInfoThread insertThread = new InsertShareInfoThread(device, this.mMultipleFlag, this.mMimeTypeOfSendingFile, this.mUriOfSendingFile, this.mMimeTypeOfSendingFiles, this.mUrisOfSendingFiles, this.mIsHandoverInitiated);
            if (this.mMultipleFlag) {
                this.mFileNumInBatch = this.mUrisOfSendingFiles.size();
            }
            insertThread.start();
        }
    }

    private class InsertShareInfoThread extends Thread {
        private final boolean mIsHandoverInitiated;
        private final boolean mIsMultiple;
        private final BluetoothDevice mRemoteDevice;
        private final String mTypeOfMultipleFiles;
        private final String mTypeOfSingleFile;
        private final String mUri;
        private final ArrayList<Uri> mUris;

        InsertShareInfoThread(BluetoothDevice device, boolean multiple, String typeOfSingleFile, String uri, String typeOfMultipleFiles, ArrayList<Uri> uris, boolean handoverInitiated) {
            super("Insert ShareInfo Thread");
            this.mRemoteDevice = device;
            this.mIsMultiple = multiple;
            this.mTypeOfSingleFile = typeOfSingleFile;
            this.mUri = uri;
            this.mTypeOfMultipleFiles = typeOfMultipleFiles;
            this.mUris = uris;
            this.mIsHandoverInitiated = handoverInitiated;
            synchronized (BluetoothOppManager.this) {
                BluetoothOppManager.access$008(BluetoothOppManager.this);
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Process.setThreadPriority(10);
            if (this.mRemoteDevice == null) {
                Log.e(BluetoothOppManager.TAG, "Target bt device is null!");
                return;
            }
            if (this.mIsMultiple) {
                insertMultipleShare();
            } else {
                insertSingleShare();
            }
            synchronized (BluetoothOppManager.this) {
                BluetoothOppManager.access$010(BluetoothOppManager.this);
            }
        }

        private void insertMultipleShare() {
            int count = this.mUris.size();
            Long ts = Long.valueOf(System.currentTimeMillis());
            for (int i = 0; i < count; i++) {
                Uri fileUri = this.mUris.get(i);
                ContentValues values = new ContentValues();
                values.put("uri", fileUri.toString());
                ContentResolver contentResolver = BluetoothOppManager.this.mContext.getContentResolver();
                String contentType = contentResolver.getType(BluetoothOppUtility.originalUri(fileUri));
                if (TextUtils.isEmpty(contentType)) {
                    contentType = this.mTypeOfMultipleFiles;
                }
                values.put(BluetoothShare.MIMETYPE, contentType);
                values.put(BluetoothShare.DESTINATION, this.mRemoteDevice.getAddress());
                values.put("timestamp", ts);
                if (this.mIsHandoverInitiated) {
                    values.put(BluetoothShare.USER_CONFIRMATION, (Integer) 5);
                }
                BluetoothOppManager.this.mContext.getContentResolver().insert(BluetoothShare.CONTENT_URI, values);
            }
        }

        private void insertSingleShare() {
            ContentValues values = new ContentValues();
            values.put("uri", this.mUri);
            values.put(BluetoothShare.MIMETYPE, this.mTypeOfSingleFile);
            values.put(BluetoothShare.DESTINATION, this.mRemoteDevice.getAddress());
            if (this.mIsHandoverInitiated) {
                values.put(BluetoothShare.USER_CONFIRMATION, (Integer) 5);
            }
            BluetoothOppManager.this.mContext.getContentResolver().insert(BluetoothShare.CONTENT_URI, values);
        }
    }

    void cleanUpSendingFileInfo() {
        synchronized (this) {
            if (!this.mMultipleFlag && this.mUriOfSendingFile != null) {
                Uri uri = Uri.parse(this.mUriOfSendingFile);
                BluetoothOppUtility.closeSendFileInfo(uri);
            } else if (this.mUrisOfSendingFiles != null) {
                for (Uri uri2 : this.mUrisOfSendingFiles) {
                    BluetoothOppUtility.closeSendFileInfo(uri2);
                }
            }
        }
    }
}
