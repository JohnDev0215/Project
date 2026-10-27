package com.amazonaws.mobileconnectors.iot;

import com.amazonaws.util.StringUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;

/* JADX INFO: loaded from: classes5.dex */
class PrivateKeyReader {
    private final String keyPemString;

    public PrivateKeyReader(String str) {
        this.keyPemString = str;
    }

    public PrivateKey getPrivateKey() throws InvalidKeySpecException, IOException {
        return PEM.readPrivateKey(new ByteArrayInputStream(this.keyPemString.getBytes(StringUtils.UTF8)));
    }
}
