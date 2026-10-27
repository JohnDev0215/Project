package com.amazon.mp3.feed;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.reactnative.AmazonReactActivity;
import com.amazon.mp3.reactnative.AmazonReactFragment;
import com.amazon.mp3.reactnative.ReactInitializer;
import com.amazon.mp3.reactnative.ReactInitializerRegistry;
import com.amazon.music.connect.feed.DefaultReactIdentityProvider;
import com.amazon.music.connect.feed.DefaultToolbarProvider;
import com.amazon.music.inappmessaging.internal.model.Splash;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class ActivityFeedInitializer {
    private final String INITIAL_PROP_LOCALE = Splash.PARAMS_LOCALE;

    public void startActivityFeed(FragmentActivity hostActivity) {
        hostActivity.startActivity(getReactActivityIntent(hostActivity));
    }

    public Fragment getReactFragment(FragmentActivity activity) {
        Bundle bundle = new Bundle();
        bundle.putString(Splash.PARAMS_LOCALE, Locale.getDefault().toString());
        ReactInitializer reactInitializer = new ReactInitializer(activity.getApplication());
        reactInitializer.setIdentityProvider(new DefaultReactIdentityProvider(activity));
        Map<ReactInitializerRegistry.Key, ReactInitializer> map = ReactInitializerRegistry.Registry;
        ReactInitializerRegistry.Key key = ReactInitializerRegistry.Key.FEED;
        map.put(key, reactInitializer);
        return new AmazonReactFragment.Builder().setComponentName("Feed").setLaunchOptions(bundle).setReactInitializerRegistryKey(key).build();
    }

    private Intent getReactActivityIntent(FragmentActivity activity) {
        Intent intent = new Intent(activity, (Class<?>) AmazonReactActivity.class);
        Toolbar toolbar = (Toolbar) LayoutInflater.from(activity).inflate(R.layout.activity_feed_react_toolbar, (ViewGroup) null);
        ((TextView) toolbar.findViewById(R.id.react_toolbar_title)).setText(R.string.activity_feed_title);
        ReactInitializer reactInitializer = new ReactInitializer(activity.getApplication());
        reactInitializer.setIdentityProvider(new DefaultReactIdentityProvider(activity));
        reactInitializer.setToolbarProvider(new DefaultToolbarProvider(toolbar));
        Map<ReactInitializerRegistry.Key, ReactInitializer> map = ReactInitializerRegistry.Registry;
        ReactInitializerRegistry.Key key = ReactInitializerRegistry.Key.FEED;
        map.put(key, reactInitializer);
        Bundle bundle = new Bundle();
        bundle.putSerializable(AmazonReactActivity.ReactInitializerNameExtra, key);
        bundle.putString(AmazonReactActivity.ModuleNameExtra, "Feed");
        Bundle bundle2 = new Bundle();
        bundle2.putString(Splash.PARAMS_LOCALE, Locale.getDefault().toString());
        bundle.putBundle(AmazonReactActivity.InitialPropsExtra, bundle2);
        intent.putExtras(bundle);
        return intent;
    }
}
