package com.android.bluetooth.mapclient;

import android.app.PendingIntent;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothUuid;
import android.bluetooth.SdpMasRecord;
import android.content.Intent;
import android.net.Uri;
import android.os.SystemProperties;
import android.provider.Telephony;
import android.util.Log;
import com.android.bluetooth.BluetoothMetricsProto;
import com.android.bluetooth.Utils;
import com.android.bluetooth.btservice.MetricsLogger;
import com.android.bluetooth.btservice.ProfileService;
import com.android.bluetooth.map.BluetoothMapbMessageMime;
import com.android.bluetooth.mapclient.Bmessage;
import com.android.bluetooth.mapclient.EventReport;
import com.android.bluetooth.mapclient.MasClient;
import com.android.bluetooth.mapclient.RequestSetMessageStatus;
import com.android.bluetooth.statemachine.IState;
import com.android.bluetooth.statemachine.State;
import com.android.bluetooth.statemachine.StateMachine;
import com.android.vcard.VCardConfig;
import com.android.vcard.VCardConstants;
import com.android.vcard.VCardEntry;
import com.android.vcard.VCardProperty;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
final class MceStateMachine extends StateMachine {
    private static final String BLUETOOTH_MAP_AUTO_GET_NEW_MESSAGE = "vendor.bt.pts.mce.autogetnewmessage";
    private static final String BLUETOOTH_MAP_FILTER_MESSAGE_TYPE = "vendor.bt.pts.mce.messagetype";
    private static final String BLUETOOTH_MAP_FILTER_ORIGINATOR = "vendor.bt.pts.mce.originator";
    private static final String BLUETOOTH_MAP_FILTER_PERIODBEGIN = "vendor.bt.pts.mce.periodbegin";
    private static final String BLUETOOTH_MAP_FILTER_PERIODEND = "vendor.bt.pts.mce.periodend";
    private static final String BLUETOOTH_MAP_FILTER_PRIORITY = "vendor.bt.pts.mce.priority";
    private static final String BLUETOOTH_MAP_FILTER_READ_STATUS = "vendor.bt.pts.mce.readstatus";
    private static final String BLUETOOTH_MAP_FILTER_RECIPIENT = "vendor.bt.pts.mce.recipient";
    private static final String BLUETOOTH_MAP_FILTER_USE_PROPERTY = "vendor.bt.pts.mce.useproperty";
    private static final String BLUETOOTH_MAP_INSTANCE_UNDER_TEST = "vendor.bt.pts.mce.instance";
    private static final String BLUETOOTH_MAP_TEST_UPLOAD = "vendor.bt.pts.mce.test.upload";
    private static final Boolean DBG = false;
    private static final String FOLDER_INBOX = "inbox";
    private static final String FOLDER_MSG = "msg";
    private static final String FOLDER_OUTBOX = "outbox";
    private static final String FOLDER_TELECOM = "telecom";
    private static final String INBOX_PATH = "telecom/msg/inbox";
    private static final int MAX_MESSAGES = 20;
    private static final String MESSAGES_FILTER_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final int MSG_CONNECT = 1;
    private static final int MSG_CONNECTING_TIMEOUT = 3;
    private static final int MSG_DISCONNECT = 2;
    private static final int MSG_DISCONNECTING_TIMEOUT = 4;
    static final int MSG_GET_LISTING = 2004;
    static final int MSG_GET_MESSAGE_LISTING = 2005;
    static final int MSG_INBOUND_MESSAGE = 2002;
    static final int MSG_MAS_CONNECTED = 1001;
    static final int MSG_MAS_DISCONNECTED = 1002;
    static final int MSG_MAS_REQUEST_COMPLETED = 1003;
    static final int MSG_MAS_REQUEST_FAILED = 1004;
    static final int MSG_MAS_SDP_DONE = 1005;
    static final int MSG_MAS_SDP_FAILED = 1006;
    static final int MSG_NOTIFICATION = 2003;
    static final int MSG_OUTBOUND_MESSAGE = 2001;
    static final int MSG_SET_MESSAGE_STATUS = 2006;
    private static final String TAG = "MceSM";
    private static final int TIMEOUT = 10000;
    private static final int UNSET = -1;
    private State mConnected;
    private State mConnecting;
    private Bmessage.Type mDefaultMessageType;
    private HashMap<Bmessage, PendingIntent> mDeliveryReceiptRequested;
    private final BluetoothDevice mDevice;
    private State mDisconnected;
    private State mDisconnecting;
    private MasClient mMasClient;
    private ConcurrentHashMap<String, MessageMetadata> mMessages;
    private int mPreviousState;
    private HashMap<String, Bmessage> mSentMessageLog;
    private HashMap<Bmessage, PendingIntent> mSentReceiptRequested;
    private MapClientService mService;

    private class MessageMetadata {
        private final String mHandle;
        private boolean mRead;
        private final Long mTimestamp;

        MessageMetadata(String handle, Long timestamp, boolean read) {
            this.mHandle = handle;
            this.mTimestamp = timestamp;
            this.mRead = read;
        }

        public String getHandle() {
            return this.mHandle;
        }

        public Long getTimestamp() {
            return this.mTimestamp;
        }

