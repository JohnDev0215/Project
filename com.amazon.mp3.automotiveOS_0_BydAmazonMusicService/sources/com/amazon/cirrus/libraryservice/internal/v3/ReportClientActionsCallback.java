package com.amazon.cirrus.libraryservice.internal.v3;

import com.amazon.cirrus.libraryservice.v3.ReportClientActionsResponse;
import com.amazon.cirrus.shared.model.AccountLockedException;
import com.amazon.cirrus.shared.model.AccountNotFoundException;
import com.amazon.cirrus.shared.model.AccountNotVerifiedException;
import com.amazon.cirrus.shared.model.AuthenticationException;
import com.amazon.cirrus.shared.model.InvalidBenefitsException;
import com.amazon.cirrus.shared.model.InvalidParameterException;
import com.amazon.cirrus.shared.model.InvalidTermsOfUseException;
import com.amazon.cirrus.shared.model.RequestThrottledException;
import com.amazon.cirrus.shared.model.ResourceNotFoundException;
import com.amazon.cirrus.shared.model.ServiceException;
import com.amazon.hermes.Callback;
import com.amazon.hermes.CoralError;
import com.amazon.hermes.UnhandledError;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ReportClientActionsCallback extends Callback<ReportClientActionsResponse> {
    public void onAccountLockedException(AccountLockedException accountLockedException) {
    }

    public void onAccountNotFoundException(AccountNotFoundException accountNotFoundException) {
    }

    public void onAccountNotVerifiedException(AccountNotVerifiedException accountNotVerifiedException) {
    }

    public void onAuthenticationException(AuthenticationException authenticationException) {
    }

    @Override // com.amazon.hermes.Callback
    public void onCoralError(CoralError coralError) {
        Throwable cause = coralError.getCause();
        if (cause instanceof ServiceException) {
            onServiceException((ServiceException) cause);
            return;
        }
        if (cause instanceof InvalidParameterException) {
            onInvalidParameterException((InvalidParameterException) cause);
            return;
        }
        if (cause instanceof InvalidTermsOfUseException) {
            onInvalidTermsOfUseException((InvalidTermsOfUseException) cause);
            return;
        }
        if (cause instanceof AccountNotFoundException) {
            onAccountNotFoundException((AccountNotFoundException) cause);
            return;
        }
        if (cause instanceof ResourceNotFoundException) {
            onResourceNotFoundException((ResourceNotFoundException) cause);
            return;
        }
        if (cause instanceof AccountNotVerifiedException) {
            onAccountNotVerifiedException((AccountNotVerifiedException) cause);
            return;
        }
        if (cause instanceof AuthenticationException) {
            onAuthenticationException((AuthenticationException) cause);
            return;
        }
        if (cause instanceof InvalidBenefitsException) {
            onInvalidBenefitsException((InvalidBenefitsException) cause);
            return;
        }
        if (cause instanceof RequestThrottledException) {
            onRequestThrottledException((RequestThrottledException) cause);
        } else if (cause instanceof AccountLockedException) {
            onAccountLockedException((AccountLockedException) cause);
        } else {
            onUnhandledError(new UnhandledError(cause));
        }
    }

    public void onInvalidBenefitsException(InvalidBenefitsException invalidBenefitsException) {
    }

    public void onInvalidParameterException(InvalidParameterException invalidParameterException) {
    }

    public void onInvalidTermsOfUseException(InvalidTermsOfUseException invalidTermsOfUseException) {
    }

    public void onRequestThrottledException(RequestThrottledException requestThrottledException) {
    }

    public void onResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
    }

    public void onServiceException(ServiceException serviceException) {
    }
}
