package p;

import android.content.ClipData;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContentInfo;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class hz implements gz, iz {
    public final /* synthetic */ int c = 0;
    public final ClipData e;
    public final int k;
    public int l;
    public Uri m;
    public Bundle n;

    public hz(hz hzVar) {
        ClipData clipData = hzVar.e;
        clipData.getClass();
        this.e = clipData;
        int i = hzVar.k;
        if (i < 0) {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too low)", "source", 0, 5));
        }
        if (i > 5) {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too high)", "source", 0, 5));
        }
        this.k = i;
        int i2 = hzVar.l;
        if ((i2 & 1) == i2) {
            this.l = i2;
            this.m = hzVar.m;
            this.n = hzVar.n;
        } else {
            throw new IllegalArgumentException("Requested flags 0x" + Integer.toHexString(i2) + ", but only 0x" + Integer.toHexString(1) + " are allowed");
        }
    }

    @Override // p.gz
    public final void a(Bundle bundle) {
        this.n = bundle;
    }

    @Override // p.gz
    public final void b(Uri uri) {
        this.m = uri;
    }

    @Override // p.gz
    public final jz build() {
        return new jz(new hz(this));
    }

    @Override // p.gz
    public final void c(int i) {
        this.l = i;
    }

    @Override // p.iz
    public final ClipData e() {
        return this.e;
    }

    @Override // p.iz
    public final int getFlags() {
        return this.l;
    }

    @Override // p.iz
    public final ContentInfo q() {
        return null;
    }

    @Override // p.iz
    public final int r() {
        return this.k;
    }

    public final String toString() {
        String str;
        switch (this.c) {
            case 1:
                StringBuilder sb = new StringBuilder("ContentInfoCompat{clip=");
                sb.append(this.e.getDescription());
                sb.append(", source=");
                int i = this.k;
                sb.append(i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? String.valueOf(i) : "SOURCE_PROCESS_TEXT" : "SOURCE_AUTOFILL" : "SOURCE_DRAG_AND_DROP" : "SOURCE_INPUT_METHOD" : "SOURCE_CLIPBOARD" : "SOURCE_APP");
                sb.append(", flags=");
                int i2 = this.l;
                sb.append((i2 & 1) != 0 ? "FLAG_CONVERT_TO_PLAIN_TEXT" : String.valueOf(i2));
                if (this.m == null) {
                    str = "";
                } else {
                    str = ", hasLinkUri(" + this.m.toString().length() + ")";
                }
                sb.append(str);
                return u4.m(sb, this.n != null ? ", hasExtras" : "", "}");
            default:
                return super.toString();
        }
    }

    public hz(ClipData clipData, int i) {
        this.e = clipData;
        this.k = i;
    }
}
