package org.apache.shiro.crypto;

import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.GCMParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class AesCipherService extends DefaultBlockCipherService {
    private static final String ALGORITHM_NAME = "AES";

    public AesCipherService() {
        super("AES");
        setMode(OperationMode.GCM);
        setStreamingMode(OperationMode.GCM);
        setPaddingScheme(PaddingScheme.NONE);
        setStreamingPaddingScheme(PaddingScheme.NONE);
    }

    @Override // org.apache.shiro.crypto.JcaCipherService
    protected AlgorithmParameterSpec createParameterSpec(byte[] bArr, boolean z) {
        if ((z && OperationMode.GCM.name().equals(getStreamingModeName())) || (!z && OperationMode.GCM.name().equals(getModeName()))) {
            return new GCMParameterSpec(getKeySize(), bArr);
        }
        return super.createParameterSpec(bArr, z);
    }
}
