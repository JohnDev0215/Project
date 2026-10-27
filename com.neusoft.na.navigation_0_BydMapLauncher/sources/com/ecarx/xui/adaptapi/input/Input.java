package com.ecarx.xui.adaptapi.input;

import android.content.Context;
import com.ecarx.xui.adaptapi.sign.SignVerify;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Input extends AdaptAPI {
    public abstract boolean abandonKeysInterception(IKeyCallback iKeyCallback);

    public abstract int[] requestKeysInterception(int[] iArr, IKeyCallback iKeyCallback);

    public static Input create(Context context) {
        if (SignVerify.instance().appVerify(context)) {
            return new KeyInput(context);
        }
        return null;
    }
}
