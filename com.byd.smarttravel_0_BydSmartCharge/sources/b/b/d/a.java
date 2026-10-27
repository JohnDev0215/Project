package b.b.d;

import android.content.Context;
import android.graphics.Rect;
import android.text.method.TransformationMethod;
import android.view.View;
import java.util.Locale;

/* JADX INFO: compiled from: AllCapsTransformationMethod.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements TransformationMethod {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Locale f644b;

    public a(Context context) {
        this.f644b = context.getResources().getConfiguration().locale;
    }

    @Override // android.text.method.TransformationMethod
    public CharSequence getTransformation(CharSequence charSequence, View view) {
        if (charSequence != null) {
            return charSequence.toString().toUpperCase(this.f644b);
        }
        return null;
    }

    @Override // android.text.method.TransformationMethod
    public void onFocusChanged(View view, CharSequence charSequence, boolean z, int i, Rect rect) {
    }
}
