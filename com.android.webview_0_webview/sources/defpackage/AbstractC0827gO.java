package defpackage;

import android.content.Context;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.view.Display;
import android.view.WindowManager;
import java.util.List;
import java.util.Objects;
import java.util.WeakHashMap;
import org.chromium.base.BuildInfo;
import org.chromium.ui.display.DisplayAndroidManager;

/* JADX INFO: renamed from: gO, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0827gO {
    public static final InterfaceC0771fO[] m = new InterfaceC0771fO[0];
    public final int b;
    public float d;
    public int e;
    public int f;
    public int g;
    public float h;
    public Display.Mode i;
    public List j;
    public boolean k;
    public boolean l;
    public final WeakHashMap a = new WeakHashMap();
    public Point c = new Point();

    public AbstractC0827gO(int i) {
        this.b = i;
    }

    public static DisplayAndroidManager b() {
        return DisplayAndroidManager.a();
    }

    public static AbstractC0827gO c(Context context) {
        Display display = (BuildInfo.e() && Is.a(context) == null) ? ((DisplayManager) Is.a.getSystemService("display")).getDisplay(0) : ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayAndroidManager displayAndroidManagerA = DisplayAndroidManager.a();
        Objects.requireNonNull(displayAndroidManagerA);
        AbstractC0827gO abstractC0827gO = (AbstractC0827gO) displayAndroidManagerA.c.get(display.getDisplayId());
        if (abstractC0827gO != null) {
            return abstractC0827gO;
        }
        int displayId = display.getDisplayId();
        C0994jO c0994jO = new C0994jO(display);
        displayAndroidManagerA.c.put(displayId, c0994jO);
        c0994jO.f(display);
        return c0994jO;
    }

    public void a(InterfaceC0771fO interfaceC0771fO) {
        this.a.put(interfaceC0771fO, null);
    }

    public final InterfaceC0771fO[] d() {
        return (InterfaceC0771fO[]) this.a.keySet().toArray(m);
    }

    public void e(Point point, Float f, Integer num, Integer num2, Integer num3, Boolean bool, Boolean bool2, Float f2, Display.Mode mode, List list) {
        List list2;
        boolean z = true;
        boolean z2 = (point == null || this.c.equals(point)) ? false : true;
        boolean z3 = (f == null || this.d == f.floatValue()) ? false : true;
        boolean z4 = (num == null || this.e == num.intValue()) ? false : true;
        boolean z5 = (num2 == null || this.f == num2.intValue()) ? false : true;
        boolean z6 = (num3 == null || this.g == num3.intValue()) ? false : true;
        boolean z7 = (bool == null || this.k == bool.booleanValue()) ? false : true;
        boolean z8 = (bool2 == null || this.l == bool2.booleanValue()) ? false : true;
        boolean z9 = (f2 == null || this.h == f2.floatValue()) ? false : true;
        boolean z10 = list != null && ((list2 = this.j) == null || list2.equals(list));
        boolean z11 = (mode == null || mode.equals(this.i)) ? false : true;
        if (!z2 && !z3 && !z4 && !z5 && !z6 && !z7 && !z8 && !z9 && !z10 && !z11) {
            z = false;
        }
        if (z) {
            if (z2) {
                this.c = point;
            }
            if (z3) {
                this.d = f.floatValue();
            }
            if (z4) {
                this.e = num.intValue();
            }
            if (z5) {
                this.f = num2.intValue();
            }
            if (z6) {
                this.g = num3.intValue();
            }
            if (z7) {
                this.k = bool.booleanValue();
            }
            if (z8) {
                this.l = bool2.booleanValue();
            }
            if (z9) {
                this.h = f2.floatValue();
            }
            if (z10) {
                this.j = list;
            }
            if (z11) {
                this.i = mode;
            }
            b().b(this);
            if (z6) {
                for (InterfaceC0771fO interfaceC0771fO : d()) {
                    interfaceC0771fO.x(this.g);
                }
            }
            if (z3) {
                for (InterfaceC0771fO interfaceC0771fO2 : d()) {
                    interfaceC0771fO2.e(this.d);
                }
            }
            if (z9) {
                for (InterfaceC0771fO interfaceC0771fO3 : d()) {
                    interfaceC0771fO3.j(this.h);
                }
            }
            if (z10) {
                for (InterfaceC0771fO interfaceC0771fO4 : d()) {
                    interfaceC0771fO4.y(this.j);
                }
            }
            if (z11) {
                for (InterfaceC0771fO interfaceC0771fO5 : d()) {
                    interfaceC0771fO5.C(this.i);
                }
            }
        }
    }
}
