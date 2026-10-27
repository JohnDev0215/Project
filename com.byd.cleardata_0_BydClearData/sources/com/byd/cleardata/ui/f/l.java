package com.byd.cleardata.ui.f;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.byd.cleardata.R;

/* JADX INFO: loaded from: classes.dex */
public class l extends h implements View.OnClickListener {
    private TextView b0;
    private TextView c0;
    private int d0;
    private View e0;

    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            com.byd.cleardata.b.h().C();
        }
    }

    private void C1() {
        TextView textView;
        int i;
        com.byd.cleardata.i.c.b("initViewByState");
        if (com.byd.cleardata.b.h().g() == com.byd.cleardata.d.e.PASTING) {
            this.e0.setBackgroundResource(R.color.bg_footer);
            this.b0.setVisibility(0);
            this.c0.setVisibility(0);
            this.b0.setEnabled(true);
            if (this.d0 == 6) {
                textView = this.b0;
                i = R.string.compress;
            } else {
                textView = this.b0;
                i = R.string.paste;
            }
            textView.setText(R(i));
        } else {
            this.e0.setBackground(null);
            this.d0 = -2;
            this.b0.setVisibility(8);
            this.c0.setVisibility(8);
        }
        D1();
    }

    private void D1() {
        this.b0.setEnabled(com.byd.cleardata.a.f().p(com.byd.cleardata.b.h().k()) && (com.byd.cleardata.b.h().j() == 2));
    }

    @Override // com.byd.cleardata.ui.f.h
    protected void A1(View view) {
        this.e0 = view.findViewById(R.id.root_footer);
        TextView textView = (TextView) view.findViewById(R.id.btn_paste);
        this.b0 = textView;
        textView.setOnClickListener(this);
        TextView textView2 = (TextView) view.findViewById(R.id.btn_paste_cancel);
        this.c0 = textView2;
        textView2.setOnClickListener(new a());
    }

    @Override // com.byd.cleardata.ui.f.h
    protected int B1() {
        return R.layout.fragment_footer;
    }

    @Override // androidx.fragment.app.Fragment
    public void G0(Bundle bundle) {
        super.G0(bundle);
        bundle.putInt("footer_operate", this.d0);
    }

    @Override // androidx.fragment.app.Fragment
    public void e0(Bundle bundle) {
        super.e0(bundle);
        if (bundle != null) {
            this.d0 = bundle.getInt("footer_operate");
            C1();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        com.byd.cleardata.b bVarH;
        com.byd.cleardata.d.e eVar;
        int id = view.getId();
        if (id != R.id.btn_paste) {
            if (id == R.id.btn_paste_cancel) {
                com.byd.cleardata.b.h().C();
                return;
            }
            return;
        }
        int i = this.d0;
        if (i == 4) {
            bVarH = com.byd.cleardata.b.h();
            eVar = com.byd.cleardata.d.e.COPYING;
        } else if (i == 5) {
            bVarH = com.byd.cleardata.b.h();
            eVar = com.byd.cleardata.d.e.MOVING;
        } else {
            if (i != 6) {
                return;
            }
            bVarH = com.byd.cleardata.b.h();
            eVar = com.byd.cleardata.d.e.COMPRESSING;
        }
        bVarH.y(eVar);
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @org.greenrobot.eventbus.m
    public void onFileStateEvent(com.byd.cleardata.e.d dVar) {
        com.byd.cleardata.i.c.b("onFileStateEvent");
        if (com.byd.cleardata.b.h().g() == com.byd.cleardata.d.e.PASTING) {
            this.d0 = ((Integer) dVar.a()).intValue();
        }
        C1();
    }

    @org.greenrobot.eventbus.m
    public void onMenuEvent(com.byd.cleardata.e.e eVar) {
        D1();
    }

    @org.greenrobot.eventbus.m
    public void onTabEvent(com.byd.cleardata.e.i iVar) {
        D1();
    }

    @Override // com.byd.cleardata.ui.f.h
    protected boolean z1() {
        return true;
    }
}
