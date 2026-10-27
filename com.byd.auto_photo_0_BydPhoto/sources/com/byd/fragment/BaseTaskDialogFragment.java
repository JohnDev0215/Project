package com.byd.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import com.byd.taskmanager.b.a;
import com.byd.taskmanager.b.c;
import com.byd.ui.b;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseTaskDialogFragment extends BaseDialogFragment {
    private b uiTaskController;

    public <T> BaseTaskDialogFragment addTask(int i, a<T> aVar, c<T> cVar) {
        this.uiTaskController.b(this, i, aVar, cVar, (this.mainLayout == null || cVar == null) ? false : true);
        return this;
    }

    public void cancel(int i) {
        this.uiTaskController.c(i);
    }

    public void executeTask(int i) {
        this.uiTaskController.d(i);
    }

    public a getTask(int i) {
        return this.uiTaskController.e(i);
    }

    public boolean isTaskExist(int i) {
        return this.uiTaskController.f(i);
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.uiTaskController = new b();
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewOnCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.uiTaskController.g(this);
        return viewOnCreateView;
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        this.uiTaskController.h();
        super.onDestroy();
        this.uiTaskController = null;
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.uiTaskController.i();
        super.onDestroyView();
    }
}
