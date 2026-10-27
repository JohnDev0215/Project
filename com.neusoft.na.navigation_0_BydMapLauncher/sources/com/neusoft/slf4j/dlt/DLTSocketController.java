package com.neusoft.slf4j.dlt;

import android.util.Log;
import com.neusoft.slf4j.dlt.SocketController;
import rx.functions.Action1;

/* JADX INFO: loaded from: classes.dex */
public final class DLTSocketController {
    private static final String LOGTAG = "DLTSocketController";
    private static DLTSocketController instance = null;
    private static boolean isInitialized = false;
    private final DltLogger dltLogger;
    private final SocketController socketController;

    private DLTSocketController(String str, int i, String str2, String str3, String str4) {
        this.dltLogger = new DltLogger(str2, str3, str4);
        SocketController socketController = new SocketController(str, i);
        this.socketController = socketController;
        socketController.getConnectionStatus().subscribe(new Action1<SocketController.ConnectionStatus>() { // from class: com.neusoft.slf4j.dlt.DLTSocketController.1
            @Override // rx.functions.Action1
            public void call(SocketController.ConnectionStatus connectionStatus) {
                Log.i(DLTSocketController.LOGTAG, "SocketController: New connection status: " + connectionStatus);
            }
        });
        socketController.getErrorMessage().subscribe(new Action1<String>() { // from class: com.neusoft.slf4j.dlt.DLTSocketController.2
            @Override // rx.functions.Action1
            public void call(String str5) {
                Log.i(DLTSocketController.LOGTAG, "SocketController: Error: " + str5);
            }
        });
        socketController.start();
    }

    static DLTSocketController getInstance() {
        return instance;
    }

    @Deprecated
    public static synchronized void init(String str, int i) {
        init(str, i, "OCTA", "DAO ", "DAO ");
    }

    public static synchronized void init(String str, int i, String str2, String str3, String str4) {
        if (!isInitialized) {
            instance = new DLTSocketController(str, i, str2, str3, str4);
            isInitialized = true;
        }
    }

    SocketController getSocketController() {
        return this.socketController;
    }

    void log(String str, String str2, DltLogLevel dltLogLevel) {
        if (isInitialized) {
            this.socketController.send(this.dltLogger.makeMessage("" + str + ": " + str2, dltLogLevel));
        }
    }
}
