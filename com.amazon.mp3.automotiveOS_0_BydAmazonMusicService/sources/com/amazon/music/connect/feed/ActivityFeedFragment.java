package com.amazon.music.connect.feed;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.amazon.music.connect.R$color;
import com.amazon.music.connect.R$id;
import com.amazon.music.connect.R$layout;
import com.amazon.music.connect.R$string;
import com.amazon.music.connect.feed.FeedEvent;

/* JADX INFO: loaded from: classes4.dex */
public class ActivityFeedFragment extends Fragment {
    private FeedViewModel feedViewModel;
    private RecyclerView recyclerView;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        final View viewInflate = View.inflate(getActivity(), R$layout.activity_feed, null);
        this.feedViewModel = (FeedViewModel) ViewModelProviders.of(getActivity()).get(FeedViewModel.class);
        final FeedItemAdapter feedItemAdapter = new FeedItemAdapter(this.feedViewModel);
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) viewInflate.findViewById(R$id.swipe_container);
        if (getActivity() == null) {
            return null;
        }
        this.feedViewModel.getFeedItemsLiveData().observe(this, new Observer<PagedList<FeedItem>>() { // from class: com.amazon.music.connect.feed.ActivityFeedFragment.1
            @Override // androidx.lifecycle.Observer
            public void onChanged(PagedList<FeedItem> pagedList) {
                TextView textView = (TextView) viewInflate.findViewById(R$id.no_items_text);
                if (!ActivityFeedFragment.this.feedViewModel.artistFollowSupported) {
                    textView.setText(ActivityFeedFragment.this.getResources().getString(R$string.activity_feed_empty_text_alt));
                }
                if (pagedList.size() > 0) {
                    textView.setVisibility(8);
                    ActivityFeedFragment.this.getActivity().getSharedPreferences("device_cache_shared_preferences", 0).edit().putString("com.amazon.mp3.userdefaults.connect.lastSeenActivityFeedItemId", pagedList.get(0).id).commit();
                } else {
                    textView.setVisibility(0);
                }
                feedItemAdapter.submitList(pagedList);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R$id.list_view);
        this.recyclerView = recyclerView;
        recyclerView.setAdapter(feedItemAdapter);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(linearLayoutManager);
        SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() { // from class: com.amazon.music.connect.feed.ActivityFeedFragment.2
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public void onRefresh() {
                ActivityFeedFragment.this.checkInternetConnection();
                ActivityFeedFragment.this.feedViewModel.refresh();
            }
        };
        swipeRefreshLayout.setColorSchemeResources(R$color.white);
        swipeRefreshLayout.setEnabled(true);
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R$color.glass));
        swipeRefreshLayout.setRefreshing(true);
        checkInternetConnection();
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.feedViewModel.getFeedEventLiveData().postValue(new FeedEvent(FeedEvent.Type.FRAGMENT_RESUMED, null, null));
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        FeedMetrics.emitPageViewMetric();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView != null) {
            recyclerView.setAdapter(null);
        }
        this.feedViewModel.getFeedEventLiveData().postValue(new FeedEvent(FeedEvent.Type.FRAGMENT_DESTROYED, null, null));
        this.feedViewModel = null;
        super.onDestroyView();
    }

    public void checkInternetConnection() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getActivity().getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) {
            return;
        }
        Toast.makeText(getActivity(), getResources().getString(R$string.disconnected), 0).show();
    }
}
