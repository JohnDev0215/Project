package com.google.android.material.bottomsheet;

import android.view.View;
import android.widget.FrameLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import defpackage.pt3;
import defpackage.wr1;
import java.util.ArrayList;

/* JADX INFO: compiled from: BottomSheetDialog.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements wr1 {
    public final /* synthetic */ BottomSheetDialog e;

    public a(BottomSheetDialog bottomSheetDialog) {
        this.e = bottomSheetDialog;
    }

    @Override // defpackage.wr1
    public final pt3 k(View view, pt3 pt3Var) {
        BottomSheetDialog bottomSheetDialog = this.e;
        BottomSheetDialog.b bVar = bottomSheetDialog.q;
        if (bVar != null) {
            bottomSheetDialog.j.U.remove(bVar);
        }
        BottomSheetDialog.b bVar2 = new BottomSheetDialog.b(bottomSheetDialog.m, pt3Var);
        bottomSheetDialog.q = bVar2;
        bVar2.e(bottomSheetDialog.getWindow());
        BottomSheetBehavior<FrameLayout> bottomSheetBehavior = bottomSheetDialog.j;
        BottomSheetDialog.b bVar3 = bottomSheetDialog.q;
        ArrayList<BottomSheetBehavior.c> arrayList = bottomSheetBehavior.U;
        if (!arrayList.contains(bVar3)) {
            arrayList.add(bVar3);
        }
        return pt3Var;
    }
}
