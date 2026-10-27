package com.android.car.developeroptions.homepage.contextualcards;

import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import android.text.TextUtils;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.intelligence.ContextualCardProto$ContextualCard;
import com.android.car.developeroptions.intelligence.ContextualCardProto$ContextualCardList;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public class ContextualCardFeatureProviderImpl implements ContextualCardFeatureProvider {
    private final Context mContext;

    public ContextualCardFeatureProviderImpl(Context context) {
        this.mContext = context;
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.ContextualCardFeatureProvider
    public void logHomepageDisplay(long j) {
        sendBroadcast(new Intent().putExtra("type", 38).putExtra("latency", j));
    }

    void sendBroadcast(Intent intent) {
        intent.setPackage(this.mContext.getString(R.string.config_settingsintelligence_package_name));
        String string = this.mContext.getString(R.string.config_settingsintelligence_log_action);
        if (TextUtils.isEmpty(string)) {
            return;
        }
        intent.setAction(string);
        this.mContext.sendBroadcastAsUser(intent, UserHandle.CURRENT);
    }

    byte[] serialize(List<ContextualCard> list) {
        final ContextualCardProto$ContextualCardList.Builder builderNewBuilder = ContextualCardProto$ContextualCardList.newBuilder();
        list.stream().forEach(new Consumer() { // from class: com.android.car.developeroptions.homepage.contextualcards.-$$Lambda$ContextualCardFeatureProviderImpl$SGm7uKkyOopw9zolViPymFpkY9o
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ContextualCardFeatureProviderImpl.lambda$serialize$0(builderNewBuilder, (ContextualCard) obj);
            }
        });
        return builderNewBuilder.build().toByteArray();
    }

    static /* synthetic */ void lambda$serialize$0(ContextualCardProto$ContextualCardList.Builder builder, ContextualCard contextualCard) {
        ContextualCardProto$ContextualCard.Builder builderNewBuilder = ContextualCardProto$ContextualCard.newBuilder();
        builderNewBuilder.setSliceUri(contextualCard.getSliceUri().toString());
        builderNewBuilder.setCardName(contextualCard.getName());
        builderNewBuilder.setCardScore(contextualCard.getRankingScore());
        builder.addCard(builderNewBuilder.build());
    }
}
