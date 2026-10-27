package com.android.bluetooth.pbap;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.UserHandle;
import android.util.Log;
import com.android.bluetooth.BluetoothMetricsProto;
import com.android.bluetooth.BluetoothObexTransport;
import com.android.bluetooth.IObexConnectionHandler;
import com.android.bluetooth.ObexRejectServer;
import com.android.bluetooth.R;
import com.android.bluetooth.btservice.MetricsLogger;
import com.android.bluetooth.btservice.ProfileService;
import com.android.bluetooth.mapapi.BluetoothMapContract;
import com.android.bluetooth.sap.SapService;
import com.android.internal.util.State;
import com.android.internal.util.StateMachine;
import com.android.vcard.VCardConfig;
import java.io.IOException;
import javax.obex.Authenticator;
import javax.obex.ServerSession;

/* JADX INFO: loaded from: classes.dex */
class PbapStateMachine extends StateMachine {
    static final int AUTHORIZED = 1;
    static final int AUTH_CANCELLED = 8;
    static final int AUTH_KEY_INPUT = 7;
    static final int CREATE_NOTIFICATION = 5;
    private static final boolean DEBUG = true;
    static final int DISCONNECT = 3;
    private static final String PBAP_OBEX_NOTIFICATION_CHANNEL = "pbap_obex_notification_channel";
    static final int REJECTED = 2;
    static final int REMOVE_NOTIFICATION = 6;
    static final int REQUEST_PERMISSION = 4;
    private static final String TAG = "PbapStateMachine";
    private static final boolean VERBOSE = true;
    private BluetoothSocket mConnSocket;
    private final Connected mConnected;
    private final Finished mFinished;
    private IObexConnectionHandler mIObexConnectionHandler;
    private int mNotificationId;
    private BluetoothPbapAuthenticator mObexAuth;
    private BluetoothPbapObexServer mPbapServer;
    private PbapStateBase mPrevState;
    private BluetoothDevice mRemoteDevice;
    private ServerSession mServerSession;
    private BluetoothPbapService mService;
    private Handler mServiceHandler;
    private final WaitingForAuth mWaitingForAuth;

    private PbapStateMachine(BluetoothPbapService service, Looper looper, BluetoothDevice device, BluetoothSocket connSocket, IObexConnectionHandler obexConnectionHandler, Handler pbapHandler, int notificationId) {
        super(TAG, looper);
        this.mWaitingForAuth = new WaitingForAuth();
        this.mFinished = new Finished();
        this.mConnected = new Connected();
        this.mService = service;
        this.mIObexConnectionHandler = obexConnectionHandler;
        this.mRemoteDevice = device;
        this.mServiceHandler = pbapHandler;
        this.mConnSocket = connSocket;
        this.mNotificationId = notificationId;
        addState(this.mFinished);
        addState(this.mWaitingForAuth);
        addState(this.mConnected);
        setInitialState(this.mWaitingForAuth);
    }

    static PbapStateMachine make(BluetoothPbapService service, Looper looper, BluetoothDevice device, BluetoothSocket connSocket, IObexConnectionHandler obexConnectionHandler, Handler pbapHandler, int notificationId) {
        PbapStateMachine stateMachine = new PbapStateMachine(service, looper, device, connSocket, obexConnectionHandler, pbapHandler, notificationId);
        stateMachine.start();
        return stateMachine;
    }

    BluetoothDevice getRemoteDevice() {
        return this.mRemoteDevice;
    }

    private abstract class PbapStateBase extends State {
        abstract int getConnectionStateInt();

        private PbapStateBase() {
        }

        public void enter() {
            if (!(this instanceof WaitingForAuth) && PbapStateMachine.this.mPrevState == null) {
                throw new IllegalStateException("mPrevState is null on entering initial state");
            }
            enforceValidConnectionStateTransition();
        }

        public void exit() {
            PbapStateMachine.this.mPrevState = this;
        }

