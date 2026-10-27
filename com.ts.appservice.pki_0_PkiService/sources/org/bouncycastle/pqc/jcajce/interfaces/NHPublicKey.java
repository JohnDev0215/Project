package org.bouncycastle.pqc.jcajce.interfaces;

import java.security.PublicKey;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public interface NHPublicKey extends NHKey, PublicKey {
    byte[] getPublicData();
}
