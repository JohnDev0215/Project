package com.byd.ime.subtype.show;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import com.byd.ime.subtype.show.SubtypeSettingsActivityPresenter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AllSubtypeDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0012\u0010\f\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/byd/ime/subtype/show/AllSubtypeDialog;", "Landroid/app/AlertDialog;", "Lcom/byd/ime/subtype/show/SubtypeSettingsActivityPresenter$CallBack;", "context", "Landroid/content/Context;", "themeResId", "", "(Landroid/content/Context;I)V", "mSubtypeSettingsPresenter", "Lcom/byd/ime/subtype/show/SubtypeSettingsActivityPresenter;", "doClose", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "LatinIME_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class AllSubtypeDialog extends AlertDialog implements SubtypeSettingsActivityPresenter.CallBack {
    private SubtypeSettingsActivityPresenter mSubtypeSettingsPresenter;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AllSubtypeDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // android.app.AlertDialog, android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SubtypeSettingsActivityPresenter subtypeSettingsActivityPresenter = new SubtypeSettingsActivityPresenter();
        this.mSubtypeSettingsPresenter = subtypeSettingsActivityPresenter;
        SubtypeSettingsActivityPresenter subtypeSettingsActivityPresenter2 = null;
        if (subtypeSettingsActivityPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSubtypeSettingsPresenter");
            subtypeSettingsActivityPresenter = null;
        }
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        subtypeSettingsActivityPresenter.init(context);
        SubtypeSettingsActivityPresenter subtypeSettingsActivityPresenter3 = this.mSubtypeSettingsPresenter;
        if (subtypeSettingsActivityPresenter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSubtypeSettingsPresenter");
            subtypeSettingsActivityPresenter3 = null;
        }
        subtypeSettingsActivityPresenter3.setCallback(this);
        SubtypeSettingsActivityPresenter subtypeSettingsActivityPresenter4 = this.mSubtypeSettingsPresenter;
        if (subtypeSettingsActivityPresenter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSubtypeSettingsPresenter");
        } else {
            subtypeSettingsActivityPresenter2 = subtypeSettingsActivityPresenter4;
        }
        setContentView(subtypeSettingsActivityPresenter2.findAndInitContentView());
    }

    @Override // com.byd.ime.subtype.show.SubtypeSettingsActivityPresenter.CallBack
    public void doClose() {
        dismiss();
    }
}
