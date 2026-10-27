package com.neusoft.na.dltdroid.service;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DltMessage.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \u000f2\u00020\u0001:\u0002\u000f\u0010B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0002J\u0006\u0010\u000e\u001a\u00020\u0003R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/neusoft/na/dltdroid/service/DltMessage;", "", "buffer", "Ljava/nio/ByteBuffer;", "(Ljava/nio/ByteBuffer;)V", "length", "", "getLength", "()I", "mBuffer", "createByteBuffer", "len", "direct", "", "toByteBuffer", "Companion", "MessageType", "dltdroid-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class DltMessage {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int DLT_MAXLENGTH = 10240;
    private static final int DLT_STORAGE_HEADER_LEN = 16;
    private static final int DUH_HEADER_LEN = 8;
    private final ByteBuffer mBuffer;

    /* JADX INFO: compiled from: DltMessage.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/neusoft/na/dltdroid/service/DltMessage$MessageType;", "", "(Ljava/lang/String;I)V", "DLT", "DUH", "dltdroid-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public enum MessageType {
        DLT,
        DUH
    }

    public final int getLength() {
        return this.mBuffer.limit();
    }

    public DltMessage(ByteBuffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        ByteBuffer byteBufferCreateByteBuffer = createByteBuffer(buffer.remaining(), buffer.isDirect());
        this.mBuffer = byteBufferCreateByteBuffer;
        byteBufferCreateByteBuffer.put(buffer);
        byteBufferCreateByteBuffer.flip();
    }

    public final ByteBuffer toByteBuffer() {
        ByteBuffer b = this.mBuffer.duplicate();
        Intrinsics.checkNotNullExpressionValue(b, "b");
        return b;
    }

    private final ByteBuffer createByteBuffer(int len, boolean direct) {
        ByteBuffer res;
        if (direct) {
            res = ByteBuffer.allocateDirect(len);
        } else {
            res = ByteBuffer.allocate(len);
        }
        res.order(ByteOrder.LITTLE_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(res, "res");
        return res;
    }

    /* JADX INFO: compiled from: DltMessage.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000eR\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/neusoft/na/dltdroid/service/DltMessage$Companion;", "", "()V", "DLT_MAXLENGTH", "", "getDLT_MAXLENGTH", "()I", "DLT_STORAGE_HEADER_LEN", "getDLT_STORAGE_HEADER_LEN", "DUH_HEADER_LEN", "getDUH_HEADER_LEN", "read", "Lcom/neusoft/na/dltdroid/service/DltMessage;", "buffer", "Ljava/nio/ByteBuffer;", "dltdroid-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {

        /* JADX INFO: compiled from: DltMessage.kt */
        @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[MessageType.values().length];
                try {
                    iArr[MessageType.DLT.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[MessageType.DUH.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getDLT_MAXLENGTH() {
            return DltMessage.DLT_MAXLENGTH;
        }

        public final int getDLT_STORAGE_HEADER_LEN() {
            return DltMessage.DLT_STORAGE_HEADER_LEN;
        }

        public final int getDUH_HEADER_LEN() {
            return DltMessage.DUH_HEADER_LEN;
        }

        public final DltMessage read(ByteBuffer buffer) {
            MessageType messageType;
            int dlt_storage_header_len;
            Intrinsics.checkNotNullParameter(buffer, "buffer");
            ByteBuffer byteBufferDuplicate = buffer.duplicate();
            byteBufferDuplicate.order(ByteOrder.BIG_ENDIAN);
            byteBufferDuplicate.flip();
            boolean z = false;
            DltMessage dltMessage = null;
            while (!z && byteBufferDuplicate.remaining() >= 4) {
                int iPosition = byteBufferDuplicate.position();
                if (((char) byteBufferDuplicate.get(iPosition)) == 'D') {
                    int i = iPosition + 1;
                    if (((char) byteBufferDuplicate.get(i)) == 'L' && ((char) byteBufferDuplicate.get(iPosition + 2)) == 'T') {
                        messageType = MessageType.DLT;
                    } else if (((char) byteBufferDuplicate.get(i)) == 'U' && ((char) byteBufferDuplicate.get(iPosition + 2)) == 'H') {
                        messageType = MessageType.DUH;
                    } else {
                        messageType = null;
                    }
                    if (messageType != null) {
                        int i2 = WhenMappings.$EnumSwitchMapping$0[messageType.ordinal()];
                        if (i2 == 1) {
                            dlt_storage_header_len = getDLT_STORAGE_HEADER_LEN();
                        } else {
                            if (i2 != 2) {
                                throw new NoWhenBranchMatchedException();
                            }
                            dlt_storage_header_len = getDUH_HEADER_LEN();
                        }
                        if (byteBufferDuplicate.remaining() < dlt_storage_header_len + 4) {
                            z = true;
                        } else {
                            short s = byteBufferDuplicate.getShort(iPosition + dlt_storage_header_len + 2);
                            if (s > getDLT_MAXLENGTH() || s < 0) {
                                byteBufferDuplicate.position(byteBufferDuplicate.position() + 3);
                            } else {
                                if (byteBufferDuplicate.remaining() >= dlt_storage_header_len + s) {
                                    ByteBuffer b = byteBufferDuplicate.duplicate();
                                    b.position(b.position() + dlt_storage_header_len);
                                    b.limit(b.position() + s);
                                    Intrinsics.checkNotNullExpressionValue(b, "b");
                                    dltMessage = new DltMessage(b);
                                    byteBufferDuplicate.position(byteBufferDuplicate.position() + dlt_storage_header_len + s);
                                }
                                z = true;
                            }
                        }
                    }
                }
                if (!z) {
                    byteBufferDuplicate.get();
                }
            }
            if (byteBufferDuplicate.position() > 0) {
                byteBufferDuplicate.compact();
                buffer.position(byteBufferDuplicate.position());
                buffer.limit(buffer.capacity());
            }
            return dltMessage;
        }
    }
}
