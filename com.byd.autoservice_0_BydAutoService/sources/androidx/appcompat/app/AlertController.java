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
import l0.t;

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
    public final Context f134a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final i.f f135b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Window f136c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f137d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CharSequence f138e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public CharSequence f139f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ListView f140g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public View f141h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f142i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f143j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f144k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f145l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f146m;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Button f148o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public CharSequence f149p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Message f150q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Drawable f151r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Button f152s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public CharSequence f153t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Message f154u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Drawable f155v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public Button f156w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public CharSequence f157x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public Message f158y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public Drawable f159z;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f147n = false;
    public int B = 0;
    public int I = -1;
    public int Q = 0;
    public final View.OnClickListener S = new a();

    public static class RecycleListView extends ListView {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f160b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f161c;

        public RecycleListView(Context context) {
            this(context, null);
        }

        public void a(boolean z3, boolean z4) {
            if (z4 && z3) {
                return;
            }
            setPadding(getPaddingLeft(), z3 ? getPaddingTop() : this.f160b, getPaddingRight(), z4 ? getPaddingBottom() : this.f161c);
        }

        public RecycleListView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RecycleListView);
            this.f161c = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.RecycleListView_paddingBottomNoButtons, -1);
            this.f160b = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.RecycleListView_paddingTopNoTitle, -1);
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
            Message messageObtain = (view != alertController.f148o || (message3 = alertController.f150q) == null) ? (view != alertController.f152s || (message2 = alertController.f154u) == null) ? (view != alertController.f156w || (message = alertController.f158y) == null) ? null : Message.obtain(message) : Message.obtain(message2) : Message.obtain(message3);
            if (messageObtain != null) {
                messageObtain.sendToTarget();
            }
            AlertController alertController2 = AlertController.this;
            alertController2.R.obtainMessage(1, alertController2.f135b).sendToTarget();
        }
    }

    public class b implements NestedScrollView.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f163a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f164b;

        public b(View view, View view2) {
            this.f163a = view;
            this.f164b = view2;
        }

        @Override // androidx.core.widget.NestedScrollView.b
        public void a(NestedScrollView nestedScrollView, int i3, int i4, int i5, int i6) {
            AlertController.f(nestedScrollView, this.f163a, this.f164b);
        }
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f166b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ View f167c;

        public c(View view, View view2) {
            this.f166b = view;
            this.f167c = view2;
        }

        @Override // java.lang.Runnable
        public void run() {
            AlertController.f(AlertController.this.A, this.f166b, this.f167c);
        }
    }

    public class d implements AbsListView.OnScrollListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f169a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f170b;

        public d(View view, View view2) {
            this.f169a = view;
            this.f170b = view2;
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i3, int i4, int i5) {
            AlertController.f(absListView, this.f169a, this.f170b);
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i3) {
        }
    }

    public class e implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f172b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ View f173c;

        public e(View view, View view2) {
            this.f172b = view;
            this.f173c = view2;
        }

        @Override // java.lang.Runnable
        public void run() {
            AlertController.f(AlertController.this.f140g, this.f172b, this.f173c);
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
        public final Context f175a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final LayoutInflater f176b;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Drawable f178d;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public CharSequence f180f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public View f181g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public CharSequence f182h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public CharSequence f183i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public Drawable f184j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public DialogInterface.OnClickListener f185k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public CharSequence f186l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public Drawable f187m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public DialogInterface.OnClickListener f188n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public CharSequence f189o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public Drawable f190p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public DialogInterface.OnClickListener f191q;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public DialogInterface.OnCancelListener f193s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public DialogInterface.OnDismissListener f194t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public DialogInterface.OnKeyListener f195u;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public CharSequence[] f196v;

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public ListAdapter f197w;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public DialogInterface.OnClickListener f198x;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public int f199y;

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        public View f200z;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f177c = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f179e = 0;
        public boolean E = false;
        public int I = -1;
        public boolean P = true;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public boolean f192r = true;

        public class a extends ArrayAdapter<CharSequence> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ RecycleListView f201b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(Context context, int i3, int i4, CharSequence[] charSequenceArr, RecycleListView recycleListView) {
                super(context, i3, i4, charSequenceArr);
                this.f201b = recycleListView;
            }

            @Override // android.widget.ArrayAdapter, android.widget.Adapter
            public View getView(int i3, View view, ViewGroup viewGroup) {
                View view2 = super.getView(i3, view, viewGroup);
                boolean[] zArr = f.this.F;
                if (zArr != null && zArr[i3]) {
                    this.f201b.setItemChecked(i3, true);
                }
                return view2;
            }
        }

        public class b extends CursorAdapter {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final int f203b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final int f204c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ RecycleListView f205d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ AlertController f206e;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(Context context, Cursor cursor, boolean z3, RecycleListView recycleListView, AlertController alertController) {
                super(context, cursor, z3);
                this.f205d = recycleListView;
                this.f206e = alertController;
                Cursor cursor2 = getCursor();
                this.f203b = cursor2.getColumnIndexOrThrow(f.this.L);
                this.f204c = cursor2.getColumnIndexOrThrow(f.this.M);
            }

            @Override // android.widget.CursorAdapter
            public void bindView(View view, Context context, Cursor cursor) {
                ((CheckedTextView) view.findViewById(R.id.text1)).setText(cursor.getString(this.f203b));
                this.f205d.setItemChecked(cursor.getPosition(), cursor.getInt(this.f204c) == 1);
            }

            @Override // android.widget.CursorAdapter
            public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
                return f.this.f176b.inflate(this.f206e.M, viewGroup, false);
            }
        }

        public class c implements AdapterView.OnItemClickListener {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AlertController f208b;

            public c(AlertController alertController) {
                this.f208b = alertController;
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i3, long j3) {
                f.this.f198x.onClick(this.f208b.f135b, i3);
                if (f.this.H) {
                    return;
                }
                this.f208b.f135b.dismiss();
            }
        }

        public class d implements AdapterView.OnItemClickListener {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ RecycleListView f210b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ AlertController f211c;

            public d(RecycleListView recycleListView, AlertController alertController) {
                this.f210b = recycleListView;
                this.f211c = alertController;
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i3, long j3) {
                boolean[] zArr = f.this.F;
                if (zArr != null) {
                    zArr[i3] = this.f210b.isItemChecked(i3);
                }
                f.this.J.onClick(this.f211c.f135b, i3, this.f210b.isItemChecked(i3));
            }
        }

        public interface e {
            void a(ListView listView);
        }

        public f(Context context) {
            this.f175a = context;
            this.f176b = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        public void a(AlertController alertController) {
            View view = this.f181g;
            if (view != null) {
                alertController.l(view);
            } else {
                CharSequence charSequence = this.f180f;
                if (charSequence != null) {
                    alertController.q(charSequence);
                }
                Drawable drawable = this.f178d;
                if (drawable != null) {
                    alertController.n(drawable);
                }
                int i3 = this.f177c;
                if (i3 != 0) {
                    alertController.m(i3);
                }
                int i4 = this.f179e;
                if (i4 != 0) {
                    alertController.m(alertController.c(i4));
                }
            }
            CharSequence charSequence2 = this.f182h;
            if (charSequence2 != null) {
                alertController.o(charSequence2);
            }
            CharSequence charSequence3 = this.f183i;
            if (charSequence3 != null || this.f184j != null) {
                alertController.k(-1, charSequence3, this.f185k, null, this.f184j);
            }
            CharSequence charSequence4 = this.f186l;
            if (charSequence4 != null || this.f187m != null) {
                alertController.k(-2, charSequence4, this.f188n, null, this.f187m);
            }
            CharSequence charSequence5 = this.f189o;
            if (charSequence5 != null || this.f190p != null) {
                alertController.k(-3, charSequence5, this.f191q, null, this.f190p);
            }
            if (this.f196v != null || this.K != null || this.f197w != null) {
                b(alertController);
            }
            View view2 = this.f200z;
            if (view2 != null) {
                if (this.E) {
                    alertController.t(view2, this.A, this.B, this.C, this.D);
                    return;
                } else {
                    alertController.s(view2);
                    return;
                }
            }
            int i5 = this.f199y;
            if (i5 != 0) {
                alertController.r(i5);
            }
        }

        public final void b(AlertController alertController) {
            ListAdapter hVar;
            RecycleListView recycleListView = (RecycleListView) this.f176b.inflate(alertController.L, (ViewGroup) null);
            if (this.G) {
                Cursor cursor = this.K;
                hVar = cursor == null ? new a(this.f175a, alertController.M, R.id.text1, this.f196v, recycleListView) : new b(this.f175a, cursor, false, recycleListView, alertController);
            } else {
                int i3 = this.H ? alertController.N : alertController.O;
                Cursor cursor2 = this.K;
                if (cursor2 != null) {
                    hVar = new SimpleCursorAdapter(this.f175a, i3, cursor2, new String[]{this.L}, new int[]{R.id.text1});
                } else {
                    hVar = this.f197w;
                    if (hVar == null) {
                        hVar = new h(this.f175a, i3, R.id.text1, this.f196v);
                    }
                }
            }
            e eVar = this.O;
            if (eVar != null) {
                eVar.a(recycleListView);
            }
            alertController.H = hVar;
            alertController.I = this.I;
            if (this.f198x != null) {
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
            alertController.f140g = recycleListView;
        }
    }

    public static final class g extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<DialogInterface> f213a;

        public g(DialogInterface dialogInterface) {
            this.f213a = new WeakReference<>(dialogInterface);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i3 = message.what;
            if (i3 == -3 || i3 == -2 || i3 == -1) {
                ((DialogInterface.OnClickListener) message.obj).onClick(this.f213a.get(), message.what);
            } else {
                if (i3 != 1) {
                    return;
                }
                ((DialogInterface) message.obj).dismiss();
            }
        }
    }

    public static class h extends ArrayAdapter<CharSequence> {
        public h(Context context, int i3, int i4, CharSequence[] charSequenceArr) {
            super(context, i3, i4, charSequenceArr);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public long getItemId(int i3) {
            return i3;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public boolean hasStableIds() {
            return true;
        }
    }

    public AlertController(Context context, i.f fVar, Window window) {
        this.f134a = context;
        this.f135b = fVar;
        this.f136c = window;
        this.R = new g(fVar);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, R$styleable.AlertDialog, R$attr.alertDialogStyle, 0);
        this.J = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AlertDialog_android_layout, 0);
        this.K = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AlertDialog_buttonPanelSideLayout, 0);
        this.L = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AlertDialog_listLayout, 0);
        this.M = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AlertDialog_multiChoiceItemLayout, 0);
        this.N = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AlertDialog_singleChoiceItemLayout, 0);
        this.O = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AlertDialog_listItemLayout, 0);
        this.P = typedArrayObtainStyledAttributes.getBoolean(R$styleable.AlertDialog_showTitle, true);
        this.f137d = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.AlertDialog_buttonIconDimen, 0);
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

    public int c(int i3) {
        TypedValue typedValue = new TypedValue();
        this.f134a.getTheme().resolveAttribute(i3, typedValue, true);
        return typedValue.resourceId;
    }

    public ListView d() {
        return this.f140g;
    }

    public void e() {
        this.f135b.setContentView(j());
        y();
    }

    public boolean g(int i3, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.A;
        return nestedScrollView != null && nestedScrollView.q(keyEvent);
    }

    public boolean h(int i3, KeyEvent keyEvent) {
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
        int i3 = this.K;
        return i3 == 0 ? this.J : this.Q == 1 ? i3 : this.J;
    }

    public void k(int i3, CharSequence charSequence, DialogInterface.OnClickListener onClickListener, Message message, Drawable drawable) {
        if (message == null && onClickListener != null) {
            message = this.R.obtainMessage(i3, onClickListener);
        }
        if (i3 == -3) {
            this.f157x = charSequence;
            this.f158y = message;
            this.f159z = drawable;
        } else if (i3 == -2) {
            this.f153t = charSequence;
            this.f154u = message;
            this.f155v = drawable;
        } else {
            if (i3 != -1) {
                throw new IllegalArgumentException("Button does not exist");
            }
            this.f149p = charSequence;
            this.f150q = message;
            this.f151r = drawable;
        }
    }

    public void l(View view) {
        this.G = view;
    }

    public void m(int i3) {
        this.C = null;
        this.B = i3;
        ImageView imageView = this.D;
        if (imageView != null) {
            if (i3 == 0) {
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
        this.f139f = charSequence;
        TextView textView = this.F;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public final void p(ViewGroup viewGroup, View view, int i3, int i4) {
        View viewFindViewById = this.f136c.findViewById(R$id.scrollIndicatorUp);
        View viewFindViewById2 = this.f136c.findViewById(R$id.scrollIndicatorDown);
        if (Build.VERSION.SDK_INT >= 23) {
            t.y0(view, i3, i4);
            if (viewFindViewById != null) {
                viewGroup.removeView(viewFindViewById);
            }
            if (viewFindViewById2 != null) {
                viewGroup.removeView(viewFindViewById2);
                return;
            }
            return;
        }
        if (viewFindViewById != null && (i3 & 1) == 0) {
            viewGroup.removeView(viewFindViewById);
            viewFindViewById = null;
        }
        if (viewFindViewById2 != null && (i3 & 2) == 0) {
            viewGroup.removeView(viewFindViewById2);
            viewFindViewById2 = null;
        }
        if (viewFindViewById == null && viewFindViewById2 == null) {
            return;
        }
        if (this.f139f != null) {
            this.A.setOnScrollChangeListener(new b(viewFindViewById, viewFindViewById2));
            this.A.post(new c(viewFindViewById, viewFindViewById2));
            return;
        }
        ListView listView = this.f140g;
        if (listView != null) {
            listView.setOnScrollListener(new d(viewFindViewById, viewFindViewById2));
            this.f140g.post(new e(viewFindViewById, viewFindViewById2));
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
        this.f138e = charSequence;
        TextView textView = this.E;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public void r(int i3) {
        this.f141h = null;
        this.f142i = i3;
        this.f147n = false;
    }

    public void s(View view) {
        this.f141h = view;
        this.f142i = 0;
        this.f147n = false;
    }

    public void t(View view, int i3, int i4, int i5, int i6) {
        this.f141h = view;
        this.f142i = 0;
        this.f147n = true;
        this.f143j = i3;
        this.f144k = i4;
        this.f145l = i5;
        this.f146m = i6;
    }

    public final void u(ViewGroup viewGroup) {
        int i3;
        Button button = (Button) viewGroup.findViewById(R.id.button1);
        this.f148o = button;
        button.setOnClickListener(this.S);
        if (TextUtils.isEmpty(this.f149p) && this.f151r == null) {
            this.f148o.setVisibility(8);
            i3 = 0;
        } else {
            this.f148o.setText(this.f149p);
            Drawable drawable = this.f151r;
            if (drawable != null) {
                int i4 = this.f137d;
                drawable.setBounds(0, 0, i4, i4);
                this.f148o.setCompoundDrawables(this.f151r, null, null, null);
            }
            this.f148o.setVisibility(0);
            i3 = 1;
        }
        Button button2 = (Button) viewGroup.findViewById(R.id.button2);
        this.f152s = button2;
        button2.setOnClickListener(this.S);
        if (TextUtils.isEmpty(this.f153t) && this.f155v == null) {
            this.f152s.setVisibility(8);
        } else {
            this.f152s.setText(this.f153t);
            Drawable drawable2 = this.f155v;
            if (drawable2 != null) {
                int i5 = this.f137d;
                drawable2.setBounds(0, 0, i5, i5);
                this.f152s.setCompoundDrawables(this.f155v, null, null, null);
            }
            this.f152s.setVisibility(0);
            i3 |= 2;
        }
        Button button3 = (Button) viewGroup.findViewById(R.id.button3);
        this.f156w = button3;
        button3.setOnClickListener(this.S);
        if (TextUtils.isEmpty(this.f157x) && this.f159z == null) {
            this.f156w.setVisibility(8);
        } else {
            this.f156w.setText(this.f157x);
            Drawable drawable3 = this.f159z;
            if (drawable3 != null) {
                int i6 = this.f137d;
                drawable3.setBounds(0, 0, i6, i6);
                this.f156w.setCompoundDrawables(this.f159z, null, null, null);
            }
            this.f156w.setVisibility(0);
            i3 |= 4;
        }
        if (z(this.f134a)) {
            if (i3 == 1) {
                b(this.f148o);
            } else if (i3 == 2) {
                b(this.f152s);
            } else if (i3 == 4) {
                b(this.f156w);
            }
        }
        if (i3 != 0) {
            return;
        }
        viewGroup.setVisibility(8);
    }

    public final void v(ViewGroup viewGroup) {
        NestedScrollView nestedScrollView = (NestedScrollView) this.f136c.findViewById(R$id.scrollView);
        this.A = nestedScrollView;
        nestedScrollView.setFocusable(false);
        this.A.setNestedScrollingEnabled(false);
        TextView textView = (TextView) viewGroup.findViewById(R.id.message);
        this.F = textView;
        if (textView == null) {
            return;
        }
        CharSequence charSequence = this.f139f;
        if (charSequence != null) {
            textView.setText(charSequence);
            return;
        }
        textView.setVisibility(8);
        this.A.removeView(this.F);
        if (this.f140g == null) {
            viewGroup.setVisibility(8);
            return;
        }
        ViewGroup viewGroup2 = (ViewGroup) this.A.getParent();
        int iIndexOfChild = viewGroup2.indexOfChild(this.A);
        viewGroup2.removeViewAt(iIndexOfChild);
        viewGroup2.addView(this.f140g, iIndexOfChild, new ViewGroup.LayoutParams(-1, -1));
    }

    public final void w(ViewGroup viewGroup) {
        View viewInflate = this.f141h;
        if (viewInflate == null) {
            viewInflate = this.f142i != 0 ? LayoutInflater.from(this.f134a).inflate(this.f142i, viewGroup, false) : null;
        }
        boolean z3 = viewInflate != null;
        if (!z3 || !a(viewInflate)) {
            this.f136c.setFlags(131072, 131072);
        }
        if (!z3) {
            viewGroup.setVisibility(8);
            return;
        }
        FrameLayout frameLayout = (FrameLayout) this.f136c.findViewById(R$id.custom);
        frameLayout.addView(viewInflate, new ViewGroup.LayoutParams(-1, -1));
        if (this.f147n) {
            frameLayout.setPadding(this.f143j, this.f144k, this.f145l, this.f146m);
        }
        if (this.f140g != null) {
            ((LinearLayoutCompat.a) viewGroup.getLayoutParams()).f526a = 0.0f;
        }
    }

    public final void x(ViewGroup viewGroup) {
        if (this.G != null) {
            viewGroup.addView(this.G, 0, new ViewGroup.LayoutParams(-1, -2));
            this.f136c.findViewById(R$id.title_template).setVisibility(8);
            return;
        }
        this.D = (ImageView) this.f136c.findViewById(R.id.icon);
        if (!(!TextUtils.isEmpty(this.f138e)) || !this.P) {
            this.f136c.findViewById(R$id.title_template).setVisibility(8);
            this.D.setVisibility(8);
            viewGroup.setVisibility(8);
            return;
        }
        TextView textView = (TextView) this.f136c.findViewById(R$id.alertTitle);
        this.E = textView;
        textView.setText(this.f138e);
        int i3 = this.B;
        if (i3 != 0) {
            this.D.setImageResource(i3);
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
        View viewFindViewById3 = this.f136c.findViewById(R$id.parentPanel);
        int i3 = R$id.topPanel;
        View viewFindViewById4 = viewFindViewById3.findViewById(i3);
        int i4 = R$id.contentPanel;
        View viewFindViewById5 = viewFindViewById3.findViewById(i4);
        int i5 = R$id.buttonPanel;
        View viewFindViewById6 = viewFindViewById3.findViewById(i5);
        ViewGroup viewGroup = (ViewGroup) viewFindViewById3.findViewById(R$id.customPanel);
        w(viewGroup);
        View viewFindViewById7 = viewGroup.findViewById(i3);
        View viewFindViewById8 = viewGroup.findViewById(i4);
        View viewFindViewById9 = viewGroup.findViewById(i5);
        ViewGroup viewGroupI = i(viewFindViewById7, viewFindViewById4);
        ViewGroup viewGroupI2 = i(viewFindViewById8, viewFindViewById5);
        ViewGroup viewGroupI3 = i(viewFindViewById9, viewFindViewById6);
        v(viewGroupI2);
        u(viewGroupI3);
        x(viewGroupI);
        boolean z3 = viewGroup.getVisibility() != 8;
        boolean z4 = (viewGroupI == null || viewGroupI.getVisibility() == 8) ? 0 : 1;
        boolean z5 = (viewGroupI3 == null || viewGroupI3.getVisibility() == 8) ? false : true;
        if (!z5 && viewGroupI2 != null && (viewFindViewById2 = viewGroupI2.findViewById(R$id.textSpacerNoButtons)) != null) {
            viewFindViewById2.setVisibility(0);
        }
        if (z4 != 0) {
            NestedScrollView nestedScrollView = this.A;
            if (nestedScrollView != null) {
                nestedScrollView.setClipToPadding(true);
            }
            View viewFindViewById10 = (this.f139f == null && this.f140g == null) ? null : viewGroupI.findViewById(R$id.titleDividerNoCustom);
            if (viewFindViewById10 != null) {
                viewFindViewById10.setVisibility(0);
            }
        } else if (viewGroupI2 != null && (viewFindViewById = viewGroupI2.findViewById(R$id.textSpacerNoTitle)) != null) {
            viewFindViewById.setVisibility(0);
        }
        ListView listView = this.f140g;
        if (listView instanceof RecycleListView) {
            ((RecycleListView) listView).a(z4, z5);
        }
        if (!z3) {
            View view = this.f140g;
            if (view == null) {
                view = this.A;
            }
            if (view != null) {
                p(viewGroupI2, view, z4 | (z5 ? 2 : 0), 3);
            }
        }
        ListView listView2 = this.f140g;
        if (listView2 == null || (listAdapter = this.H) == null) {
            return;
        }
        listView2.setAdapter(listAdapter);
        int i6 = this.I;
        if (i6 > -1) {
            listView2.setItemChecked(i6, true);
            listView2.setSelection(i6);
        }
    }
}
