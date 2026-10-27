package cerence.ark.assistant.controller.domain.hmi;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.controller.RequestHandler;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.controller.domain.hmi.param.HighlightItemParam;
import java.util.Map;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsHighlightItemRequestHandler extends AbsBaseHmiRequestHandler {
    private static final String TAG = "AbsHighlightItemRequestHandler";

    protected abstract void highlightItem(HighlightItemParam highlightItemParam, IResponseSender<BaseResult> iResponseSender);

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsBaseHmiRequestHandler
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
}
