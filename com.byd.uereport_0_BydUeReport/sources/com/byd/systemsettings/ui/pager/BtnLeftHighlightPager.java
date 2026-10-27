package com.byd.systemsettings.ui.pager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.byd.systemsettings.R;
import e4.d;
import z3.a;
import z3.f;

/* JADX INFO: loaded from: classes2.dex */
public class BtnLeftHighlightPager extends a {
    private Button btnCancel;
    private Button btnOk;
    private ViewGroup opertionBottom;
    private TextView tvMsg;
    private TextView tvTitle;

    public BtnLeftHighlightPager(Context context) {
        super(context);
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // z3.g
    public int getLayoutId() {
        return R.layout.dialog_btn_left_hight_light;
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

    @Override // z3.a
    public int initHeight(Context context) {
        return (int) context.getResources().getDimension(R.dimen.dp_312);
    }

    @Override // z3.e
    public void initViews(Context context) {
        this.tvTitle = (TextView) this.mRootView.findViewById(R.id.tv_title_dialog_btn_left_hight_light);
        this.tvMsg = (TextView) this.mRootView.findViewById(R.id.tv_msg_dialog_btn_left_hight_light);
        Button button = (Button) this.mRootView.findViewById(R.id.btn_ok_dialog_bottom);
        this.btnOk = button;
        button.setOnClickListener(this);
        this.opertionBottom = (ViewGroup) this.mRootView.findViewById(R.id.ll_opertion_dialog_bottom);
        Button button2 = (Button) this.mRootView.findViewById(R.id.btn_cancel_dialog_bottom);
        this.btnCancel = button2;
        button2.setOnClickListener(this);
    }

    @Override // z3.a
    public int initWidth(Context context) {
        return (int) context.getResources().getDimension(R.dimen.dp_560);
    }

    @Override // z3.g
    public f newP() {
        return null;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.btn_ok_dialog_bottom) {
            if (id == R.id.btn_cancel_dialog_bottom) {
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

    @Override // z3.e
    public void onLtrRudderR(boolean z7) {
        setViewGroupRtl(this.opertionBottom, true);
    }

    @Override // z3.e
    public void onRtlRudderL(boolean z7) {
        setViewGroupRtl(this.opertionBottom, false);
    }

    public void setBtnCancelText(String str) {
        if (this.btnOk != null) {
            Button button = this.btnCancel;
            if (str == null) {
                str = "";
            }
            button.setText(str);
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
