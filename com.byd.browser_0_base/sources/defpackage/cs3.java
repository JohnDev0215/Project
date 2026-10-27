package defpackage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;
import defpackage.ds3;

/* JADX INFO: compiled from: WebappDataStorage.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cs3 extends i9<Bitmap> {
    public final /* synthetic */ ds3.a h;
    public final /* synthetic */ ds3 i;

    public cs3(ds3 ds3Var, qs3 qs3Var) {
        this.i = ds3Var;
        this.h = qs3Var;
    }

    @Override // defpackage.i9
    public final Bitmap b() {
        String string = this.i.b.getString("splash_icon", null);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        byte[] bArrDecode = Base64.decode(string, 0);
        return BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length);
    }

    @Override // defpackage.i9
    public final void l(Bitmap bitmap) {
        qs3 qs3Var = (qs3) this.h;
        qs3Var.getClass();
        int i = qs3Var.b;
        qs3Var.c.d(qs3Var.a, i, bitmap, false);
    }
}
