package com.byd.systemsettings.ui.pager;

import android.content.Context;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.LeadingMarginSpan;
import android.view.View;
import android.widget.TextView;
import com.byd.systemsettings.R;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import z3.a;
import z3.f;

/* JADX INFO: loaded from: classes2.dex */
public class HintPager extends a {
    private TextView tvMsg;
    private TextView tvTitle;

    public HintPager(Context context) {
        super(context);
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // z3.g
    public int getLayoutId() {
        return R.layout.dialog_hint;
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
        this.tvTitle = (TextView) this.mRootView.findViewById(R.id.tv_title_dialog_hint);
        this.tvMsg = (TextView) this.mRootView.findViewById(R.id.tv_msg_dialog_hint);
        this.mRootView.findViewById(R.id.iv_back_dialog_hint).setOnClickListener(this);
    }

    @Override // z3.g
    public f newP() {
        return null;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    public void setMessageText(int i8, int i9, String... strArr) {
        if (i8 < 0 || i9 < i8 || strArr == null) {
            return;
        }
        float fMeasureText = this.tvMsg.getPaint().measureText("1.") / 2;
        x3.a.a(this.TAG, "---showHint---unit = " + fMeasureText);
        LeadingMarginSpan.Standard standard = new LeadingMarginSpan.Standard((int) (((float) i8) * fMeasureText), (int) (((float) i9) * fMeasureText));
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        for (int i10 = 0; i10 < strArr.length; i10++) {
            SpannableString spannableString = new SpannableString(i10 < strArr.length - 1 ? strArr[i10] + BydAdbHelper.COMMAND_LINE_END : strArr[i10]);
            spannableString.setSpan(standard, 0, spannableString.length(), 18);
            spannableStringBuilder.append((CharSequence) spannableString);
        }
        this.tvMsg.setText(spannableStringBuilder);
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
