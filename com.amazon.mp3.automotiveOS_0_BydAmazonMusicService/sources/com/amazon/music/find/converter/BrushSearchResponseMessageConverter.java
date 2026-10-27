package com.amazon.music.find.converter;

import com.amazon.music.find.converter.model.SearchResponseMessageBrushV2;
import com.amazon.music.find.model.SearchResponseMessage;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.base.BaseViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: BrushSearchResponseMessageConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0002H\u0016J\u0010\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\bH\u0016¨\u0006\n"}, d2 = {"Lcom/amazon/music/find/converter/BrushSearchResponseMessageConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/music/find/converter/model/SearchResponseMessageBrushV2;", "()V", "convert", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "data", "getFromClass", "Lkotlin/reflect/KClass;", "", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BrushSearchResponseMessageConverter implements SingleBaseModelConverter<SearchResponseMessageBrushV2> {
    public KClass<? extends Object> getFromClass() {
        return Reflection.getOrCreateKotlinClass(SearchResponseMessageBrushV2.class);
    }

    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(SearchResponseMessageBrushV2 data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return new SearchResponseMessage(data.getViewId(), data.getSecondaryLinkText(), data.getNoResultFound(), data.getSpellCorrections(), data.getPrimaryMessage(), data.getSecondaryMessage(), data.getPrimaryMessageFontStyle(), data.getSecondaryMessageFontStyle(), data.getSpacerBetweenMessages(), data.getShouldCenterMessage());
    }
}
