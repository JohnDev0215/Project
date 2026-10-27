package com.neusoft.dlt.service;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes.dex */
public class DltMessage {
    public static final int DLT_MAXLENGTH = 10240;
    public static final int DLT_STORAGE_HEADER_LEN = 16;
    public static final int DUH_HEADER_LEN = 8;
    private ByteBuffer mBuffer;

    enum MessageType {
        DLT,
        DUH
    }

    public int getLength() {
        return this.mBuffer.limit();
    }

    public DltMessage(ByteBuffer byteBuffer) {
        ByteBuffer byteBufferCreateByteBuffer = createByteBuffer(byteBuffer.remaining(), byteBuffer.isDirect());
        this.mBuffer = byteBufferCreateByteBuffer;
        byteBufferCreateByteBuffer.put(byteBuffer);
        this.mBuffer.flip();
    }

    public ByteBuffer toByteBuffer() {
        return this.mBuffer.duplicate();
    }

    private ByteBuffer createByteBuffer(int i, boolean z) {
        ByteBuffer byteBufferAllocate;
        if (z) {
            byteBufferAllocate = ByteBuffer.allocateDirect(i);
        } else {
            byteBufferAllocate = ByteBuffer.allocate(i);
        }
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        return byteBufferAllocate;
    }

    public static DltMessage read(ByteBuffer byteBuffer) {
        MessageType messageType;
        ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
        byteBufferDuplicate.order(ByteOrder.BIG_ENDIAN);
        byteBufferDuplicate.flip();
        DltMessage dltMessage = null;
        boolean z = false;
        while (!z && byteBufferDuplicate.remaining() >= 4) {
            int iPosition = byteBufferDuplicate.position();
            if (byteBufferDuplicate.get(iPosition) == 68) {
                int i = iPosition + 1;
                if (byteBufferDuplicate.get(i) == 76 && byteBufferDuplicate.get(iPosition + 2) == 84) {
                    messageType = MessageType.DLT;
                } else {
                    messageType = (byteBufferDuplicate.get(i) == 85 && byteBufferDuplicate.get(iPosition + 2) == 72) ? MessageType.DUH : null;
                }
                if (messageType != null) {
                    int i2 = AnonymousClass1.$SwitchMap$com$neusoft$dlt$service$DltMessage$MessageType[messageType.ordinal()];
                    short s = i2 != 1 ? i2 != 2 ? (short) 0 : (short) 8 : (short) 16;
                    if (byteBufferDuplicate.remaining() < s + 4) {
                        z = true;
                    } else {
                        short s2 = byteBufferDuplicate.getShort(iPosition + s + 2);
                        if (s2 > 10240 || s2 < 0) {
                            byteBufferDuplicate.position(byteBufferDuplicate.position() + 3);
                        } else {
                            if (byteBufferDuplicate.remaining() >= s + s2) {
                                ByteBuffer byteBufferDuplicate2 = byteBufferDuplicate.duplicate();
                                byteBufferDuplicate2.position(byteBufferDuplicate2.position() + s);
                                byteBufferDuplicate2.limit(byteBufferDuplicate2.position() + s2);
                                dltMessage = new DltMessage(byteBufferDuplicate2);
                                byteBufferDuplicate.position(byteBufferDuplicate.position() + s + s2);
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
            byteBuffer.position(byteBufferDuplicate.position());
            byteBuffer.limit(byteBuffer.capacity());
        }
        return dltMessage;
    }

    /* JADX INFO: renamed from: com.neusoft.dlt.service.DltMessage$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$neusoft$dlt$service$DltMessage$MessageType;

        static {
            int[] iArr = new int[MessageType.values().length];
            $SwitchMap$com$neusoft$dlt$service$DltMessage$MessageType = iArr;
            try {
                iArr[MessageType.DLT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$neusoft$dlt$service$DltMessage$MessageType[MessageType.DUH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
