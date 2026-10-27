package defpackage;

import android.text.InputFilter;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.SparseArray;
import android.widget.TextView;
import androidx.emoji2.text.d;

/* JADX INFO: compiled from: EmojiTextViewHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class qg0 {
    public final b a;

    /* JADX INFO: compiled from: EmojiTextViewHelper.java */
    public static class a extends b {
        public final TextView a;
        public final ng0 b;
        public boolean c = true;

        public a(TextView textView) {
            this.a = textView;
            this.b = new ng0(textView);
        }

        @Override // qg0.b
        public final InputFilter[] a(InputFilter[] inputFilterArr) {
            if (!this.c) {
                SparseArray sparseArray = new SparseArray(1);
                for (int i = 0; i < inputFilterArr.length; i++) {
                    InputFilter inputFilter = inputFilterArr[i];
                    if (inputFilter instanceof ng0) {
                        sparseArray.put(i, inputFilter);
                    }
                }
                if (sparseArray.size() == 0) {
                    return inputFilterArr;
                }
                int length = inputFilterArr.length;
                InputFilter[] inputFilterArr2 = new InputFilter[inputFilterArr.length - sparseArray.size()];
                int i2 = 0;
                for (int i3 = 0; i3 < length; i3++) {
                    if (sparseArray.indexOfKey(i3) < 0) {
                        inputFilterArr2[i2] = inputFilterArr[i3];
                        i2++;
                    }
                }
                return inputFilterArr2;
            }
            int length2 = inputFilterArr.length;
            int i4 = 0;
            while (true) {
                ng0 ng0Var = this.b;
                if (i4 >= length2) {
                    InputFilter[] inputFilterArr3 = new InputFilter[inputFilterArr.length + 1];
                    System.arraycopy(inputFilterArr, 0, inputFilterArr3, 0, length2);
                    inputFilterArr3[length2] = ng0Var;
                    return inputFilterArr3;
                }
                if (inputFilterArr[i4] == ng0Var) {
                    return inputFilterArr;
                }
                i4++;
            }
        }

        @Override // qg0.b
        public final boolean b() {
            return this.c;
        }

        @Override // qg0.b
        public final void c(boolean z) {
            if (z) {
                TextView textView = this.a;
                textView.setTransformationMethod(e(textView.getTransformationMethod()));
            }
        }

        @Override // qg0.b
        public final void d(boolean z) {
            this.c = z;
            TextView textView = this.a;
            textView.setTransformationMethod(e(textView.getTransformationMethod()));
            textView.setFilters(a(textView.getFilters()));
        }

        @Override // qg0.b
        public final TransformationMethod e(TransformationMethod transformationMethod) {
            return this.c ? ((transformationMethod instanceof sg0) || (transformationMethod instanceof PasswordTransformationMethod)) ? transformationMethod : new sg0(transformationMethod) : transformationMethod instanceof sg0 ? ((sg0) transformationMethod).e : transformationMethod;
        }
    }

    /* JADX INFO: compiled from: EmojiTextViewHelper.java */
    public static class b {
        public InputFilter[] a(InputFilter[] inputFilterArr) {
            throw null;
        }

        public boolean b() {
            throw null;
        }

        public void c(boolean z) {
            throw null;
        }

        public void d(boolean z) {
            throw null;
        }

        public TransformationMethod e(TransformationMethod transformationMethod) {
            throw null;
        }
    }

    /* JADX INFO: compiled from: EmojiTextViewHelper.java */
    public static class c extends b {
        public final a a;

        public c(TextView textView) {
            this.a = new a(textView);
        }

        @Override // qg0.b
        public final InputFilter[] a(InputFilter[] inputFilterArr) {
            return (d.k != null) ^ true ? inputFilterArr : this.a.a(inputFilterArr);
        }

        @Override // qg0.b
        public final boolean b() {
            return this.a.c;
        }

        @Override // qg0.b
        public final void c(boolean z) {
            if (!(d.k != null)) {
                return;
            }
            this.a.c(z);
        }

        @Override // qg0.b
        public final void d(boolean z) {
            boolean z2 = !(d.k != null);
            a aVar = this.a;
            if (z2) {
                aVar.c = z;
            } else {
                aVar.d(z);
            }
        }

        @Override // qg0.b
        public final TransformationMethod e(TransformationMethod transformationMethod) {
            return (d.k != null) ^ true ? transformationMethod : this.a.e(transformationMethod);
        }
    }

    public qg0(TextView textView) {
        if (textView == null) {
            throw new NullPointerException("textView cannot be null");
        }
        this.a = new c(textView);
    }
}
