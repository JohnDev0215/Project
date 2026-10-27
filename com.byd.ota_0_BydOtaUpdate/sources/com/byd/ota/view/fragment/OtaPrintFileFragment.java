package com.byd.ota.view.fragment;

import android.app.Fragment;
import android.content.Context;
import android.hardware.bydauto.ota.DiagnosticInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import c.a.b.b.a;
import c.a.b.d.d;
import c.a.g.l.h;
import com.byd.ota.R;
import com.byd.ota.view.widget.FlowRadioGroup;
import com.byd.repository.bean.DiagnoseEcuInfo;
import com.byd.repository.bean.EcuOtaResult;
import com.byd.repository.bean.UpdateResultInfo;
import com.byd.repository.bean.request.RequestDownloadReportInfo;
import com.byd.repository.bean.request.RequestSubmitReportInfo;
import com.byd.repository.bean.response.ResponseCheckVersionInfo;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class OtaPrintFileFragment extends Fragment implements View.OnClickListener {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f3121c = OtaPrintFileFragment.class.getSimpleName();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public FlowRadioGroup f3122d;
    public Button s;
    public TextView x;
    public File[] y;

    @Override // android.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) throws Throwable {
        String strA;
        if (view.getId() != R.id.ota_print_file_confirm_btn) {
            return;
        }
        int checkedRadioButtonId = this.f3122d.getCheckedRadioButtonId();
        File[] fileArr = this.y;
        if (fileArr == null || fileArr.length <= 0) {
            return;
        }
        if (checkedRadioButtonId > -1) {
            strA = d.a(fileArr[checkedRadioButtonId].getPath());
            String str = this.y[checkedRadioButtonId].getName().split("\\.")[0];
            str.hashCode();
            switch (str) {
                case "updateResultInfo":
                    strA = h.f((UpdateResultInfo) h.d(strA, UpdateResultInfo.class));
                    break;
                case "upgradeInfo":
                case "ecuOtaResult":
                    strA = h.f((EcuOtaResult) h.d(strA, EcuOtaResult.class));
                    break;
                case "diagnoseEcuInfo":
                    strA = h.f(h.a(strA, DiagnoseEcuInfo.class));
                    break;
                case "illegal_strategy":
                case "strategy":
                    strA = h.f((ResponseCheckVersionInfo) h.d(strA, ResponseCheckVersionInfo.class));
                    break;
                case "downloadReportInfo":
                    strA = h.f((RequestDownloadReportInfo) h.d(strA, RequestDownloadReportInfo.class));
                    break;
                case "submitReportInfo":
                    strA = h.f((RequestSubmitReportInfo) h.d(strA, RequestSubmitReportInfo.class));
                    break;
                case "diagnosticTroubleCode":
                    strA = h.f(h.a(strA, DiagnosticInfo.class));
                    break;
            }
            this.x.setText(strA);
        }
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_ota_print_file, (ViewGroup) null);
        this.f3122d = (FlowRadioGroup) viewInflate.findViewById(R.id.ota_print_file_radio_group);
        this.s = (Button) viewInflate.findViewById(R.id.ota_print_file_confirm_btn);
        this.x = (TextView) viewInflate.findViewById(R.id.ota_print_file_result_tv);
        this.s.setOnClickListener(this);
        return viewInflate;
    }

    @Override // android.app.Fragment
    public void onDetach() {
        super.onDetach();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        File[] fileArrListFiles = new File(a.f2321b).listFiles();
        this.y = fileArrListFiles;
        if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
            return;
        }
        this.f3122d.removeAllViews();
        for (int i2 = 0; i2 < this.y.length; i2++) {
            RadioButton radioButton = new RadioButton(getActivity());
            radioButton.setId(i2);
            radioButton.setText(this.y[i2].getName());
            this.f3122d.addView(radioButton);
        }
    }
}
