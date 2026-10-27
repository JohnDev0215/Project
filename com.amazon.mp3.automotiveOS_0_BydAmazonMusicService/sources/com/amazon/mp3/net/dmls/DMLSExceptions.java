package com.amazon.mp3.net.dmls;

import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class DMLSExceptions {
    private static final String TAG = "DMLSExceptions";

    public static class InvalidContentIdException extends DMLSException {
    }

    public static void validateCoralResponse(DMLSHttpClient client) throws DMLSException {
        StatusCode fromString;
        String str = "DMLS Logging: Incomplete Result for response";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject = client.getResult();
            fromString = StatusCode.getFromString(jSONObject.getString("statusCode"));
            String strOptString = jSONObject.optString("statusMessage");
            if (fromString != StatusCode.SUCCESS) {
                Log.warning(TAG, "DMLS Logging: validateCoralResponse - response: %s", jSONObject.toString());
            }
            str = strOptString;
        } catch (AbstractHttpClient.IncompleteResultException e) {
            Log.warning(TAG, "DMLS Logging: Incomplete Result for response", e);
            fromString = StatusCode.UNKNOWN;
        } catch (JSONException e2) {
            Log.warning(TAG, "DMLS Logging: Invalid JSON in response", e2);
            fromString = StatusCode.UNKNOWN;
            str = "Invalid JSON in response: " + jSONObject.toString();
        }
        if (StatusCode.isError(fromString)) {
            try {
            } catch (JSONException e3) {
                Log.warning(TAG, "DMLS Logging: Error parsing JSON of StreamingConcurrencyStatusResponse:", e3);
                throw new StatusCodeException(fromString, str, null);
            }
        }
    }

    public static void validateContentResponse(ContentResponse response) throws DMLSException {
        StatusCode status = response.getStatus();
        String statusMessage = response.getStatusMessage();
        if (StatusCode.isError(status)) {
            throw new StatusCodeException(status, statusMessage);
        }
    }

    public static class DMLSException extends ServiceException {
        DMLSException(String what) {
            super(what);
        }

        DMLSException() {
        }
    }

    public static class StatusCodeException extends DMLSException {
        private IStreamingConcurrencyStatusResponse mResponse;
        private StatusCode mStatusCode;

        StatusCodeException(StatusCode code, String what) {
            super(code.toString() + ": " + what);
            this.mStatusCode = code;
        }

        StatusCodeException(StatusCode code, String what, IStreamingConcurrencyStatusResponse response) {
            super(code.toString() + ": " + what);
            this.mStatusCode = code;
            this.mResponse = response;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public StatusCodeException() {
            StatusCode statusCode = StatusCode.UNKNOWN;
            super(statusCode.toString());
            this.mStatusCode = statusCode;
        }

        public StatusCode getStatus() {
            return this.mStatusCode;
        }

        public IStreamingConcurrencyStatusResponse getResponse() {
            return this.mResponse;
        }
    }

    public static class MaxRetryException extends DMLSException {
        MaxRetryException() {
        }
    }
}
