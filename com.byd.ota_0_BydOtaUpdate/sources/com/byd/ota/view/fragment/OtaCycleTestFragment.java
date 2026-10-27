package com.byd.ota.view.fragment;

import android.app.Fragment;
import android.content.Context;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.ota.BYDAutoOtaDevice;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import c.a.b.d.d;
import c.a.g.l.l;
import com.byd.ota.R;
import java.io.File;
import java.io.FilenameFilter;

/* JADX INFO: loaded from: classes.dex */
public class OtaCycleTestFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f3111c = OtaCycleTestFragment.class.getSimpleName();
    public a E3;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Switch f3112d;
    public Switch s;
    public Switch x;
    public Context y;

    public interface a {
    }

    public final void a() {
        b();
        d.c(this.y.getFilesDir().getPath() + "/1.auto.upgrade");
    }

    public final void b() {
        File[] fileArrListFiles = new File(this.y.getFilesDir().getPath()).listFiles(new FilenameFilter() { // from class: c.a.d.i.d1.b
            @Override // java.io.FilenameFilter
            public final boolean accept(File file, String str) {
                return str.endsWith(".auto.upgrade");
            }
        });
        if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
            return;
        }
        for (File file : fileArrListFiles) {
            d.g(file.getPath());
        }
    }

    public final boolean c() {
        return d.o(this.y.getFilesDir().getPath(), "1.auto.upgrade");
    }

    public void d(Context context) {
        this.y = context;
    }

    @Override // android.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.E3 = (a) getActivity();
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) throws Throwable {
        int id = compoundButton.getId();
        if (id == R.id.ota_cycle_test_bench_switch) {
            if (!compoundButton.isChecked()) {
                l.k(false);
                return;
            } else if (!TextUtils.isEmpty(c.a.b.c.a.k().c())) {
                l.k(true);
                return;
            } else {
                c.a.d.h.d.a(getContext(), getString(R.string.ota_tool_cycle_test_vin_empty), 1);
                this.s.setChecked(false);
                return;
            }
        }
        if (id != R.id.ota_cycle_test_vehicle_switch) {
            if (id != R.id.usb_cycle_test_media_switch) {
                return;
            }
            if (compoundButton.isChecked()) {
                a();
                return;
            } else {
                b();
                return;
            }
        }
        if (compoundButton.isChecked()) {
            l.m(true);
            return;
        }
        l.m(false);
        if (BYDAutoBodyworkDevice.getInstance(getActivity()).getPowerLevel() == 0) {
            BYDAutoOtaDevice.getInstance(getActivity()).setBackLightState(2);
        }
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_ota_cycle_test, (ViewGroup) null);
        this.f3112d = (Switch) viewInflate.findViewById(R.id.ota_cycle_test_vehicle_switch);
        this.s = (Switch) viewInflate.findViewById(R.id.ota_cycle_test_bench_switch);
        this.x = (Switch) viewInflate.findViewById(R.id.usb_cycle_test_media_switch);
        this.f3112d.setOnCheckedChangeListener(this);
        this.s.setOnCheckedChangeListener(this);
        this.x.setOnCheckedChangeListener(this);
        return viewInflate;
    }

    @Override // android.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.E3 = null;
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        this.f3112d.setChecked(l.g());
        this.s.setChecked(l.e());
        this.x.setChecked(c());
    }
}
