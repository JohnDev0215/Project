package com.android.internal.app;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import com.android.internal.app.chooser.DisplayResolveInfo;
import com.android.internal.app.chooser.MultiDisplayResolveInfo;

/* JADX INFO: loaded from: classes4.dex */
public class ChooserStackedAppDialogFragment extends ChooserTargetActionsDialogFragment implements DialogInterface.OnClickListener {
    private MultiDisplayResolveInfo mMultiDisplayResolveInfo;
    private int mParentWhich;

    @Override // com.android.internal.app.ChooserTargetActionsDialogFragment
    protected Drawable getItemIcon(DisplayResolveInfo displayResolveInfo) {
        return null;
    }

    public ChooserStackedAppDialogFragment() {
    }

    public ChooserStackedAppDialogFragment(MultiDisplayResolveInfo multiDisplayResolveInfo, int i, UserHandle userHandle) {
        super(multiDisplayResolveInfo.getTargets(), userHandle);
        this.mMultiDisplayResolveInfo = multiDisplayResolveInfo;
        this.mParentWhich = i;
    }

    @Override // com.android.internal.app.ChooserTargetActionsDialogFragment
    protected CharSequence getItemLabel(DisplayResolveInfo displayResolveInfo) {
        return displayResolveInfo.getResolveInfo().loadLabel(getContext().getPackageManager());
    }

    @Override // com.android.internal.app.ChooserTargetActionsDialogFragment, android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.mMultiDisplayResolveInfo.setSelected(i);
        ((ChooserActivity) getActivity()).startSelected(this.mParentWhich, false, true);
        dismiss();
    }
}
