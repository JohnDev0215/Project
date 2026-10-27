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
public class NotificationAppAdapter extends BydAdapter<b.b.g.p.k.a> {

    public class a implements CompoundButton.OnCheckedChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b.b.g.p.k.a f2719a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f2720b;

        public a(b.b.g.p.k.a aVar, int i) {
            this.f2719a = aVar;
            this.f2720b = i;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            this.f2719a.i(z);
            if (NotificationAppAdapter.this.mBtnCheckedCallback != null) {
                NotificationAppAdapter.this.mBtnCheckedCallback.a(this.f2720b, z, BtnType.BTN_0);
            }
        }
    }

    public class b extends BydAdapter<b.b.g.p.k.a>.BaseViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ImageView f2722a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public TextView f2723b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public BydSwitch f2724c;

        public b(View view) {
            super(view);
            a(view);
        }

        public final void a(View view) {
            this.f2722a = (ImageView) view.findViewById(R.id.iv_icon_item_disable_self_satrt);
            this.f2723b = (TextView) view.findViewById(R.id.tv_name_item_disable_self_satrt);
            this.f2724c = (BydSwitch) view.findViewById(R.id.switch_item_disable_self_satrt);
        }
    }

    public NotificationAppAdapter(List<b.b.g.p.k.a> list) {
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
        bVar.itemView.setClickable(false);
        b.b.g.p.k.a aVar = (b.b.g.p.k.a) this.data.get(i);
        if ("com.spotify.music".equals(aVar.d())) {
            bVar.f2722a.setImageResource(R.drawable.icon_spotify);
        } else {
            bVar.f2722a.setImageDrawable(aVar.c());
        }
        bVar.f2723b.setText(aVar.a());
        bVar.f2724c.setChecked(aVar.f(), false);
        bVar.f2724c.setOnCheckedChangeListener(new a(aVar, i));
    }
}
