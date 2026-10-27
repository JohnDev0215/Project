package v1;

import android.app.PendingIntent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import androidx.core.graphics.drawable.IconCompat;

/* JADX INFO: renamed from: v1.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2536c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Bundle f22944a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public IconCompat f22945b;
    public final boolean c;
    public final boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f22946e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final CharSequence f22947f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final PendingIntent f22948g;

    public C2536c(IconCompat iconCompat, CharSequence charSequence, PendingIntent pendingIntent) {
        Bundle bundle = new Bundle();
        this.d = true;
        this.f22945b = iconCompat;
        int i7 = iconCompat.f11742a;
        if ((i7 == -1 ? ((Icon) iconCompat.f11743b).getType() : i7) == 2) {
            this.f22946e = iconCompat.c();
        }
        if (charSequence != null && charSequence.length() > 5120) {
            charSequence = charSequence.subSequence(0, 5120);
        }
        this.f22947f = charSequence;
        this.f22948g = pendingIntent;
        this.f22944a = bundle;
        this.c = true;
        this.d = true;
    }
}
