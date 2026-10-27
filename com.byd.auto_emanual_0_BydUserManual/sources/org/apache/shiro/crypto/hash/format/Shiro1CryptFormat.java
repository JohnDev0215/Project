package org.apache.shiro.crypto.hash.format;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.StringUtils;

/* JADX INFO: loaded from: classes.dex */
public class Shiro1CryptFormat implements ModularCryptFormat, ParsableHashFormat {
    public static final String ID = "shiro1";
    public static final String MCF_PREFIX = "$shiro1$";

    @Override // org.apache.shiro.crypto.hash.format.ModularCryptFormat
    public String getId() {
        return ID;
    }

    @Override // org.apache.shiro.crypto.hash.format.HashFormat
    public String format(Hash hash) {
        if (hash == null) {
            return null;
        }
        String algorithmName = hash.getAlgorithmName();
        ByteSource salt = hash.getSalt();
        StringBuilder sbAppend = new StringBuilder(MCF_PREFIX).append(algorithmName).append(ModularCryptFormat.TOKEN_DELIMITER).append(hash.getIterations()).append(ModularCryptFormat.TOKEN_DELIMITER);
        if (salt != null) {
            sbAppend.append(salt.toBase64());
        }
        sbAppend.append(ModularCryptFormat.TOKEN_DELIMITER);
        sbAppend.append(hash.toBase64());
        return sbAppend.toString();
    }

    @Override // org.apache.shiro.crypto.hash.format.ParsableHashFormat
    public Hash parse(String str) {
        if (str == null) {
            return null;
        }
        if (!str.startsWith(MCF_PREFIX)) {
            throw new IllegalArgumentException("The argument is not a valid 'shiro1' formatted hash.");
        }
        String[] strArrSplit = str.substring(8).split("\\$");
        int length = strArrSplit.length - 1;
        int i = length - 1;
        String str2 = strArrSplit[length];
        int i2 = i - 1;
        String str3 = strArrSplit[i];
        int i3 = i2 - 1;
        String str4 = strArrSplit[i2];
        String str5 = strArrSplit[i3];
        byte[] bArrDecode = Base64.decode(str2);
        ByteSource byteSourceBytes = StringUtils.hasLength(str3) ? ByteSource.Util.bytes(Base64.decode(str3)) : null;
        try {
            int i4 = Integer.parseInt(str4);
            SimpleHash simpleHash = new SimpleHash(str5);
            simpleHash.setBytes(bArrDecode);
            if (byteSourceBytes != null) {
                simpleHash.setSalt(byteSourceBytes);
            }
            simpleHash.setIterations(i4);
            return simpleHash;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Unable to parse formatted hash string: " + str, e);
        }
    }
}
