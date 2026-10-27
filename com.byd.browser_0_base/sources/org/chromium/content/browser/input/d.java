package org.chromium.content.browser.input;

import android.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.SparseBooleanArray;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import defpackage.aq3;
import defpackage.rh2;
import defpackage.sh2;
import defpackage.th2;
import defpackage.uh2;
import defpackage.vh2;
import defpackage.wh3;
import java.util.ArrayList;
import org.chromium.base.Callback;
import org.chromium.content.R$attr;
import org.chromium.content.R$style;
import org.chromium.content.browser.input.SelectPopup;

/* JADX INFO: compiled from: SelectPopupDialog.java */
/* JADX INFO: loaded from: classes3.dex */
public final class d implements SelectPopup.a {
    public static final int[] d = {R$attr.select_dialog_multichoice, R$attr.select_dialog_singlechoice};
    public final AlertDialog a;
    public final Callback<int[]> b;
    public boolean c;

    public d(Context context, aq3 aq3Var, ArrayList arrayList, boolean z, int[] iArr) {
        this.b = aq3Var;
        ListView listView = new ListView(context);
        listView.setCacheColorHint(0);
        if (wh3.a == null) {
            wh3.a = new wh3();
        }
        wh3.a.getClass();
        AlertDialog alertDialogCreate = new AlertDialog.Builder(context).create();
        this.a = alertDialogCreate;
        alertDialogCreate.setView(listView);
        alertDialogCreate.setCancelable(true);
        alertDialogCreate.setInverseBackgroundForced(true);
        if (z) {
            alertDialogCreate.setButton(-1, alertDialogCreate.getContext().getString(R.string.ok), new sh2(this, listView));
            alertDialogCreate.setButton(-2, alertDialogCreate.getContext().getString(R.string.cancel), new th2(this));
        }
        Context context2 = alertDialogCreate.getContext();
        TypedArray typedArrayObtainStyledAttributes = alertDialogCreate.getContext().obtainStyledAttributes(R$style.SelectPopupDialog, d);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(!z ? 1 : 0, 0);
        typedArrayObtainStyledAttributes.recycle();
        listView.setAdapter((ListAdapter) new rh2(resourceId, context2, arrayList));
        listView.setFocusableInTouchMode(true);
        if (z) {
            listView.setChoiceMode(2);
            for (int i : iArr) {
                listView.setItemChecked(i, true);
            }
        } else {
            listView.setChoiceMode(1);
            listView.setOnItemClickListener(new uh2(this, listView));
            if (iArr.length > 0) {
                listView.setSelection(iArr[0]);
                listView.setItemChecked(iArr[0], true);
            }
        }
        this.a.setOnCancelListener(new vh2(this));
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

    @Override // org.chromium.content.browser.input.SelectPopup.a
    public final void a() {
        try {
            this.a.show();
        } catch (WindowManager.BadTokenException unused) {
            d(null);
        }
    }

    @Override // org.chromium.content.browser.input.SelectPopup.a
    public final void b(boolean z) {
        AlertDialog alertDialog = this.a;
        if (z) {
            alertDialog.cancel();
            d(null);
        } else {
            this.c = true;
            alertDialog.cancel();
        }
    }

    public final void d(int[] iArr) {
        if (this.c) {
            return;
        }
        this.b.onResult(iArr);
        this.c = true;
    }
}
