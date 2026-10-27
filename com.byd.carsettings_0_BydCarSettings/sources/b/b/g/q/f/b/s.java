package b.b.g.q.f.b;

import android.content.Context;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.LeadingMarginSpan;
import android.view.View;
import android.widget.TextView;
import com.byd.carsettings.R;
import com.bydux.api.common.tools.cmd.BydAdbHelper;

/* JADX INFO: compiled from: HintPager.java */
/* JADX INFO: loaded from: classes2.dex */
public class s extends b.b.m.e.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f1708a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextView f1709b;

    public s(Context context) {
        super(context);
    }

    public void b(int i, int i2, String... strArr) {
        if (i < 0 || i2 < i || strArr == null) {
            return;
        }
        float fMeasureText = this.f1709b.getPaint().measureText("1.") / 2;
        b.b.m.c.a.a(this.TAG, "---showHint---unit = " + fMeasureText);
        LeadingMarginSpan.Standard standard = new LeadingMarginSpan.Standard((int) (((float) i) * fMeasureText), (int) (((float) i2) * fMeasureText));
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        for (int i3 = 0; i3 < strArr.length; i3++) {
            SpannableString spannableString = new SpannableString(i3 < strArr.length - 1 ? strArr[i3] + BydAdbHelper.COMMAND_LINE_END : strArr[i3]);
            spannableString.setSpan(standard, 0, spannableString.length(), 18);
            spannableStringBuilder.append((CharSequence) spannableString);
        }
        this.f1709b.setText(spannableStringBuilder);
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.dialog_hint;
    }

    @Override // b.b.m.e.f
    public void initData(Context context) {
    }

    @Override // b.b.m.e.f
    public void initViews(Context context) {
        this.f1708a = (TextView) this.mRootView.findViewById(R.id.tv_title_dialog_hint);
        this.f1709b = (TextView) this.mRootView.findViewById(R.id.tv_msg_dialog_hint);
        this.mRootView.findViewById(R.id.iv_back_dialog_hint).setOnClickListener(this);
    }

    @Override // b.b.m.e.h
    public b.b.m.e.g newP() {
        return null;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // b.b.m.e.a
    public void setTitleText(String str) {
        TextView textView = this.f1708a;
        if (textView != null) {
            if (str == null) {
                str = "";
            }
            textView.setText(str);
        }
    }
}
