package l0;

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
import l0.b;

/* JADX INFO: compiled from: CursorAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a extends BaseAdapter implements Filterable, b.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected boolean f7880e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected boolean f7881g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    protected Cursor f7882h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    protected Context f7883i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    protected int f7884j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    protected C0113a f7885k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    protected DataSetObserver f7886l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    protected l0.b f7887m;

    /* JADX INFO: renamed from: l0.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CursorAdapter.java */
    private class C0113a extends ContentObserver {
        C0113a() {
            super(new Handler());
        }

        @Override // android.database.ContentObserver
        public boolean deliverSelfNotifications() {
            return true;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z6) {
            a.this.i();
        }
    }

    /* JADX INFO: compiled from: CursorAdapter.java */
    private class b extends DataSetObserver {
        b() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            a aVar = a.this;
            aVar.f7880e = true;
            aVar.notifyDataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            a aVar = a.this;
            aVar.f7880e = false;
            aVar.notifyDataSetInvalidated();
        }
    }

    public a(Context context, Cursor cursor, boolean z6) {
        f(context, cursor, z6 ? 1 : 2);
    }

    public abstract CharSequence a(Cursor cursor);

    public void b(Cursor cursor) {
        Cursor cursorJ = j(cursor);
        if (cursorJ != null) {
            cursorJ.close();
        }
    }

    @Override // l0.b.a
    public Cursor c() {
        return this.f7882h;
    }

    public abstract void e(View view, Context context, Cursor cursor);

    void f(Context context, Cursor cursor, int i7) {
        if ((i7 & 1) == 1) {
            i7 |= 2;
            this.f7881g = true;
        } else {
            this.f7881g = false;
        }
        boolean z6 = cursor != null;
        this.f7882h = cursor;
        this.f7880e = z6;
        this.f7883i = context;
        this.f7884j = z6 ? cursor.getColumnIndexOrThrow("_id") : -1;
        if ((i7 & 2) == 2) {
            this.f7885k = new C0113a();
            this.f7886l = new b();
        } else {
            this.f7885k = null;
            this.f7886l = null;
        }
        if (z6) {
            C0113a c0113a = this.f7885k;
            if (c0113a != null) {
                cursor.registerContentObserver(c0113a);
            }
            DataSetObserver dataSetObserver = this.f7886l;
            if (dataSetObserver != null) {
                cursor.registerDataSetObserver(dataSetObserver);
            }
        }
    }

    public abstract View g(Context context, Cursor cursor, ViewGroup viewGroup);

    @Override // android.widget.Adapter
    public int getCount() {
        Cursor cursor;
        if (!this.f7880e || (cursor = this.f7882h) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i7, View view, ViewGroup viewGroup) {
        if (!this.f7880e) {
            return null;
        }
        this.f7882h.moveToPosition(i7);
        if (view == null) {
            view = g(this.f7883i, this.f7882h, viewGroup);
        }
        e(view, this.f7883i, this.f7882h);
        return view;
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        if (this.f7887m == null) {
            this.f7887m = new l0.b(this);
        }
        return this.f7887m;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i7) {
        Cursor cursor;
        if (!this.f7880e || (cursor = this.f7882h) == null) {
            return null;
        }
        cursor.moveToPosition(i7);
        return this.f7882h;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i7) {
        Cursor cursor;
        if (this.f7880e && (cursor = this.f7882h) != null && cursor.moveToPosition(i7)) {
            return this.f7882h.getLong(this.f7884j);
        }
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i7, View view, ViewGroup viewGroup) {
        if (!this.f7880e) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (this.f7882h.moveToPosition(i7)) {
            if (view == null) {
                view = h(this.f7883i, this.f7882h, viewGroup);
            }
            e(view, this.f7883i, this.f7882h);
            return view;
        }
        throw new IllegalStateException("couldn't move cursor to position " + i7);
    }

    public abstract View h(Context context, Cursor cursor, ViewGroup viewGroup);

    protected void i() {
        Cursor cursor;
        if (!this.f7881g || (cursor = this.f7882h) == null || cursor.isClosed()) {
            return;
        }
        this.f7880e = this.f7882h.requery();
    }

    public Cursor j(Cursor cursor) {
        Cursor cursor2 = this.f7882h;
        if (cursor == cursor2) {
            return null;
        }
        if (cursor2 != null) {
            C0113a c0113a = this.f7885k;
            if (c0113a != null) {
                cursor2.unregisterContentObserver(c0113a);
            }
            DataSetObserver dataSetObserver = this.f7886l;
            if (dataSetObserver != null) {
                cursor2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.f7882h = cursor;
        if (cursor != null) {
            C0113a c0113a2 = this.f7885k;
            if (c0113a2 != null) {
                cursor.registerContentObserver(c0113a2);
            }
            DataSetObserver dataSetObserver2 = this.f7886l;
            if (dataSetObserver2 != null) {
                cursor.registerDataSetObserver(dataSetObserver2);
            }
            this.f7884j = cursor.getColumnIndexOrThrow("_id");
            this.f7880e = true;
            notifyDataSetChanged();
        } else {
            this.f7884j = -1;
            this.f7880e = false;
            notifyDataSetInvalidated();
        }
        return cursor2;
    }
}
