package spi;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.activity.result.ActivityResultCallback;
import androidx.core.view.inputmethod.InputConnectionCompat;
import androidx.core.view.inputmethod.InputContentInfoCompat;
import androidx.fragment.app.FragmentKt;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.ui.NavigationUI;
import com.byd.overseaappstore.ui.MainActivity;
import com.byd.overseaappstore.ui.vm.MainViewModel;
import com.google.android.material.navigation.NavigationBarView;
import java.util.ArrayList;
import java.util.Objects;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class sf implements InputConnectionCompat.OnCommitContentListener, FragmentResultListener, NavigationBarView.OnItemSelectedListener, ActivityResultCallback {
    public final /* synthetic */ Object a;

    public /* synthetic */ sf(Object obj) {
        this.a = obj;
    }

    @Override // androidx.activity.result.ActivityResultCallback
    public final void onActivityResult(Object obj) {
        MainActivity this$0 = (MainActivity) this.a;
        int i = MainActivity.s;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        yk ykVar = yk.a;
        Context context = this$0.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(context, "applicationContext");
        Objects.requireNonNull(ykVar);
        Intrinsics.checkNotNullParameter(context, "context");
        boolean zIsEmpty = ((ArrayList) ykVar.a(context)).isEmpty();
        MainViewModel mainViewModelS = this$0.s();
        Context applicationContext = this$0.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        boolean zF = mainViewModelS.f(applicationContext);
        ct.a("permissionWork launcherStartForResult permissionAllGranted:" + zIsEmpty + " protocolAgree:" + zF + " ", new Object[0]);
        this$0.o(this$0.s().a(zIsEmpty, zF), false);
    }

    @Override // androidx.core.view.inputmethod.InputConnectionCompat.OnCommitContentListener
    public final boolean onCommitContent(InputContentInfoCompat inputContentInfoCompat, int i, Bundle bundle) {
        return InputConnectionCompat.lambda$createOnCommitContentListenerUsingPerformReceiveContent$0((View) this.a, inputContentInfoCompat, i, bundle);
    }

    @Override // androidx.fragment.app.FragmentResultListener
    public final void onFragmentResult(String str, Bundle bundle) {
        FragmentKt.m15setFragmentResultListener$lambda0((Function2) this.a, str, bundle);
    }

    @Override // com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
    public final boolean onNavigationItemSelected(MenuItem menuItem) {
        return NavigationUI.m44setupWithNavController$lambda6((NavController) this.a, menuItem);
    }
}
