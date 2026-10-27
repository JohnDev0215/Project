package org.bouncycastle.est.jcajce;

import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public interface SSLSocketFactoryCreator {
    SSLSocketFactory createFactory();

    boolean isTrusted();
}
