package com.amazon.podcast.cast;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.amazon.music.casting.CastingDevice;
import com.amazon.music.casting.CastingException;
import com.amazon.music.casting.session.CastingConnectionCallback;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.castingviews.CastingDeviceListView;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.podcast.metrics.UiMetricAttributes$DetailPageItemIdType;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observer;

/* JADX INFO: loaded from: classes5.dex */
public final class CastingDeviceList extends AppCompatActivity {
    private static final String TAG = "CastingDeviceList";
    private CastingDeviceListView castingDeviceListView;
    private LinearLayout castingListLayout;
    private CastingSessionManager castingManager;
    private FrameLayout frameLayout;
    private View loadingView;
    private Playback playback;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View volumeScrubberView;
    private SeekBar volumeSeekbar;
    private final Logger logger = LoggerFactory.getLogger(TAG);
    private boolean backPressed = true;
    private final CastingConnectionCallback callback = new CastingConnectionCallback() { // from class: com.amazon.podcast.cast.CastingDeviceList.1
        @Override // com.amazon.music.casting.session.CastingConnectionCallback
        public void onConnected(CastingDevice castingDevice) {
            CastingDeviceList.this.close();
            if (CastingDeviceList.this.volumeScrubberView != null) {
                CastingDeviceList.this.volumeScrubberView.setVisibility(0);
            }
        }

        @Override // com.amazon.music.casting.session.CastingConnectionCallback
        public void onDisconnected(TerminationReason terminationReason) {
            CastingDeviceList.this.close();
            if (CastingDeviceList.this.volumeScrubberView != null) {
                CastingDeviceList.this.volumeScrubberView.setVisibility(8);
            }
        }

        @Override // com.amazon.music.casting.session.CastingConnectionCallback
        public void onError(CastingException e) {
            CastingDeviceList.this.logger.debug("Casting connection failed with an exception:  " + e.getMessage());
        }
    };

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R$layout.pocast_casting_fragment);
        this.playback = Podcast.getPlayback();
        this.castingManager = CastingSessionManager.getInstance();
        this.swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R$id.swipeRefreshLayout);
        View viewFindViewById = findViewById(R$id.loading_spinner);
        this.loadingView = viewFindViewById;
        viewFindViewById.setVisibility(0);
        this.frameLayout = (FrameLayout) findViewById(R$id.frame);
        this.castingListLayout = (LinearLayout) findViewById(R$id.casting_list_layout);
        this.volumeScrubberView = findViewById(R$id.volumeScrubber);
        SeekBar seekBar = (SeekBar) findViewById(R$id.volumeSeekBar);
        this.volumeSeekbar = seekBar;
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.amazon.podcast.cast.CastingDeviceList.2
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar2, int i, boolean b) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar2) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar2) {
                int progress = seekBar2.getProgress();
                CastingDeviceList.this.castingManager.setVolume(progress);
                CastingDeviceList.this.logger.debug("Casting volume is set to:  " + progress);
            }
        });
        fetchView();
        this.castingManager.registerListener(this.callback);
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.amazon.podcast.cast.CastingDeviceList.3
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public void onRefresh() {
                CastingDeviceList.this.fetchView();
            }
        });
        dispatchOnViewMethods();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        this.frameLayout = null;
        this.loadingView = null;
        CastingDeviceListView castingDeviceListView = this.castingDeviceListView;
        if (castingDeviceListView != null) {
            castingDeviceListView.onDestroy();
            this.castingDeviceListView = null;
        }
        this.castingManager.unregisterListener(this.callback);
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fetchView() {
        CastingDeviceListView castingDeviceListViewCreateCastingDeviceListView = CastingViewFactory.createCastingDeviceListView(this);
        this.castingDeviceListView = castingDeviceListViewCreateCastingDeviceListView;
        if (castingDeviceListViewCreateCastingDeviceListView == null) {
            return;
        }
        castingDeviceListViewCreateCastingDeviceListView.getView(null).subscribe(new Observer<ViewGroup>() { // from class: com.amazon.podcast.cast.CastingDeviceList.4
            @Override // rx.Observer
            public void onCompleted() {
                CastingDeviceList.this.swipeRefreshLayout.setRefreshing(false);
            }

            @Override // rx.Observer
            public void onError(final Throwable throwable) {
                CastingDeviceList.this.swipeRefreshLayout.setRefreshing(false);
            }

            @Override // rx.Observer
            public void onNext(final ViewGroup view) {
                CastingDeviceList.this.swipeRefreshLayout.setRefreshing(false);
                CastingDeviceList.this.setupView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupView(final ViewGroup viewGroup) {
        FrameLayout frameLayout;
        if (viewGroup != null && (frameLayout = this.frameLayout) != null) {
            frameLayout.removeAllViews();
            this.frameLayout.addView(viewGroup, -1, -1);
            this.frameLayout.setVisibility(0);
            if (Casting.isCasting()) {
                this.volumeScrubberView.setVisibility(0);
            } else {
                this.volumeScrubberView.setVisibility(8);
            }
        }
        View view = this.loadingView;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void close() {
        this.backPressed = false;
        onBackPressed();
    }

    private void dispatchOnViewMethods() {
        ArrayList arrayList = new ArrayList();
        if (this.playback.getMedia() == null || this.playback.getMedia().mediaId() == null) {
            return;
        }
        String strMediaId = this.playback.getMedia().mediaId();
        arrayList.addAll(UiMetricMethods.onPageViewed(UiMetricAttributes$PageType.PODCASTS_CASTING_DEVICE_LIST, UiMetricAttributes$DetailPageItemIdType.PODCAST_EPISODE_ID, strMediaId));
        this.playback.dispatch(strMediaId, arrayList);
    }
}
