package androidx.appcompat.app;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.R;
import androidx.appcompat.app.AlertController;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.widget.NestedScrollView;
import defpackage.in3;
import defpackage.om3;
import defpackage.qj1;
import defpackage.s81;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class AlertDialog extends AppCompatDialog implements DialogInterface {
    public final AlertController j;

    public static class Builder {
        public final AlertController.AlertParams a;
        public final int b;

        public Builder(Context context) {
            this(context, AlertDialog.g(context, 0));
        }

        public AlertDialog a() {
            ListAdapter checkedItemAdapter;
            final AlertController.AlertParams alertParams = this.a;
            AlertDialog alertDialog = new AlertDialog(alertParams.a, this.b);
            View view = alertParams.e;
            final AlertController alertController = alertDialog.j;
            if (view != null) {
                alertController.C = view;
            } else {
                CharSequence charSequence = alertParams.d;
                if (charSequence != null) {
                    alertController.e = charSequence;
                    TextView textView = alertController.A;
                    if (textView != null) {
                        textView.setText(charSequence);
                    }
                }
                Drawable drawable = alertParams.c;
                if (drawable != null) {
                    alertController.y = drawable;
                    alertController.x = 0;
                    ImageView imageView = alertController.z;
                    if (imageView != null) {
                        imageView.setVisibility(0);
                        alertController.z.setImageDrawable(drawable);
                    }
                }
            }
            CharSequence charSequence2 = alertParams.f;
            if (charSequence2 != null) {
                alertController.f = charSequence2;
                TextView textView2 = alertController.B;
                if (textView2 != null) {
                    textView2.setText(charSequence2);
                }
            }
            CharSequence charSequence3 = alertParams.g;
            if (charSequence3 != null) {
                alertController.e(-1, charSequence3, alertParams.h);
            }
            CharSequence charSequence4 = alertParams.i;
            if (charSequence4 != null) {
                alertController.e(-2, charSequence4, alertParams.j);
            }
            if (alertParams.o != null || alertParams.p != null) {
                final AlertController.RecycleListView recycleListView = (AlertController.RecycleListView) alertParams.b.inflate(alertController.G, (ViewGroup) null);
                if (alertParams.t) {
                    checkedItemAdapter = new ArrayAdapter<CharSequence>(alertParams.a, alertController.H, alertParams.o) { // from class: androidx.appcompat.app.AlertController.AlertParams.1
                        @Override // android.widget.ArrayAdapter, android.widget.Adapter
                        public final View getView(int i, View view2, ViewGroup viewGroup) {
                            View view3 = super.getView(i, view2, viewGroup);
                            boolean[] zArr = AlertParams.this.s;
                            if (zArr != null && zArr[i]) {
                                recycleListView.setItemChecked(i, true);
                            }
                            return view3;
                        }
                    };
                } else {
                    int i = alertParams.u ? alertController.I : alertController.f0J;
                    checkedItemAdapter = alertParams.p;
                    if (checkedItemAdapter == null) {
                        checkedItemAdapter = new AlertController.CheckedItemAdapter(alertParams.a, i, alertParams.o);
                    }
                }
                alertController.D = checkedItemAdapter;
                alertController.E = alertParams.v;
                if (alertParams.q != null) {
                    recycleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: androidx.appcompat.app.AlertController.AlertParams.3
                        @Override // android.widget.AdapterView.OnItemClickListener
                        public final void onItemClick(AdapterView<?> adapterView, View view2, int i2, long j) {
                            AlertParams alertParams2 = AlertParams.this;
                            DialogInterface.OnClickListener onClickListener = alertParams2.q;
                            AlertController alertController2 = alertController;
                            onClickListener.onClick(alertController2.b, i2);
                            if (alertParams2.u) {
                                return;
                            }
                            alertController2.b.dismiss();
                        }
                    });
                } else if (alertParams.w != null) {
                    recycleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: androidx.appcompat.app.AlertController.AlertParams.4
                        @Override // android.widget.AdapterView.OnItemClickListener
                        public final void onItemClick(AdapterView<?> adapterView, View view2, int i2, long j) {
                            AlertParams alertParams2 = AlertParams.this;
                            boolean[] zArr = alertParams2.s;
                            RecycleListView recycleListView2 = recycleListView;
                            if (zArr != null) {
                                zArr[i2] = recycleListView2.isItemChecked(i2);
                            }
                            alertParams2.w.onClick(alertController.b, i2, recycleListView2.isItemChecked(i2));
                        }
                    });
                }
                if (alertParams.u) {
                    recycleListView.setChoiceMode(1);
                } else if (alertParams.t) {
                    recycleListView.setChoiceMode(2);
                }
                alertController.g = recycleListView;
            }
            View view2 = alertParams.r;
            if (view2 != null) {
                alertController.h = view2;
                alertController.i = 0;
                alertController.j = false;
            }
            alertDialog.setCancelable(alertParams.k);
            if (alertParams.k) {
                alertDialog.setCanceledOnTouchOutside(true);
            }
            alertDialog.setOnCancelListener(alertParams.l);
            alertDialog.setOnDismissListener(alertParams.m);
            DialogInterface.OnKeyListener onKeyListener = alertParams.n;
            if (onKeyListener != null) {
                alertDialog.setOnKeyListener(onKeyListener);
            }
            return alertDialog;
        }

        public Builder b(int i) {
            AlertController.AlertParams alertParams = this.a;
            alertParams.f = alertParams.a.getText(i);
            return this;
        }

        public Builder c(CharSequence charSequence) {
            this.a.f = charSequence;
            return this;
        }

        public void d(CharSequence[] charSequenceArr, boolean[] zArr, qj1.a aVar) {
            AlertController.AlertParams alertParams = this.a;
            alertParams.o = charSequenceArr;
            alertParams.w = aVar;
            alertParams.s = zArr;
            alertParams.t = true;
        }

        public Builder e(int i, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.a;
            alertParams.i = alertParams.a.getText(i);
            alertParams.j = onClickListener;
            return this;
        }

        public Builder f(int i, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.a;
            alertParams.g = alertParams.a.getText(i);
            alertParams.h = onClickListener;
            return this;
        }

        public void g(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.a;
            alertParams.g = charSequence;
            alertParams.h = onClickListener;
        }

        public void h(CharSequence[] charSequenceArr, int i, s81.a aVar) {
            AlertController.AlertParams alertParams = this.a;
            alertParams.o = charSequenceArr;
            alertParams.q = aVar;
            alertParams.v = i;
            alertParams.u = true;
        }

        public Builder i(int i) {
            AlertController.AlertParams alertParams = this.a;
            alertParams.d = alertParams.a.getText(i);
            return this;
        }

        public Builder j(View view) {
            this.a.r = view;
            return this;
        }

        public final AlertDialog k() {
            AlertDialog alertDialogA = a();
            alertDialogA.show();
            return alertDialogA;
        }

        public Builder(Context context, int i) {
            this.a = new AlertController.AlertParams(new ContextThemeWrapper(context, AlertDialog.g(context, i)));
            this.b = i;
        }
    }

    public AlertDialog(Context context, int i) {
        super(context, g(context, i));
        this.j = new AlertController(getContext(), this, getWindow());
    }

    public static int g(Context context, int i) {
        if (((i >>> 24) & 255) >= 1) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public final Button e() {
        return this.j.k;
    }

    public final ListView f() {
        return this.j.g;
    }

    public final void h(ViewGroup viewGroup) {
        AlertController alertController = this.j;
        alertController.h = viewGroup;
        alertController.i = 0;
        alertController.j = false;
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        int i;
        View view;
        ListAdapter listAdapter;
        View viewFindViewById;
        super.onCreate(bundle);
        AlertController alertController = this.j;
        alertController.b.setContentView(alertController.F);
        int i2 = R.id.parentPanel;
        Window window = alertController.c;
        View viewFindViewById2 = window.findViewById(i2);
        int i3 = R.id.topPanel;
        View viewFindViewById3 = viewFindViewById2.findViewById(i3);
        int i4 = R.id.contentPanel;
        View viewFindViewById4 = viewFindViewById2.findViewById(i4);
        int i5 = R.id.buttonPanel;
        View viewFindViewById5 = viewFindViewById2.findViewById(i5);
        ViewGroup viewGroup = (ViewGroup) viewFindViewById2.findViewById(R.id.customPanel);
        View viewInflate = alertController.h;
        Context context = alertController.a;
        if (viewInflate == null) {
            viewInflate = alertController.i != 0 ? LayoutInflater.from(context).inflate(alertController.i, viewGroup, false) : null;
        }
        boolean z = viewInflate != null;
        if (!z || !AlertController.a(viewInflate)) {
            window.setFlags(131072, 131072);
        }
        if (z) {
            FrameLayout frameLayout = (FrameLayout) window.findViewById(R.id.custom);
            frameLayout.addView(viewInflate, new ViewGroup.LayoutParams(-1, -1));
            if (alertController.j) {
                frameLayout.setPadding(0, 0, 0, 0);
            }
            if (alertController.g != null) {
                ((LinearLayout.LayoutParams) ((LinearLayoutCompat.LayoutParams) viewGroup.getLayoutParams())).weight = 0.0f;
            }
        } else {
            viewGroup.setVisibility(8);
        }
        View viewFindViewById6 = viewGroup.findViewById(i3);
        View viewFindViewById7 = viewGroup.findViewById(i4);
        View viewFindViewById8 = viewGroup.findViewById(i5);
        ViewGroup viewGroupD = AlertController.d(viewFindViewById6, viewFindViewById3);
        ViewGroup viewGroupD2 = AlertController.d(viewFindViewById7, viewFindViewById4);
        ViewGroup viewGroupD3 = AlertController.d(viewFindViewById8, viewFindViewById5);
        NestedScrollView nestedScrollView = (NestedScrollView) window.findViewById(R.id.scrollView);
        alertController.w = nestedScrollView;
        nestedScrollView.setFocusable(false);
        alertController.w.setNestedScrollingEnabled(false);
        TextView textView = (TextView) viewGroupD2.findViewById(android.R.id.message);
        alertController.B = textView;
        if (textView != null) {
            CharSequence charSequence = alertController.f;
            if (charSequence != null) {
                textView.setText(charSequence);
            } else {
                textView.setVisibility(8);
                alertController.w.removeView(alertController.B);
                if (alertController.g != null) {
                    ViewGroup viewGroup2 = (ViewGroup) alertController.w.getParent();
                    int iIndexOfChild = viewGroup2.indexOfChild(alertController.w);
                    viewGroup2.removeViewAt(iIndexOfChild);
                    viewGroup2.addView(alertController.g, iIndexOfChild, new ViewGroup.LayoutParams(-1, -1));
                } else {
                    viewGroupD2.setVisibility(8);
                }
            }
        }
        Button button = (Button) viewGroupD3.findViewById(android.R.id.button1);
        alertController.k = button;
        View.OnClickListener onClickListener = alertController.M;
        button.setOnClickListener(onClickListener);
        boolean zIsEmpty = TextUtils.isEmpty(alertController.l);
        int i6 = alertController.d;
        if (zIsEmpty && alertController.n == null) {
            alertController.k.setVisibility(8);
            i = 0;
        } else {
            alertController.k.setText(alertController.l);
            Drawable drawable = alertController.n;
            if (drawable != null) {
                drawable.setBounds(0, 0, i6, i6);
                alertController.k.setCompoundDrawables(alertController.n, null, null, null);
            }
            alertController.k.setVisibility(0);
            i = 1;
        }
        Button button2 = (Button) viewGroupD3.findViewById(android.R.id.button2);
        alertController.o = button2;
        button2.setOnClickListener(onClickListener);
        if (TextUtils.isEmpty(alertController.p) && alertController.r == null) {
            alertController.o.setVisibility(8);
        } else {
            alertController.o.setText(alertController.p);
            Drawable drawable2 = alertController.r;
            if (drawable2 != null) {
                drawable2.setBounds(0, 0, i6, i6);
                alertController.o.setCompoundDrawables(alertController.r, null, null, null);
            }
            alertController.o.setVisibility(0);
            i |= 2;
        }
        Button button3 = (Button) viewGroupD3.findViewById(android.R.id.button3);
        alertController.s = button3;
        button3.setOnClickListener(onClickListener);
        if (TextUtils.isEmpty(alertController.t) && alertController.v == null) {
            alertController.s.setVisibility(8);
            view = null;
        } else {
            alertController.s.setText(alertController.t);
            Drawable drawable3 = alertController.v;
            if (drawable3 != null) {
                drawable3.setBounds(0, 0, i6, i6);
                view = null;
                alertController.s.setCompoundDrawables(alertController.v, null, null, null);
            } else {
                view = null;
            }
            alertController.s.setVisibility(0);
            i |= 4;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.alertDialogCenterButtons, typedValue, true);
        if (typedValue.data != 0) {
            if (i == 1) {
                AlertController.b(alertController.k);
            } else if (i == 2) {
                AlertController.b(alertController.o);
            } else if (i == 4) {
                AlertController.b(alertController.s);
            }
        }
        if (!(i != 0)) {
            viewGroupD3.setVisibility(8);
        }
        if (alertController.C != null) {
            viewGroupD.addView(alertController.C, 0, new ViewGroup.LayoutParams(-1, -2));
            window.findViewById(R.id.title_template).setVisibility(8);
        } else {
            alertController.z = (ImageView) window.findViewById(android.R.id.icon);
            if ((!TextUtils.isEmpty(alertController.e)) && alertController.K) {
                TextView textView2 = (TextView) window.findViewById(R.id.alertTitle);
                alertController.A = textView2;
                textView2.setText(alertController.e);
                int i7 = alertController.x;
                if (i7 != 0) {
                    alertController.z.setImageResource(i7);
                } else {
                    Drawable drawable4 = alertController.y;
                    if (drawable4 != null) {
                        alertController.z.setImageDrawable(drawable4);
                    } else {
                        alertController.A.setPadding(alertController.z.getPaddingLeft(), alertController.z.getPaddingTop(), alertController.z.getPaddingRight(), alertController.z.getPaddingBottom());
                        alertController.z.setVisibility(8);
                    }
                }
            } else {
                window.findViewById(R.id.title_template).setVisibility(8);
                alertController.z.setVisibility(8);
                viewGroupD.setVisibility(8);
            }
        }
        boolean z2 = viewGroup.getVisibility() != 8;
        int i8 = (viewGroupD == null || viewGroupD.getVisibility() == 8) ? 0 : 1;
        boolean z3 = viewGroupD3.getVisibility() != 8;
        if (!z3 && (viewFindViewById = viewGroupD2.findViewById(R.id.textSpacerNoButtons)) != null) {
            viewFindViewById.setVisibility(0);
        }
        if (i8 != 0) {
            NestedScrollView nestedScrollView2 = alertController.w;
            if (nestedScrollView2 != null) {
                nestedScrollView2.setClipToPadding(true);
            }
            View viewFindViewById9 = (alertController.f == null && alertController.g == null) ? view : viewGroupD.findViewById(R.id.titleDividerNoCustom);
            if (viewFindViewById9 != null) {
                viewFindViewById9.setVisibility(0);
            }
        } else {
            View viewFindViewById10 = viewGroupD2.findViewById(R.id.textSpacerNoTitle);
            if (viewFindViewById10 != null) {
                viewFindViewById10.setVisibility(0);
            }
        }
        AlertController.RecycleListView recycleListView = alertController.g;
        if (recycleListView instanceof AlertController.RecycleListView) {
            recycleListView.getClass();
            if (!z3 || i8 == 0) {
                recycleListView.setPadding(recycleListView.getPaddingLeft(), i8 != 0 ? recycleListView.getPaddingTop() : recycleListView.e, recycleListView.getPaddingRight(), z3 ? recycleListView.getPaddingBottom() : recycleListView.f);
            }
        }
        if (!z2) {
            View view2 = alertController.g;
            if (view2 == null) {
                view2 = alertController.w;
            }
            if (view2 != null) {
                int i9 = z3 ? 2 : 0;
                View viewFindViewById11 = window.findViewById(R.id.scrollIndicatorUp);
                View viewFindViewById12 = window.findViewById(R.id.scrollIndicatorDown);
                WeakHashMap<View, in3> weakHashMap = om3.a;
                om3.j.b(view2, i8 | i9, 3);
                if (viewFindViewById11 != null) {
                    viewGroupD2.removeView(viewFindViewById11);
                }
                if (viewFindViewById12 != null) {
                    viewGroupD2.removeView(viewFindViewById12);
                }
            }
        }
        AlertController.RecycleListView recycleListView2 = alertController.g;
        if (recycleListView2 == null || (listAdapter = alertController.D) == null) {
            return;
        }
        recycleListView2.setAdapter(listAdapter);
        int i10 = alertController.E;
        if (i10 > -1) {
            recycleListView2.setItemChecked(i10, true);
            recycleListView2.setSelection(i10);
        }
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.j.w;
        if (nestedScrollView != null && nestedScrollView.c(keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.j.w;
        if (nestedScrollView != null && nestedScrollView.c(keyEvent)) {
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public final void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        AlertController alertController = this.j;
        alertController.e = charSequence;
        TextView textView = alertController.A;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }
}
