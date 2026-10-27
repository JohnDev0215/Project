package defpackage;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.R;
import androidx.appcompat.app.AppCompatViewInflater;
import androidx.appcompat.widget.VectorEnabledTintResources;

/* JADX INFO: compiled from: AsyncAppCompatFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class v8 implements LayoutInflater.Factory2 {
    public AppCompatViewInflater e;

    public final View a(View view, String str, Context context, AttributeSet attributeSet) {
        if (this.e == null) {
            String string = context.obtainStyledAttributes(R.styleable.AppCompatTheme).getString(R.styleable.AppCompatTheme_viewInflaterClass);
            if (string == null) {
                this.e = new AppCompatViewInflater();
            } else {
                try {
                    this.e = (AppCompatViewInflater) context.getClassLoader().loadClass(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable th) {
                    Log.i("AsyncAppCompatFactory", "Failed to instantiate custom view inflater " + string + ". Falling back to default.", th);
                    this.e = new AppCompatViewInflater();
                }
            }
        }
        AppCompatViewInflater appCompatViewInflater = this.e;
        int i = VectorEnabledTintResources.a;
        return appCompatViewInflater.f(view, str, context, attributeSet);
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return a(view, str, context, attributeSet);
    }

    @Override // android.view.LayoutInflater.Factory
    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return a(null, str, context, attributeSet);
    }
}
