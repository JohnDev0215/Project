package com.android.car.ui.toolbar;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.EditText;
import androidx.annotation.Nullable;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"AppCompatCustomView"})
@TargetApi(28)
class CarUiEditText extends EditText {

    @Nullable
    public BiConsumer<String, Bundle> a;

    public CarUiEditText(Context context) {
        super(context);
        this.a = null;
    }

    @Override // android.widget.TextView
    public final boolean onPrivateIMECommand(String str, Bundle bundle) {
        BiConsumer<String, Bundle> biConsumer = this.a;
        if (biConsumer == null) {
            return false;
        }
        biConsumer.accept(str, bundle);
        return false;
    }

    public CarUiEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = null;
    }

    public CarUiEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = null;
    }

    public CarUiEditText(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.a = null;
    }
}
