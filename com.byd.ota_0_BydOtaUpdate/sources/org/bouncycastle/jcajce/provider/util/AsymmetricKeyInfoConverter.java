package org.bouncycastle.jcajce.provider.util;

import g.a.a.p2.p;
import g.a.a.w2.u;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;

/* JADX INFO: loaded from: classes.dex */
public interface AsymmetricKeyInfoConverter {
    PrivateKey generatePrivate(p pVar) throws IOException;

    PublicKey generatePublic(u uVar) throws IOException;
}
