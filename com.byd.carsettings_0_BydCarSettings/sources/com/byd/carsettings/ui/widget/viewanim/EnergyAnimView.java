package com.byd.carsettings.ui.widget.viewanim;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import b.b.a.f.d;
import b.b.a.i.a;
import b.b.g.n.q.b;
import b.b.g.r.n;
import b.b.g.r.o;
import com.byd.carsettings.R;
import com.byd.carsettings.ui.widget.LineTipsView;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes2.dex */
public class EnergyAnimView extends FrameLayout {
    public LineTipsView A;
    public int B;
    public int D;
    public int E;
    public int F;
    public int G;
    public int H;
    public int I;
    public int J;
    public int K;
    public boolean L;
    public int M;
    public int N;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f3480a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public o f3481b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public o f3482c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public o f3483d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public o f3484e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public o f3485f;
    public o g;
    public o h;
    public o i;
    public o j;
    public ImageView k;
    public ImageView l;
    public ImageView m;
    public ImageView n;
    public ImageView o;
    public ImageView p;
    public ImageView q;
    public ImageView r;
    public ImageView s;
    public ImageView t;
    public TextView u;
    public ImageView v;
    public ImageView w;
    public ImageView x;
    public ImageView y;
    public ImageView z;

    public EnergyAnimView(Context context) {
        super(context, null);
        this.f3480a = "能量流";
        this.B = -99;
        this.D = -99;
        this.E = -99;
        this.F = -99;
        this.G = -99;
        this.H = -99;
        this.I = -99;
        this.J = -99;
        this.K = -99;
        this.L = false;
        this.M = -1;
        this.N = -1;
    }

    public void a(o... oVarArr) {
        if (oVarArr == null || oVarArr.length <= 0) {
            return;
        }
        for (o oVar : oVarArr) {
            if (oVar != null) {
                oVar.o();
                oVar.m();
            }
        }
    }

    public void b(ImageView... imageViewArr) {
        if (imageViewArr == null || imageViewArr.length <= 0) {
            return;
        }
        for (ImageView imageView : imageViewArr) {
            if (imageView != null) {
                imageView.setImageDrawable(null);
            }
        }
    }

