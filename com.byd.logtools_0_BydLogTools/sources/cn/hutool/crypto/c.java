package cn.hutool.crypto;

import java.security.Provider;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* JADX INFO: compiled from: ProviderFactory.java */
/* JADX INFO: loaded from: classes.dex */
public class c {
    public static Provider a() {
        return new BouncyCastleProvider();
    }
}
