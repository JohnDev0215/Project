package a.h.a;

import a.h.a.b;
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

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends BaseAdapter implements Filterable, b.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected boolean f449b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected boolean f450c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected Cursor f451d;
    protected Context e;
    protected int f;
    protected C0016a g;
    protected DataSetObserver h;
    protected a.h.a.b i;

    /* JADX INFO: renamed from: a.h.a.a$a, reason: collision with other inner class name */
    private class C0016a extends ContentObserver {
        C0016a() {
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

    private class b extends DataSetObserver {
        b() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            a aVar = a.this;
            aVar.f449b = true;
            aVar.notifyDataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            a aVar = a.this;
            aVar.f449b = false;
            aVar.notifyDataSetInvalidated();
        }
    }

    public a(Context context, Cursor cursor, boolean z) {
        b(context, cursor, z ? 1 : 2);
    }

    public abstract void a(View view, Context context, Cursor cursor);

    void b(Context context, Cursor cursor, int i) {
        b bVar;
        if ((i & 1) == 1) {
            i |= 2;
            this.f450c = true;
        } else {
            this.f450c = false;
        }
        boolean z = cursor != null;
        this.f451d = cursor;
        this.f449b = z;
        this.e = context;
        this.f = z ? cursor.getColumnIndexOrThrow("_id") : -1;
        if ((i & 2) == 2) {
            this.g = new C0016a();
            bVar = new b();
        } else {
            bVar = null;
            this.g = null;
        }
        this.h = bVar;
        if (z) {
            C0016a c0016a = this.g;
            if (c0016a != null) {
                cursor.registerContentObserver(c0016a);
            }
            DataSetObserver dataSetObserver = this.h;
            if (dataSetObserver != null) {
                cursor.registerDataSetObserver(dataSetObserver);
            }
        }
    }

    public abstract View c(Context context, Cursor cursor, ViewGroup viewGroup);

    @Override // a.h.a.b.a
    public void changeCursor(Cursor cursor) {
        Cursor cursorF = f(cursor);
        if (cursorF != null) {
            cursorF.close();
        }
    }

    @Override // a.h.a.b.a
    public abstract CharSequence convertToString(Cursor cursor);

    public abstract View d(Context context, Cursor cursor, ViewGroup viewGroup);

    protected void e() {
        Cursor cursor;
        if (!this.f450c || (cursor = this.f451d) == null || cursor.isClosed()) {
            return;
        }
        this.f449b = this.f451d.requery();
    }

    public Cursor f(Cursor cursor) {
        Cursor cursor2 = this.f451d;
        if (cursor == cursor2) {
            return null;
        }
        if (cursor2 != null) {
            C0016a c0016a = this.g;
            if (c0016a != null) {
                cursor2.unregisterContentObserver(c0016a);
            }
            DataSetObserver dataSetObserver = this.h;
            if (dataSetObserver != null) {
                cursor2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.f451d = cursor;
        if (cursor != null) {
            C0016a c0016a2 = this.g;
            if (c0016a2 != null) {
                cursor.registerContentObserver(c0016a2);
            }
            DataSetObserver dataSetObserver2 = this.h;
            if (dataSetObserver2 != null) {
                cursor.registerDataSetObserver(dataSetObserver2);
            }
            this.f = cursor.getColumnIndexOrThrow("_id");
            this.f449b = true;
            notifyDataSetChanged();
        } else {
            this.f = -1;
            this.f449b = false;
            notifyDataSetInvalidated();
        }
        return cursor2;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        Cursor cursor;
        if (!this.f449b || (cursor = this.f451d) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override // a.h.a.b.a
    public Cursor getCursor() {
        return this.f451d;
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        if (!this.f449b) {
            return null;
        }
        this.f451d.moveToPosition(i);
        if (view == null) {
            view = c(this.e, this.f451d, viewGroup);
        }
        a(view, this.e, this.f451d);
        return view;
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        if (this.i == null) {
            this.i = new a.h.a.b(this);
        }
        return this.i;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        Cursor cursor;
        if (!this.f449b || (cursor = this.f451d) == null) {
            return null;
        }
        cursor.moveToPosition(i);
        return this.f451d;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        Cursor cursor;
        if (this.f449b && (cursor = this.f451d) != null && cursor.moveToPosition(i)) {
            return this.f451d.getLong(this.f);
        }
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (!this.f449b) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (this.f451d.moveToPosition(i)) {
            if (view == null) {
                view = d(this.e, this.f451d, viewGroup);
            }
            a(view, this.e, this.f451d);
            return view;
        }
        throw new IllegalStateException("couldn't move cursor to position " + i);
    }
}
