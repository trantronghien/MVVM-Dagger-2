package com.example.hientt17.example.view.adapter;

import android.databinding.BindingAdapter;
import android.view.View;

/**
 * Created by HienTT17 on 1/25/2018.
 */

public class CustomBindingAdapter {
    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
