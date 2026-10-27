package com.amazon.music.search;

import com.amazon.tenzing.textsearch.v1_1.AutoCorrection;
import com.amazon.tenzing.textsearch.v1_1.DidYouMeanCorrection;
import com.amazon.tenzing.textsearch.v1_1.SpellCorrection;
import com.amazon.tenzing.textsearch.v1_1.SpellCorrectionResponse;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class SpellCorrections {
    private AutoCorrection mAutoCorrection;
    private String mAutoCorrectionId;
    private List<String> mAutoCorrectionText;
    private String mBlockRef;
    private DidYouMeanCorrection mDidYouMeanCorrection;
    private String mDidYouMeanCorrectionId;
    private String mDidYouMeanCorrectionText;

    static SpellCorrections create(SpellCorrectionResponse spellCorrectionResponse) {
        SpellCorrection offer = spellCorrectionResponse.getOffer();
        SpellCorrection executed = spellCorrectionResponse.getExecuted();
        String blockRef = spellCorrectionResponse.getBlockRef();
        if (offer == null && executed == null) {
            return null;
        }
        return new SpellCorrections(offer == null ? null : offer.getDidYouMean(), executed != null ? executed.getAutoCorrect() : null, blockRef);
    }

    public boolean hasDidYouMeanCorrection() {
        return this.mDidYouMeanCorrection != null;
    }

    public boolean hasAutoCorrection() {
        return this.mAutoCorrection != null;
    }

    public boolean hasCorrection() {
        return hasDidYouMeanCorrection() || hasAutoCorrection();
    }

    public String getDidYouMeanCorrectionText() {
        return this.mDidYouMeanCorrectionText;
    }

    public String getDidYouMeanCorrectionId() {
        return this.mDidYouMeanCorrectionId;
    }

    public List<String> getAutoCorrectionText() {
        return this.mAutoCorrectionText;
    }

    public String getAutoCorrectionId() {
        return this.mAutoCorrectionId;
    }

    public String getBlockRef() {
        return this.mBlockRef;
    }

    private SpellCorrections(DidYouMeanCorrection didYouMeanCorrection, AutoCorrection autoCorrection, String str) {
        this.mDidYouMeanCorrection = didYouMeanCorrection;
        this.mAutoCorrection = autoCorrection;
        this.mBlockRef = str;
        if (hasDidYouMeanCorrection()) {
            this.mDidYouMeanCorrectionText = this.mDidYouMeanCorrection.getText();
            this.mDidYouMeanCorrectionId = this.mDidYouMeanCorrection.getId();
        }
        if (hasAutoCorrection()) {
            this.mAutoCorrectionText = new ArrayList(this.mAutoCorrection.getText());
            this.mAutoCorrectionId = this.mAutoCorrection.getId();
        }
    }
}
