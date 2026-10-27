package com.permissionx.guolindev.request;

import com.permissionx.guolindev.dialog.RationaleDialog;
import com.permissionx.guolindev.dialog.RationaleDialogFragment;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ExplainScope {
    private ChainTask chainTask;
    private PermissionBuilder pb;

    ExplainScope(PermissionBuilder permissionBuilder, ChainTask chainTask) {
        this.pb = permissionBuilder;
        this.chainTask = chainTask;
    }

    public void showRequestReasonDialog(List<String> list, String str, String str2, String str3) {
        this.pb.showHandlePermissionDialog(this.chainTask, true, list, str, str2, str3);
    }

    public void showRequestReasonDialog(List<String> list, String str, String str2) {
        showRequestReasonDialog(list, str, str2, null);
    }

    public void showRequestReasonDialog(RationaleDialog rationaleDialog) {
        this.pb.showHandlePermissionDialog(this.chainTask, true, rationaleDialog);
    }

    public void showRequestReasonDialog(RationaleDialogFragment rationaleDialogFragment) {
        this.pb.showHandlePermissionDialog(this.chainTask, true, rationaleDialogFragment);
    }
}
