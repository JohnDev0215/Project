package androidx.cursoradapter.a;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.annotation.RestrictTo;
import androidx.cursoradapter.a.b;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public abstract class a extends BaseAdapter implements Filterable, b.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    protected boolean f608c;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    protected Cursor d;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    protected Context e;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    protected int f;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    protected C0020a g;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    protected DataSetObserver h;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    protected b i;

    /* JADX INFO: renamed from: androidx.cursoradapter.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    private class C0020a extends ContentObserver {
    }

    public abstract void a(View view, Context context, Cursor cursor);

    public abstract View b(Context context, Cursor cursor, ViewGroup viewGroup);

    public abstract View c(Context context, Cursor cursor, ViewGroup viewGroup);

    public void changeCursor(Cursor cursor) {
        Cursor cursorD = d(cursor);
        if (cursorD != null) {
            cursorD.close();
        }
    }

    public abstract CharSequence convertToString(Cursor cursor);

    public Cursor d(Cursor cursor) {
        Cursor cursor2 = this.d;
        if (cursor == cursor2) {
            return null;
        }
        if (cursor2 != null) {
            C0020a c0020a = this.g;
            if (c0020a != null) {
                cursor2.unregisterContentObserver(c0020a);
            }
            DataSetObserver dataSetObserver = this.h;
            if (dataSetObserver != null) {
                cursor2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.d = cursor;
        if (cursor != null) {
            C0020a c0020a2 = this.g;
            if (c0020a2 != null) {
                cursor.registerContentObserver(c0020a2);
            }
            DataSetObserver dataSetObserver2 = this.h;
            if (dataSetObserver2 != null) {
                cursor.registerDataSetObserver(dataSetObserver2);
            }
            this.f = cursor.getColumnIndexOrThrow("_id");
            this.f608c = true;
            notifyDataSetChanged();
        } else {
            this.f = -1;
            this.f608c = false;
            notifyDataSetInvalidated();
        }
        return cursor2;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        Cursor cursor;
        if (!this.f608c || (cursor = this.d) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override // androidx.cursoradapter.a.b.a
    public Cursor getCursor() {
        return this.d;
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        if (!this.f608c) {
            return null;
        }
        this.d.moveToPosition(i);
        if (view == null) {
            view = b(this.e, this.d, viewGroup);
        }
        a(view, this.e, this.d);
        return view;
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        if (this.i == null) {
            this.i = new b(this);
        }
        return this.i;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        Cursor cursor;
        if (!this.f608c || (cursor = this.d) == null) {
            return null;
        }
        cursor.moveToPosition(i);
        return this.d;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        Cursor cursor;
        if (this.f608c && (cursor = this.d) != null && cursor.moveToPosition(i)) {
            return this.d.getLong(this.f);
        }
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (!this.f608c) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (this.d.moveToPosition(i)) {
            if (view == null) {
                view = c(this.e, this.d, viewGroup);
            }
            a(view, this.e, this.d);
            return view;
        }
        throw new IllegalStateException("couldn't move cursor to position " + i);
    }
}
