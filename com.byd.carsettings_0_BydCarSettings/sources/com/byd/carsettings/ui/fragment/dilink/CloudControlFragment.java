package com.byd.carsettings.ui.fragment.dilink;

import android.content.Context;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import androidx.fragment.app.FragmentActivity;
import b.b.g.n.k.b;
import b.b.m.c.a;
import b.b.m.f.d.d;
import com.byd.base.view.BydSwitch;
import com.byd.carsettings.R;
import com.byd.mvp.mvp.XFragment;

/* JADX INFO: loaded from: classes2.dex */
public class CloudControlFragment extends XFragment<b> implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    public BydSwitch m;
    public BydSwitch n;
    public ImageView o;

    @Override // com.byd.mvp.mvp.XFragment
    public void A(Context context) {
        this.m = (BydSwitch) this.f3717c.findViewById(R.id.switch_clairvoyant_cloud_control);
        this.n = (BydSwitch) this.f3717c.findViewById(R.id.switch_remote_location_cloud_control);
        ImageView imageView = (ImageView) this.f3717c.findViewById(R.id.iv_details_cloud_control_pager);
        this.o = imageView;
        imageView.setOnClickListener(this);
        this.m.setOnCheckedChangeListener(this);
        this.n.setOnCheckedChangeListener(this);
    }

    public final void K() {
        this.m.setOnCheckedChangeListener(null);
        this.n.setOnCheckedChangeListener(null);
        this.o.setOnClickListener(null);
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: L, reason: merged with bridge method [inline-methods] */
    public b newP() {
        return new b();
    }

    public final void M() {
        a.a(this.f3715a, "----onDetailsOperate---");
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        b.b.g.q.d.u.b.n(activity, activity.getString(R.string.remote_location_cloud_control), activity.getString(R.string.dilink_remote_location_details));
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.pager_cloud_control;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        a.a(this.f3715a, "----onCheckedChanged---b = " + z);
        int id = compoundButton.getId();
        if (id == R.id.switch_clairvoyant_cloud_control) {
            ((b) mo7getP()).c(z);
        } else {
            if (id != R.id.switch_remote_location_cloud_control) {
                return;
            }
            ((b) mo7getP()).d(z);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (!this.f3719e && view.getId() == R.id.iv_details_cloud_control_pager) {
            M();
        }
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        d.c(getClass().getName());
        K();
        super.onDestroyView();
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void y(Context context) {
    }
}
