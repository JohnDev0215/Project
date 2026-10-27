package com.amazon.mp3.net;

import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class CoralHttpClient extends RequestIdInjectingHttpClient {
    private static final String TAG = "CoralHttpClient";
    private final Protocol mProtocol;

    public enum Protocol {
        RPCv0,
        RPCv1,
        AWS_JSONv1,
        AWS_JSONv1_1,
        AWS_QUERY,
        EVERYNIGHT
    }

    protected ServiceException getMappedException(String type, String message) {
        return null;
    }

    public CoralHttpClient() {
        this(Protocol.RPCv1);
    }

    public CoralHttpClient(Protocol protocol) {
        this.mProtocol = protocol;
    }

    @Override // com.amazon.mp3.net.AbstractHttpClient
    protected void validateResponse() throws ServiceException {
        if (getStatusCode() != 200) {
            try {
                int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$net$CoralHttpClient$Protocol[this.mProtocol.ordinal()];
                if (i == 1) {
                    validateRpcV1Response(getResult());
                } else if (i != 2) {
                } else {
                    validateAwsResponse(getResult());
                }
            } catch (AbstractHttpClient.IncompleteResultException unused) {
            } catch (JSONException e) {
                try {
                    Log.debug(TAG, "A Coral call failed. Response: " + getResult());
                } catch (AbstractHttpClient.IncompleteResultException unused2) {
                }
                throw new ServiceException("Coral response with error status code but invalid error message", e);
            }
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.net.CoralHttpClient$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$net$CoralHttpClient$Protocol;

        static {
            int[] iArr = new int[Protocol.values().length];
            $SwitchMap$com$amazon$mp3$net$CoralHttpClient$Protocol = iArr;
            try {
                iArr[Protocol.RPCv1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$net$CoralHttpClient$Protocol[Protocol.AWS_QUERY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private void validateRpcV1Response(JSONObject response) throws ServiceException, JSONException {
        throwMappedException(response.getString("__type"), response.getString("message"));
    }

    private void validateAwsResponse(JSONObject response) throws ServiceException, JSONException {
        JSONObject jSONObject = response.getJSONObject("Error");
        throwMappedException(jSONObject.getString("Code"), jSONObject.getString("Message"));
    }

    private void throwMappedException(String type, String message) throws ServiceException {
        ServiceException mappedException = getMappedException(type, message);
        if (mappedException != null) {
            throw mappedException;
        }
        throw new ServiceException("Unmapped Coral exception: " + type + ": " + message);
    }
}
