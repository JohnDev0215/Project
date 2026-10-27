package defpackage;

import org.chromium.chrome.R$string;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes2.dex
 */
/* JADX INFO: renamed from: w0, reason: case insensitive filesystem */
/* JADX INFO: compiled from: AccountPickerBottomSheetDefaultStrings.java */
/* JADX INFO: loaded from: classes2.dex */
public final class C0217w0 implements InterfaceC0223z0 {
    public final /* synthetic */ int a;

    @Override // defpackage.InterfaceC0223z0
    public final int a() {
        switch (this.a) {
            case 0:
                return R$string.signin_account_picker_dismiss_button;
            default:
                return R$string.cancel;
        }
    }

    @Override // defpackage.InterfaceC0223z0
    public final int b() {
        switch (this.a) {
            case 0:
                return R$string.signin_account_picker_bottom_sheet_subtitle;
            default:
                return R$string.signin_account_picker_bottom_sheet_subtitle_for_send_tab_to_self;
        }
    }

    @Override // defpackage.InterfaceC0223z0
    public final int getTitle() {
        switch (this.a) {
            case 0:
                return R$string.signin_account_picker_dialog_title;
            default:
                return R$string.signin_account_picker_bottom_sheet_title_for_send_tab_to_self;
        }
    }
}
