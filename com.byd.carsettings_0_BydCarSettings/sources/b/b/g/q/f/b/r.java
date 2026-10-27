package b.b.g.q.f.b;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;
import androidx.core.view.GravityCompat;
import com.byd.carsettings.R;

/* JADX INFO: compiled from: HintBtnCenterPager.java */
/* JADX INFO: loaded from: classes2.dex */
public class r extends b.b.m.e.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f1705a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextView f1706b;

    /* JADX INFO: compiled from: HintBtnCenterPager.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (r.this.f1706b.getLineCount() < 3) {
                r.this.f1706b.setGravity(17);
            } else {
                r.this.f1706b.setGravity(GravityCompat.START);
            }
        }
    }

    public r(Context context) {
        super(context);
    }

    public void c(String str) {
        TextView textView = this.f1706b;
        if (textView != null) {
            textView.setText(str);
            this.f1706b.postDelayed(new a(), 300L);
        }
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.dialog_hint_1_btn;
    }

    @Override // b.b.m.e.f
    public void initData(Context context) {
    }

    @Override // b.b.m.e.f
    public void initViews(Context context) {
        this.f1705a = (TextView) this.mRootView.findViewById(R.id.tv_title_dialog_hint_1_btn);
        TextView textView = (TextView) this.mRootView.findViewById(R.id.tv_msg_dialog_hint_1_btn);
        this.f1706b = textView;
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        this.mRootView.findViewById(R.id.btn_ok_dialog_hint_1_btn).setOnClickListener(this);
    }

    @Override // b.b.m.e.h
    public b.b.m.e.g newP() {
        return null;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.btn_ok_dialog_hint_1_btn) {
            return;
        }
        finish();
    }

    @Override // b.b.m.e.a
    public void setTitleText(String str) {
        TextView textView = this.f1705a;
        if (textView != null) {
            if (str == null) {
                str = "";
            }
            textView.setText(str);
        }
    }
}
