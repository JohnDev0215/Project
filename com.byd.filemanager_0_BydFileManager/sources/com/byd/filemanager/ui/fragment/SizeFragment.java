package com.byd.filemanager.ui.fragment;

import android.view.View;
import com.byd.filemanager.R;
import com.byd.filemanager.ui.view.ScaleView;
import com.byd.music.f.i;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;

/* JADX INFO: loaded from: classes.dex */
public class SizeFragment extends BaseFragment {
    private ScaleView c0;
    private io.reactivex.disposables.b d0;

    private void D1() {
        io.reactivex.disposables.b bVar = this.d0;
        if (bVar != null && !bVar.isDisposed()) {
            this.d0.dispose();
        }
        String strL = com.byd.filemanager.e.f().l(com.byd.filemanager.f.h().k());
        this.c0.setVisibility(strL != null ? 0 : 4);
        if (strL != null) {
            this.d0 = com.byd.filemanager.bean.c.e(strL).k(d.a.p.a.a()).f(d.a.l.b.a.a()).h(new d.a.m.e<com.byd.filemanager.bean.c>() { // from class: com.byd.filemanager.ui.fragment.SizeFragment.1
                @Override // d.a.m.e
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void accept(com.byd.filemanager.bean.c cVar) throws Exception {
                    if (cVar == null) {
                        SizeFragment.this.c0.setVisibility(4);
                    } else {
                        SizeFragment.this.c0.setVisibility(0);
                        SizeFragment.this.c0.setScale(cVar);
                    }
                }
            });
        }
    }

    @Override // com.byd.filemanager.ui.fragment.BaseFragment
    protected void A1(View view) {
        this.c0 = (ScaleView) view.findViewById(R.id.scaleView);
        D1();
    }

    @Override // com.byd.filemanager.ui.fragment.BaseFragment
    protected int B1() {
        return R.layout.fragment_size;
    }

    @l(threadMode = ThreadMode.MAIN)
    public void onDiskEvent(com.byd.music.f.b bVar) {
        D1();
    }

    @l(threadMode = ThreadMode.MAIN)
    public void onFileChangeEvent(com.byd.music.f.c cVar) {
        D1();
    }

    @l(threadMode = ThreadMode.MAIN)
    public void onTabEvent(i iVar) {
        D1();
    }

    @Override // com.byd.filemanager.ui.fragment.BaseFragment
    protected boolean z1() {
        return true;
    }
}
