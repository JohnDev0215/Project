package com.amazon.mp3.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer;
import com.amazon.mp3.library.adapter.TrackListAdapter;
import com.amazon.mp3.library.fragment.TrackListFragment;
import com.amazon.mp3.library.util.TrackUtil;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.view.ContentDisabledHelper;
import com.amazon.music.widget.eux.EUXConfiguration;
import com.amazon.music.widget.eux.adapters.EUXAdapter;
import com.amazon.music.widget.eux.adapters.EUXOfflineContentAdapter;
import com.amazon.music.widget.eux.adapters.EUXTracksAdapter;

/* JADX INFO: loaded from: classes3.dex */
public class SongListFragment extends TrackListFragment {
    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected View getEmptyView(EUXConfiguration euxConfiguration) {
        EUXAdapter eUXOfflineContentAdapter;
        if (this.mIsRemote) {
            eUXOfflineContentAdapter = new EUXTracksAdapter(getContext());
        } else {
            eUXOfflineContentAdapter = new EUXOfflineContentAdapter(getContext());
        }
        return eUXOfflineContentAdapter.getView(euxConfiguration);
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (BottomNavigationBarContainer.getInstance().isEnabled()) {
            BottomNavigationBarContainer.getInstance().setBottomBarTab(LastViewedScreenUtil.LastViewedScreen.LIBRARY);
        }
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateShuffleContainer(((TrackListAdapter) getAdapter()).getCursor());
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.service.job.JobSessionFragment
    protected void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
        super.onJobFinished(jobId, job, resultCode, resultBundle);
        updateShuffleContainer(((TrackListAdapter) getAdapter()).getCursor());
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected void onContentUriChanged() {
        super.onContentUriChanged();
        updateShuffleContainer(((TrackListAdapter) getAdapter()).getCursor());
    }

    protected void updateShuffleContainer(final Cursor cursor) {
        if (this.mShuffleContainer == null || cursor == null) {
            return;
        }
        boolean z = true;
        boolean z2 = !isEmpty() && TrackUtil.containsAtLeastOneAvailableTrack(cursor, this.mIsRemote);
        View view = this.mShuffleContainer;
        if (!z2 && !UserSubscriptionUtil.isNightwingOnly()) {
            z = false;
        }
        view.setEnabled(z);
        ContentDisabledHelper.setContentEnabled(this.mShuffleContainer, z2);
    }
}
