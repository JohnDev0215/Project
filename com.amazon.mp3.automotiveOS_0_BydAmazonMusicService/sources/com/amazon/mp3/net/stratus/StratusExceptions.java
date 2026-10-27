package com.amazon.mp3.net.stratus;

import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.util.Log;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class StratusExceptions {
    private static final String TAG = "StratusExceptions";

    public static void validateCoralResponse(StratusHttpClient client) throws StratusResourceNotFoundException, StratusServiceException {
        int statusCode = client.getStatusCode();
        if (statusCode == 200 || statusCode < 400 || statusCode > 500) {
            return;
        }
        try {
            JSONObject result = client.getResult();
            String strOptString = result.optString("__type");
            Log.error(TAG, "%s: %s", strOptString, result.optString("message"));
            if (statusCode < 500 && strOptString != null && strOptString.contains("ResourceNotFoundException")) {
                throw new StratusResourceNotFoundException(strOptString);
            }
            throw new StratusServiceException(strOptString);
        } catch (AbstractHttpClient.IncompleteResultException e) {
            Log.error(TAG, "Unable to validate coral response", e);
            throw new StratusServiceException("HttpClient said IncompleteResult");
        }
    }

    public static class StratusServiceException extends ServiceException {
        StratusServiceException(String what) {
            super(what);
        }
    }

    public static class StratusResourceNotFoundException extends ServiceException {
        StratusResourceNotFoundException(String what) {
            super(what);
        }
    }
}
