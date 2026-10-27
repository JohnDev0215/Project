package n0;

import android.text.Editable;
import android.text.Spanned;
import android.text.TextWatcher;
import android.widget.TextView;
import androidx.databinding.h;

/* JADX INFO: compiled from: TextViewBindingAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: compiled from: TextViewBindingAdapter.java */
    class a implements TextWatcher {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ c f8496e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ d f8497g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ h f8498h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        final /* synthetic */ b f8499i;

        a(c cVar, d dVar, h hVar, b bVar) {
            this.f8496e = cVar;
            this.f8497g = dVar;
            this.f8498h = hVar;
            this.f8499i = bVar;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            b bVar = this.f8499i;
            if (bVar != null) {
                bVar.afterTextChanged(editable);
            }
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i7, int i8, int i9) {
            c cVar = this.f8496e;
            if (cVar != null) {
                cVar.beforeTextChanged(charSequence, i7, i8, i9);
            }
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i7, int i8, int i9) {
            d dVar = this.f8497g;
            if (dVar != null) {
                dVar.onTextChanged(charSequence, i7, i8, i9);
            }
            h hVar = this.f8498h;
            if (hVar != null) {
                hVar.a();
            }
        }
    }

    /* JADX INFO: compiled from: TextViewBindingAdapter.java */
    public interface b {
        void afterTextChanged(Editable editable);
    }

    /* JADX INFO: compiled from: TextViewBindingAdapter.java */
    public interface c {
        void beforeTextChanged(CharSequence charSequence, int i7, int i8, int i9);
    }

    /* JADX INFO: compiled from: TextViewBindingAdapter.java */
    public interface d {
        void onTextChanged(CharSequence charSequence, int i7, int i8, int i9);
    }

    public static String a(TextView textView) {
        return textView.getText().toString();
    }

    private static boolean b(CharSequence charSequence, CharSequence charSequence2) {
        if ((charSequence == null) != (charSequence2 == null)) {
            return true;
        }
        if (charSequence == null) {
            return false;
        }
        int length = charSequence.length();
        if (length != charSequence2.length()) {
            return true;
        }
        for (int i7 = 0; i7 < length; i7++) {
            if (charSequence.charAt(i7) != charSequence2.charAt(i7)) {
                return true;
            }
        }
        return false;
    }

    public static void c(TextView textView, CharSequence charSequence) {
        CharSequence text = textView.getText();
        if (charSequence != text) {
            if (charSequence == null && text.length() == 0) {
                return;
            }
            if (charSequence instanceof Spanned) {
                if (charSequence.equals(text)) {
                    return;
                }
            } else if (!b(charSequence, text)) {
                return;
            }
            textView.setText(charSequence);
        }
    }

    public static void d(TextView textView, c cVar, d dVar, b bVar, h hVar) {
        a aVar = (cVar == null && bVar == null && dVar == null && hVar == null) ? null : new a(cVar, dVar, hVar, bVar);
        TextWatcher textWatcher = (TextWatcher) n0.c.a(textView, aVar, p0.a.textWatcher);
        if (textWatcher != null) {
            textView.removeTextChangedListener(textWatcher);
        }
        if (aVar != null) {
            textView.addTextChangedListener(aVar);
        }
    }
}
