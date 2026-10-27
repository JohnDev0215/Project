package com.amazon.mp3.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.adapter.RecentStationsRecyclerViewAdapter;
import com.amazon.mp3.search.model.Station;
import java.util.Collection;

/* JADX INFO: loaded from: classes4.dex */
public class RecentStationsHorizontalListView extends LinearLayout {
    private final RecentStationsRecyclerViewAdapter recentStationsAdapter;

    public RecentStationsHorizontalListView(final Context context, final int artworkSize, final RecentStationsRecyclerViewAdapter.ItemButtonListener recentStationOverflowButtonListener, final RecentStationsRecyclerViewAdapter.ItemButtonListener recentClickListener, final View.OnCreateContextMenuListener recentStationOnCreateContextMenuListener) {
        super(context);
        setOrientation(1);
        setPadding(0, getResources().getDimensionPixelSize(R.dimen.recents_tab_top_spacer_height), 0, 0);
        setBackgroundColor(getResources().getColor(R.color.transparent));
        LayoutInflater.from(context).inflate(R.layout.recent_stations_list_view_content, this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recent_stations_list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        RecentStationsRecyclerViewAdapter recentStationsRecyclerViewAdapter = new RecentStationsRecyclerViewAdapter(artworkSize, R.layout.recent_stations_grid_tile, recentStationOverflowButtonListener, recentClickListener, recentStationOnCreateContextMenuListener);
        this.recentStationsAdapter = recentStationsRecyclerViewAdapter;
        recyclerView.setAdapter(recentStationsRecyclerViewAdapter);
        setRecentStationsVisibility(8);
    }

    public void setRecentStationsVisibility(final int visibility) {
        if (getVisibility() == visibility) {
            return;
        }
        RecentStationsRecyclerViewAdapter recentStationsRecyclerViewAdapter = this.recentStationsAdapter;
        if ((recentStationsRecyclerViewAdapter == null || recentStationsRecyclerViewAdapter.getItemCount() == 0) && visibility == 0) {
            return;
        }
        setVisibility(visibility);
    }

    public void setStations(@NonNull final Collection<? extends Station> collection) {
        this.recentStationsAdapter.setStations(collection);
    }

    public Station getStation(final int position) {
        return this.recentStationsAdapter.getItem(position);
    }

    public void notifyDataSetChanged() {
        this.recentStationsAdapter.notifyDataSetChanged();
    }
}
