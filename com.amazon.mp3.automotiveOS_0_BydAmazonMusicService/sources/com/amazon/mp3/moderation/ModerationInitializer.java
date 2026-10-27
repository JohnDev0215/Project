package com.amazon.mp3.moderation;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.feed.FeedViewModelProvider;
import com.amazon.mp3.reactnative.AmazonReactActivity;
import com.amazon.mp3.reactnative.ReactInitializer;
import com.amazon.mp3.reactnative.ReactInitializerRegistry;
import com.amazon.music.connect.feed.DefaultReactIdentityProvider;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class ModerationInitializer {
    private final Bundle entityData;

    public ModerationInitializer(Bundle entityData) {
        this.entityData = entityData;
    }

    public void startModeration(FragmentActivity hostActivity) {
        hostActivity.startActivity(getReactActivityIntent(hostActivity));
    }

    private Intent getReactActivityIntent(FragmentActivity activity) {
        Intent intent = new Intent(activity, (Class<?>) AmazonReactActivity.class);
        ReactInitializer reactInitializer = new ReactInitializer(activity.getApplication());
        new FeedViewModelProvider(activity).get();
        reactInitializer.setIdentityProvider(new DefaultReactIdentityProvider(activity));
        Map<ReactInitializerRegistry.Key, ReactInitializer> map = ReactInitializerRegistry.Registry;
        ReactInitializerRegistry.Key key = ReactInitializerRegistry.Key.MODERATION;
        map.put(key, reactInitializer);
        Bundle bundle = new Bundle();
        bundle.putSerializable(AmazonReactActivity.ReactInitializerNameExtra, key);
        bundle.putString(AmazonReactActivity.ModuleNameExtra, "Moderation");
        bundle.putBundle(AmazonReactActivity.InitialPropsExtra, this.entityData);
        intent.putExtras(bundle);
        return intent;
    }
}
