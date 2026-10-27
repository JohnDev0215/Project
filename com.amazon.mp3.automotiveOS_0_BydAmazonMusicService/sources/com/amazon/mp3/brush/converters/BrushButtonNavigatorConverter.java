package com.amazon.mp3.brush.converters;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.baseviews.model.configuration.AlbumModelConfiguration;
import com.amazon.mp3.baseviews.model.configuration.ModelConfiguration;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.ButtonNavigatorModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.musicensembleservice.brush.Album;
import com.amazon.musicensembleservice.brush.Button;
import com.amazon.musicensembleservice.brush.Entity;
import com.amazon.musicensembleservice.brush.NavigationEntity;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushButtonNavigatorConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0013\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u0014\u0010\u0013J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\u0012\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0004\u001a\u00020\u0007H\u0002J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0004\u001a\u00020\nH\u0002J\u0012\u0010\f\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0004\u001a\u00020\u0002H\u0016R$\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushButtonNavigatorConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/musicensembleservice/brush/Entity;", "Lcom/amazon/musicensembleservice/brush/Album;", "data", "Lcom/amazon/music/views/library/models/ButtonNavigatorModel;", "convertAlbum", "Lcom/amazon/musicensembleservice/brush/Button;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "getModelFromButton", "Lcom/amazon/musicensembleservice/brush/NavigationEntity;", "getModelFromNavigationEntity", "convert", "Lcom/amazon/mp3/baseviews/model/configuration/ModelConfiguration;", "modelConfiguration", "Lcom/amazon/mp3/baseviews/model/configuration/ModelConfiguration;", "getModelConfiguration", "()Lcom/amazon/mp3/baseviews/model/configuration/ModelConfiguration;", "setModelConfiguration", "(Lcom/amazon/mp3/baseviews/model/configuration/ModelConfiguration;)V", "<init>", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushButtonNavigatorConverter implements SingleBaseModelConverter<Entity> {
    private ModelConfiguration modelConfiguration;

    public BrushButtonNavigatorConverter() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public BrushButtonNavigatorConverter(ModelConfiguration modelConfiguration) {
        this.modelConfiguration = modelConfiguration;
    }

    public /* synthetic */ BrushButtonNavigatorConverter(ModelConfiguration modelConfiguration, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : modelConfiguration);
    }

    public final void setModelConfiguration(ModelConfiguration modelConfiguration) {
        this.modelConfiguration = modelConfiguration;
    }

    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(Entity data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (data instanceof Album) {
            return convertAlbum((Album) data);
        }
        if (data instanceof Button) {
            return getModelFromButton((Button) data);
        }
        if (data instanceof NavigationEntity) {
            return getModelFromNavigationEntity((NavigationEntity) data);
        }
        return null;
    }

    private final ButtonNavigatorModel convertAlbum(Album data) {
        if (!new AlbumModelConfiguration(null, null, 3, null).shouldShowUpsellOption(BrushConverterUtils.getAlbumMetadata$default(BrushConverterUtils.INSTANCE, data, this.modelConfiguration, null, 4, null))) {
            return null;
        }
        String blockRef = data.getBlockRef();
        String string = AmazonApplication.getContext().getResources().getString(R.string.dmusic_sonicrush_upsell_dialog);
        Intrinsics.checkNotNullExpressionValue(string, "getContext().resources.g…_sonicrush_upsell_dialog)");
        return new ButtonNavigatorModel(blockRef, string, "", null, null, false, ButtonSizeKey.MEDIUM, ButtonStyleKey.GLASS_OUTLINE, SpacerKey.MEDIUM, null, 0, 1592, null);
    }

    private final BaseViewModel getModelFromButton(Button data) {
        String blockRef = data.getBlockRef();
        String title = data.getTitle();
        Intrinsics.checkNotNullExpressionValue(title, "data.title");
        String target = data.getTarget();
        Intrinsics.checkNotNullExpressionValue(target, "data.target");
        return new ButtonNavigatorModel(blockRef, title, target, null, null, false, null, null, null, null, 0, 2040, null);
    }

    private final BaseViewModel getModelFromNavigationEntity(NavigationEntity data) {
        String blockRef = data.getBlockRef();
        String title = data.getTitle();
        Intrinsics.checkNotNullExpressionValue(title, "data.title");
        String target = data.getTarget();
        Intrinsics.checkNotNullExpressionValue(target, "data.target");
        return new ButtonNavigatorModel(blockRef, title, target, null, null, false, null, null, null, null, 0, 2040, null);
    }
}
