package com.engine.tbox.item;

import android.content.Context;
import com.engine.tbox.core.utils.LogUtils;
import com.engine.tbox.utils.AESUtils;
import com.engine.tbox.utils.RSAUtils;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* JADX INFO: loaded from: classes.dex */
public class Auth {
    private static final int BLOCK_SIZE = 16;
    private static final String IV = "NEUSOFT-TBOX-BYD";
    private static final String ORIGIN = "Hello";
    private static final String TAG = "TBoxAuth";
    private Key mAesKey;
    private AESUtils mAesUtils;
    private boolean mAuth = false;
    private boolean mAuthFeature = false;
    private PublicKey mPublicKey;
    private byte[] mRsaPublicKey;

    public Auth(Context context) {
        AESUtils aESUtils = new AESUtils(context);
        this.mAesUtils = aESUtils;
        aESUtils.prepare("NEUSOFT-TBOX-BYD");
        LogUtils.i(TAG, "Auth:" + this.mAuth, new Object[0]);
    }

    public boolean isAuthEnable() {
        return this.mAuthFeature;
    }

    public boolean resetAesKey() {
        LogUtils.d(TAG, "resetAesKey", new Object[0]);
        return this.mAesUtils.resetSecurityKey("NEUSOFT-TBOX-BYD");
    }

    public void enableAuth(boolean auth) {
        LogUtils.i(TAG, "enableAuth:" + auth, new Object[0]);
        this.mAuthFeature = auth;
    }

    public boolean isAuthed() {
        return this.mAuth;
    }

    public void setAuth(boolean auth) {
        LogUtils.i(TAG, "setAuth:" + auth, new Object[0]);
        this.mAuth = auth;
    }

    public boolean checkAuthed() {
        LogUtils.v(TAG, "mAuthFeature:" + this.mAuthFeature + " mAuth:" + this.mAuth, new Object[0]);
        return this.mAuthFeature && this.mAuth;
    }

    private byte[] aesEncrypt(byte[] input) {
        if (input.length == 0) {
            LogUtils.d(TAG, "don't need to do aesEncrypt beacuse of length is 0", new Object[0]);
            return input;
        }
        try {
            byte[] output = this.mAesUtils.encryptByAesCbcPkcs7("NEUSOFT-TBOX-BYD", input);
            return output;
        } catch (IllegalStateException | UnsupportedOperationException | InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException exp) {
            exp.printStackTrace();
            return input;
        }
    }

    private byte[] aesDecrypt(byte[] input) {
        if (input.length % 16 != 0) {
            LogUtils.e(TAG, "aesDecrypt decrypt data failed because of length:" + input.length, new Object[0]);
            return input;
        }
        if (input.length == 0) {
            LogUtils.w(TAG, "request have no data", new Object[0]);
            return input;
        }
        try {
            byte[] output = this.mAesUtils.decryptByAesCbcPKcs7("NEUSOFT-TBOX-BYD", input);
            return output;
        } catch (IllegalStateException | UnsupportedOperationException | InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException exp) {
            exp.printStackTrace();
            return null;
        }
    }

    public boolean encryptTBoxMessage(TboxMessage msg) {
        boolean auth = checkAuthed();
        LogUtils.v(TAG, "encryptTBoxMessage auth:" + auth, new Object[0]);
        if (!auth) {
            return false;
        }
        byte[] data = msg.getData();
        msg.putData(aesEncrypt(data));
        return true;
    }

    public boolean decryptTBoxMessage(TboxMessage msg) {
        boolean auth = checkAuthed();
        LogUtils.v(TAG, "decryptTBoxMessage auth:" + auth, new Object[0]);
        if (!auth) {
            return false;
        }
        byte[] data = aesDecrypt(msg.getData());
        if (data == null) {
            msg.putEmptyData();
            return false;
        }
        msg.putData(data);
        return true;
    }

    public Key getKey() {
        return this.mAesUtils.getKey();
    }

    public byte[] forceDecrypt(byte[] data) {
        return aesDecrypt(data);
    }

    public byte[] foreceEncrypt(String data) {
        return aesEncrypt(data.getBytes(StandardCharsets.UTF_8));
    }

    public PublicKey getPublicKey() {
        return this.mPublicKey;
    }

    public void generatorAesPublicKey() {
        this.mAesUtils.prepare("NEUSOFT-TBOX-BYD");
        this.mAesKey = this.mAesUtils.getKey();
        LogUtils.v(TAG, "mAesKey:" + this.mAesKey + "value:" + AESUtils.dataToHexString(this.mAesKey.getEncoded()), new Object[0]);
    }

    public byte[] getRsaEncryptAesPublicKey(byte[] rsaPublicKey) throws BadPaddingException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException {
        this.mRsaPublicKey = rsaPublicKey;
        LogUtils.v(TAG, "getRsaEncryptAesPublicKey mAesKey:" + AESUtils.dataToHexString(this.mAesKey.getEncoded()) + " iv:" + AESUtils.dataToHexString("NEUSOFT-TBOX-BYD".getBytes(StandardCharsets.UTF_8)), new Object[0]);
        return RSAUtils.rsaEncryptInputWithPublicKey(this.mRsaPublicKey, this.mAesKey.getEncoded());
    }

    public void setPublicKey(PublicKey publicKey) {
        this.mPublicKey = publicKey;
    }
}
