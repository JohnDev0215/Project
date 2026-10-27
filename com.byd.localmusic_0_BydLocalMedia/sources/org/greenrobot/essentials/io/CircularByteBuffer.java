package org.greenrobot.essentials.io;

/* JADX INFO: loaded from: classes5.dex */
public class CircularByteBuffer {
    private int available;
    private final byte[] buffer;
    private final int capacity;
    private int idxGet;
    private int idxPut;

    public CircularByteBuffer() {
        this(8192);
    }

    public CircularByteBuffer(int i) {
        this.capacity = i;
        this.buffer = new byte[i];
    }

    public synchronized void clear() {
        this.available = 0;
        this.idxPut = 0;
        this.idxGet = 0;
    }

    public synchronized int get() {
        if (this.available == 0) {
            return -1;
        }
        byte b = this.buffer[this.idxGet];
        this.idxGet = (this.idxGet + 1) % this.capacity;
        this.available--;
        return b;
    }

    public int get(byte[] bArr) {
        return get(bArr, 0, bArr.length);
    }

    public synchronized int get(byte[] bArr, int i, int i2) {
        if (this.available == 0) {
            return 0;
        }
        int iMin = Math.min((this.idxGet < this.idxPut ? this.idxPut : this.capacity) - this.idxGet, i2);
        System.arraycopy(this.buffer, this.idxGet, bArr, i, iMin);
        int i3 = this.idxGet + iMin;
        this.idxGet = i3;
        if (i3 == this.capacity) {
            int iMin2 = Math.min(i2 - iMin, this.idxPut);
            if (iMin2 > 0) {
                System.arraycopy(this.buffer, 0, bArr, i + iMin, iMin2);
                this.idxGet = iMin2;
                iMin += iMin2;
            } else {
                this.idxGet = 0;
            }
        }
        this.available -= iMin;
        return iMin;
    }

    public synchronized boolean put(byte b) {
        if (this.available == this.capacity) {
            return false;
        }
        this.buffer[this.idxPut] = b;
        this.idxPut = (this.idxPut + 1) % this.capacity;
        this.available++;
        return true;
    }

    public int put(byte[] bArr) {
        return put(bArr, 0, bArr.length);
    }

    public synchronized int put(byte[] bArr, int i, int i2) {
        if (this.available == this.capacity) {
            return 0;
        }
        int iMin = Math.min((this.idxPut < this.idxGet ? this.idxGet : this.capacity) - this.idxPut, i2);
        System.arraycopy(bArr, i, this.buffer, this.idxPut, iMin);
        int i3 = this.idxPut + iMin;
        this.idxPut = i3;
        if (i3 == this.capacity) {
            int iMin2 = Math.min(i2 - iMin, this.idxGet);
            if (iMin2 > 0) {
                System.arraycopy(bArr, i + iMin, this.buffer, 0, iMin2);
                this.idxPut = iMin2;
                iMin += iMin2;
            } else {
                this.idxPut = 0;
            }
        }
        this.available += iMin;
        return iMin;
    }

    public synchronized int peek() {
        return this.available > 0 ? this.buffer[this.idxGet] : (byte) -1;
    }

    public synchronized int skip(int i) {
        if (i > this.available) {
            i = this.available;
        }
        this.idxGet = (this.idxGet + i) % this.capacity;
        this.available -= i;
        return i;
    }

    public int capacity() {
        return this.capacity;
    }

    public synchronized int available() {
        return this.available;
    }

    public synchronized int free() {
        return this.capacity - this.available;
    }

    public synchronized byte[] rawBuffer() {
        return this.buffer;
    }

    public synchronized int rawIndexGet() {
        return this.idxGet;
    }

    public synchronized int rawIndexPut() {
        return this.idxPut;
    }
}
