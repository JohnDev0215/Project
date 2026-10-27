package androidx.mediarouter.app;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialog;
import androidx.mediarouter.R$attr;
import androidx.mediarouter.R$bool;
import androidx.mediarouter.R$id;
import androidx.mediarouter.R$integer;
import androidx.mediarouter.R$layout;
import androidx.mediarouter.R$string;
import androidx.mediarouter.media.g;
import androidx.mediarouter.media.h;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.qd1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MediaRouteDynamicChooserDialog extends AppCompatDialog {
    public final h j;
    public final c k;
    public final Context l;
    public g m;
    public ArrayList n;
    public d o;
    public RecyclerView p;
    public boolean q;
    public h.C0020h r;
    public final long s;
    public long t;
    public final a u;

    public class a extends Handler {
        public a() {
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            List list = (List) message.obj;
            MediaRouteDynamicChooserDialog mediaRouteDynamicChooserDialog = MediaRouteDynamicChooserDialog.this;
            mediaRouteDynamicChooserDialog.getClass();
            mediaRouteDynamicChooserDialog.t = SystemClock.uptimeMillis();
            mediaRouteDynamicChooserDialog.n.clear();
            mediaRouteDynamicChooserDialog.n.addAll(list);
            mediaRouteDynamicChooserDialog.o.D();
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            MediaRouteDynamicChooserDialog.this.dismiss();
        }
    }

    public final class c extends h.a {
        public c() {
        }

        @Override // androidx.mediarouter.media.h.a
        public final void d() {
            MediaRouteDynamicChooserDialog.this.e();
        }

        @Override // androidx.mediarouter.media.h.a
        public final void e(h.C0020h c0020h) {
            MediaRouteDynamicChooserDialog.this.e();
        }

        @Override // androidx.mediarouter.media.h.a
        public final void f() {
            MediaRouteDynamicChooserDialog.this.e();
        }

        @Override // androidx.mediarouter.media.h.a
        public final void g(h.C0020h c0020h) {
            MediaRouteDynamicChooserDialog.this.dismiss();
        }
    }

    public final class d extends RecyclerView.e<RecyclerView.a0> {
        public final ArrayList<b> h = new ArrayList<>();
        public final LayoutInflater i;
        public final Drawable j;
        public final Drawable k;
        public final Drawable l;
        public final Drawable m;

        public class a extends RecyclerView.a0 {
            public final TextView y;

            public a(View view) {
                super(view);
                this.y = (TextView) view.findViewById(R$id.mr_picker_header_name);
            }
        }

        public class b {
            public final Object a;
            public final int b;

            public b(Object obj) {
                this.a = obj;
                if (obj instanceof String) {
                    this.b = 1;
                } else {
                    if (!(obj instanceof h.C0020h)) {
                        throw new IllegalArgumentException();
                    }
                    this.b = 2;
                }
            }
        }

        public class c extends RecyclerView.a0 {
            public final ProgressBar A;
            public final TextView B;
            public final View y;
            public final ImageView z;

            public c(View view) {
                super(view);
                this.y = view;
                this.z = (ImageView) view.findViewById(R$id.mr_picker_route_icon);
                ProgressBar progressBar = (ProgressBar) view.findViewById(R$id.mr_picker_route_progress_bar);
                this.A = progressBar;
                this.B = (TextView) view.findViewById(R$id.mr_picker_route_name);
                f.k(MediaRouteDynamicChooserDialog.this.l, progressBar);
            }
        }

        public d() {
            this.i = LayoutInflater.from(MediaRouteDynamicChooserDialog.this.l);
            int i = R$attr.mediaRouteDefaultIconDrawable;
            Context context = MediaRouteDynamicChooserDialog.this.l;
            this.j = f.e(context, i);
            this.k = f.e(context, R$attr.mediaRouteTvIconDrawable);
            this.l = f.e(context, R$attr.mediaRouteSpeakerIconDrawable);
            this.m = f.e(context, R$attr.mediaRouteSpeakerGroupIconDrawable);
            D();
        }

        public final void D() {
            ArrayList<b> arrayList = this.h;
            arrayList.clear();
            MediaRouteDynamicChooserDialog mediaRouteDynamicChooserDialog = MediaRouteDynamicChooserDialog.this;
            arrayList.add(new b(mediaRouteDynamicChooserDialog.l.getString(R$string.mr_chooser_title)));
            Iterator it = mediaRouteDynamicChooserDialog.n.iterator();
            while (it.hasNext()) {
                arrayList.add(new b((h.C0020h) it.next()));
            }
            o();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.e
        public final int k() {
            return this.h.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.e
        public final int m(int i) {
            return this.h.get(i).b;
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x006b  */
        @Override // androidx.recyclerview.widget.RecyclerView.e
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void t(androidx.recyclerview.widget.RecyclerView.a0 r8, int r9) {
            /*
                r7 = this;
                int r0 = r7.m(r9)
                java.util.ArrayList<androidx.mediarouter.app.MediaRouteDynamicChooserDialog$d$b> r7 = r7.h
                java.lang.Object r7 = r7.get(r9)
                androidx.mediarouter.app.MediaRouteDynamicChooserDialog$d$b r7 = (androidx.mediarouter.app.MediaRouteDynamicChooserDialog.d.b) r7
                r9 = 1
                if (r0 == r9) goto L89
                java.lang.String r1 = "RecyclerAdapter"
                r2 = 2
                if (r0 == r2) goto L1b
                java.lang.String r7 = "Cannot bind item to ViewHolder because of wrong view type"
                android.util.Log.w(r1, r7)
                goto L96
            L1b:
                androidx.mediarouter.app.MediaRouteDynamicChooserDialog$d$c r8 = (androidx.mediarouter.app.MediaRouteDynamicChooserDialog.d.c) r8
                java.lang.Object r7 = r7.a
                androidx.mediarouter.media.h$h r7 = (androidx.mediarouter.media.h.C0020h) r7
                r0 = 0
                android.view.View r3 = r8.y
                r3.setVisibility(r0)
                android.widget.ProgressBar r0 = r8.A
                r4 = 4
                r0.setVisibility(r4)
                androidx.mediarouter.app.d r0 = new androidx.mediarouter.app.d
                r0.<init>(r8, r7)
                r3.setOnClickListener(r0)
                java.lang.String r0 = r7.d
                android.widget.TextView r3 = r8.B
                r3.setText(r0)
                androidx.mediarouter.app.MediaRouteDynamicChooserDialog$d r0 = androidx.mediarouter.app.MediaRouteDynamicChooserDialog.d.this
                r0.getClass()
                android.net.Uri r3 = r7.f
                if (r3 == 0) goto L6b
                androidx.mediarouter.app.MediaRouteDynamicChooserDialog r4 = androidx.mediarouter.app.MediaRouteDynamicChooserDialog.this     // Catch: java.io.IOException -> L59
                android.content.Context r4 = r4.l     // Catch: java.io.IOException -> L59
                android.content.ContentResolver r4 = r4.getContentResolver()     // Catch: java.io.IOException -> L59
                java.io.InputStream r4 = r4.openInputStream(r3)     // Catch: java.io.IOException -> L59
                r5 = 0
                android.graphics.drawable.Drawable r1 = android.graphics.drawable.Drawable.createFromStream(r4, r5)     // Catch: java.io.IOException -> L59
                if (r1 == 0) goto L6b
                goto L83
            L59:
                r4 = move-exception
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                java.lang.String r6 = "Failed to load "
                r5.<init>(r6)
                r5.append(r3)
                java.lang.String r3 = r5.toString()
                android.util.Log.w(r1, r3, r4)
            L6b:
                int r1 = r7.m
                if (r1 == r9) goto L80
                if (r1 == r2) goto L7d
                boolean r7 = r7.g()
                if (r7 == 0) goto L7a
                android.graphics.drawable.Drawable r7 = r0.m
                goto L82
            L7a:
                android.graphics.drawable.Drawable r7 = r0.j
                goto L82
            L7d:
                android.graphics.drawable.Drawable r7 = r0.l
                goto L82
            L80:
                android.graphics.drawable.Drawable r7 = r0.k
            L82:
                r1 = r7
            L83:
                android.widget.ImageView r7 = r8.z
                r7.setImageDrawable(r1)
                goto L96
            L89:
                androidx.mediarouter.app.MediaRouteDynamicChooserDialog$d$a r8 = (androidx.mediarouter.app.MediaRouteDynamicChooserDialog.d.a) r8
                java.lang.Object r7 = r7.a
                java.lang.String r7 = r7.toString()
                android.widget.TextView r8 = r8.y
                r8.setText(r7)
            L96:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.app.MediaRouteDynamicChooserDialog.d.t(androidx.recyclerview.widget.RecyclerView$a0, int):void");
        }

        @Override // androidx.recyclerview.widget.RecyclerView.e
        public final RecyclerView.a0 v(int i, RecyclerView recyclerView) {
            LayoutInflater layoutInflater = this.i;
            if (i == 1) {
                return new a(layoutInflater.inflate(R$layout.mr_picker_header_item, (ViewGroup) recyclerView, false));
            }
            if (i == 2) {
                return new c(layoutInflater.inflate(R$layout.mr_picker_route_item, (ViewGroup) recyclerView, false));
            }
            throw new IllegalStateException();
        }
    }

    public static final class e implements Comparator<h.C0020h> {
        public static final e e = new e();

        @Override // java.util.Comparator
        public final int compare(h.C0020h c0020h, h.C0020h c0020h2) {
            return c0020h.d.compareToIgnoreCase(c0020h2.d);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MediaRouteDynamicChooserDialog(Context context) {
        ContextThemeWrapper contextThemeWrapperA = f.a(0, context, false);
        super(contextThemeWrapperA, f.b(contextThemeWrapperA));
        this.m = g.c;
        this.u = new a();
        Context context2 = getContext();
        this.j = h.d(context2);
        this.k = new c();
        this.l = context2;
        this.s = context2.getResources().getInteger(R$integer.mr_update_routes_delay_ms);
    }

    public final void e() {
        if (this.r == null && this.q) {
            this.j.getClass();
            h.b();
            h.d dVarC = h.c();
            ArrayList arrayList = new ArrayList(dVarC == null ? Collections.emptyList() : dVarC.g);
            int size = arrayList.size();
            while (true) {
                int i = size - 1;
                if (size <= 0) {
                    break;
                }
                h.C0020h c0020h = (h.C0020h) arrayList.get(i);
                if (!(!c0020h.f() && c0020h.g && c0020h.j(this.m))) {
                    arrayList.remove(i);
                }
                size = i;
            }
            Collections.sort(arrayList, e.e);
            long jUptimeMillis = SystemClock.uptimeMillis() - this.t;
            long j = this.s;
            if (jUptimeMillis < j) {
                a aVar = this.u;
                aVar.removeMessages(1);
                aVar.sendMessageAtTime(aVar.obtainMessage(1, arrayList), this.t + j);
            } else {
                this.t = SystemClock.uptimeMillis();
                this.n.clear();
                this.n.addAll(arrayList);
                this.o.D();
            }
        }
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.q = true;
        this.j.a(this.m, this.k, 1);
        e();
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R$layout.mr_picker_dialog);
        Context context = this.l;
        f.j(context, this);
        this.n = new ArrayList();
        ((ImageButton) findViewById(R$id.mr_picker_close_button)).setOnClickListener(new b());
        this.o = new d();
        RecyclerView recyclerView = (RecyclerView) findViewById(R$id.mr_picker_list);
        this.p = recyclerView;
        recyclerView.setAdapter(this.o);
        this.p.setLayoutManager(new LinearLayoutManager(context));
        Resources resources = context.getResources();
        int i = R$bool.is_tablet;
        getWindow().setLayout(!resources.getBoolean(i) ? -1 : qd1.a(context), context.getResources().getBoolean(i) ? -2 : -1);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.q = false;
        this.j.h(this.k);
        this.u.removeMessages(1);
    }
}
