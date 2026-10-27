package org.apache.shiro.crypto;

import org.apache.shiro.util.ByteSource;

/* JADX INFO: loaded from: classes.dex */
public interface RandomNumberGenerator {
    ByteSource nextBytes();

    ByteSource nextBytes(int i);
}
