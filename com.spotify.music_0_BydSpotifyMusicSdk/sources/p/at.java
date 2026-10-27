package p;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.textfield.TextInputLayout;
import com.spotify.music.R;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class at implements tj2 {
    public final /* synthetic */ int a;
    public final /* synthetic */ be0 b;

    public /* synthetic */ at(be0 be0Var, int i) {
        this.a = i;
        this.b = be0Var;
    }

    public final void a(TextInputLayout textInputLayout) {
        int i = this.a;
        be0 be0Var = this.b;
        switch (i) {
            case 0:
                EditText editText = textInputLayout.getEditText();
                et etVar = (et) be0Var;
                textInputLayout.setEndIconVisible(et.d(etVar));
                textInputLayout.setEndIconCheckable(false);
                ra2 ra2Var = etVar.f;
                editText.setOnFocusChangeListener(ra2Var);
                etVar.c.setOnFocusChangeListener(ra2Var);
                pa2 pa2Var = etVar.e;
                editText.removeTextChangedListener(pa2Var);
                editText.addTextChangedListener(pa2Var);
                return;
            case 1:
                EditText editText2 = textInputLayout.getEditText();
                if (!(editText2 instanceof AutoCompleteTextView)) {
                    throw new RuntimeException("EditText needs to be an AutoCompleteTextView if an Exposed Dropdown Menu is being used.");
                }
                AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) editText2;
                pb0 pb0Var = (pb0) be0Var;
                int boxBackgroundMode = pb0Var.a.getBoxBackgroundMode();
                if (boxBackgroundMode == 2) {
                    autoCompleteTextView.setDropDownBackgroundDrawable(pb0Var.n);
                } else if (boxBackgroundMode == 1) {
                    autoCompleteTextView.setDropDownBackgroundDrawable(pb0Var.m);
                }
                if (autoCompleteTextView.getKeyListener() == null) {
                    TextInputLayout textInputLayout2 = pb0Var.a;
                    int boxBackgroundMode2 = textInputLayout2.getBoxBackgroundMode();
                    qf1 boxBackground = textInputLayout2.getBoxBackground();
                    int iQ = f60.q(autoCompleteTextView, R.attr.colorControlHighlight);
                    int[][] iArr = {new int[]{android.R.attr.state_pressed}, new int[0]};
                    if (boxBackgroundMode2 == 2) {
                        int iQ2 = f60.q(autoCompleteTextView, R.attr.colorSurface);
                        qf1 qf1Var = new qf1(boxBackground.c.a);
                        int iX = f60.x(iQ, iQ2, 0.1f);
                        qf1Var.k(new ColorStateList(iArr, new int[]{iX, 0}));
                        qf1Var.setTint(iQ2);
                        ColorStateList colorStateList = new ColorStateList(iArr, new int[]{iX, iQ2});
                        qf1 qf1Var2 = new qf1(boxBackground.c.a);
                        qf1Var2.setTint(-1);
                        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{new RippleDrawable(colorStateList, qf1Var, qf1Var2), boxBackground});
                        WeakHashMap weakHashMap = zt2.a;
                        it2.q(autoCompleteTextView, layerDrawable);
                    } else if (boxBackgroundMode2 == 1) {
                        int boxBackgroundColor = textInputLayout2.getBoxBackgroundColor();
                        RippleDrawable rippleDrawable = new RippleDrawable(new ColorStateList(iArr, new int[]{f60.x(iQ, boxBackgroundColor, 0.1f), boxBackgroundColor}), boxBackground, boxBackground);
                        WeakHashMap weakHashMap2 = zt2.a;
                        it2.q(autoCompleteTextView, rippleDrawable);
                    }
                }
                autoCompleteTextView.setOnTouchListener(new nb0(pb0Var, autoCompleteTextView));
                autoCompleteTextView.setOnFocusChangeListener(pb0Var.f);
                autoCompleteTextView.setOnDismissListener(new ob0(pb0Var));
                autoCompleteTextView.setThreshold(0);
                lb0 lb0Var = pb0Var.e;
                autoCompleteTextView.removeTextChangedListener(lb0Var);
                autoCompleteTextView.addTextChangedListener(lb0Var);
                textInputLayout.setEndIconCheckable(true);
                textInputLayout.setErrorIconDrawable((Drawable) null);
                if (autoCompleteTextView.getKeyListener() == null) {
                    CheckableImageButton checkableImageButton = pb0Var.c;
                    WeakHashMap weakHashMap3 = zt2.a;
                    it2.s(checkableImageButton, 2);
                }
                textInputLayout.setTextInputAccessibilityDelegate(pb0Var.g);
                textInputLayout.setEndIconVisible(true);
                return;
            default:
                EditText editText3 = textInputLayout.getEditText();
                textInputLayout.setEndIconVisible(true);
                textInputLayout.setEndIconCheckable(true);
                ms1 ms1Var = (ms1) be0Var;
                ms1Var.c.setChecked(!ms1.d(ms1Var));
                lb0 lb0Var2 = ms1Var.e;
                editText3.removeTextChangedListener(lb0Var2);
                editText3.addTextChangedListener(lb0Var2);
                return;
        }
    }
}
