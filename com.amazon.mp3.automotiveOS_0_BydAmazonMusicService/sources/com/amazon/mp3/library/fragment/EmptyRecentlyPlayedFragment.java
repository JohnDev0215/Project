package com.amazon.mp3.library.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.amazon.mp3.eux.EUXProvider;
import com.amazon.music.widget.eux.EUXConfiguration;
import com.amazon.music.widget.eux.adapters.EUXRecentlyPlayedAdapter;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes3.dex */
public class EmptyRecentlyPlayedFragment extends Fragment {
    private EUXRecentlyPlayedAdapter mEuxAdapter;
    private Subscription mSubscription;

    public static EmptyRecentlyPlayedFragment newInstance() {
        return new EmptyRecentlyPlayedFragment();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        EUXRecentlyPlayedAdapter eUXRecentlyPlayedAdapter = new EUXRecentlyPlayedAdapter(getContext());
        this.mEuxAdapter = eUXRecentlyPlayedAdapter;
        return eUXRecentlyPlayedAdapter.getView(null);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mSubscription = Observable.create(new Observable.OnSubscribe<EUXConfiguration>() { // from class: com.amazon.mp3.library.fragment.EmptyRecentlyPlayedFragment.2
            @Override // rx.functions.Action1
            public void call(final Subscriber<? super EUXConfiguration> subscriber) {
                subscriber.onNext(EUXProvider.buildEUXConfiguration(EmptyRecentlyPlayedFragment.this.getContext()));
                subscriber.onCompleted();
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.computation()).subscribe(new Observer<EUXConfiguration>() { // from class: com.amazon.mp3.library.fragment.EmptyRecentlyPlayedFragment.1
            @Override // rx.Observer
            public void onCompleted() {
            }

            @Override // rx.Observer
            public void onError(final Throwable throwable) {
            }

            @Override // rx.Observer
            public void onNext(final EUXConfiguration euxConfiguration) {
                EmptyRecentlyPlayedFragment.this.mEuxAdapter.getView(euxConfiguration);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        Subscription subscription = this.mSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }
}
