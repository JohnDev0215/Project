package com.engine.tbox.emulator;

import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.engine.tbox.core.conn.AbstractConnection;
import com.engine.tbox.core.conn.IResponse;
import com.engine.tbox.core.data.CountSyncMessage;
import com.engine.tbox.core.net.IEventHandler;
import com.engine.tbox.core.net.SocketStateMachine;
import com.engine.tbox.core.utils.LogUtils;
import com.engine.tbox.data.ReservationCharging;
import com.engine.tbox.item.MessageUtils;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class TboxServerConnection extends AbstractConnection {
    private static final int ACTIVE_LEN = 7;
    private static final String LOCAL_IP = "127.0.0.1";
    private static final int MAX_OP_LIST = 255;
    private static final int PORT = 28889;
    private static final long SEC = 1000;
    private static final String TAG = "TboxServerConnection";
    private static final long TEN_SEC = 10000;
    private static final long TIMEOUT = 500000;
    private final Handler connectionHandler;
    private SocketStateMachine mMachine;
    private Thread mServieNtyMsgThread;
    private final ServerRecvHandler recvHandler;
    private final ServerSendHandler sendHandler;
    private final ArrayList<SocketChannel> mChannels = new ArrayList<>();
    private final HandlerThread connectionHandlerThread = new HandlerThread("connection Handler");
    private final HandlerThread sendHandlerThread = new HandlerThread("ConnectionSendThread");
    private final HandlerThread recvHandlerThread = new HandlerThread("ConnectionRecvThread");

    public TboxServerConnection() {
        this.connectionHandlerThread.start();
        this.sendHandlerThread.start();
        this.recvHandlerThread.start();
        this.connectionHandler = new Handler(this.connectionHandlerThread.getLooper());
        this.sendHandler = new ServerSendHandler(this.sendHandlerThread.getLooper());
        this.recvHandler = new ServerRecvHandler(this.recvHandlerThread.getLooper());
        addHandler(this.connectionHandler);
        this.sendHandler.addConnection(this);
        this.recvHandler.addConnection(this);
        SocketStateMachine socketStateMachine = new SocketStateMachine("TboxServerMachine", new SocketStateMachine.SocketInterface() { // from class: com.engine.tbox.emulator.TboxServerConnection.1
            @Override // com.engine.tbox.core.net.SocketStateMachine.SocketInterface
            public boolean createSocket(Selector selector) {
                try {
                    InetSocketAddress address = new InetSocketAddress(TboxServerConnection.LOCAL_IP, TboxServerConnection.PORT);
                    ServerSocketChannel channel = ServerSocketChannel.open();
                    LogUtils.d(TboxServerConnection.TAG, "Server " + channel.hashCode() + " have been created", new Object[0]);
                    if (Build.VERSION.SDK_INT >= 24) {
                        channel.bind((SocketAddress) address);
                    } else {
                        channel.socket().bind(address);
                    }
                    channel.configureBlocking(false);
                    channel.register(selector, 16);
                    return true;
                } catch (IOException exception) {
                    exception.printStackTrace();
                    return false;
                }
            }

            @Override // com.engine.tbox.core.net.SocketStateMachine.SocketInterface
            public void destroySocket(Selector selector) {
            }
        });
        this.mMachine = socketStateMachine;
        socketStateMachine.registerHandler(new IEventHandler() { // from class: com.engine.tbox.emulator.TboxServerConnection.2
            @Override // com.engine.tbox.core.net.IEventHandler
            public boolean onRead(SocketChannel channel, ByteBuffer buffer) {
                LogUtils.d(TboxServerConnection.TAG, "server on Read:" + buffer, new Object[0]);
                Message msg = TboxServerConnection.this.recvHandler.obtainMessage();
                msg.what = 1;
                msg.obj = buffer;
                TboxServerConnection.this.recvHandler.sendMessage(msg);
                return true;
            }

            @Override // com.engine.tbox.core.net.IEventHandler
            public boolean onNewConnection(SocketChannel channel) {
                LogUtils.d(TboxServerConnection.TAG, "server add channel:" + channel.hashCode(), new Object[0]);
                synchronized (this) {
                    TboxServerConnection.this.mChannels.add(channel);
                }
                TboxServerConnection.this.mServieNtyMsgThread = new Thread() { // from class: com.engine.tbox.emulator.TboxServerConnection.2.1
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        while (true) {
                            TboxServerConnection.this.sendServeNetTrafficReq();
                            TboxServerConnection.this.sleepThread(TboxServerConnection.SEC);
                            TboxServerConnection.this.sendSnReq();
                            TboxServerConnection.this.sleepThread(TboxServerConnection.SEC);
                            TboxServerConnection.this.sendVersionReq();
                            TboxServerConnection.this.sleepThread(TboxServerConnection.SEC);
                            TboxServerConnection.this.sendOtaNty();
                            TboxServerConnection.this.sleepThread(TboxServerConnection.SEC);
                            TboxServerConnection.this.sendChargePlan();
                            TboxServerConnection.this.sleepThread(TboxServerConnection.SEC);
                            TboxServerConnection.this.sendChargeConn();
                            TboxServerConnection.this.sleepThread(TboxServerConnection.SEC);
                            TboxServerConnection.this.sendOpList(255);
                            TboxServerConnection.this.sleepThread(TboxServerConnection.SEC);
                            TboxServerConnection.this.sendOpList(1);
                            TboxServerConnection.this.sleepThread(TboxServerConnection.SEC);
                            TboxServerConnection.this.sendOpList(2);
                            TboxServerConnection.this.sleepThread(TboxServerConnection.SEC);
                            TboxServerConnection.this.sendOpList(0);
                            TboxServerConnection.this.sleepThread(TboxServerConnection.SEC);
                            TboxServerConnection.this.sendConfig();
                            TboxServerConnection.this.sleepThread(TboxServerConnection.SEC);
                            TboxServerConnection.this.sendConfig();
                            TboxServerConnection.this.sleepThread(TboxServerConnection.TEN_SEC);
                        }
                    }
                };
                TboxServerConnection.this.mServieNtyMsgThread.start();
                return super.onNewConnection(channel);
            }

            @Override // com.engine.tbox.core.net.IEventHandler
            public boolean onConnectFailed(SocketChannel channel) {
                return super.onConnectFailed(channel);
            }

            @Override // com.engine.tbox.core.net.IEventHandler
            public boolean onSocketError(SocketChannel channel) {
                return super.onSocketError(channel);
            }

            @Override // com.engine.tbox.core.net.IEventHandler
            public boolean onExit() {
                return super.onExit();
            }
        });
        this.recvHandler.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sleepThread(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException exp) {
            exp.printStackTrace();
        }
    }

    private Message msgToSendHandlerMessage(CountSyncMessage msg) {
        Message message = this.sendHandler.obtainMessage(msg.identify());
        message.obj = msg;
        return message;
    }

    @Override // com.engine.tbox.core.conn.AbstractConnection, com.engine.tbox.core.conn.IConnection
    public boolean sendAsyncMessage(CountSyncMessage msg) {
        Message message = msgToSendHandlerMessage(msg);
        return this.sendHandler.sendMessage(message);
    }

    @Override // com.engine.tbox.core.conn.AbstractConnection, com.engine.tbox.core.conn.IConnection
    public boolean sendSyncMessage(CountSyncMessage msg, IResponse response) {
        Message message = msgToSendHandlerMessage(msg);
        this.sendHandler.sendMessage(message);
        boolean ret = msg.waitForSignal();
        if (ret) {
            CountSyncMessage respone = msg.getRespone();
            response.onResponse(respone);
        } else {
            response.onTimeout(msg);
        }
        return ret;
    }

    @Override // com.engine.tbox.core.conn.AbstractConnection, com.engine.tbox.core.conn.IConnection
    public boolean sendByteBuffer(ByteBuffer buff) {
        ArrayList<SocketChannel> copyChannels;
        ArrayList<SocketChannel> removeChannel = new ArrayList<>();
        synchronized (this) {
            copyChannels = new ArrayList<>(this.mChannels);
        }
        LogUtils.d(TAG, "channel list:" + copyChannels.size(), new Object[0]);
        for (SocketChannel channel : copyChannels) {
            try {
                channel.write(buff);
            } catch (IOException e) {
                removeChannel.add(channel);
                e.printStackTrace();
            }
        }
        synchronized (this) {
            this.mChannels.removeAll(removeChannel);
        }
        return true;
    }

    @Override // com.engine.tbox.core.conn.AbstractConnection, com.engine.tbox.core.conn.IConnection
    public boolean start() {
        this.mMachine.start();
        this.mMachine.turnOn();
        return true;
    }

    @Override // com.engine.tbox.core.conn.AbstractConnection, com.engine.tbox.core.conn.IConnection
    public boolean stop() {
        this.mMachine.turnOff();
        this.mMachine.quitNow();
        return true;
    }

    public void sendSnReq() {
        LogUtils.d(TAG, "sendSnReq", new Object[0]);
        sendAsyncMessage(MessageUtils.buildServerSnReq());
    }

    public void sendVersionReq() {
        LogUtils.d(TAG, "sendVersionReq", new Object[0]);
        sendAsyncMessage(MessageUtils.buildServerVersionReq());
    }

    public void sendServeNetTrafficReq() {
        LogUtils.d(TAG, "sendServeNetTrafficReq", new Object[0]);
        byte[] data = {1};
        sendAsyncMessage(MessageUtils.buildServerNetworkTrafficReq(data));
    }

    public void sendVirtualVinReq(byte[] data) {
        LogUtils.d(TAG, "sendVirtualVinReq", new Object[0]);
        sendAsyncMessage(MessageUtils.buildServeGetVirtualVinReq(data));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendOtaNty() {
        LogUtils.d(TAG, "sendOtaNty", new Object[0]);
        byte[] data = new byte[7];
        int pos = 0 + 1;
        data[0] = 1;
        int i = 1;
        while (i < 7) {
            data[pos] = (byte) i;
            i++;
            pos++;
        }
        sendAsyncMessage(MessageUtils.buildOtaInfo(data));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendConfig() {
        LogUtils.d(TAG, "sendConfig", new Object[0]);
        sendAsyncMessage(MessageUtils.buildServerNationReqMessage());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendChargePlan() {
        LogUtils.d(TAG, "sendChargePlan", new Object[0]);
        ReservationCharging.RcTime startTime = new ReservationCharging.RcTime();
        startTime.setMin(1);
        startTime.setHour(1);
        ReservationCharging.RcTime endTime = new ReservationCharging.RcTime();
        endTime.setMin(2);
        endTime.setHour(2);
        ReservationCharging.RcTime outTime = new ReservationCharging.RcTime();
        outTime.setMin(2);
        outTime.setHour(2);
        ReservationCharging.RcTime discountStartTime = new ReservationCharging.RcTime();
        discountStartTime.setHour(1);
        discountStartTime.setMin(1);
        ReservationCharging.RcTime discountEndTime = new ReservationCharging.RcTime();
        discountEndTime.setMin(2);
        discountEndTime.setHour(2);
        ReservationCharging rc = new ReservationCharging();
        rc.setRsvOutSwitch((byte) 1);
        rc.setRsvTime(startTime, endTime);
        rc.setRsvOutTime(outTime);
        rc.setPreferentialTariffTimeSlot(startTime, endTime);
        rc.setTimeZone("ASIA");
        sendAsyncMessage(MessageUtils.buildChargePlaneDown(ReservationCharging.toByte(rc)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendOpList(int n) {
        byte[] bytes = MessageUtils.prepareServerOpListData(n);
        LogUtils.d(TAG, "sendOpList bytes:" + bytes.length, new Object[0]);
        sendAsyncMessage(MessageUtils.buildTBoxServerOperationList(bytes));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendChargeConn() {
        LogUtils.d(TAG, "sendChargeConn", new Object[0]);
        byte[] data = {0};
        sendAsyncMessage(MessageUtils.buildUserChargePlaneDown(data));
        sleepThread(SEC);
        data[0] = 1;
        sendAsyncMessage(MessageUtils.buildUserChargePlaneDown(data));
        sleepThread(SEC);
        data[0] = 2;
        sendAsyncMessage(MessageUtils.buildUserChargePlaneDown(data));
    }
}
