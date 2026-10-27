package com.byd.vrassistant.hmi;

import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.controller.domain.hmi.AbsPageControlRequestHandler;
import cerence.ark.assistant.controller.domain.hmi.param.PageControlParam;
import cerence.ark.assistant.controller.domain.hmi.result.PageControlResult;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.viewmodel.PickListViewModel;
import com.byd.vrassistant.viewmodel.ViewModelHelper;

/* JADX INFO: loaded from: classes.dex */
public class PageControlHandler extends AbsPageControlRequestHandler {
    private static final String TAG = "NEU_PageControlHandler";
    PickListViewModel pickListViewModel = (PickListViewModel) ViewModelHelper.getAppViewModel(PickListViewModel.class);

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsPageControlRequestHandler
    protected void pageControl(PageControlParam pageControlParam, IResponseSender<PageControlResult> iResponseSender) {
        RLog.i(TAG, "enter pageControl");
        if (pageControlParam != null) {
            RLog.i(TAG, "pageControlParam =>" + pageControlParam.getScenario() + "," + pageControlParam.getPageControlType() + "," + pageControlParam.getPageOrdinal());
        }
        this.pickListViewModel.pageControl(pageControlParam, iResponseSender);
    }
}
