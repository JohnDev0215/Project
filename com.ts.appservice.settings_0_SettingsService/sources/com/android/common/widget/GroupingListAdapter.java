package com.android.common.widget;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/* JADX INFO: loaded from: classes.dex */
public abstract class GroupingListAdapter extends BaseAdapter {
    private static final long EXPANDED_GROUP_MASK = Long.MIN_VALUE;
    private static final int GROUP_METADATA_ARRAY_INCREMENT = 128;
    private static final int GROUP_METADATA_ARRAY_INITIAL_SIZE = 16;
    private static final long GROUP_OFFSET_MASK = 4294967295L;
    private static final long GROUP_SIZE_MASK = 9223372032559808512L;
    public static final int ITEM_TYPE_GROUP_HEADER = 1;
    public static final int ITEM_TYPE_IN_GROUP = 2;
    public static final int ITEM_TYPE_STANDALONE = 0;
    private Context mContext;
    private int mCount;
    private Cursor mCursor;
    private int mGroupCount;
    private long[] mGroupMetadata;
    private int mLastCachedCursorPosition;
    private int mLastCachedGroup;
    private int mLastCachedListPosition;
    private int mRowIdColumnIndex;
    private SparseIntArray mPositionCache = new SparseIntArray();
    private PositionMetadata mPositionMetadata = new PositionMetadata();
    protected ContentObserver mChangeObserver = new ContentObserver(new Handler()) { // from class: com.android.common.widget.GroupingListAdapter.1
        @Override // android.database.ContentObserver
        public boolean deliverSelfNotifications() {
            return true;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange) {
            GroupingListAdapter.this.onContentChanged();
        }
    };
    protected DataSetObserver mDataSetObserver = new DataSetObserver() { // from class: com.android.common.widget.GroupingListAdapter.2
        @Override // android.database.DataSetObserver
        public void onChanged() {
            GroupingListAdapter.this.notifyDataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            GroupingListAdapter.this.notifyDataSetInvalidated();
        }
    };

    protected abstract void addGroups(Cursor cursor);

    protected abstract void bindChildView(View view, Context context, Cursor cursor);

    protected abstract void bindGroupView(View view, Context context, Cursor cursor, int i, boolean z);

    protected abstract void bindStandAloneView(View view, Context context, Cursor cursor);

    protected abstract View newChildView(Context context, ViewGroup viewGroup);

    protected abstract View newGroupView(Context context, ViewGroup viewGroup);

    protected abstract View newStandAloneView(Context context, ViewGroup viewGroup);

    protected static class PositionMetadata {
        int childCount;
        int cursorPosition;
        private int groupPosition;
        boolean isExpanded;
        int itemType;
        private int listPosition = -1;

        protected PositionMetadata() {
        }
    }

    public GroupingListAdapter(Context context) {
        this.mContext = context;
        resetCache();
    }

    private void resetCache() {
        this.mCount = -1;
        this.mLastCachedListPosition = -1;
        this.mLastCachedCursorPosition = -1;
        this.mLastCachedGroup = -1;
        this.mPositionMetadata.listPosition = -1;
        this.mPositionCache.clear();
    }

    protected void onContentChanged() {
    }

    public void changeCursor(Cursor cursor) {
        Cursor cursor2 = this.mCursor;
        if (cursor == cursor2) {
            return;
        }
        if (cursor2 != null) {
            cursor2.unregisterContentObserver(this.mChangeObserver);
            this.mCursor.unregisterDataSetObserver(this.mDataSetObserver);
            this.mCursor.close();
        }
        this.mCursor = cursor;
        resetCache();
        findGroups();
        if (cursor != null) {
            cursor.registerContentObserver(this.mChangeObserver);
            cursor.registerDataSetObserver(this.mDataSetObserver);
            this.mRowIdColumnIndex = cursor.getColumnIndexOrThrow("_id");
            notifyDataSetChanged();
            return;
        }
        notifyDataSetInvalidated();
    }

    public Cursor getCursor() {
        return this.mCursor;
    }

    private void findGroups() {
        this.mGroupCount = 0;
        this.mGroupMetadata = new long[16];
        Cursor cursor = this.mCursor;
        if (cursor == null) {
            return;
        }
        addGroups(cursor);
    }

    protected void addGroup(int cursorPosition, int size, boolean expanded) {
        int i = this.mGroupCount;
        long[] jArr = this.mGroupMetadata;
        if (i >= jArr.length) {
            int newSize = idealLongArraySize(jArr.length + 128);
            long[] array = new long[newSize];
            System.arraycopy(this.mGroupMetadata, 0, array, 0, this.mGroupCount);
            this.mGroupMetadata = array;
        }
        long metadata = (((long) size) << 32) | ((long) cursorPosition);
        if (expanded) {
            metadata |= EXPANDED_GROUP_MASK;
        }
        long[] jArr2 = this.mGroupMetadata;
        int i2 = this.mGroupCount;
        this.mGroupCount = i2 + 1;
        jArr2[i2] = metadata;
    }

    private int idealLongArraySize(int need) {
        return idealByteArraySize(need * 8) / 8;
    }

