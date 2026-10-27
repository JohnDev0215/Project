package com.android.launcher3;

import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import com.android.launcher3.CellLayout;
import com.android.launcher3.folder.Folder;
import com.android.launcher3.folder.FolderPagedView;
import com.android.launcher3.util.FocusLogic;

/* JADX INFO: loaded from: classes4.dex */
public class FocusHelper {
    private static final boolean DEBUG = false;
    private static final String TAG = "FocusHelper";

    public static class PagedFolderKeyEventListener implements View.OnKeyListener {
        private final Folder mFolder;

        public PagedFolderKeyEventListener(Folder folder) {
            this.mFolder = folder;
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View v, int keyCode, KeyEvent e) {
            boolean zShouldConsume = FocusLogic.shouldConsume(keyCode);
            if (e.getAction() == 1) {
                return zShouldConsume;
            }
            if (!(v.getParent() instanceof ShortcutAndWidgetContainer)) {
                return false;
            }
            ShortcutAndWidgetContainer shortcutAndWidgetContainer = (ShortcutAndWidgetContainer) v.getParent();
            CellLayout cellLayout = (CellLayout) shortcutAndWidgetContainer.getParent();
            int iIndexOfChild = shortcutAndWidgetContainer.indexOfChild(v);
            FolderPagedView folderPagedView = (FolderPagedView) cellLayout.getParent();
            int iIndexOfChild2 = folderPagedView.indexOfChild(cellLayout);
            int pageCount = folderPagedView.getPageCount();
            boolean zIsRtl = Utilities.isRtl(v.getResources());
            int[][] iArrCreateSparseMatrix = FocusLogic.createSparseMatrix(cellLayout);
            int iHandleKeyEvent = FocusLogic.handleKeyEvent(keyCode, iArrCreateSparseMatrix, iIndexOfChild, iIndexOfChild2, pageCount, zIsRtl);
            if (iHandleKeyEvent == -1) {
                handleNoopKey(keyCode, v);
                return zShouldConsume;
            }
            View adjacentChildInNextFolderPage = null;
            switch (iHandleKeyEvent) {
                case -10:
                case -9:
                    int i = iIndexOfChild2 + 1;
                    ShortcutAndWidgetContainer cellLayoutChildrenForIndex = FocusHelper.getCellLayoutChildrenForIndex(folderPagedView, i);
                    if (cellLayoutChildrenForIndex != null) {
                        folderPagedView.snapToPage(i);
                        adjacentChildInNextFolderPage = FocusLogic.getAdjacentChildInNextFolderPage(cellLayoutChildrenForIndex, v, iHandleKeyEvent);
                    }
                    break;
                case -8:
                    int i2 = iIndexOfChild2 + 1;
                    ShortcutAndWidgetContainer cellLayoutChildrenForIndex2 = FocusHelper.getCellLayoutChildrenForIndex(folderPagedView, i2);
                    if (cellLayoutChildrenForIndex2 != null) {
                        folderPagedView.snapToPage(i2);
                        adjacentChildInNextFolderPage = cellLayoutChildrenForIndex2.getChildAt(0, 0);
                    }
                    break;
                case -7:
                    adjacentChildInNextFolderPage = folderPagedView.getLastItem();
                    break;
                case -6:
                    adjacentChildInNextFolderPage = cellLayout.getChildAt(0, 0);
                    break;
                case -5:
                case -2:
                    int i3 = iIndexOfChild2 - 1;
                    ShortcutAndWidgetContainer cellLayoutChildrenForIndex3 = FocusHelper.getCellLayoutChildrenForIndex(folderPagedView, i3);
                    if (cellLayoutChildrenForIndex3 != null) {
                        int i4 = ((CellLayout.LayoutParams) v.getLayoutParams()).cellY;
                        folderPagedView.snapToPage(i3);
                        adjacentChildInNextFolderPage = cellLayoutChildrenForIndex3.getChildAt((iHandleKeyEvent == -5) ^ cellLayoutChildrenForIndex3.invertLayoutHorizontally() ? 0 : iArrCreateSparseMatrix.length - 1, i4);
                    }
                    break;
                case -4:
                    int i5 = iIndexOfChild2 - 1;
                    ShortcutAndWidgetContainer cellLayoutChildrenForIndex4 = FocusHelper.getCellLayoutChildrenForIndex(folderPagedView, i5);
                    if (cellLayoutChildrenForIndex4 != null) {
                        folderPagedView.snapToPage(i5);
                        adjacentChildInNextFolderPage = cellLayoutChildrenForIndex4.getChildAt(iArrCreateSparseMatrix.length - 1, iArrCreateSparseMatrix[0].length - 1);
                    }
                    break;
                case -3:
                    int i6 = iIndexOfChild2 - 1;
                    ShortcutAndWidgetContainer cellLayoutChildrenForIndex5 = FocusHelper.getCellLayoutChildrenForIndex(folderPagedView, i6);
                    if (cellLayoutChildrenForIndex5 != null) {
                        folderPagedView.snapToPage(i6);
                        adjacentChildInNextFolderPage = cellLayoutChildrenForIndex5.getChildAt(0, 0);
                    }
                    break;
                default:
                    adjacentChildInNextFolderPage = shortcutAndWidgetContainer.getChildAt(iHandleKeyEvent);
                    break;
            }
            if (adjacentChildInNextFolderPage != null) {
                adjacentChildInNextFolderPage.requestFocus();
                FocusHelper.playSoundEffect(keyCode, v);
            } else {
                handleNoopKey(keyCode, v);
            }
            return zShouldConsume;
        }

