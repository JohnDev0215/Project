package com.google.android.exoplayer2.source.hls.playlist;

import com.google.android.exoplayer2.Format;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public final class HlsMasterPlaylist extends HlsPlaylist {
    public final List<HlsUrl> audios;
    public final Format muxedAudioFormat;
    public final List<Format> muxedCaptionFormats;
    public final List<HlsUrl> subtitles;
    public final List<HlsUrl> variants;

    public static final class HlsUrl {
        public final Format format;
        public final String url;

        public static HlsUrl createMediaPlaylistHlsUrl(String str) {
            return new HlsUrl(str, Format.createContainerFormat("0", "application/x-mpegURL", null, null, -1, 0, null));
        }

        public HlsUrl(String str, Format format) {
            this.url = str;
            this.format = format;
        }
    }

    public HlsMasterPlaylist(String str, List<String> list, List<HlsUrl> list2, List<HlsUrl> list3, List<HlsUrl> list4, Format format, List<Format> list5) {
        super(str, list);
        this.variants = Collections.unmodifiableList(list2);
        this.audios = Collections.unmodifiableList(list3);
        this.subtitles = Collections.unmodifiableList(list4);
        this.muxedAudioFormat = format;
        this.muxedCaptionFormats = list5 != null ? Collections.unmodifiableList(list5) : null;
    }

    public static HlsMasterPlaylist createSingleVariantMasterPlaylist(String str) {
        List listSingletonList = Collections.singletonList(HlsUrl.createMediaPlaylistHlsUrl(str));
        List listEmptyList = Collections.emptyList();
        return new HlsMasterPlaylist(null, Collections.emptyList(), listSingletonList, listEmptyList, listEmptyList, null, null);
    }
}
