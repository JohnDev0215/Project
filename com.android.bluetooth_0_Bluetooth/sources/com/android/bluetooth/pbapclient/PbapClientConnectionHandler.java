package com.android.bluetooth.pbapclient;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.bluetooth.BluetoothUuid;
import android.bluetooth.SdpPseRecord;
import android.content.Context;
import android.content.Intent;
import android.net.INetd;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.CallLog;
import android.util.Log;
import com.android.bluetooth.BluetoothObexTransport;
import com.android.bluetooth.R;
import com.android.bluetooth.btservice.ProfileService;
import com.android.vcard.VCardEntry;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.obex.ClientSession;
import javax.obex.HeaderSet;

/* JADX INFO: loaded from: classes.dex */
class PbapClientConnectionHandler extends Handler {
    static final boolean DBG = false;
    private static final int DEFAULT_BATCH_SIZE = 250;
    private static final int FAILURE = 3;
    public static final String FAV_PATH = "telecom/fav.vcf";
    public static final String ICH_PATH = "telecom/ich.vcf";
    private static final int L2CAP_INVALID_PSM = -1;
    public static final String MCH_PATH = "telecom/mch.vcf";
    static final int MSG_CONNECT = 1;
    static final int MSG_DISCONNECT = 2;
    static final int MSG_DOWNLOAD = 3;
    static final int MSG_ONCE_MORE_DOWNLOAD = 4;
    public static final String OCH_PATH = "telecom/och.vcf";
    private static final int PBAP_FEATURE_BROWSING = 2;
    private static final int PBAP_FEATURE_DEFAULT_IMAGE_FORMAT = 512;
    private static final int PBAP_FEATURE_DOWNLOADING = 1;
    private static final long PBAP_FILTER_ADR = 32;
    private static final long PBAP_FILTER_EMAIL = 256;
    private static final long PBAP_FILTER_FN = 2;
    private static final long PBAP_FILTER_N = 4;
    private static final long PBAP_FILTER_NICKNAME = 8388608;
    private static final long PBAP_FILTER_PHOTO = 8;
    private static final long PBAP_FILTER_TEL = 128;
    private static final long PBAP_FILTER_VERSION = 1;
    private static final long PBAP_REQUESTED_FIELDS = 8388736;
    private static final int PBAP_SUPPORTED_FEATURE = 513;
    private static final byte[] PBAP_TARGET = {121, 97, 53, -16, -16, -59, 17, -40, 9, 102, 8, 0, 32, Ascii.FF, -102, 102};
    public static final int PBAP_V1_2 = 258;
    public static final String PB_PATH = "telecom/pb.vcf";
    private static final int SCENE_ALL = 0;
    private static final int SCENE_CALLLOG = 2;
    private static final int SCENE_PHONEBOOK = 1;
    public static final String SIM_ICH_PATH = "SIM1/telecom/ich.vcf";
    public static final String SIM_MCH_PATH = "SIM1/telecom/mch.vcf";
    public static final String SIM_OCH_PATH = "SIM1/telecom/och.vcf";
    public static final String SIM_PB_PATH = "SIM1/telecom/pb.vcf";
    private static final int START = 1;
    private static final int SUCCESS = 2;
    private static final int SUPPORTED_REPOSITORIES_FAVORITES = 8;
    private static final int SUPPORTED_REPOSITORIES_LOCALPHONEBOOK = 1;
    private static final int SUPPORTED_REPOSITORIES_SIMCARD = 2;
    static final String TAG = "PbapClientConnHandler";
    private static final int UPPER_LIMIT = 65535;
    public static final byte VCARD_TYPE_21 = 0;
    public static final byte VCARD_TYPE_30 = 1;
    static final boolean VDBG = false;
    private boolean isFavPathResult;
    private boolean isIchPbPathResult;
    private boolean isMchPathResult;
    private boolean isOchPbPathResult;
    private boolean isPbPathResult;
    private boolean isSimPbPathResult;
    private Account mAccount;
    private boolean mAccountCreated;
    private AccountManager mAccountManager;
    private final BluetoothAdapter mAdapter;
    private BluetoothPbapObexAuthenticator mAuth;
    private Context mContext;
    private final BluetoothDevice mDevice;
    private ClientSession mObexSession;
    private PbapClientService mPbapClientService;
    private final PbapClientStateMachine mPbapClientStateMachine;
    private SdpPseRecord mPseRec;
    private BluetoothSocket mSocket;

