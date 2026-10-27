package com.amazon.ion.impl.bin;

import com.amazon.digitalmusicplayback.AudioFormat;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
final class WriteBuffer implements Closeable {
    private final BlockAllocator allocator;
    private final List<Block> blocks;
    private Block current;
    private int index;

    public WriteBuffer(BlockAllocator blockAllocator) {
        this.allocator = blockAllocator;
        ArrayList arrayList = new ArrayList();
        this.blocks = arrayList;
        allocateNewBlock();
        this.index = 0;
        this.current = (Block) arrayList.get(0);
    }

    private void allocateNewBlock() {
        this.blocks.add(this.allocator.allocateBlock());
    }

    private int index(long j) {
        return (int) (j / ((long) this.allocator.getBlockSize()));
    }

    private int offset(long j) {
        return (int) (j % ((long) this.allocator.getBlockSize()));
    }

    public void reset() {
        close();
        allocateNewBlock();
        this.index = 0;
        this.current = this.blocks.get(0);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Iterator<Block> it = this.blocks.iterator();
        while (it.hasNext()) {
            it.next().close();
        }
        this.blocks.clear();
    }

    public void truncate(long j) {
        int iIndex = index(j);
        int iOffset = offset(j);
        Block block = this.blocks.get(iIndex);
        this.index = iIndex;
        block.limit = iOffset;
        this.current = block;
    }

    public int remaining() {
        return this.current.remaining();
    }

    public long position() {
        return (((long) this.index) * ((long) this.allocator.getBlockSize())) + ((long) this.current.limit);
    }

    public int getUInt8At(long j) {
        int iIndex = index(j);
        return this.blocks.get(iIndex).data[offset(j)] & 255;
    }

    public void writeByte(byte b) {
        if (remaining() < 1) {
            if (this.index == this.blocks.size() - 1) {
                allocateNewBlock();
            }
            int i = this.index + 1;
            this.index = i;
            this.current = this.blocks.get(i);
        }
        Block block = this.current;
        byte[] bArr = block.data;
        int i2 = block.limit;
        bArr[i2] = b;
        block.limit = i2 + 1;
    }

    private void writeBytesSlow(byte[] bArr, int i, int i2) {
        while (i2 > 0) {
            Block block = this.current;
            int iMin = Math.min(i2, block.remaining());
            System.arraycopy(bArr, i, block.data, block.limit, iMin);
            block.limit += iMin;
            i += iMin;
            i2 -= iMin;
            if (block.remaining() == 0) {
                if (this.index == this.blocks.size() - 1) {
                    allocateNewBlock();
                }
                int i3 = this.index + 1;
                this.index = i3;
                this.current = this.blocks.get(i3);
            }
        }
    }

    public void writeBytes(byte[] bArr, int i, int i2) {
        if (i2 > remaining()) {
            writeBytesSlow(bArr, i, i2);
            return;
        }
        Block block = this.current;
        System.arraycopy(bArr, i, block.data, block.limit, i2);
        block.limit += i2;
    }

    public void writeBytes(byte[] bArr) {
        writeBytes(bArr, 0, bArr.length);
    }

    public void writeUInt8(long j) {
        writeByte((byte) j);
    }

    private void writeUInt16Slow(long j) {
        writeByte((byte) (j >> 8));
        writeByte((byte) j);
    }

    public void writeUInt16(long j) {
        if (remaining() < 2) {
            writeUInt16Slow(j);
            return;
        }
        Block block = this.current;
        byte[] bArr = block.data;
        int i = block.limit;
        int i2 = i + 1;
        bArr[i] = (byte) (j >> 8);
        bArr[i2] = (byte) j;
        block.limit = i2 + 1;
    }

    private void writeUInt24Slow(long j) {
        writeByte((byte) (j >> 16));
        writeByte((byte) (j >> 8));
        writeByte((byte) j);
    }

    public void writeUInt24(long j) {
        if (remaining() < 3) {
            writeUInt24Slow(j);
            return;
        }
        Block block = this.current;
        byte[] bArr = block.data;
        int i = block.limit;
        int i2 = i + 1;
        bArr[i] = (byte) (j >> 16);
        int i3 = i2 + 1;
        bArr[i2] = (byte) (j >> 8);
        bArr[i3] = (byte) j;
        block.limit = i3 + 1;
    }

    private void writeUInt32Slow(long j) {
        writeByte((byte) (j >> 24));
        writeByte((byte) (j >> 16));
        writeByte((byte) (j >> 8));
        writeByte((byte) j);
    }

