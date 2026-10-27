package cerence.ark.assistant.api.controller;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponse;
import cerence.ark.assistant.api.dynamic.IDynamicContentProvider;

/* JADX INFO: loaded from: classes.dex */
public interface IHostController extends IController {
    IDynamicContentProvider loadDynamicContentProvider();

    IResponse<BaseResult> onUnhandledRequest(IRequest iRequest);
}
