package com.permissionx.guolindev.request;

import com.permissionx.guolindev.PermissionX;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
abstract class BaseTask implements ChainTask {
    ExplainScope explainReasonScope;
    ForwardScope forwardToSettingsScope;
    protected ChainTask next;
    protected PermissionBuilder pb;

    BaseTask(PermissionBuilder permissionBuilder) {
        this.pb = permissionBuilder;
        this.explainReasonScope = new ExplainScope(this.pb, this);
        this.forwardToSettingsScope = new ForwardScope(this.pb, this);
    }

    @Override // com.permissionx.guolindev.request.ChainTask
    public ExplainScope getExplainScope() {
        return this.explainReasonScope;
    }

    @Override // com.permissionx.guolindev.request.ChainTask
    public ForwardScope getForwardScope() {
        return this.forwardToSettingsScope;
    }

    @Override // com.permissionx.guolindev.request.ChainTask
    public void finish() {
        ChainTask chainTask = this.next;
        if (chainTask != null) {
            chainTask.request();
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.pb.deniedPermissions);
        arrayList.addAll(this.pb.permanentDeniedPermissions);
        arrayList.addAll(this.pb.permissionsWontRequest);
        if (this.pb.requireBackgroundLocationPermission) {
            if (PermissionX.isGranted(this.pb.activity, RequestBackgroundLocationPermission.ACCESS_BACKGROUND_LOCATION)) {
                this.pb.grantedPermissions.add(RequestBackgroundLocationPermission.ACCESS_BACKGROUND_LOCATION);
            } else {
                arrayList.add(RequestBackgroundLocationPermission.ACCESS_BACKGROUND_LOCATION);
            }
        }
        if (this.pb.requestCallback != null) {
            this.pb.requestCallback.onResult(arrayList.isEmpty(), new ArrayList(this.pb.grantedPermissions), arrayList);
        }
    }
}