        private void broadcastConnectionState(BluetoothDevice device, int fromState, int toState) {
            stateLogD("broadcastConnectionState " + device.getAddressOnlyforLog() + ": " + fromState + "->" + toState);
            Intent intent = new Intent("android.bluetooth.pbap.profile.action.CONNECTION_STATE_CHANGED");
            intent.putExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", fromState);
            intent.putExtra("android.bluetooth.profile.extra.STATE", toState);
            intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
            intent.addFlags(16777216);
            PbapStateMachine.this.mService.sendBroadcastAsUser(intent, UserHandle.ALL, ProfileService.BLUETOOTH_PERM);
        }

        void broadcastStateTransitions() {
            int prevStateInt = 0;
            if (PbapStateMachine.this.mPrevState != null) {
                prevStateInt = PbapStateMachine.this.mPrevState.getConnectionStateInt();
            }
            if (getConnectionStateInt() != prevStateInt) {
                stateLogD("connection state changed: " + PbapStateMachine.this.mRemoteDevice + ": " + PbapStateMachine.this.mPrevState + " -> " + this);
                broadcastConnectionState(PbapStateMachine.this.mRemoteDevice, prevStateInt, getConnectionStateInt());
            }
        }

        private void enforceValidConnectionStateTransition() {
            boolean isValidTransition = false;
            if (this == PbapStateMachine.this.mWaitingForAuth) {
                isValidTransition = PbapStateMachine.this.mPrevState == null;
            } else if (this == PbapStateMachine.this.mFinished) {
                if (PbapStateMachine.this.mPrevState != PbapStateMachine.this.mConnected && PbapStateMachine.this.mPrevState != PbapStateMachine.this.mWaitingForAuth) {
                    z = false;
                }
                isValidTransition = z;
            } else if (this == PbapStateMachine.this.mConnected) {
                if (PbapStateMachine.this.mPrevState != PbapStateMachine.this.mFinished && PbapStateMachine.this.mPrevState != PbapStateMachine.this.mWaitingForAuth) {
                    z = false;
                }
                isValidTransition = z;
            }
            if (!isValidTransition) {
                throw new IllegalStateException("Invalid state transition from " + PbapStateMachine.this.mPrevState + " to " + this + " for device " + PbapStateMachine.this.mRemoteDevice);
            }
        }

        void stateLogD(String msg) {
            PbapStateMachine.this.log(getName() + ": currentDevice=" + PbapStateMachine.this.mRemoteDevice + ", msg=" + msg);
        }
    }

    class WaitingForAuth extends PbapStateBase {
        WaitingForAuth() {
            super();
        }

        @Override // com.android.bluetooth.pbap.PbapStateMachine.PbapStateBase
        int getConnectionStateInt() {
            return 1;
        }

        @Override // com.android.bluetooth.pbap.PbapStateMachine.PbapStateBase
        public void enter() {
            super.enter();
            broadcastStateTransitions();
        }

