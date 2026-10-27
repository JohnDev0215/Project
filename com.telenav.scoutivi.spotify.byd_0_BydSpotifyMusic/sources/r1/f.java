package r1;

/* JADX INFO: compiled from: ByteArrayAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class f implements a<byte[]> {
    @Override // r1.a
    public String a() {
        return "ByteArrayPool";
    }

    @Override // r1.a
    public int c() {
        return 1;
    }

    @Override // r1.a
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public int b(byte[] bArr) {
        return bArr.length;
    }

    @Override // r1.a
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public byte[] newArray(int i7) {
        return new byte[i7];
    }
}
