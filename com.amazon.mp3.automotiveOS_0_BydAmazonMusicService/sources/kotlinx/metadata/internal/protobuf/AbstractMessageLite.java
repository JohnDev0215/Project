package kotlinx.metadata.internal.protobuf;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import kotlinx.metadata.internal.protobuf.MessageLite;

/* JADX INFO: loaded from: classes7.dex */
public abstract class AbstractMessageLite implements MessageLite {
    protected int memoizedHashCode = 0;

    @Override // kotlinx.metadata.internal.protobuf.MessageLite
    public abstract /* synthetic */ int getSerializedSize();

    UninitializedMessageException newUninitializedMessageException() {
        return new UninitializedMessageException(this);
    }

    public static abstract class Builder<BuilderType extends Builder> implements MessageLite.Builder {
        @Override // 
        public abstract BuilderType clone();

        @Override // kotlinx.metadata.internal.protobuf.MessageLite.Builder
        public abstract BuilderType mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException;

        static final class LimitedInputStream extends FilterInputStream {
            private int limit;

            LimitedInputStream(InputStream inputStream, int i) {
                super(inputStream);
                this.limit = i;
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public int available() throws IOException {
                return Math.min(super.available(), this.limit);
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public int read() throws IOException {
                if (this.limit <= 0) {
                    return -1;
                }
                int i = super.read();
                if (i >= 0) {
                    this.limit--;
                }
                return i;
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public int read(byte[] bArr, int i, int i2) throws IOException {
                int i3 = this.limit;
                if (i3 <= 0) {
                    return -1;
                }
                int i4 = super.read(bArr, i, Math.min(i2, i3));
                if (i4 >= 0) {
                    this.limit -= i4;
                }
                return i4;
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public long skip(long j) throws IOException {
                long jSkip = super.skip(Math.min(j, this.limit));
                if (jSkip >= 0) {
                    this.limit = (int) (((long) this.limit) - jSkip);
                }
                return jSkip;
            }
        }

        protected static UninitializedMessageException newUninitializedMessageException(MessageLite messageLite) {
            return new UninitializedMessageException(messageLite);
        }
    }
}
