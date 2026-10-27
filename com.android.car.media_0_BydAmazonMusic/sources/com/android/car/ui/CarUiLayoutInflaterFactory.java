package com.android.car.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatViewInflater;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.ui.pluginsupport.c;
import com.android.car.ui.recyclerview.CarUiRecyclerView;
import com.android.car.ui.widget.CarUiTextView;

/* JADX INFO: loaded from: classes2.dex */
@TargetApi(28)
public class CarUiLayoutInflaterFactory extends AppCompatViewInflater implements LayoutInflater.Factory2 {
    @Override // androidx.appcompat.app.AppCompatViewInflater
    @NonNull
    public AppCompatTextView createTextView(Context context, AttributeSet attributeSet) {
        int i = CarUiTextView.a;
        return c.a(context).createTextView(context, attributeSet);
    }

    @Override // androidx.appcompat.app.AppCompatViewInflater
    @Nullable
    public View createView(Context context, String str, AttributeSet attributeSet) {
        if (CarUiRecyclerView.class.getName().equals(str)) {
            return c.a(context).createRecyclerView(context, attributeSet).getView();
        }
        if (str.contentEquals("CarUiTextView")) {
            return c.a(context).createTextView(context, attributeSet);
        }
        if ("androidx.recyclerview.widget.RecyclerView".equals(str)) {
            return new RecyclerView(context, attributeSet);
        }
        if ("TextView".equals(str)) {
            return c.a(context).createTextView(context, attributeSet);
        }
        return null;
    }

    @Override // android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return createView(context, str, attributeSet);
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return null;
    }
}
