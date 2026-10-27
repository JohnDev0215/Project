package spi;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.constraintlayout.motion.widget.ViewTransition;
import androidx.fragment.app.strictmode.FragmentStrictMode;
import androidx.fragment.app.strictmode.Violation;
import androidx.window.embedding.ExtensionEmbeddingBackend;
import com.byd.overseaappstore.module.ability.util.SettingUtil;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class kv implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;

    public /* synthetic */ kv(Object obj, Object obj2, int i) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                ((ViewTransition) this.b).lambda$applyTransition$0((View[]) this.c);
                break;
            case 1:
                FragmentStrictMode.m21handlePolicyViolation$lambda1((String) this.b, (Violation) this.c);
                break;
            case 2:
                ExtensionEmbeddingBackend.SplitListenerWrapper.m53accept$lambda1((ExtensionEmbeddingBackend.SplitListenerWrapper) this.b, (List) this.c);
                break;
            default:
                View view = (View) this.b;
                InputMethodManager manager = (InputMethodManager) this.c;
                SettingUtil settingUtil = SettingUtil.a;
                Intrinsics.checkNotNullParameter(view, "$view");
                Intrinsics.checkNotNullParameter(manager, "$manager");
                view.requestFocus();
                manager.showSoftInput(view, 0);
                break;
        }
    }
}
