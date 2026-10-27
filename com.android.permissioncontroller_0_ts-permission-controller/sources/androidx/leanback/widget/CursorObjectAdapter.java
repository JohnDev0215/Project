package androidx.leanback.widget;

import android.database.Cursor;
import android.util.LruCache;
import androidx.leanback.database.CursorMapper;

/* JADX INFO: loaded from: classes3.dex */
public class CursorObjectAdapter extends ObjectAdapter {
    private static final int CACHE_SIZE = 100;
    private Cursor mCursor;
    private final LruCache<Integer, Object> mItemCache;
    private CursorMapper mMapper;

    public CursorObjectAdapter(PresenterSelector presenterSelector) {
        super(presenterSelector);
        this.mItemCache = new LruCache<>(100);
    }

    public CursorObjectAdapter(Presenter presenter) {
        super(presenter);
        this.mItemCache = new LruCache<>(100);
    }

    public CursorObjectAdapter() {
        this.mItemCache = new LruCache<>(100);
    }

    public void changeCursor(Cursor cursor) {
        Cursor cursor2 = this.mCursor;
        if (cursor == cursor2) {
            return;
        }
        if (cursor2 != null) {
            cursor2.close();
        }
        this.mCursor = cursor;
        this.mItemCache.trimToSize(0);
        onCursorChanged();
    }

    public Cursor swapCursor(Cursor cursor) {
        Cursor cursor2 = this.mCursor;
        if (cursor == cursor2) {
            return cursor2;
        }
        Cursor oldCursor = this.mCursor;
        this.mCursor = cursor;
        this.mItemCache.trimToSize(0);
        onCursorChanged();
        return oldCursor;
    }

    protected void onCursorChanged() {
        notifyChanged();
    }

    public final Cursor getCursor() {
        return this.mCursor;
    }

    public final void setMapper(CursorMapper mapper) {
        boolean changed = this.mMapper != mapper;
        this.mMapper = mapper;
        if (changed) {
            onMapperChanged();
        }
    }

    protected void onMapperChanged() {
    }

    public final CursorMapper getMapper() {
        return this.mMapper;
    }

    @Override // androidx.leanback.widget.ObjectAdapter
    public int size() {
        Cursor cursor = this.mCursor;
        if (cursor == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override // androidx.leanback.widget.ObjectAdapter
    public Object get(int index) {
        Cursor cursor = this.mCursor;
        if (cursor == null) {
            return null;
        }
        if (!cursor.moveToPosition(index)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Object item = this.mItemCache.get(Integer.valueOf(index));
        if (item != null) {
            return item;
        }
        Object item2 = this.mMapper.convert(this.mCursor);
        this.mItemCache.put(Integer.valueOf(index), item2);
        return item2;
    }

    public void close() {
        Cursor cursor = this.mCursor;
        if (cursor != null) {
            cursor.close();
            this.mCursor = null;
        }
    }

    public boolean isClosed() {
        Cursor cursor = this.mCursor;
        return cursor == null || cursor.isClosed();
    }

    protected final void invalidateCache(int index) {
        this.mItemCache.remove(Integer.valueOf(index));
    }

    protected final void invalidateCache(int index, int count) {
        int limit = count + index;
        while (index < limit) {
            invalidateCache(index);
            index++;
        }
    }

    @Override // androidx.leanback.widget.ObjectAdapter
    public boolean isImmediateNotifySupported() {
        return true;
    }
}
