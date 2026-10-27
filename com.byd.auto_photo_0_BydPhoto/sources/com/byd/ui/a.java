package com.byd.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.byd.taskmanager.b.c;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a extends Fragment {
    private View mainView;
    protected boolean releaseOnDestroy = true;
    private b uiTaskController;

    public <T> a addTask(int i, com.byd.taskmanager.b.a<T> aVar, c<T> cVar) {
        this.uiTaskController.b(this, i, aVar, cVar, (this.mainView == null || cVar == null) ? false : true);
        return this;
    }

    public void cancelTask(int i) {
        b bVar = this.uiTaskController;
        if (bVar == null) {
            return;
        }
        bVar.c(i);
    }

    public void executeTask(int i) {
        this.uiTaskController.d(i);
    }

    protected <T extends View> T findView(int i) {
        return (T) this.mainView.findViewById(i);
    }

    protected abstract int getLayout();

    /* JADX INFO: Access modifiers changed from: protected */
    public com.byd.taskmanager.b.a getTask(int i) {
        return this.uiTaskController.e(i);
    }

    public boolean isTaskExist(int i) {
        return this.uiTaskController.f(i);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.uiTaskController = new b();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mainView = layoutInflater.inflate(getLayout(), viewGroup, false);
        this.uiTaskController.g(this);
        this.releaseOnDestroy = true;
        return this.mainView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        if (this.releaseOnDestroy) {
            this.uiTaskController.h();
        }
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.mainView = null;
        this.uiTaskController.i();
        super.onDestroyView();
    }

    public void setReleaseOnDestroy(boolean z) {
        this.releaseOnDestroy = z;
    }
}
