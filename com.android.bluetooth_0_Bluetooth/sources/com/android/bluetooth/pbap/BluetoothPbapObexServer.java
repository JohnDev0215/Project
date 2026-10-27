package com.android.bluetooth.pbap;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.INetd;
import android.os.Handler;
import android.os.Message;
import android.os.UserManager;
import android.provider.CallLog;
import android.text.TextUtils;
import android.util.Log;
import com.android.bluetooth.BluetoothStatsLog;
import com.android.bluetooth.opp.BluetoothShare;
import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.obex.ApplicationParameter;
import javax.obex.HeaderSet;
import javax.obex.Operation;
import javax.obex.ServerRequestHandler;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothPbapObexServer extends ServerRequestHandler {
    private static final int CALLLOG_NUM_LIMIT = 50;
    private static final String CCH = "cch";
    private static final boolean D = true;
    private static final String FAV = "fav";
    private static final String ICH = "ich";
    public static final long INVALID_VALUE_PARAMETER = -1;
    private static final String MCH = "mch";
    private static final int NEED_SEND_BODY = -1;
    private static final String OCH = "och";
    public static final int ORDER_BY_ALPHABETICAL = 1;
    public static final int ORDER_BY_INDEXED = 0;
    private static final String PB = "pb";
    private static final String SIM1 = "SIM1";
    private static final String TAG = "BluetoothPbapObexServer";
    private static final String TYPE_LISTING = "x-bt/vcard-listing";
    private static final String TYPE_PB = "x-bt/phonebook";
    private static final String TYPE_VCARD = "x-bt/vcard";
    private static final int UUID_LENGTH = 16;
    private static final boolean V = false;
    private static final int VCARD_NAME_SUFFIX_LENGTH = 5;
    private Handler mCallback;
    private AppParamValue mConnAppParamValue;
    private Context mContext;
    private PbapStateMachine mStateMachine;
    private BluetoothPbapVcardManager mVcardManager;
    private static final byte[] PBAP_TARGET = {121, 97, 53, -16, -16, -59, 17, -40, 9, 102, 8, 0, 32, Ascii.FF, -102, 102};
    private static final String TELECOM_PATH = "/telecom";
    private static final String PB_PATH = "/telecom/pb";
    private static final String FAV_PATH = "/telecom/fav";
    private static final String ICH_PATH = "/telecom/ich";
    private static final String OCH_PATH = "/telecom/och";
    private static final String MCH_PATH = "/telecom/mch";
    private static final String CCH_PATH = "/telecom/cch";
    private static final String[] LEGAL_PATH = {TELECOM_PATH, PB_PATH, FAV_PATH, ICH_PATH, OCH_PATH, MCH_PATH, CCH_PATH};
    private static final String[] LEGAL_PATH_WITH_SIM = {TELECOM_PATH, PB_PATH, FAV_PATH, ICH_PATH, OCH_PATH, MCH_PATH, CCH_PATH, "/SIM1", "/SIM1/telecom", "/SIM1/telecom/ich", "/SIM1/telecom/och", "/SIM1/telecom/mch", "/SIM1/telecom/cch", "/SIM1/telecom/pb"};
    public static boolean sIsAborted = false;
    private boolean mNeedPhonebookSize = false;
    private boolean mNeedNewMissedCallsNum = false;
    private boolean mVcardSelector = false;
    private String mCurrentPath = INetd.NEXTHOP_NONE;
    private int mOrderBy = 0;
    private long mDatabaseIdentifierLow = -1;
    private long mDatabaseIdentifierHigh = -1;
    private long mFolderVersionCounterbitMask = 8;
    private long mDatabaseIdentifierBitMask = 4;

    public static class ContentType {
        public static final int COMBINED_CALL_HISTORY = 5;
        public static final int FAVORITES = 6;
        public static final int INCOMING_CALL_HISTORY = 2;
        public static final int MISSED_CALL_HISTORY = 4;
        public static final int OUTGOING_CALL_HISTORY = 3;
        public static final int PHONEBOOK = 1;
    }

    public BluetoothPbapObexServer(Handler callback, Context context, PbapStateMachine stateMachine) {
        this.mCallback = null;
        this.mCallback = callback;
        this.mContext = context;
        this.mVcardManager = new BluetoothPbapVcardManager(this.mContext);
        this.mStateMachine = stateMachine;
    }

    public int onConnect(HeaderSet request, HeaderSet reply) {
        notifyUpdateWakeLock();
        try {
            byte[] uuid = (byte[]) request.getHeader(70);
            if (uuid == null) {
                return 198;
            }
            Log.d(TAG, "onConnect(): uuid=" + Arrays.toString(uuid));
            if (uuid.length != 16) {
                Log.w(TAG, "Wrong UUID length");
                return 198;
            }
            for (int i = 0; i < 16; i++) {
                if (uuid[i] != PBAP_TARGET[i]) {
                    Log.w(TAG, "Wrong UUID");
                    return 198;
                }
            }
            reply.setHeader(74, uuid);
            try {
                byte[] remote = (byte[]) request.getHeader(74);
                if (remote != null) {
                    Log.d(TAG, "onConnect(): remote=" + Arrays.toString(remote));
                    reply.setHeader(70, remote);
                }
                try {
                    this.mConnAppParamValue = new AppParamValue();
                    byte[] appParam = (byte[]) request.getHeader(76);
                    if (appParam == null) {
                        return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
                    }
                    if (!parseApplicationParameter(appParam, this.mConnAppParamValue)) {
                        return BluetoothShare.STATUS_RUNNING;
                    }
                    return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
                } catch (IOException e) {
                    Log.e(TAG, e.toString());
                    return 208;
                }
            } catch (IOException e2) {
                Log.e(TAG, e2.toString());
                return 208;
            }
        } catch (IOException e3) {
            Log.e(TAG, e3.toString());
            return 208;
        }
    }

    public void onDisconnect(HeaderSet req, HeaderSet resp) {
        Log.d(TAG, "onDisconnect(): enter");
        notifyUpdateWakeLock();
        resp.responseCode = BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
    }

    public int onAbort(HeaderSet request, HeaderSet reply) {
        Log.d(TAG, "onAbort(): enter.");
        notifyUpdateWakeLock();
        sIsAborted = true;
        return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
    }

    public int onPut(Operation op) {
        Log.d(TAG, "onPut(): not support PUT request.");
        notifyUpdateWakeLock();
        return BluetoothShare.STATUS_RUNNING;
    }

    public int onDelete(HeaderSet request, HeaderSet reply) {
        Log.d(TAG, "onDelete(): not support PUT request.");
        notifyUpdateWakeLock();
        return BluetoothShare.STATUS_RUNNING;
    }

    public int onSetPath(HeaderSet request, HeaderSet reply, boolean backup, boolean create) {
        Log.d(TAG, "before setPath, mCurrentPath ==  " + this.mCurrentPath);
        notifyUpdateWakeLock();
        String currentPathTmp = this.mCurrentPath;
        try {
            String tmpPath = (String) request.getHeader(1);
            Log.d(TAG, "backup=" + backup + " create=" + create + " name=" + tmpPath);
            if (backup) {
                if (currentPathTmp.length() != 0) {
                    currentPathTmp = currentPathTmp.substring(0, currentPathTmp.lastIndexOf("/"));
                }
            } else if (tmpPath == null) {
                currentPathTmp = INetd.NEXTHOP_NONE;
            } else {
                currentPathTmp = currentPathTmp + "/" + tmpPath;
            }
            if (currentPathTmp.length() != 0 && !isLegalPath(currentPathTmp)) {
                if (create) {
                    Log.w(TAG, "path create is forbidden!");
                    return 195;
                }
                Log.w(TAG, "path is not legal");
                return 196;
            }
            this.mCurrentPath = currentPathTmp;
            return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
        } catch (IOException e) {
            Log.e(TAG, "Get name header fail");
            return 208;
        }
    }

    public void onClose() {
        this.mStateMachine.sendMessage(3);
    }

    public int onGet(Operation op) {
        String type;
        String name;
        boolean validName;
        notifyUpdateWakeLock();
        sIsAborted = false;
        HeaderSet reply = new HeaderSet();
        AppParamValue appParamValue = new AppParamValue();
        try {
            HeaderSet request = op.getReceivedHeader();
            try {
                type = (String) request.getHeader(66);
                try {
                    name = (String) request.getHeader(1);
                } catch (IOException e) {
                }
            } catch (IOException e2) {
            }
            try {
                byte[] appParam = (byte[]) request.getHeader(76);
                Log.d(TAG, "OnGet type is " + type + "; name is " + name);
                if (type == null) {
                    return 198;
                }
                if (!UserManager.get(this.mContext).isUserUnlocked()) {
                    Log.e(TAG, "Storage locked, " + type + " failed");
                    return 211;
                }
                if (!TextUtils.isEmpty(name)) {
                    validName = true;
                } else {
                    validName = false;
                }
                if (validName && (!validName || !type.equals(TYPE_VCARD))) {
                    if (!name.contains(SIM1.subSequence(0, SIM1.length()))) {
                        if (!isNameMatchTarget(name, PB)) {
                            if (!isNameMatchTarget(name, FAV)) {
                                if (!isNameMatchTarget(name, ICH)) {
                                    if (!isNameMatchTarget(name, OCH)) {
                                        if (!isNameMatchTarget(name, MCH)) {
                                            if (isNameMatchTarget(name, CCH)) {
                                                appParamValue.needTag = 5;
                                                appParamValue.callHistoryVersionCounter = this.mVcardManager.getCallHistoryPrimaryFolderVersion(5);
                                                Log.v(TAG, "download combined calls request");
                                            } else {
                                                Log.w(TAG, "Input name doesn't contain valid info!!!");
                                                return 196;
                                            }
                                        } else {
                                            appParamValue.needTag = 4;
                                            appParamValue.callHistoryVersionCounter = this.mVcardManager.getCallHistoryPrimaryFolderVersion(4);
                                            this.mNeedNewMissedCallsNum = true;
                                            Log.v(TAG, "download missed calls request");
                                        }
                                    } else {
                                        appParamValue.needTag = 3;
                                        appParamValue.callHistoryVersionCounter = this.mVcardManager.getCallHistoryPrimaryFolderVersion(3);
                                        Log.v(TAG, "download outgoing calls request");
                                    }
                                } else {
                                    appParamValue.needTag = 2;
                                    appParamValue.callHistoryVersionCounter = this.mVcardManager.getCallHistoryPrimaryFolderVersion(2);
                                    Log.v(TAG, "download incoming calls request");
                                }
                            } else {
                                appParamValue.needTag = 6;
                                Log.v(TAG, "download favorites request");
                            }
                        } else {
                            appParamValue.needTag = 1;
                            Log.v(TAG, "download phonebook request");
                        }
                    } else {
                        Log.w(TAG, "Not support access SIM card info!");
                        return 198;
                    }
                } else {
                    Log.d(TAG, "Guess what carkit actually want from current path (" + this.mCurrentPath + ")");
                    if (this.mCurrentPath.equals(PB_PATH)) {
                        appParamValue.needTag = 1;
                    } else if (this.mCurrentPath.equals(FAV_PATH)) {
                        appParamValue.needTag = 6;
                    } else if (this.mCurrentPath.equals(ICH_PATH)) {
                        appParamValue.needTag = 2;
                    } else if (this.mCurrentPath.equals(OCH_PATH)) {
                        appParamValue.needTag = 3;
                    } else if (this.mCurrentPath.equals(MCH_PATH)) {
                        appParamValue.needTag = 4;
                        this.mNeedNewMissedCallsNum = true;
                    } else if (this.mCurrentPath.equals(CCH_PATH)) {
                        appParamValue.needTag = 5;
                    } else if (this.mCurrentPath.equals(TELECOM_PATH)) {
                        if (!validName && type.equals(TYPE_LISTING)) {
                            Log.e(TAG, "invalid vcard listing request in default folder");
                            return 196;
                        }
                    } else {
                        Log.w(TAG, "mCurrentpath is not valid path!!!");
                        return 198;
                    }
                    Log.v(TAG, "onGet(): appParamValue.needTag=" + appParamValue.needTag);
                }
                if (appParam != null && !parseApplicationParameter(appParam, appParamValue)) {
                    return BluetoothShare.STATUS_RUNNING;
                }
                if (type.equals(TYPE_LISTING)) {
                    return pullVcardListing(appParam, appParamValue, reply, op, name);
                }
                if (type.equals(TYPE_VCARD)) {
                    return pullVcardEntry(appParam, appParamValue, op, reply, name, this.mCurrentPath);
                }
                if (type.equals(TYPE_PB)) {
                    return pullPhonebook(appParam, appParamValue, reply, op, name);
                }
                Log.w(TAG, "unknown type request!!!");
                return 198;
            } catch (IOException e3) {
                Log.e(TAG, "request headers error");
                return 208;
            }
        } catch (IOException e4) {
        }
    }

    private boolean isNameMatchTarget(String name, String target) {
        if (name == null) {
            return false;
        }
        String contentTypeName = name;
        if (contentTypeName.endsWith(".vcf")) {
            contentTypeName = contentTypeName.substring(0, contentTypeName.length() - ".vcf".length());
        }
        String[] nameList = contentTypeName.split("/");
        for (String subName : nameList) {
            if (subName.equals(target)) {
                return true;
            }
        }
        return false;
    }

    private boolean isLegalPath(String str) {
        if (str.length() == 0) {
            return true;
        }
        int i = 0;
        while (true) {
            String[] strArr = LEGAL_PATH;
            if (i < strArr.length) {
                if (str.equals(strArr[i])) {
                    return true;
                }
                i++;
            } else {
                return false;
            }
        }
    }

    private class AppParamValue {
        public byte[] callHistoryVersionCounter;
        public int maxListCount = 65535;
        public int listStartOffset = 0;
        public String searchValue = INetd.NEXTHOP_NONE;
        public String searchAttr = INetd.NEXTHOP_NONE;
        public String order = INetd.NEXTHOP_NONE;
        public int needTag = 0;
        public boolean vcard21 = true;
        public boolean ignorefilter = true;
        public String vCardSelectorOperator = "0";
        public byte[] propertySelector = {0, 0, 0, 0, 0, 0, 0, 0};
        public byte[] vCardSelector = {0, 0, 0, 0, 0, 0, 0, 0};
        public byte[] supportedFeature = {0, 0, 0, 0};

        AppParamValue() {
        }

        public void dump() {
            Log.i(BluetoothPbapObexServer.TAG, "maxListCount=" + this.maxListCount + " listStartOffset=" + this.listStartOffset + " searchValue=" + this.searchValue + " searchAttr=" + this.searchAttr + " needTag=" + this.needTag + " vcard21=" + this.vcard21 + " order=" + this.order + "vcardselector=" + this.vCardSelector + "vcardselop=" + this.vCardSelectorOperator);
        }
    }

    private boolean parseApplicationParameter(byte[] appParam, AppParamValue appParamValue) {
        int i = 0;
        boolean parseOk = true;
        while (i < appParam.length && parseOk) {
            byte b = appParam[i];
            if (b == 12) {
                int i2 = i + 2;
                for (int index = 0; index < 8; index++) {
                    if (appParam[i2 + index] != 0) {
                        this.mVcardSelector = true;
                        appParamValue.vCardSelector[index] = appParam[i2 + index];
                    }
                }
                i = i2 + 8;
            } else if (b == 14) {
                int i3 = i + 2;
                appParamValue.vCardSelectorOperator = Byte.toString(appParam[i3]);
                i = i3 + 1;
            } else if (b == 16) {
                int i4 = i + 2;
                for (int index2 = 0; index2 < 4; index2++) {
                    if (appParam[i4 + index2] != 0) {
                        appParamValue.supportedFeature[index2] = appParam[i4 + index2];
                    }
                }
                i = i4 + 4;
            } else {
                switch (b) {
                    case 1:
                        int i5 = i + 2;
                        appParamValue.order = Byte.toString(appParam[i5]);
                        i = i5 + 1;
                        break;
                    case 2:
                        i++;
                        int length = appParam[i];
                        if (length == 0) {
                            parseOk = false;
                        } else {
                            if (appParam[i + length] == 0) {
                                appParamValue.searchValue = new String(appParam, i + 1, length - 1);
                            } else {
                                appParamValue.searchValue = new String(appParam, i + 1, length);
                            }
                            i = i + length + 1;
                        }
                        break;
                    case 3:
                        int i6 = i + 2;
                        appParamValue.searchAttr = Byte.toString(appParam[i6]);
                        i = i6 + 1;
                        break;
                    case 4:
                        int i7 = i + 2;
                        if (appParam[i7] == 0 && appParam[i7 + 1] == 0) {
                            this.mNeedPhonebookSize = true;
                        } else {
                            int highValue = appParam[i7] & UnsignedBytes.MAX_VALUE;
                            int lowValue = appParam[i7 + 1] & UnsignedBytes.MAX_VALUE;
                            appParamValue.maxListCount = (highValue * 256) + lowValue;
                        }
                        i = i7 + 2;
                        break;
                    case 5:
                        int i8 = i + 2;
                        int highValue2 = appParam[i8] & UnsignedBytes.MAX_VALUE;
                        int lowValue2 = appParam[i8 + 1] & UnsignedBytes.MAX_VALUE;
                        appParamValue.listStartOffset = (highValue2 * 256) + lowValue2;
                        i = i8 + 2;
                        break;
                    case 6:
                        int i9 = i + 2;
                        for (int index3 = 0; index3 < 8; index3++) {
                            if (appParam[i9 + index3] != 0) {
                                appParamValue.ignorefilter = false;
                                appParamValue.propertySelector[index3] = appParam[i9 + index3];
                            }
                        }
                        i = i9 + 8;
                        break;
                    case 7:
                        int i10 = i + 2;
                        if (appParam[i10] != 0) {
                            appParamValue.vcard21 = false;
                        }
                        i = i10 + 1;
                        break;
                    default:
                        parseOk = false;
                        Log.e(TAG, "Parse Application Parameter error");
                        break;
                }
            }
        }
        appParamValue.dump();
        return parseOk;
    }

    private int sendVcardListingXml(AppParamValue appParamValue, Operation op, int needSendBody, int size) {
        String type;
        StringBuilder result = new StringBuilder();
        int itemsFound = 0;
        result.append("<?xml version=\"1.0\"?>");
        result.append("<!DOCTYPE vcard-listing SYSTEM \"vcard-listing.dtd\">");
        result.append("<vCard-listing version=\"1.0\">");
        if (appParamValue.needTag == 1 || appParamValue.needTag == 6) {
            if (appParamValue.searchAttr.equals("0")) {
                type = "name";
            } else if (!appParamValue.searchAttr.equals("1")) {
                type = INetd.NEXTHOP_NONE;
            } else {
                type = "number";
            }
            if (type.length() > 0) {
                itemsFound = createList(appParamValue, needSendBody, size, result, type);
            } else {
                return 204;
            }
        } else {
            ArrayList<String> nameList = this.mVcardManager.loadCallHistoryList(appParamValue.needTag);
            int requestSize = nameList.size() >= appParamValue.maxListCount ? appParamValue.maxListCount : nameList.size();
            int startPoint = appParamValue.listStartOffset;
            int endPoint = startPoint + requestSize;
            if (endPoint > nameList.size()) {
                endPoint = nameList.size();
            }
            Log.d(TAG, "call log list, size=" + requestSize + " offset=" + appParamValue.listStartOffset);
            for (int j = startPoint; j < endPoint; j++) {
                writeVCardEntry(j + 1, nameList.get(j), result);
            }
        }
        result.append("</vCard-listing>");
        Log.d(TAG, "itemsFound =" + itemsFound);
        return pushBytes(op, result.toString());
    }

    private int createList(AppParamValue appParamValue, int needSendBody, int size, StringBuilder result, String type) {
        int itemsFound;
        String compareValue;
        int itemsFound2 = 0;
        ArrayList<String> nameList = this.mVcardSelector ? this.mVcardManager.getSelectedPhonebookNameList(this.mOrderBy, appParamValue.vcard21, needSendBody, size, appParamValue.vCardSelector, appParamValue.vCardSelectorOperator) : this.mVcardManager.getPhonebookNameList(this.mOrderBy);
        int requestSize = nameList.size() >= appParamValue.maxListCount ? appParamValue.maxListCount : nameList.size();
        int listSize = nameList.size();
        String compareValue2 = INetd.NEXTHOP_NONE;
        Log.d(TAG, "search by " + type + ", requestSize=" + requestSize + " offset=" + appParamValue.listStartOffset + " searchValue=" + appParamValue.searchValue);
        if (type.equals("number")) {
            ArrayList<Integer> savedPosList = new ArrayList<>();
            ArrayList<String> selectedNameList = new ArrayList<>();
            ArrayList<String> names = this.mVcardManager.getContactNamesByNumber(appParamValue.searchValue);
            if (this.mOrderBy == 1) {
                Collections.sort(names);
            }
            for (int i = 0; i < names.size(); i++) {
                String compareValue3 = names.get(i).trim();
                Log.d(TAG, "compareValue=" + compareValue3);
                int pos = 0;
                while (pos < listSize) {
                    String currentValue = nameList.get(pos);
                    if (!currentValue.equals(compareValue3)) {
                        itemsFound = itemsFound2;
                        compareValue = compareValue3;
                    } else {
                        if (!currentValue.contains(",")) {
                            itemsFound = itemsFound2;
                            compareValue = compareValue3;
                        } else {
                            itemsFound = itemsFound2;
                            compareValue = compareValue3;
                            currentValue = currentValue.substring(0, currentValue.lastIndexOf(44));
                        }
                        selectedNameList.add(currentValue);
                        savedPosList.add(Integer.valueOf(pos));
                    }
                    pos++;
                    itemsFound2 = itemsFound;
                    compareValue3 = compareValue;
                }
            }
            for (int j = appParamValue.listStartOffset; j < selectedNameList.size() && itemsFound2 < requestSize; j++) {
                itemsFound2++;
                writeVCardEntry(savedPosList.get(j).intValue(), selectedNameList.get(j), result);
            }
            return itemsFound2;
        }
        ArrayList<Integer> savedPosList2 = new ArrayList<>();
        ArrayList<String> selectedNameList2 = new ArrayList<>();
        if (appParamValue.searchValue != null) {
            compareValue2 = appParamValue.searchValue.trim().toLowerCase();
        }
        for (int pos2 = 0; pos2 < listSize; pos2++) {
            String currentValue2 = nameList.get(pos2);
            if (currentValue2.contains(",")) {
                currentValue2 = currentValue2.substring(0, currentValue2.lastIndexOf(44));
            }
            if (appParamValue.searchValue != null && (appParamValue.searchValue.isEmpty() || currentValue2.toLowerCase().startsWith(compareValue2.toLowerCase()))) {
                selectedNameList2.add(currentValue2);
                savedPosList2.add(Integer.valueOf(pos2));
            }
        }
        int pos3 = appParamValue.listStartOffset;
        int itemsFound3 = 0;
        for (int i2 = pos3; i2 < selectedNameList2.size() && itemsFound3 < requestSize; i2++) {
            itemsFound3++;
            writeVCardEntry(savedPosList2.get(i2).intValue(), selectedNameList2.get(i2), result);
        }
        return itemsFound3;
    }

    private int pushHeader(Operation op, HeaderSet reply) {
        OutputStream outputStream = null;
        Log.d(TAG, "Push Header");
        Log.d(TAG, reply.toString());
        try {
            try {
                op.sendHeaders(reply);
                outputStream = op.openOutputStream();
                outputStream.flush();
            } catch (IOException e) {
                Log.e(TAG, e.toString());
                if (closeStream(outputStream, op)) {
                    return 208;
                }
            }
            if (closeStream(outputStream, op)) {
                return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
            }
            return 208;
        } catch (Throwable th) {
            if (!closeStream(outputStream, op)) {
            }
            throw th;
        }
    }

    private int pushBytes(Operation op, String vcardString) {
        if (vcardString == null) {
            Log.w(TAG, "vcardString is null!");
            return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
        }
        OutputStream outputStream = null;
        int pushResult = BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
        try {
            outputStream = op.openOutputStream();
            outputStream.write(vcardString.getBytes());
        } catch (IOException e) {
            Log.e(TAG, "open/write outputstrem failed" + e.toString());
            pushResult = 208;
        }
        if (!closeStream(outputStream, op)) {
            return 208;
        }
        return pushResult;
    }

    private int handleAppParaForResponse(AppParamValue appParamValue, int size, HeaderSet reply, Operation op, String name) {
        byte[] misnum = new byte[1];
        ApplicationParameter ap = new ApplicationParameter();
        boolean needSendCallHistoryVersionCounters = false;
        if (isNameMatchTarget(name, MCH) || isNameMatchTarget(name, ICH) || isNameMatchTarget(name, OCH) || isNameMatchTarget(name, CCH)) {
            needSendCallHistoryVersionCounters = checkPbapFeatureSupport(this.mFolderVersionCounterbitMask);
        }
        boolean needSendPhonebookVersionCounters = false;
        if (isNameMatchTarget(name, PB) || isNameMatchTarget(name, FAV)) {
            needSendPhonebookVersionCounters = checkPbapFeatureSupport(this.mFolderVersionCounterbitMask);
        }
        if (this.mNeedPhonebookSize) {
            Log.d(TAG, "Need Phonebook size in response header.");
            this.mNeedPhonebookSize = false;
            byte[] pbsize = {(byte) ((size / 256) & 255), (byte) ((size % 256) & 255)};
            ap.addAPPHeader((byte) 8, (byte) 2, pbsize);
            if (this.mNeedNewMissedCallsNum) {
                this.mNeedNewMissedCallsNum = false;
                int nmnum = 0;
                ContentResolver contentResolver = this.mContext.getContentResolver();
                Cursor c = contentResolver.query(CallLog.Calls.CONTENT_URI, null, "type = 3 AND new = 1", null, "date DESC");
                if (c != null) {
                    nmnum = c.getCount();
                    c.close();
                }
                int nmnum2 = nmnum > 0 ? nmnum : 0;
                misnum[0] = (byte) nmnum2;
                ap.addAPPHeader((byte) 9, (byte) 1, misnum);
                Log.d(TAG, "handleAppParaForResponse(): mNeedNewMissedCallsNum=true,  num= " + nmnum2);
            }
            if (checkPbapFeatureSupport(this.mDatabaseIdentifierBitMask)) {
                setDbCounters(ap);
            }
            if (needSendPhonebookVersionCounters) {
                setFolderVersionCounters(ap);
            }
            if (needSendCallHistoryVersionCounters) {
                setCallversionCounters(ap, appParamValue);
            }
            reply.setHeader(76, ap.getAPPparam());
            Log.d(TAG, "Send back Phonebook size only, without body info! Size= " + size);
            return pushHeader(op, reply);
        }
        if (this.mNeedNewMissedCallsNum) {
            Log.d(TAG, "Need new missed call num in response header.");
            this.mNeedNewMissedCallsNum = false;
            int nmnum3 = 0;
            ContentResolver contentResolver2 = this.mContext.getContentResolver();
            Cursor c2 = contentResolver2.query(CallLog.Calls.CONTENT_URI, null, "type = 3 AND new = 1", null, "date DESC");
            if (c2 != null) {
                nmnum3 = c2.getCount();
                c2.close();
            }
            int nmnum4 = nmnum3 > 0 ? nmnum3 : 0;
            misnum[0] = (byte) nmnum4;
            Log.d(TAG, "handleAppParaForResponse(): mNeedNewMissedCallsNum=true,  num= " + nmnum4);
            ap.addAPPHeader((byte) 9, (byte) 1, misnum);
            reply.setHeader(76, ap.getAPPparam());
            Log.d(TAG, "handleAppParaForResponse(): mNeedNewMissedCallsNum=true,  num= " + nmnum4);
            try {
                op.sendHeaders(reply);
            } catch (IOException e) {
                Log.e(TAG, e.toString());
                return 208;
            }
        }
        if (checkPbapFeatureSupport(this.mDatabaseIdentifierBitMask)) {
            setDbCounters(ap);
            reply.setHeader(76, ap.getAPPparam());
            try {
                op.sendHeaders(reply);
            } catch (IOException e2) {
                Log.e(TAG, e2.toString());
                return 208;
            }
        }
        if (needSendPhonebookVersionCounters) {
            setFolderVersionCounters(ap);
            reply.setHeader(76, ap.getAPPparam());
            try {
                op.sendHeaders(reply);
            } catch (IOException e3) {
                Log.e(TAG, e3.toString());
                return 208;
            }
        }
        if (needSendCallHistoryVersionCounters) {
            setCallversionCounters(ap, appParamValue);
            reply.setHeader(76, ap.getAPPparam());
            try {
                op.sendHeaders(reply);
                return -1;
            } catch (IOException e4) {
                Log.e(TAG, e4.toString());
                return 208;
            }
        }
        return -1;
    }

    private int pullVcardListing(byte[] appParam, AppParamValue appParamValue, HeaderSet reply, Operation op, String name) {
        String searchAttr = appParamValue.searchAttr.trim();
        if (searchAttr == null || searchAttr.length() == 0) {
            appParamValue.searchAttr = "0";
            Log.d(TAG, "searchAttr is not set by PCE, assume search by name by default");
        } else {
            if (!searchAttr.equals("0") && !searchAttr.equals("1")) {
                Log.w(TAG, "search attr not supported");
                if (!searchAttr.equals("2")) {
                    return 204;
                }
                Log.w(TAG, "do not support search by sound");
                return 209;
            }
            Log.i(TAG, "searchAttr is valid: " + searchAttr);
        }
        int size = this.mVcardManager.getPhonebookSize(appParamValue.needTag);
        int needSendBody = handleAppParaForResponse(appParamValue, size, reply, op, name);
        if (needSendBody != -1) {
            op.noBodyHeader();
            return needSendBody;
        }
        if (size == 0) {
            Log.d(TAG, "PhonebookSize is 0, return.");
            return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
        }
        String orderPara = appParamValue.order.trim();
        if (TextUtils.isEmpty(orderPara)) {
            orderPara = "0";
            Log.d(TAG, "Order parameter is not set by PCE. Assume order by 'Indexed' by default");
        } else {
            if (!orderPara.equals("0") && !orderPara.equals("1")) {
                Log.d(TAG, "Order parameter is not supported: " + appParamValue.order);
                if (!orderPara.equals("2")) {
                    return 204;
                }
                Log.w(TAG, "Do not support order by sound");
                return 209;
            }
            Log.i(TAG, "Order parameter is valid: " + orderPara);
        }
        if (orderPara.equals("0")) {
            this.mOrderBy = 0;
        } else if (orderPara.equals("1")) {
            this.mOrderBy = 1;
        }
        return sendVcardListingXml(appParamValue, op, needSendBody, size);
    }

    private int pullVcardEntry(byte[] appParam, AppParamValue appParamValue, Operation op, HeaderSet reply, String name, String currentPath) {
        int intIndex;
        if (name == null || name.length() < 5) {
            Log.d(TAG, "Name is Null, or the length of name < 5 !");
            return 198;
        }
        String strIndex = name.substring(0, (name.length() - 5) + 1);
        if (strIndex.trim().length() == 0) {
            intIndex = 0;
        } else {
            try {
                intIndex = Integer.parseInt(strIndex);
            } catch (NumberFormatException e) {
                Log.e(TAG, "catch number format exception " + e.toString());
                return 198;
            }
        }
        int size = this.mVcardManager.getPhonebookSize(appParamValue.needTag);
        int needSendBody = handleAppParaForResponse(appParamValue, size, reply, op, name);
        if (size == 0) {
            Log.d(TAG, "PhonebookSize is 0, return.");
            return 196;
        }
        boolean vcard21 = appParamValue.vcard21;
        if (appParamValue.needTag == 0) {
            Log.w(TAG, "wrong path!");
            return 198;
        }
        if (appParamValue.needTag == 1 || appParamValue.needTag == 6) {
            int size2 = size;
            if (intIndex < 0 || intIndex >= size2) {
                Log.w(TAG, "The requested vcard is not acceptable! name= " + name);
                return 196;
            }
            if (intIndex != 0 || appParamValue.needTag != 1) {
                return this.mVcardManager.composeAndSendPhonebookOneVcard(op, intIndex, vcard21, null, this.mOrderBy, appParamValue.ignorefilter, appParamValue.propertySelector);
            }
            String ownerVcard = this.mVcardManager.getOwnerPhoneNumberVcard(vcard21, appParamValue.ignorefilter ? null : appParamValue.propertySelector);
            return pushBytes(op, ownerVcard);
        }
        if (intIndex <= 0 || intIndex > size) {
            Log.w(TAG, "The requested vcard is not acceptable! name= " + name);
            return 196;
        }
        if (intIndex >= 1) {
            return this.mVcardManager.composeAndSendSelectedCallLogVcards(appParamValue.needTag, op, intIndex, intIndex, vcard21, needSendBody, size, appParamValue.ignorefilter, appParamValue.propertySelector, appParamValue.vCardSelector, appParamValue.vCardSelectorOperator, this.mVcardSelector);
        }
        return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
    }

    private int pullPhonebook(byte[] appParam, AppParamValue appParamValue, HeaderSet reply, Operation op, String name) {
        int endPoint;
        int dotIndex;
        if (name != null && (dotIndex = name.indexOf(".")) >= 0 && dotIndex <= name.length() && !name.regionMatches(dotIndex + 1, "vcf", 0, "vcf".length())) {
            Log.w(TAG, "name is not .vcf");
            return 198;
        }
        int pbSize = this.mVcardManager.getPhonebookSize(appParamValue.needTag);
        int needSendBody = handleAppParaForResponse(appParamValue, pbSize, reply, op, name);
        if (needSendBody != -1) {
            op.noBodyHeader();
            return needSendBody;
        }
        if (pbSize == 0) {
            Log.d(TAG, "PhonebookSize is 0, return.");
            return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
        }
        int requestSize = pbSize >= appParamValue.maxListCount ? appParamValue.maxListCount : pbSize;
        int startIndex = 1;
        int lastIndex = pbSize;
        if (appParamValue.needTag == 1) {
            startIndex = 0;
            lastIndex = pbSize - 1;
        }
        int startPoint = appParamValue.listStartOffset + startIndex;
        int endPoint2 = (startPoint + requestSize) - 1;
        if (appParamValue.listStartOffset < 0 || startPoint > lastIndex) {
            Log.w(TAG, "listStartOffset is not correct! " + startPoint);
            return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
        }
        if (endPoint2 <= lastIndex) {
            endPoint = endPoint2;
        } else {
            endPoint = lastIndex;
        }
        if (appParamValue.needTag != 1 && appParamValue.needTag != 6 && requestSize > 50) {
            requestSize = 50;
        }
        Log.d(TAG, "pullPhonebook(): requestSize=" + requestSize + " startPoint=" + startPoint + " endPoint=" + endPoint);
        boolean vcard21 = appParamValue.vcard21;
        boolean favorites = appParamValue.needTag == 6;
        if (appParamValue.needTag != 1 && !favorites) {
            return this.mVcardManager.composeAndSendSelectedCallLogVcards(appParamValue.needTag, op, startPoint, endPoint, vcard21, needSendBody, pbSize, appParamValue.ignorefilter, appParamValue.propertySelector, appParamValue.vCardSelector, appParamValue.vCardSelectorOperator, this.mVcardSelector);
        }
        int pbSize2 = pbSize;
        if (startPoint != 0) {
            return this.mVcardManager.composeAndSendPhonebookVcards(op, startPoint, endPoint, vcard21, null, needSendBody, pbSize2, appParamValue.ignorefilter, appParamValue.propertySelector, appParamValue.vCardSelector, appParamValue.vCardSelectorOperator, this.mVcardSelector, favorites);
        }
        String ownerVcard = this.mVcardManager.getOwnerPhoneNumberVcard(vcard21, appParamValue.ignorefilter ? null : appParamValue.propertySelector);
        return endPoint == 0 ? pushBytes(op, ownerVcard) : this.mVcardManager.composeAndSendPhonebookVcards(op, 1, endPoint, vcard21, ownerVcard, needSendBody, pbSize2, appParamValue.ignorefilter, appParamValue.propertySelector, appParamValue.vCardSelector, appParamValue.vCardSelectorOperator, this.mVcardSelector, favorites);
    }

    public static boolean closeStream(OutputStream out, Operation op) {
        boolean returnvalue = true;
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                Log.e(TAG, "outputStream close failed" + e.toString());
                returnvalue = false;
            }
        }
        if (op != null) {
            try {
                op.close();
                return returnvalue;
            } catch (IOException e2) {
                Log.e(TAG, "operation close failed" + e2.toString());
                return false;
            }
        }
        return returnvalue;
    }

    public final void onAuthenticationFailure(byte[] userName) {
    }

    public static final String createSelectionPara(int type) {
        if (type == 2) {
            return "(type=1 OR type=5)";
        }
        if (type == 3) {
            return "type=2";
        }
        if (type != 4) {
            return null;
        }
        return "type=3";
    }

    private void xmlEncode(String name, StringBuilder result) {
        if (name == null) {
            return;
        }
        StringCharacterIterator iterator = new StringCharacterIterator(name);
        for (char character = iterator.current(); character != 65535; character = iterator.next()) {
            if (character == '<') {
                result.append("&lt;");
            } else if (character == '>') {
                result.append("&gt;");
            } else if (character == '\"') {
                result.append("&quot;");
            } else if (character == '\'') {
                result.append("&#039;");
            } else if (character == '&') {
                result.append("&amp;");
            } else {
                result.append(character);
            }
        }
    }

    private void writeVCardEntry(int vcfIndex, String name, StringBuilder result) {
        result.append("<card handle=\"");
        result.append(vcfIndex);
        result.append(".vcf\" name=\"");
        xmlEncode(name, result);
        result.append("\"/>");
    }

    private void notifyUpdateWakeLock() {
        Message msg = Message.obtain(this.mCallback);
        msg.what = 5004;
        msg.sendToTarget();
    }

    public static final void logHeader(HeaderSet hs) {
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

    private void setDbCounters(ApplicationParameter ap) {
        ap.addAPPHeader(Ascii.CR, Ascii.DLE, getDatabaseIdentifier());
    }

    private void setFolderVersionCounters(ApplicationParameter ap) {
        ap.addAPPHeader((byte) 10, Ascii.DLE, getPBPrimaryFolderVersion());
        ap.addAPPHeader(Ascii.VT, Ascii.DLE, getPBSecondaryFolderVersion());
    }

    private void setCallversionCounters(ApplicationParameter ap, AppParamValue appParamValue) {
        ap.addAPPHeader((byte) 10, Ascii.DLE, appParamValue.callHistoryVersionCounter);
        ap.addAPPHeader(Ascii.VT, Ascii.DLE, appParamValue.callHistoryVersionCounter);
    }

    private byte[] getDatabaseIdentifier() {
        this.mDatabaseIdentifierHigh = 0L;
        long j = BluetoothPbapUtils.sDbIdentifier.get();
        this.mDatabaseIdentifierLow = j;
        if (j != -1 && this.mDatabaseIdentifierHigh != -1) {
            ByteBuffer ret = ByteBuffer.allocate(16);
            ret.putLong(this.mDatabaseIdentifierHigh);
            ret.putLong(this.mDatabaseIdentifierLow);
            return ret.array();
        }
        return null;
    }

    private byte[] getPBPrimaryFolderVersion() {
        ByteBuffer pvc = ByteBuffer.allocate(16);
        pvc.putLong(0L);
        Log.d(TAG, "primaryVersionCounter is " + BluetoothPbapUtils.sPrimaryVersionCounter);
        pvc.putLong(BluetoothPbapUtils.sPrimaryVersionCounter);
        return pvc.array();
    }

    private byte[] getPBSecondaryFolderVersion() {
        ByteBuffer svc = ByteBuffer.allocate(16);
        svc.putLong(0L);
        Log.d(TAG, "secondaryVersionCounter is " + BluetoothPbapUtils.sSecondaryVersionCounter);
        svc.putLong(BluetoothPbapUtils.sSecondaryVersionCounter);
        return svc.array();
    }

    private boolean checkPbapFeatureSupport(long featureBitMask) {
        Log.d(TAG, "checkPbapFeatureSupport featureBitMask is " + featureBitMask);
        return (((long) ByteBuffer.wrap(this.mConnAppParamValue.supportedFeature).getInt()) & featureBitMask) != 0;
    }
}
