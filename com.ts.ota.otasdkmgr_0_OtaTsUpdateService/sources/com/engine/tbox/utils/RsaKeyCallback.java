package com.engine.tbox.utils;

import java.security.PrivateKey;
import java.security.PublicKey;

/* JADX INFO: loaded from: classes.dex */
public interface RsaKeyCallback {
    void generatorKey(PublicKey publicKey, PrivateKey privateKey);
}
