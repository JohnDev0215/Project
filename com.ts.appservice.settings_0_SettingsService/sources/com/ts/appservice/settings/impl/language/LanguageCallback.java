package com.ts.appservice.settings.impl.language;

import android.content.Context;
import android.os.Message;
import android.os.RemoteException;
import com.ts.appservice.settings.callback.DoCallback;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.language.ILanguageSettingsCallback;

/* JADX INFO: loaded from: classes.dex */
public class LanguageCallback extends DoCallback<ILanguageSettingsCallback> {
    private static final String TAG = LanguageCallback.class.getSimpleName();

    protected LanguageCallback(Context context) {
        super(context);
    }

    @Override // com.ts.appservice.settings.callback.DoCallback
    protected String getLogTag() {
        return TAG;
    }

    @Override // com.ts.appservice.settings.callback.DoCallback
    protected void dequeueMessage(Message msg) {
        if (msg.what == 110) {
            int value = msg.arg1;
            callbackOnLanguageChanged(value);
            return;
        }
        LogUtil.verbose(TAG, "unhandle Message : " + msg.what);
    }

    public void onLanguageChanged(int value) {
        LogUtil.debug(TAG, "onLanguageChanged() value: " + value);
        Message msg = getMessage(110);
        msg.arg1 = value;
        enqueueMessage(msg);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private void callbackOnLanguageChanged(int value) {
        int n = this.mRemoteCallbacks.beginBroadcast();
        LogUtil.debug(TAG, "onLanguageChanged broadcast count : " + n + " value: " + value);
        for (int i = 0; i < n; i++) {
            try {
                this.mRemoteCallbacks.getBroadcastItem(i).onLanguageChanged(value);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
        this.mRemoteCallbacks.finishBroadcast();
    }
}
