package com.stingray.karaoke.car.ui.navigation;

import android.hardware.bydauto.properties.CarSettings;
import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Lcom/stingray/karaoke/car/ui/navigation/NavigationRoutes;", "", "value", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "TABS", "SONG_DETAILS", "SONGS", "ARTISTS", "ABOUT", "FAQ", "CONTACT_US", "DEV_SETTINGS", "SETTINGS", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class NavigationRoutes {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ NavigationRoutes[] $VALUES;
    private final String value;
    public static final NavigationRoutes TABS = new NavigationRoutes("TABS", 0, "tabs");
    public static final NavigationRoutes SONG_DETAILS = new NavigationRoutes("SONG_DETAILS", 1, "song_detail");
    public static final NavigationRoutes SONGS = new NavigationRoutes("SONGS", 2, "songs");
    public static final NavigationRoutes ARTISTS = new NavigationRoutes("ARTISTS", 3, "artists");
    public static final NavigationRoutes ABOUT = new NavigationRoutes("ABOUT", 4, "about");
    public static final NavigationRoutes FAQ = new NavigationRoutes("FAQ", 5, "faq");
    public static final NavigationRoutes CONTACT_US = new NavigationRoutes("CONTACT_US", 6, "contact_us");
    public static final NavigationRoutes DEV_SETTINGS = new NavigationRoutes("DEV_SETTINGS", 7, "dev_settings");
    public static final NavigationRoutes SETTINGS = new NavigationRoutes("SETTINGS", 8, CarSettings.UserTableData.TABLE_NAME_SETTINGS);

    private static final /* synthetic */ NavigationRoutes[] $values() {
        return new NavigationRoutes[]{TABS, SONG_DETAILS, SONGS, ARTISTS, ABOUT, FAQ, CONTACT_US, DEV_SETTINGS, SETTINGS};
    }

    static {
        NavigationRoutes[] navigationRoutesArr$values = $values();
        $VALUES = navigationRoutesArr$values;
        $ENTRIES = r.e(navigationRoutesArr$values);
    }

    private NavigationRoutes(String str, int i7, String str2) {
        this.value = str2;
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static NavigationRoutes valueOf(String str) {
        return (NavigationRoutes) Enum.valueOf(NavigationRoutes.class, str);
    }

    public static NavigationRoutes[] values() {
        return (NavigationRoutes[]) $VALUES.clone();
    }

    public final String getValue() {
        return this.value;
    }
}
