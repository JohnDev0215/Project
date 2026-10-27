package com.amazon.mp3.library.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import com.amazon.mp3.activity.DialogActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.BauhausDialogBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BlockChildUserFlowDialog.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0014¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/library/dialog/BlockChildUserFlowDialog;", "Lcom/amazon/mp3/activity/DialogActivity;", "()V", "createDialog", "Landroid/app/Dialog;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BlockChildUserFlowDialog extends DialogActivity {
    @Override // com.amazon.mp3.activity.DialogActivity
    protected Dialog createDialog() {
        Bundle extras = getIntent().getExtras();
        return new BauhausDialogBuilder(this).setTitle(extras != null && extras.getBoolean("accountFull", false) ? R.string.dmusic_family_plan_full_error_dialog_text : R.string.dmusic_error_dialog_text).setPositiveButton(R.string.dmusic_family_plan_full_error_dialog_button, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.dialog.BlockChildUserFlowDialog$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                BlockChildUserFlowDialog.m1230createDialog$lambda0(this.f$0, dialogInterface, i);
            }
        }).create();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: createDialog$lambda-0, reason: not valid java name */
    public static final void m1230createDialog$lambda0(BlockChildUserFlowDialog this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }
}