        public void handleNoopKey(int keyCode, View v) {
            if (keyCode == 20) {
                this.mFolder.mFolderName.requestFocus();
                FocusHelper.playSoundEffect(keyCode, v);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0115  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static boolean handleHotseatButtonKeyEvent(android.view.View r17, int r18, android.view.KeyEvent r19) {
        /*
            Method dump skipped, instruction units count: 294
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.FocusHelper.handleHotseatButtonKeyEvent(android.view.View, int, android.view.KeyEvent):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:66:0x01c1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static boolean handleIconKeyEvent(android.view.View r17, int r18, android.view.KeyEvent r19) {
        /*
            Method dump skipped, instruction units count: 484
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.FocusHelper.handleIconKeyEvent(android.view.View, int, android.view.KeyEvent):boolean");
    }

    static ShortcutAndWidgetContainer getCellLayoutChildrenForIndex(ViewGroup container, int i) {
        return ((CellLayout) container.getChildAt(i)).getShortcutsAndWidgets();
    }

    static void playSoundEffect(int keyCode, View v) {
        if (keyCode != 92) {
            if (keyCode != 93) {
                if (keyCode != 122) {
                    if (keyCode != 123) {
                        switch (keyCode) {
                            case 21:
                                v.playSoundEffect(1);
                                break;
                            case 22:
                                v.playSoundEffect(3);
                                break;
                        }
                    }
                }
            }
            v.playSoundEffect(4);
            return;
        }
        v.playSoundEffect(2);
    }

    private static View handlePreviousPageLastItem(Workspace workspace, CellLayout hotseatLayout, int pageIndex, boolean isRtl) {
        int i = pageIndex - 1;
        if (i < 0) {
            return null;
        }
        View firstFocusableIconInReverseReadingOrder = getFirstFocusableIconInReverseReadingOrder((CellLayout) workspace.getChildAt(i), isRtl);
        if (firstFocusableIconInReverseReadingOrder != null) {
            return firstFocusableIconInReverseReadingOrder;
        }
        View firstFocusableIconInReverseReadingOrder2 = getFirstFocusableIconInReverseReadingOrder(hotseatLayout, isRtl);
        workspace.snapToPage(i);
        return firstFocusableIconInReverseReadingOrder2;
    }

    private static View handleNextPageFirstItem(Workspace workspace, CellLayout hotseatLayout, int pageIndex, boolean isRtl) {
        int i = pageIndex + 1;
        if (i >= workspace.getPageCount()) {
            return null;
        }
        View firstFocusableIconInReadingOrder = getFirstFocusableIconInReadingOrder((CellLayout) workspace.getChildAt(i), isRtl);
        if (firstFocusableIconInReadingOrder != null) {
            return firstFocusableIconInReadingOrder;
        }
        View firstFocusableIconInReadingOrder2 = getFirstFocusableIconInReadingOrder(hotseatLayout, isRtl);
        workspace.snapToPage(i);
        return firstFocusableIconInReadingOrder2;
    }

    private static View getFirstFocusableIconInReadingOrder(CellLayout cellLayout, boolean isRtl) {
        int countX = cellLayout.getCountX();
        for (int i = 0; i < cellLayout.getCountY(); i++) {
            int i2 = isRtl ? -1 : 1;
            for (int i3 = isRtl ? countX - 1 : 0; i3 >= 0 && i3 < countX; i3 += i2) {
                View childAt = cellLayout.getChildAt(i3, i);
                if (childAt != null && childAt.isFocusable()) {
                    return childAt;
                }
            }
        }
        return null;
    }

    private static View getFirstFocusableIconInReverseReadingOrder(CellLayout cellLayout, boolean isRtl) {
        int countX = cellLayout.getCountX();
        for (int countY = cellLayout.getCountY() - 1; countY >= 0; countY--) {
            int i = isRtl ? 1 : -1;
            for (int i2 = isRtl ? 0 : countX - 1; i2 >= 0 && i2 < countX; i2 += i) {
                View childAt = cellLayout.getChildAt(i2, countY);
                if (childAt != null && childAt.isFocusable()) {
                    return childAt;
                }
            }
        }
        return null;
    }
}
