package android.media;

import android.graphics.Canvas;
import android.media.MediaTimeProvider;
import android.os.Handler;
import android.util.Log;
import android.util.LongSparseArray;
import android.util.Pair;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Vector;

/* JADX INFO: loaded from: classes2.dex */
public abstract class SubtitleTrack implements MediaTimeProvider.OnMediaTimeListener {
    private static final String TAG = "SubtitleTrack";
    private MediaFormat mFormat;
    private long mLastTimeMs;
    private long mLastUpdateTimeMs;
    private Runnable mRunnable;
    protected MediaTimeProvider mTimeProvider;
    protected boolean mVisible;
    protected final LongSparseArray<Run> mRunsByEndTime = new LongSparseArray<>();
    protected final LongSparseArray<Run> mRunsByID = new LongSparseArray<>();
    protected final Vector<Cue> mActiveCues = new Vector<>();
    public boolean DEBUG = false;
    protected Handler mHandler = new Handler();
    private long mNextScheduledTimeMs = -1;
    protected CueList mCues = new CueList();

    public static class Cue {
        public long mEndTimeMs;
        public long[] mInnerTimesMs;
        public Cue mNextInRun;
        public long mRunID;
        public long mStartTimeMs;

        public void onTime(long j) {
        }
    }

    public interface RenderingWidget {

        public interface OnChangedListener {
            void onChanged(RenderingWidget renderingWidget);
        }

        void draw(Canvas canvas);

        void onAttachedToWindow();

        void onDetachedFromWindow();

        void setOnChangedListener(OnChangedListener onChangedListener);

        void setSize(int i, int i2);

        void setVisible(boolean z);
    }

    public abstract RenderingWidget getRenderingWidget();

    public abstract void onData(byte[] bArr, boolean z, long j);

    public abstract void updateView(Vector<Cue> vector);

    public SubtitleTrack(MediaFormat mediaFormat) {
        this.mFormat = mediaFormat;
        clearActiveCues();
        this.mLastTimeMs = -1L;
    }

    public final MediaFormat getFormat() {
        return this.mFormat;
    }

