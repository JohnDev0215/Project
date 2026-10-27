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
    public boolean f1192b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1193c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Cursor f1194d;
    public Context e;
    public int f;
    public C0027a g;
    public DataSetObserver h;
    public b.h.a.b i;

    /* JADX INFO: renamed from: b.h.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CursorAdapter.java */
    public class C0027a extends ContentObserver {
        public C0027a() {
            super(new Handler());
        }

        @Override // android.database.ContentObserver
        public boolean deliverSelfNotifications() {
            return true;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            a.this.i();
        }
    }

    /* JADX INFO: compiled from: CursorAdapter.java */
    public class b extends DataSetObserver {
        public b() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            a aVar = a.this;
            aVar.f1192b = true;
            aVar.notifyDataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            a aVar = a.this;
            aVar.f1192b = false;
            aVar.notifyDataSetInvalidated();
        }
    }

    public a(Context context, Cursor cursor, boolean z) {
        f(context, cursor, z ? 1 : 2);
    }

    public abstract CharSequence a(Cursor cursor);

    public void b(Cursor cursor) {
        Cursor cursorJ = j(cursor);
        if (cursorJ != null) {
            cursorJ.close();
        }
    }

    @Override // b.h.a.b.a
    public Cursor c() {
        return this.f1194d;
    }

    public abstract void e(View view, Context context, Cursor cursor);

    public void f(Context context, Cursor cursor, int i) {
        if ((i & 1) == 1) {
            i |= 2;
            this.f1193c = true;
        } else {
            this.f1193c = false;
        }
        boolean z = cursor != null;
        this.f1194d = cursor;
        this.f1192b = z;
        this.e = context;
        this.f = z ? cursor.getColumnIndexOrThrow("_id") : -1;
        if ((i & 2) == 2) {
            this.g = new C0027a();
            this.h = new b();
        } else {
            this.g = null;
            this.h = null;
        }
        if (z) {
            C0027a c0027a = this.g;
            if (c0027a != null) {
                cursor.registerContentObserver(c0027a);
            }
            DataSetObserver dataSetObserver = this.h;
            if (dataSetObserver != null) {
                cursor.registerDataSetObserver(dataSetObserver);
            }
        }
    }

    public abstract View g(Context context, Cursor cursor, ViewGroup viewGroup);

    @Override // android.widget.Adapter
    public int getCount() {
        Cursor cursor;
        if (!this.f1192b || (cursor = this.f1194d) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        if (!this.f1192b) {
            return null;
        }
        this.f1194d.moveToPosition(i);
        if (view == null) {
            view = g(this.e, this.f1194d, viewGroup);
        }
        e(view, this.e, this.f1194d);
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
        if (!this.f1192b || (cursor = this.f1194d) == null) {
            return null;
        }
        cursor.moveToPosition(i);
        return this.f1194d;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        Cursor cursor;
        if (this.f1192b && (cursor = this.f1194d) != null && cursor.moveToPosition(i)) {
            return this.f1194d.getLong(this.f);
        }
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (!this.f1192b) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (this.f1194d.moveToPosition(i)) {
            if (view == null) {
                view = h(this.e, this.f1194d, viewGroup);
            }
            e(view, this.e, this.f1194d);
            return view;
        }
        throw new IllegalStateException("couldn't move cursor to position " + i);
    }

    public abstract View h(Context context, Cursor cursor, ViewGroup viewGroup);

    public void i() {
        Cursor cursor;
        if (!this.f1193c || (cursor = this.f1194d) == null || cursor.isClosed()) {
            return;
        }
        this.f1192b = this.f1194d.requery();
    }

    public Cursor j(Cursor cursor) {
        Cursor cursor2 = this.f1194d;
        if (cursor == cursor2) {
            return null;
        }
        if (cursor2 != null) {
            C0027a c0027a = this.g;
            if (c0027a != null) {
                cursor2.unregisterContentObserver(c0027a);
            }
            DataSetObserver dataSetObserver = this.h;
            if (dataSetObserver != null) {
                cursor2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.f1194d = cursor;
        if (cursor != null) {
            C0027a c0027a2 = this.g;
            if (c0027a2 != null) {
                cursor.registerContentObserver(c0027a2);
            }
            DataSetObserver dataSetObserver2 = this.h;
            if (dataSetObserver2 != null) {
                cursor.registerDataSetObserver(dataSetObserver2);
            }
            this.f = cursor.getColumnIndexOrThrow("_id");
            this.f1192b = true;
            notifyDataSetChanged();
        } else {
            this.f = -1;
            this.f1192b = false;
            notifyDataSetInvalidated();
        }
        return cursor2;
    }
}
