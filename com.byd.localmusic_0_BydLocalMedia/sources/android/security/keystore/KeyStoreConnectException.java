package android.security.keystore;

import java.security.ProviderException;

/* JADX INFO: loaded from: classes3.dex */
public class KeyStoreConnectException extends ProviderException {
    public KeyStoreConnectException() {
        super("Failed to communicate with keystore service");
    }
}
