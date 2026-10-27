package androidx.media3.ui;

import a5.e;
import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import e2.g0;
import e2.h0;
import e2.m0;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import k4.x;
import m3.J;
import m3.K;

/* JADX INFO: loaded from: classes.dex */
public class TrackSelectionView extends LinearLayout {
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final LayoutInflater f12058m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final CheckedTextView f12059n;
    public final CheckedTextView o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final e f12060p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final ArrayList f12061q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final HashMap f12062r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f12063s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f12064t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public J f12065u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public CheckedTextView[][] f12066v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f12067w;

    public TrackSelectionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        setOrientation(1);
        setSaveFromParentEnabled(false);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{R.attr.selectableItemBackground});
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        this.l = resourceId;
        typedArrayObtainStyledAttributes.recycle();
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        this.f12058m = layoutInflaterFrom;
        e eVar = new e(this, 3);
        this.f12060p = eVar;
        this.f12065u = new x(getResources());
        this.f12061q = new ArrayList();
        this.f12062r = new HashMap();
        CheckedTextView checkedTextView = (CheckedTextView) layoutInflaterFrom.inflate(R.layout.simple_list_item_single_choice, (ViewGroup) this, false);
        this.f12059n = checkedTextView;
        checkedTextView.setBackgroundResource(resourceId);
        checkedTextView.setText(com.stingray.drivemodedetector.R.string.exo_track_selection_none);
        checkedTextView.setEnabled(false);
        checkedTextView.setFocusable(true);
        checkedTextView.setOnClickListener(eVar);
        checkedTextView.setVisibility(8);
        addView(checkedTextView);
        addView(layoutInflaterFrom.inflate(com.stingray.drivemodedetector.R.layout.exo_list_divider, (ViewGroup) this, false));
        CheckedTextView checkedTextView2 = (CheckedTextView) layoutInflaterFrom.inflate(R.layout.simple_list_item_single_choice, (ViewGroup) this, false);
        this.o = checkedTextView2;
        checkedTextView2.setBackgroundResource(resourceId);
        checkedTextView2.setText(com.stingray.drivemodedetector.R.string.exo_track_selection_auto);
        checkedTextView2.setEnabled(false);
        checkedTextView2.setFocusable(true);
        checkedTextView2.setOnClickListener(eVar);
        addView(checkedTextView2);
    }

    public final void a() {
        this.f12059n.setChecked(this.f12067w);
        boolean z6 = this.f12067w;
        HashMap map = this.f12062r;
        this.o.setChecked(!z6 && map.size() == 0);
        for (int i7 = 0; i7 < this.f12066v.length; i7++) {
            h0 h0Var = (h0) map.get(((m0) this.f12061q.get(i7)).f14222b);
            int i10 = 0;
            while (true) {
                CheckedTextView[] checkedTextViewArr = this.f12066v[i7];
                if (i10 < checkedTextViewArr.length) {
                    if (h0Var != null) {
                        Object tag = checkedTextViewArr[i10].getTag();
                        tag.getClass();
                        this.f12066v[i7][i10].setChecked(h0Var.f14119b.contains(Integer.valueOf(((K) tag).f19001b)));
                    } else {
                        checkedTextViewArr[i10].setChecked(false);
                    }
                    i10++;
                }
            }
        }
    }

    public final void b() {
        for (int childCount = getChildCount() - 1; childCount >= 3; childCount--) {
            removeViewAt(childCount);
        }
        ArrayList arrayList = this.f12061q;
        boolean zIsEmpty = arrayList.isEmpty();
        CheckedTextView checkedTextView = this.o;
        CheckedTextView checkedTextView2 = this.f12059n;
        if (zIsEmpty) {
            checkedTextView2.setEnabled(false);
            checkedTextView.setEnabled(false);
            return;
        }
        checkedTextView2.setEnabled(true);
        checkedTextView.setEnabled(true);
        this.f12066v = new CheckedTextView[arrayList.size()][];
        boolean z6 = this.f12064t && arrayList.size() > 1;
        for (int i7 = 0; i7 < arrayList.size(); i7++) {
            m0 m0Var = (m0) arrayList.get(i7);
            boolean z10 = this.f12063s && m0Var.c;
            CheckedTextView[][] checkedTextViewArr = this.f12066v;
            int i10 = m0Var.f14221a;
            checkedTextViewArr[i7] = new CheckedTextView[i10];
            K[] kArr = new K[i10];
            for (int i11 = 0; i11 < m0Var.f14221a; i11++) {
                kArr[i11] = new K(m0Var, i11);
            }
            for (int i12 = 0; i12 < i10; i12++) {
                LayoutInflater layoutInflater = this.f12058m;
                if (i12 == 0) {
                    addView(layoutInflater.inflate(com.stingray.drivemodedetector.R.layout.exo_list_divider, (ViewGroup) this, false));
                }
                CheckedTextView checkedTextView3 = (CheckedTextView) layoutInflater.inflate((z10 || z6) ? R.layout.simple_list_item_multiple_choice : R.layout.simple_list_item_single_choice, (ViewGroup) this, false);
                checkedTextView3.setBackgroundResource(this.l);
                J j3 = this.f12065u;
                K k10 = kArr[i12];
                checkedTextView3.setText(((x) j3).c(k10.f19000a.f14222b.d[k10.f19001b]));
                checkedTextView3.setTag(kArr[i12]);
                if (m0Var.b(i12)) {
                    checkedTextView3.setFocusable(true);
                    checkedTextView3.setOnClickListener(this.f12060p);
                } else {
                    checkedTextView3.setFocusable(false);
                    checkedTextView3.setEnabled(false);
                }
                this.f12066v[i7][i12] = checkedTextView3;
                addView(checkedTextView3);
            }
        }
        a();
    }

    public boolean getIsDisabled() {
        return this.f12067w;
    }

    public Map<g0, h0> getOverrides() {
        return this.f12062r;
    }

    public void setAllowAdaptiveSelections(boolean z6) {
        if (this.f12063s != z6) {
            this.f12063s = z6;
            b();
        }
    }

    public void setAllowMultipleOverrides(boolean z6) {
        if (this.f12064t != z6) {
            this.f12064t = z6;
            if (!z6) {
                HashMap map = this.f12062r;
                if (map.size() > 1) {
                    ArrayList arrayList = this.f12061q;
                    HashMap map2 = new HashMap();
                    for (int i7 = 0; i7 < arrayList.size(); i7++) {
                        h0 h0Var = (h0) map.get(((m0) arrayList.get(i7)).f14222b);
                        if (h0Var != null && map2.isEmpty()) {
                            map2.put(h0Var.f14118a, h0Var);
                        }
                    }
                    map.clear();
                    map.putAll(map2);
                }
            }
            b();
        }
    }

    public void setShowDisableOption(boolean z6) {
        this.f12059n.setVisibility(z6 ? 0 : 8);
    }

    public void setTrackNameProvider(J j3) {
        j3.getClass();
        this.f12065u = j3;
        b();
    }
}
