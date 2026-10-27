package org.chromium.chrome.browser.signin;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;

/* JADX INFO: loaded from: classes2.dex */
public class LegacySyncPromoView extends LinearLayout {
    public TextView e;
    public TextView f;
    public Button g;

    public static class a implements b {
    }

    public interface b {
    }

    public static class c {
        public final int a;
        public final b b;

        public c(int i, a aVar) {
            this.a = i;
            this.b = aVar;
        }
    }

    public LegacySyncPromoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public static LegacySyncPromoView a(int i, ViewGroup viewGroup) {
        LegacySyncPromoView legacySyncPromoView = (LegacySyncPromoView) LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.legacy_sync_promo_view, viewGroup, false);
        legacySyncPromoView.getClass();
        if (i == 9) {
            legacySyncPromoView.e.setText(R$string.sync_your_bookmarks);
        } else {
            legacySyncPromoView.e.setVisibility(8);
        }
        return legacySyncPromoView;
    }

    private c getStateForStartUsing() {
        return new c(R$string.ntp_recent_tabs_sync_promo_instructions, new a());
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        c stateForStartUsing = getStateForStartUsing();
        TextView textView = this.f;
        Button button = this.g;
        textView.setText(stateForStartUsing.a);
        ((a) stateForStartUsing.b).getClass();
        button.setVisibility(8);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.e = (TextView) findViewById(R$id.title);
        this.f = (TextView) findViewById(R$id.description);
        this.g = (Button) findViewById(R$id.sign_in);
    }
}
