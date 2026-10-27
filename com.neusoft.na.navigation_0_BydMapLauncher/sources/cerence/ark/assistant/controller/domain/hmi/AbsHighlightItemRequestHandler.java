package cerence.ark.assistant.controller.domain.hmi;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.api.controller.RequestHandler;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.controller.domain.hmi.param.HighlightItemParam;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbsHighlightItemRequestHandler implements RequestHandler<BaseResult> {
    private static final String TAG = "AbsHighlightItemRequestHandler";
    private final Map<String, RequestHandler<? extends BaseResult>> mMap;

    protected abstract void highlightItem(HighlightItemParam highlightItemParam, IResponseSender<BaseResult> iResponseSender);

    protected AbsHighlightItemRequestHandler() {
        HashMap map = new HashMap();
        this.mMap = map;
        loadRequestHandlers(map);
    }

    public void loadRequestHandlers(Map<String, RequestHandler<? extends BaseResult>> map) {
        map.put("", new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsHighlightItemRequestHandler.1
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsHighlightItemRequestHandler.this.highlightItem(HighlightItemParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsHighlightItemRequestHandler.TAG, "[AbsHighlightItemRequestHandler]: exception", e);
                }
            }
        });
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
                iResponseSender.send(new Response(ResultCodeEnum.UNSUPPORTED));
            }
        } catch (JSONException e) {
            Logger.error(TAG, "onHandle exception", e);
        }
    }
}
