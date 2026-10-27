package org.bouncycastle.crypto.engines;

/* JADX INFO: loaded from: classes4.dex */
public class SEEDWrapEngine extends RFC3394WrapEngine {
    public SEEDWrapEngine() {
        super(new SEEDEngine());
    }
}
