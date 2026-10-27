package org.tukaani.xz.check;

/* JADX INFO: loaded from: classes2.dex */
public class CRC32 extends Check {
    private final java.util.zip.CRC32 state = new java.util.zip.CRC32();

    public CRC32() {
        this.size = 4;
        this.name = "CRC32";
    }

    @Override // org.tukaani.xz.check.Check
    public void update(byte[] buf, int off, int len) {
        this.state.update(buf, off, len);
    }

    @Override // org.tukaani.xz.check.Check
    public byte[] finish() {
        long value = this.state.getValue();
        byte[] buf = {(byte) value, (byte) (value >>> 8), (byte) (value >>> 16), (byte) (value >>> 24)};
        this.state.reset();
        return buf;
    }
}
