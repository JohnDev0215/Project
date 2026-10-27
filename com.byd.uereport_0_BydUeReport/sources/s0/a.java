package s0;

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
import s0.b;

/* JADX INFO: compiled from: CursorAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a extends BaseAdapter implements Filterable, b.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f7869d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f7870e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Cursor f7871f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Context f7872g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f7873h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public C0116a f7874i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public DataSetObserver f7875j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public s0.b f7876k;

    /* JADX INFO: renamed from: s0.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CursorAdapter.java */
    public class C0116a extends ContentObserver {
        public C0116a() {
            super(new Handler());
        }

        @Override // android.database.ContentObserver
        public boolean deliverSelfNotifications() {
            return true;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z7) {
            a.this.e();
        }
    }

    /* JADX INFO: compiled from: CursorAdapter.java */
    public class b extends DataSetObserver {
        public b() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            a aVar = a.this;
            aVar.f7869d = true;
            aVar.notifyDataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            a aVar = a.this;
            aVar.f7869d = false;
            aVar.notifyDataSetInvalidated();
        }
    }

    public a(Context context, Cursor cursor, boolean z7) {
        b(context, cursor, z7 ? 1 : 2);
    }

    public abstract void a(View view, Context context, Cursor cursor);

    public void b(Context context, Cursor cursor, int i8) {
        if ((i8 & 1) == 1) {
            i8 |= 2;
            this.f7870e = true;
        } else {
            this.f7870e = false;
        }
        boolean z7 = cursor != null;
        this.f7871f = cursor;
        this.f7869d = z7;
        this.f7872g = context;
        this.f7873h = z7 ? cursor.getColumnIndexOrThrow("_id") : -1;
        if ((i8 & 2) == 2) {
            this.f7874i = new C0116a();
            this.f7875j = new b();
        } else {
            this.f7874i = null;
            this.f7875j = null;
        }
        if (z7) {
            C0116a c0116a = this.f7874i;
            if (c0116a != null) {
                cursor.registerContentObserver(c0116a);
            }
            DataSetObserver dataSetObserver = this.f7875j;
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

    public void e() {
        Cursor cursor;
        if (!this.f7870e || (cursor = this.f7871f) == null || cursor.isClosed()) {
            return;
        }
        this.f7869d = this.f7871f.requery();
    }

    public Cursor f(Cursor cursor) {
        Cursor cursor2 = this.f7871f;
        if (cursor == cursor2) {
            return null;
        }
        if (cursor2 != null) {
            C0116a c0116a = this.f7874i;
            if (c0116a != null) {
                cursor2.unregisterContentObserver(c0116a);
            }
            DataSetObserver dataSetObserver = this.f7875j;
            if (dataSetObserver != null) {
                cursor2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.f7871f = cursor;
        if (cursor != null) {
            C0116a c0116a2 = this.f7874i;
            if (c0116a2 != null) {
                cursor.registerContentObserver(c0116a2);
            }
            DataSetObserver dataSetObserver2 = this.f7875j;
            if (dataSetObserver2 != null) {
                cursor.registerDataSetObserver(dataSetObserver2);
            }
            this.f7873h = cursor.getColumnIndexOrThrow("_id");
            this.f7869d = true;
            notifyDataSetChanged();
        } else {
            this.f7873h = -1;
            this.f7869d = false;
            notifyDataSetInvalidated();
        }
        return cursor2;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        Cursor cursor;
        if (!this.f7869d || (cursor = this.f7871f) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override // s0.b.a
    public Cursor getCursor() {
        return this.f7871f;
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i8, View view, ViewGroup viewGroup) {
        if (!this.f7869d) {
            return null;
        }
        this.f7871f.moveToPosition(i8);
        if (view == null) {
            view = c(this.f7872g, this.f7871f, viewGroup);
        }
        a(view, this.f7872g, this.f7871f);
        return view;
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        if (this.f7876k == null) {
            this.f7876k = new s0.b(this);
        }
        return this.f7876k;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i8) {
        Cursor cursor;
        if (!this.f7869d || (cursor = this.f7871f) == null) {
            return null;
        }
        cursor.moveToPosition(i8);
        return this.f7871f;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i8) {
        Cursor cursor;
        if (this.f7869d && (cursor = this.f7871f) != null && cursor.moveToPosition(i8)) {
            return this.f7871f.getLong(this.f7873h);
        }
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i8, View view, ViewGroup viewGroup) {
        if (!this.f7869d) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (this.f7871f.moveToPosition(i8)) {
            if (view == null) {
                view = d(this.f7872g, this.f7871f, viewGroup);
            }
            a(view, this.f7872g, this.f7871f);
            return view;
        }
        throw new IllegalStateException("couldn't move cursor to position " + i8);
    }
}
