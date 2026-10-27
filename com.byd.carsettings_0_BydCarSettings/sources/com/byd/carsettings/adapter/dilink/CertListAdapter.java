package com.byd.carsettings.adapter.dilink;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.base.view.BydSwitch;
import com.byd.carsettings.R;
import com.byd.mvp.ui.adapter.BtnType;
import com.byd.mvp.ui.adapter.BydAdapter;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CertListAdapter extends BydAdapter<b.b.g.m.d.a> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f2697a;

    public class a implements CompoundButton.OnCheckedChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b.b.g.m.d.a f2698a;

        public a(b.b.g.m.d.a aVar) {
            this.f2698a = aVar;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (CertListAdapter.this.onItemBtnCheckedCallbackV2 != null) {
                this.f2698a.i(z);
                CertListAdapter.this.onItemBtnCheckedCallbackV2.onItemBtnChecked(this.f2698a, z, BtnType.BTN_0);
            }
        }
    }

    public class b extends BydAdapter<b.b.g.m.d.a>.BaseViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public TextView f2700a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public TextView f2701b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public BydSwitch f2702c;

        public b(View view) {
            super(view);
            a(view);
        }

        public final void a(View view) {
            this.f2700a = (TextView) view.findViewById(R.id.tv_cert_name_item_cert_list);
            this.f2701b = (TextView) view.findViewById(R.id.tv_cert_name_a_item_cert_list);
            this.f2702c = (BydSwitch) view.findViewById(R.id.switch_item_cert_list);
        }
    }

    public CertListAdapter(List<b.b.g.m.d.a> list, Context context) {
        super(list);
        this.f2697a = context;
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public BydAdapter<b.b.g.m.d.a>.BaseViewHolder initViewHolder(View view) {
        return new b(view);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public int loadLayoutId() {
        return b.b.o.b.b.a.c() == 2 ? R.layout.item_cert_list_r : R.layout.item_cert_list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        b bVar = (b) viewHolder;
        b.b.g.m.d.a aVar = (b.b.g.m.d.a) this.data.get(i);
        bVar.itemView.setClickable(false);
        bVar.f2700a.setText(aVar.e());
        bVar.f2701b.setText(aVar.d());
        bVar.f2701b.setVisibility(TextUtils.isEmpty(aVar.d()) ? 8 : 0);
        int dimensionPixelOffset = this.f2697a.getResources().getDimensionPixelOffset(R.dimen.dp_10);
        if (TextUtils.isEmpty(aVar.d())) {
            int i2 = (int) (((double) dimensionPixelOffset) * 1.5d);
            bVar.f2700a.setPadding(0, i2, 0, i2);
        } else {
            bVar.f2700a.setPadding(0, dimensionPixelOffset, 0, 0);
        }
        bVar.f2702c.setChecked(aVar.f(), false);
        bVar.f2702c.setOnCheckedChangeListener(new a(aVar));
    }
}
