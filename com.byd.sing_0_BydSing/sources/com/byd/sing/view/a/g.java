package com.byd.sing.view.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.byd.sing.R;
import com.byd.sing.SingApplication;

/* JADX INFO: compiled from: Frame.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class g implements View.OnClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected View f3309b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected int f3310c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected int f3311d;
    protected int e;
    protected int f;
    protected boolean g = n();
    protected a h;
    protected boolean i;

    /* JADX INFO: compiled from: Frame.java */
    public interface a {
        void a(int i);
    }

    public g(int i) {
        this.e = i;
        f();
        this.f = c();
    }

    private void f() {
        if (this.g) {
            this.f3310c = -1;
            this.f3311d = -1;
        } else {
            int[] iArr = new int[2];
            i(iArr);
            this.f3310c = iArr[0] == 0 ? -2 : SingApplication.a().getResources().getDimensionPixelSize(iArr[0]);
            this.f3311d = iArr[1] != 0 ? SingApplication.a().getResources().getDimensionPixelSize(iArr[1]) : -2;
        }
    }

    public abstract void a();

    protected abstract void b(View view);

    protected abstract int c();

    protected abstract int d();

    protected abstract int e();

    protected abstract int g();

    public void h() {
        this.f3309b = LayoutInflater.from(SingApplication.a()).inflate(this.f, (ViewGroup) null);
        int iG = g();
        if (iG != 0) {
            ((TextView) this.f3309b.findViewById(R.id.tv_title)).setText(iG);
            ImageView imageView = (ImageView) this.f3309b.findViewById(R.id.iv_left);
            int iD = d();
            if (iD != 0) {
                imageView.setImageResource(iD);
                imageView.setOnClickListener(this);
            } else {
                imageView.setVisibility(8);
            }
            ImageView imageView2 = (ImageView) this.f3309b.findViewById(R.id.iv_right);
            int iE = e();
            if (iE != 0) {
                imageView2.setImageResource(iE);
                imageView2.setOnClickListener(this);
            } else {
                imageView2.setVisibility(8);
            }
        }
        b(this.f3309b);
    }

    protected abstract void i(int[] iArr);

    public boolean j(Context context) {
        return context.getResources().getConfiguration().orientation != 1;
    }

    public boolean k() {
        return this.i;
    }

    protected void l() {
        f();
    }

    public abstract void m();

    protected abstract boolean n();

    public void o(a aVar) {
        this.h = aVar;
    }

    public void p() {
        h();
    }
}