    public void writeUInt32(long j) {
        if (remaining() < 4) {
            writeUInt32Slow(j);
            return;
        }
        Block block = this.current;
        byte[] bArr = block.data;
        int i = block.limit;
        int i2 = i + 1;
        bArr[i] = (byte) (j >> 24);
        int i3 = i2 + 1;
        bArr[i2] = (byte) (j >> 16);
        int i4 = i3 + 1;
        bArr[i3] = (byte) (j >> 8);
        bArr[i4] = (byte) j;
        block.limit = i4 + 1;
    }

    private void writeUInt40Slow(long j) {
        writeByte((byte) (j >> 32));
        writeByte((byte) (j >> 24));
        writeByte((byte) (j >> 16));
        writeByte((byte) (j >> 8));
        writeByte((byte) j);
    }

    public void writeUInt40(long j) {
        if (remaining() < 5) {
            writeUInt40Slow(j);
            return;
        }
        Block block = this.current;
        byte[] bArr = block.data;
        int i = block.limit;
        int i2 = i + 1;
        bArr[i] = (byte) (j >> 32);
        int i3 = i2 + 1;
        bArr[i2] = (byte) (j >> 24);
        int i4 = i3 + 1;
        bArr[i3] = (byte) (j >> 16);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (j >> 8);
        bArr[i5] = (byte) j;
        block.limit = i5 + 1;
    }

    private void writeUInt48Slow(long j) {
        writeByte((byte) (j >> 40));
        writeByte((byte) (j >> 32));
        writeByte((byte) (j >> 24));
        writeByte((byte) (j >> 16));
        writeByte((byte) (j >> 8));
        writeByte((byte) j);
    }

    public void writeUInt48(long j) {
        if (remaining() < 6) {
            writeUInt48Slow(j);
            return;
        }
        Block block = this.current;
        byte[] bArr = block.data;
        int i = block.limit;
        int i2 = i + 1;
        bArr[i] = (byte) (j >> 40);
        int i3 = i2 + 1;
        bArr[i2] = (byte) (j >> 32);
        int i4 = i3 + 1;
        bArr[i3] = (byte) (j >> 24);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (j >> 16);
        int i6 = i5 + 1;
        bArr[i5] = (byte) (j >> 8);
        bArr[i6] = (byte) j;
        block.limit = i6 + 1;
    }

    private void writeUInt56Slow(long j) {
        writeByte((byte) (j >> 48));
        writeByte((byte) (j >> 40));
        writeByte((byte) (j >> 32));
        writeByte((byte) (j >> 24));
        writeByte((byte) (j >> 16));
        writeByte((byte) (j >> 8));
        writeByte((byte) j);
    }

    public void writeUInt56(long j) {
        if (remaining() < 7) {
            writeUInt56Slow(j);
            return;
        }
        Block block = this.current;
        byte[] bArr = block.data;
        int i = block.limit;
        int i2 = i + 1;
        bArr[i] = (byte) (j >> 48);
        int i3 = i2 + 1;
        bArr[i2] = (byte) (j >> 40);
        int i4 = i3 + 1;
        bArr[i3] = (byte) (j >> 32);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (j >> 24);
        int i6 = i5 + 1;
        bArr[i5] = (byte) (j >> 16);
        int i7 = i6 + 1;
        bArr[i6] = (byte) (j >> 8);
        bArr[i7] = (byte) j;
        block.limit = i7 + 1;
    }

    private void writeUInt64Slow(long j) {
        writeByte((byte) (j >> 56));
        writeByte((byte) (j >> 48));
        writeByte((byte) (j >> 40));
        writeByte((byte) (j >> 32));
        writeByte((byte) (j >> 24));
        writeByte((byte) (j >> 16));
        writeByte((byte) (j >> 8));
        writeByte((byte) j);
    }

    public void writeUInt64(long j) {
        if (remaining() < 8) {
            writeUInt64Slow(j);
            return;
        }
        Block block = this.current;
        byte[] bArr = block.data;
        int i = block.limit;
        int i2 = i + 1;
        bArr[i] = (byte) (j >> 56);
        int i3 = i2 + 1;
        bArr[i2] = (byte) (j >> 48);
        int i4 = i3 + 1;
        bArr[i3] = (byte) (j >> 40);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (j >> 32);
        int i6 = i5 + 1;
        bArr[i5] = (byte) (j >> 24);
        int i7 = i6 + 1;
        bArr[i6] = (byte) (j >> 16);
        int i8 = i7 + 1;
        bArr[i7] = (byte) (j >> 8);
        bArr[i8] = (byte) j;
        block.limit = i8 + 1;
    }

