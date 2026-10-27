package Y4;

import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public final class f extends Drawable.ConstantState {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public k f10708a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public T4.a f10709b;
    public ColorStateList c;
    public ColorStateList d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ColorStateList f10710e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public PorterDuff.Mode f10711f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Rect f10712g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f10713h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f10714i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f10715j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f10716k;
    public float l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f10717m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f10718n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Paint.Style f10719p;

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final int getChangingConfigurations() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable() {
        g gVar = new g(this);
        gVar.f10727p = true;
        return gVar;
    }
}
