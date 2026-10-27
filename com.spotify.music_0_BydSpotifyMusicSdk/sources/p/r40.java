package p;

import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
public final class r40 extends ContentObserver {
    public final /* synthetic */ int a = 0;
    public final /* synthetic */ Object b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r40(s40 s40Var) {
        super(new Handler());
        this.b = s40Var;
    }

    @Override // android.database.ContentObserver
    public final boolean deliverSelfNotifications() {
        switch (this.a) {
            case 0:
                return true;
            default:
                return super.deliverSelfNotifications();
        }
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z, Uri uri) {
        switch (this.a) {
            case 1:
                ((rv0) this.b).c();
                break;
            default:
                super.onChange(z, uri);
                break;
        }
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        Cursor cursor;
        switch (this.a) {
            case 0:
                s40 s40Var = (s40) this.b;
                if (s40Var.e && (cursor = s40Var.k) != null && !cursor.isClosed()) {
                    s40Var.c = s40Var.k.requery();
                    break;
                }
                break;
            default:
                super.onChange(z);
                break;
        }
    }
}
