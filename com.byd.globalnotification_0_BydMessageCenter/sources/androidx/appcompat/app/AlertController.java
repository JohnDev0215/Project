package androidx.appcompat.app;

import a.b.j;
import a.b.k.r;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import java.lang.ref.WeakReference;

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
    public final Context f1386a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final r f1387b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Window f1388c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1389d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CharSequence f1390e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public CharSequence f1391f;
    public ListView g;
    public View h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public Button o;
    public CharSequence p;
    public Message q;
    public Drawable r;
    public Button s;
    public CharSequence t;
    public Message u;
    public Drawable v;
    public Button w;
    public CharSequence x;
    public Message y;
    public Drawable z;
    public boolean n = false;
    public int B = 0;
    public int I = -1;
    public int Q = 0;
    public final View.OnClickListener S = new a();

    public static class RecycleListView extends ListView {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f1392a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f1393b;

        public RecycleListView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.RecycleListView);
            this.f1393b = typedArrayObtainStyledAttributes.getDimensionPixelOffset(j.RecycleListView_paddingBottomNoButtons, -1);
            this.f1392a = typedArrayObtainStyledAttributes.getDimensionPixelOffset(j.RecycleListView_paddingTopNoTitle, -1);
        }
    }

    public class a implements View.OnClickListener {
        public a() {
        }

        /* JADX WARN: Removed duplicated region for block: B:6:0x000a A[PHI: r0
  0x000a: PHI (r0v5 android.os.Message) = (r0v3 android.os.Message), (r0v6 android.os.Message) binds: [B:10:0x0017, B:5:0x0008] A[DONT_GENERATE, DONT_INLINE]] */
        @Override // android.view.View.OnClickListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onClick(android.view.View r3) {
            /*
                r2 = this;
                androidx.appcompat.app.AlertController r0 = androidx.appcompat.app.AlertController.this
                android.widget.Button r1 = r0.o
                if (r3 != r1) goto Lf
                android.os.Message r0 = r0.q
                if (r0 == 0) goto Lf
            La:
                android.os.Message r3 = android.os.Message.obtain(r0)
                goto L2a
            Lf:
                androidx.appcompat.app.AlertController r0 = androidx.appcompat.app.AlertController.this
                android.widget.Button r1 = r0.s
                if (r3 != r1) goto L1a
                android.os.Message r0 = r0.u
                if (r0 == 0) goto L1a
                goto La
            L1a:
                androidx.appcompat.app.AlertController r0 = androidx.appcompat.app.AlertController.this
                android.widget.Button r1 = r0.w
                if (r3 != r1) goto L29
                android.os.Message r3 = r0.y
                if (r3 == 0) goto L29
                android.os.Message r3 = android.os.Message.obtain(r3)
                goto L2a
            L29:
                r3 = 0
            L2a:
                if (r3 == 0) goto L2f
                r3.sendToTarget()
            L2f:
                androidx.appcompat.app.AlertController r2 = androidx.appcompat.app.AlertController.this
                android.os.Handler r3 = r2.R
                r0 = 1
                a.b.k.r r2 = r2.f1387b
                android.os.Message r2 = r3.obtainMessage(r0, r2)
                r2.sendToTarget()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AlertController.a.onClick(android.view.View):void");
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Context f1395a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final LayoutInflater f1396b;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Drawable f1398d;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public CharSequence f1400f;
        public View g;
        public DialogInterface.OnCancelListener i;
        public DialogInterface.OnDismissListener j;
        public DialogInterface.OnKeyListener k;
        public ListAdapter l;
        public DialogInterface.OnClickListener m;
        public boolean o;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1397c = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1399e = 0;
        public boolean n = false;
        public int p = -1;
        public boolean h = true;

        public b(Context context) {
            this.f1395a = context;
            this.f1396b = (LayoutInflater) context.getSystemService("layout_inflater");
        }
    }

    public static final class c extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<DialogInterface> f1401a;

        public c(DialogInterface dialogInterface) {
            this.f1401a = new WeakReference<>(dialogInterface);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == -3 || i == -2 || i == -1) {
                ((DialogInterface.OnClickListener) message.obj).onClick(this.f1401a.get(), message.what);
            } else {
                if (i != 1) {
                    return;
                }
                ((DialogInterface) message.obj).dismiss();
            }
        }
    }

    public static class d extends ArrayAdapter<CharSequence> {
        public d(Context context, int i, int i2, CharSequence[] charSequenceArr) {
            super(context, i, i2, (Object[]) null);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public boolean hasStableIds() {
            return true;
        }
    }

    public AlertController(Context context, r rVar, Window window) {
        this.f1386a = context;
        this.f1387b = rVar;
        this.f1388c = window;
        this.R = new c(rVar);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, j.AlertDialog, a.b.a.alertDialogStyle, 0);
        this.J = typedArrayObtainStyledAttributes.getResourceId(j.AlertDialog_android_layout, 0);
        this.K = typedArrayObtainStyledAttributes.getResourceId(j.AlertDialog_buttonPanelSideLayout, 0);
        this.L = typedArrayObtainStyledAttributes.getResourceId(j.AlertDialog_listLayout, 0);
        this.M = typedArrayObtainStyledAttributes.getResourceId(j.AlertDialog_multiChoiceItemLayout, 0);
        this.N = typedArrayObtainStyledAttributes.getResourceId(j.AlertDialog_singleChoiceItemLayout, 0);
        this.O = typedArrayObtainStyledAttributes.getResourceId(j.AlertDialog_listItemLayout, 0);
        this.P = typedArrayObtainStyledAttributes.getBoolean(j.AlertDialog_showTitle, true);
        this.f1389d = typedArrayObtainStyledAttributes.getDimensionPixelSize(j.AlertDialog_buttonIconDimen, 0);
        typedArrayObtainStyledAttributes.recycle();
        rVar.a().l(1);
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

    public static void c(View view, View view2, View view3) {
        if (view2 != null) {
            view2.setVisibility(view.canScrollVertically(-1) ? 0 : 4);
        }
        if (view3 != null) {
            view3.setVisibility(view.canScrollVertically(1) ? 0 : 4);
        }
    }

    public final void b(Button button) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button.getLayoutParams();
        layoutParams.gravity = 1;
        layoutParams.weight = 0.5f;
        button.setLayoutParams(layoutParams);
    }

    public final ViewGroup d(View view, View view2) {
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

    public void e(int i) {
        this.C = null;
        this.B = i;
        ImageView imageView = this.D;
        if (imageView != null) {
            if (i == 0) {
                imageView.setVisibility(8);
            } else {
                imageView.setVisibility(0);
                this.D.setImageResource(this.B);
            }
        }
    }
}
