package androidx.loader.content;

import android.content.Context;
import android.database.Cursor;
import androidx.loader.content.Loader;
import defpackage.am;
import defpackage.ty;
import defpackage.us1;

/* JADX INFO: loaded from: classes.dex */
public class CursorLoader extends AsyncTaskLoader<Cursor> {
    public final Loader<Cursor>.a i;
    public Cursor j;
    public am k;

    public CursorLoader(Context context) {
        super(context);
        this.i = new Loader.a();
    }

    @Override // androidx.loader.content.Loader
    public final void a(Object obj) {
        Cursor cursor = (Cursor) obj;
        if (this.d) {
            if (cursor != null) {
                cursor.close();
                return;
            }
            return;
        }
        Cursor cursor2 = this.j;
        this.j = cursor;
        if (cursor2 == null || cursor2 == cursor || cursor2.isClosed()) {
            return;
        }
        cursor2.close();
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    public final void c() {
        synchronized (this) {
            am amVar = this.k;
            if (amVar != null) {
                amVar.a();
            }
        }
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    public final Cursor e() {
        synchronized (this) {
            if (this.h != null) {
                throw new us1();
            }
            this.k = new am();
        }
        try {
            Cursor cursorA = ty.a(this.a.getContentResolver(), this.k);
            if (cursorA != null) {
                try {
                    cursorA.getCount();
                    cursorA.registerContentObserver(this.i);
                } catch (RuntimeException e) {
                    cursorA.close();
                    throw e;
                }
            }
            synchronized (this) {
                this.k = null;
            }
            return cursorA;
        } catch (Throwable th) {
            synchronized (this) {
                this.k = null;
                throw th;
            }
        }
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    public final void g(Cursor cursor) {
        Cursor cursor2 = cursor;
        if (cursor2 == null || cursor2.isClosed()) {
            return;
        }
        cursor2.close();
    }
}
