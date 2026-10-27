package b.b.f;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import b.b.m.e.g;
import com.byd.carsettings.R;
import com.byd.mvp.callback.PagerBtnType;

/* JADX INFO: compiled from: ChangeCpaaDevicePager.java */
/* JADX INFO: loaded from: classes2.dex */
public class e extends b.b.m.e.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f692a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextView f693b;

    public e(Context context) {
        super(context);
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.dialog_btn_left_hight_light;
    }

    @Override // b.b.m.e.f
    public void initData(Context context) {
        this.f692a.setText(context.getString(R.string.cp_warning));
        this.f693b.setText(context.getString(R.string.carplay_switch_device));
    }

    @Override // b.b.m.e.a
    public int initHeight(Context context) {
        return (int) context.getResources().getDimension(R.dimen.dp_312);
    }

    @Override // b.b.m.e.f
    public void initViews(Context context) {
        this.f692a = (TextView) this.mRootView.findViewById(R.id.tv_title_dialog_btn_left_hight_light);
        this.f693b = (TextView) this.mRootView.findViewById(R.id.tv_msg_dialog_btn_left_hight_light);
        TextView textView = (TextView) this.mRootView.findViewById(R.id.btn_ok_dialog_bottom);
        textView.setText(context.getString(R.string.dilink_yes));
        textView.setOnClickListener(this);
        TextView textView2 = (TextView) this.mRootView.findViewById(R.id.btn_cancel_dialog_bottom);
        textView2.setOnClickListener(this);
        textView2.setText(context.getString(R.string.dilink_no));
    }

    @Override // b.b.m.e.a
    public int initWidth(Context context) {
        return (int) context.getResources().getDimension(R.dimen.dp_560);
    }

    @Override // b.b.m.e.h
    public g newP() {
        return null;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_cancel_dialog_bottom) {
            b.b.m.b.a aVar = this.mBtnCallback;
            if (aVar != null) {
                aVar.onBtnClicked(PagerBtnType.BOTTOM_RIGHT);
            }
            finish();
            return;
        }
        if (id != R.id.btn_ok_dialog_bottom) {
            return;
        }
        b.b.m.b.a aVar2 = this.mBtnCallback;
        if (aVar2 != null) {
            aVar2.onBtnClicked(PagerBtnType.BOTTOM_LEFT);
        }
        finish();
    }
}
