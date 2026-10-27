package com.byd.vrassistant.hmi;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.controller.domain.hmi.AbsHighlightItemRequestHandler;
import cerence.ark.assistant.controller.domain.hmi.param.HighlightItemParam;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.viewmodel.PickListViewModel;
import com.byd.vrassistant.viewmodel.ViewManagerViewModel;
import com.byd.vrassistant.viewmodel.ViewModelHelper;

/* JADX INFO: loaded from: classes.dex */
public class HighlightHandler extends AbsHighlightItemRequestHandler {
    private static final String TAG = "NEU_HighlightHandler";
    PickListViewModel pickListViewModel = (PickListViewModel) ViewModelHelper.getAppViewModel(PickListViewModel.class);

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsHighlightItemRequestHandler
    protected void highlightItem(HighlightItemParam highlightItemParam, IResponseSender<BaseResult> iResponseSender) {
        RLog.i(TAG, "enter highlightItem ,param = " + highlightItemParam.getItemOrdinal());
        this.pickListViewModel.setSelectedItem(highlightItemParam.getItemOrdinal() - 1);
        ((ViewManagerViewModel) ViewModelHelper.getAppViewModel(ViewManagerViewModel.class)).removeViewBlew();
        iResponseSender.send(new Response("OK"));
    }
}
