package com.android.bluetooth.hfp;

import android.bluetooth.BluetoothDevice;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.INetd;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import com.android.bluetooth.R;
import com.android.bluetooth.Utils;
import com.android.bluetooth.map.BluetoothMapContentObserver;
import com.android.bluetooth.util.DevicePolicyUtils;
import com.android.bluetooth.util.GsmAlphabet;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class AtPhonebook {
    private static final String BLUETOOTH_ADMIN_PERM = "android.permission.BLUETOOTH_ADMIN";
    private static final boolean DBG = false;
    private static final String INCOMING_CALL_WHERE = "type=1";
    private static final int MAX_PHONEBOOK_SIZE = 16384;
    private static final String MISSED_CALL_WHERE = "type=3";
    private static final String OUTGOING_CALL_WHERE = "type=2";
    private static final String TAG = "BluetoothAtPhonebook";
    static final int TYPE_READ = 0;
    static final int TYPE_SET = 1;
    static final int TYPE_TEST = 2;
    static final int TYPE_UNKNOWN = -1;
    private boolean mCheckingAccessPermission;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int mCpbrIndex1;
    private int mCpbrIndex2;
    private String mCurrentPhonebook;
    private HeadsetNativeInterface mNativeInterface;
    private final String mPairingPackage;
    private static final String[] CALLS_PROJECTION = {"_id", "number", "presentation"};
    private static final String[] PHONES_PROJECTION = {"_id", "display_name", "data1", "data2"};
    private String mCharacterSet = "UTF-8";
    private final HashMap<String, PhonebookResult> mPhonebooks = new HashMap<>(4);

    private class PhonebookResult {
        public Cursor cursor;
        public int nameColumn;
        public int numberColumn;
        public int numberPresentationColumn;
        public int typeColumn;

        private PhonebookResult() {
        }
    }

    public AtPhonebook(Context context, HeadsetNativeInterface nativeInterface) {
        this.mContext = context;
        this.mPairingPackage = context.getString(R.string.pairing_ui_package);
        this.mContentResolver = context.getContentResolver();
        this.mNativeInterface = nativeInterface;
        this.mPhonebooks.put("DC", new PhonebookResult());
        this.mPhonebooks.put("RC", new PhonebookResult());
        this.mPhonebooks.put("MC", new PhonebookResult());
        this.mPhonebooks.put("ME", new PhonebookResult());
        this.mCurrentPhonebook = "ME";
        this.mCpbrIndex2 = -1;
        this.mCpbrIndex1 = -1;
    }

    public void cleanup() {
        this.mPhonebooks.clear();
    }

    public String getLastDialledNumber() {
        String[] projection = {"number"};
        Bundle queryArgs = new Bundle();
        queryArgs.putString("android:query-arg-sql-selection", OUTGOING_CALL_WHERE);
        queryArgs.putString("android:query-arg-sql-sort-order", "date DESC");
        queryArgs.putInt("android:query-arg-limit", 1);
        Cursor cursor = this.mContentResolver.query(CallLog.Calls.CONTENT_URI, projection, queryArgs, null);
        if (cursor == null) {
            Log.w(TAG, "getLastDialledNumber, cursor is null");
            return null;
        }
        if (cursor.getCount() < 1) {
            cursor.close();
            Log.w(TAG, "getLastDialledNumber, cursor.getCount is 0");
            return null;
        }
        cursor.moveToNext();
        int column = cursor.getColumnIndexOrThrow("number");
        String number = cursor.getString(column);
        cursor.close();
        return number;
    }

    public boolean getCheckingAccessPermission() {
        return this.mCheckingAccessPermission;
    }

    public void setCheckingAccessPermission(boolean checkingAccessPermission) {
        this.mCheckingAccessPermission = checkingAccessPermission;
    }

    public void setCpbrIndex(int cpbrIndex) {
        this.mCpbrIndex2 = cpbrIndex;
        this.mCpbrIndex1 = cpbrIndex;
    }

    private byte[] getByteAddress(BluetoothDevice device) {
        return Utils.getBytesFromAddress(device.getAddress());
    }

    public void handleCscsCommand(String atString, int type, BluetoothDevice device) {
        log("handleCscsCommand - atString = " + atString);
        int atCommandResult = 0;
        int atCommandErrorCode = -1;
        String atCommandResponse = null;
        if (type == 0) {
            log("handleCscsCommand - Read Command");
            atCommandResponse = "+CSCS: \"" + this.mCharacterSet + "\"";
            atCommandResult = 1;
        } else if (type == 1) {
            log("handleCscsCommand - Set Command");
            String[] args = atString.split("=");
            if (args.length < 2 || args[1] == null) {
                this.mNativeInterface.atResponseCode(device, 0, -1);
            } else {
                String characterSet = atString.split("=")[1].replace("\"", INetd.NEXTHOP_NONE);
                if (characterSet.equals("GSM") || characterSet.equals("IRA") || characterSet.equals("UTF-8") || characterSet.equals("UTF8")) {
                    this.mCharacterSet = characterSet;
                    atCommandResult = 1;
                } else {
                    atCommandErrorCode = 4;
                }
            }
        } else if (type == 2) {
            log("handleCscsCommand - Test Command");
            atCommandResponse = "+CSCS: (\"UTF-8\",\"IRA\",\"GSM\")";
            atCommandResult = 1;
        } else {
            log("handleCscsCommand - Invalid chars");
            atCommandErrorCode = 25;
        }
        if (atCommandResponse != null) {
            this.mNativeInterface.atResponseString(device, atCommandResponse);
        }
        this.mNativeInterface.atResponseCode(device, atCommandResult, atCommandErrorCode);
    }

    public void handleCpbsCommand(String atString, int type, BluetoothDevice device) {
        log("handleCpbsCommand - atString = " + atString);
        int atCommandResult = 0;
        int atCommandErrorCode = -1;
        String atCommandResponse = null;
        if (type == 0) {
            log("handleCpbsCommand - read command");
            if ("SM".equals(this.mCurrentPhonebook)) {
                atCommandResponse = "+CPBS: \"SM\",0," + getMaxPhoneBookSize(0);
                atCommandResult = 1;
            } else {
                PhonebookResult pbr = getPhonebookResult(this.mCurrentPhonebook, true);
                if (pbr == null) {
                    atCommandErrorCode = 4;
                } else {
                    int size = pbr.cursor.getCount();
                    atCommandResponse = "+CPBS: \"" + this.mCurrentPhonebook + "\"," + size + "," + getMaxPhoneBookSize(size);
                    pbr.cursor.close();
                    pbr.cursor = null;
                    atCommandResult = 1;
                }
            }
        } else if (type == 1) {
            log("handleCpbsCommand - set command");
            String[] args = atString.split("=");
            if (args.length < 2 || args[1] == null) {
                atCommandErrorCode = 4;
            } else {
                String pb = args[1].trim();
                while (pb.endsWith("\"")) {
                    pb = pb.substring(0, pb.length() - 1);
                }
                while (pb.startsWith("\"")) {
                    pb = pb.substring(1, pb.length());
                }
                if (getPhonebookResult(pb, false) == null && !"SM".equals(pb)) {
                    atCommandErrorCode = 3;
                } else {
                    this.mCurrentPhonebook = pb;
                    atCommandResult = 1;
                }
            }
        } else if (type == 2) {
            log("handleCpbsCommand - test command");
            atCommandResponse = "+CPBS: (\"ME\",\"SM\",\"DC\",\"RC\",\"MC\")";
            atCommandResult = 1;
        } else {
            log("handleCpbsCommand - invalid chars");
            atCommandErrorCode = 25;
        }
        if (atCommandResponse != null) {
            this.mNativeInterface.atResponseString(device, atCommandResponse);
        }
        this.mNativeInterface.atResponseCode(device, atCommandResult, atCommandErrorCode);
    }

    void handleCpbrCommand(String atString, int type, BluetoothDevice remoteDevice) {
        int index2;
        int size;
        log("handleCpbrCommand - atString = " + atString);
        if (type != 0 && type != 1) {
            if (type == 2) {
                log("handleCpbrCommand - test command");
                if ("SM".equals(this.mCurrentPhonebook)) {
                    size = 0;
                } else {
                    PhonebookResult pbr = getPhonebookResult(this.mCurrentPhonebook, true);
                    if (pbr == null) {
                        this.mNativeInterface.atResponseCode(remoteDevice, 0, 3);
                        return;
                    }
                    int size2 = pbr.cursor.getCount();
                    log("handleCpbrCommand - size = " + size2);
                    pbr.cursor.close();
                    pbr.cursor = null;
                    size = size2;
                }
                if (size == 0) {
                    size = 1;
                }
                String atCommandResponse = "+CPBR: (1-" + size + "),30,30";
                this.mNativeInterface.atResponseString(remoteDevice, atCommandResponse);
                this.mNativeInterface.atResponseCode(remoteDevice, 1, -1);
                return;
            }
            log("handleCpbrCommand - invalid chars");
            this.mNativeInterface.atResponseCode(remoteDevice, 0, 25);
            return;
        }
        log("handleCpbrCommand - set/read command");
        if (this.mCpbrIndex1 != -1) {
            this.mNativeInterface.atResponseCode(remoteDevice, 0, 3);
            return;
        }
        if (atString.split("=").length < 2) {
            this.mNativeInterface.atResponseCode(remoteDevice, 0, -1);
            return;
        }
        String atCommand = atString.split("=")[1];
        String[] indices = atCommand.split(",");
        for (int i = 0; i < indices.length; i++) {
            indices[i] = indices[i].replace(Utils.WAIT, ' ').trim();
        }
        try {
            int index1 = Integer.parseInt(indices[0]);
            if (indices.length == 1) {
                index2 = index1;
            } else {
                index2 = Integer.parseInt(indices[1]);
            }
            this.mCpbrIndex1 = index1;
            this.mCpbrIndex2 = index2;
            this.mCheckingAccessPermission = true;
            int permission = checkAccessPermission(remoteDevice);
            if (permission == 1) {
                this.mCheckingAccessPermission = false;
                int atCommandResult = processCpbrCommand(remoteDevice);
                this.mCpbrIndex2 = -1;
                this.mCpbrIndex1 = -1;
                this.mNativeInterface.atResponseCode(remoteDevice, atCommandResult, -1);
                return;
            }
            if (permission == 2) {
                this.mCheckingAccessPermission = false;
                this.mCpbrIndex2 = -1;
                this.mCpbrIndex1 = -1;
                this.mNativeInterface.atResponseCode(remoteDevice, 0, 0);
            }
        } catch (Exception e) {
            log("handleCpbrCommand - exception - invalid chars: " + e.toString());
            this.mNativeInterface.atResponseCode(remoteDevice, 0, 25);
        }
    }

    private synchronized PhonebookResult getPhonebookResult(String pb, boolean force) {
        if (pb == null) {
            return null;
        }
        PhonebookResult pbr = this.mPhonebooks.get(pb);
        if (pbr == null) {
            pbr = new PhonebookResult();
        }
        if (force || pbr.cursor == null) {
            if (!queryPhonebook(pb, pbr)) {
                return null;
            }
        }
        return pbr;
    }

    private synchronized boolean queryPhonebook(String pb, PhonebookResult pbr) {
        String where;
        boolean ancillaryPhonebook = true;
        if (pb.equals("ME")) {
            ancillaryPhonebook = false;
            where = null;
        } else if (pb.equals("DC")) {
            where = OUTGOING_CALL_WHERE;
        } else if (pb.equals("RC")) {
            where = INCOMING_CALL_WHERE;
        } else {
            if (!pb.equals("MC")) {
                return false;
            }
            where = MISSED_CALL_WHERE;
        }
        if (pbr.cursor != null) {
            pbr.cursor.close();
            pbr.cursor = null;
        }
        if (ancillaryPhonebook) {
            Bundle queryArgs = new Bundle();
            queryArgs.putString("android:query-arg-sql-selection", where);
            queryArgs.putString("android:query-arg-sql-sort-order", "date DESC");
            queryArgs.putInt("android:query-arg-limit", 16384);
            pbr.cursor = this.mContentResolver.query(CallLog.Calls.CONTENT_URI, CALLS_PROJECTION, queryArgs, null);
            if (pbr.cursor == null) {
                return false;
            }
            pbr.numberColumn = pbr.cursor.getColumnIndexOrThrow("number");
            pbr.numberPresentationColumn = pbr.cursor.getColumnIndexOrThrow("presentation");
            pbr.typeColumn = -1;
            pbr.nameColumn = -1;
        } else {
            Bundle queryArgs2 = new Bundle();
            queryArgs2.putString("android:query-arg-sql-selection", where);
            queryArgs2.putInt("android:query-arg-limit", 16384);
            Uri phoneContentUri = DevicePolicyUtils.getEnterprisePhoneUri(this.mContext);
            pbr.cursor = this.mContentResolver.query(phoneContentUri, PHONES_PROJECTION, queryArgs2, null);
            if (pbr.cursor == null) {
                return false;
            }
            pbr.numberColumn = pbr.cursor.getColumnIndex("data1");
            pbr.numberPresentationColumn = -1;
            pbr.typeColumn = pbr.cursor.getColumnIndex("data2");
            pbr.nameColumn = pbr.cursor.getColumnIndex("display_name");
        }
        Log.i(TAG, "Refreshed phonebook " + pb + " with " + pbr.cursor.getCount() + " results");
        return true;
    }

    synchronized void resetAtState() {
        this.mCharacterSet = "UTF-8";
        this.mCpbrIndex2 = -1;
        this.mCpbrIndex1 = -1;
        this.mCheckingAccessPermission = false;
    }

    private synchronized int getMaxPhoneBookSize(int currSize) {
        int maxSize;
        maxSize = currSize >= 100 ? currSize : 100;
        return roundUpToPowerOfTwo(maxSize + (maxSize / 2));
    }

    private int roundUpToPowerOfTwo(int x) {
        int x2 = x | (x >> 1);
        int x3 = x2 | (x2 >> 2);
        int x4 = x3 | (x3 >> 4);
        int x5 = x4 | (x4 >> 8);
        return (x5 | (x5 >> 16)) + 1;
    }

    int processCpbrCommand(BluetoothDevice device) {
        int i;
        log("processCpbrCommand");
        new StringBuilder();
        if (!"SM".equals(this.mCurrentPhonebook)) {
            int i2 = 1;
            PhonebookResult pbr = getPhonebookResult(this.mCurrentPhonebook, true);
            if (pbr == null) {
                Log.e(TAG, "pbr is null");
                return 0;
            }
            if (pbr.cursor.getCount() == 0 || (i = this.mCpbrIndex1) <= 0 || this.mCpbrIndex2 < i || i > pbr.cursor.getCount()) {
                Log.e(TAG, "Invalid request or no results, returning");
                return 1;
            }
            if (this.mCpbrIndex2 > pbr.cursor.getCount()) {
                Log.w(TAG, "max index requested is greater than number of records available, resetting it");
                this.mCpbrIndex2 = pbr.cursor.getCount();
            }
            pbr.cursor.moveToPosition(this.mCpbrIndex1 - 1);
            log("mCpbrIndex1 = " + this.mCpbrIndex1 + " and mCpbrIndex2 = " + this.mCpbrIndex2);
            int index = this.mCpbrIndex1;
            while (index <= this.mCpbrIndex2) {
                String number = pbr.cursor.getString(pbr.numberColumn);
                String name = null;
                if (pbr.nameColumn == -1 && number != null && number.length() > 0) {
                    Cursor c = this.mContentResolver.query(Uri.withAppendedPath(ContactsContract.PhoneLookup.ENTERPRISE_CONTENT_FILTER_URI, number), new String[]{"display_name", BluetoothMapContentObserver.EXTRA_MESSAGE_SENT_MSG_TYPE}, null, null, null);
                    if (c != null) {
                        if (c.moveToFirst()) {
                            name = c.getString(0);
                            c.getInt(i2);
                        }
                        c.close();
                    }
                } else if (pbr.nameColumn != -1) {
                    name = pbr.cursor.getString(pbr.nameColumn);
                } else {
                    log("processCpbrCommand: empty name and number");
                }
                if (name == null) {
                    name = INetd.NEXTHOP_NONE;
                }
                String name2 = name.trim();
                if (name2.length() > 28) {
                    name2 = name2.substring(0, 28);
                }
                if (pbr.typeColumn != -1) {
                    int type = pbr.cursor.getInt(pbr.typeColumn);
                    name2 = name2 + "/" + getPhoneType(type);
                }
                if (number == null) {
                    number = INetd.NEXTHOP_NONE;
                }
                int regionType = PhoneNumberUtils.toaFromString(number);
                String number2 = PhoneNumberUtils.stripSeparators(number.trim());
                if (number2.length() > 30) {
                    number2 = number2.substring(0, 30);
                }
                int numberPresentation = 1;
                if (pbr.numberPresentationColumn != -1) {
                    numberPresentation = pbr.cursor.getInt(pbr.numberPresentationColumn);
                }
                if (numberPresentation != i2) {
                    number2 = INetd.NEXTHOP_NONE;
                    name2 = this.mContext.getString(R.string.unknownNumber);
                }
                if (!name2.isEmpty() && this.mCharacterSet.equals("GSM")) {
                    byte[] nameByte = GsmAlphabet.stringToGsm8BitPacked(name2);
                    if (nameByte == null) {
                        name2 = this.mContext.getString(R.string.unknownNumber);
                    } else {
                        name2 = new String(nameByte);
                    }
                }
                String record = "+CPBR: " + index + ",\"" + number2 + "\"," + regionType + ",\"" + name2 + "\"";
                this.mNativeInterface.atResponseString(device, record + "\r\n\r\n");
                if (!pbr.cursor.moveToNext()) {
                    break;
                }
                index++;
                i2 = 1;
            }
            if (pbr.cursor != null) {
                pbr.cursor.close();
                pbr.cursor = null;
            }
            return 1;
        }
        return 1;
    }

    private int checkAccessPermission(BluetoothDevice remoteDevice) {
        log("checkAccessPermission");
        int permission = remoteDevice.getPhonebookAccessPermission();
        if (permission == 0) {
            log("checkAccessPermission - ACTION_CONNECTION_ACCESS_REQUEST");
            Intent intent = new Intent("android.bluetooth.device.action.CONNECTION_ACCESS_REQUEST");
            intent.setPackage(this.mPairingPackage);
            intent.putExtra("android.bluetooth.device.extra.ACCESS_REQUEST_TYPE", 2);
            intent.putExtra("android.bluetooth.device.extra.DEVICE", remoteDevice);
            this.mContext.sendOrderedBroadcast(intent, "android.permission.BLUETOOTH_ADMIN");
        }
        return permission;
    }

    private static String getPhoneType(int type) {
        if (type == 1) {
            return "H";
        }
        if (type == 2) {
            return "M";
        }
        if (type == 3) {
            return "W";
        }
        if (type == 4 || type == 5) {
            return "F";
        }
        return "O";
    }

    private static void log(String msg) {
        Log.d(TAG, msg);
    }
}
