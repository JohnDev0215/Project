package com.amazon.music.signin;

import androidx.annotation.NonNull;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class SignInTaskId {
    private final String taskName;
    private int taskValue;
    public static final SignInTaskId SSO_SIGN_IN_START = new SignInTaskId("sso", 1);
    public static final SignInTaskId SIGN_IN_WITH_DIALOG_COMPLETE = new SignInTaskId("dialog complete", 2);
    public static final SignInTaskId ACCOUNT_DATA_SAVED = new SignInTaskId("account data saved", 3);
    public static final SignInTaskId SIGN_IN_TASKS_WITHOUT_DIALOG_START = new SignInTaskId("sign in flow tasks only", 4);
    public static final SignInTaskId SIGN_IN_WITH_RECOVER_ACCOUNT_START = new SignInTaskId("recover account start", 5);
    public static final SignInTaskId SIGN_IN_WITH_RECOVER_ACCOUNT_COMPLETE = new SignInTaskId("recover account complete", 6);
    public static final SignInTaskId SIGN_IN_WITH_NATIVE_SCREEN_COMPLETE = new SignInTaskId("native screen sign in complete", 7);
    public static final SignInTaskId SIGN_IN_CODE_INVALID = new SignInTaskId("please request a new code", 8);
    public static final SignInTaskId SIGN_IN_WITH_LINK_CODE_COMPLETE = new SignInTaskId("link code sign in complete", 9);

    protected SignInTaskId(@NonNull String str, int i) {
        Validate.notNull(str, "taskName cannot be null", new Object[0]);
        Validate.notNull(str, "taskValue cannot be null", new Object[0]);
        this.taskName = str;
        this.taskValue = i;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public int getTaskValue() {
        return this.taskValue;
    }
}
