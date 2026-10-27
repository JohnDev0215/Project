package b.h.a;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import b.h.a.b;

/* JADX INFO: compiled from: CursorAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a extends BaseAdapter implements Filterable, b.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected boolean f1866b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected boolean f1867c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected Cursor f1868d;
    protected Context e;
    protected int f;
    protected C0044a g;
    protected DataSetObserver h;
    protected b.h.a.b i;

    /* JADX INFO: renamed from: b.h.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CursorAdapter.java */
    private class C0044a extends ContentObserver {
        C0044a() {
            super(new Handler());
        }

        @Override // android.database.ContentObserver
        public boolean deliverSelfNotifications() {
            return true;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            a.this.e();
        }
    }

    /* JADX INFO: compiled from: CursorAdapter.java */
    private class b extends DataSetObserver {
        b() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            a aVar = a.this;
            aVar.f1866b = true;
            aVar.notifyDataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            a aVar = a.this;
            aVar.f1866b = false;
            aVar.notifyDataSetInvalidated();
        }
    }

    public a(Context context, Cursor cursor, boolean z) {
        b(context, cursor, z ? 1 : 2);
    }

    public abstract void a(View view, Context context, Cursor cursor);

    void b(Context context, Cursor cursor, int i) {
        if ((i & 1) == 1) {
            i |= 2;
            this.f1867c = true;
        } else {
            this.f1867c = false;
        }
        boolean z = cursor != null;
        this.f1868d = cursor;
        this.f1866b = z;
        this.e = context;
        this.f = z ? cursor.getColumnIndexOrThrow("_id") : -1;
        if ((i & 2) == 2) {
            this.g = new C0044a();
            this.h = new b();
        } else {
            this.g = null;
            this.h = null;
        }
        if (z) {
            C0044a c0044a = this.g;
            if (c0044a != null) {
                cursor.registerContentObserver(c0044a);
            }
            DataSetObserver dataSetObserver = this.h;
            if (dataSetObserver != null) {
                cursor.registerDataSetObserver(dataSetObserver);
            }
        }
    }

    public abstract View c(Context context, Cursor cursor, ViewGroup viewGroup);

    public void changeCursor(Cursor cursor) {
        Cursor cursorF = f(cursor);
        if (cursorF != null) {
            cursorF.close();
        }
    }

    public abstract CharSequence convertToString(Cursor cursor);

    public abstract View d(Context context, Cursor cursor, ViewGroup viewGroup);

    protected void e() {
        Cursor cursor;
        if (!this.f1867c || (cursor = this.f1868d) == null || cursor.isClosed()) {
            return;
        }
        this.f1866b = this.f1868d.requery();
    }

    public Cursor f(Cursor cursor) {
        Cursor cursor2 = this.f1868d;
        if (cursor == cursor2) {
            return null;
        }
        if (cursor2 != null) {
            C0044a c0044a = this.g;
            if (c0044a != null) {
                cursor2.unregisterContentObserver(c0044a);
            }
            DataSetObserver dataSetObserver = this.h;
            if (dataSetObserver != null) {
                cursor2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.f1868d = cursor;
        if (cursor != null) {
            C0044a c0044a2 = this.g;
            if (c0044a2 != null) {
                cursor.registerContentObserver(c0044a2);
            }
            DataSetObserver dataSetObserver2 = this.h;
            if (dataSetObserver2 != null) {
                cursor.registerDataSetObserver(dataSetObserver2);
            }
            this.f = cursor.getColumnIndexOrThrow("_id");
            this.f1866b = true;
            notifyDataSetChanged();
        } else {
            this.f = -1;
            this.f1866b = false;
            notifyDataSetInvalidated();
        }
        return cursor2;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        Cursor cursor;
        if (!this.f1866b || (cursor = this.f1868d) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override // b.h.a.b.a
    public Cursor getCursor() {
        return this.f1868d;
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        if (!this.f1866b) {
            return null;
        }
        this.f1868d.moveToPosition(i);
        if (view == null) {
            view = c(this.e, this.f1868d, viewGroup);
        }
        a(view, this.e, this.f1868d);
        return view;
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        if (this.i == null) {
            this.i = new b.h.a.b(this);
        }
        return this.i;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        Cursor cursor;
        if (!this.f1866b || (cursor = this.f1868d) == null) {
            return null;
        }
        cursor.moveToPosition(i);
        return this.f1868d;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        Cursor cursor;
        if (this.f1866b && (cursor = this.f1868d) != null && cursor.moveToPosition(i)) {
            return this.f1868d.getLong(this.f);
        }
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (!this.f1866b) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (this.f1868d.moveToPosition(i)) {
            if (view == null) {
                view = d(this.e, this.f1868d, viewGroup);
            }
            a(view, this.e, this.f1868d);
            return view;
        }
        throw new IllegalStateException("couldn't move cursor to position " + i);
    }
}
