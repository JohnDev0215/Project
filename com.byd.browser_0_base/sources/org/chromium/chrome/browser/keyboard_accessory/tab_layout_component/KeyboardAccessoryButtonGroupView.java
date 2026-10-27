package org.chromium.chrome.browser.keyboard_accessory.tab_layout_component;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import java.util.ArrayList;
import org.chromium.ui.widget.ChromeImageButton;

/* JADX INFO: loaded from: classes.dex */
public class KeyboardAccessoryButtonGroupView extends LinearLayout {
    public final ArrayList<ChromeImageButton> e;

    public interface a {
    }

    public KeyboardAccessoryButtonGroupView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = new ArrayList<>();
        setGravity(17);
    }

    public ArrayList<ChromeImageButton> getButtons() {
        return this.e;
    }

    public void setButtonSelectionListener(a aVar) {
    }
}
