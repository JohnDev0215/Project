package com.android.car.ui.appstyledview;

import android.view.View;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import spi.c;
import spi.o;
import spi.q;

/* JADX INFO: loaded from: classes2.dex */
public class AppStyledViewControllerAdapterV1 implements o {

    @NonNull
    private final q mOemController;

    public AppStyledViewControllerAdapterV1(@NonNull q qVar) {
        this.mOemController = qVar;
        qVar.a();
    }

    public View getAppStyledView(@Nullable View view) {
        this.mOemController.c();
        return this.mOemController.getView();
    }

    public WindowManager.LayoutParams getDialogWindowLayoutParam(WindowManager.LayoutParams layoutParams) {
        return this.mOemController.b();
    }

    public void setNavIcon(int i) {
        if (i == 0) {
            this.mOemController.a();
        } else {
            if (i != 1) {
                throw new IllegalArgumentException(c.d("Unknown nav icon style: ", i));
            }
            this.mOemController.a();
        }
    }

    public void setOnNavIconClickListener(Runnable runnable) {
        this.mOemController.d();
    }
}
