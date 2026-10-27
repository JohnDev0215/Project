package b.b.g.q.f.b;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import b.b.m.f.d.d;
import com.byd.carsettings.R;
import java.util.List;

/* JADX INFO: compiled from: AddImePager.java */
/* JADX INFO: loaded from: classes2.dex */
public class b extends b.b.m.e.a<b.b.g.n.p.a> implements RadioGroup.OnCheckedChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f1548a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public RadioGroup f1549b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public List<b.b.g.m.d.b> f1550c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b.b.g.m.d.b f1551d;

    public b(Context context) {
        super(context);
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.p.a newP() {
        return new b.b.g.n.p.a();
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.dialog_def_input_system;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.m.e.f
    public void initData(Context context) {
        this.f1548a.setText(context.getString(R.string.add_input_method));
        this.f1550c = ((b.b.g.n.p.a) mo7getP()).d();
        b.b.m.c.a.a(this.TAG, "--initData---listNotUsed = " + this.f1550c);
        List<b.b.g.m.d.b> list = this.f1550c;
        if (list == null || list.isEmpty()) {
            return;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.f1550c.size(); i2++) {
            if (this.f1550c.get(i2).e()) {
                i = i2;
            }
            b.b.g.m.d.b bVar = this.f1550c.get(i2);
            RadioButton radioButton = (RadioButton) this.lf.inflate(R.layout.item_check_rbtn, (ViewGroup) null, false);
            radioButton.setText(bVar.b());
            if (bVar.e()) {
                i = i2;
            }
            this.f1549b.addView(radioButton);
        }
        RadioGroup radioGroup = this.f1549b;
        radioGroup.check(radioGroup.getChildAt(i).getId());
        this.f1551d = this.f1550c.get(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.m.e.f
    public void initViews(Context context) {
        ((b.b.g.n.p.a) mo7getP()).e();
        this.f1548a = (TextView) this.mRootView.findViewById(R.id.tv_title_dialog_common_titles);
        this.mRootView.findViewById(R.id.iv_back_dialog_common_title).setVisibility(8);
        RadioGroup radioGroup = (RadioGroup) this.mRootView.findViewById(R.id.rg_dialog_def_input_system);
        this.f1549b = radioGroup;
        radioGroup.setOnCheckedChangeListener(this);
        this.mRootView.findViewById(R.id.btn_ok_dialog_bottom).setOnClickListener(this);
        this.mRootView.findViewById(R.id.btn_cancel_dialog_bottom).setOnClickListener(this);
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        this.f1551d = this.f1550c.get(getSubviewIndex(radioGroup, i));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_cancel_dialog_bottom) {
            finish();
            return;
        }
        if (id != R.id.btn_ok_dialog_bottom) {
            return;
        }
        ((b.b.g.n.p.a) mo7getP()).c(this.f1551d);
        finish();
        d.a aVar = this.mOkListener;
        if (aVar != null) {
            aVar.onOKClicked();
        }
    }
}
