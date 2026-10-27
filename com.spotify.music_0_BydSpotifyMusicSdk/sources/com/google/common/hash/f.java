package com.google.common.hash;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import p.o11;
import p.zu;

/* JADX INFO: loaded from: classes.dex */
public final class f extends zu {
    public long l;
    public long m;
    public final ByteBuffer i = ByteBuffer.allocate(23).order(ByteOrder.LITTLE_ENDIAN);
    public final int j = 16;
    public final int k = 16;
    public int n = 0;

    public f(int i) {
        long j = i;
        this.l = j;
        this.m = j;
    }

    public final void H0() {
        ByteBuffer byteBuffer = this.i;
        byteBuffer.flip();
        while (byteBuffer.remaining() >= this.k) {
            I0(byteBuffer);
        }
        byteBuffer.compact();
    }

    public final void I0(ByteBuffer byteBuffer) {
        long j = byteBuffer.getLong();
        long j2 = byteBuffer.getLong();
        long jRotateLeft = (Long.rotateLeft(j * (-8663945395140668459L), 31) * 5545529020109919103L) ^ this.l;
        this.l = jRotateLeft;
        long jRotateLeft2 = Long.rotateLeft(jRotateLeft, 27);
        long j3 = this.m;
        this.l = ((jRotateLeft2 + j3) * 5) + 1390208809;
        long jRotateLeft3 = (Long.rotateLeft(j2 * 5545529020109919103L, 33) * (-8663945395140668459L)) ^ j3;
        this.m = jRotateLeft3;
        this.m = ((Long.rotateLeft(jRotateLeft3, 31) + this.l) * 5) + 944331445;
        this.n += 16;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // p.zu
    public final o11 l0() {
        long j;
        long j2;
        long j3;
        long j4;
        long j5;
        long j6;
        long j7;
        H0();
        ByteBuffer byteBuffer = this.i;
        byteBuffer.flip();
        if (byteBuffer.remaining() > 0) {
            this.n = byteBuffer.remaining() + this.n;
            long j8 = 0;
            switch (byteBuffer.remaining()) {
                case 1:
                    j = 0;
                    j7 = j ^ ((long) (byteBuffer.get(0) & 255));
                    this.l = (Long.rotateLeft(j7 * (-8663945395140668459L), 31) * 5545529020109919103L) ^ this.l;
                    this.m ^= Long.rotateLeft(j8 * 5545529020109919103L, 33) * (-8663945395140668459L);
                    byteBuffer.position(byteBuffer.limit());
                    break;
                case 2:
                    j2 = 0;
                    j = j2 ^ (((long) (byteBuffer.get(1) & 255)) << 8);
                    j7 = j ^ ((long) (byteBuffer.get(0) & 255));
                    this.l = (Long.rotateLeft(j7 * (-8663945395140668459L), 31) * 5545529020109919103L) ^ this.l;
                    this.m ^= Long.rotateLeft(j8 * 5545529020109919103L, 33) * (-8663945395140668459L);
                    byteBuffer.position(byteBuffer.limit());
                    break;
                case 3:
                    j3 = 0;
                    j2 = j3 ^ (((long) (byteBuffer.get(2) & 255)) << 16);
                    j = j2 ^ (((long) (byteBuffer.get(1) & 255)) << 8);
                    j7 = j ^ ((long) (byteBuffer.get(0) & 255));
                    this.l = (Long.rotateLeft(j7 * (-8663945395140668459L), 31) * 5545529020109919103L) ^ this.l;
                    this.m ^= Long.rotateLeft(j8 * 5545529020109919103L, 33) * (-8663945395140668459L);
                    byteBuffer.position(byteBuffer.limit());
                    break;
                case 4:
                    j4 = 0;
                    j3 = j4 ^ (((long) (byteBuffer.get(3) & 255)) << 24);
                    j2 = j3 ^ (((long) (byteBuffer.get(2) & 255)) << 16);
                    j = j2 ^ (((long) (byteBuffer.get(1) & 255)) << 8);
                    j7 = j ^ ((long) (byteBuffer.get(0) & 255));
                    this.l = (Long.rotateLeft(j7 * (-8663945395140668459L), 31) * 5545529020109919103L) ^ this.l;
                    this.m ^= Long.rotateLeft(j8 * 5545529020109919103L, 33) * (-8663945395140668459L);
                    byteBuffer.position(byteBuffer.limit());
                    break;
                case 5:
                    j5 = 0;
                    j4 = j5 ^ (((long) (byteBuffer.get(4) & 255)) << 32);
                    j3 = j4 ^ (((long) (byteBuffer.get(3) & 255)) << 24);
                    j2 = j3 ^ (((long) (byteBuffer.get(2) & 255)) << 16);
                    j = j2 ^ (((long) (byteBuffer.get(1) & 255)) << 8);
                    j7 = j ^ ((long) (byteBuffer.get(0) & 255));
                    this.l = (Long.rotateLeft(j7 * (-8663945395140668459L), 31) * 5545529020109919103L) ^ this.l;
                    this.m ^= Long.rotateLeft(j8 * 5545529020109919103L, 33) * (-8663945395140668459L);
                    byteBuffer.position(byteBuffer.limit());
                    break;
                case 6:
                    j6 = 0;
                    j5 = (((long) (byteBuffer.get(5) & 255)) << 40) ^ j6;
                    j4 = j5 ^ (((long) (byteBuffer.get(4) & 255)) << 32);
                    j3 = j4 ^ (((long) (byteBuffer.get(3) & 255)) << 24);
                    j2 = j3 ^ (((long) (byteBuffer.get(2) & 255)) << 16);
                    j = j2 ^ (((long) (byteBuffer.get(1) & 255)) << 8);
                    j7 = j ^ ((long) (byteBuffer.get(0) & 255));
                    this.l = (Long.rotateLeft(j7 * (-8663945395140668459L), 31) * 5545529020109919103L) ^ this.l;
                    this.m ^= Long.rotateLeft(j8 * 5545529020109919103L, 33) * (-8663945395140668459L);
                    byteBuffer.position(byteBuffer.limit());
                    break;
                case 7:
                    j6 = ((long) (byteBuffer.get(6) & 255)) << 48;
                    j5 = (((long) (byteBuffer.get(5) & 255)) << 40) ^ j6;
                    j4 = j5 ^ (((long) (byteBuffer.get(4) & 255)) << 32);
                    j3 = j4 ^ (((long) (byteBuffer.get(3) & 255)) << 24);
                    j2 = j3 ^ (((long) (byteBuffer.get(2) & 255)) << 16);
                    j = j2 ^ (((long) (byteBuffer.get(1) & 255)) << 8);
                    j7 = j ^ ((long) (byteBuffer.get(0) & 255));
                    this.l = (Long.rotateLeft(j7 * (-8663945395140668459L), 31) * 5545529020109919103L) ^ this.l;
                    this.m ^= Long.rotateLeft(j8 * 5545529020109919103L, 33) * (-8663945395140668459L);
                    byteBuffer.position(byteBuffer.limit());
                    break;
                case 8:
                    j7 = byteBuffer.getLong();
                    this.l = (Long.rotateLeft(j7 * (-8663945395140668459L), 31) * 5545529020109919103L) ^ this.l;
                    this.m ^= Long.rotateLeft(j8 * 5545529020109919103L, 33) * (-8663945395140668459L);
                    byteBuffer.position(byteBuffer.limit());
                    break;
                case 9:
                    j8 ^= (long) (byteBuffer.get(8) & 255);
                    j7 = byteBuffer.getLong();
                    this.l = (Long.rotateLeft(j7 * (-8663945395140668459L), 31) * 5545529020109919103L) ^ this.l;
                    this.m ^= Long.rotateLeft(j8 * 5545529020109919103L, 33) * (-8663945395140668459L);
                    byteBuffer.position(byteBuffer.limit());
                    break;
                case 10:
                    j8 ^= ((long) (byteBuffer.get(9) & 255)) << 8;
                    j8 ^= (long) (byteBuffer.get(8) & 255);
                    j7 = byteBuffer.getLong();
                    this.l = (Long.rotateLeft(j7 * (-8663945395140668459L), 31) * 5545529020109919103L) ^ this.l;
                    this.m ^= Long.rotateLeft(j8 * 5545529020109919103L, 33) * (-8663945395140668459L);
                    byteBuffer.position(byteBuffer.limit());
                    break;
                case 11:
                    j8 ^= ((long) (byteBuffer.get(10) & 255)) << 16;
                    j8 ^= ((long) (byteBuffer.get(9) & 255)) << 8;
                    j8 ^= (long) (byteBuffer.get(8) & 255);
                    j7 = byteBuffer.getLong();
                    this.l = (Long.rotateLeft(j7 * (-8663945395140668459L), 31) * 5545529020109919103L) ^ this.l;
                    this.m ^= Long.rotateLeft(j8 * 5545529020109919103L, 33) * (-8663945395140668459L);
                    byteBuffer.position(byteBuffer.limit());
                    break;
                case 12:
                    j8 ^= ((long) (byteBuffer.get(11) & 255)) << 24;
                    j8 ^= ((long) (byteBuffer.get(10) & 255)) << 16;
                    j8 ^= ((long) (byteBuffer.get(9) & 255)) << 8;
                    j8 ^= (long) (byteBuffer.get(8) & 255);
                    j7 = byteBuffer.getLong();
                    this.l = (Long.rotateLeft(j7 * (-8663945395140668459L), 31) * 5545529020109919103L) ^ this.l;
                    this.m ^= Long.rotateLeft(j8 * 5545529020109919103L, 33) * (-8663945395140668459L);
                    byteBuffer.position(byteBuffer.limit());
                    break;
                case 13:
                    j8 ^= ((long) (byteBuffer.get(12) & 255)) << 32;
                    j8 ^= ((long) (byteBuffer.get(11) & 255)) << 24;
                    j8 ^= ((long) (byteBuffer.get(10) & 255)) << 16;
                    j8 ^= ((long) (byteBuffer.get(9) & 255)) << 8;
                    j8 ^= (long) (byteBuffer.get(8) & 255);
                    j7 = byteBuffer.getLong();
                    this.l = (Long.rotateLeft(j7 * (-8663945395140668459L), 31) * 5545529020109919103L) ^ this.l;
                    this.m ^= Long.rotateLeft(j8 * 5545529020109919103L, 33) * (-8663945395140668459L);
                    byteBuffer.position(byteBuffer.limit());
                    break;
                case 14:
                    j8 ^= ((long) (byteBuffer.get(13) & 255)) << 40;
                    j8 ^= ((long) (byteBuffer.get(12) & 255)) << 32;
                    j8 ^= ((long) (byteBuffer.get(11) & 255)) << 24;
                    j8 ^= ((long) (byteBuffer.get(10) & 255)) << 16;
                    j8 ^= ((long) (byteBuffer.get(9) & 255)) << 8;
                    j8 ^= (long) (byteBuffer.get(8) & 255);
                    j7 = byteBuffer.getLong();
                    this.l = (Long.rotateLeft(j7 * (-8663945395140668459L), 31) * 5545529020109919103L) ^ this.l;
                    this.m ^= Long.rotateLeft(j8 * 5545529020109919103L, 33) * (-8663945395140668459L);
                    byteBuffer.position(byteBuffer.limit());
                    break;
                case 15:
                    j8 = ((long) (byteBuffer.get(14) & 255)) << 48;
                    j8 ^= ((long) (byteBuffer.get(13) & 255)) << 40;
                    j8 ^= ((long) (byteBuffer.get(12) & 255)) << 32;
                    j8 ^= ((long) (byteBuffer.get(11) & 255)) << 24;
                    j8 ^= ((long) (byteBuffer.get(10) & 255)) << 16;
                    j8 ^= ((long) (byteBuffer.get(9) & 255)) << 8;
                    j8 ^= (long) (byteBuffer.get(8) & 255);
                    j7 = byteBuffer.getLong();
                    this.l = (Long.rotateLeft(j7 * (-8663945395140668459L), 31) * 5545529020109919103L) ^ this.l;
                    this.m ^= Long.rotateLeft(j8 * 5545529020109919103L, 33) * (-8663945395140668459L);
                    byteBuffer.position(byteBuffer.limit());
                    break;
                default:
                    throw new AssertionError("Should never get here.");
            }
        }
        long j9 = this.l;
        long j10 = this.n;
        long j11 = j9 ^ j10;
        long j12 = j10 ^ this.m;
        long j13 = j11 + j12;
        long j14 = j12 + j13;
        long j15 = (j13 ^ (j13 >>> 33)) * (-49064778989728563L);
        long j16 = (j15 ^ (j15 >>> 33)) * (-4265267296055464877L);
        long j17 = (j14 ^ (j14 >>> 33)) * (-49064778989728563L);
        long j18 = (j17 ^ (j17 >>> 33)) * (-4265267296055464877L);
        long j19 = j18 ^ (j18 >>> 33);
        long j20 = (j16 ^ (j16 >>> 33)) + j19;
        this.l = j20;
        this.m = j19 + j20;
        byte[] bArrArray = ByteBuffer.wrap(new byte[16]).order(ByteOrder.LITTLE_ENDIAN).putLong(this.l).putLong(this.m).array();
        char[] cArr = o11.c;
        return new HashCode$BytesHashCode(bArrArray);
    }

    @Override // p.zu
    public final zu y0(byte[] bArr, int i) {
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr, 0, i).order(ByteOrder.LITTLE_ENDIAN);
        int iRemaining = byteBufferOrder.remaining();
        ByteBuffer byteBuffer = this.i;
        if (iRemaining <= byteBuffer.remaining()) {
            byteBuffer.put(byteBufferOrder);
            if (byteBuffer.remaining() < 8) {
                H0();
            }
        } else {
            int iPosition = this.j - byteBuffer.position();
            for (int i2 = 0; i2 < iPosition; i2++) {
                byteBuffer.put(byteBufferOrder.get());
            }
            H0();
            while (byteBufferOrder.remaining() >= this.k) {
                I0(byteBufferOrder);
            }
            byteBuffer.put(byteBufferOrder);
        }
        return this;
    }

    @Override // p.zu
    public final zu z0(char c) {
        ByteBuffer byteBuffer = this.i;
        byteBuffer.putChar(c);
        if (byteBuffer.remaining() < 8) {
            H0();
        }
        return this;
    }
}
