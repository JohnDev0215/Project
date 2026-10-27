package cerence.ark.assistant.controller.domain.hmi;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.api.controller.RequestHandler;
import cerence.ark.assistant.api.hmi.HmiListener;
import cerence.ark.assistant.common.Logger;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsHmiListener implements HmiListener {
    private static final String TAG = "AbsHmiListener";
    private final Map<String, RequestHandler<? extends BaseResult>> mMap;

    public abstract void loadRequestHandlers(Map<String, RequestHandler<? extends BaseResult>> map);

    protected AbsHmiListener() {
        HashMap map = new HashMap();
        this.mMap = map;
        loadRequestHandlers(map);
    }

    @Override // cerence.ark.assistant.api.hmi.HmiListener
    public void onRequest(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
        RequestHandler<? extends BaseResult> requestHandler = this.mMap.get(iRequest.getName());
        if (requestHandler != null) {
            requestHandler.onHandle(iRequest, iResponseSender);
        } else {
            Logger.error(TAG, "the request handler of [" + iRequest.getName() + "] does not found in the map!");
            iResponseSender.send(new Response("UNSUPPORTED"));
        }
    }
}
