package com.android.launcher3.folder;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.R;
import com.android.launcher3.WorkspaceItemInfo;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public class SelectedFolderApp {
    private FolderAppAdapter appAdapter;
    private TextView cancelBtn;
    private TextView confirmBtn;
    private RecyclerView folderAppsRv;
    private View folderAppsView;
    private OnAddAppsListener mListener;
    private TextView titleTv;

    interface OnAddAppsListener {
        void onAdd(ArrayList<WorkspaceItemInfo> items);
    }

    public SelectedFolderApp(Launcher launcher) {
        View viewFindViewById = launcher.findViewById(R.id.rl_folder_apps);
        this.folderAppsView = viewFindViewById;
        this.titleTv = (TextView) viewFindViewById.findViewById(R.id.tv_folder_title);
        this.confirmBtn = (TextView) this.folderAppsView.findViewById(R.id.btn_confirm);
        this.cancelBtn = (TextView) this.folderAppsView.findViewById(R.id.btn_cancel);
        this.folderAppsRv = (RecyclerView) this.folderAppsView.findViewById(R.id.rv_folder_apps);
        FolderAppAdapter folderAppAdapter = new FolderAppAdapter(launcher);
        this.appAdapter = folderAppAdapter;
        this.folderAppsRv.setAdapter(folderAppAdapter);
        this.folderAppsRv.setLayoutManager(new GridLayoutManager(launcher, 3));
        this.confirmBtn.setOnClickListener(new View.OnClickListener() { // from class: com.android.launcher3.folder.SelectedFolderApp.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (SelectedFolderApp.this.mListener != null) {
                    SelectedFolderApp.this.mListener.onAdd(SelectedFolderApp.this.appAdapter.getSelectedItems());
                }
                SelectedFolderApp.this.mListener = null;
                SelectedFolderApp.this.hide();
            }
        });
        this.cancelBtn.setOnClickListener(new View.OnClickListener() { // from class: com.android.launcher3.folder.SelectedFolderApp.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                SelectedFolderApp.this.hide();
            }
        });
    }

    private void show() {
        this.folderAppsView.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hide() {
        this.folderAppsView.setVisibility(8);
    }

    public void showAddAppsView(Folder folder, OnAddAppsListener listener) {
        this.mListener = listener;
        ArrayList<WorkspaceItemInfo> all = getAll(folder);
        this.appAdapter.setItems(all);
        ArrayList<WorkspaceItemInfo> items = folder.getItems();
        this.appAdapter.setSelectedItems(items);
        this.titleTv.setText(folder.getResources().getString(R.string.launcher_add_to_folder, String.valueOf(items.size()), String.valueOf(all.size())));
        show();
        this.folderAppsRv.scrollToPosition(0);
    }

    private static ArrayList<WorkspaceItemInfo> getAll(Folder folder) {
        ArrayList<ItemInfo> allWorkspaceItems = LauncherAppState.getInstance(folder.getContext()).getModel().getAllWorkspaceItems();
        ArrayList<WorkspaceItemInfo> items = folder.getItems();
        ArrayList<WorkspaceItemInfo> arrayList = new ArrayList<>(allWorkspaceItems.size() + items.size());
        arrayList.addAll(items);
        for (ItemInfo itemInfo : allWorkspaceItems) {
            if ((itemInfo instanceof WorkspaceItemInfo) && !arrayList.contains(itemInfo)) {
                arrayList.add((WorkspaceItemInfo) itemInfo);
            }
        }
        return arrayList;
    }
}
