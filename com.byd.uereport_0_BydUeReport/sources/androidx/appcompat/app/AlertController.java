package androidx.appcompat.app;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build;
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
import android.widget.AbsListView;
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
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import androidx.appcompat.R$styleable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.widget.NestedScrollView;
import java.lang.ref.WeakReference;
import o0.t;
import ts.car.service.carplay.data.config.CarPlayConfig;

/* JADX INFO: loaded from: classes.dex */
public class AlertController {
    public NestedScrollView A;
    public Drawable C;
    public ImageView D;
    public TextView E;
    public TextView F;
    public View G;
    public ListAdapter H;
    public int J;
    public int K;
    public int L;
    public int M;
    public int N;
    public int O;
    public boolean P;
    public Handler R;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f241a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final l.f f242b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Window f243c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f244d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CharSequence f245e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public CharSequence f246f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ListView f247g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public View f248h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f249i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f250j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f251k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f252l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f253m;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Button f255o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public CharSequence f256p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Message f257q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Drawable f258r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Button f259s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public CharSequence f260t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Message f261u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Drawable f262v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public Button f263w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public CharSequence f264x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public Message f265y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public Drawable f266z;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f254n = false;
    public int B = 0;
    public int I = -1;
    public int Q = 0;
    public final View.OnClickListener S = new a();

    public static class RecycleListView extends ListView {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f267d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f268e;

        public RecycleListView(Context context) {
            this(context, null);
        }

        public void a(boolean z7, boolean z8) {
            if (z8 && z7) {
                return;
            }
            setPadding(getPaddingLeft(), z7 ? getPaddingTop() : this.f267d, getPaddingRight(), z8 ? getPaddingBottom() : this.f268e);
        }

