package defpackage;

import android.content.ClipData;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContentInfo;
import java.util.Locale;

/* JADX INFO: compiled from: ContentInfoCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class oy {
    public final e a;

    /* JADX INFO: compiled from: ContentInfoCompat.java */
    public static final class a implements b {
        public final ContentInfo.Builder a;

        public a(ClipData clipData, int i) {
            this.a = new ContentInfo.Builder(clipData, i);
        }

        @Override // oy.b
        public final void a(Uri uri) {
            this.a.setLinkUri(uri);
        }

        @Override // oy.b
        public final oy build() {
            return new oy(new d(this.a.build()));
        }

        @Override // oy.b
        public final void setExtras(Bundle bundle) {
            this.a.setExtras(bundle);
        }

        @Override // oy.b
        public final void setFlags(int i) {
            this.a.setFlags(i);
        }
    }

    /* JADX INFO: compiled from: ContentInfoCompat.java */
    public interface b {
        void a(Uri uri);

        oy build();

        void setExtras(Bundle bundle);

        void setFlags(int i);
    }

    /* JADX INFO: compiled from: ContentInfoCompat.java */
    public static final class c implements b {
        public final ClipData a;
        public final int b;
        public int c;
        public Uri d;
        public Bundle e;

        public c(ClipData clipData, int i) {
            this.a = clipData;
            this.b = i;
        }

        @Override // oy.b
        public final void a(Uri uri) {
            this.d = uri;
        }

        @Override // oy.b
        public final oy build() {
            return new oy(new f(this));
        }

        @Override // oy.b
        public final void setExtras(Bundle bundle) {
            this.e = bundle;
        }

        @Override // oy.b
        public final void setFlags(int i) {
            this.c = i;
        }
    }

    /* JADX INFO: compiled from: ContentInfoCompat.java */
    public static final class d implements e {
        public final ContentInfo a;

        public d(ContentInfo contentInfo) {
            contentInfo.getClass();
            this.a = contentInfo;
        }

        @Override // oy.e
        public final ClipData a() {
            return this.a.getClip();
        }

        @Override // oy.e
        public final ContentInfo b() {
            return this.a;
        }

        @Override // oy.e
        public final int c() {
            return this.a.getSource();
        }

        @Override // oy.e
        public final int getFlags() {
            return this.a.getFlags();
        }

        public final String toString() {
            return "ContentInfoCompat{" + this.a + "}";
        }
    }

    /* JADX INFO: compiled from: ContentInfoCompat.java */
    public interface e {
        ClipData a();

        ContentInfo b();

        int c();

        int getFlags();
    }

    /* JADX INFO: compiled from: ContentInfoCompat.java */
    public static final class f implements e {
        public final ClipData a;
        public final int b;
        public final int c;
        public final Uri d;
        public final Bundle e;

        public f(c cVar) {
            ClipData clipData = cVar.a;
            clipData.getClass();
            this.a = clipData;
            int i = cVar.b;
            if (i < 0) {
                throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too low)", BYDAutoMultimediaDevice.PARAM_SOURCE, 0, 5));
            }
            if (i > 5) {
                throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too high)", BYDAutoMultimediaDevice.PARAM_SOURCE, 0, 5));
            }
            this.b = i;
            int i2 = cVar.c;
            if ((i2 & 1) == i2) {
                this.c = i2;
                this.d = cVar.d;
                this.e = cVar.e;
            } else {
                throw new IllegalArgumentException("Requested flags 0x" + Integer.toHexString(i2) + ", but only 0x" + Integer.toHexString(1) + " are allowed");
            }
        }

        @Override // oy.e
        public final ClipData a() {
            return this.a;
        }

        @Override // oy.e
        public final ContentInfo b() {
            return null;
        }

        @Override // oy.e
        public final int c() {
            return this.b;
        }

        @Override // oy.e
        public final int getFlags() {
            return this.c;
        }

        public final String toString() {
            String str;
            StringBuilder sb = new StringBuilder("ContentInfoCompat{clip=");
            sb.append(this.a.getDescription());
            sb.append(", source=");
            int i = this.b;
            sb.append(i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? String.valueOf(i) : "SOURCE_PROCESS_TEXT" : "SOURCE_AUTOFILL" : "SOURCE_DRAG_AND_DROP" : "SOURCE_INPUT_METHOD" : "SOURCE_CLIPBOARD" : "SOURCE_APP");
            sb.append(", flags=");
            int i2 = this.c;
            sb.append((i2 & 1) != 0 ? "FLAG_CONVERT_TO_PLAIN_TEXT" : String.valueOf(i2));
            Uri uri = this.d;
            if (uri == null) {
                str = "";
            } else {
                str = ", hasLinkUri(" + uri.toString().length() + ")";
            }
            sb.append(str);
            return gq2.i(sb, this.e != null ? ", hasExtras" : "", "}");
        }
    }

    public oy(e eVar) {
        this.a = eVar;
    }

    public final String toString() {
        return this.a.toString();
    }
}
