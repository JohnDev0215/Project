package com.android.launcher3.folder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import androidx.recyclerview.widget.RecyclerView;
import com.android.launcher3.BubbleTextView;
import com.android.launcher3.R;
import com.android.launcher3.WorkspaceItemInfo;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public class FolderAppAdapter extends RecyclerView.Adapter<Holder> {
    private final LayoutInflater mInflater;
    private final ArrayList<WorkspaceItemInfo> mItems = new ArrayList<>();
    private final ArrayList<WorkspaceItemInfo> mSelectedItems = new ArrayList<>();

    public FolderAppAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public void setItems(ArrayList<WorkspaceItemInfo> items) {
        this.mItems.clear();
        if (items != null && !items.isEmpty()) {
            this.mItems.addAll(items);
        }
        notifyDataSetChanged();
    }

    public void setSelectedItems(ArrayList<WorkspaceItemInfo> items) {
        this.mSelectedItems.clear();
        if (items != null && !items.isEmpty()) {
            this.mSelectedItems.addAll(items);
        }
        notifyDataSetChanged();
    }

    public ArrayList<WorkspaceItemInfo> getSelectedItems() {
        return this.mSelectedItems;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(this.mInflater.inflate(R.layout.launcher_folder_app_item, parent, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(Holder holder, int position) {
        final WorkspaceItemInfo workspaceItemInfo = this.mItems.get(position);
        holder.selectAppCb.setOnCheckedChangeListener(null);
        holder.selectAppCb.setChecked(this.mSelectedItems.contains(workspaceItemInfo));
        holder.appBtv.reset();
        holder.appBtv.applyFromWorkspaceItem(workspaceItemInfo);
        holder.selectAppCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.android.launcher3.folder.FolderAppAdapter.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FolderAppAdapter.this.mSelectedItems.add(workspaceItemInfo);
                } else {
                    FolderAppAdapter.this.mSelectedItems.remove(workspaceItemInfo);
                }
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mItems.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        BubbleTextView appBtv;
        CheckBox selectAppCb;

        public Holder(View itemView) {
            super(itemView);
            this.selectAppCb = (CheckBox) itemView.findViewById(R.id.cb_select_app);
            this.appBtv = (BubbleTextView) itemView.findViewById(R.id.btv_app);
        }
    }
}
