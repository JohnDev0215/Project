package com.google.android.exoplayer2.source.hls.playlist;

import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public final class HlsMediaPlaylist extends HlsPlaylist {
    public final int discontinuitySequence;
    public final long durationUs;
    public final boolean hasDiscontinuitySequence;
    public final boolean hasEndTag;
    public final boolean hasIndependentSegmentsTag;
    public final boolean hasProgramDateTime;
    public final Segment initializationSegment;
    public final int mediaSequence;
    public final int playlistType;
    public final List<Segment> segments;
    public final long startOffsetUs;
    public final long startTimeUs;
    public final long targetDurationUs;
    public final int version;

    public static final class Segment implements Comparable<Long> {
        public final long byterangeLength;
        public final long byterangeOffset;
        public final long durationUs;
        public final String encryptionIV;
        public final String encryptionKeyUri;
        public final boolean isEncrypted;
        public final int relativeDiscontinuitySequence;
        public final long relativeStartTimeUs;
        public final String url;

        public Segment(String str, long j, long j2) {
            this(str, 0L, -1, -9223372036854775807L, false, null, null, j, j2);
        }

        public Segment(String str, long j, int i, long j2, boolean z, String str2, String str3, long j3, long j4) {
            this.url = str;
            this.durationUs = j;
            this.relativeDiscontinuitySequence = i;
            this.relativeStartTimeUs = j2;
            this.isEncrypted = z;
            this.encryptionKeyUri = str2;
            this.encryptionIV = str3;
            this.byterangeOffset = j3;
            this.byterangeLength = j4;
        }

        @Override // java.lang.Comparable
        public int compareTo(Long l) {
            if (this.relativeStartTimeUs > l.longValue()) {
                return 1;
            }
            return this.relativeStartTimeUs < l.longValue() ? -1 : 0;
        }
    }

    public HlsMediaPlaylist(int i, String str, List<String> list, long j, long j2, boolean z, int i2, int i3, int i4, long j3, boolean z2, boolean z3, boolean z4, Segment segment, List<Segment> list2) {
        long j4;
        super(str, list);
        this.playlistType = i;
        this.startTimeUs = j2;
        this.hasDiscontinuitySequence = z;
        this.discontinuitySequence = i2;
        this.mediaSequence = i3;
        this.version = i4;
        this.targetDurationUs = j3;
        this.hasIndependentSegmentsTag = z2;
        this.hasEndTag = z3;
        this.hasProgramDateTime = z4;
        this.initializationSegment = segment;
        this.segments = Collections.unmodifiableList(list2);
        if (!list2.isEmpty()) {
            Segment segment2 = list2.get(list2.size() - 1);
            this.durationUs = segment2.relativeStartTimeUs + segment2.durationUs;
        } else {
            this.durationUs = 0L;
        }
        if (j == -9223372036854775807L) {
            j4 = -9223372036854775807L;
        } else {
            j4 = j >= 0 ? j : this.durationUs + j;
        }
        this.startOffsetUs = j4;
    }

    public boolean isNewerThan(HlsMediaPlaylist hlsMediaPlaylist) {
        int i;
        int i2;
        if (hlsMediaPlaylist == null || (i = this.mediaSequence) > (i2 = hlsMediaPlaylist.mediaSequence)) {
            return true;
        }
        if (i < i2) {
            return false;
        }
        int size = this.segments.size();
        int size2 = hlsMediaPlaylist.segments.size();
        if (size <= size2) {
            return size == size2 && this.hasEndTag && !hlsMediaPlaylist.hasEndTag;
        }
        return true;
    }

    public long getEndTimeUs() {
        return this.startTimeUs + this.durationUs;
    }

    public HlsMediaPlaylist copyWith(long j, int i) {
        return new HlsMediaPlaylist(this.playlistType, this.baseUri, this.tags, this.startOffsetUs, j, true, i, this.mediaSequence, this.version, this.targetDurationUs, this.hasIndependentSegmentsTag, this.hasEndTag, this.hasProgramDateTime, this.initializationSegment, this.segments);
    }

    public HlsMediaPlaylist copyWithEndTag() {
        return this.hasEndTag ? this : new HlsMediaPlaylist(this.playlistType, this.baseUri, this.tags, this.startOffsetUs, this.startTimeUs, this.hasDiscontinuitySequence, this.discontinuitySequence, this.mediaSequence, this.version, this.targetDurationUs, this.hasIndependentSegmentsTag, true, this.hasProgramDateTime, this.initializationSegment, this.segments);
    }
}
