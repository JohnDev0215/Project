package androidx.mediarouter.app;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.mediarouter.R$id;
import androidx.mediarouter.R$integer;
import androidx.mediarouter.R$layout;
import androidx.mediarouter.R$string;
import androidx.mediarouter.media.t;
import androidx.mediarouter.media.u;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class h extends androidx.appcompat.app.c {
    final u e;
    private final c f;
    Context g;
    private t h;
    List<u.g> i;
    private ImageButton j;
    private d k;
    private RecyclerView l;
    private boolean m;
    private long n;
    private long o;
    private final Handler p;

    /* JADX INFO: compiled from: caradapter-bug */
    class a extends Handler {
        a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            h.this.m((List) message.obj);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            h.this.dismiss();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private final class c extends u.b {
        c() {
        }

        @Override // androidx.mediarouter.media.u.b
        public void d(u uVar, u.g gVar) {
            h.this.h();
        }

        @Override // androidx.mediarouter.media.u.b
        public void e(u uVar, u.g gVar) {
            h.this.h();
        }

        @Override // androidx.mediarouter.media.u.b
        public void g(u uVar, u.g gVar) {
            h.this.h();
        }

        @Override // androidx.mediarouter.media.u.b
        public void h(u uVar, u.g gVar) {
            h.this.dismiss();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private final class d extends RecyclerView.Adapter<RecyclerView.A> {
        private final ArrayList<b> d = new ArrayList<>();
        private final LayoutInflater e;
        private final Drawable f;
        private final Drawable g;
        private final Drawable h;
        private final Drawable i;

        /* JADX INFO: compiled from: caradapter-bug */
        private class a extends RecyclerView.A {
            TextView t;

            a(d dVar, View view) {
                super(view);
                this.t = (TextView) view.findViewById(R$id.mr_picker_header_name);
            }

            public void M(b bVar) {
                this.t.setText(bVar.a().toString());
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        private class b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final Object f887a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private final int f888b;

            b(d dVar, Object obj) {
                this.f887a = obj;
                if (obj instanceof String) {
                    this.f888b = 1;
                } else if (obj instanceof u.g) {
                    this.f888b = 2;
                } else {
                    this.f888b = 0;
                    Log.w("RecyclerAdapter", "Wrong type of data passed to Item constructor");
                }
            }

            public Object a() {
                return this.f887a;
            }

            public int b() {
                return this.f888b;
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        private class c extends RecyclerView.A {
            final View t;
            final ImageView u;
            final ProgressBar v;
            final TextView w;

            /* JADX INFO: compiled from: caradapter-bug */
            class a implements View.OnClickListener {

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ u.g f889c;

                a(u.g gVar) {
                    this.f889c = gVar;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    this.f889c.H();
                    c.this.u.setVisibility(4);
                    c.this.v.setVisibility(0);
                }
            }

            c(View view) {
                super(view);
                this.t = view;
                this.u = (ImageView) view.findViewById(R$id.mr_picker_route_icon);
                this.v = (ProgressBar) view.findViewById(R$id.mr_picker_route_progress_bar);
                this.w = (TextView) view.findViewById(R$id.mr_picker_route_name);
                j.t(h.this.g, this.v);
            }

            public void M(b bVar) {
                u.g gVar = (u.g) bVar.a();
                this.t.setVisibility(0);
                this.v.setVisibility(4);
                this.t.setOnClickListener(new a(gVar));
                this.w.setText(gVar.l());
                this.u.setImageDrawable(d.this.D(gVar));
            }
        }

        d() {
            this.e = LayoutInflater.from(h.this.g);
            this.f = j.g(h.this.g);
            this.g = j.q(h.this.g);
            this.h = j.m(h.this.g);
            this.i = j.n(h.this.g);
            F();
        }

        private Drawable C(u.g gVar) {
            int iF = gVar.f();
            return iF != 1 ? iF != 2 ? gVar.x() ? this.i : this.f : this.h : this.g;
        }

        Drawable D(u.g gVar) {
            Uri uriI = gVar.i();
            if (uriI != null) {
                try {
                    Drawable drawableCreateFromStream = Drawable.createFromStream(h.this.g.getContentResolver().openInputStream(uriI), null);
                    if (drawableCreateFromStream != null) {
                        return drawableCreateFromStream;
                    }
                } catch (IOException e) {
                    Log.w("RecyclerAdapter", "Failed to load " + uriI, e);
                }
            }
            return C(gVar);
        }

        public b E(int i) {
            return this.d.get(i);
        }

        void F() {
            this.d.clear();
            this.d.add(new b(this, h.this.g.getString(R$string.mr_chooser_title)));
            Iterator<u.g> it = h.this.i.iterator();
            while (it.hasNext()) {
                this.d.add(new b(this, it.next()));
            }
            k();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int f() {
            return this.d.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int h(int i) {
            return this.d.get(i).b();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void r(RecyclerView.A a2, int i) {
            int iH = h(i);
            b bVarE = E(i);
            if (iH == 1) {
                ((a) a2).M(bVarE);
            } else if (iH != 2) {
                Log.w("RecyclerAdapter", "Cannot bind item to ViewHolder because of wrong view type");
            } else {
                ((c) a2).M(bVarE);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.A t(ViewGroup viewGroup, int i) {
            if (i == 1) {
                return new a(this, this.e.inflate(R$layout.mr_picker_header_item, viewGroup, false));
            }
            if (i == 2) {
                return new c(this.e.inflate(R$layout.mr_picker_route_item, viewGroup, false));
            }
            Log.w("RecyclerAdapter", "Cannot create ViewHolder because of wrong view type");
            return null;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static final class e implements Comparator<u.g> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final e f890c = new e();

        e() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(u.g gVar, u.g gVar2) {
            return gVar.l().compareToIgnoreCase(gVar2.l());
        }
    }

    public h(Context context) {
        this(context, 0);
    }

    public boolean e(@NonNull u.g gVar) {
        return !gVar.v() && gVar.w() && gVar.D(this.h);
    }

    public void f(@NonNull List<u.g> list) {
        int size = list.size();
        while (true) {
            int i = size - 1;
            if (size <= 0) {
                return;
            }
            if (!e(list.get(i))) {
                list.remove(i);
            }
            size = i;
        }
    }

    public void h() {
        if (this.m) {
            ArrayList arrayList = new ArrayList(this.e.i());
            f(arrayList);
            Collections.sort(arrayList, e.f890c);
            if (SystemClock.uptimeMillis() - this.o >= this.n) {
                m(arrayList);
                return;
            }
            this.p.removeMessages(1);
            Handler handler = this.p;
            handler.sendMessageAtTime(handler.obtainMessage(1, arrayList), this.o + this.n);
        }
    }

    public void k(@NonNull t tVar) {
        if (tVar == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        if (this.h.equals(tVar)) {
            return;
        }
        this.h = tVar;
        if (this.m) {
            this.e.m(this.f);
            this.e.b(tVar, this.f, 1);
        }
        h();
    }

    void l() {
        getWindow().setLayout(g.c(this.g), g.a(this.g));
    }

    void m(List<u.g> list) {
        this.o = SystemClock.uptimeMillis();
        this.i.clear();
        this.i.addAll(list);
        this.k.F();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    @CallSuper
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.m = true;
        this.e.b(this.h, this.f, 1);
        h();
    }

    @Override // androidx.appcompat.app.c, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R$layout.mr_picker_dialog);
        j.s(this.g, this);
        this.i = new ArrayList();
        ImageButton imageButton = (ImageButton) findViewById(R$id.mr_picker_close_button);
        this.j = imageButton;
        imageButton.setOnClickListener(new b());
        this.k = new d();
        RecyclerView recyclerView = (RecyclerView) findViewById(R$id.mr_picker_list);
        this.l = recyclerView;
        recyclerView.l1(this.k);
        this.l.o1(new LinearLayoutManager(this.g));
        l();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    @CallSuper
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.m = false;
        this.e.m(this.f);
        this.p.removeMessages(1);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public h(Context context, int i) {
        Context contextB = j.b(context, i, false);
        super(contextB, j.c(contextB));
        this.h = t.f996c;
        this.p = new a();
        Context context2 = getContext();
        this.e = u.g(context2);
        this.f = new c();
        this.g = context2;
        this.n = context2.getResources().getInteger(R$integer.mr_update_routes_delay_ms);
    }
}
