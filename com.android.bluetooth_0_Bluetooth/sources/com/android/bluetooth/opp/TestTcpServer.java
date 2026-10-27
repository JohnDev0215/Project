package com.android.bluetooth.opp;

import android.util.Log;
import com.android.bluetooth.BluetoothStatsLog;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.obex.HeaderSet;
import javax.obex.Operation;
import javax.obex.ServerRequestHandler;

/* JADX INFO: compiled from: TestActivity.java */
/* JADX INFO: loaded from: classes.dex */
class TestTcpServer extends ServerRequestHandler implements Runnable {
    static final int PORT = 6500;
    private static final String TAG = "ServerRequestHandler";
    private static final boolean V = false;
    public boolean a = false;

    @Override // java.lang.Runnable
    public void run() {
        try {
            updateStatus("[server:] listen on port 6500");
            TestTcpSessionNotifier rsn = new TestTcpSessionNotifier(PORT);
            updateStatus("[server:] Now waiting for a client to connect");
            rsn.acceptAndOpen(this);
            updateStatus("[server:] A client is now connected");
        } catch (Exception ex) {
            updateStatus("[server:] Caught the error: " + ex);
        }
    }

    TestTcpServer() {
        updateStatus("enter construtor of TcpServer");
    }

    public int onConnect(HeaderSet request, HeaderSet reply) {
        updateStatus("[server:] The client has created an OBEX session");
        synchronized (this) {
            while (!this.a) {
                try {
                    wait(500L);
                } catch (InterruptedException e) {
                }
            }
        }
        updateStatus("[server:] we accpet the seesion");
        return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
    }

    public int onPut(Operation op) {
        int len;
        FileOutputStream fos = null;
        try {
            InputStream is = op.openInputStream();
            updateStatus("Got data bytes " + is.available() + " name " + op.getReceivedHeader().getHeader(1) + " type " + op.getType());
            File f = new File((String) op.getReceivedHeader().getHeader(1));
            fos = new FileOutputStream(f);
            byte[] b = new byte[1000];
            while (is.available() > 0 && (len = is.read(b)) > 0) {
                fos.write(b, 0, len);
            }
            fos.close();
            is.close();
            updateStatus("[server:] Wrote data to " + f.getAbsolutePath());
            return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
        } catch (Exception e) {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
            return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
        }
    }

    public void onDisconnect(HeaderSet req, HeaderSet resp) {
        updateStatus("[server:] The client has disconnected the OBEX session");
    }

    public void updateStatus(String message) {
        Log.v(TAG, "\n" + message);
    }

    public void onAuthenticationFailure(byte[] userName) {
    }

    public int onSetPath(HeaderSet request, HeaderSet reply, boolean backup, boolean create) {
        return 209;
    }

    public int onDelete(HeaderSet request, HeaderSet reply) {
        return 209;
    }

    public int onGet(Operation op) {
        return 209;
    }
}
