package com.android.launcher3;

import android.os.Process;
import com.android.launcher3.model.ModelWriter;
import com.android.launcher3.util.ContentWriter;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public class FolderInfo extends ItemInfo {
    public static final int FLAG_ITEMS_SORTED = 1;
    public static final int FLAG_MULTI_PAGE_ANIMATION = 4;
    public static final int FLAG_WORK_FOLDER = 2;
    public static final int NO_FLAGS = 0;
    public ArrayList<WorkspaceItemInfo> contents = new ArrayList<>();
    ArrayList<FolderListener> listeners = new ArrayList<>();
    public int options;

    public interface FolderListener {
        void onAdd(WorkspaceItemInfo item, int rank);

        void onItemsChanged(boolean animate);

        void onRemove(WorkspaceItemInfo item);

        void onTitleChanged(CharSequence title);

        void prepareAutoUpdate();
    }

    public FolderInfo() {
        this.itemType = 2;
        this.user = Process.myUserHandle();
    }

    public void add(WorkspaceItemInfo item, boolean animate) {
        add(item, this.contents.size(), animate);
    }

    public void add(WorkspaceItemInfo item, int rank, boolean animate) {
        int iBoundToRange = Utilities.boundToRange(rank, 0, this.contents.size());
        this.contents.add(iBoundToRange, item);
        for (int i = 0; i < this.listeners.size(); i++) {
            this.listeners.get(i).onAdd(item, iBoundToRange);
        }
        itemsChanged(animate);
    }

    public void remove(WorkspaceItemInfo item, boolean animate) {
        this.contents.remove(item);
        for (int i = 0; i < this.listeners.size(); i++) {
            this.listeners.get(i).onRemove(item);
        }
        itemsChanged(animate);
    }

    public void setTitle(CharSequence title) {
        this.title = title;
        for (int i = 0; i < this.listeners.size(); i++) {
            this.listeners.get(i).onTitleChanged(title);
        }
    }

    @Override // com.android.launcher3.ItemInfo
    public void onAddToDatabase(ContentWriter writer) {
        super.onAddToDatabase(writer);
        writer.put("title", this.title).put("options", Integer.valueOf(this.options));
    }

    public void addListener(FolderListener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(FolderListener listener) {
        this.listeners.remove(listener);
    }

    public void itemsChanged(boolean animate) {
        for (int i = 0; i < this.listeners.size(); i++) {
            this.listeners.get(i).onItemsChanged(animate);
        }
    }

    public void prepareAutoUpdate() {
        for (int i = 0; i < this.listeners.size(); i++) {
            this.listeners.get(i).prepareAutoUpdate();
        }
    }

    public boolean hasOption(int optionFlag) {
        return (this.options & optionFlag) != 0;
    }

    public void setOption(int option, boolean isEnabled, ModelWriter writer) {
        int i = this.options;
        if (isEnabled) {
            this.options = option | i;
        } else {
            this.options = (~option) & i;
        }
        if (writer == null || i == this.options) {
            return;
        }
        writer.updateItemInDatabase(this);
    }
}
