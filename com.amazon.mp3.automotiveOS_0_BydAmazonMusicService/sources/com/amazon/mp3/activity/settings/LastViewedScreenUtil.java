package com.amazon.mp3.activity.settings;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.mp3.AmazonApplication;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class LastViewedScreenUtil {
    private static Set<OnLastViewedSourceChangedListener> mOnLastViewedSourceChangedListeners = new HashSet();

    public enum LastViewedScreen {
        LIBRARY,
        STORE,
        NOW_PLAYING,
        PRIME_MUSIC,
        SEARCH,
        ALEXA
    }

    public interface OnLastViewedSourceChangedListener {
        void onLastViewChanged();
    }

    public enum LastViewedSource {
        CLOUD("cirrus"),
        DEVICE("cirrus-local");

        private final String mValue;

        LastViewedSource(final String value) {
            this.mValue = value;
        }

        public String toCirrusMediaSource() {
            return this.mValue;
        }

        public static LastViewedSource fromCirrusMediaSource(final String source) {
            for (LastViewedSource lastViewedSource : values()) {
                if (lastViewedSource.toCirrusMediaSource().equals(source)) {
                    return lastViewedSource;
                }
            }
            return CLOUD;
        }
    }

    private static SharedPreferences getSharedPreference(Context context) {
        return context.getSharedPreferences("com.amazon.mp3_LastViewedScreenUtil", 4);
    }

    public static void registerViewSourceChangeListener(OnLastViewedSourceChangedListener lastViewedSourceChangedListener) {
        mOnLastViewedSourceChangedListeners.add(lastViewedSourceChangedListener);
    }

    public static void removeViewSourceChangeListener(OnLastViewedSourceChangedListener lastViewedSourceChangedListener) {
        if (mOnLastViewedSourceChangedListeners.contains(lastViewedSourceChangedListener)) {
            mOnLastViewedSourceChangedListeners.remove(lastViewedSourceChangedListener);
        }
    }

    public static LastViewedScreen getLastViewedScreen(Context context) {
        LastViewedScreen lastViewedScreen;
        String string = getSharedPreference(context).getString("lastViewedScreen", null);
        if (!AmazonApplication.getCapabilities().shouldBrowseBeSupported()) {
            lastViewedScreen = LastViewedScreen.LIBRARY;
        } else {
            lastViewedScreen = LastViewedScreen.PRIME_MUSIC;
        }
        if (string == null) {
            return lastViewedScreen;
        }
        try {
            return LastViewedScreen.valueOf(string);
        } catch (IllegalArgumentException unused) {
            return lastViewedScreen;
        }
    }

    public static LastViewedSource getLastViewedSource(Context context) {
        return getLastViewedSource(context, null);
    }

    public static LastViewedSource getLastViewedSource(Context context, LastViewedSource defaultSource) {
        String string = getSharedPreference(context).getString("lastViewedSource", null);
        return string == null ? defaultSource : LastViewedSource.valueOf(string);
    }

    public static boolean isSourceLocal(final Context context) {
        return "cirrus-local".equals(getLastViewedSource(context, LastViewedSource.CLOUD).toCirrusMediaSource());
    }

    public static void setLastViewed(Context context, LastViewedScreen lastScreen) {
        setLastViewed(context, lastScreen, null);
    }

    public static void setLastViewedSource(Context context, LastViewedSource lastSource) {
        setLastViewed(context, null, lastSource);
    }

    public static void setLastViewed(Context context, LastViewedScreen lastScreen, LastViewedSource lastSource) {
        SharedPreferences.Editor editorEdit = getSharedPreference(context).edit();
        if (lastScreen != null) {
            editorEdit.putString("lastViewedScreen", lastScreen.toString());
        }
        editorEdit.putLong("lastViewedTimeStamp", System.currentTimeMillis());
        if (lastSource != null) {
            editorEdit.putString("lastViewedSource", lastSource.toString());
            for (OnLastViewedSourceChangedListener onLastViewedSourceChangedListener : mOnLastViewedSourceChangedListeners) {
                if (onLastViewedSourceChangedListener != null) {
                    onLastViewedSourceChangedListener.onLastViewChanged();
                }
            }
        }
        editorEdit.apply();
    }
}
