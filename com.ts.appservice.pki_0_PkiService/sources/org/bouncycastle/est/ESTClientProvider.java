package org.bouncycastle.est;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public interface ESTClientProvider {
    boolean isTrusted();

    ESTClient makeClient();
}