    PbapClientConnectionHandler(Looper looper, Context context, PbapClientStateMachine stateMachine, BluetoothDevice device) {
        super(looper);
        this.isPbPathResult = true;
        this.isFavPathResult = true;
        this.isSimPbPathResult = true;
        this.isMchPathResult = true;
        this.isIchPbPathResult = true;
        this.isOchPbPathResult = true;
        this.mPseRec = null;
        this.mAuth = null;
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mDevice = device;
        this.mContext = context;
        this.mPbapClientStateMachine = stateMachine;
        this.mAuth = new BluetoothPbapObexAuthenticator(this);
        this.mAccountManager = AccountManager.get(this.mPbapClientStateMachine.getContext());
        this.mAccount = new Account(this.mDevice.getAddress(), this.mContext.getString(R.string.pbap_account_type));
        this.mPbapClientService = PbapClientService.getPbapClientService();
    }

    PbapClientConnectionHandler(Builder pceHandlerbuild) {
        super(pceHandlerbuild.mLooper);
        this.isPbPathResult = true;
        this.isFavPathResult = true;
        this.isSimPbPathResult = true;
        this.isMchPathResult = true;
        this.isIchPbPathResult = true;
        this.isOchPbPathResult = true;
        this.mPseRec = null;
        this.mAuth = null;
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mDevice = pceHandlerbuild.mDevice;
        this.mContext = pceHandlerbuild.mContext;
        this.mPbapClientStateMachine = pceHandlerbuild.mClientStateMachine;
        this.mAuth = new BluetoothPbapObexAuthenticator(this);
        this.mAccountManager = AccountManager.get(this.mPbapClientStateMachine.getContext());
        this.mAccount = new Account(this.mDevice.getAddress(), this.mContext.getString(R.string.pbap_account_type));
        this.mPbapClientService = PbapClientService.getPbapClientService();
    }

    public static class Builder {
        private PbapClientStateMachine mClientStateMachine;
        private Context mContext;
        private BluetoothDevice mDevice;
        private Looper mLooper;

        public Builder setLooper(Looper loop) {
            this.mLooper = loop;
            return this;
        }

        public Builder setClientSM(PbapClientStateMachine clientStateMachine) {
            this.mClientStateMachine = clientStateMachine;
            return this;
        }

        public Builder setRemoteDevice(BluetoothDevice device) {
            this.mDevice = device;
            return this;
        }

        public Builder setContext(Context context) {
            this.mContext = context;
            return this;
        }

        public PbapClientConnectionHandler build() {
            PbapClientConnectionHandler pbapClientHandler = new PbapClientConnectionHandler(this);
            return pbapClientHandler;
        }
    }