    public void writeInt8(long j) {
        if (j < 0) {
            j = (-j) | 128;
        }
        writeUInt8(j);
    }

    public void writeInt16(long j) {
        if (j < 0) {
            j = (-j) | 32768;
        }
        writeUInt16(j);
    }

    public void writeInt24(long j) {
        if (j < 0) {
            j = (-j) | 8388608;
        }
        writeUInt24(j);
    }

    public void writeInt32(long j) {
        if (j < 0) {
            j = (-j) | AudioFormat.WIDE_LEFT;
        }
        writeUInt32(j);
    }

    public void writeInt40(long j) {
        if (j < 0) {
            j = (-j) | 549755813888L;
        }
        writeUInt40(j);
    }

    public void writeInt48(long j) {
        if (j < 0) {
            j = (-j) | 140737488355328L;
        }
        writeUInt48(j);
    }

    public void writeInt56(long j) {
        if (j < 0) {
            j = (-j) | 36028797018963968L;
        }
        writeUInt56(j);
    }

    public void writeInt64(long j) {
        if (j < 0) {
            j = (-j) | Long.MIN_VALUE;
        }
        writeUInt64(j);
    }

    private int writeVarUIntSlow(long j) {
        int i;
        if (j >= 72057594037927936L) {
            writeUInt8((j >> 56) & 127);
            i = 2;
        } else {
            i = 1;
        }
        if (j >= 562949953421312L) {
            writeUInt8((j >> 49) & 127);
            i++;
        }
        if (j >= 4398046511104L) {
            writeUInt8((j >> 42) & 127);
            i++;
        }
        if (j >= AudioFormat.LOW_FREQUENCY_2) {
            writeUInt8((j >> 35) & 127);
            i++;
        }
        if (j >= 268435456) {
            writeUInt8((j >> 28) & 127);
            i++;
        }
        if (j >= 2097152) {
            writeUInt8((j >> 21) & 127);
            i++;
        }
        if (j >= 16384) {
            writeUInt8((j >> 14) & 127);
            i++;
        }
        if (j >= 128) {
            writeUInt8((j >> 7) & 127);
            i++;
        }
        writeUInt8((j & 127) | 128);
        return i;
    }

    private int writeVarUIntDirect2(long j) {
        Block block = this.current;
        byte[] bArr = block.data;
        int i = block.limit;
        int i2 = i + 1;
        bArr[i] = (byte) ((j >> 7) & 127);
        bArr[i2] = (byte) ((j & 127) | 128);
        block.limit = i2 + 1;
        return 2;
    }

    private int writeVarUIntDirect3(long j) {
        Block block = this.current;
        byte[] bArr = block.data;
        int i = block.limit;
        int i2 = i + 1;
        bArr[i] = (byte) ((j >> 14) & 127);
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((j >> 7) & 127);
        bArr[i3] = (byte) ((j & 127) | 128);
        block.limit = i3 + 1;
        return 3;
    }

    private int writeVarUIntDirect4(long j) {
        Block block = this.current;
        byte[] bArr = block.data;
        int i = block.limit;
        int i2 = i + 1;
        bArr[i] = (byte) ((j >> 21) & 127);
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((j >> 14) & 127);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((j >> 7) & 127);
        bArr[i4] = (byte) ((j & 127) | 128);
        block.limit = i4 + 1;
        return 4;
    }

    private int writeVarUIntDirect5(long j) {
        Block block = this.current;
        byte[] bArr = block.data;
        int i = block.limit;
        int i2 = i + 1;
        bArr[i] = (byte) ((j >> 28) & 127);
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((j >> 21) & 127);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((j >> 14) & 127);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((j >> 7) & 127);
        bArr[i5] = (byte) ((j & 127) | 128);
        block.limit = i5 + 1;
        return 5;
    }

    public int writeVarUInt(long j) {
        if (j < 128) {
            writeUInt8((j & 127) | 128);
            return 1;
        }
        if (j < 16384) {
            if (remaining() < 2) {
                return writeVarUIntSlow(j);
            }
            return writeVarUIntDirect2(j);
        }
        if (j < 2097152) {
            if (remaining() < 3) {
                return writeVarUIntSlow(j);
            }
            return writeVarUIntDirect3(j);
        }
        if (j < 268435456) {
            if (remaining() < 4) {
                return writeVarUIntSlow(j);
            }
            return writeVarUIntDirect4(j);
        }
        if (j < AudioFormat.LOW_FREQUENCY_2) {
            if (remaining() < 5) {
                return writeVarUIntSlow(j);
            }
            return writeVarUIntDirect5(j);
        }
        return writeVarUIntSlow(j);
    }

