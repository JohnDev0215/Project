package spi;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.Size;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.car.media.common.MediaItemMetadata;
import com.android.car.media.main.R$bool;
import com.android.car.media.main.R$drawable;
import com.android.car.media.main.R$integer;
import com.android.car.media.main.R$string;
import com.android.car.ui.toolbar.b;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/* JADX INFO: compiled from: AppBarController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class m {
    public final int a;
    public final com.android.car.ui.toolbar.j c;
    public final Context d;
    public final boolean e;
    public final com.android.car.ui.toolbar.b f;
    public final com.android.car.ui.toolbar.b g;
    public final com.android.car.ui.toolbar.b h;
    public final com.android.car.ui.toolbar.b i;
    public boolean k;
    public boolean l;
    public String m;
    public Drawable o;
    public final ArrayList<qa<MediaItemMetadata.b>> b = new ArrayList<>();

    @NonNull
    public a j = new a();
    public int n = -1;

    /* JADX INFO: compiled from: AppBarController.java */
    public static class a {
        public void a() {
        }

        public void b(String str) {
        }

        public void c() {
        }

        public void d() {
        }

        public void e(MediaItemMetadata mediaItemMetadata) {
        }
    }

    public m(Context context, com.android.car.ui.toolbar.j jVar) {
        this.c = jVar;
        this.d = context.getApplicationContext();
        this.a = context.getResources().getInteger(R$integer.max_tabs);
        boolean z = context.getResources().getBoolean(R$bool.use_media_source_logo_for_app_selector);
        this.e = z;
        final int i = 0;
        jVar.registerSearchListener(new l(this, 0));
        jVar.registerSearchCompletedListener(new f2(this, 4));
        b.C0013b c0013b = new b.C0013b(context);
        c0013b.d();
        c0013b.i = new b.e(this) { // from class: spi.k
            public final /* synthetic */ m b;

            {
                this.b = this;
            }

            @Override // com.android.car.ui.toolbar.b.e
            public final void b(com.android.car.ui.toolbar.b bVar) {
                switch (i) {
                    case 0:
                        this.b.j.c();
                        break;
                    case 1:
                        this.b.j.d();
                        break;
                    default:
                        this.b.j.a();
                        break;
                }
            }
        };
        com.android.car.ui.toolbar.b bVarA = c0013b.a();
        this.f = bVarA;
        b.C0013b c0013b2 = new b.C0013b(context);
        c0013b2.e();
        c0013b2.u = 64;
        final int i2 = 1;
        c0013b2.i = new b.e(this) { // from class: spi.k
            public final /* synthetic */ m b;

            {
                this.b = this;
            }

            @Override // com.android.car.ui.toolbar.b.e
            public final void b(com.android.car.ui.toolbar.b bVar) {
                switch (i2) {
                    case 0:
                        this.b.j.c();
                        break;
                    case 1:
                        this.b.j.d();
                        break;
                    default:
                        this.b.j.a();
                        break;
                }
            }
        };
        com.android.car.ui.toolbar.b bVarA2 = c0013b2.a();
        this.g = bVarA2;
        b.C0013b c0013b3 = new b.C0013b(context);
        c0013b3.c(R$string.menu_item_sound_settings_title);
        c0013b3.b(R$drawable.ic_equalizer);
        final int i3 = 2;
        c0013b3.i = new b.e(this) { // from class: spi.k
            public final /* synthetic */ m b;

            {
                this.b = this;
            }

            @Override // com.android.car.ui.toolbar.b.e
            public final void b(com.android.car.ui.toolbar.b bVar) {
                switch (i3) {
                    case 0:
                        this.b.j.c();
                        break;
                    case 1:
                        this.b.j.d();
                        break;
                    default:
                        this.b.j.a();
                        break;
                }
            }
        };
        com.android.car.ui.toolbar.b bVarA3 = c0013b3.a();
        this.h = bVarA3;
        b.C0013b c0013b4 = new b.C0013b(context);
        c0013b4.c(R$string.menu_item_app_selector_title);
        c0013b4.k = !z;
        c0013b4.h = z ? null : context.getDrawable(R$drawable.ic_app_switch);
        c0013b4.i = new b6(context, null);
        com.android.car.ui.toolbar.b bVarA4 = c0013b4.a();
        this.i = bVarA4;
        jVar.setMenuItems(Arrays.asList(bVarA, bVarA3, bVarA2, bVarA4));
        bVarA4.e(false);
    }

    public final int a() {
        if (this.b.isEmpty()) {
            return 0;
        }
        return this.b.size();
    }

    public final void b(MediaItemMetadata mediaItemMetadata) {
        com.android.car.ui.toolbar.j jVar = this.c;
        if (jVar == null || jVar.getNavButtonMode() != s7.DISABLED) {
            Log.i("AppBarController", "Don't show the nav button, so return.");
            return;
        }
        for (int i = 0; i < this.b.size(); i++) {
            if (mediaItemMetadata != null && Objects.equals(mediaItemMetadata.b(), this.b.get(i).h.b())) {
                this.c.selectTab(i);
                return;
            }
        }
    }

    public final void c(int i) {
        this.c.setBackground(i);
    }

    public final void d(@Nullable List<MediaItemMetadata> list) {
        if (list == null) {
            list = Collections.emptyList();
        }
        for (qa<MediaItemMetadata.b> qaVar : this.b) {
            qaVar.j = null;
            qaVar.d(this.d, null);
        }
        this.b.clear();
        Size sizeA = s6.a(this.d);
        for (MediaItemMetadata mediaItemMetadata : list.subList(0, Math.min(list.size(), this.a))) {
            qa<MediaItemMetadata.b> qaVar2 = new qa<>(this.d, sizeA, mediaItemMetadata, new l(this, 1));
            qaVar2.d(this.d, mediaItemMetadata.g);
            this.b.add(qaVar2);
        }
        this.n = this.b.isEmpty() ? -1 : 0;
        Iterator<qa<MediaItemMetadata.b>> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().j = new l(this, 2);
        }
        g();
    }

    public final void e(s7 s7Var) {
        if (s7Var != this.c.getNavButtonMode()) {
            this.c.setNavButtonMode(s7Var);
            g();
        }
    }

    public final void f() {
        if (this.c.getSearchMode() != v9.DISABLED) {
            this.c.setLogo(this.o);
        } else {
            if (!this.e) {
                this.c.setLogo(this.o);
                return;
            }
            com.android.car.ui.toolbar.b bVar = this.i;
            bVar.j = this.o;
            bVar.f();
        }
    }

    public final void g() {
        if (this.c.getNavButtonMode() != s7.DISABLED) {
            this.c.setTabs(Collections.emptyList());
        } else {
            this.c.setTabs((List) this.b.stream().map(d0.j).collect(Collectors.toList()), this.n);
        }
    }
}
