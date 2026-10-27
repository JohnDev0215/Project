package androidx.appcompat.app;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.widget.NestedScrollView;
import i0.u;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
class AlertController {
    NestedScrollView A;
    private Drawable C;
    private ImageView D;
    private TextView E;
    private TextView F;
    private View G;
    ListAdapter H;
    private int J;
    private int K;
    int L;
    int M;
    int N;
    int O;
    private boolean P;
    Handler R;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f362a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final f f363b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Window f364c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f365d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private CharSequence f366e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private CharSequence f367f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    ListView f368g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private View f369h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f370i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private int f371j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int f372k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private int f373l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private int f374m;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    Button f376o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private CharSequence f377p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    Message f378q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private Drawable f379r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    Button f380s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private CharSequence f381t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    Message f382u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private Drawable f383v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    Button f384w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private CharSequence f385x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    Message f386y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private Drawable f387z;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private boolean f375n = false;
    private int B = 0;
    int I = -1;
    private int Q = 0;
    private final View.OnClickListener S = new a();

    public static class RecycleListView extends ListView {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final int f388e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final int f389g;

        public RecycleListView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, e.j.RecycleListView);
            this.f389g = typedArrayObtainStyledAttributes.getDimensionPixelOffset(e.j.RecycleListView_paddingBottomNoButtons, -1);
            this.f388e = typedArrayObtainStyledAttributes.getDimensionPixelOffset(e.j.RecycleListView_paddingTopNoTitle, -1);
        }

        public void a(boolean z6, boolean z7) {
            if (z7 && z6) {
                return;
            }
            setPadding(getPaddingLeft(), z6 ? getPaddingTop() : this.f388e, getPaddingRight(), z7 ? getPaddingBottom() : this.f389g);
        }
    }

    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Message message;
            Message message2;
            Message message3;
            AlertController alertController = AlertController.this;
            Message messageObtain = (view != alertController.f376o || (message3 = alertController.f378q) == null) ? (view != alertController.f380s || (message2 = alertController.f382u) == null) ? (view != alertController.f384w || (message = alertController.f386y) == null) ? null : Message.obtain(message) : Message.obtain(message2) : Message.obtain(message3);
            if (messageObtain != null) {
                messageObtain.sendToTarget();
            }
            AlertController alertController2 = AlertController.this;
            alertController2.R.obtainMessage(1, alertController2.f363b).sendToTarget();
        }
    }

    public static class b {
        public int A;
        public int B;
        public int C;
        public int D;
        public boolean[] F;
        public boolean G;
        public boolean H;
        public DialogInterface.OnMultiChoiceClickListener J;
        public Cursor K;
        public String L;
        public String M;
        public AdapterView.OnItemSelectedListener N;
        public e O;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Context f391a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final LayoutInflater f392b;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Drawable f394d;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public CharSequence f396f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public View f397g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public CharSequence f398h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public CharSequence f399i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public Drawable f400j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public DialogInterface.OnClickListener f401k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public CharSequence f402l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public Drawable f403m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public DialogInterface.OnClickListener f404n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public CharSequence f405o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public Drawable f406p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public DialogInterface.OnClickListener f407q;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public DialogInterface.OnCancelListener f409s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public DialogInterface.OnDismissListener f410t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public DialogInterface.OnKeyListener f411u;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public CharSequence[] f412v;

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public ListAdapter f413w;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public DialogInterface.OnClickListener f414x;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public int f415y;

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        public View f416z;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f393c = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f395e = 0;
        public boolean E = false;
        public int I = -1;
        public boolean P = true;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public boolean f408r = true;

        class a extends ArrayAdapter<CharSequence> {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ RecycleListView f417e;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(Context context, int i7, int i8, CharSequence[] charSequenceArr, RecycleListView recycleListView) {
                super(context, i7, i8, charSequenceArr);
                this.f417e = recycleListView;
            }

            @Override // android.widget.ArrayAdapter, android.widget.Adapter
            public View getView(int i7, View view, ViewGroup viewGroup) {
                View view2 = super.getView(i7, view, viewGroup);
                boolean[] zArr = b.this.F;
                if (zArr != null && zArr[i7]) {
                    this.f417e.setItemChecked(i7, true);
                }
                return view2;
            }
        }

        /* JADX INFO: renamed from: androidx.appcompat.app.AlertController$b$b, reason: collision with other inner class name */
        class C0012b extends CursorAdapter {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            private final int f419e;

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            private final int f420g;

            /* JADX INFO: renamed from: h, reason: collision with root package name */
            final /* synthetic */ RecycleListView f421h;

            /* JADX INFO: renamed from: i, reason: collision with root package name */
            final /* synthetic */ AlertController f422i;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0012b(Context context, Cursor cursor, boolean z6, RecycleListView recycleListView, AlertController alertController) {
                super(context, cursor, z6);
                this.f421h = recycleListView;
                this.f422i = alertController;
                Cursor cursor2 = getCursor();
                this.f419e = cursor2.getColumnIndexOrThrow(b.this.L);
                this.f420g = cursor2.getColumnIndexOrThrow(b.this.M);
            }

            @Override // android.widget.CursorAdapter
            public void bindView(View view, Context context, Cursor cursor) {
                ((CheckedTextView) view.findViewById(R.id.text1)).setText(cursor.getString(this.f419e));
                this.f421h.setItemChecked(cursor.getPosition(), cursor.getInt(this.f420g) == 1);
            }

            @Override // android.widget.CursorAdapter
            public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
                return b.this.f392b.inflate(this.f422i.M, viewGroup, false);
            }
        }

        class c implements AdapterView.OnItemClickListener {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ AlertController f424e;

            c(AlertController alertController) {
                this.f424e = alertController;
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i7, long j7) {
                b.this.f414x.onClick(this.f424e.f363b, i7);
                if (b.this.H) {
                    return;
                }
                this.f424e.f363b.dismiss();
            }
        }

        class d implements AdapterView.OnItemClickListener {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ RecycleListView f426e;

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            final /* synthetic */ AlertController f427g;

            d(RecycleListView recycleListView, AlertController alertController) {
                this.f426e = recycleListView;
                this.f427g = alertController;
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i7, long j7) {
                boolean[] zArr = b.this.F;
                if (zArr != null) {
                    zArr[i7] = this.f426e.isItemChecked(i7);
                }
                b.this.J.onClick(this.f427g.f363b, i7, this.f426e.isItemChecked(i7));
            }
        }

        public interface e {
            void a(ListView listView);
        }

        public b(Context context) {
            this.f391a = context;
            this.f392b = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        private void b(AlertController alertController) {
            ListAdapter dVar;
            RecycleListView recycleListView = (RecycleListView) this.f392b.inflate(alertController.L, (ViewGroup) null);
            if (this.G) {
                dVar = this.K == null ? new a(this.f391a, alertController.M, R.id.text1, this.f412v, recycleListView) : new C0012b(this.f391a, this.K, false, recycleListView, alertController);
            } else {
                int i7 = this.H ? alertController.N : alertController.O;
                if (this.K != null) {
                    dVar = new SimpleCursorAdapter(this.f391a, i7, this.K, new String[]{this.L}, new int[]{R.id.text1});
                } else {
                    dVar = this.f413w;
                    if (dVar == null) {
                        dVar = new d(this.f391a, i7, R.id.text1, this.f412v);
                    }
                }
            }
            e eVar = this.O;
            if (eVar != null) {
                eVar.a(recycleListView);
            }
            alertController.H = dVar;
            alertController.I = this.I;
            if (this.f414x != null) {
                recycleListView.setOnItemClickListener(new c(alertController));
            } else if (this.J != null) {
                recycleListView.setOnItemClickListener(new d(recycleListView, alertController));
            }
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.N;
            if (onItemSelectedListener != null) {
                recycleListView.setOnItemSelectedListener(onItemSelectedListener);
            }
            if (this.H) {
                recycleListView.setChoiceMode(1);
            } else if (this.G) {
                recycleListView.setChoiceMode(2);
            }
            alertController.f368g = recycleListView;
        }

        public void a(AlertController alertController) {
            View view = this.f397g;
            if (view != null) {
                alertController.k(view);
            } else {
                CharSequence charSequence = this.f396f;
                if (charSequence != null) {
                    alertController.p(charSequence);
                }
                Drawable drawable = this.f394d;
                if (drawable != null) {
                    alertController.m(drawable);
                }
                int i7 = this.f393c;
                if (i7 != 0) {
                    alertController.l(i7);
                }
                int i8 = this.f395e;
                if (i8 != 0) {
                    alertController.l(alertController.c(i8));
                }
            }
            CharSequence charSequence2 = this.f398h;
            if (charSequence2 != null) {
                alertController.n(charSequence2);
            }
            CharSequence charSequence3 = this.f399i;
            if (charSequence3 != null || this.f400j != null) {
                alertController.j(-1, charSequence3, this.f401k, null, this.f400j);
            }
            CharSequence charSequence4 = this.f402l;
            if (charSequence4 != null || this.f403m != null) {
                alertController.j(-2, charSequence4, this.f404n, null, this.f403m);
            }
            CharSequence charSequence5 = this.f405o;
            if (charSequence5 != null || this.f406p != null) {
                alertController.j(-3, charSequence5, this.f407q, null, this.f406p);
            }
            if (this.f412v != null || this.K != null || this.f413w != null) {
                b(alertController);
            }
            View view2 = this.f416z;
            if (view2 != null) {
                if (this.E) {
                    alertController.s(view2, this.A, this.B, this.C, this.D);
                    return;
                } else {
                    alertController.r(view2);
                    return;
                }
            }
            int i9 = this.f415y;
            if (i9 != 0) {
                alertController.q(i9);
            }
        }
    }

    private static final class c extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private WeakReference<DialogInterface> f429a;

        public c(DialogInterface dialogInterface) {
            this.f429a = new WeakReference<>(dialogInterface);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i7 = message.what;
            if (i7 == -3 || i7 == -2 || i7 == -1) {
                ((DialogInterface.OnClickListener) message.obj).onClick(this.f429a.get(), message.what);
            } else {
                if (i7 != 1) {
                    return;
                }
                ((DialogInterface) message.obj).dismiss();
            }
        }
    }

    private static class d extends ArrayAdapter<CharSequence> {
        public d(Context context, int i7, int i8, CharSequence[] charSequenceArr) {
            super(context, i7, i8, charSequenceArr);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public long getItemId(int i7) {
            return i7;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public boolean hasStableIds() {
            return true;
        }
    }

    public AlertController(Context context, f fVar, Window window) {
        this.f362a = context;
        this.f363b = fVar;
        this.f364c = window;
        this.R = new c(fVar);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, e.j.AlertDialog, e.a.alertDialogStyle, 0);
        this.J = typedArrayObtainStyledAttributes.getResourceId(e.j.AlertDialog_android_layout, 0);
        this.K = typedArrayObtainStyledAttributes.getResourceId(e.j.AlertDialog_buttonPanelSideLayout, 0);
        this.L = typedArrayObtainStyledAttributes.getResourceId(e.j.AlertDialog_listLayout, 0);
        this.M = typedArrayObtainStyledAttributes.getResourceId(e.j.AlertDialog_multiChoiceItemLayout, 0);
        this.N = typedArrayObtainStyledAttributes.getResourceId(e.j.AlertDialog_singleChoiceItemLayout, 0);
        this.O = typedArrayObtainStyledAttributes.getResourceId(e.j.AlertDialog_listItemLayout, 0);
        this.P = typedArrayObtainStyledAttributes.getBoolean(e.j.AlertDialog_showTitle, true);
        this.f365d = typedArrayObtainStyledAttributes.getDimensionPixelSize(e.j.AlertDialog_buttonIconDimen, 0);
        typedArrayObtainStyledAttributes.recycle();
        fVar.d(1);
    }

    static boolean a(View view) {
        if (view.onCheckIsTextEditor()) {
            return true;
        }
        if (!(view instanceof ViewGroup)) {
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        while (childCount > 0) {
            childCount--;
            if (a(viewGroup.getChildAt(childCount))) {
                return true;
            }
        }
        return false;
    }

    private void b(Button button) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button.getLayoutParams();
        layoutParams.gravity = 1;
        layoutParams.weight = 0.5f;
        button.setLayoutParams(layoutParams);
    }

    private ViewGroup h(View view, View view2) {
        if (view == null) {
            if (view2 instanceof ViewStub) {
                view2 = ((ViewStub) view2).inflate();
            }
            return (ViewGroup) view2;
        }
        if (view2 != null) {
            ViewParent parent = view2.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view2);
            }
        }
        if (view instanceof ViewStub) {
            view = ((ViewStub) view).inflate();
        }
        return (ViewGroup) view;
    }

    private int i() {
        int i7 = this.K;
        return i7 == 0 ? this.J : this.Q == 1 ? i7 : this.J;
    }

    private void o(ViewGroup viewGroup, View view, int i7, int i8) {
        View viewFindViewById = this.f364c.findViewById(e.f.scrollIndicatorUp);
        View viewFindViewById2 = this.f364c.findViewById(e.f.scrollIndicatorDown);
        u.y0(view, i7, i8);
        if (viewFindViewById != null) {
            viewGroup.removeView(viewFindViewById);
        }
        if (viewFindViewById2 != null) {
            viewGroup.removeView(viewFindViewById2);
        }
    }

    private void t(ViewGroup viewGroup) {
        int i7;
        Button button = (Button) viewGroup.findViewById(R.id.button1);
        this.f376o = button;
        button.setOnClickListener(this.S);
        if (TextUtils.isEmpty(this.f377p) && this.f379r == null) {
            this.f376o.setVisibility(8);
            i7 = 0;
        } else {
            this.f376o.setText(this.f377p);
            Drawable drawable = this.f379r;
            if (drawable != null) {
                int i8 = this.f365d;
                drawable.setBounds(0, 0, i8, i8);
                this.f376o.setCompoundDrawables(this.f379r, null, null, null);
            }
            this.f376o.setVisibility(0);
            i7 = 1;
        }
        Button button2 = (Button) viewGroup.findViewById(R.id.button2);
        this.f380s = button2;
        button2.setOnClickListener(this.S);
        if (TextUtils.isEmpty(this.f381t) && this.f383v == null) {
            this.f380s.setVisibility(8);
        } else {
            this.f380s.setText(this.f381t);
            Drawable drawable2 = this.f383v;
            if (drawable2 != null) {
                int i9 = this.f365d;
                drawable2.setBounds(0, 0, i9, i9);
                this.f380s.setCompoundDrawables(this.f383v, null, null, null);
            }
            this.f380s.setVisibility(0);
            i7 |= 2;
        }
        Button button3 = (Button) viewGroup.findViewById(R.id.button3);
        this.f384w = button3;
        button3.setOnClickListener(this.S);
        if (TextUtils.isEmpty(this.f385x) && this.f387z == null) {
            this.f384w.setVisibility(8);
        } else {
            this.f384w.setText(this.f385x);
            Drawable drawable3 = this.f387z;
            if (drawable3 != null) {
                int i10 = this.f365d;
                drawable3.setBounds(0, 0, i10, i10);
                this.f384w.setCompoundDrawables(this.f387z, null, null, null);
            }
            this.f384w.setVisibility(0);
            i7 |= 4;
        }
        if (y(this.f362a)) {
            if (i7 == 1) {
                b(this.f376o);
            } else if (i7 == 2) {
                b(this.f380s);
            } else if (i7 == 4) {
                b(this.f384w);
            }
        }
        if (i7 != 0) {
            return;
        }
        viewGroup.setVisibility(8);
    }

    private void u(ViewGroup viewGroup) {
        NestedScrollView nestedScrollView = (NestedScrollView) this.f364c.findViewById(e.f.scrollView);
        this.A = nestedScrollView;
        nestedScrollView.setFocusable(false);
        this.A.setNestedScrollingEnabled(false);
        TextView textView = (TextView) viewGroup.findViewById(R.id.message);
        this.F = textView;
        if (textView == null) {
            return;
        }
        CharSequence charSequence = this.f367f;
        if (charSequence != null) {
            textView.setText(charSequence);
            return;
        }
        textView.setVisibility(8);
        this.A.removeView(this.F);
        if (this.f368g == null) {
            viewGroup.setVisibility(8);
            return;
        }
        ViewGroup viewGroup2 = (ViewGroup) this.A.getParent();
        int iIndexOfChild = viewGroup2.indexOfChild(this.A);
        viewGroup2.removeViewAt(iIndexOfChild);
        viewGroup2.addView(this.f368g, iIndexOfChild, new ViewGroup.LayoutParams(-1, -1));
    }

    private void v(ViewGroup viewGroup) {
        View viewInflate = this.f369h;
        if (viewInflate == null) {
            viewInflate = this.f370i != 0 ? LayoutInflater.from(this.f362a).inflate(this.f370i, viewGroup, false) : null;
        }
        boolean z6 = viewInflate != null;
        if (!z6 || !a(viewInflate)) {
            this.f364c.setFlags(131072, 131072);
        }
        if (!z6) {
            viewGroup.setVisibility(8);
            return;
        }
        FrameLayout frameLayout = (FrameLayout) this.f364c.findViewById(e.f.custom);
        frameLayout.addView(viewInflate, new ViewGroup.LayoutParams(-1, -1));
        if (this.f375n) {
            frameLayout.setPadding(this.f371j, this.f372k, this.f373l, this.f374m);
        }
        if (this.f368g != null) {
            ((LinearLayout.LayoutParams) ((LinearLayoutCompat.a) viewGroup.getLayoutParams())).weight = 0.0f;
        }
    }

    private void w(ViewGroup viewGroup) {
        if (this.G != null) {
            viewGroup.addView(this.G, 0, new ViewGroup.LayoutParams(-1, -2));
            this.f364c.findViewById(e.f.title_template).setVisibility(8);
            return;
        }
        this.D = (ImageView) this.f364c.findViewById(R.id.icon);
        if (!(!TextUtils.isEmpty(this.f366e)) || !this.P) {
            this.f364c.findViewById(e.f.title_template).setVisibility(8);
            this.D.setVisibility(8);
            viewGroup.setVisibility(8);
            return;
        }
        TextView textView = (TextView) this.f364c.findViewById(e.f.alertTitle);
        this.E = textView;
        textView.setText(this.f366e);
        int i7 = this.B;
        if (i7 != 0) {
            this.D.setImageResource(i7);
            return;
        }
        Drawable drawable = this.C;
        if (drawable != null) {
            this.D.setImageDrawable(drawable);
        } else {
            this.E.setPadding(this.D.getPaddingLeft(), this.D.getPaddingTop(), this.D.getPaddingRight(), this.D.getPaddingBottom());
            this.D.setVisibility(8);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void x() {
        View viewFindViewById;
        ListAdapter listAdapter;
        View viewFindViewById2;
        View viewFindViewById3 = this.f364c.findViewById(e.f.parentPanel);
        int i7 = e.f.topPanel;
        View viewFindViewById4 = viewFindViewById3.findViewById(i7);
        int i8 = e.f.contentPanel;
        View viewFindViewById5 = viewFindViewById3.findViewById(i8);
        int i9 = e.f.buttonPanel;
        View viewFindViewById6 = viewFindViewById3.findViewById(i9);
        ViewGroup viewGroup = (ViewGroup) viewFindViewById3.findViewById(e.f.customPanel);
        v(viewGroup);
        View viewFindViewById7 = viewGroup.findViewById(i7);
        View viewFindViewById8 = viewGroup.findViewById(i8);
        View viewFindViewById9 = viewGroup.findViewById(i9);
        ViewGroup viewGroupH = h(viewFindViewById7, viewFindViewById4);
        ViewGroup viewGroupH2 = h(viewFindViewById8, viewFindViewById5);
        ViewGroup viewGroupH3 = h(viewFindViewById9, viewFindViewById6);
        u(viewGroupH2);
        t(viewGroupH3);
        w(viewGroupH);
        boolean z6 = viewGroup.getVisibility() != 8;
        boolean z7 = (viewGroupH == null || viewGroupH.getVisibility() == 8) ? 0 : 1;
        boolean z8 = (viewGroupH3 == null || viewGroupH3.getVisibility() == 8) ? false : true;
        if (!z8 && viewGroupH2 != null && (viewFindViewById2 = viewGroupH2.findViewById(e.f.textSpacerNoButtons)) != null) {
            viewFindViewById2.setVisibility(0);
        }
        if (z7 != 0) {
            NestedScrollView nestedScrollView = this.A;
            if (nestedScrollView != null) {
                nestedScrollView.setClipToPadding(true);
            }
            View viewFindViewById10 = (this.f367f == null && this.f368g == null) ? null : viewGroupH.findViewById(e.f.titleDividerNoCustom);
            if (viewFindViewById10 != null) {
                viewFindViewById10.setVisibility(0);
            }
        } else if (viewGroupH2 != null && (viewFindViewById = viewGroupH2.findViewById(e.f.textSpacerNoTitle)) != null) {
            viewFindViewById.setVisibility(0);
        }
        ListView listView = this.f368g;
        if (listView instanceof RecycleListView) {
            ((RecycleListView) listView).a(z7, z8);
        }
        if (!z6) {
            View view = this.f368g;
            if (view == null) {
                view = this.A;
            }
            if (view != null) {
                o(viewGroupH2, view, z7 | (z8 ? 2 : 0), 3);
            }
        }
        ListView listView2 = this.f368g;
        if (listView2 == null || (listAdapter = this.H) == null) {
            return;
        }
        listView2.setAdapter(listAdapter);
        int i10 = this.I;
        if (i10 > -1) {
            listView2.setItemChecked(i10, true);
            listView2.setSelection(i10);
        }
    }

    private static boolean y(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(e.a.alertDialogCenterButtons, typedValue, true);
        return typedValue.data != 0;
    }

    public int c(int i7) {
        TypedValue typedValue = new TypedValue();
        this.f362a.getTheme().resolveAttribute(i7, typedValue, true);
        return typedValue.resourceId;
    }

    public ListView d() {
        return this.f368g;
    }

    public void e() {
        this.f363b.setContentView(i());
        x();
    }

    public boolean f(int i7, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.A;
        return nestedScrollView != null && nestedScrollView.q(keyEvent);
    }

    public boolean g(int i7, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.A;
        return nestedScrollView != null && nestedScrollView.q(keyEvent);
    }

    public void j(int i7, CharSequence charSequence, DialogInterface.OnClickListener onClickListener, Message message, Drawable drawable) {
        if (message == null && onClickListener != null) {
            message = this.R.obtainMessage(i7, onClickListener);
        }
        if (i7 == -3) {
            this.f385x = charSequence;
            this.f386y = message;
            this.f387z = drawable;
        } else if (i7 == -2) {
            this.f381t = charSequence;
            this.f382u = message;
            this.f383v = drawable;
        } else {
            if (i7 != -1) {
                throw new IllegalArgumentException("Button does not exist");
            }
            this.f377p = charSequence;
            this.f378q = message;
            this.f379r = drawable;
        }
    }

    public void k(View view) {
        this.G = view;
    }

    public void l(int i7) {
        this.C = null;
        this.B = i7;
        ImageView imageView = this.D;
        if (imageView != null) {
            if (i7 == 0) {
                imageView.setVisibility(8);
            } else {
                imageView.setVisibility(0);
                this.D.setImageResource(this.B);
            }
        }
    }

    public void m(Drawable drawable) {
        this.C = drawable;
        this.B = 0;
        ImageView imageView = this.D;
        if (imageView != null) {
            if (drawable == null) {
                imageView.setVisibility(8);
            } else {
                imageView.setVisibility(0);
                this.D.setImageDrawable(drawable);
            }
        }
    }

    public void n(CharSequence charSequence) {
        this.f367f = charSequence;
        TextView textView = this.F;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public void p(CharSequence charSequence) {
        this.f366e = charSequence;
        TextView textView = this.E;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public void q(int i7) {
        this.f369h = null;
        this.f370i = i7;
        this.f375n = false;
    }

    public void r(View view) {
        this.f369h = view;
        this.f370i = 0;
        this.f375n = false;
    }

    public void s(View view, int i7, int i8, int i9, int i10) {
        this.f369h = view;
        this.f370i = 0;
        this.f375n = true;
        this.f371j = i7;
        this.f372k = i8;
        this.f373l = i9;
        this.f374m = i10;
    }
}
