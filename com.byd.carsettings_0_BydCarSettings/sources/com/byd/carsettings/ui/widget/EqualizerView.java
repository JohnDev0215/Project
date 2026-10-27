package com.byd.carsettings.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.b.a.f.h;
import b.b.g.o.h.c;
import com.byd.carsettings.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class EqualizerView extends LinearLayout implements b.b.g.q.h.c.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f3397a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f3398b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3399c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3400d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3401e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3402f;
    public int g;
    public int h;
    public int i;
    public int j;
    public String[] k;
    public int[] l;
    public int[] m;
    public int[] n;
    public float o;
    public LinearLayout p;
    public LinearLayout q;
    public LinearLayout r;
    public List<b> s;
    public List<c> t;
    public b.b.g.q.h.c.a u;

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Map f3403a;

        public a(Map map) {
            this.f3403a = map;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (b bVar : EqualizerView.this.s) {
                Integer num = (Integer) this.f3403a.get(bVar.f3406b);
                if (num != null) {
                    bVar.f3407c.setProgress(num.intValue());
                    EqualizerView.this.f(bVar.f3409e, num.intValue());
                }
            }
        }
    }

    public class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public View f3405a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Integer f3406b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public SeekBar f3407c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public TextView f3408d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public TextView f3409e;

        public b() {
        }
    }

    public EqualizerView(@NonNull Context context) {
        super(context);
        this.f3397a = false;
        this.f3398b = true;
        this.f3399c = -1;
        this.f3400d = -1;
        this.f3401e = -1;
        this.f3402f = -1;
        this.g = -1;
        this.h = -1;
        this.i = -1;
        this.j = -1;
        this.o = 0.0f;
        d(context);
    }

    public final void c(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.equalizer_view);
        this.g = typedArrayObtainStyledAttributes.getResourceId(11, this.g);
        this.h = typedArrayObtainStyledAttributes.getResourceId(1, this.h);
        this.f3399c = typedArrayObtainStyledAttributes.getResourceId(6, this.f3399c);
        this.f3400d = typedArrayObtainStyledAttributes.getResourceId(8, this.f3400d);
        this.f3401e = typedArrayObtainStyledAttributes.getResourceId(9, this.f3401e);
        this.f3402f = typedArrayObtainStyledAttributes.getResourceId(5, this.f3402f);
        this.o = typedArrayObtainStyledAttributes.getDimension(7, this.o);
        this.f3397a = typedArrayObtainStyledAttributes.getBoolean(10, this.f3397a);
        this.f3398b = typedArrayObtainStyledAttributes.getBoolean(0, this.f3398b);
        this.i = typedArrayObtainStyledAttributes.getResourceId(2, this.i);
        this.j = typedArrayObtainStyledAttributes.getResourceId(3, this.j);
        if (this.g != -1) {
            this.k = context.getResources().getStringArray(this.g);
        }
        if (this.h != -1) {
            this.l = context.getResources().getIntArray(this.h);
        }
        if (this.i != -1) {
            this.m = context.getResources().getIntArray(this.i);
        }
        if (this.j != -1) {
            this.n = context.getResources().getIntArray(this.j);
        }
        b.b.m.c.a.a("EqualizerView", "---initAttr--titleArr: " + Arrays.toString(this.k) + "\nmaxArr: " + Arrays.toString(this.m) + "\nminArr: " + Arrays.toString(this.n));
        typedArrayObtainStyledAttributes.recycle();
    }

    @SuppressLint({"NewApi"})
    public final void d(Context context) {
        int[] iArr;
        TextView textView;
        TextView textView2;
        setOrientation(1);
        b.b.m.c.a.a("EqualizerView", "---initViews--showValues: " + this.f3397a);
        this.q = new LinearLayout(context);
        addView(this.q, new LinearLayout.LayoutParams(-1, -2));
        this.q.setVisibility(this.f3397a ? 0 : 8);
        this.p = new LinearLayout(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0);
        layoutParams.weight = 1.0f;
        addView(this.p, layoutParams);
        this.r = new LinearLayout(context);
        addView(this.r, new LinearLayout.LayoutParams(-1, -2));
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        this.s = new ArrayList();
        String[] strArr = this.k;
        if (strArr != null && this.l != null && (iArr = this.m) != null && this.n != null) {
            int iMin = Math.min(strArr.length, iArr.length);
            b.b.m.c.a.a("EqualizerView", "---initViews--min: " + iMin + "\ntitleArr.length: " + this.k.length + "\nmaxArr.length: " + this.m.length);
            int i = this.f3399c;
            if (-1 == i) {
                i = R.layout.view_equalizer_seekbar;
            }
            int i2 = this.f3400d;
            if (-1 == i2) {
                i2 = R.layout.view_sound_freq_band_title;
            }
            int i3 = this.f3401e;
            if (-1 == i3) {
                i3 = R.layout.view_sound_freq_band_value;
            }
            int i4 = this.f3402f;
            if (-1 == i4) {
                i4 = R.id.sbar_view_equalizer_seekbar;
            }
            for (int i5 = 0; i5 < iMin; i5++) {
                b bVar = new b();
                View viewInflate = layoutInflaterFrom.inflate(i, (ViewGroup) null);
                bVar.f3405a = viewInflate;
                bVar.f3407c = (SeekBar) viewInflate.findViewById(i4);
                bVar.f3408d = (TextView) layoutInflaterFrom.inflate(i2, (ViewGroup) null);
                bVar.f3409e = (TextView) layoutInflaterFrom.inflate(i3, (ViewGroup) null);
                bVar.f3406b = Integer.valueOf(this.l[i5]);
                if (this.f3397a && (textView2 = bVar.f3409e) != null) {
                    textView2.setVisibility(0);
                    bVar.f3409e.setText("0");
                }
                bVar.f3407c.setTag(Integer.valueOf(i5));
                String[] strArr2 = this.k;
                if (strArr2[i5] != null && (textView = bVar.f3408d) != null) {
                    textView.setText(strArr2[i5]);
                }
                bVar.f3407c.setOnSeekBarChangeListener(this);
                if (e(this.m, this.n, i5)) {
                    bVar.f3407c.setMax(100);
                    bVar.f3407c.setMin(0);
                } else {
                    bVar.f3407c.setMax(this.m[i5]);
                    bVar.f3407c.setMin(this.n[i5]);
                }
                this.s.add(bVar);
            }
        }
        this.t = new ArrayList();
        int size = this.s.size();
        for (int i6 = 0; i6 < size; i6++) {
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -1);
            layoutParams2.weight = 1.0f;
            this.r.addView(this.s.get(i6).f3408d, layoutParams2);
        }
        if (this.f3397a) {
            for (int i7 = 0; i7 < size; i7++) {
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(0, -1);
                layoutParams3.weight = 1.0f;
                this.q.addView(this.s.get(i7).f3409e, layoutParams3);
            }
        }
        for (int i8 = 0; i8 < size; i8++) {
            LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(0, -1);
            layoutParams4.weight = 1.0f;
            this.p.addView(this.s.get(i8).f3405a, layoutParams4);
            this.t.add(new c(this.k[i8], this.l[i8], i8, 0));
        }
    }

    public final boolean e(int[] iArr, int[] iArr2, int i) {
        return iArr == null || iArr2 == null || iArr.length != iArr2.length || i >= iArr.length;
    }

    public final void f(TextView textView, int i) {
        g(textView, 0, i);
    }

    public final void g(TextView textView, int i, int i2) {
        if (textView == null || !this.f3397a) {
            return;
        }
        if (this.f3398b) {
            i2 -= this.m[i] / 2;
        }
        textView.setText(i2 + "");
    }

    public Map<Integer, Integer> getCurFreqBands() {
        HashMap map = new HashMap();
        for (b bVar : this.s) {
            map.put(bVar.f3406b, Integer.valueOf(bVar.f3407c.getProgress()));
        }
        return map;
    }

    public void h(Map<Integer, Integer> map) {
        List<b> list;
        if (map == null || map.isEmpty() || (list = this.s) == null || list.isEmpty()) {
            return;
        }
        b.b.m.c.a.a("EqualizerView", "---updateFreqBandValues--map: " + map + ",freqBandItems:" + this.s.size());
        h.a().g(new a(map), 80L);
    }

    @Override // b.b.g.q.h.c.b, android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        int iIntValue = ((Integer) seekBar.getTag()).intValue();
        b.b.m.c.a.a("EqualizerView", "---onProgressChanged--index = " + iIntValue);
        List<b> list = this.s;
        if (list == null || list.isEmpty() || iIntValue >= this.s.size()) {
            return;
        }
        g(this.s.get(iIntValue).f3409e, iIntValue, i);
    }

    @Override // b.b.g.q.h.c.b, android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        int iIntValue = ((Integer) seekBar.getTag()).intValue();
        b.b.m.c.a.a("EqualizerView", "---onStopTrackingTouch--index = " + iIntValue);
        List<b> list = this.s;
        if (list == null || list.isEmpty() || iIntValue >= this.s.size()) {
            return;
        }
        b bVar = this.s.get(iIntValue);
        int progress = seekBar.getProgress();
        b.b.m.c.a.a("EqualizerView", "---onStopTrackingTouch--progress = " + progress);
        g(bVar.f3409e, iIntValue, progress);
        if (this.u != null) {
            c cVar = this.t.get(iIntValue);
            cVar.c(progress);
            this.u.a(cVar);
        }
    }

    public void setOnFreqBandChangedCallback(b.b.g.q.h.c.a aVar) {
        this.u = aVar;
    }

    public EqualizerView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3397a = false;
        this.f3398b = true;
        this.f3399c = -1;
        this.f3400d = -1;
        this.f3401e = -1;
        this.f3402f = -1;
        this.g = -1;
        this.h = -1;
        this.i = -1;
        this.j = -1;
        this.o = 0.0f;
        c(context, attributeSet);
        d(context);
    }

    public EqualizerView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3397a = false;
        this.f3398b = true;
        this.f3399c = -1;
        this.f3400d = -1;
        this.f3401e = -1;
        this.f3402f = -1;
        this.g = -1;
        this.h = -1;
        this.i = -1;
        this.j = -1;
        this.o = 0.0f;
        c(context, attributeSet);
        d(context);
    }
}