        public synchronized boolean getRead() {
            return this.mRead;
        }

        public synchronized void setRead(boolean read) {
            this.mRead = read;
        }
    }

    MceStateMachine(MapClientService service, BluetoothDevice device) {
        this(service, device, null);
    }

    MceStateMachine(MapClientService service, BluetoothDevice device, MasClient masClient) {
        super(TAG);
        this.mPreviousState = 0;
        this.mSentMessageLog = new HashMap<>(20);
        this.mSentReceiptRequested = new HashMap<>(20);
        this.mDeliveryReceiptRequested = new HashMap<>(20);
        this.mDefaultMessageType = Bmessage.Type.SMS_CDMA;
        this.mMessages = new ConcurrentHashMap<>();
        this.mMasClient = masClient;
        this.mService = service;
        this.mPreviousState = 0;
        this.mDevice = device;
        this.mDisconnected = new Disconnected();
        this.mConnecting = new Connecting();
        this.mDisconnecting = new Disconnecting();
        this.mConnected = new Connected();
        addState(this.mDisconnected);
        addState(this.mConnecting);
        addState(this.mDisconnecting);
        addState(this.mConnected);
        setInitialState(this.mConnecting);
        start();
    }

    public void doQuit() {
        quitNow();
    }

    @Override // com.android.bluetooth.statemachine.StateMachine
    protected void onQuitting() {
        MapClientService mapClientService = this.mService;
        if (mapClientService != null) {
            mapClientService.cleanupDevice(this.mDevice);
        }
    }

