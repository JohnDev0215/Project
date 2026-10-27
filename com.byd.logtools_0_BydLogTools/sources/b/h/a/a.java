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
    protected boolean f2602b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected boolean f2603c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected Cursor f2604d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected Context f2605e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected int f2606f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected C0043a f2607g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    protected DataSetObserver f2608h;
    protected b.h.a.b i;

    /* JADX INFO: renamed from: b.h.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CursorAdapter.java */
    private class C0043a extends ContentObserver {
        C0043a() {
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
            aVar.f2602b = true;
            aVar.notifyDataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            a aVar = a.this;
            aVar.f2602b = false;
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
            this.f2603c = true;
        } else {
            this.f2603c = false;
        }
        boolean z = cursor != null;
        this.f2604d = cursor;
        this.f2602b = z;
        this.f2605e = context;
        this.f2606f = z ? cursor.getColumnIndexOrThrow("_id") : -1;
        if ((i & 2) == 2) {
            this.f2607g = new C0043a();
            this.f2608h = new b();
        } else {
            this.f2607g = null;
            this.f2608h = null;
        }
        if (z) {
            C0043a c0043a = this.f2607g;
            if (c0043a != null) {
                cursor.registerContentObserver(c0043a);
            }
            DataSetObserver dataSetObserver = this.f2608h;
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
        if (!this.f2603c || (cursor = this.f2604d) == null || cursor.isClosed()) {
            return;
        }
        this.f2602b = this.f2604d.requery();
    }

    public Cursor f(Cursor cursor) {
        Cursor cursor2 = this.f2604d;
        if (cursor == cursor2) {
            return null;
        }
        if (cursor2 != null) {
            C0043a c0043a = this.f2607g;
            if (c0043a != null) {
                cursor2.unregisterContentObserver(c0043a);
            }
            DataSetObserver dataSetObserver = this.f2608h;
            if (dataSetObserver != null) {
                cursor2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.f2604d = cursor;
        if (cursor != null) {
            C0043a c0043a2 = this.f2607g;
            if (c0043a2 != null) {
                cursor.registerContentObserver(c0043a2);
            }
            DataSetObserver dataSetObserver2 = this.f2608h;
            if (dataSetObserver2 != null) {
                cursor.registerDataSetObserver(dataSetObserver2);
            }
            this.f2606f = cursor.getColumnIndexOrThrow("_id");
            this.f2602b = true;
            notifyDataSetChanged();
        } else {
            this.f2606f = -1;
            this.f2602b = false;
            notifyDataSetInvalidated();
        }
        return cursor2;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        Cursor cursor;
        if (!this.f2602b || (cursor = this.f2604d) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override // b.h.a.b.a
    public Cursor getCursor() {
        return this.f2604d;
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        if (!this.f2602b) {
            return null;
        }
        this.f2604d.moveToPosition(i);
        if (view == null) {
            view = c(this.f2605e, this.f2604d, viewGroup);
        }
        a(view, this.f2605e, this.f2604d);
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
        if (!this.f2602b || (cursor = this.f2604d) == null) {
            return null;
        }
        cursor.moveToPosition(i);
        return this.f2604d;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        Cursor cursor;
        if (this.f2602b && (cursor = this.f2604d) != null && cursor.moveToPosition(i)) {
            return this.f2604d.getLong(this.f2606f);
        }
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (!this.f2602b) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (this.f2604d.moveToPosition(i)) {
            if (view == null) {
                view = d(this.f2605e, this.f2604d, viewGroup);
            }
            a(view, this.f2605e, this.f2604d);
            return view;
        }
        throw new IllegalStateException("couldn't move cursor to position " + i);
    }
}
