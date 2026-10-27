package com.amazon.ion.impl;

/* JADX INFO: loaded from: classes3.dex */
final class UnifiedSavePointManagerX {
    UnifiedInputBufferX _buffer;
    int _free_count;
    int _open_save_points;
    UnifiedInputStreamX _stream;
    SavePoint _inuse = null;
    SavePoint _free = null;
    SavePoint _active_stack = null;

    public UnifiedSavePointManagerX(UnifiedInputStreamX unifiedInputStreamX) {
        this._stream = unifiedInputStreamX;
        this._buffer = unifiedInputStreamX._buffer;
    }

    public final boolean isSavePointOpen() {
        return this._open_save_points > 0;
    }

    public final long lengthOf(SavePoint savePoint) {
        int startIdx = savePoint.getStartIdx();
        int endIdx = savePoint.getEndIdx();
        if (startIdx == -1 || endIdx == -1) {
            return 0L;
        }
        if (startIdx == endIdx) {
            return savePoint.getEndPos() - savePoint.getStartPos();
        }
        UnifiedDataPageX page = this._buffer.getPage(startIdx);
        return this._buffer.getPage(endIdx).getFilePosition(savePoint.getEndPos()) - page.getFilePosition(savePoint.getStartPos());
    }

    public final SavePoint savePointAllocate() {
        SavePoint savePoint = this._free;
        if (savePoint == null) {
            savePoint = new SavePoint(this);
        } else {
            this._free = savePoint._next;
            this._free_count--;
            savePoint.clear();
        }
        savePoint._next = this._inuse;
        savePoint._prev = null;
        SavePoint savePoint2 = this._inuse;
        if (savePoint2 == null) {
            this._inuse = savePoint;
        } else {
            savePoint2._prev = savePoint;
        }
        return savePoint;
    }

    public final SavePoint savePointActiveTop() {
        return this._active_stack;
    }

    public final void savePointPushActive(SavePoint savePoint, long j, long j2) {
        int endPos;
        int currentPageIdx = this._buffer.getCurrentPageIdx();
        UnifiedInputStreamX unifiedInputStreamX = this._stream;
        int i = unifiedInputStreamX._pos;
        int i2 = unifiedInputStreamX._limit;
        this._buffer.getPage(currentPageIdx);
        savePoint.set_prev_pos(currentPageIdx, i, i2, j, j2);
        savePoint._next_active = this._active_stack;
        this._active_stack = savePoint;
        savePoint.set_active();
        int startIdx = savePoint.getStartIdx();
        int startPos = savePoint.getStartPos();
        UnifiedDataPageX page = this._buffer.getPage(startIdx);
        if (savePoint.getEndIdx() != savePoint.getStartIdx()) {
            endPos = page.getBufferLimit();
        } else {
            endPos = savePoint.getEndPos();
        }
        this._stream.make_page_current(page, startIdx, startPos, endPos);
    }

