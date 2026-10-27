package com.google.common.hash;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import p.k1;
import p.v03;
import p.zu;

/* JADX INFO: loaded from: classes.dex */
final class MessageDigestHashFunction extends k1 implements Serializable {
    private final int bytes;
    private final MessageDigest prototype;
    private final boolean supportsClone;
    private final String toString;

    public static final class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        private final String algorithmName;
        private final int bytes;
        private final String toString;

        public SerializedForm(String str, int i, String str2) {
            this.algorithmName = str;
            this.bytes = i;
            this.toString = str2;
        }

        private Object readResolve() {
            return new MessageDigestHashFunction(this.algorithmName, this.bytes, this.toString);
        }
    }

    public MessageDigestHashFunction(String str, String str2) {
        boolean z;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            this.prototype = messageDigest;
            this.bytes = messageDigest.getDigestLength();
            this.toString = str2;
            try {
                messageDigest.clone();
                z = true;
            } catch (CloneNotSupportedException unused) {
                z = false;
            }
            this.supportsClone = z;
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    @Override // p.p11
    public final zu a() {
        if (this.supportsClone) {
            try {
                return new e((MessageDigest) this.prototype.clone(), this.bytes);
            } catch (CloneNotSupportedException unused) {
            }
        }
        try {
            return new e(MessageDigest.getInstance(this.prototype.getAlgorithm()), this.bytes);
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    public final String toString() {
        return this.toString;
    }

    public Object writeReplace() {
        return new SerializedForm(this.prototype.getAlgorithm(), this.bytes, this.toString);
    }

    public MessageDigestHashFunction(String str, int i, String str2) {
        boolean z;
        str2.getClass();
        this.toString = str2;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            this.prototype = messageDigest;
            int digestLength = messageDigest.getDigestLength();
            if (i >= 4 && i <= digestLength) {
                this.bytes = i;
                try {
                    messageDigest.clone();
                    z = true;
                } catch (CloneNotSupportedException unused) {
                    z = false;
                }
                this.supportsClone = z;
                return;
            }
            throw new IllegalArgumentException(v03.z("bytes (%s) must be >= 4 and < %s", Integer.valueOf(i), Integer.valueOf(digestLength)));
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }
}
