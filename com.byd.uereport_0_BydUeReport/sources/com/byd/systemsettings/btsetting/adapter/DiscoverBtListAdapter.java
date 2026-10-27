package com.byd.systemsettings.btsetting.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import c4.a;
import com.byd.bt.model.BTDevice;
import com.byd.bt.model.PhoneType;
import com.byd.systemsettings.R;
import com.byd.systemsettings.btsetting.BtImgUtil;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DiscoverBtListAdapter extends a<BTDevice> {

    public class MyViewHolder extends a<BTDevice>.C0013a {
        public ImageView ivType;
        public TextView tvName;

        public MyViewHolder(View view) {
            super(view);
            this.ivType = (ImageView) view.findViewById(R.id.iv_bt_type_item_discover_bt_list);
            this.tvName = (TextView) view.findViewById(R.id.tv_bt_name_item_discover_bt_list);
        }
    }

    public DiscoverBtListAdapter(List<BTDevice> list) {
        super(list);
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
    public a<BTDevice>.C0013a initViewHolder(View view) {
        return new MyViewHolder(view);
    }

    @Override // c4.a
    public int loadLayoutId() {
        return R.layout.item_discover_bt_list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public void onBindViewHolder(RecyclerView.c0 c0Var, int i8) {
        if (i8 >= getItemCount()) {
            return;
        }
        MyViewHolder myViewHolder = (MyViewHolder) c0Var;
        myViewHolder.id = i8;
        BTDevice bTDevice = (BTDevice) this.data.get(i8);
        myViewHolder.tvName.setText(bTDevice.getName());
        int btTypeImgId = BtImgUtil.getBtTypeImgId(bTDevice.getType(), PhoneType.NORMAL, false);
        if (btTypeImgId != 0) {
            myViewHolder.ivType.setImageResource(btTypeImgId);
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
