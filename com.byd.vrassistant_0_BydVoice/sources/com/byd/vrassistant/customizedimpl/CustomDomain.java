package com.byd.vrassistant.customizedimpl;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.api.controller.Domain;
import cerence.ark.assistant.api.controller.RequestHandler;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CustomDomain extends Domain {
    /* JADX INFO: Access modifiers changed from: private */
    public void takeAction(String str) {
    }

    @Override // cerence.ark.assistant.api.Activated
    public void activate() {
    }

    @Override // cerence.ark.assistant.api.Activated
    public void deactivate() {
    }

    public CustomDomain(Dhi dhi) {
        super(dhi);
    }

    @Override // cerence.ark.assistant.api.controller.Domain
    public void loadRequestHandlers(Map<String, RequestHandler<? extends BaseResult>> map) {
        map.put("request_name", new RequestHandler<BaseResult>() { // from class: com.byd.vrassistant.customizedimpl.CustomDomain.1
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return false;
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    JSONObject jSONObject = new JSONObject(iRequest.getParams());
                    String strOptString = jSONObject.optString("param_name1");
                    jSONObject.optString("param_name2");
                    jSONObject.optInt("param_name3");
                    CustomDomain.this.takeAction(strOptString);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.putOpt("result_param_name1", "xx");
                    jSONObject2.putOpt("result_param_name2", 123);
                    jSONObject2.putOpt("result_param_name3", true);
                    iResponseSender.send(new Response("OK", new BaseResult(jSONObject2.toString())));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
