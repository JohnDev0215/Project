package defpackage;

import java.io.Serializable;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: SingleWebFeedParameters.java */
/* JADX INFO: loaded from: classes.dex */
public final class on2 {
    public static on2 c;
    public int a;
    public final Object b;

    public /* synthetic */ on2(Serializable serializable, int i) {
        this.b = serializable;
        this.a = i;
    }

    public /* synthetic */ on2(ByteBuffer byteBuffer) {
        this.b = byteBuffer;
        c = null;
    }
}
