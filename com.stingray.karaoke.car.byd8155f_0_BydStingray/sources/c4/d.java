package c4;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: classes.dex */
public final class d extends ByteArrayOutputStream {
    public final /* synthetic */ e l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(e eVar, int i7) {
        super(i7);
        this.l = eVar;
    }

    @Override // java.io.ByteArrayOutputStream
    public final String toString() {
        int i7 = ((ByteArrayOutputStream) this).count;
        if (i7 > 0 && ((ByteArrayOutputStream) this).buf[i7 - 1] == 13) {
            i7--;
        }
        try {
            return new String(((ByteArrayOutputStream) this).buf, 0, i7, this.l.f12395m.name());
        } catch (UnsupportedEncodingException e10) {
            throw new AssertionError(e10);
        }
    }
}
