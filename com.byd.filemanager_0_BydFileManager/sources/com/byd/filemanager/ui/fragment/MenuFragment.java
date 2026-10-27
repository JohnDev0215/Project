package com.byd.filemanager.ui.fragment;

import android.content.res.Configuration;
import android.os.SystemClock;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.filemanager.R;
import com.byd.filemanager.bean.FileState;
import com.byd.filemanager.ui.d.i;
import com.byd.music.f.h;
import com.byd.music.f.j;
import org.greenrobot.eventbus.l;

/* JADX INFO: loaded from: classes.dex */
public class MenuFragment extends BaseFragment {
    private RecyclerView c0;
    private com.byd.filemanager.ui.b.c d0;
    private LinearLayoutManager e0;
    private long f0;

    @Override // com.byd.filemanager.ui.fragment.BaseFragment
    protected void A1(View view) {
        this.c0 = (RecyclerView) view.findViewById(R.id.recyclerView);
        if (com.byd.filemanager.f.h().v()) {
            if (com.byd.filemanager.f.h().x()) {
                this.c0.setBackgroundResource(R.drawable.img_menu_bg_rh);
            } else {
                this.c0.setBackgroundResource(R.drawable.img_menu_bg);
            }
        }
        AppCompatActivity appCompatActivity = this.a0;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(appCompatActivity, com.byd.filemanager.k.c.d(appCompatActivity) ? 1 : 0, false);
        this.e0 = linearLayoutManager;
        this.c0.setLayoutManager(linearLayoutManager);
        if (com.byd.filemanager.k.c.e(j1())) {
            this.c0.setOverScrollMode(0);
            i.a(this.c0, 1);
        } else {
            this.c0.setOverScrollMode(2);
        }
        com.byd.filemanager.ui.b.c cVar = new com.byd.filemanager.ui.b.c(this.a0);
        this.d0 = cVar;
        cVar.K(new com.byd.filemanager.ui.a<String>() { // from class: com.byd.filemanager.ui.fragment.MenuFragment.1
            @Override // com.byd.filemanager.ui.a
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void a(String str, final int i) {
                com.byd.filemanager.k.b.b("position = " + i);
                if (i < 0 || SystemClock.uptimeMillis() - MenuFragment.this.f0 < 100) {
                    com.byd.filemanager.k.b.b("menuIndex = " + com.byd.filemanager.f.h().j());
                    MenuFragment.this.c0.postDelayed(new Runnable() { // from class: com.byd.filemanager.ui.fragment.MenuFragment.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MenuFragment.this.d0.F(com.byd.filemanager.f.h().j(), i);
                        }
                    }, 100L);
                    return;
                }
                MenuFragment.this.f0 = SystemClock.uptimeMillis();
                if (com.byd.filemanager.k.c.e(MenuFragment.this.a0)) {
                    int iF2 = MenuFragment.this.e0.f2();
                    int iL2 = MenuFragment.this.e0.l2();
                    com.byd.filemanager.k.b.b("first = " + iF2 + " ,position = " + i + " ,last = " + iL2);
                    if (i == iF2) {
                        if (iF2 > 0) {
                            MenuFragment.this.c0.j1(i - 1);
                        }
                    } else if (i == iL2 && iL2 < MenuFragment.this.d0.c() - 1) {
                        MenuFragment.this.c0.j1(i + 1);
                    }
                }
                com.byd.filemanager.k.b.b("post event");
                com.byd.filemanager.f.h().I(i);
                org.greenrobot.eventbus.c.c().k(new com.byd.music.f.e(i));
            }
        });
        this.d0.I(com.byd.filemanager.f.h().j());
        this.c0.setAdapter(this.d0);
    }

    @Override // com.byd.filemanager.ui.fragment.BaseFragment
    protected int B1() {
        return com.byd.filemanager.f.h().v() ? com.byd.filemanager.f.h().x() ? R.layout.fragment_menu_rh : R.layout.fragment_menu : R.layout.fragment_menu_denza;
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        int i = configuration.orientation;
        if (i == 1) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.a0, 0, false);
            this.e0 = linearLayoutManager;
            this.c0.setLayoutManager(linearLayoutManager);
            this.c0.setHorizontalFadingEdgeEnabled(true);
            this.c0.setFadingEdgeLength(K().getDimensionPixelSize(R.dimen.dp_100));
            this.c0.setOverScrollMode(0);
            i.a(this.c0, 1);
            return;
        }
        if (i == 2) {
            LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this.a0, 1, false);
            this.e0 = linearLayoutManager2;
            this.c0.setLayoutManager(linearLayoutManager2);
            this.c0.setHorizontalFadingEdgeEnabled(false);
            this.c0.setOverScrollMode(2);
        }
    }

    @l
    public void onFileStateEvent(com.byd.music.f.d dVar) {
        if (com.byd.filemanager.f.h().g() == FileState.PASTING) {
            this.c0.j1(0);
            this.d0.I(com.byd.filemanager.f.h().j());
            this.d0.h();
        }
    }

    @l
    public void onPathEvent(h hVar) {
        com.byd.filemanager.k.b.b("onPathEvent");
        if (hVar.b() == 14) {
            this.d0.I(com.byd.filemanager.f.h().j());
            this.d0.h();
        }
    }

    @l
    public void onVisitEvent(j jVar) {
        com.byd.filemanager.k.b.b("onVisitEvent");
        View childAt = this.c0.getChildAt(com.byd.filemanager.f.h().j());
        childAt.setSoundEffectsEnabled(false);
        childAt.performClick();
        childAt.setSoundEffectsEnabled(true);
    }

    @Override // com.byd.filemanager.ui.fragment.BaseFragment
    protected boolean z1() {
        return true;
    }
}
