package cerence.ark.assistant.controller.domain.hmi;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.api.controller.RequestHandler;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.controller.domain.hmi.param.NaviPoiListParam;
import cerence.ark.assistant.controller.domain.hmi.param.NaviRecentListParam;
import cerence.ark.assistant.controller.domain.hmi.param.NaviRouteOptionListParam;
import cerence.ark.assistant.controller.domain.hmi.param.PhoneCallLogParam;
import cerence.ark.assistant.controller.domain.hmi.param.PhoneContactDisambigListParam;
import cerence.ark.assistant.controller.domain.hmi.param.PhoneContactListParam;
import cerence.ark.assistant.controller.domain.hmi.param.PhoneNumberDisambigListParam;
import cerence.ark.assistant.controller.domain.hmi.param.ShowPicklistParam;
import cerence.ark.assistant.controller.domain.hmi.result.ShowPicklistResult;
import cerence.ark.assistant.controller.enumeration.PickListScenarioEnum;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbsShowPicklistRequestHandler implements RequestHandler<BaseResult> {
    private static final String TAG = "AbsShowPicklistRequestHandler";
    private final Map<String, RequestHandler<? extends BaseResult>> mMap;

    protected abstract void naviPoiList(NaviPoiListParam naviPoiListParam, IResponseSender<ShowPicklistResult> iResponseSender);

    protected abstract void naviRecentList(NaviRecentListParam naviRecentListParam, IResponseSender<ShowPicklistResult> iResponseSender);

    protected abstract void naviRouteOptionList(NaviRouteOptionListParam naviRouteOptionListParam, IResponseSender<ShowPicklistResult> iResponseSender);

    protected abstract void phoneCallLog(PhoneCallLogParam phoneCallLogParam, IResponseSender<ShowPicklistResult> iResponseSender);

    protected abstract void phoneContactDisambigList(PhoneContactDisambigListParam phoneContactDisambigListParam, IResponseSender<ShowPicklistResult> iResponseSender);

    protected abstract void phoneContactList(PhoneContactListParam phoneContactListParam, IResponseSender<ShowPicklistResult> iResponseSender);

    protected abstract void phoneNumberDisambigList(PhoneNumberDisambigListParam phoneNumberDisambigListParam, IResponseSender<ShowPicklistResult> iResponseSender);

    protected AbsShowPicklistRequestHandler() {
        HashMap map = new HashMap();
        this.mMap = map;
        loadRequestHandlers(map);
    }

    public void loadRequestHandlers(Map<String, RequestHandler<? extends BaseResult>> map) {
        map.put(PickListScenarioEnum.NAVI_POI_LIST, new RequestHandler<ShowPicklistResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsShowPicklistRequestHandler.1
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<ShowPicklistResult> iResponseSender) {
                try {
                    AbsShowPicklistRequestHandler.this.naviPoiList(NaviPoiListParam.fromJson(ShowPicklistParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsShowPicklistRequestHandler.TAG, "[AbsShowPicklistRequestHandler -> showPicklist -> NAVI_POI_LIST]: exception", e);
                }
            }
        });
        map.put(PickListScenarioEnum.NAVI_RECENT_LIST, new RequestHandler<ShowPicklistResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsShowPicklistRequestHandler.2
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<ShowPicklistResult> iResponseSender) {
                try {
                    AbsShowPicklistRequestHandler.this.naviRecentList(NaviRecentListParam.fromJson(ShowPicklistParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsShowPicklistRequestHandler.TAG, "[AbsShowPicklistRequestHandler -> showPicklist -> NAVI_RECENT_LIST]: exception", e);
                }
            }
        });
        map.put(PickListScenarioEnum.NAVI_ROUTE_OPTION_LIST, new RequestHandler<ShowPicklistResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsShowPicklistRequestHandler.3
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<ShowPicklistResult> iResponseSender) {
                try {
                    AbsShowPicklistRequestHandler.this.naviRouteOptionList(NaviRouteOptionListParam.fromJson(ShowPicklistParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsShowPicklistRequestHandler.TAG, "[AbsShowPicklistRequestHandler -> showPicklist -> NAVI_ROUTE_OPTION_LIST]: exception", e);
                }
            }
        });
        map.put(PickListScenarioEnum.PHONE_CALL_LOG, new RequestHandler<ShowPicklistResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsShowPicklistRequestHandler.4
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<ShowPicklistResult> iResponseSender) {
                try {
                    AbsShowPicklistRequestHandler.this.phoneCallLog(PhoneCallLogParam.fromJson(ShowPicklistParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsShowPicklistRequestHandler.TAG, "[AbsShowPicklistRequestHandler -> showPicklist -> PHONE_CALL_LOG]: exception", e);
                }
            }
        });
        map.put(PickListScenarioEnum.PHONE_CONTACT_DISAMBIG_LIST, new RequestHandler<ShowPicklistResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsShowPicklistRequestHandler.5
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<ShowPicklistResult> iResponseSender) {
                try {
                    AbsShowPicklistRequestHandler.this.phoneContactDisambigList(PhoneContactDisambigListParam.fromJson(ShowPicklistParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsShowPicklistRequestHandler.TAG, "[AbsShowPicklistRequestHandler -> showPicklist -> PHONE_CONTACT_DISAMBIG_LIST]: exception", e);
                }
            }
        });
        map.put(PickListScenarioEnum.PHONE_CONTACT_LIST, new RequestHandler<ShowPicklistResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsShowPicklistRequestHandler.6
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<ShowPicklistResult> iResponseSender) {
                try {
                    AbsShowPicklistRequestHandler.this.phoneContactList(PhoneContactListParam.fromJson(ShowPicklistParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsShowPicklistRequestHandler.TAG, "[AbsShowPicklistRequestHandler -> showPicklist -> PHONE_CONTACT_LIST]: exception", e);
                }
            }
        });
        map.put(PickListScenarioEnum.PHONE_NUMBER_DISAMBIG_LIST, new RequestHandler<ShowPicklistResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsShowPicklistRequestHandler.7
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<ShowPicklistResult> iResponseSender) {
                try {
                    AbsShowPicklistRequestHandler.this.phoneNumberDisambigList(PhoneNumberDisambigListParam.fromJson(ShowPicklistParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsShowPicklistRequestHandler.TAG, "[AbsShowPicklistRequestHandler -> showPicklist -> PHONE_NUMBER_DISAMBIG_LIST]: exception", e);
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
