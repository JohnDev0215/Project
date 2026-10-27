package org.apache.shiro.crypto;

/* JADX INFO: loaded from: classes.dex */
public class BlowfishCipherService extends DefaultBlockCipherService {
    private static final String ALGORITHM_NAME = "Blowfish";
    private static final int BLOCK_SIZE = 64;

    public BlowfishCipherService() {
        super(ALGORITHM_NAME);
        setInitializationVectorSize(64);
    }
}
