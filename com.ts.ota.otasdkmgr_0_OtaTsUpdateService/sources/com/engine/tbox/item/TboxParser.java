package com.engine.tbox.item;

import com.engine.tbox.core.data.fsm.Fsm;
import com.engine.tbox.core.data.fsm.State;
import com.engine.tbox.core.utils.LogUtils;
import com.engine.tbox.core.utils.TypeUtils;
import com.engine.tbox.item.TboxMessage;
import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class TboxParser {
    private static final int MESSAGE_BAICE_LENGTH = 9;
    private static final int PACKET_CMD_STATE = 3;
    private static final int PACKET_CRC_STATE = 6;
    private static final int PACKET_DATA_STATE = 5;
    private static final int PACKET_DEL_END_STATE = 7;
    private static final int PACKET_DEL_STATE = 1;
    private static final int PACKET_EOF_STATE = 8;
    private static final int PACKET_INVLID_STATE = -1;
    private static final int PACKET_LEN_STATE = 4;
    private static final int PACKET_SOM_STATE = 2;
    private static final int PACKET_START_STATE = 0;
    private static final int REMOVE_MAX_LEN = 2;
    private Fsm mFsm;
    private TboxEventGenerator mGenerator;
    private String mTag;
    private ParseThread mThread;
    private PacketStartState mStartState = new PacketStartState();
    private PacketDleState mDleState = new PacketDleState();
    private PacketSomState mSomState = new PacketSomState();
    private PacketCmdState mCmdState = new PacketCmdState();
    private PacketLenState mLenState = new PacketLenState();
    private PacketDataState mDataState = new PacketDataState();
    private PacketCrcState mCrcState = new PacketCrcState();
    private PacketDleEndState mDleEndState = new PacketDleEndState();
    private PacketEofState mEofState = new PacketEofState();
    private LinkedBlockingDeque<ByteBuffer> mDequeue = new LinkedBlockingDeque<>();

    public class PacketStartState extends State {
        private static final int DLE_DATA = 13;
        private static final int DLE_LEN = 1;
        private byte[] byteArray = new byte[1];
        private boolean enough = true;

        public PacketStartState() {
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public byte[] currentEventByte() {
            return this.byteArray;
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public void onEnter() {
            this.enough = true;
            this.mFsm.clearValue();
            if (this.mFsm.getEventGeneratorLength() < 9) {
                LogUtils.v(TboxParser.this.mTag, "no enough length:" + this.mFsm.getEventGeneratorLength() + " but min length:9", new Object[0]);
                this.enough = false;
            }
            this.mFsm.mark();
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public void onExit() {
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public int onEvent(byte[] event) {
            if (event.length != 1 || !this.enough) {
                this.mFsm.reset();
                return -1;
            }
            if (event[0] == 13) {
                return 1;
            }
            this.mFsm.resetAndRemove(1);
            return 0;
        }
    }

    public class PacketDleState extends State {
        private static final int SOM_DATA = 5;
        private static final int SOM_LEN = 1;
        private byte[] byteArray = new byte[1];

        public PacketDleState() {
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public byte[] currentEventByte() {
            return this.byteArray;
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public void onEnter() {
            LogUtils.v(TboxParser.this.mTag, "PacketDleState enter", new Object[0]);
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public void onExit() {
            LogUtils.d(TboxParser.this.mTag, "PacketDleState exit", new Object[0]);
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public int onEvent(byte[] event) {
            if (event.length != 1) {
                this.mFsm.reset();
                return -1;
            }
            if (event[0] == 5) {
                return 2;
            }
            this.mFsm.resetAndRemove(2);
            return 0;
        }
    }

    public class PacketSomState extends State {
        private static final int CMD_LEN = 2;
        private byte[] byteArray = new byte[2];

        public PacketSomState() {
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public byte[] currentEventByte() {
            return this.byteArray;
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public void onEnter() {
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public void onExit() {
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public int onEvent(byte[] event) {
            if (event.length != 2) {
                this.mFsm.reset();
                return -1;
            }
            short cmd = TypeUtils.byteArrayToShort(event);
            if (MessageConstants.isSupportCmd(cmd)) {
                LogUtils.d(TboxParser.this.mTag, "get cmd:0x" + Integer.toHexString(cmd), new Object[0]);
                this.mFsm.putValue("cmd", Short.valueOf(cmd));
                return 3;
            }
            this.mFsm.resetAndRemove(2);
            this.mFsm.onErrorCmdMessage(cmd);
            return 0;
        }
    }

    public class PacketCmdState extends State {
        private static final int LENGTH_LEN = 2;
        private byte[] byteArray = new byte[2];

        public PacketCmdState() {
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public byte[] currentEventByte() {
            return this.byteArray;
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public void onEnter() {
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public void onExit() {
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public int onEvent(byte[] event) {
            if (event.length != 2) {
                this.mFsm.reset();
                return -1;
            }
            int len = TypeUtils.byteArrayToShortInt(event);
            this.mFsm.putValue("len", Integer.valueOf(len));
            LogUtils.d(TboxParser.this.mTag, " message len:" + len, new Object[0]);
            return 4;
        }
    }

    public class PacketLenState extends State {
        private int mLen = 0;

        public PacketLenState() {
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public byte[] currentEventByte() {
            int iIntValue = ((Integer) this.mFsm.getValue("len")).intValue();
            this.mLen = iIntValue;
            return new byte[iIntValue];
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public void onEnter() {
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public void onExit() {
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public int onEvent(byte[] event) {
            this.mFsm.putValue("data", event);
            return 5;
        }
    }

    public class PacketDataState extends State {
        private static final int CRC_LEN = 1;
        private byte chk = 0;
        private byte[] byteArray = new byte[1];

        public PacketDataState() {
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public byte[] currentEventByte() {
            return this.byteArray;
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public void onEnter() {
            short cmd = ((Short) this.mFsm.getValue("cmd")).shortValue();
            int len = ((Integer) this.mFsm.getValue("len")).intValue();
            byte[] bytes = (byte[]) this.mFsm.getValue("data");
            this.chk = TboxMessage.generateCheckSum(cmd, len, bytes);
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public void onExit() {
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public int onEvent(byte[] event) {
            if (event.length == 1) {
                LogUtils.d(TboxParser.this.mTag, "checksum calc:" + Integer.toHexString(this.chk) + " data check:" + Integer.toHexString(event[0]), new Object[0]);
                if (this.chk != event[0]) {
                    this.mFsm.resetAndRemove(2);
                    this.mFsm.onErrorCrcMessage(this.chk);
                    return 0;
                }
                return 6;
            }
            this.mFsm.reset();
            return -1;
        }
    }

    public class PacketCrcState extends State {
        private static final int DLEEND_DATA = 13;
        private static final int DLEEND_LEN = 1;
        private byte[] byteArray = new byte[1];

        public PacketCrcState() {
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public byte[] currentEventByte() {
            return this.byteArray;
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public void onEnter() {
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public void onExit() {
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public int onEvent(byte[] event) {
            if (event.length != 1) {
                this.mFsm.reset();
                return -1;
            }
            if (13 != event[0]) {
                this.mFsm.resetAndRemove(2);
                return 0;
            }
            return 7;
        }
    }

    public class PacketDleEndState extends State {
        private static final int EOF_DATA = 10;
        private static final int EOF_LEN = 1;
        private byte[] byteArray = new byte[1];

        public PacketDleEndState() {
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public byte[] currentEventByte() {
            return this.byteArray;
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public void onEnter() {
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public void onExit() {
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public int onEvent(byte[] event) {
            if (event.length != 1) {
                this.mFsm.reset();
                return -1;
            }
            if (10 != event[0]) {
                this.mFsm.resetAndRemove(2);
                return 0;
            }
            return 8;
        }
    }

    public class PacketEofState extends State {
        public PacketEofState() {
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public byte[] currentEventByte() {
            return new byte[0];
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public void onEnter() {
            TboxMessage.Builder builder = new TboxMessage.Builder();
            short cmd = ((Short) this.mFsm.getValue("cmd")).shortValue();
            byte[] bytes = (byte[]) this.mFsm.getValue("data");
            TboxMessage msg = builder.addCmd(cmd).putData(bytes).build();
            this.mFsm.onMessageReady(msg);
            this.mFsm.transToState(0);
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public void onExit() {
        }

        @Override // com.engine.tbox.core.data.fsm.State
        public int onEvent(byte[] event) {
            return 0;
        }
    }

    public class TboxEventGenerator implements Fsm.EventGenerator {
        private static final int MAX_LEN = 65544;
        private ByteBuffer mBuffer = ByteBuffer.allocate(MAX_LEN);

        public TboxEventGenerator() {
        }

        public ByteBuffer getBuffer() {
            this.mBuffer.flip();
            return this.mBuffer;
        }

        @Override // com.engine.tbox.core.data.fsm.Fsm.EventGenerator
        public int getMessageLen() {
            int len = this.mBuffer.limit() - this.mBuffer.position();
            LogUtils.v(TboxParser.this.mTag, "EventGenerator max len:" + len, new Object[0]);
            return len;
        }

        @Override // com.engine.tbox.core.data.fsm.Fsm.EventGenerator
        public boolean generateEvent(byte[] byteArray) {
            if (byteArray == null) {
                LogUtils.e(TboxParser.this.mTag, "generateEvent event: byteArray is null", new Object[0]);
                return false;
            }
            if (getMessageLen() < byteArray.length) {
                LogUtils.v(TboxParser.this.mTag, "No enough length " + getMessageLen() + " event exception need:" + byteArray.length, new Object[0]);
                return false;
            }
            for (int i = 0; i < byteArray.length; i++) {
                byteArray[i] = this.mBuffer.get();
            }
            return true;
        }

        @Override // com.engine.tbox.core.data.fsm.Fsm.EventGenerator
        public void mark() {
            LogUtils.v(TboxParser.this.mTag, "mark the generator", new Object[0]);
            this.mBuffer.mark();
        }

        @Override // com.engine.tbox.core.data.fsm.Fsm.EventGenerator
        public void reset() {
            LogUtils.v(TboxParser.this.mTag, "reset the generator", new Object[0]);
            this.mBuffer.reset();
        }
    }

    public class ParseThread extends Thread {
        private CountDownLatch mCountLatch;
        private boolean mThreadLoop = true;

        public ParseThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            setName("Parse Thread");
            LogUtils.v(TboxParser.this.mTag, getName() + " ParseThread run enter", new Object[0]);
            ByteBuffer buffer = TboxParser.this.mGenerator.getBuffer();
            LogUtils.v(TboxParser.this.mTag, getName() + " generator buffer" + buffer, new Object[0]);
            while (true) {
                if (!this.mThreadLoop) {
                    break;
                }
                ByteBuffer first = null;
                try {
                    first = (ByteBuffer) TboxParser.this.mDequeue.pollFirst(1L, TimeUnit.SECONDS);
                } catch (InterruptedException exp) {
                    exp.printStackTrace();
                }
                if (!this.mThreadLoop) {
                    LogUtils.i(TboxParser.this.mTag, getName() + " exit the loop", new Object[0]);
                    break;
                }
                if (first != null) {
                    LogUtils.d(TboxParser.this.mTag, getName() + " get bytebuffer:" + first, new Object[0]);
                    if (changeByteBuffer(buffer, first)) {
                        TboxParser.this.mDequeue.addFirst(first);
                    }
                    TboxParser.this.mFsm.startAndLoop();
                }
            }
            CountDownLatch countDownLatch = this.mCountLatch;
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
            LogUtils.i(TboxParser.this.mTag, getName() + " exit the ParseThread", new Object[0]);
        }

        public boolean changeByteBuffer(ByteBuffer dst, ByteBuffer src) {
            dst.compact();
            int len1 = dst.remaining();
            LogUtils.d(TboxParser.this.mTag, "buffer remaining len:" + len1 + " dst:" + dst, new Object[0]);
            String str = TboxParser.this.mTag;
            StringBuilder sb = new StringBuilder();
            sb.append("src buffer:");
            sb.append(src);
            LogUtils.d(str, sb.toString(), new Object[0]);
            if (len1 == 0) {
                return src.remaining() > 0;
            }
            int len2 = src.remaining();
            int fillLen = Math.min(len1, len2);
            LogUtils.d(TboxParser.this.mTag, "fillLen:" + fillLen + " len1:" + len1 + " len2:" + len2, new Object[0]);
            byte[] bytes = new byte[fillLen];
            src.get(bytes);
            dst.put(bytes);
            dst.flip();
            return src.remaining() > 0;
        }

        public void stopThread() {
            LogUtils.i(TboxParser.this.mTag, "stop the ParseThread", new Object[0]);
            this.mThreadLoop = false;
            CountDownLatch countDownLatch = this.mCountLatch;
            if (countDownLatch != null) {
                try {
                    countDownLatch.await(2L, TimeUnit.SECONDS);
                } catch (InterruptedException exp) {
                    exp.printStackTrace();
                }
                this.mCountLatch = null;
            }
        }

        @Override // java.lang.Thread
        public synchronized void start() {
            LogUtils.d(TboxParser.this.mTag, "start the ParseThread", new Object[0]);
            this.mCountLatch = new CountDownLatch(1);
            super.start();
        }
    }

    public TboxParser(String tag) {
        if (tag == null) {
            this.mTag = "TboxParser";
        } else {
            this.mTag = tag;
        }
        this.mFsm = new Fsm(this.mTag);
        LogUtils.d("TboxParser", "tag:" + this.mTag, new Object[0]);
        this.mFsm.addState(0, this.mStartState).addState(1, this.mDleState).addState(2, this.mSomState).addState(3, this.mCmdState).addState(4, this.mLenState).addState(5, this.mDataState).addState(6, this.mCrcState).addState(7, this.mDleEndState).addState(8, this.mEofState);
        this.mFsm.setInitState(0);
        TboxEventGenerator tboxEventGenerator = new TboxEventGenerator();
        this.mGenerator = tboxEventGenerator;
        this.mFsm.registerEventGenerator(tboxEventGenerator);
    }

    public void registerMessageReady(Fsm.OnMessageReady callback) {
        this.mFsm.registerMessageReady(callback);
    }

    public void start(String name) {
        ParseThread parseThread = new ParseThread();
        this.mThread = parseThread;
        parseThread.setName(name);
        this.mThread.start();
    }

    public void stop() {
        LogUtils.d(this.mTag, "stop the " + this.mThread, new Object[0]);
        ParseThread parseThread = this.mThread;
        if (parseThread != null) {
            parseThread.stopThread();
        }
    }

    public void parseMsgFromBuffer(ByteBuffer buffer) {
        LogUtils.v(this.mTag, "dequeue enqueue", new Object[0]);
        try {
            this.mDequeue.putLast(buffer);
        } catch (InterruptedException exp) {
            exp.printStackTrace();
        }
    }
}
