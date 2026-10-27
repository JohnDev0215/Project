package cerence.ark.assistant.controller.domain.hmi;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.controller.RequestHandler;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.controller.domain.hmi.param.AlbumListParam;
import cerence.ark.assistant.controller.domain.hmi.param.PhoneContactDisambigListParam;
import cerence.ark.assistant.controller.domain.hmi.param.PhoneNumberDisambigListParam;
import cerence.ark.assistant.controller.domain.hmi.param.ShowPicklistParam;
import cerence.ark.assistant.controller.domain.hmi.param.SongListParam;
import cerence.ark.assistant.controller.domain.hmi.param.UdePoiListParam;
import cerence.ark.assistant.controller.domain.hmi.param.UdeRecentListParam;
import cerence.ark.assistant.controller.domain.hmi.param.VcDrivingModeListParam;
import cerence.ark.assistant.controller.domain.hmi.param.VcRoadModeListParam;
import cerence.ark.assistant.controller.domain.hmi.result.ShowPicklistResult;
import cerence.ark.assistant.controller.enumeration.PickListScenarioEnum;
import java.util.Map;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsShowPicklistRequestHandler extends AbsBaseHmiRequestHandler {
    private static final String TAG = "AbsShowPicklistRequestHandler";

    protected abstract void albumList(AlbumListParam albumListParam, IResponseSender<ShowPicklistResult> iResponseSender);

    protected abstract void phoneContactDisambigList(PhoneContactDisambigListParam phoneContactDisambigListParam, IResponseSender<ShowPicklistResult> iResponseSender);

    protected abstract void phoneNumberDisambigList(PhoneNumberDisambigListParam phoneNumberDisambigListParam, IResponseSender<ShowPicklistResult> iResponseSender);

    protected abstract void songList(SongListParam songListParam, IResponseSender<ShowPicklistResult> iResponseSender);

    protected abstract void udePoiList(UdePoiListParam udePoiListParam, IResponseSender<ShowPicklistResult> iResponseSender);

    protected abstract void udeRecentList(UdeRecentListParam udeRecentListParam, IResponseSender<ShowPicklistResult> iResponseSender);

    protected abstract void vcDrivingModeList(VcDrivingModeListParam vcDrivingModeListParam, IResponseSender<ShowPicklistResult> iResponseSender);

    protected abstract void vcRoadModeList(VcRoadModeListParam vcRoadModeListParam, IResponseSender<ShowPicklistResult> iResponseSender);

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsBaseHmiRequestHandler
    public void loadRequestHandlers(Map<String, RequestHandler<? extends BaseResult>> map) {
        map.put(PickListScenarioEnum.ALBUM_LIST, new RequestHandler<ShowPicklistResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsShowPicklistRequestHandler.1
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<ShowPicklistResult> iResponseSender) {
                try {
                    AbsShowPicklistRequestHandler.this.albumList(AlbumListParam.fromJson(ShowPicklistParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsShowPicklistRequestHandler.TAG, "[AbsShowPicklistRequestHandler -> showPicklist -> ALBUM_LIST]: exception", e);
                }
            }
        });
        map.put(PickListScenarioEnum.PHONE_CONTACT_DISAMBIG_LIST, new RequestHandler<ShowPicklistResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsShowPicklistRequestHandler.2
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<ShowPicklistResult> iResponseSender) {
                try {
                    AbsShowPicklistRequestHandler.this.phoneContactDisambigList(PhoneContactDisambigListParam.fromJson(ShowPicklistParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsShowPicklistRequestHandler.TAG, "[AbsShowPicklistRequestHandler -> showPicklist -> PHONE_CONTACT_DISAMBIG_LIST]: exception", e);
                }
            }
        });
        map.put(PickListScenarioEnum.PHONE_NUMBER_DISAMBIG_LIST, new RequestHandler<ShowPicklistResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsShowPicklistRequestHandler.3
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<ShowPicklistResult> iResponseSender) {
                try {
                    AbsShowPicklistRequestHandler.this.phoneNumberDisambigList(PhoneNumberDisambigListParam.fromJson(ShowPicklistParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsShowPicklistRequestHandler.TAG, "[AbsShowPicklistRequestHandler -> showPicklist -> PHONE_NUMBER_DISAMBIG_LIST]: exception", e);
                }
            }
        });
        map.put(PickListScenarioEnum.SONG_LIST, new RequestHandler<ShowPicklistResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsShowPicklistRequestHandler.4
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<ShowPicklistResult> iResponseSender) {
                try {
                    AbsShowPicklistRequestHandler.this.songList(SongListParam.fromJson(ShowPicklistParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsShowPicklistRequestHandler.TAG, "[AbsShowPicklistRequestHandler -> showPicklist -> SONG_LIST]: exception", e);
                }
            }
        });
        map.put(PickListScenarioEnum.UDE_POI_LIST, new RequestHandler<ShowPicklistResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsShowPicklistRequestHandler.5
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<ShowPicklistResult> iResponseSender) {
                try {
                    AbsShowPicklistRequestHandler.this.udePoiList(UdePoiListParam.fromJson(ShowPicklistParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsShowPicklistRequestHandler.TAG, "[AbsShowPicklistRequestHandler -> showPicklist -> UDE_POI_LIST]: exception", e);
                }
            }
        });
        map.put(PickListScenarioEnum.UDE_RECENT_LIST, new RequestHandler<ShowPicklistResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsShowPicklistRequestHandler.6
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<ShowPicklistResult> iResponseSender) {
                try {
                    AbsShowPicklistRequestHandler.this.udeRecentList(UdeRecentListParam.fromJson(ShowPicklistParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsShowPicklistRequestHandler.TAG, "[AbsShowPicklistRequestHandler -> showPicklist -> UDE_RECENT_LIST]: exception", e);
                }
            }
        });
        map.put(PickListScenarioEnum.VC_DRIVING_MODE_LIST, new RequestHandler<ShowPicklistResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsShowPicklistRequestHandler.7
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<ShowPicklistResult> iResponseSender) {
                try {
                    AbsShowPicklistRequestHandler.this.vcDrivingModeList(VcDrivingModeListParam.fromJson(ShowPicklistParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsShowPicklistRequestHandler.TAG, "[AbsShowPicklistRequestHandler -> showPicklist -> VC_DRIVING_MODE_LIST]: exception", e);
                }
            }
        });
        map.put(PickListScenarioEnum.VC_ROAD_MODE_LIST, new RequestHandler<ShowPicklistResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsShowPicklistRequestHandler.8
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<ShowPicklistResult> iResponseSender) {
                try {
                    AbsShowPicklistRequestHandler.this.vcRoadModeList(VcRoadModeListParam.fromJson(ShowPicklistParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsShowPicklistRequestHandler.TAG, "[AbsShowPicklistRequestHandler -> showPicklist -> VC_ROAD_MODE_LIST]: exception", e);
                }
            }
        });
    }
}
