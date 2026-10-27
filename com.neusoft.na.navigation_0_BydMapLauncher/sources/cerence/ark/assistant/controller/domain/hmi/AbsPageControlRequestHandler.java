package cerence.ark.assistant.controller.domain.hmi;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.api.controller.RequestHandler;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.controller.domain.hmi.param.PageControlParam;
import cerence.ark.assistant.controller.domain.hmi.result.PageControlResult;
import cerence.ark.assistant.controller.enumeration.PickListScenarioEnum;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbsPageControlRequestHandler implements RequestHandler<BaseResult> {
    private static final String TAG = "AbsPageControlRequestHandler";
    private final Map<String, RequestHandler<? extends BaseResult>> mMap;

    protected abstract void pageControl(PageControlParam pageControlParam, IResponseSender<PageControlResult> iResponseSender);

    protected AbsPageControlRequestHandler() {
        HashMap map = new HashMap();
        this.mMap = map;
        loadRequestHandlers(map);
    }

    public void loadRequestHandlers(Map<String, RequestHandler<? extends BaseResult>> map) {
        map.put(PickListScenarioEnum.PHONE_NUMBER_DISAMBIG_LIST, new RequestHandler<PageControlResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsPageControlRequestHandler.1
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<PageControlResult> iResponseSender) {
                try {
                    AbsPageControlRequestHandler.this.pageControl(PageControlParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsPageControlRequestHandler.TAG, "[AbsPageControlRequestHandler -> PHONE_NUMBER_DISAMBIG_LIST]: exception", e);
                }
            }
        });
        map.put(PickListScenarioEnum.NAVI_ROUTE_OPTION_LIST, new RequestHandler<PageControlResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsPageControlRequestHandler.2
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<PageControlResult> iResponseSender) {
                try {
                    AbsPageControlRequestHandler.this.pageControl(PageControlParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsPageControlRequestHandler.TAG, "[AbsPageControlRequestHandler -> NAVI_ROUTE_OPTION_LIST]: exception", e);
                }
            }
        });
        map.put(PickListScenarioEnum.PHONE_CONTACT_LIST, new RequestHandler<PageControlResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsPageControlRequestHandler.3
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<PageControlResult> iResponseSender) {
                try {
                    AbsPageControlRequestHandler.this.pageControl(PageControlParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsPageControlRequestHandler.TAG, "[AbsPageControlRequestHandler -> PHONE_CONTACT_LIST]: exception", e);
                }
            }
        });
        map.put(PickListScenarioEnum.NAVI_POI_LIST, new RequestHandler<PageControlResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsPageControlRequestHandler.4
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<PageControlResult> iResponseSender) {
                try {
                    AbsPageControlRequestHandler.this.pageControl(PageControlParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsPageControlRequestHandler.TAG, "[AbsPageControlRequestHandler -> NAVI_POI_LIST]: exception", e);
                }
            }
        });
        map.put(PickListScenarioEnum.PHONE_CALL_LOG, new RequestHandler<PageControlResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsPageControlRequestHandler.5
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<PageControlResult> iResponseSender) {
                try {
                    AbsPageControlRequestHandler.this.pageControl(PageControlParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsPageControlRequestHandler.TAG, "[AbsPageControlRequestHandler -> PHONE_CALL_LOG]: exception", e);
                }
            }
        });
        map.put(PickListScenarioEnum.PHONE_CONTACT_DISAMBIG_LIST, new RequestHandler<PageControlResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsPageControlRequestHandler.6
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<PageControlResult> iResponseSender) {
                try {
                    AbsPageControlRequestHandler.this.pageControl(PageControlParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsPageControlRequestHandler.TAG, "[AbsPageControlRequestHandler -> PHONE_CONTACT_DISAMBIG_LIST]: exception", e);
                }
            }
        });
        map.put(PickListScenarioEnum.NAVI_RECENT_LIST, new RequestHandler<PageControlResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsPageControlRequestHandler.7
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<PageControlResult> iResponseSender) {
                try {
                    AbsPageControlRequestHandler.this.pageControl(PageControlParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsPageControlRequestHandler.TAG, "[AbsPageControlRequestHandler -> NAVI_RECENT_LIST]: exception", e);
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
