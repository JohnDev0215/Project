package b.b.g.q.f.b;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import b.b.m.f.d.d;
import com.byd.carsettings.R;

/* JADX INFO: compiled from: BtnLeftHighlightPager.java */
/* JADX INFO: loaded from: classes2.dex */
public class g extends b.b.m.e.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ViewGroup f1597a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextView f1598b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TextView f1599c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Button f1600d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Button f1601e;

    public g(Context context) {
        super(context);
    }

    public void b(String str) {
        if (this.f1600d != null) {
            Button button = this.f1601e;
            if (str == null) {
                str = "";
            }
            button.setText(str);
        }
    }

    public void c(String str) {
        Button button = this.f1600d;
        if (button != null) {
            if (str == null) {
                str = "";
            }
            button.setText(str);
        }
    }

    public void e(String str) {
        TextView textView = this.f1599c;
        if (textView != null) {
            if (str == null) {
                str = "";
            }
            textView.setText(str);
        }
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.dialog_btn_left_hight_light;
    }

    @Override // b.b.m.e.f
    public void initData(Context context) {
    }

    @Override // b.b.m.e.a
    public int initHeight(Context context) {
        return (int) context.getResources().getDimension(R.dimen.dp_312);
    }

    @Override // b.b.m.e.f
    public void initViews(Context context) {
        this.f1598b = (TextView) this.mRootView.findViewById(R.id.tv_title_dialog_btn_left_hight_light);
        this.f1599c = (TextView) this.mRootView.findViewById(R.id.tv_msg_dialog_btn_left_hight_light);
        Button button = (Button) this.mRootView.findViewById(R.id.btn_ok_dialog_bottom);
        this.f1600d = button;
        button.setOnClickListener(this);
        this.f1597a = (ViewGroup) this.mRootView.findViewById(R.id.ll_opertion_dialog_bottom);
        Button button2 = (Button) this.mRootView.findViewById(R.id.btn_cancel_dialog_bottom);
        this.f1601e = button2;
        button2.setOnClickListener(this);
    }

    @Override // b.b.m.e.a
    public int initWidth(Context context) {
        return (int) context.getResources().getDimension(R.dimen.dp_560);
    }

    @Override // b.b.m.e.h
    public b.b.m.e.g newP() {
        return null;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_cancel_dialog_bottom) {
            finish();
        } else {
            if (id != R.id.btn_ok_dialog_bottom) {
                return;
            }
            d.a aVar = this.mOkListener;
            if (aVar != null) {
                aVar.onOKClicked();
            }
            finish();
        }
    }

    @Override // b.b.m.e.f
    public void onLtrRudderR(boolean z) {
        setViewGroupRtl(this.f1597a, true);
    }

    @Override // b.b.m.e.f
    public void onRtlRudderL(boolean z) {
        setViewGroupRtl(this.f1597a, false);
    }

    @Override // b.b.m.e.a
    public void setTitleText(String str) {
        TextView textView = this.f1598b;
        if (textView != null) {
            if (str == null) {
                str = "";
            }
            textView.setText(str);
        }
    }
}
