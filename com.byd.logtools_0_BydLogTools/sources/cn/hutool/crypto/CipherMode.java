package cn.hutool.crypto;

/* JADX INFO: loaded from: classes.dex */
public enum CipherMode {
    encrypt(1),
    decrypt(2),
    wrap(3),
    unwrap(4);

    private final int value;

    CipherMode(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
