package b.b.a.h;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.byd.base.view.BydRelativeLayout;
import com.byd.base.view.BydSwitch;
import com.byd.base.view.BydTouchImageView;
import com.byd.base.view.segmentedbutton.SegmentedButtonGroup;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.window.DampingScrollView;

/* JADX INFO: compiled from: ViewListenerUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class l {
    public static void A(TextView textView, Context context, int i) {
        String strA = h.a(context, i);
        B(textView, strA);
        a.b(l.class, " string : " + strA);
    }

    public static void B(TextView textView, String str) {
        if (textView != null) {
            textView.setText(str);
        }
    }

    public static void C(boolean z, View... viewArr) {
        if (viewArr == null || viewArr.length <= 0) {
            return;
        }
        for (View view : viewArr) {
            if (view != null) {
                if (z) {
                    k.h(view);
                } else {
                    k.e(view);
                }
            }
        }
    }

    public static void D(float f2, View... viewArr) {
        for (View view : viewArr) {
            if (view != null) {
                view.setAlpha(f2);
                view.setClickable(false);
                view.setSoundEffectsEnabled(false);
            }
        }
    }

    public static void E(CompoundButton.OnCheckedChangeListener onCheckedChangeListener, CheckBox... checkBoxArr) {
        if (checkBoxArr == null || checkBoxArr.length <= 0) {
            return;
        }
        for (CheckBox checkBox : checkBoxArr) {
            if (checkBox != null) {
                checkBox.setOnCheckedChangeListener(onCheckedChangeListener);
            }
        }
    }

    public static void F(View.OnClickListener onClickListener, View... viewArr) {
        if (viewArr == null || viewArr.length <= 0) {
            return;
        }
        for (View view : viewArr) {
            if (view != null) {
                view.setOnClickListener(onClickListener);
            }
        }
    }

    public static void G(View.OnLongClickListener onLongClickListener, View... viewArr) {
        if (viewArr == null || viewArr.length <= 0) {
            return;
        }
        for (View view : viewArr) {
            if (view != null) {
                view.setOnLongClickListener(onLongClickListener);
            }
        }
    }

    public static void H(boolean z, float f2, View... viewArr) {
        for (View view : viewArr) {
            if (view != null) {
                view.setAlpha(f2);
                view.setClickable(z);
                view.setSoundEffectsEnabled(z);
            }
        }
    }

    public static void I(float f2, View... viewArr) {
        for (View view : viewArr) {
            if (view != null) {
                view.setAlpha(f2);
                view.setClickable(true);
                view.setSoundEffectsEnabled(true);
            }
        }
    }

    public static void J(int i, View... viewArr) {
        if (viewArr == null || viewArr.length <= 0) {
            return;
        }
        for (View view : viewArr) {
            if (view != null) {
                view.setVisibility(i);
            }
        }
    }

    public static void K(boolean z, View... viewArr) {
        if (viewArr == null || viewArr.length <= 0) {
            return;
        }
        for (View view : viewArr) {
            if (view != null) {
                view.setVisibility(z ? 0 : 8);
            }
        }
    }

    public static boolean a(BydSwitch bydSwitch) {
        if (bydSwitch != null) {
            return bydSwitch.e();
        }
        return false;
    }

    public static void b(View view, Context context, int i) {
        if (view == null || context == null) {
            return;
        }
        view.setBackground(ContextCompat.getDrawable(context, i));
    }

    public static void c(View view, Drawable drawable) {
        if (view != null) {
            view.setBackground(drawable);
        }
    }

    public static void d(View view, BydSwitch bydSwitch, boolean z, CompoundButton.OnCheckedChangeListener onCheckedChangeListener, View view2, View.OnClickListener onClickListener, boolean z2) {
        if (z2) {
            K(true, view);
            if (bydSwitch != null) {
                bydSwitch.setChecked(z, false);
            }
            y(onCheckedChangeListener, bydSwitch);
            if (view2 != null) {
                F(onClickListener, view2);
            }
        }
    }

    public static void e(View view, BydSwitch bydSwitch, boolean z, CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        K(true, view);
        if (bydSwitch != null) {
            bydSwitch.setChecked(z, false);
        }
        y(onCheckedChangeListener, bydSwitch);
    }

    public static void f(BydSwitch bydSwitch, boolean z) {
        g(bydSwitch, z, false);
    }

    public static void g(BydSwitch bydSwitch, boolean z, boolean z2) {
        if (bydSwitch != null) {
            bydSwitch.setChecked(z, z2);
        }
    }

    public static void h(CheckBox checkBox, boolean z) {
        if (checkBox != null) {
            checkBox.setChecked(z);
        }
    }

    public static void i(CheckBox checkBox, int i) {
        if (checkBox != null) {
            checkBox.setTextColor(i);
        }
    }

    public static void j(ImageView imageView, int i, Context context) {
        l(imageView, i, context, true, true);
    }

    public static void k(ImageView imageView, int i, Context context, boolean z) {
        l(imageView, i, context, true, z);
    }

    public static void l(ImageView imageView, int i, Context context, boolean z, boolean z2) {
        if (imageView == null || context == null || !z) {
            return;
        }
        if (b.b.a.i.a.f() && z2) {
            imageView.setRotationY(180.0f);
        } else {
            imageView.setRotationY(0.0f);
        }
        imageView.setBackground(context.getResources().getDrawable(i));
    }

    public static void m(ImageView imageView, Drawable drawable, Context context, boolean z) {
        if (imageView != null) {
            if (z) {
                imageView.setRotationY(180.0f);
            }
            try {
                imageView.setImageDrawable(drawable);
            } catch (Exception e2) {
                BydLogHelper.logE(e2.getMessage());
            }
        }
    }

    public static void n(boolean z, BydRelativeLayout... bydRelativeLayoutArr) {
        for (BydRelativeLayout bydRelativeLayout : bydRelativeLayoutArr) {
            if (bydRelativeLayout != null) {
                bydRelativeLayout.setDispatch(z);
            }
        }
    }

    public static void o(BydTouchImageView.c cVar, BydTouchImageView... bydTouchImageViewArr) {
        if (bydTouchImageViewArr == null || bydTouchImageViewArr.length <= 0) {
            return;
        }
        for (BydTouchImageView bydTouchImageView : bydTouchImageViewArr) {
            if (bydTouchImageView != null) {
                bydTouchImageView.setIvTouchListener(cVar);
            }
        }
    }

    public static void p(RadioGroup.OnCheckedChangeListener onCheckedChangeListener, RadioGroup... radioGroupArr) {
        if (radioGroupArr == null || radioGroupArr.length <= 0) {
            return;
        }
        for (RadioGroup radioGroup : radioGroupArr) {
            if (radioGroup != null) {
                radioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
            }
        }
    }

    public static void q(SeekBar seekBar, int i) {
        if (seekBar != null) {
            seekBar.setProgress(i);
        }
    }

    public static void r(boolean z, DampingScrollView... dampingScrollViewArr) {
        for (DampingScrollView dampingScrollView : dampingScrollViewArr) {
            if (dampingScrollView != null) {
                dampingScrollView.setScroll(z);
            }
        }
    }

    public static void s(SeekBar.OnSeekBarChangeListener onSeekBarChangeListener, SeekBar... seekBarArr) {
        if (seekBarArr == null || seekBarArr.length <= 0) {
            return;
        }
        for (SeekBar seekBar : seekBarArr) {
            if (seekBar != null) {
                seekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
            }
        }
    }

    public static void t(SegmentedButtonGroup.f fVar, SegmentedButtonGroup... segmentedButtonGroupArr) {
        if (segmentedButtonGroupArr == null || segmentedButtonGroupArr.length <= 0) {
            return;
        }
        for (SegmentedButtonGroup segmentedButtonGroup : segmentedButtonGroupArr) {
            if (segmentedButtonGroup != null) {
                segmentedButtonGroup.setOnPositionChangedListener(fVar);
            }
        }
    }

    public static void u(View view, SegmentedButtonGroup segmentedButtonGroup, int i, SegmentedButtonGroup.f fVar) {
        K(true, view);
        if (segmentedButtonGroup != null) {
            segmentedButtonGroup.setPositionWithOutCallback(i, false);
        }
        t(fVar, segmentedButtonGroup);
    }

    public static void v(View view, SegmentedButtonGroup segmentedButtonGroup, int i, SegmentedButtonGroup.f fVar, View view2, View.OnClickListener onClickListener, boolean z) {
        K(z, view);
        if (z) {
            if (segmentedButtonGroup != null) {
                segmentedButtonGroup.setPositionWithOutCallback(i, false);
            }
            t(fVar, segmentedButtonGroup);
            if (view2 != null) {
                F(onClickListener, view2);
            }
        }
    }

    public static void w(SegmentedButtonGroup segmentedButtonGroup, int i) {
        if (segmentedButtonGroup != null) {
            segmentedButtonGroup.setPositionWithOutCallback(i, false);
        }
    }

    public static void x(TextView textView, boolean z) {
        if (textView != null) {
            textView.setSelected(z);
        }
    }

    public static void y(CompoundButton.OnCheckedChangeListener onCheckedChangeListener, BydSwitch... bydSwitchArr) {
        if (bydSwitchArr == null || bydSwitchArr.length <= 0) {
            return;
        }
        for (BydSwitch bydSwitch : bydSwitchArr) {
            if (bydSwitch != null) {
                bydSwitch.setOnCheckedChangeListener(onCheckedChangeListener);
            }
        }
    }

    public static void z(int i, TextView textView, Context context, int i2, String str) {
        String str2 = String.format(h.a(context, i2), i + str);
        B(textView, str2);
        a.b(l.class, " string : " + str2 + " progress : " + i);
    }
}
