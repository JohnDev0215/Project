package com.byd.filemanager.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"AppCompatCustomView"})
public class AppEditText extends EditText {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Field f3863b;

    static {
        try {
            Field declaredField = View.class.getDeclaredField("mParent");
            f3863b = declaredField;
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        }
    }

    public AppEditText(Context context) {
        super(context.getApplicationContext());
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        try {
            Field field = f3863b;
            if (field != null) {
                field.set(this, null);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        super.onDetachedFromWindow();
    }

    public AppEditText(Context context, AttributeSet attributeSet) {
        super(context.getApplicationContext(), attributeSet);
    }

    public AppEditText(Context context, AttributeSet attributeSet, int i) {
        super(context.getApplicationContext(), attributeSet, i);
    }
}
