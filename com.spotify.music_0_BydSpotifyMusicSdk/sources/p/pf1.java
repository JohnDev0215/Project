package p;

import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public final class pf1 extends Drawable.ConstantState {
    public oc2 a;
    public cc0 b;
    public ColorStateList c;
    public ColorStateList d;
    public ColorStateList e;
    public ColorStateList f;
    public PorterDuff.Mode g;
    public Rect h;
    public float i;
    public float j;
    public float k;
    public int l;
    public float m;
    public float n;
    public float o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f167p;
    public int q;
    public int r;
    public int s;
    public boolean t;
    public Paint.Style u;

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final int getChangingConfigurations() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable() {
        qf1 qf1Var = new qf1(this);
        qf1Var.m = true;
        return qf1Var;
    }
}
