package com.byd.carplay.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.carplay.model.CPAADevice;
import com.byd.carplay.model.CarPlayType;
import com.byd.carsettings.R;
import com.byd.mvp.ui.adapter.BtnType;
import com.byd.mvp.ui.adapter.BydAdapter;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CarPlayListAdapter extends BydAdapter<CPAADevice> {

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f2567a;

        public a(int i) {
            this.f2567a = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (CarPlayListAdapter.this.mCallback != null) {
                CarPlayListAdapter.this.mCallback.onItemBtnClicked(this.f2567a, BtnType.BTN_4);
            }
        }
    }

    public class b extends BydAdapter<CPAADevice>.BaseViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ImageView f2569a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public TextView f2570b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ImageView f2571c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public ImageView f2572d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ImageView f2573e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public ImageView f2574f;
        public ImageView g;
        public View h;

        public b(View view) {
            super(view);
            this.f2569a = (ImageView) view.findViewById(R.id.iv_device_type_item_car_play_list);
            this.f2570b = (TextView) view.findViewById(R.id.tv_device_name_item_car_play_list);
            this.f2571c = (ImageView) view.findViewById(R.id.iv_usb_state_item_car_play_list);
            this.f2572d = (ImageView) view.findViewById(R.id.iv_phone_state_item_car_play_list);
            this.f2573e = (ImageView) view.findViewById(R.id.iv_wifi_state_item_car_play_list);
            this.f2574f = (ImageView) view.findViewById(R.id.iv_bt_state_item_car_play_list);
            this.g = (ImageView) view.findViewById(R.id.iv_delete_item_car_play_list);
            this.h = view.findViewById(R.id.v_item_bg_checked_item_car_play_list);
        }
    }

    public CarPlayListAdapter(List<CPAADevice> list) {
        super(list);
    }

    public final int d(CPAADevice cPAADevice) {
        if (CarPlayType.CAR_PLAY.equals(cPAADevice.getType())) {
            return (cPAADevice.deviceStatus == 4 || cPAADevice.mWirelessAvailableState == 1) ? R.drawable.icon_light_public_carplay : R.drawable.icon_light_public_carplay_unenable;
        }
        int i = cPAADevice.deviceStatus;
        return (i == 4 || i == 1) ? R.drawable.icon_light_public_android_auto : R.drawable.icon_light_public_android_auto_unenable;
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter, b.b.m.f.c.a.a
    public void drag(int i, int i2) {
        b.b.m.f.c.a.b bVar = this.mDragItemCallback;
        if (bVar != null) {
            bVar.a(i, i2);
        }
        notifyItemRemoved(i);
    }

    public final boolean e(CPAADevice cPAADevice) {
        if (!CarPlayType.CAR_PLAY.equals(cPAADevice.getType())) {
            int i = cPAADevice.deviceStatus;
            if (i != 4 && i != 1) {
                return false;
            }
        } else if (cPAADevice.deviceStatus != 4 && cPAADevice.mWirelessAvailableState != 1) {
            return false;
        }
        return true;
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public BydAdapter<CPAADevice>.BaseViewHolder initViewHolder(View view) {
        return new b(view);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public int loadLayoutId() {
        return R.layout.item_car_play_list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        b bVar = (b) viewHolder;
        bVar.id = i;
        CPAADevice cPAADevice = (CPAADevice) this.data.get(i);
        bVar.f2570b.setText(cPAADevice.getName());
        bVar.f2570b.setEnabled(e(cPAADevice));
        b.b.m.c.a.a("CarPlayListAdapter", "device.getName:" + cPAADevice.getName() + ",device.deviceStatus:" + cPAADevice.deviceStatus);
        bVar.f2569a.setImageResource(d(cPAADevice));
        if (cPAADevice.connectType != 1 || CarPlayType.CAR_PLAY.equals(cPAADevice.getType())) {
            bVar.f2571c.setVisibility(8);
        } else {
            bVar.f2571c.setVisibility(0);
        }
        bVar.g.setOnClickListener(new a(i));
        if (cPAADevice.isConnected()) {
            bVar.h.setVisibility(0);
        } else {
            bVar.h.setVisibility(4);
        }
        if (cPAADevice.isHasPhoneSignal()) {
            bVar.f2572d.setVisibility(0);
        } else {
            bVar.f2572d.setVisibility(8);
        }
        if (cPAADevice.isHasBtSignal()) {
            bVar.f2574f.setVisibility(0);
        } else {
            bVar.f2574f.setVisibility(4);
        }
        if (!cPAADevice.isHasWiFiSignal() && (!cPAADevice.isConnected() || cPAADevice.connectType != 2)) {
            bVar.f2573e.setVisibility(4);
        } else if (CarPlayType.CAR_PLAY.equals(cPAADevice.getType())) {
            bVar.f2573e.setVisibility(4);
        } else {
            bVar.f2573e.setVisibility(0);
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
