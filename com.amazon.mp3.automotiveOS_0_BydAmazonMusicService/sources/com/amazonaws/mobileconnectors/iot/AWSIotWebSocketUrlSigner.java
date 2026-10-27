package com.amazonaws.mobileconnectors.iot;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSSessionCredentials;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.auth.SigningAlgorithm;
import com.amazonaws.regions.Region;
import com.amazonaws.util.BinaryUtils;
import com.amazonaws.util.DateUtils;
import com.amazonaws.util.StringUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Date;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes5.dex */
class AWSIotWebSocketUrlSigner {
    private static final String ALGORITHM = "AWS4-HMAC-SHA256";
    private static final String CANONICAL_URI = "/mqtt";
    private static final String DATE_PATTERN = "yyyyMMdd";
    private static final String KEY_PREFIX = "AWS4";
    private static final String METHOD = "GET";
    private static final String TERMINATOR = "aws4_request";
    private static final String TIME_PATTERN = "yyyyMMdd'T'HHmmss'Z'";
    private String signerServiceName;

    public AWSIotWebSocketUrlSigner(String str) {
        this.signerServiceName = str;
    }

    public String getSignedUrl(String str, AWSCredentials aWSCredentials, long j, Region region) {
        if (aWSCredentials instanceof AnonymousAWSCredentials) {
            throw new IllegalArgumentException("Credentials cannot be Anonymous");
        }
        String name = region.getName();
        AWSCredentials aWSCredentialsSanitizeCredentials = sanitizeCredentials(aWSCredentials);
        String amzDate = getAmzDate(j);
        String dateStamp = getDateStamp(j);
        String str2 = dateStamp + "/" + name + "/" + this.signerServiceName + "/aws4_request";
        StringBuilder sb = new StringBuilder();
        sb.append("X-Amz-Algorithm=");
        sb.append(ALGORITHM);
        sb.append("&X-Amz-Credential=");
        try {
            String str3 = aWSCredentialsSanitizeCredentials.getAWSAccessKeyId() + "/" + str2;
            Charset charset = StringUtils.UTF8;
            sb.append(URLEncoder.encode(str3, charset.name()));
            sb.append("&X-Amz-Date=");
            sb.append(amzDate);
            sb.append("&X-Amz-SignedHeaders=host");
            String hex = BinaryUtils.toHex(sign(("AWS4-HMAC-SHA256\n" + amzDate + "\n" + str2 + "\n" + BinaryUtils.toHex(hash("GET\n/mqtt\n" + sb.toString() + "\n" + ("host:" + str + "\n") + "\nhost\n" + BinaryUtils.toHex(hash(""))))).getBytes(), getSigningKey(dateStamp, name, this.signerServiceName, aWSCredentialsSanitizeCredentials), SigningAlgorithm.HmacSHA256));
            sb.append("&X-Amz-Signature=");
            sb.append(hex);
            String str4 = "wss://" + str + CANONICAL_URI + "?" + sb.toString();
            if (!(aWSCredentials instanceof AWSSessionCredentials)) {
                return str4;
            }
            try {
                return str4 + "&X-Amz-Security-Token=" + URLEncoder.encode(((AWSSessionCredentials) aWSCredentials).getSessionToken(), charset.name());
            } catch (UnsupportedEncodingException e) {
                throw new AmazonClientException("Error encoding URL when appending session token to URL", e);
            }
        } catch (UnsupportedEncodingException e2) {
            throw new AmazonClientException("Error encoding URL when building WebSocket URL", e2);
        }
    }

    private byte[] getSigningKey(String str, String str2, String str3, AWSCredentials aWSCredentials) {
        byte[] bytes = (KEY_PREFIX + aWSCredentials.getAWSSecretKey()).getBytes();
        SigningAlgorithm signingAlgorithm = SigningAlgorithm.HmacSHA256;
        return sign(TERMINATOR, sign(str3, sign(str2, sign(str, bytes, signingAlgorithm), signingAlgorithm), signingAlgorithm), signingAlgorithm);
    }

    private String getAmzDate(long j) {
        return DateUtils.format("yyyyMMdd'T'HHmmss'Z'", new Date(j));
    }

    private String getDateStamp(long j) {
        return DateUtils.format(DATE_PATTERN, new Date(j));
    }

    AWSCredentials sanitizeCredentials(AWSCredentials aWSCredentials) {
        String aWSAccessKeyId = aWSCredentials.getAWSAccessKeyId();
        String aWSSecretKey = aWSCredentials.getAWSSecretKey();
        boolean z = aWSCredentials instanceof AWSSessionCredentials;
        String sessionToken = z ? ((AWSSessionCredentials) aWSCredentials).getSessionToken() : null;
        if (aWSSecretKey != null) {
            aWSSecretKey = aWSSecretKey.trim();
        }
        if (aWSAccessKeyId != null) {
            aWSAccessKeyId = aWSAccessKeyId.trim();
        }
        if (sessionToken != null) {
            sessionToken = sessionToken.trim();
        }
        if (z) {
            return new BasicSessionCredentials(aWSAccessKeyId, aWSSecretKey, sessionToken);
        }
        return new BasicAWSCredentials(aWSAccessKeyId, aWSSecretKey);
    }

    byte[] hash(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes(StringUtils.UTF8));
            return messageDigest.digest();
        } catch (Exception e) {
            throw new AmazonClientException("Unable to compute hash while signing request: " + e.getMessage(), e);
        }
    }

    byte[] sign(String str, byte[] bArr, SigningAlgorithm signingAlgorithm) {
        try {
            return sign(str.getBytes(StringUtils.UTF8), bArr, signingAlgorithm);
        } catch (Exception e) {
            throw new AmazonClientException("Unable to calculate a request signature: " + e.getMessage(), e);
        }
    }

    byte[] sign(byte[] bArr, byte[] bArr2, SigningAlgorithm signingAlgorithm) {
        try {
            Mac mac = Mac.getInstance(signingAlgorithm.toString());
            mac.init(new SecretKeySpec(bArr2, signingAlgorithm.toString()));
            return mac.doFinal(bArr);
        } catch (Exception e) {
            throw new AmazonClientException("Unable to calculate a request signature: " + e.getMessage(), e);
        }
    }
}
