package org.bouncycastle.crypto.engines;

/* JADX INFO: loaded from: classes4.dex */
public class AESWrapEngine extends RFC3394WrapEngine {
    public AESWrapEngine() {
        super(new AESEngine());
    }
}