    @Override // android.os.Handler
    public void handleMessage(Message msg) {
        String callLogActionMore;
        String bookActionMore;
        int i = msg.what;
        if (i == 1) {
            this.mPseRec = (SdpPseRecord) msg.obj;
            if (!connectSocket()) {
                Log.w(TAG, "Socket CONNECT Failure ");
                this.mPbapClientStateMachine.sendMessage(6);
                return;
            } else if (connectObexSession()) {
                this.mPbapClientStateMachine.sendMessage(5);
                return;
            } else {
                this.mPbapClientStateMachine.sendMessage(6);
                return;
            }
        }
        if (i == 2) {
            try {
                if (this.mObexSession != null) {
                    this.mObexSession.disconnect((HeaderSet) null);
                    this.mObexSession.close();
                }
                closeSocket();
            } catch (IOException e) {
                Log.w(TAG, "DISCONNECT Failure ", e);
            }
            removeAccount(this.mAccount);
            removeCallLog(this.mAccount);
            this.mPbapClientStateMachine.sendMessage(7);
            return;
        }
        if (i == 3) {
            int arg1 = msg.arg1;
            Log.d(TAG, "MSG_DOWNLOAD  scene: " + arg1);
            boolean zAddAccount = addAccount(this.mAccount);
            this.mAccountCreated = zAddAccount;
            if (!zAddAccount) {
                Log.e(TAG, "Account creation failed.");
                return;
            }
            if (arg1 == 0) {
                downloadContactsTotalSize();
                PbapClientService pbapClientService = this.mPbapClientService;
                if (pbapClientService != null) {
                    pbapClientService.notifyCallLogState(INetd.NEXTHOP_NONE, 1, this.mDevice.getAddress());
                }
                HashMap<String, Integer> callCounter = new HashMap<>();
                downloadCallLog(MCH_PATH, callCounter);
                downloadCallLog(ICH_PATH, callCounter);
                downloadCallLog(OCH_PATH, callCounter);
                PbapClientService pbapClientService2 = this.mPbapClientService;
                if (pbapClientService2 != null) {
                    pbapClientService2.notifyCallLogState(INetd.NEXTHOP_NONE, 2, this.mDevice.getAddress());
                    return;
                }
                return;
            }
            if (arg1 == 1) {
                downloadContactsTotalSize();
                return;
            }
            if (arg1 == 2) {
                PbapClientService pbapClientService3 = this.mPbapClientService;
                if (pbapClientService3 != null) {
                    pbapClientService3.notifyCallLogState(INetd.NEXTHOP_NONE, 1, this.mDevice.getAddress());
                }
                HashMap<String, Integer> callCounter2 = new HashMap<>();
                downloadCallLog(MCH_PATH, callCounter2);
                downloadCallLog(ICH_PATH, callCounter2);
                downloadCallLog(OCH_PATH, callCounter2);
                PbapClientService pbapClientService4 = this.mPbapClientService;
                if (pbapClientService4 != null) {
                    pbapClientService4.notifyCallLogState(INetd.NEXTHOP_NONE, 2, this.mDevice.getAddress());
                    return;
                }
                return;
            }
            return;
        }
        if (i == 4) {
            if (!this.mAccountCreated) {
                removeAccount(this.mAccount);
                boolean zAddAccount2 = addAccount(this.mAccount);
                this.mAccountCreated = zAddAccount2;
                if (!zAddAccount2) {
                    Log.e(TAG, "Account creation failed.");
                    return;
                }
            }
            int arg12 = msg.arg1;
            removeCallLog(this.mAccount);
            if (arg12 == 1) {
                Intent intentBookMoreStart = new Intent("android.bluetooth.pbapclient.profile.action.PULLPHONEBOOK_START");
                intentBookMoreStart.putExtra("android.bluetooth.device.extra.DEVICE", this.mDevice);
                this.mContext.sendBroadcast(intentBookMoreStart, ProfileService.BLUETOOTH_PERM);
                if (isRepositorySupported(1)) {
                    this.isPbPathResult = true;
                    downloadContacts(PB_PATH);
                }
                if (isRepositorySupported(8)) {
                    this.isFavPathResult = true;
                    downloadContacts(FAV_PATH);
                }
                if (isRepositorySupported(2)) {
                    this.isSimPbPathResult = true;
                    downloadContacts(SIM_PB_PATH);
                }
                if (this.isFavPathResult || this.isPbPathResult || this.isSimPbPathResult) {
                    bookActionMore = "android.bluetooth.pbapclient.profile.action.PULLPHONEBOOK_SUCCESS";
                } else {
                    bookActionMore = "android.bluetooth.pbapclient.profile.action.PULLPHONEBOOK_FAIL";
                }
                Intent intentBookMore = new Intent(bookActionMore);
                intentBookMore.putExtra("android.bluetooth.device.extra.DEVICE", this.mDevice);
                this.mContext.sendBroadcast(intentBookMore, ProfileService.BLUETOOTH_PERM);
                return;
            }
            if (arg12 == 2) {
                Intent intentCallLogMoreStart = new Intent("android.bluetooth.pbapclient.profile.action.PULLPHONECALLLOG_START");
                intentCallLogMoreStart.putExtra("android.bluetooth.device.extra.DEVICE", this.mDevice);
                this.mContext.sendBroadcast(intentCallLogMoreStart, ProfileService.BLUETOOTH_PERM);
                HashMap<String, Integer> callMore = new HashMap<>();
                this.isIchPbPathResult = true;
                this.isMchPathResult = true;
                this.isOchPbPathResult = true;
                downloadCallLog(MCH_PATH, callMore);
                downloadCallLog(ICH_PATH, callMore);
                downloadCallLog(OCH_PATH, callMore);
                if (this.isMchPathResult || this.isIchPbPathResult || this.isOchPbPathResult) {
                    callLogActionMore = "android.bluetooth.pbapclient.profile.action.PULLPHONECALLLOG_SUCCESS";
                } else {
                    callLogActionMore = "android.bluetooth.pbapclient.profile.action.PULLPHONECALLLOG_FAIL";
                }
                Intent intentCallLogMore = new Intent(callLogActionMore);
                intentCallLogMore.putExtra("android.bluetooth.device.extra.DEVICE", this.mDevice);
                this.mContext.sendBroadcast(intentCallLogMore, ProfileService.BLUETOOTH_PERM);
                return;
            }
            return;
        }
        Log.w(TAG, "Received Unexpected Message");
    }

