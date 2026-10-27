package org.apache.shiro.crypto.hash;

import org.apache.shiro.util.ByteSource;

/* JADX INFO: loaded from: classes.dex */
public interface Hash extends ByteSource {
    String getAlgorithmName();

    int getIterations();

    ByteSource getSalt();
}
