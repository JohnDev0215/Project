package defpackage;

import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.chromium.base.Callback;
import org.chromium.components.browser_ui.modaldialog.ModalDialogView;
import org.chromium.components.browser_ui.widget.FadingEdgeScrollView;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class Lw implements LO {
    public void b(final JO jo, ModalDialogView modalDialogView, InterfaceC1821yO interfaceC1821yO) {
        IO io = AbstractC1711wO.c;
        if (io == interfaceC1821yO) {
            modalDialogView.x.setText((CharSequence) jo.a(io));
            modalDialogView.e();
            return;
        }
        IO io2 = AbstractC1711wO.d;
        if (io2 == interfaceC1821yO) {
            modalDialogView.y.setImageDrawable((Drawable) jo.a(io2));
            modalDialogView.e();
            return;
        }
        IO io3 = AbstractC1711wO.e;
        if (io3 == interfaceC1821yO) {
            modalDialogView.z.setText((String) jo.a(io3));
            modalDialogView.e();
            return;
        }
        IO io4 = AbstractC1711wO.f;
        if (io4 == interfaceC1821yO) {
            View view = (View) jo.a(io4);
            if (modalDialogView.A.getChildCount() > 0) {
                modalDialogView.A.removeAllViews();
            }
            if (view == null) {
                modalDialogView.A.setVisibility(8);
                return;
            }
            BN.d(view);
            modalDialogView.A.addView(view);
            modalDialogView.A.setVisibility(0);
            return;
        }
        IO io5 = AbstractC1711wO.g;
        if (io5 == interfaceC1821yO) {
            modalDialogView.a(0).setText((String) jo.a(io5));
            modalDialogView.d();
            return;
        }
        IO io6 = AbstractC1711wO.h;
        if (io6 == interfaceC1821yO) {
            modalDialogView.a(0).setContentDescription((String) jo.a(io6));
            return;
        }
        if (AbstractC1711wO.i == interfaceC1821yO) {
            modalDialogView.a(0).setEnabled(!jo.b(r0));
            return;
        }
        IO io7 = AbstractC1711wO.j;
        if (io7 == interfaceC1821yO) {
            modalDialogView.a(1).setText((String) jo.a(io7));
            modalDialogView.d();
            return;
        }
        IO io8 = AbstractC1711wO.k;
        if (io8 == interfaceC1821yO) {
            modalDialogView.a(1).setContentDescription((String) jo.a(io8));
            return;
        }
        if (AbstractC1711wO.l == interfaceC1821yO) {
            modalDialogView.a(1).setEnabled(!jo.b(r0));
            return;
        }
        HO ho = AbstractC1711wO.o;
        if (ho != interfaceC1821yO) {
            if (AbstractC1711wO.a == interfaceC1821yO) {
                modalDialogView.E = new Callback(jo) { // from class: Kw
                    public final JO a;

                    {
                        this.a = jo;
                    }

                    @Override // org.chromium.base.Callback
                    public void a(Object obj) {
                        JO jo2 = this.a;
                        ((InterfaceC1656vO) jo2.a(AbstractC1711wO.a)).d(jo2, ((Integer) obj).intValue());
                    }
                };
                return;
            }
            if (AbstractC1711wO.m == interfaceC1821yO) {
                return;
            }
            EO eo = AbstractC1711wO.n;
            if (eo != interfaceC1821yO) {
                FO fo = AbstractC1711wO.b;
                return;
            }
            boolean zB = jo.b(eo);
            if (modalDialogView.G == zB) {
                return;
            }
            modalDialogView.G = zB;
            if (zB) {
                Button buttonA = modalDialogView.a(0);
                Button buttonA2 = modalDialogView.a(1);
                View.OnTouchListener onTouchListener = new View.OnTouchListener() { // from class: Jw
                    @Override // android.view.View.OnTouchListener
                    public boolean onTouch(View view2, MotionEvent motionEvent) {
                        return ModalDialogView.c(motionEvent);
                    }
                };
                buttonA.setFilterTouchesWhenObscured(true);
                buttonA.setOnTouchListener(onTouchListener);
                buttonA2.setFilterTouchesWhenObscured(true);
                buttonA2.setOnTouchListener(onTouchListener);
                return;
            }
            return;
        }
        boolean zB2 = jo.b(ho);
        if (modalDialogView.F == zB2) {
            return;
        }
        modalDialogView.F = zB2;
        CharSequence text = modalDialogView.x.getText();
        Drawable drawable = modalDialogView.y.getDrawable();
        modalDialogView.w.setVisibility(8);
        ViewGroup viewGroup = (ViewGroup) modalDialogView.findViewById(zB2 ? AbstractC0371Um.w3 : AbstractC0371Um.U4);
        modalDialogView.w = viewGroup;
        modalDialogView.x = (TextView) viewGroup.findViewById(AbstractC0371Um.S4);
        modalDialogView.y = (ImageView) modalDialogView.w.findViewById(AbstractC0371Um.V4);
        modalDialogView.x.setText(text);
        modalDialogView.e();
        modalDialogView.y.setImageDrawable(drawable);
        modalDialogView.e();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) modalDialogView.A.getLayoutParams();
        if (zB2) {
            layoutParams.height = -2;
            layoutParams.weight = 0.0f;
            FadingEdgeScrollView fadingEdgeScrollView = modalDialogView.v;
            fadingEdgeScrollView.v = 1;
            fadingEdgeScrollView.w = 1;
            fadingEdgeScrollView.invalidate();
        } else {
            layoutParams.height = 0;
            layoutParams.weight = 1.0f;
            FadingEdgeScrollView fadingEdgeScrollView2 = modalDialogView.v;
            fadingEdgeScrollView2.v = 0;
            fadingEdgeScrollView2.w = 0;
            fadingEdgeScrollView2.invalidate();
        }
        modalDialogView.A.setLayoutParams(layoutParams);
    }
}