    synchronized BluetoothDevice getDevice() {
        return this.mDevice;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onConnectionStateChanged(int prevState, int state) {
        if (this.mDevice == null) {
            return;
        }
        if (DBG.booleanValue()) {
            Log.d(TAG, "Connection state " + this.mDevice + ": " + prevState + "->" + state);
        }
        if (prevState != state && state == 2) {
            MetricsLogger.logProfileConnectionEvent(BluetoothMetricsProto.ProfileId.MAP_CLIENT);
        }
        Intent intent = new Intent("android.bluetooth.mapmce.profile.action.CONNECTION_STATE_CHANGED");
        intent.putExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", prevState);
        intent.putExtra("android.bluetooth.profile.extra.STATE", state);
        intent.putExtra("android.bluetooth.device.extra.DEVICE", this.mDevice);
        intent.addFlags(VCardConfig.FLAG_APPEND_TYPE_PARAM);
        this.mService.sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
    }

    public synchronized int getState() {
        IState currentState = getCurrentState();
        if (currentState != null && currentState.getClass() != Disconnected.class) {
            if (currentState.getClass() == Connected.class) {
                return 2;
            }
            if (currentState.getClass() == Connecting.class) {
                return 1;
            }
            if (currentState.getClass() != Disconnecting.class) {
                return 0;
            }
            return 3;
        }
        return 0;
    }

    public boolean disconnect() {
        if (DBG.booleanValue()) {
            Log.d(TAG, "Disconnect Request " + this.mDevice.getAddress());
        }
        sendMessage(2, this.mDevice);
        return true;
    }

    public synchronized boolean sendMapMessage(Uri[] contacts, String message, PendingIntent sentIntent, PendingIntent deliveredIntent) {
        if (DBG.booleanValue()) {
            Log.d(TAG, "Send Message " + message);
        }
        if (contacts != null && contacts.length > 0) {
            if (getCurrentState() != this.mConnected) {
                return false;
            }
            Bmessage bmsg = new Bmessage();
            bmsg.setType(getDefaultMessageType());
            bmsg.setStatus(Bmessage.Status.READ);
            for (Uri contact : contacts) {
                VCardEntry destEntry = new VCardEntry();
                VCardProperty destEntryPhone = new VCardProperty();
                if (DBG.booleanValue()) {
                    Log.d(TAG, "Scheme " + contact.getScheme());
                }
                if ("tel".equals(contact.getScheme())) {
                    destEntryPhone.setName(VCardConstants.PROPERTY_TEL);
                    destEntryPhone.addValues(contact.getSchemeSpecificPart());
                    if (DBG.booleanValue()) {
                        Log.d(TAG, "Sending to phone numbers " + destEntryPhone.getValueList());
                    }
                    destEntry.addProperty(destEntryPhone);
                    bmsg.addRecipient(destEntry);
                } else {
                    if (DBG.booleanValue()) {
                        Log.w(TAG, "Scheme " + contact.getScheme() + " not supported.");
                    }
                    return false;
                }
            }
            bmsg.setBodyContent(message);
            if (sentIntent != null) {
                this.mSentReceiptRequested.put(bmsg, sentIntent);
            }
            if (deliveredIntent != null) {
                this.mDeliveryReceiptRequested.put(bmsg, deliveredIntent);
            }
            sendMessage(MSG_OUTBOUND_MESSAGE, bmsg);
            return true;
        }
        return false;
    }

    synchronized boolean getMessage(String handle) {
        if (DBG.booleanValue()) {
            Log.d(TAG, "getMessage" + handle);
        }
        if (getCurrentState() == this.mConnected) {
            sendMessage(MSG_INBOUND_MESSAGE, handle);
            return true;
        }
        return false;
    }

    synchronized boolean getUnreadMessages() {
        if (DBG.booleanValue()) {
            Log.d(TAG, "getMessage");
        }
        if (getCurrentState() == this.mConnected) {
            sendMessage(MSG_GET_MESSAGE_LISTING, "inbox");
            return true;
        }
        return false;
    }

    synchronized int getSupportedFeatures() {
        if (getCurrentState() == this.mConnected && this.mMasClient != null) {
            if (DBG.booleanValue()) {
                Log.d(TAG, "returning getSupportedFeatures from SDP record");
            }
            return this.mMasClient.getSdpMasRecord().getSupportedFeatures();
        }
        if (DBG.booleanValue()) {
            Log.d(TAG, "in getSupportedFeatures, returning 0");
        }
        return 0;
    }

    synchronized boolean setMessageStatus(String handle, int status) {
        RequestSetMessageStatus.StatusIndicator statusIndicator;
        byte value;
        if (DBG.booleanValue()) {
            Log.d(TAG, "setMessageStatus(" + handle + ", " + status + ")");
        }
        if (getCurrentState() != this.mConnected) {
            return false;
        }
        if (status != 0) {
            if (status == 1) {
                statusIndicator = RequestSetMessageStatus.StatusIndicator.READ;
                value = 1;
            } else if (status == 2) {
                statusIndicator = RequestSetMessageStatus.StatusIndicator.DELETED;
                value = 0;
            } else if (status == 3) {
                statusIndicator = RequestSetMessageStatus.StatusIndicator.DELETED;
                value = 1;
            } else {
                Log.e(TAG, "Invalid parameter for status" + status);
                return false;
            }
        } else {
            statusIndicator = RequestSetMessageStatus.StatusIndicator.READ;
            value = 0;
        }
        sendMessage(MSG_SET_MESSAGE_STATUS, 0, 0, new RequestSetMessageStatus(handle, statusIndicator, value));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getContactURIFromPhone(String number) {
        return "tel:" + number;
    }

    Bmessage.Type getDefaultMessageType() {
        synchronized (this.mDefaultMessageType) {
            if (Utils.isPtsTestMode()) {
                return MapUtils.sendMessageType();
            }
            return this.mDefaultMessageType;
        }
    }

    void setDefaultMessageType(SdpMasRecord sdpMasRecord) {
        int supportedMessageTypes = sdpMasRecord.getSupportedMessageTypes();
        synchronized (this.mDefaultMessageType) {
            try {
                if ((supportedMessageTypes & 8) > 0) {
                    this.mDefaultMessageType = Bmessage.Type.MMS;
                } else if ((supportedMessageTypes & 4) > 0) {
                    this.mDefaultMessageType = Bmessage.Type.SMS_CDMA;
                } else if ((supportedMessageTypes & 2) > 0) {
                    this.mDefaultMessageType = Bmessage.Type.SMS_GSM;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void dump(StringBuilder sb) {
        ProfileService.println(sb, "mCurrentDevice: " + this.mDevice.getAddress() + "(" + this.mDevice.getName() + ") " + toString());
    }

    class Disconnected extends State {
        Disconnected() {
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            if (MceStateMachine.DBG.booleanValue()) {
                Log.d(MceStateMachine.TAG, "Enter Disconnected: " + MceStateMachine.this.getCurrentMessage().what);
            }
            MceStateMachine mceStateMachine = MceStateMachine.this;
            mceStateMachine.onConnectionStateChanged(mceStateMachine.mPreviousState, 0);
            MceStateMachine.this.mPreviousState = 0;
            MceStateMachine.this.quit();
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void exit() {
            MceStateMachine.this.mPreviousState = 0;
        }
    }

    class Connecting extends State {
        Connecting() {
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            if (MceStateMachine.DBG.booleanValue()) {
                Log.d(MceStateMachine.TAG, "Enter Connecting: " + MceStateMachine.this.getCurrentMessage().what);
            }
            MceStateMachine mceStateMachine = MceStateMachine.this;
            mceStateMachine.onConnectionStateChanged(mceStateMachine.mPreviousState, 1);
            MceStateMachine.this.mDevice.sdpSearch(BluetoothUuid.MAS);
            MceStateMachine.this.sendMessageDelayed(3, 10000L);
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(android.os.Message message) {
            if (MceStateMachine.DBG.booleanValue()) {
                Log.d(MceStateMachine.TAG, "processMessage" + getName() + message.what);
            }
            int i = message.what;
            if (i == 1 || i == 2) {
                MceStateMachine.this.deferMessage(message);
            } else if (i == 3) {
                MceStateMachine mceStateMachine = MceStateMachine.this;
                mceStateMachine.transitionTo(mceStateMachine.mDisconnecting);
            } else if (i == 1001) {
                MceStateMachine mceStateMachine2 = MceStateMachine.this;
                mceStateMachine2.transitionTo(mceStateMachine2.mConnected);
            } else if (i == 1002) {
                if (MceStateMachine.this.mMasClient != null) {
                    MceStateMachine.this.mMasClient.shutdown();
                }
                MceStateMachine mceStateMachine3 = MceStateMachine.this;
                mceStateMachine3.transitionTo(mceStateMachine3.mDisconnected);
            } else if (i == MceStateMachine.MSG_MAS_SDP_DONE) {
                if (MceStateMachine.DBG.booleanValue()) {
                    Log.d(MceStateMachine.TAG, "SDP Complete");
                }
                if (MceStateMachine.this.mMasClient == null) {
                    SdpMasRecord record = (SdpMasRecord) message.obj;
                    if (record == null) {
                        Log.e(MceStateMachine.TAG, "Unexpected: SDP record is null for device " + MceStateMachine.this.mDevice.getName());
                        return false;
                    }
                    if (isValidInstance(record.getMasInstanceId())) {
                        MceStateMachine.this.mMasClient = new MasClient(MceStateMachine.this.mDevice, MceStateMachine.this, record);
                        MceStateMachine.this.setDefaultMessageType(record);
                    }
                }
            } else {
                Log.w(MceStateMachine.TAG, "Unexpected message: " + message.what + " from state:" + getName());
                return false;
            }
            return true;
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void exit() {
            MceStateMachine.this.mPreviousState = 1;
            MceStateMachine.this.removeMessages(3);
        }

        private boolean isValidInstance(int instance) {
            if (MceStateMachine.DBG.booleanValue()) {
                Log.d(MceStateMachine.TAG, "instance " + instance + ", getUnderTestInstance() " + getUnderTestInstance());
            }
            if (getUnderTestInstance() == -1 || isInstanceUnderTest(instance)) {
                return true;
            }
            return false;
        }

        private int getUnderTestInstance() {
            return SystemProperties.getInt(MceStateMachine.BLUETOOTH_MAP_INSTANCE_UNDER_TEST, -1);
        }

        private boolean isInstanceUnderTest(int instance) {
            if (getUnderTestInstance() == instance) {
                return true;
            }
            return false;
        }
    }

    class Connected extends State {
        Connected() {
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            if (MceStateMachine.DBG.booleanValue()) {
                Log.d(MceStateMachine.TAG, "Enter Connected: " + MceStateMachine.this.getCurrentMessage().what);
            }
            MceStateMachine mceStateMachine = MceStateMachine.this;
            mceStateMachine.onConnectionStateChanged(mceStateMachine.mPreviousState, 2);
            MceStateMachine.this.mMasClient.makeRequest(new RequestSetPath(MceStateMachine.FOLDER_TELECOM));
            MceStateMachine.this.mMasClient.makeRequest(new RequestSetPath("msg"));
            MceStateMachine.this.mMasClient.makeRequest(new RequestSetPath("inbox"));
            MceStateMachine.this.mMasClient.makeRequest(new RequestGetFolderListing(0, 0));
            MceStateMachine.this.mMasClient.makeRequest(new RequestSetPath(false));
            if (!isTestUpload()) {
                MceStateMachine.this.mMasClient.makeRequest(new RequestSetNotificationRegistration(true));
                MceStateMachine.this.mMasClient.makeRequest(new RequestUpdateInbox());
            }
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(android.os.Message message) {
            int i = message.what;
            if (i != 1) {
                if (i != 2) {
                    if (i == 1002) {
                        MceStateMachine.this.deferMessage(message);
                        MceStateMachine mceStateMachine = MceStateMachine.this;
                        mceStateMachine.transitionTo(mceStateMachine.mDisconnecting);
                    } else if (i != 1003) {
                        switch (i) {
                            case MceStateMachine.MSG_OUTBOUND_MESSAGE /* 2001 */:
                                MceStateMachine.this.mMasClient.makeRequest(new RequestPushMessage("outbox", (Bmessage) message.obj, null, false, false));
                                break;
                            case MceStateMachine.MSG_INBOUND_MESSAGE /* 2002 */:
                                MceStateMachine.this.mMasClient.makeRequest(new RequestGetMessage((String) message.obj, MasClient.CharsetType.UTF_8, false));
                                break;
                            case MceStateMachine.MSG_NOTIFICATION /* 2003 */:
                                processNotification(message);
                                break;
                            case MceStateMachine.MSG_GET_LISTING /* 2004 */:
                                MceStateMachine.this.mMasClient.makeRequest(new RequestGetFolderListing(0, 0));
                                break;
                            case MceStateMachine.MSG_GET_MESSAGE_LISTING /* 2005 */:
                                MessagesFilter filter = new MessagesFilter();
                                if (!isFilterPropUsed()) {
                                    filter.setMessageType(MapUtils.fetchMessageType());
                                    filter.setReadStatus((byte) 1);
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.add(5, -7);
                                    filter.setPeriod(calendar.getTime(), null);
                                } else {
                                    Log.d(MceStateMachine.TAG, "go here 123");
                                    setFilterFromProperties(filter);
                                }
                                MceStateMachine.this.mMasClient.makeRequest(new RequestGetMessagesListing((String) message.obj, 0, filter, 0, 50, 0));
                                break;
                            case MceStateMachine.MSG_SET_MESSAGE_STATUS /* 2006 */:
                                if (message.obj instanceof RequestSetMessageStatus) {
                                    MceStateMachine.this.mMasClient.makeRequest((RequestSetMessageStatus) message.obj);
                                }
                                break;
                            default:
                                Log.w(MceStateMachine.TAG, "Unexpected message: " + message.what + " from state:" + getName());
                                return false;
                        }
                    } else {
                        if (MceStateMachine.DBG.booleanValue()) {
                            Log.d(MceStateMachine.TAG, "Completed request");
                        }
                        if (message.obj instanceof RequestGetMessage) {
                            processInboundMessage((RequestGetMessage) message.obj);
                        } else if (message.obj instanceof RequestPushMessage) {
                            String messageHandle = ((RequestPushMessage) message.obj).getMsgHandle();
                            if (MceStateMachine.DBG.booleanValue()) {
                                Log.d(MceStateMachine.TAG, "Message Sent......." + messageHandle);
                            }
                            if (messageHandle != null && messageHandle.length() > 2) {
                                MceStateMachine.this.mSentMessageLog.put(messageHandle.substring(2), ((RequestPushMessage) message.obj).getBMsg());
                            }
                        } else if (message.obj instanceof RequestGetMessagesListing) {
                            processMessageListing((RequestGetMessagesListing) message.obj);
                        } else if (message.obj instanceof RequestSetMessageStatus) {
                            processSetMessageStatus((RequestSetMessageStatus) message.obj);
                        } else if (message.obj instanceof RequestUpdateInbox) {
                            processUpdateInbox((RequestUpdateInbox) message.obj);
                        }
                    }
                } else if (MceStateMachine.this.mDevice.equals(message.obj)) {
                    MceStateMachine mceStateMachine2 = MceStateMachine.this;
                    mceStateMachine2.transitionTo(mceStateMachine2.mDisconnecting);
                }
            } else if (!MceStateMachine.this.mDevice.equals(message.obj)) {
                MceStateMachine.this.deferMessage(message);
                MceStateMachine mceStateMachine3 = MceStateMachine.this;
                mceStateMachine3.transitionTo(mceStateMachine3.mDisconnecting);
            }
            return true;
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void exit() {
            MceStateMachine.this.mPreviousState = 2;
        }

        private void processNotification(android.os.Message msg) {
            if (MceStateMachine.DBG.booleanValue()) {
                Log.d(MceStateMachine.TAG, "Handler: msg: " + msg.what);
            }
            if (msg.what == MceStateMachine.MSG_NOTIFICATION) {
                EventReport ev = (EventReport) msg.obj;
                if (ev != null) {
                    if (MceStateMachine.DBG.booleanValue()) {
                        Log.d(MceStateMachine.TAG, "Message Type = " + ev.getType() + ", Message handle = " + ev.getHandle());
                    }
                    int i = AnonymousClass1.$SwitchMap$com$android$bluetooth$mapclient$EventReport$Type[ev.getType().ordinal()];
                    if (i != 1) {
                        if (i == 2 || i == 3) {
                            notifySentMessageStatus(ev.getHandle(), ev.getType());
                            return;
                        }
                        return;
                    }
                    if (isAutoGetNewMessage()) {
                        if (!MceStateMachine.this.mMessages.contains(ev.getHandle())) {
                            Calendar calendar = Calendar.getInstance();
                            MessageMetadata metadata = MceStateMachine.this.new MessageMetadata(ev.getHandle(), Long.valueOf(calendar.getTime().getTime()), false);
                            MceStateMachine.this.mMessages.put(ev.getHandle(), metadata);
                        }
                        MceStateMachine.this.mMasClient.makeRequest(new RequestGetMessage(ev.getHandle(), MasClient.CharsetType.UTF_8, false));
                        return;
                    }
                    return;
                }
                Log.w(MceStateMachine.TAG, "MSG_NOTIFICATION event is null");
            }
        }

        private boolean isAutoGetNewMessage() {
            return SystemProperties.getBoolean(MceStateMachine.BLUETOOTH_MAP_AUTO_GET_NEW_MESSAGE, true);
        }

        private boolean isFilterPropUsed() {
            return SystemProperties.getBoolean(MceStateMachine.BLUETOOTH_MAP_FILTER_USE_PROPERTY, false);
        }

        private boolean isTestUpload() {
            return SystemProperties.getBoolean(MceStateMachine.BLUETOOTH_MAP_TEST_UPLOAD, false);
        }

        private void setFilterFromProperties(MessagesFilter filter) {
            Date filterBegin;
            Date filterEnd;
            filter.setMessageType((byte) SystemProperties.getInt(MceStateMachine.BLUETOOTH_MAP_FILTER_MESSAGE_TYPE, 0));
            filter.setReadStatus((byte) SystemProperties.getInt(MceStateMachine.BLUETOOTH_MAP_FILTER_READ_STATUS, 1));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(MceStateMachine.MESSAGES_FILTER_DATE_FORMAT);
            try {
                filterBegin = simpleDateFormat.parse(SystemProperties.get(MceStateMachine.BLUETOOTH_MAP_FILTER_PERIODBEGIN));
            } catch (ParseException e) {
                Log.e(MceStateMachine.TAG, "Exception during parse begin period " + SystemProperties.get(MceStateMachine.BLUETOOTH_MAP_FILTER_PERIODBEGIN));
                filterBegin = null;
            }
            try {
                filterEnd = simpleDateFormat.parse(SystemProperties.get(MceStateMachine.BLUETOOTH_MAP_FILTER_PERIODEND));
            } catch (ParseException e2) {
                Log.e(MceStateMachine.TAG, "Exception during parse end period " + SystemProperties.get(MceStateMachine.BLUETOOTH_MAP_FILTER_PERIODEND));
                filterEnd = null;
            }
            filter.setPeriod(filterBegin, filterEnd);
            filter.setRecipient(SystemProperties.get(MceStateMachine.BLUETOOTH_MAP_FILTER_RECIPIENT));
            filter.setOriginator(SystemProperties.get(MceStateMachine.BLUETOOTH_MAP_FILTER_ORIGINATOR));
            filter.setPriority((byte) SystemProperties.getInt(MceStateMachine.BLUETOOTH_MAP_FILTER_PRIORITY, 0));
            if (MceStateMachine.DBG.booleanValue()) {
                Log.d(MceStateMachine.TAG, "filter " + filter);
            }
        }

        private void markMessageRead(RequestGetMessage request) {
            if (MceStateMachine.DBG.booleanValue()) {
                Log.d(MceStateMachine.TAG, "markMessageRead");
            }
            MessageMetadata metadata = (MessageMetadata) MceStateMachine.this.mMessages.get(request.getHandle());
            metadata.setRead(true);
            MceStateMachine.this.mMasClient.makeRequest(new RequestSetMessageStatus(request.getHandle(), RequestSetMessageStatus.StatusIndicator.READ, (byte) 1));
        }

        private void markMessageDeleted(RequestGetMessage request) {
            if (MceStateMachine.DBG.booleanValue()) {
                Log.d(MceStateMachine.TAG, "markMessageDeleted");
            }
            MceStateMachine.this.mMasClient.makeRequest(new RequestSetMessageStatus(request.getHandle(), RequestSetMessageStatus.StatusIndicator.DELETED, (byte) 1));
        }

        private void processMessageListing(RequestGetMessagesListing request) {
            if (MceStateMachine.DBG.booleanValue()) {
                Log.d(MceStateMachine.TAG, "processMessageListing");
            }
            ArrayList<Message> messageListing = request.getList();
            if (messageListing != null) {
                for (int i = messageListing.size() - 1; i >= 0; i--) {
                    Message msg = messageListing.get(i);
                    if (MceStateMachine.DBG.booleanValue()) {
                        Log.d(MceStateMachine.TAG, "getting message for handle " + msg.getHandle());
                    }
                    MceStateMachine.this.mMessages.put(msg.getHandle(), MceStateMachine.this.new MessageMetadata(msg.getHandle(), Long.valueOf(msg.getDateTime().getTime()), msg.isRead()));
                    MceStateMachine.this.getMessage(msg.getHandle());
                }
            }
        }

        private void processSetMessageStatus(RequestSetMessageStatus request) {
            Intent intent;
            if (MceStateMachine.DBG.booleanValue()) {
                Log.d(MceStateMachine.TAG, "processSetMessageStatus");
            }
            int result = 1;
            if (!request.isSuccess()) {
                Log.e(MceStateMachine.TAG, "Set message status failed");
                result = 0;
            }
            RequestSetMessageStatus.StatusIndicator status = request.getStatusIndicator();
            int i = AnonymousClass1.$SwitchMap$com$android$bluetooth$mapclient$RequestSetMessageStatus$StatusIndicator[status.ordinal()];
            if (i == 1) {
                intent = new Intent("android.bluetooth.mapmce.profile.action.MESSAGE_READ_STATUS_CHANGED");
                intent.putExtra("android.bluetooth.mapmce.profile.extra.MESSAGE_READ_STATUS", request.getValue() == 1);
            } else if (i == 2) {
                intent = new Intent("android.bluetooth.mapmce.profile.action.MESSAGE_DELETED_STATUS_CHANGED");
                intent.putExtra("android.bluetooth.mapmce.profile.extra.MESSAGE_DELETED_STATUS", request.getValue() == 1);
            } else {
                Log.e(MceStateMachine.TAG, "Unknown status indicator " + status);
                return;
            }
            intent.putExtra("android.bluetooth.mapmce.profile.extra.MESSAGE_HANDLE", request.getHandle());
            intent.putExtra("android.bluetooth.device.extra.RESULT_CODE", result);
            MceStateMachine.this.mService.sendBroadcast(intent);
        }

        private void processUpdateInbox(RequestUpdateInbox request) {
            if (request.isSuccess()) {
                if (MceStateMachine.DBG.booleanValue()) {
                    Log.d(MceStateMachine.TAG, "UpdateInbox success");
                    return;
                }
                return;
            }
            Log.e(MceStateMachine.TAG, "UpdateInbox failed");
        }

        private void processInboundMessage(RequestGetMessage request) {
            Bmessage message = request.getMessage();
            if (MceStateMachine.DBG.booleanValue()) {
                Log.d(MceStateMachine.TAG, "Notify inbound Message" + message);
            }
            if (message == null) {
                return;
            }
            if (!MceStateMachine.INBOX_PATH.equalsIgnoreCase(message.getFolder())) {
                if (MceStateMachine.DBG.booleanValue()) {
                    Log.d(MceStateMachine.TAG, "Ignoring message received in " + message.getFolder() + ".");
                    return;
                }
                return;
            }
            int i = AnonymousClass1.$SwitchMap$com$android$bluetooth$mapclient$Bmessage$Type[message.getType().ordinal()];
            if (i == 1 || i == 2 || i == 3) {
                if (MceStateMachine.DBG.booleanValue()) {
                    Log.d(MceStateMachine.TAG, "Body: " + message.getBodyContent());
                }
                if (MceStateMachine.DBG.booleanValue()) {
                    Log.d(MceStateMachine.TAG, message.toString());
                }
                if (MceStateMachine.DBG.booleanValue()) {
                    Log.d(MceStateMachine.TAG, "Recipients" + message.getRecipients().toString());
                }
                MessageMetadata metadata = (MessageMetadata) MceStateMachine.this.mMessages.get(request.getHandle());
                metadata.setRead(request.getMessage().getStatus() == Bmessage.Status.READ);
                Log.d(MceStateMachine.TAG, "MessageMetadata" + metadata);
                Intent intent = new Intent();
                intent.setAction("android.bluetooth.mapmce.profile.action.MESSAGE_RECEIVED");
                intent.putExtra("android.bluetooth.device.extra.DEVICE", MceStateMachine.this.mDevice);
                intent.putExtra("android.bluetooth.mapmce.profile.extra.MESSAGE_HANDLE", request.getHandle());
                intent.putExtra("android.bluetooth.mapmce.profile.extra.MESSAGE_TIMESTAMP", metadata.getTimestamp());
                intent.putExtra("android.bluetooth.mapmce.profile.extra.MESSAGE_READ_STATUS", metadata.getRead());
                intent.putExtra("android.intent.extra.TEXT", message.getBodyContent());
                VCardEntry originator = message.getOriginator();
                if (originator != null) {
                    if (MceStateMachine.DBG.booleanValue()) {
                        Log.d(MceStateMachine.TAG, originator.toString());
                    }
                    List<VCardEntry.PhoneData> phoneData = originator.getPhoneList();
                    if (phoneData != null && phoneData.size() > 0) {
                        String phoneNumber = phoneData.get(0).getNumber();
                        if (MceStateMachine.DBG.booleanValue()) {
                            Log.d(MceStateMachine.TAG, "Originator number: " + phoneNumber);
                        }
                        intent.putExtra("android.bluetooth.mapmce.profile.extra.SENDER_CONTACT_URI", MceStateMachine.this.getContactURIFromPhone(phoneNumber));
                    }
                    intent.putExtra("android.bluetooth.mapmce.profile.extra.SENDER_CONTACT_NAME", originator.getDisplayName());
                }
                if (message.getType() == Bmessage.Type.MMS) {
                    BluetoothMapbMessageMime mmsBmessage = new BluetoothMapbMessageMime();
                    mmsBmessage.parseMsgPart(message.getBodyContent());
                    intent.putExtra("android.intent.extra.TEXT", mmsBmessage.getMessageAsText());
                    ArrayList<VCardEntry> recipients = message.getRecipients();
                    if (recipients != null && !recipients.isEmpty()) {
                        intent.putExtra("android.intent.extra.CC", getRecipientsUri(recipients));
                    }
                }
                String defaultMessagingPackage = Telephony.Sms.getDefaultSmsPackage(MceStateMachine.this.mService);
                if (defaultMessagingPackage != null) {
                    intent.setPackage(defaultMessagingPackage);
                }
                Log.d(MceStateMachine.TAG, "go here");
                MceStateMachine.this.mService.sendBroadcast(intent, "android.permission.RECEIVE_SMS");
                return;
            }
            Log.e(MceStateMachine.TAG, "Received unhandled type" + message.getType().toString());
        }

        private String[] getRecipientsUri(ArrayList<VCardEntry> recipients) {
            Set<String> uris = new HashSet<>();
            for (VCardEntry recipient : recipients) {
                List<VCardEntry.PhoneData> phoneData = recipient.getPhoneList();
                if (phoneData != null && phoneData.size() > 0) {
                    String phoneNumber = phoneData.get(0).getNumber();
                    if (MceStateMachine.DBG.booleanValue()) {
                        Log.d(MceStateMachine.TAG, "CC Recipient number: " + phoneNumber);
                    }
                    uris.add(MceStateMachine.this.getContactURIFromPhone(phoneNumber));
                }
            }
            String[] stringUris = new String[uris.size()];
            return (String[]) uris.toArray(stringUris);
        }

        private void notifySentMessageStatus(String handle, EventReport.Type status) {
            if (MceStateMachine.DBG.booleanValue()) {
                Log.d(MceStateMachine.TAG, "got a status for " + handle + " Status = " + status);
            }
            if (handle == null || handle.length() <= 2) {
                return;
            }
            PendingIntent intentToSend = null;
            String shortHandle = handle.substring(2);
            if (status == EventReport.Type.SENDING_FAILURE || status == EventReport.Type.SENDING_SUCCESS) {
                intentToSend = (PendingIntent) MceStateMachine.this.mSentReceiptRequested.remove(MceStateMachine.this.mSentMessageLog.get(shortHandle));
            } else if (status == EventReport.Type.DELIVERY_SUCCESS || status == EventReport.Type.DELIVERY_FAILURE) {
                intentToSend = (PendingIntent) MceStateMachine.this.mDeliveryReceiptRequested.remove(MceStateMachine.this.mSentMessageLog.get(shortHandle));
            }
            if (intentToSend != null) {
                try {
                    if (MceStateMachine.DBG.booleanValue()) {
                        Log.d(MceStateMachine.TAG, "*******Sending " + intentToSend);
                    }
                    int result = -1;
                    if (status == EventReport.Type.SENDING_FAILURE || status == EventReport.Type.DELIVERY_FAILURE) {
                        result = 1;
                    }
                    intentToSend.send(result);
                    return;
                } catch (PendingIntent.CanceledException e) {
                    Log.w(MceStateMachine.TAG, "Notification Request Canceled" + e);
                    return;
                }
            }
            Log.e(MceStateMachine.TAG, "Received a notification on message with handle = " + handle + ", but it is NOT found in mSentMessageLog! where did it go?");
        }
    }

    /* JADX INFO: renamed from: com.android.bluetooth.mapclient.MceStateMachine$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$bluetooth$mapclient$Bmessage$Type;
        static final /* synthetic */ int[] $SwitchMap$com$android$bluetooth$mapclient$EventReport$Type;
        static final /* synthetic */ int[] $SwitchMap$com$android$bluetooth$mapclient$RequestSetMessageStatus$StatusIndicator;

        static {
            int[] iArr = new int[Bmessage.Type.values().length];
            $SwitchMap$com$android$bluetooth$mapclient$Bmessage$Type = iArr;
            try {
                iArr[Bmessage.Type.SMS_CDMA.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$android$bluetooth$mapclient$Bmessage$Type[Bmessage.Type.SMS_GSM.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$android$bluetooth$mapclient$Bmessage$Type[Bmessage.Type.MMS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$android$bluetooth$mapclient$Bmessage$Type[Bmessage.Type.EMAIL.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            int[] iArr2 = new int[RequestSetMessageStatus.StatusIndicator.values().length];
            $SwitchMap$com$android$bluetooth$mapclient$RequestSetMessageStatus$StatusIndicator = iArr2;
            try {
                iArr2[RequestSetMessageStatus.StatusIndicator.READ.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$android$bluetooth$mapclient$RequestSetMessageStatus$StatusIndicator[RequestSetMessageStatus.StatusIndicator.DELETED.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            int[] iArr3 = new int[EventReport.Type.values().length];
            $SwitchMap$com$android$bluetooth$mapclient$EventReport$Type = iArr3;
            try {
                iArr3[EventReport.Type.NEW_MESSAGE.ordinal()] = 1;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$android$bluetooth$mapclient$EventReport$Type[EventReport.Type.DELIVERY_SUCCESS.ordinal()] = 2;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$android$bluetooth$mapclient$EventReport$Type[EventReport.Type.SENDING_SUCCESS.ordinal()] = 3;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    class Disconnecting extends State {
        Disconnecting() {
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            if (MceStateMachine.DBG.booleanValue()) {
                Log.d(MceStateMachine.TAG, "Enter Disconnecting: " + MceStateMachine.this.getCurrentMessage().what);
            }
            MceStateMachine mceStateMachine = MceStateMachine.this;
            mceStateMachine.onConnectionStateChanged(mceStateMachine.mPreviousState, 3);
            if (MceStateMachine.this.mMasClient != null) {
                MceStateMachine.this.mMasClient.makeRequest(new RequestSetNotificationRegistration(false));
                MceStateMachine.this.mMasClient.shutdown();
                MceStateMachine.this.sendMessageDelayed(4, 10000L);
            } else {
                MceStateMachine mceStateMachine2 = MceStateMachine.this;
                mceStateMachine2.transitionTo(mceStateMachine2.mDisconnected);
            }
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(android.os.Message message) {
            int i = message.what;
            if (i == 1 || i == 2) {
                MceStateMachine.this.deferMessage(message);
            } else if (i == 4 || i == 1002) {
                MceStateMachine.this.mMasClient = null;
                MceStateMachine mceStateMachine = MceStateMachine.this;
                mceStateMachine.transitionTo(mceStateMachine.mDisconnected);
            } else {
                Log.w(MceStateMachine.TAG, "Unexpected message: " + message.what + " from state:" + getName());
                return false;
            }
            return true;
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void exit() {
            MceStateMachine.this.mPreviousState = 3;
            MceStateMachine.this.removeMessages(4);
        }
    }

    void receiveEvent(EventReport ev) {
        if (DBG.booleanValue()) {
            Log.d(TAG, "Message Type = " + ev.getType() + ", Message handle = " + ev.getHandle());
        }
        sendMessage(MSG_NOTIFICATION, ev);
    }
}
