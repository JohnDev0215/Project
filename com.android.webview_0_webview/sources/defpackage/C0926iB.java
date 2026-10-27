package defpackage;

import android.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.SparseBooleanArray;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.List;
import java.util.Objects;
import org.chromium.base.Callback;

/* JADX INFO: renamed from: iB, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0926iB implements InterfaceC0479aB {
    public static final int[] d = {AbstractC0245Nm.u6, AbstractC0245Nm.v6};
    public final AlertDialog a;
    public final Callback b;
    public boolean c;

    public C0926iB(Context context, Callback callback, List list, boolean z, int[] iArr) {
        this.b = callback;
        ListView listView = new ListView(context);
        listView.setCacheColorHint(0);
        Objects.requireNonNull(C1657vP.a());
        AlertDialog alertDialogCreate = new AlertDialog.Builder(context).create();
        this.a = alertDialogCreate;
        alertDialogCreate.setView(listView);
        this.a.setCancelable(true);
        this.a.setInverseBackgroundForced(true);
        if (z) {
            AlertDialog alertDialog = this.a;
            alertDialog.setButton(-1, alertDialog.getContext().getString(R.string.ok), new DialogInterfaceOnClickListenerC0702eB(this, listView));
            AlertDialog alertDialog2 = this.a;
            alertDialog2.setButton(-2, alertDialog2.getContext().getString(R.string.cancel), new DialogInterfaceOnClickListenerC0758fB(this));
        }
        Context context2 = this.a.getContext();
        TypedArray typedArrayObtainStyledAttributes = this.a.getContext().obtainStyledAttributes(AbstractC0622cn.Y3, d);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(!z ? 1 : 0, 0);
        typedArrayObtainStyledAttributes.recycle();
        listView.setAdapter((ListAdapter) new C0647dB(context2, resourceId, list));
        listView.setFocusableInTouchMode(true);
        if (z) {
            listView.setChoiceMode(2);
            for (int i : iArr) {
                listView.setItemChecked(i, true);
            }
        } else {
            listView.setChoiceMode(1);
            listView.setOnItemClickListener(new C0814gB(this, listView));
            if (iArr.length > 0) {
                listView.setSelection(iArr[0]);
                listView.setItemChecked(iArr[0], true);
            }
        }
        this.a.setOnCancelListener(new DialogInterfaceOnCancelListenerC0870hB(this));
    }

    public static int[] c(ListView listView) {
        SparseBooleanArray checkedItemPositions = listView.getCheckedItemPositions();
        int i = 0;
        for (int i2 = 0; i2 < checkedItemPositions.size(); i2++) {
            if (checkedItemPositions.valueAt(i2)) {
                i++;
            }
        }
        int[] iArr = new int[i];
        int i3 = 0;
        for (int i4 = 0; i4 < checkedItemPositions.size(); i4++) {
            if (checkedItemPositions.valueAt(i4)) {
                iArr[i3] = checkedItemPositions.keyAt(i4);
                i3++;
            }
        }
        return iArr;
    }

    @Override // defpackage.InterfaceC0479aB
    public void a() {
        try {
            this.a.show();
        } catch (WindowManager.BadTokenException unused) {
            d(null);
        }
    }

    @Override // defpackage.InterfaceC0479aB
    public void b(boolean z) {
        if (z) {
            this.a.cancel();
            d(null);
        } else {
            this.c = true;
            this.a.cancel();
        }
    }

    public final void d(int[] iArr) {
        if (this.c) {
            return;
        }
        this.b.a(iArr);
        this.c = true;
    }
}
