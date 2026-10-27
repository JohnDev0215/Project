package b.b.g.q.f.b;

import android.content.Context;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import b.b.g.p.d;
import com.byd.carsettings.R;
import com.byd.carsettings.ui.dialog.CustomViewDialog;

/* JADX INFO: compiled from: MediaDetailsPager.java */
/* JADX INFO: loaded from: classes2.dex */
public class u extends b.b.m.e.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ViewGroup f1712a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextView f1713b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TextView f1714c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TextView f1715d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TextView f1716e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public TextView f1717f;
    public TextView g;
    public TextView h;
    public TextView i;
    public TextView j;
    public TextView k;
    public View l;
    public View m;
    public CustomViewDialog n;

    public u(Context context, CustomViewDialog customViewDialog) {
        super(context);
        this.n = customViewDialog;
    }

    public final boolean b() {
        return BYDAutoStudy.getInstance(getContext()).getSupportTSRFlow() == 2;
    }

    public final void c() {
        b.b.g.p.d dVarA = d.g.a();
        dVarA.W(getContext());
        b.b.g.m.d.f fVarR = dVarA.r(getContext());
        b.b.m.c.a.a(this.TAG, "---setBydVersionInfo---hasISAMap: " + b() + ", vi: " + fVarR);
        String strA = fVarR.a();
        TextView textView = this.f1714c;
        if (strA == null) {
            strA = "";
        }
        textView.setText(strA);
        String strC = fVarR.c();
        TextView textView2 = this.f1715d;
        if (strC == null) {
            strC = "";
        }
        textView2.setText(strC);
        String strB = fVarR.b();
        TextView textView3 = this.f1716e;
        if (strB == null) {
            strB = "";
        }
        textView3.setText(strB);
        String strD = fVarR.d();
        TextView textView4 = this.g;
        if (strD == null) {
            strD = "";
        }
        textView4.setText(strD);
        this.m.setVisibility(b() ? 0 : 8);
        String strE = fVarR.e();
        this.k.setText(strE != null ? strE : "");
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.dialog_media_version_details;
    }

    @Override // b.b.m.e.f
    public void initData(Context context) {
        this.f1713b.setText(context.getString(R.string.version_media_detail));
        c();
    }

    @Override // b.b.m.e.f
    public void initViews(Context context) {
        this.f1712a = (ViewGroup) this.mRootView.findViewById(R.id.ll_title_dialog_common_title);
        this.f1713b = (TextView) this.mRootView.findViewById(R.id.tv_title_dialog_common_titles);
        this.f1714c = (TextView) this.mRootView.findViewById(R.id.tv_byd_system_version_dialog_media_version);
        this.f1715d = (TextView) this.mRootView.findViewById(R.id.tv_imei_dialog_media_version);
        this.f1716e = (TextView) this.mRootView.findViewById(R.id.tv_icc_id_dialog_media_version);
        this.f1717f = (TextView) this.mRootView.findViewById(R.id.tv_controller_version_dialog_media_version);
        this.l = this.mRootView.findViewById(R.id.rl_phone_number_dialog_media_version);
        this.g = (TextView) this.mRootView.findViewById(R.id.tv_phone_number_dialog_media_version);
        this.h = (TextView) this.mRootView.findViewById(R.id.tv_dsp_version_dialog_media_version);
        this.i = (TextView) this.mRootView.findViewById(R.id.tv_audio_version_title_dialog_media_version);
        this.j = (TextView) this.mRootView.findViewById(R.id.tv_audio_version_dialog_media_version);
        this.m = this.mRootView.findViewById(R.id.rl_isa_version_dialog_media_version);
        this.k = (TextView) this.mRootView.findViewById(R.id.tv_isa_version_dialog_media_version);
        this.l.setVisibility(8);
        this.mRootView.findViewById(R.id.iv_back_dialog_common_title).setOnClickListener(this);
        this.mRootView.findViewById(R.id.btn_ok_dialog_media_version).setOnClickListener(this);
    }

    @Override // b.b.m.e.h
    public b.b.m.e.g newP() {
        return null;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_ok_dialog_media_version || id == R.id.iv_back_dialog_common_title) {
            finish();
            this.n.dismiss();
        }
    }

    @Override // b.b.m.e.f
    public void onLtrRudderR(boolean z) {
        setViewGroupRtl(this.f1712a, true);
    }

    @Override // b.b.m.e.f
    public void onRtlRudderL(boolean z) {
        super.onRtlRudderL(z);
        setViewGroupRtl(this.f1712a, false);
    }

    @Override // b.b.m.e.a
    public void setTitleText(String str) {
        TextView textView = this.f1713b;
        if (textView != null) {
            if (str == null) {
                str = "";
            }
            textView.setText(str);
        }
    }
}
