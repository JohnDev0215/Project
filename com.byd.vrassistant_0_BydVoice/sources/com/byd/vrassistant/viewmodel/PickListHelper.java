package com.byd.vrassistant.viewmodel;

import android.util.Log;
import android.util.Pair;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.controller.domain.hmi.param.PageControlParam;
import cerence.ark.assistant.controller.domain.hmi.result.PageControlResult;
import cerence.ark.assistant.controller.domain.hmi.result.ShowPicklistResult;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import cerence.ark.assistant.controller.struct.PageInfo;
import com.byd.vrassistant.bridge.BridgeHelper;
import com.byd.vrassistant.hint.GSON;
import com.byd.vrassistant.utils.RLog;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class PickListHelper<T> {
    public static final int DEFAULT_PAGE_SIZE = 3;
    private static final String TAG = "NEU_PickListHelper";
    private int mPageSize = 3;
    private int mCurrentPage = 1;
    private int mTotalPage = 1;
    protected int mStartIndex = 0;
    protected int mEndIndex = 0;
    private int totalSize = 0;
    protected List<T> mAllData = new ArrayList();
    protected List<T> mCurrentDisplayData = new ArrayList();

    public List<T> setData(List<T> list) {
        reInitData();
        if (list == null || list.isEmpty()) {
            Log.e(TAG, "setData: ", new Exception("list data is null"));
            return this.mCurrentDisplayData;
        }
        this.mAllData.addAll(list);
        int size = this.mAllData.size();
        this.totalSize = size;
        if (size % 3 > 0) {
            this.mTotalPage = (size / 3) + 1;
        } else {
            this.mTotalPage = size / 3;
        }
        Log.i(TAG, "calculateTotalPage: " + this.totalSize);
        return setSelectedPage(1);
    }

    public List<T> setSelectedPage(int i) {
        int i2;
        RLog.i(TAG, "setSelectedPage: page==" + i);
        if (i < 1 || i > (i2 = this.mTotalPage)) {
            return this.mCurrentDisplayData;
        }
        this.mCurrentPage = i;
        if (i != i2) {
            this.mPageSize = 3;
        } else if (i == 1) {
            this.mPageSize = this.totalSize;
        } else {
            int i3 = this.totalSize;
            if (i3 % 3 == 0) {
                this.mPageSize = 3;
            } else {
                this.mPageSize = i3 % 3;
            }
        }
        RLog.i(TAG, "setSelectedPage: page==" + i + " ,mPageSize==" + this.mPageSize);
        return setPage(i, this.mPageSize);
    }

    private List<T> setPage(int i, int i2) {
        this.mCurrentDisplayData.clear();
        this.mCurrentDisplayData.addAll(getPageData(i, i2));
        BridgeHelper.getVrUiStatus().onPickListDataShow(this.mCurrentDisplayData);
        return this.mCurrentDisplayData;
    }

    private List<T> getPageData(int i, int i2) {
        RLog.i(TAG, "getPageData_page==" + i + " , mAllData.size()==" + this.mAllData.size() + " , getPageData_pageSize==" + i2 + ",mTotalPage=" + this.mTotalPage);
        int i3 = i - 1;
        if (this.mAllData.isEmpty() || this.mAllData.size() <= i2) {
            return this.mAllData;
        }
        if (i == this.mTotalPage) {
            int i4 = i3 * 3;
            this.mStartIndex = i4;
            this.mEndIndex = i4 + i2;
            RLog.i(TAG, "getPageData: lastpage mStartIndex==" + this.mStartIndex + "   mEndIndex==" + this.mEndIndex);
        } else {
            int i5 = i3 * 3;
            this.mStartIndex = i5;
            this.mEndIndex = i5 + 3;
            RLog.i(TAG, "getPageData: page mStartIndex==" + this.mStartIndex + "   mEndIndex==" + this.mEndIndex);
        }
        return this.mAllData.subList(this.mStartIndex, this.mEndIndex);
    }

    public Pair<Integer, Integer> pageControl(PageControlParam pageControlParam, IResponseSender<PageControlResult> iResponseSender) {
        Log.d(TAG, "pageControl() called with: pageControlParam = [" + GSON.toJson(pageControlParam) + "]");
        String pageControlType = pageControlParam.getPageControlType();
        pageControlType.hashCode();
        switch (pageControlType) {
            case "SET_PAGE":
                int pageOrdinal = pageControlParam.getPageOrdinal();
                if (this.mCurrentPage == pageOrdinal) {
                    sendPageControlResponse("ALREADY_EXPECTED", iResponseSender);
                    break;
                } else {
                    setSelectedPage(pageOrdinal);
                    sendPageControlResponse("OK", iResponseSender);
                    break;
                }
                break;
            case "NEXT_PAGE":
                int i = this.mCurrentPage;
                if (i < this.mTotalPage) {
                    setSelectedPage(i + 1);
                    sendPageControlResponse("OK", iResponseSender);
                    break;
                } else {
                    sendPageControlResponse("OUT_OF_RANGE", iResponseSender);
                    break;
                }
                break;
            case "LAST_PAGE":
                int i2 = this.mCurrentPage;
                int i3 = this.mTotalPage;
                if (i2 == i3) {
                    sendPageControlResponse("ALREADY_EXPECTED", iResponseSender);
                    break;
                } else {
                    setSelectedPage(i3);
                    sendPageControlResponse("OK", iResponseSender);
                    break;
                }
                break;
            case "PREV_PAGE":
                int i4 = this.mCurrentPage;
                if (i4 > 1) {
                    setSelectedPage(i4 - 1);
                    sendPageControlResponse("OK", iResponseSender);
                    break;
                } else {
                    sendPageControlResponse("OUT_OF_RANGE", iResponseSender);
                    break;
                }
                break;
            default:
                sendPageControlResponse(ResultCodeEnum.ERROR, iResponseSender);
                break;
        }
        return Pair.create(Integer.valueOf(this.mCurrentPage), Integer.valueOf(this.mTotalPage));
    }

    public void sendPageControlResponse(String str, IResponseSender<PageControlResult> iResponseSender) {
        PageControlResult pageControlResult = new PageControlResult();
        pageControlResult.setStartItemIndex(getStartIndex());
        pageControlResult.setNumOfItems(this.mPageSize);
        pageControlResult.setStartItemOrdinal(1);
        iResponseSender.send(new Response(str, pageControlResult));
    }

    public void sendShowPickListResponse(IResponseSender<ShowPicklistResult> iResponseSender) {
        Log.e(TAG, "enter sendPicklistResponse");
        ShowPicklistResult showPicklistResult = new ShowPicklistResult();
        PageInfo pageInfo = new PageInfo();
        pageInfo.setStartItemIndex(getStartIndex());
        pageInfo.setStartItemOrdinal(1);
        pageInfo.setNumOfItems(this.mPageSize);
        pageInfo.setTotalPageNumber(this.mTotalPage);
        showPicklistResult.setPageInfo(pageInfo);
        iResponseSender.send(new Response("OK", showPicklistResult));
        Log.i(TAG, "sendShowPickListResponse: end");
    }

    public Pair<Integer, Integer> getPageInfo() {
        return Pair.create(Integer.valueOf(this.mCurrentPage), Integer.valueOf(this.mTotalPage));
    }

    public List<T> getCurrentDisplayData() {
        return this.mCurrentDisplayData;
    }

    public int getStartIndex() {
        return this.mStartIndex;
    }

    private void reInitData() {
        this.mCurrentPage = 1;
        this.mTotalPage = 1;
        this.mStartIndex = 0;
        this.mEndIndex = 0;
        this.totalSize = 0;
        this.mCurrentDisplayData.clear();
        this.mAllData.clear();
    }
}
