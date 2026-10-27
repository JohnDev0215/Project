package com.byd.btsetting.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.bt.model.BTPowerLevel;
import com.byd.bt.model.BondDevice;
import com.byd.carsettings.R;
import com.byd.mvp.ui.adapter.BtnType;
import com.byd.mvp.ui.adapter.BydAdapter;

/* JADX INFO: loaded from: classes.dex */
public class BondBtListAdapter extends BydAdapter<BondDevice> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f2547a;

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ BondDevice f2548a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f2549b;

        public a(BondDevice bondDevice, int i) {
            this.f2548a = bondDevice;
            this.f2549b = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if ((!this.f2548a.isConnected() || this.f2548a.getLevel() == BTPowerLevel.UNDEFINED) && BondBtListAdapter.this.mCallback != null) {
                BondBtListAdapter.this.mCallback.onItemBtnClicked(this.f2549b, BtnType.BTN_0);
            }
        }
    }

    public static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f2551a;

        static {
            int[] iArr = new int[BTPowerLevel.values().length];
            f2551a = iArr;
            try {
                iArr[BTPowerLevel.LEVEL_0.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2551a[BTPowerLevel.LEVEL_1.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2551a[BTPowerLevel.LEVEL_2.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2551a[BTPowerLevel.LEVEL_3.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2551a[BTPowerLevel.LEVEL_4.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public class c extends BydAdapter<BondDevice>.BaseViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ImageView f2552a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public TextView f2553b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public TextView f2554c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public ImageView f2555d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ImageView f2556e;

        public c(View view) {
            super(view);
            this.f2552a = (ImageView) view.findViewById(R.id.iv_bt_type_item_bond_bt_list);
            this.f2553b = (TextView) view.findViewById(R.id.tv_bt_name_item_bond_bt_list);
            this.f2554c = (TextView) view.findViewById(R.id.tv_bt_connect_state_item_bond_bt_list);
            this.f2555d = (ImageView) view.findViewById(R.id.iv_delete_connected_item_bond_bt_list);
            this.f2556e = (ImageView) view.findViewById(R.id.iv_bg_item_bond_bt_list);
        }
    }

    public void checkItem(int i) {
        int i2 = 0;
        while (i2 < this.data.size()) {
            ((BondDevice) this.data.get(i2)).setChecked(i2 == i);
            i2++;
        }
        notifyDataSetChanged();
    }

    public final int d(BTPowerLevel bTPowerLevel) {
        if (bTPowerLevel == null) {
            return R.drawable.icon_public_connected_def;
        }
        int i = b.f2551a[bTPowerLevel.ordinal()];
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? R.drawable.icon_public_connected_def : R.drawable.icon_public_phone_battery_10_48 : R.drawable.icon_public_phone_battery_30_48 : R.drawable.icon_public_phone_battery_70_48 : R.drawable.icon_public_phone_battery_90_48 : R.drawable.icon_public_phone_battery_100_48;
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter, b.b.m.f.c.a.a
    public void drag(int i, int i2) {
        b.b.m.f.c.a.b bVar = this.mDragItemCallback;
        if (bVar != null) {
            bVar.a(i, i2);
        }
        notifyItemRemoved(i);
    }

    public final void e(TextView textView, BondDevice bondDevice) {
        if (!bondDevice.isChecked() || bondDevice.getConnectState() == 0) {
            textView.setVisibility(8);
            return;
        }
        textView.setVisibility(0);
        Context context = textView.getContext();
        int connectState = bondDevice.getConnectState();
        if (connectState == 1) {
            textView.setText(context.getString(R.string.bt_state_connecting));
        } else {
            if (connectState != 2) {
                return;
            }
            textView.setText(context.getString(R.string.bt_state_connected));
        }
    }

    public final void f(ImageView imageView, BondDevice bondDevice) {
        imageView.setImageResource(bondDevice.isConnected() ? bondDevice.isHfpConnected() ? d(bondDevice.getLevel()) : R.drawable.icon_public_connected_def : R.drawable.icon_public_trash_48);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public BydAdapter<BondDevice>.BaseViewHolder initViewHolder(View view) {
        return new c(view);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public int loadLayoutId() {
        return R.layout.item_bond_bt_list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (i >= getItemCount()) {
            return;
        }
        c cVar = (c) viewHolder;
        cVar.id = i;
        BondDevice bondDevice = (BondDevice) this.data.get(i);
        cVar.f2553b.setText(bondDevice.getName());
        Context context = cVar.f2553b.getContext();
        int color = context.getColor(R.color.text_color_main);
        int color2 = context.getColor(R.color.text_color_btn_blue);
        boolean zIsChecked = bondDevice.isChecked();
        cVar.f2553b.setTextColor(zIsChecked ? color2 : color);
        TextView textView = cVar.f2554c;
        if (zIsChecked) {
            color = color2;
        }
        textView.setTextColor(color);
        e(cVar.f2554c, bondDevice);
        int iA = b.b.e.a.a(bondDevice.getType(), bondDevice.getPhoneType(), bondDevice.isCpConnected());
        if (iA != 0) {
            cVar.f2552a.setImageResource(iA);
        }
        f(cVar.f2555d, bondDevice);
        cVar.f2556e.setVisibility(zIsChecked ? 0 : 8);
        cVar.f2555d.setOnClickListener(new a(bondDevice, i));
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter, android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.f2547a) {
            super.onClick(view);
            checkItem(((BydAdapter.BaseViewHolder) view.getTag()).getId());
        }
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public void removeItemByPosition(int i) {
        b.b.m.f.c.a.b bVar = this.mDragItemCallback;
        if (bVar != null) {
            bVar.d(i);
        }
        notifyItemRemoved(i);
    }
}
