package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.util.Assertions;
import java.io.EOFException;
import java.io.IOException;

/* JADX INFO: loaded from: classes6.dex */
final class DefaultOggSeeker implements OggSeeker {
    private long end;
    private long endGranule;
    private final long endPosition;
    private final OggPageHeader pageHeader = new OggPageHeader();
    private long positionBeforeSeekToEnd;
    private long start;
    private long startGranule;
    private final long startPosition;
    private int state;
    private final StreamReader streamReader;
    private long targetGranule;
    private long totalGranules;

    public DefaultOggSeeker(long j, long j2, StreamReader streamReader, int i, long j3) {
        Assertions.checkArgument(j >= 0 && j2 > j);
        this.streamReader = streamReader;
        this.startPosition = j;
        this.endPosition = j2;
        if (i == j2 - j) {
            this.totalGranules = j3;
            this.state = 3;
        } else {
            this.state = 0;
        }
    }

    @Override // com.google.android.exoplayer2.extractor.ogg.OggSeeker
    public long read(ExtractorInput extractorInput) throws InterruptedException, IOException {
        int i = this.state;
        if (i == 0) {
            long position = extractorInput.getPosition();
            this.positionBeforeSeekToEnd = position;
            this.state = 1;
            long j = this.endPosition - 65307;
            if (j > position) {
                return j;
            }
        } else if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    return -1L;
                }
                throw new IllegalStateException();
            }
            long j2 = this.targetGranule;
            long jSkipToPageOfGranule = 0;
            if (j2 != 0) {
                long nextSeekPosition = getNextSeekPosition(j2, extractorInput);
                if (nextSeekPosition >= 0) {
                    return nextSeekPosition;
                }
                jSkipToPageOfGranule = skipToPageOfGranule(extractorInput, this.targetGranule, -(nextSeekPosition + 2));
            }
            this.state = 3;
            return -(jSkipToPageOfGranule + 2);
        }
        this.totalGranules = readGranuleOfLastPage(extractorInput);
        this.state = 3;
        return this.positionBeforeSeekToEnd;
    }

    @Override // com.google.android.exoplayer2.extractor.ogg.OggSeeker
    public long startSeek(long j) {
        int i = this.state;
        Assertions.checkArgument(i == 3 || i == 2);
        this.targetGranule = j != 0 ? this.streamReader.convertTimeToGranule(j) : 0L;
        this.state = 2;
        resetSeeking();
        return this.targetGranule;
    }

    @Override // com.google.android.exoplayer2.extractor.ogg.OggSeeker
    public OggSeekMap createSeekMap() {
        if (this.totalGranules != 0) {
            return new OggSeekMap();
        }
        return null;
    }

    public void resetSeeking() {
        this.start = this.startPosition;
        this.end = this.endPosition;
        this.startGranule = 0L;
        this.endGranule = this.totalGranules;
    }

    public long getNextSeekPosition(long j, ExtractorInput extractorInput) throws InterruptedException, IOException {
        if (this.start == this.end) {
            return -(this.startGranule + 2);
        }
        long position = extractorInput.getPosition();
        if (!skipToNextPage(extractorInput, this.end)) {
            long j2 = this.start;
            if (j2 != position) {
                return j2;
            }
            throw new IOException("No ogg page can be found.");
        }
        this.pageHeader.populate(extractorInput, false);
        extractorInput.resetPeekPosition();
        OggPageHeader oggPageHeader = this.pageHeader;
        long j3 = oggPageHeader.granulePosition;
        long j4 = j - j3;
        int i = oggPageHeader.headerSize + oggPageHeader.bodySize;
        if (j4 < 0 || j4 > 72000) {
            if (j4 < 0) {
                this.end = position;
                this.endGranule = j3;
            } else {
                long j5 = i;
                long position2 = extractorInput.getPosition() + j5;
                this.start = position2;
                this.startGranule = this.pageHeader.granulePosition;
                if ((this.end - position2) + j5 < 100000) {
                    extractorInput.skipFully(i);
                    return -(this.startGranule + 2);
                }
            }
            long j6 = this.end;
            long j7 = this.start;
            if (j6 - j7 < 100000) {
                this.end = j7;
                return j7;
            }
            long position3 = extractorInput.getPosition() - ((long) (i * (j4 <= 0 ? 2 : 1)));
            long j8 = this.end;
            long j9 = this.start;
            return Math.min(Math.max(position3 + ((j4 * (j8 - j9)) / (this.endGranule - this.startGranule)), j9), this.end - 1);
        }
        extractorInput.skipFully(i);
        return -(this.pageHeader.granulePosition + 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getEstimatedPosition(long j, long j2, long j3) {
        long j4 = this.endPosition;
        long j5 = this.startPosition;
        long j6 = j + (((j2 * (j4 - j5)) / this.totalGranules) - j3);
        if (j6 >= j5) {
            j5 = j6;
        }
        return j5 >= j4 ? j4 - 1 : j5;
    }

    private class OggSeekMap implements SeekMap {
        @Override // com.google.android.exoplayer2.extractor.SeekMap
        public boolean isSeekable() {
            return true;
        }

        private OggSeekMap() {
        }

        @Override // com.google.android.exoplayer2.extractor.SeekMap
        public long getPosition(long j) {
            if (j == 0) {
                return DefaultOggSeeker.this.startPosition;
            }
            long jConvertTimeToGranule = DefaultOggSeeker.this.streamReader.convertTimeToGranule(j);
            DefaultOggSeeker defaultOggSeeker = DefaultOggSeeker.this;
            return defaultOggSeeker.getEstimatedPosition(defaultOggSeeker.startPosition, jConvertTimeToGranule, 30000L);
        }

        @Override // com.google.android.exoplayer2.extractor.SeekMap
        public long getDurationUs() {
            return DefaultOggSeeker.this.streamReader.convertGranuleToTime(DefaultOggSeeker.this.totalGranules);
        }
    }

    void skipToNextPage(ExtractorInput extractorInput) throws InterruptedException, IOException {
        if (!skipToNextPage(extractorInput, this.endPosition)) {
            throw new EOFException();
        }
    }

    boolean skipToNextPage(ExtractorInput extractorInput, long j) throws InterruptedException, IOException {
        int i;
        long jMin = Math.min(j + 3, this.endPosition);
        int position = 2048;
        byte[] bArr = new byte[2048];
        while (true) {
            int i2 = 0;
            if (extractorInput.getPosition() + ((long) position) > jMin && (position = (int) (jMin - extractorInput.getPosition())) < 4) {
                return false;
            }
            extractorInput.peekFully(bArr, 0, position, false);
            while (true) {
                i = position - 3;
                if (i2 < i) {
                    if (bArr[i2] == 79 && bArr[i2 + 1] == 103 && bArr[i2 + 2] == 103 && bArr[i2 + 3] == 83) {
                        extractorInput.skipFully(i2);
                        return true;
                    }
                    i2++;
                }
            }
            extractorInput.skipFully(i);
        }
    }

    long readGranuleOfLastPage(ExtractorInput extractorInput) throws InterruptedException, IOException {
        skipToNextPage(extractorInput);
        this.pageHeader.reset();
        while ((this.pageHeader.type & 4) != 4 && extractorInput.getPosition() < this.endPosition) {
            this.pageHeader.populate(extractorInput, false);
            OggPageHeader oggPageHeader = this.pageHeader;
            extractorInput.skipFully(oggPageHeader.headerSize + oggPageHeader.bodySize);
        }
        return this.pageHeader.granulePosition;
    }

    long skipToPageOfGranule(ExtractorInput extractorInput, long j, long j2) throws InterruptedException, IOException {
        this.pageHeader.populate(extractorInput, false);
        while (true) {
            OggPageHeader oggPageHeader = this.pageHeader;
            if (oggPageHeader.granulePosition < j) {
                extractorInput.skipFully(oggPageHeader.headerSize + oggPageHeader.bodySize);
                OggPageHeader oggPageHeader2 = this.pageHeader;
                long j3 = oggPageHeader2.granulePosition;
                oggPageHeader2.populate(extractorInput, false);
                j2 = j3;
            } else {
                extractorInput.resetPeekPosition();
                return j2;
            }
        }
    }
}
