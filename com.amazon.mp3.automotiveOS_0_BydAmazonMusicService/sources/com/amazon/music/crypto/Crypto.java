package com.amazon.music.crypto;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.crypto.NoSuchPaddingException;

/* JADX INFO: loaded from: classes4.dex */
public class Crypto {
    private String mAlias;
    private List<CryptoInterceptor> mCryptoInterceptors;
    private int mEncryptionAlgorithmIndex;

    public synchronized void tryReinitECB() throws Exception {
        int i = this.mEncryptionAlgorithmIndex;
        if (i == 0) {
            this.mCryptoInterceptors.set(i, new ECBCryptoInterceptor(getAlias()));
        }
    }

    public synchronized int getEncryptionAlgorithmIndex() {
        return this.mEncryptionAlgorithmIndex;
    }

    public synchronized void setEncryptionAlgorithmIndex(int i) {
        this.mEncryptionAlgorithmIndex = i;
    }

    public Crypto(String str, Boolean bool) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        ArrayList arrayList = new ArrayList();
        this.mCryptoInterceptors = arrayList;
        arrayList.add(new ECBCryptoInterceptor(str));
        this.mCryptoInterceptors.add(new GCMCryptoInterceptor(str));
        if (bool.booleanValue()) {
            this.mEncryptionAlgorithmIndex = 1;
        } else {
            this.mEncryptionAlgorithmIndex = 0;
        }
        this.mAlias = str;
    }

    public String getAlias() {
        return this.mAlias;
    }

    public synchronized void addGCMModeOnTheFly() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        if (this.mCryptoInterceptors.size() == 1) {
            this.mCryptoInterceptors.add(new GCMCryptoInterceptor(this.mAlias));
        }
        this.mEncryptionAlgorithmIndex = 1;
    }

    public synchronized String encrypt(String str) throws EncryptionException {
        String strEncrypt;
        try {
            strEncrypt = this.mCryptoInterceptors.get(this.mEncryptionAlgorithmIndex).encrypt(str);
            if (strEncrypt == null || strEncrypt.length() <= 0) {
                throw new EncryptionException();
            }
        } catch (Exception unused) {
            throw new EncryptionException();
        }
        return strEncrypt;
    }

    public synchronized String decrypt(String str) throws DecryptionException {
        String strDecrypt;
        try {
            strDecrypt = this.mCryptoInterceptors.get(this.mEncryptionAlgorithmIndex).decrypt(str);
            if (strDecrypt == null) {
                throw new DecryptionException();
            }
        } catch (Exception unused) {
            throw new DecryptionException();
        }
        return strDecrypt;
    }

    public List<CryptoInterceptor> getCryptoInterceptors() {
        return Collections.unmodifiableList(this.mCryptoInterceptors);
    }
}