    protected void onData(SubtitleData subtitleData) {
        long startTimeUs = subtitleData.getStartTimeUs() + 1;
        onData(subtitleData.getData(), true, startTimeUs);
        setRunDiscardTimeMs(startTimeUs, (subtitleData.getStartTimeUs() + subtitleData.getDurationUs()) / 1000);
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0009 A[Catch: all -> 0x00ba, TryCatch #0 {, blocks: (B:4:0x0003, B:7:0x000c, B:8:0x0018, B:10:0x001e, B:12:0x0036, B:14:0x003a, B:15:0x0050, B:17:0x005d, B:18:0x0061, B:20:0x006f, B:22:0x0073, B:23:0x0089, B:25:0x008d, B:26:0x0090, B:27:0x0096, B:29:0x009a, B:30:0x009f, B:32:0x00a7, B:34:0x00b2, B:35:0x00b6, B:6:0x0009), top: B:41:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected synchronized void updateActiveCues(boolean r7, long r8) {
        /*
            r6 = this;
            monitor-enter(r6)
            if (r7 != 0) goto L9
            long r0 = r6.mLastUpdateTimeMs     // Catch: java.lang.Throwable -> Lba
            int r7 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r7 <= 0) goto Lc
        L9:
            r6.clearActiveCues()     // Catch: java.lang.Throwable -> Lba
        Lc:
            android.media.SubtitleTrack$CueList r7 = r6.mCues     // Catch: java.lang.Throwable -> Lba
            long r0 = r6.mLastUpdateTimeMs     // Catch: java.lang.Throwable -> Lba
            java.lang.Iterable r7 = r7.entriesBetween(r0, r8)     // Catch: java.lang.Throwable -> Lba
            java.util.Iterator r7 = r7.iterator()     // Catch: java.lang.Throwable -> Lba
        L18:
            boolean r0 = r7.hasNext()     // Catch: java.lang.Throwable -> Lba
            if (r0 == 0) goto L9f
            java.lang.Object r0 = r7.next()     // Catch: java.lang.Throwable -> Lba
            android.util.Pair r0 = (android.util.Pair) r0     // Catch: java.lang.Throwable -> Lba
            S r1 = r0.second     // Catch: java.lang.Throwable -> Lba
            android.media.SubtitleTrack$Cue r1 = (android.media.SubtitleTrack.Cue) r1     // Catch: java.lang.Throwable -> Lba
            long r2 = r1.mEndTimeMs     // Catch: java.lang.Throwable -> Lba
            F r4 = r0.first     // Catch: java.lang.Throwable -> Lba
            java.lang.Long r4 = (java.lang.Long) r4     // Catch: java.lang.Throwable -> Lba
            long r4 = r4.longValue()     // Catch: java.lang.Throwable -> Lba
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 != 0) goto L61
            boolean r0 = r6.DEBUG     // Catch: java.lang.Throwable -> Lba
            if (r0 == 0) goto L50
            java.lang.String r0 = "SubtitleTrack"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lba
            r2.<init>()     // Catch: java.lang.Throwable -> Lba
            java.lang.String r3 = "Removing "
            r2.append(r3)     // Catch: java.lang.Throwable -> Lba
            r2.append(r1)     // Catch: java.lang.Throwable -> Lba
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> Lba
            android.util.Log.v(r0, r2)     // Catch: java.lang.Throwable -> Lba
        L50:
            java.util.Vector<android.media.SubtitleTrack$Cue> r0 = r6.mActiveCues     // Catch: java.lang.Throwable -> Lba
            r0.remove(r1)     // Catch: java.lang.Throwable -> Lba
            long r0 = r1.mRunID     // Catch: java.lang.Throwable -> Lba
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 != 0) goto L18
            r7.remove()     // Catch: java.lang.Throwable -> Lba
            goto L18
        L61:
            long r2 = r1.mStartTimeMs     // Catch: java.lang.Throwable -> Lba
            F r0 = r0.first     // Catch: java.lang.Throwable -> Lba
            java.lang.Long r0 = (java.lang.Long) r0     // Catch: java.lang.Throwable -> Lba
            long r4 = r0.longValue()     // Catch: java.lang.Throwable -> Lba
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 != 0) goto L96
            boolean r0 = r6.DEBUG     // Catch: java.lang.Throwable -> Lba
            if (r0 == 0) goto L89
            java.lang.String r0 = "SubtitleTrack"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lba
            r2.<init>()     // Catch: java.lang.Throwable -> Lba
            java.lang.String r3 = "Adding "
            r2.append(r3)     // Catch: java.lang.Throwable -> Lba
            r2.append(r1)     // Catch: java.lang.Throwable -> Lba
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> Lba
            android.util.Log.v(r0, r2)     // Catch: java.lang.Throwable -> Lba
        L89:
            long[] r0 = r1.mInnerTimesMs     // Catch: java.lang.Throwable -> Lba
            if (r0 == 0) goto L90
            r1.onTime(r8)     // Catch: java.lang.Throwable -> Lba
        L90:
            java.util.Vector<android.media.SubtitleTrack$Cue> r0 = r6.mActiveCues     // Catch: java.lang.Throwable -> Lba
            r0.add(r1)     // Catch: java.lang.Throwable -> Lba
            goto L18
        L96:
            long[] r0 = r1.mInnerTimesMs     // Catch: java.lang.Throwable -> Lba
            if (r0 == 0) goto L18
            r1.onTime(r8)     // Catch: java.lang.Throwable -> Lba
            goto L18
        L9f:
            android.util.LongSparseArray<android.media.SubtitleTrack$Run> r7 = r6.mRunsByEndTime     // Catch: java.lang.Throwable -> Lba
            int r7 = r7.size()     // Catch: java.lang.Throwable -> Lba
            if (r7 <= 0) goto Lb6
            android.util.LongSparseArray<android.media.SubtitleTrack$Run> r7 = r6.mRunsByEndTime     // Catch: java.lang.Throwable -> Lba
            r0 = 0
            long r1 = r7.keyAt(r0)     // Catch: java.lang.Throwable -> Lba
            int r7 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1))
            if (r7 > 0) goto Lb6
            r6.removeRunsByEndTimeIndex(r0)     // Catch: java.lang.Throwable -> Lba
            goto L9f
        Lb6:
            r6.mLastUpdateTimeMs = r8     // Catch: java.lang.Throwable -> Lba
            monitor-exit(r6)
            return
        Lba:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: android.media.SubtitleTrack.updateActiveCues(boolean, long):void");
    }

    private void removeRunsByEndTimeIndex(int i) {
        Run runValueAt = this.mRunsByEndTime.valueAt(i);
        while (runValueAt != null) {
            Cue cue = runValueAt.mFirstCue;
            while (cue != null) {
                this.mCues.remove(cue);
                Cue cue2 = cue.mNextInRun;
                cue.mNextInRun = null;
                cue = cue2;
            }
            this.mRunsByID.remove(runValueAt.mRunID);
            Run run = runValueAt.mNextRunAtEndTimeMs;
            runValueAt.mPrevRunAtEndTimeMs = null;
            runValueAt.mNextRunAtEndTimeMs = null;
            runValueAt = run;
        }
        this.mRunsByEndTime.removeAt(i);
    }

    protected void finalize() throws Throwable {
        for (int size = this.mRunsByEndTime.size() - 1; size >= 0; size--) {
            removeRunsByEndTimeIndex(size);
        }
        super.finalize();
    }

    private synchronized void takeTime(long j) {
        this.mLastTimeMs = j;
    }

    protected synchronized void clearActiveCues() {
        if (this.DEBUG) {
            Log.v(TAG, "Clearing " + this.mActiveCues.size() + " active cues");
        }
        this.mActiveCues.clear();
        this.mLastUpdateTimeMs = -1L;
    }

    protected void scheduleTimedEvents() {
        if (this.mTimeProvider != null) {
            this.mNextScheduledTimeMs = this.mCues.nextTimeAfter(this.mLastTimeMs);
            if (this.DEBUG) {
                Log.d(TAG, "sched @" + this.mNextScheduledTimeMs + " after " + this.mLastTimeMs);
            }
            MediaTimeProvider mediaTimeProvider = this.mTimeProvider;
            long j = this.mNextScheduledTimeMs;
            mediaTimeProvider.notifyAt(j >= 0 ? j * 1000 : -1L, this);
        }
    }

    @Override // android.media.MediaTimeProvider.OnMediaTimeListener
    public void onTimedEvent(long j) {
        if (this.DEBUG) {
            Log.d(TAG, "onTimedEvent " + j);
        }
        synchronized (this) {
            long j2 = j / 1000;
            updateActiveCues(false, j2);
            takeTime(j2);
        }
        updateView(this.mActiveCues);
        scheduleTimedEvents();
    }

    @Override // android.media.MediaTimeProvider.OnMediaTimeListener
    public void onSeek(long j) {
        if (this.DEBUG) {
            Log.d(TAG, "onSeek " + j);
        }
        synchronized (this) {
            long j2 = j / 1000;
            updateActiveCues(true, j2);
            takeTime(j2);
        }
        updateView(this.mActiveCues);
        scheduleTimedEvents();
    }

    @Override // android.media.MediaTimeProvider.OnMediaTimeListener
    public void onStop() {
        synchronized (this) {
            if (this.DEBUG) {
                Log.d(TAG, "onStop");
            }
            clearActiveCues();
            this.mLastTimeMs = -1L;
        }
        updateView(this.mActiveCues);
        this.mNextScheduledTimeMs = -1L;
        MediaTimeProvider mediaTimeProvider = this.mTimeProvider;
        if (mediaTimeProvider != null) {
            mediaTimeProvider.notifyAt(-1L, this);
        }
    }

    public void show() {
        if (this.mVisible) {
            return;
        }
        this.mVisible = true;
        RenderingWidget renderingWidget = getRenderingWidget();
        if (renderingWidget != null) {
            renderingWidget.setVisible(true);
        }
        MediaTimeProvider mediaTimeProvider = this.mTimeProvider;
        if (mediaTimeProvider != null) {
            mediaTimeProvider.scheduleUpdate(this);
        }
    }

    public void hide() {
        if (this.mVisible) {
            MediaTimeProvider mediaTimeProvider = this.mTimeProvider;
            if (mediaTimeProvider != null) {
                mediaTimeProvider.cancelNotifications(this);
            }
            RenderingWidget renderingWidget = getRenderingWidget();
            if (renderingWidget != null) {
                renderingWidget.setVisible(false);
            }
            this.mVisible = false;
        }
    }

    protected synchronized boolean addCue(Cue cue) {
        this.mCues.add(cue);
        if (cue.mRunID != 0) {
            Run run = this.mRunsByID.get(cue.mRunID);
            if (run == null) {
                run = new Run();
                this.mRunsByID.put(cue.mRunID, run);
                run.mEndTimeMs = cue.mEndTimeMs;
            } else if (run.mEndTimeMs < cue.mEndTimeMs) {
                run.mEndTimeMs = cue.mEndTimeMs;
            }
            cue.mNextInRun = run.mFirstCue;
            run.mFirstCue = cue;
        }
        final long currentTimeUs = -1;
        if (this.mTimeProvider != null) {
            try {
                currentTimeUs = this.mTimeProvider.getCurrentTimeUs(false, true) / 1000;
            } catch (IllegalStateException unused) {
            }
        }
        if (this.DEBUG) {
            Log.v(TAG, "mVisible=" + this.mVisible + ", " + cue.mStartTimeMs + " <= " + currentTimeUs + ", " + cue.mEndTimeMs + " >= " + this.mLastTimeMs);
        }
        if (this.mVisible && cue.mStartTimeMs <= currentTimeUs && cue.mEndTimeMs >= this.mLastTimeMs) {
            if (this.mRunnable != null) {
                this.mHandler.removeCallbacks(this.mRunnable);
            }
            Runnable runnable = new Runnable() { // from class: android.media.SubtitleTrack.1
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        SubtitleTrack.this.mRunnable = null;
                        SubtitleTrack.this.updateActiveCues(true, currentTimeUs);
                        SubtitleTrack.this.updateView(SubtitleTrack.this.mActiveCues);
                    }
                }
            };
            this.mRunnable = runnable;
            if (this.mHandler.postDelayed(runnable, 10L)) {
                if (this.DEBUG) {
                    Log.v(TAG, "scheduling update");
                }
            } else if (this.DEBUG) {
                Log.w(TAG, "failed to schedule subtitle view update");
            }
            return true;
        }
        if (this.mVisible && cue.mEndTimeMs >= this.mLastTimeMs && (cue.mStartTimeMs < this.mNextScheduledTimeMs || this.mNextScheduledTimeMs < 0)) {
            scheduleTimedEvents();
        }
        return false;
    }

    public synchronized void setTimeProvider(MediaTimeProvider mediaTimeProvider) {
        if (this.mTimeProvider == mediaTimeProvider) {
            return;
        }
        if (this.mTimeProvider != null) {
            this.mTimeProvider.cancelNotifications(this);
        }
        this.mTimeProvider = mediaTimeProvider;
        if (mediaTimeProvider != null) {
            mediaTimeProvider.scheduleUpdate(this);
        }
    }

    static class CueList {
        private static final String TAG = "CueList";
        public boolean DEBUG = false;
        private SortedMap<Long, Vector<Cue>> mCues = new TreeMap();

        private boolean addEvent(Cue cue, long j) {
            Vector<Cue> vector = this.mCues.get(Long.valueOf(j));
            if (vector == null) {
                vector = new Vector<>(2);
                this.mCues.put(Long.valueOf(j), vector);
            } else if (vector.contains(cue)) {
                return false;
            }
            vector.add(cue);
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeEvent(Cue cue, long j) {
            Vector<Cue> vector = this.mCues.get(Long.valueOf(j));
            if (vector != null) {
                vector.remove(cue);
                if (vector.size() == 0) {
                    this.mCues.remove(Long.valueOf(j));
                }
            }
        }

        public void add(Cue cue) {
            if (cue.mStartTimeMs < cue.mEndTimeMs && addEvent(cue, cue.mStartTimeMs)) {
                long j = cue.mStartTimeMs;
                if (cue.mInnerTimesMs != null) {
                    for (long j2 : cue.mInnerTimesMs) {
                        if (j2 > j && j2 < cue.mEndTimeMs) {
                            addEvent(cue, j2);
                            j = j2;
                        }
                    }
                }
                addEvent(cue, cue.mEndTimeMs);
            }
        }

        public void remove(Cue cue) {
            removeEvent(cue, cue.mStartTimeMs);
            if (cue.mInnerTimesMs != null) {
                for (long j : cue.mInnerTimesMs) {
                    removeEvent(cue, j);
                }
            }
            removeEvent(cue, cue.mEndTimeMs);
        }

        public Iterable<Pair<Long, Cue>> entriesBetween(final long j, final long j2) {
            return new Iterable<Pair<Long, Cue>>() { // from class: android.media.SubtitleTrack.CueList.1
                @Override // java.lang.Iterable
                public Iterator<Pair<Long, Cue>> iterator() {
                    if (CueList.this.DEBUG) {
                        Log.d(CueList.TAG, "slice (" + j + ", " + j2 + "]=");
                    }
                    try {
                        return CueList.this.new EntryIterator(CueList.this.mCues.subMap(Long.valueOf(j + 1), Long.valueOf(j2 + 1)));
                    } catch (IllegalArgumentException unused) {
                        return CueList.this.new EntryIterator(null);
                    }
                }
            };
        }

        public long nextTimeAfter(long j) {
            try {
                SortedMap<Long, Vector<Cue>> sortedMapTailMap = this.mCues.tailMap(Long.valueOf(j + 1));
                if (sortedMapTailMap != null) {
                    return sortedMapTailMap.firstKey().longValue();
                }
            } catch (IllegalArgumentException | NoSuchElementException unused) {
            }
            return -1L;
        }

        class EntryIterator implements Iterator<Pair<Long, Cue>> {
            private long mCurrentTimeMs;
            private boolean mDone;
            private Pair<Long, Cue> mLastEntry;
            private Iterator<Cue> mLastListIterator;
            private Iterator<Cue> mListIterator;
            private SortedMap<Long, Vector<Cue>> mRemainingCues;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return !this.mDone;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public Pair<Long, Cue> next() {
                if (this.mDone) {
                    throw new NoSuchElementException("");
                }
                this.mLastEntry = new Pair<>(Long.valueOf(this.mCurrentTimeMs), this.mListIterator.next());
                Iterator<Cue> it = this.mListIterator;
                this.mLastListIterator = it;
                if (!it.hasNext()) {
                    nextKey();
                }
                return this.mLastEntry;
            }

            @Override // java.util.Iterator
            public void remove() {
                if (this.mLastListIterator == null || this.mLastEntry.second.mEndTimeMs != this.mLastEntry.first.longValue()) {
                    throw new IllegalStateException("");
                }
                this.mLastListIterator.remove();
                this.mLastListIterator = null;
                if (((Vector) CueList.this.mCues.get(this.mLastEntry.first)).size() == 0) {
                    CueList.this.mCues.remove(this.mLastEntry.first);
                }
                Cue cue = this.mLastEntry.second;
                CueList.this.removeEvent(cue, cue.mStartTimeMs);
                if (cue.mInnerTimesMs != null) {
                    for (long j : cue.mInnerTimesMs) {
                        CueList.this.removeEvent(cue, j);
                    }
                }
            }

            public EntryIterator(SortedMap<Long, Vector<Cue>> sortedMap) {
                if (CueList.this.DEBUG) {
                    Log.v(CueList.TAG, sortedMap + "");
                }
                this.mRemainingCues = sortedMap;
                this.mLastListIterator = null;
                nextKey();
            }

            private void nextKey() {
                while (this.mRemainingCues != null) {
                    try {
                        long jLongValue = this.mRemainingCues.firstKey().longValue();
                        this.mCurrentTimeMs = jLongValue;
                        this.mListIterator = this.mRemainingCues.get(Long.valueOf(jLongValue)).iterator();
                        try {
                            this.mRemainingCues = this.mRemainingCues.tailMap(Long.valueOf(this.mCurrentTimeMs + 1));
                        } catch (IllegalArgumentException unused) {
                            this.mRemainingCues = null;
                        }
                        this.mDone = false;
                        if (this.mListIterator.hasNext()) {
                            return;
                        }
                    } catch (NoSuchElementException unused2) {
                        this.mDone = true;
                        this.mRemainingCues = null;
                        this.mListIterator = null;
                        return;
                    }
                }
                throw new NoSuchElementException("");
            }
        }

        CueList() {
        }
    }

    protected void finishedRun(long j) {
        Run run;
        if (j == 0 || j == -1 || (run = this.mRunsByID.get(j)) == null) {
            return;
        }
        run.storeByEndTimeMs(this.mRunsByEndTime);
    }

    public void setRunDiscardTimeMs(long j, long j2) {
        Run run;
        if (j == 0 || j == -1 || (run = this.mRunsByID.get(j)) == null) {
            return;
        }
        run.mEndTimeMs = j2;
        run.storeByEndTimeMs(this.mRunsByEndTime);
    }

    public int getTrackType() {
        return getRenderingWidget() == null ? 3 : 4;
    }

    private static class Run {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        public long mEndTimeMs;
        public Cue mFirstCue;
        public Run mNextRunAtEndTimeMs;
        public Run mPrevRunAtEndTimeMs;
        public long mRunID;
        private long mStoredEndTimeMs;

        private Run() {
            this.mEndTimeMs = -1L;
            this.mRunID = 0L;
            this.mStoredEndTimeMs = -1L;
        }

        public void storeByEndTimeMs(LongSparseArray<Run> longSparseArray) {
            int iIndexOfKey = longSparseArray.indexOfKey(this.mStoredEndTimeMs);
            if (iIndexOfKey >= 0) {
                if (this.mPrevRunAtEndTimeMs == null) {
                    Run run = this.mNextRunAtEndTimeMs;
                    if (run == null) {
                        longSparseArray.removeAt(iIndexOfKey);
                    } else {
                        longSparseArray.setValueAt(iIndexOfKey, run);
                    }
                }
                removeAtEndTimeMs();
            }
            long j = this.mEndTimeMs;
            if (j >= 0) {
                this.mPrevRunAtEndTimeMs = null;
                Run run2 = longSparseArray.get(j);
                this.mNextRunAtEndTimeMs = run2;
                if (run2 != null) {
                    run2.mPrevRunAtEndTimeMs = this;
                }
                longSparseArray.put(this.mEndTimeMs, this);
                this.mStoredEndTimeMs = this.mEndTimeMs;
            }
        }

        public void removeAtEndTimeMs() {
            Run run = this.mPrevRunAtEndTimeMs;
            if (run != null) {
                run.mNextRunAtEndTimeMs = this.mNextRunAtEndTimeMs;
                this.mPrevRunAtEndTimeMs = null;
            }
            Run run2 = this.mNextRunAtEndTimeMs;
            if (run2 != null) {
                run2.mPrevRunAtEndTimeMs = run;
                this.mNextRunAtEndTimeMs = null;
            }
        }
    }
}
