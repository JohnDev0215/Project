package cerence.ark.assistant.controller.domain.hmi;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.controller.RequestHandler;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.controller.domain.hmi.param.PageControlParam;
import cerence.ark.assistant.controller.domain.hmi.result.PageControlResult;
import cerence.ark.assistant.controller.enumeration.PickListScenarioEnum;
import java.util.Map;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsPageControlRequestHandler extends AbsBaseHmiRequestHandler {
    private static final String TAG = "AbsPageControlRequestHandler";

    protected abstract void pageControl(PageControlParam pageControlParam, IResponseSender<PageControlResult> iResponseSender);

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsBaseHmiRequestHandler
    public void loadRequestHandlers(Map<String, RequestHandler<? extends BaseResult>> map) {
        map.put(PickListScenarioEnum.ALBUM_LIST, new RequestHandler<PageControlResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsPageControlRequestHandler.1
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<PageControlResult> iResponseSender) {
                try {
                    AbsPageControlRequestHandler.this.pageControl(PageControlParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsPageControlRequestHandler.TAG, "[AbsPageControlRequestHandler -> ALBUM_LIST]: exception", e);
                }
            }
        });
        map.put(PickListScenarioEnum.VC_DRIVING_MODE_LIST, new RequestHandler<PageControlResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsPageControlRequestHandler.2
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<PageControlResult> iResponseSender) {
                try {
                    AbsPageControlRequestHandler.this.pageControl(PageControlParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsPageControlRequestHandler.TAG, "[AbsPageControlRequestHandler -> VC_DRIVING_MODE_LIST]: exception", e);
                }
            }
        });
        map.put(PickListScenarioEnum.SONG_LIST, new RequestHandler<PageControlResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsPageControlRequestHandler.3
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<PageControlResult> iResponseSender) {
                try {
                    AbsPageControlRequestHandler.this.pageControl(PageControlParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsPageControlRequestHandler.TAG, "[AbsPageControlRequestHandler -> SONG_LIST]: exception", e);
                }
            }
        });
        map.put(PickListScenarioEnum.PHONE_CONTACT_DISAMBIG_LIST, new RequestHandler<PageControlResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsPageControlRequestHandler.4
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<PageControlResult> iResponseSender) {
                try {
                    AbsPageControlRequestHandler.this.pageControl(PageControlParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsPageControlRequestHandler.TAG, "[AbsPageControlRequestHandler -> PHONE_CONTACT_DISAMBIG_LIST]: exception", e);
                }
            }
        });
        map.put(PickListScenarioEnum.PHONE_NUMBER_DISAMBIG_LIST, new RequestHandler<PageControlResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsPageControlRequestHandler.5
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<PageControlResult> iResponseSender) {
                try {
                    AbsPageControlRequestHandler.this.pageControl(PageControlParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsPageControlRequestHandler.TAG, "[AbsPageControlRequestHandler -> PHONE_NUMBER_DISAMBIG_LIST]: exception", e);
                }
            }
        });
        map.put(PickListScenarioEnum.VC_ROAD_MODE_LIST, new RequestHandler<PageControlResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsPageControlRequestHandler.6
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<PageControlResult> iResponseSender) {
                try {
                    AbsPageControlRequestHandler.this.pageControl(PageControlParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsPageControlRequestHandler.TAG, "[AbsPageControlRequestHandler -> VC_ROAD_MODE_LIST]: exception", e);
                }
            }
        });
        map.put(PickListScenarioEnum.UDE_POI_LIST, new RequestHandler<PageControlResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsPageControlRequestHandler.7
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<PageControlResult> iResponseSender) {
                try {
                    AbsPageControlRequestHandler.this.pageControl(PageControlParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsPageControlRequestHandler.TAG, "[AbsPageControlRequestHandler -> UDE_POI_LIST]: exception", e);
                }
            }
        });
        map.put(PickListScenarioEnum.UDE_RECENT_LIST, new RequestHandler<PageControlResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsPageControlRequestHandler.8
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<PageControlResult> iResponseSender) {
                try {
                    AbsPageControlRequestHandler.this.pageControl(PageControlParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsPageControlRequestHandler.TAG, "[AbsPageControlRequestHandler -> UDE_RECENT_LIST]: exception", e);
                }
            }
        });
    }
}
