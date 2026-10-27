package com.permissionx.guolindev.request;

import com.permissionx.guolindev.PermissionX;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class RequestBackgroundLocationPermission extends BaseTask {
    public static final String ACCESS_BACKGROUND_LOCATION = "android.permission.ACCESS_BACKGROUND_LOCATION";

    @Override // com.permissionx.guolindev.request.BaseTask, com.permissionx.guolindev.request.ChainTask
    public /* bridge */ /* synthetic */ void finish() {
        super.finish();
    }

    @Override // com.permissionx.guolindev.request.BaseTask, com.permissionx.guolindev.request.ChainTask
    public /* bridge */ /* synthetic */ ExplainScope getExplainScope() {
        return super.getExplainScope();
    }

    @Override // com.permissionx.guolindev.request.BaseTask, com.permissionx.guolindev.request.ChainTask
    public /* bridge */ /* synthetic */ ForwardScope getForwardScope() {
        return super.getForwardScope();
    }

    RequestBackgroundLocationPermission(PermissionBuilder permissionBuilder) {
        super(permissionBuilder);
    }

    @Override // com.permissionx.guolindev.request.ChainTask
    public void request() {
        if (this.pb.requireBackgroundLocationPermission) {
            boolean zIsGranted = PermissionX.isGranted(this.pb.activity, "android.permission.ACCESS_FINE_LOCATION");
            boolean zIsGranted2 = PermissionX.isGranted(this.pb.activity, "android.permission.ACCESS_COARSE_LOCATION");
            if (zIsGranted || zIsGranted2) {
                if (this.pb.explainReasonCallback != null || this.pb.explainReasonCallbackWithBeforeParam != null) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(ACCESS_BACKGROUND_LOCATION);
                    if (this.pb.explainReasonCallbackWithBeforeParam != null) {
                        this.pb.explainReasonCallbackWithBeforeParam.onExplainReason(this.explainReasonScope, arrayList, true);
                        return;
                    } else {
                        this.pb.explainReasonCallback.onExplainReason(this.explainReasonScope, arrayList);
                        return;
                    }
                }
                requestAgain(null);
                return;
            }
        }
        finish();
    }

    @Override // com.permissionx.guolindev.request.ChainTask
    public void requestAgain(List<String> list) {
        this.pb.requestAccessBackgroundLocationNow(this);
    }
}
