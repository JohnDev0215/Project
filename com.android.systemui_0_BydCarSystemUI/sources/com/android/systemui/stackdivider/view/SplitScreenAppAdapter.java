package com.android.systemui.stackdivider.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.R;
import com.android.systemui.stackdivider.DividerUtils;
import com.android.systemui.util.FastClickUtil;
import com.byd.card.bean.AppInfo;
import com.byd.card.forbidden.ForbiddenManager;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.window.ToastUtils;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class SplitScreenAppAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Activity mActivity;
    private final Context mContext;
    private final boolean mIsPrimary;
    private List<AppInfo> mSupportSplitScreenAppList;
    private ViewGroup mViewGroup;
    private final List<ViewHolder> mViewHolders = new ArrayList();

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView appImage;
        TextView appName;

        public ViewHolder(View view) {
            super(view);
            this.appImage = (ImageView) view.findViewById(R.id.app_image);
            this.appName = (TextView) view.findViewById(R.id.app_name);
        }
    }

    public SplitScreenAppAdapter(Context context, List<AppInfo> mSupportSplitScreenAppList, boolean isPrimary, Activity activity, ViewGroup viewGroup) {
        this.mSupportSplitScreenAppList = mSupportSplitScreenAppList;
        this.mIsPrimary = isPrimary;
        this.mContext = context;
        this.mActivity = activity;
        this.mViewGroup = viewGroup;
        updateForbidden(ForbiddenManager.isPGear());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.split_screen_app_list_item, parent, false));
        viewHolder.appImage.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.stackdivider.view.-$$Lambda$SplitScreenAppAdapter$RGAHdqeyd9V_evcMPI9uHChRz-c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreateViewHolder$0$SplitScreenAppAdapter(viewHolder, view);
            }
        });
        this.mViewHolders.add(viewHolder);
        return viewHolder;
    }

    public /* synthetic */ void lambda$onCreateViewHolder$0$SplitScreenAppAdapter(ViewHolder viewHolder, View view) {
        if (FastClickUtil.isFastDoubleClick(500L)) {
            BydLogHelper.logD("onCreateViewHolder Click isFastDoubleClick");
            return;
        }
        AppInfo appInfo = this.mSupportSplitScreenAppList.get(viewHolder.getAdapterPosition());
        if (appInfo.isForbidden()) {
            ToastUtils.show(this.mContext, R.string.open_app_on_p_gear);
            return;
        }
        String packageName = appInfo.getPackageName();
        String activity = appInfo.getActivity();
        BydLogHelper.logD("onCreateViewHolder Click appName : " + appInfo.getName() + " , packName : " + packageName);
        if (packageName != null) {
            boolean zIsAppShowingOtherSide = DividerUtils.getInstance().isAppShowingOtherSide(packageName, this.mIsPrimary ? 4 : 3);
            BydLogHelper.logD("onCreateViewHolder Click mIsPrimary : " + this.mIsPrimary + " , isAppShowingOtherSide : " + zIsAppShowingOtherSide);
            if (zIsAppShowingOtherSide) {
                if (this.mIsPrimary) {
                    EventBus.getDefault().post(new SplitScreenEvent(2));
                    return;
                } else {
                    EventBus.getDefault().post(new SplitScreenEvent(1));
                    return;
                }
            }
            DividerUtils.getInstance().startActivity(this.mContext, packageName, activity, this.mIsPrimary);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
        AppInfo appInfo = this.mSupportSplitScreenAppList.get(position);
        holder.appImage.setImageDrawable(appInfo.getIcon());
        holder.appName.setText(appInfo.getName());
        if (appInfo.isForbidden()) {
            holder.appImage.setAlpha(0.4f);
            holder.appName.setAlpha(0.4f);
        } else {
            holder.appImage.setAlpha(1.0f);
            holder.appName.setAlpha(1.0f);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mSupportSplitScreenAppList.size();
    }

    public void refreshList(List<AppInfo> list) {
        this.mSupportSplitScreenAppList = list;
        updateForbidden(ForbiddenManager.isPGear());
    }

    public void updateForbidden(boolean isPGear) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.mSupportSplitScreenAppList);
        ForbiddenManager.updateForbidden(arrayList, isPGear);
        notifyDataSetChanged();
    }
}
