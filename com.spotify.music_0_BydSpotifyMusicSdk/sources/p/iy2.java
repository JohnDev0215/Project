package p;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Base64;
import com.google.android.datatransport.runtime.synchronization.SynchronizationException;
import com.google.firebase.components.ComponentRegistrar;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class iy2 implements hi0, h62, g91, cw, i80 {
    public final /* synthetic */ int c;

    public /* synthetic */ iy2(int i) {
        this.c = i;
    }

    @Override // p.h62
    public final Object apply(Object obj) {
        switch (this.c) {
            case 3:
                ae0 ae0Var = j62.n;
                return (List) j62.o0(((SQLiteDatabase) obj).rawQuery("SELECT distinct t._id, t.backend_name, t.priority, t.extras FROM transport_contexts AS t, events AS e WHERE e.context_id = t._id", new String[0]), new iy2(7));
            case 4:
                ae0 ae0Var2 = j62.n;
                throw new SynchronizationException("Timed out while trying to open db.", (Throwable) obj);
            case 5:
                Cursor cursor = (Cursor) obj;
                ae0 ae0Var3 = j62.n;
                if (cursor.moveToNext()) {
                    return Long.valueOf(cursor.getLong(0));
                }
                return 0L;
            case 6:
                ae0 ae0Var4 = j62.n;
                throw new SynchronizationException("Timed out while trying to acquire the lock.", (Throwable) obj);
            case 7:
                Cursor cursor2 = (Cursor) obj;
                ae0 ae0Var5 = j62.n;
                ArrayList arrayList = new ArrayList();
                while (cursor2.moveToNext()) {
                    v4 v4VarA = wi.a();
                    v4VarA.M(cursor2.getString(1));
                    v4VarA.N(gx1.b(cursor2.getInt(2)));
                    String string = cursor2.getString(3);
                    v4VarA.k = string == null ? null : Base64.decode(string, 0);
                    arrayList.add(v4VarA.e());
                }
                return arrayList;
            case 8:
                ae0 ae0Var6 = j62.n;
                return Boolean.valueOf(((Cursor) obj).getCount() > 0);
            case 9:
                return Boolean.valueOf(((Cursor) obj).moveToNext());
            case 10:
                Cursor cursor3 = (Cursor) obj;
                ae0 ae0Var7 = j62.n;
                ArrayList arrayList2 = new ArrayList();
                int length = 0;
                while (cursor3.moveToNext()) {
                    byte[] blob = cursor3.getBlob(0);
                    arrayList2.add(blob);
                    length += blob.length;
                }
                byte[] bArr = new byte[length];
                int length2 = 0;
                for (int i = 0; i < arrayList2.size(); i++) {
                    byte[] bArr2 = (byte[]) arrayList2.get(i);
                    System.arraycopy(bArr2, 0, bArr, length2, bArr2.length);
                    length2 += bArr2.length;
                }
                return bArr;
            case 11:
                Cursor cursor4 = (Cursor) obj;
                ae0 ae0Var8 = j62.n;
                if (cursor4.moveToNext()) {
                    return Long.valueOf(cursor4.getLong(0));
                }
                return null;
            default:
                z50.b.getClass();
                return i40.a.t((g40) obj).getBytes(Charset.forName("UTF-8"));
        }
    }

    @Override // p.hi0
    public final ii0 d() {
        return ii0.a;
    }

    @Override // p.i80
    public final void e(yy1 yy1Var) {
    }

    @Override // p.cw
    public final List f(ComponentRegistrar componentRegistrar) {
        return componentRegistrar.getComponents();
    }
}
