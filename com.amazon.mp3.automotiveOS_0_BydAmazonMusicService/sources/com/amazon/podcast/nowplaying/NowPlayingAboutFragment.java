package com.amazon.podcast.nowplaying;

import Podcast.PlaybackInterface.v1_0.MediaMetadataElement;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.amazon.podcast.NavigationHandler;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$color;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.R$string;
import com.amazon.podcast.deeplinks.Deeplinks;
import com.amazon.podcast.media.playback.Media;
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
    private TextView title;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R$layout.podcast_now_playing_about_fragment, container, false);
        this.resources = getResources();
        TextView textView = (TextView) viewInflate.findViewById(R$id.title);
        this.title = textView;
        textView.setTypeface(Podcast.getRuntimeStyleSheet().getHeadline4TypeFace());
        this.description = (EmberTextView) viewInflate.findViewById(R$id.description);
        Podcast.getPlayback().getMedia().addMediaMetadataCallback(this);
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
        bindTitle(metadataElement);
        if (metadataElement == null || StringUtils.isBlank(metadataElement.getDescription())) {
            String string = this.resources.getString(R$string.podcast_description_placeholder_text);
            EmberTextView emberTextView = this.description;
            emberTextView.setText(Strings.convertToSpannable(string, emberTextView));
            return;
        }
        this.description.setText(Strings.convertToSpannable(metadataElement.getDescription(), this.description));
    }

    private void bindTitle(final MediaMetadataElement metadataElement) {
        if (metadataElement == null || StringUtils.isBlank(metadataElement.getSubtitle())) {
            return;
        }
        String string = this.resources.getString(R$string.podcast_now_playing_title_text, metadataElement.getSubtitle());
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(this.resources.getColor(R$color.primary));
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
        spannableStringBuilder.setSpan(foregroundColorSpan, string.indexOf(metadataElement.getSubtitle()), string.length(), 18);
        this.title.setText(spannableStringBuilder);
        this.title.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplaying.NowPlayingAboutFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$bindTitle$0(metadataElement, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$bindTitle$0(MediaMetadataElement mediaMetadataElement, View view) {
        new NavigationHandler(getContext()).navigateToDeeplink(Deeplinks.podcast(mediaMetadataElement.getMediaCollectionId(), mediaMetadataElement.getTitle()));
    }
}
