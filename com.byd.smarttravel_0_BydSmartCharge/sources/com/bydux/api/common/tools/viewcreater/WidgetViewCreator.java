package com.bydux.api.common.tools.viewcreater;

import android.app.Application;
import android.content.Context;
import android.view.ViewGroup;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class WidgetViewCreator {
    private static final String TAG = "WidgetViewCreator";
    private Class<?> btClass;
    private Object btObject;
    private Application mApplication;
    private Context mContext;
    private ViewGroup mView;
    private Context resContext;

    public void create(Application application, String str, String str2, Context context) {
        if (this.mView == null || this.btClass == null || this.btObject == null) {
            try {
                this.mApplication = application;
                Context contextCreatePackageContext = context.createPackageContext(str, 3);
                Class<?> clsLoadClass = contextCreatePackageContext.getClassLoader().loadClass(str2);
                this.btClass = clsLoadClass;
                this.btObject = clsLoadClass.newInstance();
                this.mContext = contextCreatePackageContext;
                this.resContext = context;
            } catch (Throwable th) {
                BydLogHelper.logE(TAG, "create", th.getMessage());
            }
        }
    }

    public ViewGroup getView(String str) {
        ViewGroup viewGroup = this.mView;
        if (viewGroup != null) {
            return viewGroup;
        }
        try {
            this.mView = (ViewGroup) this.btClass.getDeclaredMethod(str, Context.class, Application.class, Context.class).invoke(this.btObject, this.mContext, this.mApplication, this.resContext);
        } catch (Throwable th) {
            BydLogHelper.logE(TAG, "getView", th.getMessage());
        }
        return this.mView;
    }
}
