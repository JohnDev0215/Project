package defpackage;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputContentInfo;
import defpackage.f01;
import defpackage.oy;

/* JADX INFO: compiled from: InputConnectionCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class d01 extends InputConnectionWrapper {
    public final /* synthetic */ e01 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d01(InputConnection inputConnection, c01 c01Var) {
        super(inputConnection, false);
        this.a = c01Var;
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public final boolean commitContent(InputContentInfo inputContentInfo, int i, Bundle bundle) {
        Bundle bundle2;
        f01 f01Var = inputContentInfo == null ? null : new f01(new f01.a(inputContentInfo));
        View view = (View) ((c01) this.a).f;
        boolean z = false;
        if ((i & 1) != 0) {
            try {
                ((f01.a) f01Var.a).b();
                Parcelable parcelable = (Parcelable) ((f01.a) f01Var.a).a();
                bundle2 = bundle == null ? new Bundle() : new Bundle(bundle);
                bundle2.putParcelable("androidx.core.view.extra.INPUT_CONTENT_INFO", parcelable);
            } catch (Exception e) {
                Log.w("InputConnectionCompat", "Can't insert content from IME; requestPermission() failed", e);
            }
        } else {
            bundle2 = bundle;
        }
        ClipDescription description = ((f01.a) f01Var.a).a.getDescription();
        f01.a aVar = (f01.a) f01Var.a;
        ClipData clipData = new ClipData(description, new ClipData.Item(aVar.a.getContentUri()));
        oy.b aVar2 = Build.VERSION.SDK_INT >= 31 ? new oy.a(clipData, 2) : new oy.c(clipData, 2);
        aVar2.a(aVar.a.getLinkUri());
        aVar2.setExtras(bundle2);
        if (om3.h(view, aVar2.build()) == null) {
            z = true;
        }
        if (z) {
            return true;
        }
        return super.commitContent(inputContentInfo, i, bundle);
    }
}
