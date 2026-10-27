package defpackage;

import java.nio.charset.StandardCharsets;

/* JADX INFO: compiled from: NetworkTrafficAnnotationTag.java */
/* JADX INFO: loaded from: classes3.dex */
public final class yl1 {
    public static final /* synthetic */ int b = 0;
    public final int a;

    static {
        new yl1("undefined");
        new yl1("undefined");
        new yl1("test");
    }

    public yl1(String str) {
        long j = 0;
        for (byte b2 : str.getBytes(StandardCharsets.UTF_8)) {
            j = ((j * 31) + ((long) b2)) % 138003713;
        }
        this.a = (int) j;
    }
}
