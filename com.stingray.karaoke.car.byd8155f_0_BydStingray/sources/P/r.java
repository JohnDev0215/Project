package P;

import android.content.Context;
import android.view.ViewGroup;
import com.stingray.drivemodedetector.R;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import n7.AbstractC1999n;
import n7.AbstractC2004s;

/* JADX INFO: loaded from: classes.dex */
public final class r extends ViewGroup {
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayList f5249m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ArrayList f5250n;
    public final M.t o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f5251p;

    public r(Context context) {
        super(context);
        this.l = 5;
        ArrayList arrayList = new ArrayList();
        this.f5249m = arrayList;
        ArrayList arrayList2 = new ArrayList();
        this.f5250n = arrayList2;
        this.o = new M.t(9);
        setClipChildren(false);
        t tVar = new t(context);
        addView(tVar);
        arrayList.add(tVar);
        arrayList2.add(tVar);
        this.f5251p = 1;
        setTag(R.id.hide_in_inspector_tag, Boolean.TRUE);
    }

    public final t a(s sVar) {
        M.t tVar = this.o;
        t tVar2 = (t) ((LinkedHashMap) tVar.l).get(sVar);
        if (tVar2 != null) {
            return tVar2;
        }
        t tVar3 = (t) AbstractC2004s.O(this.f5250n);
        LinkedHashMap linkedHashMap = (LinkedHashMap) tVar.l;
        LinkedHashMap linkedHashMap2 = (LinkedHashMap) tVar.f4151m;
        if (tVar3 == null) {
            int i7 = this.f5251p;
            ArrayList arrayList = this.f5249m;
            if (i7 > AbstractC1999n.C(arrayList)) {
                tVar3 = new t(getContext());
                addView(tVar3);
                arrayList.add(tVar3);
            } else {
                tVar3 = (t) arrayList.get(this.f5251p);
                s sVar2 = (s) linkedHashMap2.get(tVar3);
                if (sVar2 != null) {
                    sVar2.h0();
                    t tVar4 = (t) linkedHashMap.get(sVar2);
                    if (tVar4 != null) {
                    }
                    linkedHashMap.remove(sVar2);
                    tVar3.c();
                }
            }
            int i10 = this.f5251p;
            if (i10 < this.l - 1) {
                this.f5251p = i10 + 1;
            } else {
                this.f5251p = 0;
            }
        }
        linkedHashMap.put(sVar, tVar3);
        linkedHashMap2.put(tVar3, sVar);
        return tVar3;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i10, int i11, int i12) {
    }

    @Override // android.view.View
    public final void onMeasure(int i7, int i10) {
        setMeasuredDimension(0, 0);
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
    }
}
