package androidx.test.internal.runner;

import android.app.Instrumentation;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import androidx.test.internal.util.Checks;
import androidx.test.internal.util.LogUtil;
import androidx.test.internal.util.ParcelableIBinder;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.MonitoringInstrumentation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public class InstrumentationConnection {
    public static final String BROADCAST_FILTER = "androidx.test.runner.InstrumentationConnection.event";
    static final String BUNDLE_BR_NEW_BINDER = "new_instrumentation_binder";
    private static final String BUNDLE_KEY_CLIENTS = "instr_clients";
    private static final String BUNDLE_KEY_CLIENT_MESSENGER = "instr_client_msgr";
    private static final String BUNDLE_KEY_CLIENT_TYPE = "instr_client_type";
    private static final String BUNDLE_KEY_UUID = "instr_uuid";
    private static final InstrumentationConnection DEFAULT_INSTANCE = new InstrumentationConnection(InstrumentationRegistry.getInstrumentation().getTargetContext());
    static final int MSG_ADD_CLIENTS_IN_BUNDLE = 6;
    static final int MSG_ADD_INSTRUMENTATION = 4;
    private static final int MSG_HANDLE_INSTRUMENTATION_FROM_BROADCAST = 3;
    private static final int MSG_PERFORM_CLEANUP = 11;
    private static final int MSG_PERFORM_CLEANUP_FINISHED = 12;
    private static final int MSG_REG_CLIENT = 8;
    private static final int MSG_REMOTE_ADD_CLIENT = 0;
    static final int MSG_REMOTE_CLEANUP_REQUEST = 10;
    private static final int MSG_REMOTE_REMOVE_CLIENT = 1;
    private static final int MSG_REMOVE_CLIENTS_IN_BUNDLE = 7;
    private static final int MSG_REMOVE_INSTRUMENTATION = 5;
    private static final int MSG_TERMINATE = 2;
    private static final int MSG_UN_REG_CLIENT = 9;
    private static final String TAG = "InstrConnection";
    private static MonitoringInstrumentation.ActivityFinisher activityFinisher;
    private static Instrumentation instrumentation;
    IncomingHandler incomingHandler;
    final BroadcastReceiver messengerReceiver = new MessengerReceiver();
    private Context targetContext;

    InstrumentationConnection(Context context) {
        this.targetContext = (Context) Checks.checkNotNull(context, "Context can't be null");
    }

    public static InstrumentationConnection getInstance() {
        return DEFAULT_INSTANCE;
    }

    public synchronized void init(Instrumentation instrumentation2, MonitoringInstrumentation.ActivityFinisher finisher) {
        LogUtil.logDebugWithProcess(TAG, "init", new Object[0]);
        if (this.incomingHandler == null) {
            instrumentation = instrumentation2;
            activityFinisher = finisher;
            HandlerThread ht = new HandlerThread("InstrumentationConnectionThread");
            ht.start();
            this.incomingHandler = new IncomingHandler(ht.getLooper());
            Intent intent = new Intent(BROADCAST_FILTER);
            Bundle bundle = new Bundle();
            bundle.putParcelable(BUNDLE_BR_NEW_BINDER, new ParcelableIBinder(this.incomingHandler.messengerHandler.getBinder()));
            intent.putExtra(BUNDLE_BR_NEW_BINDER, bundle);
            try {
                this.targetContext.sendBroadcast(intent);
                this.targetContext.registerReceiver(this.messengerReceiver, new IntentFilter(BROADCAST_FILTER));
            } catch (SecurityException e) {
                Log.i(TAG, "Could not send broadcast or register receiver (isolatedProcess?)");
            }
        }
    }

    public synchronized void terminate() {
        LogUtil.logDebugWithProcess(TAG, "Terminate is called", new Object[0]);
        if (this.incomingHandler != null) {
            this.incomingHandler.runSyncTask(new Callable<Void>() { // from class: androidx.test.internal.runner.InstrumentationConnection.1
                @Override // java.util.concurrent.Callable
                public Void call() {
                    InstrumentationConnection.this.incomingHandler.doDie();
                    return null;
                }
            });
            this.targetContext.unregisterReceiver(this.messengerReceiver);
            this.incomingHandler = null;
        }
    }

    public synchronized void requestRemoteInstancesActivityCleanup() {
        Checks.checkState(this.incomingHandler != null, "Instrumentation Connection in not yet initialized");
        UUID uuid = UUID.randomUUID();
        CountDownLatch latch = new CountDownLatch(1);
        this.incomingHandler.associateLatch(uuid, latch);
        Message msg = this.incomingHandler.obtainMessage(10);
        msg.replyTo = this.incomingHandler.messengerHandler;
        Bundle bundle = msg.getData();
        bundle.putSerializable(BUNDLE_KEY_UUID, uuid);
        msg.setData(bundle);
        this.incomingHandler.sendMessage(msg);
        try {
            try {
                if (!latch.await(2L, TimeUnit.SECONDS)) {
                    try {
                        String strValueOf = String.valueOf(uuid);
                        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 60);
                        sb.append("Timed out while attempting to perform activity clean up for ");
                        sb.append(strValueOf);
                        Log.w(TAG, sb.toString());
                    } catch (InterruptedException e) {
                        e = e;
                        String strValueOf2 = String.valueOf(uuid);
                        StringBuilder sb2 = new StringBuilder(String.valueOf(strValueOf2).length() + 61);
                        sb2.append("Interrupted while waiting for response from message with id: ");
                        sb2.append(strValueOf2);
                        Log.e(TAG, sb2.toString(), e);
                        this.incomingHandler.disassociateLatch(uuid);
                    } catch (Throwable th) {
                        th = th;
                        this.incomingHandler.disassociateLatch(uuid);
                        throw th;
                    }
                }
                this.incomingHandler.disassociateLatch(uuid);
            } catch (InterruptedException e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public synchronized void registerClient(String type, Messenger messenger) {
        Checks.checkState(this.incomingHandler != null, "Instrumentation Connection in not yet initialized");
        String strValueOf = String.valueOf(type);
        Log.i(TAG, strValueOf.length() != 0 ? "Register client of type: ".concat(strValueOf) : new String("Register client of type: "));
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_CLIENT_TYPE, type);
        bundle.putParcelable(BUNDLE_KEY_CLIENT_MESSENGER, messenger);
        Message msg = this.incomingHandler.obtainMessage(8);
        msg.setData(bundle);
        this.incomingHandler.sendMessage(msg);
    }

    public synchronized Set<Messenger> getClientsForType(final String type) {
        return this.incomingHandler.getClientsForType(type);
    }

    public synchronized void unregisterClient(String type, Messenger messenger) {
        Checks.checkState(this.incomingHandler != null, "Instrumentation Connection in not yet initialized");
        String strValueOf = String.valueOf(type);
        Log.i(TAG, strValueOf.length() != 0 ? "Unregister client of type: ".concat(strValueOf) : new String("Unregister client of type: "));
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_CLIENT_TYPE, type);
        bundle.putParcelable(BUNDLE_KEY_CLIENT_MESSENGER, messenger);
        Message msg = this.incomingHandler.obtainMessage(9);
        msg.setData(bundle);
        this.incomingHandler.sendMessage(msg);
    }

    class MessengerReceiver extends BroadcastReceiver {
        MessengerReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "Broadcast received", new Object[0]);
            Bundle extras = intent.getBundleExtra(InstrumentationConnection.BUNDLE_BR_NEW_BINDER);
            if (extras == null) {
                Log.w(InstrumentationConnection.TAG, "Broadcast intent doesn't contain any extras, ignoring it..");
                return;
            }
            ParcelableIBinder iBinder = (ParcelableIBinder) extras.getParcelable(InstrumentationConnection.BUNDLE_BR_NEW_BINDER);
            if (iBinder != null) {
                Messenger msgr = new Messenger(iBinder.getIBinder());
                Message msg = InstrumentationConnection.this.incomingHandler.obtainMessage(3);
                msg.replyTo = msgr;
                InstrumentationConnection.this.incomingHandler.sendMessage(msg);
            }
        }
    }

    static class IncomingHandler extends Handler {
        private final Map<UUID, CountDownLatch> latches;
        Messenger messengerHandler;
        Set<Messenger> otherInstrumentations;
        Map<String, Set<Messenger>> typedClients;

        public IncomingHandler(Looper looper) {
            super(looper);
            this.messengerHandler = new Messenger(this);
            this.otherInstrumentations = new HashSet();
            this.typedClients = new HashMap();
            this.latches = new HashMap();
            if (Looper.getMainLooper() == looper || Looper.myLooper() == looper) {
                throw new IllegalStateException("This handler should not be using the main thread looper nor the instrumentation thread looper.");
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "handleMessage(MSG_REMOTE_ADD_CLIENT)", new Object[0]);
                    registerClient(msg.getData().getString(InstrumentationConnection.BUNDLE_KEY_CLIENT_TYPE), (Messenger) msg.getData().getParcelable(InstrumentationConnection.BUNDLE_KEY_CLIENT_MESSENGER));
                    break;
                case 1:
                    LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "handleMessage(MSG_REMOTE_REMOVE_CLIENT)", new Object[0]);
                    unregisterClient(msg.getData().getString(InstrumentationConnection.BUNDLE_KEY_CLIENT_TYPE), msg.replyTo);
                    break;
                case 2:
                    LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "handleMessage(MSG_TERMINATE)", new Object[0]);
                    doDie();
                    break;
                case 3:
                    LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "handleMessage(MSG_HANDLE_INSTRUMENTATION_FROM_BROADCAST)", new Object[0]);
                    if (this.otherInstrumentations.add(msg.replyTo)) {
                        sendMessageWithReply(msg.replyTo, 4, null);
                    } else {
                        Log.w(InstrumentationConnection.TAG, "Broadcast with existing binder was received, ignoring it..");
                    }
                    break;
                case 4:
                    LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "handleMessage(MSG_ADD_INSTRUMENTATION)", new Object[0]);
                    if (this.otherInstrumentations.add(msg.replyTo)) {
                        if (!this.typedClients.isEmpty()) {
                            sendMessageWithReply(msg.replyTo, 6, null);
                        }
                        clientsRegistrationFromBundle(msg.getData(), true);
                    } else {
                        Log.w(InstrumentationConnection.TAG, "Message with existing binder was received, ignoring it..");
                    }
                    break;
                case 5:
                    LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "handleMessage(MSG_REMOVE_INSTRUMENTATION)", new Object[0]);
                    if (!this.otherInstrumentations.remove(msg.replyTo)) {
                        Log.w(InstrumentationConnection.TAG, "Attempting to remove a non-existent binder!");
                    }
                    break;
                case 6:
                    LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "handleMessage(MSG_ADD_CLIENTS_IN_BUNDLE)", new Object[0]);
                    clientsRegistrationFromBundle(msg.getData(), true);
                    break;
                case 7:
                    LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "handleMessage(MSG_REMOVE_CLIENTS_IN_BUNDLE)", new Object[0]);
                    clientsRegistrationFromBundle(msg.getData(), false);
                    break;
                case 8:
                    LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "handleMessage(MSG_REG_CLIENT)", new Object[0]);
                    registerClient(msg.getData().getString(InstrumentationConnection.BUNDLE_KEY_CLIENT_TYPE), (Messenger) msg.getData().getParcelable(InstrumentationConnection.BUNDLE_KEY_CLIENT_MESSENGER));
                    sendMessageToOtherInstr(0, msg.getData());
                    break;
                case 9:
                    LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "handleMessage(MSG_UN_REG_CLIENT)", new Object[0]);
                    unregisterClient(msg.getData().getString(InstrumentationConnection.BUNDLE_KEY_CLIENT_TYPE), (Messenger) msg.getData().getParcelable(InstrumentationConnection.BUNDLE_KEY_CLIENT_MESSENGER));
                    sendMessageToOtherInstr(1, msg.getData());
                    break;
                case 10:
                    LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "handleMessage(MSG_REMOTE_CLEANUP_REQUEST)", new Object[0]);
                    if (this.otherInstrumentations.isEmpty()) {
                        Message m = obtainMessage(12);
                        m.setData(msg.getData());
                        sendMessage(m);
                    } else {
                        sendMessageToOtherInstr(11, msg.getData());
                    }
                    break;
                case 11:
                    LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "handleMessage(MSG_PERFORM_CLEANUP)", new Object[0]);
                    InstrumentationConnection.instrumentation.runOnMainSync(InstrumentationConnection.activityFinisher);
                    sendMessageWithReply(msg.replyTo, 12, msg.getData());
                    break;
                case 12:
                    LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "handleMessage(MSG_PERFORM_CLEANUP_FINISHED)", new Object[0]);
                    notifyLatch((UUID) msg.getData().getSerializable(InstrumentationConnection.BUNDLE_KEY_UUID));
                    break;
                default:
                    int i = msg.what;
                    StringBuilder sb = new StringBuilder(42);
                    sb.append("Unknown message code received: ");
                    sb.append(i);
                    Log.w(InstrumentationConnection.TAG, sb.toString());
                    super.handleMessage(msg);
                    break;
            }
        }

        private void notifyLatch(UUID uuid) {
            if (uuid != null && this.latches.containsKey(uuid)) {
                this.latches.get(uuid).countDown();
                return;
            }
            String strValueOf = String.valueOf(uuid);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 16);
            sb.append("Latch not found ");
            sb.append(strValueOf);
            Log.w(InstrumentationConnection.TAG, sb.toString());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void associateLatch(final UUID latchId, final CountDownLatch latch) {
            runSyncTask(new Callable<Void>() { // from class: androidx.test.internal.runner.InstrumentationConnection.IncomingHandler.1
                @Override // java.util.concurrent.Callable
                public Void call() {
                    IncomingHandler.this.latches.put(latchId, latch);
                    return null;
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void disassociateLatch(final UUID latchId) {
            runSyncTask(new Callable<Void>() { // from class: androidx.test.internal.runner.InstrumentationConnection.IncomingHandler.2
                @Override // java.util.concurrent.Callable
                public Void call() {
                    IncomingHandler.this.latches.remove(latchId);
                    return null;
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public <T> T runSyncTask(Callable<T> task) {
            FutureTask<T> futureTask = new FutureTask<>(task);
            post(futureTask);
            try {
                return futureTask.get();
            } catch (InterruptedException e) {
                throw new IllegalStateException(e.getCause());
            } catch (ExecutionException e2) {
                throw new IllegalStateException(e2.getCause());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void doDie() {
            Log.i(InstrumentationConnection.TAG, "terminating process");
            sendMessageToOtherInstr(5, null);
            this.otherInstrumentations.clear();
            this.typedClients.clear();
            LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "quitting looper...", new Object[0]);
            getLooper().quit();
            LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "finishing instrumentation...", new Object[0]);
            InstrumentationConnection.instrumentation.finish(0, null);
            Instrumentation unused = InstrumentationConnection.instrumentation = null;
            MonitoringInstrumentation.ActivityFinisher unused2 = InstrumentationConnection.activityFinisher = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Set<Messenger> getClientsForType(final String type) {
            FutureTask<Set<Messenger>> associationTask = new FutureTask<>(new Callable<Set<Messenger>>() { // from class: androidx.test.internal.runner.InstrumentationConnection.IncomingHandler.3
                @Override // java.util.concurrent.Callable
                public Set<Messenger> call() {
                    return IncomingHandler.this.typedClients.get(type);
                }
            });
            post(associationTask);
            try {
                return associationTask.get();
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            } catch (ExecutionException e2) {
                throw new IllegalStateException(e2.getCause());
            }
        }

        private void sendMessageWithReply(Messenger toMessenger, int what, Bundle data) {
            StringBuilder sb = new StringBuilder(45);
            sb.append("sendMessageWithReply type: ");
            sb.append(what);
            sb.append(" called");
            LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, sb.toString(), new Object[0]);
            Message msg = obtainMessage(what);
            msg.replyTo = this.messengerHandler;
            if (data != null) {
                msg.setData(data);
            }
            if (!this.typedClients.isEmpty()) {
                Bundle clientsBundle = msg.getData();
                ArrayList<String> keyList = new ArrayList<>(this.typedClients.keySet());
                clientsBundle.putStringArrayList(InstrumentationConnection.BUNDLE_KEY_CLIENTS, keyList);
                for (Map.Entry<String, Set<Messenger>> entry : this.typedClients.entrySet()) {
                    String clientType = String.valueOf(entry.getKey());
                    Messenger[] clientArray = (Messenger[]) entry.getValue().toArray(new Messenger[entry.getValue().size()]);
                    clientsBundle.putParcelableArray(clientType, clientArray);
                }
                msg.setData(clientsBundle);
            }
            try {
                toMessenger.send(msg);
            } catch (RemoteException e) {
                Log.w(InstrumentationConnection.TAG, "The remote process is terminated unexpectedly", e);
                instrBinderDied(toMessenger);
            }
        }

        private void sendMessageToOtherInstr(int what, Bundle data) {
            LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "sendMessageToOtherInstr() called with: what = [%s], data = [%s]", Integer.valueOf(what), data);
            for (Messenger otherInstr : this.otherInstrumentations) {
                sendMessageWithReply(otherInstr, what, data);
            }
        }

        private void clientsRegistrationFromBundle(Bundle clientsBundle, boolean shouldRegister) {
            LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "clientsRegistrationFromBundle called", new Object[0]);
            if (clientsBundle == null) {
                Log.w(InstrumentationConnection.TAG, "The client bundle is null, ignoring...");
                return;
            }
            ArrayList<String> clientTypes = clientsBundle.getStringArrayList(InstrumentationConnection.BUNDLE_KEY_CLIENTS);
            if (clientTypes == null) {
                Log.w(InstrumentationConnection.TAG, "No clients found in the given bundle");
                return;
            }
            for (String type : clientTypes) {
                Parcelable[] clientArray = clientsBundle.getParcelableArray(String.valueOf(type));
                if (clientArray != null) {
                    for (Parcelable client : clientArray) {
                        if (shouldRegister) {
                            registerClient(type, (Messenger) client);
                        } else {
                            unregisterClient(type, (Messenger) client);
                        }
                    }
                }
            }
        }

        private void registerClient(String type, Messenger client) {
            LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "registerClient called with type = [%s] client = [%s]", type, client);
            Checks.checkNotNull(type, "type cannot be null!");
            Checks.checkNotNull(client, "client cannot be null!");
            Set<Messenger> clientSet = this.typedClients.get(type);
            if (clientSet == null) {
                Set<Messenger> clientSet2 = new HashSet<>();
                clientSet2.add(client);
                this.typedClients.put(type, clientSet2);
                return;
            }
            clientSet.add(client);
        }

        private void unregisterClient(String type, Messenger client) {
            LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "unregisterClient called with type = [%s] client = [%s]", type, client);
            Checks.checkNotNull(type, "type cannot be null!");
            Checks.checkNotNull(client, "client cannot be null!");
            if (!this.typedClients.containsKey(type)) {
                String strValueOf = String.valueOf(type);
                Log.w(InstrumentationConnection.TAG, strValueOf.length() != 0 ? "There are no registered clients for type: ".concat(strValueOf) : new String("There are no registered clients for type: "));
                return;
            }
            Set<Messenger> clientSet = this.typedClients.get(type);
            if (!clientSet.contains(client)) {
                StringBuilder sb = new StringBuilder(String.valueOf(type).length() + 78);
                sb.append("Could not unregister client for type ");
                sb.append(type);
                sb.append(" because it doesn't seem to be registered");
                Log.w(InstrumentationConnection.TAG, sb.toString());
                return;
            }
            clientSet.remove(client);
            if (clientSet.isEmpty()) {
                this.typedClients.remove(type);
            }
        }

        private void instrBinderDied(Messenger instrMessenger) {
            Message msg = obtainMessage(5);
            msg.replyTo = instrMessenger;
            sendMessage(msg);
        }
    }
}
