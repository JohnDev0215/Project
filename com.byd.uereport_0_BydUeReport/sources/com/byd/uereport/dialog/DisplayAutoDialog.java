package com.byd.uereport.dialog;

import android.content.Context;
import android.view.View;
import com.byd.uereport.R;
import com.byd.uereport.widget.DelayDismissTextView;
import p3.e;
import widget.DialogTouchLinearLayout;

/* JADX INFO: loaded from: classes2.dex */
public class DisplayAutoDialog extends DisplayModeDialog {
    public DisplayAutoDialog(Context context, int i8, String str, e eVar) {
        super(context, i8, str, eVar);
    }

    @Override // com.byd.uereport.dialog.DisplayModeDialog
    public boolean e() {
        return true;
    }

    @Override // com.byd.uereport.dialog.DisplayModeDialog, b5.g
    public void initContent(View view) {
        if ((view instanceof DialogTouchLinearLayout) && e()) {
            ((DialogTouchLinearLayout) view).b(getLayoutParams(), new DialogTouchLinearLayout.a() { // from class: b5.a
                @Override // widget.DialogTouchLinearLayout.a
                public final void removeView(View view2) {
                    this.f2072a.onClick(view2);
                }
            });
        }
        ((DelayDismissTextView) view.findViewById(R.id.tv_display_confirm)).l(this, e());
    }

    @Override // com.byd.uereport.dialog.DisplayModeDialog, b5.g
    public int initLayoutRes() {
        return R.layout.dialog_display_auto_stop;
    }
}
