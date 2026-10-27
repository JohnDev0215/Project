package a5;

import android.text.Editable;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.CheckedTextView;
import android.widget.EditText;
import androidx.appcompat.widget.Toolbar;
import androidx.media3.ui.TrackSelectionView;
import com.google.android.material.textfield.TextInputLayout;
import d5.I;
import e2.g0;
import e2.h0;
import e2.m0;
import java.util.ArrayList;
import java.util.HashMap;
import m3.K;
import q.MenuItemC2152i;
import r.A0;
import r.w0;

/* JADX INFO: loaded from: classes.dex */
public final class e implements View.OnClickListener {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f11143m;

    public /* synthetic */ e(Object obj, int i7) {
        this.l = i7;
        this.f11143m = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.l) {
            case 0:
                h hVar = (h) this.f11143m;
                Editable text = hVar.f11170a.getEditText().getText();
                if (text != null) {
                    text.clear();
                }
                TextInputLayout textInputLayout = hVar.f11170a;
                textInputLayout.k(textInputLayout.f12932s0, textInputLayout.f12936u0);
                break;
            case 1:
                o oVar = (o) this.f11143m;
                o.d(oVar, (AutoCompleteTextView) oVar.f11170a.getEditText());
                break;
            case 2:
                s sVar = (s) this.f11143m;
                EditText editText = sVar.f11170a.getEditText();
                if (editText != null) {
                    int selectionEnd = editText.getSelectionEnd();
                    if (s.d(sVar)) {
                        editText.setTransformationMethod(null);
                    } else {
                        editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                    if (selectionEnd >= 0) {
                        editText.setSelection(selectionEnd);
                    }
                    TextInputLayout textInputLayout2 = sVar.f11170a;
                    textInputLayout2.k(textInputLayout2.f12932s0, textInputLayout2.f12936u0);
                    break;
                }
                break;
            case 3:
                TrackSelectionView trackSelectionView = (TrackSelectionView) this.f11143m;
                CheckedTextView checkedTextView = trackSelectionView.f12059n;
                HashMap map = trackSelectionView.f12062r;
                boolean z6 = true;
                if (view == checkedTextView) {
                    trackSelectionView.f12067w = true;
                    map.clear();
                } else if (view == trackSelectionView.o) {
                    trackSelectionView.f12067w = false;
                    map.clear();
                } else {
                    trackSelectionView.f12067w = false;
                    Object tag = view.getTag();
                    tag.getClass();
                    K k10 = (K) tag;
                    m0 m0Var = k10.f19000a;
                    g0 g0Var = m0Var.f14222b;
                    h0 h0Var = (h0) map.get(g0Var);
                    int i7 = k10.f19001b;
                    if (h0Var == null) {
                        if (!trackSelectionView.f12064t && map.size() > 0) {
                            map.clear();
                        }
                        map.put(g0Var, new h0(g0Var, I.B(Integer.valueOf(i7))));
                    } else {
                        ArrayList arrayList = new ArrayList(h0Var.f14119b);
                        boolean zIsChecked = ((CheckedTextView) view).isChecked();
                        boolean z10 = trackSelectionView.f12063s && m0Var.c;
                        if (!z10 && (!trackSelectionView.f12064t || trackSelectionView.f12061q.size() <= 1)) {
                            z6 = false;
                        }
                        if (zIsChecked && z6) {
                            arrayList.remove(Integer.valueOf(i7));
                            if (arrayList.isEmpty()) {
                                map.remove(g0Var);
                            } else {
                                map.put(g0Var, new h0(g0Var, arrayList));
                            }
                        } else if (!zIsChecked) {
                            if (z10) {
                                arrayList.add(Integer.valueOf(i7));
                                map.put(g0Var, new h0(g0Var, arrayList));
                            } else {
                                map.put(g0Var, new h0(g0Var, I.B(Integer.valueOf(i7))));
                            }
                        }
                    }
                }
                trackSelectionView.a();
                break;
            case 4:
                w0 w0Var = ((Toolbar) this.f11143m).f11371V;
                MenuItemC2152i menuItemC2152i = w0Var == null ? null : w0Var.f21566m;
                if (menuItemC2152i != null) {
                    menuItemC2152i.collapseActionView();
                }
                break;
            default:
                A0 a02 = (A0) this.f11143m;
                if (a02.f21416k != null) {
                    a02.getClass();
                }
                break;
        }
    }

    public e(A0 a02) {
        this.l = 5;
        this.f11143m = a02;
        a02.f21408a.getContext();
    }
}
