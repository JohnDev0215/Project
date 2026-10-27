package com.amazon.mp3.weblab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'DM_CURATE_PLAYLIST_RECOMMENDATIONS_AA' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public final class Weblab {
    private static final /* synthetic */ Weblab[] $VALUES;
    public static final Weblab DM_ANDROID_CLOUD_QUEUE;
    public static final Weblab DM_ANDROID_FACETED_NAV_EXPERIMENT;
    public static final Weblab DM_ANDROID_FACETED_NAV_LAUNCH;
    public static final Weblab DM_ANDROID_IN_APP_BILLING;
    public static final Weblab DM_ANDROID_SETTINGS_CANCEL_MEMBERSHIP;
    public static final Weblab DM_ASTERIX_ANDROID_HAPTIC_FEEDBACK_ON_PLAYBACK_AA;
    public static final Weblab DM_ASTERIX_ANDROID_HAPTIC_FEEDBACK_ON_PLAYBACK_AB;
    public static final Weblab DM_ASTERIX_ANDROID_HAPTIC_FEEDBACK_ON_PLAYBACK_LAUNCH;
    public static final Weblab DM_ASTERIX_ANDROID_NOTIFICATION_EARLY_USE_AA;
    public static final Weblab DM_ASTERIX_ANDROID_NOTIFICATION_EARLY_USE_AB;
    public static final Weblab DM_ASTERIX_ANDROID_NOTIFICATION_EARLY_USE_LAUNCH;
    public static final Weblab DM_ASTERIX_ANDROID_SFA_PRIME_CUSTOMERS_AA_V3;
    public static final Weblab DM_ASTERIX_ANDROID_SFA_PRIME_CUSTOMERS_AB_V3;
    public static final Weblab DM_ASTERIX_ARTIST_NAME_DETAIL_LINK_AA;
    public static final Weblab DM_ASTERIX_ARTIST_NAME_DETAIL_LINK_AB;
    public static final Weblab DM_CCMP_ANDROID_SPLASH_TRANSITION;
    public static final Weblab DM_CURATE_LIKES_ON_NOW_PLAYING_AA;
    public static final Weblab DM_CURATE_LIKES_ON_NOW_PLAYING_AB;
    public static final Weblab DM_CURATE_LIKES_ON_NOW_PLAYING_FIRE_TABLET_AA;
    public static final Weblab DM_CURATE_LIKES_ON_NOW_PLAYING_FIRE_TABLET_AB;
    public static final Weblab DM_CURATE_MY_LIBRARY_V2_FRONT_PAGE_AA;
    public static final Weblab DM_CURATE_MY_LIBRARY_V2_FRONT_PAGE_AB;
    public static final Weblab DM_CURATE_MY_LIBRARY_V2_FRONT_PAGE_FIRE_TABLET_AA;
    public static final Weblab DM_CURATE_MY_LIBRARY_V2_FRONT_PAGE_FIRE_TABLET_AB;
    public static final Weblab DM_CURATE_MY_LIKES_IN_MADE_FOR_YOU_FIRE_TABLET_AA;
    public static final Weblab DM_CURATE_MY_LIKES_IN_MADE_FOR_YOU_FIRE_TABLET_AB;
    public static final Weblab DM_CURATE_NOW_PLAYING_IMPROVEMENTS_AA;
    public static final Weblab DM_CURATE_NOW_PLAYING_IMPROVEMENTS_AB;
    public static final Weblab DM_CURATE_NOW_PLAYING_IMPROVEMENTS_FIRE_TABLET_AA;
    public static final Weblab DM_CURATE_NOW_PLAYING_IMPROVEMENTS_FIRE_TABLET_AB;
    public static final Weblab DM_CURATE_PLAYLIST_DETAIL_BAUHAUS_AA;
    public static final Weblab DM_CURATE_PLAYLIST_DETAIL_BAUHAUS_AB;
    public static final Weblab DM_CURATE_PLAYLIST_DETAIL_BAUHAUS_TABLET_AA;
    public static final Weblab DM_CURATE_PLAYLIST_DETAIL_BAUHAUS_TABLET_AB;
    public static final Weblab DM_CURATE_PLAYLIST_RECOMMENDATIONS_AA;
    public static final Weblab DM_CURATE_PLAYLIST_RECOMMENDATIONS_AB;
    public static final Weblab DM_CURATE_PLAYLIST_RECOMMENDATIONS_TABLET_AA;
    public static final Weblab DM_CURATE_PLAYLIST_RECOMMENDATIONS_TABLET_AB;
    public static final Weblab DM_EARLYUSE_RESTART_SIGNIN_OPTIMIZATION;
    public static final Weblab DM_EXPERIMENT_CATEGORY_PAGE_YOUNG_ADULTS;
    public static final Weblab DM_IMMERSE_STAGE_MUSIC_PODCAST_ANDROID;
    public static final Weblab DM_LAUNCH_CATEGORY_PAGE_YOUNG_ADULTS;
    public static final Weblab DM_LPN_OFFLINE_DOWNLOAD_BUTTON_AA;
    public static final Weblab DM_LPN_OFFLINE_DOWNLOAD_BUTTON_AB;
    public static final Weblab DM_LPN_OFFLINE_DOWNLOAD_BUTTON_PHASE_2_AA;
    public static final Weblab DM_LPN_OFFLINE_DOWNLOAD_BUTTON_PHASE_2_AB;
    public static final Weblab DM_MANAGE_FAMILY_PAGE;
    public static final Weblab DM_MUSICACCESS_AMCONNECT_AUTO_CONNECT_ANDROID_EXPERIMENT;
    public static final Weblab DM_MUSICACCESS_AMCONNECT_CASTING_INGRESS_ANDROID_EXPERIMENT;
    public static final Weblab DM_MUSICACCESS_AMCONNECT_CASTING_UX_UPDATES_ANDROID;
    public static final Weblab DM_SUBS_1CLICK_CONCURRENCY_FAMILY_SIGNUP_UPSELL_AA;
    public static final Weblab DM_SUBS_1CLICK_CONCURRENCY_FAMILY_SIGNUP_UPSELL_AB;
    public static final Weblab DM_SUBS_1CLICK_CONCURRENCY_FAMILY_UPGRADE_UPSELL_AA;
    public static final Weblab DM_SUBS_1CLICK_CONCURRENCY_FAMILY_UPGRADE_UPSELL_AB;
    public static final Weblab DM_SUBS_FAMILY_1CLICK_SIGNUP_AA;
    public static final Weblab DM_SUBS_FAMILY_1CLICK_SIGNUP_AB;
    public static final Weblab DM_TRACKLOOKUP_BROWSECONTENTSELECTOR_ALLSTREAMABLE;
    public static final Weblab MUSICSTATIONS_ENTITY_PLAYLIST_CONTEXT_MENU_AA;
    public static final Weblab MUSICSTATIONS_ENTITY_PLAYLIST_CONTEXT_MENU_AB;
    private final String weblabName;

    public static Weblab valueOf(String name) {
        return (Weblab) Enum.valueOf(Weblab.class, name);
    }

    public static Weblab[] values() {
        return (Weblab[]) $VALUES.clone();
    }

    static {
        Weblab weblab = new Weblab("DM_CCMP_ANDROID_SPLASH_TRANSITION", 0, "CCMP_DYN_MSG_ANDROID_V2_SPLASH_TRANSITION_391962");
        DM_CCMP_ANDROID_SPLASH_TRANSITION = weblab;
        Weblab weblab2 = new Weblab("DM_ASTERIX_ANDROID_SFA_PRIME_CUSTOMERS_AA_V3", 1, "DM_ASTERIX_ANDROID_SFA_PRIME_10PER_AA_V3_330999");
        DM_ASTERIX_ANDROID_SFA_PRIME_CUSTOMERS_AA_V3 = weblab2;
        Weblab weblab3 = new Weblab("DM_ASTERIX_ANDROID_SFA_PRIME_CUSTOMERS_AB_V3", 2, "DM_ASTERIX_ANDROID_SFA_PRIME_10PER_AB_V3_328389");
        DM_ASTERIX_ANDROID_SFA_PRIME_CUSTOMERS_AB_V3 = weblab3;
        Weblab weblab4 = new Weblab("DM_ASTERIX_ARTIST_NAME_DETAIL_LINK_AA", 3, "DM_ASTERIX_ARTIST_NAME_DETAIL_LINK_343668");
        DM_ASTERIX_ARTIST_NAME_DETAIL_LINK_AA = weblab4;
        Weblab weblab5 = new Weblab("DM_ASTERIX_ARTIST_NAME_DETAIL_LINK_AB", 4, "DM_ASTERIX_ARTIST_NAME_DETAIL_LINK_365450");
        DM_ASTERIX_ARTIST_NAME_DETAIL_LINK_AB = weblab5;
        ExperimentWeblabs experimentWeblabs = ExperimentWeblabs.DM_CURATE_PLAYLIST_RECOMMENDATIONS;
        Weblab weblab6 = new Weblab("DM_CURATE_PLAYLIST_RECOMMENDATIONS_AA", 5, experimentWeblabs.getAaWeblab());
        DM_CURATE_PLAYLIST_RECOMMENDATIONS_AA = weblab6;
        Weblab weblab7 = new Weblab("DM_CURATE_PLAYLIST_RECOMMENDATIONS_AB", 6, experimentWeblabs.getAbWeblab());
        DM_CURATE_PLAYLIST_RECOMMENDATIONS_AB = weblab7;
        ExperimentWeblabs experimentWeblabs2 = ExperimentWeblabs.DM_CURATE_PLAYLIST_RECOMMENDATIONS_TABLET;
        Weblab weblab8 = new Weblab("DM_CURATE_PLAYLIST_RECOMMENDATIONS_TABLET_AA", 7, experimentWeblabs2.getAaWeblab());
        DM_CURATE_PLAYLIST_RECOMMENDATIONS_TABLET_AA = weblab8;
        Weblab weblab9 = new Weblab("DM_CURATE_PLAYLIST_RECOMMENDATIONS_TABLET_AB", 8, experimentWeblabs2.getAbWeblab());
        DM_CURATE_PLAYLIST_RECOMMENDATIONS_TABLET_AB = weblab9;
        ExperimentWeblabs experimentWeblabs3 = ExperimentWeblabs.DM_CURATE_PLAYLIST_DETAIL_BAUHAUS;
        Weblab weblab10 = new Weblab("DM_CURATE_PLAYLIST_DETAIL_BAUHAUS_AA", 9, experimentWeblabs3.getAaWeblab());
        DM_CURATE_PLAYLIST_DETAIL_BAUHAUS_AA = weblab10;
        Weblab weblab11 = new Weblab("DM_CURATE_PLAYLIST_DETAIL_BAUHAUS_AB", 10, experimentWeblabs3.getAbWeblab());
        DM_CURATE_PLAYLIST_DETAIL_BAUHAUS_AB = weblab11;
        ExperimentWeblabs experimentWeblabs4 = ExperimentWeblabs.DM_CURATE_PLAYLIST_DETAIL_BAUHAUS_TABLET;
        Weblab weblab12 = new Weblab("DM_CURATE_PLAYLIST_DETAIL_BAUHAUS_TABLET_AA", 11, experimentWeblabs4.getAaWeblab());
        DM_CURATE_PLAYLIST_DETAIL_BAUHAUS_TABLET_AA = weblab12;
        Weblab weblab13 = new Weblab("DM_CURATE_PLAYLIST_DETAIL_BAUHAUS_TABLET_AB", 12, experimentWeblabs4.getAbWeblab());
        DM_CURATE_PLAYLIST_DETAIL_BAUHAUS_TABLET_AB = weblab13;
        ExperimentWeblabs experimentWeblabs5 = ExperimentWeblabs.DM_SUBS_CONCURRENCY_ONE_CLICK_SIGNUP;
        Weblab weblab14 = new Weblab("DM_SUBS_FAMILY_1CLICK_SIGNUP_AA", 13, experimentWeblabs5.getAaWeblab());
        DM_SUBS_FAMILY_1CLICK_SIGNUP_AA = weblab14;
        Weblab weblab15 = new Weblab("DM_SUBS_FAMILY_1CLICK_SIGNUP_AB", 14, experimentWeblabs5.getAbWeblab());
        DM_SUBS_FAMILY_1CLICK_SIGNUP_AB = weblab15;
        Weblab weblab16 = new Weblab("DM_SUBS_1CLICK_CONCURRENCY_FAMILY_SIGNUP_UPSELL_AA", 15, "DIGITAL_MUSIC_1CLICK_CONCURRENCY_FAMILYPLAN_ANDROID_AA_289420");
        DM_SUBS_1CLICK_CONCURRENCY_FAMILY_SIGNUP_UPSELL_AA = weblab16;
        Weblab weblab17 = new Weblab("DM_SUBS_1CLICK_CONCURRENCY_FAMILY_SIGNUP_UPSELL_AB", 16, "DIGITAL_MUSIC_1CLICK_CONCURRENCY_FAMILYPLAN_ANDROID_AB_289421");
        DM_SUBS_1CLICK_CONCURRENCY_FAMILY_SIGNUP_UPSELL_AB = weblab17;
        Weblab weblab18 = new Weblab("DM_SUBS_1CLICK_CONCURRENCY_FAMILY_UPGRADE_UPSELL_AA", 17, "DIGITAL_MUSIC_1CLICK_CONCURRENCY_FAMILY_UPGRADE_ANDROID_AA_292463");
        DM_SUBS_1CLICK_CONCURRENCY_FAMILY_UPGRADE_UPSELL_AA = weblab18;
        Weblab weblab19 = new Weblab("DM_SUBS_1CLICK_CONCURRENCY_FAMILY_UPGRADE_UPSELL_AB", 18, "DIGITAL_MUSIC_1CLICK_CONCURRENCY_FAMILY_UPGRADE_ANDROID_AB_292458");
        DM_SUBS_1CLICK_CONCURRENCY_FAMILY_UPGRADE_UPSELL_AB = weblab19;
        Weblab weblab20 = new Weblab("DM_LAUNCH_CATEGORY_PAGE_YOUNG_ADULTS", 19, "CATEGORY_PAGE_YOUNG_ADULTS_502443");
        DM_LAUNCH_CATEGORY_PAGE_YOUNG_ADULTS = weblab20;
        Weblab weblab21 = new Weblab("DM_EXPERIMENT_CATEGORY_PAGE_YOUNG_ADULTS", 20, "DIGITAL_MUSIC_CATEGORY_PAGE_YOUNG_ADULTS_AB_532018");
        DM_EXPERIMENT_CATEGORY_PAGE_YOUNG_ADULTS = weblab21;
        ExperimentWeblabs experimentWeblabs6 = ExperimentWeblabs.DM_CURATE_MY_LIKES_IN_MADE_FOR_YOU_FIRE_TABLET;
        Weblab weblab22 = new Weblab("DM_CURATE_MY_LIKES_IN_MADE_FOR_YOU_FIRE_TABLET_AA", 21, experimentWeblabs6.getAaWeblab());
        DM_CURATE_MY_LIKES_IN_MADE_FOR_YOU_FIRE_TABLET_AA = weblab22;
        Weblab weblab23 = new Weblab("DM_CURATE_MY_LIKES_IN_MADE_FOR_YOU_FIRE_TABLET_AB", 22, experimentWeblabs6.getAbWeblab());
        DM_CURATE_MY_LIKES_IN_MADE_FOR_YOU_FIRE_TABLET_AB = weblab23;
        Weblab weblab24 = new Weblab("DM_MANAGE_FAMILY_PAGE", 23, "DIGITAL_MUSIC_MANAGE_FAMILY_PAGE_ANDROID_311943");
        DM_MANAGE_FAMILY_PAGE = weblab24;
        Weblab weblab25 = new Weblab("DM_ANDROID_SETTINGS_CANCEL_MEMBERSHIP", 24, "DIGITAL_MUSIC_ANDROID_SETTINGS_CANCEL_MEMBERSHIP_446699");
        DM_ANDROID_SETTINGS_CANCEL_MEMBERSHIP = weblab25;
        ExperimentWeblabs experimentWeblabs7 = ExperimentWeblabs.DM_LPN_OFFLINE_DOWNLOAD_BUTTON;
        Weblab weblab26 = new Weblab("DM_LPN_OFFLINE_DOWNLOAD_BUTTON_AA", 25, experimentWeblabs7.getAaWeblab());
        DM_LPN_OFFLINE_DOWNLOAD_BUTTON_AA = weblab26;
        Weblab weblab27 = new Weblab("DM_LPN_OFFLINE_DOWNLOAD_BUTTON_AB", 26, experimentWeblabs7.getAbWeblab());
        DM_LPN_OFFLINE_DOWNLOAD_BUTTON_AB = weblab27;
        Weblab weblab28 = new Weblab("DM_ANDROID_CLOUD_QUEUE", 27, "DM_ANDROID_CLOUD_QUEUE_347182");
        DM_ANDROID_CLOUD_QUEUE = weblab28;
        Weblab weblab29 = new Weblab("DM_CURATE_MY_LIBRARY_V2_FRONT_PAGE_AA", 28, "DM_CURATE_MY_LIBRARY_V2_FRONT_PAGE_AA_340659");
        DM_CURATE_MY_LIBRARY_V2_FRONT_PAGE_AA = weblab29;
        Weblab weblab30 = new Weblab("DM_CURATE_MY_LIBRARY_V2_FRONT_PAGE_AB", 29, "DM_CURATE_MY_LIBRARY_V2_FRONT_PAGE_AB_340657");
        DM_CURATE_MY_LIBRARY_V2_FRONT_PAGE_AB = weblab30;
        Weblab weblab31 = new Weblab("DM_CURATE_MY_LIBRARY_V2_FRONT_PAGE_FIRE_TABLET_AA", 30, "DM_CURATE_MY_LIBRARY_V2_FRONT_PAGE_FIRE_TABLET_AA_340684");
        DM_CURATE_MY_LIBRARY_V2_FRONT_PAGE_FIRE_TABLET_AA = weblab31;
        Weblab weblab32 = new Weblab("DM_CURATE_MY_LIBRARY_V2_FRONT_PAGE_FIRE_TABLET_AB", 31, "DM_CURATE_MY_LIBRARY_V2_FRONT_PAGE_FIRE_TABLET_AB_340664");
        DM_CURATE_MY_LIBRARY_V2_FRONT_PAGE_FIRE_TABLET_AB = weblab32;
        Weblab weblab33 = new Weblab("DM_CURATE_NOW_PLAYING_IMPROVEMENTS_AA", 32, "DM_CURATE_NOW_PLAYING_IMPROVEMENTS_AA_371360");
        DM_CURATE_NOW_PLAYING_IMPROVEMENTS_AA = weblab33;
        Weblab weblab34 = new Weblab("DM_CURATE_NOW_PLAYING_IMPROVEMENTS_AB", 33, "DM_CURATE_NOW_PLAYING_IMPROVEMENTS_AB_371357");
        DM_CURATE_NOW_PLAYING_IMPROVEMENTS_AB = weblab34;
        Weblab weblab35 = new Weblab("DM_CURATE_NOW_PLAYING_IMPROVEMENTS_FIRE_TABLET_AA", 34, "DM_CURATE_NOW_PLAYING_IMPROVEMENTS_FIRE_TABLET_AA_371363");
        DM_CURATE_NOW_PLAYING_IMPROVEMENTS_FIRE_TABLET_AA = weblab35;
        Weblab weblab36 = new Weblab("DM_CURATE_NOW_PLAYING_IMPROVEMENTS_FIRE_TABLET_AB", 35, "DM_CURATE_NOW_PLAYING_IMPROVEMENTS_FIRE_TABLET_AB_371362");
        DM_CURATE_NOW_PLAYING_IMPROVEMENTS_FIRE_TABLET_AB = weblab36;
        Weblab weblab37 = new Weblab("DM_CURATE_LIKES_ON_NOW_PLAYING_AA", 36, "DM_CURATE_LIKES_ON_NOW_PLAYING_AA_496793");
        DM_CURATE_LIKES_ON_NOW_PLAYING_AA = weblab37;
        Weblab weblab38 = new Weblab("DM_CURATE_LIKES_ON_NOW_PLAYING_AB", 37, "DM_CURATE_LIKES_ON_NOW_PLAYING_AB_496790");
        DM_CURATE_LIKES_ON_NOW_PLAYING_AB = weblab38;
        Weblab weblab39 = new Weblab("DM_CURATE_LIKES_ON_NOW_PLAYING_FIRE_TABLET_AA", 38, "DM_CURATE_LIKES_ON_NOW_PLAYING_FIRE_TABLET_AA_496795");
        DM_CURATE_LIKES_ON_NOW_PLAYING_FIRE_TABLET_AA = weblab39;
        Weblab weblab40 = new Weblab("DM_CURATE_LIKES_ON_NOW_PLAYING_FIRE_TABLET_AB", 39, "DM_CURATE_LIKES_ON_NOW_PLAYING_FIRE_TABLET_AB_496794");
        DM_CURATE_LIKES_ON_NOW_PLAYING_FIRE_TABLET_AB = weblab40;
        Weblab weblab41 = new Weblab("DM_TRACKLOOKUP_BROWSECONTENTSELECTOR_ALLSTREAMABLE", 40, "DM_TRACKLOOKUP_BROWSECONTENTSELECTOR_ALLSTREAMABLE_367951");
        DM_TRACKLOOKUP_BROWSECONTENTSELECTOR_ALLSTREAMABLE = weblab41;
        ExperimentWeblabs experimentWeblabs8 = ExperimentWeblabs.MUSICSTATIONS_ENTITY_PLAYLIST_CONTEXT_MENU;
        Weblab weblab42 = new Weblab("MUSICSTATIONS_ENTITY_PLAYLIST_CONTEXT_MENU_AA", 41, experimentWeblabs8.getAaWeblab());
        MUSICSTATIONS_ENTITY_PLAYLIST_CONTEXT_MENU_AA = weblab42;
        Weblab weblab43 = new Weblab("MUSICSTATIONS_ENTITY_PLAYLIST_CONTEXT_MENU_AB", 42, experimentWeblabs8.getAbWeblab());
        MUSICSTATIONS_ENTITY_PLAYLIST_CONTEXT_MENU_AB = weblab43;
        Weblab weblab44 = new Weblab("DM_ANDROID_IN_APP_BILLING", 43, "DM_ANDROID_IN_APP_BILLING_416333");
        DM_ANDROID_IN_APP_BILLING = weblab44;
        Weblab weblab45 = new Weblab("DM_ASTERIX_ANDROID_NOTIFICATION_EARLY_USE_LAUNCH", 44, "DM_ASTERIX_ANDROID_NOTIFICATION_EARLY_USE_LAUNCH_413754");
        DM_ASTERIX_ANDROID_NOTIFICATION_EARLY_USE_LAUNCH = weblab45;
        Weblab weblab46 = new Weblab("DM_ASTERIX_ANDROID_NOTIFICATION_EARLY_USE_AA", 45, "DM_ASTERIX_ANDROID_NOTIFICATION_EARLY_USE_AA_413642");
        DM_ASTERIX_ANDROID_NOTIFICATION_EARLY_USE_AA = weblab46;
        Weblab weblab47 = new Weblab("DM_ASTERIX_ANDROID_NOTIFICATION_EARLY_USE_AB", 46, "DM_ASTERIX_ANDROID_NOTIFICATION_EARLY_USE_AB_413644");
        DM_ASTERIX_ANDROID_NOTIFICATION_EARLY_USE_AB = weblab47;
        ExperimentWeblabs experimentWeblabs9 = ExperimentWeblabs.DM_LPN_OFFLINE_DOWNLOAD_BUTTON_PHASE_2;
        Weblab weblab48 = new Weblab("DM_LPN_OFFLINE_DOWNLOAD_BUTTON_PHASE_2_AA", 47, experimentWeblabs9.getAaWeblab());
        DM_LPN_OFFLINE_DOWNLOAD_BUTTON_PHASE_2_AA = weblab48;
        Weblab weblab49 = new Weblab("DM_LPN_OFFLINE_DOWNLOAD_BUTTON_PHASE_2_AB", 48, experimentWeblabs9.getAbWeblab());
        DM_LPN_OFFLINE_DOWNLOAD_BUTTON_PHASE_2_AB = weblab49;
        Weblab weblab50 = new Weblab("DM_MUSICACCESS_AMCONNECT_CASTING_UX_UPDATES_ANDROID", 49, "MUSICACCESS_AMCONNECT_CASTING_UX_UPDATES_ANDROID_502690");
        DM_MUSICACCESS_AMCONNECT_CASTING_UX_UPDATES_ANDROID = weblab50;
        Weblab weblab51 = new Weblab("DM_ANDROID_FACETED_NAV_LAUNCH", 50, "DM_ANDROID_FACETED_NAV_463273");
        DM_ANDROID_FACETED_NAV_LAUNCH = weblab51;
        Weblab weblab52 = new Weblab("DM_ANDROID_FACETED_NAV_EXPERIMENT", 51, "DIGITAL_MUSIC_ANDROID_FACETED_NAV_494156");
        DM_ANDROID_FACETED_NAV_EXPERIMENT = weblab52;
        Weblab weblab53 = new Weblab("DM_MUSICACCESS_AMCONNECT_CASTING_INGRESS_ANDROID_EXPERIMENT", 52, "DM_MUSICACCESS_AMCONNECT_CASTING_INGRESS_ANDROID_EXPERIMENT_543356");
        DM_MUSICACCESS_AMCONNECT_CASTING_INGRESS_ANDROID_EXPERIMENT = weblab53;
        Weblab weblab54 = new Weblab("DM_MUSICACCESS_AMCONNECT_AUTO_CONNECT_ANDROID_EXPERIMENT", 53, "DM_MUSICACCESS_AMCONNECT_AUTO_CONNECT_ANDROID_EXPERIMENT_548166");
        DM_MUSICACCESS_AMCONNECT_AUTO_CONNECT_ANDROID_EXPERIMENT = weblab54;
        ExperimentWeblabs experimentWeblabs10 = ExperimentWeblabs.DM_ASTERIX_ANDROID_HAPTIC_FEEDBACK_ON_PLAYBACK;
        Weblab weblab55 = new Weblab("DM_ASTERIX_ANDROID_HAPTIC_FEEDBACK_ON_PLAYBACK_AA", 54, experimentWeblabs10.getAaWeblab());
        DM_ASTERIX_ANDROID_HAPTIC_FEEDBACK_ON_PLAYBACK_AA = weblab55;
        Weblab weblab56 = new Weblab("DM_ASTERIX_ANDROID_HAPTIC_FEEDBACK_ON_PLAYBACK_AB", 55, experimentWeblabs10.getAbWeblab());
        DM_ASTERIX_ANDROID_HAPTIC_FEEDBACK_ON_PLAYBACK_AB = weblab56;
        Weblab weblab57 = new Weblab("DM_ASTERIX_ANDROID_HAPTIC_FEEDBACK_ON_PLAYBACK_LAUNCH", 56, "DM_ASTERIX_ANDROID_HAPTIC_FEEDBACK_ON_PLAYBACK_LAUNCH_572097");
        DM_ASTERIX_ANDROID_HAPTIC_FEEDBACK_ON_PLAYBACK_LAUNCH = weblab57;
        Weblab weblab58 = new Weblab("DM_IMMERSE_STAGE_MUSIC_PODCAST_ANDROID", 57, "DM_IMMERSE_STAGE_MUSIC_PODCAST_ANDROID_568937");
        DM_IMMERSE_STAGE_MUSIC_PODCAST_ANDROID = weblab58;
        Weblab weblab59 = new Weblab("DM_EARLYUSE_RESTART_SIGNIN_OPTIMIZATION", 58, "DIGITAL_MUSIC_EARLYUSE_RESTART_SIGNIN_OPTIMIZATION_619027");
        DM_EARLYUSE_RESTART_SIGNIN_OPTIMIZATION = weblab59;
        $VALUES = new Weblab[]{weblab, weblab2, weblab3, weblab4, weblab5, weblab6, weblab7, weblab8, weblab9, weblab10, weblab11, weblab12, weblab13, weblab14, weblab15, weblab16, weblab17, weblab18, weblab19, weblab20, weblab21, weblab22, weblab23, weblab24, weblab25, weblab26, weblab27, weblab28, weblab29, weblab30, weblab31, weblab32, weblab33, weblab34, weblab35, weblab36, weblab37, weblab38, weblab39, weblab40, weblab41, weblab42, weblab43, weblab44, weblab45, weblab46, weblab47, weblab48, weblab49, weblab50, weblab51, weblab52, weblab53, weblab54, weblab55, weblab56, weblab57, weblab58, weblab59};
    }

    private Weblab(String $enum$name, int $enum$ordinal, String weblabName) {
        this.weblabName = weblabName;
    }

    public static String[] getAllWeblabs() {
        ArrayList arrayList = new ArrayList();
        Iterator it = Arrays.asList(values()).iterator();
        while (it.hasNext()) {
            arrayList.add(((Weblab) it.next()).toString());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.weblabName;
    }
}