    public final void savePointPopActive(SavePoint savePoint) {
        if (savePoint != this._active_stack) {
            throw new IllegalArgumentException("save point being released isn't currently active");
        }
        this._active_stack = savePoint._next_active;
        savePoint._next_active = null;
        savePoint.set_inactive();
        this._stream.save_point_reset_to_prev(savePoint);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void save_point_clear(SavePoint savePoint) {
        if (savePoint.isClear()) {
            return;
        }
        int startIdx = savePoint.getStartIdx();
        if ((savePoint.getEndIdx() == -1 && startIdx == -1) || startIdx == -1) {
            return;
        }
        this._open_save_points--;
        save_point_unpin(savePoint);
    }

    private final void save_point_unpin(SavePoint savePoint) {
        if (savePoint.isActive()) {
            throw new IllegalArgumentException("you can't release an active save point");
        }
        if (this._buffer.decLock() && this._open_save_points == 0) {
            this._buffer.resetToCurrentPage();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SavePoint save_point_start(SavePoint savePoint, long j, long j2) {
        if (savePoint.isDefined()) {
            throw new IllegalArgumentException("you can't start an active save point");
        }
        int currentPageIdx = this._buffer.getCurrentPageIdx();
        this._buffer.incLock();
        savePoint.set_start_pos(currentPageIdx, this._stream._pos, j, j2);
        this._open_save_points++;
        return savePoint;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void save_point_mark_end(SavePoint savePoint, int i) {
        if (savePoint.isActive()) {
            throw new IllegalArgumentException("you can't start an active save point");
        }
        UnifiedDataPageX currentPage = this._buffer.getCurrentPage();
        int currentPageIdx = this._buffer.getCurrentPageIdx();
        int bufferLimit = this._stream._pos + i;
        if (i != 0) {
            if (bufferLimit >= currentPage.getBufferLimit()) {
                bufferLimit -= currentPage.getOriginalStartingOffset();
                currentPageIdx++;
                currentPage = this._buffer.getPage(currentPageIdx);
            } else if (bufferLimit < currentPage.getStartingOffset()) {
                int originalStartingOffset = bufferLimit - currentPage.getOriginalStartingOffset();
                currentPageIdx--;
                currentPage = this._buffer.getPage(currentPageIdx);
                bufferLimit = currentPage.getBufferLimit() - originalStartingOffset;
            }
            if (currentPage == null || bufferLimit >= currentPage.getBufferLimit() || bufferLimit < currentPage.getStartingOffset()) {
                end_point_too_far(currentPageIdx);
            }
        }
        savePoint.set_end_pos(currentPageIdx, bufferLimit);
    }

    private final void end_point_too_far(int i) {
        throw new IllegalArgumentException("end point [" + i + "] must be within 1 page of current [" + this._buffer.getCurrentPageIdx() + "]");
    }

    public static class SavePoint {
        private int _end_idx;
        private int _end_pos;
        private SavePoint _next;
        private SavePoint _next_active;
        private UnifiedSavePointManagerX _owner;
        private SavePoint _prev;
        private int _prev_idx;
        private int _prev_limit;
        private long _prev_line_count;
        private long _prev_line_start;
        private int _prev_pos;
        private int _start_idx;
        private long _start_line_count;
        private long _start_line_start;
        private int _start_pos;
        private SavePointState _state;

        public enum SavePointState {
            CLEAR,
            DEFINED,
            ACTIVE
        }

        SavePoint(UnifiedSavePointManagerX unifiedSavePointManagerX) {
            clear();
            this._owner = unifiedSavePointManagerX;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void set_start_pos(int i, int i2, long j, long j2) {
            this._state = SavePointState.DEFINED;
            this._start_idx = i;
            this._start_pos = i2;
            this._start_line_count = j;
            this._start_line_start = j2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void set_end_pos(int i, int i2) {
            this._end_idx = i;
            this._end_pos = i2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void set_prev_pos(int i, int i2, int i3, long j, long j2) {
            this._prev_idx = i;
            this._prev_pos = i2;
            this._prev_limit = i3;
            this._prev_line_count = j;
            this._prev_line_start = j2;
        }

        public final void clear() {
            if (isDefined()) {
                this._owner.save_point_clear(this);
            }
            this._state = SavePointState.CLEAR;
            this._start_idx = -1;
            this._end_idx = -1;
            this._prev_idx = -1;
        }

        public final void start(long j, long j2) {
            this._owner.save_point_start(this, j, j2);
        }

        public final void markEnd() {
            this._owner.save_point_mark_end(this, 0);
        }

        public final void markEnd(int i) {
            this._owner.save_point_mark_end(this, i);
        }

        public final boolean isClear() {
            return this._state == SavePointState.CLEAR;
        }

        public final boolean isDefined() {
            SavePointState savePointState = this._state;
            return savePointState == SavePointState.DEFINED || savePointState == SavePointState.ACTIVE;
        }

        public final boolean isActive() {
            return this._state == SavePointState.ACTIVE;
        }

        public final void set_active() {
            this._state = SavePointState.ACTIVE;
        }

        public final void set_inactive() {
            this._state = SavePointState.DEFINED;
        }

        public final long length() {
            if (this._start_idx == -1 || this._end_idx == -1) {
                return 0L;
            }
            return this._owner.lengthOf(this);
        }

        public final int getStartIdx() {
            return this._start_idx;
        }

        public final int getStartPos() {
            return this._start_pos;
        }

        public final long getStartLineNumber() {
            return this._start_line_count;
        }

        public final long getStartLineStart() {
            return this._start_line_start;
        }

        public final int getEndIdx() {
            return this._end_idx;
        }

        public final int getEndPos() {
            return this._end_pos;
        }

        public final int getPrevIdx() {
            return this._prev_idx;
        }

        public final int getPrevPos() {
            return this._prev_pos;
        }

        public final int getPrevLimit() {
            return this._prev_limit;
        }

        public final long getPrevLineNumber() {
            return this._prev_line_count;
        }

        public final long getPrevLineStart() {
            return this._prev_line_start;
        }
    }
}
