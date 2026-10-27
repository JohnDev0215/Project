package com.android.systemui.statusbar;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.R;
import com.android.systemui.base.BaseDialog3;
import com.android.systemui.util.AppUtil;
import com.byd.card.utils.AppUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class StatusListDialog extends BaseDialog3 {
    private static final int WHAT_CLOSE = 1;
    private StatusAdapter mAdapter;
    private int mEnd;
    private Handler mHandler;
    private List<StatusBean> mList;
    private RecyclerView mRecyclerView;

    @Override // com.android.systemui.base.BaseDialog3
    protected int getLayoutId() {
        return R.layout.dialog_status_list;
    }

    @Override // com.android.systemui.base.BaseDialog3
    protected void initData() {
    }

    public StatusListDialog(Context context, List<StatusBean> list) {
        super(context);
        this.mHandler = new Handler(Looper.getMainLooper()) { // from class: com.android.systemui.statusbar.StatusListDialog.2
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {
                    StatusListDialog.this.dismiss();
                }
            }
        };
        this.mList = list;
        setCanceledOnTouchOutside(true);
        initView();
    }

    private void initView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.mRecyclerView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        PaddingItemDecoration paddingItemDecoration = new PaddingItemDecoration(getContext(), R.dimen.dp_54, R.dimen.dp_26);
        paddingItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.bg_divider));
        this.mRecyclerView.addItemDecoration(paddingItemDecoration);
        StatusAdapter statusAdapter = new StatusAdapter(this.mList);
        this.mAdapter = statusAdapter;
        this.mRecyclerView.setAdapter(statusAdapter);
        this.mRecyclerView.setOnTouchListener(new View.OnTouchListener() { // from class: com.android.systemui.statusbar.StatusListDialog.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                BydLogHelper.logD("action = " + action);
                if (action != 0) {
                    if (action != 1) {
                        if (action != 2) {
                            if (action != 3) {
                                return false;
                            }
                        }
                    }
                    StatusListDialog.this.mHandler.sendEmptyMessageDelayed(1, 8000L);
                    return false;
                }
                StatusListDialog.this.mHandler.removeMessages(1);
                return false;
            }
        });
        this.mHandler.removeMessages(1);
        this.mHandler.sendEmptyMessageDelayed(1, 8000L);
    }

    @Override // com.android.systemui.base.BaseDialog3
    protected void setAttributes() {
        initSize();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        if (this.mWidth != 0 && this.mHeight != 0) {
            attributes.width = this.mWidth;
            attributes.height = this.mHeight;
        } else {
            attributes.width = -2;
            attributes.height = -2;
        }
        if (this.mLocationX != 0) {
            attributes.x = this.mLocationX;
        }
        if (this.mLocationY != 0) {
            attributes.y = this.mLocationY;
        }
        attributes.gravity = this.mGravity;
        attributes.format = -3;
        attributes.dimAmount = 0.0f;
        if (Build.VERSION.SDK_INT >= 26) {
            attributes.type = 2008;
        } else {
            attributes.type = 2003;
        }
        attributes.packageName = this.mContext.getPackageName();
        attributes.flags = 787752;
        getWindow().setAttributes(attributes);
    }

    @Override // com.android.systemui.base.BaseDialog3
    protected void initSize() {
        this.mWidth = this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_278);
        this.mHeight = this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_410);
        this.mGravity = 48;
        this.mLocationY = this.mContext.getResources().getDimensionPixelSize(R.dimen.status_bar_height);
    }

    public void update(List<StatusBean> list, boolean isConfigurationChanged) {
        BydLogHelper.logD("update isConfigurationChanged = " + isConfigurationChanged);
        if (isShowing()) {
            if (list == null || list.isEmpty()) {
                dismiss();
                return;
            }
            this.mList.clear();
            this.mList.addAll(list);
            this.mAdapter.notifyDataSetChanged();
            if (isConfigurationChanged) {
                showLocationX();
            }
        }
    }

    public static class StatusAdapter extends RecyclerView.Adapter<ViewHolder> {
        private List<StatusBean> mStatusBeanList;

        public StatusAdapter(List<StatusBean> statusBeanList) {
            this.mStatusBeanList = statusBeanList;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_status, parent, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder holder, int position) {
            int i;
            StatusBean statusBean = this.mStatusBeanList.get(position);
            if (statusBean.isVisibility()) {
                int status = statusBean.getStatus();
                BydLogHelper.logD("status = " + status);
                int i2 = 0;
                holder.ivIcon.setPadding(0, 0, 0, 0);
                switch (statusBean.getId()) {
                    case 0:
                        i2 = R.mipmap.icon_status_bar_show_inner;
                        i = R.string.sys_ui_status_show_mode_inner;
                        if (status == 2) {
                            i2 = R.mipmap.icon_status_bar_show_outer;
                            i = R.string.sys_ui_status_show_mode_outer;
                        }
                        break;
                    case 1:
                        holder.ivIcon.setPadding(8, 8, 8, 8);
                        i2 = R.mipmap.icon_status_bar_dvr_error;
                        i = R.string.sys_ui_status_dvr_error;
                        if (status == 2) {
                            i2 = R.mipmap.icon_status_bar_dvr_pause;
                            i = R.string.sys_ui_status_dvr_pause;
                        }
                        break;
                    case 2:
                        holder.ivIcon.setPadding(8, 8, 8, 8);
                        i2 = R.mipmap.icon_status_bar_ota;
                        i = R.string.sys_ui_status_ota;
                        break;
                    case 3:
                        holder.ivIcon.setPadding(8, 8, 8, 8);
                        i2 = R.mipmap.icon_status_bar_sdcard_warm;
                        i = R.string.sys_ui_status_sdcard;
                        break;
                    case 4:
                        i2 = R.mipmap.icon_status_bar_list_usb;
                        i = R.string.sys_ui_status_usb;
                        break;
                    case 5:
                        if (status == 0) {
                            i2 = R.mipmap.icon_status_bar_wireless_charge_left;
                            i = R.string.sys_ui_status_wireless_charging;
                        } else if (status == 1) {
                            i2 = R.mipmap.icon_status_bar_wireless_charge_full_left;
                            i = R.string.sys_ui_status_wireless_charged;
                        } else if (status == 2) {
                            i2 = R.mipmap.icon_status_bar_wireless_charge_error_left;
                            i = R.string.sys_ui_status_wireless_charge_error;
                        } else {
                            i = 0;
                        }
                        break;
                    case 6:
                        if (status == 0) {
                            i2 = R.mipmap.icon_status_bar_wireless_charge;
                            i = R.string.sys_ui_status_wireless_charging;
                        } else if (status == 1) {
                            i2 = R.mipmap.icon_status_bar_wireless_charge_full;
                            i = R.string.sys_ui_status_wireless_charged;
                        } else if (status == 2) {
                            i2 = R.mipmap.icon_status_bar_wireless_charge_error;
                            i = R.string.sys_ui_status_wireless_charge_error;
                        } else {
                            i = 0;
                        }
                        break;
                    case 7:
                        if (status == 0) {
                            i2 = R.mipmap.icon_status_bar_wireless_charge_right;
                            i = R.string.sys_ui_status_wireless_charging;
                        } else if (status == 1) {
                            i2 = R.mipmap.icon_status_bar_wireless_charge_full_right;
                            i = R.string.sys_ui_status_wireless_charged;
                        } else if (status == 2) {
                            i2 = R.mipmap.icon_status_bar_wireless_charge_error_right;
                            i = R.string.sys_ui_status_wireless_charge_error;
                        } else {
                            i = 0;
                        }
                        break;
                    case 8:
                        i2 = R.mipmap.icon_status_bar_list_mic;
                        i = R.string.sys_ui_status_mic;
                        break;
                    case 9:
                        i2 = R.mipmap.icon_status_bar_list_camera;
                        i = R.string.sys_ui_status_camera;
                        break;
                    default:
                        i = 0;
                        break;
                }
                holder.ivIcon.setImageResource(i2);
                holder.tvDetail.setText(i);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.mStatusBeanList.size();
        }

        private static class ViewHolder extends RecyclerView.ViewHolder {
            ImageView ivIcon;
            TextView tvDetail;

            public ViewHolder(View itemView) {
                super(itemView);
                this.ivIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
                this.tvDetail = (TextView) itemView.findViewById(R.id.tv_detail);
            }
        }
    }

    public void show(int end) {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeMessages(1);
            this.mHandler.sendEmptyMessageDelayed(1, 8000L);
        }
        if (AppUtils.isRightRudder()) {
            this.mEnd = end;
        } else {
            this.mEnd = AppUtil.getScreenWidth() - end;
        }
        showLocationX();
    }

    public void showLocationX() {
        this.mLocationX = ((AppUtil.getScreenWidth() >> 1) - (this.mWidth >> 1)) - this.mEnd;
        if (AppUtils.isRightRudder()) {
            this.mLocationX = -this.mLocationX;
        }
        BydLogHelper.logD("mLocationX = " + this.mLocationX);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.x = this.mLocationX;
        getWindow().setAttributes(attributes);
        super.show();
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeMessages(1);
        }
    }
}
