package com.android.launcher3.folder;

import android.util.Log;
import com.android.launcher3.FolderInfo;
import com.android.launcher3.InvariantDeviceProfile;

/* JADX INFO: loaded from: classes4.dex */
public class FolderIconPreviewVerifier {
    private static final String TAG = "FolderPreviewVerifier";
    private int mGridCountX;
    private final int mMaxGridCountX;
    private final int mMaxGridCountY;
    private final int mMaxItemsPerPage;
    private int mNumItemsInFolder;
    private final int[] mGridSize = {1, 1};
    private boolean mDisplayingUpperLeftQuadrant = false;

    public FolderIconPreviewVerifier(InvariantDeviceProfile profile) {
        int i = profile.numFolderColumns;
        this.mMaxGridCountX = i;
        int i2 = profile.numFolderRows;
        this.mMaxGridCountY = i2;
        this.mMaxItemsPerPage = i * i2;
    }

    public void setFolderInfo(FolderInfo info) {
        int size = info.contents.size();
        if (size != this.mNumItemsInFolder) {
            FolderPagedView.calculateGridSize(size, 0, 0, this.mMaxGridCountX, this.mMaxGridCountY, this.mMaxItemsPerPage, this.mGridSize);
            this.mGridCountX = this.mGridSize[0];
            this.mDisplayingUpperLeftQuadrant = size > 9;
            this.mNumItemsInFolder = size;
        }
    }

    public boolean isItemInPreview(int rank) {
        return isItemInPreview(0, rank);
    }

    public boolean isItemInPreview(int page, int rank) {
        if (this.mGridSize[0] == 1) {
            Log.w(TAG, "setFolderInfo not called before checking if item is in preview.");
        }
        return rank < 9;
    }
}