        public boolean processMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                PbapStateMachine pbapStateMachine = PbapStateMachine.this;
                pbapStateMachine.transitionTo(pbapStateMachine.mConnected);
            } else if (i == 2) {
                rejectConnection();
                PbapStateMachine pbapStateMachine2 = PbapStateMachine.this;
                pbapStateMachine2.transitionTo(pbapStateMachine2.mFinished);
            } else if (i == 3) {
                PbapStateMachine.this.mServiceHandler.removeMessages(2, PbapStateMachine.this);
                PbapStateMachine.this.mServiceHandler.obtainMessage(2, PbapStateMachine.this).sendToTarget();
                PbapStateMachine pbapStateMachine3 = PbapStateMachine.this;
                pbapStateMachine3.transitionTo(pbapStateMachine3.mFinished);
            } else if (i == 4) {
                PbapStateMachine.this.mService.checkOrGetPhonebookPermission(PbapStateMachine.this);
            }
            return true;
        }

        private void rejectConnection() {
            PbapStateMachine.this.mPbapServer = new BluetoothPbapObexServer(PbapStateMachine.this.mServiceHandler, PbapStateMachine.this.mService, PbapStateMachine.this);
            BluetoothObexTransport transport = new BluetoothObexTransport(PbapStateMachine.this.mConnSocket);
            ObexRejectServer server = new ObexRejectServer(211, PbapStateMachine.this.mConnSocket);
            try {
                PbapStateMachine.this.mServerSession = new ServerSession(transport, server, (Authenticator) null);
            } catch (IOException ex) {
                Log.e(PbapStateMachine.TAG, "Caught exception starting OBEX reject server session" + ex.toString());
            }
        }
    }

    class Finished extends PbapStateBase {
        Finished() {
            super();
        }

        @Override // com.android.bluetooth.pbap.PbapStateMachine.PbapStateBase
        int getConnectionStateInt() {
            return 0;
        }

        @Override // com.android.bluetooth.pbap.PbapStateMachine.PbapStateBase
        public void enter() {
            super.enter();
            if (PbapStateMachine.this.mServerSession != null) {
                PbapStateMachine.this.mServerSession.close();
                PbapStateMachine.this.mServerSession = null;
            }
            try {
                PbapStateMachine.this.mConnSocket.close();
                PbapStateMachine.this.mConnSocket = null;
            } catch (IOException e) {
                Log.e(PbapStateMachine.TAG, "Close Connection Socket error: " + e.toString());
            }
            PbapStateMachine.this.mServiceHandler.obtainMessage(SapService.MSG_RELEASE_WAKE_LOCK, PbapStateMachine.this).sendToTarget();
            broadcastStateTransitions();
        }
    }

    class Connected extends PbapStateBase {
        Connected() {
            super();
        }

        @Override // com.android.bluetooth.pbap.PbapStateMachine.PbapStateBase
        int getConnectionStateInt() {
            return 2;
        }

        @Override // com.android.bluetooth.pbap.PbapStateMachine.PbapStateBase
        public void enter() {
            try {
                startObexServerSession();
            } catch (IOException ex) {
                Log.e(PbapStateMachine.TAG, "Caught exception starting OBEX server session" + ex.toString());
            }
            broadcastStateTransitions();
            MetricsLogger.logProfileConnectionEvent(BluetoothMetricsProto.ProfileId.PBAP);
            PbapStateMachine.this.mService.setConnectionPolicy(PbapStateMachine.this.mRemoteDevice, 100);
        }

        public boolean processMessage(Message message) {
            int i = message.what;
            if (i == 3) {
                stopObexServerSession();
                return true;
            }
            if (i == 5) {
                createPbapNotification();
                return true;
            }
            if (i == 6) {
                Intent i2 = new Intent("com.android.bluetooth.pbap.userconfirmtimeout");
                PbapStateMachine.this.mService.sendBroadcast(i2);
                notifyAuthCancelled();
                removePbapNotification(PbapStateMachine.this.mNotificationId);
                return true;
            }
            if (i == 7) {
                String key = (String) message.obj;
                notifyAuthKeyInput(key);
                return true;
            }
            if (i == 8) {
                notifyAuthCancelled();
                return true;
            }
            return true;
        }

        private void startObexServerSession() throws IOException {
            Log.v(PbapStateMachine.TAG, "Pbap Service startObexServerSession");
            PbapStateMachine.this.mServiceHandler.sendMessage(PbapStateMachine.this.mServiceHandler.obtainMessage(5004));
            PbapStateMachine.this.mPbapServer = new BluetoothPbapObexServer(PbapStateMachine.this.mServiceHandler, PbapStateMachine.this.mService, PbapStateMachine.this);
            synchronized (this) {
                PbapStateMachine.this.mObexAuth = new BluetoothPbapAuthenticator(PbapStateMachine.this);
                PbapStateMachine.this.mObexAuth.setChallenged(false);
                PbapStateMachine.this.mObexAuth.setCancelled(false);
            }
            BluetoothObexTransport transport = new BluetoothObexTransport(PbapStateMachine.this.mConnSocket);
            PbapStateMachine.this.mServerSession = new ServerSession(transport, PbapStateMachine.this.mPbapServer, PbapStateMachine.this.mObexAuth);
        }

        private void stopObexServerSession() {
            Log.v(PbapStateMachine.TAG, "Pbap Service stopObexServerSession");
            PbapStateMachine pbapStateMachine = PbapStateMachine.this;
            pbapStateMachine.transitionTo(pbapStateMachine.mFinished);
        }

        private void createPbapNotification() {
            NotificationManager nm = (NotificationManager) PbapStateMachine.this.mService.getSystemService(BluetoothMapContract.RECEPTION_STATE_NOTIFICATION);
            NotificationChannel notificationChannel = new NotificationChannel(PbapStateMachine.PBAP_OBEX_NOTIFICATION_CHANNEL, PbapStateMachine.this.mService.getString(R.string.pbap_notification_group), 4);
            nm.createNotificationChannel(notificationChannel);
            Intent clickIntent = new Intent();
            clickIntent.setClass(PbapStateMachine.this.mService, BluetoothPbapActivity.class);
            clickIntent.putExtra("com.android.bluetooth.pbap.device", PbapStateMachine.this.mRemoteDevice);
            clickIntent.addFlags(VCardConfig.FLAG_REFRAIN_QP_TO_NAME_PROPERTIES);
            clickIntent.setAction("com.android.bluetooth.pbap.authchall");
            Intent deleteIntent = new Intent();
            deleteIntent.setClass(PbapStateMachine.this.mService, BluetoothPbapService.class);
            deleteIntent.setAction("com.android.bluetooth.pbap.authcancelled");
            String name = PbapStateMachine.this.mRemoteDevice.getName();
            Notification notification = new Notification.Builder(PbapStateMachine.this.mService, PbapStateMachine.PBAP_OBEX_NOTIFICATION_CHANNEL).setWhen(System.currentTimeMillis()).setContentTitle(PbapStateMachine.this.mService.getString(R.string.auth_notif_title)).setContentText(PbapStateMachine.this.mService.getString(R.string.auth_notif_message, new Object[]{name})).setSmallIcon(17301632).setTicker(PbapStateMachine.this.mService.getString(R.string.auth_notif_ticker)).setColor(PbapStateMachine.this.mService.getResources().getColor(android.R.color.system_notification_accent_color, PbapStateMachine.this.mService.getTheme())).setFlag(16, true).setFlag(8, true).setContentIntent(PendingIntent.getActivity(PbapStateMachine.this.mService, 0, clickIntent, 0)).setDeleteIntent(PendingIntent.getBroadcast(PbapStateMachine.this.mService, 0, deleteIntent, 0)).setLocalOnly(true).build();
            nm.notify(PbapStateMachine.this.mNotificationId, notification);
        }

        private void removePbapNotification(int id) {
            NotificationManager nm = (NotificationManager) PbapStateMachine.this.mService.getSystemService(BluetoothMapContract.RECEPTION_STATE_NOTIFICATION);
            nm.cancel(id);
        }

        private synchronized void notifyAuthCancelled() {
            PbapStateMachine.this.mObexAuth.setCancelled(true);
        }

        private synchronized void notifyAuthKeyInput(String key) {
            if (key != null) {
                PbapStateMachine.this.mObexAuth.setSessionKey(key);
                PbapStateMachine.this.mObexAuth.setChallenged(true);
            } else {
                PbapStateMachine.this.mObexAuth.setChallenged(true);
            }
        }
    }

    synchronized int getConnectionState() {
        PbapStateBase state = getCurrentState();
        if (state == null) {
            return 0;
        }
        return state.getConnectionStateInt();
    }

    protected void log(String msg) {
        super.log(msg);
    }
}
