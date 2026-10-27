package com.android.launcher3.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.android.launcher3.R;
import com.android.launcher3.WidgetPreviewLoader;
import com.android.launcher3.icons.IconCache;
import com.android.launcher3.model.WidgetItem;
import com.android.launcher3.util.LabelComparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* JADX INFO: loaded from: classes4.dex */
public class WidgetsListAdapter extends RecyclerView.Adapter<WidgetsRowViewHolder> {
    private static final boolean DEBUG = false;
    private static final String TAG = "WidgetsListAdapter";
    private boolean mApplyBitmapDeferred;
    private final WidgetsDiffReporter mDiffReporter;
    private ArrayList<WidgetListRowEntry> mEntries = new ArrayList<>();
    private final View.OnClickListener mIconClickListener;
    private final View.OnLongClickListener mIconLongClickListener;
    private final int mIndent;
    private final LayoutInflater mLayoutInflater;
    private final WidgetPreviewLoader mWidgetPreviewLoader;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int pos) {
        return pos;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public boolean onFailedToRecycleView(WidgetsRowViewHolder holder) {
        return true;
    }

    public WidgetsListAdapter(Context context, LayoutInflater layoutInflater, WidgetPreviewLoader widgetPreviewLoader, IconCache iconCache, View.OnClickListener iconClickListener, View.OnLongClickListener iconLongClickListener) {
        this.mLayoutInflater = layoutInflater;
        this.mWidgetPreviewLoader = widgetPreviewLoader;
        this.mIconClickListener = iconClickListener;
        this.mIconLongClickListener = iconLongClickListener;
        this.mIndent = context.getResources().getDimensionPixelSize(R.dimen.widget_section_indent);
        this.mDiffReporter = new WidgetsDiffReporter(iconCache, this);
    }

    public void setApplyBitmapDeferred(boolean isDeferred, RecyclerView rv) {
        this.mApplyBitmapDeferred = isDeferred;
        for (int childCount = rv.getChildCount() - 1; childCount >= 0; childCount--) {
            WidgetsRowViewHolder widgetsRowViewHolder = (WidgetsRowViewHolder) rv.getChildViewHolder(rv.getChildAt(childCount));
            for (int childCount2 = widgetsRowViewHolder.cellContainer.getChildCount() - 1; childCount2 >= 0; childCount2--) {
                View childAt = widgetsRowViewHolder.cellContainer.getChildAt(childCount2);
                if (childAt instanceof WidgetCell) {
                    ((WidgetCell) childAt).setApplyBitmapDeferred(this.mApplyBitmapDeferred);
                }
            }
        }
    }

    public void setWidgets(ArrayList<WidgetListRowEntry> tempEntries) {
        WidgetListRowEntryComparator widgetListRowEntryComparator = new WidgetListRowEntryComparator();
        Collections.sort(tempEntries, widgetListRowEntryComparator);
        this.mDiffReporter.process(this.mEntries, tempEntries, widgetListRowEntryComparator);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mEntries.size();
    }

    public String getSectionName(int pos) {
        return this.mEntries.get(pos).titleSectionName;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(WidgetsRowViewHolder holder, int pos) {
        WidgetListRowEntry widgetListRowEntry = this.mEntries.get(pos);
        ArrayList<WidgetItem> arrayList = widgetListRowEntry.widgets;
        ViewGroup viewGroup = holder.cellContainer;
        int size = arrayList.size() + Math.max(0, arrayList.size() - 1);
        int childCount = viewGroup.getChildCount();
        if (size > childCount) {
            while (childCount < size) {
                if ((childCount & 1) == 1) {
                    this.mLayoutInflater.inflate(R.layout.widget_list_divider, viewGroup);
                } else {
                    WidgetCell widgetCell = (WidgetCell) this.mLayoutInflater.inflate(R.layout.widget_cell, viewGroup, false);
                    widgetCell.setOnClickListener(this.mIconClickListener);
                    widgetCell.setOnLongClickListener(this.mIconLongClickListener);
                    viewGroup.addView(widgetCell);
                }
                childCount++;
            }
        } else if (size < childCount) {
            while (size < childCount) {
                viewGroup.getChildAt(size).setVisibility(8);
                size++;
            }
        }
        holder.title.applyFromPackageItemInfo(widgetListRowEntry.pkgItem);
        for (int i = 0; i < arrayList.size(); i++) {
            int i2 = i * 2;
            WidgetCell widgetCell2 = (WidgetCell) viewGroup.getChildAt(i2);
            widgetCell2.applyFromCellItem(arrayList.get(i), this.mWidgetPreviewLoader);
            widgetCell2.setApplyBitmapDeferred(this.mApplyBitmapDeferred);
            widgetCell2.ensurePreview();
            widgetCell2.setVisibility(0);
            if (i > 0) {
                viewGroup.getChildAt(i2 - 1).setVisibility(0);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public WidgetsRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup viewGroup = (ViewGroup) this.mLayoutInflater.inflate(R.layout.widgets_list_row_view, parent, false);
        viewGroup.findViewById(R.id.widgets_cell_list).setPaddingRelative(this.mIndent, 0, 1, 0);
        return new WidgetsRowViewHolder(viewGroup);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(WidgetsRowViewHolder holder) {
        int childCount = holder.cellContainer.getChildCount();
        for (int i = 0; i < childCount; i += 2) {
            ((WidgetCell) holder.cellContainer.getChildAt(i)).clear();
        }
    }

    public static class WidgetListRowEntryComparator implements Comparator<WidgetListRowEntry> {
        private final LabelComparator mComparator = new LabelComparator();

        @Override // java.util.Comparator
        public int compare(WidgetListRowEntry a, WidgetListRowEntry b) {
            return this.mComparator.compare(a.pkgItem.title.toString(), b.pkgItem.title.toString());
        }
    }
}
