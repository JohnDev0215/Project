package com.amazon.avod.service;

import com.amazon.avod.core.AVODRemoteException;
import com.amazon.avod.http.HttpStatusCodeException;
import com.amazon.bolthttp.policy.impl.SimpleRetryPolicy;
import com.google.android.gms.cast.MediaError;
import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class ResponseRetryPolicy extends SimpleRetryPolicy {
    private static final Set<Class<? extends Exception>> EMPTY_SET = new HashSet();
    private static final Set<Integer> NON_RETRIABLE_HTTP_CODES = Sets.newHashSet(304, 400, 404, 405, 406, 409, 410, Integer.valueOf(MediaError.DetailedErrorCode.HLS_MANIFEST_MASTER), Integer.valueOf(MediaError.DetailedErrorCode.HLS_MANIFEST_PLAYLIST), 413, 414, 415, 417, Integer.valueOf(MediaError.DetailedErrorCode.DASH_MANIFEST_NO_PERIODS), Integer.valueOf(MediaError.DetailedErrorCode.DASH_MANIFEST_NO_MIMETYPE), 426, 429, Integer.valueOf(MediaError.DetailedErrorCode.SMOOTH_MANIFEST), 451);

    public ResponseRetryPolicy(int i, long j) {
        super(i, j, EMPTY_SET);
    }

    @Override // com.amazon.bolthttp.policy.impl.SimpleRetryPolicy
    protected boolean isRetryableException(Exception exc) {
        if (exc instanceof HttpStatusCodeException) {
            return !NON_RETRIABLE_HTTP_CODES.contains(Integer.valueOf(((HttpStatusCodeException) exc).getStatusCode()));
        }
        if (exc instanceof AVODRemoteException) {
            return "CDP.Authorization".equals(((AVODRemoteException) exc).getErrorCode());
        }
        return false;
    }
}
