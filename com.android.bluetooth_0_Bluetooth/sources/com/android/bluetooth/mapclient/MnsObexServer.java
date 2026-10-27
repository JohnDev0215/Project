package com.android.bluetooth.mapclient;

import android.util.Log;
import com.android.bluetooth.BluetoothStatsLog;
import com.android.bluetooth.ObexServerSockets;
import com.android.bluetooth.opp.BluetoothShare;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import javax.obex.HeaderSet;
import javax.obex.Operation;
import javax.obex.ServerRequestHandler;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
class MnsObexServer extends ServerRequestHandler {
    private static final byte[] MNS_TARGET = {-69, 88, 43, 65, 66, Ascii.FF, 17, -37, -80, -34, 8, 0, 32, Ascii.FF, -102, 102};
    private static final String TAG = "MnsObexServer";
    private static final String TYPE = "x-bt/MAP-event-report";
    private static final boolean VDBG = false;
    private final ObexServerSockets mObexServerSockets;
    private final WeakReference<MceStateMachine> mStateMachineReference;

    MnsObexServer(MceStateMachine stateMachine, ObexServerSockets socketOriginator) {
        this.mStateMachineReference = new WeakReference<>(stateMachine);
        this.mObexServerSockets = socketOriginator;
    }

    public int onConnect(HeaderSet request, HeaderSet reply) {
        try {
            byte[] uuid = (byte[]) request.getHeader(70);
            if (!Arrays.equals(uuid, MNS_TARGET)) {
                return 198;
            }
            reply.setHeader(74, MNS_TARGET);
            return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
        } catch (IOException e) {
            return 208;
        }
    }

    public void onDisconnect(HeaderSet request, HeaderSet reply) {
        MceStateMachine currentStateMachine = this.mStateMachineReference.get();
        if (currentStateMachine != null) {
            currentStateMachine.disconnect();
        }
    }

    public int onGet(Operation op) {
        return BluetoothShare.STATUS_RUNNING;
    }

    public int onPut(Operation op) throws XmlPullParserException {
        try {
            HeaderSet headerset = op.getReceivedHeader();
            String type = (String) headerset.getHeader(66);
            ObexAppParameters oap = ObexAppParameters.fromHeaderSet(headerset);
            if (TYPE.equals(type) && oap.exists(Ascii.SI)) {
                Byte.valueOf(oap.getByte(Ascii.SI));
                EventReport ev = EventReport.fromStream(op.openDataInputStream());
                op.close();
                MceStateMachine currentStateMachine = this.mStateMachineReference.get();
                if (currentStateMachine != null) {
                    currentStateMachine.receiveEvent(ev);
                    return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
                }
                return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
            }
            return BluetoothShare.STATUS_RUNNING;
        } catch (IOException e) {
            Log.e(TAG, "I/O exception when handling PUT request", e);
            return 208;
        }
    }

    public int onAbort(HeaderSet request, HeaderSet reply) {
        return 209;
    }

    public int onSetPath(HeaderSet request, HeaderSet reply, boolean backup, boolean create) {
        return BluetoothShare.STATUS_RUNNING;
    }

    public void onClose() {
    }
}