    private synchronized boolean connectSocket() {
        try {
            if (this.mPseRec == null) {
                this.mSocket = this.mDevice.createRfcommSocketToServiceRecord(BluetoothUuid.PBAP_PSE.getUuid());
            } else if (this.mPseRec.getL2capPsm() != -1) {
                this.mSocket = this.mDevice.createL2capSocket(this.mPseRec.getL2capPsm());
            } else {
                this.mSocket = this.mDevice.createRfcommSocket(this.mPseRec.getRfcommChannelNumber());
            }
        } catch (IOException e) {
            Log.e(TAG, "Error while connecting socket", e);
        }
        if (this.mSocket != null) {
            this.mSocket.connect();
            return true;
        }
        Log.w(TAG, "Could not create socket");
        return false;
    }

    private boolean connectObexSession() {
        try {
            BluetoothObexTransport transport = new BluetoothObexTransport(this.mSocket);
            ClientSession clientSession = new ClientSession(transport);
            this.mObexSession = clientSession;
            clientSession.setAuthenticator(this.mAuth);
            HeaderSet connectionRequest = new HeaderSet();
            connectionRequest.setHeader(70, PBAP_TARGET);
            if (this.mPseRec != null) {
                ObexAppParameters oap = new ObexAppParameters();
                if (this.mPseRec.getProfileVersion() >= 258) {
                    oap.add(Ascii.DLE, 513);
                }
                oap.addToHeaderSet(connectionRequest);
            }
            HeaderSet connectionResponse = this.mObexSession.connect(connectionRequest);
            boolean connectionSuccessful = connectionResponse.getResponseCode() == 160;
            return connectionSuccessful;
        } catch (IOException | NullPointerException e) {
            Log.w(TAG, "CONNECT Failure " + e.toString());
            closeSocket();
            return false;
        }
    }

    public void abort() {
        closeSocket();
        getLooper().getThread().interrupt();
    }

    private synchronized void closeSocket() {
        try {
            if (this.mSocket != null) {
                this.mSocket.close();
                this.mSocket = null;
            }
        } catch (IOException e) {
            Log.e(TAG, "Error when closing socket", e);
            this.mSocket = null;
        }
    }

