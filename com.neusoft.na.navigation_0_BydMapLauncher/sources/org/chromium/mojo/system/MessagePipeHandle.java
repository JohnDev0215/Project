package org.chromium.mojo.system;

import java.nio.ByteBuffer;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface MessagePipeHandle extends Handle {
    @Override // org.chromium.mojo.system.Handle
    MessagePipeHandle pass();

    ResultAnd<ReadMessageResult> readMessage(ByteBuffer byteBuffer, int i, ReadFlags readFlags);

    void writeMessage(ByteBuffer byteBuffer, List<? extends Handle> list, WriteFlags writeFlags);

    public static class CreateFlags extends Flags<CreateFlags> {
        private static final int FLAG_NONE = 0;
        public static final CreateFlags NONE = none().immutable();

        protected CreateFlags(int i) {
            super(i);
        }

        public static CreateFlags none() {
            return new CreateFlags(0);
        }
    }

    public static class CreateOptions {
        private CreateFlags mFlags = CreateFlags.NONE;

        public CreateFlags getFlags() {
            return this.mFlags;
        }
    }

    public static class WriteFlags extends Flags<WriteFlags> {
        private static final int FLAG_NONE = 0;
        public static final WriteFlags NONE = none().immutable();

        private WriteFlags(int i) {
            super(i);
        }

        public static WriteFlags none() {
            return new WriteFlags(0);
        }
    }

    public static class ReadFlags extends Flags<ReadFlags> {
        private static final int FLAG_MAY_DISCARD = 1;
        private static final int FLAG_NONE = 0;
        public static final ReadFlags NONE = none().immutable();

        private ReadFlags(int i) {
            super(i);
        }

        public ReadFlags setMayDiscard(boolean z) {
            return setFlag(1, z);
        }

        public static ReadFlags none() {
            return new ReadFlags(0);
        }
    }

    public static class ReadMessageResult {
        private List<UntypedHandle> mHandles;
        private int mHandlesCount;
        private int mMessageSize;

        public int getMessageSize() {
            return this.mMessageSize;
        }

        public void setMessageSize(int i) {
            this.mMessageSize = i;
        }

        public int getHandlesCount() {
            return this.mHandlesCount;
        }

        public void setHandlesCount(int i) {
            this.mHandlesCount = i;
        }

        public List<UntypedHandle> getHandles() {
            return this.mHandles;
        }

        public void setHandles(List<UntypedHandle> list) {
            this.mHandles = list;
        }
    }
}
