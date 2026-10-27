package com.byd.engineermode.bean;

import android.content.Context;
import com.byd.engineermode.utils.StringArrayToList;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ElectricContact.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    b f2289a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    List<ElectricBean> f2290b;

    public static a c(Context context, int i, int i2) {
        a aVar = new a();
        aVar.d(new b());
        ArrayList<String> arrayListF = StringArrayToList.h().f(i);
        if (arrayListF != null && arrayListF.size() > 0) {
            aVar.a().d(String.valueOf(i2));
            aVar.e(new ArrayList());
            for (int i3 = 0; i3 < arrayListF.size(); i3++) {
                ElectricBean electricBean = new ElectricBean(String.valueOf(i3));
                com.byd.engineermode.utils.a.a("init ElectricContact mArrayList.get(i) = " + arrayListF.get(i3));
                aVar.b().add(electricBean);
            }
        }
        return aVar;
    }

    public b a() {
        return this.f2289a;
    }

    public List<ElectricBean> b() {
        return this.f2290b;
    }

    public void d(b bVar) {
        this.f2289a = bVar;
    }

    public void e(List<ElectricBean> list) {
        this.f2290b = list;
    }
}
