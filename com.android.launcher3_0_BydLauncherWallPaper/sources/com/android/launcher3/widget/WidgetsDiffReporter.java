package com.android.launcher3.widget;

import androidx.recyclerview.widget.RecyclerView;
import com.android.launcher3.icons.IconCache;
import com.android.launcher3.model.PackageItemInfo;
import com.android.launcher3.widget.WidgetsListAdapter;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public class WidgetsDiffReporter {
    private static final boolean DEBUG = false;
    private static final String TAG = "WidgetsDiffReporter";
    private final IconCache mIconCache;
    private final RecyclerView.Adapter mListener;

    public WidgetsDiffReporter(IconCache iconCache, RecyclerView.Adapter listener) {
        this.mIconCache = iconCache;
        this.mListener = listener;
    }

    public void process(ArrayList<WidgetListRowEntry> currentEntries, ArrayList<WidgetListRowEntry> newEntries, WidgetsListAdapter.WidgetListRowEntryComparator comparator) {
        int size;
        if (currentEntries.isEmpty() || newEntries.isEmpty()) {
            if (currentEntries.size() != newEntries.size()) {
                currentEntries.clear();
                currentEntries.addAll(newEntries);
                this.mListener.notifyDataSetChanged();
                return;
            }
            return;
        }
        Iterator it = ((ArrayList) currentEntries.clone()).iterator();
        Iterator<WidgetListRowEntry> it2 = newEntries.iterator();
        WidgetListRowEntry widgetListRowEntry = (WidgetListRowEntry) it.next();
        WidgetListRowEntry next = it2.next();
        while (true) {
            int iComparePackageName = comparePackageName(widgetListRowEntry, next, comparator);
            if (iComparePackageName < 0) {
                int iIndexOf = currentEntries.indexOf(widgetListRowEntry);
                this.mListener.notifyItemRemoved(iIndexOf);
                currentEntries.remove(iIndexOf);
                widgetListRowEntry = it.hasNext() ? (WidgetListRowEntry) it.next() : null;
            } else {
                if (iComparePackageName > 0) {
                    if (widgetListRowEntry != null) {
                        size = currentEntries.indexOf(widgetListRowEntry);
                    } else {
                        size = currentEntries.size();
                    }
                    currentEntries.add(size, next);
                    next = it2.hasNext() ? it2.next() : null;
                    this.mListener.notifyItemInserted(size);
                } else {
                    if (!isSamePackageItemInfo(widgetListRowEntry.pkgItem, next.pkgItem) || !widgetListRowEntry.widgets.equals(next.widgets)) {
                        int iIndexOf2 = currentEntries.indexOf(widgetListRowEntry);
                        currentEntries.set(iIndexOf2, next);
                        this.mListener.notifyItemChanged(iIndexOf2);
                    }
                    widgetListRowEntry = it.hasNext() ? (WidgetListRowEntry) it.next() : null;
                    if (it2.hasNext()) {
                        next = it2.next();
                    }
                }
                next = next;
            }
            if (widgetListRowEntry == null && next == null) {
                return;
            }
        }
    }

    private int comparePackageName(WidgetListRowEntry curRow, WidgetListRowEntry newRow, WidgetsListAdapter.WidgetListRowEntryComparator comparator) {
        if (curRow == null && newRow == null) {
            throw new IllegalStateException("Cannot compare PackageItemInfo if both rows are null.");
        }
        if (curRow == null && newRow != null) {
            return 1;
        }
        if (curRow == null || newRow != null) {
            return comparator.compare(curRow, newRow);
        }
        return -1;
    }

    private boolean isSamePackageItemInfo(PackageItemInfo curInfo, PackageItemInfo newInfo) {
        return curInfo.iconBitmap.equals(newInfo.iconBitmap) && !this.mIconCache.isDefaultIcon(curInfo.iconBitmap, curInfo.user);
    }
}
