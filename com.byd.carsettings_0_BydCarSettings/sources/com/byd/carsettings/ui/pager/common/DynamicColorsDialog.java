package com.byd.carsettings.ui.pager.common;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.byd.base.view.dialog.BaseDialogFragment;
import com.byd.carsettings.R;

/* JADX INFO: loaded from: classes2.dex */
public class DynamicColorsDialog extends BaseDialogFragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3264a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextView f3265b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public View f3266c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Button f3267d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Button f3268e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public RadioGroup f3269f;
    public a g;

    public interface a {
        void a(int i);
    }

    public static DynamicColorsDialog u(int i) {
        DynamicColorsDialog dynamicColorsDialog = new DynamicColorsDialog();
        Bundle bundle = new Bundle();
        bundle.putInt("bundle_def_index", i);
        dynamicColorsDialog.setArguments(bundle);
        return dynamicColorsDialog;
    }

    @Override // com.byd.base.view.dialog.BaseDialogFragment
    public int getLayoutResource() {
        return R.layout.dialog_ial_dynamic_colors;
    }

    public final void initData(Bundle bundle) {
        this.f3264a = bundle.getInt("bundle_def_index");
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        this.f3264a = t(radioGroup, i);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        a aVar;
        if (view.getId() == R.id.btn_ok_dialog_bottom && (aVar = this.g) != null) {
            aVar.a(this.f3264a);
        }
        dismiss();
    }

    @Override // com.byd.base.view.dialog.BaseDialogFragment, com.byd.base.view.dialog.LeakDialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        initData(arguments);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f3265b = (TextView) view.findViewById(R.id.tv_title_dialog_common_titles);
        View viewFindViewById = view.findViewById(R.id.iv_back_dialog_common_title);
        this.f3266c = viewFindViewById;
        viewFindViewById.setOnClickListener(this);
        Button button = (Button) view.findViewById(R.id.btn_ok_dialog_bottom);
        this.f3267d = button;
        button.setOnClickListener(this);
        Button button2 = (Button) view.findViewById(R.id.btn_cancel_dialog_bottom);
        this.f3268e = button2;
        button2.setOnClickListener(this);
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.rg_dialog_ial_dynamic_colors);
        this.f3269f = radioGroup;
        radioGroup.setOnCheckedChangeListener(this);
        int i = this.f3264a;
        if (i < 0 || i >= this.f3269f.getChildCount()) {
            return;
        }
        RadioGroup radioGroup2 = this.f3269f;
        radioGroup2.check(radioGroup2.getChildAt(this.f3264a).getId());
    }

    public void setOnOKClickedListener(a aVar) {
        this.g = aVar;
    }

    public final int t(ViewGroup viewGroup, int i) {
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            if (i == viewGroup.getChildAt(i2).getId()) {
                return i2;
            }
        }
        return 0;
    }

    public void v(int i) {
        if (i != this.f3264a) {
            this.f3264a = i;
            if (i < 0 || i >= this.f3269f.getChildCount()) {
                return;
            }
            RadioGroup radioGroup = this.f3269f;
            radioGroup.check(radioGroup.getChildAt(this.f3264a).getId());
        }
    }
}
