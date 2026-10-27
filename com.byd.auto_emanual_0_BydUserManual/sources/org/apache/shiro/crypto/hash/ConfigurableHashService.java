package org.apache.shiro.crypto.hash;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.util.ByteSource;

/* JADX INFO: loaded from: classes.dex */
public interface ConfigurableHashService extends HashService {
    void setHashAlgorithmName(String str);

    void setHashIterations(int i);

    void setPrivateSalt(ByteSource byteSource);

    void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator);
}
