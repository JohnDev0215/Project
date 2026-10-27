package a.i.a;

import a.i.a.b;
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

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f882a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f883b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Cursor f884c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Context f885d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f886e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public C0019a f887f;
    public DataSetObserver g;
    public a.i.a.b h;

    /* JADX INFO: renamed from: a.i.a.a$a, reason: collision with other inner class name */
    public class C0019a extends ContentObserver {
        public C0019a() {
            super(new Handler());
        }

        @Override // android.database.ContentObserver
        public boolean deliverSelfNotifications() {
            return true;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            Cursor cursor;
            a aVar = a.this;
            if (!aVar.f883b || (cursor = aVar.f884c) == null || cursor.isClosed()) {
                return;
            }
            aVar.f882a = aVar.f884c.requery();
        }
    }

    public class b extends DataSetObserver {
        public b() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            a aVar = a.this;
            aVar.f882a = true;
            aVar.notifyDataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            a aVar = a.this;
            aVar.f882a = false;
            aVar.notifyDataSetInvalidated();
        }
    }

    public a(Context context, Cursor cursor, boolean z) {
        b bVar;
        int i = z ? 1 : 2;
        if ((i & 1) == 1) {
            i |= 2;
            this.f883b = true;
        } else {
            this.f883b = false;
        }
        boolean z2 = cursor != null;
        this.f884c = cursor;
        this.f882a = z2;
        this.f885d = context;
        this.f886e = z2 ? cursor.getColumnIndexOrThrow("_id") : -1;
        if ((i & 2) == 2) {
            this.f887f = new C0019a();
            bVar = new b();
        } else {
            bVar = null;
            this.f887f = null;
        }
        this.g = bVar;
        if (z2) {
            C0019a c0019a = this.f887f;
            if (c0019a != null) {
                cursor.registerContentObserver(c0019a);
            }
            DataSetObserver dataSetObserver = this.g;
            if (dataSetObserver != null) {
                cursor.registerDataSetObserver(dataSetObserver);
            }
        }
    }

    public abstract void a(View view, Context context, Cursor cursor);

    public void b(Cursor cursor) {
        Cursor cursor2 = this.f884c;
        if (cursor == cursor2) {
            cursor2 = null;
        } else {
            if (cursor2 != null) {
                C0019a c0019a = this.f887f;
                if (c0019a != null) {
                    cursor2.unregisterContentObserver(c0019a);
                }
                DataSetObserver dataSetObserver = this.g;
                if (dataSetObserver != null) {
                    cursor2.unregisterDataSetObserver(dataSetObserver);
                }
            }
            this.f884c = cursor;
            if (cursor != null) {
                C0019a c0019a2 = this.f887f;
                if (c0019a2 != null) {
                    cursor.registerContentObserver(c0019a2);
                }
                DataSetObserver dataSetObserver2 = this.g;
                if (dataSetObserver2 != null) {
                    cursor.registerDataSetObserver(dataSetObserver2);
                }
                this.f886e = cursor.getColumnIndexOrThrow("_id");
                this.f882a = true;
                notifyDataSetChanged();
            } else {
                this.f886e = -1;
                this.f882a = false;
                notifyDataSetInvalidated();
            }
        }
        if (cursor2 != null) {
            cursor2.close();
        }
    }

    public abstract CharSequence c(Cursor cursor);

    public abstract View d(Context context, Cursor cursor, ViewGroup viewGroup);

    @Override // android.widget.Adapter
    public int getCount() {
        Cursor cursor;
        if (!this.f882a || (cursor = this.f884c) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        if (!this.f882a) {
            return null;
        }
        this.f884c.moveToPosition(i);
        if (view == null) {
            c cVar = (c) this;
            view = cVar.k.inflate(cVar.j, viewGroup, false);
        }
        a(view, this.f885d, this.f884c);
        return view;
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        if (this.h == null) {
            this.h = new a.i.a.b(this);
        }
        return this.h;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        Cursor cursor;
        if (!this.f882a || (cursor = this.f884c) == null) {
            return null;
        }
        cursor.moveToPosition(i);
        return this.f884c;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        Cursor cursor;
        if (this.f882a && (cursor = this.f884c) != null && cursor.moveToPosition(i)) {
            return this.f884c.getLong(this.f886e);
        }
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (!this.f882a) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (this.f884c.moveToPosition(i)) {
            if (view == null) {
                view = d(this.f885d, this.f884c, viewGroup);
            }
            a(view, this.f885d, this.f884c);
            return view;
        }
        throw new IllegalStateException("couldn't move cursor to position " + i);
    }
}
