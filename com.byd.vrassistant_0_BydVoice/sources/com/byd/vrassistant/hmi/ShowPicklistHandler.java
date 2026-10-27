package com.byd.vrassistant.hmi;

import android.util.Log;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.controller.domain.hmi.AbsShowPicklistRequestHandler;
import cerence.ark.assistant.controller.domain.hmi.param.AlbumListParam;
import cerence.ark.assistant.controller.domain.hmi.param.PhoneContactDisambigListParam;
import cerence.ark.assistant.controller.domain.hmi.param.PhoneNumberDisambigListParam;
import cerence.ark.assistant.controller.domain.hmi.param.SongListParam;
import cerence.ark.assistant.controller.domain.hmi.param.UdePoiListParam;
import cerence.ark.assistant.controller.domain.hmi.param.UdeRecentListParam;
import cerence.ark.assistant.controller.domain.hmi.param.VcDrivingModeListParam;
import cerence.ark.assistant.controller.domain.hmi.param.VcRoadModeListParam;
import cerence.ark.assistant.controller.domain.hmi.result.ShowPicklistResult;
import cerence.ark.assistant.controller.struct.ContactInfo;
import cerence.ark.assistant.controller.struct.PhoneNumberInfo;
import com.blankj.utilcode.util.GsonUtils;
import com.byd.vrassistant.bean.PhoneNumberListParam;
import com.byd.vrassistant.bean.ViewBlewType;
import com.byd.vrassistant.bean.VoiceBarListBean;
import com.byd.vrassistant.conversation.NaviHelper;
import com.byd.vrassistant.conversation.PhoneHelper;
import com.byd.vrassistant.conversation.VehicleSettingsHelper;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.viewmodel.PickListViewModel;
import com.byd.vrassistant.viewmodel.ViewManagerViewModel;
import com.byd.vrassistant.viewmodel.ViewModelHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public class ShowPicklistHandler extends AbsShowPicklistRequestHandler {
    private static final String TAG = "NEU_ShowPicklistHandler";
    ViewManagerViewModel viewManagerViewModel = (ViewManagerViewModel) ViewModelHelper.getAppViewModel(ViewManagerViewModel.class);
    PickListViewModel pickListViewModel = (PickListViewModel) ViewModelHelper.getAppViewModel(PickListViewModel.class);

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsShowPicklistRequestHandler
    protected void albumList(AlbumListParam albumListParam, IResponseSender<ShowPicklistResult> iResponseSender) {
        iResponseSender.send(new Response("OK"));
    }

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsShowPicklistRequestHandler
    protected void phoneContactDisambigList(PhoneContactDisambigListParam phoneContactDisambigListParam, IResponseSender<ShowPicklistResult> iResponseSender) {
        Log.i(TAG, "sendShowPickListResponse: start");
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        phoneContactDisambigListParam.getContactList().forEach(new Consumer() { // from class: com.byd.vrassistant.hmi.ShowPicklistHandler$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                arrayList2.add(Long.valueOf(((ContactInfo) obj).getContactId()));
            }
        });
        final Map<Long, List<String>> contactPhone = PhoneHelper.getInstance().readContactPhone(arrayList2);
        phoneContactDisambigListParam.getContactList().forEach(new Consumer() { // from class: com.byd.vrassistant.hmi.ShowPicklistHandler$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ShowPicklistHandler.lambda$phoneContactDisambigList$1(contactPhone, arrayList, (ContactInfo) obj);
            }
        });
        PhoneNumberListParam phoneNumberListParam = new PhoneNumberListParam();
        phoneNumberListParam.setList(arrayList);
        phoneNumberListParam.setType(0);
        this.pickListViewModel.phoneNumberList(phoneNumberListParam, iResponseSender);
        this.viewManagerViewModel.setViewBlewType(ViewBlewType.PHONE_CONTACT);
    }

    static /* synthetic */ void lambda$phoneContactDisambigList$1(Map map, List list, ContactInfo contactInfo) {
        VoiceBarListBean voiceBarListBean = new VoiceBarListBean();
        voiceBarListBean.setTxtTop(contactInfo.getContactName());
        List list2 = (List) map.get(Long.valueOf(contactInfo.getContactId()));
        if (list2 != null && !list2.isEmpty()) {
            voiceBarListBean.setTxtBottom((String) list2.get(0));
        }
        Log.i(TAG, "phoneContactDisambigList: id = " + contactInfo.getContactId() + " ,name = " + contactInfo.getContactName());
        list.add(voiceBarListBean);
    }

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsShowPicklistRequestHandler
    protected void phoneNumberDisambigList(final PhoneNumberDisambigListParam phoneNumberDisambigListParam, IResponseSender<ShowPicklistResult> iResponseSender) {
        Log.i(TAG, "sendShowPickListResponse: start");
        final ArrayList arrayList = new ArrayList();
        phoneNumberDisambigListParam.getPhoneNumberList().forEach(new Consumer() { // from class: com.byd.vrassistant.hmi.ShowPicklistHandler$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ShowPicklistHandler.lambda$phoneNumberDisambigList$2(phoneNumberDisambigListParam, arrayList, (PhoneNumberInfo) obj);
            }
        });
        PhoneNumberListParam phoneNumberListParam = new PhoneNumberListParam();
        phoneNumberListParam.setList(arrayList);
        phoneNumberListParam.setType(1);
        this.pickListViewModel.phoneNumberList(phoneNumberListParam, iResponseSender);
        this.viewManagerViewModel.setViewBlewType(ViewBlewType.PHONE_NUMBER);
    }

    static /* synthetic */ void lambda$phoneNumberDisambigList$2(PhoneNumberDisambigListParam phoneNumberDisambigListParam, List list, PhoneNumberInfo phoneNumberInfo) {
        VoiceBarListBean voiceBarListBean = new VoiceBarListBean();
        voiceBarListBean.setTxtTop(phoneNumberDisambigListParam.getContact().getContactName());
        voiceBarListBean.setTxtBottom(phoneNumberInfo.getNumber());
        voiceBarListBean.setTxtEnd(phoneNumberInfo.getNumberType());
        list.add(voiceBarListBean);
    }

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsShowPicklistRequestHandler
    protected void songList(SongListParam songListParam, IResponseSender<ShowPicklistResult> iResponseSender) {
        RLog.e(TAG, "songList : " + GsonUtils.toJson(songListParam));
        iResponseSender.send(new Response("OK"));
    }

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsShowPicklistRequestHandler
    protected void udePoiList(UdePoiListParam udePoiListParam, IResponseSender<ShowPicklistResult> iResponseSender) {
        VehicleSettingsHelper.stopAppByForce("com.byd.avm");
        int mapAppStatus = NaviHelper.getInstance().getMapAppStatus();
        RLog.i(TAG, "udePoiList: getMapAppStatus==" + mapAppStatus);
        if (mapAppStatus == 0) {
            RLog.i(TAG, "udePoiList reqNaviLaunchMap: mapStatus==" + NaviHelper.getInstance().reqNaviLaunchMap());
        } else if (2 == mapAppStatus) {
            RLog.i(TAG, "udePoiList reqNaviBackToMap: mapStatus==" + NaviHelper.getInstance().reqNaviBackToMap());
        }
        this.pickListViewModel.udePoiList(udePoiListParam, iResponseSender);
        this.viewManagerViewModel.setViewBlewType(ViewBlewType.UDE_POI);
    }

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsShowPicklistRequestHandler
    protected void udeRecentList(UdeRecentListParam udeRecentListParam, IResponseSender<ShowPicklistResult> iResponseSender) {
        int mapAppStatus = NaviHelper.getInstance().getMapAppStatus();
        RLog.i(TAG, "udeRecentList: getMapAppStatus==" + mapAppStatus);
        if (mapAppStatus == 0) {
            RLog.i(TAG, "udeRecentList reqNaviLaunchMap: mapStatus==" + NaviHelper.getInstance().reqNaviLaunchMap());
        } else if (2 == mapAppStatus) {
            RLog.i(TAG, "udeRecentList reqNaviBackToMap: mapStatus==" + NaviHelper.getInstance().reqNaviBackToMap());
        }
        this.pickListViewModel.udeRecentList(udeRecentListParam, iResponseSender);
        this.viewManagerViewModel.setViewBlewType(ViewBlewType.UDE_RECENT);
    }

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsShowPicklistRequestHandler
    protected void vcDrivingModeList(VcDrivingModeListParam vcDrivingModeListParam, IResponseSender<ShowPicklistResult> iResponseSender) {
        this.pickListViewModel.vcDrivingModeList(vcDrivingModeListParam, iResponseSender);
        this.viewManagerViewModel.setViewBlewType(ViewBlewType.DRIVING_MODE);
    }

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsShowPicklistRequestHandler
    protected void vcRoadModeList(VcRoadModeListParam vcRoadModeListParam, IResponseSender<ShowPicklistResult> iResponseSender) {
        this.pickListViewModel.vcRoadModeList(vcRoadModeListParam, iResponseSender);
        this.viewManagerViewModel.setViewBlewType(ViewBlewType.ROAD_MODE);
    }
}