    void downloadContactsTotalSize() {
        if (this.mPbapClientService == null) {
            this.mPbapClientService = PbapClientService.getPbapClientService();
        }
        PbapClientService pbapClientService = this.mPbapClientService;
        if (pbapClientService != null) {
            pbapClientService.notifyPhoneBookState(INetd.NEXTHOP_NONE, 1, this.mDevice.getAddress());
        }
        Log.d(TAG, "downloadContactsTotalSize start downloadPB enter");
        int total = 0;
        List<String> pathList = new ArrayList<>();
        if (isRepositorySupported(1)) {
            Log.d(TAG, "downloadContactsTotalSize addPath: telecom/pb.vcf");
            pathList.add(PB_PATH);
        }
        if (isRepositorySupported(8)) {
            Log.d(TAG, "downloadContactsTotalSize addPath: telecom/fav.vcf");
            pathList.add(FAV_PATH);
        }
        if (isRepositorySupported(2)) {
            Log.d(TAG, "downloadContactsTotalSize addPath: SIM1/telecom/pb.vcf");
            pathList.add(SIM_PB_PATH);
        }
        Log.d(TAG, "downloadContactsTotalSize start downloadPB pathList: " + pathList.size());
        for (int i = 0; i < pathList.size(); i++) {
            try {
                String path = pathList.get(i);
                BluetoothPbapRequestPullPhoneBookSize requestPbSize = new BluetoothPbapRequestPullPhoneBookSize(path, PBAP_REQUESTED_FIELDS);
                requestPbSize.execute(this.mObexSession);
                int pbSize = requestPbSize.getSize();
                Log.d(TAG, "downloadContactsTotalSize path: " + path + " size:" + pbSize);
                total += pbSize;
            } catch (IOException exception) {
                Log.e(TAG, "Download contactsTotalSize failure" + exception.toString());
            }
        }
        Log.d(TAG, "downloadContactsTotalSize total: " + total);
        PbapClientService pbapClientService2 = this.mPbapClientService;
        if (pbapClientService2 != null) {
            pbapClientService2.notifyPhoneBookSize(INetd.NEXTHOP_NONE, total, this.mDevice.getAddress());
        }
        downloadContactsExecute(pathList);
        PbapClientService pbapClientService3 = this.mPbapClientService;
        if (pbapClientService3 != null) {
            pbapClientService3.notifyPhoneBookState(INetd.NEXTHOP_NONE, 2, this.mDevice.getAddress());
        }
    }

    void downloadContactsExecute(List<String> pathList) {
        Log.d(TAG, "downloadContactsExecute start downloadPB: " + pathList.size());
        for (int i = 0; i < pathList.size(); i++) {
            String path = pathList.get(i);
            Log.d(TAG, "downloadContacts path: " + path);
            downloadContacts(path);
        }
    }

