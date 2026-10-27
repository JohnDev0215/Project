package com.engine.tbox.core.data.fsm;

import android.util.ArrayMap;
import com.engine.tbox.core.data.CountSyncMessage;
import com.engine.tbox.core.utils.LogUtils;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class Fsm {
    private State mCurrentState;
    private EventGenerator mGenerator;
    private State mInitState;
    OnMessageReady mMessageCallback;
    private String mTag;
    private ArrayMap<Integer, State> mStateMap = new ArrayMap<>();
    private FsmContext mContext = new FsmContext();

    public interface EventGenerator {
        boolean generateEvent(byte[] bArr);

        int getMessageLen();

        void mark();

        void reset();
    }

    public interface OnMessageReady {
        void onErrorCmdMessage(int i);

        void onErrorCrcMessage(int i);

        void onMessageReady(CountSyncMessage countSyncMessage);
    }

    public static class FsmContext {
        private ConcurrentHashMap<String, Object> mValues = new ConcurrentHashMap<>();

        public void putValue(String key, Object value) {
            this.mValues.put(key, value);
        }

        public Object getValue(String key) {
            return this.mValues.get(key);
        }

        public void updateValue(String key, Object value) {
            boolean containsKey = this.mValues.containsKey(key);
            if (containsKey) {
                this.mValues.put(key, value);
            }
        }

        public void deleteValue(String key) {
            this.mValues.remove(key);
        }

        public void clear() {
            this.mValues.clear();
        }
    }

    public Fsm(String tag) {
        this.mTag = "Fsm";
        if (tag != null) {
            this.mTag = tag;
        }
    }

    public void transToState(int keyOfState) {
        State state = this.mStateMap.get(Integer.valueOf(keyOfState));
        if (this.mCurrentState != null) {
            LogUtils.v(this.mTag, this.mCurrentState.name() + " onExit", new Object[0]);
            this.mCurrentState.onExit();
        }
        this.mCurrentState = state;
        state.onEnter();
        LogUtils.v(this.mTag, this.mCurrentState.name() + " onEnter", new Object[0]);
    }

    public static class NoEventException extends Exception {
        public NoEventException(String s) {
            super(s);
        }

        public NoEventException() {
            super("No event exception");
        }
    }

    public int getEventGeneratorLength() {
        EventGenerator eventGenerator = this.mGenerator;
        if (eventGenerator != null) {
            return eventGenerator.getMessageLen();
        }
        return 0;
    }

    public void mark() {
        EventGenerator eventGenerator = this.mGenerator;
        if (eventGenerator != null) {
            eventGenerator.mark();
        }
    }

    public void reset() {
        LogUtils.d(this.mTag, "reset", new Object[0]);
        EventGenerator eventGenerator = this.mGenerator;
        if (eventGenerator != null) {
            eventGenerator.reset();
        }
    }

    public void resetAndRemove(int len) {
        LogUtils.d(this.mTag, "resetAndRemove:" + len, new Object[0]);
        EventGenerator eventGenerator = this.mGenerator;
        if (eventGenerator != null) {
            eventGenerator.reset();
            byte[] array = new byte[len];
            this.mGenerator.generateEvent(array);
        }
    }

    public void clearValue() {
        FsmContext fsmContext = this.mContext;
        if (fsmContext != null) {
            fsmContext.clear();
        }
    }

    public void registerMessageReady(OnMessageReady ready) {
        this.mMessageCallback = ready;
    }

    public void onMessageReady(CountSyncMessage msg) {
        OnMessageReady onMessageReady = this.mMessageCallback;
        if (onMessageReady != null) {
            onMessageReady.onMessageReady(msg);
        }
    }

    public void onErrorCmdMessage(int cmd) {
        OnMessageReady onMessageReady = this.mMessageCallback;
        if (onMessageReady != null) {
            onMessageReady.onErrorCmdMessage(cmd);
        }
    }

    public void onErrorCrcMessage(int crc) {
        OnMessageReady onMessageReady = this.mMessageCallback;
        if (onMessageReady != null) {
            onMessageReady.onErrorCrcMessage(crc);
        }
    }

    public void putValue(String key, Object value) {
        this.mContext.putValue(key, value);
    }

    public Object getValue(String key) {
        return this.mContext.getValue(key);
    }

    public void updateValue(String key, Object value) {
        this.mContext.updateValue(key, value);
    }

    public void deleteValue(String key) {
        this.mContext.deleteValue(key);
    }

    public Fsm setInitState(int key) {
        this.mInitState = this.mStateMap.get(Integer.valueOf(key));
        return this;
    }

    public Fsm addState(int key, State state) {
        boolean containsKey = this.mStateMap.containsKey(Integer.valueOf(key));
        if (!containsKey) {
            this.mStateMap.put(Integer.valueOf(key), state);
            state.addFsm(this);
        }
        return this;
    }

    public void registerEventGenerator(EventGenerator generator) {
        this.mGenerator = generator;
    }

    public boolean startAndLoop() {
        State state = this.mInitState;
        if (state == null) {
            LogUtils.d(this.mTag, "the init state haven't been registered", new Object[0]);
            return false;
        }
        if (this.mGenerator == null) {
            LogUtils.e(this.mTag, "generator is null", new Object[0]);
            return false;
        }
        this.mCurrentState = state;
        LogUtils.d(this.mTag, this.mCurrentState.name() + " enter", new Object[0]);
        this.mCurrentState.onEnter();
        while (true) {
            byte[] event = this.mCurrentState.currentEventByte();
            if (!this.mGenerator.generateEvent(event)) {
                if (this.mCurrentState != this.mInitState) {
                    LogUtils.e(this.mTag, "state:" + this.mCurrentState.name() + "generator event failed", new Object[0]);
                }
                this.mGenerator.reset();
                return true;
            }
            LogUtils.d(this.mTag, "state:" + this.mCurrentState.name() + " get event length:" + event.length, new Object[0]);
            int keyOfState = this.mCurrentState.onEvent(event);
            State state2 = this.mStateMap.get(Integer.valueOf(keyOfState));
            if (state2 == null) {
                LogUtils.e(this.mTag, "invalid state of key:" + keyOfState + " when " + this.mCurrentState + " handle event:" + Arrays.toString(event), new Object[0]);
                return false;
            }
            LogUtils.v(this.mTag, this.mCurrentState.name() + " onExit", new Object[0]);
            this.mCurrentState.onExit();
            this.mCurrentState = state2;
            state2.onEnter();
            LogUtils.v(this.mTag, this.mCurrentState.name() + " onEnter", new Object[0]);
        }
    }

    public State getCurrentState() {
        return this.mCurrentState;
    }
}
