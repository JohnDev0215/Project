package b.b.g.q.f.b;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.byd.carsettings.R;

/* JADX INFO: compiled from: HamanSoundBalancePager.java */
/* JADX INFO: loaded from: classes2.dex */
public class o extends b.b.m.e.a implements RadioGroup.OnCheckedChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f1679a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ImageView f1680b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public RadioGroup f1681c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b.b.g.o.g.a f1682d;

    /* JADX INFO: compiled from: HamanSoundBalancePager.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (o.this.mCallback != null) {
                o.this.mCallback.onBackPress();
            }
            o.this.finish();
        }
    }

    public o(Context context) {
        super(context);
    }

    public static void e(int i, ImageView imageView, b.b.g.o.g.a aVar) {
        if (imageView == null) {
            return;
        }
        int i2 = -1;
        switch (i) {
            case R.id.rbtn_all_harman_sound_balance /* 2131297775 */:
                imageView.setImageResource(R.drawable.bg_harman_sound_balance_all);
                i2 = 0;
                break;
            case R.id.rbtn_row_1st_harman_sound_balance /* 2131297847 */:
                imageView.setImageResource(R.drawable.bg_harman_sound_balance_1st);
                i2 = 1;
                break;
            case R.id.rbtn_row_last_harman_sound_balance /* 2131297848 */:
                imageView.setImageResource(R.drawable.bg_harman_sound_balance_last);
                i2 = 2;
                break;
        }
        if (aVar != null) {
            aVar.a(i2);
        }
    }

    public void f(int i, b.b.g.o.g.a aVar) {
        this.f1682d = aVar;
        if (i < this.f1681c.getChildCount()) {
            int id = this.f1681c.getChildAt(i).getId();
            this.f1681c.check(id);
            e(id, this.f1680b, aVar);
        }
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.dialog_harman_sound_balance;
    }

    @Override // b.b.m.e.f
    public void initData(Context context) {
        this.f1679a.setText(context.getString(R.string.sound_field_balance_sound));
    }

    @Override // b.b.m.e.f
    public void initViews(Context context) {
        this.f1679a = (TextView) this.mRootView.findViewById(R.id.tv_title_dialog_common_titles);
        View viewFindViewById = this.mRootView.findViewById(R.id.iv_back_dialog_common_title);
        this.f1680b = (ImageView) this.mRootView.findViewById(R.id.iv_focus_harman_sound_balance);
        this.f1681c = (RadioGroup) this.mRootView.findViewById(R.id.rg_harman_sound_balance);
        viewFindViewById.setOnClickListener(new a());
        this.f1681c.setOnCheckedChangeListener(this);
    }

    @Override // b.b.m.e.h
    public b.b.m.e.g newP() {
        return null;
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        e(i, this.f1680b, this.f1682d);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }
}
