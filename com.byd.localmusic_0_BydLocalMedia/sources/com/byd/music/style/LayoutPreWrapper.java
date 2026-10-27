package com.byd.music.style;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.LayoutInflaterCompat;
import com.byd.music.R;
import com.google.android.material.tabs.TabLayout;

/* JADX INFO: loaded from: classes5.dex */
public class LayoutPreWrapper {
    public static void setLayoutPreWrapper(final AppCompatActivity appCompatActivity) {
        final LayoutInflater layoutInflater = appCompatActivity.getLayoutInflater();
        LayoutInflaterCompat.setFactory2(layoutInflater, new LayoutInflater.Factory2() { // from class: com.byd.music.style.LayoutPreWrapper.1
            @Override // android.view.LayoutInflater.Factory
            public View onCreateView(String str, Context context, AttributeSet attributeSet) {
                return null;
            }

            @Override // android.view.LayoutInflater.Factory2
            public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
                return LayoutPreWrapper.parseView(appCompatActivity.getDelegate().createView(view, str, context, attributeSet), layoutInflater, view, str, appCompatActivity, attributeSet);
            }
        });
    }

    public static void setLayoutPreWrapper(final Context context, final LayoutInflater layoutInflater) {
        LayoutInflaterCompat.setFactory2(layoutInflater, new LayoutInflater.Factory2() { // from class: com.byd.music.style.LayoutPreWrapper.2
            @Override // android.view.LayoutInflater.Factory
            public View onCreateView(String str, Context context2, AttributeSet attributeSet) {
                return null;
            }

            @Override // android.view.LayoutInflater.Factory2
            public View onCreateView(View view, String str, Context context2, AttributeSet attributeSet) {
                return LayoutPreWrapper.parseView(null, layoutInflater, view, str, context, attributeSet);
            }
        });
    }

    public static void setLayoutPreWrapper(final Context context, LayoutInflater layoutInflater, final LayoutInflater layoutInflater2) {
        final LayoutInflater.Factory2 factory2 = layoutInflater.getFactory2();
        LayoutInflaterCompat.setFactory2(layoutInflater2, new LayoutInflater.Factory2() { // from class: com.byd.music.style.LayoutPreWrapper.3
            @Override // android.view.LayoutInflater.Factory
            public View onCreateView(String str, Context context2, AttributeSet attributeSet) {
                return null;
            }

            @Override // android.view.LayoutInflater.Factory2
            public View onCreateView(View view, String str, Context context2, AttributeSet attributeSet) {
                LayoutInflater.Factory2 factory22 = factory2;
                return LayoutPreWrapper.parseView(factory22 != null ? factory22.onCreateView(view, str, context2, attributeSet) : null, layoutInflater2, view, str, context, attributeSet);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static View parseView(View view, LayoutInflater layoutInflater, View view2, String str, Context context, AttributeSet attributeSet) {
        Drawable drawable;
        Drawable drawable2;
        if (view == null) {
            try {
                if (-1 == str.indexOf(46)) {
                    view = layoutInflater.onCreateView(context, view2, str, attributeSet);
                } else {
                    view = layoutInflater.createView(context, str, null, attributeSet);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (view != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{16842964});
            int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
            if (resourceId != 0) {
                view.setBackgroundResource(resourceId);
            }
            typedArrayObtainStyledAttributes.recycle();
            if (view instanceof TextView) {
                TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, new int[]{16842904, 16842906, 16843666, 16843667, 16843990});
                int resourceId2 = typedArrayObtainStyledAttributes2.getResourceId(0, 0);
                int resourceId3 = typedArrayObtainStyledAttributes2.getResourceId(1, 0);
                int resourceId4 = typedArrayObtainStyledAttributes2.getResourceId(2, 0);
                int resourceId5 = typedArrayObtainStyledAttributes2.getResourceId(3, 0);
                int resourceId6 = typedArrayObtainStyledAttributes2.getResourceId(4, 0);
                if (resourceId2 != 0) {
                    try {
                        ((TextView) view).setTextColor(context.getColorStateList(resourceId2));
                    } catch (Resources.NotFoundException unused) {
                        ((TextView) view).setTextColor(context.getColor(resourceId2));
                    }
                }
                if (resourceId3 != 0) {
                    ((TextView) view).setHintTextColor(context.getColor(resourceId3));
                }
                if (resourceId4 != 0) {
                    drawable = context.getDrawable(resourceId4);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                } else {
                    drawable = null;
                }
                if (resourceId5 != 0) {
                    drawable2 = context.getDrawable(resourceId5);
                    drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
                } else {
                    drawable2 = null;
                }
                ((TextView) view).setCompoundDrawables(drawable, null, drawable2, null);
                if ((view instanceof RadioButton) && resourceId6 != 0) {
                    ((RadioButton) view).setCompoundDrawableTintList(context.getColorStateList(resourceId6));
                }
                typedArrayObtainStyledAttributes2.recycle();
            } else if (view instanceof ImageView) {
                TypedArray typedArrayObtainStyledAttributes3 = context.obtainStyledAttributes(attributeSet, new int[]{16843033});
                int resourceId7 = typedArrayObtainStyledAttributes3.getResourceId(0, 0);
                if (resourceId7 != 0) {
                    ((ImageView) view).setImageResource(resourceId7);
                }
                typedArrayObtainStyledAttributes3.recycle();
            } else if (view instanceof TabLayout) {
                TypedArray typedArrayObtainStyledAttributes4 = context.obtainStyledAttributes(attributeSet, R.styleable.TabLayout);
                int resourceId8 = typedArrayObtainStyledAttributes4.getResourceId(R.styleable.TabLayout_tabIndicatorColor, 0);
                int resourceId9 = typedArrayObtainStyledAttributes4.getResourceId(R.styleable.TabLayout_tabTextColor, 0);
                int resourceId10 = typedArrayObtainStyledAttributes4.getResourceId(R.styleable.TabLayout_tabSelectedTextColor, 0);
                if (resourceId8 != 0) {
                    ((TabLayout) view).setSelectedTabIndicatorColor(context.getColor(resourceId8));
                }
                if (resourceId9 != 0 && resourceId10 != 0) {
                    ((TabLayout) view).setTabTextColors(context.getColor(resourceId9), context.getColor(resourceId10));
                }
                typedArrayObtainStyledAttributes4.recycle();
            } else if (view instanceof SeekBar) {
                TypedArray typedArrayObtainStyledAttributes5 = context.obtainStyledAttributes(attributeSet, new int[]{16843068});
                int resourceId11 = typedArrayObtainStyledAttributes5.getResourceId(0, 0);
                if (resourceId11 != 0) {
                    ((SeekBar) view).setProgressDrawable(context.getDrawable(resourceId11));
                }
                typedArrayObtainStyledAttributes5.recycle();
            }
        }
        return view;
    }
}
