package com.byd.filemanager.ui.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.byd.filemanager.R;
import com.byd.filemanager.bean.FileState;
import com.byd.music.f.i;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;

/* JADX INFO: loaded from: classes.dex */
public class FooterFragment extends BaseFragment implements View.OnClickListener {
    private TextView c0;
    private TextView d0;
    private int e0;
    private View f0;

    private void C1() {
        if (com.byd.filemanager.f.h().g() == FileState.PASTING) {
            if (com.byd.filemanager.f.h().v()) {
                this.f0.setBackgroundResource(R.color.bg_footer);
            } else {
                this.f0.setBackgroundResource(R.color.bg_footer_denza);
            }
            this.c0.setVisibility(0);
            this.d0.setVisibility(0);
            this.c0.setEnabled(true);
            if (this.e0 == 6) {
                this.c0.setText(Q(R.string.compress));
            } else {
                this.c0.setText(Q(R.string.paste));
            }
        } else {
            this.f0.setBackground(null);
            this.e0 = -2;
            this.c0.setVisibility(8);
            this.d0.setVisibility(8);
        }
        D1();
    }

    private void D1() {
        boolean zS = com.byd.filemanager.e.f().s(com.byd.filemanager.f.h().k());
        boolean z = false;
        boolean z2 = com.byd.filemanager.f.h().j() == 1;
        TextView textView = this.c0;
        if (zS && z2) {
            z = true;
        }
        textView.setEnabled(z);
    }

    @Override // com.byd.filemanager.ui.fragment.BaseFragment
    protected void A1(View view) {
        this.f0 = view.findViewById(R.id.root_footer);
        TextView textView = (TextView) view.findViewById(R.id.btn_paste);
        this.c0 = textView;
        textView.setOnClickListener(this);
        TextView textView2 = (TextView) view.findViewById(R.id.btn_paste_cancel);
        this.d0 = textView2;
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.byd.filemanager.ui.fragment.FooterFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                com.byd.filemanager.f.h().K();
            }
        });
    }

    @Override // com.byd.filemanager.ui.fragment.BaseFragment
    protected int B1() {
        return com.byd.filemanager.f.h().v() ? com.byd.filemanager.f.h().x() ? R.layout.fragment_footer_rh : R.layout.fragment_footer : com.byd.filemanager.f.h().x() ? R.layout.fragment_footer_denza_rh : R.layout.fragment_footer_denza;
    }

    @Override // androidx.fragment.app.Fragment
    public void G0(Bundle bundle) {
        super.G0(bundle);
        bundle.putInt("footer_operate", this.e0);
    }

    @Override // androidx.fragment.app.Fragment
    public void e0(Bundle bundle) {
        super.e0(bundle);
        if (bundle != null) {
            this.e0 = bundle.getInt("footer_operate");
            C1();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.btn_paste) {
            if (id == R.id.btn_paste_cancel) {
                com.byd.filemanager.f.h().K();
                return;
            }
            return;
        }
        int i = this.e0;
        if (i == 4) {
            com.byd.filemanager.f.h().G(FileState.COPYING);
        } else if (i == 5) {
            com.byd.filemanager.f.h().G(FileState.MOVING);
        } else {
            if (i != 6) {
                return;
            }
            com.byd.filemanager.f.h().G(FileState.COMPRESSING);
        }
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @l(threadMode = ThreadMode.MAIN)
    public void onDiskEvent(com.byd.music.f.b bVar) {
        com.byd.filemanager.k.b.c(this.Z, "onDiskEvent volume");
        int iB = bVar.b();
        if (iB == com.byd.filemanager.f.h().k()) {
            com.byd.filemanager.k.b.c(this.Z, "onDiskEvent volume index = " + iB);
            this.c0.setEnabled(com.byd.filemanager.e.f().s(iB));
        }
    }

    @l
    public void onFileStateEvent(com.byd.music.f.d dVar) {
        FileState fileStateG = com.byd.filemanager.f.h().g();
        com.byd.filemanager.k.b.c(this.Z, "onFileStateEvent =" + fileStateG);
        if (fileStateG == FileState.PASTING) {
            this.e0 = ((Integer) dVar.a()).intValue();
        }
        C1();
    }

    @l
    public void onMenuEvent(com.byd.music.f.e eVar) {
        D1();
    }

    @l
    public void onTabEvent(i iVar) {
        D1();
    }

    @Override // com.byd.filemanager.ui.fragment.BaseFragment
    protected boolean z1() {
        return true;
    }
}
