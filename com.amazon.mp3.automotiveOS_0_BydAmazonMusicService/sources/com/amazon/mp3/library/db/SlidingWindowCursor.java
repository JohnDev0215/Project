package com.amazon.mp3.library.db;

import android.database.ContentObserver;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Handler;
import android.os.Looper;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class SlidingWindowCursor extends VirtualizedCursor {
    private static final String TAG = SlidingWindowCursor.class.getSimpleName();
    private ContentObserver mContentObserver;
    private int mCount;
    private Cursor mCursor;
    private WindowFillThread mFillThread;
    private DummyCursorWindow mHeadWindow;
    private DummyCursorWindow mTailWindow;
    private CursorWindow mSlidingWindow = null;
    private int mLastRequestedPosition = -1;
    private Object mFillLock = new Object();
    private Handler mHandler = AmazonApplication.getDefaultHandler();
    private boolean mClosed = false;
    DataSetObservable mDataSetObservable = new DataSetObservable();
    private Runnable mStartThreadRunnable = new Runnable() { // from class: com.amazon.mp3.library.db.SlidingWindowCursor.1
        @Override // java.lang.Runnable
        public void run() {
            SlidingWindowCursor slidingWindowCursor = SlidingWindowCursor.this;
            slidingWindowCursor.checkStartWindowFillThread(slidingWindowCursor.mLastRequestedPosition);
        }
    };

    public SlidingWindowCursor(Cursor c) {
        ContentObserver contentObserver = new ContentObserver(AmazonApplication.getBackgroundHandler()) { // from class: com.amazon.mp3.library.db.SlidingWindowCursor.2
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange) {
                Log.debug(SlidingWindowCursor.TAG, "Received change, killing fill thread!");
                SlidingWindowCursor.this.killThread();
            }
        };
        this.mContentObserver = contentObserver;
        this.mCursor = c;
        c.registerContentObserver(contentObserver);
        this.mCount = this.mCursor.getCount();
        this.mHeadWindow = new DummyCursorWindow(this.mCount);
        this.mTailWindow = new DummyCursorWindow(0);
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            new WindowFillThread(0).fillOnCurrentThread();
        }
        setWindowPosition(0);
        moveToFirst();
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public int getCount() {
        return this.mCount;
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public String[] getColumnNames() {
        return this.mCursor.getColumnNames();
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public int getColumnIndex(String columnName) {
        return this.mCursor.getColumnIndex(columnName);
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public boolean requery() {
        throw new UnsupportedOperationException("requery() not supported, create a new instance instead");
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public void deactivate() {
        killThread();
    }

    @Override // android.database.AbstractCursor, android.database.Cursor, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (this.mClosed) {
            return;
        }
        this.mClosed = true;
        Log.verbose(TAG, "close() called");
        killThread(true);
        setWindow(null);
        CursorWindow cursorWindow = this.mSlidingWindow;
        if (cursorWindow != null) {
            cursorWindow.close();
            this.mSlidingWindow = null;
        }
        this.mHeadWindow.setStartPosition(0);
        this.mHeadWindow.setNumRows(this.mCount);
        this.mCursor.unregisterContentObserver(this.mContentObserver);
        this.mCursor.close();
        super.close();
    }

    @Override // com.amazon.mp3.library.db.VirtualizedCursor
    public boolean rowCached(int position) {
        return windowContains(this.mSlidingWindow, position);
    }

    @Override // android.database.AbstractCursor, android.database.CrossProcessCursor
    public boolean onMove(int oldPosition, int newPosition) {
        setWindowPosition(newPosition);
        return true;
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public void registerDataSetObserver(DataSetObserver observer) {
        this.mDataSetObservable.registerObserver(observer);
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public void unregisterDataSetObserver(DataSetObserver observer) {
        this.mDataSetObservable.unregisterObserver(observer);
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public void registerContentObserver(ContentObserver observer) {
        this.mCursor.registerContentObserver(observer);
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public void unregisterContentObserver(ContentObserver observer) {
        this.mCursor.unregisterContentObserver(observer);
    }

    private void setWindowPosition(int position) {
        if (position == -1 || this.mCount <= 0 || this.mClosed) {
            setWindow(null);
            return;
        }
        CursorWindow window = getWindow();
        CursorWindow cursorWindow = this.mSlidingWindow;
        if (cursorWindow != null && windowContains(cursorWindow, position)) {
            if (this.mSlidingWindow != window) {
                if (window != null) {
                    window.releaseReference();
                    window.close();
                }
                Log.verbose(TAG, "switching to sliding Window");
                this.mSlidingWindow.acquireReference();
                setWindow(this.mSlidingWindow);
                return;
            }
            return;
        }
        if (windowContains(this.mHeadWindow, position)) {
            if (window != this.mHeadWindow) {
                Log.verbose(TAG, "switching to head Window");
                setWindow(this.mHeadWindow);
            }
        } else if (windowContains(this.mTailWindow, position) && window != this.mTailWindow) {
            Log.verbose(TAG, "switching to tail Window");
            setWindow(this.mTailWindow);
        }
        this.mLastRequestedPosition = position;
        this.mHandler.removeCallbacks(this.mStartThreadRunnable);
        this.mHandler.postDelayed(this.mStartThreadRunnable, 250L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void checkStartWindowFillThread(int position) {
        if (this.mClosed) {
            Log.verbose(TAG, "already closed, not starting thread");
            return;
        }
        WindowFillThread windowFillThread = this.mFillThread;
        if (windowFillThread != null && !windowFillThread.isInterrupted() && this.mFillThread.probablyContains(position)) {
            Log.verbose(TAG, "current fill thread contains %d, allowing it to complete...", Integer.valueOf(position));
            return;
        }
        if (this.mFillThread != null) {
            Log.verbose(TAG, "current fill thread does not contain %d (%d-%d)", Integer.valueOf(position), Integer.valueOf(this.mFillThread.mStartPos), Integer.valueOf(this.mFillThread.mEstimatedEndPos));
        } else {
            Log.verbose(TAG, "current fill thread is null");
        }
        killThread();
        WindowFillThread windowFillThread2 = new WindowFillThread(position);
        this.mFillThread = windowFillThread2;
        windowFillThread2.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void killThread() {
        killThread(false);
    }

    private synchronized void killThread(boolean wait) {
        WindowFillThread windowFillThread = this.mFillThread;
        if (windowFillThread != null) {
            windowFillThread.interrupt();
            if (wait) {
                try {
                    this.mFillThread.join();
                } catch (InterruptedException unused) {
                }
            }
            this.mFillThread = null;
        }
    }

    private boolean windowContains(CursorWindow window, int position) {
        return window != null && position >= window.getStartPosition() && position < window.getStartPosition() + window.getNumRows();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dumpCursorWindow(String label, CursorWindow w) {
        int startPosition = w.getStartPosition();
        int numRows = w.getNumRows();
        if (numRows == 0) {
            Log.verbose(TAG, label + "=(empty)");
            return;
        }
        Log.verbose(TAG, label + "=(%d - %d)", Integer.valueOf(startPosition), Integer.valueOf((startPosition + numRows) - 1));
    }

    private class WindowFillThread extends Thread {
        private final int mEstimatedEndPos;
        private final int mPosition;
        private final int mStartPos;

        public WindowFillThread(int position) {
            this.mPosition = position;
            this.mStartPos = Math.max(0, position - 250);
            this.mEstimatedEndPos = position + ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
            setName(SlidingWindowCursor.TAG + ".WindowFillThread." + hashCode());
        }

        public boolean probablyContains(int position) {
            return position >= this.mStartPos && position < this.mEstimatedEndPos;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            fillOnCurrentThread();
        }

        void fillOnCurrentThread() {
            synchronized (SlidingWindowCursor.this.mFillLock) {
                CursorWindow cursorWindow = new CursorWindow(true);
                try {
                    SlidingWindowCursor.this.mCursor.moveToPosition(this.mStartPos - 1);
                    if (!DbUtil.fillWindowFromCursor(SlidingWindowCursor.this.mCursor, cursorWindow, 500)) {
                        String str = SlidingWindowCursor.TAG;
                        Object[] objArr = new Object[1];
                        objArr[0] = isInterrupted() ? "true" : "false";
                        Log.verbose(str, "fillWindowFromCursor failed. Interrupted=%s", objArr);
                    } else {
                        Log.verbose(SlidingWindowCursor.TAG, "fillWindowFromCursor finished. start=%d, count=%d", Integer.valueOf(cursorWindow.getStartPosition()), Integer.valueOf(cursorWindow.getNumRows()));
                        SlidingWindowCursor.this.mHandler.post(SlidingWindowCursor.this.new WindowFillCompleteRunnable(cursorWindow));
                        cursorWindow = null;
                    }
                    if (cursorWindow != null) {
                    }
                } finally {
                    cursorWindow.close();
                }
            }
        }
    }

    private class WindowFillCompleteRunnable implements Runnable {
        CursorWindow mNewWindow;

        WindowFillCompleteRunnable(CursorWindow window) {
            this.mNewWindow = window;
        }

        @Override // java.lang.Runnable
        public void run() {
            SlidingWindowCursor.this.mHeadWindow.setStartPosition(0);
            SlidingWindowCursor.this.mHeadWindow.setNumRows(this.mNewWindow.getStartPosition());
            if (SlidingWindowCursor.this.mSlidingWindow != null && SlidingWindowCursor.this.getWindow() != SlidingWindowCursor.this.mSlidingWindow) {
                SlidingWindowCursor.this.mSlidingWindow.close();
            }
            SlidingWindowCursor.this.mSlidingWindow = this.mNewWindow;
            int startPosition = this.mNewWindow.getStartPosition() + this.mNewWindow.getNumRows();
            SlidingWindowCursor.this.mTailWindow.setStartPosition(startPosition);
            SlidingWindowCursor.this.mTailWindow.setNumRows(SlidingWindowCursor.this.mCount - startPosition);
            SlidingWindowCursor slidingWindowCursor = SlidingWindowCursor.this;
            slidingWindowCursor.dumpCursorWindow("mHeadWindow", slidingWindowCursor.mHeadWindow);
            SlidingWindowCursor slidingWindowCursor2 = SlidingWindowCursor.this;
            slidingWindowCursor2.dumpCursorWindow("mSlidingWindow", slidingWindowCursor2.mSlidingWindow);
            SlidingWindowCursor slidingWindowCursor3 = SlidingWindowCursor.this;
            slidingWindowCursor3.dumpCursorWindow("mTailWindow", slidingWindowCursor3.mTailWindow);
            SlidingWindowCursor.this.mDataSetObservable.notifyChanged();
        }
    }
}
