package com.amazon.mp3.library.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.actionbar.view.ActionBarView;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.util.SuggestedSongsViewModel;
import com.amazon.mp3.recentlyplayed.RecentTracksRecyclerViewAdapter;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.music.events.NavigationAppEvent;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class CuratePlaylistFragment extends Fragment {
    private static final String TAG = CuratePlaylistFragment.class.getSimpleName();
    private ActionBarView mActionBarTextAndPlayerView;
    private String mId;
    private String mName;
    private SuggestedSongsViewModel mSuggestedSongsViewModel;

    public static Builder create() {
        return new Builder();
    }

    public static class Builder {
        private String mId;
        private String mName;

        private Builder() {
        }

        public Builder withId(@NonNull final String id) {
            this.mId = id;
            return this;
        }

        public Builder withName(@NonNull final String name) {
            this.mName = name;
            return this;
        }

        public CuratePlaylistFragment build() {
            Bundle bundle = new Bundle();
            bundle.putString("id", this.mId);
            bundle.putString("name", this.mName);
            CuratePlaylistFragment curatePlaylistFragment = new CuratePlaylistFragment();
            curatePlaylistFragment.setArguments(bundle);
            return curatePlaylistFragment;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle instanceState) {
        super.onCreate(instanceState);
        Bundle arguments = getArguments();
        this.mId = arguments.getString("id");
        this.mName = arguments.getString("name");
        if (this.mSuggestedSongsViewModel == null) {
            this.mSuggestedSongsViewModel = (SuggestedSongsViewModel) ViewModelProviders.of(this).get(SuggestedSongsViewModel.class);
        }
        this.mSuggestedSongsViewModel.setPlaylistId(this.mId);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        if (appCompatActivity == null) {
            Log.warning(TAG, "Can't show fragment because activity is null");
            return null;
        }
        View viewInflate = View.inflate(appCompatActivity, R.layout.curate_playlist_page, null);
        this.mActionBarTextAndPlayerView = new ActionBarView(appCompatActivity, null);
        ((MusicHomeActivity) getActivity()).setHeaderView(this.mActionBarTextAndPlayerView);
        ((MusicHomeActivity) getActivity()).requestHomeButtonAsBack();
        setHasOptionsMenu(false);
        this.mActionBarTextAndPlayerView.setTitles(this.mName, null);
        setTrackCountText(0);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R.id.recentTracksRecyclerView);
        recyclerView.setAdapter(new RecentTracksRecyclerViewAdapter(appCompatActivity, this.mSuggestedSongsViewModel));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setVisibility(0);
        this.mSuggestedSongsViewModel.getAddedTracks().observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.mp3.library.fragment.CuratePlaylistFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$onCreateView$0((List) obj);
            }
        });
        setRetainInstance(true);
        NavigationAppEvent.builder("createPlaylist").withOrientation(ScreenUtil.getScreenOrientation(getContext())).publish();
        return viewInflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateView$0(List list) {
        setTrackCountText(list.size());
    }

    private void setTrackCountText(int count) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        this.mActionBarTextAndPlayerView.setSubtitle(String.format(context.getResources().getQuantityText(R.plurals.dmusic_library_songs_count, count).toString(), Integer.valueOf(count)));
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        if (getActivity() != null && !getActivity().isChangingConfigurations()) {
            this.mSuggestedSongsViewModel.saveAndClear();
        }
        super.onDestroyView();
    }
}