    private int idealByteArraySize(int need) {
        for (int i = 4; i < 32; i++) {
            if (need <= (1 << i) - 12) {
                return (1 << i) - 12;
            }
        }
        return need;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.mCursor == null) {
            return 0;
        }
        int i = this.mCount;
        if (i != -1) {
            return i;
        }
        int cursorPosition = 0;
        int count = 0;
        for (int i2 = 0; i2 < this.mGroupCount; i2++) {
            long metadata = this.mGroupMetadata[i2];
            int offset = (int) (GROUP_OFFSET_MASK & metadata);
            boolean expanded = (EXPANDED_GROUP_MASK & metadata) != 0;
            int size = (int) ((GROUP_SIZE_MASK & metadata) >> 32);
            int count2 = count + (offset - cursorPosition);
            if (expanded) {
                count = count2 + size + 1;
            } else {
                count = count2 + 1;
            }
            cursorPosition = offset + size;
        }
        int count3 = (this.mCursor.getCount() + count) - cursorPosition;
        this.mCount = count3;
        return count3;
    }

    public void obtainPositionMetadata(PositionMetadata metadata, int position) {
        if (metadata.listPosition == position) {
            return;
        }
        int listPosition = 0;
        int cursorPosition = 0;
        int firstGroupToCheck = 0;
        int i = this.mLastCachedListPosition;
        if (i != -1) {
            if (position <= i) {
                int index = this.mPositionCache.indexOfKey(position);
                if (index < 0 && (~index) - 1 >= this.mPositionCache.size()) {
                    index--;
                }
                if (index >= 0) {
                    listPosition = this.mPositionCache.keyAt(index);
                    firstGroupToCheck = this.mPositionCache.valueAt(index);
                    long descriptor = this.mGroupMetadata[firstGroupToCheck];
                    cursorPosition = (int) (descriptor & GROUP_OFFSET_MASK);
                }
            } else {
                firstGroupToCheck = this.mLastCachedGroup;
                listPosition = this.mLastCachedListPosition;
                cursorPosition = this.mLastCachedCursorPosition;
            }
        }
        int i2 = firstGroupToCheck;
        while (true) {
            if (i2 < this.mGroupCount) {
                long group = this.mGroupMetadata[i2];
                int offset = (int) (group & GROUP_OFFSET_MASK);
                int listPosition2 = listPosition + (offset - cursorPosition);
                if (i2 > this.mLastCachedGroup) {
                    this.mPositionCache.append(listPosition2, i2);
                    this.mLastCachedListPosition = listPosition2;
                    this.mLastCachedCursorPosition = offset;
                    this.mLastCachedGroup = i2;
                }
                if (position < listPosition2) {
                    metadata.itemType = 0;
                    metadata.cursorPosition = offset - (listPosition2 - position);
                    return;
                }
                boolean expanded = (EXPANDED_GROUP_MASK & group) != 0;
                int size = (int) ((GROUP_SIZE_MASK & group) >> 32);
                if (position == listPosition2) {
                    metadata.itemType = 1;
                    metadata.groupPosition = i2;
                    metadata.isExpanded = expanded;
                    metadata.childCount = size;
                    metadata.cursorPosition = offset;
                    return;
                }
                if (expanded) {
                    if (position < listPosition2 + size + 1) {
                        metadata.itemType = 2;
                        metadata.cursorPosition = ((position - listPosition2) + offset) - 1;
                        return;
                    }
                    listPosition = listPosition2 + size + 1;
                } else {
                    listPosition = listPosition2 + 1;
                }
                cursorPosition = offset + size;
                i2++;
            } else {
                metadata.itemType = 0;
                metadata.cursorPosition = (position - listPosition) + cursorPosition;
                return;
            }
        }
    }

    public boolean isGroupHeader(int position) {
        obtainPositionMetadata(this.mPositionMetadata, position);
        return this.mPositionMetadata.itemType == 1;
    }

    public int getGroupSize(int position) {
        obtainPositionMetadata(this.mPositionMetadata, position);
        return this.mPositionMetadata.childCount;
    }

    public void toggleGroup(int position) {
        obtainPositionMetadata(this.mPositionMetadata, position);
        if (this.mPositionMetadata.itemType != 1) {
            throw new IllegalArgumentException("Not a group at position " + position);
        }
        if (this.mPositionMetadata.isExpanded) {
            long[] jArr = this.mGroupMetadata;
            int i = this.mPositionMetadata.groupPosition;
            jArr[i] = jArr[i] & Long.MAX_VALUE;
        } else {
            long[] jArr2 = this.mGroupMetadata;
            int i2 = this.mPositionMetadata.groupPosition;
            jArr2[i2] = jArr2[i2] | EXPANDED_GROUP_MASK;
        }
        resetCache();
        notifyDataSetChanged();
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return 3;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int position) {
        obtainPositionMetadata(this.mPositionMetadata, position);
        return this.mPositionMetadata.itemType;
    }

    @Override // android.widget.Adapter
    public Object getItem(int position) {
        if (this.mCursor == null) {
            return null;
        }
        obtainPositionMetadata(this.mPositionMetadata, position);
        if (this.mCursor.moveToPosition(this.mPositionMetadata.cursorPosition)) {
            return this.mCursor;
        }
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        Object item = getItem(position);
        if (item != null) {
            return this.mCursor.getLong(this.mRowIdColumnIndex);
        }
        return -1L;
    }

    @Override // android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        obtainPositionMetadata(this.mPositionMetadata, position);
        View view = convertView;
        if (view == null) {
            int i = this.mPositionMetadata.itemType;
            if (i == 0) {
                view = newStandAloneView(this.mContext, parent);
            } else if (i != 1) {
                if (i == 2) {
                    view = newChildView(this.mContext, parent);
                }
            } else {
                view = newGroupView(this.mContext, parent);
            }
        }
        this.mCursor.moveToPosition(this.mPositionMetadata.cursorPosition);
        int i2 = this.mPositionMetadata.itemType;
        if (i2 == 0) {
            bindStandAloneView(view, this.mContext, this.mCursor);
        } else if (i2 != 1) {
            if (i2 == 2) {
                bindChildView(view, this.mContext, this.mCursor);
            }
        } else {
            bindGroupView(view, this.mContext, this.mCursor, this.mPositionMetadata.childCount, this.mPositionMetadata.isExpanded);
        }
        return view;
    }
}
