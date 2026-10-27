package V0;

import N1.C0420a;
import T.C0738e0;
import android.text.InputFilter;
import android.text.Selection;
import android.text.Spannable;
import android.widget.TextView;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class f extends T1.g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f9306a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f9307b;
    public final Object c;

    public f(C0738e0 c0738e0, C0420a c0420a) {
        this.f9307b = c0738e0;
        this.c = c0420a;
    }

    @Override // T1.g
    public void a() {
        switch (this.f9306a) {
            case 0:
                ((C0420a) this.c).l = i.f9310a;
                break;
        }
    }

    @Override // T1.g
    public final void b() {
        InputFilter[] filters;
        int length;
        switch (this.f9306a) {
            case 0:
                ((C0738e0) this.f9307b).setValue(Boolean.TRUE);
                ((C0420a) this.c).l = new j(true);
                break;
            default:
                TextView textView = (TextView) ((WeakReference) this.f9307b).get();
                InputFilter inputFilter = (InputFilter) ((WeakReference) this.c).get();
                if (inputFilter != null && textView != null && (filters = textView.getFilters()) != null) {
                    for (InputFilter inputFilter2 : filters) {
                        if (inputFilter2 == inputFilter) {
                            if (textView.isAttachedToWindow()) {
                                CharSequence text = textView.getText();
                                T1.i iVarA = T1.i.a();
                                if (text == null) {
                                    length = 0;
                                } else {
                                    iVarA.getClass();
                                    length = text.length();
                                }
                                CharSequence charSequenceF = iVarA.f(0, length, 0, text);
                                if (text != charSequenceF) {
                                    int selectionStart = Selection.getSelectionStart(charSequenceF);
                                    int selectionEnd = Selection.getSelectionEnd(charSequenceF);
                                    textView.setText(charSequenceF);
                                    if (charSequenceF instanceof Spannable) {
                                        Spannable spannable = (Spannable) charSequenceF;
                                        if (selectionStart >= 0 && selectionEnd >= 0) {
                                            Selection.setSelection(spannable, selectionStart, selectionEnd);
                                        } else if (selectionStart >= 0) {
                                            Selection.setSelection(spannable, selectionStart);
                                        } else if (selectionEnd >= 0) {
                                            Selection.setSelection(spannable, selectionEnd);
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    break;
                }
                break;
        }
    }

    public f(TextView textView, V1.c cVar) {
        this.f9307b = new WeakReference(textView);
        this.c = new WeakReference(cVar);
    }
}
