package com.byd.carsettings.adapter.dilink;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.base.view.BydSwitch;
import com.byd.carsettings.R;
import com.byd.mvp.ui.adapter.BtnType;
import com.byd.mvp.ui.adapter.BydAdapter;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DisableSelfStartAdapter extends BydAdapter<b.b.g.p.k.a> {

    public class a implements CompoundButton.OnCheckedChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f2707a;

        public a(int i) {
            this.f2707a = i;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (DisableSelfStartAdapter.this.mBtnCheckedCallback != null) {
                DisableSelfStartAdapter.this.mBtnCheckedCallback.a(this.f2707a, z, BtnType.BTN_0);
            }
        }
    }

    public class b extends BydAdapter<b.b.g.p.k.a>.BaseViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ImageView f2709a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public TextView f2710b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public BydSwitch f2711c;

        public b(View view) {
            super(view);
            a(view);
        }

        public final void a(View view) {
            this.f2709a = (ImageView) view.findViewById(R.id.iv_icon_item_disable_self_satrt);
            this.f2710b = (TextView) view.findViewById(R.id.tv_name_item_disable_self_satrt);
            this.f2711c = (BydSwitch) view.findViewById(R.id.switch_item_disable_self_satrt);
        }
    }

    public DisableSelfStartAdapter(List<b.b.g.p.k.a> list) {
        super(list);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public BydAdapter<b.b.g.p.k.a>.BaseViewHolder initViewHolder(View view) {
        return new b(view);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public int loadLayoutId() {
        return R.layout.item_disable_self_start;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        b bVar = (b) viewHolder;
        b.b.g.p.k.a aVar = (b.b.g.p.k.a) this.data.get(i);
        bVar.f2709a.setImageDrawable(aVar.c());
        bVar.f2710b.setText(aVar.a());
        bVar.f2711c.setChecked(aVar.f(), false);
        bVar.f2711c.setOnCheckedChangeListener(new a(i));
    }
}
