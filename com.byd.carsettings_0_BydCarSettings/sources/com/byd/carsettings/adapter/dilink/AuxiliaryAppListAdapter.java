package com.byd.carsettings.adapter.dilink;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ServiceInfo;
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
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class AuxiliaryAppListAdapter extends BydAdapter<AccessibilityServiceInfo> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f2687a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Set<ComponentName> f2688b;

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f2689a;

        public a(int i) {
            this.f2689a = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (AuxiliaryAppListAdapter.this.mCallback != null) {
                AuxiliaryAppListAdapter.this.mCallback.onItemBtnClicked(this.f2689a, BtnType.BTN_0);
            }
        }
    }

    public class b implements CompoundButton.OnCheckedChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f2691a;

        public b(int i) {
            this.f2691a = i;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (AuxiliaryAppListAdapter.this.mBtnCheckedCallback != null) {
                AuxiliaryAppListAdapter.this.mBtnCheckedCallback.a(this.f2691a, z, BtnType.BTN_0);
            }
        }
    }

    public class c extends BydAdapter<AccessibilityServiceInfo>.BaseViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public BydSwitch f2693a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public TextView f2694b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ImageView f2695c;

        public c(View view) {
            super(view);
            a(view);
        }

        public final void a(View view) {
            this.f2693a = (BydSwitch) view.findViewById(R.id.switch_item_auxiliary_app_list);
            this.f2694b = (TextView) view.findViewById(R.id.tv_app_name_item_auxiliary_app_list);
            this.f2695c = (ImageView) view.findViewById(R.id.iv_detail_item_auxiliary_app_list);
        }
    }

    public AuxiliaryAppListAdapter(Context context, List<AccessibilityServiceInfo> list) {
        super(list);
        this.f2687a = context;
        this.f2688b = b.b.g.p.i.a.a(context);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public BydAdapter<AccessibilityServiceInfo>.BaseViewHolder initViewHolder(View view) {
        return new c(view);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public int loadLayoutId() {
        return R.layout.item_auxiliary_app_list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        c cVar = (c) viewHolder;
        AccessibilityServiceInfo accessibilityServiceInfo = (AccessibilityServiceInfo) this.data.get(i);
        ServiceInfo serviceInfo = accessibilityServiceInfo.getResolveInfo().serviceInfo;
        ComponentName componentName = new ComponentName(serviceInfo.packageName, serviceInfo.name);
        String string = accessibilityServiceInfo.getResolveInfo().loadLabel(this.f2687a.getPackageManager()).toString();
        accessibilityServiceInfo.loadDescription(this.f2687a.getPackageManager());
        cVar.f2694b.setText(string.trim());
        cVar.f2695c.setOnClickListener(new a(i));
        cVar.f2693a.setChecked(this.f2688b.contains(componentName), false);
        cVar.f2693a.setOnCheckedChangeListener(new b(i));
    }
}
