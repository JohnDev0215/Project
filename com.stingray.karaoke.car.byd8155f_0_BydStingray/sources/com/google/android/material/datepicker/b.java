package com.google.android.material.datepicker;

import P8.n;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import com.stingray.drivemodedetector.R;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes.dex */
public final class b<S> extends X1.e {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final LinkedHashSet f12843A;
    public final LinkedHashSet B;

    public b() {
        new LinkedHashSet();
        new LinkedHashSet();
        this.f12843A = new LinkedHashSet();
        this.B = new LinkedHashSet();
    }

    public static boolean h(Context context, int i7) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(n.U(R.attr.materialCalendarStyle, context, a.class.getCanonicalName()), new int[]{i7});
        boolean z6 = typedArrayObtainStyledAttributes.getBoolean(0, false);
        typedArrayObtainStyledAttributes.recycle();
        return z6;
    }

    @Override // X1.e, android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        Iterator it = this.f12843A.iterator();
        while (it.hasNext()) {
            ((DialogInterface.OnCancelListener) it.next()).onCancel(dialogInterface);
        }
    }

    @Override // X1.e, android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        Iterator it = this.B.iterator();
        while (it.hasNext()) {
            ((DialogInterface.OnDismissListener) it.next()).onDismiss(dialogInterface);
        }
        super.onDismiss(dialogInterface);
    }
}
