package androidx.core.app;

import android.app.PendingIntent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import androidx.core.graphics.drawable.IconCompat;
import defpackage.oa2;
import java.util.ArrayList;
import java.util.Set;

/* JADX INFO: compiled from: NotificationCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    public final Bundle a;
    public IconCompat b;
    public final oa2[] c;
    public final boolean d;
    public final boolean e;
    public final int f;
    public final boolean g;

    @Deprecated
    public final int h;
    public final CharSequence i;
    public PendingIntent j;
    public final boolean k;

    /* JADX INFO: renamed from: androidx.core.app.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: NotificationCompat.java */
    public static final class C0006a {
        public final IconCompat a;
        public final CharSequence b;
        public final PendingIntent c;
        public final boolean d;
        public final Bundle e;
        public ArrayList<oa2> f;
        public final int g;
        public final boolean h;
        public final boolean i;
        public final boolean j;

        public C0006a(IconCompat iconCompat, CharSequence charSequence, PendingIntent pendingIntent) {
            this(iconCompat, charSequence, pendingIntent, new Bundle());
        }

        public final a a() {
            CharSequence[] charSequenceArr;
            Set<String> set;
            if (this.i && this.c == null) {
                throw new NullPointerException("Contextual Actions must contain a valid PendingIntent");
            }
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList<oa2> arrayList3 = this.f;
            if (arrayList3 != null) {
                for (oa2 oa2Var : arrayList3) {
                    if ((oa2Var.d || ((charSequenceArr = oa2Var.c) != null && charSequenceArr.length != 0) || (set = oa2Var.g) == null || set.isEmpty()) ? false : true) {
                        arrayList.add(oa2Var);
                    } else {
                        arrayList2.add(oa2Var);
                    }
                }
            }
            return new a(this.a, this.b, this.c, this.e, arrayList2.isEmpty() ? null : (oa2[]) arrayList2.toArray(new oa2[arrayList2.size()]), arrayList.isEmpty() ? null : (oa2[]) arrayList.toArray(new oa2[arrayList.size()]), this.d, this.g, this.h, this.i, this.j);
        }

        public C0006a(IconCompat iconCompat, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle) {
            this.d = true;
            this.h = true;
            this.a = iconCompat;
            this.b = NotificationCompat$Builder.b(charSequence);
            this.c = pendingIntent;
            this.e = bundle;
            this.f = null;
            this.d = true;
            this.g = 0;
            this.h = true;
            this.i = false;
            this.j = false;
        }
    }

    public a(IconCompat iconCompat, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, oa2[] oa2VarArr, oa2[] oa2VarArr2, boolean z, int i, boolean z2, boolean z3, boolean z4) {
        this.e = true;
        this.b = iconCompat;
        if (iconCompat != null) {
            int i2 = iconCompat.a;
            if ((i2 == -1 ? ((Icon) iconCompat.b).getType() : i2) == 2) {
                this.h = iconCompat.c();
            }
        }
        this.i = NotificationCompat$Builder.b(charSequence);
        this.j = pendingIntent;
        this.a = bundle == null ? new Bundle() : bundle;
        this.c = oa2VarArr;
        this.d = z;
        this.f = i;
        this.e = z2;
        this.g = z3;
        this.k = z4;
    }
}