    private int writeVarIntSlow(long j, long j2) {
        int i;
        if (j >= 4611686018427387904L) {
            writeUInt8(((j >> 62) & 63) | j2);
            i = 2;
        } else {
            i = 1;
        }
        if (j >= 36028797018963968L) {
            long j3 = j >> 56;
            writeUInt8(i == 1 ? (j3 & 63) | j2 : j3 & 127);
            i++;
        }
        if (j >= 281474976710656L) {
            long j4 = j >> 49;
            writeUInt8(i == 1 ? (j4 & 63) | j2 : j4 & 127);
            i++;
        }
        if (j >= 2199023255552L) {
            long j5 = j >> 42;
            writeUInt8(i == 1 ? (j5 & 63) | j2 : j5 & 127);
            i++;
        }
        if (j >= AudioFormat.SURROUND_DIRECT_RIGHT) {
            long j6 = j >> 35;
            writeUInt8(i == 1 ? (j6 & 63) | j2 : j6 & 127);
            i++;
        }
        if (j >= 134217728) {
            long j7 = j >> 28;
            writeUInt8(i == 1 ? (j7 & 63) | j2 : j7 & 127);
            i++;
        }
        if (j >= 1048576) {
            long j8 = j >> 21;
            writeUInt8(i == 1 ? (j8 & 63) | j2 : j8 & 127);
            i++;
        }
        if (j >= 8192) {
            long j9 = j >> 14;
            writeUInt8(i == 1 ? (j9 & 63) | j2 : j9 & 127);
            i++;
        }
        if (j >= 64) {
            long j10 = j >> 7;
            writeUInt8(i == 1 ? (j10 & 63) | j2 : j10 & 127);
            i++;
        }
        writeUInt8((i == 1 ? (j & 63) | j2 : j & 127) | 128);
        return i;
    }

    public int writeVarInt(long j) {
        long j2 = j < 0 ? 64L : 0L;
        if (j < 0) {
            j = -j;
        }
        if (j < 64) {
            writeUInt8((j & 63) | 128 | j2);
            return 1;
        }
        long j3 = j < 0 ? 1L : 0L;
        int iRemaining = remaining();
        if (j < 8192 && iRemaining >= 2) {
            return writeVarUIntDirect2(j | (j3 << 13));
        }
        if (j < 1048576 && iRemaining >= 3) {
            return writeVarUIntDirect3(j | (j3 << 20));
        }
        if (j < 134217728 && iRemaining >= 4) {
            return writeVarUIntDirect4(j | (j3 << 27));
        }
        if (j < AudioFormat.SURROUND_DIRECT_RIGHT && iRemaining >= 5) {
            return writeVarUIntDirect5(j | (j3 << 34));
        }
        return writeVarIntSlow(j, j2);
    }

    public void writeVarUIntDirect1At(long j, long j2) {
        writeUInt8At(j, (j2 & 127) | 128);
    }

    private void writeVarUIntDirect2StraddlingAt(int i, int i2, long j) {
        this.blocks.get(i).data[i2] = (byte) ((j >> 7) & 127);
        this.blocks.get(i + 1).data[0] = (byte) ((j & 127) | 128);
    }

    public void writeVarUIntDirect2At(long j, long j2) {
        int iIndex = index(j);
        int iOffset = offset(j);
        if (iOffset + 2 > this.allocator.getBlockSize()) {
            writeVarUIntDirect2StraddlingAt(iIndex, iOffset, j2);
            return;
        }
        byte[] bArr = this.blocks.get(iIndex).data;
        bArr[iOffset] = (byte) ((j2 >> 7) & 127);
        bArr[iOffset + 1] = (byte) ((j2 & 127) | 128);
    }

    public void writeUInt8At(long j, long j2) {
        this.blocks.get(index(j)).data[offset(j)] = (byte) j2;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        for (int i = 0; i <= this.index; i++) {
            Block block = this.blocks.get(i);
            outputStream.write(block.data, 0, block.limit);
        }
    }

    public void writeTo(OutputStream outputStream, long j, long j2) throws IOException {
        while (j2 > 0) {
            int iIndex = index(j);
            int iOffset = offset(j);
            Block block = this.blocks.get(iIndex);
            int iMin = (int) Math.min(block.data.length - iOffset, j2);
            outputStream.write(block.data, iOffset, iMin);
            long j3 = iMin;
            j += j3;
            j2 -= j3;
        }
    }
}
