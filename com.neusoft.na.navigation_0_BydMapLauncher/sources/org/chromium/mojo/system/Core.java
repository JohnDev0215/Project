package org.chromium.mojo.system;

import java.util.List;
import org.chromium.mojo.system.DataPipe;
import org.chromium.mojo.system.MessagePipeHandle;
import org.chromium.mojo.system.SharedBufferHandle;

/* JADX INFO: loaded from: classes3.dex */
public interface Core {
    public static final long DEADLINE_INFINITE = -1;

    UntypedHandle acquireNativeHandle(int i);

    Pair<DataPipe.ProducerHandle, DataPipe.ConsumerHandle> createDataPipe(DataPipe.CreateOptions createOptions);

    RunLoop createDefaultRunLoop();

    Pair<MessagePipeHandle, MessagePipeHandle> createMessagePipe(MessagePipeHandle.CreateOptions createOptions);

    SharedBufferHandle createSharedBuffer(SharedBufferHandle.CreateOptions createOptions, long j);

    RunLoop getCurrentRunLoop();

    AsyncWaiter getDefaultAsyncWaiter();

    long getTimeTicksNow();

    WaitResult wait(Handle handle, HandleSignals handleSignals, long j);

    WaitManyResult waitMany(List<Pair<Handle, HandleSignals>> list, long j);

    public static class HandleSignals extends Flags<HandleSignals> {
        private static final int FLAG_NONE = 0;
        private static final int FLAG_PEER_CLOSED = 4;
        private static final int FLAG_READABLE = 1;
        private static final int FLAG_WRITABLE = 2;
        public static final HandleSignals NONE = none().immutable();
        public static final HandleSignals READABLE = none().setReadable(true).immutable();
        public static final HandleSignals WRITABLE = none().setWritable(true).immutable();

        public HandleSignals(int i) {
            super(i);
        }

        public HandleSignals setReadable(boolean z) {
            return setFlag(1, z);
        }

        public HandleSignals setWritable(boolean z) {
            return setFlag(2, z);
        }

        public HandleSignals setPeerClosed(boolean z) {
            return setFlag(4, z);
        }

        public static HandleSignals none() {
            return new HandleSignals(0);
        }
    }

    public static class HandleSignalsState {
        private final HandleSignals mSatisfiableSignals;
        private final HandleSignals mSatisfiedSignals;

        public HandleSignalsState(HandleSignals handleSignals, HandleSignals handleSignals2) {
            this.mSatisfiedSignals = handleSignals;
            this.mSatisfiableSignals = handleSignals2;
        }

        public HandleSignals getSatisfiedSignals() {
            return this.mSatisfiedSignals;
        }

        public HandleSignals getSatisfiableSignals() {
            return this.mSatisfiableSignals;
        }
    }

    public static class WaitResult {
        private HandleSignalsState mHandleSignalsState;
        private int mMojoResult;

        public int getMojoResult() {
            return this.mMojoResult;
        }

        public void setMojoResult(int i) {
            this.mMojoResult = i;
        }

        public HandleSignalsState getHandleSignalsState() {
            return this.mHandleSignalsState;
        }

        public void setHandleSignalsState(HandleSignalsState handleSignalsState) {
            this.mHandleSignalsState = handleSignalsState;
        }
    }

    public static class WaitManyResult {
        private int mHandleIndex;
        private int mMojoResult;
        private List<HandleSignalsState> mSignalStates;

        public int getMojoResult() {
            return this.mMojoResult;
        }

        public void setMojoResult(int i) {
            this.mMojoResult = i;
        }

        public int getHandleIndex() {
            return this.mHandleIndex;
        }

        public void setHandleIndex(int i) {
            this.mHandleIndex = i;
        }

        public List<HandleSignalsState> getSignalStates() {
            return this.mSignalStates;
        }

        public void setSignalStates(List<HandleSignalsState> list) {
            this.mSignalStates = list;
        }
    }
}
