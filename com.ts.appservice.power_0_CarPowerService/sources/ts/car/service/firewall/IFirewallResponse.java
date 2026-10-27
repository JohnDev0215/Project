package ts.car.service.firewall;

import ts.car.service.firewall.data.ResultCode;
import ts.car.service.firewall.data.ResultType;

/* JADX INFO: loaded from: classes.dex */
public interface IFirewallResponse {
    void onGetLogResponse(int i, int i2, int i3, String str);

    void onGetRuleResponse(int i, int i2, int i3, String str);

    void onRequestFinished(ResultType resultType, ResultCode resultCode);
}
