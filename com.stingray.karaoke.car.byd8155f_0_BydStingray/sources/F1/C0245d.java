package F1;

import android.content.ClipData;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContentInfo;
import java.util.Locale;

/* JADX INFO: renamed from: F1.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0245d implements InterfaceC0244c, InterfaceC0246e {
    public final /* synthetic */ int l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ClipData f2326m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f2327n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Uri f2328p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Bundle f2329q;

    public /* synthetic */ C0245d() {
    }

    @Override // F1.InterfaceC0246e
    public ContentInfo A() {
        return null;
    }

    @Override // F1.InterfaceC0244c
    public void D(Uri uri) {
        this.f2328p = uri;
    }

    @Override // F1.InterfaceC0244c
    public void R(int i7) {
        this.o = i7;
    }

    @Override // F1.InterfaceC0244c
    public C0247f a() {
        return new C0247f(new C0245d(this));
    }

    @Override // F1.InterfaceC0246e
    public int g() {
        return this.f2327n;
    }

    @Override // F1.InterfaceC0246e
    public int j() {
        return this.o;
    }

    @Override // F1.InterfaceC0246e
    public ClipData k() {
        return this.f2326m;
    }

    @Override // F1.InterfaceC0244c
    public void n(Bundle bundle) {
        this.f2329q = bundle;
    }

    public String toString() {
        String str;
        switch (this.l) {
            case 1:
                StringBuilder sb = new StringBuilder("ContentInfoCompat{clip=");
                sb.append(this.f2326m.getDescription());
                sb.append(", source=");
                int i7 = this.f2327n;
                sb.append(i7 != 0 ? i7 != 1 ? i7 != 2 ? i7 != 3 ? i7 != 4 ? i7 != 5 ? String.valueOf(i7) : "SOURCE_PROCESS_TEXT" : "SOURCE_AUTOFILL" : "SOURCE_DRAG_AND_DROP" : "SOURCE_INPUT_METHOD" : "SOURCE_CLIPBOARD" : "SOURCE_APP");
                sb.append(", flags=");
                int i10 = this.o;
                sb.append((i10 & 1) != 0 ? "FLAG_CONVERT_TO_PLAIN_TEXT" : String.valueOf(i10));
                Uri uri = this.f2328p;
                if (uri == null) {
                    str = "";
                } else {
                    str = ", hasLinkUri(" + uri.toString().length() + ")";
                }
                sb.append(str);
                return android.hardware.bydauto.ac.tsimpl.a.l(sb, this.f2329q != null ? ", hasExtras" : "", "}");
            default:
                return super.toString();
        }
    }

    public C0245d(C0245d c0245d) {
        ClipData clipData = c0245d.f2326m;
        clipData.getClass();
        this.f2326m = clipData;
        int i7 = c0245d.f2327n;
        if (i7 < 0) {
            Locale locale = Locale.US;
            throw new IllegalArgumentException("source is out of range of [0, 5] (too low)");
        }
        if (i7 > 5) {
            Locale locale2 = Locale.US;
            throw new IllegalArgumentException("source is out of range of [0, 5] (too high)");
        }
        this.f2327n = i7;
        int i10 = c0245d.o;
        if ((i10 & 1) == i10) {
            this.o = i10;
            this.f2328p = c0245d.f2328p;
            this.f2329q = c0245d.f2329q;
        } else {
            throw new IllegalArgumentException("Requested flags 0x" + Integer.toHexString(i10) + ", but only 0x" + Integer.toHexString(1) + " are allowed");
        }
    }
}
