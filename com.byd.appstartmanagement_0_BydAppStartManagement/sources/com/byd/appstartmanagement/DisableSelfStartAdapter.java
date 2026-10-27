package com.byd.appstartmanagement;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.base.view.BydSwitch;
import com.byd.mvp.ui.adapter.BtnType;
import com.byd.mvp.ui.adapter.BydAdapter;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DisableSelfStartAdapter extends BydAdapter<b.a.a.a> {

    public class a implements CompoundButton.OnCheckedChangeListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f527b;

        public a(int i) {
            this.f527b = i;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (DisableSelfStartAdapter.this.f != null) {
                DisableSelfStartAdapter.this.f.a(this.f527b, z, BtnType.BTN_0);
            }
        }
    }

    public class b extends BydAdapter<b.a.a.a>.BaseViewHolder {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ImageView f529c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public TextView f530d;
        public BydSwitch e;

        public b(View view) {
            super(view);
            b(view);
        }

        public final void b(View view) {
            this.f529c = (ImageView) view.findViewById(R.id.iv_icon_item_disable_self_satrt);
            this.f530d = (TextView) view.findViewById(R.id.tv_name_item_disable_self_satrt);
            this.e = (BydSwitch) view.findViewById(R.id.switch_item_disable_self_satrt);
        }
    }

    public DisableSelfStartAdapter(List<b.a.a.a> list) {
        super(list);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public BydAdapter<b.a.a.a>.BaseViewHolder e(View view) {
        return new b(view);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public int g() {
        return R.layout.item_disable_self_start;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        b bVar = (b) viewHolder;
        b.a.a.a aVar = (b.a.a.a) this.f684b.get(i);
        bVar.f529c.setImageDrawable(aVar.c());
        bVar.f530d.setText(aVar.a());
        bVar.e.setChecked(aVar.d(), false);
        bVar.e.setOnCheckedChangeListener(new a(i));
    }
}
