package cerence.ark.assistant.controller.domain.hmi;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.api.controller.RequestHandler;
import cerence.ark.assistant.common.Logger;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBaseHmiRequestHandler implements RequestHandler<BaseResult> {
    private static final String TAG = "AbsBaseHmiRequestHandler";
    private final Map<String, RequestHandler<? extends BaseResult>> mMap;

    protected abstract void loadRequestHandlers(Map<String, RequestHandler<? extends BaseResult>> map);

    protected AbsBaseHmiRequestHandler() {
        HashMap map = new HashMap();
        this.mMap = map;
        loadRequestHandlers(map);
    }

    @Override // cerence.ark.assistant.api.controller.RequestHandler
    public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
        try {
            String strOptString = new JSONObject(iRequest.getParams()).optString("scenario", "");
            RequestHandler<? extends BaseResult> requestHandler = this.mMap.get(strOptString);
            if (requestHandler != null) {
                requestHandler.onHandle(iRequest, iResponseSender);
            } else {
                Logger.error(TAG, "the scenario handler of [" + strOptString + "] does not found in the map!");
                iResponseSender.send(new Response("UNSUPPORTED"));
            }
        } catch (JSONException e) {
            Logger.error(TAG, "onHandle exception", e);
        }
    }
}
