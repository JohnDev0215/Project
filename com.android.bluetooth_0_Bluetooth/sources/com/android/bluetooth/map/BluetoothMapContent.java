package com.android.bluetooth.map;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.INetd;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.support.v4.media.session.PlaybackStateCompat;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.util.Rfc822Token;
import android.text.util.Rfc822Tokenizer;
import android.util.Log;
import androidx.room.FtsOptions;
import com.android.bluetooth.DeviceWorkArounds;
import com.android.bluetooth.SignedLongLong;
import com.android.bluetooth.map.BluetoothMapUtils;
import com.android.bluetooth.mapapi.BluetoothMapContract;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothMapContent {
    public static final long CONVO_PARAMETER_MASK_ALL_ENABLED = 4294967295L;
    public static final long CONVO_PARAMETER_MASK_DEFAULT = 225;
    private static final int CONVO_PARAM_MASK_CONVO_LAST_ACTIVITY = 2;
    private static final int CONVO_PARAM_MASK_CONVO_NAME = 1;
    private static final int CONVO_PARAM_MASK_CONVO_READ_STATUS = 4;
    private static final int CONVO_PARAM_MASK_CONVO_SUMMARY = 16;
    private static final int CONVO_PARAM_MASK_CONVO_VERSION_COUNTER = 8;
    private static final int CONVO_PARAM_MASK_PARTTICIPANTS = 32;
    private static final int CONVO_PARAM_MASK_PART_CHAT_STATE = 256;
    private static final int CONVO_PARAM_MASK_PART_DISP_NAME = 128;
    private static final int CONVO_PARAM_MASK_PART_LAST_ACTIVITY = 512;
    private static final int CONVO_PARAM_MASK_PART_NAME = 2048;
    private static final int CONVO_PARAM_MASK_PART_PRESENCE = 4096;
    private static final int CONVO_PARAM_MASK_PART_PRESENCE_TEXT = 8192;
    private static final int CONVO_PARAM_MASK_PART_PRIORITY = 16384;
    private static final int CONVO_PARAM_MASK_PART_UCI = 64;
    private static final int CONVO_PARAM_MASK_PART_X_BT_UID = 1024;
    private static final String[] CONVO_VERSION_PROJECTION;
    private static final boolean D = false;
    private static final int FILTER_READ_STATUS_ALL = 0;
    private static final int FILTER_READ_STATUS_READ_ONLY = 2;
    private static final int FILTER_READ_STATUS_UNREAD_ONLY = 1;
    public static final String INSERT_ADDRES_TOKEN = "insert-address-token";
    public static final int MAP_MESSAGE_CHARSET_NATIVE = 0;
    public static final int MAP_MESSAGE_CHARSET_UTF8 = 1;
    private static final int MASK_ATTACHMENT_MIME = 1048576;
    private static final int MASK_ATTACHMENT_SIZE = 1024;
    private static final int MASK_CONVERSATION_ID = 131072;
    private static final int MASK_CONVERSATION_NAME = 262144;
    private static final int MASK_DATETIME = 2;
    private static final int MASK_DELIVERY_STATUS = 65536;
    private static final int MASK_FOLDER_TYPE = 1048576;
    private static final int MASK_PRIORITY = 2048;
    private static final int MASK_PROTECTED = 16384;
    private static final int MASK_READ = 4096;
    private static final int MASK_RECEPTION_STATUS = 256;
    private static final int MASK_RECIPIENT_ADDRESSING = 32;
    private static final int MASK_RECIPIENT_NAME = 16;
    private static final int MASK_REPLYTO_ADDRESSING = 32768;
    private static final int MASK_SENDER_ADDRESSING = 8;
    private static final int MASK_SENDER_NAME = 4;
    private static final int MASK_SENT = 8192;
    private static final int MASK_SIZE = 128;
    private static final int MASK_SUBJECT = 1;
    private static final int MASK_TEXT = 512;
    private static final int MASK_TYPE = 64;
    public static final int MMS_BCC = 129;
    public static final int MMS_FROM = 137;
    private static final int MMS_SMS_THREAD_COL_DATE;
    private static final int MMS_SMS_THREAD_COL_ID;
    private static final int MMS_SMS_THREAD_COL_READ;
    private static final int MMS_SMS_THREAD_COL_RECIPIENT_IDS;
    private static final int MMS_SMS_THREAD_COL_SNIPPET;
    private static final int MMS_SMS_THREAD_COL_SNIPPET_CS;
    private static final String[] MMS_SMS_THREAD_PROJECTION;
    public static final int MMS_TO = 151;
    public static final long PARAMETER_MASK_ALL_ENABLED = 4294967295L;
    private static final String[] RECIPIENT_ID_PROJECTION;
    private static final String TAG = "BluetoothMapContent";
    private static final boolean V = false;
    private final BluetoothMapAccountItem mAccount;
    private final String mBaseUri;
    private final Context mContext;
    private final BluetoothMapMasInstance mMasInstance;
    private final ContentResolver mResolver;
    public static final int MMS_CC = 130;
    private static final String INTERESTED_MESSAGE_TYPE_CLAUSE = String.format("( %s = %d OR %s = %d OR %s = %d )", "m_type", 128, "m_type", Integer.valueOf(BluetoothMapContentObserver.MESSAGE_TYPE_RETRIEVE_CONF), "m_type", Integer.valueOf(MMS_CC));
    static final String[] SMS_PROJECTION = {"_id", "thread_id", "address", BluetoothMapContract.MessageColumns.BODY, BluetoothMapContract.MessageColumns.DATE, BluetoothMapContract.FILTER_READ_STATUS, BluetoothMapContentObserver.EXTRA_MESSAGE_SENT_MSG_TYPE, "status", "locked", "error_code"};
    static final String[] MMS_PROJECTION = {"_id", "thread_id", "m_id", "m_size", "sub", "ct_t", "text_only", BluetoothMapContract.MessageColumns.DATE, "date_sent", BluetoothMapContract.FILTER_READ_STATUS, "msg_box", "st", "pri"};
    static final String[] SMS_CONVO_PROJECTION = {"_id", "thread_id", "address", BluetoothMapContract.MessageColumns.DATE, BluetoothMapContract.FILTER_READ_STATUS, BluetoothMapContentObserver.EXTRA_MESSAGE_SENT_MSG_TYPE, "status", "locked", "error_code"};
    static final String[] MMS_CONVO_PROJECTION = {"_id", "thread_id", "m_id", "m_size", "sub", "ct_t", "text_only", BluetoothMapContract.MessageColumns.DATE, "date_sent", BluetoothMapContract.FILTER_READ_STATUS, "msg_box", "st", "pri", "address"};
    private String mMessageVersion = "1.0";
    private int mRemoteFeatureMask = 31;
    private int mMsgListingVersion = 10;

    static {
        String[] strArr = {"_id", BluetoothMapContract.MessageColumns.DATE, "snippet", "snippet_cs", BluetoothMapContract.FILTER_READ_STATUS, "recipient_ids"};
        MMS_SMS_THREAD_PROJECTION = strArr;
        CONVO_VERSION_PROJECTION = new String[]{"thread_id", "thread_name", BluetoothMapContract.ConversationColumns.READ_STATUS, BluetoothMapContract.ConversationColumns.LAST_THREAD_ACTIVITY, BluetoothMapContract.ConversationColumns.SUMMARY};
        List<String> projection = Arrays.asList(strArr);
        MMS_SMS_THREAD_COL_ID = projection.indexOf("_id");
        MMS_SMS_THREAD_COL_DATE = projection.indexOf(BluetoothMapContract.MessageColumns.DATE);
        MMS_SMS_THREAD_COL_SNIPPET = projection.indexOf("snippet");
        MMS_SMS_THREAD_COL_SNIPPET_CS = projection.indexOf("snippet_cs");
        MMS_SMS_THREAD_COL_READ = projection.indexOf(BluetoothMapContract.FILTER_READ_STATUS);
        MMS_SMS_THREAD_COL_RECIPIENT_IDS = projection.indexOf("recipient_ids");
        RECIPIENT_ID_PROJECTION = new String[]{"recipient_ids"};
    }

    private class FilterInfo {
        public static final int TYPE_EMAIL = 2;
        public static final int TYPE_IM = 3;
        public static final int TYPE_MMS = 1;
        public static final int TYPE_SMS = 0;
        public int mContactColBtUid;
        public int mContactColChatState;
        public int mContactColContactUci;
        public int mContactColLastActive;
        public int mContactColName;
        public int mContactColNickname;
        public int mContactColPresenceState;
        public int mContactColPresenceText;
        public int mContactColPriority;
        public int mConvoColConvoId;
        public int mConvoColLastActivity;
        public int mConvoColName;
        public int mConvoColRead;
        public int mConvoColSummary;
        public int mConvoColVersionCounter;
        public int mMessageColAccountId;
        public int mMessageColAttachment;
        public int mMessageColAttachmentMime;
        public int mMessageColAttachmentSize;
        public int mMessageColBccAddress;
        public int mMessageColBody;
        public int mMessageColCcAddress;
        public int mMessageColDate;
        public int mMessageColDelivery;
        public int mMessageColFolder;
        public int mMessageColFromAddress;
        public int mMessageColId;
        public int mMessageColPriority;
        public int mMessageColProtected;
        public int mMessageColRead;
        public int mMessageColReception;
        public int mMessageColReplyTo;
        public int mMessageColSize;
        public int mMessageColSubject;
        public int mMessageColThreadId;
        public int mMessageColThreadName;
        public int mMessageColToAddress;
        public int mMmsColAttachmentSize;
        public int mMmsColDate;
        public int mMmsColFolder;
        public int mMmsColId;
        public int mMmsColRead;
        public int mMmsColSize;
        public int mMmsColSubject;
        public int mMmsColTextOnly;
        public int mMmsColThreadId;
        int mMsgType;
        String mPhoneAlphaTag;
        String mPhoneNum;
        int mPhoneType;
        public int mSmsColAddress;
        public int mSmsColDate;
        public int mSmsColFolder;
        public int mSmsColId;
        public int mSmsColRead;
        public int mSmsColSubject;
        public int mSmsColThreadId;
        public int mSmsColType;

        private FilterInfo() {
            this.mMsgType = 0;
            this.mPhoneType = 0;
            this.mPhoneNum = null;
            this.mPhoneAlphaTag = null;
            this.mMessageColId = -1;
            this.mMessageColDate = -1;
            this.mMessageColBody = -1;
            this.mMessageColSubject = -1;
            this.mMessageColFolder = -1;
            this.mMessageColRead = -1;
            this.mMessageColSize = -1;
            this.mMessageColFromAddress = -1;
            this.mMessageColToAddress = -1;
            this.mMessageColCcAddress = -1;
            this.mMessageColBccAddress = -1;
            this.mMessageColReplyTo = -1;
            this.mMessageColAccountId = -1;
            this.mMessageColAttachment = -1;
            this.mMessageColAttachmentSize = -1;
            this.mMessageColAttachmentMime = -1;
            this.mMessageColPriority = -1;
            this.mMessageColProtected = -1;
            this.mMessageColReception = -1;
            this.mMessageColDelivery = -1;
            this.mMessageColThreadId = -1;
            this.mMessageColThreadName = -1;
            this.mSmsColFolder = -1;
            this.mSmsColRead = -1;
            this.mSmsColId = -1;
            this.mSmsColSubject = -1;
            this.mSmsColAddress = -1;
            this.mSmsColDate = -1;
            this.mSmsColType = -1;
            this.mSmsColThreadId = -1;
            this.mMmsColRead = -1;
            this.mMmsColFolder = -1;
            this.mMmsColAttachmentSize = -1;
            this.mMmsColTextOnly = -1;
            this.mMmsColId = -1;
            this.mMmsColSize = -1;
            this.mMmsColDate = -1;
            this.mMmsColSubject = -1;
            this.mMmsColThreadId = -1;
            this.mConvoColConvoId = -1;
            this.mConvoColLastActivity = -1;
            this.mConvoColName = -1;
            this.mConvoColRead = -1;
            this.mConvoColVersionCounter = -1;
            this.mConvoColSummary = -1;
            this.mContactColBtUid = -1;
            this.mContactColChatState = -1;
            this.mContactColContactUci = -1;
            this.mContactColNickname = -1;
            this.mContactColLastActive = -1;
            this.mContactColName = -1;
            this.mContactColPresenceState = -1;
            this.mContactColPresenceText = -1;
            this.mContactColPriority = -1;
        }

        /* synthetic */ FilterInfo(BluetoothMapContent x0, AnonymousClass1 x1) {
            this();
        }

        public void setMessageColumns(Cursor c) {
            this.mMessageColId = c.getColumnIndex("_id");
            this.mMessageColDate = c.getColumnIndex(BluetoothMapContract.MessageColumns.DATE);
            this.mMessageColSubject = c.getColumnIndex(BluetoothMapContract.MessageColumns.SUBJECT);
            this.mMessageColFolder = c.getColumnIndex(BluetoothMapContract.MessageColumns.FOLDER_ID);
            this.mMessageColRead = c.getColumnIndex(BluetoothMapContract.MessageColumns.FLAG_READ);
            this.mMessageColSize = c.getColumnIndex(BluetoothMapContract.MessageColumns.MESSAGE_SIZE);
            this.mMessageColFromAddress = c.getColumnIndex(BluetoothMapContract.MessageColumns.FROM_LIST);
            this.mMessageColToAddress = c.getColumnIndex(BluetoothMapContract.MessageColumns.TO_LIST);
            this.mMessageColAttachment = c.getColumnIndex(BluetoothMapContract.MessageColumns.FLAG_ATTACHMENT);
            this.mMessageColAttachmentSize = c.getColumnIndex(BluetoothMapContract.MessageColumns.ATTACHMENT_SIZE);
            this.mMessageColPriority = c.getColumnIndex(BluetoothMapContract.MessageColumns.FLAG_HIGH_PRIORITY);
            this.mMessageColProtected = c.getColumnIndex(BluetoothMapContract.MessageColumns.FLAG_PROTECTED);
            this.mMessageColReception = c.getColumnIndex(BluetoothMapContract.MessageColumns.RECEPTION_STATE);
            this.mMessageColDelivery = c.getColumnIndex(BluetoothMapContract.MessageColumns.DEVILERY_STATE);
            this.mMessageColThreadId = c.getColumnIndex("thread_id");
        }

        public void setEmailMessageColumns(Cursor c) {
            setMessageColumns(c);
            this.mMessageColCcAddress = c.getColumnIndex(BluetoothMapContract.EmailMessageColumns.CC_LIST);
            this.mMessageColBccAddress = c.getColumnIndex(BluetoothMapContract.EmailMessageColumns.BCC_LIST);
            this.mMessageColReplyTo = c.getColumnIndex(BluetoothMapContract.EmailMessageColumns.REPLY_TO_LIST);
        }

        public void setImMessageColumns(Cursor c) {
            setMessageColumns(c);
            this.mMessageColThreadName = c.getColumnIndex("thread_name");
            this.mMessageColAttachmentMime = c.getColumnIndex(BluetoothMapContract.MessageColumns.ATTACHMENT_MINE_TYPES);
            this.mMessageColBody = c.getColumnIndex(BluetoothMapContract.MessageColumns.BODY);
        }

        public void setEmailImConvoColumns(Cursor c) {
            this.mConvoColConvoId = c.getColumnIndex("thread_id");
            this.mConvoColLastActivity = c.getColumnIndex(BluetoothMapContract.ConversationColumns.LAST_THREAD_ACTIVITY);
            this.mConvoColName = c.getColumnIndex("thread_name");
            this.mConvoColRead = c.getColumnIndex(BluetoothMapContract.ConversationColumns.READ_STATUS);
            this.mConvoColVersionCounter = c.getColumnIndex(BluetoothMapContract.ConversationColumns.VERSION_COUNTER);
            this.mConvoColSummary = c.getColumnIndex(BluetoothMapContract.ConversationColumns.SUMMARY);
            setEmailImConvoContactColumns(c);
        }

        public void setEmailImConvoContactColumns(Cursor c) {
            this.mContactColBtUid = c.getColumnIndex(BluetoothMapContract.ConvoContactColumns.X_BT_UID);
            this.mContactColChatState = c.getColumnIndex(BluetoothMapContract.ChatStatusColumns.CHAT_STATE);
            this.mContactColContactUci = c.getColumnIndex(BluetoothMapContract.ConvoContactColumns.UCI);
            this.mContactColNickname = c.getColumnIndex(BluetoothMapContract.ConvoContactColumns.NICKNAME);
            this.mContactColLastActive = c.getColumnIndex(BluetoothMapContract.ChatStatusColumns.LAST_ACTIVE);
            this.mContactColName = c.getColumnIndex("name");
            this.mContactColPresenceState = c.getColumnIndex(BluetoothMapContract.PresenceColumns.PRESENCE_STATE);
            this.mContactColPresenceText = c.getColumnIndex(BluetoothMapContract.PresenceColumns.STATUS_TEXT);
            this.mContactColPriority = c.getColumnIndex(BluetoothMapContract.PresenceColumns.PRIORITY);
        }

        public void setSmsColumns(Cursor c) {
            this.mSmsColId = c.getColumnIndex("_id");
            this.mSmsColFolder = c.getColumnIndex(BluetoothMapContentObserver.EXTRA_MESSAGE_SENT_MSG_TYPE);
            this.mSmsColRead = c.getColumnIndex(BluetoothMapContract.FILTER_READ_STATUS);
            this.mSmsColSubject = c.getColumnIndex(BluetoothMapContract.MessageColumns.BODY);
            this.mSmsColAddress = c.getColumnIndex("address");
            this.mSmsColDate = c.getColumnIndex(BluetoothMapContract.MessageColumns.DATE);
            this.mSmsColType = c.getColumnIndex(BluetoothMapContentObserver.EXTRA_MESSAGE_SENT_MSG_TYPE);
            this.mSmsColThreadId = c.getColumnIndex("thread_id");
        }

        public void setMmsColumns(Cursor c) {
            this.mMmsColId = c.getColumnIndex("_id");
            this.mMmsColFolder = c.getColumnIndex("msg_box");
            this.mMmsColRead = c.getColumnIndex(BluetoothMapContract.FILTER_READ_STATUS);
            this.mMmsColAttachmentSize = c.getColumnIndex("m_size");
            this.mMmsColTextOnly = c.getColumnIndex("text_only");
            this.mMmsColSize = c.getColumnIndex("m_size");
            this.mMmsColDate = c.getColumnIndex(BluetoothMapContract.MessageColumns.DATE);
            this.mMmsColSubject = c.getColumnIndex("sub");
            this.mMmsColThreadId = c.getColumnIndex("thread_id");
        }
    }

    public BluetoothMapContent(Context context, BluetoothMapAccountItem account, BluetoothMapMasInstance mas) {
        this.mContext = context;
        this.mResolver = context.getContentResolver();
        this.mMasInstance = mas;
        if (account != null) {
            this.mBaseUri = account.mBase_uri + "/";
            this.mAccount = account;
            return;
        }
        this.mBaseUri = null;
        this.mAccount = null;
    }

    private static void close(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
            }
        }
    }

    private void setProtected(BluetoothMapMessageListingElement e, Cursor c, FilterInfo fi, BluetoothMapAppParams ap) {
        if ((ap.getParameterMask() & PlaybackStateCompat.ACTION_PREPARE) != 0) {
            String protect = "no";
            if (fi.mMsgType == 2 || fi.mMsgType == 3) {
                int flagProtected = c.getInt(fi.mMessageColProtected);
                if (flagProtected == 1) {
                    protect = "yes";
                }
            }
            e.setProtect(protect);
        }
    }

    private void setThreadId(BluetoothMapMessageListingElement e, Cursor c, FilterInfo fi, BluetoothMapAppParams ap) {
        if ((ap.getParameterMask() & PlaybackStateCompat.ACTION_PREPARE_FROM_URI) != 0) {
            long threadId = 0;
            BluetoothMapUtils.TYPE type = BluetoothMapUtils.TYPE.SMS_GSM;
            if (fi.mMsgType == 0) {
                threadId = c.getLong(fi.mSmsColThreadId);
            } else if (fi.mMsgType == 1) {
                threadId = c.getLong(fi.mMmsColThreadId);
                type = BluetoothMapUtils.TYPE.MMS;
            } else if (fi.mMsgType == 2 || fi.mMsgType == 3) {
                threadId = c.getLong(fi.mMessageColThreadId);
                type = BluetoothMapUtils.TYPE.EMAIL;
            }
            e.setThreadId(threadId, type);
        }
    }

    private void setThreadName(BluetoothMapMessageListingElement e, Cursor c, FilterInfo fi, BluetoothMapAppParams ap) {
        if ((ap.getParameterMask() & PlaybackStateCompat.ACTION_SET_REPEAT_MODE) != 0 && fi.mMsgType == 3) {
            String threadName = c.getString(fi.mMessageColThreadName);
            e.setThreadName(threadName);
        }
    }

    private void setSent(BluetoothMapMessageListingElement e, Cursor c, FilterInfo fi, BluetoothMapAppParams ap) {
        String sent;
        if ((ap.getParameterMask() & PlaybackStateCompat.ACTION_PLAY_FROM_URI) != 0) {
            int msgType = 0;
            if (fi.mMsgType == 0) {
                msgType = c.getInt(fi.mSmsColFolder);
            } else if (fi.mMsgType == 1) {
                msgType = c.getInt(fi.mMmsColFolder);
            } else if (fi.mMsgType == 2 || fi.mMsgType == 3) {
                msgType = c.getInt(fi.mMessageColFolder);
            }
            if (msgType == 2) {
                sent = "yes";
            } else {
                sent = "no";
            }
            e.setSent(sent);
        }
    }

    private void setRead(BluetoothMapMessageListingElement e, Cursor c, FilterInfo fi, BluetoothMapAppParams ap) {
        int read = 0;
        if (fi.mMsgType == 0) {
            read = c.getInt(fi.mSmsColRead);
        } else if (fi.mMsgType == 1) {
            read = c.getInt(fi.mMmsColRead);
        } else if (fi.mMsgType == 2 || fi.mMsgType == 3) {
            read = c.getInt(fi.mMessageColRead);
        }
        e.setRead(read == 1, (ap.getParameterMask() & PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM) != 0);
    }

    private void setConvoRead(BluetoothMapConvoListingElement e, Cursor c, FilterInfo fi, BluetoothMapAppParams ap) {
        int read = c.getInt(fi.mConvoColRead);
        e.setRead(read == 1, (ap.getParameterMask() & PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM) != 0);
    }

    private void setPriority(BluetoothMapMessageListingElement e, Cursor c, FilterInfo fi, BluetoothMapAppParams ap) {
        if ((ap.getParameterMask() & PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH) != 0) {
            String priority = "no";
            if (fi.mMsgType == 2 || fi.mMsgType == 3) {
                int highPriority = c.getInt(fi.mMessageColPriority);
                if (highPriority == 1) {
                    priority = "yes";
                }
            }
            int pri = 0;
            if (fi.mMsgType == 1) {
                pri = c.getInt(c.getColumnIndex("pri"));
            }
            if (pri == 130) {
                priority = "yes";
            }
            e.setPriority(priority);
        }
    }

    private void setAttachment(BluetoothMapMessageListingElement e, Cursor c, FilterInfo fi, BluetoothMapAppParams ap) {
        if ((ap.getParameterMask() & PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) != 0) {
            int size = 0;
            String attachmentMimeTypes = null;
            if (fi.mMsgType == 1) {
                if (c.getInt(fi.mMmsColTextOnly) == 0 && (size = c.getInt(fi.mMmsColAttachmentSize)) <= 0) {
                    size = 1;
                }
            } else if (fi.mMsgType == 2) {
                int attachment = c.getInt(fi.mMessageColAttachment);
                size = c.getInt(fi.mMessageColAttachmentSize);
                if (attachment == 1 && size == 0) {
                    size = 1;
                }
            } else if (fi.mMsgType == 3) {
                int attachment2 = c.getInt(fi.mMessageColAttachment);
                size = c.getInt(fi.mMessageColAttachmentSize);
                if (attachment2 == 1 && size == 0) {
                    size = 1;
                    attachmentMimeTypes = c.getString(fi.mMessageColAttachmentMime);
                }
            }
            e.setAttachmentSize(size);
            if (this.mMsgListingVersion > 10 && (ap.getParameterMask() & PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) != 0) {
                e.setAttachmentMimeTypes(attachmentMimeTypes);
            }
        }
    }

    private void setText(BluetoothMapMessageListingElement e, Cursor c, FilterInfo fi, BluetoothMapAppParams ap) {
        if ((ap.getParameterMask() & 512) != 0) {
            String hasText = INetd.NEXTHOP_NONE;
            if (fi.mMsgType == 0) {
                hasText = "yes";
            } else if (fi.mMsgType == 1) {
                int textOnly = c.getInt(fi.mMmsColTextOnly);
                if (textOnly == 1) {
                    hasText = "yes";
                } else {
                    long id = c.getLong(fi.mMmsColId);
                    String text = getTextPartsMms(this.mResolver, id);
                    if (text != null && text.length() > 0) {
                        hasText = "yes";
                    } else {
                        hasText = "no";
                    }
                }
            } else {
                int textOnly2 = fi.mMsgType;
                if (textOnly2 == 2 || fi.mMsgType == 3) {
                    hasText = "yes";
                }
            }
            e.setText(hasText);
        }
    }

    private void setReceptionStatus(BluetoothMapMessageListingElement e, Cursor c, FilterInfo fi, BluetoothMapAppParams ap) {
        if ((ap.getParameterMask() & 256) != 0) {
            e.setReceptionStatus(BluetoothMapContract.RECEPTION_STATE_COMPLETE);
        }
    }

    private void setDeliveryStatus(BluetoothMapMessageListingElement e, Cursor c, FilterInfo fi, BluetoothMapAppParams ap) {
        if ((ap.getParameterMask() & PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) != 0) {
            String deliveryStatus = BluetoothMapContract.DELIVERY_STATE_DELIVERED;
            if (fi.mMsgType == 2 || fi.mMsgType == 3) {
                deliveryStatus = c.getString(fi.mMessageColDelivery);
            }
            e.setDeliveryStatus(deliveryStatus);
        }
    }

    private void setSize(BluetoothMapMessageListingElement e, Cursor c, FilterInfo fi, BluetoothMapAppParams ap) {
        if ((ap.getParameterMask() & 128) != 0) {
            int size = 0;
            if (fi.mMsgType == 0) {
                size = c.getString(fi.mSmsColSubject).length();
            } else if (fi.mMsgType == 1) {
                size = c.getInt(fi.mMmsColSize);
                String subject = e.getSubject();
                if (subject == null || subject.length() == 0) {
                    setSubject(e, c, fi, ap);
                }
                if (subject != null && subject.length() != 0) {
                    size += subject.length();
                }
            } else if (fi.mMsgType == 2 || fi.mMsgType == 3) {
                size = c.getInt(fi.mMessageColSize);
            }
            if (size <= 0) {
                size = 1;
            }
            e.setSize(size);
        }
    }

    private BluetoothMapUtils.TYPE getType(Cursor c, FilterInfo fi) {
        if (fi.mMsgType == 0) {
            if (fi.mPhoneType == 2) {
                BluetoothMapUtils.TYPE type = BluetoothMapUtils.TYPE.SMS_CDMA;
                return type;
            }
            BluetoothMapUtils.TYPE type2 = BluetoothMapUtils.TYPE.SMS_GSM;
            return type2;
        }
        if (fi.mMsgType == 1) {
            BluetoothMapUtils.TYPE type3 = BluetoothMapUtils.TYPE.MMS;
            return type3;
        }
        if (fi.mMsgType == 2) {
            BluetoothMapUtils.TYPE type4 = BluetoothMapUtils.TYPE.EMAIL;
            return type4;
        }
        if (fi.mMsgType != 3) {
            return null;
        }
        BluetoothMapUtils.TYPE type5 = BluetoothMapUtils.TYPE.IM;
        return type5;
    }

    private void setFolderType(BluetoothMapMessageListingElement e, Cursor c, FilterInfo fi, BluetoothMapAppParams ap) {
        if ((ap.getParameterMask() & PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) != 0) {
            String folderType = null;
            if (fi.mMsgType == 0) {
                int folderId = c.getInt(fi.mSmsColFolder);
                folderType = folderId == 1 ? BluetoothMapContract.FOLDER_NAME_INBOX : folderId == 2 ? "sent" : folderId == 3 ? BluetoothMapContract.FOLDER_NAME_DRAFT : (folderId == 4 || folderId == 5 || folderId == 6) ? BluetoothMapContract.FOLDER_NAME_OUTBOX : BluetoothMapContract.FOLDER_NAME_DELETED;
            } else if (fi.mMsgType == 1) {
                int folderId2 = c.getInt(fi.mMmsColFolder);
                folderType = folderId2 == 1 ? BluetoothMapContract.FOLDER_NAME_INBOX : folderId2 == 2 ? "sent" : folderId2 == 3 ? BluetoothMapContract.FOLDER_NAME_DRAFT : folderId2 == 4 ? BluetoothMapContract.FOLDER_NAME_OUTBOX : BluetoothMapContract.FOLDER_NAME_DELETED;
            } else if (fi.mMsgType != 2 && fi.mMsgType == 3) {
                int folderId3 = c.getInt(fi.mMessageColFolder);
                folderType = ((long) folderId3) == 1 ? BluetoothMapContract.FOLDER_NAME_INBOX : ((long) folderId3) == 2 ? "sent" : ((long) folderId3) == 3 ? BluetoothMapContract.FOLDER_NAME_DRAFT : ((long) folderId3) == 4 ? BluetoothMapContract.FOLDER_NAME_OUTBOX : ((long) folderId3) == 5 ? BluetoothMapContract.FOLDER_NAME_DELETED : BluetoothMapContract.FOLDER_NAME_OTHER;
            }
            e.setFolderType(folderType);
        }
    }

    private String getRecipientNameEmail(BluetoothMapMessageListingElement e, Cursor c, FilterInfo fi) {
        String toAddress = c.getString(fi.mMessageColToAddress);
        String ccAddress = c.getString(fi.mMessageColCcAddress);
        String bccAddress = c.getString(fi.mMessageColBccAddress);
        StringBuilder sb = new StringBuilder();
        if (toAddress != null) {
            Rfc822Token[] tokens = Rfc822Tokenizer.tokenize(toAddress);
            if (tokens.length != 0) {
                boolean first = true;
                for (Rfc822Token rfc822Token : tokens) {
                    String name = rfc822Token.getName();
                    if (!first) {
                        sb.append("; ");
                    }
                    sb.append(name);
                    first = false;
                }
            }
            if (ccAddress != null) {
                sb.append("; ");
            }
        }
        if (ccAddress != null) {
            Rfc822Token[] tokens2 = Rfc822Tokenizer.tokenize(ccAddress);
            if (tokens2.length != 0) {
                boolean first2 = true;
                for (Rfc822Token rfc822Token2 : tokens2) {
                    String name2 = rfc822Token2.getName();
                    if (!first2) {
                        sb.append("; ");
                    }
                    sb.append(name2);
                    first2 = false;
                }
            }
            if (bccAddress != null) {
                sb.append("; ");
            }
        }
        if (bccAddress != null) {
            Rfc822Token[] tokens3 = Rfc822Tokenizer.tokenize(bccAddress);
            if (tokens3.length != 0) {
                boolean first3 = true;
                for (Rfc822Token rfc822Token3 : tokens3) {
                    String name3 = rfc822Token3.getName();
                    if (!first3) {
                        sb.append("; ");
                    }
                    sb.append(name3);
                    first3 = false;
                }
            }
        }
        return sb.toString();
    }

    private String getRecipientAddressingEmail(BluetoothMapMessageListingElement e, Cursor c, FilterInfo fi) {
        String toAddress = c.getString(fi.mMessageColToAddress);
        String ccAddress = c.getString(fi.mMessageColCcAddress);
        String bccAddress = c.getString(fi.mMessageColBccAddress);
        StringBuilder sb = new StringBuilder();
        if (toAddress != null) {
            Rfc822Token[] tokens = Rfc822Tokenizer.tokenize(toAddress);
            if (tokens.length != 0) {
                boolean first = true;
                for (Rfc822Token rfc822Token : tokens) {
                    String email = rfc822Token.getAddress();
                    if (!first) {
                        sb.append("; ");
                    }
                    sb.append(email);
                    first = false;
                }
            }
            if (ccAddress != null) {
                sb.append("; ");
            }
        }
        if (ccAddress != null) {
            Rfc822Token[] tokens2 = Rfc822Tokenizer.tokenize(ccAddress);
            if (tokens2.length != 0) {
                boolean first2 = true;
                for (Rfc822Token rfc822Token2 : tokens2) {
                    String email2 = rfc822Token2.getAddress();
                    if (!first2) {
                        sb.append("; ");
                    }
                    sb.append(email2);
                    first2 = false;
                }
            }
            if (bccAddress != null) {
                sb.append("; ");
            }
        }
        if (bccAddress != null) {
            Rfc822Token[] tokens3 = Rfc822Tokenizer.tokenize(bccAddress);
            if (tokens3.length != 0) {
                boolean first3 = true;
                for (Rfc822Token rfc822Token3 : tokens3) {
                    String email3 = rfc822Token3.getAddress();
                    if (!first3) {
                        sb.append("; ");
                    }
                    sb.append(email3);
                    first3 = false;
                }
            }
        }
        return sb.toString();
    }

    private void setRecipientAddressing(BluetoothMapMessageListingElement e, Cursor c, FilterInfo fi, BluetoothMapAppParams ap) {
        if ((ap.getParameterMask() & 32) != 0) {
            String address = null;
            if (fi.mMsgType == 0) {
                int msgType = c.getInt(fi.mSmsColType);
                if (msgType == 1) {
                    address = fi.mPhoneNum;
                } else {
                    address = c.getString(c.getColumnIndex("address"));
                }
                if (address == null && msgType == 3) {
                    int threadIdInd = c.getColumnIndex("thread_id");
                    String threadIdStr = c.getString(threadIdInd);
                    if (threadIdStr != null) {
                        address = getCanonicalAddressSms(this.mResolver, Integer.valueOf(threadIdStr).intValue());
                    }
                }
            } else if (fi.mMsgType == 1) {
                long id = c.getLong(c.getColumnIndex("_id"));
                address = getAddressMms(this.mResolver, id, 151);
            } else if (fi.mMsgType == 2) {
                address = getRecipientAddressingEmail(e, c, fi);
            }
            if (address == null) {
                address = INetd.NEXTHOP_NONE;
            }
            e.setRecipientAddressing(address);
        }
    }

    private void setRecipientName(BluetoothMapMessageListingElement e, Cursor c, FilterInfo fi, BluetoothMapAppParams ap) {
        String phone;
        if ((ap.getParameterMask() & 16) != 0) {
            String name = null;
            if (fi.mMsgType == 0) {
                int msgType = c.getInt(fi.mSmsColType);
                if (msgType != 1) {
                    String phone2 = c.getString(fi.mSmsColAddress);
                    if (phone2 != null && !phone2.isEmpty()) {
                        name = getContactNameFromPhone(phone2, this.mResolver);
                    }
                } else {
                    name = fi.mPhoneAlphaTag;
                }
            } else if (fi.mMsgType == 1) {
                long id = c.getLong(fi.mMmsColId);
                if (e.getRecipientAddressing() != null) {
                    phone = getAddressMms(this.mResolver, id, 151);
                } else {
                    phone = e.getRecipientAddressing();
                }
                if (phone != null && !phone.isEmpty()) {
                    name = getContactNameFromPhone(phone, this.mResolver);
                }
            } else if (fi.mMsgType == 2) {
                name = getRecipientNameEmail(e, c, fi);
            }
            if (name == null) {
                name = INetd.NEXTHOP_NONE;
            }
            e.setRecipientName(name);
        }
    }

    private void setSenderAddressing(BluetoothMapMessageListingElement e, Cursor c, FilterInfo fi, BluetoothMapAppParams ap) {
        String tempAddress;
        if ((ap.getParameterMask() & 8) != 0) {
            String address = INetd.NEXTHOP_NONE;
            if (fi.mMsgType == 0) {
                int msgType = c.getInt(fi.mSmsColType);
                if (msgType == 1) {
                    tempAddress = c.getString(fi.mSmsColAddress);
                } else {
                    tempAddress = fi.mPhoneNum;
                }
                if (tempAddress != null) {
                    address = PhoneNumberUtils.extractNetworkPortion(tempAddress);
                    Boolean alpha = Boolean.valueOf(PhoneNumberUtils.stripSeparators(tempAddress).matches("[0-9]*[a-zA-Z]+[0-9]*"));
                    if (address == null || address.length() < 2 || alpha.booleanValue()) {
                        address = tempAddress;
                    }
                }
            } else if (fi.mMsgType == 1) {
                long id = c.getLong(fi.mMmsColId);
                String tempAddress2 = getAddressMms(this.mResolver, id, MMS_FROM);
                address = PhoneNumberUtils.extractNetworkPortion(tempAddress2);
                if (address == null || address.length() < 1) {
                    address = tempAddress2;
                }
            } else if (fi.mMsgType == 2) {
                String nameEmail = c.getString(fi.mMessageColFromAddress);
                Rfc822Token[] tokens = Rfc822Tokenizer.tokenize(nameEmail);
                if (tokens.length != 0) {
                    boolean first = true;
                    for (int i = 0; i < tokens.length; i++) {
                        String[] emails = {tokens[i].getAddress()};
                        tokens[i].getName();
                        if (!first) {
                            address = address + "; ";
                        }
                        address = address + emails[0];
                        first = false;
                    }
                }
            } else if (fi.mMsgType == 3) {
                long contactId = c.getLong(fi.mMessageColFromAddress);
                Uri contactsUri = Uri.parse(this.mBaseUri + BluetoothMapContract.TABLE_CONVOCONTACT);
                Cursor contacts = this.mResolver.query(contactsUri, BluetoothMapContract.BT_CONTACT_PROJECTION, "convo_id = " + contactId, null, null);
                if (contacts != null) {
                    try {
                        if (contacts.moveToFirst()) {
                            address = contacts.getString(contacts.getColumnIndex(BluetoothMapContract.ConvoContactColumns.UCI));
                        }
                    } finally {
                        if (contacts != null) {
                            contacts.close();
                        }
                    }
                }
            }
            if (address == null) {
                address = INetd.NEXTHOP_NONE;
            }
            e.setSenderAddressing(address);
        }
    }

    private void setSenderName(BluetoothMapMessageListingElement e, Cursor c, FilterInfo fi, BluetoothMapAppParams ap) {
        String phone;
        if ((ap.getParameterMask() & 4) != 0) {
            String name = INetd.NEXTHOP_NONE;
            if (fi.mMsgType == 0) {
                int msgType = c.getInt(c.getColumnIndex(BluetoothMapContentObserver.EXTRA_MESSAGE_SENT_MSG_TYPE));
                if (msgType == 1) {
                    String phone2 = c.getString(fi.mSmsColAddress);
                    if (phone2 != null && !phone2.isEmpty()) {
                        name = getContactNameFromPhone(phone2, this.mResolver);
                    }
                } else {
                    name = fi.mPhoneAlphaTag;
                }
            } else if (fi.mMsgType == 1) {
                long id = c.getLong(fi.mMmsColId);
                if (e.getSenderAddressing() != null) {
                    phone = getAddressMms(this.mResolver, id, MMS_FROM);
                } else {
                    phone = e.getSenderAddressing();
                }
                if (phone != null && !phone.isEmpty()) {
                    name = getContactNameFromPhone(phone, this.mResolver);
                }
            } else if (fi.mMsgType == 2) {
                String nameEmail = c.getString(fi.mMessageColFromAddress);
                Rfc822Token[] tokens = Rfc822Tokenizer.tokenize(nameEmail);
                if (tokens.length != 0) {
                    boolean first = true;
                    for (int i = 0; i < tokens.length; i++) {
                        String[] emails = {tokens[i].getAddress()};
                        String nameIn = tokens[i].getName();
                        if (!first) {
                            name = name + "; ";
                        }
                        name = name + nameIn;
                        first = false;
                    }
                }
            } else if (fi.mMsgType == 3) {
                long contactId = c.getLong(fi.mMessageColFromAddress);
                Uri contactsUri = Uri.parse(this.mBaseUri + BluetoothMapContract.TABLE_CONVOCONTACT);
                Cursor contacts = this.mResolver.query(contactsUri, BluetoothMapContract.BT_CONTACT_PROJECTION, "convo_id = " + contactId, null, null);
                if (contacts != null) {
                    try {
                        if (contacts.moveToFirst()) {
                            name = contacts.getString(contacts.getColumnIndex("name"));
                        }
                    } finally {
                        if (contacts != null) {
                            contacts.close();
                        }
                    }
                }
            }
            if (name == null) {
                name = INetd.NEXTHOP_NONE;
            }
            e.setSenderName(name);
        }
    }

    private void setDateTime(BluetoothMapMessageListingElement e, Cursor c, FilterInfo fi, BluetoothMapAppParams ap) {
        if ((ap.getParameterMask() & 2) != 0) {
            long date = 0;
            if (fi.mMsgType == 0) {
                date = c.getLong(fi.mSmsColDate);
            } else if (fi.mMsgType == 1) {
                date = c.getLong(fi.mMmsColDate) * 1000;
            } else if (fi.mMsgType == 2 || fi.mMsgType == 3) {
                date = c.getLong(fi.mMessageColDate);
            }
            e.setDateTime(date);
        }
    }

    private void setLastActivity(BluetoothMapConvoListingElement e, Cursor c, FilterInfo fi, BluetoothMapAppParams ap) {
        long date = 0;
        if (fi.mMsgType == 0 || fi.mMsgType == 1) {
            date = c.getLong(MMS_SMS_THREAD_COL_DATE);
        } else if (fi.mMsgType == 2 || fi.mMsgType == 3) {
            date = c.getLong(fi.mConvoColLastActivity);
        }
        e.setLastActivity(date);
    }

    public static String getTextPartsMms(ContentResolver r, long id) {
        String part;
        String text = INetd.NEXTHOP_NONE;
        String selection = new String("mid=" + id);
        String uriStr = new String(Telephony.Mms.CONTENT_URI + "/" + id + "/part");
        Uri uriAddress = Uri.parse(uriStr);
        Cursor c = r.query(uriAddress, null, selection, null, null);
        if (c != null) {
            try {
                if (c.moveToFirst()) {
                    do {
                        String ct = c.getString(c.getColumnIndex("ct"));
                        if (ct.equals("text/plain") && (part = c.getString(c.getColumnIndex(BluetoothMapContract.MessagePartColumns.TEXT))) != null) {
                            text = text + part;
                        }
                    } while (c.moveToNext());
                }
            } finally {
                if (c != null) {
                    c.close();
                }
            }
        }
        return text;
    }

    private void setSubject(BluetoothMapMessageListingElement e, Cursor c, FilterInfo fi, BluetoothMapAppParams ap) {
        String subject = INetd.NEXTHOP_NONE;
        int subLength = ap.getSubjectLength();
        if (subLength == -1) {
            subLength = 256;
        }
        if (DeviceWorkArounds.addressStartsWith(BluetoothMapService.getRemoteDevice().getAddress(), DeviceWorkArounds.HONDA_CARKIT) || (ap.getParameterMask() & 1) != 0) {
            if (fi.mMsgType == 0) {
                subject = c.getString(fi.mSmsColSubject);
            } else if (fi.mMsgType == 1) {
                subject = c.getString(fi.mMmsColSubject);
                if (subject == null || subject.length() == 0) {
                    long id = c.getLong(fi.mMmsColId);
                    subject = getTextPartsMms(this.mResolver, id);
                }
            } else if (fi.mMsgType == 2 || fi.mMsgType == 3) {
                subject = c.getString(fi.mMessageColSubject);
            }
            if (subject != null && subject.length() > subLength) {
                subject = subject.substring(0, subLength);
            } else if (subject == null) {
                subject = INetd.NEXTHOP_NONE;
            }
            e.setSubject(subject);
        }
    }

    private void setHandle(BluetoothMapMessageListingElement e, Cursor c, FilterInfo fi, BluetoothMapAppParams ap) {
        long handle = -1;
        if (fi.mMsgType == 0) {
            handle = c.getLong(fi.mSmsColId);
        } else if (fi.mMsgType == 1) {
            handle = c.getLong(fi.mMmsColId);
        } else if (fi.mMsgType == 2 || fi.mMsgType == 3) {
            handle = c.getLong(fi.mMessageColId);
        }
        e.setHandle(handle);
    }

    private BluetoothMapMessageListingElement element(Cursor c, FilterInfo fi, BluetoothMapAppParams ap) {
        BluetoothMapMessageListingElement e = new BluetoothMapMessageListingElement();
        setHandle(e, c, fi, ap);
        setDateTime(e, c, fi, ap);
        e.setType(getType(c, fi), (ap.getParameterMask() & 64) != 0);
        setRead(e, c, fi, ap);
        e.setCursorIndex(c.getPosition());
        return e;
    }

    private BluetoothMapConvoListingElement createConvoElement(Cursor c, FilterInfo fi, BluetoothMapAppParams ap) {
        BluetoothMapConvoListingElement e = new BluetoothMapConvoListingElement();
        setLastActivity(e, c, fi, ap);
        e.setType(getType(c, fi));
        e.setCursorIndex(c.getPosition());
        return e;
    }

    public static String getContactNameFromPhone(String phone, ContentResolver resolver) {
        String name = null;
        if (TextUtils.isEmpty(phone)) {
            return null;
        }
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.ENTERPRISE_CONTENT_FILTER_URI, Uri.encode(phone));
        String[] projection = {"_id", "display_name"};
        Cursor c = null;
        try {
            c = resolver.query(uri, projection, "in_visible_group=1", null, "display_name ASC");
            if (c != null) {
                int colIndex = c.getColumnIndex("display_name");
                if (c.getCount() >= 1) {
                    c.moveToFirst();
                    name = c.getString(colIndex);
                }
            }
            return name;
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    /* JADX WARN: Finally extract failed */
    public static String getCanonicalAddressSms(ContentResolver r, int threadId) {
        Cursor cr;
        Uri sAllCanonical = Telephony.MmsSms.CONTENT_URI.buildUpon().appendPath("canonical-addresses").build();
        Uri sAllThreadsUri = Telephony.Threads.CONTENT_URI.buildUpon().appendQueryParameter(FtsOptions.TOKENIZER_SIMPLE, "true").build();
        Cursor cr2 = null;
        String recipientAddress = INetd.NEXTHOP_NONE;
        String recipientIds = null;
        String whereClause = "_id=" + threadId;
        try {
            cr2 = r.query(sAllThreadsUri, RECIPIENT_ID_PROJECTION, whereClause, null, null);
            if (cr2 != null && cr2.moveToFirst()) {
                recipientIds = cr2.getString(0);
            }
            if (cr2 == null) {
                cr = cr2;
            } else {
                cr2.close();
                cr = null;
            }
            if (recipientIds != null) {
                String[] recipients = recipientIds.split(" ");
                String whereClause2 = INetd.NEXTHOP_NONE;
                for (String id : recipients) {
                    if (whereClause2.length() != 0) {
                        whereClause2 = whereClause2 + " OR ";
                    }
                    whereClause2 = whereClause2 + "_id=" + id;
                }
                try {
                    cr = r.query(sAllCanonical, null, whereClause2, null, null);
                    if (cr != null && cr.moveToFirst()) {
                        do {
                            if (recipientAddress.length() != 0) {
                                recipientAddress = recipientAddress + ";";
                            }
                            recipientAddress = recipientAddress + cr.getString(cr.getColumnIndex("address"));
                        } while (cr.moveToNext());
                    }
                    if (cr != null) {
                        cr.close();
                    }
                } catch (Throwable th) {
                    if (cr != null) {
                        cr.close();
                    }
                    throw th;
                }
            }
            return recipientAddress;
        } catch (Throwable th2) {
            if (cr2 != null) {
                cr2.close();
            }
            throw th2;
        }
    }

    public static String getAddressMms(ContentResolver r, long id, int type) {
        String selection = new String("msg_id=" + id + " AND type=" + type);
        String uriStr = new String(Telephony.Mms.CONTENT_URI + "/" + id + "/addr");
        Uri uriAddress = Uri.parse(uriStr);
        String addr = null;
        String[] projection = {"address"};
        Cursor c = null;
        try {
            c = r.query(uriAddress, projection, selection, null, null);
            int colIndex = c.getColumnIndex("address");
            if (c != null && c.moveToFirst()) {
                addr = c.getString(colIndex);
                if (addr.equals(INSERT_ADDRES_TOKEN)) {
                    addr = INetd.NEXTHOP_NONE;
                }
            }
            return addr;
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    private boolean matchRecipientMms(Cursor c, FilterInfo fi, String recip) {
        long id = c.getLong(c.getColumnIndex("_id"));
        String phone = getAddressMms(this.mResolver, id, 151);
        if (phone != null && phone.length() > 0) {
            if (phone.matches(recip)) {
                return true;
            }
            String name = getContactNameFromPhone(phone, this.mResolver);
            if (name != null && name.length() > 0 && name.matches(recip)) {
                return true;
            }
            return false;
        }
        return false;
    }

    private boolean matchRecipientSms(Cursor c, FilterInfo fi, String recip) {
        int msgType = c.getInt(c.getColumnIndex(BluetoothMapContentObserver.EXTRA_MESSAGE_SENT_MSG_TYPE));
        if (msgType == 1) {
            String phone = fi.mPhoneNum;
            String name = fi.mPhoneAlphaTag;
            if (phone != null && phone.length() > 0 && phone.matches(recip)) {
                return true;
            }
            if (name != null && name.length() > 0 && name.matches(recip)) {
                return true;
            }
            return false;
        }
        String phone2 = c.getString(c.getColumnIndex("address"));
        if (phone2 != null && phone2.length() > 0) {
            if (phone2.matches(recip)) {
                return true;
            }
            String name2 = getContactNameFromPhone(phone2, this.mResolver);
            if (name2 != null && name2.length() > 0 && name2.matches(recip)) {
                return true;
            }
            return false;
        }
        return false;
    }

    private boolean matchRecipient(Cursor c, FilterInfo fi, BluetoothMapAppParams ap) {
        String recip = ap.getFilterRecipient();
        if (recip != null && recip.length() > 0) {
            String recip2 = ".*" + recip.replace("*", ".*") + ".*";
            if (fi.mMsgType == 0) {
                boolean res = matchRecipientSms(c, fi, recip2);
                return res;
            }
            if (fi.mMsgType == 1) {
                boolean res2 = matchRecipientMms(c, fi, recip2);
                return res2;
            }
            return false;
        }
        return true;
    }

    private boolean matchOriginatorMms(Cursor c, FilterInfo fi, String orig) {
        long id = c.getLong(c.getColumnIndex("_id"));
        String phone = getAddressMms(this.mResolver, id, MMS_FROM);
        if (phone != null && phone.length() > 0) {
            if (phone.matches(orig)) {
                return true;
            }
            String name = getContactNameFromPhone(phone, this.mResolver);
            if (name != null && name.length() > 0 && name.matches(orig)) {
                return true;
            }
            return false;
        }
        return false;
    }

    private boolean matchOriginatorSms(Cursor c, FilterInfo fi, String orig) {
        int msgType = c.getInt(c.getColumnIndex(BluetoothMapContentObserver.EXTRA_MESSAGE_SENT_MSG_TYPE));
        if (msgType == 1) {
            String phone = c.getString(c.getColumnIndex("address"));
            if (phone != null && phone.length() > 0) {
                if (phone.matches(orig)) {
                    return true;
                }
                String name = getContactNameFromPhone(phone, this.mResolver);
                if (name != null && name.length() > 0 && name.matches(orig)) {
                    return true;
                }
                return false;
            }
            return false;
        }
        String phone2 = fi.mPhoneNum;
        String name2 = fi.mPhoneAlphaTag;
        if (phone2 != null && phone2.length() > 0 && phone2.matches(orig)) {
            return true;
        }
        if (name2 != null && name2.length() > 0 && name2.matches(orig)) {
            return true;
        }
        return false;
    }

    private boolean matchOriginator(Cursor c, FilterInfo fi, BluetoothMapAppParams ap) {
        String orig = ap.getFilterOriginator();
        if (orig != null && orig.length() > 0) {
            String orig2 = ".*" + orig.replace("*", ".*") + ".*";
            if (fi.mMsgType == 0) {
                boolean res = matchOriginatorSms(c, fi, orig2);
                return res;
            }
            if (fi.mMsgType == 1) {
                boolean res2 = matchOriginatorMms(c, fi, orig2);
                return res2;
            }
            return false;
        }
        return true;
    }

    private boolean matchAddresses(Cursor c, FilterInfo fi, BluetoothMapAppParams ap) {
        return matchOriginator(c, fi, ap) && matchRecipient(c, fi, ap);
    }

    private String setWhereFilterFolderTypeSms(String folder) {
        if (BluetoothMapContract.FOLDER_NAME_INBOX.equalsIgnoreCase(folder)) {
            return "type = 1 AND thread_id <> -1";
        }
        if (BluetoothMapContract.FOLDER_NAME_OUTBOX.equalsIgnoreCase(folder)) {
            return "(type = 4 OR type = 5 OR type = 6) AND thread_id <> -1";
        }
        if ("sent".equalsIgnoreCase(folder)) {
            return "type = 2 AND thread_id <> -1";
        }
        if (BluetoothMapContract.FOLDER_NAME_DRAFT.equalsIgnoreCase(folder)) {
            return "type = 3 AND (thread_id IS NULL OR thread_id <> -1 )";
        }
        if (!BluetoothMapContract.FOLDER_NAME_DELETED.equalsIgnoreCase(folder)) {
            return INetd.NEXTHOP_NONE;
        }
        return "thread_id = -1";
    }

    private String setWhereFilterFolderTypeMms(String folder) {
        if (BluetoothMapContract.FOLDER_NAME_INBOX.equalsIgnoreCase(folder)) {
            return "msg_box = 1 AND thread_id <> -1";
        }
        if (BluetoothMapContract.FOLDER_NAME_OUTBOX.equalsIgnoreCase(folder)) {
            return "msg_box = 4 AND thread_id <> -1";
        }
        if ("sent".equalsIgnoreCase(folder)) {
            return "msg_box = 2 AND thread_id <> -1";
        }
        if (BluetoothMapContract.FOLDER_NAME_DRAFT.equalsIgnoreCase(folder)) {
            return "msg_box = 3 AND (thread_id IS NULL OR thread_id <> -1 )";
        }
        if (!BluetoothMapContract.FOLDER_NAME_DELETED.equalsIgnoreCase(folder)) {
            return INetd.NEXTHOP_NONE;
        }
        return "thread_id = -1";
    }

    private String setWhereFilterFolderTypeEmail(long folderId) {
        if (folderId >= 0) {
            String where = "folder_id = " + folderId;
            return where;
        }
        Log.e(TAG, "setWhereFilterFolderTypeEmail: not valid!");
        throw new IllegalArgumentException("Invalid folder ID");
    }

    private String setWhereFilterFolderTypeIm(long folderId) {
        if (folderId > 0) {
            String where = "folder_id = " + folderId;
            return where;
        }
        Log.e(TAG, "setWhereFilterFolderTypeIm: not valid!");
        throw new IllegalArgumentException("Invalid folder ID");
    }

    private String setWhereFilterFolderType(BluetoothMapFolderElement folderElement, FilterInfo fi) {
        if (folderElement.shouldIgnore()) {
            return "1=1";
        }
        if (fi.mMsgType == 0) {
            String where = setWhereFilterFolderTypeSms(folderElement.getName());
            return where;
        }
        if (fi.mMsgType == 1) {
            String where2 = setWhereFilterFolderTypeMms(folderElement.getName());
            return where2;
        }
        if (fi.mMsgType == 2) {
            String where3 = setWhereFilterFolderTypeEmail(folderElement.getFolderId());
            return where3;
        }
        if (fi.mMsgType != 3) {
            return "1=1";
        }
        String where4 = setWhereFilterFolderTypeIm(folderElement.getFolderId());
        return where4;
    }

    private String setWhereFilterReadStatus(BluetoothMapAppParams ap, FilterInfo fi) {
        String where = INetd.NEXTHOP_NONE;
        if (ap.getFilterReadStatus() == -1) {
            return INetd.NEXTHOP_NONE;
        }
        if (fi.mMsgType == 0) {
            if ((ap.getFilterReadStatus() & 1) != 0) {
                where = " AND read= 0";
            }
            if ((ap.getFilterReadStatus() & 2) != 0) {
                return " AND read= 1";
            }
            return where;
        }
        if (fi.mMsgType == 1) {
            if ((ap.getFilterReadStatus() & 1) != 0) {
                where = " AND read= 0";
            }
            if ((ap.getFilterReadStatus() & 2) != 0) {
                return " AND read= 1";
            }
            return where;
        }
        if (fi.mMsgType != 2 && fi.mMsgType != 3) {
            return INetd.NEXTHOP_NONE;
        }
        if ((ap.getFilterReadStatus() & 1) != 0) {
            where = " AND flag_read= 0";
        }
        if ((ap.getFilterReadStatus() & 2) != 0) {
            return " AND flag_read= 1";
        }
        return where;
    }

    private String setWhereFilterPeriod(BluetoothMapAppParams ap, FilterInfo fi) {
        String where = INetd.NEXTHOP_NONE;
        if (ap.getFilterPeriodBegin() != -1) {
            if (fi.mMsgType == 0) {
                where = " AND date >= " + ap.getFilterPeriodBegin();
            } else if (fi.mMsgType == 1) {
                where = " AND date >= " + (ap.getFilterPeriodBegin() / 1000);
            } else if (fi.mMsgType == 2 || fi.mMsgType == 3) {
                where = " AND date >= " + ap.getFilterPeriodBegin();
            }
        }
        if (ap.getFilterPeriodEnd() != -1) {
            if (fi.mMsgType == 0) {
                return where + " AND date < " + ap.getFilterPeriodEnd();
            }
            if (fi.mMsgType == 1) {
                return where + " AND date < " + (ap.getFilterPeriodEnd() / 1000);
            }
            if (fi.mMsgType == 2 || fi.mMsgType == 3) {
                return where + " AND date < " + ap.getFilterPeriodEnd();
            }
            return where;
        }
        return where;
    }

    private String setWhereFilterLastActivity(BluetoothMapAppParams ap, FilterInfo fi) {
        String where = INetd.NEXTHOP_NONE;
        if (ap.getFilterLastActivityBegin() != -1) {
            if (fi.mMsgType == 0) {
                where = " AND date >= " + ap.getFilterLastActivityBegin();
            } else if (fi.mMsgType == 1) {
                where = " AND date >= " + (ap.getFilterLastActivityBegin() / 1000);
            } else if (fi.mMsgType == 2 || fi.mMsgType == 3) {
                where = " AND last_thread_activity >= " + ap.getFilterPeriodBegin();
            }
        }
        if (ap.getFilterLastActivityEnd() != -1) {
            if (fi.mMsgType == 0) {
                return where + " AND date < " + ap.getFilterLastActivityEnd();
            }
            if (fi.mMsgType == 1) {
                return where + " AND date < " + (ap.getFilterPeriodEnd() / 1000);
            }
            if (fi.mMsgType == 2 || fi.mMsgType == 3) {
                return where + " AND last_thread_activity < " + ap.getFilterLastActivityEnd();
            }
            return where;
        }
        return where;
    }

    private String setWhereFilterOriginatorEmail(BluetoothMapAppParams ap) {
        String orig = ap.getFilterOriginator();
        if (orig == null || orig.length() <= 0) {
            return INetd.NEXTHOP_NONE;
        }
        String where = " AND from_list LIKE '%" + orig.replace("*", "%") + "%'";
        return where;
    }

    private String setWhereFilterOriginatorIM(BluetoothMapAppParams ap) {
        String orig = ap.getFilterOriginator();
        if (orig == null || orig.length() <= 0) {
            return INetd.NEXTHOP_NONE;
        }
        String where = " AND from_list LIKE '%" + orig.replace("*", "%") + "%'";
        return where;
    }

    private String setWhereFilterPriority(BluetoothMapAppParams ap, FilterInfo fi) {
        String where = INetd.NEXTHOP_NONE;
        int pri = ap.getFilterPriority();
        if (fi.mMsgType == 1) {
            if (pri == 2) {
                where = INetd.NEXTHOP_NONE + " AND pri<=" + Integer.toString(MMS_BCC);
            } else if (pri == 1) {
                where = INetd.NEXTHOP_NONE + " AND pri=" + Integer.toString(MMS_CC);
            }
        }
        if (fi.mMsgType == 2 || fi.mMsgType == 3) {
            if (pri == 2) {
                return where + " AND high_priority!=1";
            }
            if (pri == 1) {
                return where + " AND high_priority=1";
            }
            return where;
        }
        return where;
    }

    private String setWhereFilterRecipientEmail(BluetoothMapAppParams ap) {
        String recip = ap.getFilterRecipient();
        if (recip == null || recip.length() <= 0) {
            return INetd.NEXTHOP_NONE;
        }
        String recip2 = recip.replace("*", "%");
        String where = " AND (to_list LIKE '%" + recip2 + "%' OR " + BluetoothMapContract.EmailMessageColumns.CC_LIST + " LIKE '%" + recip2 + "%' OR " + BluetoothMapContract.EmailMessageColumns.BCC_LIST + " LIKE '%" + recip2 + "%' )";
        return where;
    }

    private String setWhereFilterMessageHandle(BluetoothMapAppParams ap, FilterInfo fi) {
        long id = -1;
        String msgHandle = ap.getFilterMsgHandleString();
        if (msgHandle != null) {
            id = BluetoothMapUtils.getCpHandle(msgHandle);
        }
        if (id == -1) {
            return INetd.NEXTHOP_NONE;
        }
        if (fi.mMsgType == 0) {
            String where = " AND _id = " + id;
            return where;
        }
        if (fi.mMsgType == 1) {
            String where2 = " AND _id = " + id;
            return where2;
        }
        if (fi.mMsgType != 2 && fi.mMsgType != 3) {
            return INetd.NEXTHOP_NONE;
        }
        String where3 = " AND _id = " + id;
        return where3;
    }

    private String setWhereFilterThreadId(BluetoothMapAppParams ap, FilterInfo fi) {
        long id = -1;
        String msgHandle = ap.getFilterConvoIdString();
        if (msgHandle != null) {
            id = BluetoothMapUtils.getMsgHandleAsLong(msgHandle);
        }
        if (id <= 0) {
            return INetd.NEXTHOP_NONE;
        }
        if (fi.mMsgType == 0) {
            String where = " AND thread_id = " + id;
            return where;
        }
        if (fi.mMsgType == 1) {
            String where2 = " AND thread_id = " + id;
            return where2;
        }
        if (fi.mMsgType != 2 && fi.mMsgType != 3) {
            return INetd.NEXTHOP_NONE;
        }
        String where3 = " AND thread_id = " + id;
        return where3;
    }

    private String setWhereFilter(BluetoothMapFolderElement folderElement, FilterInfo fi, BluetoothMapAppParams ap) {
        String where = INetd.NEXTHOP_NONE + setWhereFilterFolderType(folderElement, fi);
        String msgHandleWhere = setWhereFilterMessageHandle(ap, fi);
        if (msgHandleWhere.isEmpty()) {
            String where2 = ((where + setWhereFilterReadStatus(ap, fi)) + setWhereFilterPriority(ap, fi)) + setWhereFilterPeriod(ap, fi);
            if (fi.mMsgType == 2) {
                where2 = (where2 + setWhereFilterOriginatorEmail(ap)) + setWhereFilterRecipientEmail(ap);
            }
            if (fi.mMsgType == 3) {
                where2 = where2 + setWhereFilterOriginatorIM(ap);
            }
            return where2 + setWhereFilterThreadId(ap, fi);
        }
        return where + msgHandleWhere;
    }

    private void setConvoWhereFilterSmsMms(StringBuilder selection, ArrayList<String> selectionArgs, FilterInfo fi, BluetoothMapAppParams ap) {
        if (smsSelected(fi, ap) || mmsSelected(ap)) {
            if (ap.getFilterReadStatus() != -1) {
                if ((ap.getFilterReadStatus() & 1) != 0) {
                    selection.append(" AND ");
                    selection.append(BluetoothMapContract.FILTER_READ_STATUS);
                    selection.append(" = 0");
                }
                if ((ap.getFilterReadStatus() & 2) != 0) {
                    selection.append(" AND ");
                    selection.append(BluetoothMapContract.FILTER_READ_STATUS);
                    selection.append(" = 1");
                }
            }
            if (ap.getFilterLastActivityBegin() != -1) {
                selection.append(" AND ");
                selection.append(BluetoothMapContract.MessageColumns.DATE);
                selection.append(" >= ");
                selection.append(ap.getFilterLastActivityBegin());
            }
            if (ap.getFilterLastActivityEnd() != -1) {
                selection.append(" AND ");
                selection.append(BluetoothMapContract.MessageColumns.DATE);
                selection.append(" <= ");
                selection.append(ap.getFilterLastActivityEnd());
            }
            long convoId = -1;
            if (ap.getFilterConvoId() != null) {
                convoId = ap.getFilterConvoId().getLeastSignificantBits();
            }
            if (convoId > 0) {
                selection.append(" AND ");
                selection.append("_id");
                selection.append(" = ");
                selection.append(Long.toString(convoId));
            }
        }
    }

    private boolean smsSelected(FilterInfo fi, BluetoothMapAppParams ap) {
        int msgType = ap.getFilterMessageType();
        int phoneType = fi.mPhoneType;
        if (msgType == -1 || (msgType & 3) == 0) {
            return true;
        }
        if ((msgType & 1) == 0 && phoneType == 1) {
            return true;
        }
        if ((msgType & 2) == 0 && phoneType == 2) {
            return true;
        }
        return false;
    }

    private boolean mmsSelected(BluetoothMapAppParams ap) {
        int msgType = ap.getFilterMessageType();
        if (msgType == -1 || (msgType & 8) == 0) {
            return true;
        }
        return false;
    }

    private boolean emailSelected(BluetoothMapAppParams ap) {
        int msgType = ap.getFilterMessageType();
        if (msgType == -1 || (msgType & 4) == 0) {
            return true;
        }
        return false;
    }

    private boolean imSelected(BluetoothMapAppParams ap) {
        int msgType = ap.getFilterMessageType();
        if (msgType == -1 || (msgType & 16) == 0) {
            return true;
        }
        return false;
    }

    private void setFilterInfo(FilterInfo fi) {
        TelephonyManager tm = (TelephonyManager) this.mContext.getSystemService("phone");
        if (tm != null) {
            fi.mPhoneType = tm.getPhoneType();
            fi.mPhoneNum = tm.getLine1Number();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x02d0 A[Catch: all -> 0x039b, TryCatch #5 {all -> 0x039b, blocks: (B:100:0x02a6, B:101:0x02a9, B:103:0x02af, B:105:0x02ba, B:107:0x02d0, B:109:0x02de, B:111:0x02ea, B:118:0x0303, B:120:0x030b, B:133:0x033d, B:135:0x0370, B:123:0x0315, B:125:0x031d, B:128:0x0328, B:130:0x0330, B:115:0x02f5), top: B:178:0x02a6 }] */
    /* JADX WARN: Removed duplicated region for block: B:133:0x033d A[Catch: all -> 0x039b, TryCatch #5 {all -> 0x039b, blocks: (B:100:0x02a6, B:101:0x02a9, B:103:0x02af, B:105:0x02ba, B:107:0x02d0, B:109:0x02de, B:111:0x02ea, B:118:0x0303, B:120:0x030b, B:133:0x033d, B:135:0x0370, B:123:0x0315, B:125:0x031d, B:128:0x0328, B:130:0x0330, B:115:0x02f5), top: B:178:0x02a6 }] */
    /* JADX WARN: Removed duplicated region for block: B:137:0x037a  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0388  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x038d  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0392  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0397  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0237 A[Catch: all -> 0x039f, PHI: r11 r12 r18
  0x0237: PHI (r11v9 'offsetNum' int) = (r11v6 'offsetNum' int), (r11v6 'offsetNum' int), (r11v7 'offsetNum' int), (r11v7 'offsetNum' int) binds: [B:69:0x01a9, B:71:0x01af, B:76:0x01e4, B:88:0x0235] A[DONT_GENERATE, DONT_INLINE]
  0x0237: PHI (r12v11 'limit' java.lang.String) = 
  (r12v8 'limit' java.lang.String)
  (r12v8 'limit' java.lang.String)
  (r12v9 'limit' java.lang.String)
  (r12v9 'limit' java.lang.String)
 binds: [B:69:0x01a9, B:71:0x01af, B:76:0x01e4, B:88:0x0235] A[DONT_GENERATE, DONT_INLINE]
  0x0237: PHI (r18v10 'emailCursor' android.database.Cursor) = 
  (r18v5 'emailCursor' android.database.Cursor)
  (r18v5 'emailCursor' android.database.Cursor)
  (r18v5 'emailCursor' android.database.Cursor)
  (r18v6 'emailCursor' android.database.Cursor)
 binds: [B:69:0x01a9, B:71:0x01af, B:76:0x01e4, B:88:0x0235] A[DONT_GENERATE, DONT_INLINE], TRY_ENTER, TryCatch #9 {all -> 0x039f, blocks: (B:70:0x01ab, B:72:0x01b1, B:74:0x01b9, B:75:0x01d9, B:77:0x01e6, B:89:0x0237, B:91:0x023d, B:93:0x0243, B:95:0x024b, B:96:0x026b), top: B:186:0x01ab }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.android.bluetooth.map.BluetoothMapMessageListing msgListing(com.android.bluetooth.map.BluetoothMapFolderElement r28, com.android.bluetooth.map.BluetoothMapAppParams r29) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 977
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.map.BluetoothMapContent.msgListing(com.android.bluetooth.map.BluetoothMapFolderElement, com.android.bluetooth.map.BluetoothMapAppParams):com.android.bluetooth.map.BluetoothMapMessageListing");
    }

    public int msgListingSize(BluetoothMapFolderElement folderElement, BluetoothMapAppParams ap) {
        Cursor c;
        int cnt = 0;
        FilterInfo fi = new FilterInfo(this, null);
        setFilterInfo(fi);
        if (smsSelected(fi, ap) && folderElement.hasSmsMmsContent()) {
            fi.mMsgType = 0;
            c = this.mResolver.query(Telephony.Sms.CONTENT_URI, SMS_PROJECTION, setWhereFilter(folderElement, fi, ap), null, "date DESC");
            if (c != null) {
                try {
                    cnt = c.getCount();
                } finally {
                }
            }
            if (c != null) {
                c.close();
            }
        }
        if (mmsSelected(ap) && folderElement.hasSmsMmsContent()) {
            fi.mMsgType = 1;
            Cursor c2 = this.mResolver.query(Telephony.Mms.CONTENT_URI, MMS_PROJECTION, setWhereFilter(folderElement, fi, ap), null, "date DESC");
            if (c2 != null) {
                try {
                    cnt += c2.getCount();
                } finally {
                }
            }
            if (c2 != null) {
                c2.close();
            }
        }
        if (emailSelected(ap) && folderElement.hasEmailContent()) {
            fi.mMsgType = 2;
            String where = setWhereFilter(folderElement, fi, ap);
            if (!where.isEmpty()) {
                Uri contentUri = Uri.parse(this.mBaseUri + BluetoothMapContract.TABLE_MESSAGE);
                c = this.mResolver.query(contentUri, BluetoothMapContract.BT_MESSAGE_PROJECTION, where, null, "date DESC");
                if (c != null) {
                    try {
                        cnt += c.getCount();
                    } finally {
                        if (c != null) {
                            c.close();
                        }
                    }
                }
                if (c != null) {
                    c.close();
                }
            }
        }
        if (imSelected(ap) && folderElement.hasImContent()) {
            fi.mMsgType = 3;
            String where2 = setWhereFilter(folderElement, fi, ap);
            if (!where2.isEmpty()) {
                Uri contentUri2 = Uri.parse(this.mBaseUri + BluetoothMapContract.TABLE_MESSAGE);
                Cursor c3 = this.mResolver.query(contentUri2, BluetoothMapContract.BT_INSTANT_MESSAGE_PROJECTION, where2, null, "date DESC");
                if (c3 != null) {
                    try {
                        cnt += c3.getCount();
                    } finally {
                        if (c3 != null) {
                            c3.close();
                        }
                    }
                }
                if (c3 != null) {
                    c3.close();
                }
            }
        }
        return cnt;
    }

    public boolean msgListingHasUnread(BluetoothMapFolderElement folderElement, BluetoothMapAppParams ap) {
        Cursor c;
        int cnt = 0;
        FilterInfo fi = new FilterInfo(this, null);
        setFilterInfo(fi);
        if (smsSelected(fi, ap) && folderElement.hasSmsMmsContent()) {
            fi.mMsgType = 0;
            c = this.mResolver.query(Telephony.Sms.CONTENT_URI, SMS_PROJECTION, (setWhereFilterFolderType(folderElement, fi) + " AND read=0 ") + setWhereFilterPeriod(ap, fi), null, "date DESC");
            if (c != null) {
                try {
                    cnt = c.getCount();
                } finally {
                }
            }
            if (c != null) {
                c.close();
            }
        }
        if (mmsSelected(ap) && folderElement.hasSmsMmsContent()) {
            fi.mMsgType = 1;
            c = this.mResolver.query(Telephony.Mms.CONTENT_URI, MMS_PROJECTION, (setWhereFilterFolderType(folderElement, fi) + " AND read=0 ") + setWhereFilterPeriod(ap, fi), null, "date DESC");
            if (c != null) {
                try {
                    cnt += c.getCount();
                } finally {
                }
            }
            if (c != null) {
                c.close();
            }
        }
        if (emailSelected(ap) && folderElement.getFolderId() != -1) {
            fi.mMsgType = 2;
            String where = setWhereFilterFolderType(folderElement, fi);
            if (!where.isEmpty()) {
                String where2 = (where + " AND flag_read=0 ") + setWhereFilterPeriod(ap, fi);
                Uri contentUri = Uri.parse(this.mBaseUri + BluetoothMapContract.TABLE_MESSAGE);
                Cursor c2 = this.mResolver.query(contentUri, BluetoothMapContract.BT_MESSAGE_PROJECTION, where2, null, "date DESC");
                if (c2 != null) {
                    try {
                        cnt += c2.getCount();
                    } finally {
                        if (c2 != null) {
                            c2.close();
                        }
                        throw th;
                    }
                }
                if (c2 != null) {
                    c2.close();
                }
            }
        }
        if (imSelected(ap) && folderElement.hasImContent()) {
            fi.mMsgType = 3;
            String where3 = setWhereFilter(folderElement, fi, ap);
            if (!where3.isEmpty()) {
                String where4 = (where3 + " AND flag_read=0 ") + setWhereFilterPeriod(ap, fi);
                Uri contentUri2 = Uri.parse(this.mBaseUri + BluetoothMapContract.TABLE_MESSAGE);
                Cursor c3 = this.mResolver.query(contentUri2, BluetoothMapContract.BT_INSTANT_MESSAGE_PROJECTION, where4, null, "date DESC");
                if (c3 != null) {
                    try {
                        cnt += c3.getCount();
                    } finally {
                        if (c3 != null) {
                            c3.close();
                        }
                        throw th;
                    }
                }
                if (c3 != null) {
                    c3.close();
                }
            }
        }
        return cnt > 0;
    }

    /* JADX WARN: Incorrect condition in loop: B:107:0x023c */
    /* JADX WARN: Removed duplicated region for block: B:140:0x02b7 A[Catch: all -> 0x005c, TryCatch #7 {all -> 0x005c, blocks: (B:14:0x0051, B:120:0x0258, B:122:0x0273, B:142:0x02bc, B:133:0x029b, B:134:0x02a2, B:140:0x02b7, B:101:0x0216, B:105:0x0223, B:106:0x0234, B:108:0x023e, B:23:0x0065, B:25:0x006b, B:27:0x0071, B:34:0x0091, B:36:0x0097), top: B:175:0x0051 }] */
    /* JADX WARN: Removed duplicated region for block: B:142:0x02bc A[Catch: all -> 0x005c, TRY_LEAVE, TryCatch #7 {all -> 0x005c, blocks: (B:14:0x0051, B:120:0x0258, B:122:0x0273, B:142:0x02bc, B:133:0x029b, B:134:0x02a2, B:140:0x02b7, B:101:0x0216, B:105:0x0223, B:106:0x0234, B:108:0x023e, B:23:0x0065, B:25:0x006b, B:27:0x0071, B:34:0x0091, B:36:0x0097), top: B:175:0x0051 }] */
    /* JADX WARN: Removed duplicated region for block: B:187:0x02bf A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.android.bluetooth.map.BluetoothMapConvoListing convoListing(com.android.bluetooth.map.BluetoothMapAppParams r27, boolean r28) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 745
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.map.BluetoothMapContent.convoListing(com.android.bluetooth.map.BluetoothMapAppParams, boolean):com.android.bluetooth.map.BluetoothMapConvoListing");
    }

    /* JADX INFO: renamed from: com.android.bluetooth.map.BluetoothMapContent$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$bluetooth$map$BluetoothMapUtils$TYPE;

        static {
            int[] iArr = new int[BluetoothMapUtils.TYPE.values().length];
            $SwitchMap$com$android$bluetooth$map$BluetoothMapUtils$TYPE = iArr;
            try {
                iArr[BluetoothMapUtils.TYPE.SMS_CDMA.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$android$bluetooth$map$BluetoothMapUtils$TYPE[BluetoothMapUtils.TYPE.SMS_GSM.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$android$bluetooth$map$BluetoothMapUtils$TYPE[BluetoothMapUtils.TYPE.MMS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$android$bluetooth$map$BluetoothMapUtils$TYPE[BluetoothMapUtils.TYPE.EMAIL.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$android$bluetooth$map$BluetoothMapUtils$TYPE[BluetoothMapUtils.TYPE.IM.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    boolean refreshSmsMmsConvoVersions() {
        boolean listChangeDetected = false;
        Uri uri = Telephony.Threads.CONTENT_URI.buildUpon().appendQueryParameter(FtsOptions.TOKENIZER_SIMPLE, "true").build();
        Cursor cursor = this.mResolver.query(uri, MMS_SMS_THREAD_PROJECTION, null, null, "date DESC");
        if (cursor != null) {
            try {
                cursor.moveToPosition(-1);
                synchronized (getSmsMmsConvoList()) {
                    int size = Math.max(getSmsMmsConvoList().size(), cursor.getCount());
                    HashMap<Long, BluetoothMapConvoListingElement> newList = new HashMap<>(size);
                    while (cursor.moveToNext()) {
                        boolean convoChanged = false;
                        Long id = Long.valueOf(cursor.getLong(MMS_SMS_THREAD_COL_ID));
                        BluetoothMapConvoListingElement convoElement = getSmsMmsConvoList().remove(id);
                        if (convoElement == null) {
                            convoElement = new BluetoothMapConvoListingElement();
                            convoElement.setConvoId(1L, id.longValue());
                            listChangeDetected = true;
                            convoElement.setVersionCounter(0L);
                        }
                        long lastActivity = cursor.getLong(MMS_SMS_THREAD_COL_DATE);
                        boolean z = true;
                        if (cursor.getInt(MMS_SMS_THREAD_COL_READ) != 1) {
                            z = false;
                        }
                        boolean read = z;
                        if (lastActivity != convoElement.getLastActivity()) {
                            convoChanged = true;
                            convoElement.setLastActivity(lastActivity);
                        }
                        if (read != convoElement.getReadBool()) {
                            convoChanged = true;
                            convoElement.setRead(read, false);
                        }
                        String idsStr = cursor.getString(MMS_SMS_THREAD_COL_RECIPIENT_IDS);
                        if (!idsStr.equals(convoElement.getSmsMmsContacts())) {
                            listChangeDetected = true;
                            convoElement.setSmsMmsContacts(idsStr);
                        }
                        if (convoChanged) {
                            listChangeDetected = true;
                            convoElement.incrementVersionCounter();
                        }
                        newList.put(id, convoElement);
                    }
                    if (getSmsMmsConvoList().size() != 0) {
                        listChangeDetected = true;
                    }
                    setSmsMmsConvoList(newList);
                }
                if (listChangeDetected) {
                    this.mMasInstance.updateSmsMmsConvoListVersionCounter();
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        return listChangeDetected;
    }

    /* JADX WARN: Incorrect condition in loop: B:35:0x00e6 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    boolean refreshImEmailConvoVersions() {
        /*
            Method dump skipped, instruction units count: 335
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.map.BluetoothMapContent.refreshImEmailConvoVersions():boolean");
    }

    private void updateSmsMmsConvoVersion(Cursor cursor, BluetoothMapConvoListingElement ele) {
        long id = ele.getCpConvoId();
        BluetoothMapConvoListingElement convoElement = getSmsMmsConvoList().get(Long.valueOf(id));
        boolean listChangeDetected = false;
        boolean convoChanged = false;
        if (convoElement == null) {
            convoElement = new BluetoothMapConvoListingElement();
            getSmsMmsConvoList().put(Long.valueOf(id), convoElement);
            convoElement.setConvoId(1L, id);
            listChangeDetected = true;
            convoElement.setVersionCounter(0L);
        }
        long lastActivity = cursor.getLong(MMS_SMS_THREAD_COL_DATE);
        boolean read = cursor.getInt(MMS_SMS_THREAD_COL_READ) == 1;
        if (lastActivity != convoElement.getLastActivity()) {
            convoChanged = true;
            convoElement.setLastActivity(lastActivity);
        }
        if (read != convoElement.getReadBool()) {
            convoChanged = true;
            convoElement.setRead(read, false);
        }
        if (convoChanged) {
            listChangeDetected = true;
            convoElement.incrementVersionCounter();
        }
        if (listChangeDetected) {
            this.mMasInstance.updateSmsMmsConvoListVersionCounter();
        }
        ele.setVersionCounter(convoElement.getVersionCounter());
    }

    private void updateImEmailConvoVersion(Cursor cursor, FilterInfo fi, BluetoothMapConvoListingElement ele) {
        long id = ele.getCpConvoId();
        BluetoothMapConvoListingElement convoElement = getImEmailConvoList().get(Long.valueOf(id));
        boolean listChangeDetected = false;
        boolean convoChanged = false;
        if (convoElement == null) {
            convoElement = new BluetoothMapConvoListingElement();
            convoElement.setConvoId(2L, id);
            getImEmailConvoList().put(Long.valueOf(id), convoElement);
            listChangeDetected = true;
            convoElement.setVersionCounter(0L);
        }
        String name = cursor.getString(fi.mConvoColName);
        long lastActivity = cursor.getLong(fi.mConvoColLastActivity);
        boolean read = cursor.getInt(fi.mConvoColRead) == 1;
        if (lastActivity != convoElement.getLastActivity()) {
            convoChanged = true;
            convoElement.setLastActivity(lastActivity);
        }
        if (read != convoElement.getReadBool()) {
            convoChanged = true;
            convoElement.setRead(read, false);
        }
        if (name != null && !name.equals(convoElement.getName())) {
            convoChanged = true;
            convoElement.setName(name);
        }
        if (convoChanged) {
            listChangeDetected = true;
            convoElement.incrementVersionCounter();
        }
        if (listChangeDetected) {
            this.mMasInstance.updateImEmailConvoListVersionCounter();
        }
        ele.setVersionCounter(convoElement.getVersionCounter());
    }

    private void populateSmsMmsConvoElement(BluetoothMapConvoListingElement ele, Cursor smsMmsCursor, BluetoothMapAppParams ap, SmsMmsContacts contacts) {
        smsMmsCursor.moveToPosition(ele.getCursorIndex());
        int parameterMask = (int) ap.getConvoParameterMask();
        ele.setConvoId(1L, smsMmsCursor.getLong(MMS_SMS_THREAD_COL_ID));
        boolean read = smsMmsCursor.getInt(MMS_SMS_THREAD_COL_READ) == 1;
        if ((parameterMask & 4) != 0) {
            ele.setRead(read, true);
        } else {
            ele.setRead(read, false);
        }
        if ((parameterMask & 2) != 0) {
            long timeStamp = smsMmsCursor.getLong(MMS_SMS_THREAD_COL_DATE);
            ele.setLastActivity(timeStamp);
        } else {
            ele.setLastActivity(-1L);
        }
        if ((parameterMask & 8) != 0) {
            updateSmsMmsConvoVersion(smsMmsCursor, ele);
        }
        if ((parameterMask & 1) != 0) {
            ele.setName(INetd.NEXTHOP_NONE);
        }
        if ((parameterMask & 16) != 0) {
            String summary = smsMmsCursor.getString(MMS_SMS_THREAD_COL_SNIPPET);
            String cs = smsMmsCursor.getString(MMS_SMS_THREAD_COL_SNIPPET_CS);
            if (summary != null && cs != null && !cs.equals("UTF-8")) {
                try {
                    summary = new String(summary.getBytes(cs), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    Log.e(TAG, "populateSmsMmsConvoElement: " + e);
                }
            }
            ele.setSummary(summary);
        }
        if ((parameterMask & 32) != 0 && ap.getFilterRecipient() == null) {
            String idsStr = smsMmsCursor.getString(MMS_SMS_THREAD_COL_RECIPIENT_IDS);
            addSmsMmsContacts(ele, contacts, idsStr, null, ap);
        }
    }

    private void populateImEmailConvoElement(BluetoothMapConvoListingElement ele, Cursor tmpCursor, BluetoothMapAppParams ap, FilterInfo fi) {
        tmpCursor.moveToPosition(ele.getCursorIndex());
        int parameterMask = (int) ap.getConvoParameterMask();
        long threadId = tmpCursor.getLong(fi.mConvoColConvoId);
        ele.setConvoId(2L, threadId);
        if ((parameterMask & 1) != 0) {
            ele.setName(tmpCursor.getString(fi.mConvoColName));
        }
        boolean reportRead = false;
        if ((parameterMask & 4) != 0) {
            reportRead = true;
        }
        ele.setRead(1 == tmpCursor.getInt(fi.mConvoColRead), reportRead);
        long timestamp = tmpCursor.getLong(fi.mConvoColLastActivity);
        if ((parameterMask & 2) == 0) {
            ele.setLastActivity(-1L);
        } else {
            ele.setLastActivity(timestamp);
        }
        if ((parameterMask & 8) != 0) {
            updateImEmailConvoVersion(tmpCursor, fi, ele);
        }
        if ((parameterMask & 16) != 0) {
            ele.setSummary(tmpCursor.getString(fi.mConvoColSummary));
        }
        if ((parameterMask & 32) != 0) {
            do {
                BluetoothMapConvoContactElement c = new BluetoothMapConvoContactElement();
                if ((parameterMask & 1024) != 0) {
                    c.setBtUid(new SignedLongLong(tmpCursor.getLong(fi.mContactColBtUid), 0L));
                }
                if ((parameterMask & 256) != 0) {
                    c.setChatState(tmpCursor.getInt(fi.mContactColChatState));
                }
                if ((parameterMask & 4096) != 0) {
                    c.setPresenceAvailability(tmpCursor.getInt(fi.mContactColPresenceState));
                }
                if ((parameterMask & 8192) != 0) {
                    c.setPresenceStatus(tmpCursor.getString(fi.mContactColPresenceText));
                }
                if ((parameterMask & 16384) != 0) {
                    c.setPriority(tmpCursor.getInt(fi.mContactColPriority));
                }
                if ((parameterMask & 128) != 0) {
                    c.setDisplayName(tmpCursor.getString(fi.mContactColNickname));
                }
                if ((parameterMask & 64) != 0) {
                    c.setContactId(tmpCursor.getString(fi.mContactColContactUci));
                }
                if ((parameterMask & 512) != 0) {
                    c.setLastActivity(tmpCursor.getLong(fi.mContactColLastActive));
                }
                if ((parameterMask & 2048) != 0) {
                    c.setName(tmpCursor.getString(fi.mContactColName));
                }
                ele.addContact(c);
                if (!tmpCursor.moveToNext()) {
                    return;
                }
            } while (tmpCursor.getLong(fi.mConvoColConvoId) == threadId);
        }
    }

    private Uri appendConvoListQueryParameters(BluetoothMapAppParams ap, Uri contentUri) {
        Uri.Builder newUri = contentUri.buildUpon();
        String str = ap.getFilterRecipient();
        if (str != null) {
            newUri.appendQueryParameter(BluetoothMapContract.FILTER_ORIGINATOR_SUBSTRING, str.trim().replace("*", "%"));
        }
        long time = ap.getFilterLastActivityBegin();
        if (time > 0) {
            newUri.appendQueryParameter(BluetoothMapContract.FILTER_PERIOD_BEGIN, Long.toString(time));
        }
        long time2 = ap.getFilterLastActivityEnd();
        if (time2 > 0) {
            newUri.appendQueryParameter(BluetoothMapContract.FILTER_PERIOD_END, Long.toString(time2));
        }
        int readStatus = ap.getFilterReadStatus();
        if (readStatus > 0) {
            if (readStatus == 1) {
                newUri.appendQueryParameter(BluetoothMapContract.FILTER_READ_STATUS, "false");
            } else if (readStatus == 2) {
                newUri.appendQueryParameter(BluetoothMapContract.FILTER_READ_STATUS, "true");
            }
        }
        long convoId = -1;
        if (ap.getFilterConvoId() != null) {
            convoId = ap.getFilterConvoId().getLeastSignificantBits();
        }
        if (convoId > 0) {
            newUri.appendQueryParameter("thread_id", Long.toString(convoId));
        }
        return newUri.build();
    }

    private boolean addSmsMmsContacts(BluetoothMapConvoListingElement convoElement, SmsMmsContacts contacts, String idsStr, String recipientFilter, BluetoothMapAppParams ap) {
        String recipientFilter2;
        BluetoothMapConvoContactElement contactElement;
        boolean foundContact;
        int parameterMask = (int) ap.getConvoParameterMask();
        String[] ids = idsStr.split(" ");
        long[] longIds = new long[ids.length];
        if (recipientFilter == null) {
            recipientFilter2 = recipientFilter;
        } else {
            recipientFilter2 = recipientFilter.trim();
        }
        boolean foundContact2 = false;
        for (int i = 0; i < ids.length; i++) {
            try {
                long longId = Long.parseLong(ids[i]);
                longIds[i] = longId;
                if (recipientFilter2 == null) {
                    foundContact2 = true;
                } else {
                    String addr = contacts.getPhoneNumber(this.mResolver, longId);
                    if (addr != null && contacts.getContactNameFromPhone(addr, this.mResolver, recipientFilter2) != null) {
                        foundContact2 = true;
                    }
                }
            } catch (NumberFormatException e) {
            }
        }
        if (foundContact2) {
            boolean foundContact3 = false;
            for (long id : longIds) {
                String addr2 = contacts.getPhoneNumber(this.mResolver, id);
                if (addr2 != null) {
                    MapContact contact = contacts.getContactNameFromPhone(addr2, this.mResolver);
                    if (contact == null) {
                        contactElement = new BluetoothMapConvoContactElement();
                        if ((parameterMask & 2048) != 0) {
                            contactElement.setName(addr2);
                        }
                        if ((parameterMask & 64) != 0) {
                            contactElement.setContactId(addr2);
                        }
                        foundContact = true;
                    } else {
                        contactElement = BluetoothMapConvoContactElement.createFromMapContact(contact, addr2);
                        foundContact = true;
                        if ((parameterMask & 64) == 0) {
                            contactElement.setContactId(null);
                        }
                        if ((parameterMask & 1024) == 0) {
                            contactElement.setBtUid(null);
                        }
                        if ((parameterMask & 128) == 0) {
                            contactElement.setDisplayName(null);
                        }
                    }
                    convoElement.addContact(contactElement);
                    foundContact3 = foundContact;
                }
            }
            return foundContact3;
        }
        return foundContact2;
    }

    private String getFolderName(int type, int threadId) {
        if (threadId == -1) {
            return BluetoothMapContract.FOLDER_NAME_DELETED;
        }
        switch (type) {
            case 1:
                return BluetoothMapContract.FOLDER_NAME_INBOX;
            case 2:
                return "sent";
            case 3:
                return BluetoothMapContract.FOLDER_NAME_DRAFT;
            case 4:
            case 5:
            case 6:
                return BluetoothMapContract.FOLDER_NAME_OUTBOX;
            default:
                return INetd.NEXTHOP_NONE;
        }
    }

    public byte[] getMessage(String handle, BluetoothMapAppParams appParams, BluetoothMapFolderElement folderElement, String version) throws UnsupportedEncodingException {
        BluetoothMapUtils.TYPE type = BluetoothMapUtils.getMsgTypeFromHandle(handle);
        this.mMessageVersion = version;
        long id = BluetoothMapUtils.getCpHandle(handle);
        if (appParams.getFractionRequest() == 1) {
            throw new IllegalArgumentException("FRACTION_REQUEST_NEXT does not make sence as we always return the full message.");
        }
        int i = AnonymousClass1.$SwitchMap$com$android$bluetooth$map$BluetoothMapUtils$TYPE[type.ordinal()];
        if (i == 1 || i == 2) {
            return getSmsMessage(id, appParams.getCharset());
        }
        if (i == 3) {
            return getMmsMessage(id, appParams);
        }
        if (i == 4) {
            return getEmailMessage(id, appParams, folderElement);
        }
        if (i == 5) {
            return getIMMessage(id, appParams, folderElement);
        }
        throw new IllegalArgumentException("Invalid message handle.");
    }

    private String setVCardFromPhoneNumber(BluetoothMapbMessage message, String phone, boolean incoming) {
        String[] emailAddresses;
        String contactId = null;
        String contactName = null;
        String[] phoneNumbers = new String[1];
        if (TextUtils.isEmpty(phone)) {
            return null;
        }
        phoneNumbers[0] = phone;
        String[] emailAddresses2 = null;
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.ENTERPRISE_CONTENT_FILTER_URI, Uri.encode(phone));
        String[] projection = {"_id", "display_name"};
        Cursor p = this.mResolver.query(uri, projection, "in_visible_group=1", null, "_id ASC");
        if (p != null) {
            try {
                if (p.moveToFirst()) {
                    contactId = p.getString(p.getColumnIndex("_id"));
                    contactName = p.getString(p.getColumnIndex("display_name"));
                }
            } finally {
                close(p);
            }
        }
        if (contactId == null) {
            emailAddresses = null;
        } else {
            Cursor q = null;
            try {
                q = this.mResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, "contact_id = ?", new String[]{contactId}, null);
                if (q != null && q.moveToFirst()) {
                    int i = 0;
                    emailAddresses2 = new String[q.getCount()];
                    while (true) {
                        String emailAddress = q.getString(q.getColumnIndex("data1"));
                        int i2 = i + 1;
                        emailAddresses2[i] = emailAddress;
                        if (q == null || !q.moveToNext()) {
                            break;
                        }
                        i = i2;
                    }
                }
                close(q);
                emailAddresses = emailAddresses2;
            } catch (Throwable th) {
                close(q);
                throw th;
            }
        }
        if (incoming) {
            message.addOriginator(contactName, contactName, phoneNumbers, emailAddresses, null, null);
        } else {
            message.addRecipient(contactName, contactName, phoneNumbers, emailAddresses, null, null);
        }
        return contactName;
    }

    public byte[] getSmsMessage(long id, int charset) throws UnsupportedEncodingException {
        BluetoothMapbMessageSms message = new BluetoothMapbMessageSms();
        TelephonyManager tm = (TelephonyManager) this.mContext.getSystemService("phone");
        Cursor c = this.mResolver.query(Telephony.Sms.CONTENT_URI, SMS_PROJECTION, "_ID = " + id, null, null);
        if (c == null || !c.moveToFirst()) {
            throw new IllegalArgumentException("SMS handle not found");
        }
        if (c != null) {
            try {
                if (c.moveToFirst()) {
                    if (tm.getPhoneType() == 2) {
                        message.setType(BluetoothMapUtils.TYPE.SMS_CDMA);
                    } else {
                        message.setType(BluetoothMapUtils.TYPE.SMS_GSM);
                    }
                    message.setVersionString(this.mMessageVersion);
                    String read = c.getString(c.getColumnIndex(BluetoothMapContract.FILTER_READ_STATUS));
                    if (read.equalsIgnoreCase("1")) {
                        message.setStatus(true);
                    } else {
                        message.setStatus(false);
                    }
                    int type = c.getInt(c.getColumnIndex(BluetoothMapContentObserver.EXTRA_MESSAGE_SENT_MSG_TYPE));
                    int threadId = c.getInt(c.getColumnIndex("thread_id"));
                    message.setFolder(getFolderName(type, threadId));
                    String msgBody = c.getString(c.getColumnIndex(BluetoothMapContract.MessageColumns.BODY));
                    String phone = c.getString(c.getColumnIndex("address"));
                    if (phone == null && type == 3) {
                        phone = getCanonicalAddressSms(this.mResolver, threadId);
                    }
                    long time = c.getLong(c.getColumnIndex(BluetoothMapContract.MessageColumns.DATE));
                    if (type == 1) {
                        setVCardFromPhoneNumber(message, phone, true);
                    } else {
                        setVCardFromPhoneNumber(message, phone, false);
                    }
                    if (charset == 0) {
                        if (type == 1) {
                            message.setSmsBodyPdus(BluetoothMapSmsPdu.getDeliverPdus(this.mContext, msgBody, phone, time));
                        } else {
                            message.setSmsBodyPdus(BluetoothMapSmsPdu.getSubmitPdus(this.mContext, msgBody, phone));
                        }
                    } else {
                        message.setSmsBody(msgBody);
                    }
                    return message.encode();
                }
            } finally {
                if (c != null) {
                    c.close();
                }
            }
        }
        if (c != null) {
            c.close();
        }
        return message.encode();
    }

    private void extractMmsAddresses(long id, BluetoothMapbMessageMime message) {
        String selection = new String("msg_id=" + id);
        String uriStr = new String(Telephony.Mms.CONTENT_URI + "/" + id + "/addr");
        Uri uriAddress = Uri.parse(uriStr);
        Cursor c = this.mResolver.query(uriAddress, null, selection, null, null);
        try {
            if (c.moveToFirst()) {
                do {
                    String address = c.getString(c.getColumnIndex("address"));
                    if (!address.equals(INSERT_ADDRES_TOKEN)) {
                        Integer type = Integer.valueOf(c.getInt(c.getColumnIndex(BluetoothMapContentObserver.EXTRA_MESSAGE_SENT_MSG_TYPE)));
                        int iIntValue = type.intValue();
                        if (iIntValue == 129) {
                            String contactName = setVCardFromPhoneNumber(message, address, false);
                            message.addBcc(contactName, address);
                        } else if (iIntValue == 130) {
                            String contactName2 = setVCardFromPhoneNumber(message, address, false);
                            message.addCc(contactName2, address);
                        } else if (iIntValue == 137) {
                            String contactName3 = setVCardFromPhoneNumber(message, address, true);
                            message.addFrom(contactName3, address);
                        } else if (iIntValue == 151) {
                            String contactName4 = setVCardFromPhoneNumber(message, address, false);
                            message.addTo(contactName4, address);
                        }
                    }
                } while (c.moveToNext());
            }
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    private byte[] readRawDataPart(Uri contentPartUri, long partid) {
        String uriStr = new String(contentPartUri + "/" + partid);
        Uri uriAddress = Uri.parse(uriStr);
        InputStream is = null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[8192];
        byte[] retVal = null;
        try {
            try {
                is = this.mResolver.openInputStream(uriAddress);
                while (true) {
                    int len = is.read(buffer);
                    if (len == -1) {
                        break;
                    }
                    os.write(buffer, 0, len);
                }
                retVal = os.toByteArray();
            } catch (IOException e) {
                Log.w(TAG, "Error reading part data", e);
            }
            return retVal;
        } finally {
            close(os);
            close(is);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0162 A[LOOP:0: B:5:0x0050->B:36:0x0162, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x015e A[EDGE_INSN: B:55:0x015e->B:34:0x015e BREAK  A[LOOP:0: B:5:0x0050->B:36:0x0162], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void extractMmsParts(long r24, com.android.bluetooth.map.BluetoothMapbMessageMime r26) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 377
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.map.BluetoothMapContent.extractMmsParts(long, com.android.bluetooth.map.BluetoothMapbMessageMime):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x012d A[LOOP:0: B:5:0x0059->B:37:0x012d, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0135 A[EDGE_INSN: B:59:0x0135->B:41:0x0135 BREAK  A[LOOP:0: B:5:0x0059->B:37:0x012d], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void extractIMParts(long r20, com.android.bluetooth.map.BluetoothMapbMessageMime r22) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 326
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.map.BluetoothMapContent.extractIMParts(long, com.android.bluetooth.map.BluetoothMapbMessageMime):void");
    }

    public byte[] getMmsMessage(long id, BluetoothMapAppParams appParams) throws UnsupportedEncodingException {
        if (appParams.getCharset() == 0) {
            throw new IllegalArgumentException("MMS charset native not allowed for MMS - must be utf-8");
        }
        BluetoothMapbMessageMime message = new BluetoothMapbMessageMime();
        Cursor c = this.mResolver.query(Telephony.Mms.CONTENT_URI, MMS_PROJECTION, "_ID = " + id, null, null);
        if (c != null) {
            try {
                if (c.moveToFirst()) {
                    message.setType(BluetoothMapUtils.TYPE.MMS);
                    message.setVersionString(this.mMessageVersion);
                    String read = c.getString(c.getColumnIndex(BluetoothMapContract.FILTER_READ_STATUS));
                    boolean z = true;
                    if (read.equalsIgnoreCase("1")) {
                        message.setStatus(true);
                    } else {
                        message.setStatus(false);
                    }
                    int msgBox = c.getInt(c.getColumnIndex("msg_box"));
                    int threadId = c.getInt(c.getColumnIndex("thread_id"));
                    message.setFolder(getFolderName(msgBox, threadId));
                    message.setSubject(c.getString(c.getColumnIndex("sub")));
                    message.setMessageId(c.getString(c.getColumnIndex("m_id")));
                    message.setContentType(c.getString(c.getColumnIndex("ct_t")));
                    message.setDate(c.getLong(c.getColumnIndex(BluetoothMapContract.MessageColumns.DATE)) * 1000);
                    message.setTextOnly(c.getInt(c.getColumnIndex("text_only")) != 0);
                    if (appParams.getAttachment() == 0) {
                        z = false;
                    }
                    message.setIncludeAttachments(z);
                    extractMmsParts(id, message);
                    extractMmsAddresses(id, message);
                    return message.encode();
                }
            } finally {
                if (c != null) {
                    c.close();
                }
            }
        }
        if (c != null) {
            c.close();
        }
        return message.encode();
    }

    /* JADX WARN: Can't wrap try/catch for region: R(15:170|54|197|55|56|200|57|(5:58|(1:60)(1:204)|130|(1:132)|133)|61|191|62|(2:174|66)|130|(0)|133) */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0267  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0288 A[Catch: all -> 0x028d, TryCatch #18 {all -> 0x028d, blocks: (B:6:0x0045, B:155:0x0288, B:157:0x028c, B:46:0x017e, B:47:0x0181, B:52:0x018d, B:62:0x01e0, B:66:0x01e7, B:130:0x0261, B:137:0x026f, B:143:0x0278, B:149:0x0280, B:90:0x021e, B:96:0x0227, B:104:0x0237, B:110:0x0240, B:118:0x0250, B:124:0x0259), top: B:190:0x0045 }] */
    /* JADX WARN: Type inference failed for: r14v10, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r14v11, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r14v12 */
    /* JADX WARN: Type inference failed for: r14v13, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r14v14 */
    /* JADX WARN: Type inference failed for: r14v15 */
    /* JADX WARN: Type inference failed for: r14v16 */
    /* JADX WARN: Type inference failed for: r14v17 */
    /* JADX WARN: Type inference failed for: r14v18 */
    /* JADX WARN: Type inference failed for: r14v19 */
    /* JADX WARN: Type inference failed for: r14v2 */
    /* JADX WARN: Type inference failed for: r14v21 */
    /* JADX WARN: Type inference failed for: r14v24 */
    /* JADX WARN: Type inference failed for: r14v3 */
    /* JADX WARN: Type inference failed for: r14v4 */
    /* JADX WARN: Type inference failed for: r14v5 */
    /* JADX WARN: Type inference failed for: r14v6 */
    /* JADX WARN: Type inference failed for: r14v7 */
    /* JADX WARN: Type inference failed for: r14v8 */
    /* JADX WARN: Type inference failed for: r14v9, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r8v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public byte[] getEmailMessage(long r29, com.android.bluetooth.map.BluetoothMapAppParams r31, com.android.bluetooth.map.BluetoothMapFolderElement r32) throws java.io.UnsupportedEncodingException {
        /*
            Method dump skipped, instruction units count: 681
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.map.BluetoothMapContent.getEmailMessage(long, com.android.bluetooth.map.BluetoothMapAppParams, com.android.bluetooth.map.BluetoothMapFolderElement):byte[]");
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0217  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public byte[] getIMMessage(long r28, com.android.bluetooth.map.BluetoothMapAppParams r30, com.android.bluetooth.map.BluetoothMapFolderElement r31) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 573
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.map.BluetoothMapContent.getIMMessage(long, com.android.bluetooth.map.BluetoothMapAppParams, com.android.bluetooth.map.BluetoothMapFolderElement):byte[]");
    }

    public void setRemoteFeatureMask(int featureMask) {
        this.mRemoteFeatureMask = featureMask;
        if ((featureMask & 512) == 512) {
            this.mMsgListingVersion = 11;
        }
    }

    public int getRemoteFeatureMask() {
        return this.mRemoteFeatureMask;
    }

    HashMap<Long, BluetoothMapConvoListingElement> getSmsMmsConvoList() {
        return this.mMasInstance.getSmsMmsConvoList();
    }

    void setSmsMmsConvoList(HashMap<Long, BluetoothMapConvoListingElement> smsMmsConvoList) {
        this.mMasInstance.setSmsMmsConvoList(smsMmsConvoList);
    }

    HashMap<Long, BluetoothMapConvoListingElement> getImEmailConvoList() {
        return this.mMasInstance.getImEmailConvoList();
    }

    void setImEmailConvoList(HashMap<Long, BluetoothMapConvoListingElement> imEmailConvoList) {
        this.mMasInstance.setImEmailConvoList(imEmailConvoList);
    }
}
