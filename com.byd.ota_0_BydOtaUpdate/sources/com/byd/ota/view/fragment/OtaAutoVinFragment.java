package com.byd.ota.view.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import c.a.b.d.e;
import c.a.b.d.h;
import com.byd.ota.R;

/* JADX INFO: loaded from: classes.dex */
public class OtaAutoVinFragment extends Fragment implements View.OnClickListener {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f3109c = OtaAutoVinFragment.class.getSimpleName();
    public a E3;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Button f3110d;
    public Button s;
    public Button x;
    public TextView y;

    public interface a {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void b(EditText editText, DialogInterface dialogInterface, int i2) {
        String strTrim = editText.getText().toString().trim();
        if (TextUtils.isEmpty(strTrim)) {
            Toast.makeText(getActivity(), getString(R.string.ota_tool_cycle_test_input_vin_empty), 0).show();
        } else {
            if (strTrim.length() != 17) {
                Toast.makeText(getActivity(), getString(R.string.ota_tool_cycle_test_vin_length_incorrect), 0).show();
                return;
            }
            Toast.makeText(getActivity(), strTrim, 0).show();
            c.a.b.c.a.k().H(strTrim);
            this.y.setText(TextUtils.isEmpty(c.a.b.c.a.k().c()) ? "none" : c.a.b.c.a.k().c());
        }
    }

    public final void c() throws Throwable {
        String strTrim = BYDAutoBodyworkDevice.getInstance(getActivity()).getRealAutoVIN().trim();
        String strC = h.c("persist.vendor.bosch.cfg.diag.vin");
        if (!TextUtils.isEmpty(strTrim)) {
            c.a.b.c.a.k().H(strTrim);
        } else if (!TextUtils.isEmpty(strC)) {
            c.a.b.c.a.k().H(strC);
        } else {
            e.b(f3109c, "autoVin is empty");
            c.a.b.c.a.k().H("");
        }
    }

    public final void d() {
        final EditText editText = new EditText(getActivity());
        editText.setInputType(1);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(17)});
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.ota_tool_write_vin)).setIcon(android.R.drawable.ic_dialog_info).setView(editText).setNegativeButton(getString(R.string.cancel), (DialogInterface.OnClickListener) null);
        builder.setPositiveButton(getString(R.string.confirm), new DialogInterface.OnClickListener() { // from class: c.a.d.i.d1.a
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                this.f2542c.b(editText, dialogInterface, i2);
            }
        });
        builder.show();
    }

    @Override // android.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.E3 = (a) getActivity();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) throws Throwable {
        switch (view.getId()) {
            case R.id.ota_cycle_test_read_vin_btn /* 2131230988 */:
                this.y.setText(TextUtils.isEmpty(c.a.b.c.a.k().c()) ? "none" : c.a.b.c.a.k().c());
                break;
            case R.id.ota_cycle_test_reset_vin_btn /* 2131230989 */:
                c();
                this.y.setText(TextUtils.isEmpty(c.a.b.c.a.k().c()) ? "none" : c.a.b.c.a.k().c());
                break;
            case R.id.ota_cycle_test_write_vin_btn /* 2131230992 */:
                d();
                break;
        }
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_ota_auto_vin, (ViewGroup) null);
        this.f3110d = (Button) viewInflate.findViewById(R.id.ota_cycle_test_read_vin_btn);
        this.s = (Button) viewInflate.findViewById(R.id.ota_cycle_test_write_vin_btn);
        this.x = (Button) viewInflate.findViewById(R.id.ota_cycle_test_reset_vin_btn);
        this.y = (TextView) viewInflate.findViewById(R.id.ota_cycle_test_result_tv);
        this.f3110d.setOnClickListener(this);
        this.s.setOnClickListener(this);
        this.x.setOnClickListener(this);
        return viewInflate;
    }

    @Override // android.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.E3 = null;
    }
}
