package com.amazon.podcast.nowplayingstage.overlays;

import Podcast.PlaybackInterface.v1_0.MediaMetadataElement;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.R$string;
import com.amazon.podcast.media.playback.Media;
import com.amazon.podcast.nowplayingstage.NowPlayingActivity;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.Strings;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class NowPlayingAboutFragment extends Fragment implements Media.MediaMetadataCallback {
    private static final Logger logger = LoggerFactory.getLogger("NowPlayingAboutFragment");
    private EmberTextView description;
    private Resources resources;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R$layout.podcast_now_playing_stage_about_fragment, container, false);
        this.resources = getResources();
        this.description = (EmberTextView) viewInflate.findViewById(R$id.description);
        Podcast.getPlayback().getMedia().addMediaMetadataCallback(this);
        final GestureDetector gestureDetector = new GestureDetector(getContext(), new OverlayGestureListener());
        viewInflate.setOnTouchListener(new View.OnTouchListener() { // from class: com.amazon.podcast.nowplayingstage.overlays.NowPlayingAboutFragment.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        Media media = Podcast.getPlayback().getMedia();
        bind(media.getMediaMetadataElement());
        media.addMediaMetadataCallback(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        Podcast.getPlayback().getMedia().removeMediaMetadataCallback(this);
    }

    @Override // com.amazon.podcast.media.playback.Media.MediaMetadataCallback
    public void onMediaMetadataChange(final MediaMetadataElement mediaMetadata) {
        bind(mediaMetadata);
    }

    private void bind(final MediaMetadataElement metadataElement) {
        if (metadataElement == null || StringUtils.isBlank(metadataElement.getDescription())) {
            String string = this.resources.getString(R$string.podcast_description_placeholder_text);
            EmberTextView emberTextView = this.description;
            emberTextView.setText(Strings.convertToSpannable(string, emberTextView));
            return;
        }
        this.description.setText(Strings.convertToSpannable(metadataElement.getDescription(), this.description));
    }

    private class OverlayGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        public OverlayGestureListener() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1 != null && e2 != null) {
                boolean isOverlayExpanded = ((NowPlayingActivity) NowPlayingAboutFragment.this.getActivity()).getIsOverlayExpanded();
                float y = e1.getY() - e2.getY();
                boolean z = y < 0.0f;
                if (!isOverlayExpanded && !z) {
                    if (y >= 10.0f && Math.abs(velocityY) > 200.0f) {
                        ((NowPlayingActivity) NowPlayingAboutFragment.this.getActivity()).setExpandedMode();
                    }
                    return true;
                }
            }
            return false;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onShowPress(MotionEvent e) {
            super.onShowPress(e);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent e) {
            return super.onDoubleTap(e);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTapEvent(MotionEvent e) {
            return super.onDoubleTapEvent(e);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return super.onSingleTapConfirmed(e);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnContextClickListener
        public boolean onContextClick(MotionEvent e) {
            return super.onContextClick(e);
        }
    }
}
