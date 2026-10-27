package com.byd.vrassistant.simpleimpl;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.api.controller.Domain;
import cerence.ark.assistant.api.controller.IHostController;
import cerence.ark.assistant.api.dynamic.DefaultDynamicContentProvider;
import cerence.ark.assistant.api.dynamic.IDynamicContentProvider;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class SimpleController implements IHostController {
    @Override // cerence.ark.assistant.api.controller.IController
    public void loadDomains(Map<String, Domain> map, Dhi dhi) {
    }

    @Override // cerence.ark.assistant.api.controller.IHostController
    public IDynamicContentProvider loadDynamicContentProvider() {
        return new DefaultDynamicContentProvider();
    }

    @Override // cerence.ark.assistant.api.controller.IHostController
    public Response<BaseResult> onUnhandledRequest(IRequest iRequest) {
        return new Response<>("UNSUPPORTED");
    }
}
