package defpackage;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import org.chromium.components.embedder_support.delegate.ColorPickerAdvanced;
import org.chromium.components.embedder_support.delegate.ColorPickerSimple;
import org.chromium.components.embedder_support.delegate.ColorSuggestion;

/* JADX INFO: renamed from: wx, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AlertDialogC1738wx extends AlertDialog implements InterfaceC1903zx {
    public final ColorPickerAdvanced s;
    public final ColorPickerSimple t;
    public final Button u;
    public final View v;
    public final View w;
    public final InterfaceC1903zx x;
    public final int y;
    public int z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlertDialogC1738wx(Context context, InterfaceC1903zx interfaceC1903zx, int i, ColorSuggestion[] colorSuggestionArr) {
        super(context, 0);
        this.x = interfaceC1903zx;
        this.y = i;
        this.z = i;
        View viewC = c(context, AbstractC0425Xm.K);
        setCustomTitle(viewC);
        this.w = viewC.findViewById(AbstractC0371Um.a4);
        ((TextView) viewC.findViewById(AbstractC0371Um.S4)).setText(AbstractC0566bn.J0);
        setButton(-1, context.getString(AbstractC0566bn.G0), new DialogInterfaceOnClickListenerC1518sx(this));
        setButton(-2, context.getString(AbstractC0566bn.A0), new DialogInterfaceOnClickListenerC1573tx(this));
        setOnCancelListener(new DialogInterfaceOnCancelListenerC1628ux(this));
        View viewC2 = c(context, AbstractC0425Xm.f11J);
        this.v = viewC2;
        setView(viewC2);
        Button button = (Button) this.v.findViewById(AbstractC0371Um.w2);
        this.u = button;
        button.setOnClickListener(new ViewOnClickListenerC1683vx(this));
        ColorPickerAdvanced colorPickerAdvanced = (ColorPickerAdvanced) this.v.findViewById(AbstractC0371Um.l0);
        this.s = colorPickerAdvanced;
        colorPickerAdvanced.setVisibility(8);
        ColorPickerSimple colorPickerSimple = (ColorPickerSimple) this.v.findViewById(AbstractC0371Um.m0);
        this.t = colorPickerSimple;
        int[] iArr = ColorPickerSimple.t;
        colorPickerSimple.s = this;
        if (colorSuggestionArr == null) {
            int length = iArr.length;
            colorSuggestionArr = new ColorSuggestion[length];
            for (int i2 = 0; i2 < length; i2++) {
                colorSuggestionArr[i2] = new ColorSuggestion(iArr[i2], colorPickerSimple.getContext().getString(ColorPickerSimple.u[i2]));
            }
        }
        ViewOnClickListenerC1848yx viewOnClickListenerC1848yx = new ViewOnClickListenerC1848yx(colorPickerSimple.getContext(), colorSuggestionArr);
        viewOnClickListenerC1848yx.u = colorPickerSimple;
        colorPickerSimple.setAdapter((ListAdapter) viewOnClickListenerC1848yx);
        int i3 = this.y;
        this.z = i3;
        View view = this.w;
        if (view != null) {
            view.setBackgroundColor(i3);
        }
    }

    public static void b(AlertDialogC1738wx alertDialogC1738wx, int i) {
        InterfaceC1903zx interfaceC1903zx = alertDialogC1738wx.x;
        if (interfaceC1903zx != null) {
            interfaceC1903zx.a(i);
        }
    }

    @Override // defpackage.InterfaceC1903zx
    public void a(int i) {
        this.z = i;
        View view = this.w;
        if (view != null) {
            view.setBackgroundColor(i);
        }
    }

    public View c(Context context, int i) {
        C0683dt c0683dtX = C0683dt.x();
        try {
            View viewInflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(i, (ViewGroup) null);
            c0683dtX.close();
            return viewInflate;
        } catch (Throwable th) {
            try {
                c0683dtX.close();
            } catch (Throwable th2) {
                AbstractC0279Pk.a.a(th, th2);
            }
            throw th;
        }
    }
}
