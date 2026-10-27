package com.byd.carsettings.adapter.dilink;

import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import b.b.g.r.z;
import com.byd.base.view.BydSwitch;
import com.byd.carsettings.R;
import com.byd.mvp.ui.adapter.BtnType;
import com.byd.mvp.ui.adapter.BydAdapter;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AppPermissionAdapter extends BydAdapter<b.b.g.m.d.c> {

    public class a implements BydSwitch.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ c f2678a;

        public a(c cVar) {
            this.f2678a = cVar;
        }

        @Override // com.byd.base.view.BydSwitch.a
        public void OnBydSwitchTouch(View view, MotionEvent motionEvent, boolean z) {
            this.f2678a.f2684c.playSoundEffect(0);
            if (motionEvent.getAction() != 1) {
                return;
            }
            if (this.f2678a.f2684c.isEnabled()) {
                this.f2678a.f2684c.setChecked(z);
            } else {
                z.c(view.getContext(), view.getContext().getString(R.string.dilink_system_permission_not_update));
            }
        }
    }

    public class b implements CompoundButton.OnCheckedChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f2680a;

        public b(int i) {
            this.f2680a = i;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (AppPermissionAdapter.this.mBtnCheckedCallback != null) {
                AppPermissionAdapter.this.mBtnCheckedCallback.a(this.f2680a, z, BtnType.BTN_0);
            }
        }
    }

    public class c extends BydAdapter<b.b.g.m.d.c>.BaseViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ImageView f2682a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public TextView f2683b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public BydSwitch f2684c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public View f2685d;

        public c(View view) {
            super(view);
            a(view);
        }

        public final void a(View view) {
            this.f2682a = (ImageView) view.findViewById(R.id.iv_permission_icon_item_permission_list);
            this.f2683b = (TextView) view.findViewById(R.id.tv_permission_name_item_app_list);
            this.f2684c = (BydSwitch) view.findViewById(R.id.switch_item_permission_list);
            this.f2685d = view.findViewById(R.id.v_line_item_permission_list);
        }
    }

    public AppPermissionAdapter(List<b.b.g.m.d.c> list) {
        super(list);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public BydAdapter<b.b.g.m.d.c>.BaseViewHolder initViewHolder(View view) {
        return new c(view);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public int loadLayoutId() {
        return R.layout.item_permission_list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        c cVar = (c) viewHolder;
        cVar.itemView.setClickable(false);
        b.b.g.m.d.c cVar2 = (b.b.g.m.d.c) this.data.get(i);
        if (cVar2.e() != null && cVar2.e().getImgId() != 0) {
            cVar.f2682a.setImageResource(cVar2.e().getImgId());
        }
        cVar.f2683b.setText(cVar2.b());
        BydSwitch bydSwitch = cVar.f2684c;
        bydSwitch.setChecked(cVar2.f(bydSwitch.getContext()), false);
        if (cVar2.h(cVar.f2684c.getContext()) || cVar2.g()) {
            cVar.f2684c.setEnabled(false);
            cVar.f2684c.setAlpha(0.5f);
        } else {
            cVar.f2684c.setEnabled(true);
            cVar.f2684c.setAlpha(1.0f);
        }
        cVar.f2684c.setOnInterceptTouchListener(new a(cVar));
        cVar.f2684c.setOnCheckedChangeListener(new b(i));
        if (i == this.data.size() - 1) {
            cVar.f2685d.setVisibility(8);
        } else {
            cVar.f2685d.setVisibility(0);
        }
    }
}
