package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class Struct {
    public ByteBuffer bb;
    public int bb_pos;

    public void __reset() {
        __reset(0, null);
    }

    public void __reset(int i, ByteBuffer byteBuffer) {
        this.bb = byteBuffer;
        if (byteBuffer == null) {
            i = 0;
        }
        this.bb_pos = i;
    }
}
