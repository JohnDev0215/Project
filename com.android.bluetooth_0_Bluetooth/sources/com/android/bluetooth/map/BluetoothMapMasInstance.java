package com.android.bluetooth.map;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.net.INetd;
import android.os.Handler;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.util.Log;
import com.android.bluetooth.BluetoothObexTransport;
import com.android.bluetooth.IObexConnectionHandler;
import com.android.bluetooth.ObexServerSockets;
import com.android.bluetooth.map.BluetoothMapContentObserver;
import com.android.bluetooth.map.BluetoothMapUtils;
import com.android.bluetooth.sdp.SdpManager;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import javax.obex.Authenticator;
import javax.obex.ServerSession;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothMapMasInstance implements IObexConnectionHandler {
    private static final String BLUETOOTH_MAP_VERSION_PROPERTY = "persist.bluetooth.mapversion";
    private static final boolean D = false;
    static final int SDP_MAP_MAS_FEATURES_1_2 = 127;
    static final int SDP_MAP_MAS_FEATURES_1_3 = 394239;
    static final int SDP_MAP_MAS_FEATURES_1_4 = 394239;
    private static final int SDP_MAP_MAS_VERSION_1_2 = 258;
    private static final int SDP_MAP_MAS_VERSION_1_3 = 259;
    private static final int SDP_MAP_MAS_VERSION_1_4 = 260;
    private static final int SDP_MAP_MSG_TYPE_EMAIL = 1;
    private static final int SDP_MAP_MSG_TYPE_IM = 16;
    private static final int SDP_MAP_MSG_TYPE_MMS = 8;
    private static final int SDP_MAP_MSG_TYPE_SMS_CDMA = 4;
    private static final int SDP_MAP_MSG_TYPE_SMS_GSM = 2;
    public static final String TYPE_EMAIL_STR = "EMAIL";
    public static final String TYPE_IM_STR = "IM";
    public static final String TYPE_SMS_MMS_STR = "SMS/MMS";
    private static final boolean V = false;
    private volatile boolean mAcceptNewConnections;
    private BluetoothMapAccountItem mAccount;
    private BluetoothAdapter mAdapter;
    private String mBaseUri;
    private BluetoothSocket mConnSocket;
    private Map<String, BluetoothMapConvoContactElement> mContactList;
    private Context mContext;
    private AtomicLong mDbIndetifier;
    private boolean mEnableSmsMms;
    private AtomicLong mFolderVersionCounter;
    private HashMap<Long, BluetoothMapConvoListingElement> mImEmailConvoList;
    private AtomicLong mImEmailConvoListVersionCounter;
    private volatile boolean mInterrupted;
    private BluetoothMapObexServer mMapServer;
    private BluetoothMapService mMapService;
    private int mMasInstanceId;
    private BluetoothMnsObexClient mMnsClient;
    private Map<Long, BluetoothMapContentObserver.Msg> mMsgListMms;
    private Map<Long, BluetoothMapContentObserver.Msg> mMsgListMsg;
    private Map<Long, BluetoothMapContentObserver.Msg> mMsgListSms;
    BluetoothMapContentObserver mObserver;
    private BluetoothDevice mRemoteDevice;
    private int mRemoteFeatureMask;
    private int mSdpHandle;
    private ServerSession mServerSession;
    private ObexServerSockets mServerSockets;
    private Handler mServiceHandler;
    private volatile boolean mShutdown;
    private HashMap<Long, BluetoothMapConvoListingElement> mSmsMmsConvoList;
    private AtomicLong mSmsMmsConvoListVersionCounter;
    private final String mTag;
    private static volatile int sInstanceCounter = 0;
    private static int sFeatureMask = 127;

    public BluetoothMapMasInstance(BluetoothMapService mapService, Context context, BluetoothMapAccountItem account, int masId, boolean enableSmsMms) {
        this.mServerSession = null;
        this.mServerSockets = null;
        this.mSdpHandle = -1;
        this.mConnSocket = null;
        this.mRemoteDevice = null;
        this.mShutdown = false;
        this.mAcceptNewConnections = false;
        this.mServiceHandler = null;
        this.mMapService = null;
        this.mContext = null;
        this.mMnsClient = null;
        this.mAccount = null;
        this.mBaseUri = null;
        this.mMasInstanceId = -1;
        this.mEnableSmsMms = false;
        this.mDbIndetifier = new AtomicLong();
        this.mFolderVersionCounter = new AtomicLong(0L);
        this.mSmsMmsConvoListVersionCounter = new AtomicLong(0L);
        this.mImEmailConvoListVersionCounter = new AtomicLong(0L);
        this.mMsgListSms = null;
        this.mMsgListMms = null;
        this.mMsgListMsg = null;
        this.mSmsMmsConvoList = new HashMap<>();
        this.mImEmailConvoList = new HashMap<>();
        this.mRemoteFeatureMask = 31;
        StringBuilder sb = new StringBuilder();
        sb.append("BluetoothMapMasInstance");
        int i = sInstanceCounter;
        sInstanceCounter = i + 1;
        sb.append(i);
        this.mTag = sb.toString();
        this.mMapService = mapService;
        this.mServiceHandler = mapService.getHandler();
        this.mContext = context;
        this.mAccount = account;
        if (account != null) {
            this.mBaseUri = account.mBase_uri;
        }
        this.mMasInstanceId = masId;
        this.mEnableSmsMms = enableSmsMms;
        init();
    }

    private void removeSdpRecord() {
        if (this.mAdapter != null && this.mSdpHandle >= 0 && SdpManager.getDefaultManager() != null) {
            boolean status = SdpManager.getDefaultManager().removeSdpRecord(this.mSdpHandle);
            Log.d(this.mTag, "RemoveSDPrecord returns " + status);
            this.mSdpHandle = -1;
        }
    }

    protected BluetoothMapMasInstance() {
        this.mServerSession = null;
        this.mServerSockets = null;
        this.mSdpHandle = -1;
        this.mConnSocket = null;
        this.mRemoteDevice = null;
        this.mShutdown = false;
        this.mAcceptNewConnections = false;
        this.mServiceHandler = null;
        this.mMapService = null;
        this.mContext = null;
        this.mMnsClient = null;
        this.mAccount = null;
        this.mBaseUri = null;
        this.mMasInstanceId = -1;
        this.mEnableSmsMms = false;
        this.mDbIndetifier = new AtomicLong();
        this.mFolderVersionCounter = new AtomicLong(0L);
        this.mSmsMmsConvoListVersionCounter = new AtomicLong(0L);
        this.mImEmailConvoListVersionCounter = new AtomicLong(0L);
        this.mMsgListSms = null;
        this.mMsgListMms = null;
        this.mMsgListMsg = null;
        this.mSmsMmsConvoList = new HashMap<>();
        this.mImEmailConvoList = new HashMap<>();
        this.mRemoteFeatureMask = 31;
        StringBuilder sb = new StringBuilder();
        sb.append("BluetoothMapMasInstance");
        int i = sInstanceCounter;
        sInstanceCounter = i + 1;
        sb.append(i);
        this.mTag = sb.toString();
    }

    public String toString() {
        return "MasId: " + this.mMasInstanceId + " Uri:" + this.mBaseUri + " SMS/MMS:" + this.mEnableSmsMms;
    }

    private void init() {
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    private void updateDbIdentifier() {
        this.mDbIndetifier.set(Calendar.getInstance().getTime().getTime());
    }

    void updateFolderVersionCounter() {
        this.mFolderVersionCounter.incrementAndGet();
    }

    void updateSmsMmsConvoListVersionCounter() {
        this.mSmsMmsConvoListVersionCounter.incrementAndGet();
    }

    void updateImEmailConvoListVersionCounter() {
        this.mImEmailConvoListVersionCounter.incrementAndGet();
    }

    Map<Long, BluetoothMapContentObserver.Msg> getMsgListSms() {
        return this.mMsgListSms;
    }

    void setMsgListSms(Map<Long, BluetoothMapContentObserver.Msg> msgListSms) {
        this.mMsgListSms = msgListSms;
    }

    Map<Long, BluetoothMapContentObserver.Msg> getMsgListMms() {
        return this.mMsgListMms;
    }

    void setMsgListMms(Map<Long, BluetoothMapContentObserver.Msg> msgListMms) {
        this.mMsgListMms = msgListMms;
    }

    Map<Long, BluetoothMapContentObserver.Msg> getMsgListMsg() {
        return this.mMsgListMsg;
    }

    void setMsgListMsg(Map<Long, BluetoothMapContentObserver.Msg> msgListMsg) {
        this.mMsgListMsg = msgListMsg;
    }

    Map<String, BluetoothMapConvoContactElement> getContactList() {
        return this.mContactList;
    }

    void setContactList(Map<String, BluetoothMapConvoContactElement> contactList) {
        this.mContactList = contactList;
    }

    HashMap<Long, BluetoothMapConvoListingElement> getSmsMmsConvoList() {
        return this.mSmsMmsConvoList;
    }

    void setSmsMmsConvoList(HashMap<Long, BluetoothMapConvoListingElement> smsMmsConvoList) {
        this.mSmsMmsConvoList = smsMmsConvoList;
    }

    HashMap<Long, BluetoothMapConvoListingElement> getImEmailConvoList() {
        return this.mImEmailConvoList;
    }

    void setImEmailConvoList(HashMap<Long, BluetoothMapConvoListingElement> imEmailConvoList) {
        this.mImEmailConvoList = imEmailConvoList;
    }

    int getMasId() {
        return this.mMasInstanceId;
    }

    long getDbIdentifier() {
        return this.mDbIndetifier.get();
    }

    long getFolderVersionCounter() {
        return this.mFolderVersionCounter.get();
    }

    long getCombinedConvoListVersionCounter() {
        long combinedVersionCounter = this.mSmsMmsConvoListVersionCounter.get();
        return combinedVersionCounter + this.mImEmailConvoListVersionCounter.get();
    }

    public synchronized void startSocketListeners() {
        if (this.mServerSession != null) {
            this.mServerSession.close();
            this.mServerSession = null;
        }
        if (this.mObserver != null) {
            this.mObserver.deinit();
            this.mObserver = null;
        }
        closeConnectionSocket();
        if (this.mServerSockets != null) {
            this.mAcceptNewConnections = true;
        } else {
            this.mServerSockets = ObexServerSockets.create(this);
            this.mAcceptNewConnections = true;
            if (this.mServerSockets == null) {
                Log.e(this.mTag, "Failed to start the listeners");
            } else {
                removeSdpRecord();
                this.mSdpHandle = createMasSdpRecord(this.mServerSockets.getRfcommChannel(), this.mServerSockets.getL2capPsm());
                updateDbIdentifier();
            }
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private int createMasSdpRecord(int rfcommChannel, int l2capPsm) {
        int masVersion;
        String masName = INetd.NEXTHOP_NONE;
        int messageTypeFlags = 0;
        if (this.mEnableSmsMms) {
            masName = TYPE_SMS_MMS_STR;
            messageTypeFlags = 0 | 14;
        }
        if (this.mBaseUri != null) {
            if (this.mEnableSmsMms) {
                if (this.mAccount.getType() == BluetoothMapUtils.TYPE.EMAIL) {
                    masName = masName + "/EMAIL";
                } else if (this.mAccount.getType() == BluetoothMapUtils.TYPE.IM) {
                    masName = masName + "/IM";
                }
            } else {
                masName = this.mAccount.getName();
            }
            if (this.mAccount.getType() == BluetoothMapUtils.TYPE.EMAIL) {
                messageTypeFlags |= 1;
            } else if (this.mAccount.getType() == BluetoothMapUtils.TYPE.IM) {
                messageTypeFlags |= 16;
            }
        }
        String currentValue = SystemProperties.get(BLUETOOTH_MAP_VERSION_PROPERTY, "map12");
        byte b = -1;
        switch (currentValue.hashCode()) {
            case 103662717:
                if (currentValue.equals("map12")) {
                    b = 0;
                }
                break;
            case 103662718:
                if (currentValue.equals("map13")) {
                    b = 1;
                }
                break;
            case 103662719:
                if (currentValue.equals("map14")) {
                    b = 2;
                }
                break;
        }
        if (b == 0) {
            sFeatureMask = 127;
            masVersion = 258;
        } else if (b == 1) {
            sFeatureMask = 394239;
            masVersion = SDP_MAP_MAS_VERSION_1_3;
        } else if (b == 2) {
            sFeatureMask = 394239;
            masVersion = SDP_MAP_MAS_VERSION_1_4;
        } else {
            sFeatureMask = 127;
            masVersion = 258;
        }
        return SdpManager.getDefaultManager().createMapMasRecord(masName, this.mMasInstanceId, rfcommChannel, l2capPsm, masVersion, messageTypeFlags, sFeatureMask);
    }

    public boolean startObexServerSession(BluetoothMnsObexClient mnsClient) throws IOException, RemoteException {
        if (this.mConnSocket != null) {
            if (this.mServerSession != null) {
                return true;
            }
            this.mMnsClient = mnsClient;
            BluetoothMapContentObserver bluetoothMapContentObserver = new BluetoothMapContentObserver(this.mContext, this.mMnsClient, this, this.mAccount, this.mEnableSmsMms);
            this.mObserver = bluetoothMapContentObserver;
            bluetoothMapContentObserver.init();
            BluetoothMapObexServer bluetoothMapObexServer = new BluetoothMapObexServer(this.mServiceHandler, this.mContext, this.mObserver, this, this.mAccount, this.mEnableSmsMms);
            this.mMapServer = bluetoothMapObexServer;
            bluetoothMapObexServer.setRemoteFeatureMask(this.mRemoteFeatureMask);
            BluetoothObexTransport transport = new BluetoothObexTransport(this.mConnSocket);
            this.mServerSession = new ServerSession(transport, this.mMapServer, (Authenticator) null);
            return true;
        }
        return false;
    }

    public boolean handleSmsSendIntent(Context context, Intent intent) {
        BluetoothMapContentObserver bluetoothMapContentObserver = this.mObserver;
        if (bluetoothMapContentObserver != null) {
            return bluetoothMapContentObserver.handleSmsSendIntent(context, intent);
        }
        return false;
    }

    public boolean isStarted() {
        return this.mConnSocket != null;
    }

    public void shutdown() {
        ServerSession serverSession = this.mServerSession;
        if (serverSession != null) {
            serverSession.close();
            this.mServerSession = null;
        }
        BluetoothMapContentObserver bluetoothMapContentObserver = this.mObserver;
        if (bluetoothMapContentObserver != null) {
            bluetoothMapContentObserver.deinit();
            this.mObserver = null;
        }
        removeSdpRecord();
        closeConnectionSocket();
        closeServerSockets(false);
    }

    public void restartObexServerSession() {
        startSocketListeners();
    }

    private synchronized void closeServerSockets(boolean block) {
        ObexServerSockets sockets = this.mServerSockets;
        if (sockets != null) {
            sockets.shutdown(block);
            this.mServerSockets = null;
        }
    }

    private synchronized void closeConnectionSocket() {
        if (this.mConnSocket != null) {
            try {
                try {
                    this.mConnSocket.close();
                } finally {
                    this.mConnSocket = null;
                }
            } catch (IOException e) {
                Log.e(this.mTag, "Close Connection Socket error: ", e);
                this.mConnSocket = null;
            }
        }
    }

    public void setRemoteFeatureMask(int supportedFeatures) {
        int i = sFeatureMask & supportedFeatures;
        this.mRemoteFeatureMask = i;
        BluetoothMapUtils.savePeerSupportUtcTimeStamp(i);
        BluetoothMapContentObserver bluetoothMapContentObserver = this.mObserver;
        if (bluetoothMapContentObserver != null) {
            bluetoothMapContentObserver.setObserverRemoteFeatureMask(this.mRemoteFeatureMask);
            this.mMapServer.setRemoteFeatureMask(this.mRemoteFeatureMask);
        }
    }

    public int getRemoteFeatureMask() {
        return this.mRemoteFeatureMask;
    }

    public static int getFeatureMask() {
        return sFeatureMask;
    }

    @Override // com.android.bluetooth.IObexConnectionHandler
    public synchronized boolean onConnect(BluetoothDevice device, BluetoothSocket socket) {
        if (!this.mAcceptNewConnections) {
            return false;
        }
        boolean isValid = this.mMapService.onConnect(device, this);
        if (isValid) {
            this.mRemoteDevice = device;
            this.mConnSocket = socket;
            this.mAcceptNewConnections = false;
        }
        return isValid;
    }

    @Override // com.android.bluetooth.IObexConnectionHandler
    public synchronized void onAcceptFailed() {
        this.mServerSockets = null;
        if (this.mShutdown) {
            Log.e(this.mTag, "Failed to accept incomming connection - shutdown");
        } else {
            Log.e(this.mTag, "Failed to accept incomming connection - restarting");
            startSocketListeners();
        }
    }
}
