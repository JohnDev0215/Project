package com.permissionx.guolindev.request;

import com.permissionx.guolindev.dialog.RationaleDialog;
import com.permissionx.guolindev.dialog.RationaleDialogFragment;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ForwardScope {
    private ChainTask chainTask;
    private PermissionBuilder pb;

    ForwardScope(PermissionBuilder permissionBuilder, ChainTask chainTask) {
        this.pb = permissionBuilder;
        this.chainTask = chainTask;
    }

    public void showForwardToSettingsDialog(List<String> list, String str, String str2, String str3) {
        this.pb.showHandlePermissionDialog(this.chainTask, false, list, str, str2, str3);
    }

    public void showForwardToSettingsDialog(List<String> list, String str, String str2) {
        showForwardToSettingsDialog(list, str, str2, null);
    }

    public void showForwardToSettingsDialog(RationaleDialog rationaleDialog) {
        this.pb.showHandlePermissionDialog(this.chainTask, false, rationaleDialog);
    }

    public void showForwardToSettingsDialog(RationaleDialogFragment rationaleDialogFragment) {
        this.pb.showHandlePermissionDialog(this.chainTask, false, rationaleDialogFragment);
    }
}
