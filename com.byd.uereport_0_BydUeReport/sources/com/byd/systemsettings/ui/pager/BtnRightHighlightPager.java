package com.byd.systemsettings.ui.pager;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.byd.systemsettings.R;
import e4.d;
import z3.a;
import z3.f;

/* JADX INFO: loaded from: classes2.dex */
public class BtnRightHighlightPager extends a {
    private Button btnOk;
    private TextView tvMsg;
    private TextView tvTitle;

    public BtnRightHighlightPager(Context context) {
        super(context);
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // z3.g
    public int getLayoutId() {
        return R.layout.dialog_btn_right_hight_light;
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ Object getP() {
        return super.getP();
    }

    /* JADX WARN: Incorrect return type in method signature: ()TP; */
    @Override // z3.g
    public /* bridge */ /* synthetic */ f getP() {
        return super.getP();
    }

    @Override // z3.e
    public void initData(Context context) {
    }

    @Override // z3.e
    public void initViews(Context context) {
        this.tvTitle = (TextView) this.mRootView.findViewById(R.id.tv_title_dialog_btn_right_hight_light);
        this.tvMsg = (TextView) this.mRootView.findViewById(R.id.tv_msg_dialog_btn_right_hight_light);
        Button button = (Button) this.mRootView.findViewById(R.id.btn_ok_dialog_bottom_right_hight_light);
        this.btnOk = button;
        button.setOnClickListener(this);
        this.mRootView.findViewById(R.id.btn_cancel_dialog_bottom_right_hight_light).setOnClickListener(this);
    }

    @Override // z3.g
    public f newP() {
        return null;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.btn_ok_dialog_bottom_right_hight_light) {
            if (id == R.id.btn_cancel_dialog_bottom_right_hight_light) {
                finish();
            }
        } else {
            d dVar = this.mOkListener;
            if (dVar != null) {
                dVar.onOKClicked();
            }
            finish();
        }
    }

    public void setBtnOkText(String str) {
        Button button = this.btnOk;
        if (button != null) {
            if (str == null) {
                str = "";
            }
            button.setText(str);
        }
    }

    public void setMessageText(String str) {
        TextView textView = this.tvMsg;
        if (textView != null) {
            if (str == null) {
                str = "";
            }
            textView.setText(str);
        }
    }

    @Override // z3.a
    public void setTitleText(String str) {
        TextView textView = this.tvTitle;
        if (textView != null) {
            if (str == null) {
                str = "";
            }
            textView.setText(str);
        }
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void unbindP() {
        super.unbindP();
    }
}
