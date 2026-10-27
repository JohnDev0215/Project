package com.byd.uereport.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import b5.g;
import com.byd.uereport.R;
import com.byd.uereport.widget.DelayDismissTextView;
import com.bydux.api.common.tools.log.BydLogHelper;
import f5.a;
import p3.e;
import widget.DialogTouchLinearLayout;

/* JADX INFO: loaded from: classes2.dex */
public class DisplayModeDialog extends g implements View.OnClickListener {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3222d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f3223e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f3224f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public e f3225g;

    public DisplayModeDialog(Context context, int i8, String str, e eVar) {
        super(context);
        this.f3222d = i8;
        this.f3223e = str;
        this.f3225g = eVar;
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.flags = 787752;
        getWindow().setAttributes(attributes);
    }

    public void c(int i8) {
        DelayDismissTextView delayDismissTextView = (DelayDismissTextView) this.mRoot.findViewById(R.id.tv_display_confirm);
        delayDismissTextView.setText(this.mContext.getString(R.string.display_dialog_confirm, Integer.valueOf(i8)));
        delayDismissTextView.f(i8);
    }

    public int d() {
        return ((DelayDismissTextView) this.mRoot.findViewById(R.id.tv_display_confirm)).getValaue();
    }

    public boolean e() {
        StringBuilder sb = new StringBuilder();
        sb.append(!TextUtils.isEmpty(this.f3223e));
        sb.append("    返回的状态");
        BydLogHelper.logE("Display", sb.toString());
        return !TextUtils.isEmpty(this.f3223e);
    }

    public void f(boolean z7) {
        this.f3224f = z7;
    }

    public void g(String str) {
        Log.i("DisplayModeDialog", "updateDialogContent: " + str);
        if (this.mRoot == null || TextUtils.isEmpty(str)) {
            return;
        }
        TextView textView = (TextView) this.mRoot.findViewById(R.id.tv_display_message);
        DelayDismissTextView delayDismissTextView = (DelayDismissTextView) this.mRoot.findViewById(R.id.tv_display_confirm);
        if (textView == null || delayDismissTextView == null) {
            return;
        }
        textView.setText(str);
        delayDismissTextView.setText(this.mContext.getString(R.string.display_dialog_confirm, 8));
        delayDismissTextView.k();
    }

    @Override // b5.g
    public void initContent(View view) {
        if ((view instanceof DialogTouchLinearLayout) && e()) {
            ((DialogTouchLinearLayout) view).b(getLayoutParams(), new DialogTouchLinearLayout.a() { // from class: b5.e
                @Override // widget.DialogTouchLinearLayout.a
                public final void removeView(View view2) {
                    this.f2077a.onClick(view2);
                }
            });
        }
        DelayDismissTextView delayDismissTextView = (DelayDismissTextView) view.findViewById(R.id.tv_display_confirm);
        TextView textView = (TextView) view.findViewById(R.id.tv_display_message);
        Log.i("DisplayModeDialog", "initContent msgContent: " + this.f3223e + ", displayModel : " + this.f3222d);
        boolean z7 = true;
        if (TextUtils.isEmpty(this.f3223e)) {
            int i8 = this.f3222d;
            if (1 == i8) {
                textView.setText(this.mContext.getString(R.string.display_into_static_indoor));
            } else if (2 == i8) {
                textView.setText(this.mContext.getString(R.string.display_into_static_outdoor));
            }
        } else {
            textView.setText(this.f3223e);
        }
        delayDismissTextView.setText(this.mContext.getString(R.string.display_dialog_confirm, 8));
        if (e() && !this.f3224f) {
            z7 = false;
        }
        delayDismissTextView.l(this, z7);
    }

    @Override // b5.g
    public void initData() {
        if (this.f3222d < 1) {
            this.f3222d = a.h(this.mContext).f();
        }
        BydLogHelper.logI("DisplayModeDialog", "initData", "displayModel = " + this.f3222d);
    }

    @Override // b5.g
    public int initLayoutRes() {
        return R.layout.dialog_display_mode_change;
    }

    @Override // b5.g
    public boolean isFullScreen() {
        return false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        dismiss();
        e eVar = this.f3225g;
        if (eVar != null) {
            eVar.d();
        }
    }

    @Override // b5.g
    public void setWidthAndHeight(int[] iArr) {
        iArr[0] = R.dimen.dp_560;
        iArr[1] = R.dimen.dp_300;
    }
}
