package com.amazon.mp3.view;

import android.content.Context;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.WrapperListAdapter;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public class HeaderGridView extends GridView {
    private ArrayList<FixedViewInfo> mHeaderViewInfos;

    @Override // android.view.ViewGroup
    public void setClipChildren(boolean clipChildren) {
    }

    private static class FixedViewInfo {
        public Object mData;
        public boolean mIsSelectable;
        public View mView;
        public ViewGroup mViewContainer;

        private FixedViewInfo() {
        }
    }

    private void initHeaderGridView() {
        super.setClipChildren(false);
    }

    public HeaderGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mHeaderViewInfos = new ArrayList<>();
        initHeaderGridView();
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        ListAdapter adapter = getAdapter();
        if (adapter == null || !(adapter instanceof HeaderViewGridAdapter)) {
            return;
        }
        ((HeaderViewGridAdapter) adapter).setNumColumns(getNumColumns());
    }

    public void addHeaderView(View v, Object data, boolean isSelectable, boolean ignoreHorizontalPadding) {
        ListAdapter adapter = getAdapter();
        if (adapter != null && !(adapter instanceof HeaderViewGridAdapter)) {
            throw new IllegalStateException("Cannot add header view to grid -- setAdapter has already been called.");
        }
        FixedViewInfo fixedViewInfo = new FixedViewInfo();
        FullWidthFixedViewLayout fullWidthFixedViewLayout = new FullWidthFixedViewLayout(getContext());
        fullWidthFixedViewLayout.addView(v);
        if (ignoreHorizontalPadding) {
            setupHeaderMargins(v);
        }
        fixedViewInfo.mView = v;
        fixedViewInfo.mViewContainer = fullWidthFixedViewLayout;
        fixedViewInfo.mData = data;
        fixedViewInfo.mIsSelectable = isSelectable;
        this.mHeaderViewInfos.add(fixedViewInfo);
        if (adapter != null) {
            ((HeaderViewGridAdapter) adapter).notifyDataSetChanged();
        }
    }

    private void setupHeaderMargins(View v) {
        setClipToPadding(false);
        int scrollBarSize = (int) (getScrollBarSize() * 0.5f);
        int paddingLeft = getPaddingLeft() + scrollBarSize;
        int paddingRight = getPaddingRight() + scrollBarSize;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
        marginLayoutParams.setMargins(-paddingLeft, marginLayoutParams.topMargin, -paddingRight, marginLayoutParams.bottomMargin);
        v.setLayoutParams(marginLayoutParams);
    }

    public int getHeaderViewCount() {
        return this.mHeaderViewInfos.size();
    }

    @Override // android.widget.AdapterView
    public void setAdapter(ListAdapter adapter) {
        if (this.mHeaderViewInfos.size() > 0) {
            HeaderViewGridAdapter headerViewGridAdapter = new HeaderViewGridAdapter(this.mHeaderViewInfos, adapter);
            int numColumns = getNumColumns();
            if (numColumns > 1) {
                headerViewGridAdapter.setNumColumns(numColumns);
            }
            super.setAdapter((ListAdapter) headerViewGridAdapter);
            return;
        }
        super.setAdapter(adapter);
    }

    private class FullWidthFixedViewLayout extends FrameLayout {
        public FullWidthFixedViewLayout(Context context) {
            super(context);
        }

        @Override // android.widget.FrameLayout, android.view.View
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec((HeaderGridView.this.getMeasuredWidth() - HeaderGridView.this.getPaddingLeft()) - HeaderGridView.this.getPaddingRight(), View.MeasureSpec.getMode(widthMeasureSpec)), heightMeasureSpec);
        }
    }

    private static class HeaderViewGridAdapter implements WrapperListAdapter, Filterable {
        private final ListAdapter mAdapter;
        boolean mAreAllFixedViewsSelectable;
        ArrayList<FixedViewInfo> mHeaderViewInfos;
        private final boolean mIsFilterable;
        private final DataSetObservable mDataSetObservable = new DataSetObservable();
        private int mNumColumns = 1;

        public HeaderViewGridAdapter(ArrayList<FixedViewInfo> headerViewInfos, ListAdapter adapter) {
            this.mAdapter = adapter;
            this.mIsFilterable = adapter instanceof Filterable;
            if (headerViewInfos == null) {
                throw new IllegalArgumentException("headerViewInfos cannot be null");
            }
            this.mHeaderViewInfos = headerViewInfos;
            this.mAreAllFixedViewsSelectable = areAllListInfosSelectable(headerViewInfos);
        }

        public int getHeadersCount() {
            return this.mHeaderViewInfos.size();
        }

        @Override // android.widget.Adapter
        public boolean isEmpty() {
            ListAdapter listAdapter = this.mAdapter;
            return (listAdapter == null || listAdapter.isEmpty()) && getHeadersCount() == 0;
        }

        public void setNumColumns(int numColumns) {
            if (numColumns < 1) {
                throw new IllegalArgumentException("Number of columns must be 1 or more");
            }
            if (this.mNumColumns != numColumns) {
                this.mNumColumns = numColumns;
                notifyDataSetChanged();
            }
        }

        private boolean areAllListInfosSelectable(ArrayList<FixedViewInfo> infos) {
            if (infos == null) {
                return true;
            }
            Iterator<FixedViewInfo> it = infos.iterator();
            while (it.hasNext()) {
                if (!it.next().mIsSelectable) {
                    return false;
                }
            }
            return true;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            if (this.mAdapter != null) {
                return (getHeadersCount() * this.mNumColumns) + this.mAdapter.getCount();
            }
            return getHeadersCount() * this.mNumColumns;
        }

        @Override // android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            ListAdapter listAdapter = this.mAdapter;
            if (listAdapter != null) {
                return this.mAreAllFixedViewsSelectable && listAdapter.areAllItemsEnabled();
            }
            return true;
        }

        @Override // android.widget.ListAdapter
        public boolean isEnabled(int position) {
            int headersCount = getHeadersCount();
            int i = this.mNumColumns;
            int i2 = headersCount * i;
            if (position < i2) {
                return position % i == 0 && this.mHeaderViewInfos.get(position / i).mIsSelectable;
            }
            int i3 = position - i2;
            ListAdapter listAdapter = this.mAdapter;
            if (listAdapter != null && i3 < listAdapter.getCount()) {
                return this.mAdapter.isEnabled(i3);
            }
            throw new ArrayIndexOutOfBoundsException(position);
        }

        @Override // android.widget.Adapter
        public Object getItem(int position) {
            int headersCount = getHeadersCount();
            int i = this.mNumColumns;
            int i2 = headersCount * i;
            if (position < i2) {
                if (position % i == 0) {
                    return this.mHeaderViewInfos.get(position / i).mData;
                }
                return null;
            }
            int i3 = position - i2;
            ListAdapter listAdapter = this.mAdapter;
            if (listAdapter != null && i3 < listAdapter.getCount()) {
                return this.mAdapter.getItem(i3);
            }
            throw new ArrayIndexOutOfBoundsException(position);
        }

        @Override // android.widget.Adapter
        public long getItemId(int position) {
            int i;
            int headersCount = getHeadersCount() * this.mNumColumns;
            ListAdapter listAdapter = this.mAdapter;
            if (listAdapter == null || position < headersCount || (i = position - headersCount) >= listAdapter.getCount()) {
                return -1L;
            }
            return this.mAdapter.getItemId(i);
        }

        @Override // android.widget.Adapter
        public boolean hasStableIds() {
            ListAdapter listAdapter = this.mAdapter;
            if (listAdapter != null) {
                return listAdapter.hasStableIds();
            }
            return false;
        }

        @Override // android.widget.Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            int headersCount = getHeadersCount();
            int i = this.mNumColumns;
            int i2 = headersCount * i;
            if (position < i2) {
                ViewGroup viewGroup = this.mHeaderViewInfos.get(position / i).mViewContainer;
                if (position % this.mNumColumns == 0) {
                    return viewGroup;
                }
                if (convertView == null) {
                    convertView = new View(parent.getContext());
                }
                convertView.setVisibility(4);
                convertView.setMinimumHeight(viewGroup.getHeight());
                return convertView;
            }
            int i3 = position - i2;
            ListAdapter listAdapter = this.mAdapter;
            if (listAdapter != null && i3 < listAdapter.getCount()) {
                return this.mAdapter.getView(i3, convertView, parent);
            }
            throw new ArrayIndexOutOfBoundsException(position);
        }

        @Override // android.widget.Adapter
        public int getItemViewType(int position) {
            int i;
            int headersCount = getHeadersCount();
            int i2 = this.mNumColumns;
            int i3 = headersCount * i2;
            if (position < i3 && position % i2 != 0) {
                ListAdapter listAdapter = this.mAdapter;
                if (listAdapter != null) {
                    return listAdapter.getViewTypeCount();
                }
                return 1;
            }
            ListAdapter listAdapter2 = this.mAdapter;
            if (listAdapter2 == null || position < i3 || (i = position - i3) >= listAdapter2.getCount()) {
                return -2;
            }
            return this.mAdapter.getItemViewType(i);
        }

        @Override // android.widget.Adapter
        public int getViewTypeCount() {
            ListAdapter listAdapter = this.mAdapter;
            if (listAdapter != null) {
                return listAdapter.getViewTypeCount() + 1;
            }
            return 2;
        }

        @Override // android.widget.Adapter
        public void registerDataSetObserver(DataSetObserver observer) {
            this.mDataSetObservable.registerObserver(observer);
            ListAdapter listAdapter = this.mAdapter;
            if (listAdapter != null) {
                listAdapter.registerDataSetObserver(observer);
            }
        }

        @Override // android.widget.Adapter
        public void unregisterDataSetObserver(DataSetObserver observer) {
            this.mDataSetObservable.unregisterObserver(observer);
            ListAdapter listAdapter = this.mAdapter;
            if (listAdapter != null) {
                listAdapter.unregisterDataSetObserver(observer);
            }
        }

        @Override // android.widget.Filterable
        public Filter getFilter() {
            if (this.mIsFilterable) {
                return ((Filterable) this.mAdapter).getFilter();
            }
            return null;
        }

        @Override // android.widget.WrapperListAdapter
        public ListAdapter getWrappedAdapter() {
            return this.mAdapter;
        }

        public void notifyDataSetChanged() {
            this.mDataSetObservable.notifyChanged();
        }
    }
}
