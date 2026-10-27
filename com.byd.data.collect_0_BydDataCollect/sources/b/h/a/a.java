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
    protected boolean f2759b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected boolean f2760c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected Cursor f2761d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected Context f2762e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected int f2763f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected C0049a f2764g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    protected DataSetObserver f2765h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    protected b.h.a.b f2766i;

    /* JADX INFO: renamed from: b.h.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CursorAdapter.java */
    private class C0049a extends ContentObserver {
        C0049a() {
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
            aVar.f2759b = true;
            aVar.notifyDataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            a aVar = a.this;
            aVar.f2759b = false;
            aVar.notifyDataSetInvalidated();
        }
    }

    public a(Context context, Cursor cursor, boolean z) {
        b(context, cursor, z ? 1 : 2);
    }

    public abstract void a(View view, Context context, Cursor cursor);

    void b(Context context, Cursor cursor, int i2) {
        if ((i2 & 1) == 1) {
            i2 |= 2;
            this.f2760c = true;
        } else {
            this.f2760c = false;
        }
        boolean z = cursor != null;
        this.f2761d = cursor;
        this.f2759b = z;
        this.f2762e = context;
        this.f2763f = z ? cursor.getColumnIndexOrThrow("_id") : -1;
        if ((i2 & 2) == 2) {
            this.f2764g = new C0049a();
            this.f2765h = new b();
        } else {
            this.f2764g = null;
            this.f2765h = null;
        }
        if (z) {
            C0049a c0049a = this.f2764g;
            if (c0049a != null) {
                cursor.registerContentObserver(c0049a);
            }
            DataSetObserver dataSetObserver = this.f2765h;
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
        if (!this.f2760c || (cursor = this.f2761d) == null || cursor.isClosed()) {
            return;
        }
        this.f2759b = this.f2761d.requery();
    }

    public Cursor f(Cursor cursor) {
        Cursor cursor2 = this.f2761d;
        if (cursor == cursor2) {
            return null;
        }
        if (cursor2 != null) {
            C0049a c0049a = this.f2764g;
            if (c0049a != null) {
                cursor2.unregisterContentObserver(c0049a);
            }
            DataSetObserver dataSetObserver = this.f2765h;
            if (dataSetObserver != null) {
                cursor2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.f2761d = cursor;
        if (cursor != null) {
            C0049a c0049a2 = this.f2764g;
            if (c0049a2 != null) {
                cursor.registerContentObserver(c0049a2);
            }
            DataSetObserver dataSetObserver2 = this.f2765h;
            if (dataSetObserver2 != null) {
                cursor.registerDataSetObserver(dataSetObserver2);
            }
            this.f2763f = cursor.getColumnIndexOrThrow("_id");
            this.f2759b = true;
            notifyDataSetChanged();
        } else {
            this.f2763f = -1;
            this.f2759b = false;
            notifyDataSetInvalidated();
        }
        return cursor2;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        Cursor cursor;
        if (!this.f2759b || (cursor = this.f2761d) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override // b.h.a.b.a
    public Cursor getCursor() {
        return this.f2761d;
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i2, View view, ViewGroup viewGroup) {
        if (!this.f2759b) {
            return null;
        }
        this.f2761d.moveToPosition(i2);
        if (view == null) {
            view = c(this.f2762e, this.f2761d, viewGroup);
        }
        a(view, this.f2762e, this.f2761d);
        return view;
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        if (this.f2766i == null) {
            this.f2766i = new b.h.a.b(this);
        }
        return this.f2766i;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        Cursor cursor;
        if (!this.f2759b || (cursor = this.f2761d) == null) {
            return null;
        }
        cursor.moveToPosition(i2);
        return this.f2761d;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        Cursor cursor;
        if (this.f2759b && (cursor = this.f2761d) != null && cursor.moveToPosition(i2)) {
            return this.f2761d.getLong(this.f2763f);
        }
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        if (!this.f2759b) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (this.f2761d.moveToPosition(i2)) {
            if (view == null) {
                view = d(this.f2762e, this.f2761d, viewGroup);
            }
            a(view, this.f2762e, this.f2761d);
            return view;
        }
        throw new IllegalStateException("couldn't move cursor to position " + i2);
    }
}
