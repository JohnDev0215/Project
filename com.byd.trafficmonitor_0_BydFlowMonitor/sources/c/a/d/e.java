package c.a.d;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.fragment.app.DialogFragment;
import com.bydux.api.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;
import java.lang.reflect.Field;

/* JADX INFO: compiled from: BaseDialogFragment.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class e extends DialogFragment {
    public final int p = 200;
    public Dialog q;
    public View r;
    public View s;
    public c.a.d.m.c t;
    public c.a.d.m.b u;

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        n(1, R.style.DialogFullScreen);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(q(), viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        c.a.d.m.b bVar = this.u;
        if (bVar != null) {
            bVar.a();
            this.u = null;
        }
        c.a.d.m.c cVar = this.t;
        if (cVar != null) {
            cVar.a();
            this.t = null;
        }
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public LayoutInflater onGetLayoutInflater(Bundle bundle) {
        boolean zR = r();
        LayoutInflater layoutInflaterOnGetLayoutInflater = super.onGetLayoutInflater(bundle);
        if (zR) {
            BydLogHelper.logD("反射设置DialogFragment 成功！");
        } else {
            s();
            BydLogHelper.logD("反射设置DialogFragment 失败！尝试设置Dialog监听");
        }
        return layoutInflaterOnGetLayoutInflater;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialogG = g();
        this.q = dialogG;
        if (dialogG != null) {
            Window window = dialogG.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.dimAmount = 0.0f;
            window.addFlags(Integer.MIN_VALUE);
            window.clearFlags(67108864);
            window.setStatusBarColor(0);
            window.getDecorView().setSystemUiVisibility(1792);
            window.setAttributes(attributes);
        }
    }

    public abstract int q();

    public final boolean r() {
        if (this.t == null) {
            this.t = new c.a.d.m.c(this);
        }
        if (this.u == null) {
            this.u = new c.a.d.m.b(this);
        }
        try {
            Class<?> cls = Class.forName("androidx.fragment.app.DialogFragment");
            Field declaredField = cls.getDeclaredField("mOnCancelListener");
            declaredField.setAccessible(true);
            declaredField.set(this, this.u);
            Field declaredField2 = cls.getDeclaredField("mOnDismissListener");
            declaredField2.setAccessible(true);
            declaredField2.set(this, this.t);
            return true;
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return false;
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
            BydLogHelper.logE("dialog 反射替换失败：不允许访问:" + e3);
            return false;
        } catch (NoSuchFieldException e4) {
            e4.printStackTrace();
            BydLogHelper.logE("dialog 反射替换失败：未找到变量:" + e4);
            return false;
        }
    }

    public final void s() {
        Dialog dialogG = g();
        if (dialogG != null) {
            dialogG.setOnCancelListener(this.u);
            dialogG.setOnDismissListener(this.t);
        }
    }

    public void t(View view, View view2) {
        this.r = view;
        this.s = view2;
        view.animate().alpha(1.0f).setDuration(200L).setInterpolator(PathInterpolatorUtils.getStandard()).start();
        this.s.animate().scaleX(1.0f).scaleY(1.0f).setDuration(200L).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).start();
        this.s.animate().alpha(1.0f).setDuration(200L).setInterpolator(PathInterpolatorUtils.getStandard()).start();
    }
}
