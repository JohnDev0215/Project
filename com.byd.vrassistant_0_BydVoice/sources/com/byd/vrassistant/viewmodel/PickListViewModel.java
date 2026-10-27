package com.byd.vrassistant.viewmodel;

import android.util.Log;
import android.util.Pair;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.controller.domain.hmi.param.PageControlParam;
import cerence.ark.assistant.controller.domain.hmi.param.UdePoiListParam;
import cerence.ark.assistant.controller.domain.hmi.param.UdeRecentListParam;
import cerence.ark.assistant.controller.domain.hmi.param.VcDrivingModeListParam;
import cerence.ark.assistant.controller.domain.hmi.param.VcRoadModeListParam;
import cerence.ark.assistant.controller.domain.hmi.result.PageControlResult;
import cerence.ark.assistant.controller.domain.hmi.result.ShowPicklistResult;
import cerence.ark.assistant.controller.enumeration.PageControlTypeEnum;
import cerence.ark.assistant.controller.enumeration.PickListScenarioEnum;
import cerence.ark.assistant.controller.struct.PlaceInfo;
import com.byd.vrassistant.bean.PhoneNumberListParam;
import com.byd.vrassistant.bean.ResultFrom;
import com.byd.vrassistant.bridge.BridgeHelper;
import com.byd.vrassistant.hint.GSON;
import com.byd.vrassistant.livedata.MutableResult;
import com.byd.vrassistant.livedata.SafeMutableLiveData;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class PickListViewModel extends ViewModel {
    private static final String TAG = "NEU_PickListViewModel";
    ViewManagerViewModel viewManagerViewModel = (ViewManagerViewModel) ViewModelHelper.getAppViewModel(ViewManagerViewModel.class);
    SafeMutableLiveData<List<?>> listData = new SafeMutableLiveData<>();
    SafeMutableLiveData<Integer> listSize = new SafeMutableLiveData<>();
    SafeMutableLiveData<ResultFrom> resultFrom = new SafeMutableLiveData<>();
    MutableResult<Pair<Integer, Integer>> pageInfo = new MutableResult<>();
    MutableResult<Integer> selectedItemResult = new MutableResult<>();
    PickListHelper pickListHelper = new PickListHelper();

    public void phoneNumberList(PhoneNumberListParam phoneNumberListParam, IResponseSender<ShowPicklistResult> iResponseSender) {
        this.listSize.setValue(Integer.valueOf(phoneNumberListParam.getList().size()));
        this.listData.setValue(this.pickListHelper.setData(phoneNumberListParam.getList()));
        this.pageInfo.setValue(this.pickListHelper.getPageInfo());
        this.pickListHelper.sendShowPickListResponse(iResponseSender);
        this.resultFrom.setValue(ResultFrom.NONE);
    }

    public void udePoiList(UdePoiListParam udePoiListParam, IResponseSender<ShowPicklistResult> iResponseSender) {
        this.listSize.setValue(Integer.valueOf(udePoiListParam.getPoiList().size()));
        this.listData.setValue(this.pickListHelper.setData(udePoiListParam.getPoiList()));
        this.pageInfo.setValue(this.pickListHelper.getPageInfo());
        this.pickListHelper.sendShowPickListResponse(iResponseSender);
        this.resultFrom.setValue(getResultFromWithPlace(udePoiListParam.getPoiList().get(0)));
    }

    public void udeRecentList(UdeRecentListParam udeRecentListParam, IResponseSender<ShowPicklistResult> iResponseSender) {
        this.listSize.setValue(Integer.valueOf(udeRecentListParam.getRecentList().size()));
        this.listData.setValue(this.pickListHelper.setData(udeRecentListParam.getRecentList()));
        this.pageInfo.setValue(this.pickListHelper.getPageInfo());
        this.pickListHelper.sendShowPickListResponse(iResponseSender);
        this.resultFrom.setValue(getResultFromWithPlace(udeRecentListParam.getRecentList().get(0)));
    }

    public void vcDrivingModeList(VcDrivingModeListParam vcDrivingModeListParam, IResponseSender<ShowPicklistResult> iResponseSender) {
        this.listSize.setValue(Integer.valueOf(vcDrivingModeListParam.getDrivingModeList().size()));
        this.listData.setValue(this.pickListHelper.setData(vcDrivingModeListParam.getDrivingModeList()));
        this.pageInfo.setValue(this.pickListHelper.getPageInfo());
        this.pickListHelper.sendShowPickListResponse(iResponseSender);
        this.resultFrom.setValue(ResultFrom.NONE);
    }

    public void vcRoadModeList(VcRoadModeListParam vcRoadModeListParam, IResponseSender<ShowPicklistResult> iResponseSender) {
        this.listSize.setValue(Integer.valueOf(vcRoadModeListParam.getRoadModeList().size()));
        this.listData.setValue(this.pickListHelper.setData(vcRoadModeListParam.getRoadModeList()));
        this.pageInfo.setValue(this.pickListHelper.getPageInfo());
        this.pickListHelper.sendShowPickListResponse(iResponseSender);
        this.resultFrom.setValue(ResultFrom.NONE);
    }

    public void setSelectedItem(int i) {
        this.selectedItemResult.setValue(Integer.valueOf(i));
    }

    public void pageControl(PageControlParam pageControlParam, IResponseSender<PageControlResult> iResponseSender) {
        boolean zEquals = Objects.equals(pageControlParam.getPageControlType(), PageControlTypeEnum.SET_PAGE);
        boolean z = Objects.equals(pageControlParam.getScenario(), PickListScenarioEnum.UDE_POI_LIST) || Objects.equals(pageControlParam.getScenario(), PickListScenarioEnum.UDE_RECENT_LIST);
        boolean zIsPoiShow = BridgeHelper.getNaviStatus().isPoiShow();
        if (z && zIsPoiShow) {
            this.viewManagerViewModel.resetViewBlewType();
            BridgeHelper.getVrUiStatus().onPickListPageChanged();
            if (zEquals) {
                this.pickListHelper.sendPageControlResponse("OK", iResponseSender);
                return;
            }
        }
        this.pickListHelper.pageControl(pageControlParam, iResponseSender);
        this.pageInfo.setValue(this.pickListHelper.getPageInfo());
        this.listData.setValue(this.pickListHelper.getCurrentDisplayData());
    }

    public LiveData<List<?>> getListData() {
        return this.listData;
    }

    public LiveData<Integer> getListSize() {
        return this.listSize;
    }

    public MutableResult<Pair<Integer, Integer>> getPageInfo() {
        return this.pageInfo;
    }

    public LiveData<ResultFrom> getResultFrom() {
        return this.resultFrom;
    }

    public ResultFrom getResultFromWithPlace(PlaceInfo placeInfo) {
        Log.i(TAG, "getResultFromWithPlace: " + GSON.toJson(placeInfo));
        return String.valueOf(placeInfo.getExtraInfo().charAt(0)).equalsIgnoreCase("G") ? ResultFrom.GOOGLE : ResultFrom.HERE;
    }
}
