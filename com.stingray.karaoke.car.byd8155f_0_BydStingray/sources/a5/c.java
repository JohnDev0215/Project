package a5;

import F1.H;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.textfield.TextInputLayout;
import com.stingray.drivemodedetector.R;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f11139a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ p f11140b;

    public /* synthetic */ c(p pVar, int i7) {
        this.f11139a = i7;
        this.f11140b = pVar;
    }

    public final void a(TextInputLayout textInputLayout) {
        p pVar = this.f11140b;
        switch (this.f11139a) {
            case 0:
                EditText editText = textInputLayout.getEditText();
                textInputLayout.setEndIconVisible(editText.hasFocus() && editText.getText().length() > 0);
                textInputLayout.setEndIconCheckable(false);
                h hVar = (h) pVar;
                editText.setOnFocusChangeListener(hVar.f11148e);
                a aVar = hVar.d;
                editText.removeTextChangedListener(aVar);
                editText.addTextChangedListener(aVar);
                return;
            case 1:
                EditText editText2 = textInputLayout.getEditText();
                if (!(editText2 instanceof AutoCompleteTextView)) {
                    throw new RuntimeException("EditText needs to be an AutoCompleteTextView if an Exposed Dropdown Menu is being used.");
                }
                AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) editText2;
                o oVar = (o) pVar;
                int boxBackgroundMode = oVar.f11170a.getBoxBackgroundMode();
                if (boxBackgroundMode == 2) {
                    autoCompleteTextView.setDropDownBackgroundDrawable(oVar.f11167m);
                } else if (boxBackgroundMode == 1) {
                    autoCompleteTextView.setDropDownBackgroundDrawable(oVar.l);
                }
                if (!o.f(autoCompleteTextView)) {
                    TextInputLayout textInputLayout2 = oVar.f11170a;
                    int boxBackgroundMode2 = textInputLayout2.getBoxBackgroundMode();
                    Y4.g boxBackground = textInputLayout2.getBoxBackground();
                    int iY = com.bumptech.glide.c.Y(autoCompleteTextView, R.attr.colorControlHighlight);
                    int[][] iArr = {new int[]{android.R.attr.state_pressed}, new int[0]};
                    if (boxBackgroundMode2 == 2) {
                        int iY2 = com.bumptech.glide.c.Y(autoCompleteTextView, R.attr.colorSurface);
                        Y4.g gVar = new Y4.g(boxBackground.l.f10708a);
                        int iQ0 = com.bumptech.glide.c.q0(iY, iY2, 0.1f);
                        gVar.h(new ColorStateList(iArr, new int[]{iQ0, 0}));
                        gVar.setTint(iY2);
                        ColorStateList colorStateList = new ColorStateList(iArr, new int[]{iQ0, iY2});
                        Y4.g gVar2 = new Y4.g(boxBackground.l.f10708a);
                        gVar2.setTint(-1);
                        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{new RippleDrawable(colorStateList, gVar, gVar2), boxBackground});
                        int[] iArr2 = H.f2303a;
                        autoCompleteTextView.setBackground(layerDrawable);
                    } else if (boxBackgroundMode2 == 1) {
                        int boxBackgroundColor = textInputLayout2.getBoxBackgroundColor();
                        RippleDrawable rippleDrawable = new RippleDrawable(new ColorStateList(iArr, new int[]{com.bumptech.glide.c.q0(iY, boxBackgroundColor, 0.1f), boxBackgroundColor}), boxBackground, boxBackground);
                        int[] iArr3 = H.f2303a;
                        autoCompleteTextView.setBackground(rippleDrawable);
                    }
                }
                autoCompleteTextView.setOnTouchListener(new m(oVar, autoCompleteTextView));
                autoCompleteTextView.setOnFocusChangeListener(oVar.f11160e);
                autoCompleteTextView.setOnDismissListener(new n(oVar));
                autoCompleteTextView.setThreshold(0);
                k kVar = oVar.d;
                autoCompleteTextView.removeTextChangedListener(kVar);
                autoCompleteTextView.addTextChangedListener(kVar);
                textInputLayout.setEndIconCheckable(true);
                textInputLayout.setErrorIconDrawable((Drawable) null);
                if (autoCompleteTextView.getKeyListener() == null) {
                    CheckableImageButton checkableImageButton = oVar.c;
                    int[] iArr4 = H.f2303a;
                    checkableImageButton.setImportantForAccessibility(2);
                }
                textInputLayout.setTextInputAccessibilityDelegate(oVar.f11161f);
                textInputLayout.setEndIconVisible(true);
                return;
            default:
                EditText editText3 = textInputLayout.getEditText();
                textInputLayout.setEndIconVisible(true);
                textInputLayout.setEndIconCheckable(true);
                s sVar = (s) pVar;
                sVar.c.setChecked(!s.d(sVar));
                k kVar2 = sVar.d;
                editText3.removeTextChangedListener(kVar2);
                editText3.addTextChangedListener(kVar2);
                return;
        }
    }
}
