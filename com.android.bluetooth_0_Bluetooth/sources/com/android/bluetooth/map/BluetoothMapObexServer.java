package com.android.bluetooth.map;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelUuid;
import android.os.RemoteException;
import android.os.UserManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.android.bluetooth.BluetoothStatsLog;
import com.android.bluetooth.map.BluetoothMapUtils;
import com.android.bluetooth.mapapi.BluetoothMapContract;
import com.android.bluetooth.opp.BluetoothShare;
import com.android.bluetooth.sap.SapService;
import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.Calendar;
import javax.obex.HeaderSet;
import javax.obex.Operation;
import javax.obex.ServerRequestHandler;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothMapObexServer extends ServerRequestHandler {
    private static final boolean D = false;
    private static final int MAS_INSTANCE_INFORMATION_LENGTH = 200;
    private static final long PROVIDER_ANR_TIMEOUT = 20000;
    private static final String TAG = "BluetoothMapObexServer";
    private static final int THREADED_MAIL_HEADER_ID = 250;
    private static final long THREAD_MAIL_KEY = 1397510985;
    private static final String TYPE_GET_CONVO_LISTING = "x-bt/MAP-convo-listing";
    private static final String TYPE_GET_FOLDER_LISTING = "x-obex/folder-listing";
    private static final String TYPE_GET_MAS_INSTANCE_INFORMATION = "x-bt/MASInstanceInformation";
    private static final String TYPE_GET_MESSAGE_LISTING = "x-bt/MAP-msg-listing";
    private static final String TYPE_MESSAGE = "x-bt/message";
    private static final String TYPE_MESSAGE_UPDATE = "x-bt/MAP-messageUpdate";
    private static final String TYPE_SET_MESSAGE_STATUS = "x-bt/messageStatus";
    private static final String TYPE_SET_NOTIFICATION_FILTER = "x-bt/MAP-notification-filter";
    private static final String TYPE_SET_NOTIFICATION_REGISTRATION = "x-bt/MAP-NotificationRegistration";
    private static final String TYPE_SET_OWNER_STATUS = "x-bt/participant";
    private static final int UUID_LENGTH = 16;
    private static final boolean V = false;
    private BluetoothMapAccountItem mAccount;
    private long mAccountId;
    private String mAuthority;
    private String mBaseUriString;
    private Handler mCallback;
    private Context mContext;
    private BluetoothMapFolderElement mCurrentFolder;
    private Uri mEmailFolderUri;
    private boolean mEnableSmsMms;
    private int mMasId;
    private BluetoothMapMasInstance mMasInstance;
    private BluetoothMapContentObserver mObserver;
    BluetoothMapContent mOutContent;
    private ContentProviderClient mProviderClient;
    private int mRemoteFeatureMask;
    private ContentResolver mResolver;
    private static final byte[] MAP_TARGET = {-69, 88, 43, SignedBytes.MAX_POWER_OF_TWO, 66, Ascii.FF, 17, -37, -80, -34, 8, 0, 32, Ascii.FF, -102, 102};
    public static final ParcelUuid MAP = ParcelUuid.fromString("00001134-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid MNS = ParcelUuid.fromString("00001133-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid MAS = ParcelUuid.fromString("00001132-0000-1000-8000-00805F9B34FB");
    private boolean mIsAborted = false;
    private boolean mThreadIdSupport = false;
    private String mMessageVersion = "1.0";

    public BluetoothMapObexServer(Handler callback, Context context, BluetoothMapContentObserver observer, BluetoothMapMasInstance mas, BluetoothMapAccountItem account, boolean enableSmsMms) throws RemoteException {
        this.mObserver = null;
        this.mCallback = null;
        this.mBaseUriString = null;
        this.mAccountId = 0L;
        this.mAccount = null;
        this.mEmailFolderUri = null;
        this.mMasId = 0;
        this.mRemoteFeatureMask = 31;
        this.mEnableSmsMms = false;
        this.mProviderClient = null;
        this.mCallback = callback;
        this.mContext = context;
        this.mObserver = observer;
        this.mEnableSmsMms = enableSmsMms;
        this.mAccount = account;
        this.mMasId = mas.getMasId();
        this.mMasInstance = mas;
        this.mRemoteFeatureMask = mas.getRemoteFeatureMask();
        if (account != null && account.getProviderAuthority() != null) {
            this.mAccountId = account.getAccountId();
            this.mAuthority = account.getProviderAuthority();
            this.mResolver = this.mContext.getContentResolver();
            this.mBaseUriString = account.mBase_uri + "/";
            if (account.getType() == BluetoothMapUtils.TYPE.EMAIL) {
                this.mEmailFolderUri = BluetoothMapContract.buildFolderUri(this.mAuthority, Long.toString(this.mAccountId));
            }
            this.mProviderClient = acquireUnstableContentProviderOrThrow();
        }
        buildFolderStructure();
        this.mObserver.setFolderStructure(this.mCurrentFolder.getRoot());
        this.mOutContent = new BluetoothMapContent(this.mContext, this.mAccount, this.mMasInstance);
    }

    private ContentProviderClient acquireUnstableContentProviderOrThrow() throws RemoteException {
        ContentProviderClient providerClient = this.mResolver.acquireUnstableContentProviderClient(this.mAuthority);
        if (providerClient == null) {
            throw new RemoteException("Failed to acquire provider for " + this.mAuthority);
        }
        providerClient.setDetectNotResponding(PROVIDER_ANR_TIMEOUT);
        return providerClient;
    }

    private void buildFolderStructure() throws RemoteException {
        BluetoothMapFolderElement bluetoothMapFolderElement = new BluetoothMapFolderElement("root", null);
        this.mCurrentFolder = bluetoothMapFolderElement;
        bluetoothMapFolderElement.setHasSmsMmsContent(this.mEnableSmsMms);
        boolean hasIM = false;
        boolean hasEmail = false;
        BluetoothMapAccountItem bluetoothMapAccountItem = this.mAccount;
        if (bluetoothMapAccountItem != null) {
            if (bluetoothMapAccountItem.getType() == BluetoothMapUtils.TYPE.IM) {
                hasIM = true;
            }
            if (this.mAccount.getType() == BluetoothMapUtils.TYPE.EMAIL) {
                hasEmail = true;
            }
        }
        this.mCurrentFolder.setHasImContent(hasIM);
        this.mCurrentFolder.setHasEmailContent(hasEmail);
        BluetoothMapFolderElement tmpFolder = this.mCurrentFolder.addFolder("telecom");
        tmpFolder.setHasSmsMmsContent(this.mEnableSmsMms);
        tmpFolder.setHasImContent(hasIM);
        tmpFolder.setHasEmailContent(hasEmail);
        BluetoothMapFolderElement tmpFolder2 = tmpFolder.addFolder(NotificationCompat.CATEGORY_MESSAGE);
        tmpFolder2.setHasSmsMmsContent(this.mEnableSmsMms);
        tmpFolder2.setHasImContent(hasIM);
        tmpFolder2.setHasEmailContent(hasEmail);
        addBaseFolders(tmpFolder2);
        if (this.mEnableSmsMms) {
            addSmsMmsFolders(tmpFolder2);
        }
        if (hasEmail) {
            addEmailFolders(tmpFolder2);
        }
        if (hasIM) {
            addImFolders(tmpFolder2);
        }
    }

    private void addBaseFolders(BluetoothMapFolderElement root) {
        root.addFolder(BluetoothMapContract.FOLDER_NAME_INBOX);
        root.addFolder(BluetoothMapContract.FOLDER_NAME_OUTBOX);
        root.addFolder("sent");
        root.addFolder(BluetoothMapContract.FOLDER_NAME_DELETED);
    }

    private void addSmsMmsFolders(BluetoothMapFolderElement root) {
        root.addSmsMmsFolder(BluetoothMapContract.FOLDER_NAME_INBOX);
        root.addSmsMmsFolder(BluetoothMapContract.FOLDER_NAME_OUTBOX);
        root.addSmsMmsFolder("sent");
        root.addSmsMmsFolder(BluetoothMapContract.FOLDER_NAME_DELETED);
        root.addSmsMmsFolder(BluetoothMapContract.FOLDER_NAME_DRAFT);
    }

    private void addImFolders(BluetoothMapFolderElement root) throws RemoteException {
        root.addImFolder(BluetoothMapContract.FOLDER_NAME_INBOX, 1L);
        root.addImFolder(BluetoothMapContract.FOLDER_NAME_OUTBOX, 4L);
        root.addImFolder("sent", 2L);
        root.addImFolder(BluetoothMapContract.FOLDER_NAME_DELETED, 5L);
        root.addImFolder(BluetoothMapContract.FOLDER_NAME_DRAFT, 3L);
    }

    private void addEmailFolders(BluetoothMapFolderElement parentFolder) throws RemoteException {
        String where = "parent_id = " + parentFolder.getFolderId();
        Cursor c = this.mProviderClient.query(this.mEmailFolderUri, BluetoothMapContract.BT_FOLDER_PROJECTION, where, null, null);
        if (c != null) {
            try {
                c.moveToPosition(-1);
                while (c.moveToNext()) {
                    String name = c.getString(c.getColumnIndex("name"));
                    long id = c.getLong(c.getColumnIndex("_id"));
                    BluetoothMapFolderElement newFolder = parentFolder.addEmailFolder(name, id);
                    addEmailFolders(newFolder);
                }
            } finally {
                if (c != null) {
                    c.close();
                }
            }
        }
    }

    public boolean isSrmSupported() {
        return true;
    }

    public int getRemoteFeatureMask() {
        return this.mRemoteFeatureMask;
    }

    public void setRemoteFeatureMask(int mRemoteFeatureMask) {
        this.mRemoteFeatureMask = mRemoteFeatureMask;
        this.mOutContent.setRemoteFeatureMask(mRemoteFeatureMask);
        if ((mRemoteFeatureMask & 256) == 256) {
            this.mMessageVersion = "1.1";
        }
    }

    public int onConnect(HeaderSet request, HeaderSet reply) {
        this.mThreadIdSupport = false;
        this.mMessageVersion = "1.0";
        notifyUpdateWakeLock();
        try {
            byte[] uuid = (byte[]) request.getHeader(70);
            Long threadedMailKey = (Long) request.getHeader(THREADED_MAIL_HEADER_ID);
            if (uuid == null) {
                return 198;
            }
            if (uuid.length != 16) {
                Log.w(TAG, "Wrong UUID length");
                return 198;
            }
            for (int i = 0; i < 16; i++) {
                if (uuid[i] != MAP_TARGET[i]) {
                    Log.w(TAG, "Wrong UUID");
                    return 198;
                }
            }
            reply.setHeader(74, uuid);
            try {
                byte[] remote = (byte[]) request.getHeader(74);
                if (remote != null) {
                    reply.setHeader(70, remote);
                }
                if (threadedMailKey != null && threadedMailKey.longValue() == THREAD_MAIL_KEY) {
                    this.mThreadIdSupport = true;
                    reply.setHeader(THREADED_MAIL_HEADER_ID, Long.valueOf(THREAD_MAIL_KEY));
                }
                if ((this.mRemoteFeatureMask & 512) == 512) {
                    this.mThreadIdSupport = true;
                }
                if ((this.mRemoteFeatureMask & 256) == 256) {
                    this.mMessageVersion = "1.1";
                }
                Handler handler = this.mCallback;
                if (handler != null) {
                    Message msg = Message.obtain(handler);
                    msg.what = SapService.MSG_SESSION_ESTABLISHED;
                    msg.sendToTarget();
                    return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
                }
                return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
            } catch (IOException e) {
                Log.e(TAG, "Exception during onConnect:", e);
                this.mThreadIdSupport = false;
                return 208;
            }
        } catch (IOException e2) {
            Log.e(TAG, "Exception during onConnect:", e2);
            return 208;
        }
    }

    public void onDisconnect(HeaderSet req, HeaderSet resp) {
        notifyUpdateWakeLock();
        resp.responseCode = BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
        Handler handler = this.mCallback;
        if (handler != null) {
            Message msg = Message.obtain(handler);
            msg.what = SapService.MSG_SESSION_DISCONNECTED;
            msg.sendToTarget();
        }
    }

    public int onAbort(HeaderSet request, HeaderSet reply) {
        notifyUpdateWakeLock();
        this.mIsAborted = true;
        return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
    }

    private boolean isUserUnlocked() {
        UserManager manager = UserManager.get(this.mContext);
        return manager == null || manager.isUserUnlocked();
    }

    public int onPut(Operation op) {
        this.mIsAborted = false;
        notifyUpdateWakeLock();
        BluetoothMapAppParams appParams = null;
        try {
            HeaderSet request = op.getReceivedHeader();
            String type = (String) request.getHeader(66);
            String name = (String) request.getHeader(1);
            byte[] appParamRaw = (byte[]) request.getHeader(76);
            if (appParamRaw != null) {
                appParams = new BluetoothMapAppParams(appParamRaw);
            }
            if (type.equals(TYPE_MESSAGE_UPDATE)) {
                return updateInbox();
            }
            if (type.equals(TYPE_SET_NOTIFICATION_REGISTRATION)) {
                return this.mObserver.setNotificationRegistration(appParams.getNotificationStatus());
            }
            if (type.equals(TYPE_SET_NOTIFICATION_FILTER)) {
                if (!isUserUnlocked()) {
                    Log.e(TAG, "Storage locked, " + type + " failed");
                    return 211;
                }
                this.mObserver.setNotificationFilter(appParams.getNotificationFilter());
                return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
            }
            if (type.equals(TYPE_SET_MESSAGE_STATUS)) {
                if (!isUserUnlocked()) {
                    Log.e(TAG, "Storage locked, " + type + " failed");
                    return 211;
                }
                return setMessageStatus(name, appParams);
            }
            if (!type.equals(TYPE_MESSAGE)) {
                return type.equals(TYPE_SET_OWNER_STATUS) ? setOwnerStatus(name, appParams) : BluetoothShare.STATUS_RUNNING;
            }
            if (!isUserUnlocked()) {
                Log.e(TAG, "Storage locked, " + type + " failed");
                return 211;
            }
            return pushMessage(op, name, appParams, this.mMessageVersion);
        } catch (RemoteException e) {
            try {
                this.mProviderClient = acquireUnstableContentProviderOrThrow();
            } catch (RemoteException e2) {
            }
            return BluetoothShare.STATUS_RUNNING;
        } catch (Exception e3) {
            Log.e(TAG, "Exception occured while handling request");
            return this.mIsAborted ? BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED : BluetoothShare.STATUS_RUNNING;
        }
    }

    private int updateInbox() throws RemoteException {
        BluetoothMapFolderElement inboxFolder;
        if (this.mAccount == null || (inboxFolder = this.mCurrentFolder.getFolderByName(BluetoothMapContract.FOLDER_NAME_INBOX)) == null) {
            return 209;
        }
        long accountId = this.mAccountId;
        Bundle extras = new Bundle(2);
        if (accountId == -1) {
            return 209;
        }
        extras.putLong(BluetoothMapContract.EXTRA_UPDATE_FOLDER_ID, inboxFolder.getFolderId());
        extras.putLong(BluetoothMapContract.EXTRA_UPDATE_ACCOUNT_ID, accountId);
        Uri.parse(this.mBaseUriString);
        try {
            Bundle myBundle = this.mProviderClient.call(BluetoothMapContract.METHOD_UPDATE_FOLDER, null, extras);
            if (myBundle != null) {
                return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
            }
            return 209;
        } catch (RemoteException e) {
            this.mProviderClient = acquireUnstableContentProviderOrThrow();
            return 211;
        } catch (IllegalArgumentException e2) {
            return 211;
        } catch (NullPointerException e3) {
            return 211;
        }
    }

    private BluetoothMapFolderElement getFolderElementFromName(String folderName) {
        if (folderName == null || folderName.trim().isEmpty()) {
            BluetoothMapFolderElement folderElement = this.mCurrentFolder;
            return folderElement;
        }
        BluetoothMapFolderElement folderElement2 = this.mCurrentFolder.getSubFolder(folderName);
        return folderElement2;
    }

    private int pushMessage(Operation op, String folderName, BluetoothMapAppParams appParams, String messageVersion) throws RemoteException {
        RemoteException e;
        BluetoothMapFolderElement folderElement;
        if (appParams.getCharset() == -1) {
            return 204;
        }
        InputStream bMsgStream = null;
        try {
            try {
                folderElement = getFolderElementFromName(folderName);
            } catch (RemoteException e2) {
                e = e2;
            } catch (IOException e3) {
                e = e3;
            } catch (IllegalArgumentException e4) {
                e = e4;
            } catch (Exception e5) {
                e = e5;
            } catch (Throwable th) {
                e = th;
            }
            if (folderElement == null) {
                Log.w(TAG, "pushMessage: folderElement == null - sending OBEX_HTTP_PRECON_FAILED");
                if (0 != 0) {
                    try {
                        bMsgStream.close();
                    } catch (IOException e6) {
                    }
                }
                return 204;
            }
            String folderName2 = folderElement.getName();
            try {
                if (!folderName2.equalsIgnoreCase(BluetoothMapContract.FOLDER_NAME_OUTBOX) && !folderName2.equalsIgnoreCase(BluetoothMapContract.FOLDER_NAME_DRAFT)) {
                    if (0 != 0) {
                        try {
                            bMsgStream.close();
                        } catch (IOException e7) {
                        }
                    }
                    return 198;
                }
                bMsgStream = op.openInputStream();
                BluetoothMapbMessage message = BluetoothMapbMessage.parse(bMsgStream, appParams.getCharset());
                try {
                    message.setVersionString(messageVersion);
                    if (message.getType().equals(BluetoothMapUtils.TYPE.SMS_GSM) || message.getType().equals(BluetoothMapUtils.TYPE.SMS_CDMA)) {
                        TelephonyManager tm = (TelephonyManager) this.mContext.getSystemService("phone");
                        if (tm.getPhoneType() == 1) {
                            message.setType(BluetoothMapUtils.TYPE.SMS_GSM);
                        } else if (tm.getPhoneType() == 2) {
                            message.setType(BluetoothMapUtils.TYPE.SMS_CDMA);
                        }
                    }
                    if (this.mObserver == null || message == null) {
                        if (bMsgStream != null) {
                            try {
                                bMsgStream.close();
                            } catch (IOException e8) {
                            }
                        }
                        return 211;
                    }
                    if ((message.getType().equals(BluetoothMapUtils.TYPE.EMAIL) && folderElement.getFolderId() == -1) || ((message.getType().equals(BluetoothMapUtils.TYPE.SMS_GSM) || message.getType().equals(BluetoothMapUtils.TYPE.SMS_CDMA) || message.getType().equals(BluetoothMapUtils.TYPE.MMS)) && !folderElement.hasSmsMmsContent())) {
                        if (bMsgStream != null) {
                            try {
                                bMsgStream.close();
                            } catch (IOException e9) {
                            }
                        }
                        return 198;
                    }
                    try {
                        long handle = this.mObserver.pushMessage(message, folderElement, appParams, this.mBaseUriString);
                        if (handle < 0) {
                            if (bMsgStream != null) {
                                try {
                                    bMsgStream.close();
                                } catch (IOException e10) {
                                }
                            }
                            return 211;
                        }
                        HeaderSet replyHeaders = new HeaderSet();
                        String handleStr = BluetoothMapUtils.getMapHandle(handle, message.getType());
                        replyHeaders.setHeader(1, handleStr);
                        try {
                            op.sendHeaders(replyHeaders);
                            if (bMsgStream != null) {
                                try {
                                    bMsgStream.close();
                                } catch (IOException e11) {
                                }
                            }
                            return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
                        } catch (RemoteException e12) {
                            e = e12;
                        } catch (IOException e13) {
                            e = e13;
                            if (this.mIsAborted) {
                                if (bMsgStream != null) {
                                    try {
                                        bMsgStream.close();
                                    } catch (IOException e14) {
                                    }
                                }
                                return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
                            }
                            if (bMsgStream != null) {
                                try {
                                    bMsgStream.close();
                                } catch (IOException e15) {
                                }
                            }
                            return BluetoothShare.STATUS_RUNNING;
                        } catch (IllegalArgumentException e16) {
                            e = e16;
                            if (bMsgStream != null) {
                                try {
                                    bMsgStream.close();
                                } catch (IOException e17) {
                                }
                            }
                            return 204;
                        } catch (Exception e18) {
                            e = e18;
                            if (bMsgStream != null) {
                                try {
                                    bMsgStream.close();
                                } catch (IOException e19) {
                                }
                            }
                            return BluetoothShare.STATUS_RUNNING;
                        }
                    } catch (RemoteException e20) {
                        e = e20;
                    } catch (IOException e21) {
                        e = e21;
                    } catch (IllegalArgumentException e22) {
                        e = e22;
                    } catch (Exception e23) {
                        e = e23;
                    } catch (Throwable th2) {
                        e = th2;
                        if (bMsgStream == null) {
                            throw e;
                        }
                        try {
                            bMsgStream.close();
                            throw e;
                        } catch (IOException e24) {
                            throw e;
                        }
                    }
                } catch (RemoteException e25) {
                    e = e25;
                } catch (IOException e26) {
                    e = e26;
                } catch (IllegalArgumentException e27) {
                    e = e27;
                } catch (Exception e28) {
                    e = e28;
                } catch (Throwable th3) {
                    e = th3;
                }
            } catch (RemoteException e29) {
                e = e29;
            } catch (IOException e30) {
                e = e30;
            } catch (IllegalArgumentException e31) {
                e = e31;
            } catch (Exception e32) {
                e = e32;
            } catch (Throwable th4) {
                e = th4;
            }
            try {
                this.mProviderClient = acquireUnstableContentProviderOrThrow();
            } catch (RemoteException e33) {
            }
            if (bMsgStream != null) {
                try {
                    bMsgStream.close();
                } catch (IOException e34) {
                }
            }
            return BluetoothShare.STATUS_RUNNING;
        } catch (Throwable th5) {
            e = th5;
        }
    }

    private int setMessageStatus(String msgHandle, BluetoothMapAppParams appParams) {
        int indicator = appParams.getStatusIndicator();
        int value = appParams.getStatusValue();
        if (msgHandle == null) {
            return 204;
        }
        if (indicator == -1 || value == -1) {
        }
        if (this.mObserver == null) {
            return 211;
        }
        try {
            long handle = BluetoothMapUtils.getCpHandle(msgHandle);
            BluetoothMapUtils.TYPE msgType = BluetoothMapUtils.getMsgTypeFromHandle(msgHandle);
            if (indicator == 1) {
                if (this.mObserver.setMessageStatusDeleted(handle, msgType, this.mCurrentFolder, this.mBaseUriString, value)) {
                    return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
                }
                return 211;
            }
            if (indicator == 0) {
                try {
                    if (this.mObserver.setMessageStatusRead(handle, msgType, this.mBaseUriString, value)) {
                        return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
                    }
                    return 211;
                } catch (RemoteException e) {
                    return 211;
                }
            }
            return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
        } catch (NumberFormatException e2) {
            Log.w(TAG, "Wrongly formatted message handle: " + msgHandle);
            return 204;
        } catch (IllegalArgumentException e3) {
            Log.w(TAG, "Message type not found in handle string: " + msgHandle);
            return 204;
        }
    }

    private int setOwnerStatus(String msgHandle, BluetoothMapAppParams appParams) throws RemoteException {
        BluetoothMapAccountItem bluetoothMapAccountItem = this.mAccount;
        if (bluetoothMapAccountItem == null || bluetoothMapAccountItem.getType() != BluetoothMapUtils.TYPE.IM) {
            return 211;
        }
        Bundle extras = new Bundle(5);
        int presenceState = appParams.getPresenceAvailability();
        String presenceStatus = appParams.getPresenceStatus();
        long lastActivity = appParams.getLastActivity();
        int chatState = appParams.getChatState();
        String chatStatusConvoId = appParams.getChatStateConvoIdString();
        if (presenceState == -1 && presenceStatus == null && lastActivity == -1 && chatState == -1 && chatStatusConvoId == null) {
            return 204;
        }
        if (presenceState != -1) {
            extras.putInt(BluetoothMapContract.EXTRA_PRESENCE_STATE, presenceState);
        }
        if (presenceStatus != null) {
            extras.putString(BluetoothMapContract.EXTRA_PRESENCE_STATUS, presenceStatus);
        }
        if (lastActivity != -1) {
            extras.putLong(BluetoothMapContract.EXTRA_LAST_ACTIVE, lastActivity);
        }
        if (chatState != -1 && chatStatusConvoId != null) {
            extras.putInt(BluetoothMapContract.EXTRA_CHAT_STATE, chatState);
            extras.putString(BluetoothMapContract.EXTRA_CONVERSATION_ID, chatStatusConvoId);
        }
        Uri.parse(this.mBaseUriString);
        try {
            Bundle myBundle = this.mProviderClient.call(BluetoothMapContract.METHOD_SET_OWNER_STATUS, null, extras);
            if (myBundle != null) {
                return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
            }
            return 209;
        } catch (RemoteException e) {
            this.mProviderClient = acquireUnstableContentProviderOrThrow();
            return 211;
        } catch (IllegalArgumentException e2) {
            return 211;
        } catch (NullPointerException e3) {
            return 211;
        }
    }

    public int onSetPath(HeaderSet request, HeaderSet reply, boolean backup, boolean create) {
        notifyUpdateWakeLock();
        try {
            String folderName = (String) request.getHeader(1);
            if (backup) {
                if (this.mCurrentFolder.getParent() == null) {
                    return BluetoothShare.STATUS_RUNNING;
                }
                this.mCurrentFolder = this.mCurrentFolder.getParent();
            }
            if (folderName == null || folderName.trim().isEmpty()) {
                if (!backup) {
                    this.mCurrentFolder = this.mCurrentFolder.getRoot();
                    return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
                }
                return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
            }
            BluetoothMapFolderElement folder = this.mCurrentFolder.getSubFolder(folderName);
            if (folder == null) {
                return BluetoothShare.STATUS_RUNNING;
            }
            this.mCurrentFolder = folder;
            return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
        } catch (Exception e) {
            Log.e(TAG, "request headers error");
            return BluetoothShare.STATUS_RUNNING;
        }
    }

    public void onClose() {
        Handler handler = this.mCallback;
        if (handler != null) {
            Message msg = Message.obtain(handler);
            msg.what = SapService.MSG_SERVERSESSION_CLOSE;
            msg.arg1 = this.mMasId;
            msg.sendToTarget();
        }
        ContentProviderClient contentProviderClient = this.mProviderClient;
        if (contentProviderClient != null) {
            contentProviderClient.close();
            this.mProviderClient = null;
        }
    }

    public int onGet(Operation op) {
        notifyUpdateWakeLock();
        this.mIsAborted = false;
        BluetoothMapAppParams appParams = null;
        try {
            HeaderSet request = op.getReceivedHeader();
            String type = (String) request.getHeader(66);
            byte[] appParamRaw = (byte[]) request.getHeader(76);
            if (appParamRaw != null) {
                appParams = new BluetoothMapAppParams(appParamRaw);
            }
            if (type == null) {
                return BluetoothShare.STATUS_RUNNING;
            }
            if (type.equals(TYPE_GET_FOLDER_LISTING)) {
                return sendFolderListingRsp(op, appParams);
            }
            if (type.equals(TYPE_GET_MESSAGE_LISTING)) {
                String name = (String) request.getHeader(1);
                if (!isUserUnlocked()) {
                    Log.e(TAG, "Storage locked, " + type + " failed");
                    return 211;
                }
                return sendMessageListingRsp(op, appParams, name);
            }
            if (type.equals(TYPE_GET_CONVO_LISTING)) {
                String name2 = (String) request.getHeader(1);
                if (!isUserUnlocked()) {
                    Log.e(TAG, "Storage locked, " + type + " failed");
                    return 211;
                }
                return sendConvoListingRsp(op, appParams, name2);
            }
            if (type.equals(TYPE_GET_MAS_INSTANCE_INFORMATION)) {
                return sendMASInstanceInformationRsp(op, appParams);
            }
            if (type.equals(TYPE_MESSAGE)) {
                String name3 = (String) request.getHeader(1);
                if (!isUserUnlocked()) {
                    Log.e(TAG, "Storage locked, " + type + " failed");
                    return 211;
                }
                return sendGetMessageRsp(op, name3, appParams, this.mMessageVersion);
            }
            Log.w(TAG, "unknown type request: " + type);
            return 198;
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Exception:", e);
            return 204;
        } catch (ParseException e2) {
            Log.e(TAG, "Exception:", e2);
            return 204;
        } catch (Exception e3) {
            Log.e(TAG, "Exception occured while handling request");
            if (!this.mIsAborted) {
                return BluetoothShare.STATUS_RUNNING;
            }
            return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:124:0x01d5 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01d8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x014e A[EXC_TOP_SPLITTER, LOOP:0: B:128:0x014e->B:63:0x015e, LOOP_START, PHI: r8
  0x014e: PHI (r8v10 'bytesWritten' int) = (r8v9 'bytesWritten' int), (r8v11 'bytesWritten' int) binds: [B:57:0x014c, B:63:0x015e] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:129:0x01cc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01b8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0094 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:156:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00aa A[Catch: IllegalArgumentException -> 0x0098, IOException -> 0x009e, TRY_ENTER, TRY_LEAVE, TryCatch #14 {IOException -> 0x009e, IllegalArgumentException -> 0x0098, blocks: (B:21:0x0094, B:29:0x00aa, B:32:0x00b3, B:36:0x00cb, B:41:0x00f0), top: B:142:0x0094 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b3 A[Catch: IllegalArgumentException -> 0x0098, IOException -> 0x009e, TRY_ENTER, TryCatch #14 {IOException -> 0x009e, IllegalArgumentException -> 0x0098, blocks: (B:21:0x0094, B:29:0x00aa, B:32:0x00b3, B:36:0x00cb, B:41:0x00f0), top: B:142:0x0094 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00d8 A[Catch: IllegalArgumentException -> 0x01ac, IOException -> 0x01c0, TRY_ENTER, TRY_LEAVE, TryCatch #12 {IOException -> 0x01c0, IllegalArgumentException -> 0x01ac, blocks: (B:18:0x008d, B:27:0x00a4, B:30:0x00ad, B:38:0x00d8), top: B:146:0x008d }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00f0 A[Catch: IllegalArgumentException -> 0x0098, IOException -> 0x009e, TRY_ENTER, TRY_LEAVE, TryCatch #14 {IOException -> 0x009e, IllegalArgumentException -> 0x0098, blocks: (B:21:0x0094, B:29:0x00aa, B:32:0x00b3, B:36:0x00cb, B:41:0x00f0), top: B:142:0x0094 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00f4 A[Catch: IllegalArgumentException -> 0x01a0, IOException -> 0x01a6, TRY_ENTER, TryCatch #13 {IOException -> 0x01a6, IllegalArgumentException -> 0x01a0, blocks: (B:39:0x00eb, B:44:0x00f7, B:46:0x00fe, B:43:0x00f4), top: B:144:0x00eb }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00fe A[Catch: IllegalArgumentException -> 0x01a0, IOException -> 0x01a6, TRY_LEAVE, TryCatch #13 {IOException -> 0x01a6, IllegalArgumentException -> 0x01a0, blocks: (B:39:0x00eb, B:44:0x00f7, B:46:0x00fe, B:43:0x00f4), top: B:144:0x00eb }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0115 A[Catch: IllegalArgumentException -> 0x0196, IOException -> 0x019b, TryCatch #15 {IOException -> 0x019b, IllegalArgumentException -> 0x0196, blocks: (B:48:0x0108, B:50:0x010e, B:52:0x0115, B:53:0x0125), top: B:140:0x0108 }] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0188  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int sendMessageListingRsp(javax.obex.Operation r17, com.android.bluetooth.map.BluetoothMapAppParams r18, java.lang.String r19) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 475
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.map.BluetoothMapObexServer.sendMessageListingRsp(javax.obex.Operation, com.android.bluetooth.map.BluetoothMapAppParams, java.lang.String):int");
    }

    private void setMsgTypeFilterParams(BluetoothMapAppParams appParams, boolean overwrite) {
        int masFilterMask = 0;
        if (!this.mEnableSmsMms) {
            int masFilterMask2 = 0 | 2;
            masFilterMask = masFilterMask2 | 1 | 8;
        }
        BluetoothMapAccountItem bluetoothMapAccountItem = this.mAccount;
        if (bluetoothMapAccountItem == null) {
            masFilterMask = masFilterMask | 4 | 16;
        } else {
            if (bluetoothMapAccountItem.getType() != BluetoothMapUtils.TYPE.EMAIL) {
                masFilterMask |= 4;
            }
            if (this.mAccount.getType() != BluetoothMapUtils.TYPE.IM) {
                masFilterMask |= 16;
            }
        }
        if (overwrite) {
            appParams.setFilterMessageType(masFilterMask);
            return;
        }
        int newMask = appParams.getFilterMessageType();
        if (newMask == -1) {
            appParams.setFilterMessageType(newMask);
        } else {
            appParams.setFilterMessageType(newMask | masFilterMask);
        }
    }

    private int sendConvoListingRsp(Operation op, BluetoothMapAppParams appParams, String folderName) throws Throwable {
        BluetoothMapAppParams appParams2;
        OutputStream outStream = null;
        byte[] outBytes = null;
        int bytesWritten = 0;
        HeaderSet replyHeaders = new HeaderSet();
        BluetoothMapAppParams outAppParams = new BluetoothMapAppParams();
        if (appParams != null) {
            appParams2 = appParams;
        } else {
            appParams2 = new BluetoothMapAppParams();
            appParams2.setMaxListCount(1024);
            appParams2.setStartOffset(0);
        }
        setMsgTypeFilterParams(appParams2, true);
        try {
            if (appParams2.getMaxListCount() == -1) {
                appParams2.setMaxListCount(1024);
            }
            if (appParams2.getStartOffset() == -1) {
                appParams2.setStartOffset(0);
            }
            if (appParams2.getMaxListCount() != 0) {
                BluetoothMapConvoListing outList = this.mOutContent.convoListing(appParams2, false);
                outAppParams.setConvoListingSize(outList.getCount());
                outBytes = outList.encode();
            } else {
                outAppParams.setConvoListingSize(this.mOutContent.convoListing(appParams2, true).getCount());
                if (this.mEnableSmsMms) {
                    this.mOutContent.refreshSmsMmsConvoVersions();
                }
                if (this.mAccount != null) {
                    this.mOutContent.refreshImEmailConvoVersions();
                }
                this.mObserver.refreshConvoListVersionCounter();
                if ((this.mRemoteFeatureMask & 8192) > 0) {
                    outAppParams.setConvoListingVerCounter(this.mMasInstance.getCombinedConvoListVersionCounter(), 0L);
                }
                op.noBodyHeader();
            }
            outAppParams.setDatabaseIdentifier(0L, this.mMasInstance.getDbIdentifier());
            outAppParams.setMseTime(Calendar.getInstance().getTime().getTime());
            replyHeaders.setHeader(76, outAppParams.encodeParams());
        } catch (IOException e) {
            e = e;
        } catch (IllegalArgumentException e2) {
            e = e2;
        }
        try {
            op.sendHeaders(replyHeaders);
            OutputStream outStream2 = op.openOutputStream();
            int maxChunkSize = op.getMaxPacketSize();
            if (outBytes != null) {
                while (bytesWritten < outBytes.length && !this.mIsAborted) {
                    try {
                        try {
                            int bytesToWrite = Math.min(maxChunkSize, outBytes.length - bytesWritten);
                            outStream2.write(outBytes, bytesWritten, bytesToWrite);
                            bytesWritten += bytesToWrite;
                        } catch (IOException e3) {
                            if (outStream2 != null) {
                                outStream2.close();
                            }
                        } catch (Throwable th) {
                            if (outStream2 != null) {
                                try {
                                    outStream2.close();
                                    throw th;
                                } catch (IOException e4) {
                                    throw th;
                                }
                            }
                            throw th;
                        }
                    } catch (IOException e5) {
                    }
                }
                if (outStream2 != null) {
                    outStream2.close();
                }
                if (bytesWritten != outBytes.length && !this.mIsAborted) {
                    Log.w(TAG, "sendConvoListingRsp: bytesWritten != outBytes.length - sending OBEX_HTTP_BAD_REQUEST");
                    return BluetoothShare.STATUS_RUNNING;
                }
                return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
            }
            if (outStream2 != null) {
                try {
                    outStream2.close();
                    return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
                } catch (IOException e6) {
                    return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
                }
            }
            return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
        } catch (IOException e7) {
            e = e7;
            Log.w(TAG, "sendConvoListingRsp: IOException - sending OBEX_HTTP_BAD_REQUEST", e);
            if (0 != 0) {
                try {
                    outStream.close();
                } catch (IOException e8) {
                }
            }
            if (this.mIsAborted) {
                return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
            }
            return BluetoothShare.STATUS_RUNNING;
        } catch (IllegalArgumentException e9) {
            e = e9;
            Log.w(TAG, "sendConvoListingRsp: IllegalArgumentException - sending OBEX_HTTP_BAD_REQUEST", e);
            if (0 != 0) {
                try {
                    outStream.close();
                    return BluetoothShare.STATUS_RUNNING;
                } catch (IOException e10) {
                    return BluetoothShare.STATUS_RUNNING;
                }
            }
            return BluetoothShare.STATUS_RUNNING;
        }
    }

    private int sendFolderListingRsp(Operation op, BluetoothMapAppParams appParams) {
        byte[] outBytes = null;
        BluetoothMapAppParams outAppParams = new BluetoothMapAppParams();
        int bytesWritten = 0;
        HeaderSet replyHeaders = new HeaderSet();
        if (appParams == null) {
            appParams = new BluetoothMapAppParams();
            appParams.setMaxListCount(1024);
        }
        try {
            int maxListCount = appParams.getMaxListCount();
            int listStartOffset = appParams.getStartOffset();
            if (listStartOffset == -1) {
                listStartOffset = 0;
            }
            if (maxListCount == -1) {
                maxListCount = 1024;
            }
            if (maxListCount != 0) {
                outBytes = this.mCurrentFolder.encode(listStartOffset, maxListCount);
            } else {
                outAppParams.setFolderListingSize(this.mCurrentFolder.getSubFolderCount());
                op.noBodyHeader();
            }
            replyHeaders.setHeader(76, outAppParams.encodeParams());
            op.sendHeaders(replyHeaders);
            outStream = maxListCount != 0 ? op.openOutputStream() : null;
            int maxChunkSize = op.getMaxPacketSize();
            if (outBytes == null) {
                return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
            }
            while (bytesWritten < outBytes.length && !this.mIsAborted) {
                try {
                    try {
                        int bytesToWrite = Math.min(maxChunkSize, outBytes.length - bytesWritten);
                        outStream.write(outBytes, bytesWritten, bytesToWrite);
                        bytesWritten += bytesToWrite;
                    } catch (IOException e) {
                        if (outStream != null) {
                            outStream.close();
                        }
                    } catch (Throwable th) {
                        if (outStream != null) {
                            try {
                                outStream.close();
                            } catch (IOException e2) {
                            }
                        }
                        throw th;
                    }
                } catch (IOException e3) {
                }
            }
            if (outStream != null) {
                outStream.close();
            }
            return (bytesWritten == outBytes.length || this.mIsAborted) ? BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED : BluetoothShare.STATUS_RUNNING;
        } catch (IOException e1) {
            Log.w(TAG, "sendFolderListingRsp: IOException - sending OBEX_HTTP_BAD_REQUEST Exception:", e1);
            if (0 != 0) {
                try {
                    outStream.close();
                } catch (IOException e4) {
                }
            }
            return this.mIsAborted ? BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED : BluetoothShare.STATUS_RUNNING;
        } catch (IllegalArgumentException e12) {
            Log.w(TAG, "sendFolderListingRsp: IllegalArgumentException - sending OBEX_HTTP_BAD_REQUEST Exception:", e12);
            if (0 != 0) {
                try {
                    outStream.close();
                    return 204;
                } catch (IOException e5) {
                    return 204;
                }
            }
            return 204;
        }
    }

    private int sendMASInstanceInformationRsp(Operation op, BluetoothMapAppParams appParams) {
        String outString = null;
        int bytesWritten = 0;
        try {
            if (this.mMasId != appParams.getMasInstanceId()) {
                return BluetoothShare.STATUS_RUNNING;
            }
            if (this.mAccount != null) {
                if (this.mAccount.getType() == BluetoothMapUtils.TYPE.EMAIL) {
                    outString = this.mAccount.getName() != null ? this.mAccount.getName() : "EMAIL";
                } else if (this.mAccount.getType() == BluetoothMapUtils.TYPE.IM && (outString = this.mAccount.getUciFull()) == null) {
                    String uci = this.mAccount.getUci();
                    int length = 5;
                    if (uci != null) {
                        length = 5 + uci.length();
                    }
                    StringBuilder sb = new StringBuilder(length);
                    sb.append("un");
                    if (this.mMasId < 10) {
                        sb.append("00");
                    } else if (this.mMasId < 100) {
                        sb.append("0");
                    }
                    sb.append(this.mMasId);
                    if (uci != null) {
                        sb.append(":");
                        sb.append(uci);
                    }
                    outString = sb.toString();
                }
            } else {
                outString = BluetoothMapMasInstance.TYPE_SMS_MMS_STR;
            }
            byte[] outBytes = BluetoothMapUtils.truncateUtf8StringToBytearray(outString, 200);
            OutputStream outStream = op.openOutputStream();
            int maxChunkSize = op.getMaxPacketSize();
            if (outBytes == null) {
                return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
            }
            while (bytesWritten < outBytes.length && !this.mIsAborted) {
                try {
                    try {
                        int bytesToWrite = Math.min(maxChunkSize, outBytes.length - bytesWritten);
                        outStream.write(outBytes, bytesWritten, bytesToWrite);
                        bytesWritten += bytesToWrite;
                    } catch (IOException e) {
                        if (outStream != null) {
                            outStream.close();
                        }
                    } catch (Throwable th) {
                        if (outStream != null) {
                            try {
                                outStream.close();
                            } catch (IOException e2) {
                            }
                        }
                        throw th;
                    }
                } catch (IOException e3) {
                }
            }
            if (outStream != null) {
                outStream.close();
            }
            return (bytesWritten == outBytes.length || this.mIsAborted) ? BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED : BluetoothShare.STATUS_RUNNING;
        } catch (IOException e4) {
            Log.w(TAG, "sendMASInstanceInformationRsp: IOException - sending OBEX_HTTP_BAD_REQUEST", e4);
            return this.mIsAborted ? BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED : BluetoothShare.STATUS_RUNNING;
        }
    }

    private int sendGetMessageRsp(Operation op, String handle, BluetoothMapAppParams appParams, String version) {
        OutputStream outStream = null;
        int bytesWritten = 0;
        try {
            byte[] outBytes = this.mOutContent.getMessage(handle, appParams, this.mCurrentFolder, version);
            if ((BluetoothMapUtils.getMsgTypeFromHandle(handle).equals(BluetoothMapUtils.TYPE.EMAIL) || BluetoothMapUtils.getMsgTypeFromHandle(handle).equals(BluetoothMapUtils.TYPE.IM)) && appParams.getFractionRequest() == 0) {
                BluetoothMapAppParams outAppParams = new BluetoothMapAppParams();
                HeaderSet replyHeaders = new HeaderSet();
                outAppParams.setFractionDeliver(1);
                replyHeaders.setHeader(76, outAppParams.encodeParams());
                op.sendHeaders(replyHeaders);
            }
            OutputStream outStream2 = op.openOutputStream();
            int maxChunkSize = op.getMaxPacketSize();
            if (outBytes == null) {
                return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
            }
            while (bytesWritten < outBytes.length && !this.mIsAborted) {
                try {
                    try {
                        int bytesToWrite = Math.min(maxChunkSize, outBytes.length - bytesWritten);
                        outStream2.write(outBytes, bytesWritten, bytesToWrite);
                        bytesWritten += bytesToWrite;
                    } catch (IOException e) {
                        if (outStream2 != null) {
                            outStream2.close();
                        }
                    } catch (Throwable th) {
                        if (outStream2 != null) {
                            try {
                                outStream2.close();
                            } catch (IOException e2) {
                            }
                        }
                        throw th;
                    }
                } catch (IOException e3) {
                }
            }
            if (outStream2 != null) {
                outStream2.close();
            }
            return (bytesWritten == outBytes.length || this.mIsAborted) ? BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED : BluetoothShare.STATUS_RUNNING;
        } catch (IOException e4) {
            Log.w(TAG, "sendGetMessageRsp: IOException - sending OBEX_HTTP_BAD_REQUEST", e4);
            if (0 != 0) {
                try {
                    outStream.close();
                } catch (IOException e5) {
                }
            }
            return this.mIsAborted ? BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED : BluetoothShare.STATUS_RUNNING;
        } catch (IllegalArgumentException e6) {
            Log.w(TAG, "sendGetMessageRsp: IllegalArgumentException (e.g. invalid handle) - sending OBEX_HTTP_BAD_REQUEST", e6);
            if (0 != 0) {
                try {
                    outStream.close();
                } catch (IOException e7) {
                }
            }
            return BluetoothShare.STATUS_RUNNING;
        }
    }

    public int onDelete(HeaderSet request, HeaderSet reply) {
        this.mIsAborted = false;
        notifyUpdateWakeLock();
        BluetoothMapAppParams appParams = null;
        try {
            String type = (String) request.getHeader(66);
            String name = (String) request.getHeader(1);
            byte[] appParamRaw = (byte[]) request.getHeader(76);
            if (appParamRaw != null) {
                appParams = new BluetoothMapAppParams(appParamRaw);
            }
            if (!type.equals(TYPE_SET_NOTIFICATION_FILTER)) {
                return type.equals(TYPE_SET_OWNER_STATUS) ? setOwnerStatus(name, appParams) : BluetoothShare.STATUS_RUNNING;
            }
            this.mObserver.setNotificationFilter(appParams.getNotificationFilter());
            return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
        } catch (RemoteException e) {
            try {
                this.mProviderClient = acquireUnstableContentProviderOrThrow();
            } catch (RemoteException e2) {
            }
            return BluetoothShare.STATUS_RUNNING;
        } catch (Exception e3) {
            Log.e(TAG, "Exception occured while handling request");
            return this.mIsAborted ? BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED : BluetoothShare.STATUS_RUNNING;
        }
    }

    private void notifyUpdateWakeLock() {
        Handler handler = this.mCallback;
        if (handler != null) {
            Message msg = Message.obtain(handler);
            msg.what = SapService.MSG_ACQUIRE_WAKE_LOCK;
            msg.sendToTarget();
        }
    }

    private static void logHeader(HeaderSet hs) {
        Log.v(TAG, "Dumping HeaderSet " + hs.toString());
        try {
            Log.v(TAG, "CONNECTION_ID : " + hs.getHeader(203));
            Log.v(TAG, "NAME : " + hs.getHeader(1));
            Log.v(TAG, "TYPE : " + hs.getHeader(66));
            Log.v(TAG, "TARGET : " + hs.getHeader(70));
            Log.v(TAG, "WHO : " + hs.getHeader(74));
            Log.v(TAG, "APPLICATION_PARAMETER : " + hs.getHeader(76));
        } catch (IOException e) {
            Log.e(TAG, "dump HeaderSet error " + e);
        }
        Log.v(TAG, "NEW!!! Dumping HeaderSet END");
    }
}
