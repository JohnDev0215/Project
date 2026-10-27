package com.amplifyframework.api.aws;

import androidx.annotation.NonNull;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.model.AuthRule;
import com.amplifyframework.core.model.ModelField;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.ModelSchema;
import com.amplifyframework.core.model.auth.AuthorizationTypeIterator;
import com.amplifyframework.core.model.auth.MultiAuthorizationTypeIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class MultiAuthModeStrategy implements AuthModeStrategy {
    private static MultiAuthModeStrategy instance;

    private MultiAuthModeStrategy() {
    }

    public static synchronized MultiAuthModeStrategy getInstance() {
        if (instance == null) {
            instance = new MultiAuthModeStrategy();
        }
        return instance;
    }

    @Override // com.amplifyframework.api.aws.AuthModeStrategy
    public AuthorizationTypeIterator authTypesFor(@NonNull ModelSchema modelSchema, @NonNull final ModelOperation modelOperation) {
        final ArrayList arrayList = new ArrayList();
        Consumer consumer = new Consumer() { // from class: com.amplifyframework.api.aws.MultiAuthModeStrategy$$ExternalSyntheticLambda0
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                MultiAuthModeStrategy.lambda$authTypesFor$0(modelOperation, arrayList, (List) obj);
            }
        };
        consumer.accept(modelSchema.getAuthRules());
        Iterator<ModelField> it = modelSchema.getFields().values().iterator();
        while (it.hasNext()) {
            consumer.accept(it.next().getAuthRules());
        }
        return new MultiAuthorizationTypeIterator(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$authTypesFor$0(ModelOperation modelOperation, List list, List list2) {
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            AuthRule authRule = (AuthRule) it.next();
            if (authRule.getOperationsOrDefault().contains(modelOperation)) {
                list.add(authRule);
            }
        }
    }
}
