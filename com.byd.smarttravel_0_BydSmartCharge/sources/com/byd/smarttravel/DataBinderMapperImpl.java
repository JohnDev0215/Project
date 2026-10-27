package com.byd.smarttravel;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.ViewDataBinding;
import b.j.d;
import b.j.e;
import c.a.b.c.b;
import c.a.b.c.b0;
import c.a.b.c.c;
import c.a.b.c.c0;
import c.a.b.c.e0;
import c.a.b.c.f;
import c.a.b.c.f0;
import c.a.b.c.h;
import c.a.b.c.h0;
import c.a.b.c.i;
import c.a.b.c.i0;
import c.a.b.c.j;
import c.a.b.c.k;
import c.a.b.c.k0;
import c.a.b.c.l0;
import c.a.b.c.m;
import c.a.b.c.n;
import c.a.b.c.p;
import c.a.b.c.r;
import c.a.b.c.t;
import c.a.b.c.v;
import c.a.b.c.w;
import c.a.b.c.y;
import c.a.b.c.z;
import com.bydux.api.R;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DataBinderMapperImpl extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final SparseIntArray f1951a;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(13);
        f1951a = sparseIntArray;
        sparseIntArray.put(R.layout.activity_charge_main, 1);
        sparseIntArray.put(R.layout.activity_charge_main_byd, 2);
        sparseIntArray.put(R.layout.activity_setinfo, 3);
        sparseIntArray.put(R.layout.activity_setinfo_byd, 4);
        sparseIntArray.put(R.layout.activity_test, 5);
        sparseIntArray.put(R.layout.anim_charge_view, 6);
        sparseIntArray.put(R.layout.anim_charge_view_byd, 7);
        sparseIntArray.put(R.layout.view_plan_charge, 8);
        sparseIntArray.put(R.layout.view_plan_charge_byd, 9);
        sparseIntArray.put(R.layout.view_plan_usecar, 10);
        sparseIntArray.put(R.layout.view_plan_usecar_byd, 11);
        sparseIntArray.put(R.layout.view_plan_usecar_hev, 12);
        sparseIntArray.put(R.layout.view_plan_usecar_hev_byd, 13);
    }

    @Override // b.j.d
    public List<d> a() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        return arrayList;
    }

    @Override // b.j.d
    public ViewDataBinding b(e eVar, View view, int i) {
        int i2 = f1951a.get(i);
        if (i2 <= 0) {
            return null;
        }
        Object tag = view.getTag();
        if (tag == null) {
            throw new RuntimeException("view must have a tag");
        }
        switch (i2) {
            case 1:
                if ("layout/activity_charge_main_0".equals(tag)) {
                    return new b(eVar, view);
                }
                if ("layout-port/activity_charge_main_0".equals(tag)) {
                    return new c(eVar, view);
                }
                throw new IllegalArgumentException("The tag for activity_charge_main is invalid. Received: " + tag);
            case 2:
                if ("layout-port/activity_charge_main_byd_0".equals(tag)) {
                    return new f(eVar, view);
                }
                if ("layout/activity_charge_main_byd_0".equals(tag)) {
                    return new c.a.b.c.e(eVar, view);
                }
                throw new IllegalArgumentException("The tag for activity_charge_main_byd is invalid. Received: " + tag);
            case 3:
                if ("layout-ldrtl-port/activity_setinfo_0".equals(tag)) {
                    return new j(eVar, view);
                }
                if ("layout/activity_setinfo_0".equals(tag)) {
                    return new h(eVar, view);
                }
                if ("layout-ldrtl-land/activity_setinfo_0".equals(tag)) {
                    return new i(eVar, view);
                }
                if ("layout-port/activity_setinfo_0".equals(tag)) {
                    return new k(eVar, view);
                }
                throw new IllegalArgumentException("The tag for activity_setinfo is invalid. Received: " + tag);
            case 4:
                if ("layout-port/activity_setinfo_byd_0".equals(tag)) {
                    return new n(eVar, view);
                }
                if ("layout/activity_setinfo_byd_0".equals(tag)) {
                    return new m(eVar, view);
                }
                throw new IllegalArgumentException("The tag for activity_setinfo_byd is invalid. Received: " + tag);
            case 5:
                if ("layout/activity_test_0".equals(tag)) {
                    return new p(eVar, view);
                }
                throw new IllegalArgumentException("The tag for activity_test is invalid. Received: " + tag);
            case 6:
                if ("layout/anim_charge_view_0".equals(tag)) {
                    return new r(eVar, view);
                }
                throw new IllegalArgumentException("The tag for anim_charge_view is invalid. Received: " + tag);
            case 7:
                if ("layout/anim_charge_view_byd_0".equals(tag)) {
                    return new t(eVar, view);
                }
                throw new IllegalArgumentException("The tag for anim_charge_view_byd is invalid. Received: " + tag);
            case 8:
                if ("layout/view_plan_charge_0".equals(tag)) {
                    return new v(eVar, view);
                }
                if ("layout-port/view_plan_charge_0".equals(tag)) {
                    return new w(eVar, view);
                }
                throw new IllegalArgumentException("The tag for view_plan_charge is invalid. Received: " + tag);
            case 9:
                if ("layout/view_plan_charge_byd_0".equals(tag)) {
                    return new y(eVar, view);
                }
                if ("layout-port/view_plan_charge_byd_0".equals(tag)) {
                    return new z(eVar, view);
                }
                throw new IllegalArgumentException("The tag for view_plan_charge_byd is invalid. Received: " + tag);
            case 10:
                if ("layout-port/view_plan_usecar_0".equals(tag)) {
                    return new c0(eVar, view);
                }
                if ("layout/view_plan_usecar_0".equals(tag)) {
                    return new b0(eVar, view);
                }
                throw new IllegalArgumentException("The tag for view_plan_usecar is invalid. Received: " + tag);
            case 11:
                if ("layout-port/view_plan_usecar_byd_0".equals(tag)) {
                    return new f0(eVar, view);
                }
                if ("layout/view_plan_usecar_byd_0".equals(tag)) {
                    return new e0(eVar, view);
                }
                throw new IllegalArgumentException("The tag for view_plan_usecar_byd is invalid. Received: " + tag);
            case 12:
                if ("layout/view_plan_usecar_hev_0".equals(tag)) {
                    return new h0(eVar, view);
                }
                if ("layout-port/view_plan_usecar_hev_0".equals(tag)) {
                    return new i0(eVar, view);
                }
                throw new IllegalArgumentException("The tag for view_plan_usecar_hev is invalid. Received: " + tag);
            case 13:
                if ("layout/view_plan_usecar_hev_byd_0".equals(tag)) {
                    return new k0(eVar, view);
                }
                if ("layout-port/view_plan_usecar_hev_byd_0".equals(tag)) {
                    return new l0(eVar, view);
                }
                throw new IllegalArgumentException("The tag for view_plan_usecar_hev_byd is invalid. Received: " + tag);
            default:
                return null;
        }
    }

    @Override // b.j.d
    public ViewDataBinding c(e eVar, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || f1951a.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }
}
