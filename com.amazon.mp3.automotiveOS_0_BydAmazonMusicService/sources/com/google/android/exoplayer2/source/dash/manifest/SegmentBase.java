package com.google.android.exoplayer2.source.dash.manifest;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.Util;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public abstract class SegmentBase {
    final RangedUri initialization;
    final long presentationTimeOffset;
    final long timescale;

    public SegmentBase(RangedUri rangedUri, long j, long j2) {
        this.initialization = rangedUri;
        this.timescale = j;
        this.presentationTimeOffset = j2;
    }

    public RangedUri getInitialization(Representation representation) {
        return this.initialization;
    }

    public long getPresentationTimeOffsetUs() {
        return Util.scaleLargeTimestamp(this.presentationTimeOffset, 1000000L, this.timescale);
    }

    public static class SingleSegmentBase extends SegmentBase {
        final long indexLength;
        final long indexStart;

        public SingleSegmentBase(RangedUri rangedUri, long j, long j2, long j3, long j4) {
            super(rangedUri, j, j2);
            this.indexStart = j3;
            this.indexLength = j4;
        }

        public SingleSegmentBase() {
            this(null, 1L, 0L, 0L, 0L);
        }

        public RangedUri getIndex() {
            long j = this.indexLength;
            if (j <= 0) {
                return null;
            }
            return new RangedUri(null, this.indexStart, j);
        }
    }

    public static abstract class MultiSegmentBase extends SegmentBase {
        final long duration;
        final List<SegmentTimelineElement> segmentTimeline;
        final int startNumber;

        public abstract int getSegmentCount(long j);

        public abstract RangedUri getSegmentUrl(Representation representation, int i);

        public MultiSegmentBase(RangedUri rangedUri, long j, long j2, int i, long j3, List<SegmentTimelineElement> list) {
            super(rangedUri, j, j2);
            this.startNumber = i;
            this.duration = j3;
            this.segmentTimeline = list;
        }

        public int getSegmentNum(long j, long j2) {
            int firstSegmentNum = getFirstSegmentNum();
            int segmentCount = getSegmentCount(j2);
            if (segmentCount == 0) {
                return firstSegmentNum;
            }
            if (this.segmentTimeline == null) {
                int i = this.startNumber + ((int) (j / ((this.duration * 1000000) / this.timescale)));
                return i < firstSegmentNum ? firstSegmentNum : segmentCount == -1 ? i : Math.min(i, (firstSegmentNum + segmentCount) - 1);
            }
            int i2 = (segmentCount + firstSegmentNum) - 1;
            int i3 = firstSegmentNum;
            while (i3 <= i2) {
                int i4 = ((i2 - i3) / 2) + i3;
                long segmentTimeUs = getSegmentTimeUs(i4);
                if (segmentTimeUs < j) {
                    i3 = i4 + 1;
                } else {
                    if (segmentTimeUs <= j) {
                        return i4;
                    }
                    i2 = i4 - 1;
                }
            }
            return i3 == firstSegmentNum ? i3 : i2;
        }

        public final long getSegmentDurationUs(int i, long j) {
            List<SegmentTimelineElement> list = this.segmentTimeline;
            if (list != null) {
                return (list.get(i - this.startNumber).duration * 1000000) / this.timescale;
            }
            int segmentCount = getSegmentCount(j);
            return (segmentCount == -1 || i != (getFirstSegmentNum() + segmentCount) + (-1)) ? (this.duration * 1000000) / this.timescale : j - getSegmentTimeUs(i);
        }

        public final long getSegmentTimeUs(int i) {
            long j;
            List<SegmentTimelineElement> list = this.segmentTimeline;
            if (list != null) {
                j = list.get(i - this.startNumber).startTime - this.presentationTimeOffset;
            } else {
                j = ((long) (i - this.startNumber)) * this.duration;
            }
            return Util.scaleLargeTimestamp(j, 1000000L, this.timescale);
        }

        public int getFirstSegmentNum() {
            return this.startNumber;
        }

        public boolean isExplicit() {
            return this.segmentTimeline != null;
        }
    }

    public static class SegmentList extends MultiSegmentBase {
        final List<RangedUri> mediaSegments;

        @Override // com.google.android.exoplayer2.source.dash.manifest.SegmentBase.MultiSegmentBase
        public boolean isExplicit() {
            return true;
        }

        public SegmentList(RangedUri rangedUri, long j, long j2, int i, long j3, List<SegmentTimelineElement> list, List<RangedUri> list2) {
            super(rangedUri, j, j2, i, j3, list);
            this.mediaSegments = list2;
        }

        @Override // com.google.android.exoplayer2.source.dash.manifest.SegmentBase.MultiSegmentBase
        public RangedUri getSegmentUrl(Representation representation, int i) {
            return this.mediaSegments.get(i - this.startNumber);
        }

        @Override // com.google.android.exoplayer2.source.dash.manifest.SegmentBase.MultiSegmentBase
        public int getSegmentCount(long j) {
            return this.mediaSegments.size();
        }
    }

    public static class SegmentTemplate extends MultiSegmentBase {
        final UrlTemplate initializationTemplate;
        final UrlTemplate mediaTemplate;

        public SegmentTemplate(RangedUri rangedUri, long j, long j2, int i, long j3, List<SegmentTimelineElement> list, UrlTemplate urlTemplate, UrlTemplate urlTemplate2) {
            super(rangedUri, j, j2, i, j3, list);
            this.initializationTemplate = urlTemplate;
            this.mediaTemplate = urlTemplate2;
        }

        @Override // com.google.android.exoplayer2.source.dash.manifest.SegmentBase
        public RangedUri getInitialization(Representation representation) {
            UrlTemplate urlTemplate = this.initializationTemplate;
            if (urlTemplate != null) {
                Format format = representation.format;
                return new RangedUri(urlTemplate.buildUri(format.id, 0, format.bitrate, 0L), 0L, -1L);
            }
            return super.getInitialization(representation);
        }

        @Override // com.google.android.exoplayer2.source.dash.manifest.SegmentBase.MultiSegmentBase
        public RangedUri getSegmentUrl(Representation representation, int i) {
            long j;
            List<SegmentTimelineElement> list = this.segmentTimeline;
            if (list != null) {
                j = list.get(i - this.startNumber).startTime;
            } else {
                j = ((long) (i - this.startNumber)) * this.duration;
            }
            long j2 = j;
            UrlTemplate urlTemplate = this.mediaTemplate;
            Format format = representation.format;
            return new RangedUri(urlTemplate.buildUri(format.id, i, format.bitrate, j2), 0L, -1L);
        }

        @Override // com.google.android.exoplayer2.source.dash.manifest.SegmentBase.MultiSegmentBase
        public int getSegmentCount(long j) {
            List<SegmentTimelineElement> list = this.segmentTimeline;
            if (list != null) {
                return list.size();
            }
            if (j != -9223372036854775807L) {
                return (int) Util.ceilDivide(j, (this.duration * 1000000) / this.timescale);
            }
            return -1;
        }
    }

    public static class SegmentTimelineElement {
        final long duration;
        final long startTime;

        public SegmentTimelineElement(long j, long j2) {
            this.startTime = j;
            this.duration = j2;
        }
    }
}