    public final void c(Context context) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.anim_energy_manage, (ViewGroup) null);
        viewInflate.setRotationY(a.f531a ? 180.0f : 0.0f);
        addView(viewInflate);
        this.k = (ImageView) viewInflate.findViewById(R.id.iv_car);
        this.l = (ImageView) viewInflate.findViewById(R.id.iv_car_bg);
        this.m = (ImageView) viewInflate.findViewById(R.id.iv_engine);
        this.n = (ImageView) viewInflate.findViewById(R.id.iv_front_motor);
        this.o = (ImageView) viewInflate.findViewById(R.id.iv_rear_motor);
        this.p = (ImageView) viewInflate.findViewById(R.id.iv_drive);
        this.q = (ImageView) viewInflate.findViewById(R.id.iv_pipe_f);
        this.r = (ImageView) viewInflate.findViewById(R.id.iv_pipe_fb);
        this.s = (ImageView) viewInflate.findViewById(R.id.iv_pipe_r);
        this.t = (ImageView) viewInflate.findViewById(R.id.iv_battery);
        this.v = (ImageView) viewInflate.findViewById(R.id.iv_pipe_emb);
        this.w = (ImageView) viewInflate.findViewById(R.id.iv_tyre_fr);
        this.x = (ImageView) viewInflate.findViewById(R.id.iv_tyre_fl);
        this.y = (ImageView) viewInflate.findViewById(R.id.iv_tyre_rr);
        this.z = (ImageView) viewInflate.findViewById(R.id.iv_tyre_rl);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_battery_progress);
        this.u = textView;
        textView.setRotationY(a.f531a ? 180.0f : 0.0f);
        LineTipsView lineTipsView = (LineTipsView) viewInflate.findViewById(R.id.lineTipsView);
        this.A = lineTipsView;
        if (a.f531a) {
            lineTipsView.setPoint(new Point(getResources().getDimensionPixelSize(R.dimen.dp_256), getResources().getDimensionPixelSize(R.dimen.dp_199)), new Point(getResources().getDimensionPixelSize(R.dimen.dp_368), getResources().getDimensionPixelSize(R.dimen.dp_266)), new Point(getResources().getDimensionPixelSize(R.dimen.dp_182), getResources().getDimensionPixelSize(R.dimen.dp_242)), new Point(getResources().getDimensionPixelSize(R.dimen.dp_287), getResources().getDimensionPixelSize(R.dimen.dp_316)));
        } else {
            lineTipsView.setPoint(new Point(getResources().getDimensionPixelSize(R.dimen.dp_212), getResources().getDimensionPixelSize(R.dimen.dp_265)), new Point(getResources().getDimensionPixelSize(R.dimen.dp_327), getResources().getDimensionPixelSize(R.dimen.dp_199)), new Point(getResources().getDimensionPixelSize(R.dimen.dp_287), getResources().getDimensionPixelSize(R.dimen.dp_316)), new Point(getResources().getDimensionPixelSize(R.dimen.dp_402), getResources().getDimensionPixelSize(R.dimen.dp_240)));
        }
        this.A.setColor(ContextCompat.getColor(context, R.color.battery_line));
        this.A.setProgressText(getResources().getString(R.string.newenergy_soc_target_elec));
    }

    public void d() {
        this.L = false;
        this.M = -1;
        this.N = -1;
        a(this.f3483d, this.f3484e, this.f3482c, this.f3481b, this.f3485f, this.h, this.g, this.j, this.i);
        this.f3484e = null;
        this.f3483d = null;
        this.f3482c = null;
        this.f3481b = null;
        this.f3485f = null;
        this.h = null;
        this.g = null;
        this.j = null;
        this.i = null;
        b(this.p, this.q, this.r, this.s, this.v, this.y, this.z, this.x, this.w);
        this.A.setAnimation(null);
        this.A.setVisibility(8);
        this.A.c();
    }

    public void setAlphaAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        long j = 1000;
        alphaAnimation.setDuration(j);
        alphaAnimation.setStartOffset(0L);
        this.l.setAnimation(alphaAnimation);
        AlphaAnimation alphaAnimation2 = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation2.setDuration(j);
        alphaAnimation2.setStartOffset(250);
        this.k.setAnimation(alphaAnimation2);
        AlphaAnimation alphaAnimation3 = new AlphaAnimation(0.0f, 1.0f);
        long j2 = 750;
        alphaAnimation3.setDuration(j2);
        long j3 = 500;
        alphaAnimation3.setStartOffset(j3);
        this.m.setAnimation(alphaAnimation3);
        if (this.n.getVisibility() == 0) {
            this.n.setAnimation(alphaAnimation3);
        }
        this.t.setAnimation(alphaAnimation3);
        this.u.setAnimation(alphaAnimation3);
        if (this.o.getVisibility() == 0) {
            this.o.setAnimation(alphaAnimation3);
        }
        AlphaAnimation alphaAnimation4 = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation4.setDuration(j3);
        alphaAnimation4.setStartOffset(j2);
        this.p.setAnimation(alphaAnimation4);
        this.q.setAnimation(alphaAnimation4);
        this.r.setAnimation(alphaAnimation4);
        this.s.setAnimation(alphaAnimation4);
        this.v.setAnimation(alphaAnimation4);
        AlphaAnimation alphaAnimation5 = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation5.setDuration(j3);
        alphaAnimation5.setStartOffset(850);
        this.x.setAnimation(alphaAnimation5);
        this.w.setAnimation(alphaAnimation5);
        this.z.setAnimation(alphaAnimation5);
        this.y.setAnimation(alphaAnimation5);
    }

    public void setBatteryProgress(int i) {
        if (i < 0 || i > 100) {
            this.t.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.energy_battery_000));
            this.u.setText("--%");
            return;
        }
        TypedArray typedArrayObtainTypedArray = getContext().getResources().obtainTypedArray(R.array.energy_battery);
        int length = typedArrayObtainTypedArray.length();
        int length2 = typedArrayObtainTypedArray.length();
        int[] iArr = new int[length2];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = typedArrayObtainTypedArray.getResourceId(i2, 0);
        }
        typedArrayObtainTypedArray.recycle();
        int i3 = length2 - 1;
        int i4 = (i * i3) / 100;
        if (i4 < length2) {
            i3 = i4;
        }
        this.t.setImageDrawable(ContextCompat.getDrawable(getContext(), iArr[i3 >= 0 ? i3 : 0]));
        this.u.setText(i + "%");
    }

    public void setCarBg() {
        try {
            b.b.m.c.a.d(this.f3480a, "[Memory]----[Anim]----[EnergyManagePager]----setCarBg");
            int[] iArrB = n.b(b.c().a());
            new d(this.k, getContext()).execute(Integer.valueOf(iArrB[0]));
            new d(this.l, getContext()).execute(Integer.valueOf(iArrB[1]));
        } catch (Exception e2) {
            BydLogHelper.logE(e2.getMessage());
        }
    }

    public void setDrivefAnim() {
        o oVar = new o(this.p, getContext());
        this.f3484e = oVar;
        oVar.i(R.array.energy_drive_f, 40);
        this.f3484e.n();
    }

    public void setEnergyStatus(boolean z, int i, int i2) {
        b.b.m.c.a.d(this.f3480a, "[Animation]----isEv：" + z + "----driveConfig：" + i + "----energyStatus：" + i2);
        if (this.L == z && this.M == i && this.N == i2) {
            b.b.m.c.a.d(this.f3480a, "[Animation]----setEnergyStatus is repeat");
            return;
        }
        this.L = z;
        this.M = i;
        this.N = i2;
        int[] iArrD = null;
        if (z) {
            if (i == 1) {
                iArrD = b.b.g.q.h.d.a.c(i2);
            } else if (i == 2) {
                iArrD = b.b.g.q.h.d.a.b(i2);
            } else if (i == 3) {
                iArrD = b.b.g.q.h.d.a.e(i2);
            }
        } else if (i == 1) {
            iArrD = b.b.g.q.h.d.a.c(i2);
        } else if (i == 2) {
            iArrD = b.b.g.q.h.d.a.b(i2);
        } else if (i == 3) {
            iArrD = b.b.g.q.h.d.a.e(i2);
        } else if (i != 4 && i == 5) {
            iArrD = b.b.g.q.h.d.a.d(i2);
        }
        if (iArrD == null) {
            return;
        }
        int i3 = iArrD[0];
        this.B = i3;
        this.D = iArrD[1];
        this.E = iArrD[2];
        this.F = iArrD[3];
        this.G = iArrD[4];
        this.H = iArrD[5];
        this.I = iArrD[6];
        this.J = iArrD[7];
        this.K = iArrD[8];
        this.m.setVisibility(i3 == -99 ? 8 : 0);
        this.n.setVisibility(this.G == -99 ? 8 : 0);
        this.o.setVisibility(this.J != -99 ? 0 : 8);
        setEngineMotorWork(this.B == 1, this.G == 1, this.J == 1);
        a(this.f3484e);
        b(this.p);
        int i4 = this.D;
        if (i4 != -99 && i4 != 0) {
            setDrivefAnim();
        }
        a(this.f3481b, this.f3485f, this.f3482c);
        b(this.q, this.v, this.r);
        if (this.G != -99 && this.E == 1 && this.H == -1 && this.I == 1) {
            setPipefbAnim(1);
        }
        if (this.G != -99 && this.H == 1 && this.I != -1) {
            setPipefbAnim(2);
        }
        if (this.G != -99 && this.E != 1 && this.H == -1 && this.I == 1) {
            setPipefbAnim(3);
        }
        if (this.G != -99 && this.H == 1 && this.I == -1) {
            setPipefbAnim(4);
        }
        if (this.G != -99 && this.E == 1 && this.I != 1) {
            setPipefAnim();
        }
        if (this.G == -99 && this.F == 1) {
            setPipeEMBAnim(0);
        }
        if (this.G != -99 && this.E == 1 && this.H != -1 && this.I == 1) {
            setPipeEMBAnim(1);
        }
        a(this.f3483d);
        b(this.s);
        int i5 = this.K;
        if (i5 == -99 || i5 == 0) {
            return;
        }
        setPiperAnim();
    }

    public void setEngineMotorWork(boolean z, boolean z2, boolean z3) {
        if (b.c().g()) {
            this.m.setImageDrawable(null);
        } else {
            this.m.setImageDrawable(ContextCompat.getDrawable(getContext(), n.c(b.c().a(), z)));
        }
        this.n.setImageDrawable(ContextCompat.getDrawable(getContext(), z2 ? R.drawable.energy_front_motor_blue : R.drawable.energy_front_motor_gray));
        this.o.setImageDrawable(ContextCompat.getDrawable(getContext(), z3 ? R.drawable.energy_rear_motor_blue : R.drawable.energy_rear_motor_gray));
    }

    public void setLineTipsView(float f2) {
        this.A.setProgress(f2);
    }

    public void setLineTipsViewGone() {
        if (this.A != null) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(1000L);
            this.A.startAnimation(alphaAnimation);
            this.A.setVisibility(8);
        }
    }

    public void setLineTipsViewVisible() {
        LineTipsView lineTipsView = this.A;
        if (lineTipsView != null) {
            lineTipsView.setVisibility(0);
        }
    }

    public void setPipeEMBAnim(int i) {
        o oVar = new o(this.v, getContext());
        this.f3485f = oVar;
        if (i == 0) {
            oVar.i(R.array.anim_pipe_eb, 40);
        } else {
            oVar.i(R.array.anim_pipe_emb, 40);
        }
        this.f3485f.n();
    }

    public void setPipefAnim() {
        o oVar = new o(this.q, getContext());
        this.f3481b = oVar;
        oVar.i(R.array.anim_pipe_f, 40);
        this.f3481b.n();
    }

    public void setPipefbAnim(int i) {
        ViewGroup.LayoutParams layoutParams = this.r.getLayoutParams();
        layoutParams.width = getResources().getDimensionPixelSize(R.dimen.dp_147);
        layoutParams.height = getResources().getDimensionPixelSize((i == 1 || i == 3) ? 2131165495 : R.dimen.dp_115);
        this.r.setLayoutParams(layoutParams);
        o oVar = new o(this.r, getContext());
        this.f3482c = oVar;
        if (i == 1) {
            oVar.i(R.array.anim_pipe_fmtb, 40);
        } else if (i == 2) {
            oVar.i(R.array.anim_pipe_ft, 40);
        } else if (i == 3) {
            oVar.i(R.array.anim_pipe_fb, 40);
        } else if (i == 4) {
            oVar.i(R.array.energy_pipe_fb_blue, 40);
        }
        this.f3482c.n();
    }

    public void setPiperAnim() {
        boolean z = this.K == 1;
        ViewGroup.LayoutParams layoutParams = this.s.getLayoutParams();
        layoutParams.width = getResources().getDimensionPixelSize(z ? 2131165703 : R.dimen.dp_173);
        layoutParams.height = getResources().getDimensionPixelSize(z ? R.dimen.dp_94 : R.dimen.dp_95);
        this.s.setLayoutParams(layoutParams);
        o oVar = new o(this.s, getContext());
        this.f3483d = oVar;
        oVar.i(z ? R.array.energy_pipe_r_blue : R.array.anim_pipe_r, 40);
        this.f3483d.n();
    }

    public void setTypeTurn(int i) {
        b.b.m.c.a.d(this.f3480a, "[动效]----轮胎转向：" + i);
        a(this.j, this.i, this.h, this.g);
        b(this.x, this.w, this.z, this.y);
        switch (i) {
            case 1:
                setTyreFAnim(1, false);
                break;
            case 2:
                setTyreFAnim(-1, false);
                break;
            case 3:
                setTyreFAnim(1, true);
                break;
            case 4:
                setTyreFAnim(-1, true);
                break;
            case 5:
                setTyreFAnim(1, false);
                setTyreRAnim(1, false);
                break;
            case 6:
                setTyreFAnim(-1, false);
                setTyreRAnim(-1, false);
                break;
            case 7:
                setTyreFAnim(1, true);
                setTyreRAnim(1, true);
                break;
            case 8:
                setTyreFAnim(-1, true);
                setTyreRAnim(-1, true);
                break;
        }
    }

    public void setTyreFAnim(int i, boolean z) {
        boolean z2 = i == 1;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.w.getLayoutParams();
        layoutParams.setMarginStart(getResources().getDimensionPixelSize(((!z2 || z) && (z2 || !z)) ? R.dimen.dp_48 : R.dimen.dp_90));
        layoutParams.topMargin = getResources().getDimensionPixelSize(((!z2 || z) && (z2 || !z)) ? R.dimen.dp_232 : R.dimen.dp_215);
        this.w.setLayoutParams(layoutParams);
        o oVar = new o(this.w, getContext());
        this.i = oVar;
        oVar.i(z2 ? z ? R.array.energy_tyre_d_drive_fr : R.array.energy_tyre_drive_fr : z ? R.array.energy_tyre_d_back_fr : R.array.energy_tyre_back_fr, 40);
        this.i.n();
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.x.getLayoutParams();
        layoutParams2.setMarginStart(getResources().getDimensionPixelSize(((!z2 || z) && (z2 || !z)) ? R.dimen.dp_184 : R.dimen.dp_220));
        layoutParams2.topMargin = getResources().getDimensionPixelSize(((!z2 || z) && (z2 || !z)) ? R.dimen.dp_338 : R.dimen.dp_321);
        this.x.setLayoutParams(layoutParams2);
        o oVar2 = new o(this.x, getContext());
        this.j = oVar2;
        oVar2.i(z2 ? z ? R.array.energy_tyre_d_drive_fl : R.array.energy_tyre_drive_fl : z ? R.array.energy_tyre_d_back_fl : R.array.energy_tyre_back_fl, 40);
        this.j.n();
    }

    public void setTyreRAnim(int i, boolean z) {
        boolean z2 = i == 1;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.y.getLayoutParams();
        layoutParams.setMarginStart(getResources().getDimensionPixelSize(((!z2 || z) && (z2 || !z)) ? R.dimen.dp_257 : R.dimen.dp_291));
        layoutParams.topMargin = getResources().getDimensionPixelSize(((!z2 || z) && (z2 || !z)) ? R.dimen.dp_122 : R.dimen.dp_107);
        this.y.setLayoutParams(layoutParams);
        o oVar = new o(this.y, getContext());
        this.g = oVar;
        oVar.i(z2 ? z ? R.array.energy_tyre_d_drive_rr : R.array.energy_tyre_drive_rr : z ? R.array.energy_tyre_d_back_rr : R.array.energy_tyre_back_rr, 40);
        this.g.n();
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.z.getLayoutParams();
        layoutParams2.setMarginStart(getResources().getDimensionPixelSize(((!z2 || z) && (z2 || !z)) ? R.dimen.dp_383 : R.dimen.dp_424));
        layoutParams2.topMargin = getResources().getDimensionPixelSize(((!z2 || z) && (z2 || !z)) ? R.dimen.dp_190 : R.dimen.dp_181);
        this.z.setLayoutParams(layoutParams2);
        o oVar2 = new o(this.z, getContext());
        this.h = oVar2;
        oVar2.i(z2 ? z ? R.array.energy_tyre_d_drive_rl : R.array.energy_tyre_drive_rl : z ? R.array.energy_tyre_d_back_rl : R.array.energy_tyre_back_rl, 40);
        this.h.n();
    }

    public EnergyAnimView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3480a = "能量流";
        this.B = -99;
        this.D = -99;
        this.E = -99;
        this.F = -99;
        this.G = -99;
        this.H = -99;
        this.I = -99;
        this.J = -99;
        this.K = -99;
        this.L = false;
        this.M = -1;
        this.N = -1;
        c(context);
    }
}
