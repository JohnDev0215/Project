package b.c.a.g;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public class c implements LayoutInflater.Factory2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ a.b.k.i f1763a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ LayoutInflater f1764b;

    public c(a.b.k.i iVar, LayoutInflater layoutInflater) {
        this.f1763a = iVar;
        this.f1764b = layoutInflater;
    }

    @Override // android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes;
        Drawable drawable;
        Drawable drawable2;
        View viewF = this.f1763a.q().f(view, str, context, attributeSet);
        LayoutInflater layoutInflater = this.f1764b;
        a.b.k.i iVar = this.f1763a;
        if (viewF == null) {
            try {
                viewF = -1 == str.indexOf(46) ? layoutInflater.onCreateView(iVar, view, str, attributeSet) : layoutInflater.createView(iVar, str, null, attributeSet);
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
            }
        }
        if (viewF != null) {
            TypedArray typedArrayObtainStyledAttributes2 = iVar.obtainStyledAttributes(attributeSet, new int[]{R.attr.background});
            int resourceId = typedArrayObtainStyledAttributes2.getResourceId(0, 0);
            if (resourceId != 0) {
                viewF.setBackgroundResource(resourceId);
            }
            typedArrayObtainStyledAttributes2.recycle();
            if (viewF instanceof TextView) {
                TypedArray typedArrayObtainStyledAttributes3 = iVar.obtainStyledAttributes(attributeSet, new int[]{R.attr.textColor, R.attr.textColorHint, R.attr.drawableStart, R.attr.drawableEnd, R.attr.drawableTint});
                int resourceId2 = typedArrayObtainStyledAttributes3.getResourceId(0, 0);
                int resourceId3 = typedArrayObtainStyledAttributes3.getResourceId(1, 0);
                int resourceId4 = typedArrayObtainStyledAttributes3.getResourceId(2, 0);
                int resourceId5 = typedArrayObtainStyledAttributes3.getResourceId(3, 0);
                int resourceId6 = typedArrayObtainStyledAttributes3.getResourceId(4, 0);
                TypedArray typedArrayObtainStyledAttributes4 = iVar.obtainStyledAttributes(attributeSet, b.c.a.b.uiStyleSkin);
                int resourceId7 = typedArrayObtainStyledAttributes4.getResourceId(0, 0);
                int resourceId8 = typedArrayObtainStyledAttributes4.getResourceId(1, 0);
                typedArrayObtainStyledAttributes4.recycle();
                if (resourceId2 != 0) {
                    try {
                        ((TextView) viewF).setTextColor(iVar.getColorStateList(resourceId2));
                    } catch (Resources.NotFoundException unused) {
                        ((TextView) viewF).setTextColor(iVar.getColor(resourceId2));
                    }
                }
                if (resourceId3 != 0) {
                    ((TextView) viewF).setHintTextColor(iVar.getColor(resourceId3));
                }
                if (resourceId7 != 0) {
                    ((TextView) viewF).setText(resourceId7);
                }
                if (resourceId8 != 0) {
                    ((TextView) viewF).setHint(resourceId8);
                }
                if (resourceId4 != 0) {
                    drawable = iVar.getDrawable(resourceId4);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                } else {
                    drawable = null;
                }
                if (resourceId5 != 0) {
                    drawable2 = iVar.getDrawable(resourceId5);
                    drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
                } else {
                    drawable2 = null;
                }
                ((TextView) viewF).setCompoundDrawables(drawable, null, drawable2, null);
                if ((viewF instanceof RadioButton) && resourceId6 != 0) {
                    ((RadioButton) viewF).setCompoundDrawableTintList(iVar.getColorStateList(resourceId6));
                }
                typedArrayObtainStyledAttributes3.recycle();
            } else {
                if (viewF instanceof ImageView) {
                    typedArrayObtainStyledAttributes = iVar.obtainStyledAttributes(attributeSet, new int[]{R.attr.src});
                    int resourceId9 = typedArrayObtainStyledAttributes.getResourceId(0, 0);
                    if (resourceId9 != 0) {
                        ((ImageView) viewF).setImageResource(resourceId9);
                    }
                } else if (viewF instanceof ProgressBar) {
                    TypedArray typedArrayObtainStyledAttributes5 = iVar.obtainStyledAttributes(attributeSet, new int[]{R.attr.progressDrawable});
                    int resourceId10 = typedArrayObtainStyledAttributes5.getResourceId(0, 0);
                    if (resourceId10 != 0) {
                        ((ProgressBar) viewF).setProgressDrawable(iVar.getDrawable(resourceId10));
                    }
                    typedArrayObtainStyledAttributes = typedArrayObtainStyledAttributes5;
                }
                typedArrayObtainStyledAttributes.recycle();
            }
        }
        return viewF;
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return null;
    }
}