        public RecycleListView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RecycleListView);
            this.f268e = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.RecycleListView_paddingBottomNoButtons, -1);
            this.f267d = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.RecycleListView_paddingTopNoTitle, -1);
        }
    }

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Message message;
            Message message2;
            Message message3;
            AlertController alertController = AlertController.this;
            Message messageObtain = (view != alertController.f255o || (message3 = alertController.f257q) == null) ? (view != alertController.f259s || (message2 = alertController.f261u) == null) ? (view != alertController.f263w || (message = alertController.f265y) == null) ? null : Message.obtain(message) : Message.obtain(message2) : Message.obtain(message3);
            if (messageObtain != null) {
                messageObtain.sendToTarget();
            }
            AlertController alertController2 = AlertController.this;
            alertController2.R.obtainMessage(1, alertController2.f242b).sendToTarget();
        }
    }

    public class b implements NestedScrollView.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f270a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f271b;

        public b(View view, View view2) {
            this.f270a = view;
            this.f271b = view2;
        }

        @Override // androidx.core.widget.NestedScrollView.b
        public void a(NestedScrollView nestedScrollView, int i8, int i9, int i10, int i11) {
            AlertController.f(nestedScrollView, this.f270a, this.f271b);
        }
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ View f273d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ View f274e;

        public c(View view, View view2) {
            this.f273d = view;
            this.f274e = view2;
        }

        @Override // java.lang.Runnable
        public void run() {
            AlertController.f(AlertController.this.A, this.f273d, this.f274e);
        }
    }

    public class d implements AbsListView.OnScrollListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f276a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f277b;

        public d(View view, View view2) {
            this.f276a = view;
            this.f277b = view2;
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i8, int i9, int i10) {
            AlertController.f(absListView, this.f276a, this.f277b);
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i8) {
        }
    }

    public class e implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ View f279d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ View f280e;

        public e(View view, View view2) {
            this.f279d = view;
            this.f280e = view2;
        }

        @Override // java.lang.Runnable
        public void run() {
            AlertController.f(AlertController.this.f247g, this.f279d, this.f280e);
        }
    }

    public static class f {
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
        public final Context f282a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final LayoutInflater f283b;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Drawable f285d;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public CharSequence f287f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public View f288g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public CharSequence f289h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public CharSequence f290i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public Drawable f291j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public DialogInterface.OnClickListener f292k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public CharSequence f293l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public Drawable f294m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public DialogInterface.OnClickListener f295n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public CharSequence f296o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public Drawable f297p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public DialogInterface.OnClickListener f298q;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public DialogInterface.OnCancelListener f300s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public DialogInterface.OnDismissListener f301t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public DialogInterface.OnKeyListener f302u;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public CharSequence[] f303v;

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public ListAdapter f304w;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public DialogInterface.OnClickListener f305x;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public int f306y;

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        public View f307z;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f284c = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f286e = 0;
        public boolean E = false;
        public int I = -1;
        public boolean P = true;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public boolean f299r = true;

        public class a extends ArrayAdapter<CharSequence> {

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ RecycleListView f308d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(Context context, int i8, int i9, CharSequence[] charSequenceArr, RecycleListView recycleListView) {
                super(context, i8, i9, charSequenceArr);
                this.f308d = recycleListView;
            }

            @Override // android.widget.ArrayAdapter, android.widget.Adapter
            public View getView(int i8, View view, ViewGroup viewGroup) {
                View view2 = super.getView(i8, view, viewGroup);
                boolean[] zArr = f.this.F;
                if (zArr != null && zArr[i8]) {
                    this.f308d.setItemChecked(i8, true);
                }
                return view2;
            }
        }

        public class b extends CursorAdapter {

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final int f310d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final int f311e;

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            public final /* synthetic */ RecycleListView f312f;

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            public final /* synthetic */ AlertController f313g;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(Context context, Cursor cursor, boolean z7, RecycleListView recycleListView, AlertController alertController) {
                super(context, cursor, z7);
                this.f312f = recycleListView;
                this.f313g = alertController;
                Cursor cursor2 = getCursor();
                this.f310d = cursor2.getColumnIndexOrThrow(f.this.L);
                this.f311e = cursor2.getColumnIndexOrThrow(f.this.M);
            }

            @Override // android.widget.CursorAdapter
            public void bindView(View view, Context context, Cursor cursor) {
                ((CheckedTextView) view.findViewById(R.id.text1)).setText(cursor.getString(this.f310d));
                this.f312f.setItemChecked(cursor.getPosition(), cursor.getInt(this.f311e) == 1);
            }

            @Override // android.widget.CursorAdapter
            public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
                return f.this.f283b.inflate(this.f313g.M, viewGroup, false);
            }
        }

        public class c implements AdapterView.OnItemClickListener {

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ AlertController f315d;

            public c(AlertController alertController) {
                this.f315d = alertController;
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i8, long j8) {
                f.this.f305x.onClick(this.f315d.f242b, i8);
                if (f.this.H) {
                    return;
                }
                this.f315d.f242b.dismiss();
            }
        }

        public class d implements AdapterView.OnItemClickListener {

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ RecycleListView f317d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ AlertController f318e;

            public d(RecycleListView recycleListView, AlertController alertController) {
                this.f317d = recycleListView;
                this.f318e = alertController;
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i8, long j8) {
                boolean[] zArr = f.this.F;
                if (zArr != null) {
                    zArr[i8] = this.f317d.isItemChecked(i8);
                }
                f.this.J.onClick(this.f318e.f242b, i8, this.f317d.isItemChecked(i8));
            }
        }

        public interface e {
            void a(ListView listView);
        }

        public f(Context context) {
            this.f282a = context;
            this.f283b = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        public void a(AlertController alertController) {
            View view = this.f288g;
            if (view != null) {
                alertController.l(view);
            } else {
                CharSequence charSequence = this.f287f;
                if (charSequence != null) {
                    alertController.q(charSequence);
                }
                Drawable drawable = this.f285d;
                if (drawable != null) {
                    alertController.n(drawable);
                }
                int i8 = this.f284c;
                if (i8 != 0) {
                    alertController.m(i8);
                }
                int i9 = this.f286e;
                if (i9 != 0) {
                    alertController.m(alertController.c(i9));
                }
            }
            CharSequence charSequence2 = this.f289h;
            if (charSequence2 != null) {
                alertController.o(charSequence2);
            }
            CharSequence charSequence3 = this.f290i;
            if (charSequence3 != null || this.f291j != null) {
                alertController.k(-1, charSequence3, this.f292k, null, this.f291j);
            }
            CharSequence charSequence4 = this.f293l;
            if (charSequence4 != null || this.f294m != null) {
                alertController.k(-2, charSequence4, this.f295n, null, this.f294m);
            }
            CharSequence charSequence5 = this.f296o;
            if (charSequence5 != null || this.f297p != null) {
                alertController.k(-3, charSequence5, this.f298q, null, this.f297p);
            }
            if (this.f303v != null || this.K != null || this.f304w != null) {
                b(alertController);
            }
            View view2 = this.f307z;
            if (view2 != null) {
                if (this.E) {
                    alertController.t(view2, this.A, this.B, this.C, this.D);
                    return;
                } else {
                    alertController.s(view2);
                    return;
                }
            }
            int i10 = this.f306y;
            if (i10 != 0) {
                alertController.r(i10);
            }
        }

        public final void b(AlertController alertController) {
            ListAdapter hVar;
            RecycleListView recycleListView = (RecycleListView) this.f283b.inflate(alertController.L, (ViewGroup) null);
            if (this.G) {
                Cursor cursor = this.K;
                hVar = cursor == null ? new a(this.f282a, alertController.M, R.id.text1, this.f303v, recycleListView) : new b(this.f282a, cursor, false, recycleListView, alertController);
            } else {
                int i8 = this.H ? alertController.N : alertController.O;
                Cursor cursor2 = this.K;
                if (cursor2 != null) {
                    hVar = new SimpleCursorAdapter(this.f282a, i8, cursor2, new String[]{this.L}, new int[]{R.id.text1});
                } else {
                    hVar = this.f304w;
                    if (hVar == null) {
                        hVar = new h(this.f282a, i8, R.id.text1, this.f303v);
                    }
                }
            }
            e eVar = this.O;
            if (eVar != null) {
                eVar.a(recycleListView);
            }
            alertController.H = hVar;
            alertController.I = this.I;
            if (this.f305x != null) {
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
            alertController.f247g = recycleListView;
        }
    }

    public static final class g extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<DialogInterface> f320a;

        public g(DialogInterface dialogInterface) {
            this.f320a = new WeakReference<>(dialogInterface);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i8 = message.what;
            if (i8 == -3 || i8 == -2 || i8 == -1) {
                ((DialogInterface.OnClickListener) message.obj).onClick(this.f320a.get(), message.what);
            } else {
                if (i8 != 1) {
                    return;
                }
                ((DialogInterface) message.obj).dismiss();
            }
        }
    }

    public static class h extends ArrayAdapter<CharSequence> {
        public h(Context context, int i8, int i9, CharSequence[] charSequenceArr) {
            super(context, i8, i9, charSequenceArr);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public long getItemId(int i8) {
            return i8;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public boolean hasStableIds() {
            return true;
        }
    }

    public AlertController(Context context, l.f fVar, Window window) {
        this.f241a = context;
        this.f242b = fVar;
        this.f243c = window;
        this.R = new g(fVar);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, R$styleable.AlertDialog, R$attr.alertDialogStyle, 0);
        this.J = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AlertDialog_android_layout, 0);
        this.K = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AlertDialog_buttonPanelSideLayout, 0);
        this.L = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AlertDialog_listLayout, 0);
        this.M = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AlertDialog_multiChoiceItemLayout, 0);
        this.N = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AlertDialog_singleChoiceItemLayout, 0);
        this.O = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AlertDialog_listItemLayout, 0);
        this.P = typedArrayObtainStyledAttributes.getBoolean(R$styleable.AlertDialog_showTitle, true);
        this.f244d = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.AlertDialog_buttonIconDimen, 0);
        typedArrayObtainStyledAttributes.recycle();
        fVar.d(1);
    }

    public static boolean a(View view) {
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

    public static void f(View view, View view2, View view3) {
        if (view2 != null) {
            view2.setVisibility(view.canScrollVertically(-1) ? 0 : 4);
        }
        if (view3 != null) {
            view3.setVisibility(view.canScrollVertically(1) ? 0 : 4);
        }
    }

    public static boolean z(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R$attr.alertDialogCenterButtons, typedValue, true);
        return typedValue.data != 0;
    }

    public final void b(Button button) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button.getLayoutParams();
        layoutParams.gravity = 1;
        layoutParams.weight = 0.5f;
        button.setLayoutParams(layoutParams);
    }

    public int c(int i8) {
        TypedValue typedValue = new TypedValue();
        this.f241a.getTheme().resolveAttribute(i8, typedValue, true);
        return typedValue.resourceId;
    }

    public ListView d() {
        return this.f247g;
    }

    public void e() {
        this.f242b.setContentView(j());
        y();
    }

    public boolean g(int i8, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.A;
        return nestedScrollView != null && nestedScrollView.q(keyEvent);
    }

    public boolean h(int i8, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.A;
        return nestedScrollView != null && nestedScrollView.q(keyEvent);
    }

    public final ViewGroup i(View view, View view2) {
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

    public final int j() {
        int i8 = this.K;
        return i8 == 0 ? this.J : this.Q == 1 ? i8 : this.J;
    }

    public void k(int i8, CharSequence charSequence, DialogInterface.OnClickListener onClickListener, Message message, Drawable drawable) {
        if (message == null && onClickListener != null) {
            message = this.R.obtainMessage(i8, onClickListener);
        }
        if (i8 == -3) {
            this.f264x = charSequence;
            this.f265y = message;
            this.f266z = drawable;
        } else if (i8 == -2) {
            this.f260t = charSequence;
            this.f261u = message;
            this.f262v = drawable;
        } else {
            if (i8 != -1) {
                throw new IllegalArgumentException("Button does not exist");
            }
            this.f256p = charSequence;
            this.f257q = message;
            this.f258r = drawable;
        }
    }

    public void l(View view) {
        this.G = view;
    }

    public void m(int i8) {
        this.C = null;
        this.B = i8;
        ImageView imageView = this.D;
        if (imageView != null) {
            if (i8 == 0) {
                imageView.setVisibility(8);
            } else {
                imageView.setVisibility(0);
                this.D.setImageResource(this.B);
            }
        }
    }

    public void n(Drawable drawable) {
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

    public void o(CharSequence charSequence) {
        this.f246f = charSequence;
        TextView textView = this.F;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public final void p(ViewGroup viewGroup, View view, int i8, int i9) {
        View viewFindViewById = this.f243c.findViewById(R$id.scrollIndicatorUp);
        View viewFindViewById2 = this.f243c.findViewById(R$id.scrollIndicatorDown);
        if (Build.VERSION.SDK_INT >= 23) {
            t.B0(view, i8, i9);
            if (viewFindViewById != null) {
                viewGroup.removeView(viewFindViewById);
            }
            if (viewFindViewById2 != null) {
                viewGroup.removeView(viewFindViewById2);
                return;
            }
            return;
        }
        if (viewFindViewById != null && (i8 & 1) == 0) {
            viewGroup.removeView(viewFindViewById);
            viewFindViewById = null;
        }
        if (viewFindViewById2 != null && (i8 & 2) == 0) {
            viewGroup.removeView(viewFindViewById2);
            viewFindViewById2 = null;
        }
        if (viewFindViewById == null && viewFindViewById2 == null) {
            return;
        }
        if (this.f246f != null) {
            this.A.setOnScrollChangeListener(new b(viewFindViewById, viewFindViewById2));
            this.A.post(new c(viewFindViewById, viewFindViewById2));
            return;
        }
        ListView listView = this.f247g;
        if (listView != null) {
            listView.setOnScrollListener(new d(viewFindViewById, viewFindViewById2));
            this.f247g.post(new e(viewFindViewById, viewFindViewById2));
            return;
        }
        if (viewFindViewById != null) {
            viewGroup.removeView(viewFindViewById);
        }
        if (viewFindViewById2 != null) {
            viewGroup.removeView(viewFindViewById2);
        }
    }

    public void q(CharSequence charSequence) {
        this.f245e = charSequence;
        TextView textView = this.E;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public void r(int i8) {
        this.f248h = null;
        this.f249i = i8;
        this.f254n = false;
    }

    public void s(View view) {
        this.f248h = view;
        this.f249i = 0;
        this.f254n = false;
    }

    public void t(View view, int i8, int i9, int i10, int i11) {
        this.f248h = view;
        this.f249i = 0;
        this.f254n = true;
        this.f250j = i8;
        this.f251k = i9;
        this.f252l = i10;
        this.f253m = i11;
    }

    public final void u(ViewGroup viewGroup) {
        int i8;
        Button button = (Button) viewGroup.findViewById(R.id.button1);
        this.f255o = button;
        button.setOnClickListener(this.S);
        if (TextUtils.isEmpty(this.f256p) && this.f258r == null) {
            this.f255o.setVisibility(8);
            i8 = 0;
        } else {
            this.f255o.setText(this.f256p);
            Drawable drawable = this.f258r;
            if (drawable != null) {
                int i9 = this.f244d;
                drawable.setBounds(0, 0, i9, i9);
                this.f255o.setCompoundDrawables(this.f258r, null, null, null);
            }
            this.f255o.setVisibility(0);
            i8 = 1;
        }
        Button button2 = (Button) viewGroup.findViewById(R.id.button2);
        this.f259s = button2;
        button2.setOnClickListener(this.S);
        if (TextUtils.isEmpty(this.f260t) && this.f262v == null) {
            this.f259s.setVisibility(8);
        } else {
            this.f259s.setText(this.f260t);
            Drawable drawable2 = this.f262v;
            if (drawable2 != null) {
                int i10 = this.f244d;
                drawable2.setBounds(0, 0, i10, i10);
                this.f259s.setCompoundDrawables(this.f262v, null, null, null);
            }
            this.f259s.setVisibility(0);
            i8 |= 2;
        }
        Button button3 = (Button) viewGroup.findViewById(R.id.button3);
        this.f263w = button3;
        button3.setOnClickListener(this.S);
        if (TextUtils.isEmpty(this.f264x) && this.f266z == null) {
            this.f263w.setVisibility(8);
        } else {
            this.f263w.setText(this.f264x);
            Drawable drawable3 = this.f266z;
            if (drawable3 != null) {
                int i11 = this.f244d;
                drawable3.setBounds(0, 0, i11, i11);
                this.f263w.setCompoundDrawables(this.f266z, null, null, null);
            }
            this.f263w.setVisibility(0);
            i8 |= 4;
        }
        if (z(this.f241a)) {
            if (i8 == 1) {
                b(this.f255o);
            } else if (i8 == 2) {
                b(this.f259s);
            } else if (i8 == 4) {
                b(this.f263w);
            }
        }
        if (i8 != 0) {
            return;
        }
        viewGroup.setVisibility(8);
    }

    public final void v(ViewGroup viewGroup) {
        NestedScrollView nestedScrollView = (NestedScrollView) this.f243c.findViewById(R$id.scrollView);
        this.A = nestedScrollView;
        nestedScrollView.setFocusable(false);
        this.A.setNestedScrollingEnabled(false);
        TextView textView = (TextView) viewGroup.findViewById(R.id.message);
        this.F = textView;
        if (textView == null) {
            return;
        }
        CharSequence charSequence = this.f246f;
        if (charSequence != null) {
            textView.setText(charSequence);
            return;
        }
        textView.setVisibility(8);
        this.A.removeView(this.F);
        if (this.f247g == null) {
            viewGroup.setVisibility(8);
            return;
        }
        ViewGroup viewGroup2 = (ViewGroup) this.A.getParent();
        int iIndexOfChild = viewGroup2.indexOfChild(this.A);
        viewGroup2.removeViewAt(iIndexOfChild);
        viewGroup2.addView(this.f247g, iIndexOfChild, new ViewGroup.LayoutParams(-1, -1));
    }

    public final void w(ViewGroup viewGroup) {
        View viewInflate = this.f248h;
        if (viewInflate == null) {
            viewInflate = this.f249i != 0 ? LayoutInflater.from(this.f241a).inflate(this.f249i, viewGroup, false) : null;
        }
        boolean z7 = viewInflate != null;
        if (!z7 || !a(viewInflate)) {
            this.f243c.setFlags(CarPlayConfig.IapFeature.IAP_FEATURE_APP_LAUNCH, CarPlayConfig.IapFeature.IAP_FEATURE_APP_LAUNCH);
        }
        if (!z7) {
            viewGroup.setVisibility(8);
            return;
        }
        FrameLayout frameLayout = (FrameLayout) this.f243c.findViewById(R$id.custom);
        frameLayout.addView(viewInflate, new ViewGroup.LayoutParams(-1, -1));
        if (this.f254n) {
            frameLayout.setPadding(this.f250j, this.f251k, this.f252l, this.f253m);
        }
        if (this.f247g != null) {
            ((LinearLayoutCompat.a) viewGroup.getLayoutParams()).f627a = 0.0f;
        }
    }

    public final void x(ViewGroup viewGroup) {
        if (this.G != null) {
            viewGroup.addView(this.G, 0, new ViewGroup.LayoutParams(-1, -2));
            this.f243c.findViewById(R$id.title_template).setVisibility(8);
            return;
        }
        this.D = (ImageView) this.f243c.findViewById(R.id.icon);
        if (!(!TextUtils.isEmpty(this.f245e)) || !this.P) {
            this.f243c.findViewById(R$id.title_template).setVisibility(8);
            this.D.setVisibility(8);
            viewGroup.setVisibility(8);
            return;
        }
        TextView textView = (TextView) this.f243c.findViewById(R$id.alertTitle);
        this.E = textView;
        textView.setText(this.f245e);
        int i8 = this.B;
        if (i8 != 0) {
            this.D.setImageResource(i8);
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
    public final void y() {
        View viewFindViewById;
        ListAdapter listAdapter;
        View viewFindViewById2;
        View viewFindViewById3 = this.f243c.findViewById(R$id.parentPanel);
        int i8 = R$id.topPanel;
        View viewFindViewById4 = viewFindViewById3.findViewById(i8);
        int i9 = R$id.contentPanel;
        View viewFindViewById5 = viewFindViewById3.findViewById(i9);
        int i10 = R$id.buttonPanel;
        View viewFindViewById6 = viewFindViewById3.findViewById(i10);
        ViewGroup viewGroup = (ViewGroup) viewFindViewById3.findViewById(R$id.customPanel);
        w(viewGroup);
        View viewFindViewById7 = viewGroup.findViewById(i8);
        View viewFindViewById8 = viewGroup.findViewById(i9);
        View viewFindViewById9 = viewGroup.findViewById(i10);
        ViewGroup viewGroupI = i(viewFindViewById7, viewFindViewById4);
        ViewGroup viewGroupI2 = i(viewFindViewById8, viewFindViewById5);
        ViewGroup viewGroupI3 = i(viewFindViewById9, viewFindViewById6);
        v(viewGroupI2);
        u(viewGroupI3);
        x(viewGroupI);
        boolean z7 = viewGroup.getVisibility() != 8;
        boolean z8 = (viewGroupI == null || viewGroupI.getVisibility() == 8) ? 0 : 1;
        boolean z9 = (viewGroupI3 == null || viewGroupI3.getVisibility() == 8) ? false : true;
        if (!z9 && viewGroupI2 != null && (viewFindViewById2 = viewGroupI2.findViewById(R$id.textSpacerNoButtons)) != null) {
            viewFindViewById2.setVisibility(0);
        }
        if (z8 != 0) {
            NestedScrollView nestedScrollView = this.A;
            if (nestedScrollView != null) {
                nestedScrollView.setClipToPadding(true);
            }
            View viewFindViewById10 = (this.f246f == null && this.f247g == null) ? null : viewGroupI.findViewById(R$id.titleDividerNoCustom);
            if (viewFindViewById10 != null) {
                viewFindViewById10.setVisibility(0);
            }
        } else if (viewGroupI2 != null && (viewFindViewById = viewGroupI2.findViewById(R$id.textSpacerNoTitle)) != null) {
            viewFindViewById.setVisibility(0);
        }
        ListView listView = this.f247g;
        if (listView instanceof RecycleListView) {
            ((RecycleListView) listView).a(z8, z9);
        }
        if (!z7) {
            View view = this.f247g;
            if (view == null) {
                view = this.A;
            }
            if (view != null) {
                p(viewGroupI2, view, z8 | (z9 ? 2 : 0), 3);
            }
        }
        ListView listView2 = this.f247g;
        if (listView2 == null || (listAdapter = this.H) == null) {
            return;
        }
        listView2.setAdapter(listAdapter);
        int i11 = this.I;
        if (i11 > -1) {
            listView2.setItemChecked(i11, true);
            listView2.setSelection(i11);
        }
    }
}
