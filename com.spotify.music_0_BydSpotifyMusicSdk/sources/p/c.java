package p;

import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public abstract class c {
    public static final byte[] a;

    static {
        ByteString byteString = ByteString.k;
        a = qo.p("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/").d();
        qo.p("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_");
    }
}
