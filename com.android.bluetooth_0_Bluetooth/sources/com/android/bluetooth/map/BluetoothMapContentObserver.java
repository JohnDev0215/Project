package com.android.bluetooth.map;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.INetd;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.os.UserManager;
import android.provider.Telephony;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SmsManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.Xml;
import androidx.core.app.NotificationCompat;
import com.android.bluetooth.BluetoothStatsLog;
import com.android.bluetooth.Utils;
import com.android.bluetooth.map.BluetoothMapUtils;
import com.android.bluetooth.map.BluetoothMapbMessageMime;
import com.android.bluetooth.mapapi.BluetoothMapContract;
import com.android.vcard.VCardBuilder;
import com.android.vcard.VCardConfig;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothMapContentObserver {
    private static final String ACTION_MESSAGE_DELIVERY = "com.android.bluetooth.BluetoothMapContentObserver.action.MESSAGE_DELIVERY";
    static final String ACTION_MESSAGE_SENT = "com.android.bluetooth.BluetoothMapContentObserver.action.MESSAGE_SENT";
    private static final int CONVERT_MMS_TO_SMS_PART_COUNT = 10;
    private static final boolean D = false;
    public static final int DELETED_THREAD_ID = -1;
    private static final long EVENT_FILTER_CONVERSATION_CHANGED = 1024;
    private static final long EVENT_FILTER_DELIVERY_FAILED = 64;
    private static final long EVENT_FILTER_DELIVERY_SUCCESS = 32;
    private static final long EVENT_FILTER_MEMORY_AVAILABLE = 256;
    private static final long EVENT_FILTER_MEMORY_FULL = 128;
    private static final long EVENT_FILTER_MESSAGE_DELETED = 2;
    private static final long EVENT_FILTER_MESSAGE_REMOVED = 16384;
    private static final long EVENT_FILTER_MESSAGE_SHIFT = 4;
    private static final long EVENT_FILTER_NEW_MESSAGE = 1;
    private static final long EVENT_FILTER_PARTICIPANT_CHATSTATE_CHANGED = 4096;
    private static final long EVENT_FILTER_PARTICIPANT_PRESENCE_CHANGED = 2048;
    private static final long EVENT_FILTER_READ_STATUS_CHANGED = 512;
    private static final long EVENT_FILTER_SENDING_FAILED = 16;
    private static final long EVENT_FILTER_SENDING_SUCCESS = 8;
    private static final String EVENT_TYPE_CHAT_STATE = "ParticipantChatStateChanged";
    private static final String EVENT_TYPE_CONVERSATION = "ConversationChanged";
    private static final String EVENT_TYPE_DELETE = "MessageDeleted";
    private static final String EVENT_TYPE_DELEVERY_SUCCESS = "DeliverySuccess";
    private static final String EVENT_TYPE_DELIVERY_FAILURE = "DeliveryFailure";
    private static final String EVENT_TYPE_NEW = "NewMessage";
    private static final String EVENT_TYPE_PRESENCE = "ParticipantPresenceChanged";
    private static final String EVENT_TYPE_READ_STATUS = "ReadStatusChanged";
    private static final String EVENT_TYPE_REMOVED = "MessageRemoved";
    private static final String EVENT_TYPE_SENDING_FAILURE = "SendingFailure";
    private static final String EVENT_TYPE_SENDING_SUCCESS = "SendingSuccess";
    private static final String EVENT_TYPE_SHIFT = "MessageShift";
    public static final String EXTRA_MESSAGE_SENT_HANDLE = "HANDLE";
    public static final String EXTRA_MESSAGE_SENT_RESULT = "result";
    public static final String EXTRA_MESSAGE_SENT_RETRY = "retry";
    public static final String EXTRA_MESSAGE_SENT_TIMESTAMP = "timestamp";
    public static final String EXTRA_MESSAGE_SENT_TRANSPARENT = "transparent";
    public static final String EXTRA_MESSAGE_SENT_URI = "uri";
    private static final HashMap<Integer, String> FOLDER_MMS_MAP;
    private static final HashMap<Integer, String> FOLDER_SMS_MAP;
    private static final String[] ID_PROJECTION;
    public static final int MESSAGE_TYPE_RETRIEVE_CONF = 132;
    private static final long PROVIDER_ANR_TIMEOUT = 20000;
    private static final String TAG = "BluetoothMapContentObserver";
    private static final Uri UPDATE_STATUS_URI;
    private static final boolean V = false;
    private BluetoothMapAccountItem mAccount;
    private String mAuthority;
    private CeBroadcastReceiver mCeBroadcastReceiver;
    private Map<String, BluetoothMapConvoContactElement> mContactList;
    private Uri mContactUri;
    private Context mContext;
    private boolean mEnableSmsMms;
    private int mMasId;
    private BluetoothMapMasInstance mMasInstance;
    private Uri mMessageUri;
    private BluetoothMnsObexClient mMnsClient;
    private Map<Long, Msg> mMsgListMms;
    private Map<Long, Msg> mMsgListMsg;
    private Map<Long, Msg> mMsgListSms;
    private ContentProviderClient mProviderClient;
    private ContentResolver mResolver;
    private SmsBroadcastReceiver mSmsBroadcastReceiver;
    private BluetoothMapUtils.TYPE mSmsType;
    public static final String EXTRA_MESSAGE_SENT_MSG_TYPE = "type";
    static final String[] SMS_PROJECTION = {"_id", "thread_id", "address", BluetoothMapContract.MessageColumns.BODY, BluetoothMapContract.MessageColumns.DATE, BluetoothMapContract.FILTER_READ_STATUS, EXTRA_MESSAGE_SENT_MSG_TYPE, "status", "locked", "error_code"};
    static final String[] SMS_PROJECTION_SHORT = {"_id", "thread_id", EXTRA_MESSAGE_SENT_MSG_TYPE, BluetoothMapContract.FILTER_READ_STATUS};
    static final String[] SMS_PROJECTION_SHORT_EXT = {"_id", "thread_id", "address", BluetoothMapContract.MessageColumns.BODY, BluetoothMapContract.MessageColumns.DATE, BluetoothMapContract.FILTER_READ_STATUS, EXTRA_MESSAGE_SENT_MSG_TYPE};
    static final String[] MMS_PROJECTION_SHORT = {"_id", "thread_id", "m_type", "msg_box", BluetoothMapContract.FILTER_READ_STATUS};
    static final String[] MMS_PROJECTION_SHORT_EXT = {"_id", "thread_id", "m_type", "msg_box", BluetoothMapContract.FILTER_READ_STATUS, BluetoothMapContract.MessageColumns.DATE, "sub", "pri"};
    static final String[] MSG_PROJECTION_SHORT = {"_id", BluetoothMapContract.MessageColumns.FOLDER_ID, BluetoothMapContract.MessageColumns.FLAG_READ};
    static final String[] MSG_PROJECTION_SHORT_EXT = {"_id", BluetoothMapContract.MessageColumns.FOLDER_ID, BluetoothMapContract.MessageColumns.FLAG_READ, BluetoothMapContract.MessageColumns.DATE, BluetoothMapContract.MessageColumns.SUBJECT, BluetoothMapContract.MessageColumns.FROM_LIST, BluetoothMapContract.MessageColumns.FLAG_HIGH_PRIORITY};
    static final String[] MSG_PROJECTION_SHORT_EXT2 = {"_id", BluetoothMapContract.MessageColumns.FOLDER_ID, BluetoothMapContract.MessageColumns.FLAG_READ, BluetoothMapContract.MessageColumns.DATE, BluetoothMapContract.MessageColumns.SUBJECT, BluetoothMapContract.MessageColumns.FROM_LIST, BluetoothMapContract.MessageColumns.FLAG_HIGH_PRIORITY, "thread_id", "thread_name"};
    private boolean mObserverRegistered = false;
    private int mMapSupportedFeatures = 31;
    private int mMapEventReportVersion = 10;
    private BluetoothMapFolderElement mFolders = new BluetoothMapFolderElement("DUMMY", null);
    private boolean mTransmitEvents = true;
    private volatile long mEventFilter = 4294967295L;
    private boolean mStorageUnlocked = false;
    private boolean mInitialized = false;
    private final ContentObserver mObserver = new ContentObserver(new Handler()) { // from class: com.android.bluetooth.map.BluetoothMapContentObserver.1
        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange) throws Throwable {
            onChange(selfChange, null);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange, Uri uri) throws Throwable {
            if (uri != null) {
                if (!BluetoothMapContentObserver.this.mStorageUnlocked) {
                    Log.v(BluetoothMapContentObserver.TAG, "Ignore events until storage is completely unlocked");
                    return;
                } else if (uri.toString().contains(BluetoothMapContract.TABLE_CONVOCONTACT)) {
                    BluetoothMapContentObserver.this.handleContactListChanges(uri);
                    return;
                } else {
                    BluetoothMapContentObserver.this.handleMsgListChanges(uri);
                    return;
                }
            }
            Log.w(BluetoothMapContentObserver.TAG, "onChange() with URI == null - not handled.");
        }
    };
    private Map<Long, PushMsgInfo> mPushMsgList = Collections.synchronizedMap(new HashMap());
    private PhoneStateListener mPhoneListener = new PhoneStateListener() { // from class: com.android.bluetooth.map.BluetoothMapContentObserver.2
        @Override // android.telephony.PhoneStateListener
        public void onServiceStateChanged(ServiceState serviceState) {
            Log.d(BluetoothMapContentObserver.TAG, "Phone service state change: " + serviceState.getState());
            if (serviceState.getState() == 0) {
                BluetoothMapContentObserver.this.resendPendingMessages();
            }
        }
    };

    static {
        HashMap<Integer, String> map = new HashMap<>();
        FOLDER_SMS_MAP = map;
        map.put(1, BluetoothMapContract.FOLDER_NAME_INBOX);
        FOLDER_SMS_MAP.put(2, "sent");
        FOLDER_SMS_MAP.put(3, BluetoothMapContract.FOLDER_NAME_DRAFT);
        FOLDER_SMS_MAP.put(4, BluetoothMapContract.FOLDER_NAME_OUTBOX);
        FOLDER_SMS_MAP.put(5, BluetoothMapContract.FOLDER_NAME_OUTBOX);
        FOLDER_SMS_MAP.put(6, BluetoothMapContract.FOLDER_NAME_OUTBOX);
        HashMap<Integer, String> map2 = new HashMap<>();
        FOLDER_MMS_MAP = map2;
        map2.put(1, BluetoothMapContract.FOLDER_NAME_INBOX);
        FOLDER_MMS_MAP.put(2, "sent");
        FOLDER_MMS_MAP.put(3, BluetoothMapContract.FOLDER_NAME_DRAFT);
        FOLDER_MMS_MAP.put(4, BluetoothMapContract.FOLDER_NAME_OUTBOX);
        ID_PROJECTION = new String[]{"_id"};
        UPDATE_STATUS_URI = Uri.withAppendedPath(Telephony.Sms.CONTENT_URI, "/status");
    }

    public BluetoothMapContentObserver(Context context, BluetoothMnsObexClient mnsClient, BluetoothMapMasInstance masInstance, BluetoothMapAccountItem account, boolean enableSmsMms) throws RemoteException {
        this.mProviderClient = null;
        this.mMasInstance = null;
        this.mEnableSmsMms = false;
        this.mAuthority = null;
        this.mMessageUri = null;
        this.mContactUri = null;
        this.mSmsBroadcastReceiver = new SmsBroadcastReceiver();
        this.mCeBroadcastReceiver = new CeBroadcastReceiver();
        this.mMsgListSms = null;
        this.mMsgListMms = null;
        this.mMsgListMsg = null;
        this.mContactList = null;
        this.mContext = context;
        this.mResolver = context.getContentResolver();
        this.mAccount = account;
        this.mMasInstance = masInstance;
        this.mMasId = masInstance.getMasId();
        setObserverRemoteFeatureMask(this.mMasInstance.getRemoteFeatureMask());
        if (account != null) {
            this.mAuthority = Uri.parse(account.mBase_uri).getAuthority();
            this.mMessageUri = Uri.parse(account.mBase_uri + "/" + BluetoothMapContract.TABLE_MESSAGE);
            if (this.mAccount.getType() == BluetoothMapUtils.TYPE.IM) {
                this.mContactUri = Uri.parse(account.mBase_uri + "/" + BluetoothMapContract.TABLE_CONVOCONTACT);
            }
            ContentProviderClient contentProviderClientAcquireUnstableContentProviderClient = this.mResolver.acquireUnstableContentProviderClient(this.mAuthority);
            this.mProviderClient = contentProviderClientAcquireUnstableContentProviderClient;
            if (contentProviderClientAcquireUnstableContentProviderClient == null) {
                throw new RemoteException("Failed to acquire provider for " + this.mAuthority);
            }
            contentProviderClientAcquireUnstableContentProviderClient.setDetectNotResponding(PROVIDER_ANR_TIMEOUT);
            Map<String, BluetoothMapConvoContactElement> contactList = this.mMasInstance.getContactList();
            this.mContactList = contactList;
            if (contactList == null) {
                setContactList(new HashMap(), false);
                initContactsList();
            }
        }
        this.mEnableSmsMms = enableSmsMms;
        this.mSmsType = getSmsType();
        this.mMnsClient = mnsClient;
        Map<Long, Msg> msgListSms = this.mMasInstance.getMsgListSms();
        this.mMsgListSms = msgListSms;
        boolean doInit = false;
        if (this.mEnableSmsMms) {
            if (msgListSms == null) {
                setMsgListSms(new HashMap(), false);
                doInit = true;
            }
            Map<Long, Msg> msgListMms = this.mMasInstance.getMsgListMms();
            this.mMsgListMms = msgListMms;
            if (msgListMms == null) {
                setMsgListMms(new HashMap(), false);
                doInit = true;
            }
        }
        if (this.mAccount != null) {
            Map<Long, Msg> msgListMsg = this.mMasInstance.getMsgListMsg();
            this.mMsgListMsg = msgListMsg;
            if (msgListMsg == null) {
                setMsgListMsg(new HashMap(), false);
                doInit = true;
            }
        }
        if (doInit) {
            initMsgList();
        }
    }

    public int getObserverRemoteFeatureMask() {
        return this.mMapSupportedFeatures;
    }

    public void setObserverRemoteFeatureMask(int remoteSupportedFeatures) {
        int featureMask = BluetoothMapMasInstance.getFeatureMask() & remoteSupportedFeatures;
        this.mMapSupportedFeatures = featureMask;
        if ((featureMask & 64) != 0) {
            this.mMapEventReportVersion = 11;
        }
        int i = this.mMapSupportedFeatures;
        if ((i & 128) != 0) {
            this.mMapEventReportVersion = 12;
        } else if ((i & 49152) != 0) {
            Log.w(TAG, "setObserverRemoteFeatureMask: Extended Event Reports 1.2 is not set eventhough PARTICIPANT_PRESENCE_CHANGE_BIT or PARTICIPANT_CHAT_STATE_CHANGE_BIT were set, mMapSupportedFeatures=" + this.mMapSupportedFeatures);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<Long, Msg> getMsgListSms() {
        return this.mMsgListSms;
    }

    private void setMsgListSms(Map<Long, Msg> msgListSms, boolean changesDetected) {
        this.mMsgListSms = msgListSms;
        if (changesDetected) {
            this.mMasInstance.updateFolderVersionCounter();
        }
        this.mMasInstance.setMsgListSms(msgListSms);
    }

    private Map<Long, Msg> getMsgListMms() {
        return this.mMsgListMms;
    }

    private void setMsgListMms(Map<Long, Msg> msgListMms, boolean changesDetected) {
        this.mMsgListMms = msgListMms;
        if (changesDetected) {
            this.mMasInstance.updateFolderVersionCounter();
        }
        this.mMasInstance.setMsgListMms(msgListMms);
    }

    private Map<Long, Msg> getMsgListMsg() {
        return this.mMsgListMsg;
    }

    private void setMsgListMsg(Map<Long, Msg> msgListMsg, boolean changesDetected) {
        this.mMsgListMsg = msgListMsg;
        if (changesDetected) {
            this.mMasInstance.updateFolderVersionCounter();
        }
        this.mMasInstance.setMsgListMsg(msgListMsg);
    }

    private Map<String, BluetoothMapConvoContactElement> getContactList() {
        return this.mContactList;
    }

    private void setContactList(Map<String, BluetoothMapConvoContactElement> contactList, boolean changesDetected) {
        this.mContactList = contactList;
        if (changesDetected) {
            this.mMasInstance.updateImEmailConvoListVersionCounter();
        }
        this.mMasInstance.setContactList(contactList);
    }

    private static boolean sendEventNewMessage(long eventFilter) {
        return (1 & eventFilter) > 0;
    }

    private static boolean sendEventMessageDeleted(long eventFilter) {
        return (2 & eventFilter) > 0;
    }

    private static boolean sendEventMessageShift(long eventFilter) {
        return (4 & eventFilter) > 0;
    }

    private static boolean sendEventSendingSuccess(long eventFilter) {
        return (8 & eventFilter) > 0;
    }

    private static boolean sendEventSendingFailed(long eventFilter) {
        return (16 & eventFilter) > 0;
    }

    private static boolean sendEventDeliverySuccess(long eventFilter) {
        return (32 & eventFilter) > 0;
    }

    private static boolean sendEventDeliveryFailed(long eventFilter) {
        return (64 & eventFilter) > 0;
    }

    private static boolean sendEventReadStatusChanged(long eventFilter) {
        return (512 & eventFilter) > 0;
    }

    private static boolean sendEventConversationChanged(long eventFilter) {
        return (1024 & eventFilter) > 0;
    }

    private static boolean sendEventParticipantPresenceChanged(long eventFilter) {
        return (2048 & eventFilter) > 0;
    }

    private static boolean sendEventParticipantChatstateChanged(long eventFilter) {
        return (4096 & eventFilter) > 0;
    }

    private static boolean sendEventMessageRemoved(long eventFilter) {
        return (16384 & eventFilter) > 0;
    }

    private BluetoothMapUtils.TYPE getSmsType() {
        TelephonyManager tm = (TelephonyManager) this.mContext.getSystemService("phone");
        if (tm.getPhoneType() == 2) {
            BluetoothMapUtils.TYPE smsType = BluetoothMapUtils.TYPE.SMS_CDMA;
            return smsType;
        }
        BluetoothMapUtils.TYPE smsType2 = BluetoothMapUtils.TYPE.SMS_GSM;
        return smsType2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getSmsFolderName(int type) {
        String name = FOLDER_SMS_MAP.get(Integer.valueOf(type));
        if (name != null) {
            return name;
        }
        Log.e(TAG, "New SMS mailbox types have been introduced, without an update in BT...");
        return "Unknown";
    }

    private static String getMmsFolderName(int mailbox) {
        String name = FOLDER_MMS_MAP.get(Integer.valueOf(mailbox));
        if (name != null) {
            return name;
        }
        Log.e(TAG, "New MMS mailboxes have been introduced, without an update in BT...");
        return "Unknown";
    }

    public void setFolderStructure(BluetoothMapFolderElement folderStructure) {
        this.mFolders = folderStructure;
    }

    private class ConvoContactInfo {
        public int mContactColBtUid;
        public int mContactColChatState;
        public int mContactColContactId;
        public int mContactColConvoId;
        public int mContactColLastActive;
        public int mContactColLastOnline;
        public int mContactColName;
        public int mContactColNickname;
        public int mContactColPresenceState;
        public int mContactColPresenceText;
        public int mContactColPriority;
        public int mContactColUci;
        public int mConvoColConvoId;
        public int mConvoColLastActivity;
        public int mConvoColName;

        private ConvoContactInfo() {
            this.mConvoColConvoId = -1;
            this.mConvoColLastActivity = -1;
            this.mConvoColName = -1;
            this.mContactColUci = -1;
            this.mContactColConvoId = -1;
            this.mContactColName = -1;
            this.mContactColNickname = -1;
            this.mContactColBtUid = -1;
            this.mContactColChatState = -1;
            this.mContactColContactId = -1;
            this.mContactColLastActive = -1;
            this.mContactColPresenceState = -1;
            this.mContactColPresenceText = -1;
            this.mContactColPriority = -1;
            this.mContactColLastOnline = -1;
        }

        public void setConvoColunms(Cursor c) {
            this.mContactColConvoId = c.getColumnIndex(BluetoothMapContract.ConvoContactColumns.CONVO_ID);
            this.mContactColName = c.getColumnIndex("name");
            this.mContactColNickname = c.getColumnIndex(BluetoothMapContract.ConvoContactColumns.NICKNAME);
            this.mContactColBtUid = c.getColumnIndex(BluetoothMapContract.ConvoContactColumns.X_BT_UID);
            this.mContactColChatState = c.getColumnIndex(BluetoothMapContract.ChatStatusColumns.CHAT_STATE);
            this.mContactColUci = c.getColumnIndex(BluetoothMapContract.ConvoContactColumns.UCI);
            this.mContactColNickname = c.getColumnIndex(BluetoothMapContract.ConvoContactColumns.NICKNAME);
            this.mContactColLastActive = c.getColumnIndex(BluetoothMapContract.ChatStatusColumns.LAST_ACTIVE);
            this.mContactColName = c.getColumnIndex("name");
            this.mContactColPresenceState = c.getColumnIndex(BluetoothMapContract.PresenceColumns.PRESENCE_STATE);
            this.mContactColPresenceText = c.getColumnIndex(BluetoothMapContract.PresenceColumns.STATUS_TEXT);
            this.mContactColPriority = c.getColumnIndex(BluetoothMapContract.PresenceColumns.PRIORITY);
            this.mContactColLastOnline = c.getColumnIndex(BluetoothMapContract.PresenceColumns.LAST_ONLINE);
        }
    }

    private class Event {
        static final String PATH = "telecom/msg/";
        public int chatState;
        public long conversationID;
        public String conversationName;
        public String datetime;
        public String eventType;
        public String folder;
        public long handle;
        public BluetoothMapUtils.TYPE msgType;
        public String oldFolder;
        public int presenceState;
        public String presenceStatus;
        public String priority;
        public String senderName;
        public String subject;
        public String uci;

        private void setFolderPath(String name, BluetoothMapUtils.TYPE type) {
            if (name != null) {
                if (type == BluetoothMapUtils.TYPE.EMAIL || type == BluetoothMapUtils.TYPE.IM) {
                    this.folder = name;
                    return;
                }
                this.folder = PATH + name;
                return;
            }
            this.folder = null;
        }

        Event(String eventType, long handle, String folder, String oldFolder, BluetoothMapUtils.TYPE msgType) {
            this.folder = null;
            this.oldFolder = null;
            this.datetime = null;
            this.uci = null;
            this.subject = null;
            this.senderName = null;
            this.priority = null;
            this.conversationName = null;
            this.conversationID = -1L;
            this.presenceState = 0;
            this.presenceStatus = null;
            this.chatState = 0;
            this.eventType = eventType;
            this.handle = handle;
            setFolderPath(folder, msgType);
            if (oldFolder != null) {
                if (msgType == BluetoothMapUtils.TYPE.EMAIL || msgType == BluetoothMapUtils.TYPE.IM) {
                    this.oldFolder = oldFolder;
                } else {
                    this.oldFolder = PATH + oldFolder;
                }
            } else {
                this.oldFolder = null;
            }
            this.msgType = msgType;
        }

        Event(String eventType, long handle, String folder, BluetoothMapUtils.TYPE msgType) {
            this.folder = null;
            this.oldFolder = null;
            this.datetime = null;
            this.uci = null;
            this.subject = null;
            this.senderName = null;
            this.priority = null;
            this.conversationName = null;
            this.conversationID = -1L;
            this.presenceState = 0;
            this.presenceStatus = null;
            this.chatState = 0;
            this.eventType = eventType;
            this.handle = handle;
            setFolderPath(folder, msgType);
            this.msgType = msgType;
        }

        Event(String eventType, long handle, String folder, BluetoothMapUtils.TYPE msgType, String datetime, String subject, String senderName, String priority) {
            this.folder = null;
            this.oldFolder = null;
            this.datetime = null;
            this.uci = null;
            this.subject = null;
            this.senderName = null;
            this.priority = null;
            this.conversationName = null;
            this.conversationID = -1L;
            this.presenceState = 0;
            this.presenceStatus = null;
            this.chatState = 0;
            this.eventType = eventType;
            this.handle = handle;
            setFolderPath(folder, msgType);
            this.msgType = msgType;
            this.datetime = datetime;
            if (subject != null) {
                this.subject = BluetoothMapUtils.stripInvalidChars(subject);
            }
            if (senderName != null) {
                this.senderName = BluetoothMapUtils.stripInvalidChars(senderName);
            }
            this.priority = priority;
        }

        Event(String eventType, long handle, String folder, BluetoothMapUtils.TYPE msgType, String datetime, String subject, String senderName, String priority, long conversationID, String conversationName) {
            this.folder = null;
            this.oldFolder = null;
            this.datetime = null;
            this.uci = null;
            this.subject = null;
            this.senderName = null;
            this.priority = null;
            this.conversationName = null;
            this.conversationID = -1L;
            this.presenceState = 0;
            this.presenceStatus = null;
            this.chatState = 0;
            this.eventType = eventType;
            this.handle = handle;
            setFolderPath(folder, msgType);
            this.msgType = msgType;
            this.datetime = datetime;
            if (subject != null) {
                this.subject = BluetoothMapUtils.stripInvalidChars(subject);
            }
            if (senderName != null) {
                this.senderName = BluetoothMapUtils.stripInvalidChars(senderName);
            }
            if (conversationID != 0) {
                this.conversationID = conversationID;
            }
            if (conversationName != null) {
                this.conversationName = BluetoothMapUtils.stripInvalidChars(conversationName);
            }
            this.priority = priority;
        }

        Event(String eventType, String uci, BluetoothMapUtils.TYPE msgType, String name, String priority, String lastActivity, long conversationID, String conversationName, int presenceState, String presenceStatus, int chatState) {
            this.folder = null;
            this.oldFolder = null;
            this.datetime = null;
            this.uci = null;
            this.subject = null;
            this.senderName = null;
            this.priority = null;
            this.conversationName = null;
            this.conversationID = -1L;
            this.presenceState = 0;
            this.presenceStatus = null;
            this.chatState = 0;
            this.eventType = eventType;
            this.uci = uci;
            this.msgType = msgType;
            if (name != null) {
                this.senderName = BluetoothMapUtils.stripInvalidChars(name);
            }
            this.priority = priority;
            this.datetime = lastActivity;
            if (conversationID != 0) {
                this.conversationID = conversationID;
            }
            if (conversationName != null) {
                this.conversationName = BluetoothMapUtils.stripInvalidChars(conversationName);
            }
            if (presenceState != 0) {
                this.presenceState = presenceState;
            }
            if (presenceStatus != null) {
                this.presenceStatus = BluetoothMapUtils.stripInvalidChars(presenceStatus);
            }
            if (chatState != 0) {
                this.chatState = chatState;
            }
        }

        public byte[] encode() throws UnsupportedEncodingException {
            StringWriter sw = new StringWriter();
            XmlSerializer xmlEvtReport = Xml.newSerializer();
            try {
                xmlEvtReport.setOutput(sw);
                xmlEvtReport.startDocument("UTF-8", true);
                xmlEvtReport.text(VCardBuilder.VCARD_END_OF_LINE);
                xmlEvtReport.startTag(INetd.NEXTHOP_NONE, "MAP-event-report");
                if (BluetoothMapContentObserver.this.mMapEventReportVersion != 12) {
                    if (BluetoothMapContentObserver.this.mMapEventReportVersion == 11) {
                        xmlEvtReport.attribute(INetd.NEXTHOP_NONE, "version", "1.1");
                    } else {
                        xmlEvtReport.attribute(INetd.NEXTHOP_NONE, "version", "1.0");
                    }
                } else {
                    xmlEvtReport.attribute(INetd.NEXTHOP_NONE, "version", "1.2");
                }
                xmlEvtReport.startTag(INetd.NEXTHOP_NONE, NotificationCompat.CATEGORY_EVENT);
                xmlEvtReport.attribute(INetd.NEXTHOP_NONE, BluetoothMapContentObserver.EXTRA_MESSAGE_SENT_MSG_TYPE, this.eventType);
                if (this.eventType.equals(BluetoothMapContentObserver.EVENT_TYPE_CONVERSATION) || this.eventType.equals(BluetoothMapContentObserver.EVENT_TYPE_PRESENCE) || this.eventType.equals(BluetoothMapContentObserver.EVENT_TYPE_CHAT_STATE)) {
                    xmlEvtReport.attribute(INetd.NEXTHOP_NONE, "participant_uci", this.uci);
                } else {
                    xmlEvtReport.attribute(INetd.NEXTHOP_NONE, "handle", BluetoothMapUtils.getMapHandle(this.handle, this.msgType));
                }
                if (this.folder != null) {
                    xmlEvtReport.attribute(INetd.NEXTHOP_NONE, "folder", this.folder);
                }
                if (this.oldFolder != null) {
                    xmlEvtReport.attribute(INetd.NEXTHOP_NONE, "old_folder", this.oldFolder);
                }
                if (this.msgType != null) {
                    xmlEvtReport.attribute(INetd.NEXTHOP_NONE, "msg_type", this.msgType.name());
                }
                if (this.datetime != null) {
                    xmlEvtReport.attribute(INetd.NEXTHOP_NONE, "datetime", this.datetime);
                }
                if (this.subject != null) {
                    xmlEvtReport.attribute(INetd.NEXTHOP_NONE, BluetoothMapContract.MessageColumns.SUBJECT, this.subject.substring(0, this.subject.length() < 256 ? this.subject.length() : 256));
                }
                if (this.senderName != null) {
                    xmlEvtReport.attribute(INetd.NEXTHOP_NONE, "sender_name", this.senderName.substring(0, this.senderName.length() < 256 ? this.senderName.length() : 255));
                }
                if (this.priority != null) {
                    xmlEvtReport.attribute(INetd.NEXTHOP_NONE, BluetoothMapContract.PresenceColumns.PRIORITY, this.priority);
                }
                if (BluetoothMapContentObserver.this.mMapEventReportVersion > 11) {
                    if (this.conversationName != null) {
                        xmlEvtReport.attribute(INetd.NEXTHOP_NONE, "conversation_name", this.conversationName);
                    }
                    if (this.conversationID != -1) {
                        xmlEvtReport.attribute(INetd.NEXTHOP_NONE, "conversation_id", BluetoothMapUtils.getMapConvoHandle(this.conversationID, this.msgType));
                    }
                    if (this.eventType.equals(BluetoothMapContentObserver.EVENT_TYPE_PRESENCE)) {
                        if (this.presenceState != 0) {
                            xmlEvtReport.attribute(INetd.NEXTHOP_NONE, "presence_availability", String.valueOf(this.presenceState));
                        }
                        if (this.presenceStatus != null) {
                            xmlEvtReport.attribute(INetd.NEXTHOP_NONE, "presence_status", this.presenceStatus.substring(0, this.presenceStatus.length() < 256 ? this.subject.length() : 256));
                        }
                    }
                    if (this.eventType.equals(BluetoothMapContentObserver.EVENT_TYPE_PRESENCE) && this.chatState != 0) {
                        xmlEvtReport.attribute(INetd.NEXTHOP_NONE, BluetoothMapContract.ChatStatusColumns.CHAT_STATE, String.valueOf(this.chatState));
                    }
                }
                xmlEvtReport.endTag(INetd.NEXTHOP_NONE, NotificationCompat.CATEGORY_EVENT);
                xmlEvtReport.endTag(INetd.NEXTHOP_NONE, "MAP-event-report");
                xmlEvtReport.endDocument();
            } catch (IOException e) {
            } catch (IllegalArgumentException e2) {
            } catch (IllegalStateException e3) {
            }
            return sw.toString().getBytes("UTF-8");
        }
    }

    class Msg {
        public int flagRead;
        public long folderId;
        public long id;
        public boolean localInitiatedSend;
        public long oldFolderId;
        public int threadId;
        public boolean transparent;
        public int type;

        Msg(long id, int type, int threadId, int readFlag) {
            this.folderId = -1L;
            this.oldFolderId = -1L;
            this.localInitiatedSend = false;
            this.transparent = false;
            this.flagRead = -1;
            this.id = id;
            this.type = type;
            this.threadId = threadId;
            this.flagRead = readFlag;
        }

        Msg(long id, long folderId, int readFlag) {
            this.folderId = -1L;
            this.oldFolderId = -1L;
            this.localInitiatedSend = false;
            this.transparent = false;
            this.flagRead = -1;
            this.id = id;
            this.folderId = folderId;
            this.flagRead = readFlag;
        }

        public int hashCode() {
            long j = this.id;
            int result = (1 * 31) + ((int) (j ^ (j >>> 32)));
            return result;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Msg other = (Msg) obj;
            if (this.id == other.id) {
                return true;
            }
            return false;
        }
    }

    public int setNotificationRegistration(int notificationStatus) throws RemoteException {
        Handler mns;
        BluetoothMnsObexClient bluetoothMnsObexClient = this.mMnsClient;
        if (bluetoothMnsObexClient == null || (mns = bluetoothMnsObexClient.getMessageHandler()) == null) {
            return 211;
        }
        Message msg = mns.obtainMessage();
        if (this.mMnsClient.isValidMnsRecord()) {
            msg.what = 1;
        } else {
            msg.what = 3;
            if (this.mMnsClient.mMnsLstRegRqst != null && this.mMnsClient.mMnsLstRegRqst.isSearchInProgress()) {
                if (notificationStatus == 1) {
                    return 211;
                }
                msg.what = 1;
            }
        }
        msg.arg1 = this.mMasId;
        msg.arg2 = notificationStatus;
        mns.sendMessageDelayed(msg, 10L);
        return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
    }

    boolean eventMaskContainsContacts(long mask) {
        return sendEventParticipantPresenceChanged(mask);
    }

    boolean eventMaskContainsCovo(long mask) {
        return sendEventConversationChanged(mask) || sendEventParticipantChatstateChanged(mask);
    }

    synchronized void setNotificationFilter(long newFilter) {
        long oldFilter = this.mEventFilter;
        this.mEventFilter = newFilter;
        if (!eventMaskContainsContacts(oldFilter)) {
            eventMaskContainsContacts(newFilter);
        }
        if (!eventMaskContainsCovo(oldFilter)) {
            eventMaskContainsCovo(newFilter);
        }
    }

    public void registerObserver() throws RemoteException {
        if (this.mObserverRegistered) {
            return;
        }
        if (this.mAccount != null) {
            ContentProviderClient contentProviderClientAcquireUnstableContentProviderClient = this.mResolver.acquireUnstableContentProviderClient(this.mAuthority);
            this.mProviderClient = contentProviderClientAcquireUnstableContentProviderClient;
            if (contentProviderClientAcquireUnstableContentProviderClient == null) {
                throw new RemoteException("Failed to acquire provider for " + this.mAuthority);
            }
            contentProviderClientAcquireUnstableContentProviderClient.setDetectNotResponding(PROVIDER_ANR_TIMEOUT);
            if (this.mAccount.getType() == BluetoothMapUtils.TYPE.IM) {
                initContactsList();
            }
        }
        initMsgList();
        if (this.mEnableSmsMms) {
            this.mResolver.registerContentObserver(Telephony.MmsSms.CONTENT_URI, false, this.mObserver);
            this.mObserverRegistered = true;
        }
        if (this.mAccount != null) {
            Uri uri = Uri.parse(this.mAccount.mBase_uri_no_account + "/" + BluetoothMapContract.TABLE_MESSAGE);
            this.mResolver.registerContentObserver(uri, true, this.mObserver);
            Uri uri2 = Uri.parse(this.mAccount.mBase_uri + "/" + BluetoothMapContract.TABLE_MESSAGE);
            this.mResolver.registerContentObserver(uri2, true, this.mObserver);
            if (this.mAccount.getType() == BluetoothMapUtils.TYPE.IM) {
                Uri uri3 = Uri.parse(this.mAccount.mBase_uri_no_account + "/" + BluetoothMapContract.TABLE_CONVOCONTACT);
                this.mResolver.registerContentObserver(uri3, true, this.mObserver);
                Uri uri4 = Uri.parse(this.mAccount.mBase_uri + "/" + BluetoothMapContract.TABLE_CONVOCONTACT);
                this.mResolver.registerContentObserver(uri4, true, this.mObserver);
            }
            this.mObserverRegistered = true;
        }
    }

    public void unregisterObserver() {
        this.mResolver.unregisterContentObserver(this.mObserver);
        this.mObserverRegistered = false;
        ContentProviderClient contentProviderClient = this.mProviderClient;
        if (contentProviderClient != null) {
            contentProviderClient.close();
            this.mProviderClient = null;
        }
    }

    void refreshFolderVersionCounter() {
        if (this.mObserverRegistered) {
            return;
        }
        this.mTransmitEvents = false;
        try {
            if (this.mEnableSmsMms) {
                handleMsgListChangesSms();
                handleMsgListChangesMms();
            }
            if (this.mAccount != null) {
                try {
                    handleMsgListChangesMsg(this.mMessageUri);
                } catch (RemoteException e) {
                    Log.e(TAG, "Unable to update FolderVersionCounter. - Not fatal, but can cause undesirable user experience!", e);
                }
            }
        } finally {
            this.mTransmitEvents = true;
        }
    }

    void refreshConvoListVersionCounter() {
        if (this.mObserverRegistered) {
            return;
        }
        this.mTransmitEvents = false;
        try {
            if (this.mAccount != null && this.mContactUri != null) {
                handleContactListChanges(this.mContactUri);
            }
        } finally {
            this.mTransmitEvents = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEvent(Event evt) {
        if (!this.mTransmitEvents) {
            return;
        }
        BluetoothMnsObexClient bluetoothMnsObexClient = this.mMnsClient;
        if (bluetoothMnsObexClient == null || !bluetoothMnsObexClient.isConnected()) {
            Log.d(TAG, "sendEvent: No MNS client registered or connected- don't send event");
            return;
        }
        long eventFilter = this.mEventFilter;
        if (Objects.equals(evt.eventType, EVENT_TYPE_NEW)) {
            if (!sendEventNewMessage(eventFilter)) {
                return;
            }
        } else if (Objects.equals(evt.eventType, EVENT_TYPE_DELETE)) {
            if (!sendEventMessageDeleted(eventFilter)) {
                return;
            }
        } else if (Objects.equals(evt.eventType, EVENT_TYPE_REMOVED)) {
            if (!sendEventMessageRemoved(eventFilter)) {
                return;
            }
        } else if (Objects.equals(evt.eventType, EVENT_TYPE_SHIFT)) {
            if (!sendEventMessageShift(eventFilter)) {
                return;
            }
        } else if (Objects.equals(evt.eventType, EVENT_TYPE_DELEVERY_SUCCESS)) {
            if (!sendEventDeliverySuccess(eventFilter)) {
                return;
            }
        } else if (Objects.equals(evt.eventType, EVENT_TYPE_SENDING_SUCCESS)) {
            if (!sendEventSendingSuccess(eventFilter)) {
                return;
            }
        } else if (Objects.equals(evt.eventType, EVENT_TYPE_SENDING_FAILURE)) {
            if (!sendEventSendingFailed(eventFilter)) {
                return;
            }
        } else if (Objects.equals(evt.eventType, EVENT_TYPE_DELIVERY_FAILURE)) {
            if (!sendEventDeliveryFailed(eventFilter)) {
                return;
            }
        } else if (Objects.equals(evt.eventType, EVENT_TYPE_READ_STATUS)) {
            if (!sendEventReadStatusChanged(eventFilter)) {
                return;
            }
        } else if (Objects.equals(evt.eventType, EVENT_TYPE_CONVERSATION)) {
            if (!sendEventConversationChanged(eventFilter)) {
                return;
            }
        } else if (Objects.equals(evt.eventType, EVENT_TYPE_PRESENCE)) {
            if (!sendEventParticipantPresenceChanged(eventFilter)) {
                return;
            }
        } else if (Objects.equals(evt.eventType, EVENT_TYPE_CHAT_STATE) && !sendEventParticipantChatstateChanged(eventFilter)) {
            return;
        }
        try {
            this.mMnsClient.sendEvent(evt.encode(), this.mMasId);
        } catch (UnsupportedEncodingException e) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initMsgList() throws RemoteException {
        Cursor c;
        UserManager manager = UserManager.get(this.mContext);
        if (manager == null || !manager.isUserUnlocked()) {
            return;
        }
        if (this.mEnableSmsMms) {
            HashMap<Long, Msg> msgListSms = new HashMap<>();
            try {
                Cursor c2 = this.mResolver.query(Telephony.Sms.CONTENT_URI, SMS_PROJECTION_SHORT, null, null, null);
                if (c2 != null) {
                    try {
                        if (c2.moveToFirst()) {
                            do {
                                long id = c2.getLong(c2.getColumnIndex("_id"));
                                int type = c2.getInt(c2.getColumnIndex(EXTRA_MESSAGE_SENT_MSG_TYPE));
                                int threadId = c2.getInt(c2.getColumnIndex("thread_id"));
                                int read = c2.getInt(c2.getColumnIndex(BluetoothMapContract.FILTER_READ_STATUS));
                                Msg msg = new Msg(id, type, threadId, read);
                                msgListSms.put(Long.valueOf(id), msg);
                            } while (c2.moveToNext());
                        }
                    } finally {
                        if (c2 != null) {
                            c2.close();
                        }
                    }
                }
                if (c2 != null) {
                    c2.close();
                }
                synchronized (getMsgListSms()) {
                    getMsgListSms().clear();
                    setMsgListSms(msgListSms, true);
                }
                HashMap<Long, Msg> msgListMms = new HashMap<>();
                c = this.mResolver.query(Telephony.Mms.CONTENT_URI, MMS_PROJECTION_SHORT, null, null, null);
                if (c != null) {
                    try {
                        if (c.moveToFirst()) {
                            do {
                                long id2 = c.getLong(c.getColumnIndex("_id"));
                                int type2 = c.getInt(c.getColumnIndex("msg_box"));
                                int threadId2 = c.getInt(c.getColumnIndex("thread_id"));
                                int read2 = c.getInt(c.getColumnIndex(BluetoothMapContract.FILTER_READ_STATUS));
                                Msg msg2 = new Msg(id2, type2, threadId2, read2);
                                msgListMms.put(Long.valueOf(id2), msg2);
                            } while (c.moveToNext());
                        }
                    } finally {
                        if (c != null) {
                            c.close();
                        }
                    }
                }
                synchronized (getMsgListMms()) {
                    getMsgListMms().clear();
                    setMsgListMms(msgListMms, true);
                }
            } catch (SQLiteException e) {
                Log.e(TAG, "Failed to initialize the list of messages: " + e.toString());
                return;
            }
        }
        if (this.mAccount != null) {
            HashMap<Long, Msg> msgList = new HashMap<>();
            Uri uri = this.mMessageUri;
            c = this.mProviderClient.query(uri, MSG_PROJECTION_SHORT, null, null, null);
            if (c != null) {
                try {
                    if (c.moveToFirst()) {
                        do {
                            long id3 = c.getLong(c.getColumnIndex("_id"));
                            long folderId = c.getInt(c.getColumnIndex(BluetoothMapContract.MessageColumns.FOLDER_ID));
                            int readFlag = c.getInt(c.getColumnIndex(BluetoothMapContract.MessageColumns.FLAG_READ));
                            Msg msg3 = new Msg(id3, folderId, readFlag);
                            msgList.put(Long.valueOf(id3), msg3);
                        } while (c.moveToNext());
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
            synchronized (getMsgListMsg()) {
                getMsgListMsg().clear();
                setMsgListMsg(msgList, true);
            }
        }
    }

    private void initContactsList() throws RemoteException {
        if (this.mContactUri == null) {
            return;
        }
        Uri uri = this.mContactUri;
        Cursor c = this.mProviderClient.query(uri, BluetoothMapContract.BT_CONTACT_CHATSTATE_PRESENCE_PROJECTION, null, null, null);
        Map<String, BluetoothMapConvoContactElement> contactList = new HashMap<>();
        if (c != null) {
            try {
                if (c.moveToFirst()) {
                    ConvoContactInfo cInfo = new ConvoContactInfo();
                    cInfo.setConvoColunms(c);
                    do {
                        long convoId = c.getLong(cInfo.mContactColConvoId);
                        if (convoId != 0) {
                            String uci = c.getString(cInfo.mContactColUci);
                            String name = c.getString(cInfo.mContactColName);
                            String displayName = c.getString(cInfo.mContactColNickname);
                            String presenceStatus = c.getString(cInfo.mContactColPresenceText);
                            int presenceState = c.getInt(cInfo.mContactColPresenceState);
                            long lastActivity = c.getLong(cInfo.mContactColLastActive);
                            int chatState = c.getInt(cInfo.mContactColChatState);
                            int priority = c.getInt(cInfo.mContactColPriority);
                            String btUid = c.getString(cInfo.mContactColBtUid);
                            BluetoothMapConvoContactElement contact = new BluetoothMapConvoContactElement(uci, name, displayName, presenceStatus, presenceState, lastActivity, chatState, priority, btUid);
                            contactList.put(uci, contact);
                        }
                    } while (c.moveToNext());
                }
            } finally {
                if (c != null) {
                    c.close();
                }
            }
        }
        synchronized (getContactList()) {
            getContactList().clear();
            setContactList(contactList, true);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x0318 A[LOOP:2: B:162:0x003e->B:112:0x0318, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0335 A[Catch: all -> 0x0390, TRY_ENTER, TryCatch #8 {all -> 0x0390, blocks: (B:127:0x0340, B:128:0x0343, B:122:0x0335, B:124:0x0339), top: B:170:0x0035 }] */
    /* JADX WARN: Removed duplicated region for block: B:125:0x033a  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0316 A[EDGE_INSN: B:190:0x0316->B:111:0x0316 BREAK  A[LOOP:2: B:162:0x003e->B:112:0x0318], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void handleMsgListChangesSms() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 921
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.map.BluetoothMapContentObserver.handleMsgListChangesSms():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:126:0x0374 A[LOOP:1: B:16:0x003e->B:126:0x0374, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0388 A[Catch: all -> 0x03d8, TRY_ENTER, TryCatch #8 {all -> 0x03d8, blocks: (B:139:0x0393, B:140:0x0396, B:152:0x03d6, B:134:0x0388, B:136:0x038c), top: B:172:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:137:0x038d  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0391 A[EDGE_INSN: B:188:0x0391->B:138:0x0391 BREAK  A[LOOP:1: B:16:0x003e->B:126:0x0374], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0110 A[Catch: all -> 0x01dc, TRY_ENTER, TRY_LEAVE, TryCatch #13 {all -> 0x01dc, blocks: (B:33:0x00dc, B:35:0x00e0, B:49:0x0121, B:60:0x016c, B:66:0x01af, B:43:0x0110), top: B:181:0x00dc }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01e2 A[Catch: all -> 0x0221, TRY_LEAVE, TryCatch #0 {all -> 0x0221, blocks: (B:68:0x01d5, B:72:0x01e2), top: B:156:0x01d5 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void handleMsgListChangesMms() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 986
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.map.BluetoothMapContentObserver.handleMsgListChangesMms():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:115:0x030a A[LOOP:1: B:13:0x004c->B:115:0x030a, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:121:0x031c A[Catch: all -> 0x03aa, TRY_ENTER, TryCatch #1 {all -> 0x03aa, blocks: (B:126:0x0327, B:127:0x032a, B:128:0x0336, B:130:0x033c, B:153:0x03ab, B:149:0x03a5, B:150:0x03a8, B:121:0x031c, B:123:0x0320), top: B:157:0x0044 }] */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0321  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0325 A[EDGE_INSN: B:187:0x0325->B:125:0x0325 BREAK  A[LOOP:1: B:13:0x004c->B:115:0x030a], SYNTHETIC] */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void handleMsgListChangesMsg(android.net.Uri r37) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 941
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.map.BluetoothMapContentObserver.handleMsgListChangesMsg(android.net.Uri):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleMsgListChanges(Uri uri) throws Throwable {
        if (uri.getAuthority().equals(this.mAuthority)) {
            try {
                handleMsgListChangesMsg(uri);
            } catch (RemoteException e) {
                this.mMasInstance.restartObexServerSession();
                Log.w(TAG, "Problems contacting the ContentProvider in mas Instance " + this.mMasId + " restaring ObexServerSession");
            }
        }
        if (this.mEnableSmsMms) {
            handleMsgListChangesSms();
            handleMsgListChangesMms();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:154:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x02b0, code lost:
    
        if (r20 == null) goto L155;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x02b2, code lost:
    
        r20.close();
     */
    /* JADX WARN: Removed duplicated region for block: B:133:0x01da A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x023b  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x029c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void handleContactListChanges(android.net.Uri r41) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 759
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.map.BluetoothMapContentObserver.handleContactListChanges(android.net.Uri):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0147  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean setEmailMessageStatusDelete(com.android.bluetooth.map.BluetoothMapFolderElement r23, java.lang.String r24, long r25, int r27) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 367
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.map.BluetoothMapContentObserver.setEmailMessageStatusDelete(com.android.bluetooth.map.BluetoothMapFolderElement, java.lang.String, long, int):boolean");
    }

    private void updateThreadId(Uri uri, String valueString, long threadId) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(valueString, Long.valueOf(threadId));
        this.mResolver.update(uri, contentValues, null, null);
    }

    private boolean deleteMessageMms(long handle) {
        boolean res = false;
        Uri uri = ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, handle);
        Cursor c = this.mResolver.query(uri, null, null, null, null);
        if (c != null) {
            try {
                if (c.moveToFirst()) {
                    int threadId = c.getInt(c.getColumnIndex("thread_id"));
                    if (threadId != -1) {
                        synchronized (getMsgListMms()) {
                            Msg msg = getMsgListMms().get(Long.valueOf(handle));
                            if (msg != null) {
                                msg.threadId = -1;
                            }
                        }
                        updateThreadId(uri, "thread_id", -1L);
                    } else {
                        synchronized (getMsgListMms()) {
                            getMsgListMms().remove(Long.valueOf(handle));
                        }
                        this.mResolver.delete(uri, null, null);
                    }
                    res = true;
                }
            } finally {
                if (c != null) {
                    c.close();
                }
            }
        }
        return res;
    }

    private boolean unDeleteMessageMms(long handle) {
        String address;
        boolean res = false;
        Uri uri = ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, handle);
        Cursor c = this.mResolver.query(uri, null, null, null, null);
        if (c != null) {
            try {
                if (c.moveToFirst()) {
                    int threadId = c.getInt(c.getColumnIndex("thread_id"));
                    if (threadId == -1) {
                        long id = c.getLong(c.getColumnIndex("_id"));
                        int msgBox = c.getInt(c.getColumnIndex("msg_box"));
                        if (msgBox == 1) {
                            address = BluetoothMapContent.getAddressMms(this.mResolver, id, BluetoothMapContent.MMS_FROM);
                        } else {
                            address = BluetoothMapContent.getAddressMms(this.mResolver, id, 151);
                        }
                        Set<String> recipients = new HashSet<>();
                        recipients.addAll(Arrays.asList(address));
                        Long oldThreadId = Long.valueOf(Telephony.Threads.getOrCreateThreadId(this.mContext, recipients));
                        synchronized (getMsgListMms()) {
                            Msg msg = getMsgListMms().get(Long.valueOf(handle));
                            if (msg != null) {
                                msg.threadId = oldThreadId.intValue();
                                msg.type = 1;
                            }
                        }
                        updateThreadId(uri, "thread_id", oldThreadId.longValue());
                    } else {
                        Log.d(TAG, "Message not in deleted folder: handle " + handle + " threadId " + threadId);
                    }
                    res = true;
                }
            } finally {
                if (c != null) {
                    c.close();
                }
            }
        }
        return res;
    }

    private boolean deleteMessageSms(long handle) {
        boolean res = false;
        Uri uri = ContentUris.withAppendedId(Telephony.Sms.CONTENT_URI, handle);
        Cursor c = this.mResolver.query(uri, null, null, null, null);
        if (c != null) {
            try {
                if (c.moveToFirst()) {
                    int threadId = c.getInt(c.getColumnIndex("thread_id"));
                    if (threadId != -1) {
                        synchronized (getMsgListSms()) {
                            Msg msg = getMsgListSms().get(Long.valueOf(handle));
                            if (msg != null) {
                                msg.threadId = -1;
                            }
                        }
                        updateThreadId(uri, "thread_id", -1L);
                    } else {
                        synchronized (getMsgListSms()) {
                            getMsgListSms().remove(Long.valueOf(handle));
                        }
                        this.mResolver.delete(uri, null, null);
                    }
                    res = true;
                }
            } finally {
                if (c != null) {
                    c.close();
                }
            }
        }
        return res;
    }

    private boolean unDeleteMessageSms(long handle) {
        boolean res = false;
        Uri uri = ContentUris.withAppendedId(Telephony.Sms.CONTENT_URI, handle);
        Cursor c = this.mResolver.query(uri, null, null, null, null);
        if (c != null) {
            try {
                if (c.moveToFirst()) {
                    int threadId = c.getInt(c.getColumnIndex("thread_id"));
                    if (threadId == -1) {
                        String address = c.getString(c.getColumnIndex("address"));
                        Set<String> recipients = new HashSet<>();
                        recipients.addAll(Arrays.asList(address));
                        Long oldThreadId = Long.valueOf(Telephony.Threads.getOrCreateThreadId(this.mContext, recipients));
                        synchronized (getMsgListSms()) {
                            Msg msg = getMsgListSms().get(Long.valueOf(handle));
                            if (msg != null) {
                                msg.threadId = oldThreadId.intValue();
                                msg.type = 1;
                            }
                        }
                        updateThreadId(uri, "thread_id", oldThreadId.longValue());
                    } else {
                        Log.d(TAG, "Message not in deleted folder: handle " + handle + " threadId " + threadId);
                    }
                    res = true;
                }
            } finally {
                if (c != null) {
                    c.close();
                }
            }
        }
        return res;
    }

    public boolean setMessageStatusDeleted(long handle, BluetoothMapUtils.TYPE type, BluetoothMapFolderElement mCurrentFolder, String uriStr, int statusValue) throws Throwable {
        if (type == BluetoothMapUtils.TYPE.EMAIL) {
            boolean res = setEmailMessageStatusDelete(mCurrentFolder, uriStr, handle, statusValue);
            return res;
        }
        if (type == BluetoothMapUtils.TYPE.IM) {
            return false;
        }
        if (statusValue == 1) {
            if (type == BluetoothMapUtils.TYPE.SMS_GSM || type == BluetoothMapUtils.TYPE.SMS_CDMA) {
                boolean res2 = deleteMessageSms(handle);
                return res2;
            }
            if (type != BluetoothMapUtils.TYPE.MMS) {
                return false;
            }
            boolean res3 = deleteMessageMms(handle);
            return res3;
        }
        if (statusValue != 0) {
            return false;
        }
        if (type == BluetoothMapUtils.TYPE.SMS_GSM || type == BluetoothMapUtils.TYPE.SMS_CDMA) {
            boolean res4 = unDeleteMessageSms(handle);
            return res4;
        }
        if (type != BluetoothMapUtils.TYPE.MMS) {
            return false;
        }
        boolean res5 = unDeleteMessageMms(handle);
        return res5;
    }

    public boolean setMessageStatusRead(long handle, BluetoothMapUtils.TYPE type, String uriStr, int statusValue) throws RemoteException {
        int count = 0;
        if (type == BluetoothMapUtils.TYPE.SMS_GSM || type == BluetoothMapUtils.TYPE.SMS_CDMA) {
            Uri uri = Telephony.Sms.CONTENT_URI;
            Uri uri2 = ContentUris.withAppendedId(uri, handle);
            ContentValues contentValues = new ContentValues();
            contentValues.put(BluetoothMapContract.FILTER_READ_STATUS, Integer.valueOf(statusValue));
            contentValues.put("seen", Integer.valueOf(statusValue));
            contentValues.toString();
            synchronized (getMsgListSms()) {
                Msg msg = getMsgListSms().get(Long.valueOf(handle));
                if (msg != null) {
                    msg.flagRead = statusValue;
                }
            }
            count = this.mResolver.update(uri2, contentValues, null, null);
        } else if (type == BluetoothMapUtils.TYPE.MMS) {
            Uri uri3 = ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, handle);
            ContentValues contentValues2 = new ContentValues();
            contentValues2.put(BluetoothMapContract.FILTER_READ_STATUS, Integer.valueOf(statusValue));
            synchronized (getMsgListMms()) {
                Msg msg2 = getMsgListMms().get(Long.valueOf(handle));
                if (msg2 != null) {
                    msg2.flagRead = statusValue;
                }
            }
            count = this.mResolver.update(uri3, contentValues2, null, null);
        } else if (type == BluetoothMapUtils.TYPE.EMAIL || type == BluetoothMapUtils.TYPE.IM) {
            Uri uri4 = this.mMessageUri;
            ContentValues contentValues3 = new ContentValues();
            contentValues3.put(BluetoothMapContract.MessageColumns.FLAG_READ, Integer.valueOf(statusValue));
            contentValues3.put("_id", Long.valueOf(handle));
            synchronized (getMsgListMsg()) {
                Msg msg3 = getMsgListMsg().get(Long.valueOf(handle));
                if (msg3 != null) {
                    msg3.flagRead = statusValue;
                }
            }
            count = this.mProviderClient.update(uri4, contentValues3, null, null);
        }
        return count > 0;
    }

    private class PushMsgInfo {
        public long id;
        public int parts;
        public int partsDelivered;
        public int partsSent;
        public String phone;
        public int retry;
        public int transparent;
        public Uri uri;
        public boolean resend = false;
        public boolean sendInProgress = false;
        public boolean failedSent = false;
        public int statusDelivered = 0;
        public long timestamp = 0;

        PushMsgInfo(long id, int transparent, int retry, String phone, Uri uri) {
            this.id = id;
            this.transparent = transparent;
            this.retry = retry;
            this.phone = phone;
            this.uri = uri;
        }
    }

    private static Uri addMessageToUri(ContentResolver resolver, Uri uri, String address, String body, String subject, Long date) {
        ContentValues values = new ContentValues(7);
        int subId = SubscriptionManager.getDefaultSmsSubscriptionId();
        Log.v(TAG, "Telephony addMessageToUri sub id: " + subId);
        values.put("sub_id", Integer.valueOf(subId));
        values.put("address", address);
        if (date != null) {
            values.put(BluetoothMapContract.MessageColumns.DATE, date);
        }
        values.put(BluetoothMapContract.FILTER_READ_STATUS, (Integer) 0);
        values.put(BluetoothMapContract.MessageColumns.SUBJECT, subject);
        values.put(BluetoothMapContract.MessageColumns.BODY, body);
        values.put("status", (Integer) 32);
        return resolver.insert(uri, values);
    }

    /* JADX WARN: Removed duplicated region for block: B:206:0x0423 A[Catch: all -> 0x042e, TryCatch #9 {all -> 0x042e, blocks: (B:211:0x042c, B:206:0x0423, B:208:0x0427, B:198:0x0417, B:199:0x041a), top: B:236:0x0310 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long pushMessage(com.android.bluetooth.map.BluetoothMapbMessage r35, com.android.bluetooth.map.BluetoothMapFolderElement r36, com.android.bluetooth.map.BluetoothMapAppParams r37, java.lang.String r38) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1098
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.map.BluetoothMapContentObserver.pushMessage(com.android.bluetooth.map.BluetoothMapbMessage, com.android.bluetooth.map.BluetoothMapFolderElement, com.android.bluetooth.map.BluetoothMapAppParams, java.lang.String):long");
    }

    public long sendMmsMessage(String folder, String[] toAddress, BluetoothMapbMessageMime msg, int transparent, int retry) throws Throwable {
        if (folder != null && (folder.equalsIgnoreCase(BluetoothMapContract.FOLDER_NAME_OUTBOX) || folder.equalsIgnoreCase(BluetoothMapContract.FOLDER_NAME_DRAFT))) {
            long handle = pushMmsToFolder(3, toAddress, msg);
            if (-1 != handle && folder.equalsIgnoreCase(BluetoothMapContract.FOLDER_NAME_OUTBOX)) {
                Uri btMmsUri = MmsFileProvider.CONTENT_URI.buildUpon().appendPath(Long.toString(handle)).build();
                Intent sentIntent = new Intent(ACTION_MESSAGE_SENT);
                sentIntent.setType("message/" + Long.toString(handle));
                sentIntent.putExtra(EXTRA_MESSAGE_SENT_MSG_TYPE, BluetoothMapUtils.TYPE.MMS.ordinal());
                sentIntent.putExtra(EXTRA_MESSAGE_SENT_HANDLE, handle);
                sentIntent.putExtra(EXTRA_MESSAGE_SENT_TRANSPARENT, transparent);
                sentIntent.putExtra(EXTRA_MESSAGE_SENT_RETRY, retry);
                PendingIntent pendingSendIntent = PendingIntent.getBroadcast(this.mContext, 0, sentIntent, 0);
                SmsManager.getDefault().sendMultimediaMessage(this.mContext, btMmsUri, null, null, pendingSendIntent);
            }
            return handle;
        }
        throw new IllegalArgumentException("Cannot push message to other folders than outbox/draft");
    }

    private void moveDraftToOutbox(long handle) {
        moveMmsToFolder(handle, this.mResolver, 4);
    }

    private static void moveMmsToFolder(long handle, ContentResolver resolver, int folder) {
        if (handle != -1) {
            String whereClause = " _id= " + handle;
            Uri uri = Telephony.Mms.CONTENT_URI;
            Cursor queryResult = resolver.query(uri, null, whereClause, null, null);
            try {
                if (queryResult != null) {
                    if (queryResult.getCount() > 0) {
                        queryResult.moveToFirst();
                        ContentValues data = new ContentValues();
                        data.put("msg_box", Integer.valueOf(folder));
                        resolver.update(uri, data, whereClause, null);
                    }
                } else {
                    Log.w(TAG, "Could not move MMS message to " + getMmsFolderName(folder));
                }
                if (queryResult != null) {
                    queryResult.close();
                }
            } catch (Throwable th) {
                if (queryResult != null) {
                    queryResult.close();
                }
                throw th;
            }
        }
    }

    private long pushMmsToFolder(int folder, String[] toAddress, BluetoothMapbMessageMime msg) throws Throwable {
        Uri uri;
        ContentValues values = new ContentValues();
        values.put("msg_box", Integer.valueOf(folder));
        values.put(BluetoothMapContract.FILTER_READ_STATUS, (Integer) 0);
        values.put("seen", (Integer) 0);
        if (msg.getSubject() != null) {
            values.put("sub", msg.getSubject());
        } else {
            values.put("sub", INetd.NEXTHOP_NONE);
        }
        if (msg.getSubject() != null && msg.getSubject().length() > 0) {
            values.put("sub_cs", (Integer) 106);
        }
        values.put("ct_t", "application/vnd.wap.multipart.related");
        values.put("exp", (Integer) 604800);
        values.put("m_cls", "personal");
        values.put("m_type", (Integer) 128);
        values.put("v", (Integer) 18);
        values.put("pri", Integer.valueOf(BluetoothMapContent.MMS_BCC));
        values.put("rr", Integer.valueOf(BluetoothMapContent.MMS_BCC));
        values.put("tr_id", "T" + Long.toHexString(System.currentTimeMillis()));
        values.put("d_rpt", Integer.valueOf(BluetoothMapContent.MMS_BCC));
        values.put("locked", (Integer) 0);
        if (msg.getTextOnly()) {
            values.put("text_only", (Boolean) true);
        }
        values.put("m_size", Integer.valueOf(msg.getSize()));
        Set<String> recipients = new HashSet<>();
        recipients.addAll(Arrays.asList(toAddress));
        values.put("thread_id", Long.valueOf(Telephony.Threads.getOrCreateThreadId(this.mContext, recipients)));
        Uri uri2 = Telephony.Mms.CONTENT_URI;
        synchronized (getMsgListMms()) {
            try {
                uri = this.mResolver.insert(uri2, values);
            } catch (Throwable th) {
                th = th;
            }
            try {
                if (uri == null) {
                    Log.e(TAG, "Unabled to insert MMS " + values + "Uri: " + uri);
                    return -1L;
                }
                Cursor c = this.mResolver.query(uri, MMS_PROJECTION_SHORT, null, null, null);
                if (c != null) {
                    try {
                        if (c.moveToFirst()) {
                            long id = c.getLong(c.getColumnIndex("_id"));
                            int type = c.getInt(c.getColumnIndex("msg_box"));
                            int threadId = c.getInt(c.getColumnIndex("thread_id"));
                            int readStatus = c.getInt(c.getColumnIndex(BluetoothMapContract.FILTER_READ_STATUS));
                            Msg newMsg = new Msg(id, type, threadId, readStatus);
                            newMsg.localInitiatedSend = true;
                            getMsgListMms().put(Long.valueOf(id), newMsg);
                            c.close();
                        }
                    } finally {
                        if (c != null) {
                            c.close();
                        }
                    }
                }
                long handle = Long.parseLong(uri.getLastPathSegment());
                try {
                    if (msg.getMimeParts() == null) {
                        Log.w(TAG, "No MMS parts present...");
                    } else {
                        for (BluetoothMapbMessageMime.MimePart part : msg.getMimeParts()) {
                            int count = 0 + 1;
                            values.clear();
                            if (part.mContentType != null && part.mContentType.toUpperCase().contains("TEXT")) {
                                values.put("ct", "text/plain");
                                values.put("chset", (Integer) 106);
                                if (part.mPartName != null) {
                                    values.put("fn", part.mPartName);
                                    values.put("name", part.mPartName);
                                } else {
                                    values.put("fn", "text_" + count + ".txt");
                                    values.put("name", "text_" + count + ".txt");
                                }
                                if (part.mContentId != null) {
                                    values.put(BluetoothMapContract.MessagePartColumns.CONTENT_ID, part.mContentId);
                                } else if (part.mPartName != null) {
                                    values.put(BluetoothMapContract.MessagePartColumns.CONTENT_ID, "<" + part.mPartName + ">");
                                } else {
                                    values.put(BluetoothMapContract.MessagePartColumns.CONTENT_ID, "<text_" + count + ">");
                                }
                                if (part.mContentLocation != null) {
                                    values.put("cl", part.mContentLocation);
                                } else if (part.mPartName != null) {
                                    values.put("cl", part.mPartName + ".txt");
                                } else {
                                    values.put("cl", "text_" + count + ".txt");
                                }
                                if (part.mContentDisposition != null) {
                                    values.put("cd", part.mContentDisposition);
                                }
                                values.put(BluetoothMapContract.MessagePartColumns.TEXT, part.getDataAsString());
                                Uri uri3 = Uri.parse(Telephony.Mms.CONTENT_URI + "/" + handle + "/part");
                                this.mResolver.insert(uri3, values);
                            } else if (part.mContentType != null && part.mContentType.toUpperCase().contains("SMIL")) {
                                values.put("seq", (Integer) (-1));
                                values.put("ct", "application/smil");
                                if (part.mContentId != null) {
                                    values.put(BluetoothMapContract.MessagePartColumns.CONTENT_ID, part.mContentId);
                                } else {
                                    values.put(BluetoothMapContract.MessagePartColumns.CONTENT_ID, "<smil_" + count + ">");
                                }
                                if (part.mContentLocation != null) {
                                    values.put("cl", part.mContentLocation);
                                } else {
                                    values.put("cl", "smil_" + count + ".xml");
                                }
                                if (part.mContentDisposition != null) {
                                    values.put("cd", part.mContentDisposition);
                                }
                                values.put("fn", "smil.xml");
                                values.put("name", "smil.xml");
                                values.put(BluetoothMapContract.MessagePartColumns.TEXT, new String(part.mData, "UTF-8"));
                                Uri uri4 = Uri.parse(Telephony.Mms.CONTENT_URI + "/" + handle + "/part");
                                this.mResolver.insert(uri4, values);
                            } else {
                                writeMmsDataPart(handle, part, count);
                            }
                        }
                    }
                } catch (UnsupportedEncodingException e) {
                    Log.w(TAG, e);
                } catch (IOException e2) {
                    Log.w(TAG, e2);
                }
                values.clear();
                values.put("contact_id", "null");
                values.put("address", BluetoothMapContent.INSERT_ADDRES_TOKEN);
                values.put(EXTRA_MESSAGE_SENT_MSG_TYPE, Integer.valueOf(BluetoothMapContent.MMS_FROM));
                values.put(BluetoothMapContract.MessagePartColumns.CHARSET, (Integer) 106);
                Uri uri5 = Uri.parse(Telephony.Mms.CONTENT_URI + "/" + handle + "/addr");
                this.mResolver.insert(uri5, values);
                values.clear();
                values.put("contact_id", "null");
                values.put("address", String.join(",", toAddress));
                values.put(EXTRA_MESSAGE_SENT_MSG_TYPE, (Integer) 151);
                values.put(BluetoothMapContract.MessagePartColumns.CHARSET, (Integer) 106);
                Uri uri6 = Uri.parse(Telephony.Mms.CONTENT_URI + "/" + handle + "/addr");
                this.mResolver.insert(uri6, values);
                return handle;
            } catch (Throwable th2) {
                th = th2;
                while (true) {
                    try {
                        throw th;
                    } catch (Throwable th3) {
                        th = th3;
                    }
                }
            }
        }
    }

    private void writeMmsDataPart(long handle, BluetoothMapbMessageMime.MimePart part, int count) throws IOException {
        ContentValues values = new ContentValues();
        values.put("mid", Long.valueOf(handle));
        if (part.mContentType != null) {
            values.put("ct", part.mContentType);
        } else {
            Log.w(TAG, "MMS has no CONTENT_TYPE for part " + count);
        }
        if (part.mContentId != null) {
            values.put(BluetoothMapContract.MessagePartColumns.CONTENT_ID, part.mContentId);
        } else if (part.mPartName != null) {
            values.put(BluetoothMapContract.MessagePartColumns.CONTENT_ID, "<" + part.mPartName + ">");
        } else {
            values.put(BluetoothMapContract.MessagePartColumns.CONTENT_ID, "<part_" + count + ">");
        }
        if (part.mContentLocation != null) {
            values.put("cl", part.mContentLocation);
        } else if (part.mPartName != null) {
            values.put("cl", part.mPartName + ".dat");
        } else {
            values.put("cl", "part_" + count + ".dat");
        }
        if (part.mContentDisposition != null) {
            values.put("cd", part.mContentDisposition);
        }
        if (part.mPartName != null) {
            values.put("fn", part.mPartName);
            values.put("name", part.mPartName);
        } else {
            values.put("fn", "part_" + count + ".dat");
            values.put("name", "part_" + count + ".dat");
        }
        Uri partUri = Uri.parse(Telephony.Mms.CONTENT_URI + "/" + handle + "/part");
        Uri res = this.mResolver.insert(partUri, values);
        OutputStream os = this.mResolver.openOutputStream(res);
        os.write(part.mData);
        os.close();
    }

    public void sendMessage(PushMsgInfo msgInfo, String msgBody) {
        SmsManager smsMng = SmsManager.getDefault();
        ArrayList<String> parts = smsMng.divideMessage(msgBody);
        msgInfo.parts = parts.size();
        msgInfo.timestamp = Calendar.getInstance().getTime().getTime();
        int i = 0;
        msgInfo.partsDelivered = 0;
        msgInfo.partsSent = 0;
        ArrayList<PendingIntent> deliveryIntents = new ArrayList<>(msgInfo.parts);
        ArrayList<PendingIntent> sentIntents = new ArrayList<>(msgInfo.parts);
        if (parts != null && parts.size() > 0) {
            int i2 = 0;
            while (i2 < msgInfo.parts) {
                Intent intentDelivery = new Intent(ACTION_MESSAGE_DELIVERY, (Uri) null);
                intentDelivery.setType("message/" + Long.toString(msgInfo.id) + msgInfo.timestamp + i2);
                intentDelivery.putExtra(EXTRA_MESSAGE_SENT_HANDLE, msgInfo.id);
                intentDelivery.putExtra("timestamp", msgInfo.timestamp);
                PendingIntent pendingIntentDelivery = PendingIntent.getBroadcast(this.mContext, i, intentDelivery, VCardConfig.FLAG_CONVERT_PHONETIC_NAME_STRINGS);
                Intent intentSent = new Intent(ACTION_MESSAGE_SENT, (Uri) null);
                intentSent.setType("message/" + Long.toString(msgInfo.id) + msgInfo.timestamp + i2);
                intentSent.putExtra(EXTRA_MESSAGE_SENT_HANDLE, msgInfo.id);
                intentSent.putExtra("uri", msgInfo.uri.toString());
                intentSent.putExtra(EXTRA_MESSAGE_SENT_RETRY, msgInfo.retry);
                intentSent.putExtra(EXTRA_MESSAGE_SENT_TRANSPARENT, msgInfo.transparent);
                PendingIntent pendingIntentSent = PendingIntent.getBroadcast(this.mContext, 0, intentSent, VCardConfig.FLAG_CONVERT_PHONETIC_NAME_STRINGS);
                deliveryIntents.add(pendingIntentDelivery);
                sentIntents.add(pendingIntentSent);
                i2++;
                i = 0;
            }
            Log.d(TAG, "sendMessage to " + msgInfo.phone);
            if (parts.size() == 1) {
                smsMng.sendTextMessageWithoutPersisting(msgInfo.phone, null, parts.get(0), sentIntents.get(0), deliveryIntents.get(0));
            } else {
                smsMng.sendMultipartTextMessageWithoutPersisting(msgInfo.phone, null, parts, sentIntents, deliveryIntents);
            }
        }
    }

    private class SmsBroadcastReceiver extends BroadcastReceiver {
        private SmsBroadcastReceiver() {
        }

        public void register() {
            Handler handler = new Handler(Looper.getMainLooper());
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(BluetoothMapContentObserver.ACTION_MESSAGE_DELIVERY);
            try {
                intentFilter.addDataType("message/*");
            } catch (IntentFilter.MalformedMimeTypeException e) {
                Log.e(BluetoothMapContentObserver.TAG, "Wrong mime type!!!", e);
            }
            BluetoothMapContentObserver.this.mContext.registerReceiver(this, intentFilter, null, handler);
        }

        public void unregister() {
            try {
                BluetoothMapContentObserver.this.mContext.unregisterReceiver(this);
            } catch (IllegalArgumentException e) {
            }
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) throws Throwable {
            String action = intent.getAction();
            long handle = intent.getLongExtra(BluetoothMapContentObserver.EXTRA_MESSAGE_SENT_HANDLE, -1L);
            PushMsgInfo msgInfo = (PushMsgInfo) BluetoothMapContentObserver.this.mPushMsgList.get(Long.valueOf(handle));
            Log.d(BluetoothMapContentObserver.TAG, "onReceive: action" + action);
            if (msgInfo == null) {
                Log.d(BluetoothMapContentObserver.TAG, "onReceive: no msgInfo found for handle " + handle);
                return;
            }
            if (action.equals(BluetoothMapContentObserver.ACTION_MESSAGE_SENT)) {
                int result = intent.getIntExtra("result", 0);
                msgInfo.partsSent++;
                if (result != -1) {
                    msgInfo.failedSent = true;
                }
                if (msgInfo.partsSent == msgInfo.parts) {
                    actionMessageSent(context, intent, msgInfo, handle);
                    return;
                }
                return;
            }
            if (action.equals(BluetoothMapContentObserver.ACTION_MESSAGE_DELIVERY)) {
                long timestamp = intent.getLongExtra("timestamp", 0L);
                if (msgInfo.timestamp == timestamp) {
                    msgInfo.partsDelivered++;
                    return;
                }
                return;
            }
            Log.d(BluetoothMapContentObserver.TAG, "onReceive: Unknown action " + action);
        }

        private void actionMessageSent(Context context, Intent intent, PushMsgInfo msgInfo, long handle) throws Throwable {
            boolean delete;
            boolean delete2 = false;
            msgInfo.sendInProgress = false;
            if (!msgInfo.failedSent) {
                if (msgInfo.transparent == 0) {
                    if (!Utils.moveMessageToFolder(context, msgInfo.uri, true)) {
                        Log.w(BluetoothMapContentObserver.TAG, "Failed to move " + msgInfo.uri + " to SENT");
                    }
                } else {
                    delete2 = true;
                }
                Event evt = BluetoothMapContentObserver.this.new Event(BluetoothMapContentObserver.EVENT_TYPE_SENDING_SUCCESS, msgInfo.id, BluetoothMapContentObserver.getSmsFolderName(2), null, BluetoothMapContentObserver.this.mSmsType);
                BluetoothMapContentObserver.this.sendEvent(evt);
                delete = delete2;
            } else if (msgInfo.retry == 1) {
                msgInfo.resend = true;
                msgInfo.partsSent = 0;
                msgInfo.failedSent = false;
                Event evt2 = BluetoothMapContentObserver.this.new Event(BluetoothMapContentObserver.EVENT_TYPE_SENDING_FAILURE, msgInfo.id, BluetoothMapContentObserver.getSmsFolderName(4), null, BluetoothMapContentObserver.this.mSmsType);
                BluetoothMapContentObserver.this.sendEvent(evt2);
                delete = false;
            } else {
                if (msgInfo.transparent == 0) {
                    if (!Utils.moveMessageToFolder(context, msgInfo.uri, false)) {
                        Log.w(BluetoothMapContentObserver.TAG, "Failed to move " + msgInfo.uri + " to FAILED");
                    }
                } else {
                    delete2 = true;
                }
                Event evt3 = BluetoothMapContentObserver.this.new Event(BluetoothMapContentObserver.EVENT_TYPE_SENDING_FAILURE, msgInfo.id, BluetoothMapContentObserver.getSmsFolderName(5), null, BluetoothMapContentObserver.this.mSmsType);
                BluetoothMapContentObserver.this.sendEvent(evt3);
                delete = delete2;
            }
            if (delete) {
                synchronized (BluetoothMapContentObserver.this.getMsgListSms()) {
                    try {
                        BluetoothMapContentObserver.this.getMsgListSms().remove(Long.valueOf(msgInfo.id));
                    } catch (Throwable th) {
                        th = th;
                        while (true) {
                            try {
                                throw th;
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        }
                    }
                }
                Uri msgUri = ContentUris.withAppendedId(Telephony.Sms.CONTENT_URI, handle);
                BluetoothMapContentObserver.this.mResolver.delete(msgUri, null, null);
            }
        }

        private void actionMessageDelivery(Context context, Intent intent, PushMsgInfo msgInfo) {
            Uri messageUri = intent.getData();
            msgInfo.sendInProgress = false;
            Cursor cursor = BluetoothMapContentObserver.this.mResolver.query(msgInfo.uri, BluetoothMapContentObserver.ID_PROJECTION, null, null, null);
            try {
                if (cursor.moveToFirst()) {
                    int messageId = cursor.getInt(0);
                    Uri updateUri = ContentUris.withAppendedId(BluetoothMapContentObserver.UPDATE_STATUS_URI, messageId);
                    ContentValues contentValues = new ContentValues(2);
                    contentValues.put("status", Integer.valueOf(msgInfo.statusDelivered));
                    contentValues.put("date_sent", Long.valueOf(System.currentTimeMillis()));
                    BluetoothMapContentObserver.this.mResolver.update(updateUri, contentValues, null, null);
                } else {
                    Log.d(BluetoothMapContentObserver.TAG, "Can't find message for status update: " + messageUri);
                }
                if (msgInfo.statusDelivered == 0) {
                    Event evt = BluetoothMapContentObserver.this.new Event(BluetoothMapContentObserver.EVENT_TYPE_DELEVERY_SUCCESS, msgInfo.id, BluetoothMapContentObserver.getSmsFolderName(2), null, BluetoothMapContentObserver.this.mSmsType);
                    BluetoothMapContentObserver.this.sendEvent(evt);
                } else {
                    Event evt2 = BluetoothMapContentObserver.this.new Event(BluetoothMapContentObserver.EVENT_TYPE_DELIVERY_FAILURE, msgInfo.id, BluetoothMapContentObserver.getSmsFolderName(2), null, BluetoothMapContentObserver.this.mSmsType);
                    BluetoothMapContentObserver.this.sendEvent(evt2);
                }
                BluetoothMapContentObserver.this.mPushMsgList.remove(Long.valueOf(msgInfo.id));
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
    }

    private class CeBroadcastReceiver extends BroadcastReceiver {
        private CeBroadcastReceiver() {
        }

        public void register() {
            UserManager manager = UserManager.get(BluetoothMapContentObserver.this.mContext);
            if (manager == null || manager.isUserUnlocked()) {
                BluetoothMapContentObserver.this.mStorageUnlocked = true;
                return;
            }
            Handler handler = new Handler(Looper.getMainLooper());
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.BOOT_COMPLETED");
            BluetoothMapContentObserver.this.mContext.registerReceiver(this, intentFilter, null, handler);
        }

        public void unregister() {
            try {
                BluetoothMapContentObserver.this.mContext.unregisterReceiver(this);
            } catch (IllegalArgumentException e) {
            }
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(BluetoothMapContentObserver.TAG, "onReceive: action" + action);
            if (action.equals("android.intent.action.BOOT_COMPLETED")) {
                try {
                    BluetoothMapContentObserver.this.initMsgList();
                } catch (RemoteException e) {
                    Log.e(BluetoothMapContentObserver.TAG, "Error initializing SMS/MMS message lists.");
                }
                for (String folder : BluetoothMapContentObserver.FOLDER_SMS_MAP.values()) {
                    BluetoothMapContentObserver bluetoothMapContentObserver = BluetoothMapContentObserver.this;
                    Event evt = bluetoothMapContentObserver.new Event(BluetoothMapContentObserver.EVENT_TYPE_NEW, -1L, folder, bluetoothMapContentObserver.mSmsType);
                    BluetoothMapContentObserver.this.sendEvent(evt);
                }
                BluetoothMapContentObserver.this.mStorageUnlocked = true;
                unregister();
                return;
            }
            Log.d(BluetoothMapContentObserver.TAG, "onReceive: Unknown action " + action);
        }
    }

    public static void actionMmsSent(Context context, Intent intent, int result, Map<Long, Msg> mmsMsgList) {
        int transparent = intent.getIntExtra(EXTRA_MESSAGE_SENT_TRANSPARENT, 0);
        long handle = intent.getLongExtra(EXTRA_MESSAGE_SENT_HANDLE, -1L);
        if (handle < 0) {
            Log.w(TAG, "Intent received for an invalid handle");
            return;
        }
        ContentResolver resolver = context.getContentResolver();
        if (transparent == 1) {
            Uri uri = ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, handle);
            if (mmsMsgList != null) {
                synchronized (mmsMsgList) {
                    mmsMsgList.remove(Long.valueOf(handle));
                }
            }
            resolver.delete(uri, null, null);
            return;
        }
        if (result == -1) {
            moveMmsToFolder(handle, resolver, 2);
            return;
        }
        if (mmsMsgList != null) {
            synchronized (mmsMsgList) {
                Msg msg = mmsMsgList.get(Long.valueOf(handle));
                if (msg != null) {
                    msg.type = 4;
                }
            }
        }
        moveMmsToFolder(handle, resolver, 4);
    }

    public static void actionMessageSentDisconnected(Context context, Intent intent, int result) {
        BluetoothMapUtils.TYPE type = BluetoothMapUtils.TYPE.fromOrdinal(intent.getIntExtra(EXTRA_MESSAGE_SENT_MSG_TYPE, BluetoothMapUtils.TYPE.NONE.ordinal()));
        if (type == BluetoothMapUtils.TYPE.MMS) {
            actionMmsSent(context, intent, result, null);
        } else {
            actionSmsSentDisconnected(context, intent, result);
        }
    }

    public static void actionSmsSentDisconnected(Context context, Intent intent, int result) {
        if (Binder.getCallingPid() != Process.myPid() || context.checkCallingOrSelfPermission("android.Manifest.permission.WRITE_SMS") != 0) {
            Log.w(TAG, "actionSmsSentDisconnected: Not allowed to delete SMS/MMS messages");
            return;
        }
        boolean delete = false;
        int transparent = intent.getIntExtra(EXTRA_MESSAGE_SENT_TRANSPARENT, 0);
        String uriString = intent.getStringExtra("uri");
        if (uriString == null) {
            return;
        }
        Uri uri = Uri.parse(uriString);
        if (result == -1) {
            Log.d(TAG, "actionMessageSentDisconnected: result OK");
            if (transparent == 0) {
                if (!Utils.moveMessageToFolder(context, uri, true)) {
                    Log.d(TAG, "Failed to move " + uri + " to SENT");
                }
            } else {
                delete = true;
            }
        } else if (transparent == 0) {
            if (!Utils.moveMessageToFolder(context, uri, false)) {
                Log.d(TAG, "Failed to move " + uri + " to FAILED");
            }
        } else {
            delete = true;
        }
        if (delete) {
            ContentResolver resolver = context.getContentResolver();
            if (resolver != null) {
                resolver.delete(uri, null, null);
            } else {
                Log.w(TAG, "Unable to get resolver");
            }
        }
    }

    private void registerPhoneServiceStateListener() {
        TelephonyManager tm = (TelephonyManager) this.mContext.getSystemService("phone");
        tm.listen(this.mPhoneListener, 1);
    }

    private void unRegisterPhoneServiceStateListener() {
        TelephonyManager tm = (TelephonyManager) this.mContext.getSystemService("phone");
        tm.listen(this.mPhoneListener, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resendPendingMessages() {
        UserManager manager = UserManager.get(this.mContext);
        if (manager != null && manager.isUserUnlocked()) {
            Cursor c = this.mResolver.query(Telephony.Sms.CONTENT_URI, SMS_PROJECTION, "type = 4", null, null);
            if (c != null) {
                try {
                    if (c.moveToFirst()) {
                        do {
                            long id = c.getLong(c.getColumnIndex("_id"));
                            String msgBody = c.getString(c.getColumnIndex(BluetoothMapContract.MessageColumns.BODY));
                            PushMsgInfo msgInfo = this.mPushMsgList.get(Long.valueOf(id));
                            if (msgInfo != null && msgInfo.resend && !msgInfo.sendInProgress) {
                                msgInfo.sendInProgress = true;
                                sendMessage(msgInfo, msgBody);
                            }
                        } while (c.moveToNext());
                    }
                } finally {
                    if (c != null) {
                        c.close();
                    }
                }
            }
        }
    }

    private void failPendingMessages() {
        Cursor c = this.mResolver.query(Telephony.Sms.CONTENT_URI, SMS_PROJECTION, "type = 4", null, null);
        if (c != null) {
            try {
                if (c.moveToFirst()) {
                    do {
                        long id = c.getLong(c.getColumnIndex("_id"));
                        c.getString(c.getColumnIndex(BluetoothMapContract.MessageColumns.BODY));
                        PushMsgInfo msgInfo = this.mPushMsgList.get(Long.valueOf(id));
                        if (msgInfo != null && msgInfo.resend) {
                            Utils.moveMessageToFolder(this.mContext, msgInfo.uri, false);
                        }
                    } while (c.moveToNext());
                }
            } finally {
                if (c != null) {
                    c.close();
                }
            }
        }
    }

    private void removeDeletedMessages() {
        this.mResolver.delete(Telephony.Sms.CONTENT_URI, "thread_id = -1", null);
    }

    public void init() {
        SmsBroadcastReceiver smsBroadcastReceiver = this.mSmsBroadcastReceiver;
        if (smsBroadcastReceiver != null) {
            smsBroadcastReceiver.register();
        }
        CeBroadcastReceiver ceBroadcastReceiver = this.mCeBroadcastReceiver;
        if (ceBroadcastReceiver != null) {
            ceBroadcastReceiver.register();
        }
        registerPhoneServiceStateListener();
        this.mInitialized = true;
    }

    public void deinit() {
        this.mInitialized = false;
        unregisterObserver();
        SmsBroadcastReceiver smsBroadcastReceiver = this.mSmsBroadcastReceiver;
        if (smsBroadcastReceiver != null) {
            smsBroadcastReceiver.unregister();
        }
        unRegisterPhoneServiceStateListener();
        if (UserManager.get(this.mContext).isUserUnlocked()) {
            failPendingMessages();
            removeDeletedMessages();
        }
    }

    public boolean handleSmsSendIntent(Context context, Intent intent) throws Throwable {
        BluetoothMapUtils.TYPE type = BluetoothMapUtils.TYPE.fromOrdinal(intent.getIntExtra(EXTRA_MESSAGE_SENT_MSG_TYPE, BluetoothMapUtils.TYPE.NONE.ordinal()));
        if (type == BluetoothMapUtils.TYPE.MMS) {
            return handleMmsSendIntent(context, intent);
        }
        if (this.mInitialized) {
            this.mSmsBroadcastReceiver.onReceive(context, intent);
            return true;
        }
        return false;
    }

    public boolean handleMmsSendIntent(Context context, Intent intent) {
        if (!this.mMnsClient.isConnected()) {
            return false;
        }
        long handle = intent.getLongExtra(EXTRA_MESSAGE_SENT_HANDLE, -1L);
        int result = intent.getIntExtra("result", 0);
        actionMmsSent(context, intent, result, getMsgListMms());
        if (handle < 0) {
            Log.w(TAG, "Intent received for an invalid handle");
            return true;
        }
        if (result != -1) {
            if (this.mObserverRegistered) {
                Event evt = new Event(EVENT_TYPE_SENDING_FAILURE, handle, getMmsFolderName(4), null, BluetoothMapUtils.TYPE.MMS);
                sendEvent(evt);
            }
        } else {
            int transparent = intent.getIntExtra(EXTRA_MESSAGE_SENT_TRANSPARENT, 0);
            if (transparent != 0 && this.mObserverRegistered) {
                Event evt2 = new Event(EVENT_TYPE_SENDING_SUCCESS, handle, getMmsFolderName(4), null, BluetoothMapUtils.TYPE.MMS);
                sendEvent(evt2);
            }
        }
        return true;
    }
}
