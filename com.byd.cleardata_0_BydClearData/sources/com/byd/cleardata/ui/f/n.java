package com.byd.cleardata.ui.f;

import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.cleardata.R;

/* JADX INFO: loaded from: classes.dex */
public class n extends h {
    private RecyclerView b0;
    private com.byd.cleardata.ui.d.d c0;
    private LinearLayoutManager d0;

    class a implements com.byd.cleardata.ui.c<String> {
        a() {
        }

        @Override // com.byd.cleardata.ui.c
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(String str, int i) {
            com.byd.cleardata.i.c.b("position = " + i);
            com.byd.cleardata.i.c.b("post event");
            com.byd.cleardata.b.h().A(i);
            org.greenrobot.eventbus.c.c().k(new com.byd.cleardata.e.e(i));
            if (i == 2) {
                com.byd.cleardata.b.h().y(com.byd.cleardata.d.e.CHOOSING);
            }
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            View childAt = n.this.b0.getChildAt(com.byd.cleardata.b.h().j());
            childAt.setSoundEffectsEnabled(false);
            childAt.performClick();
            childAt.setSoundEffectsEnabled(true);
        }
    }

    @Override // com.byd.cleardata.ui.f.h
    protected void A1(View view) {
        this.b0 = (RecyclerView) view.findViewById(R.id.recyclerView);
        androidx.appcompat.app.c cVar = this.Z;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(cVar, com.byd.cleardata.i.d.d(cVar) ? 1 : 0, false);
        this.d0 = linearLayoutManager;
        this.b0.setLayoutManager(linearLayoutManager);
        if (com.byd.cleardata.i.d.e(j1())) {
            this.b0.setOverScrollMode(0);
            com.byd.cleardata.ui.g.i.a(this.b0, 1);
        } else {
            this.b0.setOverScrollMode(2);
        }
        com.byd.cleardata.ui.d.d dVar = new com.byd.cleardata.ui.d.d(this.Z);
        this.c0 = dVar;
        dVar.H(new a());
        this.c0.G(com.byd.cleardata.b.h().j());
        this.b0.setAdapter(this.c0);
        if (com.byd.cleardata.i.a.i() && com.byd.cleardata.i.d.d(j1())) {
            this.b0.setBackground(new BitmapDrawable(com.byd.cleardata.i.b.a(BitmapFactory.decodeResource(this.Z.getResources(), R.drawable.img_1menu_bg_h), 180.0f)));
        }
    }

    @Override // com.byd.cleardata.ui.f.h
    protected int B1() {
        return R.layout.fragment_menu;
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        int i = configuration.orientation;
        if (i == 1) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.Z, 0, false);
            this.d0 = linearLayoutManager;
            this.b0.setLayoutManager(linearLayoutManager);
            this.c0.I(1);
            this.b0.setBackgroundResource(R.drawable.img_1menu_bg_v);
            this.b0.setHorizontalFadingEdgeEnabled(true);
            this.b0.setFadingEdgeLength(L().getDimensionPixelSize(R.dimen.dp_100));
            this.b0.setOverScrollMode(0);
            com.byd.cleardata.ui.g.i.a(this.b0, 1);
            return;
        }
        if (i == 2) {
            LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this.Z, 1, false);
            this.d0 = linearLayoutManager2;
            this.b0.setLayoutManager(linearLayoutManager2);
            this.c0.I(0);
            if (com.byd.cleardata.i.a.i()) {
                this.b0.setBackground(new BitmapDrawable(com.byd.cleardata.i.b.a(BitmapFactory.decodeResource(this.Z.getResources(), R.drawable.img_1menu_bg_h), 180.0f)));
            } else {
                this.b0.setBackgroundResource(R.drawable.img_1menu_bg_h);
            }
            this.b0.setHorizontalFadingEdgeEnabled(false);
            this.b0.setOverScrollMode(2);
        }
    }

    @org.greenrobot.eventbus.m
    public void onFileStateEvent(com.byd.cleardata.e.d dVar) {
        if (com.byd.cleardata.b.h().g() == com.byd.cleardata.d.e.PASTING) {
            this.b0.i1(0);
            this.b0.post(new b());
        }
    }

    @org.greenrobot.eventbus.m
    public void onPathEvent(com.byd.cleardata.e.h hVar) {
        com.byd.cleardata.i.c.b("onPathEvent");
        if (hVar.b() == 14) {
            this.c0.G(com.byd.cleardata.b.h().j());
            this.c0.h();
        }
    }

    @org.greenrobot.eventbus.m
    public void onVisitEvent(com.byd.cleardata.e.j jVar) {
        com.byd.cleardata.i.c.b("onVisitEvent");
        View childAt = this.b0.getChildAt(com.byd.cleardata.b.h().j());
        childAt.setSoundEffectsEnabled(false);
        childAt.performClick();
        childAt.setSoundEffectsEnabled(true);
    }

    @Override // com.byd.cleardata.ui.f.h
    protected boolean z1() {
        return true;
    }
}
