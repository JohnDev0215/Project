package com.android.common.widget;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class CompositeCursorAdapter extends BaseAdapter {
    private static final int INITIAL_CAPACITY = 2;
    private boolean mCacheValid;
    private final Context mContext;
    private int mCount;
    private boolean mNotificationNeeded;
    private boolean mNotificationsEnabled;
    private ArrayList<Partition> mPartitions;

    protected abstract void bindView(View view, int i, Cursor cursor, int i2);

    protected abstract View newView(Context context, int i, Cursor cursor, int i2, ViewGroup viewGroup);

    public static class Partition {
        int count;
        Cursor cursor;
        boolean hasHeader;
        int idColumnIndex;
        boolean showIfEmpty;

        public Partition(boolean showIfEmpty, boolean hasHeader) {
            this.showIfEmpty = showIfEmpty;
            this.hasHeader = hasHeader;
        }

        public boolean getShowIfEmpty() {
            return this.showIfEmpty;
        }

        public boolean getHasHeader() {
            return this.hasHeader;
        }

        public boolean isEmpty() {
            return this.count == 0;
        }
    }

    public CompositeCursorAdapter(Context context) {
        this(context, 2);
    }

    public CompositeCursorAdapter(Context context, int initialCapacity) {
        this.mCount = 0;
        this.mCacheValid = true;
        this.mNotificationsEnabled = true;
        this.mContext = context;
        this.mPartitions = new ArrayList<>();
    }

    public Context getContext() {
        return this.mContext;
    }

    public void addPartition(boolean showIfEmpty, boolean hasHeader) {
        addPartition(new Partition(showIfEmpty, hasHeader));
    }

    public void addPartition(Partition partition) {
        this.mPartitions.add(partition);
        invalidate();
        notifyDataSetChanged();
    }

    public void addPartition(int location, Partition partition) {
        this.mPartitions.add(location, partition);
        invalidate();
        notifyDataSetChanged();
    }

    public void removePartition(int partitionIndex) {
        Cursor cursor = this.mPartitions.get(partitionIndex).cursor;
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        this.mPartitions.remove(partitionIndex);
        invalidate();
        notifyDataSetChanged();
    }

    public void clearPartitions() {
        for (Partition partition : this.mPartitions) {
            partition.cursor = null;
        }
        invalidate();
        notifyDataSetChanged();
    }

    public void close() {
        for (Partition partition : this.mPartitions) {
            Cursor cursor = partition.cursor;
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        this.mPartitions.clear();
        invalidate();
        notifyDataSetChanged();
    }

    public void setHasHeader(int partitionIndex, boolean flag) {
        this.mPartitions.get(partitionIndex).hasHeader = flag;
        invalidate();
    }

    public void setShowIfEmpty(int partitionIndex, boolean flag) {
        this.mPartitions.get(partitionIndex).showIfEmpty = flag;
        invalidate();
    }

    public Partition getPartition(int partitionIndex) {
        return this.mPartitions.get(partitionIndex);
    }

    protected void invalidate() {
        this.mCacheValid = false;
    }

    public int getPartitionCount() {
        return this.mPartitions.size();
    }

    protected void ensureCacheValid() {
        int count;
        if (this.mCacheValid) {
            return;
        }
        this.mCount = 0;
        for (Partition partition : this.mPartitions) {
            Cursor cursor = partition.cursor;
            if (cursor == null || cursor.isClosed()) {
                count = 0;
            } else {
                count = cursor.getCount();
            }
            if (partition.hasHeader && (count != 0 || partition.showIfEmpty)) {
                count++;
            }
            partition.count = count;
            this.mCount += count;
        }
        this.mCacheValid = true;
    }

    public boolean hasHeader(int partition) {
        return this.mPartitions.get(partition).hasHeader;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        ensureCacheValid();
        return this.mCount;
    }

    public Cursor getCursor(int partition) {
        return this.mPartitions.get(partition).cursor;
    }

    public void changeCursor(int partition, Cursor cursor) {
        Cursor prevCursor = this.mPartitions.get(partition).cursor;
        if (prevCursor != cursor) {
            if (prevCursor != null && !prevCursor.isClosed()) {
                prevCursor.close();
            }
            this.mPartitions.get(partition).cursor = cursor;
            if (cursor != null && !cursor.isClosed()) {
                this.mPartitions.get(partition).idColumnIndex = cursor.getColumnIndex("_id");
            }
            invalidate();
            notifyDataSetChanged();
        }
    }

    public boolean isPartitionEmpty(int partition) {
        Cursor cursor = this.mPartitions.get(partition).cursor;
        return cursor == null || cursor.isClosed() || cursor.getCount() == 0;
    }

    public int getPartitionForPosition(int position) {
        ensureCacheValid();
        int start = 0;
        int n = this.mPartitions.size();
        for (int i = 0; i < n; i++) {
            int end = this.mPartitions.get(i).count + start;
            if (position >= start && position < end) {
                return i;
            }
            start = end;
        }
        return -1;
    }

    public int getOffsetInPartition(int position) {
        ensureCacheValid();
        int start = 0;
        for (Partition partition : this.mPartitions) {
            int end = partition.count + start;
            if (position >= start && position < end) {
                int offset = position - start;
                if (partition.hasHeader) {
                    return offset - 1;
                }
                return offset;
            }
            start = end;
        }
        return -1;
    }

    public int getPositionForPartition(int partition) {
        ensureCacheValid();
        int position = 0;
        for (int i = 0; i < partition; i++) {
            position += this.mPartitions.get(i).count;
        }
        return position;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return getItemViewTypeCount() + 1;
    }

    public int getItemViewTypeCount() {
        return 1;
    }

    protected int getItemViewType(int partition, int position) {
        return 1;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int position) {
        ensureCacheValid();
        int start = 0;
        int n = this.mPartitions.size();
        for (int i = 0; i < n; i++) {
            int end = this.mPartitions.get(i).count + start;
            if (position >= start && position < end) {
                int offset = position - start;
                if (this.mPartitions.get(i).hasHeader) {
                    offset--;
                }
                if (offset == -1) {
                    return -1;
                }
                return getItemViewType(i, offset);
            }
            start = end;
        }
        throw new ArrayIndexOutOfBoundsException(position);
    }

    @Override // android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ensureCacheValid();
        int start = 0;
        int n = this.mPartitions.size();
        for (int i = 0; i < n; i++) {
            int end = start + this.mPartitions.get(i).count;
            if (position >= start && position < end) {
                int offset = position - start;
                int offset2 = !this.mPartitions.get(i).hasHeader ? offset : offset - 1;
                if (offset2 == -1) {
                    view = getHeaderView(i, this.mPartitions.get(i).cursor, convertView, parent);
                } else {
                    if (!this.mPartitions.get(i).cursor.moveToPosition(offset2)) {
                        throw new IllegalStateException("Couldn't move cursor to position " + offset2);
                    }
                    view = getView(i, this.mPartitions.get(i).cursor, offset2, convertView, parent);
                }
                if (view == null) {
                    throw new NullPointerException("View should not be null, partition: " + i + " position: " + offset2);
                }
                return view;
            }
            start = end;
        }
        throw new ArrayIndexOutOfBoundsException(position);
    }

    protected View getHeaderView(int partition, Cursor cursor, View convertView, ViewGroup parent) {
        View view;
        if (convertView != null) {
            view = convertView;
        } else {
            view = newHeaderView(this.mContext, partition, cursor, parent);
        }
        bindHeaderView(view, partition, cursor);
        return view;
    }

    protected View newHeaderView(Context context, int partition, Cursor cursor, ViewGroup parent) {
        return null;
    }

    protected void bindHeaderView(View view, int partition, Cursor cursor) {
    }

    protected View getView(int partition, Cursor cursor, int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView != null) {
            view = convertView;
        } else {
            view = newView(this.mContext, partition, cursor, position, parent);
        }
        bindView(view, partition, cursor, position);
        return view;
    }

    @Override // android.widget.Adapter
    public Object getItem(int position) {
        Cursor cursor;
        ensureCacheValid();
        int start = 0;
        for (Partition mPartition : this.mPartitions) {
            int end = mPartition.count + start;
            if (position >= start && position < end) {
                int offset = position - start;
                if (mPartition.hasHeader) {
                    offset--;
                }
                if (offset == -1 || (cursor = mPartition.cursor) == null || cursor.isClosed() || !cursor.moveToPosition(offset)) {
                    return null;
                }
                return cursor;
            }
            start = end;
        }
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        Cursor cursor;
        ensureCacheValid();
        int start = 0;
        for (Partition mPartition : this.mPartitions) {
            int end = mPartition.count + start;
            if (position >= start && position < end) {
                int offset = position - start;
                if (mPartition.hasHeader) {
                    offset--;
                }
                if (offset == -1 || mPartition.idColumnIndex == -1 || (cursor = mPartition.cursor) == null || cursor.isClosed() || !cursor.moveToPosition(offset)) {
                    return 0L;
                }
                return cursor.getLong(mPartition.idColumnIndex);
            }
            start = end;
        }
        return 0L;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean areAllItemsEnabled() {
        for (Partition mPartition : this.mPartitions) {
            if (mPartition.hasHeader) {
                return false;
            }
        }
        return true;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean isEnabled(int position) {
        ensureCacheValid();
        int start = 0;
        int n = this.mPartitions.size();
        for (int i = 0; i < n; i++) {
            int end = this.mPartitions.get(i).count + start;
            if (position >= start && position < end) {
                int offset = position - start;
                if (this.mPartitions.get(i).hasHeader && offset == 0) {
                    return false;
                }
                return isEnabled(i, offset);
            }
            start = end;
        }
        return false;
    }

    protected boolean isEnabled(int partition, int position) {
        return true;
    }

    public void setNotificationsEnabled(boolean flag) {
        this.mNotificationsEnabled = flag;
        if (flag && this.mNotificationNeeded) {
            notifyDataSetChanged();
        }
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        if (this.mNotificationsEnabled) {
            this.mNotificationNeeded = false;
            super.notifyDataSetChanged();
        } else {
            this.mNotificationNeeded = true;
        }
    }
}
