package b.b.g.q.f.b;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.byd.carsettings.R;
import com.byd.mvp.callback.PagerBtnType;

/* JADX INFO: compiled from: UninstallPager.java */
/* JADX INFO: loaded from: classes2.dex */
public class w extends b.b.m.e.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ImageView f1725a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextView f1726b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ViewGroup f1727c;

    public w(Context context) {
        super(context);
    }

    public void b(Drawable drawable) {
        ImageView imageView = this.f1725a;
        if (imageView == null || drawable == null) {
            return;
        }
        imageView.setImageDrawable(drawable);
    }

    public void c(String str) {
        TextView textView = this.f1726b;
        if (textView == null || str == null) {
            return;
        }
        this.f1726b.setText(String.format(textView.getContext().getString(R.string.msg_app_uninstall), str));
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.dialog_app_uninstall;
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
        this.f1727c = (ViewGroup) this.mRootView.findViewById(R.id.ll_opertion_bottom);
        this.f1725a = (ImageView) this.mRootView.findViewById(R.id.iv_app_icon_app_uninstall);
        this.f1726b = (TextView) this.mRootView.findViewById(R.id.tv_msg_app_uninstall);
        this.mRootView.findViewById(R.id.btn_ok_app_uninstall).setOnClickListener(this);
        this.mRootView.findViewById(R.id.btn_cancel_app_uninstall).setOnClickListener(this);
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
        b.b.m.b.a aVar;
        int id = view.getId();
        if (id != R.id.btn_cancel_app_uninstall) {
            if (id == R.id.btn_ok_app_uninstall && (aVar = this.mBtnCallback) != null) {
                aVar.onBtnClicked(w.class, PagerBtnType.BOTTOM_LEFT, null, null);
                return;
            }
            return;
        }
        b.b.m.b.a aVar2 = this.mBtnCallback;
        if (aVar2 != null) {
            aVar2.onBtnClicked(w.class, PagerBtnType.BOTTOM_RIGHT, null, null);
        }
    }

    @Override // b.b.m.e.f
    public void onLtrRudderR(boolean z) {
        setViewGroupRtl(this.f1727c, true);
    }

    @Override // b.b.m.e.f
    public void onRtlRudderL(boolean z) {
        setViewGroupRtl(this.f1727c, false);
    }
}
