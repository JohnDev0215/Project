package com.amazon.mp3.net;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import com.amazon.mp3.account.auth.AuthUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.platform.data.AdpInfo;
import com.google.firebase.perf.FirebasePerformance;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class RequestSigner {
    private static final String TAG = "RequestSigner";

    public static class InvalidCorpusException extends Exception {
    }

    public static boolean signRequest(final AdpInfo adpInfo, HttpRequestBuilder request) throws InvalidCorpusException {
        CorpusBuilder corpusBuilder;
        try {
            corpusBuilder = new CorpusBuilder(adpInfo, request);
        } catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException | InvalidKeySpecException e) {
            Log.error(TAG, "Cannot sign the request: %s", e.getClass().getName());
        }
        if (!corpusBuilder.isValid()) {
            throw new InvalidCorpusException();
        }
        String corpus = corpusBuilder.getCorpus();
        Objects.requireNonNull(corpus);
        byte[] bytes = corpus.getBytes(StandardCharsets.UTF_8);
        PrivateKey key = AuthUtil.parseKey(adpInfo.getPrivateKey());
        if (key != null) {
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(key);
            signature.update(bytes);
            String strEncodeToString = Base64.encodeToString(signature.sign(), 2);
            request.addHeader("x-adp-token", adpInfo.getAdpToken()).addHeader("x-adp-alg", "SHA256withRSA:1.0").addHeader("x-adp-signature", strEncodeToString + ":" + corpusBuilder.getTimestamp());
            return true;
        }
        return false;
    }

    private static class CorpusBuilder {
        String mBody;
        StringBuilder mCorpus = new StringBuilder();
        String mCurrentTimestamp = getCurrentTimestamp();
        String mDeviceToken;
        int mMethod;
        Uri mUri;

        public CorpusBuilder(final AdpInfo adpInfo, HttpRequestBuilder httpRequest) {
            this.mMethod = httpRequest.getMethod();
            this.mUri = httpRequest.getRequestUri();
            this.mBody = httpRequest.getBody();
            this.mDeviceToken = adpInfo.getAdpToken();
        }

        private String getCurrentTimestamp() {
            Locale locale = Locale.US;
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", locale).format(new GregorianCalendar(locale).getTime());
        }

        public String getCorpus() {
            if (!isValid()) {
                return null;
            }
            this.mCorpus.append(this.mMethod == 1 ? FirebasePerformance.HttpMethod.POST : FirebasePerformance.HttpMethod.GET);
            this.mCorpus.append('\n');
            this.mCorpus.append(this.mUri.getEncodedPath());
            if (this.mUri.getEncodedQuery() != null) {
                this.mCorpus.append('?');
                this.mCorpus.append(this.mUri.getEncodedQuery());
            }
            this.mCorpus.append('\n');
            this.mCorpus.append(this.mCurrentTimestamp);
            this.mCorpus.append('\n');
            this.mCorpus.append(this.mBody);
            this.mCorpus.append('\n');
            this.mCorpus.append(this.mDeviceToken);
            return this.mCorpus.toString();
        }

        public boolean isValid() {
            int i = this.mMethod;
            return ((i != 0 && i != 1) || this.mUri == null || TextUtils.isEmpty(this.mCurrentTimestamp) || TextUtils.isEmpty(this.mBody) || TextUtils.isEmpty(this.mDeviceToken)) ? false : true;
        }

        public String getTimestamp() {
            return this.mCurrentTimestamp;
        }
    }
}
