package com.permissionx.guolindev.request;

import com.permissionx.guolindev.PermissionX;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class RequestNormalPermissions extends BaseTask {
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

    RequestNormalPermissions(PermissionBuilder permissionBuilder) {
        super(permissionBuilder);
    }

    @Override // com.permissionx.guolindev.request.ChainTask
    public void request() {
        ArrayList arrayList = new ArrayList();
        for (String str : this.pb.normalPermissions) {
            if (PermissionX.isGranted(this.pb.activity, str)) {
                this.pb.grantedPermissions.add(str);
            } else {
                arrayList.add(str);
            }
        }
        if (arrayList.isEmpty()) {
            finish();
            return;
        }
        if (this.pb.explainReasonBeforeRequest && (this.pb.explainReasonCallback != null || this.pb.explainReasonCallbackWithBeforeParam != null)) {
            this.pb.explainReasonBeforeRequest = false;
            this.pb.deniedPermissions.addAll(arrayList);
            if (this.pb.explainReasonCallbackWithBeforeParam != null) {
                this.pb.explainReasonCallbackWithBeforeParam.onExplainReason(this.explainReasonScope, arrayList, true);
                return;
            } else {
                this.pb.explainReasonCallback.onExplainReason(this.explainReasonScope, arrayList);
                return;
            }
        }
        this.pb.requestNow(this.pb.normalPermissions, this);
    }

    @Override // com.permissionx.guolindev.request.ChainTask
    public void requestAgain(List<String> list) {
        HashSet hashSet = new HashSet(this.pb.grantedPermissions);
        hashSet.addAll(list);
        this.pb.requestNow(hashSet, this);
    }
}
