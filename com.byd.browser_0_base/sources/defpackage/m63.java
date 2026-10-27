package defpackage;

import android.graphics.drawable.Drawable;
import android.widget.ImageButton;
import java.util.Objects;
import org.chromium.chrome.browser.toolbar.HomeButton;

/* JADX INFO: compiled from: TabletCaptureStateToken.java */
/* JADX INFO: loaded from: classes2.dex */
public final class m63 {
    public final b a;
    public final b b;
    public final b c;
    public final b d;
    public final int e;
    public final po3 f;
    public final c g;
    public final b h;
    public final int i;
    public final int j;

    /* JADX INFO: compiled from: TabletCaptureStateToken.java */
    public static abstract class a {
        public final int a;
        public final boolean b;
        public final int c;
        public final int d;

        public a(ImageButton imageButton) {
            this.a = imageButton.getVisibility();
            this.b = imageButton.isEnabled();
            this.c = imageButton.getDrawable() == null ? 0 : imageButton.getDrawable().getLevel();
            this.d = imageButton.getImageTintList() != null ? imageButton.getImageTintList().getDefaultColor() : 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.a == aVar.a && this.b == aVar.b && this.c == aVar.c && this.d == aVar.d;
        }

        public int hashCode() {
            return Integer.hashCode(this.d) + Integer.hashCode(this.c) + Boolean.hashCode(this.b) + Integer.hashCode(this.a) + super.hashCode();
        }
    }

    /* JADX INFO: compiled from: TabletCaptureStateToken.java */
    public static class b extends a {
        public final Drawable e;

        public b(ImageButton imageButton) {
            super(imageButton);
            this.e = imageButton.getDrawable();
        }

        @Override // m63.a
        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof b) {
                return Objects.equals(this.e, ((b) obj).e) && super.equals(obj);
            }
            return false;
        }

        @Override // m63.a
        public final int hashCode() {
            return Objects.hashCode(this.e) + super.hashCode();
        }
    }

    /* JADX INFO: compiled from: TabletCaptureStateToken.java */
    public static class c extends a {
        public final int e;

        public c(ImageButton imageButton, int i) {
            super(imageButton);
            this.e = i;
        }

        @Override // m63.a
        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof c) {
                return this.e == ((c) obj).e && super.equals(obj);
            }
            return false;
        }

        @Override // m63.a
        public final int hashCode() {
            return Integer.hashCode(this.e) + super.hashCode();
        }
    }

    public m63(HomeButton homeButton, ImageButton imageButton, ImageButton imageButton2, ImageButton imageButton3, int i, po3 po3Var, ImageButton imageButton4, int i2, ImageButton imageButton5, int i3, int i4) {
        this.a = a(homeButton);
        this.b = a(imageButton);
        this.c = a(imageButton2);
        this.d = a(imageButton3);
        this.e = i;
        this.f = po3Var;
        this.g = imageButton4 == null ? null : new c(imageButton4, i2);
        this.h = a(imageButton5);
        this.i = i3;
        this.j = i4;
    }

    public static b a(ImageButton imageButton) {
        if (imageButton == null) {
            return null;
        }
        return new b(imageButton);
    }
}
