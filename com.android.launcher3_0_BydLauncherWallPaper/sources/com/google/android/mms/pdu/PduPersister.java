package com.google.android.mms.pdu;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.drm.DrmManagerClient;
import android.net.Uri;
import android.provider.Telephony;
import android.telephony.PhoneNumberUtils;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.mms.ContentType;
import com.google.android.mms.InvalidHeaderValueException;
import com.google.android.mms.MmsException;
import com.google.android.mms.util.PduCache;
import com.google.android.mms.util.PduCacheEntry;
import com.google.android.mms.util.SqliteWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class PduPersister {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final HashMap<Integer, Integer> CHARSET_COLUMN_INDEX_MAP;
    private static final HashMap<Integer, String> CHARSET_COLUMN_NAME_MAP;
    private static final boolean DEBUG = false;
    private static final long DUMMY_THREAD_ID = Long.MAX_VALUE;
    private static final HashMap<Integer, Integer> ENCODED_STRING_COLUMN_INDEX_MAP;
    private static final HashMap<Integer, String> ENCODED_STRING_COLUMN_NAME_MAP;
    private static final boolean LOCAL_LOGV = false;
    private static final HashMap<Integer, Integer> LONG_COLUMN_INDEX_MAP;
    private static final HashMap<Integer, String> LONG_COLUMN_NAME_MAP;
    private static final HashMap<Uri, Integer> MESSAGE_BOX_MAP;
    private static final HashMap<Integer, Integer> OCTET_COLUMN_INDEX_MAP;
    private static final HashMap<Integer, String> OCTET_COLUMN_NAME_MAP;
    private static final int PART_COLUMN_CHARSET = 1;
    private static final int PART_COLUMN_CONTENT_DISPOSITION = 2;
    private static final int PART_COLUMN_CONTENT_ID = 3;
    private static final int PART_COLUMN_CONTENT_LOCATION = 4;
    private static final int PART_COLUMN_CONTENT_TYPE = 5;
    private static final int PART_COLUMN_FILENAME = 6;
    private static final int PART_COLUMN_ID = 0;
    private static final int PART_COLUMN_NAME = 7;
    private static final int PART_COLUMN_TEXT = 8;
    private static final PduCache PDU_CACHE_INSTANCE;
    private static final int PDU_COLUMN_CONTENT_CLASS = 11;
    private static final int PDU_COLUMN_CONTENT_LOCATION = 5;
    private static final int PDU_COLUMN_CONTENT_TYPE = 6;
    private static final int PDU_COLUMN_DATE = 21;
    private static final int PDU_COLUMN_DELIVERY_REPORT = 12;
    private static final int PDU_COLUMN_DELIVERY_TIME = 22;
    private static final int PDU_COLUMN_EXPIRY = 23;
    private static final int PDU_COLUMN_ID = 0;
    private static final int PDU_COLUMN_MESSAGE_BOX = 1;
    private static final int PDU_COLUMN_MESSAGE_CLASS = 7;
    private static final int PDU_COLUMN_MESSAGE_ID = 8;
    private static final int PDU_COLUMN_MESSAGE_SIZE = 24;
    private static final int PDU_COLUMN_MESSAGE_TYPE = 13;
    private static final int PDU_COLUMN_MMS_VERSION = 14;
    private static final int PDU_COLUMN_PRIORITY = 15;
    private static final int PDU_COLUMN_READ_REPORT = 16;
    private static final int PDU_COLUMN_READ_STATUS = 17;
    private static final int PDU_COLUMN_REPORT_ALLOWED = 18;
    private static final int PDU_COLUMN_RESPONSE_TEXT = 9;
    private static final int PDU_COLUMN_RETRIEVE_STATUS = 19;
    private static final int PDU_COLUMN_RETRIEVE_TEXT = 3;
    private static final int PDU_COLUMN_RETRIEVE_TEXT_CHARSET = 26;
    private static final int PDU_COLUMN_STATUS = 20;
    private static final int PDU_COLUMN_SUBJECT = 4;
    private static final int PDU_COLUMN_SUBJECT_CHARSET = 25;
    private static final int PDU_COLUMN_THREAD_ID = 2;
    private static final int PDU_COLUMN_TRANSACTION_ID = 10;
    public static final int PROC_STATUS_COMPLETED = 3;
    public static final int PROC_STATUS_PERMANENTLY_FAILURE = 2;
    public static final int PROC_STATUS_TRANSIENT_FAILURE = 1;
    private static final String TAG = "PduPersister";
    public static final String TEMPORARY_DRM_OBJECT_URI = "content://mms/9223372036854775807/part";
    private static final HashMap<Integer, Integer> TEXT_STRING_COLUMN_INDEX_MAP;
    private static final HashMap<Integer, String> TEXT_STRING_COLUMN_NAME_MAP;
    private static PduPersister sPersister;
    private final ContentResolver mContentResolver;
    private final Context mContext;
    private final DrmManagerClient mDrmManagerClient;
    private static final int[] ADDRESS_FIELDS = {129, 130, 137, 151};
    private static final String[] PDU_PROJECTION = {"_id", Telephony.BaseMmsColumns.MESSAGE_BOX, "thread_id", Telephony.BaseMmsColumns.RETRIEVE_TEXT, Telephony.BaseMmsColumns.SUBJECT, Telephony.BaseMmsColumns.CONTENT_LOCATION, Telephony.BaseMmsColumns.CONTENT_TYPE, Telephony.BaseMmsColumns.MESSAGE_CLASS, Telephony.BaseMmsColumns.MESSAGE_ID, Telephony.BaseMmsColumns.RESPONSE_TEXT, Telephony.BaseMmsColumns.TRANSACTION_ID, Telephony.BaseMmsColumns.CONTENT_CLASS, Telephony.BaseMmsColumns.DELIVERY_REPORT, Telephony.BaseMmsColumns.MESSAGE_TYPE, "v", Telephony.BaseMmsColumns.PRIORITY, Telephony.BaseMmsColumns.READ_REPORT, Telephony.BaseMmsColumns.READ_STATUS, Telephony.BaseMmsColumns.REPORT_ALLOWED, Telephony.BaseMmsColumns.RETRIEVE_STATUS, Telephony.BaseMmsColumns.STATUS, "date", Telephony.BaseMmsColumns.DELIVERY_TIME, Telephony.BaseMmsColumns.EXPIRY, Telephony.BaseMmsColumns.MESSAGE_SIZE, Telephony.BaseMmsColumns.SUBJECT_CHARSET, Telephony.BaseMmsColumns.RETRIEVE_TEXT_CHARSET};
    private static final String[] PART_PROJECTION = {"_id", Telephony.Mms.Part.CHARSET, Telephony.Mms.Part.CONTENT_DISPOSITION, "cid", Telephony.Mms.Part.CONTENT_LOCATION, "ct", Telephony.Mms.Part.FILENAME, "name", "text"};

    static {
        HashMap<Uri, Integer> map = new HashMap<>();
        MESSAGE_BOX_MAP = map;
        map.put(Telephony.Mms.Inbox.CONTENT_URI, 1);
        map.put(Telephony.Mms.Sent.CONTENT_URI, 2);
        map.put(Telephony.Mms.Draft.CONTENT_URI, 3);
        map.put(Telephony.Mms.Outbox.CONTENT_URI, 4);
        HashMap<Integer, Integer> map2 = new HashMap<>();
        CHARSET_COLUMN_INDEX_MAP = map2;
        map2.put(150, 25);
        map2.put(154, 26);
        HashMap<Integer, String> map3 = new HashMap<>();
        CHARSET_COLUMN_NAME_MAP = map3;
        map3.put(150, Telephony.BaseMmsColumns.SUBJECT_CHARSET);
        map3.put(154, Telephony.BaseMmsColumns.RETRIEVE_TEXT_CHARSET);
        HashMap<Integer, Integer> map4 = new HashMap<>();
        ENCODED_STRING_COLUMN_INDEX_MAP = map4;
        map4.put(154, 3);
        map4.put(150, 4);
        HashMap<Integer, String> map5 = new HashMap<>();
        ENCODED_STRING_COLUMN_NAME_MAP = map5;
        map5.put(154, Telephony.BaseMmsColumns.RETRIEVE_TEXT);
        map5.put(150, Telephony.BaseMmsColumns.SUBJECT);
        HashMap<Integer, Integer> map6 = new HashMap<>();
        TEXT_STRING_COLUMN_INDEX_MAP = map6;
        map6.put(131, 5);
        map6.put(132, 6);
        map6.put(138, 7);
        map6.put(139, 8);
        map6.put(147, 9);
        map6.put(152, 10);
        HashMap<Integer, String> map7 = new HashMap<>();
        TEXT_STRING_COLUMN_NAME_MAP = map7;
        map7.put(131, Telephony.BaseMmsColumns.CONTENT_LOCATION);
        map7.put(132, Telephony.BaseMmsColumns.CONTENT_TYPE);
        map7.put(138, Telephony.BaseMmsColumns.MESSAGE_CLASS);
        map7.put(139, Telephony.BaseMmsColumns.MESSAGE_ID);
        map7.put(147, Telephony.BaseMmsColumns.RESPONSE_TEXT);
        map7.put(152, Telephony.BaseMmsColumns.TRANSACTION_ID);
        HashMap<Integer, Integer> map8 = new HashMap<>();
        OCTET_COLUMN_INDEX_MAP = map8;
        map8.put(186, 11);
        map8.put(134, 12);
        map8.put(140, 13);
        map8.put(141, 14);
        map8.put(143, 15);
        map8.put(144, 16);
        map8.put(155, 17);
        map8.put(145, 18);
        map8.put(153, 19);
        map8.put(149, 20);
        HashMap<Integer, String> map9 = new HashMap<>();
        OCTET_COLUMN_NAME_MAP = map9;
        map9.put(186, Telephony.BaseMmsColumns.CONTENT_CLASS);
        map9.put(134, Telephony.BaseMmsColumns.DELIVERY_REPORT);
        map9.put(140, Telephony.BaseMmsColumns.MESSAGE_TYPE);
        map9.put(141, "v");
        map9.put(143, Telephony.BaseMmsColumns.PRIORITY);
        map9.put(144, Telephony.BaseMmsColumns.READ_REPORT);
        map9.put(155, Telephony.BaseMmsColumns.READ_STATUS);
        map9.put(145, Telephony.BaseMmsColumns.REPORT_ALLOWED);
        map9.put(153, Telephony.BaseMmsColumns.RETRIEVE_STATUS);
        map9.put(149, Telephony.BaseMmsColumns.STATUS);
        HashMap<Integer, Integer> map10 = new HashMap<>();
        LONG_COLUMN_INDEX_MAP = map10;
        map10.put(133, 21);
        map10.put(135, 22);
        map10.put(136, 23);
        map10.put(142, 24);
        HashMap<Integer, String> map11 = new HashMap<>();
        LONG_COLUMN_NAME_MAP = map11;
        map11.put(133, "date");
        map11.put(135, Telephony.BaseMmsColumns.DELIVERY_TIME);
        map11.put(136, Telephony.BaseMmsColumns.EXPIRY);
        map11.put(142, Telephony.BaseMmsColumns.MESSAGE_SIZE);
        PDU_CACHE_INSTANCE = PduCache.getInstance();
    }

    private PduPersister(Context context) {
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        this.mDrmManagerClient = new DrmManagerClient(context);
    }

    public static PduPersister getPduPersister(Context context) {
        PduPersister pduPersister = sPersister;
        if (pduPersister == null) {
            sPersister = new PduPersister(context);
        } else if (!context.equals(pduPersister.mContext)) {
            sPersister.release();
            sPersister = new PduPersister(context);
        }
        return sPersister;
    }

    private void setEncodedStringValueToHeaders(Cursor cursor, int i, PduHeaders pduHeaders, int i2) {
        String string = cursor.getString(i);
        if (string == null || string.length() <= 0) {
            return;
        }
        pduHeaders.setEncodedStringValue(new EncodedStringValue(cursor.getInt(CHARSET_COLUMN_INDEX_MAP.get(Integer.valueOf(i2)).intValue()), getBytes(string)), i2);
    }

    private void setTextStringToHeaders(Cursor cursor, int i, PduHeaders pduHeaders, int i2) {
        String string = cursor.getString(i);
        if (string != null) {
            pduHeaders.setTextString(getBytes(string), i2);
        }
    }

    private void setOctetToHeaders(Cursor cursor, int i, PduHeaders pduHeaders, int i2) throws InvalidHeaderValueException {
        if (cursor.isNull(i)) {
            return;
        }
        pduHeaders.setOctet(cursor.getInt(i), i2);
    }

    private void setLongToHeaders(Cursor cursor, int i, PduHeaders pduHeaders, int i2) {
        if (cursor.isNull(i)) {
            return;
        }
        pduHeaders.setLongInteger(cursor.getLong(i), i2);
    }

    private Integer getIntegerFromPartColumn(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return Integer.valueOf(cursor.getInt(i));
    }

    private byte[] getByteArrayFromPartColumn(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return getBytes(cursor.getString(i));
    }

    private PduPart[] loadParts(long j) throws MmsException {
        Cursor cursorQuery = SqliteWrapper.query(this.mContext, this.mContentResolver, Uri.parse("content://mms/" + j + "/part"), PART_PROJECTION, null, null, null);
        InputStream inputStream = null;
        if (cursorQuery != null) {
            try {
                if (cursorQuery.getCount() != 0) {
                    PduPart[] pduPartArr = new PduPart[cursorQuery.getCount()];
                    int i = 0;
                    while (cursorQuery.moveToNext()) {
                        PduPart pduPart = new PduPart();
                        Integer integerFromPartColumn = getIntegerFromPartColumn(cursorQuery, 1);
                        if (integerFromPartColumn != null) {
                            pduPart.setCharset(integerFromPartColumn.intValue());
                        }
                        byte[] byteArrayFromPartColumn = getByteArrayFromPartColumn(cursorQuery, 2);
                        if (byteArrayFromPartColumn != null) {
                            pduPart.setContentDisposition(byteArrayFromPartColumn);
                        }
                        byte[] byteArrayFromPartColumn2 = getByteArrayFromPartColumn(cursorQuery, 3);
                        if (byteArrayFromPartColumn2 != null) {
                            pduPart.setContentId(byteArrayFromPartColumn2);
                        }
                        byte[] byteArrayFromPartColumn3 = getByteArrayFromPartColumn(cursorQuery, 4);
                        if (byteArrayFromPartColumn3 != null) {
                            pduPart.setContentLocation(byteArrayFromPartColumn3);
                        }
                        byte[] byteArrayFromPartColumn4 = getByteArrayFromPartColumn(cursorQuery, 5);
                        if (byteArrayFromPartColumn4 != null) {
                            pduPart.setContentType(byteArrayFromPartColumn4);
                            byte[] byteArrayFromPartColumn5 = getByteArrayFromPartColumn(cursorQuery, 6);
                            if (byteArrayFromPartColumn5 != null) {
                                pduPart.setFilename(byteArrayFromPartColumn5);
                            }
                            byte[] byteArrayFromPartColumn6 = getByteArrayFromPartColumn(cursorQuery, 7);
                            if (byteArrayFromPartColumn6 != null) {
                                pduPart.setName(byteArrayFromPartColumn6);
                            }
                            Uri uri = Uri.parse("content://mms/part/" + cursorQuery.getLong(0));
                            pduPart.setDataUri(uri);
                            String isoString = toIsoString(byteArrayFromPartColumn4);
                            if (!ContentType.isImageType(isoString) && !ContentType.isAudioType(isoString) && !ContentType.isVideoType(isoString)) {
                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                if ("text/plain".equals(isoString) || ContentType.APP_SMIL.equals(isoString) || "text/html".equals(isoString)) {
                                    String string = cursorQuery.getString(8);
                                    if (string == null) {
                                        string = "";
                                    }
                                    byte[] textString = new EncodedStringValue(string).getTextString();
                                    byteArrayOutputStream.write(textString, 0, textString.length);
                                } else {
                                    try {
                                        try {
                                            InputStream inputStreamOpenInputStream = this.mContentResolver.openInputStream(uri);
                                            try {
                                                byte[] bArr = new byte[256];
                                                for (int i2 = inputStreamOpenInputStream.read(bArr); i2 >= 0; i2 = inputStreamOpenInputStream.read(bArr)) {
                                                    byteArrayOutputStream.write(bArr, 0, i2);
                                                }
                                                if (inputStreamOpenInputStream != null) {
                                                    try {
                                                        inputStreamOpenInputStream.close();
                                                    } catch (IOException e) {
                                                        Log.e(TAG, "Failed to close stream", e);
                                                    }
                                                }
                                            } catch (IOException e2) {
                                                e = e2;
                                                inputStream = inputStreamOpenInputStream;
                                                Log.e(TAG, "Failed to load part data", e);
                                                cursorQuery.close();
                                                throw new MmsException(e);
                                            } catch (Throwable th) {
                                                th = th;
                                                inputStream = inputStreamOpenInputStream;
                                                if (inputStream != null) {
                                                    try {
                                                        inputStream.close();
                                                    } catch (IOException e3) {
                                                        Log.e(TAG, "Failed to close stream", e3);
                                                    }
                                                }
                                                throw th;
                                            }
                                        } catch (IOException e4) {
                                            e = e4;
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                    }
                                }
                                pduPart.setData(byteArrayOutputStream.toByteArray());
                            }
                            pduPartArr[i] = pduPart;
                            i++;
                        } else {
                            throw new MmsException("Content-Type must be set.");
                        }
                    }
                    return pduPartArr;
                }
            } finally {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            }
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return null;
    }

    private void loadAddress(long j, PduHeaders pduHeaders) {
        Cursor cursorQuery = SqliteWrapper.query(this.mContext, this.mContentResolver, Uri.parse("content://mms/" + j + "/addr"), new String[]{"address", Telephony.Mms.Addr.CHARSET, "type"}, null, null, null);
        if (cursorQuery != null) {
            while (cursorQuery.moveToNext()) {
                try {
                    String string = cursorQuery.getString(0);
                    if (!TextUtils.isEmpty(string)) {
                        int i = cursorQuery.getInt(2);
                        if (i != 129 && i != 130) {
                            if (i == 137) {
                                pduHeaders.setEncodedStringValue(new EncodedStringValue(cursorQuery.getInt(1), getBytes(string)), i);
                            } else if (i != 151) {
                                Log.e(TAG, "Unknown address type: " + i);
                            }
                        }
                        pduHeaders.appendEncodedStringValue(new EncodedStringValue(cursorQuery.getInt(1), getBytes(string)), i);
                    }
                } finally {
                    cursorQuery.close();
                }
            }
        }
    }

    public GenericPdu load(Uri uri) throws MmsException {
        PduPart[] pduPartArrLoadParts;
        GenericPdu sendReq;
        try {
            PduCache pduCache = PDU_CACHE_INSTANCE;
            synchronized (pduCache) {
                if (pduCache.isUpdating(uri)) {
                    try {
                        pduCache.wait();
                    } catch (InterruptedException e) {
                        Log.e(TAG, "load: ", e);
                    }
                    PduCache pduCache2 = PDU_CACHE_INSTANCE;
                    PduCacheEntry pduCacheEntry = pduCache2.get(uri);
                    if (pduCacheEntry != null) {
                        GenericPdu pdu = pduCacheEntry.getPdu();
                        synchronized (pduCache2) {
                            pduCache2.setUpdating(uri, false);
                            pduCache2.notifyAll();
                        }
                        return pdu;
                    }
                }
                PDU_CACHE_INSTANCE.setUpdating(uri, true);
                Cursor cursorQuery = SqliteWrapper.query(this.mContext, this.mContentResolver, uri, PDU_PROJECTION, null, null, null);
                PduHeaders pduHeaders = new PduHeaders();
                long id = ContentUris.parseId(uri);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.getCount() == 1 && cursorQuery.moveToFirst()) {
                            int i = cursorQuery.getInt(1);
                            long j = cursorQuery.getLong(2);
                            for (Map.Entry<Integer, Integer> entry : ENCODED_STRING_COLUMN_INDEX_MAP.entrySet()) {
                                setEncodedStringValueToHeaders(cursorQuery, entry.getValue().intValue(), pduHeaders, entry.getKey().intValue());
                            }
                            for (Map.Entry<Integer, Integer> entry2 : TEXT_STRING_COLUMN_INDEX_MAP.entrySet()) {
                                setTextStringToHeaders(cursorQuery, entry2.getValue().intValue(), pduHeaders, entry2.getKey().intValue());
                            }
                            for (Map.Entry<Integer, Integer> entry3 : OCTET_COLUMN_INDEX_MAP.entrySet()) {
                                setOctetToHeaders(cursorQuery, entry3.getValue().intValue(), pduHeaders, entry3.getKey().intValue());
                            }
                            for (Map.Entry<Integer, Integer> entry4 : LONG_COLUMN_INDEX_MAP.entrySet()) {
                                setLongToHeaders(cursorQuery, entry4.getValue().intValue(), pduHeaders, entry4.getKey().intValue());
                            }
                            if (id == -1) {
                                throw new MmsException("Error! ID of the message: -1.");
                            }
                            loadAddress(id, pduHeaders);
                            int octet = pduHeaders.getOctet(140);
                            PduBody pduBody = new PduBody();
                            if ((octet == 132 || octet == 128) && (pduPartArrLoadParts = loadParts(id)) != null) {
                                for (PduPart pduPart : pduPartArrLoadParts) {
                                    pduBody.addPart(pduPart);
                                }
                            }
                            switch (octet) {
                                case 128:
                                    sendReq = new SendReq(pduHeaders, pduBody);
                                    break;
                                case 129:
                                case 137:
                                case 138:
                                case 139:
                                case 140:
                                case 141:
                                case 142:
                                case 143:
                                case 144:
                                case 145:
                                case 146:
                                case 147:
                                case 148:
                                case 149:
                                case 150:
                                case 151:
                                    throw new MmsException("Unsupported PDU type: " + Integer.toHexString(octet));
                                case 130:
                                    sendReq = new NotificationInd(pduHeaders);
                                    break;
                                case 131:
                                    sendReq = new NotifyRespInd(pduHeaders);
                                    break;
                                case 132:
                                    sendReq = new RetrieveConf(pduHeaders, pduBody);
                                    break;
                                case 133:
                                    sendReq = new AcknowledgeInd(pduHeaders);
                                    break;
                                case 134:
                                    sendReq = new DeliveryInd(pduHeaders);
                                    break;
                                case 135:
                                    sendReq = new ReadRecInd(pduHeaders);
                                    break;
                                case 136:
                                    sendReq = new ReadOrigInd(pduHeaders);
                                    break;
                                default:
                                    throw new MmsException("Unrecognized PDU type: " + Integer.toHexString(octet));
                            }
                            PduCache pduCache3 = PDU_CACHE_INSTANCE;
                            synchronized (pduCache3) {
                                if (sendReq != null) {
                                    pduCache3.put(uri, new PduCacheEntry(sendReq, i, j));
                                    pduCache3.setUpdating(uri, false);
                                    pduCache3.notifyAll();
                                } else {
                                    pduCache3.setUpdating(uri, false);
                                    pduCache3.notifyAll();
                                }
                            }
                            return sendReq;
                        }
                    } finally {
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                    }
                }
                throw new MmsException("Bad uri: " + uri);
            }
        } catch (Throwable th) {
            PduCache pduCache4 = PDU_CACHE_INSTANCE;
            synchronized (pduCache4) {
                pduCache4.setUpdating(uri, false);
                pduCache4.notifyAll();
                throw th;
            }
        }
    }

    private void persistAddress(long j, int i, EncodedStringValue[] encodedStringValueArr) {
        ContentValues contentValues = new ContentValues(3);
        for (EncodedStringValue encodedStringValue : encodedStringValueArr) {
            contentValues.clear();
            contentValues.put("address", toIsoString(encodedStringValue.getTextString()));
            contentValues.put(Telephony.Mms.Addr.CHARSET, Integer.valueOf(encodedStringValue.getCharacterSet()));
            contentValues.put("type", Integer.valueOf(i));
            SqliteWrapper.insert(this.mContext, this.mContentResolver, Uri.parse("content://mms/" + j + "/addr"), contentValues);
        }
    }

    private static String getPartContentType(PduPart pduPart) {
        if (pduPart.getContentType() == null) {
            return null;
        }
        return toIsoString(pduPart.getContentType());
    }

    public Uri persistPart(PduPart pduPart, long j, HashMap<Uri, InputStream> map) throws Throwable {
        Uri uri = Uri.parse("content://mms/" + j + "/part");
        ContentValues contentValues = new ContentValues(8);
        int charset = pduPart.getCharset();
        if (charset != 0) {
            contentValues.put(Telephony.Mms.Part.CHARSET, Integer.valueOf(charset));
        }
        String partContentType = getPartContentType(pduPart);
        if (partContentType != null) {
            if (ContentType.IMAGE_JPG.equals(partContentType)) {
                partContentType = ContentType.IMAGE_JPEG;
            }
            contentValues.put("ct", partContentType);
            if (ContentType.APP_SMIL.equals(partContentType)) {
                contentValues.put("seq", (Integer) (-1));
            }
            if (pduPart.getFilename() != null) {
                contentValues.put(Telephony.Mms.Part.FILENAME, new String(pduPart.getFilename()));
            }
            if (pduPart.getName() != null) {
                contentValues.put("name", new String(pduPart.getName()));
            }
            if (pduPart.getContentDisposition() != null) {
                contentValues.put(Telephony.Mms.Part.CONTENT_DISPOSITION, toIsoString(pduPart.getContentDisposition()));
            }
            if (pduPart.getContentId() != null) {
                contentValues.put("cid", toIsoString(pduPart.getContentId()));
            }
            if (pduPart.getContentLocation() != null) {
                contentValues.put(Telephony.Mms.Part.CONTENT_LOCATION, toIsoString(pduPart.getContentLocation()));
            }
            Uri uriInsert = SqliteWrapper.insert(this.mContext, this.mContentResolver, uri, contentValues);
            if (uriInsert == null) {
                throw new MmsException("Failed to persist part, return null.");
            }
            persistData(pduPart, uriInsert, partContentType, map);
            pduPart.setDataUri(uriInsert);
            return uriInsert;
        }
        throw new MmsException("MIME type of the part must be set.");
    }

    /* JADX WARN: Code restructure failed: missing block: B:65:0x00f9, code lost:
    
        throw new com.google.android.mms.MmsException("Error converting drm data.");
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v34 */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.io.InputStream, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v20 */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.io.OutputStream, java.lang.Object] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void persistData(com.google.android.mms.pdu.PduPart r18, android.net.Uri r19, java.lang.String r20, java.util.HashMap<android.net.Uri, java.io.InputStream> r21) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 721
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.mms.pdu.PduPersister.persistData(com.google.android.mms.pdu.PduPart, android.net.Uri, java.lang.String, java.util.HashMap):void");
    }

    private void updateAddress(long j, int i, EncodedStringValue[] encodedStringValueArr) {
        SqliteWrapper.delete(this.mContext, this.mContentResolver, Uri.parse("content://mms/" + j + "/addr"), "type=" + i, null);
        persistAddress(j, i, encodedStringValueArr);
    }

    public void updateHeaders(Uri uri, SendReq sendReq) {
        PduCache pduCache = PDU_CACHE_INSTANCE;
        synchronized (pduCache) {
            if (pduCache.isUpdating(uri)) {
                try {
                    pduCache.wait();
                } catch (InterruptedException e) {
                    Log.e(TAG, "updateHeaders: ", e);
                }
            }
        }
        PDU_CACHE_INSTANCE.purge(uri);
        ContentValues contentValues = new ContentValues(10);
        byte[] contentType = sendReq.getContentType();
        if (contentType != null) {
            contentValues.put(Telephony.BaseMmsColumns.CONTENT_TYPE, toIsoString(contentType));
        }
        long date = sendReq.getDate();
        if (date != -1) {
            contentValues.put("date", Long.valueOf(date));
        }
        int deliveryReport = sendReq.getDeliveryReport();
        if (deliveryReport != 0) {
            contentValues.put(Telephony.BaseMmsColumns.DELIVERY_REPORT, Integer.valueOf(deliveryReport));
        }
        long expiry = sendReq.getExpiry();
        if (expiry != -1) {
            contentValues.put(Telephony.BaseMmsColumns.EXPIRY, Long.valueOf(expiry));
        }
        byte[] messageClass = sendReq.getMessageClass();
        if (messageClass != null) {
            contentValues.put(Telephony.BaseMmsColumns.MESSAGE_CLASS, toIsoString(messageClass));
        }
        int priority = sendReq.getPriority();
        if (priority != 0) {
            contentValues.put(Telephony.BaseMmsColumns.PRIORITY, Integer.valueOf(priority));
        }
        int readReport = sendReq.getReadReport();
        if (readReport != 0) {
            contentValues.put(Telephony.BaseMmsColumns.READ_REPORT, Integer.valueOf(readReport));
        }
        byte[] transactionId = sendReq.getTransactionId();
        if (transactionId != null) {
            contentValues.put(Telephony.BaseMmsColumns.TRANSACTION_ID, toIsoString(transactionId));
        }
        EncodedStringValue subject = sendReq.getSubject();
        if (subject != null) {
            contentValues.put(Telephony.BaseMmsColumns.SUBJECT, toIsoString(subject.getTextString()));
            contentValues.put(Telephony.BaseMmsColumns.SUBJECT_CHARSET, Integer.valueOf(subject.getCharacterSet()));
        } else {
            contentValues.put(Telephony.BaseMmsColumns.SUBJECT, "");
        }
        long messageSize = sendReq.getMessageSize();
        if (messageSize > 0) {
            contentValues.put(Telephony.BaseMmsColumns.MESSAGE_SIZE, Long.valueOf(messageSize));
        }
        PduHeaders pduHeaders = sendReq.getPduHeaders();
        HashSet hashSet = new HashSet();
        for (int i : ADDRESS_FIELDS) {
            EncodedStringValue[] encodedStringValues = null;
            if (i == 137) {
                EncodedStringValue encodedStringValue = pduHeaders.getEncodedStringValue(i);
                if (encodedStringValue != null) {
                    encodedStringValues = new EncodedStringValue[]{encodedStringValue};
                }
            } else {
                encodedStringValues = pduHeaders.getEncodedStringValues(i);
            }
            if (encodedStringValues != null) {
                updateAddress(ContentUris.parseId(uri), i, encodedStringValues);
                if (i == 151) {
                    for (EncodedStringValue encodedStringValue2 : encodedStringValues) {
                        if (encodedStringValue2 != null) {
                            hashSet.add(encodedStringValue2.getString());
                        }
                    }
                }
            }
        }
        if (!hashSet.isEmpty()) {
            contentValues.put("thread_id", Long.valueOf(Telephony.Threads.getOrCreateThreadId(this.mContext, hashSet)));
        }
        SqliteWrapper.update(this.mContext, this.mContentResolver, uri, contentValues, null, null);
    }

    private void updatePart(Uri uri, PduPart pduPart, HashMap<Uri, InputStream> map) throws Throwable {
        ContentValues contentValues = new ContentValues(7);
        int charset = pduPart.getCharset();
        if (charset != 0) {
            contentValues.put(Telephony.Mms.Part.CHARSET, Integer.valueOf(charset));
        }
        if (pduPart.getContentType() != null) {
            String isoString = toIsoString(pduPart.getContentType());
            contentValues.put("ct", isoString);
            if (pduPart.getFilename() != null) {
                contentValues.put(Telephony.Mms.Part.FILENAME, new String(pduPart.getFilename()));
            }
            if (pduPart.getName() != null) {
                contentValues.put("name", new String(pduPart.getName()));
            }
            if (pduPart.getContentDisposition() != null) {
                contentValues.put(Telephony.Mms.Part.CONTENT_DISPOSITION, toIsoString(pduPart.getContentDisposition()));
            }
            if (pduPart.getContentId() != null) {
                contentValues.put("cid", toIsoString(pduPart.getContentId()));
            }
            if (pduPart.getContentLocation() != null) {
                contentValues.put(Telephony.Mms.Part.CONTENT_LOCATION, toIsoString(pduPart.getContentLocation()));
            }
            SqliteWrapper.update(this.mContext, this.mContentResolver, uri, contentValues, null, null);
            if (pduPart.getData() == null && uri.equals(pduPart.getDataUri())) {
                return;
            }
            persistData(pduPart, uri, isoString, map);
            return;
        }
        throw new MmsException("MIME type of the part must be set.");
    }

    public void updateParts(Uri uri, PduBody pduBody, HashMap<Uri, InputStream> map) throws MmsException {
        try {
            PduCache pduCache = PDU_CACHE_INSTANCE;
            synchronized (pduCache) {
                if (pduCache.isUpdating(uri)) {
                    try {
                        pduCache.wait();
                    } catch (InterruptedException e) {
                        Log.e(TAG, "updateParts: ", e);
                    }
                    PduCacheEntry pduCacheEntry = PDU_CACHE_INSTANCE.get(uri);
                    if (pduCacheEntry != null) {
                        ((MultimediaMessagePdu) pduCacheEntry.getPdu()).setBody(pduBody);
                    }
                    PDU_CACHE_INSTANCE.setUpdating(uri, true);
                } else {
                    PDU_CACHE_INSTANCE.setUpdating(uri, true);
                }
            }
            ArrayList arrayList = new ArrayList();
            HashMap map2 = new HashMap();
            int partsNum = pduBody.getPartsNum();
            StringBuilder sbAppend = new StringBuilder().append('(');
            for (int i = 0; i < partsNum; i++) {
                PduPart part = pduBody.getPart(i);
                Uri dataUri = part.getDataUri();
                if (dataUri == null || TextUtils.isEmpty(dataUri.getAuthority()) || !dataUri.getAuthority().startsWith("mms")) {
                    arrayList.add(part);
                } else {
                    map2.put(dataUri, part);
                    if (sbAppend.length() > 1) {
                        sbAppend.append(" AND ");
                    }
                    sbAppend.append("_id");
                    sbAppend.append("!=");
                    DatabaseUtils.appendEscapedSQLString(sbAppend, dataUri.getLastPathSegment());
                }
            }
            sbAppend.append(')');
            long id = ContentUris.parseId(uri);
            SqliteWrapper.delete(this.mContext, this.mContentResolver, Uri.parse(Telephony.Mms.CONTENT_URI + "/" + id + "/part"), sbAppend.length() > 2 ? sbAppend.toString() : null, null);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                persistPart((PduPart) it.next(), id, map);
            }
            for (Map.Entry entry : map2.entrySet()) {
                updatePart((Uri) entry.getKey(), (PduPart) entry.getValue(), map);
            }
            PduCache pduCache2 = PDU_CACHE_INSTANCE;
            synchronized (pduCache2) {
                pduCache2.setUpdating(uri, false);
                pduCache2.notifyAll();
            }
        } catch (Throwable th) {
            PduCache pduCache3 = PDU_CACHE_INSTANCE;
            synchronized (pduCache3) {
                pduCache3.setUpdating(uri, false);
                pduCache3.notifyAll();
                throw th;
            }
        }
    }

    public Uri persist(GenericPdu genericPdu, Uri uri, boolean z, boolean z2, HashMap<Uri, InputStream> map) throws Throwable {
        long id;
        int i;
        int dataLength;
        int i2;
        int i3;
        Uri uriInsert;
        PduBody body;
        if (uri == null) {
            throw new MmsException("Uri may not be null.");
        }
        try {
            id = ContentUris.parseId(uri);
        } catch (NumberFormatException unused) {
            id = -1;
        }
        boolean z3 = id != -1;
        if (!z3 && MESSAGE_BOX_MAP.get(uri) == null) {
            throw new MmsException("Bad destination, must be one of content://mms/inbox, content://mms/sent, content://mms/drafts, content://mms/outbox, content://mms/temp.");
        }
        PduCache pduCache = PDU_CACHE_INSTANCE;
        synchronized (pduCache) {
            if (pduCache.isUpdating(uri)) {
                try {
                    pduCache.wait();
                } catch (InterruptedException e) {
                    Log.e(TAG, "persist1: ", e);
                }
            }
        }
        PDU_CACHE_INSTANCE.purge(uri);
        PduHeaders pduHeaders = genericPdu.getPduHeaders();
        ContentValues contentValues = new ContentValues();
        for (Map.Entry<Integer, String> entry : ENCODED_STRING_COLUMN_NAME_MAP.entrySet()) {
            int iIntValue = entry.getKey().intValue();
            EncodedStringValue encodedStringValue = pduHeaders.getEncodedStringValue(iIntValue);
            if (encodedStringValue != null) {
                String str = CHARSET_COLUMN_NAME_MAP.get(Integer.valueOf(iIntValue));
                contentValues.put(entry.getValue(), toIsoString(encodedStringValue.getTextString()));
                contentValues.put(str, Integer.valueOf(encodedStringValue.getCharacterSet()));
            }
        }
        for (Map.Entry<Integer, String> entry2 : TEXT_STRING_COLUMN_NAME_MAP.entrySet()) {
            byte[] textString = pduHeaders.getTextString(entry2.getKey().intValue());
            if (textString != null) {
                contentValues.put(entry2.getValue(), toIsoString(textString));
            }
        }
        for (Map.Entry<Integer, String> entry3 : OCTET_COLUMN_NAME_MAP.entrySet()) {
            int octet = pduHeaders.getOctet(entry3.getKey().intValue());
            if (octet != 0) {
                contentValues.put(entry3.getValue(), Integer.valueOf(octet));
            }
        }
        for (Map.Entry<Integer, String> entry4 : LONG_COLUMN_NAME_MAP.entrySet()) {
            long longInteger = pduHeaders.getLongInteger(entry4.getKey().intValue());
            if (longInteger != -1) {
                contentValues.put(entry4.getValue(), Long.valueOf(longInteger));
            }
        }
        int[] iArr = ADDRESS_FIELDS;
        HashMap<Integer, EncodedStringValue[]> map2 = new HashMap<>(iArr.length);
        for (int i4 : iArr) {
            EncodedStringValue[] encodedStringValues = null;
            if (i4 == 137) {
                EncodedStringValue encodedStringValue2 = pduHeaders.getEncodedStringValue(i4);
                if (encodedStringValue2 != null) {
                    encodedStringValues = new EncodedStringValue[]{encodedStringValue2};
                }
            } else {
                encodedStringValues = pduHeaders.getEncodedStringValues(i4);
            }
            map2.put(Integer.valueOf(i4), encodedStringValues);
        }
        HashSet<String> hashSet = new HashSet<>();
        int messageType = genericPdu.getMessageType();
        if (messageType == 130 || messageType == 132 || messageType == 128) {
            if (messageType == 128) {
                i = 0;
                loadRecipients(151, hashSet, map2, false);
            } else if (messageType == 130 || messageType == 132) {
                i = 0;
                loadRecipients(137, hashSet, map2, false);
                if (z2) {
                    loadRecipients(151, hashSet, map2, true);
                    loadRecipients(130, hashSet, map2, true);
                }
            } else {
                i = 0;
            }
            long orCreateThreadId = 0;
            if (z && !hashSet.isEmpty()) {
                orCreateThreadId = Telephony.Threads.getOrCreateThreadId(this.mContext, hashSet);
            }
            contentValues.put("thread_id", Long.valueOf(orCreateThreadId));
        } else {
            i = 0;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (!(genericPdu instanceof MultimediaMessagePdu) || (body = ((MultimediaMessagePdu) genericPdu).getBody()) == null) {
            dataLength = 0;
            i2 = 1;
        } else {
            int partsNum = body.getPartsNum();
            i2 = partsNum > 2 ? i : 1;
            int i5 = i;
            dataLength = i5;
            while (i5 < partsNum) {
                PduPart part = body.getPart(i5);
                dataLength += part.getDataLength();
                persistPart(part, jCurrentTimeMillis, map);
                String partContentType = getPartContentType(part);
                if (partContentType != null && !ContentType.APP_SMIL.equals(partContentType) && !"text/plain".equals(partContentType)) {
                    i2 = 0;
                }
                i5++;
            }
        }
        contentValues.put(Telephony.BaseMmsColumns.TEXT_ONLY, Integer.valueOf(i2));
        if (contentValues.getAsInteger(Telephony.BaseMmsColumns.MESSAGE_SIZE) == null) {
            contentValues.put(Telephony.BaseMmsColumns.MESSAGE_SIZE, Integer.valueOf(dataLength));
        }
        if (z3) {
            i3 = 0;
            SqliteWrapper.update(this.mContext, this.mContentResolver, uri, contentValues, null, null);
            uriInsert = uri;
        } else {
            i3 = 0;
            uriInsert = SqliteWrapper.insert(this.mContext, this.mContentResolver, uri, contentValues);
            if (uriInsert == null) {
                throw new MmsException("persist() failed: return null.");
            }
            id = ContentUris.parseId(uriInsert);
        }
        ContentValues contentValues2 = new ContentValues(1);
        contentValues2.put(Telephony.Mms.Part.MSG_ID, Long.valueOf(id));
        SqliteWrapper.update(this.mContext, this.mContentResolver, Uri.parse("content://mms/" + jCurrentTimeMillis + "/part"), contentValues2, null, null);
        if (!z3) {
            uriInsert = Uri.parse(uri + "/" + id);
        }
        int[] iArr2 = ADDRESS_FIELDS;
        int length = iArr2.length;
        while (i3 < length) {
            int i6 = iArr2[i3];
            EncodedStringValue[] encodedStringValueArr = map2.get(Integer.valueOf(i6));
            if (encodedStringValueArr != null) {
                persistAddress(id, i6, encodedStringValueArr);
            }
            i3++;
        }
        return uriInsert;
    }

    private void loadRecipients(int i, HashSet<String> hashSet, HashMap<Integer, EncodedStringValue[]> map, boolean z) {
        EncodedStringValue[] encodedStringValueArr = map.get(Integer.valueOf(i));
        if (encodedStringValueArr == null) {
            return;
        }
        if (z && encodedStringValueArr.length == 1) {
            return;
        }
        SubscriptionManager subscriptionManagerFrom = SubscriptionManager.from(this.mContext);
        HashSet hashSet2 = new HashSet();
        if (z) {
            Iterator<SubscriptionInfo> it = subscriptionManagerFrom.getActiveSubscriptionInfoList().iterator();
            while (it.hasNext()) {
                String line1Number = ((TelephonyManager) this.mContext.getSystemService(TelephonyManager.class)).createForSubscriptionId(it.next().getSubscriptionId()).getLine1Number();
                if (line1Number != null) {
                    hashSet2.add(line1Number);
                }
            }
        }
        for (EncodedStringValue encodedStringValue : encodedStringValueArr) {
            if (encodedStringValue != null) {
                String string = encodedStringValue.getString();
                if (z) {
                    Iterator it2 = hashSet2.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        if (!PhoneNumberUtils.compare(string, (String) it2.next()) && !hashSet.contains(string)) {
                            hashSet.add(string);
                            break;
                        }
                    }
                } else if (!hashSet.contains(string)) {
                    hashSet.add(string);
                }
            }
        }
    }

    public Uri move(Uri uri, Uri uri2) throws MmsException {
        long id = ContentUris.parseId(uri);
        if (id == -1) {
            throw new MmsException("Error! ID of the message: -1.");
        }
        Integer num = MESSAGE_BOX_MAP.get(uri2);
        if (num == null) {
            throw new MmsException("Bad destination, must be one of content://mms/inbox, content://mms/sent, content://mms/drafts, content://mms/outbox, content://mms/temp.");
        }
        ContentValues contentValues = new ContentValues(1);
        contentValues.put(Telephony.BaseMmsColumns.MESSAGE_BOX, num);
        SqliteWrapper.update(this.mContext, this.mContentResolver, uri, contentValues, null, null);
        return ContentUris.withAppendedId(uri2, id);
    }

    public static String toIsoString(byte[] bArr) {
        try {
            return new String(bArr, CharacterSets.MIMENAME_ISO_8859_1);
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "ISO_8859_1 must be supported!", e);
            return "";
        }
    }

    public static byte[] getBytes(String str) {
        try {
            return str.getBytes(CharacterSets.MIMENAME_ISO_8859_1);
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "ISO_8859_1 must be supported!", e);
            return new byte[0];
        }
    }

    public void release() {
        SqliteWrapper.delete(this.mContext, this.mContentResolver, Uri.parse(TEMPORARY_DRM_OBJECT_URI), null, null);
        this.mDrmManagerClient.release();
    }

    public Cursor getPendingMessages(long j) {
        Uri.Builder builderBuildUpon = Telephony.MmsSms.PendingMessages.CONTENT_URI.buildUpon();
        builderBuildUpon.appendQueryParameter("protocol", "mms");
        return SqliteWrapper.query(this.mContext, this.mContentResolver, builderBuildUpon.build(), null, "err_type < ? AND due_time <= ?", new String[]{String.valueOf(10), String.valueOf(j)}, Telephony.MmsSms.PendingMessages.DUE_TIME);
    }
}
