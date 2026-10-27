package com.byd.carsettings.adapter;

import android.content.Context;
import b.b.g.q.f.c.a;
import b.b.g.q.f.c.b;
import com.byd.mvp.mvp.adapter.XPagerAdapter;

/* JADX INFO: loaded from: classes2.dex */
public class PermissionPagerAdapter extends XPagerAdapter {
    public void f(Context context) {
        b bVar = new b(context);
        a aVar = new a(context);
        bVar.s(aVar);
        aVar.r(bVar);
        b(bVar);
        b(aVar);
        notifyDataSetChanged();
    }
}
