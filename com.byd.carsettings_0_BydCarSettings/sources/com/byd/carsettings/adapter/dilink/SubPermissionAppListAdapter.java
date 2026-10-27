package com.byd.carsettings.adapter.dilink;

import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import b.b.g.m.d.i;
import b.b.g.r.z;
import com.byd.base.view.BydSwitch;
import com.byd.carsettings.R;
import com.byd.mvp.ui.adapter.BtnType;
import com.byd.mvp.ui.adapter.BydAdapter;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SubPermissionAppListAdapter extends BydAdapter<i> {

    public class a implements CompoundButton.OnCheckedChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f2750a;

        public a(int i) {
            this.f2750a = i;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (SubPermissionAppListAdapter.this.mBtnCheckedCallback != null) {
                SubPermissionAppListAdapter.this.mBtnCheckedCallback.a(this.f2750a, z, BtnType.BTN_0);
            }
        }
    }

    public class b implements BydSwitch.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ c f2752a;

        public b(c cVar) {
            this.f2752a = cVar;
        }

        @Override // com.byd.base.view.BydSwitch.a
        public void OnBydSwitchTouch(View view, MotionEvent motionEvent, boolean z) {
            this.f2752a.f2756c.playSoundEffect(0);
            if (motionEvent.getAction() != 1) {
                return;
            }
            if (this.f2752a.f2756c.isEnabled()) {
                this.f2752a.f2756c.setChecked(z);
            } else {
                z.c(view.getContext(), view.getContext().getString(R.string.dilink_system_permission_not_update));
            }
        }
    }

    public class c extends BydAdapter<i>.BaseViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ImageView f2754a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public TextView f2755b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public BydSwitch f2756c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public View f2757d;

        public c(View view) {
            super(view);
            a(view);
        }

        public final void a(View view) {
            this.f2754a = (ImageView) view.findViewById(R.id.iv_app_icon_item_sub_app_list);
            this.f2755b = (TextView) view.findViewById(R.id.tv_app_name_item_sub_app_list);
            this.f2756c = (BydSwitch) view.findViewById(R.id.switch_item_sub_app_list);
            this.f2757d = view.findViewById(R.id.v_line_app_list);
        }
    }

    public SubPermissionAppListAdapter(List<i> list) {
        super(list);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public BydAdapter<i>.BaseViewHolder initViewHolder(View view) {
        return new c(view);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public int loadLayoutId() {
        return R.layout.item_sub_app_list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        c cVar = (c) viewHolder;
        cVar.itemView.setClickable(false);
        i iVar = (i) this.data.get(i);
        cVar.f2754a.setImageDrawable(iVar.l());
        cVar.f2755b.setText(iVar.k());
        BydSwitch bydSwitch = cVar.f2756c;
        bydSwitch.setChecked(iVar.f(bydSwitch.getContext()), false);
        cVar.f2756c.setOnCheckedChangeListener(new a(i));
        if (iVar.m() && iVar.g()) {
            cVar.f2756c.setEnabled(false);
            cVar.f2756c.setAlpha(0.5f);
        } else {
            cVar.f2756c.setEnabled(true);
            cVar.f2756c.setAlpha(1.0f);
        }
        cVar.f2756c.setOnInterceptTouchListener(new b(cVar));
        if (i == this.data.size() - 1) {
            cVar.f2757d.setVisibility(8);
        } else {
            cVar.f2757d.setVisibility(0);
        }
    }
}
