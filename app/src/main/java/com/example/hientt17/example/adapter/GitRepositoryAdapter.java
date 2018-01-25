package com.example.hientt17.example.adapter;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hientt17.example.BR;  // todo new
import com.example.hientt17.example.R;
import com.example.hientt17.example.model.GitHubRepository;

import java.util.List;

/**
 * Created by HienTT17 on 1/24/2018.
 */

public class GitRepositoryAdapter extends RecyclerView.Adapter<GitRepositoryAdapter.ViewHoder>{


    private List<GitHubRepository> mMyModels;

    public GitRepositoryAdapter(List<GitHubRepository> myModels) {
        mMyModels = myModels;
    }

    @Override
    public ViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_item_list, parent , false);
        return new ViewHoder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHoder holder, int position) {
        ViewDataBinding viewDataBinding = holder.getViewDataBinding();
        viewDataBinding.setVariable(BR.modelGithub, mMyModels.get(position));
    }

    public void notifyDataChange(List<GitHubRepository> listRep){
        mMyModels = listRep;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (null != mMyModels ? mMyModels.size() : 0);
    }

    public static class ViewHoder extends RecyclerView.ViewHolder{

        private ViewDataBinding mViewDataBinding;

        public ViewHoder( ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());

            mViewDataBinding = viewDataBinding;
            mViewDataBinding.executePendingBindings();
        }

        public ViewDataBinding getViewDataBinding() {
            return mViewDataBinding;
        }

    }
}
