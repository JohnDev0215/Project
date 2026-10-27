package androidx.mediarouter.app;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.mediarouter.R$attr;
import androidx.mediarouter.R$id;
import androidx.mediarouter.R$layout;
import androidx.mediarouter.media.t;
import androidx.mediarouter.media.u;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class b extends androidx.appcompat.app.c {
    private final u e;
    private final C0030b f;
    private TextView g;
    private t h;
    private ArrayList<u.g> i;
    private c j;
    private ListView k;
    private boolean l;
    private long m;
    private final Handler n;

    /* JADX INFO: compiled from: caradapter-bug */
    class a extends Handler {
        a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            b.this.m((List) message.obj);
        }
    }

    /* JADX INFO: renamed from: androidx.mediarouter.app.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    private final class C0030b extends u.b {
        C0030b() {
        }

        @Override // androidx.mediarouter.media.u.b
        public void d(u uVar, u.g gVar) {
            b.this.h();
        }

        @Override // androidx.mediarouter.media.u.b
        public void e(u uVar, u.g gVar) {
            b.this.h();
        }

        @Override // androidx.mediarouter.media.u.b
        public void g(u uVar, u.g gVar) {
            b.this.h();
        }

        @Override // androidx.mediarouter.media.u.b
        public void h(u uVar, u.g gVar) {
            b.this.dismiss();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private final class c extends ArrayAdapter<u.g> implements AdapterView.OnItemClickListener {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final LayoutInflater f860c;
        private final Drawable d;
        private final Drawable e;
        private final Drawable f;
        private final Drawable g;

        public c(Context context, List<u.g> list) {
            super(context, 0, list);
            this.f860c = LayoutInflater.from(context);
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(new int[]{R$attr.mediaRouteDefaultIconDrawable, R$attr.mediaRouteTvIconDrawable, R$attr.mediaRouteSpeakerIconDrawable, R$attr.mediaRouteSpeakerGroupIconDrawable});
            this.d = typedArrayObtainStyledAttributes.getDrawable(0);
            this.e = typedArrayObtainStyledAttributes.getDrawable(1);
            this.f = typedArrayObtainStyledAttributes.getDrawable(2);
            this.g = typedArrayObtainStyledAttributes.getDrawable(3);
            typedArrayObtainStyledAttributes.recycle();
        }

        private Drawable a(u.g gVar) {
            int iF = gVar.f();
            return iF != 1 ? iF != 2 ? gVar.x() ? this.g : this.d : this.f : this.e;
        }

        private Drawable b(u.g gVar) {
            Uri uriI = gVar.i();
            if (uriI != null) {
                try {
                    Drawable drawableCreateFromStream = Drawable.createFromStream(getContext().getContentResolver().openInputStream(uriI), null);
                    if (drawableCreateFromStream != null) {
                        return drawableCreateFromStream;
                    }
                } catch (IOException e) {
                    Log.w("MediaRouteChooserDialog", "Failed to load " + uriI, e);
                }
            }
            return a(gVar);
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = this.f860c.inflate(R$layout.mr_chooser_list_item, viewGroup, false);
            }
            u.g item = getItem(i);
            TextView textView = (TextView) view.findViewById(R$id.mr_chooser_route_name);
            TextView textView2 = (TextView) view.findViewById(R$id.mr_chooser_route_desc);
            textView.setText(item.l());
            String strD = item.d();
            boolean z = true;
            if (item.c() != 2 && item.c() != 1) {
                z = false;
            }
            if (!z || TextUtils.isEmpty(strD)) {
                textView.setGravity(16);
                textView2.setVisibility(8);
                textView2.setText("");
            } else {
                textView.setGravity(80);
                textView2.setVisibility(0);
                textView2.setText(strD);
            }
            view.setEnabled(item.w());
            ImageView imageView = (ImageView) view.findViewById(R$id.mr_chooser_route_icon);
            if (imageView != null) {
                imageView.setImageDrawable(b(item));
            }
            return view;
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean isEnabled(int i) {
            return getItem(i).w();
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            u.g item = getItem(i);
            if (item.w()) {
                item.H();
                b.this.dismiss();
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static final class d implements Comparator<u.g> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final d f861c = new d();

        d() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(u.g gVar, u.g gVar2) {
            return gVar.l().compareToIgnoreCase(gVar2.l());
        }
    }

    public b(Context context) {
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
        if (this.l) {
            ArrayList arrayList = new ArrayList(this.e.i());
            f(arrayList);
            Collections.sort(arrayList, d.f861c);
            if (SystemClock.uptimeMillis() - this.m >= 300) {
                m(arrayList);
                return;
            }
            this.n.removeMessages(1);
            Handler handler = this.n;
            handler.sendMessageAtTime(handler.obtainMessage(1, arrayList), this.m + 300);
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
        if (this.l) {
            this.e.m(this.f);
            this.e.b(tVar, this.f, 1);
        }
        h();
    }

    void l() {
        getWindow().setLayout(g.b(getContext()), -2);
    }

    void m(List<u.g> list) {
        this.m = SystemClock.uptimeMillis();
        this.i.clear();
        this.i.addAll(list);
        this.j.notifyDataSetChanged();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.l = true;
        this.e.b(this.h, this.f, 1);
        h();
    }

    @Override // androidx.appcompat.app.c, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R$layout.mr_chooser_dialog);
        this.i = new ArrayList<>();
        this.j = new c(getContext(), this.i);
        ListView listView = (ListView) findViewById(R$id.mr_chooser_list);
        this.k = listView;
        listView.setAdapter((ListAdapter) this.j);
        this.k.setOnItemClickListener(this.j);
        this.k.setEmptyView(findViewById(R.id.empty));
        this.g = (TextView) findViewById(R$id.mr_chooser_title);
        l();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        this.l = false;
        this.e.m(this.f);
        this.n.removeMessages(1);
        super.onDetachedFromWindow();
    }

    @Override // androidx.appcompat.app.c, android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        this.g.setText(charSequence);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public b(Context context, int i) {
        Context contextB = j.b(context, i, false);
        super(contextB, j.c(contextB));
        this.h = t.f996c;
        this.n = new a();
        this.e = u.g(getContext());
        this.f = new C0030b();
    }

    @Override // androidx.appcompat.app.c, android.app.Dialog
    public void setTitle(int i) {
        this.g.setText(i);
    }
}
