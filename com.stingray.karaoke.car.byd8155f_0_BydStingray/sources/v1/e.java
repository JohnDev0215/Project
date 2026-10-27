package V1;

import a.AbstractC0904a;
import android.text.InputFilter;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.SparseArray;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class e extends AbstractC0904a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final TextView f9319e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final c f9320f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f9321g = true;

    public e(TextView textView) {
        this.f9319e = textView;
        this.f9320f = new c(textView);
    }

    @Override // a.AbstractC0904a
    public final InputFilter[] E(InputFilter[] inputFilterArr) {
        if (!this.f9321g) {
            SparseArray sparseArray = new SparseArray(1);
            for (int i7 = 0; i7 < inputFilterArr.length; i7++) {
                InputFilter inputFilter = inputFilterArr[i7];
                if (inputFilter instanceof c) {
                    sparseArray.put(i7, inputFilter);
                }
            }
            if (sparseArray.size() == 0) {
                return inputFilterArr;
            }
            int length = inputFilterArr.length;
            InputFilter[] inputFilterArr2 = new InputFilter[inputFilterArr.length - sparseArray.size()];
            int i10 = 0;
            for (int i11 = 0; i11 < length; i11++) {
                if (sparseArray.indexOfKey(i11) < 0) {
                    inputFilterArr2[i10] = inputFilterArr[i11];
                    i10++;
                }
            }
            return inputFilterArr2;
        }
        int length2 = inputFilterArr.length;
        int i12 = 0;
        while (true) {
            c cVar = this.f9320f;
            if (i12 >= length2) {
                InputFilter[] inputFilterArr3 = new InputFilter[inputFilterArr.length + 1];
                System.arraycopy(inputFilterArr, 0, inputFilterArr3, 0, length2);
                inputFilterArr3[length2] = cVar;
                return inputFilterArr3;
            }
            if (inputFilterArr[i12] == cVar) {
                return inputFilterArr;
            }
            i12++;
        }
    }

    @Override // a.AbstractC0904a
    public final void Q(boolean z6) {
        if (z6) {
            V();
        }
    }

    @Override // a.AbstractC0904a
    public final void R(boolean z6) {
        this.f9321g = z6;
        V();
        TextView textView = this.f9319e;
        textView.setFilters(E(textView.getFilters()));
    }

    public final void V() {
        TextView textView = this.f9319e;
        TransformationMethod transformationMethod = textView.getTransformationMethod();
        if (this.f9321g) {
            if (!(transformationMethod instanceof i) && !(transformationMethod instanceof PasswordTransformationMethod)) {
                transformationMethod = new i(transformationMethod);
            }
        } else if (transformationMethod instanceof i) {
            transformationMethod = ((i) transformationMethod).f9326a;
        }
        textView.setTransformationMethod(transformationMethod);
    }
}
