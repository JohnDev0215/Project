package com.byd.systemsettings.btsetting.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import c4.a;
import com.byd.bt.model.BTPowerLevel;
import com.byd.bt.model.BondDevice;
import com.byd.mvp.ui.adapter.BtnType;
import com.byd.systemsettings.R;
import com.byd.systemsettings.btsetting.BtImgUtil;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class BondBtListAdapter extends a<BondDevice> {
    private boolean checkEnable;

    /* JADX INFO: renamed from: com.byd.systemsettings.btsetting.adapter.BondBtListAdapter$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        public static final /* synthetic */ int[] $SwitchMap$com$byd$bt$model$BTPowerLevel;

        static {
            int[] iArr = new int[BTPowerLevel.values().length];
            $SwitchMap$com$byd$bt$model$BTPowerLevel = iArr;
            try {
                iArr[BTPowerLevel.LEVEL_0.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$byd$bt$model$BTPowerLevel[BTPowerLevel.LEVEL_1.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$byd$bt$model$BTPowerLevel[BTPowerLevel.LEVEL_2.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$byd$bt$model$BTPowerLevel[BTPowerLevel.LEVEL_3.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$byd$bt$model$BTPowerLevel[BTPowerLevel.LEVEL_4.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public class MyViewHolder extends a<BondDevice>.C0013a {
        public ImageView ivBg;
        public ImageView ivDelete;
        public ImageView ivType;
        public TextView tvConnectState;
        public TextView tvName;

        public MyViewHolder(View view) {
            super(view);
            this.ivType = (ImageView) view.findViewById(R.id.iv_bt_type_item_bond_bt_list);
            this.tvName = (TextView) view.findViewById(R.id.tv_bt_name_item_bond_bt_list);
            this.tvConnectState = (TextView) view.findViewById(R.id.tv_bt_connect_state_item_bond_bt_list);
            this.ivDelete = (ImageView) view.findViewById(R.id.iv_delete_connected_item_bond_bt_list);
            this.ivBg = (ImageView) view.findViewById(R.id.iv_bg_item_bond_bt_list);
        }
    }

    public BondBtListAdapter(List<BondDevice> list) {
        super(list);
        this.checkEnable = true;
    }

    private int getBtImgId(BTPowerLevel bTPowerLevel) {
        if (bTPowerLevel == null) {
            return R.drawable.icon_public_connected_def;
        }
        int i8 = AnonymousClass2.$SwitchMap$com$byd$bt$model$BTPowerLevel[bTPowerLevel.ordinal()];
        return i8 != 1 ? i8 != 2 ? i8 != 3 ? i8 != 4 ? i8 != 5 ? R.drawable.icon_public_connected_def : R.drawable.icon_public_phone_battery_10_48 : R.drawable.icon_public_phone_battery_30_48 : R.drawable.icon_public_phone_battery_70_48 : R.drawable.icon_public_phone_battery_90_48 : R.drawable.icon_public_phone_battery_100_48;
    }

    private void setConnectStateText(TextView textView, BondDevice bondDevice) {
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

    private void setImgByLevel(ImageView imageView, BondDevice bondDevice) {
        imageView.setImageResource(bondDevice.isConnected() ? bondDevice.isHfpConnected() ? getBtImgId(bondDevice.getLevel()) : R.drawable.icon_public_connected_def : R.drawable.icon_public_trash_48);
    }

    public void checkItem(int i8) {
        int i9 = 0;
        while (i9 < this.data.size()) {
            ((BondDevice) this.data.get(i9)).setChecked(i9 == i8);
            i9++;
        }
        notifyDataSetChanged();
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
    public a<BondDevice>.C0013a initViewHolder(View view) {
        return new MyViewHolder(view);
    }

    public boolean isCheckEnable() {
        return this.checkEnable;
    }

    @Override // c4.a
    public int loadLayoutId() {
        return R.layout.item_bond_bt_list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public void onBindViewHolder(RecyclerView.c0 c0Var, final int i8) {
        if (i8 >= getItemCount()) {
            return;
        }
        MyViewHolder myViewHolder = (MyViewHolder) c0Var;
        myViewHolder.id = i8;
        final BondDevice bondDevice = (BondDevice) this.data.get(i8);
        myViewHolder.tvName.setText(bondDevice.getName());
        Context context = myViewHolder.tvName.getContext();
        int color = context.getColor(R.color.text_color_main);
        int color2 = context.getColor(R.color.text_color_btn_blue);
        boolean zIsChecked = bondDevice.isChecked();
        myViewHolder.tvName.setTextColor(zIsChecked ? color2 : color);
        TextView textView = myViewHolder.tvConnectState;
        if (zIsChecked) {
            color = color2;
        }
        textView.setTextColor(color);
        setConnectStateText(myViewHolder.tvConnectState, bondDevice);
        int btTypeImgId = BtImgUtil.getBtTypeImgId(bondDevice.getType(), bondDevice.getPhoneType(), bondDevice.isCpConnected());
        if (btTypeImgId != 0) {
            myViewHolder.ivType.setImageResource(btTypeImgId);
        }
        setImgByLevel(myViewHolder.ivDelete, bondDevice);
        myViewHolder.ivBg.setVisibility(zIsChecked ? 0 : 8);
        myViewHolder.ivDelete.setOnClickListener(new View.OnClickListener() { // from class: com.byd.systemsettings.btsetting.adapter.BondBtListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if ((!bondDevice.isConnected() || bondDevice.getLevel() == BTPowerLevel.UNDEFINED) && BondBtListAdapter.this.mCallback != null) {
                    BondBtListAdapter.this.mCallback.onItemBtnClicked(i8, BtnType.BTN_0);
                }
            }
        });
    }

    @Override // c4.a, android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.checkEnable) {
            super.onClick(view);
            checkItem(((a.C0013a) view.getTag()).getId());
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

    public void setCheckEnable(boolean z7) {
        this.checkEnable = z7;
    }
}
