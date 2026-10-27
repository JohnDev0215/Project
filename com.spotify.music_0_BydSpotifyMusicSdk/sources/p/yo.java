package p;

import com.google.protobuf.ByteString;

/* JADX INFO: loaded from: classes.dex */
public final class yo extends zo {
    public int c = 0;
    public final int e;
    public final /* synthetic */ ByteString k;

    public yo(ByteString byteString) {
        this.k = byteString;
        this.e = byteString.size();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.c < this.e;
    }
}
