package com.aisle.test.ui.home;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import com.aisle.test.Base.BaseFragment;
import com.aisle.test.Base.ViewModelFactory;
import com.aisle.test.R;
import com.aisle.test.interfaces.HomeCallbacks;
import com.aisle.test.viewmodels.DiscoverViewModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;

import javax.inject.Inject;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class DiscoverFragment extends BaseFragment {

    @BindView(R.id.containerScroll)
    ConstraintLayout mainContainer;
    @BindView(R.id.txvNotes)
    AppCompatTextView txvNotes;
    @BindView(R.id.txvPersonalMsg)
    AppCompatTextView txvPersonalMsg;
    @BindView(R.id.cvNotes)
    CardView cvNotes;
    @BindView(R.id.imvNotes)
    AppCompatImageView imvNotes;
    @BindView(R.id.txvNotesReviewAll)
    AppCompatTextView txvNotesReviewAll;
    @BindView(R.id.txvNameAge)
    AppCompatTextView txvNameAge;

    @BindView(R.id.txvInterestedInYou)
    AppCompatTextView txvInterestedInYou;
    @BindView(R.id.txvInterestedInYouDesc)
    AppCompatTextView txvInterestedInYouDesc;
    @BindView(R.id.btnUpgrade)
    AppCompatButton btnUpgrade;
    @BindView(R.id.imvInterestedOne)
    AppCompatImageView imvInterestedOne;
    @BindView(R.id.txvNameOne)
    AppCompatTextView txvNameOne;
    @BindView(R.id.imvInterestedTwo)
    AppCompatImageView imvInterestedTwo;
    @BindView(R.id.txvNameTwo)
    AppCompatTextView txvNameTwo;

    @Inject
    ViewModelFactory viewModelFactory;
    private DiscoverViewModel viewModel;

    public DiscoverFragment() {
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_discover;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this, viewModelFactory).get(DiscoverViewModel.class);
        HomeCallbacks callbacks = (MainActivity) getActivity();
        viewModel.getLoading().observe(getViewLifecycleOwner(), show -> {
            if (callbacks != null) {
                callbacks.showHideLoader(show);
            }
        });
        viewModel.getErrorMessageResourceId().observe(getViewLifecycleOwner(), resId -> {
            if (callbacks != null) {
                callbacks.showError(resId);
            }
        });

        viewModel.getProfiles().observe(getViewLifecycleOwner(), response -> {
            if (response != null && (response.getInvites() != null || response.getLikes() != null)) {
                mainContainer.setVisibility(View.VISIBLE);
            }
            if (response != null && response.getInvites() != null
                    && response.getInvites().getProfiles() != null
                    && !response.getInvites().getProfiles().isEmpty()) {
                txvNotes.setVisibility(View.VISIBLE);
                txvPersonalMsg.setVisibility(View.VISIBLE);
                cvNotes.setVisibility(View.VISIBLE);
                txvNotesReviewAll.setVisibility(View.VISIBLE);
                txvNameAge.setVisibility(View.VISIBLE);
                txvNameAge.setText(getString(R.string.x_x, response.getInvites().getProfiles().get(0).getGeneralInformation().getFirstName(),
                        response.getInvites().getProfiles().get(0).getGeneralInformation().getAge()));
                Glide.with(view)
                        .load(response.getInvites().getProfiles().get(0).getPhotos().get(0).getPhoto())
                        .centerCrop()
                        .into(imvNotes);
            }

            if (response != null && response.getLikes() != null
                    && response.getLikes().getProfiles() != null
                    && !response.getLikes().getProfiles().isEmpty()) {
                txvInterestedInYou.setVisibility(View.VISIBLE);
                if (!response.getLikes().getCanSeeProfile()) {
                    txvInterestedInYouDesc.setVisibility(View.VISIBLE);
                    btnUpgrade.setVisibility(View.VISIBLE);
                }
                imvInterestedOne.setVisibility(View.VISIBLE);
                RequestBuilder<Drawable> rb1 = Glide.with(view)
                        .load(response.getLikes().getProfiles().get(0).getAvatar());
                if (!response.getLikes().getCanSeeProfile()) {
                    rb1 = rb1.apply(RequestOptions.bitmapTransform(new BlurTransformation(50)));
                }
                rb1.into(imvInterestedOne);
                txvNameOne.setVisibility(View.VISIBLE);
                txvNameOne.setText(response.getLikes().getProfiles().get(0).getFirstName());

                if (response.getLikes().getProfiles().size() > 1) {
                    imvInterestedTwo.setVisibility(View.VISIBLE);
                    RequestBuilder<Drawable> rb2 = Glide.with(view)
                            .load(response.getLikes().getProfiles().get(1).getAvatar());
                    if (!response.getLikes().getCanSeeProfile()) {
                        rb2 = rb2.apply(RequestOptions.bitmapTransform(new BlurTransformation(50)));
                    }
                    rb2.into(imvInterestedTwo);
                    txvNameTwo.setVisibility(View.VISIBLE);
                    txvNameTwo.setText(response.getLikes().getProfiles().get(1).getFirstName());
                }
            }
        });
    }
}
