package com.amazon.mp3.catalog.fragment.converter;

import android.content.Context;
import android.os.Build;
import android.os.LocaleList;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.catalog.fragment.model.FollowedArtistModel;
import com.amazon.mp3.prime.browse.BrowseFactory;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.views.library.converter.BaseContainerModelConverter;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.DefaultPageHeaderModel;
import com.amazon.music.views.library.models.FeatureBarkerModel;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.VisualRowItemModel;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: LibraryFollowedArtistPageConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0019B\u000f\u0012\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0017\u0010\u0018J\b\u0010\u0004\u001a\u00020\u0003H\u0002J\u0018\u0010\n\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0007H\u0002J*\u0010\u0010\u001a\f\u0012\u0006\b\u0001\u0012\u00020\r\u0018\u00010\u000f2\u0006\u0010\u000b\u001a\u00020\u00022\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fH\u0016J\u001e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0011J\u0018\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016R\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016¨\u0006\u001a"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/converter/LibraryFollowedArtistPageConverter;", "Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "Lcom/amazon/mp3/catalog/fragment/model/FollowedArtistModel;", "Lcom/amazon/music/views/library/models/FeatureBarkerModel;", "getFeatureBarker", "Lcom/amazon/music/views/library/metadata/ArtistMetadata;", "artistMetadata", "", "title", "Lcom/amazon/music/views/library/models/VisualRowItemModel;", "convertToVisualRowItem", "data", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "children", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "convert", "", "pageIndex", "getChildrenModels", "Landroid/content/Context;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "ATCIngressImageUrl", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LibraryFollowedArtistPageConverter implements BaseContainerModelConverter<FollowedArtistModel> {
    private final Context context;

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public List<BaseViewModel> getChildrenModels(FollowedArtistModel data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return null;
    }

    public LibraryFollowedArtistPageConverter(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public /* bridge */ /* synthetic */ BaseViewContainerModel convert(FollowedArtistModel followedArtistModel, List list) {
        return convert2(followedArtistModel, (List<? extends BaseViewModel>) list);
    }

    /* JADX INFO: renamed from: convert, reason: avoid collision after fix types in other method */
    public BaseViewContainerModel<? extends BaseViewModel> convert2(FollowedArtistModel data, List<? extends BaseViewModel> children) {
        Intrinsics.checkNotNullParameter(data, "data");
        return new PageGridViewModel(null, null, null, null, children == null ? CollectionsKt__CollectionsKt.emptyList() : children, 2, 0, data.getNextPageToken(), null, null, null, 1807, null);
    }

    public final List<BaseViewModel> getChildrenModels(FollowedArtistModel data, int pageIndex) {
        Intrinsics.checkNotNullParameter(data, "data");
        ArrayList arrayList = new ArrayList();
        if (pageIndex == 0) {
            arrayList.add(new DefaultPageHeaderModel("", this.context.getString(R.string.dmusic_music_ingress_pill_text), this.context.getString(R.string.dmusic_prime_search_result_artists), Integer.valueOf(R.id.category_text), null, null, null, CollectionsKt__CollectionsKt.emptyList(), null, null, 112, null));
        }
        List<ArtistMetadata> followedArtists = data.getFollowedArtists();
        if (!(followedArtists == null || followedArtists.isEmpty())) {
            for (ArtistMetadata artistMetadata : data.getFollowedArtists()) {
                String title = artistMetadata.getTitle();
                if (title != null) {
                    arrayList.add(convertToVisualRowItem(artistMetadata, title));
                }
            }
        }
        if (StringUtils.isEmpty(data.getNextPageToken()) && Build.VERSION.SDK_INT >= 28) {
            arrayList.add(getFeatureBarker());
        }
        return arrayList;
    }

    private final FeatureBarkerModel getFeatureBarker() {
        boolean zIsTabletDevice = BrowseFactory.isTabletDevice();
        ATCIngressImageUrl aTCIngressImageUrl = ATCIngressImageUrl.INSTANCE;
        String defaultATCIngressImage = aTCIngressImageUrl.getDefaultATCIngressImage(zIsTabletDevice);
        LocaleList locales = this.context.getResources().getConfiguration().getLocales();
        Intrinsics.checkNotNullExpressionValue(locales, "context.resources.configuration.locales");
        if (locales.size() > 0) {
            Locale locale = locales.get(0);
            Intrinsics.checkNotNullExpressionValue(locale, "localeList.get(0)");
            defaultATCIngressImage = aTCIngressImageUrl.getATCImageUrl(locale, zIsTabletDevice);
        }
        String str = defaultATCIngressImage;
        return new FeatureBarkerModel(null, "", null, null, str, str, null, null, true, 205, null);
    }

    private final VisualRowItemModel convertToVisualRowItem(ArtistMetadata artistMetadata, String title) {
        String string = artistMetadata.getUri().toString();
        ArtworkFrameModel artworkFrameModel = new ArtworkFrameModel("", artistMetadata.getImageUrl(), 4, null, null, null, artistMetadata, null, null, null, null, null, 4024, null);
        Boolean bool = Boolean.FALSE;
        return new VisualRowItemModel(null, title, null, null, null, null, null, bool, null, null, null, null, null, artworkFrameModel, null, null, string, null, null, null, null, bool, true, null, 4, artistMetadata, false, false, false, null, false, false, -325132419, null);
    }

    /* JADX INFO: compiled from: LibraryFollowedArtistPageConverter.kt */
    @Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0005\u001a\u00020\u0004R3\u0010\u000b\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\tj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0006`\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR3\u0010\u000f\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\tj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0006`\n8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\f\u001a\u0004\b\u0010\u0010\u000e¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/converter/LibraryFollowedArtistPageConverter$ATCIngressImageUrl;", "", "Ljava/util/Locale;", Splash.PARAMS_LOCALE, "", "isTabletDevice", "", "getATCImageUrl", "getDefaultATCIngressImage", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "mobileLocaleImageMap", "Ljava/util/HashMap;", "getMobileLocaleImageMap", "()Ljava/util/HashMap;", "tabletLocaleImageMap", "getTabletLocaleImageMap", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class ATCIngressImageUrl {
        public static final ATCIngressImageUrl INSTANCE = new ATCIngressImageUrl();
        private static final HashMap<Locale, String> mobileLocaleImageMap;
        private static final HashMap<Locale, String> tabletLocaleImageMap;

        private ATCIngressImageUrl() {
        }

        static {
            HashMap<Locale, String> map = new HashMap<>();
            mobileLocaleImageMap = map;
            HashMap<Locale, String> map2 = new HashMap<>();
            tabletLocaleImageMap = map2;
            map.put(new Locale("en", "US"), "https://m.media-amazon.com/images/G/01/Music/Curate/ATC_Ingress_Widget/Mobile/ATCIngressWidget_en-US.png");
            map.put(new Locale("es", "US"), "https://m.media-amazon.com/images/G/01/Music/Curate/ATC_Ingress_Widget/Mobile/ATCIngressWidget_es_US.png");
            map.put(new Locale("ja", "JP"), "https://m.media-amazon.com/images/G/01/Music/Curate/ATC_Ingress_Widget/Mobile/ATCIngressWidget_JP.png");
            map.put(new Locale("de", "DE"), "https://m.media-amazon.com/images/G/01/Music/Curate/ATC_Ingress_Widget/Mobile/ATCIngressWidget_DE.png");
            map.put(new Locale("fr", "FR"), "https://m.media-amazon.com/images/G/01/Music/Curate/ATC_Ingress_Widget/Mobile/ATCIngressWidget_FR.png");
            map.put(new Locale("fr", "CA"), "https://m.media-amazon.com/images/G/01/Music/Curate/ATC_Ingress_Widget/Mobile/ATCIngressWidget_fr_CA.png");
            map.put(new Locale("it", "IT"), "https://m.media-amazon.com/images/G/01/Music/Curate/ATC_Ingress_Widget/Mobile/ATCIngressWidget_IT.png");
            map.put(new Locale("pt", "BR"), "https://m.media-amazon.com/images/G/01/Music/Curate/ATC_Ingress_Widget/Mobile/ATCIngressWidget_BR.png");
            map.put(new Locale("en", "AU"), "https://m.media-amazon.com/images/G/01/Music/Curate/ATC_Ingress_Widget/Mobile/ATCIngressWidget_AU.png");
            map.put(new Locale("en", "CA"), "https://m.media-amazon.com/images/G/01/Music/Curate/ATC_Ingress_Widget/Mobile/ATCIngressWidget_en_CA.png");
            map.put(new Locale("en", "GB"), "https://m.media-amazon.com/images/G/01/Music/Curate/ATC_Ingress_Widget/Mobile/ATCIngressWidget_GB.png");
            map.put(new Locale("es", "MX"), "https://m.media-amazon.com/images/G/01/Music/Curate/ATC_Ingress_Widget/Mobile/ATCIngressWidget_MX.png");
            map.put(new Locale("es", "ES"), "https://m.media-amazon.com/images/G/01/Music/Curate/ATC_Ingress_Widget/Mobile/ATCIngressWidget_ES.png");
            map2.put(new Locale("en", "US"), "https://m.media-amazon.com/images/G/01/Music/Curate/ATC_Ingress_Widget/Tablet/ATCIngressWidget_en-US_Ipad.png");
            map2.put(new Locale("es", "US"), "https://m.media-amazon.com/images/G/01/Music/Curate/ATC_Ingress_Widget/Tablet/ATCIngressWidget_es-US_Ipad.png");
            map2.put(new Locale("ja", "JP"), "https://m.media-amazon.com/images/G/01/Music/Curate/ATC_Ingress_Widget/Tablet/ATCIngressWidget_JP_Ipad.png");
            map2.put(new Locale("de", "DE"), "https://m.media-amazon.com/images/G/01/Music/Curate/ATC_Ingress_Widget/Tablet/ATCIngressWidget_DE_Ipad.png");
            map2.put(new Locale("fr", "FR"), "https://m.media-amazon.com/images/G/01/Music/Curate/ATC_Ingress_Widget/Tablet/ATCIngressWidget_FR_Ipad.png");
            map2.put(new Locale("fr", "CA"), "https://m.media-amazon.com/images/G/01/Music/Curate/ATC_Ingress_Widget/Tablet/ATCIngressWidget_fr_CA_Ipad.png");
            map2.put(new Locale("it", "IT"), "https://m.media-amazon.com/images/G/01/Music/Curate/ATC_Ingress_Widget/Tablet/ATCIngressWidget_IT_Ipad.png");
            map2.put(new Locale("pt", "BR"), "https://m.media-amazon.com/images/G/01/Music/Curate/ATC_Ingress_Widget/Tablet/ATCIngressWidget_BR_Ipad.png");
            map2.put(new Locale("en", "AU"), "https://m.media-amazon.com/images/G/01/Music/Curate/ATC_Ingress_Widget/Tablet/ATCIngressWidget_AU_Ipad.png");
            map2.put(new Locale("en", "CA"), "https://m.media-amazon.com/images/G/01/Music/Curate/ATC_Ingress_Widget/Tablet/ATCIngressWidget_en-CA_Ipad.png");
            map2.put(new Locale("en", "GB"), "https://m.media-amazon.com/images/G/01/Music/Curate/ATC_Ingress_Widget/Tablet/ATCIngressWidget_GB_Ipad.png");
            map2.put(new Locale("es", "MX"), "https://m.media-amazon.com/images/G/01/Music/Curate/ATC_Ingress_Widget/Tablet/ATCIngressWidget_MX_Ipad.png");
            map2.put(new Locale("es", "ES"), "https://m.media-amazon.com/images/G/01/Music/Curate/ATC_Ingress_Widget/Tablet/ATCIngressWidget_ES_Ipad.png");
        }

        public final String getATCImageUrl(Locale locale, boolean isTabletDevice) {
            Intrinsics.checkNotNullParameter(locale, "locale");
            if (!isTabletDevice) {
                HashMap<Locale, String> map = mobileLocaleImageMap;
                if (map.get(locale) != null) {
                    return map.get(locale);
                }
                return getDefaultATCIngressImage(isTabletDevice);
            }
            HashMap<Locale, String> map2 = tabletLocaleImageMap;
            if (map2.get(locale) != null) {
                return map2.get(locale);
            }
            return getDefaultATCIngressImage(isTabletDevice);
        }

        public final String getDefaultATCIngressImage(boolean isTabletDevice) {
            if (!isTabletDevice) {
                return mobileLocaleImageMap.get(new Locale("en", "US"));
            }
            return tabletLocaleImageMap.get(new Locale("en", "US"));
        }
    }
}
