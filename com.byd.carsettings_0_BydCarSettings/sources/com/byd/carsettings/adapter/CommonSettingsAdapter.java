package com.byd.carsettings.adapter;

import android.app.Activity;
import androidx.fragment.app.Fragment;
import b.b.g.q.f.a.a;
import b.b.g.q.f.a.b;
import b.b.g.q.f.a.d;
import b.b.g.q.f.a.f;
import b.b.g.q.f.a.g;
import b.b.g.q.f.a.h;
import b.b.g.q.f.a.i;
import b.b.m.e.e;
import com.byd.carsettings.model.CommonSettingItem;
import com.byd.mvp.ui.adapter.PageCardAdapter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CommonSettingsAdapter extends PageCardAdapter<CommonSettingItem> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public WeakReference<Activity> f2594e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public List<e> f2595f;
    public Fragment g;

    public CommonSettingsAdapter(Activity activity, List<CommonSettingItem> list, Fragment fragment) {
        super(list);
        this.f2595f = new ArrayList();
        this.f2594e = new WeakReference<>(activity);
        this.g = fragment;
    }

    @Override // com.byd.mvp.ui.adapter.PageCardAdapter
    public e b(int i) {
        e fVar;
        switch (i) {
            case 0:
                fVar = new f(this.f2594e.get());
                break;
            case 1:
                fVar = new b(this.f2594e.get());
                break;
            case 2:
                fVar = new a(this.f2594e.get());
                break;
            case 3:
                fVar = new d(this.f2594e.get());
                break;
            case 4:
                fVar = new b.b.g.q.f.a.e(this.f2594e.get());
                break;
            case 5:
                fVar = new h(this.f2594e.get());
                break;
            case 6:
                fVar = new g(this.f2594e.get());
                break;
            case 7:
                fVar = new i(this.f2594e.get(), this.g);
                break;
            default:
                fVar = null;
                break;
        }
        this.f2595f.add(fVar);
        return fVar;
    }

    public void e() {
        Iterator<e> it = this.f2595f.iterator();
        while (it.hasNext()) {
            it.next().l();
        }
    }

    @Override // com.byd.mvp.ui.adapter.PageCardAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        List<T> list = this.f3749a;
        if (list == 0 || list.get(i) == null) {
            return 0;
        }
        return ((CommonSettingItem) this.f3749a.get(i)).getViewType();
    }
}
