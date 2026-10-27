package com.android.car.developeroptions.datetime.timezone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.InstrumentedFragment;
import com.android.car.developeroptions.datetime.timezone.BaseTimeZoneAdapter;
import com.android.car.developeroptions.datetime.timezone.model.TimeZoneData;
import com.android.car.developeroptions.datetime.timezone.model.TimeZoneDataLoader;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseTimeZonePicker extends InstrumentedFragment implements SearchView.OnQueryTextListener {
    private BaseTimeZoneAdapter mAdapter;
    private final boolean mDefaultExpandSearch;
    private RecyclerView mRecyclerView;
    private final boolean mSearchEnabled;
    private final int mSearchHintResId;
    private SearchView mSearchView;
    private TimeZoneData mTimeZoneData;
    private final int mTitleResId;

    public interface OnListItemClickListener<T extends BaseTimeZoneAdapter.AdapterItem> {
        void onListItemClick(T t);
    }

    protected abstract BaseTimeZoneAdapter createAdapter(TimeZoneData timeZoneData);

    @Override // android.widget.SearchView.OnQueryTextListener
    public boolean onQueryTextSubmit(String str) {
        return false;
    }

    protected BaseTimeZonePicker(int i, int i2, boolean z, boolean z2) {
        this.mTitleResId = i;
        this.mSearchHintResId = i2;
        this.mSearchEnabled = z;
        this.mDefaultExpandSearch = z2;
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        getActivity().setTitle(this.mTitleResId);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.time_zone_items_list, viewGroup, false);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R.id.recycler_view);
        this.mRecyclerView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        this.mRecyclerView.setAdapter(this.mAdapter);
        getLoaderManager().initLoader(0, null, new TimeZoneDataLoader.LoaderCreator(getContext(), new TimeZoneDataLoader.OnDataReadyCallback() { // from class: com.android.car.developeroptions.datetime.timezone.-$$Lambda$t-M8SpCBcpqsFemULVC-qq6vOyY
            @Override // com.android.car.developeroptions.datetime.timezone.model.TimeZoneDataLoader.OnDataReadyCallback
            public final void onTimeZoneDataReady(TimeZoneData timeZoneData) {
                this.f$0.onTimeZoneDataReady(timeZoneData);
            }
        }));
        return viewInflate;
    }

    public void onTimeZoneDataReady(TimeZoneData timeZoneData) {
        if (this.mTimeZoneData != null || timeZoneData == null) {
            return;
        }
        this.mTimeZoneData = timeZoneData;
        BaseTimeZoneAdapter baseTimeZoneAdapterCreateAdapter = createAdapter(timeZoneData);
        this.mAdapter = baseTimeZoneAdapterCreateAdapter;
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.setAdapter(baseTimeZoneAdapterCreateAdapter);
        }
    }

    protected Locale getLocale() {
        return getContext().getResources().getConfiguration().getLocales().get(0);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (this.mSearchEnabled) {
            menuInflater.inflate(R.menu.time_zone_base_search_menu, menu);
            MenuItem menuItemFindItem = menu.findItem(R.id.time_zone_search_menu);
            SearchView searchView = (SearchView) menuItemFindItem.getActionView();
            this.mSearchView = searchView;
            searchView.setQueryHint(getText(this.mSearchHintResId));
            this.mSearchView.setOnQueryTextListener(this);
            if (this.mDefaultExpandSearch) {
                menuItemFindItem.expandActionView();
                this.mSearchView.setIconified(false);
                this.mSearchView.setActivated(true);
                this.mSearchView.setQuery("", true);
            }
            TextView textView = (TextView) this.mSearchView.findViewById(android.R.id.outsideOverlay);
            textView.setPadding(0, textView.getPaddingTop(), 0, textView.getPaddingBottom());
            View viewFindViewById = this.mSearchView.findViewById(android.R.id.orientation);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewFindViewById.getLayoutParams();
            layoutParams.setMarginStart(0);
            layoutParams.setMarginEnd(0);
            viewFindViewById.setLayoutParams(layoutParams);
        }
    }

    @Override // android.widget.SearchView.OnQueryTextListener
    public boolean onQueryTextChange(String str) {
        BaseTimeZoneAdapter baseTimeZoneAdapter = this.mAdapter;
        if (baseTimeZoneAdapter == null) {
            return false;
        }
        baseTimeZoneAdapter.getFilter().filter(str);
        return false;
    }
}
