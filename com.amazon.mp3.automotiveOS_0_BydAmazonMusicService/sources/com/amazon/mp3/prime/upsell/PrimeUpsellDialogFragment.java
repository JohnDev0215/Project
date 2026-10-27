package com.amazon.mp3.prime.upsell;

import android.app.Activity;
import android.os.Bundle;
import com.amazon.mp3.account.details.AccountDetailStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.environment.MusicTerritory;
import com.amazon.mp3.prime.Branding;
import com.amazon.mp3.prime.upsell.PrimeDialogFragment;

/* JADX INFO: loaded from: classes4.dex */
public class PrimeUpsellDialogFragment extends PrimeDialogFragment {
    public static PrimeUpsellDialogFragment newInstance(final Bundle arguments) {
        PrimeUpsellDialogFragment primeUpsellDialogFragment = new PrimeUpsellDialogFragment();
        primeUpsellDialogFragment.setArguments(arguments);
        return primeUpsellDialogFragment;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDialogListener((PrimeDialogFragment.PrimeDialogListener) getActivity());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof PrimeDialogFragment.PrimeDialogListener) {
            setDialogListener((PrimeDialogFragment.PrimeDialogListener) activity);
            return;
        }
        throw new IllegalArgumentException("Activity must implement PrimeDialogListener.");
    }

    @Override // com.amazon.mp3.prime.upsell.PrimeDialogFragment
    protected String getTitle() {
        String primeBranding = Branding.getPrimeBranding(getActivity());
        String musicTerritoryOfResidence = AccountDetailUtil.getMusicTerritoryOfResidence();
        if (MusicTerritory.CANADA.equalsIgnoreCase(musicTerritoryOfResidence)) {
            return getString(R.string.dmusic_prime_account_upsell_dialog_title_ca, primeBranding);
        }
        if (MusicTerritory.INDIA.equalsIgnoreCase(musicTerritoryOfResidence)) {
            return getString(R.string.dmusic_prime_account_upsell_dialog_title_in, primeBranding);
        }
        return getString(R.string.dmusic_prime_account_upsell_dialog_title, primeBranding);
    }

    @Override // com.amazon.mp3.prime.upsell.PrimeDialogFragment
    protected String getMessage() {
        String primeBranding = Branding.getPrimeBranding(getActivity());
        String musicTerritoryOfResidence = AccountDetailUtil.getMusicTerritoryOfResidence();
        if (MusicTerritory.CANADA.equalsIgnoreCase(musicTerritoryOfResidence)) {
            return getString(R.string.dmusic_prime_account_upsell_dialog_message_ca, primeBranding);
        }
        if (MusicTerritory.INDIA.equalsIgnoreCase(musicTerritoryOfResidence)) {
            return getString(R.string.dmusic_prime_account_upsell_dialog_message_in, primeBranding);
        }
        return getString(AccountDetailUtil.get().shouldAvoidFreeWordingForPrime() ? R.string.dmusic_prime_account_upsell_dialog_message_without_free : R.string.dmusic_prime_account_upsell_dialog_message, primeBranding);
    }

    @Override // com.amazon.mp3.prime.upsell.PrimeDialogFragment
    protected String getAcceptButton() {
        if (AccountDetailUtil.get(getActivity()).isRowPrimeOnlyMarketplace()) {
            return getString(R.string.dmusic_prime_account_upsell_dialog_positive_btn_territories);
        }
        return AccountDetailStorage.get().isFreeTrialEligible() ? getString(R.string.dmusic_amp_unlimited_start_trial) : getString(R.string.dmusic_prime_account_upsell_dialog_title, Branding.getPrimeBranding(getActivity()));
    }

    @Override // com.amazon.mp3.prime.upsell.PrimeDialogFragment
    protected String getDismissButton() {
        if (AccountDetailUtil.get(getActivity()).isRowPrimeOnlyMarketplace()) {
            return getString(R.string.dmusic_prime_account_upsell_dialog_negative_btn_territories);
        }
        return getString(R.string.dmusic_prime_account_upsell_dialog_negative_btn);
    }
}
