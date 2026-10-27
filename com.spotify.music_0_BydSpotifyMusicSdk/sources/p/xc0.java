package p;

import android.text.InputFilter;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.SparseArray;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class xc0 extends u11 {
    public final TextView w;
    public final sc0 x;
    public boolean y = true;

    public xc0(TextView textView) {
        this.w = textView;
        this.x = new sc0(textView);
    }

    @Override // p.u11
    public final InputFilter[] B(InputFilter[] inputFilterArr) {
        if (!this.y) {
            SparseArray sparseArray = new SparseArray(1);
            for (int i = 0; i < inputFilterArr.length; i++) {
                InputFilter inputFilter = inputFilterArr[i];
                if (inputFilter instanceof sc0) {
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
            sc0 sc0Var = this.x;
            if (i4 >= length2) {
                InputFilter[] inputFilterArr3 = new InputFilter[inputFilterArr.length + 1];
                System.arraycopy(inputFilterArr, 0, inputFilterArr3, 0, length2);
                inputFilterArr3[length2] = sc0Var;
                return inputFilterArr3;
            }
            if (inputFilterArr[i4] == sc0Var) {
                return inputFilterArr;
            }
            i4++;
        }
    }

    @Override // p.u11
    public final void H(boolean z) {
        if (z) {
            L();
        }
    }

    @Override // p.u11
    public final void I(boolean z) {
        this.y = z;
        L();
        TextView textView = this.w;
        textView.setFilters(B(textView.getFilters()));
    }

    public final void L() {
        TextView textView = this.w;
        TransformationMethod transformationMethod = textView.getTransformationMethod();
        if (this.y) {
            if (!(transformationMethod instanceof cd0) && !(transformationMethod instanceof PasswordTransformationMethod)) {
                transformationMethod = new cd0(transformationMethod);
            }
        } else if (transformationMethod instanceof cd0) {
            transformationMethod = ((cd0) transformationMethod).a;
        }
        textView.setTransformationMethod(transformationMethod);
    }
}