    void downloadContacts(String path) {
        int numberOfContactsRemaining;
        int startOffset;
        if (this.mPbapClientService == null) {
            this.mPbapClientService = PbapClientService.getPbapClientService();
        }
        try {
            PhonebookPullRequest processor = new PhonebookPullRequest(this.mPbapClientStateMachine.getContext(), this.mAccount);
            BluetoothPbapRequestPullPhoneBookSize requestPbSize = new BluetoothPbapRequestPullPhoneBookSize(path, PBAP_REQUESTED_FIELDS);
            requestPbSize.execute(this.mObexSession);
            int numberOfContactsRemaining2 = requestPbSize.getSize();
            if (!PB_PATH.equals(path)) {
                numberOfContactsRemaining = numberOfContactsRemaining2;
                startOffset = 0;
            } else {
                numberOfContactsRemaining = numberOfContactsRemaining2 - 1;
                startOffset = 1;
            }
            while (numberOfContactsRemaining > 0 && startOffset <= 65535) {
                int numberOfContactsToDownload = Math.min(Math.min(DEFAULT_BATCH_SIZE, numberOfContactsRemaining), (65535 - startOffset) + 1);
                BluetoothPbapRequestPullPhoneBook request = new BluetoothPbapRequestPullPhoneBook(path, this.mAccount, PBAP_REQUESTED_FIELDS, (byte) 1, numberOfContactsToDownload, startOffset);
                request.execute(this.mObexSession);
                ArrayList<VCardEntry> vcards = request.getList();
                if (vcards != null) {
                    if (this.mPbapClientService != null) {
                        Log.w(TAG, " pbapClientService ready  callback : " + vcards.size());
                        this.mPbapClientService.notifyPhoneBookContent(path, vcards, this.mDevice.getAddress());
                    }
                } else {
                    Log.e(TAG, "Phonebook Download Completed is Null");
                }
                if (path == FAV_PATH) {
                    for (VCardEntry v : vcards) {
                        v.setStarred(true);
                    }
                }
                processor.setResults(vcards);
                processor.onPullComplete();
                startOffset += numberOfContactsToDownload;
                numberOfContactsRemaining -= numberOfContactsToDownload;
            }
            if (startOffset > 65535 && numberOfContactsRemaining > 0) {
                Log.w(TAG, "Download contacts incomplete, index exceeded upper limit.");
            }
        } catch (IOException e) {
            Log.w(TAG, "Download contacts failure" + e.toString());
            PbapClientService pbapClientService = this.mPbapClientService;
            if (pbapClientService != null) {
                pbapClientService.notifyPhoneBookState(path, 3, this.mDevice.getAddress());
            }
        }
    }

    void downloadCallLog(String path, HashMap<String, Integer> callCounter) {
        try {
            BluetoothPbapRequestPullPhoneBook request = new BluetoothPbapRequestPullPhoneBook(path, this.mAccount, 0L, (byte) 1, 0, 0);
            request.execute(this.mObexSession);
            ArrayList<VCardEntry> cardEntries = request.getList();
            CallLogPullRequest processor = new CallLogPullRequest(this.mPbapClientStateMachine.getContext(), path, callCounter, this.mAccount);
            processor.setResults(cardEntries);
            processor.onPullComplete();
            if (cardEntries == null) {
                Log.e(TAG, "callLog Download Completed is Null");
            } else if (this.mPbapClientService != null) {
                Log.w(TAG, " downloadCallLog ready  callback : " + cardEntries.size());
                this.mPbapClientService.notifyCallLogContent(path, cardEntries, this.mDevice.getAddress());
            }
        } catch (IOException e) {
            Log.w(TAG, "Download call log failure");
            PbapClientService pbapClientService = this.mPbapClientService;
            if (pbapClientService != null) {
                pbapClientService.notifyCallLogState(path, 3, this.mDevice.getAddress());
            }
        }
    }

    private boolean addAccount(Account account) {
        if (this.mAccountManager.addAccountExplicitly(account, null, null)) {
            return true;
        }
        return false;
    }

    private void removeAccount(Account account) {
        if (!this.mAccountManager.removeAccountExplicitly(account)) {
            Log.e(TAG, "Failed to remove account " + this.mAccount);
        }
    }

    private void removeCallLog(Account account) {
        try {
            if (this.mContext.getContentResolver() == null) {
                return;
            }
            this.mContext.getContentResolver().delete(CallLog.Calls.CONTENT_URI, "subscription_id=?", new String[]{this.mAccount.name});
        } catch (IllegalArgumentException e) {
            Log.d(TAG, "Call Logs could not be deleted, they may not exist yet.");
        }
    }

    private boolean isRepositorySupported(int mask) {
        SdpPseRecord sdpPseRecord = this.mPseRec;
        return (sdpPseRecord == null || (sdpPseRecord.getSupportedRepositories() & mask) == 0) ? false : true;
    }
}
