package com.byd.systemsettings.cpaa.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import c4.a;
import com.byd.mvp.ui.adapter.BtnType;
import com.byd.systemsettings.R;
import com.byd.systemsettings.cpaa.model.CPAADevice;
import com.byd.systemsettings.cpaa.model.CarPlayType;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CarPlayListAdapter extends a<CPAADevice> {
    public static final int WIRELESS_AVAILABLE_STATE_AVAILABLE = 1;

    public class MyViewHolder extends a<CPAADevice>.C0013a {
        public View cpLine;
        public ImageView ivBt;
        public ImageView ivDelete;
        public ImageView ivDisconnect;
        public ImageView ivPhoe;
        public ImageView ivType;
        public ImageView ivUsb;
        public ImageView ivWifi;
        public TextView tvName;
        public View vbg;

        public MyViewHolder(View view) {
            super(view);
            this.ivType = (ImageView) view.findViewById(R.id.iv_device_type_item_car_play_list);
            this.tvName = (TextView) view.findViewById(R.id.tv_device_name_item_car_play_list);
            this.ivUsb = (ImageView) view.findViewById(R.id.iv_usb_state_item_car_play_list);
            this.ivPhoe = (ImageView) view.findViewById(R.id.iv_phone_state_item_car_play_list);
            this.ivWifi = (ImageView) view.findViewById(R.id.iv_wifi_state_item_car_play_list);
            this.ivBt = (ImageView) view.findViewById(R.id.iv_bt_state_item_car_play_list);
            this.ivDelete = (ImageView) view.findViewById(R.id.iv_delete_item_car_play_list);
            this.ivDisconnect = (ImageView) view.findViewById(R.id.iv_disconnect_item_car_play_list);
            this.vbg = view.findViewById(R.id.v_item_bg_checked_item_car_play_list);
            this.cpLine = view.findViewById(R.id.cp_line);
        }
    }

    public CarPlayListAdapter(List<CPAADevice> list) {
        super(list);
    }

    private int getDeviceTypeImgId(CPAADevice cPAADevice) {
        if (CarPlayType.CAR_PLAY.equals(cPAADevice.getType())) {
            return (cPAADevice.deviceStatus == 4 || cPAADevice.mWirelessAvailableState == 1) ? R.drawable.icon_light_public_carplay : R.drawable.icon_light_public_carplay_unenable;
        }
        int i8 = cPAADevice.deviceStatus;
        return (i8 == 4 || i8 == 1) ? R.drawable.icon_light_public_android_auto : R.drawable.icon_light_public_android_auto_unenable;
    }

    private void initImageView(ImageView imageView, final BtnType btnType, final int i8) {
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.byd.systemsettings.cpaa.adapter.CarPlayListAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CarPlayListAdapter.this.mCallback != null) {
                    CarPlayListAdapter.this.mCallback.onItemBtnClicked(i8, btnType);
                }
            }
        });
    }

    private boolean textEnable(CPAADevice cPAADevice) {
        if (!CarPlayType.CAR_PLAY.equals(cPAADevice.getType())) {
            int i8 = cPAADevice.deviceStatus;
            if (i8 != 4 && i8 != 1) {
                return false;
            }
        } else if (cPAADevice.deviceStatus != 4 && cPAADevice.mWirelessAvailableState != 1) {
            return false;
        }
        return true;
    }

    @Override // c4.a
    public void drag(int i8, int i9) {
        d4.a aVar = this.mDragItemCallback;
        if (aVar != null) {
            aVar.a(i8, i9);
        }
        notifyItemRemoved(i8);
    }

    @Override // c4.a
    public a<CPAADevice>.C0013a initViewHolder(View view) {
        return new MyViewHolder(view);
    }

    @Override // c4.a
    public int loadLayoutId() {
        return R.layout.item_car_play_list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public void onBindViewHolder(RecyclerView.c0 c0Var, final int i8) {
        MyViewHolder myViewHolder = (MyViewHolder) c0Var;
        myViewHolder.id = i8;
        CPAADevice cPAADevice = (CPAADevice) this.data.get(i8);
        myViewHolder.tvName.setText(cPAADevice.getName());
        myViewHolder.tvName.setEnabled(textEnable(cPAADevice));
        x3.a.a("CarPlayListAdapter", "device.getName:" + cPAADevice.getName() + ",device.deviceStatus:" + cPAADevice.deviceStatus);
        myViewHolder.ivType.setImageResource(getDeviceTypeImgId(cPAADevice));
        if (cPAADevice.connectType == 1 && cPAADevice.isConnected()) {
            myViewHolder.ivUsb.setVisibility(0);
        } else {
            myViewHolder.ivUsb.setVisibility(8);
        }
        myViewHolder.ivDisconnect.setOnClickListener(new View.OnClickListener() { // from class: com.byd.systemsettings.cpaa.adapter.CarPlayListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CarPlayListAdapter.this.mCallback != null) {
                    CarPlayListAdapter.this.mCallback.onItemBtnClicked(i8, BtnType.BTN_3);
                }
            }
        });
        myViewHolder.ivDelete.setOnClickListener(new View.OnClickListener() { // from class: com.byd.systemsettings.cpaa.adapter.CarPlayListAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CarPlayListAdapter.this.mCallback != null) {
                    CarPlayListAdapter.this.mCallback.onItemBtnClicked(i8, BtnType.BTN_4);
                }
            }
        });
        if (cPAADevice.isConnected()) {
            myViewHolder.vbg.setVisibility(0);
            myViewHolder.ivDisconnect.setVisibility(0);
            myViewHolder.cpLine.setVisibility(4);
        } else {
            myViewHolder.vbg.setVisibility(4);
            myViewHolder.ivDisconnect.setVisibility(4);
            if (i8 == getItemCount() - 1) {
                myViewHolder.cpLine.setVisibility(4);
            } else {
                myViewHolder.cpLine.setVisibility(0);
            }
        }
        if (cPAADevice.isHasPhoneSignal()) {
            myViewHolder.ivPhoe.setVisibility(0);
            myViewHolder.ivBt.setVisibility(0);
        } else {
            myViewHolder.ivPhoe.setVisibility(8);
            myViewHolder.ivBt.setVisibility(4);
        }
        if (!cPAADevice.isConnected() || cPAADevice.connectType != 2) {
            myViewHolder.ivWifi.setVisibility(4);
        } else if (CarPlayType.CAR_PLAY.equals(cPAADevice.getType())) {
            myViewHolder.ivWifi.setVisibility(4);
        } else {
            myViewHolder.ivWifi.setVisibility(0);
        }
    }

    @Override // c4.a
    public void removeItemByPosition(int i8) {
        d4.a aVar = this.mDragItemCallback;
        if (aVar != null) {
            aVar.d(i8);
        }
        notifyItemRemoved(i8);
    }
}
