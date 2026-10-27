package com.engine.tbox.core.net;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import com.engine.tbox.core.utils.LogUtils;
import com.engine.tbox.state.State;
import com.engine.tbox.state.StateMachine;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.NotYetConnectedException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class SocketStateMachine extends StateMachine {
    private static final int EXIT_TIMEOUT = 10;
    private static final long FIRST_RECONNECT_TIMEOUT = 200;
    private static final int FOUR_K_BUFFER = 4096;
    private static final long HALF_MIN = 30000;
    private static final int MAX_RECONNECT_COUNT = 6;
    private static final int MSG_CREATE_SOCKET = 3;
    private static final int MSG_LOOP = 4;
    private static final int MSG_RECONNECT = 9;
    private static final int MSG_SOCKET_ACCEPT = 7;
    private static final int MSG_SOCKET_CONNECTED = 5;
    private static final int MSG_SOCKET_ERROR = 8;
    private static final int MSG_SOCKET_READ = 6;
    private static final int MSG_STR_RECONNECT = 10;
    private static final int MSG_TURN_OFF = 2;
    private static final int MSG_TURN_ON = 1;
    private static final long SECOND = 1000;
    private static final long SELECT_LOOP_SLEEP_TIME = 50;
    private static final long SELECT_TIMEOUT = 1000;
    private static final String TAG = "SocketStateMachine";
    private static final long TEN_SEC = 10000;
    private DestroyState mDestroyState;
    private IEventHandler mHandler;
    private IdleState mIdleState;
    private SocketInterface mInterface;
    private LoopSocketState mLoopState;
    private PrepareSocketState mPrepareState;
    private int mReconnectCount;
    private ReconnectState mReconnectState;
    private Selector mSelector;
    private volatile boolean mStrReconnect;
    private StrReconnectState mStrReconnectState;
    private volatile boolean mWorking;

    public interface SocketInterface {
        boolean createSocket(Selector selector);

        void destroySocket(Selector selector);
    }

    static /* synthetic */ int access$1308(SocketStateMachine x0) {
        int i = x0.mReconnectCount;
        x0.mReconnectCount = i + 1;
        return i;
    }

    static class OnReadObj {
        ByteBuffer mBuffer;
        SocketChannel mChannel;

        OnReadObj() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String msgToString(int msg) {
        switch (msg) {
            case 1:
                return "turn on";
            case 2:
                return "turn off";
            case 3:
                return "create socket";
            case 4:
                return "msg loop";
            case 5:
                return "socket connected";
            case 6:
                return "socket receive msg";
            case 7:
                return "socket accept";
            case 8:
                return "socket error";
            case 9:
                return "socket reconnect";
            case 10:
                return "str reconnect";
            default:
                return "invalid msg";
        }
    }

    public SocketStateMachine(String name, SocketInterface socketInterface) {
        super(name);
        this.mWorking = true;
        this.mReconnectCount = 0;
        this.mStrReconnect = false;
        this.mIdleState = new IdleState();
        this.mPrepareState = new PrepareSocketState();
        this.mLoopState = new LoopSocketState();
        this.mReconnectState = new ReconnectState();
        this.mStrReconnectState = new StrReconnectState();
        this.mDestroyState = new DestroyState();
        initSocketMachine(socketInterface);
    }

    protected SocketStateMachine(String name, Looper looper, SocketInterface socketInterface) {
        super(name, looper);
        this.mWorking = true;
        this.mReconnectCount = 0;
        this.mStrReconnect = false;
        this.mIdleState = new IdleState();
        this.mPrepareState = new PrepareSocketState();
        this.mLoopState = new LoopSocketState();
        this.mReconnectState = new ReconnectState();
        this.mStrReconnectState = new StrReconnectState();
        this.mDestroyState = new DestroyState();
        initSocketMachine(socketInterface);
    }

    protected SocketStateMachine(String name, Handler handler, SocketInterface socketInterface) {
        super(name, handler);
        this.mWorking = true;
        this.mReconnectCount = 0;
        this.mStrReconnect = false;
        this.mIdleState = new IdleState();
        this.mPrepareState = new PrepareSocketState();
        this.mLoopState = new LoopSocketState();
        this.mReconnectState = new ReconnectState();
        this.mStrReconnectState = new StrReconnectState();
        this.mDestroyState = new DestroyState();
        initSocketMachine(socketInterface);
    }

    private void initSocketMachine(SocketInterface socketInterface) {
        if (socketInterface == null) {
            LogUtils.e(TAG, getName() + " init socketInterface is null", new Object[0]);
        }
        this.mInterface = socketInterface;
        setDbg(true);
        setInitialState(this.mIdleState);
        addState(this.mPrepareState, this.mIdleState);
        addState(this.mLoopState, this.mIdleState);
        addState(this.mReconnectState, this.mIdleState);
        addState(this.mStrReconnectState, this.mIdleState);
        addState(this.mDestroyState, this.mIdleState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean initSelector() {
        try {
            this.mSelector = Selector.open();
            return true;
        } catch (IOException exp) {
            exp.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean closeSocket() {
        boolean result = true;
        Set<SelectionKey> keys = this.mSelector.keys();
        LogUtils.d(TAG, "closeSocket keys:" + keys, new Object[0]);
        for (SelectionKey key : keys) {
            LogUtils.d(TAG, "closeSocket key:" + key, new Object[0]);
            SelectableChannel channel = key.channel();
            key.cancel();
            try {
                LogUtils.d(TAG, "close channel:" + channel.hashCode(), new Object[0]);
                channel.close();
            } catch (IOException exp) {
                exp.printStackTrace();
                result = false;
            }
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean deInitSelector() {
        boolean result = closeSocket();
        LogUtils.i(TAG, getName() + " closeSocket:" + result, new Object[0]);
        try {
            this.mSelector.close();
            return result;
        } catch (IOException exp) {
            exp.printStackTrace();
            return false;
        }
    }

    public synchronized void registerHandler(IEventHandler handler) {
        this.mHandler = handler;
    }

    public synchronized void unregisterHandler() {
        this.mHandler = null;
    }

    public void turnOn() {
        LogUtils.i(TAG, "turnOn", new Object[0]);
        sendMessage(1);
    }

    public void turnOff() {
        LogUtils.i(TAG, "turnOff", new Object[0]);
        this.mWorking = false;
        this.mSelector.wakeup();
        sendMessageAtFrontOfQueue(2);
    }

    class IdleState extends State {
        IdleState() {
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public void enter() {
            super.enter();
            LogUtils.i(SocketStateMachine.TAG, getName() + " enter", new Object[0]);
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public boolean processMessage(Message msg) {
            LogUtils.i(SocketStateMachine.TAG, getName() + " processMessage msg:" + SocketStateMachine.this.msgToString(msg.what), new Object[0]);
            if (msg.what == 1) {
                SocketStateMachine socketStateMachine = SocketStateMachine.this;
                socketStateMachine.transitionTo(socketStateMachine.mPrepareState);
            } else if (msg.what == 2) {
                SocketStateMachine socketStateMachine2 = SocketStateMachine.this;
                socketStateMachine2.transitionTo(socketStateMachine2.mDestroyState);
            } else if (msg.what == 10) {
                SocketStateMachine socketStateMachine3 = SocketStateMachine.this;
                socketStateMachine3.transitionTo(socketStateMachine3.mStrReconnectState);
            }
            return true;
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public void exit() {
            super.exit();
            LogUtils.i(SocketStateMachine.TAG, getName() + " exit", new Object[0]);
        }
    }

    class PrepareSocketState extends State {
        PrepareSocketState() {
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public void enter() {
            super.enter();
            LogUtils.i(SocketStateMachine.TAG, getName() + " enter interface:" + SocketStateMachine.this.mInterface, new Object[0]);
            SocketStateMachine.this.sendMessage(3);
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public boolean processMessage(Message msg) {
            LogUtils.i(SocketStateMachine.TAG, getName() + " processMessage msg:" + SocketStateMachine.this.msgToString(msg.what), new Object[0]);
            int i = msg.what;
            if (i != 2) {
                if (i == 3 && SocketStateMachine.this.initSelector()) {
                    if (!SocketStateMachine.this.mInterface.createSocket(SocketStateMachine.this.mSelector)) {
                        SocketStateMachine.this.deInitSelector();
                        SocketStateMachine.this.sendMessage(3, (Object) 1000L);
                        return true;
                    }
                    SocketStateMachine socketStateMachine = SocketStateMachine.this;
                    socketStateMachine.transitionTo(socketStateMachine.mLoopState);
                    return true;
                }
                return true;
            }
            SocketStateMachine socketStateMachine2 = SocketStateMachine.this;
            socketStateMachine2.transitionTo(socketStateMachine2.mDestroyState);
            return true;
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public void exit() {
            super.exit();
            LogUtils.i(SocketStateMachine.TAG, getName() + " exit", new Object[0]);
        }
    }

    class LoopThread extends Thread {
        private boolean errorExit = false;
        private CountDownLatch mCountDown;

        LoopThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            int select;
            LogUtils.i(SocketStateMachine.TAG, "LoopThread start", new Object[0]);
            setName("LoopThread");
            this.mCountDown = new CountDownLatch(1);
            while (true) {
                if (!SocketStateMachine.this.mWorking) {
                    break;
                }
                try {
                    select = SocketStateMachine.this.mSelector.select(1000L);
                } catch (IOException | CancelledKeyException | NotYetConnectedException exp) {
                    exp.printStackTrace();
                }
                if (!SocketStateMachine.this.mWorking) {
                    LogUtils.w(SocketStateMachine.TAG, "close by other thread wakeup", new Object[0]);
                    break;
                }
                if (select == 0) {
                    sleepMillisecond(SocketStateMachine.SELECT_LOOP_SLEEP_TIME);
                } else {
                    this.errorExit = false;
                    Set<SelectionKey> selectionKeys = SocketStateMachine.this.mSelector.selectedKeys();
                    LogUtils.d(SocketStateMachine.TAG, "selector starting selectionKeys size:" + selectionKeys.size() + " process tid:" + Process.myTid(), new Object[0]);
                    Iterator<SelectionKey> it = selectionKeys.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        SelectionKey key = it.next();
                        it.remove();
                        LogUtils.d(SocketStateMachine.TAG, "keys:" + key + " acceptable:" + key.isAcceptable() + " connectable:" + key.isConnectable() + " readable:" + key.isReadable(), new Object[0]);
                        if (!key.isValid()) {
                            LogUtils.d(SocketStateMachine.TAG, "invalid key:" + key, new Object[0]);
                        } else if (key.isAcceptable()) {
                            handleAccept(key);
                        } else if (key.isConnectable() && !handleConnected(key)) {
                            LogUtils.d(SocketStateMachine.TAG, "handle Connect error", new Object[0]);
                            this.errorExit = true;
                            break;
                        } else if (key.isReadable() && !handleRead(key)) {
                            LogUtils.d(SocketStateMachine.TAG, "exit when socket error", new Object[0]);
                            this.errorExit = true;
                            break;
                        }
                    }
                    if (this.errorExit) {
                        LogUtils.d(SocketStateMachine.TAG, "exit the loop Thread when socket error", new Object[0]);
                        break;
                    }
                }
            }
            SocketStateMachine.this.closeSocket();
            this.mCountDown.countDown();
            LogUtils.i(SocketStateMachine.TAG, "LoopThread exit", new Object[0]);
        }

        private boolean handleAccept(SelectionKey key) throws IOException {
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            LogUtils.d(SocketStateMachine.TAG, server.hashCode() + " server accept", new Object[0]);
            SocketChannel channel = server.accept();
            if (channel == null) {
                LogUtils.e(SocketStateMachine.TAG, "channel is null " + server.hashCode(), new Object[0]);
                return true;
            }
            channel.configureBlocking(false);
            if (SocketStateMachine.this.mHandler != null) {
                LogUtils.d(SocketStateMachine.TAG, "new socket:" + channel.hashCode(), new Object[0]);
                SocketStateMachine.this.mHandler.onNewConnection(channel);
                LogUtils.d(SocketStateMachine.TAG, "on new socket connected", new Object[0]);
            }
            channel.register(SocketStateMachine.this.mSelector, 1);
            return false;
        }

        private boolean handleRead(SelectionKey key) throws IOException {
            int len;
            SelectableChannel channel = key.channel();
            LogUtils.d(SocketStateMachine.TAG, "on read:" + channel.hashCode(), new Object[0]);
            if (channel instanceof SocketChannel) {
                SocketChannel socketChannel = (SocketChannel) channel;
                if (!channel.isOpen()) {
                    LogUtils.e(SocketStateMachine.TAG, "socketChannel:" + channel.hashCode() + " is not open now", new Object[0]);
                    return true;
                }
                ByteBuffer readBuffer = ByteBuffer.allocate(SocketStateMachine.FOUR_K_BUFFER);
                while (true) {
                    len = socketChannel.read(readBuffer);
                    if (len <= 0) {
                        break;
                    }
                    readBuffer.flip();
                    LogUtils.d(SocketStateMachine.TAG, "read len:" + len + " buffer:" + readBuffer, new Object[0]);
                    OnReadObj obj = new OnReadObj();
                    obj.mChannel = socketChannel;
                    obj.mBuffer = readBuffer;
                    SocketStateMachine.this.sendMessage(6, obj);
                    readBuffer = ByteBuffer.allocate(SocketStateMachine.FOUR_K_BUFFER);
                }
                LogUtils.d(SocketStateMachine.TAG, "read success:" + len, new Object[0]);
                if (len == -1 && SocketStateMachine.this.mHandler != null) {
                    LogUtils.d(SocketStateMachine.TAG, "channel failed:" + channel.hashCode(), new Object[0]);
                    SocketStateMachine.this.sendMessage(8, channel);
                    return false;
                }
            }
            return true;
        }

        private boolean handleConnected(SelectionKey key) {
            LogUtils.d(SocketStateMachine.TAG, "channel " + key.channel().hashCode() + " connected", new Object[0]);
            SocketChannel channel = (SocketChannel) key.channel();
            boolean connected = true;
            if (channel.isConnectionPending()) {
                try {
                    connected = channel.finishConnect();
                    LogUtils.d(SocketStateMachine.TAG, "finishConnect:" + connected, new Object[0]);
                } catch (IOException exp) {
                    exp.printStackTrace();
                    connected = false;
                }
                if (connected) {
                    SocketStateMachine.this.sendMessage(5, channel);
                } else {
                    LogUtils.e(SocketStateMachine.TAG, "error connected notify:" + channel.hashCode(), new Object[0]);
                    SocketStateMachine.this.sendMessage(8, channel);
                }
            }
            return connected;
        }

        private void sleepMillisecond(long selectLoopSleepTime) {
            try {
                Thread.sleep(selectLoopSleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean waitForExit() {
            CountDownLatch countDownLatch = this.mCountDown;
            if (countDownLatch != null) {
                try {
                    return countDownLatch.await(10L, TimeUnit.SECONDS);
                } catch (InterruptedException exp) {
                    exp.printStackTrace();
                    return false;
                }
            }
            return true;
        }
    }

    class LoopSocketState extends State {
        private boolean mConnected;
        private LoopThread mLoop;

        LoopSocketState() {
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public void enter() {
            super.enter();
            LogUtils.i(SocketStateMachine.TAG, getName() + " enter", new Object[0]);
            SocketStateMachine.this.mWorking = true;
            this.mConnected = false;
            SocketStateMachine.this.sendMessage(4);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public boolean processMessage(Message msg) {
            LogUtils.i(SocketStateMachine.TAG, getName() + " processMessage msg:" + SocketStateMachine.this.msgToString(msg.what), new Object[0]);
            switch (msg.what) {
                case 2:
                    LogUtils.i(SocketStateMachine.TAG, getName() + " Wait for Msg turn Off", new Object[0]);
                    this.mLoop.waitForExit();
                    SocketStateMachine socketStateMachine = SocketStateMachine.this;
                    socketStateMachine.transitionTo(socketStateMachine.mDestroyState);
                    return true;
                case 3:
                case 9:
                default:
                    return true;
                case 4:
                    LoopThread loopThread = SocketStateMachine.this.new LoopThread();
                    this.mLoop = loopThread;
                    loopThread.start();
                    return true;
                case 5:
                    if (SocketStateMachine.this.mHandler != null) {
                        SocketStateMachine.this.mHandler.onConnectionSuccess((SocketChannel) msg.obj);
                        SocketStateMachine.this.mReconnectCount = 0;
                        this.mConnected = true;
                        SocketStateMachine.this.mStrReconnect = false;
                    }
                    return true;
                case 6:
                    OnReadObj obj = (OnReadObj) msg.obj;
                    if (SocketStateMachine.this.mHandler != null) {
                        SocketStateMachine.this.mHandler.onRead(obj.mChannel, obj.mBuffer);
                    }
                    return true;
                case 7:
                    if (SocketStateMachine.this.mHandler != null) {
                        SocketStateMachine.this.mHandler.onNewConnection((SocketChannel) msg.obj);
                    }
                    return true;
                case 8:
                    if (SocketStateMachine.this.mHandler != null) {
                        SocketStateMachine.this.mHandler.onConnectFailed((SocketChannel) msg.obj);
                    }
                    LogUtils.i(SocketStateMachine.TAG, "socket error and mStrReconnect:" + SocketStateMachine.this.mStrReconnect, new Object[0]);
                    SocketStateMachine.this.mWorking = false;
                    this.mLoop.waitForExit();
                    if (SocketStateMachine.this.mStrReconnect) {
                        SocketStateMachine socketStateMachine2 = SocketStateMachine.this;
                        socketStateMachine2.transitionTo(socketStateMachine2.mStrReconnectState);
                    } else {
                        SocketStateMachine socketStateMachine3 = SocketStateMachine.this;
                        socketStateMachine3.transitionTo(socketStateMachine3.mReconnectState);
                    }
                    return true;
                case 10:
                    LogUtils.i(SocketStateMachine.TAG, "reconnect when the state:" + this.mConnected + " mLoop:" + this.mLoop, new Object[0]);
                    SocketStateMachine.this.mReconnectCount = 0;
                    if (!this.mConnected) {
                        SocketStateMachine.this.mWorking = false;
                        SocketStateMachine.this.mSelector.wakeup();
                        if (this.mLoop != null) {
                            LogUtils.w(SocketStateMachine.TAG, "reconnect and wait for loop exit", new Object[0]);
                            this.mLoop.waitForExit();
                        }
                        if (SocketStateMachine.this.mHandler != null) {
                            SocketStateMachine.this.mHandler.onConnectFailed(null);
                        }
                        SocketStateMachine socketStateMachine4 = SocketStateMachine.this;
                        socketStateMachine4.transitionTo(socketStateMachine4.mPrepareState);
                    } else {
                        LogUtils.w(SocketStateMachine.TAG, "the socket is connected, don't have to do reconnect", new Object[0]);
                    }
                    return true;
            }
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public void exit() {
            super.exit();
            LogUtils.i(SocketStateMachine.TAG, getName() + " exit", new Object[0]);
        }
    }

    protected long reconnectPolicy(int count) {
        if (count == 1) {
            return FIRST_RECONNECT_TIMEOUT;
        }
        if (count - 1 < 6) {
            return TEN_SEC;
        }
        return HALF_MIN;
    }

    public void reconnectImmediately() {
        LogUtils.i(TAG, "reconnectImmediately", new Object[0]);
        sendMessage(10);
    }

    class ReconnectState extends State {
        ReconnectState() {
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public void enter() {
            super.enter();
            LogUtils.i(SocketStateMachine.TAG, getName() + " enter", new Object[0]);
            SocketStateMachine.access$1308(SocketStateMachine.this);
            SocketStateMachine socketStateMachine = SocketStateMachine.this;
            socketStateMachine.sendMessageDelayed(9, socketStateMachine.reconnectPolicy(socketStateMachine.mReconnectCount));
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public boolean processMessage(Message msg) {
            LogUtils.i(SocketStateMachine.TAG, getName() + " processMessage msg:" + SocketStateMachine.this.msgToString(msg.what), new Object[0]);
            int i = msg.what;
            if (i == 2) {
                SocketStateMachine socketStateMachine = SocketStateMachine.this;
                socketStateMachine.transitionTo(socketStateMachine.mDestroyState);
                return true;
            }
            if (i == 9) {
                if (SocketStateMachine.this.hasMessages(9)) {
                    SocketStateMachine.this.removeMessages(9);
                }
                SocketStateMachine socketStateMachine2 = SocketStateMachine.this;
                socketStateMachine2.transitionTo(socketStateMachine2.mPrepareState);
                return true;
            }
            if (i == 10) {
                if (SocketStateMachine.this.hasMessages(9)) {
                    SocketStateMachine.this.removeMessages(9);
                }
                SocketStateMachine socketStateMachine3 = SocketStateMachine.this;
                socketStateMachine3.transitionTo(socketStateMachine3.mStrReconnectState);
                return true;
            }
            return true;
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public void exit() {
            super.exit();
            LogUtils.i(SocketStateMachine.TAG, getName() + " exit", new Object[0]);
        }
    }

    class StrReconnectState extends State {
        StrReconnectState() {
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public void enter() {
            super.enter();
            LogUtils.i(SocketStateMachine.TAG, getName() + " enter mStrReconnect:" + SocketStateMachine.this.mStrReconnect, new Object[0]);
            if (SocketStateMachine.this.mStrReconnect) {
                SocketStateMachine.this.sendMessageDelayed(10, 1000L);
            } else {
                SocketStateMachine.this.sendMessage(10);
            }
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public boolean processMessage(Message msg) {
            LogUtils.i(SocketStateMachine.TAG, getName() + " processMessage msg:" + SocketStateMachine.this.msgToString(msg.what), new Object[0]);
            int i = msg.what;
            if (i == 2) {
                SocketStateMachine socketStateMachine = SocketStateMachine.this;
                socketStateMachine.transitionTo(socketStateMachine.mDestroyState);
            } else if (i == 10) {
                if (SocketStateMachine.this.hasMessages(10)) {
                    SocketStateMachine.this.removeMessages(10);
                }
                SocketStateMachine.this.mReconnectCount = 0;
                SocketStateMachine.this.mStrReconnect = true;
                SocketStateMachine socketStateMachine2 = SocketStateMachine.this;
                socketStateMachine2.transitionTo(socketStateMachine2.mPrepareState);
            }
            return true;
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public void exit() {
            super.exit();
            LogUtils.i(SocketStateMachine.TAG, getName() + " exit", new Object[0]);
        }
    }

    class DestroyState extends State {
        DestroyState() {
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public void enter() {
            super.enter();
            LogUtils.i(SocketStateMachine.TAG, getName() + " enter", new Object[0]);
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public boolean processMessage(Message msg) {
            LogUtils.i(SocketStateMachine.TAG, getName() + " processMessage msg:" + msg.what, new Object[0]);
            if (msg.what == 1) {
                SocketStateMachine socketStateMachine = SocketStateMachine.this;
                socketStateMachine.transitionTo(socketStateMachine.mPrepareState);
            }
            return true;
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public void exit() {
            super.exit();
            LogUtils.i(SocketStateMachine.TAG, getName() + " exit", new Object[0]);
        }
    }
}
