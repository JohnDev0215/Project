package com.android.bluetooth.map;

import android.util.Log;
import com.android.bluetooth.SignedLongLong;
import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothMapAppParams {
    private static final int ATTACHMENT = 10;
    private static final int ATTACHMENT_LEN = 1;
    private static final int CHARSET = 20;
    private static final int CHARSET_LEN = 1;
    public static final int CHARSET_NATIVE = 0;
    public static final int CHARSET_UTF8 = 1;
    private static final int CHAT_STATE = 33;
    private static final int CHAT_STATE_CONVO_ID = 57;
    private static final int CHAT_STATE_CONVO_ID_LEN = 16;
    private static final int CHAT_STATE_LEN = 1;
    private static final int CONVO_LISTING_SIZE = 54;
    private static final int CONVO_LISTING_SIZE_LEN = 2;
    private static final int CONVO_LIST_VER_COUNTER = 27;
    private static final int CONVO_LIST_VER_COUNTER_LEN = 16;
    private static final int CONVO_PARAMETER_MASK = 38;
    private static final int CONVO_PARAMETER_MASK_LEN = 4;
    private static final int DATABASE_INDETIFIER = 26;
    private static final int DATABASE_INDETIFIER_LEN = 16;
    private static final int FILTER_CONVO_ID = 34;
    private static final int FILTER_CONVO_ID_LEN = 32;
    private static final int FILTER_MESSAGE_HANDLE = 36;
    private static final int FILTER_MESSAGE_HANDLE_LEN = 8;
    private static final int FILTER_MESSAGE_TYPE = 3;
    private static final int FILTER_MESSAGE_TYPE_LEN = 1;
    public static final int FILTER_MSG_TYPE_MASK = 31;
    public static final int FILTER_NO_EMAIL = 4;
    public static final int FILTER_NO_IM = 16;
    public static final int FILTER_NO_MMS = 8;
    public static final int FILTER_NO_SMS_CDMA = 2;
    public static final int FILTER_NO_SMS_GSM = 1;
    private static final int FILTER_ORIGINATOR = 8;
    private static final int FILTER_PERIOD_BEGIN = 4;
    private static final int FILTER_PERIOD_END = 5;
    private static final int FILTER_PRESENCE = 55;
    private static final int FILTER_PRESENCE_LEN = 1;
    private static final int FILTER_PRIORITY = 9;
    private static final int FILTER_PRIORITY_LEN = 1;
    private static final int FILTER_READ_STATUS = 6;
    private static final int FILTER_READ_STATUS_LEN = 1;
    private static final int FILTER_RECIPIENT = 7;
    private static final int FILTER_UID_PRESENT = 56;
    private static final int FILTER_UID_PRESENT_LEN = 1;
    private static final int FOLDER_LISTING_SIZE = 17;
    private static final int FOLDER_LISTING_SIZE_LEN = 2;
    private static final int FOLDER_VER_COUNTER = 35;
    private static final int FOLDER_VER_COUNTER_LEN = 32;
    private static final int FRACTION_DELIVER = 22;
    public static final int FRACTION_DELIVER_LAST = 1;
    private static final int FRACTION_DELIVER_LEN = 1;
    public static final int FRACTION_DELIVER_MORE = 0;
    private static final int FRACTION_REQUEST = 21;
    public static final int FRACTION_REQUEST_FIRST = 0;
    private static final int FRACTION_REQUEST_LEN = 1;
    public static final int FRACTION_REQUEST_NEXT = 1;
    public static final int INVALID_VALUE_PARAMETER = -1;
    private static final int LAST_ACTIVITY = 30;
    private static final int MAS_INSTANCE_ID = 15;
    private static final int MAS_INSTANCE_ID_LEN = 1;
    private static final int MAX_LIST_COUNT = 1;
    private static final int MAX_LIST_COUNT_LEN = 2;
    private static final int MESSAGE_LISTING_SIZE = 18;
    private static final int MESSAGE_LISTING_SIZE_LEN = 2;
    private static final int MSE_TIME = 25;
    private static final int NEW_MESSAGE = 13;
    private static final int NEW_MESSAGE_LEN = 1;
    private static final int NOTIFICATION_FILTER = 37;
    private static final int NOTIFICATION_FILTER_LEN = 4;
    private static final int NOTIFICATION_STATUS = 14;
    private static final int NOTIFICATION_STATUS_LEN = 1;
    public static final int NOTIFICATION_STATUS_NO = 0;
    public static final int NOTIFICATION_STATUS_YES = 1;
    private static final int PARAMETER_MASK = 16;
    private static final int PARAMETER_MASK_LEN = 4;
    private static final int PRESENCE_AVAILABLE = 28;
    private static final int PRESENCE_AVAILABLE_LEN = 1;
    private static final int PRESENCE_TEXT = 29;
    private static final int RETRY = 12;
    private static final int RETRY_LEN = 1;
    private static final int START_OFFSET = 2;
    private static final int START_OFFSET_LEN = 2;
    private static final int STATUS_INDICATOR = 23;
    public static final int STATUS_INDICATOR_DELETED = 1;
    private static final int STATUS_INDICATOR_LEN = 1;
    public static final int STATUS_INDICATOR_READ = 0;
    private static final int STATUS_VALUE = 24;
    private static final int STATUS_VALUE_LEN = 1;
    public static final int STATUS_VALUE_NO = 0;
    public static final int STATUS_VALUE_YES = 1;
    private static final int SUBJECT_LENGTH = 19;
    private static final int SUBJECT_LENGTH_LEN = 1;
    private static final String TAG = "BluetoothMapAppParams";
    private static final int TRANSPARENT = 11;
    private static final int TRANSPARENT_LEN = 1;
    private int mMaxListCount = -1;
    private int mStartOffset = -1;
    private int mFilterMessageType = -1;
    private long mFilterPeriodBegin = -1;
    private long mFilterPeriodEnd = -1;
    private int mFilterReadStatus = -1;
    private String mFilterRecipient = null;
    private String mFilterOriginator = null;
    private int mFilterPriority = -1;
    private int mAttachment = -1;
    private int mTransparent = -1;
    private int mRetry = -1;
    private int mNewMessage = -1;
    private int mNotificationStatus = -1;
    private long mNotificationFilter = -1;
    private int mMasInstanceId = -1;
    private long mParameterMask = -1;
    private int mFolderListingSize = -1;
    private int mMessageListingSize = -1;
    private int mConvoListingSize = -1;
    private int mSubjectLength = -1;
    private int mCharset = -1;
    private int mFractionRequest = -1;
    private int mFractionDeliver = -1;
    private int mStatusIndicator = -1;
    private int mStatusValue = -1;
    private long mMseTime = -1;
    private long mConvoListingVerCounterLow = -1;
    private long mConvoListingVerCounterHigh = -1;
    private long mDatabaseIdentifierLow = -1;
    private long mDatabaseIdentifierHigh = -1;
    private long mFolderVerCounterLow = -1;
    private long mFolderVerCounterHigh = -1;
    private int mPresenceAvailability = -1;
    private String mPresenceStatus = null;
    private long mLastActivity = -1;
    private int mChatState = -1;
    private SignedLongLong mFilterConvoId = null;
    private int mFilterPresence = -1;
    private int mFilterUidPresent = -1;
    private SignedLongLong mChatStateConvoId = null;
    private long mFilterMsgHandle = -1;
    private long mConvoParameterMask = -1;

    public BluetoothMapAppParams() {
    }

    public BluetoothMapAppParams(byte[] appParams) throws ParseException, IllegalArgumentException {
        parseParams(appParams);
    }

    private void parseParams(byte[] appParams) throws ParseException, IllegalArgumentException {
        int tagId = 0;
        ByteBuffer appParamBuf = ByteBuffer.wrap(appParams);
        appParamBuf.order(ByteOrder.BIG_ENDIAN);
        while (tagId < appParams.length) {
            int i = tagId + 1;
            int tagId2 = appParams[tagId] & 255;
            int i2 = i + 1;
            int tagLength = appParams[i] & 255;
            switch (tagId2) {
                case 1:
                    if (tagLength != 2) {
                        Log.w(TAG, "MAX_LIST_COUNT: Wrong length received: " + tagLength + " expected: 2");
                    } else {
                        setMaxListCount(appParamBuf.getShort(i2) & 65535);
                    }
                    break;
                case 2:
                    if (tagLength != 2) {
                        Log.w(TAG, "START_OFFSET: Wrong length received: " + tagLength + " expected: 2");
                    } else {
                        setStartOffset(appParamBuf.getShort(i2) & 65535);
                    }
                    break;
                case 3:
                    if (tagLength != 1) {
                        Log.w(TAG, "FILTER_MESSAGE_TYPE: Wrong length received: " + tagLength + " expected: 1");
                    } else {
                        setFilterMessageType(appParams[i2] & Ascii.US);
                    }
                    break;
                case 4:
                    if (tagLength != 0) {
                        setFilterPeriodBegin(new String(appParams, i2, tagLength));
                    } else {
                        Log.w(TAG, "FILTER_PERIOD_BEGIN: Wrong length received: " + tagLength + " expected to be more than 0");
                    }
                    break;
                case 5:
                    if (tagLength != 0) {
                        setFilterPeriodEnd(new String(appParams, i2, tagLength));
                    } else {
                        Log.w(TAG, "FILTER_PERIOD_END: Wrong length received: " + tagLength + " expected to be more than 0");
                    }
                    break;
                case 6:
                    if (tagLength != 1) {
                        Log.w(TAG, "FILTER_READ_STATUS: Wrong length received: " + tagLength + " expected: 1");
                    } else {
                        setFilterReadStatus(appParams[i2] & 3);
                    }
                    break;
                case 7:
                    if (tagLength != 0) {
                        setFilterRecipient(new String(appParams, i2, tagLength));
                    } else {
                        Log.w(TAG, "FILTER_RECIPIENT: Wrong length received: " + tagLength + " expected to be more than 0");
                    }
                    break;
                case 8:
                    if (tagLength != 0) {
                        setFilterOriginator(new String(appParams, i2, tagLength));
                    } else {
                        Log.w(TAG, "FILTER_ORIGINATOR: Wrong length received: " + tagLength + " expected to be more than 0");
                    }
                    break;
                case 9:
                    if (tagLength != 1) {
                        Log.w(TAG, "FILTER_PRIORITY: Wrong length received: " + tagLength + " expected: 1");
                    } else {
                        setFilterPriority(appParams[i2] & 3);
                    }
                    break;
                case 10:
                    if (tagLength != 1) {
                        Log.w(TAG, "ATTACHMENT: Wrong length received: " + tagLength + " expected: 1");
                    } else {
                        setAttachment(appParams[i2] & 1);
                    }
                    break;
                case 11:
                    if (tagLength != 1) {
                        Log.w(TAG, "TRANSPARENT: Wrong length received: " + tagLength + " expected: 1");
                    } else {
                        setTransparent(appParams[i2] & 1);
                    }
                    break;
                case 12:
                    if (tagLength != 1) {
                        Log.w(TAG, "RETRY: Wrong length received: " + tagLength + " expected: 1");
                    } else {
                        setRetry(appParams[i2] & 1);
                    }
                    break;
                case 13:
                    if (tagLength != 1) {
                        Log.w(TAG, "NEW_MESSAGE: Wrong length received: " + tagLength + " expected: 1");
                    } else {
                        setNewMessage(appParams[i2] & 1);
                    }
                    break;
                case 14:
                    if (tagLength != 1) {
                        Log.w(TAG, "NOTIFICATION_STATUS: Wrong length received: " + tagLength + " expected: 1");
                    } else {
                        setNotificationStatus(appParams[i2] & 1);
                    }
                    break;
                case 15:
                    if (tagLength != 1) {
                        Log.w(TAG, "MAS_INSTANCE_ID: Wrong length received: " + tagLength + " expected: 1");
                    } else {
                        setMasInstanceId(appParams[i2] & UnsignedBytes.MAX_VALUE);
                    }
                    break;
                case 16:
                    if (tagLength == 4) {
                        setParameterMask(4294967295L & ((long) appParamBuf.getInt(i2)));
                    } else {
                        Log.w(TAG, "PARAMETER_MASK: Wrong length received: " + tagLength + " expected: 4");
                    }
                    break;
                case 17:
                    if (tagLength != 2) {
                        Log.w(TAG, "FOLDER_LISTING_SIZE: Wrong length received: " + tagLength + " expected: 2");
                    } else {
                        setFolderListingSize(appParamBuf.getShort(i2) & 65535);
                    }
                    break;
                case 18:
                    if (tagLength != 2) {
                        Log.w(TAG, "MESSAGE_LISTING_SIZE: Wrong length received: " + tagLength + " expected: 2");
                    } else {
                        setMessageListingSize(appParamBuf.getShort(i2) & 65535);
                    }
                    break;
                case 19:
                    if (tagLength != 1) {
                        Log.w(TAG, "SUBJECT_LENGTH: Wrong length received: " + tagLength + " expected: 1");
                    } else {
                        setSubjectLength(appParams[i2] & UnsignedBytes.MAX_VALUE);
                    }
                    break;
                case 20:
                    if (tagLength != 1) {
                        Log.w(TAG, "CHARSET: Wrong length received: " + tagLength + " expected: 1");
                    } else {
                        setCharset(appParams[i2] & 1);
                    }
                    break;
                case 21:
                    if (tagLength != 1) {
                        Log.w(TAG, "FRACTION_REQUEST: Wrong length received: " + tagLength + " expected: 1");
                    } else {
                        setFractionRequest(appParams[i2] & 1);
                    }
                    break;
                case 22:
                    if (tagLength != 1) {
                        Log.w(TAG, "FRACTION_DELIVER: Wrong length received: " + tagLength + " expected: 1");
                    } else {
                        setFractionDeliver(appParams[i2] & 1);
                    }
                    break;
                case 23:
                    if (tagLength != 1) {
                        Log.w(TAG, "STATUS_INDICATOR: Wrong length received: " + tagLength + " expected: 1");
                    } else {
                        setStatusIndicator(appParams[i2] & 1);
                    }
                    break;
                case 24:
                    if (tagLength != 1) {
                        Log.w(TAG, "STATUS_VALUER: Wrong length received: " + tagLength + " expected: 1");
                    } else {
                        setStatusValue(appParams[i2] & 1);
                    }
                    break;
                case 25:
                    setMseTime(new String(appParams, i2, tagLength));
                    break;
                case 26:
                    if (tagLength != 16) {
                        Log.w(TAG, "DATABASE_IDENTIFIER: Wrong length received: " + tagLength + " expected: 16");
                    } else {
                        setDatabaseIdentifier(appParamBuf.getLong(i2), appParamBuf.getLong(i2 + 8));
                    }
                    break;
                case 27:
                    if (tagLength != 16) {
                        Log.w(TAG, "CONVO_LIST_VER_COUNTER: Wrong length received: " + tagLength + " expected: 16");
                    } else {
                        setConvoListingVerCounter(appParamBuf.getLong(i2), appParamBuf.getLong(i2 + 8));
                    }
                    break;
                case 28:
                    if (tagLength != 1) {
                        Log.w(TAG, "PRESENCE_AVAILABLE: Wrong length received: " + tagLength + " expected: 1");
                    } else {
                        setPresenceAvailability(appParams[i2]);
                    }
                    break;
                case 29:
                    if (tagLength != 0) {
                        setPresenceStatus(new String(appParams, i2, tagLength));
                    } else {
                        Log.w(TAG, "PRESENCE_STATUS: Wrong length received: " + tagLength + " expected to be more than 0");
                    }
                    break;
                case 30:
                    if (tagLength != 0) {
                        setLastActivity(new String(appParams, i2, tagLength));
                    } else {
                        Log.w(TAG, "LAST_ACTIVITY: Wrong length received: " + tagLength + " expected to be more than 0");
                    }
                    break;
                default:
                    switch (tagId2) {
                        case 33:
                            if (tagLength != 1) {
                                Log.w(TAG, "CHAT_STATE: Wrong length received: " + tagLength + " expected: 1");
                            } else {
                                setChatState(appParams[i2]);
                            }
                            break;
                        case 34:
                            if (tagLength != 0 && tagLength <= 32) {
                                setFilterConvoId(new String(appParams, i2, tagLength));
                            } else {
                                Log.w(TAG, "FILTER_CONVO_ID: Wrong length received: " + tagLength + " expected: 32");
                            }
                            break;
                        case 35:
                            break;
                        case 36:
                            if (tagLength != 0 && tagLength <= 8) {
                                setFilterMsgHandle(new String(appParams, i2, tagLength));
                            } else {
                                Log.w(TAG, "FILTER_MESSAGE_HANDLE: Wrong length received: " + tagLength + " expected: 8");
                            }
                            break;
                        case 37:
                            if (tagLength == 4) {
                                setNotificationFilter(4294967295L & ((long) appParamBuf.getInt(i2)));
                            } else {
                                Log.w(TAG, "NOTIFICATION_FILTER: Wrong length received: " + tagLength + " expected: 4");
                            }
                            break;
                        case 38:
                            if (tagLength != 4) {
                                Log.w(TAG, "CONVO_PARAMETER_MASK: Wrong length received: " + tagLength + " expected: 4");
                            } else {
                                setConvoParameterMask(4294967295L & ((long) appParamBuf.getInt(i2)));
                            }
                            break;
                        default:
                            switch (tagId2) {
                                case 54:
                                    if (tagLength != 2) {
                                        Log.w(TAG, "LISTING_SIZE: Wrong length received: " + tagLength + " expected: 2");
                                    } else {
                                        setConvoListingSize(appParamBuf.getShort(i2) & 65535);
                                    }
                                    break;
                                case 55:
                                    if (tagLength != 1) {
                                        Log.w(TAG, "FILTER_PRESENCE: Wrong length received: " + tagLength + " expected: 1");
                                    } else {
                                        setFilterPresence(appParams[i2]);
                                    }
                                    break;
                                case 56:
                                    if (tagLength != 1) {
                                        Log.w(TAG, "FILTER_UID_PRESENT: Wrong length received: " + tagLength + " expected: 1");
                                    } else {
                                        setFilterUidPresent(appParams[i2] & 1);
                                    }
                                    break;
                                case 57:
                                    if (tagLength != 16) {
                                        Log.w(TAG, "CHAT_STATE_CONVO_ID: Wrong length received: " + tagLength + " expected: 16");
                                    } else {
                                        setChatStateConvoId(appParamBuf.getLong(i2), appParamBuf.getLong(i2 + 8));
                                        Log.d(TAG, "CHAT_STATE_CONVO_ID: convo id MSB=" + BluetoothMapUtils.getLongAsString(appParamBuf.getLong(i2)) + ", LSB(+8)=" + BluetoothMapUtils.getLongAsString(appParamBuf.getLong(i2 + 8)));
                                    }
                                    break;
                                default:
                                    Log.w(TAG, "Unknown TagId received ( 0x" + Integer.toString(tagId2, 16) + "), skipping...");
                                    break;
                            }
                            break;
                    }
                    break;
            }
            tagId = i2 + tagLength;
        }
    }

    private int getParamMaxLength() throws UnsupportedEncodingException {
        int length = 0 + 76 + 97 + (getFilterPeriodBegin() == -1 ? 0 : 20) + (getFilterPeriodEnd() == -1 ? 0 : 20);
        if (getFilterRecipient() != null) {
            length += getFilterRecipient().getBytes("UTF-8").length;
        }
        if (getFilterOriginator() != null) {
            length += getFilterOriginator().getBytes("UTF-8").length;
        }
        int length2 = length + (getMseTime() == -1 ? 0 : 20);
        if (getPresenceStatus() != null) {
            length2 += getPresenceStatus().getBytes("UTF-8").length;
        }
        return length2 + (getLastActivity() != -1 ? 20 : 0);
    }

    public byte[] encodeParams() throws UnsupportedEncodingException {
        ByteBuffer appParamBuf = ByteBuffer.allocate(getParamMaxLength());
        appParamBuf.order(ByteOrder.BIG_ENDIAN);
        if (getMaxListCount() != -1) {
            appParamBuf.put((byte) 1);
            appParamBuf.put((byte) 2);
            appParamBuf.putShort((short) getMaxListCount());
        }
        if (getStartOffset() != -1) {
            appParamBuf.put((byte) 2);
            appParamBuf.put((byte) 2);
            appParamBuf.putShort((short) getStartOffset());
        }
        if (getFilterMessageType() != -1) {
            appParamBuf.put((byte) 3);
            appParamBuf.put((byte) 1);
            appParamBuf.put((byte) getFilterMessageType());
        }
        if (getFilterPeriodBegin() != -1) {
            appParamBuf.put((byte) 4);
            appParamBuf.put((byte) getFilterPeriodBeginString().getBytes("UTF-8").length);
            appParamBuf.put(getFilterPeriodBeginString().getBytes("UTF-8"));
        }
        if (getFilterPeriodEnd() != -1) {
            appParamBuf.put((byte) 5);
            appParamBuf.put((byte) getFilterPeriodEndString().getBytes("UTF-8").length);
            appParamBuf.put(getFilterPeriodEndString().getBytes("UTF-8"));
        }
        if (getFilterReadStatus() != -1) {
            appParamBuf.put((byte) 6);
            appParamBuf.put((byte) 1);
            appParamBuf.put((byte) getFilterReadStatus());
        }
        if (getFilterRecipient() != null) {
            appParamBuf.put((byte) 7);
            appParamBuf.put((byte) getFilterRecipient().getBytes("UTF-8").length);
            appParamBuf.put(getFilterRecipient().getBytes("UTF-8"));
        }
        if (getFilterOriginator() != null) {
            appParamBuf.put((byte) 8);
            appParamBuf.put((byte) getFilterOriginator().getBytes("UTF-8").length);
            appParamBuf.put(getFilterOriginator().getBytes("UTF-8"));
        }
        if (getFilterPriority() != -1) {
            appParamBuf.put((byte) 9);
            appParamBuf.put((byte) 1);
            appParamBuf.put((byte) getFilterPriority());
        }
        if (getAttachment() != -1) {
            appParamBuf.put((byte) 10);
            appParamBuf.put((byte) 1);
            appParamBuf.put((byte) getAttachment());
        }
        if (getTransparent() != -1) {
            appParamBuf.put(Ascii.VT);
            appParamBuf.put((byte) 1);
            appParamBuf.put((byte) getTransparent());
        }
        if (getRetry() != -1) {
            appParamBuf.put(Ascii.FF);
            appParamBuf.put((byte) 1);
            appParamBuf.put((byte) getRetry());
        }
        if (getNewMessage() != -1) {
            appParamBuf.put(Ascii.CR);
            appParamBuf.put((byte) 1);
            appParamBuf.put((byte) getNewMessage());
        }
        if (getNotificationStatus() != -1) {
            appParamBuf.put(Ascii.SO);
            appParamBuf.put((byte) 1);
            appParamBuf.putShort((short) getNotificationStatus());
        }
        if (getNotificationFilter() != -1) {
            appParamBuf.put((byte) 37);
            appParamBuf.put((byte) 4);
            appParamBuf.putInt((int) getNotificationFilter());
        }
        if (getMasInstanceId() != -1) {
            appParamBuf.put(Ascii.SI);
            appParamBuf.put((byte) 1);
            appParamBuf.put((byte) getMasInstanceId());
        }
        if (getParameterMask() != -1) {
            appParamBuf.put(Ascii.DLE);
            appParamBuf.put((byte) 4);
            appParamBuf.putInt((int) getParameterMask());
        }
        if (getFolderListingSize() != -1) {
            appParamBuf.put((byte) 17);
            appParamBuf.put((byte) 2);
            appParamBuf.putShort((short) getFolderListingSize());
        }
        if (getMessageListingSize() != -1) {
            appParamBuf.put(Ascii.DC2);
            appParamBuf.put((byte) 2);
            appParamBuf.putShort((short) getMessageListingSize());
        }
        if (getSubjectLength() != -1) {
            appParamBuf.put((byte) 19);
            appParamBuf.put((byte) 1);
            appParamBuf.put((byte) getSubjectLength());
        }
        if (getCharset() != -1) {
            appParamBuf.put(Ascii.DC4);
            appParamBuf.put((byte) 1);
            appParamBuf.put((byte) getCharset());
        }
        if (getFractionRequest() != -1) {
            appParamBuf.put(Ascii.NAK);
            appParamBuf.put((byte) 1);
            appParamBuf.put((byte) getFractionRequest());
        }
        if (getFractionDeliver() != -1) {
            appParamBuf.put(Ascii.SYN);
            appParamBuf.put((byte) 1);
            appParamBuf.put((byte) getFractionDeliver());
        }
        if (getStatusIndicator() != -1) {
            appParamBuf.put(Ascii.ETB);
            appParamBuf.put((byte) 1);
            appParamBuf.put((byte) getStatusIndicator());
        }
        if (getStatusValue() != -1) {
            appParamBuf.put(Ascii.CAN);
            appParamBuf.put((byte) 1);
            appParamBuf.put((byte) getStatusValue());
        }
        if (getMseTime() != -1) {
            appParamBuf.put(Ascii.EM);
            appParamBuf.put((byte) getMseTimeString().getBytes("UTF-8").length);
            appParamBuf.put(getMseTimeString().getBytes("UTF-8"));
        }
        if (getDatabaseIdentifier() != null) {
            appParamBuf.put(Ascii.SUB);
            appParamBuf.put(Ascii.DLE);
            appParamBuf.put(getDatabaseIdentifier());
        }
        if (getConvoListingVerCounter() != null) {
            appParamBuf.put((byte) 27);
            appParamBuf.put(Ascii.DLE);
            appParamBuf.put(getConvoListingVerCounter());
        }
        if (getPresenceAvailability() != -1) {
            appParamBuf.put(Ascii.FS);
            appParamBuf.put((byte) 1);
            appParamBuf.putInt(getPresenceAvailability());
        }
        if (getPresenceStatus() != null) {
            appParamBuf.put(Ascii.GS);
            appParamBuf.put((byte) getPresenceStatus().getBytes("UTF-8").length);
            appParamBuf.put(getPresenceStatus().getBytes());
        }
        if (getLastActivity() != -1) {
            appParamBuf.put(Ascii.RS);
            appParamBuf.put((byte) getLastActivityString().getBytes("UTF-8").length);
            appParamBuf.put(getLastActivityString().getBytes());
        }
        if (getChatState() != -1) {
            appParamBuf.put((byte) 33);
            appParamBuf.put((byte) 1);
            appParamBuf.putShort((short) getChatState());
        }
        if (getFilterConvoId() != null) {
            appParamBuf.put((byte) 34);
            appParamBuf.put((byte) 32);
            appParamBuf.putLong(getFilterConvoId().getMostSignificantBits());
            appParamBuf.putLong(getFilterConvoId().getLeastSignificantBits());
        }
        if (getConvoListingSize() != -1) {
            appParamBuf.put((byte) 54);
            appParamBuf.put((byte) 2);
            appParamBuf.putShort((short) getConvoListingSize());
        }
        if (getFilterPresence() != -1) {
            appParamBuf.put((byte) 55);
            appParamBuf.put((byte) 1);
            appParamBuf.putShort((short) getFilterPresence());
        }
        if (getFilterUidPresent() != -1) {
            appParamBuf.put((byte) 56);
            appParamBuf.put((byte) 1);
            appParamBuf.putShort((short) getFilterUidPresent());
        }
        if (getChatStateConvoId() != null) {
            appParamBuf.put((byte) 57);
            appParamBuf.put(Ascii.DLE);
            appParamBuf.putLong(getChatStateConvoId().getMostSignificantBits());
            appParamBuf.putLong(getChatStateConvoId().getLeastSignificantBits());
        }
        if (getFolderVerCounter() != null) {
            appParamBuf.put((byte) 35);
            appParamBuf.put((byte) 32);
            appParamBuf.put(getFolderVerCounter());
        }
        if (getFilterMsgHandle() != -1) {
            appParamBuf.put((byte) 36);
            appParamBuf.put((byte) 8);
            appParamBuf.putLong(getFilterMsgHandle());
        }
        if (getConvoParameterMask() != -1) {
            appParamBuf.put((byte) 38);
            appParamBuf.put((byte) 4);
            appParamBuf.putInt((int) getConvoParameterMask());
        }
        byte[] retBuf = Arrays.copyOfRange(appParamBuf.array(), appParamBuf.arrayOffset(), appParamBuf.arrayOffset() + appParamBuf.position());
        return retBuf;
    }

    public int getMaxListCount() {
        return this.mMaxListCount;
    }

    public void setMaxListCount(int maxListCount) throws IllegalArgumentException {
        if (maxListCount < 0 || maxListCount > 65535) {
            throw new IllegalArgumentException("Out of range, valid range is 0x0000 to 0xFFFF");
        }
        this.mMaxListCount = maxListCount;
    }

    public int getStartOffset() {
        return this.mStartOffset;
    }

    public void setStartOffset(int startOffset) throws IllegalArgumentException {
        if (startOffset < 0 || startOffset > 65535) {
            throw new IllegalArgumentException("Out of range, valid range is 0x0000 to 0xFFFF");
        }
        this.mStartOffset = startOffset;
    }

    public int getFilterMessageType() {
        return this.mFilterMessageType;
    }

    public void setFilterMessageType(int filterMessageType) throws IllegalArgumentException {
        if (filterMessageType < 0 || filterMessageType > 31) {
            throw new IllegalArgumentException("Out of range, valid range is 0x0000 to 0x001F");
        }
        this.mFilterMessageType = filterMessageType;
    }

    public long getFilterPeriodBegin() {
        return this.mFilterPeriodBegin;
    }

    public String getFilterPeriodBeginString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        Date date = new Date(this.mFilterPeriodBegin);
        return format.format(date);
    }

    public void setFilterPeriodBegin(long filterPeriodBegin) {
        this.mFilterPeriodBegin = filterPeriodBegin;
    }

    public void setFilterPeriodBegin(String filterPeriodBegin) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        Date date = format.parse(filterPeriodBegin);
        this.mFilterPeriodBegin = date.getTime();
    }

    public long getFilterLastActivityBegin() {
        return this.mFilterPeriodBegin;
    }

    public String getFilterLastActivityBeginString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        Date date = new Date(this.mFilterPeriodBegin);
        return format.format(date);
    }

    public void setFilterLastActivityBegin(long filterPeriodBegin) {
        this.mFilterPeriodBegin = filterPeriodBegin;
    }

    public void setFilterLastActivityBegin(String filterPeriodBegin) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        Date date = format.parse(filterPeriodBegin);
        this.mFilterPeriodBegin = date.getTime();
    }

    public long getFilterPeriodEnd() {
        return this.mFilterPeriodEnd;
    }

    public long getFilterLastActivityEnd() {
        return this.mFilterPeriodEnd;
    }

    public String getFilterLastActivityEndString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        Date date = new Date(this.mFilterPeriodEnd);
        return format.format(date);
    }

    public void setFilterLastActivityEnd(long filterPeriodEnd) {
        this.mFilterPeriodEnd = filterPeriodEnd;
    }

    public void setFilterPeriodEnd(String filterPeriodEnd) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        Date date = format.parse(filterPeriodEnd);
        this.mFilterPeriodEnd = date.getTime();
    }

    public String getFilterPeriodEndString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        Date date = new Date(this.mFilterPeriodEnd);
        return format.format(date);
    }

    public void setFilterPeriodEnd(long filterPeriodEnd) {
        this.mFilterPeriodEnd = filterPeriodEnd;
    }

    public void setFilterLastActivityEnd(String filterPeriodEnd) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        Date date = format.parse(filterPeriodEnd);
        this.mFilterPeriodEnd = date.getTime();
    }

    public int getFilterReadStatus() {
        return this.mFilterReadStatus;
    }

    public void setFilterReadStatus(int filterReadStatus) throws IllegalArgumentException {
        if (filterReadStatus < 0 || filterReadStatus > 2) {
            throw new IllegalArgumentException("Out of range, valid range is 0x0000 to 0x0002");
        }
        this.mFilterReadStatus = filterReadStatus;
    }

    public String getFilterRecipient() {
        return this.mFilterRecipient;
    }

    public void setFilterRecipient(String filterRecipient) {
        this.mFilterRecipient = filterRecipient;
    }

    public String getFilterOriginator() {
        return this.mFilterOriginator;
    }

    public void setFilterOriginator(String filterOriginator) {
        this.mFilterOriginator = filterOriginator;
    }

    public int getFilterPriority() {
        return this.mFilterPriority;
    }

    public void setFilterPriority(int filterPriority) throws IllegalArgumentException {
        if (filterPriority < 0 || filterPriority > 2) {
            throw new IllegalArgumentException("Out of range, valid range is 0x0000 to 0x0002");
        }
        this.mFilterPriority = filterPriority;
    }

    public void setDatabaseIdentifier(long idHigh, long idLow) {
        this.mDatabaseIdentifierHigh = idHigh;
        this.mDatabaseIdentifierLow = idLow;
    }

    public byte[] getDatabaseIdentifier() {
        if (this.mDatabaseIdentifierLow != -1 && this.mDatabaseIdentifierHigh != -1) {
            ByteBuffer ret = ByteBuffer.allocate(16);
            ret.putLong(this.mDatabaseIdentifierHigh);
            ret.putLong(this.mDatabaseIdentifierLow);
            return ret.array();
        }
        return null;
    }

    public void setConvoListingVerCounter(long countLow, long countHigh) {
        this.mConvoListingVerCounterHigh = countHigh;
        this.mConvoListingVerCounterLow = countLow;
    }

    public byte[] getConvoListingVerCounter() {
        if (this.mConvoListingVerCounterHigh != -1 && this.mConvoListingVerCounterLow != -1) {
            ByteBuffer ret = ByteBuffer.allocate(16);
            ret.putLong(this.mConvoListingVerCounterHigh);
            ret.putLong(this.mConvoListingVerCounterLow);
            return ret.array();
        }
        return null;
    }

    public void setFolderVerCounter(long countLow, long countHigh) {
        this.mFolderVerCounterHigh = countHigh;
        this.mFolderVerCounterLow = countLow;
    }

    public byte[] getFolderVerCounter() {
        if (this.mFolderVerCounterHigh != -1 && this.mFolderVerCounterLow != -1) {
            ByteBuffer ret = ByteBuffer.allocate(16);
            ret.putLong(this.mFolderVerCounterHigh);
            ret.putLong(this.mFolderVerCounterLow);
            return ret.array();
        }
        return null;
    }

    public SignedLongLong getChatStateConvoId() {
        return this.mChatStateConvoId;
    }

    public byte[] getChatStateConvoIdByteArray() {
        if (this.mChatStateConvoId != null) {
            ByteBuffer ret = ByteBuffer.allocate(16);
            ret.putLong(this.mChatStateConvoId.getMostSignificantBits());
            ret.putLong(this.mChatStateConvoId.getLeastSignificantBits());
            return ret.array();
        }
        return null;
    }

    public String getChatStateConvoIdString() {
        String str = new String(getChatStateConvoIdByteArray());
        return str;
    }

    public void setChatStateConvoId(long idHigh, long idLow) {
        this.mChatStateConvoId = new SignedLongLong(idLow, idHigh);
    }

    public void setFilterMsgHandle(String handle) {
        try {
            this.mFilterMsgHandle = BluetoothMapUtils.getLongFromString(handle);
        } catch (UnsupportedEncodingException | NumberFormatException e) {
            Log.w(TAG, "Error creating long from handle string", e);
        }
    }

    public long getFilterMsgHandle() {
        return this.mFilterMsgHandle;
    }

    public String getFilterMsgHandleString() {
        long j = this.mFilterMsgHandle;
        if (j == -1) {
            return null;
        }
        String str = BluetoothMapUtils.getLongAsString(j);
        return str;
    }

    public int getFilterUidPresent() {
        return this.mFilterUidPresent;
    }

    public void setFilterUidPresent(int present) {
        if (present < 0 || present > 255) {
            throw new IllegalArgumentException("Out of range, valid range is 0x0000 to 0x00FF");
        }
        this.mFilterUidPresent = present;
    }

    public int getFilterPresence() {
        return this.mFilterPresence;
    }

    public SignedLongLong getFilterConvoId() {
        return this.mFilterConvoId;
    }

    public String getFilterConvoIdString() {
        SignedLongLong signedLongLong = this.mFilterConvoId;
        if (signedLongLong == null) {
            return null;
        }
        String str = BluetoothMapUtils.getLongAsString(signedLongLong.getLeastSignificantBits());
        return str;
    }

    public void setFilterConvoId(String id) {
        try {
            this.mFilterConvoId = SignedLongLong.fromString(id);
        } catch (UnsupportedEncodingException | NumberFormatException e) {
            Log.w(TAG, "Error creating long from id string", e);
        }
    }

    public void setChatState(int state) {
        if (state < 0 || state > 255) {
            throw new IllegalArgumentException("Out of range, valid range is 0x0000 to 0x00FF");
        }
        this.mChatState = state;
    }

    public int getChatState() {
        return this.mChatState;
    }

    public long getLastActivity() {
        return this.mLastActivity;
    }

    public String getLastActivityString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd'T'HHmmssZ");
        Date date = new Date(this.mLastActivity);
        return format.format(date);
    }

    public void setLastActivity(long last) {
        this.mLastActivity = last;
    }

    public void setLastActivity(String lastActivity) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd'T'HHmmssZ");
        Date date = format.parse(lastActivity);
        this.mLastActivity = date.getTime();
    }

    public void setPresenceStatus(String status) {
        this.mPresenceStatus = status;
    }

    public String getPresenceStatus() {
        return this.mPresenceStatus;
    }

    public void setFilterPresence(int presence) {
        if (presence < 0 || presence > 65535) {
            throw new IllegalArgumentException("Out of range, valid range is 0x0000 to 0xFFFF");
        }
        this.mFilterPresence = presence;
    }

    public void setPresenceAvailability(int availability) {
        if (availability < 0 || availability > 255) {
            throw new IllegalArgumentException("Out of range, valid range is 0x0000 to 0x00FF");
        }
        this.mPresenceAvailability = availability;
    }

    public int getPresenceAvailability() {
        return this.mPresenceAvailability;
    }

    public int getSubjectLength() {
        return this.mSubjectLength;
    }

    public int getAttachment() {
        return this.mAttachment;
    }

    public void setAttachment(int attachment) throws IllegalArgumentException {
        if (attachment < 0 || attachment > 1) {
            throw new IllegalArgumentException("Out of range, valid range is 0x0000 to 0x0001");
        }
        this.mAttachment = attachment;
    }

    public int getTransparent() {
        return this.mTransparent;
    }

    public void setTransparent(int transparent) throws IllegalArgumentException {
        if (transparent < 0 || transparent > 1) {
            throw new IllegalArgumentException("Out of range, valid range is 0x0000 to 0x0001");
        }
        this.mTransparent = transparent;
    }

    public int getRetry() {
        return this.mRetry;
    }

    public void setRetry(int retry) throws IllegalArgumentException {
        if (retry < 0 || retry > 1) {
            throw new IllegalArgumentException("Out of range, valid range is 0x0000 to 0x0001");
        }
        this.mRetry = retry;
    }

    public int getNewMessage() {
        return this.mNewMessage;
    }

    public void setNewMessage(int newMessage) throws IllegalArgumentException {
        if (newMessage < 0 || newMessage > 1) {
            throw new IllegalArgumentException("Out of range, valid range is 0x0000 to 0x0001");
        }
        this.mNewMessage = newMessage;
    }

    public int getNotificationStatus() {
        return this.mNotificationStatus;
    }

    public void setNotificationStatus(int notificationStatus) throws IllegalArgumentException {
        if (notificationStatus < 0 || notificationStatus > 1) {
            throw new IllegalArgumentException("Out of range, valid range is 0x0000 to 0x0001");
        }
        this.mNotificationStatus = notificationStatus;
    }

    public long getNotificationFilter() {
        return this.mNotificationFilter;
    }

    public void setNotificationFilter(long notificationFilter) throws IllegalArgumentException {
        if (notificationFilter < 0 || notificationFilter > 4294967295L) {
            throw new IllegalArgumentException("Out of range, valid range is 0x0000 to 0xFFFFFFFFL");
        }
        this.mNotificationFilter = notificationFilter;
    }

    public int getMasInstanceId() {
        return this.mMasInstanceId;
    }

    public void setMasInstanceId(int masInstanceId) {
        if (masInstanceId < 0 || masInstanceId > 255) {
            throw new IllegalArgumentException("Out of range, valid range is 0x0000 to 0x00FF");
        }
        this.mMasInstanceId = masInstanceId;
    }

    public long getParameterMask() {
        return this.mParameterMask;
    }

    public void setParameterMask(long parameterMask) {
        if (parameterMask < 0 || parameterMask > 4294967295L) {
            throw new IllegalArgumentException("Out of range, valid range is 0x0000 to 0xFFFFFFFF");
        }
        this.mParameterMask = parameterMask;
    }

    public void setConvoParameterMask(long parameterMask) {
        if (parameterMask < 0 || parameterMask > 4294967295L) {
            throw new IllegalArgumentException("Out of range, valid range is 0x0000 to 0xFFFFFFFF");
        }
        this.mConvoParameterMask = parameterMask;
    }

    public long getConvoParameterMask() {
        return this.mConvoParameterMask;
    }

    public int getFolderListingSize() {
        return this.mFolderListingSize;
    }

    public void setFolderListingSize(int folderListingSize) {
        if (folderListingSize < 0 || folderListingSize > 65535) {
            throw new IllegalArgumentException("Out of range, valid range is 0x0000 to 0xFFFF");
        }
        this.mFolderListingSize = folderListingSize;
    }

    public int getMessageListingSize() {
        return this.mMessageListingSize;
    }

    public void setMessageListingSize(int messageListingSize) {
        if (messageListingSize < 0 || messageListingSize > 65535) {
            throw new IllegalArgumentException("Out of range, valid range is 0x0000 to 0xFFFF");
        }
        this.mMessageListingSize = messageListingSize;
    }

    public int getConvoListingSize() {
        return this.mConvoListingSize;
    }

    public void setConvoListingSize(int convoListingSize) {
        if (convoListingSize < 0 || convoListingSize > 65535) {
            throw new IllegalArgumentException("Out of range, valid range is 0x0000 to 0xFFFF");
        }
        this.mConvoListingSize = convoListingSize;
    }

    public void setSubjectLength(int subjectLength) {
        if (subjectLength < 0 || subjectLength > 255) {
            throw new IllegalArgumentException("Out of range, valid range is 0x0000 to 0x00FF");
        }
        this.mSubjectLength = subjectLength;
    }

    public int getCharset() {
        return this.mCharset;
    }

    public void setCharset(int charset) {
        if (charset < 0 || charset > 1) {
            throw new IllegalArgumentException("Out of range: " + charset + ", valid range is 0x0000 to 0x0001");
        }
        this.mCharset = charset;
    }

    public int getFractionRequest() {
        return this.mFractionRequest;
    }

    public void setFractionRequest(int fractionRequest) {
        if (fractionRequest < 0 || fractionRequest > 1) {
            throw new IllegalArgumentException("Out of range, valid range is 0x0000 to 0x0001");
        }
        this.mFractionRequest = fractionRequest;
    }

    public int getFractionDeliver() {
        return this.mFractionDeliver;
    }

    public void setFractionDeliver(int fractionDeliver) {
        if (fractionDeliver < 0 || fractionDeliver > 1) {
            throw new IllegalArgumentException("Out of range, valid range is 0x0000 to 0x0001");
        }
        this.mFractionDeliver = fractionDeliver;
    }

    public int getStatusIndicator() {
        return this.mStatusIndicator;
    }

    public void setStatusIndicator(int statusIndicator) {
        if (statusIndicator < 0 || statusIndicator > 1) {
            throw new IllegalArgumentException("Out of range, valid range is 0x0000 to 0x0001");
        }
        this.mStatusIndicator = statusIndicator;
    }

    public int getStatusValue() {
        return this.mStatusValue;
    }

    public void setStatusValue(int statusValue) {
        if (statusValue < 0 || statusValue > 1) {
            throw new IllegalArgumentException("Out of range, valid range is 0x0000 to 0x0001");
        }
        this.mStatusValue = statusValue;
    }

    public long getMseTime() {
        return this.mMseTime;
    }

    public String getMseTimeString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd'T'HHmmssZ");
        Date date = new Date(getMseTime());
        return format.format(date);
    }

    public void setMseTime(long mseTime) {
        this.mMseTime = mseTime;
    }

    public void setMseTime(String mseTime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd'T'HHmmssZ");
        Date date = format.parse(mseTime);
        this.mMseTime = date.getTime();
    }
}
