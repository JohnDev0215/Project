package com.amplifyframework.api;

import androidx.annotation.NonNull;
import com.amplifyframework.AmplifyException;

/* JADX INFO: loaded from: classes5.dex */
public class ApiException extends AmplifyException {
    private static final long serialVersionUID = 1;

    public ApiException(@NonNull String str, Throwable th, @NonNull String str2) {
        super(str, th, str2);
    }

    public ApiException(@NonNull String str, @NonNull String str2) {
        super(str, str2);
    }

    public static final class NonRetryableException extends ApiException {
        private static final long serialVersionUID = 1;

        public NonRetryableException(@NonNull String str, String str2) {
            super(str, str2);
        }
    }

    public static final class ApiAuthException extends ApiException {
        private static final long serialVersionUID = 1;

        public ApiAuthException(@NonNull String str, Throwable th, @NonNull String str2) {
            super(str, th, str2);
        }

        public ApiAuthException(@NonNull String str, @NonNull String str2) {
            super(str, str2);
        }
    }
}
