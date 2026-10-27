package defpackage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import java.util.Set;
import org.chromium.services.media_session.MediaMetadata;
import org.chromium.services.media_session.MediaPosition;

/* JADX INFO: compiled from: MediaNotificationInfo.java */
/* JADX INFO: loaded from: classes2.dex */
public final class gd1 {
    public final int a;
    public final MediaMetadata b;
    public final boolean c;
    public final String d;
    public final int e;
    public final boolean f;
    public final int g;
    public final Bitmap h;
    public final int i;
    public final Bitmap j;
    public final int k;
    public final Intent l;
    public final hd1 m;
    public final Set<Integer> n;
    public final MediaPosition o;

    /* JADX INFO: compiled from: MediaNotificationInfo.java */
    public static final class a {
        public MediaMetadata a;
        public boolean b;
        public int f;
        public Bitmap g;
        public int h;
        public Bitmap i;
        public Intent l;
        public hd1 m;
        public Set<Integer> n;
        public MediaPosition o;
        public String c = "";
        public int d = -1;
        public boolean e = true;
        public int j = 7;
        public int k = -1;
    }

    public gd1() {
        throw null;
    }

    public gd1(MediaMetadata mediaMetadata, boolean z, String str, int i, boolean z2, int i2, Bitmap bitmap, int i3, Bitmap bitmap2, int i4, int i5, Intent intent, hd1 hd1Var, Set set, MediaPosition mediaPosition) {
        this.b = mediaMetadata;
        this.c = z;
        this.d = str;
        this.e = i;
        this.f = z2;
        this.g = i2;
        this.h = bitmap;
        this.i = i3;
        this.j = bitmap2;
        this.a = i4;
        this.k = i5;
        this.l = intent;
        this.m = hd1Var;
        this.n = set;
        this.o = mediaPosition;
    }

    public final boolean equals(Object obj) {
        Bitmap bitmap;
        Bitmap bitmap2;
        MediaMetadata mediaMetadata;
        MediaMetadata mediaMetadata2;
        Intent intent;
        Intent intent2;
        hd1 hd1Var;
        hd1 hd1Var2;
        Set<Integer> set;
        Set<Integer> set2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof gd1)) {
            return false;
        }
        gd1 gd1Var = (gd1) obj;
        return this.c == gd1Var.c && this.f == gd1Var.f && this.e == gd1Var.e && this.g == gd1Var.g && ((bitmap = this.h) == (bitmap2 = gd1Var.h) || (bitmap != null && bitmap.sameAs(bitmap2))) && this.i == gd1Var.i && this.j == gd1Var.j && this.a == gd1Var.a && this.k == gd1Var.k && (((mediaMetadata = this.b) == (mediaMetadata2 = gd1Var.b) || (mediaMetadata != null && mediaMetadata.equals(mediaMetadata2))) && TextUtils.equals(this.d, gd1Var.d) && (((intent = this.l) == (intent2 = gd1Var.l) || (intent != null && intent.equals(intent2))) && (((hd1Var = this.m) == (hd1Var2 = gd1Var.m) || (hd1Var != null && hd1Var.equals(hd1Var2))) && (((set = this.n) == (set2 = gd1Var.n) || (set != null && set.equals(set2))) && this.o == gd1Var.o))));
    }

    public final int hashCode() {
        int i = (((this.c ? 1 : 0) * 31) + (this.f ? 1 : 0)) * 31;
        MediaMetadata mediaMetadata = this.b;
        int iHashCode = (i + (mediaMetadata == null ? 0 : mediaMetadata.hashCode())) * 31;
        String str = this.d;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        Intent intent = this.l;
        int iHashCode3 = (((((iHashCode2 + (intent == null ? 0 : intent.hashCode())) * 31) + this.e) * 31) + this.g) * 31;
        Bitmap bitmap = this.h;
        int iHashCode4 = (((iHashCode3 + (bitmap == null ? 0 : bitmap.hashCode())) * 31) + this.i) * 31;
        Bitmap bitmap2 = this.j;
        return this.o.hashCode() + ((this.n.hashCode() + ((this.m.hashCode() + ((((((iHashCode4 + (bitmap2 != null ? bitmap2.hashCode() : 0)) * 31) + this.a) * 31) + this.k) * 31)) * 31)) * 31);
    }
}
