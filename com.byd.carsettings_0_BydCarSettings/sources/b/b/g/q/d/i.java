package b.b.g.q.d;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import b.b.g.q.d.q;
import com.byd.carsettings.R;
import com.byd.carsettings.ui.view.CountDownTimerButton;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;

/* JADX INFO: compiled from: BydTipDialogBuilder.java */
/* JADX INFO: loaded from: classes2.dex */
public class i extends q implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f1447a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Dialog f1448b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b.b.g.q.a f1449c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public View f1450d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1451e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f1452f;
    public q.b g;
    public q.a h;
    public q.e i;
    public q.d j;

    /* JADX INFO: compiled from: BydTipDialogBuilder.java */
    public class a implements View.OnAttachStateChangeListener {
        public a() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            if (i.this.f1450d == null) {
                return;
            }
            Button button = (Button) i.this.f1450d.findViewById(R.id.carsetting_dialog_btn_cancel);
            if (button != null && (button instanceof CountDownTimerButton)) {
                ((CountDownTimerButton) button).k();
            }
            if (i.this.i != null) {
                i.this.i.a();
                k.e(i.this.f1452f);
            }
        }
    }

    /* JADX INFO: compiled from: BydTipDialogBuilder.java */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
        }
    }

    /* JADX INFO: compiled from: BydTipDialogBuilder.java */
    public class c implements DialogInterface.OnKeyListener {
        public c() {
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            return i.this.w(i, keyEvent);
        }
    }

    /* JADX INFO: compiled from: BydTipDialogBuilder.java */
    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (i.this.f1448b != null) {
                if (i.this.h != null) {
                    i.this.h.a();
                }
                i.this.f1448b.dismiss();
                if (i.this.f1451e) {
                    CountDownTimerButton.i();
                }
                k.e(i.this.f1452f);
            }
        }
    }

    /* JADX INFO: compiled from: BydTipDialogBuilder.java */
    public class e implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ TextView f1457a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f1458b;

        public e(TextView textView, String str) {
            this.f1457a = textView;
            this.f1458b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f1457a.getLineCount() > 2) {
                this.f1457a.setTextAlignment(5);
                this.f1457a.setText(this.f1458b);
            }
        }
    }

    /* JADX INFO: compiled from: BydTipDialogBuilder.java */
    public class f implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ TextView f1460a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f1461b;

        public f(TextView textView, String str) {
            this.f1460a = textView;
            this.f1461b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f1460a.getLineCount() > 2) {
                this.f1460a.setTextAlignment(5);
                this.f1460a.setText(this.f1461b);
            }
        }
    }

    public i(Context context) {
        this.f1447a = context;
        this.f1449c = new b.b.g.q.a(context);
    }

    public Dialog A(int i, int i2, int i3, int i4, int i5, int i6, int i7, q.b bVar, int i8, q.a aVar, q.e eVar, q.d dVar) {
        k.a(this.f1452f);
        i(i);
        p(i2);
        s(i3, i7);
        v(i4);
        q(i5);
        m(i6, bVar);
        k(i8, aVar);
        n(eVar);
        t(dVar);
        return g();
    }

    public Dialog B(int i, int i2, int i3, int i4, int i5, int i6, q.b bVar, int i7, q.a aVar) {
        return z(i, i2, i3, i4, i5, i6, 0, bVar, i7, aVar);
    }

    public Dialog g() {
        return this.f1448b;
    }

    public q h(q.c cVar) {
        if (cVar != null) {
            this.f1451e = true;
        }
        ((CountDownTimerButton) this.f1450d.findViewById(R.id.carsetting_dialog_btn_cancel)).setOnBtnCallBackListener(cVar);
        return this;
    }

    public i i(int i) {
        Dialog dialogQ = this.f1449c.q(R.layout.carseting_dialog_container);
        this.f1448b = dialogQ;
        RelativeLayout relativeLayout = (RelativeLayout) dialogQ.findViewById(R.id.dialog_container_rl);
        Context context = this.f1447a;
        b.b.a.h.l.b(relativeLayout, context, b.b.a.h.b.a(context, R.attr.dialog_masks));
        relativeLayout.removeAllViews();
        relativeLayout.setGravity(17);
        this.f1450d = LayoutInflater.from(this.f1447a).inflate(i, (ViewGroup) relativeLayout, false);
        int iC = b.b.o.b.b.a.c();
        View viewFindViewById = this.f1450d.findViewById(R.id.layout_dialog);
        if (viewFindViewById != null && ((viewFindViewById instanceof LinearLayout) || (viewFindViewById instanceof RelativeLayout))) {
            if (iC == 0 || iC == 2) {
                viewFindViewById.setLayoutDirection(0);
            } else {
                viewFindViewById.setLayoutDirection(1);
            }
        }
        this.f1450d.addOnAttachStateChangeListener(new a());
        relativeLayout.addView(this.f1450d);
        this.f1450d.setOnClickListener(new b());
        this.f1448b.setOnKeyListener(new c());
        this.f1450d.setSoundEffectsEnabled(false);
        relativeLayout.setOnClickListener(new d());
        relativeLayout.getChildAt(0).setScaleX(0.9f);
        relativeLayout.getChildAt(0).setScaleY(0.9f);
        relativeLayout.getChildAt(0).setAlpha(0.0f);
        relativeLayout.getChildAt(0).animate().scaleX(1.0f).scaleY(1.0f).setDuration(200L).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).start();
        relativeLayout.getChildAt(0).animate().alpha(1.0f).setDuration(200L).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).start();
        WindowManager.LayoutParams attributes = this.f1448b.getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        attributes.gravity = 17;
        attributes.dimAmount = 0.0f;
        attributes.windowAnimations = R.style.byd_dialog_common_anim;
        this.f1448b.getWindow().setAttributes(attributes);
        this.f1448b.setCanceledOnTouchOutside(true);
        this.f1448b.getWindow().addFlags(Integer.MIN_VALUE);
        this.f1448b.getWindow().clearFlags(67108864);
        this.f1448b.getWindow().setStatusBarColor(0);
        this.f1448b.getWindow().getDecorView().setSystemUiVisibility(1792);
        return this;
    }

    public i j(String str, q.a aVar) {
        if (!TextUtils.isEmpty(str)) {
            Button button = (Button) this.f1450d.findViewById(R.id.carsetting_dialog_btn_cancel);
            button.setText(str);
            button.setTextColor(this.f1447a.getResources().getColor(b.b.a.h.b.a(this.f1447a, R.attr.byd_vehicle_dialog_btn_cancel_text_color)));
            this.h = aVar;
            b.b.a.h.l.J(0, button);
            if (aVar != null) {
                b.b.a.h.l.F(this, button);
            }
        }
        return this;
    }

    public q k(int i, q.a aVar) {
        return i == -1 ? this : j(this.f1447a.getString(i), aVar);
    }

    public i l(String str, q.b bVar) {
        if (!TextUtils.isEmpty(str)) {
            Button button = (Button) this.f1450d.findViewById(R.id.carsetting_dialog_btn_confirm);
            button.setText(str);
            button.setTextColor(this.f1447a.getResources().getColor(b.b.a.h.b.a(this.f1447a, R.attr.byd_vehicle_dialog_btn_confirm_text_color)));
            this.g = bVar;
            b.b.a.h.l.J(0, button);
            b.b.a.h.l.F(this, button);
        }
        return this;
    }

    public q m(int i, q.b bVar) {
        return i == -1 ? this : l(this.f1447a.getString(i), bVar);
    }

    public q n(q.e eVar) {
        this.i = eVar;
        return this;
    }

    public i o(String str) {
        this.f1450d.findViewById(R.id.carsetting_dialog_head).setVisibility(0);
        ((TextView) this.f1450d.findViewById(R.id.carsetting_dialog_title)).setText(str);
        return this;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.carsetting_dialog_btn_cancel /* 2131296657 */:
                q.a aVar = this.h;
                if (aVar != null) {
                    aVar.a();
                    if (this.f1451e) {
                        CountDownTimerButton.i();
                    }
                    k.e(this.f1452f);
                }
                break;
            case R.id.carsetting_dialog_btn_confirm /* 2131296658 */:
                q.b bVar = this.g;
                if (bVar != null) {
                    bVar.a();
                    if (this.f1451e) {
                        CountDownTimerButton.i();
                    }
                    k.e(this.f1452f);
                }
                break;
        }
    }

    public q p(int i) {
        return i == -1 ? this : o(this.f1447a.getString(i));
    }

    public i q(int i) {
        if (i != -1) {
            ImageView imageView = (ImageView) this.f1450d.findViewById(R.id.carsetting_dialog_image_content);
            b.b.a.h.l.J(0, imageView);
            imageView.setImageResource(i);
        }
        return this;
    }

    public i r(String str, int i) {
        TextView textView = (TextView) this.f1450d.findViewById(R.id.carsetting_dialog_main_content);
        if (i == 0) {
            textView.setTextAlignment(4);
        }
        if (i == 1) {
            textView.setTextAlignment(5);
        }
        if (i == 2) {
            textView.setTextAlignment(6);
        }
        b.b.a.h.l.J(0, textView);
        textView.setText(str);
        textView.post(new e(textView, str));
        return this;
    }

    public q s(int i, int i2) {
        return r(this.f1447a.getString(i), i2);
    }

    public q t(q.d dVar) {
        this.j = dVar;
        return this;
    }

    public i u(String str) {
        TextView textView = (TextView) this.f1450d.findViewById(R.id.carsetting_dialog_second_content);
        b.b.a.h.l.J(0, textView);
        textView.setText(str);
        textView.post(new f(textView, str));
        return this;
    }

    public q v(int i) {
        return i == -1 ? this : u(this.f1447a.getString(i));
    }

    public boolean w(int i, KeyEvent keyEvent) {
        if (i != 4 || keyEvent.getAction() != 1) {
            return false;
        }
        q.d dVar = this.j;
        if (dVar != null) {
            dVar.a();
        }
        k.e(this.f1452f);
        if (this.f1451e) {
            CountDownTimerButton.i();
        }
        Dialog dialog = this.f1448b;
        if (dialog != null) {
            dialog.dismiss();
        }
        return true;
    }

    public void x(String str) {
        this.f1452f = str;
    }

    public Dialog y(int i, int i2, int i3, int i4, int i5, int i6, q.b bVar, int i7, q.c cVar, q.e eVar) {
        k.a(this.f1452f);
        i(i);
        p(i2);
        s(i3, 0);
        v(i4);
        q(i5);
        m(i6, bVar);
        k(i7, null);
        h(cVar);
        n(eVar);
        return g();
    }

    public Dialog z(int i, int i2, int i3, int i4, int i5, int i6, int i7, q.b bVar, int i8, q.a aVar) {
        return A(i, i2, i3, i4, i5, i6, i7, bVar, i8, aVar, null, null);
    }
}
