package androidx.mediarouter.app;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.mediarouter.R$attr;
import androidx.mediarouter.R$id;
import androidx.mediarouter.R$layout;
import androidx.mediarouter.media.g;
import androidx.mediarouter.media.h;
import defpackage.qd1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MediaRouteChooserDialog extends AppCompatDialog {
    public final h j;
    public final b k;
    public TextView l;
    public g m;
    public ArrayList<h.C0020h> n;
    public c o;
    public ListView p;
    public boolean q;
    public long r;
    public final a s;

    public class a extends Handler {
        public a() {
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            List list = (List) message.obj;
            MediaRouteChooserDialog mediaRouteChooserDialog = MediaRouteChooserDialog.this;
            mediaRouteChooserDialog.getClass();
            mediaRouteChooserDialog.r = SystemClock.uptimeMillis();
            mediaRouteChooserDialog.n.clear();
            mediaRouteChooserDialog.n.addAll(list);
            mediaRouteChooserDialog.o.notifyDataSetChanged();
        }
    }

    public final class b extends h.a {
        public b() {
        }

        @Override // androidx.mediarouter.media.h.a
        public final void d() {
            MediaRouteChooserDialog.this.e();
        }

        @Override // androidx.mediarouter.media.h.a
        public final void e(h.C0020h c0020h) {
            MediaRouteChooserDialog.this.e();
        }

        @Override // androidx.mediarouter.media.h.a
        public final void f() {
            MediaRouteChooserDialog.this.e();
        }

        @Override // androidx.mediarouter.media.h.a
        public final void g(h.C0020h c0020h) {
            MediaRouteChooserDialog.this.dismiss();
        }
    }

    public static final class c extends ArrayAdapter<h.C0020h> implements AdapterView.OnItemClickListener {
        public final LayoutInflater e;
        public final Drawable f;
        public final Drawable g;
        public final Drawable h;
        public final Drawable i;

        public c(Context context, ArrayList arrayList) {
            super(context, 0, arrayList);
            this.e = LayoutInflater.from(context);
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(new int[]{R$attr.mediaRouteDefaultIconDrawable, R$attr.mediaRouteTvIconDrawable, R$attr.mediaRouteSpeakerIconDrawable, R$attr.mediaRouteSpeakerGroupIconDrawable});
            this.f = AppCompatResources.a(context, typedArrayObtainStyledAttributes.getResourceId(0, 0));
            this.g = AppCompatResources.a(context, typedArrayObtainStyledAttributes.getResourceId(1, 0));
            this.h = AppCompatResources.a(context, typedArrayObtainStyledAttributes.getResourceId(2, 0));
            this.i = AppCompatResources.a(context, typedArrayObtainStyledAttributes.getResourceId(3, 0));
            typedArrayObtainStyledAttributes.recycle();
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public final boolean areAllItemsEnabled() {
            return false;
        }

        /* JADX WARN: Removed duplicated region for block: B:25:0x0092  */
        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final android.view.View getView(int r7, android.view.View r8, android.view.ViewGroup r9) {
            /*
                r6 = this;
                r0 = 0
                if (r8 != 0) goto Lb
                android.view.LayoutInflater r8 = r6.e
                int r1 = androidx.mediarouter.R$layout.mr_chooser_list_item
                android.view.View r8 = r8.inflate(r1, r9, r0)
            Lb:
                java.lang.Object r7 = r6.getItem(r7)
                androidx.mediarouter.media.h$h r7 = (androidx.mediarouter.media.h.C0020h) r7
                int r9 = androidx.mediarouter.R$id.mr_chooser_route_name
                android.view.View r9 = r8.findViewById(r9)
                android.widget.TextView r9 = (android.widget.TextView) r9
                int r1 = androidx.mediarouter.R$id.mr_chooser_route_desc
                android.view.View r1 = r8.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                java.lang.String r2 = r7.d
                r9.setText(r2)
                java.lang.String r2 = r7.e
                int r3 = r7.h
                r4 = 2
                r5 = 1
                if (r3 == r4) goto L33
                if (r3 != r5) goto L31
                goto L33
            L31:
                r3 = r0
                goto L34
            L33:
                r3 = r5
            L34:
                if (r3 == 0) goto L48
                boolean r3 = android.text.TextUtils.isEmpty(r2)
                if (r3 != 0) goto L48
                r3 = 80
                r9.setGravity(r3)
                r1.setVisibility(r0)
                r1.setText(r2)
                goto L57
            L48:
                r0 = 16
                r9.setGravity(r0)
                r9 = 8
                r1.setVisibility(r9)
                java.lang.String r9 = ""
                r1.setText(r9)
            L57:
                boolean r9 = r7.g
                r8.setEnabled(r9)
                int r9 = androidx.mediarouter.R$id.mr_chooser_route_icon
                android.view.View r9 = r8.findViewById(r9)
                android.widget.ImageView r9 = (android.widget.ImageView) r9
                if (r9 == 0) goto Lad
                android.net.Uri r0 = r7.f
                if (r0 == 0) goto L92
                android.content.Context r1 = r6.getContext()     // Catch: java.io.IOException -> L7e
                android.content.ContentResolver r1 = r1.getContentResolver()     // Catch: java.io.IOException -> L7e
                java.io.InputStream r1 = r1.openInputStream(r0)     // Catch: java.io.IOException -> L7e
                r2 = 0
                android.graphics.drawable.Drawable r0 = android.graphics.drawable.Drawable.createFromStream(r1, r2)     // Catch: java.io.IOException -> L7e
                if (r0 == 0) goto L92
                goto Laa
            L7e:
                r1 = move-exception
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                java.lang.String r3 = "Failed to load "
                r2.<init>(r3)
                r2.append(r0)
                java.lang.String r0 = r2.toString()
                java.lang.String r2 = "MediaRouteChooserDialog"
                android.util.Log.w(r2, r0, r1)
            L92:
                int r0 = r7.m
                if (r0 == r5) goto La7
                if (r0 == r4) goto La4
                boolean r7 = r7.g()
                if (r7 == 0) goto La1
                android.graphics.drawable.Drawable r6 = r6.i
                goto La9
            La1:
                android.graphics.drawable.Drawable r6 = r6.f
                goto La9
            La4:
                android.graphics.drawable.Drawable r6 = r6.h
                goto La9
            La7:
                android.graphics.drawable.Drawable r6 = r6.g
            La9:
                r0 = r6
            Laa:
                r9.setImageDrawable(r0)
            Lad:
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.app.MediaRouteChooserDialog.c.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public final boolean isEnabled(int i) {
            return getItem(i).g;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            h.C0020h item = getItem(i);
            if (item.g) {
                ImageView imageView = (ImageView) view.findViewById(R$id.mr_chooser_route_icon);
                ProgressBar progressBar = (ProgressBar) view.findViewById(R$id.mr_chooser_route_progress_bar);
                if (imageView != null && progressBar != null) {
                    imageView.setVisibility(8);
                    progressBar.setVisibility(0);
                }
                item.n();
            }
        }
    }

    public static final class d implements Comparator<h.C0020h> {
        public static final d e = new d();

        @Override // java.util.Comparator
        public final int compare(h.C0020h c0020h, h.C0020h c0020h2) {
            return c0020h.d.compareToIgnoreCase(c0020h2.d);
        }
    }

    public MediaRouteChooserDialog(Context context) {
        this(context, 0);
    }

    public final void e() {
        if (this.q) {
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
            Collections.sort(arrayList, d.e);
            if (SystemClock.uptimeMillis() - this.r < 300) {
                a aVar = this.s;
                aVar.removeMessages(1);
                aVar.sendMessageAtTime(aVar.obtainMessage(1, arrayList), this.r + 300);
            } else {
                this.r = SystemClock.uptimeMillis();
                this.n.clear();
                this.n.addAll(arrayList);
                this.o.notifyDataSetChanged();
            }
        }
    }

    public final void f(g gVar) {
        if (gVar == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        if (this.m.equals(gVar)) {
            return;
        }
        this.m = gVar;
        if (this.q) {
            h hVar = this.j;
            b bVar = this.k;
            hVar.h(bVar);
            hVar.a(gVar, bVar, 1);
        }
        e();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.q = true;
        this.j.a(this.m, this.k, 1);
        e();
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R$layout.mr_chooser_dialog);
        this.n = new ArrayList<>();
        this.o = new c(getContext(), this.n);
        ListView listView = (ListView) findViewById(R$id.mr_chooser_list);
        this.p = listView;
        listView.setAdapter((ListAdapter) this.o);
        this.p.setOnItemClickListener(this.o);
        this.p.setEmptyView(findViewById(R.id.empty));
        this.l = (TextView) findViewById(R$id.mr_chooser_title);
        getWindow().setLayout(qd1.a(getContext()), -2);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public final void onDetachedFromWindow() {
        this.q = false;
        this.j.h(this.k);
        this.s.removeMessages(1);
        super.onDetachedFromWindow();
    }

    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public final void setTitle(CharSequence charSequence) {
        this.l.setText(charSequence);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MediaRouteChooserDialog(Context context, int i) {
        ContextThemeWrapper contextThemeWrapperA = f.a(i, context, false);
        super(contextThemeWrapperA, f.b(contextThemeWrapperA));
        this.m = g.c;
        this.s = new a();
        this.j = h.d(getContext());
        this.k = new b();
    }

    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public final void setTitle(int i) {
        this.l.setText(i);
    }
}
