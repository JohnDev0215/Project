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
    protected boolean f2027b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected boolean f2028c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected Cursor f2029d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected Context f2030e;
    protected int f;
    protected C0040a g;
    protected DataSetObserver h;
    protected b.h.a.b i;

    /* JADX INFO: renamed from: b.h.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CursorAdapter.java */
    private class C0040a extends ContentObserver {
        C0040a() {
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
            aVar.f2027b = true;
            aVar.notifyDataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            a aVar = a.this;
            aVar.f2027b = false;
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
            this.f2028c = true;
        } else {
            this.f2028c = false;
        }
        boolean z = cursor != null;
        this.f2029d = cursor;
        this.f2027b = z;
        this.f2030e = context;
        this.f = z ? cursor.getColumnIndexOrThrow("_id") : -1;
        if ((i & 2) == 2) {
            this.g = new C0040a();
            this.h = new b();
        } else {
            this.g = null;
            this.h = null;
        }
        if (z) {
            C0040a c0040a = this.g;
            if (c0040a != null) {
                cursor.registerContentObserver(c0040a);
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
        if (!this.f2028c || (cursor = this.f2029d) == null || cursor.isClosed()) {
            return;
        }
        this.f2027b = this.f2029d.requery();
    }

    public Cursor f(Cursor cursor) {
        Cursor cursor2 = this.f2029d;
        if (cursor == cursor2) {
            return null;
        }
        if (cursor2 != null) {
            C0040a c0040a = this.g;
            if (c0040a != null) {
                cursor2.unregisterContentObserver(c0040a);
            }
            DataSetObserver dataSetObserver = this.h;
            if (dataSetObserver != null) {
                cursor2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.f2029d = cursor;
        if (cursor != null) {
            C0040a c0040a2 = this.g;
            if (c0040a2 != null) {
                cursor.registerContentObserver(c0040a2);
            }
            DataSetObserver dataSetObserver2 = this.h;
            if (dataSetObserver2 != null) {
                cursor.registerDataSetObserver(dataSetObserver2);
            }
            this.f = cursor.getColumnIndexOrThrow("_id");
            this.f2027b = true;
            notifyDataSetChanged();
        } else {
            this.f = -1;
            this.f2027b = false;
            notifyDataSetInvalidated();
        }
        return cursor2;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        Cursor cursor;
        if (!this.f2027b || (cursor = this.f2029d) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override // b.h.a.b.a
    public Cursor getCursor() {
        return this.f2029d;
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        if (!this.f2027b) {
            return null;
        }
        this.f2029d.moveToPosition(i);
        if (view == null) {
            view = c(this.f2030e, this.f2029d, viewGroup);
        }
        a(view, this.f2030e, this.f2029d);
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
        if (!this.f2027b || (cursor = this.f2029d) == null) {
            return null;
        }
        cursor.moveToPosition(i);
        return this.f2029d;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        Cursor cursor;
        if (this.f2027b && (cursor = this.f2029d) != null && cursor.moveToPosition(i)) {
            return this.f2029d.getLong(this.f);
        }
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (!this.f2027b) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (this.f2029d.moveToPosition(i)) {
            if (view == null) {
                view = d(this.f2030e, this.f2029d, viewGroup);
            }
            a(view, this.f2030e, this.f2029d);
            return view;
        }
        throw new IllegalStateException("couldn't move cursor to position " + i);
    }
}
