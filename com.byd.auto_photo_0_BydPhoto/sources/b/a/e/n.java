package b.a.e;

import android.content.Context;
import android.widget.FrameLayout;
import b.a.e.m;
import com.byd.auto_photo.R;
import com.byd.bean.FileBean;

/* JADX INFO: loaded from: classes.dex */
public class n extends m {
    public n(Context context, int i) {
        super(context, i);
    }

    @Override // b.a.e.m
    protected void t(m.f fVar) {
        fVar.e.setOnClickListener(fVar);
    }

    @Override // b.a.e.m
    protected void u(int i, m.f fVar) {
        if (this.f) {
            return;
        }
        FileBean fileBean = (FileBean) this.f460c.get(i);
        if (fileBean.getType() != 3) {
            return;
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fVar.e.getLayoutParams();
        layoutParams.leftMargin = this.d.getResources().getDimensionPixelSize(R.dimen.dp_31);
        layoutParams.topMargin = this.d.getResources().getDimensionPixelSize(R.dimen.dp_70_5);
        fVar.e.setTextColor(-1);
        fVar.e.setBackgroundResource(R.drawable.burst_tips_bg);
        fVar.e.setGravity(16);
        fVar.e.setText(String.format(this.d.getResources().getString(R.string.burst_num_photo3), Integer.valueOf(fileBean.getNum())));
    }
}
