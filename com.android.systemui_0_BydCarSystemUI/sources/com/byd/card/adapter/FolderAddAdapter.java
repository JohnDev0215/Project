package com.byd.card.adapter;

import android.app.UiStyleConfig;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.R;
import com.byd.card.drag.ApplicationInfo;
import com.byd.card.utils.BitmapUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
public class FolderAddAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "FolderAddAdapter";
    private Context context;
    private OnCheckedChangeListener listener;
    private List<ApplicationInfo> mCheckedList = new ArrayList();
    private int mFolderId;
    private List<ApplicationInfo> mList;

    public interface OnCheckedChangeListener {
        void onCheckedChanged(List<ApplicationInfo> list);
    }

    public FolderAddAdapter(Context context) {
        this.context = context;
    }

    public void updateAdapter(List<ApplicationInfo> list) {
        this.mCheckedList.clear();
        this.mList = list;
        for (ApplicationInfo applicationInfo : list) {
            if (applicationInfo.checked) {
                this.mCheckedList.add(applicationInfo);
                this.mFolderId = applicationInfo.folderId;
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewInflate = LayoutInflater.from(this.context).inflate(R.layout.folder_item_appinfo, parent, false);
        int uiStyle = UiStyleConfig.getInstance(this.context).getUiStyle();
        if (uiStyle == 1) {
            viewInflate = LayoutInflater.from(this.context).inflate(R.layout.denza_folder_item_appinfo, parent, false);
        } else if (uiStyle != 2) {
            viewInflate = LayoutInflater.from(this.context).inflate(R.layout.folder_item_appinfo, parent, false);
        }
        return new ViewHolder(viewInflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            final ViewHolder viewHolder = (ViewHolder) holder;
            String title = this.mList.get(position).getTitle();
            if (title.contains("-")) {
                setAppName(viewHolder, title);
            } else {
                viewHolder.appName.setText(title);
            }
            ApplicationInfo applicationInfo = this.mList.get(position);
            Bitmap bitmapChangedBitmap = BitmapUtils.changedBitmap(this.context, BitmapUtils.adapterMultiTheme(applicationInfo.getPackageName(), applicationInfo.getIconBytes()));
            if (bitmapChangedBitmap == null) {
                bitmapChangedBitmap = this.mList.get(position).getIcon();
            }
            viewHolder.appIcon.setBackground(new BitmapDrawable(bitmapChangedBitmap));
            final ApplicationInfo applicationInfo2 = this.mList.get(position);
            if (this.mList.get(position).checked) {
                viewHolder.checkBox.setChecked(true);
            } else {
                viewHolder.checkBox.setChecked(false);
            }
            viewHolder.mItem.setOnClickListener(new View.OnClickListener() { // from class: com.byd.card.adapter.FolderAddAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (applicationInfo2.checked) {
                        applicationInfo2.setChecked(false);
                        applicationInfo2.folderId = 0;
                        FolderAddAdapter.this.mCheckedList.remove(applicationInfo2);
                        viewHolder.checkBox.setChecked(false);
                    } else {
                        applicationInfo2.setChecked(true);
                        applicationInfo2.folderId = FolderAddAdapter.this.mFolderId;
                        FolderAddAdapter.this.mCheckedList.add(applicationInfo2);
                        viewHolder.checkBox.setChecked(true);
                    }
                    if (FolderAddAdapter.this.listener != null) {
                        FolderAddAdapter.this.listener.onCheckedChanged(FolderAddAdapter.this.mCheckedList);
                    }
                }
            });
        }
    }

    public List<ApplicationInfo> getCheckedList() {
        return this.mCheckedList;
    }

    public List<ApplicationInfo> getUnCheckedList() {
        this.mList.removeIf(new Predicate() { // from class: com.byd.card.adapter.-$$Lambda$FolderAddAdapter$_RrNklCO8Zhq7KqIW9sYpPRZhXY
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.lambda$getUnCheckedList$0$FolderAddAdapter((ApplicationInfo) obj);
            }
        });
        return this.mList;
    }

    public /* synthetic */ boolean lambda$getUnCheckedList$0$FolderAddAdapter(ApplicationInfo applicationInfo) {
        return this.mCheckedList.contains(applicationInfo);
    }

    private void setAppName(ViewHolder viewHolder, String name) {
        TextPaint paint = viewHolder.appName.getPaint();
        paint.setTextSize(viewHolder.appName.getTextSize());
        if (((int) paint.measureText(name)) > this.context.getResources().getDimensionPixelOffset(R.dimen.dp_128)) {
            int iLastIndexOf = name.lastIndexOf("-");
            viewHolder.appName.setText(String.format("%s\n%s", name.substring(0, iLastIndexOf) + "-", name.substring(iLastIndexOf + 1)));
            return;
        }
        viewHolder.appName.setText(name);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<ApplicationInfo> list = this.mList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView appIcon;
        private TextView appName;
        private CheckBox checkBox;
        private LinearLayout mItem;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mItem = (LinearLayout) itemView.findViewById(R.id.item_check);
            this.appIcon = (ImageView) itemView.findViewById(R.id.app_icon);
            this.checkBox = (CheckBox) itemView.findViewById(R.id.check_app);
            this.appName = (TextView) itemView.findViewById(R.id.app_name);
        }
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        this.listener = listener;
    }
}
