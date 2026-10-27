package com.byd.carsettings.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b.b.g.n.m.c;
import b.b.m.f.d.d;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.CommonSettingsAdapter;
import com.byd.carsettings.model.CommonItemId;
import com.byd.carsettings.model.CommonSettingItem;
import com.byd.mvp.mvp.XFragment;
import com.byd.mvp.ui.adapter.callback.RecycleCallback;
import com.byd.mvp.ui.widget.RecyclerSpace;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CommonFragment extends XFragment<c> {
    public volatile int m = 0;
    public RecyclerView n;
    public CommonSettingsAdapter o;
    public List<CommonSettingItem> p;
    public SharedPreferences q;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CommonFragment.this.P();
        }
    }

    public class b implements b.b.m.f.c.a.b {
        public b() {
        }

        @Override // b.b.m.f.c.a.b
        public void b(RecyclerView.ViewHolder viewHolder) {
            b.b.m.c.a.a(CommonFragment.this.f3715a, "---onDragCompleted----");
            CommonFragment.this.Q();
            if (viewHolder != null) {
                viewHolder.itemView.setScaleX(1.0f);
                viewHolder.itemView.setScaleY(1.0f);
            }
        }

        @Override // b.b.m.f.c.a.b
        public void c(RecyclerView.ViewHolder viewHolder) {
            if (viewHolder != null) {
                viewHolder.itemView.setScaleX(1.0796f);
                viewHolder.itemView.setScaleY(1.0833f);
            }
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void A(Context context) {
        b.b.m.c.a.a(this.f3715a, "----initViews-----context = " + context);
        this.n = (RecyclerView) this.f3717c.findViewById(R.id.rcv_common_fragment);
        View viewFindViewById = this.f3717c.findViewById(R.id.ll_start_user_manual);
        viewFindViewById.setOnClickListener(new a());
        viewFindViewById.setVisibility(8);
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void F(Bundle bundle) {
        bundle.putInt("dialog_count_common", this.m);
    }

    public final void M() {
        b.b.m.c.a.a(this.f3715a, "-----getAndSetOrderId------");
        SharedPreferences.Editor editorEdit = this.q.edit();
        int size = this.p.size();
        for (int i = 0; i < size; i++) {
            CommonSettingItem commonSettingItem = this.p.get(i);
            int value = commonSettingItem.getOriginalId().getValue();
            int i2 = this.q.getInt("common_item" + value, -1);
            if (i2 != -1) {
                commonSettingItem.setOrderId(i2);
            } else {
                commonSettingItem.setOrderId(value);
                editorEdit.putInt("common_item" + value, value);
            }
        }
        editorEdit.commit();
        Collections.sort(this.p);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void N() {
        if (this.p.isEmpty()) {
            Resources resources = getContext().getResources();
            String[] stringArray = resources.getStringArray(R.array.common_setting_card_title_array);
            int iC = ((c) mo7getP()).c();
            if (iC > 1) {
                CommonSettingItem commonSettingItem = new CommonSettingItem(CommonItemId.ATMOSPHERE_LAMP_COLOR);
                commonSettingItem.setViewType(1);
                commonSettingItem.setTitle(stringArray[0]);
                commonSettingItem.setMax(iC);
                commonSettingItem.setMin(1);
                this.p.add(commonSettingItem);
            }
            if (((c) mo7getP()).e()) {
                CommonSettingItem commonSettingItem2 = new CommonSettingItem(CommonItemId.ATMOSPHERE_LAMP_BRIGHTNESS);
                commonSettingItem2.setViewType(2);
                commonSettingItem2.setTitle(stringArray[1]);
                commonSettingItem2.setMax(5);
                commonSettingItem2.setMin(0);
                this.p.add(commonSettingItem2);
            }
            String[] stringArray2 = resources.getStringArray(R.array.soc_setting_array);
            if (((c) mo7getP()).l()) {
                CommonSettingItem commonSettingItem3 = new CommonSettingItem(CommonItemId.SOC_SETTINGS);
                commonSettingItem3.setViewType(5);
                commonSettingItem3.setTitle(stringArray[2]);
                commonSettingItem3.setRbtnTextArr(stringArray2);
                commonSettingItem3.setMax(70);
                commonSettingItem3.setMin(25);
                this.p.add(commonSettingItem3);
            }
            CommonSettingItem commonSettingItem4 = new CommonSettingItem(CommonItemId.ENERGY_FEEDBACK);
            commonSettingItem4.setViewType(3);
            commonSettingItem4.setTitle(stringArray[3]);
            commonSettingItem4.setRbtnTextArr(resources.getStringArray(R.array.energy_feedback_array));
            this.p.add(commonSettingItem4);
            if (((c) mo7getP()).j()) {
                CommonSettingItem commonSettingItem5 = new CommonSettingItem(CommonItemId.REGULATE_INSTRUMENT_BRIGHTNESS);
                commonSettingItem5.setViewType(0);
                commonSettingItem5.setTitle(stringArray[4]);
                commonSettingItem5.setMax(11);
                commonSettingItem5.setMin(1);
                this.p.add(commonSettingItem5);
            }
            if (((c) mo7getP()).m()) {
                CommonSettingItem commonSettingItem6 = new CommonSettingItem(CommonItemId.POWER_STEERING_MODE);
                commonSettingItem6.setViewType(3);
                commonSettingItem6.setTitle(stringArray[5]);
                commonSettingItem6.setRbtnTextArr(resources.getStringArray(R.array.power_steering_mode_array));
                this.p.add(commonSettingItem6);
            }
            if (((c) mo7getP()).g()) {
                CommonSettingItem commonSettingItem7 = new CommonSettingItem(CommonItemId.SIMULATION_ENGINE_SOUND);
                commonSettingItem7.setViewType(3);
                commonSettingItem7.setTitle(stringArray[6]);
                commonSettingItem7.setRbtnTextArr(resources.getStringArray(R.array.simulation_sound_of_engine_array));
                this.p.add(commonSettingItem7);
            }
            if (((c) mo7getP()).f()) {
                CommonSettingItem commonSettingItem8 = new CommonSettingItem(CommonItemId.ACTIVE_SUSPENSION);
                commonSettingItem8.setViewType(3);
                commonSettingItem8.setTitle(stringArray[7]);
                commonSettingItem8.setRbtnTextArr(resources.getStringArray(R.array.active_suspension_array));
                this.p.add(commonSettingItem8);
            }
            if (((c) mo7getP()).d()) {
                CommonSettingItem commonSettingItem9 = new CommonSettingItem(CommonItemId.ATMOSPHERE_LAMP_AREA);
                commonSettingItem9.setViewType(8);
                this.p.add(commonSettingItem9);
            }
            if (((c) mo7getP()).i() || ((c) mo7getP()).h()) {
                CommonSettingItem commonSettingItem10 = new CommonSettingItem(CommonItemId.ATMOSPHERE_LAMP_MUSIC_RHYTHM);
                commonSettingItem10.setViewType(7);
                commonSettingItem10.setTitle(stringArray[9]);
                this.p.add(commonSettingItem10);
            }
            CommonSettingItem commonSettingItem11 = new CommonSettingItem(CommonItemId.REGULATE_MEDIA_BRIGHTNESS);
            commonSettingItem11.setViewType(0);
            commonSettingItem11.setTitle(stringArray[10]);
            commonSettingItem11.setMax(255);
            commonSettingItem11.setMin(0);
            this.p.add(commonSettingItem11);
            CommonSettingItem commonSettingItem12 = new CommonSettingItem(CommonItemId.DARK_LIGHT_MODE);
            commonSettingItem12.setViewType(4);
            commonSettingItem12.setTitle(stringArray[11]);
            commonSettingItem12.setRbtnTextArr(resources.getStringArray(R.array.dark_light_mode_array));
            this.p.add(commonSettingItem12);
        }
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: O, reason: merged with bridge method [inline-methods] */
    public c newP() {
        return new c(getContext());
    }

    public final void P() {
        b.b.m.c.a.a(this.f3715a, "----startUserManual-----");
    }

    public final void Q() {
        b.b.m.c.a.a(this.f3715a, "---updateOrderId----");
        SharedPreferences.Editor editorEdit = this.q.edit();
        int size = this.p.size();
        for (int i = 0; i < size; i++) {
            CommonSettingItem commonSettingItem = this.p.get(i);
            commonSettingItem.setOrderId(i);
            editorEdit.putInt("common_item" + commonSettingItem.getOriginalId().getValue(), commonSettingItem.getOrderId());
        }
        editorEdit.commit();
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.fragment_common;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        ((c) mo7getP()).n();
        d.c(getClass().getName());
        List<CommonSettingItem> list = this.p;
        if (list != null) {
            list.clear();
        }
        CommonSettingsAdapter commonSettingsAdapter = this.o;
        if (commonSettingsAdapter != null) {
            commonSettingsAdapter.e();
            this.o = null;
        }
        super.onDestroyView();
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void v() {
        super.v();
        b.b.m.e.i.b.d(getClass().getName());
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void x(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.m = bundle.getInt("dialog_count_common", 0);
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void y(Context context) {
        b.b.m.c.a.a(this.f3715a, "----initData-----context = " + context);
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void z() {
        super.z();
        this.q = getContext().getSharedPreferences("common_item_order", 0);
        if (this.p == null) {
            this.p = new ArrayList();
        }
        N();
        M();
        if (this.o == null) {
            this.o = new CommonSettingsAdapter(getActivity(), this.p, this);
        }
        this.n.setAdapter(this.o);
        if (getResources().getConfiguration().orientation == 2) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
            gridLayoutManager.setOrientation(1);
            this.n.setLayoutManager(gridLayoutManager);
        } else {
            this.n.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        RecycleCallback recycleCallback = new RecycleCallback(this.o);
        recycleCallback.a(true);
        if (this.n.getItemDecorationCount() <= 0) {
            int dimension = (int) getContext().getResources().getDimension(R.dimen.dp_8);
            this.n.addItemDecoration(new RecyclerSpace(dimension, dimension, dimension, dimension));
            new ItemTouchHelper(recycleCallback).attachToRecyclerView(this.n);
        }
        this.n.setItemAnimator(new DefaultItemAnimator());
        this.o.setOnDragItemCallback(new b());
    }
}
