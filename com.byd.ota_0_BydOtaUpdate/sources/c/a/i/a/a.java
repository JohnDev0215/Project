package c.a.i.a;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

/* JADX INFO: compiled from: BaseDialog.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a extends Dialog {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f2785c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Resources f2786d;

    /* JADX INFO: renamed from: c.a.i.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BaseDialog.java */
    public class DialogInterfaceOnKeyListenerC0063a implements DialogInterface.OnKeyListener {
        public DialogInterfaceOnKeyListenerC0063a() {
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
            return i2 == 4;
        }
    }

    public a(Context context) {
        super(context);
        context.setTheme(c.a.e.f.f2652a);
        this.f2785c = context;
        this.f2786d = context.getResources();
        requestWindowFeature(1);
        Window window = getWindow();
        window.setDimAmount(0.5f);
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.addFlags(131072);
        window.setType(2044);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 17;
        attributes.width = -1;
        attributes.height = -1;
        attributes.format = 1;
        window.setAttributes(attributes);
        setCanceledOnTouchOutside(false);
        setOnKeyListener(new DialogInterfaceOnKeyListenerC0063a());
    }
}
