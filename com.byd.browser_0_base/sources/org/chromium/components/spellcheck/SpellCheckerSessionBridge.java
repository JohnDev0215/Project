package org.chromium.components.spellcheck;

import J.N;
import android.view.textservice.SentenceSuggestionsInfo;
import android.view.textservice.SpellCheckerSession;
import android.view.textservice.SuggestionsInfo;
import android.view.textservice.TextInfo;
import android.view.textservice.TextServicesManager;
import defpackage.xz;
import java.util.ArrayList;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class SpellCheckerSessionBridge implements SpellCheckerSession.SpellCheckerSessionListener {
    public long a;
    public final SpellCheckerSession b = ((TextServicesManager) xz.a.getSystemService("textservices")).newSpellCheckerSession(null, null, this, true);

    public SpellCheckerSessionBridge(long j) {
        this.a = j;
    }

    @CalledByNative
    public static SpellCheckerSessionBridge create(long j) {
        SpellCheckerSessionBridge spellCheckerSessionBridge = new SpellCheckerSessionBridge(j);
        if (spellCheckerSessionBridge.b == null) {
            return null;
        }
        return spellCheckerSessionBridge;
    }

    @CalledByNative
    public final void disconnect() {
        this.a = 0L;
        SpellCheckerSession spellCheckerSession = this.b;
        spellCheckerSession.cancel();
        spellCheckerSession.close();
    }

    @Override // android.view.textservice.SpellCheckerSession.SpellCheckerSessionListener
    public final void onGetSentenceSuggestions(SentenceSuggestionsInfo[] sentenceSuggestionsInfoArr) {
        if (this.a == 0) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (SentenceSuggestionsInfo sentenceSuggestionsInfo : sentenceSuggestionsInfoArr) {
            if (sentenceSuggestionsInfo != null) {
                for (int i = 0; i < sentenceSuggestionsInfo.getSuggestionsCount(); i++) {
                    if ((sentenceSuggestionsInfo.getSuggestionsInfoAt(i).getSuggestionsAttributes() & 2) == 2) {
                        arrayList.add(Integer.valueOf(sentenceSuggestionsInfo.getOffsetAt(i)));
                        arrayList2.add(Integer.valueOf(sentenceSuggestionsInfo.getLengthAt(i)));
                        SuggestionsInfo suggestionsInfoAt = sentenceSuggestionsInfo.getSuggestionsInfoAt(i);
                        ArrayList arrayList4 = new ArrayList();
                        for (int i2 = 0; i2 < suggestionsInfoAt.getSuggestionsCount(); i2++) {
                            String suggestionAt = suggestionsInfoAt.getSuggestionAt(i2);
                            if (suggestionAt.charAt(suggestionAt.length() - 1) == 8203) {
                                suggestionAt = suggestionAt.substring(0, suggestionAt.length() - 1);
                            }
                            arrayList4.add(suggestionAt);
                        }
                        arrayList3.add((String[]) arrayList4.toArray(new String[arrayList4.size()]));
                    }
                }
            }
        }
        long j = this.a;
        int size = arrayList.size();
        int[] iArr = new int[size];
        for (int i3 = 0; i3 < size; i3++) {
            iArr[i3] = ((Integer) arrayList.get(i3)).intValue();
        }
        int size2 = arrayList2.size();
        int[] iArr2 = new int[size2];
        for (int i4 = 0; i4 < size2; i4++) {
            iArr2[i4] = ((Integer) arrayList2.get(i4)).intValue();
        }
        N.M3JV9hBl(j, this, iArr, iArr2, (String[][]) arrayList3.toArray(new String[arrayList3.size()][]));
    }

    @CalledByNative
    public final void requestTextCheck(String str) {
        if (str.endsWith(".")) {
            str = str.substring(0, str.length() - 1);
        }
        this.b.getSentenceSuggestions(new TextInfo[]{new TextInfo(str)}, 5);
    }

    @Override // android.view.textservice.SpellCheckerSession.SpellCheckerSessionListener
    public final void onGetSuggestions(SuggestionsInfo[] suggestionsInfoArr) {
    }
}
