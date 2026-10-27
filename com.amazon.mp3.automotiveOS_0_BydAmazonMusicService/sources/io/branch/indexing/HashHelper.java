package io.branch.indexing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes7.dex */
class HashHelper {
    MessageDigest messageDigest_;

    HashHelper() {
        try {
            this.messageDigest_ = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException unused) {
        }
    }

    String hashContent(String str) {
        MessageDigest messageDigest = this.messageDigest_;
        if (messageDigest == null) {
            return "";
        }
        messageDigest.reset();
        this.messageDigest_.update(str.getBytes());
        return new String(this.messageDigest_.digest());
    }
}
