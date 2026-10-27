package com.byd.hvac.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import b.a.a.f.g1;
import com.byd.hvac.R;
import java.math.BigDecimal;
import java.util.Arrays;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class WindLevelButton extends ImageView implements View.OnTouchListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f998a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f999b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1000c;
    public boolean d;
    public int e;
    public int f;
    public int g;
    public a h;
    public final int[] i;
    public final int[] j;
    public final int[] k;
    public final int[] l;
    public int[] m;
    public int n;
    public int o;
    public int p;

    /* JADX INFO: compiled from: caradapter-bug */
    public interface a {
        void a(int i);

        void b(int i);

        void c(int i);
    }

    public WindLevelButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = 0;
        this.d = false;
        this.g = 7;
        this.n = 0;
        this.f = 0;
        this.f1000c = 0;
        this.f999b = 0;
        this.f998a = false;
        this.j = new int[]{R.mipmap.img_dark_air_airvolume_control_4dang_bg, R.mipmap.img_dark_air_airvolume_control_4dang_1, R.mipmap.img_dark_air_airvolume_control_4dang_2, R.mipmap.img_dark_air_airvolume_control_4dang_3, R.mipmap.img_dark_air_airvolume_control_4dang_4, R.mipmap.img_dark_air_airvolume_control_4dang_bg};
        this.i = new int[]{R.mipmap.img_dark_air_airvolume_control_bg, R.mipmap.img_dark_air_airvolume_control_1, R.mipmap.img_dark_air_airvolume_control_2, R.mipmap.img_dark_air_airvolume_control_3, R.mipmap.img_dark_air_airvolume_control_4, R.mipmap.img_dark_air_airvolume_control_5, R.mipmap.img_dark_air_airvolume_control_6, R.mipmap.img_dark_air_airvolume_control_7, R.mipmap.img_dark_air_airvolume_control_bg};
        this.l = new int[]{R.mipmap.img_dark_air_airvolume_control_4dang_bg_denza, R.mipmap.img_dark_air_airvolume_control_4dang_1_denza, R.mipmap.img_dark_air_airvolume_control_4dang_2_denza, R.mipmap.img_dark_air_airvolume_control_4dang_3_denza, R.mipmap.img_dark_air_airvolume_control_4dang_4_denza, R.mipmap.img_dark_air_airvolume_control_4dang_bg_denza};
        this.k = new int[]{R.mipmap.img_dark_air_airvolume_control_bg_denza, R.mipmap.img_dark_air_airvolume_control_1_denza, R.mipmap.img_dark_air_airvolume_control_2_denza, R.mipmap.img_dark_air_airvolume_control_3_denza, R.mipmap.img_dark_air_airvolume_control_4_denza, R.mipmap.img_dark_air_airvolume_control_5_denza, R.mipmap.img_dark_air_airvolume_control_6_denza, R.mipmap.img_dark_air_airvolume_control_7_denza, R.mipmap.img_dark_air_airvolume_control_bg_denza};
        a(attributeSet);
    }

    public final void a(AttributeSet attributeSet) {
        this.g = getContext().obtainStyledAttributes(attributeSet, R.styleable.WindLevelButton).getInt(1, 7);
        setOnTouchListener(this);
    }

    public final void b() {
        if (this.g == 4) {
            int[] iArr = g1.e().t() == 1 ? this.l : this.j;
            this.m = Arrays.copyOf(iArr, iArr.length);
        } else {
            int[] iArr2 = g1.e().t() == 1 ? this.k : this.i;
            this.m = Arrays.copyOf(iArr2, iArr2.length);
        }
    }

    public int getWindLevelMax() {
        return this.g;
    }

    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.f1000c = getMeasuredWidth();
        this.f999b = getMeasuredHeight();
        int i3 = this.f1000c;
        int i4 = i3 / 100;
        this.o = i4;
        this.n = (i3 - i4) / this.g;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (isEnabled()) {
            int action = motionEvent.getAction();
            if (action == 0) {
                if (motionEvent.getX() >= 0.0f && motionEvent.getX() < this.f1000c) {
                    BigDecimal scale = new BigDecimal((motionEvent.getX() - this.o) / this.n).setScale(0, 0);
                    if (scale.intValue() >= this.p && this.e != scale.intValue()) {
                        a aVar = this.h;
                        if (aVar != null) {
                            aVar.b(scale.intValue());
                        }
                        this.e = scale.intValue();
                        setWindLevelBackgroundGray(false);
                    }
                }
                this.d = true;
            } else if (action == 1) {
                a aVar2 = this.h;
                if (aVar2 != null) {
                    aVar2.c(this.e);
                }
                this.d = false;
            } else if (action == 2 && motionEvent.getX() >= 0.0f && motionEvent.getX() < this.f1000c) {
                BigDecimal scale2 = new BigDecimal((motionEvent.getX() - this.o) / this.n).setScale(0, 0);
                if (scale2.intValue() >= this.p && this.e != scale2.intValue()) {
                    a aVar3 = this.h;
                    if (aVar3 != null) {
                        aVar3.a(scale2.intValue());
                    }
                    this.e = scale2.intValue();
                    setWindLevelBackgroundGray(false);
                }
            }
        }
        return true;
    }

    public void setMin(int i) {
        this.p = i;
    }

    public void setOnWindLevelViewChangeListener(a aVar) {
        this.h = aVar;
    }

    public void setWindLevel(int i) {
        if (i < 0 || i > this.g || this.d) {
            return;
        }
        this.e = i;
        setWindLevelBackgroundGray(false);
    }

    public void setWindLevelBackgroundGray(boolean z) {
        int i;
        this.f998a = z;
        if (z) {
            setAlpha(0.45f);
            int[] iArr = this.m;
            int length = iArr.length - 2;
            int i2 = this.g;
            if (length == i2) {
                setImageResource(iArr[i2 + 1]);
                return;
            }
            return;
        }
        setAlpha(1.0f);
        int i3 = this.e;
        if (i3 < 0 || i3 > (i = this.g)) {
            return;
        }
        int[] iArr2 = this.m;
        if (iArr2.length - 2 == i) {
            setImageResource(iArr2[i3]);
        }
    }

    public void setWindLevelMax(int i) {
        this.g = i;
        if (i == 4) {
            this.n = this.f;
        }
        b();
    }

    public WindLevelButton(Context context) {
        this(context, null);
    }
}
