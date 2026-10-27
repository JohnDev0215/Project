package b.b.g.q.f.b;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import com.byd.carsettings.R;

/* JADX INFO: compiled from: LoadingPager.java */
/* JADX INFO: loaded from: classes2.dex */
public class t extends b.b.m.e.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f1710a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Animation f1711b;

    public t(Context context) {
        super(context);
    }

    public void b(String str) {
        TextView textView = this.f1710a;
        if (textView != null) {
            textView.setText(str);
        }
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.dialog_loading_without_title;
    }

    @Override // b.b.m.e.f
    public void initData(Context context) {
    }

    @Override // b.b.m.e.f
    public void initViews(Context context) {
        b.b.m.c.a.a(this.TAG, "---initViews---");
        this.f1710a = (TextView) this.mRootView.findViewById(R.id.tv_msg_dialog_loading_without_title);
        ImageView imageView = (ImageView) this.mRootView.findViewById(R.id.iv_loading_dialog_loading_without_title);
        Animation animationLoadAnimation = AnimationUtils.loadAnimation(context, R.anim.anim_loading);
        this.f1711b = animationLoadAnimation;
        animationLoadAnimation.setInterpolator(new LinearInterpolator());
        this.f1711b.setDuration(1000L);
        this.f1711b.setRepeatCount(-1);
        imageView.startAnimation(this.f1711b);
    }

    @Override // b.b.m.e.h
    public b.b.m.e.g newP() {
        return null;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // b.b.m.e.a, b.b.m.e.f
    public void onDestroy() {
        super.onDestroy();
        Animation animation = this.f1711b;
        if (animation != null) {
            animation.cancel();
        }
        b.b.m.c.a.a(this.TAG, "---onDestroy---");
    }
}
