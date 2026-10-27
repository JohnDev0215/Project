package com.byd.ota.view.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import c.a.b.d.c;
import c.a.b.d.j;
import c.a.b.d.l;
import c.a.g.h.d;
import c.a.g.h.e;
import c.a.g.l.h;
import com.byd.ota.R;
import com.byd.repository.bean.request.RequestRegisterInfo;
import com.byd.repository.bean.response.ResponseStatusCodeInfo;

/* JADX INFO: loaded from: classes.dex */
public class OtaNetTestFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f3118c = OtaNetTestFragment.class.getSimpleName();
    public ToggleButton E3;
    public b F3;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TextView f3119d;
    public RadioGroup s;
    public Button x;
    public TextView y;

    public class a implements d<ResponseStatusCodeInfo, String> {
        public a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void d(String str, Throwable th) {
            OtaNetTestFragment.this.y.setText("msg = " + str + ", e = " + th.toString());
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void f(ResponseStatusCodeInfo responseStatusCodeInfo) {
            OtaNetTestFragment.this.y.setText("status = " + responseStatusCodeInfo.status);
        }

        @Override // c.a.g.h.d
        /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
        public void a(final String str, final Throwable th) {
            l.d().b(new Runnable() { // from class: c.a.d.i.d1.h
                @Override // java.lang.Runnable
                public final void run() {
                    this.f2554c.d(str, th);
                }
            });
        }

        @Override // c.a.g.h.d
        /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
        public void b(final ResponseStatusCodeInfo responseStatusCodeInfo) {
            l.d().b(new Runnable() { // from class: c.a.d.i.d1.g
                @Override // java.lang.Runnable
                public final void run() {
                    this.f2552c.f(responseStatusCodeInfo);
                }
            });
        }
    }

    public interface b {
        void d(Bundle bundle);
    }

    public final void b() {
        e eVar = new e(ResponseStatusCodeInfo.class, new a());
        RequestRegisterInfo requestRegisterInfo = new RequestRegisterInfo();
        requestRegisterInfo.vin = c.a.b.c.a.k().c();
        requestRegisterInfo.iccid = c.a.b.c.a.k().E();
        c.a.g.g.a.l().i(eVar, h.f(requestRegisterInfo));
    }

    @Override // android.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.F3 = (b) getActivity();
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) throws Throwable {
        if (compoundButton.getId() != R.id.ota_net_test_env_tb) {
            return;
        }
        c.a.g.l.l.n(z);
        c.a.g.e.h.p(z);
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup radioGroup, int i2) {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ota_net_test_confirm_btn /* 2131231001 */:
                switch (this.s.getCheckedRadioButtonId()) {
                    case R.id.ota_net_test_apn1_rb /* 2131230999 */:
                        Toast.makeText(getContext(), "apn1 is checked", 1).show();
                        b();
                        break;
                    case R.id.ota_net_test_apn3_rb /* 2131231000 */:
                        Toast.makeText(getContext(), "apn3 is checked", 1).show();
                        this.y.setText("APN3 network is " + c.e(getActivity()).d());
                        break;
                    default:
                        Toast.makeText(getContext(), "none is checked", 1).show();
                        break;
                }
                break;
            case R.id.ota_net_test_date_tv /* 2131231002 */:
                if (c.a.b.d.e.d()) {
                    Bundle bundle = new Bundle();
                    bundle.putString("method_name", "showHiddenItem");
                    this.F3.d(bundle);
                }
                break;
        }
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_ota_net_test, (ViewGroup) null);
        this.f3119d = (TextView) viewInflate.findViewById(R.id.ota_net_test_date_tv);
        this.s = (RadioGroup) viewInflate.findViewById(R.id.ota_net_test_rg);
        this.x = (Button) viewInflate.findViewById(R.id.ota_net_test_confirm_btn);
        this.y = (TextView) viewInflate.findViewById(R.id.ota_net_test_result_tv);
        this.E3 = (ToggleButton) viewInflate.findViewById(R.id.ota_net_test_env_tb);
        this.f3119d.setOnClickListener(this);
        this.s.setOnCheckedChangeListener(this);
        this.x.setOnClickListener(this);
        this.E3.setOnCheckedChangeListener(this);
        return viewInflate;
    }

    @Override // android.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.F3 = null;
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        this.f3119d.setText(j.a("date"));
        this.E3.setChecked(c.a.g.l.l.h());
    }
}
