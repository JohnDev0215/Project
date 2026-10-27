package android.net.netlink;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class StructNfGenMsg {
    public final byte nfgen_family;
    public final byte version = 0;
    public final short res_id = 0;

    public StructNfGenMsg(byte b) {
        this.nfgen_family = b;
    }

    public void pack(ByteBuffer byteBuffer) {
        byteBuffer.put(this.nfgen_family);
        byteBuffer.put(this.version);
        byteBuffer.putShort(this.res_id);
    }
}
