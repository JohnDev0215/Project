package c.a.i.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: UpdateFilesChoiceDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends c.a.i.a.a {
    public static boolean s = false;
    public RecyclerView E3;
    public d F3;
    public RecyclerView G3;
    public d H3;
    public List<f> I3;
    public List<f> J3;
    public Resources K3;
    public TextView L3;
    public TextView M3;
    public TextView N3;
    public TextView O3;
    public String P3;
    public String Q3;
    public c x;
    public Button y;

    /* JADX INFO: compiled from: UpdateFilesChoiceDialog.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (i.this.F3.u() == null) {
                return;
            }
            i.this.x.a(i.this.F3.u());
            i.this.dismiss();
        }
    }

    /* JADX INFO: compiled from: UpdateFilesChoiceDialog.java */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            i.this.x.b();
            i.this.dismiss();
        }
    }

    /* JADX INFO: compiled from: UpdateFilesChoiceDialog.java */
    public interface c {
        void a(f fVar);

        void b();
    }

    /* JADX INFO: compiled from: UpdateFilesChoiceDialog.java */
    public static class e extends RecyclerView.c0 {
        public RelativeLayout t;
        public ImageView u;
        public ImageView v;
        public TextView w;
        public TextView x;

        public e(View view) {
            super(view);
            this.t = (RelativeLayout) view.findViewById(c.a.e.c.k);
            this.u = (ImageView) view.findViewById(c.a.e.c.r);
            this.v = (ImageView) view.findViewById(c.a.e.c.s);
            this.w = (TextView) view.findViewById(c.a.e.c.l);
            this.x = (TextView) view.findViewById(c.a.e.c.n);
        }
    }

    /* JADX INFO: compiled from: UpdateFilesChoiceDialog.java */
    public static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public File f2806a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f2807b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2808c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f2809d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f2810e;
    }

    public i(Context context) {
        super(context);
        this.I3 = new ArrayList();
        this.J3 = new ArrayList();
        this.K3 = context.getResources();
        setContentView(c.a.e.d.f2641h);
        this.L3 = (TextView) findViewById(c.a.e.c.f2631g);
        this.M3 = (TextView) findViewById(c.a.e.c.v);
        this.E3 = (RecyclerView) findViewById(c.a.e.c.m);
        a aVar = null;
        this.F3 = new d(this, aVar);
        this.E3.setLayoutManager(new LinearLayoutManager(this.f2785c, 1, false));
        this.E3.setAdapter(this.F3);
        Button button = (Button) findViewById(c.a.e.c.f2626b);
        this.y = button;
        button.setOnClickListener(new a());
        ((Button) findViewById(c.a.e.c.f2625a)).setOnClickListener(new b());
        this.P3 = this.K3.getString(c.a.e.e.f2651i);
        this.Q3 = this.K3.getString(c.a.e.e.r);
        this.G3 = (RecyclerView) findViewById(c.a.e.c.f2629e);
        this.H3 = new d(this, aVar);
        this.G3.setLayoutManager(new LinearLayoutManager(this.f2785c, 1, false));
        this.G3.setAdapter(this.H3);
        this.N3 = (TextView) findViewById(c.a.e.c.f2627c);
        this.O3 = (TextView) findViewById(c.a.e.c.f2630f);
        View viewFindViewById = findViewById(c.a.e.c.f2628d);
        if (s) {
            return;
        }
        viewFindViewById.setVisibility(8);
    }

    public void e(c cVar) {
        this.x = cVar;
    }

    public void f(String str) {
        this.L3.setText(String.format(this.P3, str));
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public void g(List<f> list) {
        this.I3.clear();
        this.I3.addAll(list);
        this.F3.x(this.I3);
        this.F3.g();
    }

    public final void h(String str) {
        this.M3.setText(String.format(this.Q3, str));
    }

    /* JADX INFO: compiled from: UpdateFilesChoiceDialog.java */
    public class d extends RecyclerView.g<e> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public List<f> f2801c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public f f2802d;

        /* JADX INFO: compiled from: UpdateFilesChoiceDialog.java */
        public class a implements View.OnClickListener {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ f f2804c;

            public a(f fVar) {
                this.f2804c = fVar;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (d.this.f2802d != null) {
                    d.this.f2802d.f2810e = false;
                }
                d.this.f2802d = this.f2804c;
                f fVar = this.f2804c;
                fVar.f2810e = true;
                i.this.h(fVar.f2807b);
                d.this.g();
            }
        }

        public d() {
            this.f2801c = new ArrayList();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int c() {
            return this.f2801c.size();
        }

        public f u() {
            return this.f2802d;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: v, reason: merged with bridge method [inline-methods] */
        public void i(e eVar, int i2) {
            f fVar = this.f2801c.get(i2);
            eVar.t.setOnClickListener(new a(fVar));
            c.a.k.b.c("onBindViewHolder--" + i2);
            eVar.w.setText(fVar.f2807b);
            eVar.x.setText(fVar.f2809d);
            if (fVar.f2810e) {
                eVar.t.setBackground(i.this.K3.getDrawable(c.a.e.b.f2623d, null));
                eVar.u.setSelected(true);
            } else {
                eVar.t.setBackground(i.this.K3.getDrawable(c.a.e.b.f2624e, null));
                eVar.u.setSelected(false);
            }
            int i3 = fVar.f2808c;
            if (i3 == 1) {
                eVar.v.setImageDrawable(i.this.K3.getDrawable(c.a.e.b.f2622c, null));
            } else if (i3 == 2) {
                eVar.v.setImageDrawable(i.this.K3.getDrawable(c.a.e.b.f2621b, null));
            } else {
                if (i3 != 3) {
                    return;
                }
                eVar.v.setImageDrawable(i.this.K3.getDrawable(c.a.e.b.f2620a, null));
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public e k(ViewGroup viewGroup, int i2) {
            return new e(LayoutInflater.from(i.this.f2785c).inflate(c.a.e.d.j, viewGroup, false));
        }

        public void x(List<f> list) {
            this.f2801c.clear();
            this.f2801c.addAll(list);
        }

        public /* synthetic */ d(i iVar, a aVar) {
            this();
        }
    }
}
